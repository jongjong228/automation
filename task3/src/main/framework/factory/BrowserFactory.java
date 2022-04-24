package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.ConfigUtils;
import utils.MyLogger;

public class BrowserFactory {
    public static WebDriver getWebDriver(){
        String browserName = ConfigUtils.getConfProperty("Browser");
        switch (browserName){
            case "Chrome":
                MyLogger.info("Create chrome driver");
                WebDriverManager.chromedriver().setup();
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.addArguments(ConfigUtils.getChromeSizes());
                return new ChromeDriver(cOptions);
            case "Firefox":
                MyLogger.info("Create firefox driver");
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.addArguments(ConfigUtils.getFirefoxWidth(), ConfigUtils.getFirefoxHeight());
                return new FirefoxDriver(fOptions);
            default:
                MyLogger.error("Wrong Browser name");
                throw new RuntimeException("Incorrect BrowserName");
        }
    }
}
