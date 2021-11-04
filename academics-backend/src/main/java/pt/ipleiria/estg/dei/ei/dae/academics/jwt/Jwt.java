package pt.ipleiria.estg.dei.ei.dae.academics.jwt;

public class Jwt {
    private String token;

    public Jwt(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
