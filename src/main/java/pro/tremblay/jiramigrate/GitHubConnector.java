package pro.tremblay.jiramigrate;

import pro.tremblay.jiramigrate.github.*;

/**
 * @author Henri Tremblay
 */
public class GitHubConnector extends BaseRestConnector {

    private String suffix;

    @Override
    protected String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public GetRateLimit getLimits() {
        return getRestTemplate().getForObject(getServerUrl() + "/rate_limit",
                GetRateLimit.class);
    }

    public IssueResponse createIssue(Issue issue) {
        return postForObject("issues", issue, IssueResponse.class);
    }

    public IssueResponse updateIssue(Issue issue, int number) {
        return postForObject("issues/" + number, issue, IssueResponse.class);
    }

    public Gist createGist(Gist gist) {
        return postForObject("gists", gist, Gist.class);
    }

    public Milestone createMilestone(Milestone milestone) {
        return postForObject("milestones", milestone, Milestone.class);
    }

    public Milestone[] getAllMilestones() {
        return getForObject("milestones?state=all", Milestone[].class);
    }

    public void addComment(int issueNumber, Comment comment) {
        postForObject("issues/" + issueNumber + "/comments", comment, Comment.class);
    }

    public void getAllIssues() {
        getForObject("issues", Object.class);
    }

    public void deleteMilestone(int number) {
        delete("milestones/" + number);
    }

    public Label createLabel(Label label) {
        return postForObject("labels", label, Label.class);
    }
}
