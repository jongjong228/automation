package utils;

import org.apache.commons.lang.RandomStringUtils;

public class Strings {
    public static String getRandomString() {
        LoggerUtils.getLogger().info("get random string");
        return RandomStringUtils.randomAlphabetic((int) (Math.random() * Integer.parseInt(JsonUtils.getValue(JsonKeys.randomLength)) + 1));
    }
}
