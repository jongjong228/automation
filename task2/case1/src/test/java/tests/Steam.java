package tests;

import pages.AboutPage;
import pages.GamePage;
import pages.HomePage;
import getProperties.ConfProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LeadersPage;
import singletone.Singleton;

import java.util.concurrent.TimeUnit;

public class Steam {
    private static HomePage homePage;
    private static AboutPage aboutPage;
    private static Singleton singleton;
    private static GamePage gamePage;
    private static LeadersPage leadersPage;

    @BeforeMethod
    public void signIn(){
        singleton = Singleton.getInstance();
        singleton.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(ConfProperties.getProperty("TimeStart")), TimeUnit.SECONDS);
        singleton.getDriver().manage().window().setSize(new Dimension(Integer.parseInt(ConfProperties.getProperty("Width")),Integer.parseInt(ConfProperties.getProperty("Height"))));
        singleton.getDriver().get(ConfProperties.getProperty("HomePageURL"));
    }

    @Test
    public void firstTestCase(){
        homePage = new HomePage(singleton);
        Assert.assertEquals(1,homePage.numOfUniqueElements());
    //first
        homePage.clickAboutBtn();
        aboutPage = new AboutPage(singleton);
        Assert.assertEquals(1,aboutPage.numOfUniqueElements());
    //sec
        Assert.assertTrue(aboutPage.compareNumOfPlayers());
    //third
        aboutPage.clickStoreBtn();
        Assert.assertEquals(1,homePage.numOfUniqueElements());
    //fourth
    }

    @Test
    public void secondTestCase(){
        homePage = new HomePage(singleton);
        Assert.assertEquals(1,homePage.numOfUniqueElements());
        //first step
        singleton.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(ConfProperties.getProperty("TimeStop")), TimeUnit.SECONDS);
        homePage.clickSalesLeadsBtn();
        singleton.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(ConfProperties.getProperty("TimeStart")), TimeUnit.SECONDS);
        leadersPage = new LeadersPage(singleton);
        Assert.assertEquals(1,leadersPage.numOfUniqueElements());
        //sec step
        leadersPage.clickLinuxCB();
        Assert.assertTrue(leadersPage.isLinuxCBClicked());
        //third step
        leadersPage.clickNumOfPlayerBtn();
        leadersPage.clickNumOfPlayerCB();
        Assert.assertTrue(leadersPage.isNumOfPlayersCBClicked());
        //fourth step
        leadersPage.clickSeeMoreBtn();
        leadersPage.clickActionCB();
        leadersPage.findNumOfGames();
        Assert.assertTrue(leadersPage.actionCheckBoxClicked());
        Assert.assertEquals(leadersPage.getNumOfGamesBefore(),leadersPage.getNumOfGames());
        //fifth step
        leadersPage.setGameTitle();
        leadersPage.setGameDate();
        leadersPage.setGamePrise();
        leadersPage.gameClick();
        //sixth step
        gamePage = new GamePage(singleton);
        Assert.assertEquals(1,gamePage.numOfUniqueElements());
        Assert.assertEquals(leadersPage.getGameDate(),gamePage.getGameDate());
        Assert.assertEquals(leadersPage.getGamePrise(),gamePage.getGamePrise());
        Assert.assertEquals(leadersPage.getGameTitle(),gamePage.getGameTitle());
        //seventh step
    }

    @AfterMethod
    public void quitBrowser(){
        singleton.getDriver().quit();
    }

}
