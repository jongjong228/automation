package tests;
import alert.Alerts;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigUtils;
import utils.DriverUtils;
import utils.RandomUtil;
import utils.MyLogger;

public class AlertsFramesWindows extends BaseTest{

    @Test
    public void AlertsCase(){
        MyLogger.info("First step");
        DriverUtils.openURL(ConfigUtils.getConfProperty("HomePageURL"));
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageOpened()," Home Page is not opened");
        MyLogger.info("Second step");
        homePage.clickAlertButton();
        AlertsPageHome alertsPage = new AlertsPageHome();
        Assert.assertTrue(alertsPage.isPageOpened()," Alerts Page is not opened");
        alertsPage.clickAlertButton();
        AlertsForm alertsForm = new AlertsForm();
        Assert.assertTrue(alertsForm.isPageOpened(),"Alerts Form is not opened");
        MyLogger.info("Third step");
        alertsForm.clickSimpleButton();
        Assert.assertTrue(Alerts.isAlertPresent(),"Simple Alert is not opened");
        MyLogger.info("Fourth step");
        Alerts.acceptAlert();
        Assert.assertTrue(alertsForm.isPageOpened(),"Simple Alert is not closed");
        MyLogger.info("Fifth step");
        alertsForm.clickConfirmButton();
        Assert.assertTrue(Alerts.isAlertPresent(),"Confirm Alert is not opened");
        Assert.assertEquals(Alerts.getTextAlert(),ConfigUtils.getTestProperty("AlertsConfirmText"),"Confirm Alert text is not equals");
        MyLogger.info("Sixth step");
        Alerts.acceptAlert();
        Assert.assertTrue(alertsForm.isPageOpened(),"Confirm Alert is not closed");
        Assert.assertEquals(alertsForm.getConfirmAlertText(),ConfigUtils.getTestProperty("TextNearConfirmButton"),"Text near Confirm Button is not equals");
        MyLogger.info("Seventh step");
        alertsForm.clickPromptButton();
        Assert.assertTrue(Alerts.isAlertPresent(),"Prompt Alert is not opened");
        Assert.assertEquals(Alerts.getTextAlert(),ConfigUtils.getTestProperty("AlertsPromptText"),"Prompt Alert text is not equals");
        MyLogger.info("Eighth step");
        String randomString = RandomUtil.getRandomString();
        Alerts.sendKeysAlert(randomString);
        Alerts.acceptAlert();
        Assert.assertTrue(alertsForm.isPageOpened(),"Prompt Alert is not closed");
        String namePrompt = alertsForm.getPromptAlertText().replaceAll("You entered ","");
        Assert.assertEquals(randomString,namePrompt,"Name in alert and name near prompt button is not equals");
    }
    @Test
    public void IFrameCase(){
        MyLogger.info("First step");
        DriverUtils.openURL(ConfigUtils.getConfProperty("HomePageURL"));
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageOpened()," Home Page is not opened");
        MyLogger.info("Second step");
        homePage.clickAlertButton();
        AlertsPageHome alertsPage = new AlertsPageHome();
        Assert.assertTrue(alertsPage.isPageOpened()," Alerts Page is not opened");
        alertsPage.clickNestedFramesButton();
        NestedFramesForm nestedFramesForm = new NestedFramesForm();
        Assert.assertTrue(nestedFramesForm.isPageOpened(),"Nested Frames Form is not opened");
        String str = nestedFramesForm.getParentFrameText();
        Assert.assertEquals(str,ConfigUtils.getTestProperty("ParentFrameText"),"Parent Frame Text is not equals");
        Assert.assertEquals(nestedFramesForm.getChildFrameText(),ConfigUtils.getTestProperty("ChildFrameText"),"Child Frame Text is not equals");
        DriverUtils.endWithFrame();
        MyLogger.info("Third step");
        nestedFramesForm.clickFramesButton();
        FramesForm framesForm = new FramesForm();
        Assert.assertTrue(framesForm.isPageOpened(),"Alerts Form is not opened");
        String upperFormText = framesForm.getUpperFormText();
        DriverUtils.endWithFrame();
        String bottomFormText = framesForm.getBottomFormText();
        DriverUtils.endWithFrame();
        Assert.assertEquals(bottomFormText,upperFormText,"Frames Texts are not equals");
    }


}
