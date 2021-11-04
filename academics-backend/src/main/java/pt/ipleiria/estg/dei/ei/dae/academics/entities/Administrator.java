package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import javax.persistence.Entity;

@Entity
public class Administrator extends User {

    public Administrator() {
    }

    public Administrator(String username, String name, String password, String email) {
        super(username, name, password, email);
    }
}
