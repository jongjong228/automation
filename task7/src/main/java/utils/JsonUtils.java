package utils;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class JsonUtils {
    public static final String API = "api_data.json";
    public static final String PROPERTIES = "properties.json";
    public static final String PHOTO_JSON = "photo_data.json";
    public static final String TEST_DATA = "test_data.json";
    public static final String SQL_SCRIPTS = "sql_scripts.json";
    public static final String TABLES = "sql_tables.json";
    public static final String SETTINGS = "settings.json";
    public static final String SQL_DATA = "sql_data.json";
    public static final String CONFIG_JSON = "sql_config.json";

    public static String getString(String name, String resourceName) {
        LoggerUtils.getLogger().info("Take string from json");
        return new JsonSettingsFile(resourceName).getValue(name).toString();
    }

    public static int getInt(String name, String resourceName) {
        LoggerUtils.getLogger().info("Take int from json");
        return Integer.parseInt(new JsonSettingsFile(resourceName).getValue(name).toString());
    }
}
