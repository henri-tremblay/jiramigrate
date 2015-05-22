package pro.tremblay.jiramigrate.util;

import java.net.HttpURLConnection;

public final class HttpUtil {

    private HttpUtil() {
    }

    public static void addAuthHeader(HttpURLConnection connection, String user, String password) {
        String auth = user + ":" + password;
        String authHeader = "Basic " + HashUtil.toBase64(auth);
        connection.setRequestProperty("Authorization", authHeader);
    }
}
