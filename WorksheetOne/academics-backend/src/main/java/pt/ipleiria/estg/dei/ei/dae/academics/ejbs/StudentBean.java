package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentBean {

    @PersistenceContext
    EntityManager entityManager;

    public Student create(String username, String name, String email, String password, int course_code) {
        Course course = entityManager.find(Course.class, 1);

        if(course != null)
        {
            System.out.println("Creating student");
            Student student = new Student(username, name, email, password, course);
            course.addStudent(student);
            entityManager.persist(student);
            return student;
        }
        return null;
    }

    public Student findStudent(String username) {
        return entityManager.find(Student.class, username);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) entityManager.createNamedQuery("getAllStudents").getResultList();
    }
}
