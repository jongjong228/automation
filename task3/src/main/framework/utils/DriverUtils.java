package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import singleton.Singleton;


public class DriverUtils {
    public DriverUtils(){
    }
    public static void initDriver(){
        Singleton.getInstance();
        WaitUtils.initWait();
    }
    public static void quitDriver(){
        MyLogger.info("Quit driver");
        Singleton.getDriver().quit();
        Singleton.zeroingInstance();
    }
    public static void closeDriver(){
        MyLogger.info("Close Window");
        Singleton.getDriver().close();
    }
    public static void switchToFrameByElement(WebElement element){
        MyLogger.info("Switch to frame");
       Singleton.getDriver().switchTo().frame(element);
    }
    public static void switchToFrameByID(String str){
        MyLogger.info("Switch to frame");
        Singleton.getDriver().switchTo().frame(str);
    }
    public static void endWithFrame(){
        MyLogger.info("Switch to default content");
        Singleton.getDriver().switchTo().defaultContent();
    }

    public static void openURL(String url){
        MyLogger.info("Get URL");
        Singleton.getDriver().get(url);
    }
    public static String getCurrentHandle(){
        return Singleton.getDriver().getWindowHandle();
    }
    public static void returnToHandle(String handle){
        Singleton.getDriver().switchTo().window(handle);
    }
    public static String getURL(){
        return Singleton.getDriver().getCurrentUrl();
    }
    public static void switchHandle(String mainHandle){
        for (String windowHandle : Singleton.getDriver().getWindowHandles()) {
            if(!windowHandle.contentEquals(mainHandle)) {
                Singleton.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }
    public static void scrollToElement(WebElement element){
        Point location = element.getLocation();
        ((JavascriptExecutor)Singleton.getDriver()).executeScript("window.scrollBy(0,"+location.y+");");
    }
}
