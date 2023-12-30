package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ExcelReader1;

import java.util.HashMap;
import java.util.List;

public class RegisterPage {

    public RegisterPage(WebDriver driver) {
        //call the static init method
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@href='/login']")
    public WebElement signUp;
    @FindBy(xpath = "//div[@class='signup-form']")
    public WebElement newUserSignUp_text;
    @FindBy(xpath = "//input[@data-qa='signup-name']")
    public WebElement username;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    public WebElement email;

    @FindBy(xpath="//button[@data-qa='signup-button']")
    public WebElement button;

    @FindBy(xpath="//input[@id='id_gender2']")
    public WebElement title;
    @FindBy(xpath="//input[@id='name']")
    public WebElement name;
    @FindBy(xpath="//input[@id='password']")
    public WebElement password;
    @FindBy(xpath="//select[@id='days']")
    public WebElement select;
    @FindBy(xpath="//select[@id='months']")
    public WebElement select1;
    @FindBy(xpath="//select[@id='years']")
    public WebElement select2;
    @FindBy(xpath="//input[@id='newsletter']")
    public WebElement checkbox1;
    @FindBy(xpath="//input[@id='optin']")
    public WebElement checkbox2;
    @FindBy(xpath="//input[@id='first_name']")
    public WebElement fname;
    @FindBy(xpath="//input[@id='last_name']")
    public WebElement lname;
    @FindBy(xpath="//input[@id='company']")
    public WebElement company;
    @FindBy(xpath="//input[@id='address1']")
    public WebElement address;
    @FindBy(xpath="//input[@id='address2']")
    public WebElement address2;
    @FindBy(xpath="//select[@id='country']")
    public WebElement country;
    @FindBy(xpath="//input[@id='state']")
    public WebElement state;
    @FindBy(xpath="//input[@id='city']")
    public WebElement city;
    @FindBy(xpath="//input[@id='zipcode']")
    public WebElement zipcode;
    @FindBy(xpath="//input[@id='mobile_number']")
    public WebElement mobileno;
    @FindBy(xpath="//button[@data-qa='create-account']")
    public WebElement createbutton;

    @FindBy(xpath="//h2[@class='title text-center']")
    public WebElement titleaccount;

    @FindBy(xpath="//a[@data-qa='continue-button']")
    public WebElement continue1;
    //i[@class='fa fa-home']
    @FindBy(xpath="//i[@class='fa fa-user']//parent::a")
    public WebElement textlogged;
    @FindBy(xpath="//i[@class='fa fa-trash-o']//parent::a")
     public WebElement delete;
    @FindBy(xpath="//h2[@data-qa='account-deleted']")
    public WebElement deletetext;
    @FindBy(xpath="//a[@data-qa='continue-button']")
    public WebElement continuebtn;

    @FindBy(xpath="//div[@class='signup-form']")
    public WebElement textNewuser;
    //form[@action='/signup']//p
    @FindBy(xpath="//form[@action='/signup']//p")
    public WebElement textEmailexist;

    public void clickSignUp(){
        signUp.click();
    }
    public String getNewUserSignUp_text(){
        return    newUserSignUp_text.getText();
    }
    public String getaccount(){
        return    titleaccount.getText();
    }
    public void clickcontinue(){
         continue1.click();
    }
    public String getTextLogged(){
        return textlogged.getText();
    }
    public void clickDelete(){
        delete.click();
    }
    public String getDeleteText(){
        return deletetext.getText();
    }
    public String getNewUserText(){
        return textNewuser.getText();
    }
    public String gettextExistEmail(){
        return textEmailexist.getText();
    }

    public void clickContinue(){
        continuebtn.click();
    }
    //HashMap<String,String> data= ExcelReader1.getTestData("tc1");
    public void setUserName(String strUserName){
        username .sendKeys(strUserName);
    }
    public void setEmail(String strEmail){
        email.sendKeys(strEmail);
    }
    public void signup(String strUserName,String strEmail){
        this.setUserName(strUserName);
        this.setEmail(strEmail);
        this.button.click();
    }
    public void setName(String strName){
        name .sendKeys(strName);
    }
    public void setPassword(String strPassword){
       password .sendKeys(strPassword);
    }
    public void clickCheckbox(){
        checkbox1.click();
        checkbox2.click();
    }
    public void setFirstname(String strfname){
        fname.sendKeys(strfname);
    }

    public void setLasttname(String strlname){
        lname.sendKeys(strlname);
    }
    public void setCompany(String strcompany){
        company.sendKeys(strcompany);
    }
    public void setAddress(String straddress){
        address.sendKeys(straddress);
    }
    public void setAddress2(String straddress2){
        address2.sendKeys(straddress2);
    }














    // public void setName(String firstname,String lastname){
      //  fname.sendKeys("bhagya");
       // lname.sendKeys("naik");
    //}

}
