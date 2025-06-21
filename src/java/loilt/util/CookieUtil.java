package loilt.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;

public class CookieUtil {

    private static String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    private static String decode(String value) {
        return new String(Base64.getDecoder().decode(value));
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        String encodedValue = encode(value);
        Cookie cookie = new Cookie(name, encodedValue);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return decode(cookie.getValue());
                }
            }
        }
        return null;
    }

    public static void deleteCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
