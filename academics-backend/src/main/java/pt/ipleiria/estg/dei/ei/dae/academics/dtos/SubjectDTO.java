package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

public class SubjectDTO {

    private int code;
    private String name;
    private int courseCode;
    private int courseYear;
    private int scholarYear;

    public SubjectDTO() {
    }

    public SubjectDTO(int code, String name, int courseCode, int courseYear, int scholarYear) {
        this.code = code;
        this.name = name;
        this.courseCode= courseCode;
        this.courseYear = courseYear;
        this.scholarYear = scholarYear;
    }

    /*Getters*/
    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public int getCourseCode() {
        return courseCode;
    }
    public int getCourseYear() {
        return courseYear;
    }
    public int getScholarYear() {
        return scholarYear;
    }

    /*Setters*/
    public void setCode(int code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }
    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }
    public void setScholarYear(int scholarYear) {
        this.scholarYear = scholarYear;
    }
}
