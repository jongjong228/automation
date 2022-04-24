package listeners;

import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.LoggerUtils;

public class BaseListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Assert.assertEquals(result.getStatus(), ITestResult.FAILURE, "test is not finished");
        LoggerUtils.getLogger().info("-----------Test Failed-----------");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Assert.assertEquals(result.getStatus(), ITestResult.SKIP, "test is not finished");
        LoggerUtils.getLogger().info("-----------Test Skipped-----------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Assert.assertEquals(result.getStatus(), ITestResult.SUCCESS, "test is not finished");
        LoggerUtils.getLogger().info("-----------Test Passed-----------");
    }
}
