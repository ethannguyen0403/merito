package agentsite.testcase.agencymanagement;

import agentsite.pages.agentmanagement.SubUserListingPage;

import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.testng.Assert;import agentsite.pages.HomePage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class CreditBalanceListing extends BaseCaseTest {
      /**
     * @title: Can create Sub user successfully with full permissions
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Sub User Listing
     *         2. Click on Create button
     *         3. Input all info and check all permission
     *         4. Click Submit
     * @expect: 1. Verify Sub user is created with all permission
     *          2. Verify can login agent with new sub account
     */
    @Test (groups = {"smoke"})
    @Parameters("password")
    public void Agent_AM_Credit_Balance_Listing_0006(String password) throws Exception {
        log("@title: Verify can search downline by Login ID");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        String pwDecrypt = StringUtils.decrypt(password);

        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>(){
            {
                put("Create Account", true);
                put("Update Account",true);
                put("View Account",true);
                put("Report",true);
                put("Transfer & Deposit/Withdraw",true);
                put("Account Balance", true);        }
        };

        log("Step 2. Click on Create button");
        log("Step 3. Input all info and check all permission");
        log("Step 4. Click Submit");
        String subUsername = page.createSubUser("", pwDecrypt, "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);
        log("Verify 1. Verify Sub user is created with all permission");
        Assert.assertTrue(page.verifySubUserInfo(subUsername, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Verify can login agent with new sub account");
        loginNewAccount(sosAgentURL, agentNewAccURL, subUsername, password, "112233");
        Assert.assertEquals(agentHomePage.leftMenu.lblLoginID.getText(), subUsername, "Failed!, Login ID lable display incorrect");
        log("INFO: Executed completely");
    }
}

