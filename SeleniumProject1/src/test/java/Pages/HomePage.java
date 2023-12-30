package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage(WebDriver driver) {
        //call the static init method
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a[@href='/login']")
    public WebElement signUp;
    @FindBy(xpath = "//h2[text()='Subscription']")
    public WebElement textSub;
    @FindBy(xpath = "//input[@id='susbscribe_email']")
    public WebElement emailsub;
    @FindBy(xpath = "//button[@id='subscribe']")
    public WebElement btnemailsub;
    //div[text()='You have been successfully subscribed!']
    @FindBy(xpath = " //div[text()='You have been successfully subscribed!']")
    public WebElement message;
    @FindBy(xpath = "//a[@id='scrollUp']")
    public WebElement arrowButton;
    @FindBy(xpath = "(//h2[text()='Full-Fledged practice website for Automation Engineers'])[1]")
    public WebElement text_Fullfledged;
    public String getTextSubscription(){
        return textSub.getText();
    }
    public void setEmailSubcription(String strEmail){
        emailsub.sendKeys(strEmail);
    }
    public void clickSubscription(){
        btnemailsub.click();
    }
    public String getMessage(){
        return message.getText();
    }
    public void clickArrowButton(){
        arrowButton.click();
    }
    public String getText_FullFledge(){
        return text_Fullfledged.getText();
    }

}
