package pages;


import org.openqa.selenium.By;
import singleton.Singleton;
import utils.MyLogger;
import utils.WaitUtils;

public abstract class BaseForm {
    protected String name;
    protected By uniqueElementPath;
    public BaseForm(By locator){
        this.uniqueElementPath = locator;
    }
    public boolean isPageOpened(){
        MyLogger.info("Is "+ name + " opened");
        waitForPageIsOpened();
        return (Singleton.getDriver().findElements(uniqueElementPath).size() != 0);
    }
    protected void waitForPageIsOpened(){
        WaitUtils.setWaitUntilVisibility(uniqueElementPath);
    }


}
