package org.data;

import java.util.Random;

public class TestData {
    public static final String VALID_LOGIN = "yv75y3xm";
    public static final String VALID_PASSWORD = "zIJSvq9Y";
    public static final String INVALID_LOGIN = "yv75y3xmDDD";
    public static final String INVALID_PASSWORD = "zIJSvq9YDSSF";
    public static final String VALID_PASSWORD_FOR_REGISTRATION = "12345678Qq@";
    public static final String CURRENCY_USD = "USD";
    public static final String CURRENCY_EUR = "EUR";
    public static final String COUNTRY_GB = "GB";
    public static final String COUNTRY_US = "US";
    ;
    public static final String DAY = "5";
    public static final String MONTH = "5";
    public static final String YEAR = "1964";
    public static final boolean TERMS_CHECK = true;

    public static String SIGN_IN_FIELD;
    public static String PHONE;
    public static final String BO_PROJECT = "3 jade";

    public static String generateEmail() {
        long currentTime = System.currentTimeMillis();
        int random = new Random().nextInt(1000);
        return "testUser" + currentTime + random + "@gmail.com";
    }

    public static String generateSignInField() {
        long currentTime = System.currentTimeMillis();
        int random = new Random().nextInt(1000);
        SIGN_IN_FIELD = "TEST_USER_" + currentTime + random;
        return SIGN_IN_FIELD;
    }

    public static String generatePhone() {
        Random random = new Random();
        String[] operatorCodes = {"67", "97", "96", "95", "73", "63", "50", "93"};
        String operatorCode = operatorCodes[random.nextInt(operatorCodes.length)];
        int part1 = 100 + random.nextInt(900);
        int part2 = 10 + random.nextInt(90);
        int part3 = 10 + random.nextInt(90);
        PHONE= String.format("+380(%s) %03d %02d %02d", operatorCode, part1, part2, part3);
        return PHONE;
    }
}
