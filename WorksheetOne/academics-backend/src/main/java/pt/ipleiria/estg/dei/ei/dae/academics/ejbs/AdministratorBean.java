package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Administrator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdministratorBean {

    @PersistenceContext
    EntityManager entityManager;

    public Administrator create(String username, String name, String email, String password) {
        Administrator administrator = new Administrator(username, name, password, email);
        entityManager.persist(administrator);
        return administrator;
    }
}
