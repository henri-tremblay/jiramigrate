package pro.tremblay.jiramigrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.joda.time.DateTime;
import pro.tremblay.jiramigrate.github.Comment;
import pro.tremblay.jiramigrate.github.IssueResponse;
import pro.tremblay.jiramigrate.github.Milestone;
import pro.tremblay.jiramigrate.jira.*;
import pro.tremblay.jiramigrate.util.CustomDateTimeDeserializer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;

/**
 * @author Henri Tremblay
 */
public class Import {

    public static void main( String[] args ) throws Exception {
        System.out.println(new Date(1433106216));
        Import i = new Import();
        i.init();
        i.run();
    }

    private GitHubConnector connector = new GitHubConnector();

    private Map<String, Milestone> milestoneMap = new HashMap<>();

    private void init() throws IOException {
        Properties properties = new Properties();
        try(InputStream in = new FileInputStream("jiramigrate.properties")) {
            properties.load(in);
        }

        connector = new GitHubConnector();
        connector.setServerUrl("https://api.github.com/");
        connector.setLogin(properties.getProperty("github.login"));
        connector.setPassword(properties.getProperty("github.password"));
        connector.setSuffix(properties.getProperty("github.suffix"));
        connector.setListener((req, res) -> {
            System.out.println(req);
            System.out.println(new String(res, StandardCharsets.UTF_8));
        });

        connector.init();

        Milestone[] milestones = connector.getAllMilestones();
        Stream.of(milestones).forEach(m -> {
            milestoneMap.put(m.getTitle(), m);
        });
    }

    private void run() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(DateTime.class, new CustomDateTimeDeserializer());
        mapper.registerModule(module);

        Stream<Path> paths = Files.find(Paths.get("output/raw"), 1, (p, a) -> {
            boolean b = p.toString().startsWith("output/raw/issue-");
            return b;
        });
        paths.sorted().forEach(p -> {
            try {
                Issue issue = mapper.readValue(p.toFile(), Issue.class);
                processIssue(issue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void processIssue(Issue issue) throws IOException {
        System.out.println("Processing " + issue.getKey());
        int id = Integer.parseInt(issue.getKey().substring("EASYMOCK-".length()));

        // Create an issue
        pro.tremblay.jiramigrate.github.Issue githubIssue = new pro.tremblay.jiramigrate.github.Issue();
        githubIssue.setAssignee("henri-tremblay");

        String title = issue.getFields().getSummary();
        githubIssue.setTitle(title);

        String body = "*Migrated from:* CodeHaus issue " + issue.getKey() + "\n";
        body += "*Original reporter:* " + issue.getFields().getReporter().getDisplayName()  + "\n\n";
        body += "---------------------------------------------------------\n";
        body += issue.getFields().getDescription() + "\n";

        if(!issue.getFields().getAttachment().isEmpty()) {
            System.out.println("Adding attachment");
            body += "\n---------------------------------------------------------\n";
            for(Attachment a : issue.getFields().getAttachment()) {
                body += "See https://github.com/henri-tremblay/easymock-issue-attachments/blob/master/" + a.getFilename() + "\n";
            }
        }
        githubIssue.setBody(body);

        // Add milestone
        if(!issue.getFields().getFixVersions().isEmpty()) {
            if(issue.getFields().getFixVersions().size() > 1) {
                throw new IllegalArgumentException(issue.getKey());
            }
            Milestone milestone = milestoneMap.get(issue.getFields().getFixVersions().get(0).getName());
            if(milestone == null) {
                System.out.println(
                        "Milestone not found! " + issue.getFields().getFixVersions().get(0).getName());
            }
            githubIssue.setMilestone(milestone.getNumber());
        }

        // add labels (resolution, priority, component, type
        Optional.ofNullable(convertResolution(issue.getFields().getResolution()))
                .map(r -> githubIssue.getLabels().add(r));

        Optional.ofNullable(convertPriority(issue.getFields().getPriority()))
                .map(r -> githubIssue.getLabels().add(r));

        Optional.ofNullable(convertType(issue.getFields().getIssuetype()))
                .map(r -> githubIssue.getLabels().add(r));

        issue.getFields().getComponents().forEach(c -> {
            String type = convertComponent(c);
            githubIssue.getLabels().add(type);
        });

        IssueResponse response = connector.createIssue(githubIssue);

        // add comments
        issue.getFields().getComment().getComments().forEach(c -> {
            pro.tremblay.jiramigrate.github.Comment comment = new Comment();
            String b = "*Original comment author:* " + c.getAuthor().getDisplayName() + "\n\n";
            b += "---------------------------------------------------------\n";
            b += c.getBody();
            comment.setBody(b);
            connector.addComment(response.getNumber(), comment);

            // Make sure the next comment is in the next second
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        if(!issue.getFields().getStatus().getName().equals("Open")) {
            // close the issue
            githubIssue.setState("closed");
            connector.updateIssue(githubIssue, response.getNumber());
        }

        System.out.println("Job done");

        // wait a bit to prevent quota
        int req_min = 15;
        try {
            Thread.sleep(60_000 / req_min);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertType(IssueType type) {
        if(type == null) {
            return null;
        }
        switch(type.getId()) {
        case 1:
            return "bug";
        case 2:
        case 3:
        case 4:
            return "enhancement";
        default:
            throw new IllegalArgumentException("" + type.getId());
        }
    }

    private static String convertComponent(Component component) {
        if(component == null) {
            return null;
        }
        switch(component.getId()) {
        case 15050:
            return "core";
        case 15051:
            return "classextension";
        case 15052:
            return "integration";
        default:
            throw new IllegalArgumentException("" + component.getId());
        }
    }

    private static String convertPriority(IssuePriority priority) {
        if(priority == null) {
            return null;
        }
        switch(priority.getId()) {
        case 1:
            return "blocker";
        case 2:
            return "critical";
        case 3:
            return "major";
        case 4:
            return "minor";
        default:
            throw new IllegalArgumentException("" + priority.getId());
        }
    }

    private static String convertResolution(IssueResolution resolution) {
        if(resolution == null) {
            return null;
        }
        switch(resolution.getId()) {
        case 1:
            return "fixed";
        case 2:
            return "wontfix";
        case 6:
            return "notabug";
        default:
            throw new IllegalArgumentException("" + resolution.getId());
        }
    }
//    Gist gist = new Gist();
//    gist.setIsPublic(true);
//    gist.setDescription("Attachment to issue xxx");
//    for(Attachment a : issue.getFields().getAttachment()) {
//        body += "See https://github.com/henri-tremblay/easymock-issue-attachments/blob/master/" +
//        if(isIgnored(a)) {
//            continue;
//        }
//        // Put the attachment in a gist
//        String content = new String(Files.readAllBytes(Paths.get("output/attachments/" + a.getFilename())), StandardCharsets.UTF_8);
//        File file = new File();
//        file.setContent(content);
//        gist.getFiles().put(a.getFilename(), file);
//        //body += "See xxxx";
//    }
//    gist = connector.createGist(gist);

    private boolean isIgnored(Attachment a) {
        String file = a.getFilename();
        String extension = file.substring(file.lastIndexOf('.'));
        switch (extension) {
        case ".zip":
        case ".jar":
        case ".tbz":
        case ".gz":
            return true;
        default:
            return false;
        }
    }
}
