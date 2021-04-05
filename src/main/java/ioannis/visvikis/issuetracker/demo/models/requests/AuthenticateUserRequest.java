package ioannis.visvikis.issuetracker.demo.models.requests;

import javax.validation.constraints.NotBlank;

public class AuthenticateUserRequest extends UserRequest {

    public AuthenticateUserRequest(@NotBlank String username, @NotBlank String password) {
        super(username, password);
    }

    public AuthenticateUserRequest() {

    }

    @Override
    public String toString() {
        return "LoginUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
