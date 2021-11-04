package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.SubjectDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.SubjectBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Path("subjects")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SubjectsService {

    @EJB
    SubjectBean subjectBean;

    @GET
    @Path("/")
    public Response getAllSubjects() {
        return Response.ok(toDTOsSubjects(subjectBean.getAllSubjects())).build();
    }

    @GET
    @Path("/{code}")
    public Response getSubject(@PathParam("code") int code) {
        Subject subject = subjectBean.findSubject(code);

        if(subject == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return  Response.ok(toDTOSubject(subject)).build();
    }

    @GET
    @Path("/{code}/students")
    public Response getSubjectStudents(@PathParam("code") int code) {
        Subject subject = subjectBean.findSubject(code);

        if(subject == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return  Response.ok(toDTOsStudents(subject.getStudents())).build();
    }

    @POST
    @Path("/")
    public Response createSubject(SubjectDTO subjectDTO) {
        subjectBean.create(
                subjectDTO.getCode(),
                subjectDTO.getName(),
                subjectDTO.getCourseCode(),
                subjectDTO.getCourseYear(),
                subjectDTO.getScholarYear()
        );

        Subject subject = subjectBean.findSubject(subjectDTO.getCode());

        if(subject == null)
        {
            return  Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{code}")
    public Response updateSubject(@PathParam("code") int code, SubjectDTO subjectDTO) {
        Subject subject = subjectBean.findSubject(code);

        if(subject == null)
        {
            return  Response.status(Response.Status.NOT_FOUND).build();
        }

        subject = subjectBean.update(subject, subjectDTO);
        return Response.ok(toDTOSubject(subject)).build();
    }

    @PUT
    @Path("/{code}/enroll/{username}")
    public Response enrollStudentInSubject(@PathParam("code") int code, @PathParam("username") String username) {
        return subjectBean.enrollStudentInSubject(username, code) ?  Response.ok("Enrolled successfully!").build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

    private List<StudentDTO> toDTOsStudents(LinkedHashSet<Student> students) {
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

    private List<SubjectDTO> toDTOsSubjects(List<Subject> subjects) {
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
