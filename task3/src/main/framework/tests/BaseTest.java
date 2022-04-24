package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverUtils;
import utils.MyLogger;

public abstract class BaseTest {
    @BeforeMethod
    protected void init(){
        MyLogger.info("Start of case");
        DriverUtils.initDriver();
    }
   @AfterMethod
    protected void quitBrowser(){
       MyLogger.info("End of case");
        DriverUtils.quitDriver();
    }
}
