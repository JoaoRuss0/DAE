package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class ConfigBean {

    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");

    @EJB
    private StudentBean studentBean;

    @EJB
    private CourseBean courseBean;

    @EJB
    private SubjectBean subjectBean;

    @EJB
    private TeacherBean teacherBean;

    @EJB
    private AdministratorBean administratorBean;

    @PostConstruct
    public void populateDB() {
        try {
            courseBean.create(1, "EI");
            courseBean.create(2, "EE");

            subjectBean.create(1, "Analise", 1, 2010, 2020);
            subjectBean.create(2, "Algebra", 1, 2010, 2020);
            subjectBean.create(3, "Programacao I", 1, 2010, 2020);
            subjectBean.create(4, "Programacao II", 1, 2010, 2020);

            studentBean.create("Joao", "Joao", "j@j.j", "1234", 1);
            studentBean.create("Maria", "Maria", "m@m.m", "1234", 1);
            studentBean.create("Ana", "Ana", "a@a.a", "1234", 1);
            studentBean.create("Jose", "Jose", "s@s.s", "1234", 1);

            teacherBean.create("Tamboril", "Tamboril", "t@t.t", "1234", 4);
            teacherBean.create("Feijão", "Feijão", "f@f.f", "1234", 5);

            administratorBean.create("Sergio", "Sergio", "o@o.o", "1234");
            administratorBean.create("Mario", "Mario", "i@i.i", "1234");

            subjectBean.enrollStudentInSubject("Joao", 1);
            subjectBean.enrollStudentInSubject("Joao", 2);
            subjectBean.enrollStudentInSubject("Joao", 3);
            subjectBean.enrollStudentInSubject("Joao", 4);
            subjectBean.enrollStudentInSubject("Jose", 1);
            subjectBean.enrollStudentInSubject("Jose", 2);
            subjectBean.enrollStudentInSubject("Jose", 3);
            subjectBean.enrollStudentInSubject("Jose", 4);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}