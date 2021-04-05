package ioannis.visvikis.issuetracker.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCrypt;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId = null;
    @NotBlank
    private String username;
    @NotBlank
    @JsonIgnore //do not show in response
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "assignedUser")  //A User can have multiple Issues assigned to
    private List<Issue> assignedIssuesList = new LinkedList<>();  //we're expecting more addittions-deletions than searches-iterations

    @JsonIgnore
    @OneToMany(mappedBy = "openingUser")   //A User can have multiple Issues assigned to
    private List<Issue> createdIssuesList = new LinkedList<>();  //we're expecting more addittions-deletions than searches-iterations




    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        //BCrypt.hashpw(username, BCrypt.gensalt(10))
        this.username = BCrypt.hashpw(username, BCrypt.gensalt(10));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Issue> getAssignedIssuesList() {
        return assignedIssuesList;
    }

    public void setAssignedIssuesList(List<Issue> assignedIssuesList) {
        this.assignedIssuesList = assignedIssuesList;
    }

    public List<Issue> getCreatedIssuesList() {
        return createdIssuesList;
    }

    public void setCreatedIssuesList(List<Issue> createdIssuesList) {
        this.createdIssuesList = createdIssuesList;
    }

    public User(@NotBlank String username) {
        this.username = username;
    }


    public User() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User that = (User) o;

        if (!userId.equals(that.userId)) return false;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "RegisteredUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", assignedIssuesList=" + assignedIssuesList +
                ", createdIssuesList=" + createdIssuesList +
                '}';
    }

}
