package pro.tremblay.jiramigrate;

import pro.tremblay.jiramigrate.jira.*;

import java.util.ArrayList;
import java.util.List;

public class JiraConnector extends BaseRestConnector {

    private static final int MAX_RESULTS = 1000; // 1000 is the maximum allowed by our Jira instance and also the quickest to get the results

    @Override
    protected String getSuffix() {
        return "/rest/api/2/";
    }

    public List<Issue> getAllIssues(String project) {
        String query = "jql=project=" + project;
        return getSearchResults(query);
    }

    public Issue getIssue(String id) {
        return getForObject("issue/" + id, Issue.class);
    }

    public List<Version> getAllVersions(String project) {
        return getForObject("project/" + project + "/versions", List.class);
    }

    public User getUser(String name) {
        return getForObject("user?username=" + name, User.class);
    }

    public List<Component> getComponent(String id) {
        return getForObject("component/" + id, List.class);
    }

    private List<Issue> getSearchResults(String query) {
        int startAt = 0;
        List<Issue> results = new ArrayList<>();
        SearchResult result;
        do {
            result = getForObject("search?" + query + "&startAt=" + startAt + "&maxResults=" + MAX_RESULTS, SearchResult.class);
            results.addAll(result.getIssues());
            startAt += result.getMaxResults();
        } while(startAt < result.getTotal());

        return results;
    }

}
