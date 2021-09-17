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
    }
}