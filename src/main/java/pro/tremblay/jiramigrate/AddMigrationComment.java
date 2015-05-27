package pro.tremblay.jiramigrate;

import pro.tremblay.jiramigrate.jira.Comment;
import pro.tremblay.jiramigrate.jira.Issue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

/**
 * @author Henri Tremblay
 */
public class AddMigrationComment {

    public static void main( String[] args ) throws Exception {
        AddMigrationComment export = new AddMigrationComment();
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
            System.out.println(req);
            System.out.println(new String(res, StandardCharsets.UTF_8));
        });
        connector.init();
    }

    private void run() throws IOException {
        List<Issue> issues = connector.getOpenIssues("EASYMOCK");
        issues.forEach(this::addComment);
    }

    private void addComment(Issue issue) {
        Comment comment = new Comment();
        comment.setBody("This issue will soon be migrated to Github (https://github.com/easymock/easymock/issues) soon");
        comment = connector.addComment(issue.getKey(), comment);
        System.out.println("Comment " + comment.getId() + " created for issue " + issue.getKey());
    }
}
