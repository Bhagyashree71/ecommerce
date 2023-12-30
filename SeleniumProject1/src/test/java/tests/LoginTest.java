package tests;

import Pages.LoginPage;
import Pages.RegisterPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ExcelReader1;
import utilities.QaEnvPropReader;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LoginTest extends BaseTest{
    LoginPage loginPage;
    RegisterPage rPage;


    @Test
    public void TC2_LoginWithCorrectEmailPassword(){
        HashMap<String,String> data= ExcelReader1.getTestData("tc2");
        loginPage=new LoginPage(driver);
        rPage=new RegisterPage(driver);

        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        //3. Verify that home page is visible successfully
        lunchUrl();
        String title= driver.getTitle();
        Assert.assertEquals(title,"Automation Exercise","home page is visible successfully");
        //4. Click on 'Signup / Login' button
        rPage.clickSignUp();
        //5. Verify 'Login to your account' is visible
        String text=loginPage.verifyTextLogin();
        Assert.assertTrue(text.toLowerCase().contains("login to your account"),"'Login to your account' is visible");
        //6. Enter correct email address and password
        //7. Click 'login' button
        loginPage.login(data.get("Email"),data.get("password"));
        //8. Verify that 'Logged in as username' is visible
        String textLog=rPage.getTextLogged();
        Assert.assertTrue(textLog.toLowerCase().contains("logged in as"),"Logged in as username is visible");
        //9. Click 'Delete Account' button
        rPage.clickDelete();
        //10. Verify that 'ACCOUNT DELETED!' is visible
        String a_text=rPage.getDeleteText();
        Assert.assertTrue(a_text.toLowerCase().contains("account deleted!"));
    }
    @Test
    public void TC3_LoginWithIncorrectEmailPassword(){
        HashMap<String,String> data= ExcelReader1.getTestData("tc3");
        loginPage=new LoginPage(driver);
        rPage=new RegisterPage(driver);

        //3. Verify that home page is visible successfully
        lunchUrl();
        String title= driver.getTitle();
        Assert.assertEquals(title,"Automation Exercise","home page is visible successfully");
        // 4. Click on 'Signup / Login' button
        rPage.clickSignUp();
        // 5. Verify 'Login to your account' is visible
        String text=loginPage.verifyTextLogin();
        Assert.assertTrue(text.toLowerCase().contains("login to your account"),"'Login to your account' is visible");
        // 6. Enter incorrect email address and password
        // 7. Click 'login' button
        loginPage.login(data.get("Email"),data.get("password"));
        // 8. Verify error 'Your email or password is incorrect!' is visible
        String text_IncorrectEmailPassword=loginPage.getText_IncorrectEmailPassword();
        Assert.assertTrue(text_IncorrectEmailPassword.toLowerCase().contains("your email or password is incorrect!"));

    }
    @Test
    public void TC4_LogoutUser(){
        HashMap<String,String> data= ExcelReader1.getTestData("tc1");
        loginPage=new LoginPage(driver);
        rPage=new RegisterPage(driver);
        SoftAssert sa=new SoftAssert();
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        //3. Verify that home page is visible successfully
        //4. Click on 'Signup / Login' button
        //5. Verify 'Login to your account' is visible
        lunchUrl();
        String title= driver.getTitle();
        Assert.assertEquals(title,"Automation Exercise","home page is visible successfully");
         rPage.clickSignUp();
        String text_Login=loginPage.verifyTextLogin();
        sa.assertTrue(text_Login.toLowerCase().contains("login to your account"));
        //6. Enter correct email address and password// 7. Click 'login' button
       loginPage.login(data.get("Email"),data.get("password"));
        //8. Verify that 'Logged in as username' is visible
        String text_LoggedIn=rPage.textlogged.getText();
        Assert.assertTrue(text_LoggedIn.toLowerCase().contains("logged in as"));
        //9. Click 'Logout' button
        loginPage.clickLogout();
        //10. Verify that user is navigated to login page
        String urllogin=driver.getCurrentUrl();
        Assert.assertEquals(urllogin,"https://automationexercise.com/login","user is navigated to login page");

    }


}
