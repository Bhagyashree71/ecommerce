package tests;

import Pages.LoginPage;
import Pages.RegisterPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.ExcelReader1;
import utilities.QaEnvPropReader;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class RegisterTest extends BaseTest{
    LoginPage lpage;
    RegisterPage rPage;



    @Test(priority = 0)
    public void TC1_RegisterUser()  {

        rPage=new RegisterPage(driver);
        HashMap<String,String> data= ExcelReader1.getTestData("tc4");
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        lunchUrl();
        //3. Verify that home page is visible successfully
        String title= driver.getTitle();
        Assert.assertEquals(title,"Automation Exercise","home page is visible successfully");
        //4.Click on 'Signup / Login' button
        rPage.clickSignUp();
        //5. Verify 'New User Signup!' is visible
        String textNewUserSignUp = rPage.getNewUserSignUp_text();
        Assert.assertTrue(textNewUserSignUp.contains("New User Signup!"),"New User Signup! is visible");
        //6. Enter name and email address
        //7. Click 'Signup' button
        rPage.signup(data.get("Name"),data.get("Email"));
        rPage.title.click();
        rPage.name.clear();

        //9. Fill details: Title, Name, Email, Password, Date of birth
        rPage.setName(data.get("name"));
        rPage.setPassword(data.get("password"));

        //for scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        //for selection of date of birth from dropdown
        Select dropdown=new Select(rPage.select);
        dropdown.selectByValue("2");
        Select dropdown1=new Select(rPage.select1);
        dropdown1.selectByVisibleText("February");
        Select dropdown2=new Select(rPage.select2);
        dropdown2.selectByValue("1990");
       // Thread.sleep(5000);
        //10. Select checkbox 'Sign up for our newsletter!'
        //11. Select checkbox 'Receive special offers from our partners!'
        rPage.clickCheckbox();
        //12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        rPage.setFirstname(data.get("Fname"));
        rPage.setLasttname(data.get("Lname"));
        rPage.setCompany(data.get("Company"));
        rPage.setAddress(data.get("Address"));
        rPage.setAddress2(data.get("Address2"));
        Select dropdown3=new Select(rPage.country);
        dropdown3.selectByValue("India");
        rPage.state.sendKeys("Karnataka");
        rPage.city.sendKeys("Bangalore");
        rPage.zipcode.sendKeys("560047");
        rPage.mobileno.sendKeys("8763088634");
        //13. Click 'Create Account button'
        rPage.createbutton.click();
        //driver.switchTo().alert().dismiss();
        //14. Verify that 'ACCOUNT CREATED!' is visible
        String textAccount = rPage.getaccount();
        Assert.assertTrue(textAccount.toLowerCase().contains("account created!"));
        //15. Click 'Continue' button
        rPage.clickcontinue();
        //16. Verify that 'Logged in as username' is visible
        String textLog=rPage.textlogged.getText();
        Assert.assertTrue(textLog.toLowerCase().contains("logged in as"));
        //17. Click 'Delete Account' button
        rPage.clickDelete();
        //18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        String account_text=rPage.getDeleteText();
        Assert.assertTrue(account_text.toLowerCase().contains("account deleted!"),"'ACCOUNT DELETED' is visible");
        rPage.clickcontinue();
    }
    @Test(priority=1)
    public void TC5_RegisterUserWithExistingEmail(){
        lpage=new LoginPage(driver);
        rPage=new RegisterPage(driver);
        HashMap<String,String> data= ExcelReader1.getTestData("tc1");
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        lunchUrl();
        //3. Verify that home page is visible successfully
        String title= driver.getTitle();
        Assert.assertEquals(title,"Automation Exercise","home page is visible successfully");
        //4. Click on 'Signup / Login' button
        rPage.clickSignUp();
        //5. Verify 'New User Signup!' is visible
        String value = rPage.getNewUserText();
        Assert.assertTrue(value.contains("New User Signup!"));
        //6. Enter name and already registered email address
        //7. Click 'Signup' button
        rPage.signup(data.get("Name"),data.get("Email"));
        //8. Verify error 'Email Address already exist!' is visible
        String value1 = rPage.gettextExistEmail();
        Assert.assertTrue(value1.toLowerCase().contains("email address already exist!"));


    }


}
