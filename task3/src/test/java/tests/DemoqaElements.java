package tests;

import ddt.MyDataProvider;
import myutils.RefactorStrings;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigUtils;
import utils.DriverUtils;
import utils.MyLogger;
import utils.WaitUtils;

public class DemoqaElements extends BaseTest{

    @Test
    public void HandlesCase(){
        MyLogger.info("First step");
        DriverUtils.openURL(ConfigUtils.getConfProperty("HomePageURL"));
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageOpened()," Home Page is not opened");
        MyLogger.info("Second step");
        homePage.clickAlertButton();
        AlertsPageHome alertsPage = new AlertsPageHome();
        Assert.assertTrue(alertsPage.isPageOpened()," Alerts Page is not opened");
        alertsPage.clickBrowserWindowButton();
        BrowserWindowsForm browserWindowsForm = new BrowserWindowsForm();
        Assert.assertTrue(browserWindowsForm.isPageOpened(),"Browser Window Form is not opened");
        MyLogger.info("Third step");
        String mainHandle = DriverUtils.getCurrentHandle();
        browserWindowsForm.clickTabButton();
        WaitUtils.setWaitUntilNumberWindowToBe(2);
        DriverUtils.switchHandle(mainHandle);
        SimplePage simplePage = new SimplePage();
        Assert.assertTrue(simplePage.isPageOpened(),"Simple Page is not opened");
        Assert.assertEquals(simplePage.getURL(),ConfigUtils.getConfProperty("SimpleURL"),"Page name is not equals");
        MyLogger.info("Fourth step");
        DriverUtils.closeDriver();
        DriverUtils.returnToHandle(mainHandle);
        Assert.assertTrue(browserWindowsForm.isPageOpened(),"Browser Window Form is not opened");
        MyLogger.info("Fifth step");
        browserWindowsForm.clickElementButton();
        browserWindowsForm.waitHiddenElement();
        browserWindowsForm.clickLinksButton();
        LinksForm linksForm = new LinksForm();
        Assert.assertTrue(linksForm.isPageOpened(),"Links Form is not opened");
        MyLogger.info("Sixth step");
        mainHandle = DriverUtils.getCurrentHandle();
        linksForm.clickSimpleLink();
        WaitUtils.setWaitUntilNumberWindowToBe(2);
        DriverUtils.switchHandle(mainHandle);
        Assert.assertTrue(homePage.isPageOpened(),"Home Page is not opened");
        Assert.assertEquals(DriverUtils.getURL(),ConfigUtils.getConfProperty("HomePageURL"),"Page name is not equals");
        MyLogger.info("Seventh step");
        DriverUtils.returnToHandle(mainHandle);
        Assert.assertTrue(linksForm.isPageOpened(),"Links Form is not opened");
    }

    @Test(dataProvider = "testData",dataProviderClass = MyDataProvider.class)
    public void TableCase(String ftName,String sdName,String email,String age,String salary, String department){
        MyLogger.info("Fifth step");
        DriverUtils.openURL(ConfigUtils.getConfProperty("HomePageURL"));
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageOpened()," Home Page is not opened");
        MyLogger.info("Fifth step");
        homePage.clickElementsButton();
        ElementsPage elementsPage = new ElementsPage();
        Assert.assertTrue(elementsPage.isPageOpened()," Elements Page is not opened");
        elementsPage.clickElementButton();
        TablesForm webTableForm = new TablesForm();
        webTableForm.findFirstEmptyRecord();
        Assert.assertTrue(webTableForm.isPageOpened(),"Web Tables Form is not opened");
        MyLogger.info("Fifth step");
        webTableForm.clickRecordButton();
        RegistrationForm regForm = new RegistrationForm();
        Assert.assertTrue(regForm.isPageOpened(),"Web Tables Form is not opened");
        MyLogger.info("Fifth step");
        regForm.enterInfo(ftName, sdName, email, age, salary, department);
        regForm.clickSubmButton();
        String textOfNewRecord = webTableForm.getTextOfNewRecord();
        Assert.assertEquals(textOfNewRecord, RefactorStrings.getFormattedRecord(ftName,sdName,email,age,salary,department),"New Record is not equals");
        MyLogger.info("Fifth step");
        webTableForm.deleteNewRecord();
        Assert.assertTrue(webTableForm.isNewRecordDeleted(email),"New Record is not deleted");
    }
}
