package pro.tremblay.jiramigrate;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;
import pro.tremblay.jiramigrate.util.HttpUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class BaseRestConnector {

    public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {
            ClientHttpResponse response = execution.execute(request, body);
            log(request, body, response);
            return response;
        }

        private void log(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
            if(listener == null) {
                return;
            }
            String req = request.getURI().toString();
            req = req.substring(serverUrl.length() + (req.contains(getSuffix()) ? getSuffix().length() : 0) - 1); // keep only the end
            byte[] res = IOUtils.toByteArray(response.getBody());

            listener.accept(req, res);
        }
    }

    private String serverUrl;

    private String login;

    private String password;

    private RestTemplate restTemplate;

    private BiConsumer<String, byte[]> listener;

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }

    protected abstract String getSuffix();

    protected String getServerUrl() {
        return serverUrl;
    }

    /**
     * Configure the authentication for the request. The default being basic auth
     */
    protected void addAuthentication(HttpURLConnection connection) {
        HttpUtil.addAuthHeader(connection, login, password);
    }

    @PostConstruct
    public void init() {
        restTemplate = initRestTemplate(this::addAuthentication);
    }

    protected RestTemplate initRestTemplate(Consumer<HttpURLConnection> postConnectionOpenHook) {
        ClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory() {

            @Override
            protected HttpURLConnection openConnection(URL url, Proxy proxy) throws IOException {
                HttpURLConnection connection = super.openConnection(url, proxy);
                postConnectionOpenHook.accept(connection);
                return connection;
            }
        };

        List<ClientHttpRequestInterceptor> ris = Collections.singletonList(new LoggingRequestInterceptor());

        factory = new InterceptingClientHttpRequestFactory(new BufferingClientHttpRequestFactory(factory) , ris);

        return new RestTemplate(factory);
    }

    protected <T> T getForObject(String path, Class<T> type, Object... urlVariables) {
        return getRestTemplate().getForObject(serverUrl + getSuffix() + path, type, urlVariables);
    }

    protected <T> T postForObject(String path, Object request, Class<T> type, Object... urlVariables) {
        return getRestTemplate().postForObject(serverUrl + getSuffix() + path, request, type, urlVariables);
    }

    protected void delete(String path, Object... urlVariables) {
        getRestTemplate().delete(serverUrl + getSuffix() + path, urlVariables);
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setListener(BiConsumer<String, byte[]> listener) {
        this.listener = listener;
    }
}
