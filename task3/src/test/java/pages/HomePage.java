package pages;

import elements.ElementButton;
import org.openqa.selenium.By;


public class HomePage extends BaseForm{
    private ElementButton alertButton = new ElementButton(By.xpath("//h5[contains(text(),'Alerts')]"),"Alerts & Windows Button");
    private ElementButton elementsButton = new ElementButton(By.xpath("//h5[contains(text(),'Elements')]"),"Element Button");
    private ElementButton widgetsButton = new ElementButton(By.xpath("//h5[contains(text(),'Widgets')]"),"Widgets Button");
    public HomePage() {
        super(By.xpath("//div[@class='category-cards']"));
        name = "Home Page";
    }
    public void clickAlertButton(){
        alertButton.clickElement();
    }
    public void clickElementsButton(){
        elementsButton.clickElement();
    }
    public void clickWidgetsButton(){
        widgetsButton.clickElement();
    }
}
