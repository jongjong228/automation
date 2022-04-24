package listeners;

import CRUD.*;
import keys.SQLKeys;
import keys.SQLTestKeys;
import keys.SettingsKeys;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pojo.Author;
import pojo.Project;
import pojo.Session;
import pojo.Test;
import utils.*;

import java.util.ArrayList;
import java.util.Date;

public class VKListener implements ITestListener {
    private final String FAILED = JsonUtils.getString(SQLKeys.FAILED,  JsonUtils.TABLES);
    private final String SKIPPED = JsonUtils.getString(SQLKeys.SKIPPED,  JsonUtils.TABLES);
    private final String PASSED = JsonUtils.getString(SQLKeys.PASSED, JsonUtils.TABLES);
    private final String TEST = JsonUtils.getString(SQLTestKeys.TEST, JsonUtils.SQL_DATA);
    private final String NAME = JsonUtils.getString(SQLTestKeys.NAME, JsonUtils.SQL_DATA);
    private final String BROWSER_NAME = JsonUtils.getString(SettingsKeys.BROWSER_NAME, JsonUtils.SETTINGS);
    private String status;
    private Date startTime;
    private Date endTime;

    @Override
    public void onTestStart(ITestResult res) {
        LoggerUtils.getLogger().info("-----------TS-1 START TEST-----------");
        startTime = new Date();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Assert.assertEquals(result.getStatus(), ITestResult.FAILURE, "test is not finished");
        LoggerUtils.getLogger().info("-----------Test Failed-----------");
        endTime = new Date();
        status = FAILED;
        listenerBody(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Assert.assertEquals(result.getStatus(), ITestResult.SKIP, "test is not finished");
        LoggerUtils.getLogger().info("-----------Test Skipped-----------");
        endTime = new Date();
        status = SKIPPED;
        listenerBody(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Assert.assertEquals(result.getStatus(), ITestResult.SUCCESS, "test is not finished");
        LoggerUtils.getLogger().info("-----------Test Passed-----------");
        endTime = new Date();
        status = PASSED;
        listenerBody(result.getMethod().getMethodName());
    }

    private void listenerBody(String methodName) {
        LoggerUtils.getLogger().info("-----------TS-1 INSERT TEST RESULTS IN DB-----------");
        MySQLUtils.prepareDB();
        int statusId = DBCRUD.getStatusIdForTest(status);
        Session session = new Session(SQLScriptsUtils.formatDate(startTime), Randoms.getRandomNumber(), SQLScriptsUtils.formatDate(endTime));
        int sessionId = DBCRUD.getSessionIdForTest(session);
        int authorId = DBCRUD.getAuthorIdForTest(new Author());
        int projectId = DBCRUD.getProjectIdForTest(new Project());
        ArrayList<Test> tests = new ArrayList<>();
        Test recordData = new Test(TEST, methodName, NAME, BROWSER_NAME, SQLScriptsUtils.formatDate(startTime), SQLScriptsUtils.formatDate(endTime), sessionId, statusId, authorId, projectId);
        tests.add(recordData);
        Assert.assertEquals(DBCRUD.addTests(tests), tests.size(), "tests are not added to db");
    }
}
