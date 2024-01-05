package agentsite.testcase.proteus;

import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class DownlineListingTest extends BaseCaseTest {
    @TestRails(id = "4059")
    @Test(groups = {"ps38"})
    @Parameters({"memberAccount","password"})
    public void PS38_Agent_TC4059(String memberAccount,String password) throws Exception {
        log("@title: Validate PS38 product does not display in member site if the product is inactive");
        log("Precondition: The agent account that has the currency is supported PS38 product");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("The agent the not active PS38 product for his downline");
        page.searchDownline(memberAccount,"","");

        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);
        editDownLinePage.inActiveProduct("PS38",true);

        log("Step 1. Login Member site the account under agent in precondition");
        log("Step 2. Observe product name");
        loginMember(memberAccount, password);

        log("Verify 1: Verify the product PS38 does not display");
        Assert.assertFalse(memberHomePage.isProductDisplayed("PS38"), "ERROR! PS38 product should not display as expected");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4060")
    @Test(groups = {"ps38"})
    @Parameters({"memberAccount","password"})
    public void PS38_Agent_TC4060(String memberAccount,String password) throws Exception {
        log("@title: Validate PS38 product displays in member site if the product is active");
        log("Precondition: The agent account that has the currency is supported PS38 product");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("The agent active PS38 product for his downline agent/ player");
        page.searchDownline(memberAccount,"","");
        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);
        editDownLinePage.activeProduct("PS38",true);

        log("Step 1. Login Member site the account under agent in precondition");
        log("Step 2. Observe product name");
        loginMember(memberAccount, password);

        log("Verify 1: Verify the product PS38 displays");
        Assert.assertTrue(memberHomePage.isProductDisplayed("PS38"), "ERROR! PS38 product should display as expected");

        log("INFO: Executed completely");
    }

}

