package pages;

import elements.ElementButton;
import elements.SliderElement;
import elements.TextField;
import org.openqa.selenium.By;

public class SlidersForm extends BaseForm{
    private SliderElement slider = new SliderElement(By.xpath("//span[@class='range-slider__wrap']/input"),"Slider Element");
    private TextField sliderValueField = new TextField(By.xpath("//input[@id='sliderValue']"),"Slider Value Field");
    private ElementButton progressBarButton = new ElementButton(By.xpath("//div[@class='element-list collapse show']//child::li[@id='item-4']"),"Progress bar Button");
    public SlidersForm() {
        super(By.xpath("//li[@class = 'btn btn-light active' and @id='item-3']"));
        name = "Sliders Form";
    }
    public int getSliderValue(){
        return Integer.parseInt(sliderValueField.getAttribute("value"));
    }
    public void moveSlider(int sliderValue){
            slider.moveSlider(sliderValue);
    }
    public void clickProgressBarButton(){
        progressBarButton.clickElement();
    }
}
