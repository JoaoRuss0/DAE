package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllStudents",
                query = "SELECT s FROM Student s ORDER BY s.name"
        ),
})
@Table(name = "STUDENTS")
public class Student {

    @Id
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "COURSE_CODE")
    @NotNull
    private Course course;

    public Student() {
        this.username = "a";
        this.name = "a";
        this.email = "a.a@a.a";
        this.password = "1234";
        this.course = new Course();
    }

    public Student(String username, String name, String email, String password, Course course) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.course = course;
    }

    /*Getters*/
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
    public Course getCourse() {
        return course;
    }

    /*Setters*/
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
    public void setCourse(Course course) {
        this.course = course;
    }
}
