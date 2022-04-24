package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import singletone.Singleton;

import java.util.List;

public class GamePage {
    private WebDriver driver;
    private  final By gameTitlePath = By.xpath("//div[@id='appHubAppName']");
    private  final By gamePrisePath = By.xpath("//div[@class='game_area_purchase_game_wrapper']//child::div[@class='discount_final_price']");
    private  final By gameDatePath = By.xpath("//div[@class='date']");
    private  final By uniquePath = By.xpath("//div[@class='game_area_purchase_game']");
    public GamePage(Singleton singleton) {
        this.driver = singleton.getDriver();
    }
    public int numOfUniqueElements(){
        return driver.findElements((uniquePath)).size();
    }
    public String getGameTitle(){
        return driver.findElement(gameTitlePath).getText();
    }
    public String getGamePrise(){
        return driver.findElement(gamePrisePath).getText();
    }
    public String getGameDate(){
        return driver.findElement(gameDatePath).getText();
    }


}
