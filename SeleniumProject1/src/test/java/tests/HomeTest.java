package tests;

import Pages.HomePage;
import Pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ExcelReader1;

import java.time.Duration;
import java.util.HashMap;

public class HomeTest extends BaseTest{

    @Test
    public void TC10_VerifySubscriptionInHomePage() {

        HashMap<String,String> data= ExcelReader1.getTestData("tc4");
        HomePage hpage = new HomePage(driver);
        SoftAssert sa = new SoftAssert();
        // 1. Launch browser
        // 2. Navigate to url 'http://automationexercise.com'
        // 3. Verify that home page is visible successfully
        lunchUrl();
        String hometitle = driver.getTitle();
        sa.assertEquals(hometitle, "Automation Exercise");
        // 4. Scroll down to footer
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        // 5. Verify text 'SUBSCRIPTION'
        String tx = hpage.getTextSubscription();
        sa.assertEquals(tx,"SUBSCRIPTION");
        // 6. Enter email address in input and click arrow button

        hpage.setEmailSubcription(data.get("Email"));
        //WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                //.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='subscribe']")));
        hpage.clickSubscription();
        //7. Verify success message 'You have been successfully subscribed!' is visible
        String msg=hpage.getMessage();
        Assert.assertEquals(msg,"You have been successfully subscribed!");
    }
    @Test
    public void TC25_VerifyScrollUpAndScrollDown() {
        HomePage hpage = new HomePage(driver);
        SoftAssert sa = new SoftAssert();
        //1. Launch browser
       // 2. Navigate to url 'http://automationexercise.com'
       // 3. Verify that home page is visible successfully
        lunchUrl();
        String hometitle = driver.getTitle();
        sa.assertEquals(hometitle, "Automation Exercise");
        // 4. Scroll down page to bottom
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
       // 5. Verify 'SUBSCRIPTION' is visible
        String tx = hpage.getTextSubscription();
        sa.assertEquals(tx,"SUBSCRIPTION","subscription is visible");
       // 6. Click on arrow at bottom right side to move upward
        hpage.clickArrowButton();
       // 7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
       Assert.assertEquals(hpage.getText_FullFledge(),"Full-Fledged practice website for Automation Engineers","page is scrolled up");


    }


}
