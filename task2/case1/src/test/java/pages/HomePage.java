package pages;

import getProperties.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import singletone.Singleton;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private  final By newAndRemarkPath = By.xpath("//div[@id='noteworthy_tab']/child::span");
    private  final By salesLeadsPath = By.xpath("//div[@id='noteworthy_flyout']//child::a[1]");
    private  final By aboutPath = By.xpath("//div[@id='global_header']//div[@class='submenu_community']/following::a[1]");
    private  final By uniquePath = By.xpath("//div[@id='store_nav_area']");
    public HomePage(Singleton singleton) {
        driver = singleton.getDriver();
    }
    public int numOfUniqueElements(){
        return driver.findElements((uniquePath)).size();
    }
    public void clickAboutBtn(){
        driver.findElement(aboutPath).click();
    }
    public void clickSalesLeadsBtn(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(newAndRemarkPath)).build().perform();
        WebDriverWait wait = new WebDriverWait(driver,Integer.parseInt(ConfProperties.getProperty("TimeStart")));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(salesLeadsPath)));
        driver.findElement(salesLeadsPath).click();
    }
}
