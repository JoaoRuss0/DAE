package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllSubjects",
                query = "SELECT s FROM Subject s ORDER BY s.course.name, s.scholarYear DESC, s.course.name, s.name"
        )
})
@Table(
        name = "SUBJECTS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "COURSE_CODE", "COURSE_YEAR"})
        }
)
public class Subject {

    @Id
    private int code;

    @Version
    private int version;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "COURSE_CODE")
    @NotNull
    private Course course;

    @Column(name = "COURSE_YEAR")
    @NotNull
    private int courseYear;

    @Column(name = "SCHOLAR_YEAR")
    @NotNull
    private int scholarYear;

    @ManyToMany
    @JoinTable(
            name = "SUBJECTS_STUDENTS",
            joinColumns = @JoinColumn(name = "SUBJECT_CODE", referencedColumnName = "CODE"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_USERNAME", referencedColumnName = "USERNAME")
    )
    private LinkedHashSet<Student> students;

    @ManyToMany
    @JoinTable(
            name = "SUBJECTS_TEACHERS",
            joinColumns = @JoinColumn(name = "SUBJECT_CODE", referencedColumnName = "CODE"),
            inverseJoinColumns = @JoinColumn(name = "TEACHER_USERNAME", referencedColumnName = "USERNAME")
    )
    private LinkedHashSet<Teacher> teachers;

    public Subject() {
    }

    public Subject(int code, String name, Course course, int courseYear, int scholarYear) {
        this.code = code;
        this.name = name;
        this.course = course;
        this.courseYear = courseYear;
        this.scholarYear = scholarYear;
        this.students = new LinkedHashSet<>();
        this.teachers = new LinkedHashSet<>();
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
    public int getCode() {
        return code;
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
    public LinkedHashSet<Teacher> getTeachers() {
        return new LinkedHashSet<>(teachers);
    }

    /*Setters*/
    public void setCode(int code) {
        this.code = code;
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
    public void setTeachers(LinkedHashSet<Teacher> teachers) {
        this.teachers = new LinkedHashSet<>(teachers);
    }}


