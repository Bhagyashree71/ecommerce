package tests;

import Pages.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ExcelReader1;

import java.util.HashMap;

public class OrderTest extends BaseTest {
    @Test
    public void Tc14_PlaceOrdr_registerWhileCheckout() throws InterruptedException {
        OrderPage oPage=new OrderPage(driver);
        RegisterPage rpage=new RegisterPage(driver);
        CartPage cpage = new CartPage(driver);
        ProductPage prPage = new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        HashMap<String,String> data= ExcelReader1.getTestData("tc4");
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        //3. Verify that home page is visible successfully
        lunchUrl();
        String hometitle = driver.getTitle();
        String homePageTitle="Automation Exercise";
        sa.assertEquals(hometitle,homePageTitle);

        //4. Add products to cart

        prPage.clickOnProductButton();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", prPage.viewproduct1);
        Thread.sleep(5000);
        WebElement ele = cpage.firstproduct;
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
        action.moveToElement(cpage.firstaddtocart).click().perform();
        Thread.sleep(3000);
        cpage.btncontinue.click();
        action.moveToElement(cpage.secondproduct).perform();
        Thread.sleep(2000);
        action.moveToElement(cpage.secondaddtocart).click().perform();
        cpage.btncontinue.click();

        //5. Click 'Cart' button
        cpage.clickCart();

        // 6. Verify that cart page is displayed
        Assert.assertEquals(cpage.cartPage.isDisplayed(),true);

       // 7. Click Proceed To Checkout
        oPage.clickProceedToCheckout();
        //8. Click 'Register / Login' button
        oPage.clickRegister_SignUpButton();

        //9. Fill all details in Signup and create account
        rpage.signup(data.get("Name"),data.get("Email"));
        rpage.title.click();
        rpage.name.clear();

        rpage.setName(data.get("name"));
        rpage.setPassword(data.get("password"));
        //for scroll down

        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //for selection of date of birth from dropdown
        Select dropdown=new Select(rpage.select);
        dropdown.selectByValue("2");
        Select dropdown1=new Select(rpage.select1);
        dropdown1.selectByVisibleText("February");
        Select dropdown2=new Select(rpage.select2);
        dropdown2.selectByValue("1990");
        Thread.sleep(5000);
        // Select checkbox 'Sign up for our newsletter!'
        // Select checkbox 'Receive special offers from our partners!'
        rpage.clickCheckbox();
        // Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        rpage.setFirstname(data.get("Fname"));
        rpage.setLasttname(data.get("Lname"));
        rpage.setCompany(data.get("Company"));
        rpage.setAddress(data.get("Address"));
        rpage.setAddress2(data.get("Address2"));
        Select dropdown3=new Select(rpage.country);
        dropdown3.selectByValue("India");
        rpage.state.sendKeys("Karnataka");
        rpage.city.sendKeys("Bangalore");
        rpage.zipcode.sendKeys("560047");
        rpage.mobileno.sendKeys("8763088634");
        // Click 'Create Account button'
        rpage.createbutton.click();

        //10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        String textaccount = rpage.getaccount();
        Assert.assertTrue(textaccount.toLowerCase().contains("account created!"));
        rpage.clickcontinue();

        //11. Verify ' Logged in as username' at top
        String textlog=rpage.textlogged.getText();
        Assert.assertTrue(textlog.toLowerCase().contains("logged in as"));

        //12.Click 'Cart' button
        cpage.clickCart();

        //13. Click 'Proceed To Checkout' button
        oPage.clickProceedToCheckout();

        //14. Verify Address Details and Review Your Order
        String textAddress_detail=oPage.address_detail.getText();
        Assert.assertTrue(textAddress_detail.toLowerCase().contains("address details"));
        String textReviewOrder=oPage.reviewOrder.getText();
        Assert.assertTrue(textReviewOrder.toLowerCase().contains("review your order"));

        //15. Enter description in comment text area and click 'Place Order'
        oPage.commentTextArea.sendKeys("Make a proper Packaging of product.");
        oPage.placeOrder.click();

        //16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        oPage.nameOnCard.sendKeys("Bhagya Naik");
        oPage.cardNumber.sendKeys("53443422234560098");
        oPage.cvc.sendKeys("345");
        oPage.expiryMonth.sendKeys("05");
        oPage.expiryYear.sendKeys("2026");

        //17. Click 'Pay and Confirm Order' button
         oPage.payConfirmButton.click();
       // 18. Verify success message 'Your order has been placed successfully!'
       // String textSuccessMessage=oPage.successMessage.getText();
       // System.out.println(textSuccessMessage);
      //sa.assertFalse(oPage.successMessage.isDisplayed());

       // 19. Click 'Delete Account' button
        rpage.clickDelete();

       // 20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        String a_text=rpage.getDeleteText();
        Assert.assertTrue(a_text.toLowerCase().contains("account deleted!"));
        rpage.clickcontinue();

    }
    @Test
    public void TC15_VerifyPlaceOrder_registerBeforeCheckout() throws InterruptedException {
        OrderPage oPage=new OrderPage(driver);
        RegisterPage rpage=new RegisterPage(driver);
        CartPage cpage = new CartPage(driver);
        ProductPage prPage = new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        HashMap<String,String> data= ExcelReader1.getTestData("tc3");
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        //3. Verify that home page is visible successfully
        lunchUrl();
        String hometitle = driver.getTitle();
        String homePageTitle="Automation Exercise";
        sa.assertEquals(hometitle,homePageTitle);

        //4. Click 'Signup / Login' button
        rpage.signUp.click();

        //5. Fill all details in Signup and create account
        rpage.signup(data.get("Name"),data.get("Email"));
        rpage.title.click();
        rpage.name.clear();
        rpage.setName(data.get("name"));
        rpage.setPassword(data.get("password"));
        //for scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //for selection of date of birth from dropdown
        Select dropdown=new Select(rpage.select);
        dropdown.selectByValue("5");
        Select dropdown1=new Select(rpage.select1);
        dropdown1.selectByVisibleText("February");
        Select dropdown2=new Select(rpage.select2);
        dropdown2.selectByValue("1989");
        Thread.sleep(5000);
        rpage.clickCheckbox();
        // Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        rpage.setFirstname(data.get("Fname"));
        rpage.setLasttname(data.get("Lname"));
        rpage.setCompany(data.get("Company"));
        rpage.setAddress(data.get("Address"));
        rpage.setAddress2(data.get("Address2"));
        Select dropdown3=new Select(rpage.country);
        dropdown3.selectByValue("India");
        rpage.state.sendKeys("Karnataka");
        rpage.city.sendKeys("Bangalore");
        rpage.zipcode.sendKeys("560047");
        rpage.mobileno.sendKeys("8763088634");
        rpage.createbutton.click();

        //6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        String textaccount = rpage.getaccount();
        Assert.assertTrue(textaccount.toLowerCase().contains("account created!"));
        rpage.clickcontinue();

        //7. Verify ' Logged in as username' at top
        String textlog=rpage.textlogged.getText();
        Assert.assertTrue(textlog.toLowerCase().contains("logged in as"));

        //8. Add products to cart
        prPage.clickOnProductButton();

        js.executeScript("arguments[0].scrollIntoView();", prPage.viewproduct1);
        Thread.sleep(5000);
        WebElement ele = cpage.firstproduct;
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
        Thread.sleep(3000);
        action.moveToElement(cpage.firstaddtocart).click().perform();
        Thread.sleep(3000);
        cpage.btncontinue.click();
        action.moveToElement(cpage.secondproduct).perform();
        Thread.sleep(3000);
        action.moveToElement(cpage.secondaddtocart).click().perform();
        cpage.btncontinue.click();

        //9. Click 'Cart' button
        cpage.cartbtn.click();

        //10. Verify that cart page is displayed
        Assert.assertEquals(cpage.cartPage.isDisplayed(),true);

        //11. Click Proceed To Checkout
        oPage.proceedToCheckout.click();

        //12. Verify Address Details and Review Your Order
        String textAddress_detail=oPage.address_detail.getText();
        Assert.assertTrue(textAddress_detail.toLowerCase().contains("address details"));
        String textReviewOrder=oPage.reviewOrder.getText();
        Assert.assertTrue(textReviewOrder.toLowerCase().contains("review your order"));


        //13. Enter description in comment text area and click 'Place Order'
        oPage.commentTextArea.sendKeys("Make a proper Packaging of product.");
        oPage.placeOrder.click();

        //14. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        oPage.nameOnCard.sendKeys("Bhagya Naik");
        oPage.cardNumber.sendKeys("53443422234560098");
        oPage.cvc.sendKeys("345");
        oPage.expiryMonth.sendKeys("05");
        oPage.expiryYear.sendKeys("2026");

        //15. Click 'Pay and Confirm Order' button
        oPage.payConfirmButton.click();

        //16. Verify success message 'Your order has been placed successfully!'

        //17. Click 'Delete Account' button
        rpage.clickDelete();
        //18. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        String a_text=rpage.getDeleteText();
        Assert.assertTrue(a_text.toLowerCase().contains("account deleted!"));
        rpage.clickcontinue();

    }

