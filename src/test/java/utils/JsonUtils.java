package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.User;

import java.io.IOException;


public class JsonUtils {
    private static final ISettingsFile testDate = new JsonSettingsFile("test_date.json");
    private static final ISettingsFile userDate = new JsonSettingsFile("fifth_user_date.json");

    public static String getValue(String name) {
        return testDate.getValue(name).toString();
    }

    public static String getUserValue(String name) {
        return userDate.getValue(name).toString();
    }

    public static User deserializeToPojo(Class T, String body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LoggerUtils.getLogger().info("Deserialize json to Pojo:");
            return (User) mapper.readValue(body, T);
        } catch (IOException e) {
            LoggerUtils.getLogger().warn("deserializing fail");
            e.printStackTrace();
            return null;
        }
    }

}
