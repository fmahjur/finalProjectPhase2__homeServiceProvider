package ir.maktab.HomeServiceProvider.validation;

import ir.maktab.HomeServiceProvider.exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static void isValid(String password) throws ValidationException {
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new ValidationException("invalid password!");
    }

}
