package pro.tremblay.jiramigrate.jira;

import java.util.List;

/**
 * Created by htrembl2 on 1/13/15.
 */
public class SearchResult {
    private int startAt;
    private int maxResults;
    private int total;
    private List<Issue> issues;

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
