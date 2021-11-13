package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Document;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class DocumentBean {

    @PersistenceContext
    EntityManager entityManager;

    @EJB
    StudentBean studentBean;

    public void create(String username, String filepath, String filename) throws MyEntityNotFoundException {
        Student student = studentBean.findStudent(username);
        Document document = new Document(filepath, filename, student);
        student.addDocument(document);
        entityManager.persist(document);
    }

    public Document findDocument(int id) throws MyEntityNotFoundException {
        Document document = entityManager.find(Document.class, id);

        if(document == null) {
            throw new MyEntityNotFoundException("Document with id = " + id + " does not exist.");
        }
        return document;
    }

    public List<Document> getStudentDocuments(String username){
        Query query = entityManager.createNamedQuery("getStudentDocuments", Document.class);
        query.setParameter("username", username);
        return query.getResultList();
    }
}
