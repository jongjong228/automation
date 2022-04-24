package pages;

import functions.Functions;
import getProperties.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import singletone.Singleton;

import java.util.List;

public class LeadersPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String numOfGamesBefore, date, prise, title;
    List<WebElement> games;
    private  final By gamesPath = By.xpath(" //div[@id='search_resultsRows']/child::a");
    private  final By gameTitlePath = By.xpath("//div[@id='search_resultsRows']/child::a[1]//span[@class='title']");
    private  final By gamePrisePath = By.xpath("//div[@id='search_resultsRows']/child::a[1]//div[contains(@class,'search_discount')]/following-sibling::div");
    private  final By gameDatePath = By.xpath("//div[@id='search_resultsRows']/child::a[1]//div[contains(@class,'released')]");
    private  final By linuxCBPath = By.xpath("//div[@data-value='linux']//child::span[contains(@class,'checkbox')]");
    private  final By numOfPlayersCBPath = By.xpath("//div[@data-value='48']//child::span[contains(@class,'checkbox')]");
    private  final By numOfPlayersBtnPath = By.xpath("//div[contains(@class,'collapse_block') and contains(@data-collapse-name,'3')]/child::div[1]");
    private  final By seeMorePath = By.xpath("//div[contains(@class,'collapse_block')]/a[@class='see_all_expander']");
    private  final By actionTextPath = By.xpath("//div[@data-value='19']/child::span[1]//span[contains(@class,'count')]");
    private  final By actionPath = By.xpath("//div[@data-value='19']/child::span[1]//span[contains(@class,'checkbox')]");
    private  final By searchResultsPath = By.xpath("//div[@class='search_results_count']");
    private  final By uniquePath = By.xpath("//div[@id='search_resultsRows']");

    public LeadersPage(Singleton singleton) {
        this.driver = singleton.getDriver();
    }
    public int numOfUniqueElements(){
        return driver.findElements((uniquePath)).size();
    }
    public Boolean isCBClicked(WebElement CB){
        if (CB.getAttribute("checked") != "null")
            return true;
        else return false;
    }
    public Boolean isLinuxCBClicked(){
        return isCBClicked(driver.findElement(linuxCBPath));
    }
    public Boolean isNumOfPlayersCBClicked(){
        return isCBClicked(driver.findElement(numOfPlayersCBPath));
    }
    public Boolean actionCheckBoxClicked(){
        return isCBClicked(driver.findElement(actionPath));
    }
    public String getNumOfGamesBefore(){
        return numOfGamesBefore;
    }
    public String getNumOfGames(){
        games = driver.findElements(gamesPath);
        return Integer.toString(games.size());
    }
    public void gameClick(){
        games.get(0).click();
    }
    public void setGameTitle(){
        title = driver.findElement(gameTitlePath).getText();
    }
    public void setGamePrise(){
        prise = Functions.getPriseFromField(driver.findElement(gamePrisePath).getText());
    }
    public void setGameDate(){
        date = driver.findElement(gameDatePath).getText();
    }
    public String getGameTitle(){
        return title;
    }
    public String getGamePrise(){
        return prise;
    }
    public String getGameDate(){
        return date;
    }
    public void clickSeeMoreBtn(){
        driver.findElement(seeMorePath).click();
    }
    public void clickNumOfPlayerBtn(){
        driver.findElement(numOfPlayersBtnPath).click();
    }
    public void clickNumOfPlayerCB(){
       driver.findElement(numOfPlayersCBPath).click();
    }
    public void clickLinuxCB(){
        driver.findElement(linuxCBPath).click();
    }
    public void clickActionCB(){
        driver.findElement(actionPath).click();
    }
    public void findNumOfGames(){
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.attributeContains(driver.findElement(actionTextPath),"style","display"));
        numOfGamesBefore = driver.findElement(searchResultsPath).getText();
        numOfGamesBefore = Functions.replaceSymbols(numOfGamesBefore);
    }
}

