package pages;

import elements.ElementButton;
import org.openqa.selenium.By;

public class ElementsPage extends BaseForm{
    private ElementButton tablesButton = new ElementButton(By.xpath("//div[@class='element-list collapse show']//child::li[@id='item-3']"),"Web Tables Button");
    public ElementsPage() {
        super(By.xpath("//div[@class = 'main-header' and contains(text(),'Elements')]"));
        name = "Elements Page";
    }
    public void clickElementButton(){
        tablesButton.clickElement();
    }
}
