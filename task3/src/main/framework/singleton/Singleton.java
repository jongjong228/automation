package singleton;

import factory.BrowserFactory;
import org.openqa.selenium.WebDriver;
import utils.MyLogger;

public class Singleton {
    private static Singleton instance = null;
    private static WebDriver driver;

    private Singleton(){
        this.driver = BrowserFactory.getWebDriver();
    }
    public static Singleton getInstance(){
        if (instance == null)
        instance = new Singleton();
        return instance;
    }
    public static WebDriver getDriver(){
        if (driver != null)
            return  driver;
        else {
            MyLogger.error("Driver is not initialized");
            throw new RuntimeException("Driver is not initialized");
        }
    }
    public static void zeroingInstance(){
        instance = null;
    }
}
