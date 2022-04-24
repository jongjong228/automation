package pages;


import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthPage extends Form {
    private static final By CreateButton = By.className("FlatButton__in");
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ITextBox email = elementFactory.getTextBox(By.xpath("//form/input[@name='email']"), "login TextBox");
    private final ITextBox password = elementFactory.getTextBox(By.xpath("//form/input[@name='pass']"), "password TextBox");
    private final IButton signIn = elementFactory.getButton(By.xpath("//button[@id='index_login_button']"), "Sign In Button");

    public AuthPage() {
        super(CreateButton, "Authorization Page");
    }

    public void inputPassword(String pass) {
        password.sendKeys(pass);
    }

    public void clickSignInButton() {
        signIn.click();
    }

    public void inputEmail(String pass) {
        email.sendKeys(pass);
    }
}
