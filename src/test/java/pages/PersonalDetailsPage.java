package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PersonalDetailsPage extends Form {
    private static final By personalDetailsFormPath = By.xpath("//div[@class='personal-details']");
    public PersonalDetailsPage(){
        super(personalDetailsFormPath,"Personal Details Page");
    }
}
