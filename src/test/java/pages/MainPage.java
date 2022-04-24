package pages;


import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private static final By NoButton = By.xpath("//button[@class='start__button']");
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ILink link = elementFactory.getLink(By.className("start__link"),"Link to the log in Page");
    public MainPage(){
        super(NoButton,"Main Page");
    }
    public void clickLink(){
        link.click();
    }
}
