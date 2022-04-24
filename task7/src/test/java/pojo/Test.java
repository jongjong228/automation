package pojo;

import CRUD.DBCRUD;
import keys.SQLKeys;
import utils.JsonUtils;
import utils.LoggerUtils;
import utils.SQLScriptsUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    private final String ID = JsonUtils.getString(SQLKeys.ID, JsonUtils.TABLES);
    private final String NAME = JsonUtils.getString(SQLKeys.NAME, JsonUtils.TABLES);
    private final String METHOD = JsonUtils.getString(SQLKeys.METHOD, JsonUtils.TABLES);
    private final String ENV = JsonUtils.getString(SQLKeys.ENV, JsonUtils.TABLES);
    private final String BROWSER = JsonUtils.getString(SQLKeys.BROWSER, JsonUtils.TABLES);
    private final String START = JsonUtils.getString(SQLKeys.START, JsonUtils.TABLES);
    private final String END = JsonUtils.getString(SQLKeys.END, JsonUtils.TABLES);
    private final String SESSION = JsonUtils.getString(SQLKeys.SESSION, JsonUtils.TABLES);
    private final String STATUS = JsonUtils.getString(SQLKeys.STATUS, JsonUtils.TABLES);
    private final String AUTHOR = JsonUtils.getString(SQLKeys.AUTHOR, JsonUtils.TABLES);
    private final String PROJECT = JsonUtils.getString(SQLKeys.PROJECT, JsonUtils.TABLES);
    private int id;
    private String name;
    private String methodName;
    private String startTime;
    private String endTime;
    private String env;
    private String browser;
    private int projectId;
    private int sessionId;
    private int authorId;
    private int statusId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getEnv() {
        return env;
    }

    public String getBrowser() {
        return browser;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Test(ResultSet resultSet) {
        try {
            id = resultSet.getInt(ID);
            name = resultSet.getString(NAME);
            methodName = resultSet.getString(METHOD);
            env = resultSet.getString(ENV);
            browser = resultSet.getString(BROWSER);
            startTime = resultSet.getString(START);
            endTime = resultSet.getString(END);
            sessionId = resultSet.getInt(SESSION);
            statusId = resultSet.getInt(STATUS);
            authorId = resultSet.getInt(AUTHOR);
            projectId = resultSet.getInt(PROJECT);
        } catch (SQLException ex) {
            LoggerUtils.getLogger().warn("take result exception");
        }
    }

    public Test(String name, String methodName, String env, String browser, String startTime, String endTime, int sessionId, int statusId, int authorId, int projectId) {
        setName(name);
        setMethodName(methodName);
        setEnv(env);
        setBrowser(browser);
        setStartTime(startTime);
        setEndTime(endTime);
        setSessionId(sessionId);
        setStatusId(statusId);
        setAuthorId(authorId);
        setProjectId(projectId);
    }

    public ArrayList<String> getParams() {
        ArrayList<String> params = new ArrayList<>();
        params.add(SQLScriptsUtils.formatString(this.name));
        params.add(SQLScriptsUtils.formatString(this.methodName));
        params.add(SQLScriptsUtils.formatString(this.env));
        params.add(SQLScriptsUtils.formatString(this.browser));
        params.add(SQLScriptsUtils.formatString(this.startTime));
        params.add(SQLScriptsUtils.formatString(this.endTime));
        params.add(Integer.toString(this.sessionId));
        params.add(Integer.toString(this.statusId));
        params.add(Integer.toString(this.authorId));
        params.add(Integer.toString(this.projectId));
        return params;
    }

    public static void changeValues(ArrayList<Test> tests) {
        for (Test test : tests) {
            test.setAuthorId(DBCRUD.getAuthorIdForTest(new Author()));
            test.setProjectId(DBCRUD.getProjectIdForTest(new Project()));
        }
    }
}
