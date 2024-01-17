package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {
    WebDriver driver;
    public ProductPage(WebDriver driver) {
        //call the static init method
        //this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//span[text()='Products']")
    public WebElement textProducts;
    @FindBy(xpath = "//div[@class='inventory_item']")
    public List<WebElement> products_items;
    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']")
    public WebElement first_product;
    @FindBy(xpath = "//div[@class='inventory_details_desc_container']")
    public WebElement first_productDetail;
    @FindBy(xpath = "//button[@id='back-to-products']")
    public WebElement buttonBackToProducts;
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    public WebElement cart_sign_button;
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    public WebElement addToCart_1;
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bike-light']")
    public WebElement addToCart_2;
    @FindBy(xpath = "//select[@data-test='product_sort_container']")
    public WebElement filter_option;
    @FindBy(xpath = " //select[@data-test='product_sort_container']//option")
    public List<WebElement> filterOptions;
    @FindBy(xpath = "//div[@class='inventory_item_name ']")
    public List<WebElement> productNames;
    @FindBy(xpath = " //div[@class='inventory_item_price']")
    public List<WebElement> productPrices;


    public String getTextProducts(){
        return textProducts.getText();
    }
    public void clickFirstProduct(){
        first_product.click();
    }
    public List<WebElement> getProducts(){
        System.out.println(products_items.size());
        for(WebElement product_item : products_items){
            System.out.println(product_item.getText());
        }
        return products_items;
    }
    public String getDetailFirstProduct(){
        return first_productDetail.getText();
    }
    public void clickOnBackToProduct(){
        buttonBackToProducts.click();
    }
     int i=0;
    public void clickOnAddToCart_1(){
        addToCart_1.click();
        i++;
    }
    public void clickOnAddToCart_2(){
        addToCart_2.click();
        i++;
    }
    public int getNumberOf_click(){
        return i;
    }
    public void clickCartSignButton(){
        cart_sign_button.click();
    }
    public void clickFilterButton(){
        filter_option.click();
    }

}
