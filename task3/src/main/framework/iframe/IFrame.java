package iframe;

import org.openqa.selenium.By;
import singleton.Singleton;
import utils.DriverUtils;
import utils.MyLogger;
import utils.WaitUtils;

public class IFrame {
    public static String getFrameTextByElement(By frameLocator,By textLocator){
        MyLogger.info("Get Frame Text By WebElement");
        DriverUtils.switchToFrameByElement(WaitUtils.setWaitUntilVisibility(frameLocator));
        MyLogger.info("Get Child frame text");
        return Singleton.getDriver().findElement(textLocator).getText();
    }
    public static String getFrameTextByID(By frameLocator,String id,By textLocator){
        MyLogger.info("Get Frame Text By Locator");
        WaitUtils.setWaitUntilVisibility(frameLocator);
        DriverUtils.switchToFrameByID(id);
        MyLogger.info("Get Child frame text");
        return Singleton.getDriver().findElement(textLocator).getText();
    }
}
