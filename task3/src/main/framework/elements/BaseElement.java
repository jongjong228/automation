package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import utils.DriverUtils;
import utils.MyLogger;
import utils.WaitUtils;


public abstract class BaseElement {
    protected String name;
    protected By locator;
    protected BaseElement(By locator,String name){
        this.name = name;
        this.locator = locator;
    }
    public void clickElement(){
        try {
            MyLogger.info("Click " + this.name);
            findElement().click();
        }catch(ElementClickInterceptedException e){
            MyLogger.info("Scrolling Browser To click " + name);
            DriverUtils.scrollToElement(findElement());
            findElement().click();
        }
    }
    public String getAttribute(String attribute){
        MyLogger.info("Get " + name+ " Attribute");
        return findElement().getAttribute(attribute);
    }
    public String getText(){
        MyLogger.info("Try to find text of " + name);
        return findElement().getText();
    }
    protected WebElement findElement(){
        return waitElementIsDisplayed();
    }
    public WebElement waitElementIsDisplayed(){
        return WaitUtils.setWaitUntilVisibility(locator);
    }
    public By getLocator(){
        MyLogger.info("Get " + name + "Locator");
        return this.locator;
    }

}
