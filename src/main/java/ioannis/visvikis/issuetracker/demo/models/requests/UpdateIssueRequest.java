package ioannis.visvikis.issuetracker.demo.models.requests;

import javax.validation.constraints.NotNull;

public class UpdateIssueRequest {

    @NotNull
    private Long issueId;

    private String title;

    private String description;

    private String assigneeUsername;

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

    public String getAssigneeUsername() {
        return assigneeUsername;
    }

    public void setAssigneeUsername(String assigneeUsername) {
        this.assigneeUsername = assigneeUsername;
    }


    public UpdateIssueRequest() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateIssueRequest that = (UpdateIssueRequest) o;

        if (!issueId.equals(that.issueId)) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return assigneeUsername != null ? assigneeUsername.equals(that.assigneeUsername) : that.assigneeUsername == null;
    }

    @Override
    public int hashCode() {
        int result = issueId.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (assigneeUsername != null ? assigneeUsername.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "UpdateIssueRequest{" +
                "issueId=" + issueId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", assignToUsername='" + assigneeUsername + '\'' +
                '}';
    }


}
