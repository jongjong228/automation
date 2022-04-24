package pages;

import elements.ElementButton;
import myutils.RefactorStrings;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import singleton.Singleton;

import java.util.List;

public class TablesForm extends BaseForm{
    private final int numOfMaxRecords = 10, plusOne = 1;
    private int numOfEmptyRecords;
    private final String locatorSample = "//span[@id='delete-record-";
    private ElementButton addRecordButton = new ElementButton(By.xpath("//button[@id='addNewRecordButton']"),"Add New Record Button");
    private List<WebElement> emptyRecords;
    private List<WebElement> everyRecords;
    public TablesForm() {
        super(By.xpath("//li[@class = 'btn btn-light active' and @id='item-3']"));
        name = "Web Tables Form";
    }
    public void clickRecordButton(){
        addRecordButton.clickElement();
    }
    public void findFirstEmptyRecord(){
        emptyRecords = Singleton.getDriver().findElements(By.xpath("//div[contains(@class,'rt-tr -padRow')]"));
        numOfEmptyRecords = (numOfMaxRecords - emptyRecords.size());
    }
    public String getTextOfNewRecord(){
        everyRecords = Singleton.getDriver().findElements(By.xpath("//div[contains(@class,'rt-tr ')]"));
        return everyRecords.get(numOfEmptyRecords).getText();
    }
    public void deleteNewRecord(){
         createDeleteLocator();
         new ElementButton(createDeleteLocator(),"Delete record Button").clickElement();
    }
    private By createDeleteLocator(){
        return By.xpath(RefactorStrings.createXpath(locatorSample,Integer.toString(numOfEmptyRecords + 1)));
    }
    public boolean isNewRecordDeleted(String Email){
        try{
            Singleton.getDriver().findElement(By.xpath("//div[@class='rt-td' and contains(text()," + Email + ")]"));
            return false;
        }catch(NoSuchElementException e){
            return true;
        }

    }
}
