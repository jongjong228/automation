package pages;

import elements.ElementButton;
import elements.LabelElement;
import org.openqa.selenium.By;


public class AlertsForm extends BaseForm {
    private ElementButton simpleButton = new ElementButton(By.xpath("//button[@id='alertButton']"),"Simple Alert Button");
    private ElementButton promptButton = new ElementButton(By.xpath("//button[@id='promtButton']"),"Prompt Alert Button");
    private ElementButton confirmButton = new ElementButton(By.xpath("//button[@id='confirmButton']"),"Confirm Alert Button");
    private LabelElement confirmText = new LabelElement(By.xpath("//span[@id='confirmResult']"),"Text near Confirm Alert Button");
    private LabelElement promptText = new LabelElement(By.xpath("//span[@id='promptResult']"),"Text near prompt Alert Button");


    public AlertsForm() {
        super(By.xpath("//li[@class = 'btn btn-light active' and @id='item-1']"));
        name = "Alerts Form";
    }
    public void clickSimpleButton(){
        simpleButton.clickElement();
    }
    public void clickPromptButton(){
        promptButton.clickElement();
    }
    public void clickConfirmButton(){
        confirmButton.clickElement();
    }
    public String getConfirmAlertText(){
       return confirmText.getText();
    }
    public String getPromptAlertText(){
        return promptText.getText();
    }

}
