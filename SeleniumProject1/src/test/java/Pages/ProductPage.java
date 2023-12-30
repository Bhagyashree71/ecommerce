package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ProductPage {
    public ProductPage(WebDriver driver) {
        //call the static init method
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a[@href='/products']")
    public WebElement productbtn;
    @FindBy(xpath = "(//a[text()='View Product'])[1]")
    public WebElement viewproduct1;
    @FindBy(xpath = " //div[@class='product-information']")
    public WebElement prinfo;
    @FindBy(xpath = "//input[@id='search_product']")
    public WebElement prsearch;
    @FindBy(xpath = "//button[@id='submit_search']")
    public WebElement searchbtn;
    //h2[text()='Searched Products']
    @FindBy(xpath = "//h2[text()='Searched Products']")
    public WebElement textsearch;
    @FindBy(xpath="//div[@class='features_items']")
    public List<WebElement> searchelement;

    @FindBy(xpath="//a[text()='Write Your Review']")
    public WebElement writereview;
    @FindBy(xpath="//input[@id='name']")
    public WebElement yourName;
    @FindBy(xpath="//input[@id='email']")
    public WebElement emailAddress;
    @FindBy(xpath="//textarea[@name='review']")
    public WebElement addReview;

    @FindBy(xpath="//button[@id='button-review']")
    public WebElement submitReview;
    @FindBy(xpath="//span[text()='Thank you for your review.']")
    public WebElement thanksMessage;
    @FindBy(xpath = "//div[@class='left-sidebar']")
    public WebElement leftSide_text;
    @FindBy(xpath = "(//span[@class='badge pull-right'])[1]")
    public WebElement womenCategory;
    @FindBy(xpath = "(//span[@class='badge pull-right'])[2]")
    public WebElement menCategory;
    @FindBy(xpath="//div[@id='accordian']")
    public List<WebElement> categories;
    @FindBy(xpath="//div[@id='Women']//li//a")
    public List<WebElement> categories_Women;
    @FindBy(xpath = "//h2[text()='Women - Tops Products']")
    public WebElement text_WomenTopsProduct;
    @FindBy(xpath="//div[@id='Men']//li//a")
    public List<WebElement> categories_Men;
    @FindBy(xpath="//ul[@class='nav nav-pills nav-stacked']//li//a")
    public List<WebElement> brandName;
    @FindBy(xpath="//div[@class='col-sm-9 padding-right']")
    public WebElement products_babyhug;
    @FindBy(xpath="//div[@class='product-image-wrapper']")
    public List<WebElement> biba_Products;
    @FindBy(xpath="//div[@class='product-image-wrapper']")
    public List<WebElement> search_products;



    public void clickOnProductButton(){
        productbtn.click();
    }
    public String getPrInfo(){
        return    prinfo.getText();
    }
    public void searchProduct(String strProduct){
        prsearch.sendKeys(strProduct);
    }
    public void clickSearchPr(){
        searchbtn.click();
    }
    public String getTextSearchpr(){
        return    textsearch.getText();
    }
    public String getText_leftside(){
        return    leftSide_text.getText();
    }
    public  List<WebElement> getCategories(){
        for(WebElement category:categories){
            System.out.println(category.getText());
        }
        return categories;
    }
    String WomenCategory_input="TOPS";
    int index=0;
    public  int getCategories_Women(){
        for(int i=0;i<=categories_Women.size();i++){
            System.out.println(categories_Women.get(i).getText());
            if(categories_Women.get(i).getText().equals(WomenCategory_input)){
                index=i;
                System.out.println(index);
                break;
            }
        }

        return index;
    }
    public void click_WomenCategory(){
        womenCategory.click();
    }
    public String getTextWomen_TopsProduct(){
        return    text_WomenTopsProduct.getText();
    }
    public List<WebElement> getBiba_Products(){
        System.out.println(biba_Products.size());
        for(WebElement bibaProduct:biba_Products){
            System.out.println(bibaProduct.getText());
            Assert.assertTrue(bibaProduct.isDisplayed(),"biba products are visible");
        }
        return biba_Products;
    }
    public List<WebElement> getSearchResult(){
        System.out.println(search_products.size());
        for(WebElement searchProduct : search_products){
            System.out.println(searchProduct.getText());
        }
        return search_products;
    }
}


