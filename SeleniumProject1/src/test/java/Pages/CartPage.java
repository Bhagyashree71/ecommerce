package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    public CartPage(WebDriver driver) {
        //call the static init method
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/view_cart']//parent::li")
    public WebElement cartbtn;
    @FindBy(xpath = "//h2[text()='Subscription']")
    public WebElement textsubscription;

    @FindBy(xpath = " //input[@id='susbscribe_email']")
    public WebElement emailsub;
    @FindBy(xpath = "//button[@id='subscribe']")
    public WebElement btnemailsub;
    @FindBy(xpath = " //div[text()='You have been successfully subscribed!']")
    public WebElement message;
    @FindBy(xpath = "//img[@src='/get_product_picture/1']")
    public WebElement firstproduct;
    @FindBy(xpath = "(//a[@data-product-id='1'])[1]")
    public WebElement firstaddtocart;
    @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
    public WebElement btncontinue;
    @FindBy(xpath = "//img[@src='/get_product_picture/2']")
    public WebElement secondproduct;
    @FindBy(xpath = "(//a[@data-product-id='2'])[2]")
    public WebElement secondaddtocart;

    @FindBy(xpath = "//u[text()='View Cart']//parent::a")
    public WebElement viewcart;
    @FindBy(xpath = "//li[text()='Shopping Cart']")
    public WebElement cartPage;
    @FindBy(xpath = "//h2[text()='recommended items']")
    public WebElement recommendedItems;
    @FindBy(xpath = "//div[@id='recommended-item-carousel']")
    public WebElement rec_item;
    @FindBy(xpath = "(//a[@data-product-id='1'])[1]")
    public WebElement addToCart1_recommended;
    @FindBy(xpath = "//table[@id='cart_info_table']//tr")
    public List<WebElement> rowCart;
    @FindBy(xpath = "//img[@src='get_product_picture/1']")
    public WebElement pr_img1;
    @FindBy(xpath = "//table[@id='cart_info_table']//tbody//tr")
    public List<WebElement> cartTable;
    @FindBy(xpath = "//button[@class='btn btn-default cart']")
    public WebElement addToCartButton;

    @FindBy(xpath = "//input[@id='quantity']")
    public WebElement quantityBox;

    public void clickCart() {
        cartbtn.click();
    }
    public String getTextSub() {
        return textsubscription.getText();
    }

    public void setEmailSub(String strEmail) {
        emailsub.sendKeys(strEmail);
    }

    public void clickSubscription() {
        btnemailsub.click();
    }

    public String getMessage() {
        return message.getText();
    }
    public void clickContinueShopping() {
        btncontinue.click();
    }
    public void clickViewCartButton() {
        viewcart.click();
    }

    public String getText_recommendedItems() {
        return recommendedItems.getText();
    }

    public void click_addToCart1() {
        addToCart1_recommended.click();
    }

    public int getRowCount_Cart() {
        return rowCart.size();
    }


}