    @Test
    public void TC16_VerifyPlaceOrder_LoginBeforeCheckout() throws InterruptedException {
        OrderPage oPage=new OrderPage(driver);
        RegisterPage rpage=new RegisterPage(driver);
        CartPage cpage = new CartPage(driver);
        LoginPage lpage=new LoginPage(driver);
        ProductPage prPage = new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        HashMap<String,String> data= ExcelReader1.getTestData("tc1");
       // 1. Launch browser
       // 2. Navigate to url 'http://automationexercise.com'
       // 3. Verify that home page is visible successfully

        lunchUrl();
        String hometitle = driver.getTitle();
        String homePageTitle="Automation Exercise";
        sa.assertEquals(hometitle,homePageTitle);

        // 4. Click 'Signup / Login' button
        rpage.signUp.click();

       // 5. Fill email, password and click 'Login' button
        lpage.login(data.get("Email"),data.get("password"));
       // 6. Verify 'Logged in as username' at top
        String textlog1=rpage.textlogged.getText();
        Assert.assertTrue(textlog1.toLowerCase().contains("logged in as"));
       // 7. Add products to cart
        JavascriptExecutor js = (JavascriptExecutor) driver;
        prPage.clickOnProductButton();
        js.executeScript("arguments[0].scrollIntoView();", prPage.viewproduct1);
        Thread.sleep(5000);
        WebElement ele = cpage.firstproduct;
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
        Thread.sleep(3000);
        action.moveToElement(cpage.firstaddtocart).click().perform();
        Thread.sleep(3000);
        cpage.btncontinue.click();
        action.moveToElement(cpage.secondproduct).perform();
        Thread.sleep(3000);
        action.moveToElement(cpage.secondaddtocart).click().perform();
        cpage.btncontinue.click();

        // 8. Click 'Cart' button
        cpage.cartbtn.click();

       // 9. Verify that cart page is displayed
        Assert.assertTrue(cpage.cartPage.isDisplayed());

        //10. Click Proceed To Checkout
        oPage.proceedToCheckout.click();

        //11. Verify Address Details and Review Your Order
        String textAddress_detail=oPage.address_detail.getText();
        Assert.assertTrue(textAddress_detail.toLowerCase().contains("address details"));
        String textReviewOrder=oPage.reviewOrder.getText();
        Assert.assertTrue(textReviewOrder.toLowerCase().contains("review your order"));

       // 12. Enter description in comment text area and click 'Place Order'
        oPage.commentTextArea.sendKeys("Make a proper Packaging of product.");
        oPage.placeOrder.click();

        //13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        oPage.nameOnCard.sendKeys("Bhagya Naik");
        oPage.cardNumber.sendKeys("53443422234560098");
        oPage.cvc.sendKeys("345");
        oPage.expiryMonth.sendKeys("05");
        oPage.expiryYear.sendKeys("2026");

        //14. Click 'Pay and Confirm Order' button
        oPage.payConfirmButton.click();

       // 15. Verify success message 'Your order has been placed successfully!'

     //16. Click 'Delete Account' button
//        rpage.clickDelete();
//
//        //17. Verify 'ACCOUNT DELETED!' and click 'Continue' button
//        String a_text=rpage.getDeleteText();
//        Assert.assertTrue(a_text.toLowerCase().contains("account deleted!"));
//        rpage.clickcontinue();

    }

}
