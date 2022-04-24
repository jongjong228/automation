package pages;

import elements.LinkElement;
import org.openqa.selenium.By;


public class LinksForm extends BaseForm{
    private LinkElement simpleLink = new LinkElement(By.xpath("//a[@id='simpleLink']"),"Simple Link");
    public LinksForm() {
        super(By.xpath("//li[@class = 'btn btn-light active' and @id='item-5']"));
        name = "Links Form";
    }
    public void clickSimpleLink(){
        simpleLink.clickElement();
    }
}
