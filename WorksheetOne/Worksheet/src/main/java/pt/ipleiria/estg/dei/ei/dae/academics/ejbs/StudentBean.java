package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentBean {

    @PersistenceContext
    EntityManager entityManager;

    public Student create(String username, String name, String email, String password) {
        Student student = new Student(username, name, email, password);
        entityManager.persist(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return (List<Student>) entityManager.createNamedQuery("getAllStudents").getResultList();
    }
}
