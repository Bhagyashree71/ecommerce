package tests;

import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.LoginPage;
import Pages.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ExcelReader1;
import utilities.QaEnvPropReader;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckoutTest {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        //1. Launch browser
        //2. Navigate to url 'https://www.saucedemo.com/'
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        String url = QaEnvPropReader.getProperty("url");
        driver.get(url);

    }
    @Test
    public void TC9_VerifyCheckoutProcess_placeOrder() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        HashMap<String,String> data= ExcelReader1.getTestData("tc1");
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Add  two products to cart
        productPage.clickOnAddToCart_1();
        productPage.clickOnAddToCart_2();
        //7.Click on 'Cart Sign' button
        productPage.clickCartSignButton();
        //8. Verify both products are added to Cart
        if(cartPage.numberOfProductsIn_Cart.size()>=2){
            System.out.println("Both the products are added to cart");
        }else {
            System.out.println("Both the products are not added to cart");
        }
        //9.Click on Checkout button
        checkoutPage.clickCheckout();
        //10.Enter First Name,Last Name,Postal code.
        checkoutPage.enterCheckoutDetails(data.get("Firstname"),data.get("Lastname"),data.get("Zipcode"));
        //11.Click on continue button
        checkoutPage.clickContinueButton();
        //12.Verify user navigate to Checkout page
        loginPage.verifyPage("https://www.saucedemo.com/checkout-step-two.html");
        //13.Verify products are visible in checkout page
        List<WebElement> products_checkout=checkoutPage.checkout_items;
        for (WebElement product_checkout : products_checkout) {
            Assert.assertTrue(product_checkout.isDisplayed(), "products are visible in checkout page");
        }
        //14.Verify Payment Information,Shipping Information,Price Total is visible in checkout page
        String expected_productSummary="Payment Information\n" +
                "SauceCard #31337\n" +
                "Shipping Information\n" +
                "Free Pony Express Delivery!\n" +
                "Price Total\n" +
                "Item total: $39.98\n" +
                "Tax: $3.20\n" +
                "Total: $43.18\n" +
                "Cancel\n" +
                "Finish";
        String summaryProduct=checkoutPage.product_summary.getText();
        Assert.assertTrue(summaryProduct.contains(expected_productSummary));
        //15.Click Finish button
        checkoutPage.clickFinishButton();
        //16.Verify Success message "Thank You for Your Order!"
        String orderMessage=checkoutPage.message_Thankyou.getText();
        Assert.assertTrue(orderMessage.contains("Thank you for your order!"));
        //17.click on Back Home button.
        checkoutPage.clickBackHomeButton();
    }
    @Test
    public void TC10_VerifyCheckoutProcess_withBlankFirstName() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        HashMap<String, String> data = ExcelReader1.getTestData("tc1");
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Add  two products to cart
        productPage.clickOnAddToCart_1();
        productPage.clickOnAddToCart_2();
        //7.Click on 'Cart Sign' button
        productPage.clickCartSignButton();
        //8.Click on Checkout button
        checkoutPage.clickCheckout();
        //9.Enter Last name and postal code and make FirstName lank.
        checkoutPage.enterCheckoutDetails("",data.get("Lastname"),data.get("Zipcode"));
        //10.Click on continue button
        checkoutPage.clickContinueButton();
        //11.Verify error message "Error: First Name is required" is displayed.
        String errorMessage1=checkoutPage.get_ErrorMessageFirstName();
        Assert.assertTrue(errorMessage1.contains("Error: First Name is required"));
    }
    @Test
    public void TC11_VerifyCheckoutProcess_withBlankLastName() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        HashMap<String, String> data = ExcelReader1.getTestData("tc1");
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Add  two products to cart
        productPage.clickOnAddToCart_1();
        productPage.clickOnAddToCart_2();
        //7.Click on 'Cart Sign' button
        productPage.clickCartSignButton();
        //8.Click on Checkout button
        checkoutPage.clickCheckout();
        //9.Enter Last name and postal code and make FirstName lank.
        checkoutPage.enterCheckoutDetails(data.get("Firstname"),"",data.get("Zipcode"));
        //10.Click on continue button
        checkoutPage.clickContinueButton();
        //11.Verify error message "Error: Last Name is required" is displayed.
        String errorMessage2=checkoutPage.get_ErrorMessageLastName();
        Assert.assertTrue(errorMessage2.contains("Error: Last Name is required"));
    }
    @Test
    public void TC12_VerifyCheckoutProcess_withBlankPostalCode() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        HashMap<String, String> data = ExcelReader1.getTestData("tc1");
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Add  two products to cart
        productPage.clickOnAddToCart_1();
        productPage.clickOnAddToCart_2();
        //7.Click on 'Cart Sign' button
        productPage.clickCartSignButton();
        //8.Click on Checkout button
        checkoutPage.clickCheckout();
        //9.Enter First name and postal code and make LastName Blank.
        checkoutPage.enterCheckoutDetails(data.get("Firstname"),data.get("Lastname"),"");
        //10.Click on continue button
        checkoutPage.clickContinueButton();
        //11.Verify error message "Error: Postal Code is required" is displayed.
        String errorMessage3=checkoutPage.get_ErrorMessagePostalCode();
        Assert.assertTrue(errorMessage3.contains("Error: Postal Code is required"));
    }
    @Test
    public void TC13_VerifyCancelling_CheckoutProcess() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        HashMap<String, String> data = ExcelReader1.getTestData("tc2");
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Add  two products to cart
        productPage.clickOnAddToCart_1();
        productPage.clickOnAddToCart_2();
        //7.Click on 'Cart Sign' button
        productPage.clickCartSignButton();
        //8.Click on Checkout button
        checkoutPage.clickCheckout();
        //9.Enter First Name,Last Name,Postal code.
        checkoutPage.enterCheckoutDetails(data.get("Firstname"),data.get("Lastname"),data.get("Zipcode"));
        //10.Click on continue button
        checkoutPage.clickContinueButton();
        //11.Verify user navigate to Checkout page
        loginPage.verifyPage("https://www.saucedemo.com/checkout-step-two.html");
        //12.Verify products are visible in checkout page
        List<WebElement> products_checkout=checkoutPage.checkout_items;
        for (WebElement product_checkout : products_checkout) {
            Assert.assertTrue(product_checkout.isDisplayed(), "products are visible in checkout page");
        }
        //13.Verify after clicking cancel button,it will cancel the order process and navigate to product page
         checkoutPage.clickCancelButton();
        loginPage.verifyPage("https://www.saucedemo.com/inventory.html");
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
