package ioannis.visvikis.issuetracker.demo.models.requests;

import javax.validation.constraints.NotBlank;

public abstract class UserRequest implements AccessRequest {

    @NotBlank
    protected String username;
    @NotBlank
    protected String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UserRequest(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public UserRequest() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRequest)) return false;

        UserRequest that = (UserRequest) o;

        if (!username.equals(that.username)) return false;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

}
