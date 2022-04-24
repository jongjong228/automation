package pages;

import elements.ElementButton;
import org.openqa.selenium.By;


public class BrowserWindowsForm extends BaseForm{
    private ElementButton tabButton = new ElementButton(By.xpath("//button[@id='tabButton']"),"Tab Button");
    private ElementButton elementButton = new ElementButton(By.xpath("//div[contains(text(),'Elements')]"),"Element Button");
    private ElementButton hiddenButton = new ElementButton(By.xpath("//div[@class='element-list collapse show']"),"Hidden Button");
    private ElementButton linksButton = new ElementButton(By.xpath("//div[@class='element-list collapse show']//child::li[@id='item-5']"),"Links Button");
    public BrowserWindowsForm() {
        super(By.xpath("//li[@class = 'btn btn-light active' and @id='item-0']"));
        name = "Browser Window Form";
    }
    public void clickTabButton(){
        tabButton.clickElement();
    }
    public void clickElementButton(){
        elementButton.clickElement();
    }
    public void clickLinksButton(){
        linksButton.clickElement();
    }
    public void waitHiddenElement(){
        hiddenButton.waitElementIsDisplayed();
    }
}
