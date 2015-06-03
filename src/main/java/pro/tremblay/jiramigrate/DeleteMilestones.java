package pro.tremblay.jiramigrate;

import pro.tremblay.jiramigrate.github.Milestone;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * @author Henri Tremblay
 */
public class DeleteMilestones {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        try(InputStream in = new FileInputStream("jiramigrate.properties")) {
            properties.load(in);
        }

        GitHubConnector connector = new GitHubConnector();
        connector.setServerUrl("https://api.github.com");
        connector.setLogin(properties.getProperty("github.login"));
        connector.setPassword(properties.getProperty("github.password"));
        connector.setListener((req, res) -> {
            System.out.println(req);
            System.out.println(new String(res, StandardCharsets.UTF_8));
        });

        connector.init();

        Milestone[] milestones = connector.getAllMilestones();
        Stream.of(milestones).forEach(m -> {
            connector.deleteMilestone(m.getNumber());
        });
    }
}
