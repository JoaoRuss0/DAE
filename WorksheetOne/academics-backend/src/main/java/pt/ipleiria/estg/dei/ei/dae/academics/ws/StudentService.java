package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.SubjectDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.StudentBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Path("students")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class StudentService {
    @EJB
    private StudentBean studentBean;

    @GET
    @Path("/")
    public List<StudentDTO> getAllStudentsWS() {
        return toDTOsStudents(studentBean.getAllStudents());
    }

    @GET
    @Path("/{username}")
    public Response getStudentDetails(@PathParam("username") String username) {
        Student student = studentBean.findStudent(username);

        if (student != null) {
            return Response.ok(toDTOStudent(student)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("ERROR_FINDING_STUDENT").build();
    }

    @GET
    @Path("/{username}/subjects")
    public Response getStudentSubjects(@PathParam("username") String username) {
        Student student = studentBean.findStudent(username);

        if (student != null) {
            return Response.ok(toDTOsSubjects(student.getSubjects())).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("ERROR_FINDING_STUDENT").build();
    }

    @POST
    @Path("/")
    public Response createNewStudent(StudentDTO studentDTO) {
        studentBean.create(
                studentDTO.getUsername(),
                studentDTO.getName(),
                studentDTO.getEmail(),
                studentDTO.getPassword(),
                studentDTO.getCourseCode()
        );

        Student newStudent = studentBean.findStudent(studentDTO.getUsername());

        if (newStudent == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.CREATED).entity(toDTOStudent(newStudent)).build();
    }

    private List<StudentDTO> toDTOsStudents(List<Student> students) {
        return students.stream().map(this::toDTOStudent).collect(Collectors.toList());
    }

    private StudentDTO toDTOStudent(Student student) {
        return new StudentDTO(
                student.getUsername(),
                student.getName(),
                student.getEmail(),
                student.getPassword(),
                student.getCourse()
        );
    }

    private List<SubjectDTO> toDTOsSubjects(LinkedHashSet<Subject> subjects) {
        return subjects.stream().map(this::toDTOSubject).collect(Collectors.toList());
    }

    private SubjectDTO toDTOSubject(Subject subject) {
        return new SubjectDTO(
                subject.getCode(),
                subject.getName(),
                subject.getCourse().getCode(),
                subject.getCourseYear(),
                subject.getScholarYear()
        );
    }
}
