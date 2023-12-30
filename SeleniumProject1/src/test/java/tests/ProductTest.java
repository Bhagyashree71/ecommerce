package tests;

import Pages.ProductPage;
import Pages.TestcasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ExcelReader1;

import java.util.HashMap;
import java.util.List;

public class ProductTest extends BaseTest {
    ProductPage prPage;

    @Test(priority = 0)
    public void TC8_VerifyAllProducts() {
        prPage = new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        lunchUrl();
        //3. Verify that home page is visible successfully
        String hometitle = driver.getTitle();
        Assert.assertEquals(hometitle, "Automation Exercise");

        //4. Click on 'Products' button
        prPage.clickOnProductButton();
        //5. Verify user is navigated to ALL PRODUCTS page successfully
        String productPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(productPageUrl, "https://automationexercise.com/products");
        //6. The products list is visible
        //7. Click on 'View Product' of first product
        //Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", prPage.viewproduct1);
        prPage.viewproduct1.click();
        //8. User is landed to product detail page
        String productDetailPageUrl = driver.getCurrentUrl();
        sa.assertEquals(productDetailPageUrl, "https://automationexercise.com/product_details/1");
        //9. Verify that detail detail is visible: product name, category, price, availability, condition, brand
        String textProductInfo = prPage.getPrInfo();
        sa.assertTrue(textProductInfo.toLowerCase().contains("blue top category:,rs.500,availability,condition,brand"));
    }

    @Test(priority = 1)
    public void TC9_SearchProduct() throws InterruptedException {
        prPage = new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        // 1. Launch browser
        // 2. Navigate to url 'http://automationexercise.com'
        // 3. Verify that home page is visible successfully
        lunchUrl();
        String hometitle = driver.getTitle();
        sa.assertEquals(hometitle, "Automation Exercise");
        // 4. Click on 'Products' button
        prPage.clickOnProductButton();
        //5. Verify user is navigated to ALL PRODUCTS page successfully
        String productPageUrl = driver.getCurrentUrl();
        sa.assertEquals(productPageUrl, "https://automationexercise.com/products");
        //6. Enter product name in search input and click search button
        prPage.searchProduct("dress");
        prPage.clickSearchPr();
        //7. Verify 'SEARCHED PRODUCTS' is visible
        Thread.sleep(5000);
        String textsearchpr = prPage.getTextSearchpr();
        sa.assertTrue(textsearchpr.toLowerCase().contains("searched product"));
        //8. Verify all the products related to search are visible

        List<WebElement> searchResults = prPage.getSearchResult();
        for (WebElement searchResult : searchResults) {
            //System.out.println(searchResult);
            Assert.assertTrue(searchResult.isDisplayed(),"all product related to search are visible");
        }
    }



