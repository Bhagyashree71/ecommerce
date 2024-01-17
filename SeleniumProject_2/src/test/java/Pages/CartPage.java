package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CartPage {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        //call the static init method
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='cart_item']")
    public List<WebElement> numberOfProductsIn_Cart;
    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> item_Prices;
    @FindBy(xpath = "//div[text()='Sauce Labs Backpack']")
    public WebElement first_product;
    @FindBy(xpath = "//div[@class='cart_quantity']")
    public List<WebElement> quantity_boxes;
    @FindBy(xpath = "//button[text()='Remove']")
    public List<WebElement> Remove_Buttons;



    public void getCartItemPrices(){

        for(int i=0;i<item_Prices.size();i++){
            if(item_Prices.get(i).getText().contains("$29.99")){
                System.out.println("price of product1 is visible in cart");
            }else if (item_Prices.get(i).getText().contains("$9.99")){
                System.out.println("price of product2 is visible in cart");
            }else{
                System.out.println("price is not displayed");
            }
        }
    }

    public void getQuantity(){
        for(int j=0;j<quantity_boxes.size();j++){
            if(item_Prices.get(j).getText().contains("1")){
                System.out.println("Quantity of product1 is visible in cart");
            }else if (item_Prices.get(j).getText().contains("1")){
                System.out.println("Quantity of product2 is visible in cart");
            }else{
                System.out.println("Quantity is not displayed");
            }
        }

    }
    public void click_RemoveButtons(){
       for(WebElement Remove_Button:Remove_Buttons){
           Remove_Button.click();

        }
    }
}
