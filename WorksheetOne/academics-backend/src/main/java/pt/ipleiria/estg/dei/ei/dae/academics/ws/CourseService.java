package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import pt.ipleiria.estg.dei.ei.dae.academics.dtos.CourseDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.CourseBean;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.StudentBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("courses")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CourseService {
    @EJB
    private CourseBean courseBean;

    @GET
    @Path("/")
    public List<CourseDTO> getAllCourses() {
        return toDTOs(courseBean.getAllCourses());
    }

    @POST
    @Path("/")
    public Response createNewCourse(CourseDTO courseDTO) {
        courseBean.create(courseDTO.getCode(), courseDTO.getName());

        Course course = courseBean.findCourse(courseDTO.getCode());

        if(course == null)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.CREATED).entity(courseDTO).build();
    }

    private List<CourseDTO> toDTOs(List<Course> courses) {
        return courses.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private CourseDTO toDTO(Course course) {
        return new CourseDTO(
            course.getCode(),
            course.getName()
        );
    }
}
