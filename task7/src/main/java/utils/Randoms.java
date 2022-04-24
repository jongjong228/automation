package utils;

import keys.JsonKeys;
import org.apache.commons.lang.RandomStringUtils;

public class Randoms {
    private static final int LENGTH = JsonUtils.getInt(JsonKeys.RANDOM_LENGTH, JsonUtils.PROPERTIES);
    private static final int MIN_LENGTH = JsonUtils.getInt(JsonKeys.MIN_LENGTH, JsonUtils.PROPERTIES);

    public static String getRandomString() {
        LoggerUtils.getLogger().info("get random string");
        return RandomStringUtils.randomAlphabetic((int) (Math.random() * LENGTH + MIN_LENGTH));
    }

    public static int getRandomNumber() {
        LoggerUtils.getLogger().info("get random number");
        return (int) (Math.random() * 9) + 1;
    }

    public static int getAnotherID(int id) {
        LoggerUtils.getLogger().info("get random status id");
        switch (id) {
            case 1:
                id += (int) (Math.random() * 2) + 1;
                break;
            case 2:
                if ((int) (Math.random() * 2) != 1)
                    id++;
                else
                    id--;
                break;
            case 3:
                id -= ((int) (Math.random() * 2) + 1);
                break;
            default:
                LoggerUtils.getLogger().warn("wrong status id");
                break;
        }
        return id;
    }
}
