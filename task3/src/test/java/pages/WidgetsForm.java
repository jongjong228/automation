package pages;

import elements.ElementButton;
import org.openqa.selenium.By;

public class WidgetsForm extends BaseForm{
    private ElementButton sliderButton = new ElementButton(By.xpath("//div[@class='element-list collapse show']//child::li[@id='item-3']"),"Slider Button");
    public WidgetsForm() {
        super(By.xpath("//div[@class = 'main-header' and contains(text(),'Widgets')]"));
        name = "Widgets Form";
    }
    public void clickSliderButton(){
        sliderButton.clickElement();
    }

}
