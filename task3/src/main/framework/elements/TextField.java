package elements;

import org.openqa.selenium.By;
import utils.MyLogger;

public class TextField extends BaseElement{
    public TextField(By locator, String name) {
        super(locator, name);
    }
    public void sendKey(String str){
        MyLogger.info("Send Key" + name);
        findElement().sendKeys(str);
    }
}
