package ioannis.visvikis.issuetracker.demo.models.requests;

import javax.validation.constraints.NotNull;

public class DeleteIssueRequest {

    @NotNull
    private Long issueId;

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }


    public DeleteIssueRequest(@NotNull Long issueId) {
        this.issueId = issueId;
    }

    public DeleteIssueRequest() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteIssueRequest that = (DeleteIssueRequest) o;

        return issueId.equals(that.issueId);
    }

    @Override
    public int hashCode() {
        return issueId.hashCode();
    }

    @Override
    public String toString() {
        return "DeleteIssueRequest{" +
                "issueId=" + issueId +
                '}';
    }

}
