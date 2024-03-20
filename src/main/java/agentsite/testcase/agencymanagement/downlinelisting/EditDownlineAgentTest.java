package agentsite.testcase.agencymanagement.downlinelisting;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.CreateDownLineAgentPage;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.AGConstant.CREDIT_LIMIT_ERROR_MSG;

public class EditDownlineAgentTest extends BaseCaseTest {


    /**
     * @title: Validate Cannot update if Max Player Credit exceed the limit
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any agent
     * 3. Input Max player Credit greater than the limit
     * @expect: 1. Verify Message "Max Player Credit is invalid" display
     */
//    @TestRails(id = "696")
//    @Test(groups = {"smoke_credit","isa"})
//    @Parameters({"currency"})
//    public void Agent_AM_Downline_Listing_Edit_Agent_696(String currency) throws Exception {
//        log("@title: Validate cannot update if Max Player Credit exceed the limit");
//        log("Step 1. Navigate Agency Management > Downline Listing");
//        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
//        String userID = ProfileUtils.getProfile().getUserID();
//        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
//        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname);
//        String loginID = listAccount.get(0).getUserCode();
//
//        log("Step 2. Click on Edit icon of any agent");
//        page.searchDownline(loginID, "", "Agent");
//        EditDownLinePage editDownLinePage =  page.clickEditIcon(loginID);
////        page.confirmSecurityCode(environment.getSecurityCode());
//
//        log("Step 3. Input Max player Credit greater than the limit");
//        String maxPlayerCreditLitmit = String.format("%d", editDownLinePage.creditBalanceInforSection.getMaxPlayerLitmitCredit(currency) + 1);
//        editDownLinePage.updateCashBalance(maxPlayerCreditLitmit,true);
//
//        log("Verify 1. Verify Message \"Max Player Credit is invalid\" display");
//        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.DownlineListing.MSG_INVALID_MAX_PLAYER_CREDIT, "FAILED! Incorrect max player credit is invalid");
//
//        log("INFO: Executed completely");
//    }
    @TestRails(id = "23723")
    @Test(groups = {"smoke_newui"})
    @Parameters({"currency"})
    public void Agent_AM_Downline_Listing_Edit_Agent_23723(String currency) throws Exception {
        log("@title: Validate there Cannot update if Max Player Credit exceed the limit");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any player");
        page.searchDownline(loginID, "", "");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        log("Step 3. Input value exceed max player credit and observe error message");
        double maxCreditLimit = editDownLinePage.creditBalanceInforSection.getCreditLimit(currency) + 1;
        editDownLinePage.creditBalanceInforSection.updateCreditBalance(maxCreditLimit);
        log("Verify 2. 'Credit Limit is invalid.' message displays");
        Assert.assertEquals(editDownLinePage.lblErrorMsg.getText(), CREDIT_LIMIT_ERROR_MSG, String.format("FAILED! Error message credit limit exceed does not appear expected %s actual %s", CREDIT_LIMIT_ERROR_MSG, editDownLinePage.lblErrorMsg.getText()));
        log("INFO: Executed completely");
    }
    @TestRails(id = "23724")
    @Test(groups = {"smoke_creditcash"})
    @Parameters({"password", "username"})
    public void Agent_AM_Downline_Listing_Edit_Agent_23724(String password, String username) throws Exception {
        log("@title: Validate Max Player Credit setting display correctly when create agent");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstAccount = DownLineListingUtils.getAllDownLineUsers(_brandname, username, userID);
        String loginID = lstAccount.get(0).getLoginID();

        log("Step 2. Click on Edit icon of any agent");
        page.searchDownline(loginID, "", "");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        log("Step 3. Get Max Player Credit setting value");
        String maxPlayerCreditLitmit = editDownLinePage.cashBalanceInforSection.getMaxPlayerCreditLimitSetting();
        log("Step 4. Login agent with the agent in step 2");
        agentHomePage.logout();
        loginAgent(sosAgentURL, agentSecurityCodeURL, loginID, password, environment.getSecurityCode());

        log("Step 5. Select Agency Management > Create Downline agent");
        CreateDownLineAgentPage createAgentPage = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());

        log("Verify 1. Verify Max Player Credit display correctly as setting in Max Player Credit limit section step 3");
        Assert.assertTrue(Double.valueOf(maxPlayerCreditLitmit.replace(",","")).equals(createAgentPage.cashBalanceInforSection.getMaxPlayerLitmitCredit()), "FAILED! Max player credit not match with the setting");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3663")
    @Test(groups = {"regression_po"})
    @Parameters({"controlBlockingAccount","downlineAccount", "passwordNonePO"})
    public void Agent_AM_Downline_Listing_Edit_Agent_3663(String controlBlockingAccount, String downlineAccount,String passwordNonePO) throws Exception {
        log("@title: Validate Portal can update products for an agent level and only the active product display for updated agent");
        log("Precondition: Log in successfully by  PO");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Drilldown to an agent level SMA and active the below product: Exchange, Exchange Game");
        page.searchDownline(controlBlockingAccount, "", "");
        EditDownLinePage editDownLinePage= page.downlineListing.clickEditIcon(controlBlockingAccount);
        editDownLinePage.confirmSecurityCode(environment.getSecurityCode());
        final Map<String, Boolean> PRODUCTS1 = new HashMap<String, Boolean>() {
            {
                put("Exchange", true);
                put("Exchange Games", true);
                put("Evolution",false);
                put("CMD Sportsbook",false);
                put("Supernowa Casino",false);
                put("Live Dealer European",false);
                put("Live Dealer Asian",false);
                put("Pragmatic",false);
                put("Game Hall",false);
                put("ViVo",false);
                put("Ion",false);
                put("Sabong",false);
            }};
        editDownLinePage.updateProducts(PRODUCTS1);
        page.logout();
        log("Step 3. Login SMA level");
        loginAgent(controlBlockingAccount,passwordNonePO,true);

        log("Step 4. Navigate to the Downline listing and select any downline account");
        page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(downlineAccount, "", "Agent");
        editDownLinePage= page.downlineListing.clickEditIcon(downlineAccount);
        editDownLinePage.confirmSecurityCode(environment.getSecurityCode());
        List<String> lstProduct = editDownLinePage.getProductsTab();

        log("Verify: Verify only the active products tab displayed");
        Assert.assertTrue(lstProduct.containsAll(Arrays.asList("Exchange", "Exchange Games")),"Failed! Active product list is incorrect");

        log("INFO: Executed completely");
    }


}

