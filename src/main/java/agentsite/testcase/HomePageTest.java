package agentsite.testcase;

import agentsite.pages.AccountBalancePage;
import baseTest.BaseCaseTest;
import common.AGConstant;
import common.AGConstant.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class HomePageTest extends BaseCaseTest {
    @TestRails(id = "3454")
    @Test(groups = {"regression"})
    public void Home_Page_3454(){
        log("@title: Validate menu list item display correctly");
        log("Step 1: Click on menu icon");
        agentHomePage.header.ddlMenu.moveAndHoverOnControl();
        log("Verify 1: The list sub menu display:\n" +
                "\n" +
                "Password\n" +
                "Security Code  \n" +
                "OTP");
        agentHomePage.header.verifyListSubMenuDisplay();
    }
    @TestRails(id = "3458")
    @Test(groups = {"regression"})
    public void Home_Page_3458(){
        log("@title: Validate Account Balance page display when click on Home icon");
        log("Step 1: Click on home icon");
        AccountBalancePage page = agentHomePage.clickHomeIcon();
        log("Verify 1:Account Balance page display with the title\n" +
                "example: Senior Admin Balance Info: SAI02\n" +
                "Available Balance\n" +
                "My Credit\n" +
                "My Outstanding\n" +
                "Total Outstanding\n" +
                "Today Win Loss\n" +
                "Yesterday Win Loss\n" +
                "Total Member Available Balance\n" +
                "Total Member Active/Closed/Suspended/Inactive");
        page.accountBalance.verifyTitleDisplay();
    }
}
