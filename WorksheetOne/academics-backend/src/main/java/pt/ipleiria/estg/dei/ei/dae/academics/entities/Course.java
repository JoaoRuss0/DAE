package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

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

    @NotNull
    private String name;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private ArrayList<Student> students;

    public Course() {
        this.code = 1;
        this.name = "Course #" + this.code;
        this.students = new ArrayList<>();
    }

    public Course(int code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if(!students.contains(student)){
            students.add(student);
            return;
        }
    }

    public void removeStudent(Student student) {
        if(students.contains(student)){
            students.remove(student);
            return;
        }
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
}
