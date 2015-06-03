package pro.tremblay.jiramigrate;

import pro.tremblay.jiramigrate.jira.Issue;
import pro.tremblay.jiramigrate.jira.IssueFields;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class Export {

    public static void main( String[] args ) throws Exception {
        Export export = new Export();
        export.init();
        export.run();
    }

    private JiraConnector connector = new JiraConnector();

    private String project;

    private void init() throws IOException {
        Files.createDirectories(Paths.get("output", "raw"));

        Properties properties = new Properties();
        try(InputStream in = new FileInputStream("jiramigrate.properties")) {
            properties.load(in);
        }

        project = properties.getProperty("jira.project");

        connector = new JiraConnector();
        connector.setServerUrl("http://jira.codehaus.org/");
        connector.setLogin(properties.getProperty("jira.login"));
        connector.setPassword(properties.getProperty("jira.password"));
        connector.setListener((req, res) -> {
            req = req.replace('/', '-').replace('?', '_').replace('&', '_').replace('=', '_');
            try {
                Files.write(Paths.get("output", "raw", req + ".json"), res);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        connector.init();
    }

    private void run() {
        // 1. I should also get the project but I have done it manually

        // 2. Get all versions of the project
        connector.getAllVersions(project);

        // 3. The list of priority but I did it manually

        // 4. All the fields but I did it manually

        // 5. All the components but I did it manually

        // 6. Then all issues on my project
        List<Issue> issues = connector.getAllIssues(project);
        issues.parallelStream().forEach(this::processIssue);
    }

    private void processIssue(Issue issue) {
        String key = issue.getKey();

        System.out.println("Issue " + key);
        // Now, I want a full issue, because the one from the search isn't complete
        issue = connector.getIssue(issue.getId());

        // And from this issue, I want the users (reporter, assignee, comments, attachments, components)
        // And the attachment

        IssueFields fields = issue.getFields();
        connector.getUser(fields.getAssignee().getName());
        connector.getUser(fields.getReporter().getName());
        fields.getComment().getComments().forEach(c -> {
            connector.getUser(c.getAuthor().getName());
            connector.getUser(c.getUpdateAuthor().getName());
        });
        fields.getAttachment().forEach(a -> {
            connector.getUser(a.getAuthor().getName());
        });
    }
}
