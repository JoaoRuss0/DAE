package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentBean {

    @PersistenceContext
    EntityManager entityManager;

    public Student create(String username, String name, String email, String password, int course_code) {
        Course course = entityManager.find(Course.class, course_code);

        if(course != null) {
            Student student = new Student(username, name, email, password, course);
            course.addStudent(student);
            entityManager.persist(student);

            return student;
        }
        return null;
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

    public Student findStudent(String username) {
        return entityManager.find(Student.class, username);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) entityManager.createNamedQuery("getAllStudents").getResultList();
    }
}
