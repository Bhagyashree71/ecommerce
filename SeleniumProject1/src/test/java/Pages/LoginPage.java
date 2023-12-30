package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        //call the static init method
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a[@href='/login']")
    public WebElement signUp;
    @FindBy(xpath = "//input[@data-qa='login-email']")
    public WebElement emailLogin;
    @FindBy(xpath = "//input[@data-qa='login-password']")
    public WebElement passwordLogin;
    @FindBy(xpath = "//button[@data-qa='login-button']")
    public WebElement buttonLogin;
    @FindBy(xpath = "//div[@class='login-form']//h2")
    public WebElement textLogin;
    @FindBy(xpath = "//form[@action='/login']")
    public WebElement textIncorrectEmailPassword;
    @FindBy(xpath = "//i[@class='fa fa-lock']//parent::a")
    public WebElement logout;



    public String verifyTextLogin(){
     return textLogin.getText();
    }
    public void setEmailLogin(String strEmail){
        emailLogin.sendKeys(strEmail);
    }
    public void setPasswordLogin(String strPassword){
       passwordLogin .sendKeys(strPassword);
    }
    public void login(String strEmail,String strPassword){
        this.setEmailLogin(strEmail);
        this.setPasswordLogin(strPassword);
        this.buttonLogin.click();
    }
    public String getText_IncorrectEmailPassword(){
        return textIncorrectEmailPassword.getText();
    }
    public void clickLogout(){
        logout.click();
    }



}
