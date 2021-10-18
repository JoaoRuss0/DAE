package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import pt.ipleiria.estg.dei.ei.dae.academics.dtos.CourseDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.CourseBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;

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
    public Response getAllCourses() {
        return Response.ok(toDTOs(courseBean.getAllCourses())).build();
    }

    @GET
    @Path("/{code}")
    public Response getCourse(@PathParam("code") int code) {
        Course course = courseBean.findCourse(code);

        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(toDTO(course)).build();
    }

    @POST
    @Path("/")
    public Response createNewCourse(CourseDTO courseDTO) {
        courseBean.create(courseDTO.getCode(), courseDTO.getName());

        Course course = courseBean.findCourse(courseDTO.getCode());

        if (course == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.CREATED).entity(toDTO(course)).build();
    }

    @PUT
    @Path("/{code}")
    public Response updateCourse(@PathParam("code") int code, CourseDTO courseDTO) {
        Course course = courseBean.findCourse(code);

        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        course = courseBean.update(course, courseDTO.getName());
        return Response.ok(toDTO(course)).build();
    }

    @DELETE
    @Path("/{code}")
    public Response deleteCourse(@PathParam("code") int code) {
        courseBean.deleteCourse(code);

        if (courseBean.findCourse(code) != null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.ACCEPTED).build();
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
