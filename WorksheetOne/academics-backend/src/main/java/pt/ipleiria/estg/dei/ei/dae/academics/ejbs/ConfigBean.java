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

    @EJB
    private SubjectBean subjectBean;

    @PostConstruct
    public void populateDB() {
        courseBean.create(1, "EI");
        courseBean.create(2, "EE");

        subjectBean.create(1, "Analise", 1, 2010, 2020);
        subjectBean.create(2, "Algebra", 1, 2010, 2020);
        subjectBean.create(3, "Programacao I", 1, 2010, 2020);
        subjectBean.create(4, "Programacao II", 1, 2010, 2020);

    }
}