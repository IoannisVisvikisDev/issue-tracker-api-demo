package ioannis.visvikis.issuetracker.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name="issue")
public class Issue {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="issue_id")
    private Long issueId = null;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Column(name="date_created")
    private Timestamp dateCreated;

    @NotNull
    @ManyToOne  //Many issues can have been opened by one user
    @JoinColumn(name="user_opened")
    private User openingUser;

    @NotNull
    @ManyToOne //Many issues can been assigned to one user
    @JoinColumn(name="assigned_user")
    private User assignedUser;

    @NotNull
    @ManyToOne //Many issues can been assigned to one user
    @JoinColumn(name="editor_user")
    private User editorUser;

    @Column(name="date_last_edited")
    private Timestamp dateLastEdited;


    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getOpeningUser() {
        return openingUser;
    }

    public void setOpeningUser(User openingUser) {
        this.openingUser = openingUser;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public User getEditorUser() {
        return editorUser;
    }

    public void setEditorUser(User editorUser) {
        this.editorUser = editorUser;
    }

    public Timestamp getDateLastEdited() {
        return dateLastEdited;
    }

    public void setDateLastEdited(Timestamp dateLastEdited) {
        this.dateLastEdited = dateLastEdited;
    }


    public Issue() {
    }

    public Issue(Long issueId, @NotBlank String title, @NotBlank String description, @NotNull Timestamp dateCreated, @NotNull User openingUser, @NotNull User assignedUser, @NotNull Timestamp dateLastEdited) {
        this.issueId = issueId;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.openingUser = openingUser;
        this.assignedUser = assignedUser;
        this.dateLastEdited = dateLastEdited;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (!issueId.equals(issue.issueId)) return false;
        if (!title.equals(issue.title)) return false;
        if (!description.equals(issue.description)) return false;
        if (!dateCreated.equals(issue.dateCreated)) return false;
        if (!openingUser.equals(issue.openingUser)) return false;
        if (!assignedUser.equals(issue.assignedUser)) return false;
        return dateLastEdited.equals(issue.dateLastEdited);
    }

    @Override
    public int hashCode() {
        int result = issueId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + dateCreated.hashCode();
        result = 31 * result + openingUser.hashCode();
        result = 31 * result + assignedUser.hashCode();
        result = 31 * result + dateLastEdited.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "issueId=" + issueId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", openingUser=" + openingUser +
                ", assignedUser=" + assignedUser +
                ", dateLastEdited=" + dateLastEdited +
                '}';
    }
}
