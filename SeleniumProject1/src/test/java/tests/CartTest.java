package tests;

import Pages.CartPage;
import Pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ExcelReader1;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class CartTest extends BaseTest{
    CartPage cpage;
    ProductPage prPage;

    @Test
    public void TC11_VerifySubscriptionInCartPage()  {
        HashMap<String,String> data= ExcelReader1.getTestData("tc4");
        SoftAssert sa = new SoftAssert();
        cpage = new CartPage(driver);
        prPage=new ProductPage(driver);
        lunchUrl();
        // 3. Verify that home page is visible successfully
        String hometitle = driver.getTitle();
        Assert.assertEquals(hometitle, "Automation Exercise","Home page is visible");
        //4. Click 'Cart' button
         cpage.clickCart();
       // 5. Scroll down to footer
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //6. Verify text 'SUBSCRIPTION'
        String textSubscription=cpage.getTextSub();
        sa.assertEquals(textSubscription,"SUBSCRIPTION");
        //7. Enter email address in input and click arrow button
        cpage.setEmailSub(data.get("Email"));
        cpage.clickSubscription();
       // 8. Verify success message 'You have been successfully subscribed!' is visible
        String msg=cpage.getMessage();
        Assert.assertEquals(msg,"You have been successfully subscribed!");
    }
    @Test
    public void TC12_AddProductInCart() throws InterruptedException {
        cpage = new CartPage(driver);
        prPage=new ProductPage(driver);
        lunchUrl();
        // 3. Verify that home page is visible successfully
        String hometitle = driver.getTitle();
        Assert.assertEquals(hometitle, "Automation Exercise","Home page is visible");

        //4. Click 'Products' button
        prPage.clickOnProductButton();
        //5. Hover over first product and click 'Add to cart'
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", prPage.viewproduct1);
        WebElement ele = cpage.firstproduct;
        Actions action = new Actions(driver);
        Thread.sleep(3000);
        action.moveToElement(ele).perform();
        action.moveToElement(cpage.firstaddtocart).click().perform();

        //6. Click 'Continue Shopping' button
        Thread.sleep(3000);
        cpage.clickContinueShopping();
        //7. Hover over second product and click 'Add to cart'
        action.moveToElement(cpage.secondproduct).perform();
        Thread.sleep(2000);
        action.moveToElement(cpage.secondaddtocart).click().perform();

        //8. Click 'View Cart' button
        Thread.sleep(3000);
        cpage.clickViewCartButton();
        //9. Verify both products are added to Cart
        List<WebElement> table=cpage.cartTable;
        if(table.size()>=2){
            System.out.println("Both products are added to cart");
        }else{
            System.out.println("Both the products are not added to cart");
        }
        //10. Verify their prices, quantity and total price


    }
    @Test
    public void TC13_VerifyProductQuantityInCart(){

        SoftAssert sa = new SoftAssert();
        cpage = new CartPage(driver);
        prPage=new ProductPage(driver);
        lunchUrl();
        // 3. Verify that home page is visible successfully
        String hometitle = driver.getTitle();
        Assert.assertEquals(hometitle, "Automation Exercise","Home page is visible");

        //4. Click 'View Product' for any product on home page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", prPage.viewproduct1);
        prPage.viewproduct1.click();

        //5. Verify product detail is opened

        String textprinfo=prPage.getPrInfo();
        String exp_prdetail = "Blue Top\n" +
                "Category: Women > Tops\n" +
                "Rs. 500\n" +
                "Quantity: Add to cart\n" +
                "Availability: In Stock\n" +
                "Condition: New\n" ;
        String actual_prdetail =textprinfo;
        if(actual_prdetail.toLowerCase().contains(exp_prdetail.toLowerCase())) {
            System.out.println("product detailed is opened");;
        }else {
            System.out.println("product detailed is not opened");
        }
        //6. Increase quantity to 4
        String value="4";
        cpage.quantityBox.clear();
        cpage.quantityBox.sendKeys("4");

        //7. Click 'Add to cart' button
        cpage.addToCartButton.click();

        //8. Click 'View Cart' button
        cpage.clickViewCartButton();

        //9. Verify that product is displayed in cart page with exact quantity

        WebElement quantity_cart= driver.findElement(By.xpath("//button[text()='4']"));
        String value_cart=quantity_cart.getText();
        if(cpage.pr_img1.isDisplayed() && value.equals(value_cart) ){
            System.out.println("product is displayed in cart page with exact quantity");
        }else {
            System.out.println("product is displayed in cart page is not equal");
        }

    }
    @Test
    public void TC17_RemoveProductFromCart() throws InterruptedException {
        SoftAssert sa = new SoftAssert();
        cpage = new CartPage(driver);
        prPage=new ProductPage(driver);
        lunchUrl();
        // 3. Verify that home page is visible successfully
        String hometitle = driver.getTitle();
        Assert.assertEquals(hometitle, "Automation Exercise","Home page is visible");

        //4. Add products to cart
        prPage.clickOnProductButton();

        Thread.sleep(3000);
        //WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.visibilityOf(cpage.firstproduct));

        // Hover over first product and click 'Add to cart'
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", prPage.viewproduct1);

        WebElement ele = cpage.firstproduct;
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
        action.moveToElement(cpage.firstaddtocart).click().perform();
        // Click 'Continue Shopping' button
        Thread.sleep(5000);
        cpage.btncontinue.click();
        //Hover over second product and click 'Add to cart'
        action.moveToElement(cpage.secondproduct).perform();
        Thread.sleep(2000);
        action.moveToElement(cpage.secondaddtocart).click().perform();
        cpage.btncontinue.click();

        //5. Click 'Cart' button
        cpage.cartbtn.click();

        //6. Verify that cart page is displayed
        Assert.assertTrue(cpage.cartPage.isDisplayed());

        //7. Click 'X' button corresponding to particular product
        driver.findElement(By.xpath("//a[@data-product-id='1']")).click();
        driver.findElement(By.xpath("//a[@data-product-id='2']")).click();

        //8. Verify that product is removed from the cart
        Thread.sleep(5000);
        String textEmptycart=driver.findElement(By.xpath("//b[text()='Cart is empty!']")).getText();

        Assert.assertEquals(textEmptycart,"Cart is empty!");
    }
    @Test
    public void TC22_AddToCartFromRecommendedItems(){

        SoftAssert sa = new SoftAssert();
        cpage = new CartPage(driver);
        prPage=new ProductPage(driver);
        lunchUrl();
        //3. Scroll to bottom of page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        //4. Verify 'RECOMMENDED ITEMS' are visible
       WebElement el=cpage.rec_item;
       sa.assertTrue(el.isDisplayed());
        //String textRecommendedItems=cpage.getText_recommendedItems();
        //sa.assertEquals(textRecommendedItems,true);

        //5. Click on 'Add To Cart' on Recommended product
         cpage.click_addToCart1();
        //6. Click on 'View Cart' button
        cpage.viewcart.click();
        //7. Verify that product is displayed in cart page

        if(cpage.getRowCount_Cart()>=2 && cpage.pr_img1.isDisplayed()){
            System.out.println("product is displayed in cart");
        }else{
            System.out.println("product is not displayed in cart");
        }

    }



    }