    @Test(priority =2)
    public void TC18_ViewCategoryProducts() throws InterruptedException {
        prPage=new ProductPage(driver);
        SoftAssert sa = new SoftAssert();
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        lunchUrl();
        //3. Verify that categories are visible on left side bar.
        String text_leftSide=prPage.getText_leftside();
        List<WebElement> categoriesLestSide=prPage.getCategories();
        for(WebElement categoryleftside:categoriesLestSide){
            Assert.assertTrue(categoryleftside.isDisplayed(),"categories are displayed in left side bar");
        }
        //String expectedCategory1="WOMEN\n" +
        //        "MEN\n" +
        //        "KIDS";
        //if(prPage.leftSide_text.isDisplayed() && text_leftSide.toLowerCase().contains(expectedCategory1.toLowerCase())){
        //    System.out.println("categories are visible in left side bar");
        //}else{
         //   System.out.println("categories are not visible in left side bar");
       // }

        //4. Click on 'Women' category
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", prPage.womenCategory);
        Thread.sleep(3000);
        prPage.click_WomenCategory();
        //5. Click on any category link under 'Women' category, for example: Dress
        int index_category=prPage.getCategories_Women();
        prPage.categories_Women.get(index_category).click();

        //6. Verify that category page is displayed and confirm text 'WOMEN - TOPS PRODUCTS'
        String expected_Url1="https://automationexercise.com/category_products/2";
        Assert.assertEquals(driver.getCurrentUrl(),expected_Url1);
        String textWomen_Product=prPage.getTextWomen_TopsProduct();
        Assert.assertTrue(textWomen_Product.toLowerCase().contains("women - tops products"));

        //7. On left side bar, click on any sub-category link of 'Men' category
        prPage.menCategory.click();
        String menCategory_input="JEANS";
        int index1=0;
        for(int j=0;j<prPage.categories_Men.size();j++){
            System.out.println(prPage.categories_Men.get(j).getText());
            if(prPage.categories_Men.get(j).getText().equals(menCategory_input)){
                index1=j;
                System.out.println(index1);
                break;
            }
        }
       prPage.categories_Men.get(index1).click();
        //8. Verify that user is navigated to that category page
        String expected_Url2="https://automationexercise.com/category_products/6";
        Assert.assertEquals(driver.getCurrentUrl(),expected_Url2);


    }
    @Test(priority =3)
    public void TC19_View_CartBrandProduct() throws InterruptedException {
        prPage=new ProductPage(driver);
       // 1. Launch browser
       // 2. Navigate to url 'http://automationexercise.com'
        lunchUrl();
       // 3. Click on 'Products' button
        prPage.clickOnProductButton();
        Thread.sleep(5000);
       // 4. Verify that Brands are visible on left side bar
        String text_leftSide=prPage.getText_leftside();
        String expectedBrand= "POLO\n" +
                "(5)\n" +
                "H&M\n" +
                "(5)\n" +
                "MADAME\n" +
                "(3)\n" +
                "MAST & HARBOUR\n" +
                "(4)\n" +
                "BABYHUG\n" +
                "(3)\n" +
                "ALLEN SOLLY JUNIOR\n" +
                "(3)\n" +
                "KOOKIE KIDS\n" +
                "(5)\n" +
                "BIBA";

        if(prPage.leftSide_text.isDisplayed() && text_leftSide.toLowerCase().contains(expectedBrand.toLowerCase())){
            System.out.println("Brands are visible in left side bar");
        }else{
            System.out.println("Brands are not visible in left side bar");
        }

        // 5. Click on any brand name
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", prPage.womenCategory);
        String brandName_input= "BABYHUG";
        int index2=0;
        for(int k=0;k<prPage.brandName.size();k++){
            System.out.println(prPage.brandName.get(k).getText());
            String text=prPage.brandName.get(k).getText();
            if(text.contains(brandName_input)){
                index2=k;
                System.out.println(index2);
                break;
            }
        }

        prPage.brandName.get(index2).click();
        // driver.findElement(By.xpath("(//div[@class='brands-name']//li//a)[5]")).click();

       // 6. Verify that user is navigated to brand page and brand products are displayed
        Thread.sleep(3000);
        String expected_Url_Babyhug="https://automationexercise.com/brand_products/Babyhug";
        Assert.assertEquals(driver.getCurrentUrl(),expected_Url_Babyhug);
        Assert.assertTrue(prPage.products_babyhug.isDisplayed());

        //7. On left side bar, click on any other brand link
        String brandName_input2= "BIBA";
        int index3=0;
        for(int i=0;i<prPage.brandName.size();i++){
            //System.out.println(prPage.brandName.get(i).getText());
            String text1=prPage.brandName.get(i).getText();
            if(text1.contains(brandName_input2)){
                index3=i;
                System.out.println(index3);
                break;
            }
        }
        Thread.sleep(2000);
        js.executeScript("arguments[0].scrollIntoView();", prPage.womenCategory);
        prPage.brandName.get(index3).click();

        // 8. Verify that user is navigated to that brand page and can see products
        Thread.sleep(3000);
        String expected_Url_Biba="https://automationexercise.com/brand_products/Biba";
        Assert.assertEquals(driver.getCurrentUrl(),expected_Url_Biba);
        prPage.getBiba_Products();

    }
    @Test(priority = 4)
    public void TC21_AddReviewOnProduct() throws InterruptedException {
        HashMap<String,String> data= ExcelReader1.getTestData("tc1");
        prPage=new ProductPage(driver);
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        lunchUrl();
        //3. Click on 'Products' button
        prPage.clickOnProductButton();
        //4. Verify user is navigated to ALL PRODUCTS page successfully
        String prpageurl= driver.getCurrentUrl();
        Assert.assertTrue(prpageurl.contains("https://automationexercise.com/products"));
        //5. Click on 'View Product' button
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", prPage.viewproduct1);
        prPage.viewproduct1.click();

        //6. Verify 'Write Your Review' is visible
        String textWriteReview=prPage.writereview.getText();
        Assert.assertTrue(textWriteReview.toLowerCase().contains("write your review"));
        //7. Enter name, email and review
        prPage.yourName.sendKeys(data.get("name"));
        prPage.emailAddress.sendKeys(data.get("Email"));
        prPage.addReview.sendKeys("Nice top");
        //8. Click 'Submit' button
        prPage.submitReview.click();
        //9. Verify success message 'Thank you for your review.
        String textThankMessage=prPage.thanksMessage.getText();
        Assert.assertTrue(textThankMessage.toLowerCase().contains("thank you for your review."));
    }

}
