package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllStudents",
                query = "SELECT s FROM Student s ORDER BY s.name"
        ),
})
public class Student extends User {

    @ManyToOne
    @JoinColumn(name = "COURSE_CODE")
    @NotNull
    private Course course;

    @ManyToMany(mappedBy = "students")
    private LinkedHashSet<Subject> subjects;

    public Student() {

    }

    public Student(String username, String name, String email, String password, Course course) {
        super(username, name, password, email);
        this.course = course;
        this.subjects = new LinkedHashSet<>();
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
    public Course getCourse() {
        return course;
    }
    public LinkedHashSet<Subject> getSubjects() {
        return new LinkedHashSet<>(subjects);
    }

    /*Setters*/
    public void setCourse(Course course) {
        this.course = course;
    }
    public void setSubjects(LinkedHashSet<Subject> subjects) {
        this.subjects = new LinkedHashSet<>(subjects);
    }
}
