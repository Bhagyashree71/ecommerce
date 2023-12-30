package tests;

import Pages.ContactusPage;
import Pages.LoginPage;
import Pages.RegisterPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ExcelReader1;

import java.util.HashMap;

public class ContactusTest extends BaseTest {
    LoginPage lpage;
    RegisterPage rpage;
    ContactusPage cpage;

    @Test
    public void TC6_VerifyContactUsForm(){
        lpage=new LoginPage(driver);
        rpage=new RegisterPage(driver);
        cpage=new ContactusPage(driver);
        HashMap<String,String> data= ExcelReader1.getTestData("tc4");
        SoftAssert sa=new SoftAssert();
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        lunchUrl();
        //3. Verify that home page is visible successfully
        String title= driver.getTitle();
        Assert.assertEquals(title,"Automation Exercise","home page is visible successfully");
        //4. Click on 'Contact Us' button
        cpage.clickContactus();
        //5. Verify 'GET IN TOUCH' is visible
        String text=cpage.getTextgetintouch();
        sa.assertTrue(text.toLowerCase().contains("get in touch"),"'GET IN TOUCH' is visible");
        //6. Enter name, email, subject and message
        cpage.fillContactDetail(data.get("name"),data.get("Email"),data.get("Subject"),data.get("Message"));
        //7. Upload file
        WebElement upload= cpage.choosefile;
        upload.sendKeys("C:\\Users\\smruti\\IdeaProjects\\SeleniumProjec1\\src\\test\\resources\\istockphoto-1409783718-1024x1024.jpg");
        //8. Click 'Submit' button
        cpage.clickSubmit();
        //9. Click OK button
        driver.switchTo().alert().accept();
        //10. Verify success message 'Success! Your details have been submitted successfully.' is visible
        String textmsg=cpage.getSuccessMsg();
        sa.assertEquals(textmsg,"Success! Your details have been submitted successfully.");
        //11. Click 'Home' button and verify that landed to home page successfully
        cpage.clickHomeButton();
        String homepage=driver.getCurrentUrl();
        sa.assertTrue(homepage.contains("https://automationexercise.com"));


    }
}
