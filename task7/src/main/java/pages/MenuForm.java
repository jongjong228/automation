package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MenuForm extends Form {
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ILink profileLink = elementFactory.getLink(By.id("l_pr"), "Profile Link ");

    public MenuForm() {
        super(By.id("l_msg"), "Left Menu Form");
    }

    public void clickProfileLInk() {
        profileLink.click();
    }
}
