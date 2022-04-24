package pages;

import elements.ElementButton;
import elements.TextField;
import org.openqa.selenium.By;

public class RegistrationForm extends BaseForm{
    private TextField fName = new TextField(By.xpath("//input[@id='firstName']"),"First Name Field");
    private TextField sName = new TextField(By.xpath("//input[@id='lastName']"),"Second Name Field");
    private TextField eMail = new TextField(By.xpath("//input[@id='userEmail']"),"Email Field");
    private TextField age = new TextField(By.xpath("//input[@id='age']"),"Age Field");
    private TextField salary = new TextField(By.xpath("//input[@id='salary']"),"Salary Field");
    private TextField department = new TextField(By.xpath("//input[@id='department']"),"Department Field");
    private ElementButton submButton = new ElementButton(By.xpath("//button[@id='submit']"),"Submit Button");
    public RegistrationForm() {
        super(By.xpath("//div[@id='firstName-wrapper']"));
        name = "Registration Form";
    }
    public void clickSubmButton(){
        submButton.clickElement();
    }
    public void enterInfo(String ftName,String sdName,String email,String age,String salary, String department){
        fName.sendKey(ftName);
        sName.sendKey(sdName);
        eMail.sendKey(email);
        this.age.sendKey(age);
        this.salary.sendKey(salary);
        this.department.sendKey(department);
    }
}
