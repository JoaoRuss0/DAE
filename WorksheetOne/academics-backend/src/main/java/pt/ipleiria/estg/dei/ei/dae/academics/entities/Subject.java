package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllSubjects",
                query = "SELECT s FROM Subject s ORDER BY s.course.name, s.scholarYear DESC, s.course, s.name"
        )
})
@Table(
        name = "SUBJECTS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "name",
                        "COURSE_CODE",
                        "COURSE_YEAR"
                })
        }
)
public class Subject {

    @Id
    private int id;

    @NotNull
    @Column(unique = true)
    private String name;

    @ManyToOne
    @NotNull
    @Column(unique = true)
    private Course course;

    @NotNull
    @Column(name = "COURSE_YEAR", unique = true)
    private int courseYear;

    @NotNull
    @Column(name = "SCHOLAR_YEAR")
    private int scholarYear;

    @ManyToMany
    @JoinTable(
            name = "SUBJECTS_STUDENTS",
            joinColumns = @JoinColumn(name = "SUBJECT_CODE", referencedColumnName = "CODE"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_USERNAME", referencedColumnName = "USERNAME")
    )
    private LinkedHashSet<Student> students;

    public Subject() {
    }

    public Subject(int id, String name, Course course, int courseYear, int scholarYear) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.courseYear = courseYear;
        this.scholarYear = scholarYear;
        this.students = new LinkedHashSet<>();
    }

    public Student addStudent(Student student) {
        if (students.add(student)) {
            return student;
        }
        return null;
    }

    public Student removeStudent(Student student) {
        if (students.remove(student)) {
            return student;
        }
        return null;
    }

    /*Getters*/
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public int getScholarYear() {
        return scholarYear;
    }

    public LinkedHashSet<Student> getStudents() {
        return new LinkedHashSet<>(students);
    }

    /*Setters*/
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public void setScholarYear(int scholarYear) {
        this.scholarYear = scholarYear;
    }

    public void setStudents(LinkedHashSet<Student> students) {
        this.students = new LinkedHashSet<>(students);
    }
}

