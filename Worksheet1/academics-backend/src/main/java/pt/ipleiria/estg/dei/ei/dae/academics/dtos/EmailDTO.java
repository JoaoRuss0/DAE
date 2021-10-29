package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

public class EmailDTO {

    private String Subject;
    private String Message;

    public EmailDTO() {
    }

    public EmailDTO(String subject, String message) {
        Subject = subject;
        Message = message;
    }

    /*Getters*/
    public String getSubject() {
        return Subject;
    }
    public String getMessage() {
        return Message;
    }

    /*Setters*/
    public void setSubject(String subject) {
        Subject = subject;
    }
    public void setMessage(String message) {
        Message = message;
    }
}
