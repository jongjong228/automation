package singletone;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Singleton {
    private static Singleton instance = null;
    private WebDriver driver;

    private Singleton(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }
    public static Singleton getInstance(){
        instance = new Singleton();
        return instance;
    }
    public WebDriver getDriver(){
        return  driver;
    }
}
