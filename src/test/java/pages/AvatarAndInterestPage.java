package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.Randoms;
import utils.RobotActions;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class AvatarAndInterestPage extends Form {
    private final ISettingsFile  properties = new JsonSettingsFile("properties.json");
    private final ISettingsFile testDate = new JsonSettingsFile("test_date.json");
    private final String pathToFile = testDate.getValue("/pathToFile").toString();
    private final By pathToInterestsCB = By.xpath("//child::span[@class='checkbox__box']");
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private static final By interestsForm = By.xpath("//div[@class='avatar-and-interests']");
    private final ICheckBox unselectAll = elementFactory.getCheckBox(By.xpath("//label[@for='interest_unselectall']/child::span"),"Unselect all");
    private final List<ICheckBox> interests = elementFactory.findElements(By.xpath("//div[@class='avatar-and-interests__interests-list']/div"), ElementType.CHECKBOX);
    private final ILink uploadLink = elementFactory.getLink(By.xpath("//a[contains(@class,'upload')]"),"Upload Link");
    private final IButton nextButton = elementFactory.getButton(By.xpath("//button[contains(@class,'button--stroked')]"),"Next Button");
    private final IButton downloadButton = elementFactory.getButton(By.xpath("//button[contains(@class,'upload')]"),"Download Button");
    public AvatarAndInterestPage(){
        super(interestsForm,"Avatar and Interests Page");
    }
    public void clickNextButton(){
        nextButton.click();
    }
    public void clickAndWaitDownloadButton(){
        downloadButton.click();
        File fileDownloaded = new File(pathToFile);
        try {
            AqualityServices.getConditionalWait().waitForTrue(() -> fileDownloaded.exists(),
                    Duration.ofSeconds(Integer.parseInt(properties.getValue("/WaitDurationSeconds").toString())), Duration.ofMillis(Integer.parseInt(properties.getValue("/WaitDurationMillis").toString())), "File should be downloaded");
        } catch(java.util.concurrent.TimeoutException e){
            AqualityServices.getLogger().warn("Timeout Exception");
        }
    }
    public void waitForImageError(){
        elementFactory.getLabel(By.xpath("//li[contains(text(),'upload')]"),"Upload error Lable").state().waitForDisplayed();
    }
    public void uploadFileAndWait(){
        clickUploadLink();
        RobotActions.uploadFile(pathToFile);
        elementFactory.getLabel(By.xpath("//li[contains(text(),'upload')]"),"Upload error Lable").state().waitForNotDisplayed();
    }
    private void clickUploadLink(){
        uploadLink.click();
    }
    public void clickUnselectCB(){
        unselectAll.click();
    }
    public void clickInterest(){
        elementFactory.findChildElement(interests.get(Randoms.getRandomInterest()),pathToInterestsCB,ElementType.CHECKBOX).click();
    }
}
