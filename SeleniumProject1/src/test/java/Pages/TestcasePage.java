package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestcasePage {

    public TestcasePage(WebDriver driver) {
        //call the static init method
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = " //a[@href='/test_cases']//parent::li")
    public WebElement testcase;
    public void clickTestcase(){
        testcase.click();
    }
}
