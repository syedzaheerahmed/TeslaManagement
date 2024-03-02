package com.example.TeslaManagement.Utils;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
public class PasswordGenerator {
    public String generateSecurePassword() {
        String upperCaseStr = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseStr = RandomStringUtils.random(2, 97, 122, true, true);
        String numbersStr = RandomStringUtils.randomNumeric(2);
        String specialCharStr = RandomStringUtils.random(2, 33, 47, false, false);
        String totalCharsStr = RandomStringUtils.randomAlphanumeric(2);
        String demoPassword = upperCaseStr.concat(lowerCaseStr)
                .concat(numbersStr)
                .concat(specialCharStr)
                .concat(totalCharsStr);
        List<Character> listOfChar = demoPassword.chars()
                .mapToObj(data -> (char) data)
                .collect(Collectors.toList());
        Collections.shuffle(listOfChar);
        String password = listOfChar.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }
}
