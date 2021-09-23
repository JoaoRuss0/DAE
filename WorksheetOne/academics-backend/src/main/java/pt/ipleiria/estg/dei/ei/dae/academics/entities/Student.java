package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllStudents",
                query = "SELECT s FROM Student s ORDER BY s.name"
        ),
})
@Table(name = "students")
public class Student {

    @Id
    private String username;
    private String name;
    private String email;
    private String password;

    public Student() {

    }

    public Student(String username, String name, String email, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /*Getter*/
    public String getUsername() {
        return username;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    /*Setter*/
    public void setUsername(String username) {
        this.username = username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
