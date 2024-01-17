package tests;

import Pages.CartPage;
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
import utilities.QaEnvPropReader;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartTest {
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
    public void TC7_VerifyAddProductIn_Cart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage=new CartPage(driver);
        SoftAssert sa=new SoftAssert();
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
        //8.Verify user is navigated to Cart page
        loginPage.verifyPage("https://www.saucedemo.com/cart.html");
        //9. Verify both products are added to Cart
        if(cartPage.numberOfProductsIn_Cart.size()>=2){
            System.out.println("Both the products are added to cart");
        }else {
            System.out.println("Both the products are not added to cart");
        }
        //10.Verify their price and quantity
        cartPage.getCartItemPrices();
        cartPage.getQuantity();
    }
    @Test
    public void TC8_VerifyRemoveProductsFrom_Cart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        SoftAssert sa = new SoftAssert();
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
        //8.Verify user is navigated to Cart page
        loginPage.verifyPage("https://www.saucedemo.com/cart.html");
        //9. Verify both products are added to Cart
        if (cartPage.numberOfProductsIn_Cart.size() >= 2) {
            System.out.println("Both the products are added to cart");
        } else {
            System.out.println("Both the products are not added to cart");
        }
        //10.Click on Remove button of product_1 and product_2
        cartPage.click_RemoveButtons();
        //11. Verify both products are removed from the Cart
        if(cartPage.numberOfProductsIn_Cart.size()==0){
            System.out.println("Both the products are removed from cart");
        }else {
            System.out.println("Both the products are not removed from cart");
        }

    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
