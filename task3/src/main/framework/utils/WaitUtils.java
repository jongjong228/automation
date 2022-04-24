package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import singleton.Singleton;

public class WaitUtils {
    private static WebDriverWait wait;
    public static WebElement setWaitUntilVisibility(By locator){
        return wait.until(ExpectedConditions.visibilityOf(Singleton.getDriver().findElement(locator)));
    }
    public static void setWaitUntilNumberWindowToBe(int numOfWindows){
        wait.until(ExpectedConditions.numberOfWindowsToBe(numOfWindows));
    }
    public static Alert setWaitUntilAlertIsPresent(){
        return wait.until(ExpectedConditions.alertIsPresent());
    }
    public static void initWait(){
        wait = new WebDriverWait(Singleton.getDriver(),Integer.parseInt(ConfigUtils.getConfProperty("AlertTimeStart")));
    }
    public static void setWaitUntilAttributeToBe(By locator,String attribute,String value){
        wait.until(ExpectedConditions.attributeToBe(locator,attribute,value));
    }
}
