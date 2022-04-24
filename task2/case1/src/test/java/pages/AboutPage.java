package pages;

import functions.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import singletone.Singleton;

import java.util.List;

public class AboutPage {
    private WebDriver driver;
    private  final By playerFieldPath = By.xpath("//div[contains(@class,'in_game')]/parent::div");
    private  final By onlineFieldPath = By.xpath("//div[contains(@class,'_online')]/parent::div");
    private  final By storePath = By.xpath("//span[@id='logo_holder']//img");
    private  final By uniquePath = By.xpath("//div[@id='about_greeting']");
    public int numOfUniqueElements(){
        return driver.findElements((uniquePath)).size();
    }
    public AboutPage(Singleton singleton) {
        driver = singleton.getDriver();
    }
    public Boolean compareNumOfPlayers(){
        return Functions.compareValue(driver.findElement(playerFieldPath).getText(),driver.findElement(onlineFieldPath).getText());
    }

    public void clickStoreBtn(){
        driver.findElement(storePath).click();
    }

}
