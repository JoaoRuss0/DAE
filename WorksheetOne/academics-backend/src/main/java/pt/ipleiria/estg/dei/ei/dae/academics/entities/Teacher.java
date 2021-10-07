package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;

@Entity
public class Teacher extends User {

    @NotNull
    private int office;

    @ManyToMany(mappedBy = "teachers")
    private LinkedHashSet<Subject> subjects;

    public Teacher() {

    }

    public Teacher(String username, String name, String password, String email, int office) {
        super(username, name, password, email);
        this.office = office;
        this.subjects = new LinkedHashSet<>();
    }

    /*Getters*/
    public int getOffice() {
        return office;
    }
    public LinkedHashSet<Subject> getSubjects() {
        return new LinkedHashSet<>(subjects);
    }

    /*Setters*/
    public void setOffice(int office) {
        this.office = office;
    }
    public void setSubjects(LinkedHashSet<Subject> subjects) {
        this.subjects = new LinkedHashSet<>(subjects);
    }
}
