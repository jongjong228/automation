package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthPage extends Form {
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final IComboBox loginCB = elementFactory.getComboBox(By.name("login"), "login text Box");
    private final IComboBox passwordCB = elementFactory.getComboBox(By.name("password"), "password text Box");
    private final IButton submit = elementFactory.getButton(By.xpath("//*[@type='submit']"), "submit Button");

    public AuthPage() {
        super(By.xpath("//*[contains(@class,'subCaption')]"), "Authorization Page");
    }

    public void clickSubmitButton() {
        submit.click();
    }

    public void inputEmail(String email) {
        loginCB.sendKeys(email);
    }

    public void inputPassword(String password) {
        passwordCB.sendKeys(password);
    }
}
