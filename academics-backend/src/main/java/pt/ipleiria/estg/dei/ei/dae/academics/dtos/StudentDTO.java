package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentDTO implements Serializable {

    private String username;
    private String name;
    private String email;
    private String password;
    private int courseCode;
    private String courseName;
    private List<SubjectDTO> subjects;
    private List<DocumentDTO> documents;

    public StudentDTO() {
        this.subjects = new ArrayList<>();
        this.documents = new ArrayList<>();
    }

    public StudentDTO(String username, String name, String email, String password, Course course) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.courseCode = course.getCode();
        this.courseName = course.getName();
        this.subjects = new ArrayList<>();
        this.documents = new ArrayList<>();
    }

    public StudentDTO(String username, String name, String email, String password, Course course, List<SubjectDTO> subjects, List<DocumentDTO> documents) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.courseCode = course.getCode();
        this.courseName = course.getName();
        this.subjects = subjects;
        this.documents = documents;
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
    public ArrayList<SubjectDTO> getSubjects() {
        return new ArrayList<>(this.subjects);
    }
    public ArrayList<DocumentDTO> getDocuments() {
        return new ArrayList<>(this.documents);
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
    public void setSubjects(ArrayList<SubjectDTO> subjects) {
        this.subjects = new ArrayList<>(subjects);
    }
    public void setDocuments(ArrayList<DocumentDTO> documents) {
        this.documents = new ArrayList<>(documents);
    }
}
