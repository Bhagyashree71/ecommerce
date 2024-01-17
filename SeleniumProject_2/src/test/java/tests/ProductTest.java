package tests;

import Pages.LoginPage;
import Pages.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.QaEnvPropReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductTest {

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
        @Test(priority =1)
        public void TC5_VerifyProducts_AfterLogin() {
            LoginPage loginPage = new LoginPage(driver);
            ProductPage productPage = new ProductPage(driver);
            //3. Verify that login page is visible successfully
            String urlLogin = driver.getCurrentUrl();
            Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
            //4.Enter correct Username and Correct password
            //5.Click Login button
            loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
            //6.Verify user is navigated to Product page
            loginPage.verifyPage("https://www.saucedemo.com/inventory.html");
            //7.Verify "Products" is visible in left top side.
            String text_product = productPage.getTextProducts();
            Assert.assertEquals(text_product, "Products");
            //8.Verify products are visible after login.
            List<WebElement> product_details = productPage.getProducts();
            for (WebElement product_detail : product_details) {
                Assert.assertTrue(product_detail.isDisplayed(), "all products are visible after login");
            }
        }
        @Test(priority = 2)
        public void TC6_VerifyProducts_Details(){

            LoginPage loginPage = new LoginPage(driver);
            ProductPage productPage = new ProductPage(driver);
            SoftAssert sa=new SoftAssert();
            //3. Verify that login page is visible successfully
            String urlLogin = driver.getCurrentUrl();
            Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
            //4.Enter correct Username and Correct password
            //5.Click Login button
            loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
            //6.Verify user is navigated to Product page
            loginPage.verifyPage("https://www.saucedemo.com/inventory.html");
            //7.Verify "Products" is visible in left top side.
            String text_product = productPage.getTextProducts();
            Assert.assertEquals(text_product, "Products");
            //8.click on first product
            productPage.clickFirstProduct();
            //9.User is landed to product detail page of first product
            loginPage.verifyPage("https://www.saucedemo.com/inventory-item.html?id=4");
            // 10.Verify that product detail is visible: product name, description, price
            String textFirstProductDetail = productPage.getDetailFirstProduct();
            sa.assertTrue(textFirstProductDetail.toLowerCase().contains("sauce labs backpack,carry.allthethings() with the sleek,$,29.99"));
            //11.click on Back to Products
            productPage.clickOnBackToProduct();
            //12.Verify user is navigated to Product page
            loginPage.verifyPage("https://www.saucedemo.com/inventory.html");

        }
    @Test
     public void TC6_VerifyCartSignButton() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Verify user is navigated to Product page
        loginPage.verifyPage("https://www.saucedemo.com/inventory.html");
        //7.Verify cart sign is visible on top right corner.
        sa.assertTrue(productPage.cart_sign_button.isDisplayed());
        //8.Add  products to cart
        productPage.clickOnAddToCart_1();
        productPage.clickOnAddToCart_2();
        //9.verify cart sign showing the number of product same as number of product added to cart.
        int numberOf_Product = Integer.parseInt(productPage.cart_sign_button.getText());
        System.out.println(numberOf_Product);
        int numberOfClick = productPage.getNumberOf_click();
          if (numberOf_Product == numberOfClick) {
            System.out.println("Number of product shown in cart sign is same as number of products added to cart ");
          } else {
            System.out.println("Number of product shown in cart sign is not same as number of products added to cart ");
          }
        }
    @Test
    public void TC14_VerifyFilterFunctionality() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Verify user is navigated to Product page
        loginPage.verifyPage("https://www.saucedemo.com/inventory.html");
        //7.click filter button
        productPage.clickFilterButton();
        //8.Verify that if the user clicks on the filter button then filter options should be displayed properly.
        List<WebElement> filter_options = productPage.filterOptions;
          for (WebElement filter_option : filter_options) {
            System.out.println(filter_option.getText());
            Assert.assertTrue(filter_option.isDisplayed(), "all filter options are visible");
          }
        }
    @Test
    public void TC15_VerifySelectFilterFunctionality_NameZtoA() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Verify user is navigated to Product page
        loginPage.verifyPage("https://www.saucedemo.com/inventory.html");
        //sort the products name
        List<String> sorted_names_products = new ArrayList<>();
        List<WebElement> products_names = productPage.productNames;
        for (int i = 0; i < products_names.size(); i++) {
            sorted_names_products.add(products_names.get(i).getText());
        }
        System.out.println(sorted_names_products);
        Collections.sort(sorted_names_products, Collections.reverseOrder());
        System.out.println(sorted_names_products);

        //7.click filter button
        productPage.clickFilterButton();
        //8.Select Name(ZtoA) from filter options
        Select dropdown = new Select(productPage.filter_option);
        dropdown.selectByVisibleText("Name (Z to A)");

        //9.verify products name are displayed in order Z to A
        List<String> productNames_afterFilter = new ArrayList<String>();
        List<WebElement> product_names = productPage.productNames;
        for (int j = 0; j < product_names.size(); j++) {
            productNames_afterFilter.add(products_names.get(j).getText());
        }
        System.out.println(productNames_afterFilter);
        Assert.assertEquals(sorted_names_products, productNames_afterFilter, "products are displayed in order Z to A");
    }
    @Test
    public void TC16_VerifySelectFilterFunctionality_PriceLowToHigh() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Verify user is navigated to Product page
        loginPage.verifyPage("https://www.saucedemo.com/inventory.html");
        //Sort the Products Prices
        List<Float> sorted_prices_products = new ArrayList<Float>();
        List<WebElement> product_prices = productPage.productPrices;
        for (int i = 0; i < product_prices.size(); i++) {
            sorted_prices_products.add(Float.parseFloat(product_prices.get(i).getText().replace("$", "0")));
        }
        System.out.println(sorted_prices_products);
        Collections.sort(sorted_prices_products);
        System.out.println(sorted_prices_products);
        //7.click filter button
        productPage.clickFilterButton();
        //8.Select Price(low to high) from filter options
        Select dropdown = new Select(productPage.filter_option);
        dropdown.selectByVisibleText("Price (low to high)");
        //9.verify products Prices are displayed in order low to high.
        List<Float> productPrices_afterFilter = new ArrayList<Float>();
        List<WebElement> products_prices = productPage.productPrices;
        for (int j = 0; j < products_prices.size(); j++) {
            productPrices_afterFilter.add(Float.parseFloat(products_prices.get(j).getText().replace("$", "0")));
        }
        System.out.println(productPrices_afterFilter);
        Assert.assertEquals(sorted_prices_products, productPrices_afterFilter, "products prices are displayed in order low to high");
    }
    @Test
    public void TC17_VerifySelectFilterFunctionality_PriceHighToLow() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        //3. Verify that login page is visible successfully
        String urlLogin = driver.getCurrentUrl();
        Assert.assertEquals(urlLogin, "https://www.saucedemo.com/", "login page is visible successfully");
        //4.Enter correct Username and Correct password
        //5.Click Login button
        loginPage.login(QaEnvPropReader.getProperty("login1"),QaEnvPropReader.getProperty("password"));
        //6.Verify user is navigated to Product page
        loginPage.verifyPage("https://www.saucedemo.com/inventory.html");
        //Sort the Products Prices in descending order
        List<Float> sorted_prices_products = new ArrayList<Float>();
        List<WebElement> product_prices = productPage.productPrices;
        for (int i = 0; i < product_prices.size(); i++) {
            sorted_prices_products.add(Float.parseFloat(product_prices.get(i).getText().replace("$", "0")));
        }
        System.out.println(sorted_prices_products);
        Collections.sort(sorted_prices_products,Collections.reverseOrder());
        System.out.println(sorted_prices_products);
        //7.click filter button
        productPage.clickFilterButton();
        //8.Select Price(low to high) from filter options
        Select dropdown = new Select(productPage.filter_option);
        dropdown.selectByVisibleText("Price (high to low)");
        //9.verify products Prices are displayed in order low to high.
        List<Float> productPrices_afterFilter = new ArrayList<Float>();
        List<WebElement> products_prices = productPage.productPrices;
        for (int j = 0; j < products_prices.size(); j++) {
            productPrices_afterFilter.add(Float.parseFloat(products_prices.get(j).getText().replace("$", "0")));
        }
        System.out.println(productPrices_afterFilter);
        Assert.assertEquals(sorted_prices_products, productPrices_afterFilter, "products prices are displayed in order high to low");
    }
    @AfterMethod
    public void teardown(){
            driver.quit();
    }





    }

