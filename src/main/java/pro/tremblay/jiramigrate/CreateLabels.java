package pro.tremblay.jiramigrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import pro.tremblay.jiramigrate.github.Label;
import pro.tremblay.jiramigrate.jira.Priority;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * @author Henri Tremblay
 */
public class CreateLabels {

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
            System.out.println(new String(res, StandardCharsets.UTF_8));
        });

        connector.init();

        ObjectMapper mapper = new ObjectMapper();

        // Components are labels but I made them manually
        // Resolution are labels but I made them manually

        // Priorities are labels
        Priority[] priorities = mapper.readValue(Paths.get("output", "raw", "priority.json").toFile(), Priority[].class);
        Stream.of(priorities).forEach(p -> {
            Label l = new Label();
            l.setName(p.getName());
            l.setColor(p.getStatusColor().substring(1)); // skip the #
            connector.createLabel(l);
        });
    }
}
