package pages;

import elements.ElementButton;
import iframe.IFrame;
import org.openqa.selenium.By;


public class NestedFramesForm extends BaseForm{
    private final By textPath = By.xpath("//body");
    private ElementButton framesButton = new ElementButton(By.xpath("//div[@class='element-list collapse show']//child::li[@id='item-2']"),"Frames Button");
    private final By parentFramePath = By.xpath("//iframe[@id='frame1']");
    private final By childFramePath = By.xpath("//iframe[contains(@srcdoc,'Child')]");
    public NestedFramesForm() {
        super(By.xpath("//li[@class = 'btn btn-light active' and @id='item-3']"));
        name = "Nested Frames Form";
    }
    public String getParentFrameText(){
        return IFrame.getFrameTextByID(parentFramePath,"frame1",textPath);
    }
    public String getChildFrameText(){
        return IFrame.getFrameTextByElement(childFramePath,textPath);
    }

    public void clickFramesButton(){
        framesButton.clickElement();
    }

}
