package tests;

import Pages.TestcasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestcaseTest extends BaseTest {

    TestcasePage testcasePage;

    @Test
    public void TC7_VerifyTestcasePage() {
        testcasePage=new TestcasePage(driver);
        SoftAssert sa=new SoftAssert();
        //1. Launch browser //2. Navigate to url 'http://automationexercise.com'
        lunchUrl();
        // 3. Verify that home page is visible successfully
        String homeTitle= driver.getTitle();
        Assert.assertEquals(homeTitle,"Automation Exercise","home page is visible successfully");
        //4. Click on 'Test Cases' button
        testcasePage.clickTestcase();
        //5. Verify user is navigated to test cases page successfully
        String testUrl= driver.getCurrentUrl();
        sa.assertEquals(testUrl,"https://automationexercise.com/test_cases","User is navigated to testcase page");
    }
}
