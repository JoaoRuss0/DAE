package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SubjectBean {

    @PersistenceContext
    EntityManager entityManager;

    public Subject create(int id, String name, int courseCode, int courseYear, int scholarYear) {
        Course course = entityManager.find(Course.class, courseCode);

        if (course != null) {
            Subject subject = new Subject(id, name, course, courseYear, scholarYear);
            entityManager.persist(subject);
            return subject;
        }
        return null;
    }

    public boolean enrollStudentInSubject(String username, int subjectCode) {
        Student student = entityManager.find(Student.class, username);
        Subject subject = entityManager.find(Subject.class, subjectCode);

        if (subject != null && student != null) {
            subject.addStudent(student);
            student.addSubject(subject);

            return true;
        }
        return false;
    }

    public List<Subject> getAllSubjects() {
        return (List<Subject>) entityManager.createNamedQuery("getAllSubjects").getResultList();
    }
}
