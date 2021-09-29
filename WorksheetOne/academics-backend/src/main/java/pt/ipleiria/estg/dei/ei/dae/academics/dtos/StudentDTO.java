package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;

import java.io.Serializable;

public class StudentDTO implements Serializable {
    private String username;
    private String name;
    private String email;
    private String password;
    private int courseCode;
    private String courseName;

    public StudentDTO() {

    }

    public StudentDTO(String username) {
        this.username = username;
    }

    public StudentDTO(String username, String name, String email, String password, Course course) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.courseCode = course.getCode();
        this.courseName = course.getName();
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
    public int getCourseCode() {
        return courseCode;
    }
    public String getCourseName() {
        return courseName;
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
    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
