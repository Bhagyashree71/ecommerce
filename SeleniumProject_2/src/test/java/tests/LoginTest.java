package tests;

import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.QaEnvPropReader;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginTest   {

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
    public void TC1_VerifyLoginPage(){
        LoginPage loginPage=new LoginPage(driver);
        SoftAssert sa=new SoftAssert();
        //3. Verify that login page is visible successfully
        String urlLogin= driver.getCurrentUrl();
        Assert.assertEquals(urlLogin,"https://www.saucedemo.com/","login page is visible successfully");
        //Verify "Swag Labs" is displayed in login page.
         String text_SwagLabs=loginPage.getTextSwagLab();
         sa.assertEquals(text_SwagLabs,"Swag Labs");
    }
    @Test
    public void TC2_VerifyLoginPageWithCorrect_Userid_Password(){
        LoginPage loginPage=new LoginPage(driver);
        //3. Verify that login page is visible successfully
        String urlLogin= driver.getCurrentUrl();
        Assert.assertEquals(urlLogin,"https://www.saucedemo.com/","login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Verify user is navigated to inventory page
        String urlInventory=driver.getCurrentUrl();
        Assert.assertEquals(urlInventory,"https://www.saucedemo.com/inventory.html","user is navigated to inventory page");

    }
    @Test
    public void TC3_VerifyLoginPageWith_Incorrect_Userid_Password(){
        LoginPage loginPage=new LoginPage(driver);
        //3. Verify that login page is visible successfully
        String urlLogin= driver.getCurrentUrl();
        Assert.assertEquals(urlLogin,"https://www.saucedemo.com/","login page is visible successfully");
        //4.Enter Incorrect Username and Incorrect password
        //5.Click Login button
        loginPage.login("standard","secret");
        //6.Verify error message "Epic sadface: Username and password do not match any user in this service" is displayed.
        String text_errorMessage=loginPage.getText_loginErrorMessage();
        Assert.assertEquals(text_errorMessage,"Epic sadface: Username and password do not match any user in this service");
        //loginPage.crossButton_ErrorMessage.click();
    }
    @Test
    public void TC4_VerifyLogoutUser() {
        LoginPage loginPage = new LoginPage(driver);
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login2"), QaEnvPropReader.getProperty("password"));
        //6.Verify user is navigated to inventory page
        String urlInventory = driver.getCurrentUrl();
        Assert.assertEquals(urlInventory, "https://www.saucedemo.com/inventory.html", "user is navigated to inventory page");
        //7.Click on left menu button
        loginPage.clickMenuButton();
        //8.Click on Logout
        loginPage.clickLogoutButton();
        //9.Verify user is navigated to login page
        loginPage.verifyPage("https://www.saucedemo.com/");
    }
    @Test
    public void TC18_VerifyFooterText() {
        LoginPage loginPage = new LoginPage(driver);
        //3.Enter correct Username and Correct password
        //4.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login2"), QaEnvPropReader.getProperty("password"));
        //5.Verify user is navigated to inventory page
        String urlInventory = driver.getCurrentUrl();
        Assert.assertEquals(urlInventory, "https://www.saucedemo.com/inventory.html", "user is navigated to inventory page");
        //6.scroll down to footer
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //7.Verify Footer text "© 2024  Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy"
        String textFooter = loginPage.getText_Footer();
        Assert.assertEquals(textFooter, "© 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy");
    }
    @Test
    public void TC19_VerifyTwitterPageInNewWindow() {
        LoginPage loginPage = new LoginPage(driver);
        //3.Enter correct Username and Correct password
        //4.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login2"), QaEnvPropReader.getProperty("password"));
        //5.Verify user is navigated to inventory page
        String urlInventory = driver.getCurrentUrl();
        Assert.assertEquals(urlInventory, "https://www.saucedemo.com/inventory.html", "user is navigated to inventory page");
        //6.scroll down to footer
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //7.click on twitter sign
        loginPage.clickTwitterSign();
        //8.Verify new window have opened.
        Set<String> windows=driver.getWindowHandles();
        ArrayList<String > window=new ArrayList<>(windows);
        driver.switchTo().window(window.get(1));
        //9.veify the twitter page is opened in new window.
        loginPage.verifyPage("https://twitter.com/saucelabs");

    }
    @Test
    public void TC20_VerifyFacebookPageInNewWindow() {
        LoginPage loginPage = new LoginPage(driver);
        //3.Enter correct Username and Correct password
        //4.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login2"), QaEnvPropReader.getProperty("password"));
        //5.Verify user is navigated to inventory page
        String urlInventory = driver.getCurrentUrl();
        Assert.assertEquals(urlInventory, "https://www.saucedemo.com/inventory.html", "user is navigated to inventory page");
        //6.scroll down to footer
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //7.click on Facebook sign
        loginPage.clickFacebookSign();
        //8.Verify new window have opened.
        Set<String> windows=driver.getWindowHandles();
        ArrayList<String > window=new ArrayList<>(windows);
        driver.switchTo().window(window.get(1));
        //9.veify the facebook page is opened in new window.
        loginPage.verifyPage("https://www.facebook.com/saucelabs");

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
       }


}
