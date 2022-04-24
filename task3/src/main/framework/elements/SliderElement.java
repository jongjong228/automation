package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utils.ConfigUtils;
import utils.MyLogger;

public class SliderElement extends BaseElement{
    public SliderElement(By locator, String name) {
        super(locator, name);
    }
    public void moveSlider(int sliderValue) {
        MyLogger.info("Move" + name);
        int startSliderValue = Integer.parseInt(ConfigUtils.getTestProperty("StandartSliderValue"));
        while (Integer.parseInt(getAttribute("value")) != sliderValue) {
            if (sliderValue > startSliderValue) {
                findElement().sendKeys(Keys.ARROW_RIGHT);
            } else if (sliderValue < startSliderValue) {
                findElement().sendKeys(Keys.ARROW_LEFT);
            }
        }
    }

}
