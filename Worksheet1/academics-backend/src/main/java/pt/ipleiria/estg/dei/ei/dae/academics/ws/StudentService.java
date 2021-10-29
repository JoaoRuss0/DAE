package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import pt.ipleiria.estg.dei.ei.dae.academics.dtos.EmailDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.SubjectDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.EmailBean;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.StudentBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.mail.MessagingException;
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

    @EJB
    private EmailBean emailBean;

    @GET
    @Path("/")
    public Response getAllStudents() {
        return Response.ok(toDTOsNoSubjectsStudents(studentBean.getAllStudents())).build();
    }

    @GET
    @Path("/{username}")
    public Response getStudent(@PathParam("username") String username) throws MyEntityNotFoundException {
        Student student = studentBean.findStudent(username);
        return Response.ok(toDTOStudent(student)).build();
    }

    @GET
    @Path("/{username}/subjects")
    public Response getStudentSubjects(@PathParam("username") String username) throws MyEntityNotFoundException {
        Student student = studentBean.findStudent(username);
        return Response.ok(toDTOsSubjects(student.getSubjects())).build();
    }

    @POST
    @Path("/")
    public Response createNewStudent(StudentDTO studentDTO) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        Student student = studentBean.create(
                studentDTO.getUsername(),
                studentDTO.getName(),
                studentDTO.getEmail(),
                studentDTO.getPassword(),
                studentDTO.getCourseCode()
        );
        return Response.status(Response.Status.CREATED).entity(toDTONoSubjectsStudent(student)).build();
    }

    @POST
    @Path("/{username}/email/send")
    public Response sendEmail(@PathParam("username") String username, EmailDTO email) throws MyEntityNotFoundException, MessagingException {
        Student student = studentBean.findStudent(username);

        if (student == null) {
            throw new MyEntityNotFoundException("Student with username '" + username + "' not found in our records.");
        }

        emailBean.send(student.getEmail(), email.getSubject(), email.getMessage());
        return Response.status(Response.Status.OK).entity("E-mail sent").build();
    }

    private List<StudentDTO> toDTOsNoSubjectsStudents(List<Student> students) {
        return students.stream().map(this::toDTONoSubjectsStudent).collect(Collectors.toList());
    }

    private StudentDTO toDTONoSubjectsStudent(Student student) {
        return new StudentDTO(
                student.getUsername(),
                student.getName(),
                student.getEmail(),
                student.getPassword(),
                student.getCourse()
        );
    }

    private StudentDTO toDTOStudent(Student student) {
        return new StudentDTO(
                student.getUsername(),
                student.getName(),
                student.getEmail(),
                student.getPassword(),
                student.getCourse(),
                toDTOsSubjects(student.getSubjects())
        );
    }

    /*
    private List<StudentDTO> toDTOssStudents(List<Student> students) {
        return students.stream().map(this::toDTOStudent).collect(Collectors.toList());
    }
    */

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
