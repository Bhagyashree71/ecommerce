package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {
    public OrderPage(WebDriver driver) {
        //call the static init method
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a[text()='Proceed To Checkout']")
    public WebElement proceedToCheckout;
    @FindBy(xpath = "//u[text()='Register / Login']")
    public WebElement register_LoginCheckout;

    @FindBy(xpath = "//h2[text()='Address Details']")
    public WebElement address_detail;

    @FindBy(xpath = "//h2[text()='Review Your Order']")
    public WebElement reviewOrder;
    @FindBy(xpath = "//textarea[@name='message']")
    public WebElement commentTextArea;
    @FindBy(xpath = "//a[text()='Place Order']")
    public WebElement placeOrder;
    @FindBy(xpath = " //input[@data-qa='name-on-card']")
    public WebElement nameOnCard;
    @FindBy(xpath = "//input[@data-qa='card-number']")
    public WebElement cardNumber;
    @FindBy(xpath = "//input[@data-qa='cvc']")
    public WebElement cvc;
    @FindBy(xpath = "//input[@data-qa='expiry-month']")
    public WebElement expiryMonth;
    @FindBy(xpath = "//input[@data-qa='expiry-year']")
    public WebElement expiryYear;
    @FindBy(xpath = "//button[@data-qa='pay-button']")
    public WebElement payConfirmButton;

    @FindBy(xpath = "//*[@id='success_message']")
    public WebElement successMessage;
    public void clickProceedToCheckout(){
        proceedToCheckout.click();
    }
    public void clickRegister_SignUpButton(){
        register_LoginCheckout.click();
    }
}
