package pages;

import elements.ElementButton;
import elements.ProgressBar;
import org.openqa.selenium.By;
import utils.ConfigUtils;
import utils.WaitUtils;

public class ProgressBarForm extends BaseForm{
    private ElementButton startStopButton = new ElementButton(By.xpath("//button[@id='startStopButton']"),"Start-stop Button");
    private ProgressBar progressBar = new ProgressBar(By.xpath("//div[@role='progressbar']"),"Progress Bar");
    public ProgressBarForm() {
        super(By.xpath("//li[@class = 'btn btn-light active' and @id='item-4']"));
        name = "Progress Bar Form";
    }
    public void clickStartButton(){
        startStopButton.clickElement();
    }
    public void clickStopButton(){
        startStopButton.clickElement();
    }
    public void waitProgress(){
        WaitUtils.setWaitUntilAttributeToBe(progressBar.getLocator(),ConfigUtils.getConfProperty("ProgressBarValueAttribute"),ConfigUtils.getTestProperty("EngineerAge"));
    }
    public int getCurrentProgress(){
        return Integer.parseInt(progressBar.getAttribute(ConfigUtils.getConfProperty("ProgressBarValueAttribute")));
    }
}
