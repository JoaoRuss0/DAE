package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.dtos.SubjectDTO;
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

            course.addSubject(subject);
            entityManager.merge(course);

            return subject;
        }
        return null;
    }

    public Subject update(Subject subject, SubjectDTO subjectDTO) {
        subject.setName(subjectDTO.getName());
        subject.setCourseYear(subjectDTO.getCourseYear());
        subject.setScholarYear(subjectDTO.getScholarYear());
        subject.setCourse(entityManager.find(Course.class, subjectDTO.getCourseCode()));
        entityManager.merge(subject);

        return subject;
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

    public Subject findSubject(int code) {
        return entityManager.find(Subject.class, code);
    }

    public List<Subject> getAllSubjects() {
        return (List<Subject>) entityManager.createNamedQuery("getAllSubjects").getResultList();
    }
}
