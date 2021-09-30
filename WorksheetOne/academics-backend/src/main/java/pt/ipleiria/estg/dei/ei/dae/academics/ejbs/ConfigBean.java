package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class ConfigBean {

    @EJB
    private StudentBean studentBean;

    @EJB
    private CourseBean courseBean;

    @PostConstruct
    public void populateDB() {
        courseBean.create(1, "Course #1");
        courseBean.create(2, "Course #2");

        studentBean.create("JoãoRuss0", "João", "João@gmail.com", "1234", 0);
        studentBean.create("Ana", "Ana", "Ana@gmail.com", "1234", 0);
        studentBean.create("Alberto", "Alberto", "Alberto@gmail.com", "1234", 0);
        studentBean.create("Matilde", "Matilde", "Matilde@gmail.com", "1234", 0);
    }
}