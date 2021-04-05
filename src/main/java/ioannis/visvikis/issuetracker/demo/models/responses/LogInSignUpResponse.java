package ioannis.visvikis.issuetracker.demo.models.responses;

public class LogInSignUpResponse {

    private String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public LogInSignUpResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
