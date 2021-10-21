package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class StudentBean {

    @PersistenceContext
    EntityManager entityManager;

    public Student create(String username, String name, String email, String password, int courseCode) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        if(entityManager.find(Student.class, username) != null) {
            throw new MyEntityExistsException("Student with username " + username + " already exists.");
        }

        Course course = entityManager.find(Course.class, courseCode);

        if(course == null) {
            throw new MyEntityNotFoundException("Course with code " + courseCode + " does not exist!");
        }

        Student student = null;

        try{
            student = new Student(username, name, email, password, course);
            course.addStudent(student);
            entityManager.persist(student);
        } catch(ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }

        return student;
    }

    public void update(String username, String password, String name, String email, int courseCode) {
        Student student = entityManager.find(Student.class, username);

        if(student != null) {
            Course course = entityManager.find(Course.class, courseCode);

            if(course != null) {
                entityManager.lock(student, LockModeType.OPTIMISTIC);
                student.setCourse(course);
                student.setName(name);
                student.setEmail(email);
                student.setPassword(password);
            } else {
                System.err.println("ERROR_FINDING_COURSE");
            }
        } else {
            System.err.println("ERROR_FINDING_STUDENT");
        }
    }

    public Student findStudent(String username) throws MyEntityNotFoundException {
        Student student = entityManager.find(Student.class, username);

        if(student == null) {
            throw new MyEntityNotFoundException("Student with username " + username + " does not exist.");
        }
        return student;
    }

    public List<Student> getAllStudents() {
        return (List<Student>) entityManager.createNamedQuery("getAllStudents").getResultList();
    }
}
