package ioannis.visvikis.issuetracker.demo.models.requests;


import javax.validation.constraints.NotBlank;


public class RaiseIssueRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String assigneeUsername;

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


    public RaiseIssueRequest(@NotBlank String title, @NotBlank String description, @NotBlank String assigneeUsername) {
        this.title = title;
        this.description = description;
        this.assigneeUsername = assigneeUsername;
    }

    public RaiseIssueRequest() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RaiseIssueRequest that = (RaiseIssueRequest) o;

        if (!title.equals(that.title)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RaiseIssueRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", assigneeUsername='" + assigneeUsername + '\'' +
                '}';
    }


}
