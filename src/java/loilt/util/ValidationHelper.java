package loilt.util;

public class ValidationHelper {

    public static final String VALID_USER_ID = "^[a-zA-Z0-9]{3,20}$";
    public static final String VALID_PASSWORD = "^.{6,30}$";
    public static final String VALID_NAME = "^[a-zA-Z\\s]{2,50}$"; // Cho cả firstName và lastName

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isPositiveInt(String value) {
        if (value == null) {
            return false;
        }
        try {
            int n = Integer.parseInt(value);
            return n > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean matchWithPattern(String value, String pattern) {
        return value != null && value.matches(pattern);
    }

    public static boolean isPasswordConfirmed(String password, String confirm) {
        return password != null && password.equals(confirm);
    }
}
