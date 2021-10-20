package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedHashSet;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllCourses",
                query = "SELECT c FROM Course c ORDER BY c.name"
        ),
})
@Table(
        name = "COURSES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"})
)
public class Course {

    @Id
    private int code;

    @Version
    private int version;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private ArrayList<Student> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private LinkedHashSet<Subject> subjects;

    public Course() {

    }

    public Course(int code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
        this.subjects = new LinkedHashSet<>();
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            return;
        }
    }

    public void removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            return;
        }
    }

    public Subject addSubject(Subject subject) {
        if (subjects.add(subject)) {
            return subject;
        }
        return null;
    }

    public Subject removeSubject(Subject subject) {
        if (subjects.remove(subject)) {
            return subject;
        }
        return null;
    }

    /*Getters*/
    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
    }
    public LinkedHashSet<Subject> getSubjects() {
        return new LinkedHashSet<>(subjects);
    }

    /*Setters*/
    public void setCode(int code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = new ArrayList<>(students);
    }
    public void setSubjects(LinkedHashSet<Subject> subjects) {
        this.subjects = new LinkedHashSet<>(subjects);
    }
}
