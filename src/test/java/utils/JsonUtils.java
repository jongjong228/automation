package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;


public class JsonUtils {
    private static final ISettingsFile testDate = new JsonSettingsFile("properties.json");
    private static final ISettingsFile userDate = new JsonSettingsFile("test_date.json");
    private static final ISettingsFile photoDate = new JsonSettingsFile("photo_data.json");

    public static String getString(String name) {
        LoggerUtils.getLogger().info("Take json value");
        return testDate.getValue(name).toString();
    }

    public static int getInt(String name) {
        LoggerUtils.getLogger().info("Take json value");
        return Integer.parseInt(testDate.getValue(name).toString());
    }

    public static String getPhotoString(String name) {
        LoggerUtils.getLogger().info("Take json value");
        return photoDate.getValue(name).toString();
    }

    public static String getUserString(String name) {
        LoggerUtils.getLogger().info("Take json value");
        return userDate.getValue(name).toString();
    }
}
