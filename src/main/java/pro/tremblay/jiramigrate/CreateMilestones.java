package pro.tremblay.jiramigrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;
import pro.tremblay.jiramigrate.github.Milestone;
import pro.tremblay.jiramigrate.jira.Version;

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
public class CreateMilestones {

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

        Version[] versions = mapper.readValue(Paths.get("output", "raw", "project-EASYMOCK-versions.json").toFile(), Version[].class);
        Stream.of(versions).forEach(v -> {
            Milestone m = new Milestone();
            m.setTitle(v.getName());
            m.setDescription(v.getDescription());

            LocalDate dueDate = v.getReleaseDate();
            if(dueDate != null) {
                DateTime dateTime = dueDate.toDateTimeAtStartOfDay();
                m.setDue_on(ISODateTimeFormat.dateTimeNoMillis().print(dateTime));
            }
            m.setState(v.isReleased() ? "closed" : "open");

            connector.createMilestone(m);
        });
    }
}
