package pages;

import iframe.IFrame;
import org.openqa.selenium.By;

public class FramesForm extends BaseForm{
    private final By textPath = By.xpath("//h1");
    private final By upperFramePath = By.xpath("//iframe[@id='frame1']");
    private final By bottomFramePath = By.xpath("//iframe[@id='frame2']");
    public FramesForm() {
        super(By.xpath("//li[@class = 'btn btn-light active' and @id='item-2']"));
        name = "Frames Form";
    }
    public String getUpperFormText(){
        return IFrame.getFrameTextByElement(upperFramePath,textPath);
    }
    public String getBottomFormText(){
        return IFrame.getFrameTextByElement(bottomFramePath,textPath);
    }

}
