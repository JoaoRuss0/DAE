package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

public class CourseDTO {
    private int code;
    private String name;

    public CourseDTO() {
        this.code = 1;
        this.name = "Course #" + this.code;
    }

    public CourseDTO(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /*Getters*/
    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }

    /*Setters*/
    public void setCode(int code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
}
