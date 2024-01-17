package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        //call the static init method
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[text()='Swag Labs']")
    public WebElement textSwagLab;
    @FindBy(xpath = "//input[@name='user-name']")
    public WebElement userName;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement password;
    @FindBy(xpath = "//input[@name='login-button']")
    public WebElement buttonLogin;
    @FindBy(xpath = "//div[@class='error-message-container error']//h3")
    public WebElement login_ErrorMessage;
    @FindBy(xpath = "//button[@class='error-button']")
    public WebElement crossButton_ErrorMessage;
    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    public WebElement menuButton;
    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    public WebElement logoutButton;

    @FindBy(xpath = "//div[@class='footer_copy']")
    public WebElement footer_text;
    @FindBy(xpath = "//a[text()='Twitter']")
    public WebElement twitterSign;
    @FindBy(xpath = "//a[text()='Facebook']")
    public WebElement facebookSign;

    public String getTextSwagLab(){
        return textSwagLab.getText();
    }
    public void setUsername(String strUsername){
        userName.sendKeys(strUsername);
    }
    public void setPassword(String strPassword){
        password .sendKeys(strPassword);
    }
    public void login(String strUsername,String strPassword){
        this.setUsername(strUsername);
        this.setPassword(strPassword);
        this.buttonLogin.click();
    }
    public String getText_loginErrorMessage(){
        return login_ErrorMessage.getText();
    }
    public void clickMenuButton(){
        menuButton.click();
    }
    public void clickLogoutButton(){
        logoutButton.click();
    }

    public void verifyPage(String expectedUrl) {

        String urlCurrent = driver.getCurrentUrl();
        assertEquals(urlCurrent,expectedUrl);
    }
    public String getText_Footer(){
        return footer_text.getText();
    }
    public void clickTwitterSign(){
        twitterSign.click();
    }
    public void clickFacebookSign(){
        facebookSign.click();
    }
}
