package pro.tremblay.jiramigrate.util;

import com.google.common.base.Charsets;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtil {

    public static String toBase64(String input) {
        byte[] bytes = Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
        return new String(bytes, Charsets.UTF_8);
    }

    public static String toSha1(String msg) {
        byte[] encoded = msg.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = Base64.getEncoder().encode(encoded);
        return new String(bytes, Charsets.UTF_8);
    }

    private HashUtil() {}
}
