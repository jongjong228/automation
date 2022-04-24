package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final IButton signIn = elementFactory.getButton(By.xpath("//*[contains(@class,'signIn')]//child::span"), "Sign In Button");

    public MainPage() {
        super(By.id("index_login"), "Main Page");
    }

    public void clickSignInButton() {
        signIn.click();
    }
}
