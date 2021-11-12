package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import javax.persistence.*;

@Entity
@NamedQueries(
        @NamedQuery(
                name = "getStudentDocuments",
                query = "SELECT doc FROM Document doc WHERE doc.student.username = :username"
        )
)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String filepath;

    private String filename;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_ID")
    private Student student;

    public Document() {
    }

    public Document(String filepath, String filename, Student student) {
        this.filepath = filepath;
        this.filename = filename;
        this.student = student;
    }

    /*Getters*/
    public int getId() {
        return id;
    }
    public String getFilepath() {
        return filepath;
    }
    public String getFilename() {
        return filename;
    }
    public Student getStudent() {
        return student;
    }

    /*Setters*/
    public void setId(int id) {
        this.id = id;
    }
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
}
