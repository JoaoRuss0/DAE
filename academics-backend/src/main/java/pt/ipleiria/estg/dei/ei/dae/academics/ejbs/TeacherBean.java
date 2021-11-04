package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Teacher;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TeacherBean {

    @PersistenceContext
    EntityManager entityManager;

    public Teacher create(String username, String name, String email, String password, int office) {
        Teacher teacher = new Teacher(username, name, password, email, office);
        entityManager.persist(teacher);
        return teacher;
    }
}
