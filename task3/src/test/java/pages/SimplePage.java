package pages;

import org.openqa.selenium.By;
import singleton.Singleton;

public class SimplePage extends BaseForm{
    private String pageURl = Singleton.getDriver().getCurrentUrl();
    public SimplePage() {
        super(By.xpath("//h1"));
        name = "Simple Page";
    }
    public String getURL(){
        return pageURl;
    }
}
