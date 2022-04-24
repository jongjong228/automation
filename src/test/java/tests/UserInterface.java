package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AvatarAndInterestPage;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalDetailsPage;

import java.awt.*;

public class UserInterface {
    private final ISettingsFile  testDate = new JsonSettingsFile("test_date.json");
    private final ISettingsFile  properties = new JsonSettingsFile("properties.json");
    private final Logger logger = AqualityServices.getLogger();
    private final String mainURL = properties.getValue("/MainURL").toString();
    private Browser browser;
    private MainPage mainPage;
    private LoginPage loginPage;
    @BeforeMethod
    private void init(){
        browser = AqualityServices.getBrowser();
        browser.maximize();
        logger.info("Step: Go to Welcome page ");
        browser.goTo(mainURL);
        browser.waitForPageToLoad();
        mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().isDisplayed(),"Main Page is not opened");
    }
    @Test()
    public void logInTest(){
        logger.info("Step: go to the first card");
        mainPage.clickLink();
        loginPage = new LoginPage();
        Assert.assertTrue(loginPage.state().isDisplayed()," Login Page is not opened");
        logger.info("Step: enter login date");
        loginPage.clickSendHelpButton();
        loginPage.waitForClosingHelpForm();
        loginPage.setRandomEmail();
        loginPage.setRandomDomain();
        loginPage.setRandomPassword();
        loginPage.setTopLevelDomain();
        loginPage.clickCheckBox();
        loginPage.clickNextLink();
        AvatarAndInterestPage avatarAndInterestPage = new AvatarAndInterestPage();
        Assert.assertTrue(avatarAndInterestPage.state().isDisplayed()," Avatar Page is not opened");
        logger.info("Step: enter personal date");
        avatarAndInterestPage.clickUnselectCB();
        avatarAndInterestPage.clickInterest();
        avatarAndInterestPage.clickInterest();
        avatarAndInterestPage.clickInterest();
        avatarAndInterestPage.clickNextButton();
        avatarAndInterestPage.waitForImageError();
        avatarAndInterestPage.clickAndWaitDownloadButton();
        avatarAndInterestPage.uploadFileAndWait();
        avatarAndInterestPage.clickNextButton();
        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage();
        //Assert.assertTrue(personalDetailsPage.isDisplayed()," Personal Details Page is not opened");
    }
    @Test
    public void cookieFormTest(){
        mainPage.clickLink();
        loginPage = new LoginPage();
        logger.info("Step: accept cookie");
        loginPage.clickAcceptCookies();
        loginPage.waitForCookieAccept();
        Assert.assertTrue(loginPage.isCookieFormClosed(),"Cookies form is not closed");
    }
    @Test
    public void helpFormTest(){
        mainPage.clickLink();
        loginPage = new LoginPage();
        logger.info("Step: hide help form");
        loginPage.clickSendHelpButton();
        loginPage.waitForClosingHelpForm();
        Assert.assertTrue(loginPage.isHelpFormClosed(),"Help Form is not closed");
    }
    @Test
    public void timerValueTest(){
        mainPage.clickLink();
        loginPage = new LoginPage();
        logger.info("Step: check start timer value");
        Assert.assertEquals(loginPage.getTimerValue(),testDate.getValue("/timerValue").toString(),"Timer value is not starting from zero");
    }
    @AfterMethod
    private void quitBrowser(){
        logger.info("End of the test case");
        browser.quit();
    }
}
