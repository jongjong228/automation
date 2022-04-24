package pages;

import elements.ElementButton;
import org.openqa.selenium.By;

public class AlertsPageHome extends BaseForm{
    private ElementButton alertButton = new ElementButton(By.xpath("//div[@class='element-list collapse show']//child::li[@id='item-1']"),"Alerts Button");
    private ElementButton nestedFramesButton = new ElementButton(By.xpath("//div[@class='element-list collapse show']//child::li[@id='item-3']"),"Nested Frames Button");
    private ElementButton browserWindowButton = new ElementButton(By.xpath("//div[@class='element-list collapse show']//child::li[@id='item-0']"),"Browser Window Button");
    public AlertsPageHome() {
        super(By.xpath("//div[@class = 'main-header' and contains(text(),'&')]"));
        name = "Alerts Page";
    }
    public void clickAlertButton(){
        alertButton.clickElement();
    }
    public void clickNestedFramesButton(){
        nestedFramesButton.clickElement();
    }
    public void clickBrowserWindowButton(){
        browserWindowButton.clickElement();
    }
}
