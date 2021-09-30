package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CourseBean {

    @PersistenceContext
    EntityManager entityManager;

    public Course create(int courseCode, String courseName) {
        Course course = new Course(courseCode, courseName);
        entityManager.persist(course);
        return course;
    }

    public Course update(Course course, String courseName) {
        course.setName(courseName);
        entityManager.merge(course);

        return course;
    }

    public Course findCourse(int courseCode) {
        return entityManager.find(Course.class, courseCode);
    }

    public List<Course> getAllCourses() {
        return (List<Course>) entityManager.createNamedQuery("getAllCourses").getResultList();
    }
}
