package utils;

import keys.SQLKeys;

import java.sql.*;

public class MySQLUtils {
    private static final String PATH = JsonUtils.getString(SQLKeys.CONNECTOR_PATH, JsonUtils.CONFIG_JSON);
    private static final String URL = JsonUtils.getString(SQLKeys.URL, JsonUtils.CONFIG_JSON);
    private static final String DB = JsonUtils.getString(SQLKeys.DB_NAME, JsonUtils.CONFIG_JSON);
    private static final String NAME = JsonUtils.getString(SQLKeys.USER_NAME, JsonUtils.CONFIG_JSON);
    private static final String PASSWORD = JsonUtils.getString(SQLKeys.PASSWORD, JsonUtils.CONFIG_JSON);

    public static void prepareDB() {
        try {
            LoggerUtils.getLogger().info("connect DB");
            Class.forName(PATH).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            LoggerUtils.getLogger().warn("Driver connection fail");
        }
    }

    public static String getUrl() {
        return URL + DB;
    }

    public static String getUserName() {
        return NAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static int executeUpdate(String command) {
        LoggerUtils.getLogger().info("execute update");
        try (Connection connection = DriverManager.getConnection(URL + DB, NAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate(command);
        } catch (SQLException e) {
            LoggerUtils.getLogger().warn("execute update fail");
            return 0;
        }
    }
}
