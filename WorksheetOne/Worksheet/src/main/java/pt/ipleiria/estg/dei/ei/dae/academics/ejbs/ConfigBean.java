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

    @PostConstruct
    public void populateDB()
    {
        studentBean.create("JoãoRuss0", "João", "João@gmail.com", "1234");
        studentBean.create("Ana", "Ana", "Ana@gmail.com", "1234");
        studentBean.create("Alberto", "Alberto", "Alberto@gmail.com", "1234");
        studentBean.create("Matilde", "Matilde", "Matilde@gmail.com", "1234");
    }
}