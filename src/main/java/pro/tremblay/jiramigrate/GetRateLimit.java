package pro.tremblay.jiramigrate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author Henri Tremblay
 */
public class GetRateLimit {

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
        connector.getLimits();
    }
}
