package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import java.io.Serializable;

public class StudentDTO implements Serializable {
    private String username;
    private String name;
    private String email;
    private String password;

    public StudentDTO() {

    }

    public StudentDTO(String username) {
        this.username = username;
    }

    public StudentDTO(String username, String name, String email, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
