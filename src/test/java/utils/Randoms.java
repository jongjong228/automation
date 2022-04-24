package utils;

import keys.JsonKeys;
import org.apache.commons.lang.RandomStringUtils;

public class Randoms {
    public static String getRandomString() {
        LoggerUtils.getLogger().info("get random string");
        return RandomStringUtils.randomAlphabetic((int) (Math.random() * JsonUtils.getInt(JsonKeys.RANDOM_LENGTH) + JsonUtils.getInt(JsonKeys.MIN_LENGTH)));
    }
}
