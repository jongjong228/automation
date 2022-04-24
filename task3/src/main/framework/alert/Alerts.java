package alert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import utils.MyLogger;
import utils.WaitUtils;

public class Alerts {
    private static Alert alert;
    public static boolean isAlertPresent(){
        try {
            MyLogger.info("Try to switch to alert");
            alert = WaitUtils.setWaitUntilAlertIsPresent();
            return true;
        } catch(NoAlertPresentException e) {
            MyLogger.error("No alert present");
            return false;
        }
    }
    public static void acceptAlert(){
        MyLogger.info("Try to accept alert");
        alert.accept();
    }
    public static void sendKeysAlert(String key){
        MyLogger.info("Try to send key in alert:" + key);
        alert.sendKeys(key);
    }
    public static String getTextAlert(){
        MyLogger.info("Try to get text of alert");
        return alert.getText();
    }

}
