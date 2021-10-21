package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CourseBean {

    @PersistenceContext
    EntityManager entityManager;

    public Course create(int courseCode, String courseName) throws MyEntityExistsException {
        if(entityManager.find(Course.class, courseCode) != null) {
            throw new MyEntityExistsException("Course with code " + courseCode + " already exists!");
        }

        Course course = new Course(courseCode, courseName);
        entityManager.persist(course);

        return course;
    }

    public Course update(Course course, String courseName) {
        course.setName(courseName);
        entityManager.merge(course);

        return course;
    }

    public void deleteCourse(int courseCode) throws MyEntityNotFoundException {
        Course course = this.findCourse(courseCode);

        if(course == null) {
            throw new MyEntityNotFoundException("Course with code " + courseCode + " does not exist!");
        }
        entityManager.remove(course);
    }

    public Course findCourse(int courseCode) {
        return entityManager.find(Course.class, courseCode);
    }

    public List<Course> getAllCourses() {
        return (List<Course>) entityManager.createNamedQuery("getAllCourses").getResultList();
    }
}
