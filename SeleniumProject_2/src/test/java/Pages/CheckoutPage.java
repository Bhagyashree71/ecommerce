package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ExcelReader1;

import java.util.HashMap;
import java.util.List;

public class CheckoutPage {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        //call the static init method
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[@id='checkout']")
    public WebElement checkoutButton;
    @FindBy(xpath = "//input[@id='first-name']")
    public WebElement firstName;
    @FindBy(xpath = " //input[@id='last-name']")
    public WebElement lastName;
    @FindBy(xpath = " //input[@id='postal-code']")
    public WebElement postalCode;
    @FindBy(xpath = "//input[@id='continue']")
    public WebElement continue_button;
    @FindBy(xpath = " //div[@class='cart_item']")
    public List<WebElement> checkout_items;
    @FindBy(xpath = "//div[@class='summary_info']")
    public WebElement product_summary;
    @FindBy(xpath = "//button[@id='finish']")
    public WebElement finishButton;
    @FindBy(xpath = " //button[@id='back-to-products']")
    public WebElement BackHomeButton;

    @FindBy(xpath = "//h2[text()='Thank you for your order!']")
    public WebElement message_Thankyou;
    @FindBy(xpath = "//h3[text()='Error: First Name is required']")
    public WebElement error_messageFirstName;
    @FindBy(xpath = " //h3[text()='Error: Last Name is required']")
    public WebElement error_messageLastName;
    @FindBy(xpath = "//h3[text()='Error: Postal Code is required']")
    public WebElement error_messagePostalCode;
    @FindBy(xpath = "//button[@id='cancel']")
    public WebElement cancelButton;

    public void clickCheckout(){
        checkoutButton.click();
    }
    public void enterFirstName(String firstname){
        firstName.sendKeys(firstname);
    }
    public void enterLastName(String lastname){
        lastName.sendKeys(lastname);
    }
    public void enterPostalCode(String postalcode){
        postalCode.sendKeys(postalcode);
    }
    public void enterCheckoutDetails(String firstname,String lastname,String postalcode){
        this.enterFirstName(firstname);
        this.enterLastName(lastname);
        this.enterPostalCode(postalcode);
    }
    public void clickContinueButton(){
        continue_button.click();
    }
    public void clickFinishButton(){
        finishButton.click();
    }
    public void clickBackHomeButton(){
        BackHomeButton.click();
    }
    public String get_ErrorMessageFirstName(){
        return error_messageFirstName.getText();
    }
    public String get_ErrorMessageLastName(){
        return error_messageLastName.getText();
    }
    public String get_ErrorMessagePostalCode(){
        return error_messagePostalCode.getText();
    }
    public void clickCancelButton(){
        cancelButton.click();
    }

}
