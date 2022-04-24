package CRUD;

import keys.SQLKeys;
import keys.ScriptsKeys;
import pojo.Author;
import pojo.Project;
import pojo.Session;
import pojo.Test;
import utils.JsonUtils;
import utils.LoggerUtils;
import utils.MySQLUtils;
import utils.SQLScriptsUtils;

import java.sql.*;
import java.util.ArrayList;

public class DBCRUD {
    private static final String GET_AUTHOR = JsonUtils.getString(ScriptsKeys.GET_AUTHOR, JsonUtils.SQL_SCRIPTS);
    private static final String AUTHOR = JsonUtils.getString(ScriptsKeys.AUTHOR, JsonUtils.SQL_SCRIPTS);
    private static final String CREATE_PROJECT = JsonUtils.getString(ScriptsKeys.CREATE_PROJECT, JsonUtils.SQL_SCRIPTS);
    private static final String PROJECT = JsonUtils.getString(ScriptsKeys.PROJECT, JsonUtils.SQL_SCRIPTS);
    private static final String SESSION = JsonUtils.getString(ScriptsKeys.SESSION, JsonUtils.SQL_SCRIPTS);
    private static final String NEW_SESSION = JsonUtils.getString(ScriptsKeys.NEW_SESSION, JsonUtils.SQL_SCRIPTS);
    private static final String ID = JsonUtils.getString(SQLKeys.ID, JsonUtils.TABLES);
    private static final String STATUS = JsonUtils.getString(ScriptsKeys.STATUS, JsonUtils.SQL_SCRIPTS);
    private static final String ADD_TESTS = JsonUtils.getString(ScriptsKeys.ADD_TESTS, JsonUtils.SQL_SCRIPTS);
    private static final String DELETE = JsonUtils.getString(ScriptsKeys.DELETE, JsonUtils.SQL_SCRIPTS);
    private static final String UPDATE = JsonUtils.getString(ScriptsKeys.UPDATE, JsonUtils.SQL_SCRIPTS);
    private static final String CONTAINS = JsonUtils.getString(ScriptsKeys.CONTAINS, JsonUtils.SQL_SCRIPTS);
    private static final String UPDATED = JsonUtils.getString(ScriptsKeys.UPDATED, JsonUtils.SQL_SCRIPTS);


    public static int getAuthorIdForTest(Author author) {
        LoggerUtils.getLogger().info("get author id for test");
        return getId(GET_AUTHOR, author.getName(), AUTHOR, author.getParams());
    }

    public static int getProjectIdForTest(Project project) {
        LoggerUtils.getLogger().info("get project id for test");
        return getId(PROJECT, project.getName(), CREATE_PROJECT, project.getParams());
    }

    public static int getSessionIdForTest(Session session) {
        LoggerUtils.getLogger().info("get session id for test");
        return getId(SESSION, session.getKey(), NEW_SESSION, session.getParams());
    }

    public static int getId(String script, String name, String recordScript, ArrayList<String> params) {
        LoggerUtils.getLogger().info("get id");
        try (Connection connection = DriverManager.getConnection(MySQLUtils.getUrl(), MySQLUtils.getUserName(), MySQLUtils.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(script)) {
            preparedStatement.setString(1, name);
            LoggerUtils.getLogger().info("execute Query");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt(ID);
            } else {
                createRecord(recordScript, params);
                return getId(script, name, recordScript, params);
            }
        } catch (SQLException e) {
            LoggerUtils.getLogger().warn("take data from select script fail");
            return 0;
        }
    }

    private static void createRecord(String script, ArrayList<String> params) {
        MySQLUtils.executeUpdate(script + SQLScriptsUtils.getValues(params));
    }

    public static int getStatusIdForTest(String name) {
        LoggerUtils.getLogger().info("get status id for test");
        try (Connection connection = DriverManager.getConnection(MySQLUtils.getUrl(), MySQLUtils.getUserName(), MySQLUtils.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(STATUS)) {
            preparedStatement.setString(1, name);
            LoggerUtils.getLogger().info("execute Query");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt(ID);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            LoggerUtils.getLogger().warn("take data from select script fail");
            return 0;
        }
    }

    public static int addTests(ArrayList<Test> tests) {
        StringBuilder values = new StringBuilder();
        for (Test test : tests) {
            values.append(SQLScriptsUtils.getValues(test.getParams()));
            values.append(",");
        }
        values.deleteCharAt(values.length() - 1);
        return MySQLUtils.executeUpdate(ADD_TESTS + values);
    }

    public static int deleteUpdatedTests(int num) {
        LoggerUtils.getLogger().info("delete updated tests");
        try (Connection connection = DriverManager.getConnection(MySQLUtils.getUrl(), MySQLUtils.getUserName(), MySQLUtils.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, num);
            LoggerUtils.getLogger().info("execute update");
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LoggerUtils.getLogger().warn("execute update fail");
            return 0;
        }
    }

    public static int updateTest(int status, int id) {
        LoggerUtils.getLogger().info("update tests with status_id");
        try (Connection connection = DriverManager.getConnection(MySQLUtils.getUrl(), MySQLUtils.getUserName(), MySQLUtils.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, status);
            LoggerUtils.getLogger().info("execute update");
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LoggerUtils.getLogger().warn("execute update fail");
            return 0;
        }
    }

    public static void getContainsTests(ArrayList<Test> tests, int limit, int num) {
        LoggerUtils.getLogger().info("get tests witch id%11=0");
        try (Connection connection = DriverManager.getConnection(MySQLUtils.getUrl(), MySQLUtils.getUserName(), MySQLUtils.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(CONTAINS)) {
            preparedStatement.setInt(1, num);
            preparedStatement.setInt(2, limit);
            LoggerUtils.getLogger().info("execute Query");
            ResultSet resultSet = preparedStatement.executeQuery();
            getTests(tests, resultSet);
        } catch (SQLException e) {
            LoggerUtils.getLogger().warn("execute query fail");
        }
    }

    public static void getUpdatedTests(ArrayList<Test> tests, int limit) {
        LoggerUtils.getLogger().info("get updated tests");
        try (Connection connection = DriverManager.getConnection(MySQLUtils.getUrl(), MySQLUtils.getUserName(), MySQLUtils.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATED)) {
            preparedStatement.setInt(1, limit);
            LoggerUtils.getLogger().info("execute Query");
            ResultSet resultSet = preparedStatement.executeQuery();
            getTests(tests, resultSet);
        } catch (SQLException e) {
            LoggerUtils.getLogger().warn("execute query fail");
        }
    }

    public static void getTests(ArrayList<Test> tests, ResultSet resultSet) {
        try {
            if (resultSet != null)
                while (resultSet.next()) {
                    Test test = new Test(resultSet);
                    tests.add(test);
                }
        } catch (SQLException e) {
            LoggerUtils.getLogger().warn("take data from select script fail");
        }
    }
}

