package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

public class DocumentDTO {

    private int id;
    private String filepath;
    private String filename;

    public DocumentDTO() {
    }

    public DocumentDTO(int id, String filepath, String filename) {
        this.id = id;
        this.filepath = filepath;
        this.filename = filename;
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
}
