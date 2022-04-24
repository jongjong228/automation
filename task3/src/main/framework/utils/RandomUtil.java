package utils;

import org.apache.commons.lang.RandomStringUtils;

public class RandomUtil {
    private static final int maxStringSize = Integer.parseInt(ConfigUtils.getConfProperty("MaxRandomStringSize"));
    private static final int maxSliderValue = Integer.parseInt(ConfigUtils.getTestProperty("MaxSliderValue"));
    public static String getRandomString() {
        return RandomStringUtils.randomAlphanumeric((int) (Math.random() * maxStringSize + 1));
    }
    public static int getRandomSliderValue(){
        return (int)(Math.random() * maxSliderValue);
    }
}
