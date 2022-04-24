package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.Randoms;


public class LoginPage extends Form {
    private static final By LoginFormPath = By.xpath("//div[@class='login-form']");
    private static final By listTLDomainsPath = By.xpath("//div[@class='dropdown__list']/child::div");
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ICheckBox checkbox = elementFactory.getCheckBox(By.className("checkbox__box"),"Terms Checkbox");
    private final ITextBox passwordTB = elementFactory.getTextBox(By.xpath("//div[@class='login-form__field-row']//child::input"),"password TextBox");
    private final ITextBox emailTB = elementFactory.getTextBox(By.xpath("//div[@class='align__cell']/child::input[contains(@placeholder,'email')]"),"Login TextBox");
    private final ITextBox domainTB = elementFactory.getTextBox(By.xpath("//div[@class='align__cell']/child::input[contains(@placeholder,'omain')]"),"domain TextBox");
    private final IButton popUp = elementFactory.getButton(By.xpath("//div[@class='dropdown__opener']"),"PopUp menu");
    private final ILink nextLink = elementFactory.getLink(By.xpath("//a[@class='button--secondary']"),"Next Link");
    private final IButton acceptCookiesButton = elementFactory.getButton(By.xpath("//button[contains(@class,'transparent')]"),"Accept cookies Button");
    private final IButton sendHelpToBottomButton = elementFactory.getButton(By.xpath("//button[contains(@class,'send')]"),"Send Help Form to bottom Button");
    private final IButton closeHelpFormButton = elementFactory.getButton(By.xpath("//button[contains(@class,'close')]"),"Close Help Form Button");
    private final ILabel timerCount = elementFactory.getLabel(By.xpath("//div[contains(@class,'timer')]"),"Timer Label");
    public LoginPage() {
        super(LoginFormPath, "Login Page");
    }
    public void clickCheckBox(){
        checkbox.click();
    }
    public void setRandomPassword(){
        passwordTB.clearAndType(Randoms.getRandomPassword());
    }
    public void setRandomDomain(){
        domainTB.clearAndType(Randoms.getRandomDomain());
    }
    public void setRandomEmail(){
        emailTB.clearAndType(Randoms.getRandomEmail());
    }
    public void setTopLevelDomain(){
        clickPopUp();
        elementFactory.findElements(listTLDomainsPath, ElementType.COMBOBOX).get(Randoms.getRandomTLDomainIndex()).click();
    }
    private void clickPopUp(){
        popUp.click();
    }
    public void clickNextLink(){
        nextLink.click();
    }
    public void clickAcceptCookies(){
        acceptCookiesButton.state().waitForClickable();
        acceptCookiesButton.click();
    }
    public void clickSendHelpButton(){
        sendHelpToBottomButton.state().waitForClickable();
        sendHelpToBottomButton.click();
    }
    public boolean isCookieFormClosed(){
        return !acceptCookiesButton.state().isDisplayed();
    }
    public void waitForCookieAccept(){
        acceptCookiesButton.state().waitForNotDisplayed();
    }
    public void waitForClosingHelpForm(){
        closeHelpFormButton.state().waitForNotDisplayed();
    }
    public boolean isHelpFormClosed(){
        return !closeHelpFormButton.state().isDisplayed();
    }
    public String getTimerValue(){
        return timerCount.getText();
    }
}
