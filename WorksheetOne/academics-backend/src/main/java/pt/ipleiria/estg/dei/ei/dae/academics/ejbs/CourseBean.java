package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CourseBean {

    @PersistenceContext
    EntityManager entityManager;

    public Course create(int courseCode, String courseName) {
        Course course = new Course(courseCode, courseName);
        entityManager.persist(course);
        return course;
    }

    public Course findCourse(int courseCode) {
        return entityManager.find(Course.class, courseCode);
    }

    public List<Course> getAllCourses() {
        return (List<Course>) entityManager.createNamedQuery("getAllCourses").getResultList();
    }
}
