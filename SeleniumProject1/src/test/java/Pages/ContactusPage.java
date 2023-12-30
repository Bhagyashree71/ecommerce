package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.Message;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactusPage {
    public ContactusPage(WebDriver driver) {
        //call the static init method
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a[@href='/contact_us']")
    public WebElement contactus;
    @FindBy(xpath = "//div[@class='contact-form']//h2")
    public WebElement textgetintouch;
    @FindBy(xpath = "//input[@data-qa='name']")
    public WebElement name;
    @FindBy(xpath = "//input[@data-qa='email']")
    public WebElement email;
    @FindBy(xpath = "//input[@data-qa='subject']")
    public WebElement subject;
    @FindBy(xpath = "//textarea[@data-qa='message']")
    public WebElement message;
    @FindBy(xpath = "//input[@name='upload_file']")
    public WebElement choosefile;
    @FindBy(xpath = "//input[@name='submit']")
    public WebElement submit;
    @FindBy(xpath = "//div[@class='status alert alert-success']")
    public WebElement successmsg;

    @FindBy(xpath = "//a[@class='btn btn-success']")
    public WebElement homebtn;

    public void clickContactus(){
        contactus.click();
    }
    public String getTextgetintouch(){
        return    textgetintouch.getText();
    }
    public void setName(String strName){
        name.sendKeys(strName);
    }
    public void setEmail(String strEmail){
        email.sendKeys(strEmail);
    }
    public void setSubject(String strSubject){
        subject.sendKeys(strSubject);
    }
    public void setMessage(String strMessage){
        message.sendKeys(strMessage);
    }
    public void clickSubmit(){
        submit.click();
    }
    public void clickHomeButton(){
        homebtn.click();
    }
    public String getSuccessMsg(){
        return    successmsg.getText();
    }

    public void fillContactDetail(String strName,String strEmail,String strSubject,String strMessage){
        this.setName(strName);
        this.setEmail(strEmail);
        this.setSubject(strSubject);
        this.setMessage(strMessage);
    }

}
