package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!])[a-zA-Z].{8,13})";

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validatePassword(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
