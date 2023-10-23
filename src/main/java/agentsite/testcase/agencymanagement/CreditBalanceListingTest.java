package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.CreditBalanceListingPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DoubleUtils;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class CreditBalanceListingTest extends BaseCaseTest {
    @TestRails(id = "3607")
    @Test(groups = {"smoke_sat"})
    @Parameters({"memberAccount", "password"})
    public void Credit_Balance_Listing_3607(String memberAccount) {
        log("@title: Validate can update Credit Balance");
        log("Step 1. Navigate Agency Management > Credit Balance Listing");
        CreditBalanceListingPage page = agentHomePage.navigateCreditBalanceListingPage(environment.getSecurityCode());

        log("Step 2. Select an account and click on Edit icon");
        log("Step 3: Update valid Credit Given, Max Credit, Member Max Credit and click submit button");
        page.filter(memberAccount, "", "");
        page.updateCreditSetting(memberAccount, "1", "1", "1");

        log("Verify 1. Validate Username display in the result table");
        Assert.assertTrue(page.getRowIndexofUserName(memberAccount) != 0, "Failed! Cannot get login after searching");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3608")
    @Test(groups = {"smoke_sat"})
    @Parameters({"memberAccount", "password"})
    public void Credit_Balance_Listing_3608(String memberAccount) {
        log("@title: Validate can search by username");
        log("Step 1. Navigate Agency Management > Credit Balance Listing");
        CreditBalanceListingPage page = agentHomePage.navigateCreditBalanceListingPage(environment.getSecurityCode());

        log("Step 2. Enter Username and click Submit button");
        page.filter(memberAccount, "", "");

        log("Verify 1. Validate Username display in the result table");
        Assert.assertTrue(page.getRowIndexofUserName(memberAccount) != 0, "Failed! Cannot get login after searching");

        log("INFO: Executed completely");
    }
//    @TestRails(id = "3609")
//    @Test(groups = {"smoke"})
//    @Parameters({"memberAccount"})
//    public void Credit_Balance_Listing_3609(String memberAccount) {
//        log("@title: Validate can search by login ID");
//        log("Step 1. Navigate Agency Management > Sub User Listing");
//        CreditBalanceListingPage page = agentHomePage.navigateCreditBalanceListingPage(environment.getSecurityCode());
//
//        log("Step 2. Enter Login ID and click Submit button");
//        page.filter(memberAccount, "", "");
//
//        log("Verify 1. Validate Login ID display in the result table");
//        Assert.assertTrue(page.getRowIndexofUserName(memberAccount) != 0, "Failed! Cannot get login after searching");
//
//        log("INFO: Executed completely");
//    }

    @TestRails(id = "3609")
    @Test(groups = {"smoke_sat"})
    @Parameters({"memberAccount"})
    public void Credit_Balance_Listing_3609(String memberAccount) {
        log("@title: Validate can search by login ID");
        log("Step 1. Navigate Agency Management > Credit Balance Listing");
        CreditBalanceListingPage page = agentHomePage.navigateCreditBalanceListingPage(environment.getSecurityCode());

        log("Step 2. Enter Login ID and click Submit button");
        page.filter(memberAccount, "", "");

        log("Verify 1. Validate Login ID display in the result table");
        Assert.assertTrue(page.getRowIndexofUserName(memberAccount) != 0, "Failed! Cannot get login after searching");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3610")
    @Test(groups = {"regression_sat"})
    @Parameters({"memberAccount"})
    public void Credit_Balance_Listing_3610(String memberAccount) {
        log("@title: Validate can search by login ID");
        log("pre-condition: Log in successfully by SAD that belonging to Credit line");
        log("Step 1. Navigate Agency Management > Credit Balance Listing");
        CreditBalanceListingPage page = agentHomePage.navigateCreditBalanceListingPage(environment.getSecurityCode());
        log("Step 2. Select an account and click on Edit icon");
        page.filter(memberAccount,"", "");
        log("Step 3. Update valid Credit Given, Max Credit, Member Max Credit and click submit button");
        AccountInfo creditInfoBeforeUpdate = page.getCreditInfoAccount(memberAccount);
        double updateCreditGiven = creditInfoBeforeUpdate.getCreditGiven() - 1;
        double updateMaxCredit = creditInfoBeforeUpdate.getMaxCredit() - 1;
        double updateMemberMaxCredit = creditInfoBeforeUpdate.getMemberMaxCredit() - 1;
        try{
            page.updateCreditSetting(memberAccount, String.format("%.2f", updateCreditGiven) , String.format("%.2f",updateMaxCredit), String.format("%.2f",updateMemberMaxCredit));
            log("Verify 1. Validate Credit Given, Max Credit, Member Max Credit are updated");
            page.waitingLoadingSpinner();
            Assert.assertEquals(updateCreditGiven, page.getCreditInfoAccount(memberAccount).getCreditGiven(),"Credit Given is not updated");
            Assert.assertEquals(updateMaxCredit, page.getCreditInfoAccount(memberAccount).getMaxCredit(),"Max Credit is not updated");
            Assert.assertEquals(updateMemberMaxCredit, page.getCreditInfoAccount(memberAccount).getMemberMaxCredit(),"Member Max Credit is not updated");
        } finally {
            System.out.println("Credit Given, Max Credit, Member Max Credit update to old value");
            page.updateCreditSetting(memberAccount, String.format("%.2f",creditInfoBeforeUpdate.getCreditGiven()) , String.format("%.2f",creditInfoBeforeUpdate.getMaxCredit()),String.format("%.2f",creditInfoBeforeUpdate.getMemberMaxCredit()));
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "3611")
    @Test(groups = {"interaction_sat"})
    @Parameters({"memberAccount", "password"})
    public void Credit_Balance_Listing_3611(String memberAccount, String password) throws Exception {
        log("@title: Validate player balance is correct after update credit in agent");
        log("Step 1. Navigate Agency Management > Transfer");
        CreditBalanceListingPage page = agentHomePage.navigateCreditBalanceListingPage(environment.getSecurityCode());
        page.filter(memberAccount, "", "");
        AccountInfo creditInfoBeforeUpdate = page.getCreditInfoAccount(memberAccount);
        double updateCredit = creditInfoBeforeUpdate.getCreditGiven() - 1;

        log("Step 2 Transfer an amount for a player");
        log("Step 3. Update valid Credit Given");
        page.updateCreditSetting(memberAccount, String.format("%.2f", updateCredit), "", "");

        AccountInfo creditInfoAftereUpdate = page.getCreditInfoAccount(memberAccount);
        double userBalanceinAgent = creditInfoAftereUpdate.getAvailableBalance();

        log("Step 3 Login member site");
        loginMember(memberAccount, password, true, "", "", true);
        String accountBalanceAPIOriginal = String.format("%.2f", Double.parseDouble(BetUtils.getUserBalance().getBalance()));
        String accountBalanceAPIRoundUp = String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(Double.parseDouble(BetUtils.getUserBalance().getBalance())));
        String accountBalanceUI = memberHomePage.header.getUserBalance().getBalance();

        log("Verify Verify Player balance is correct");
        Assert.assertEquals(accountBalanceAPIOriginal, String.format("%.2f", userBalanceinAgent), "Failed! Balance in member site get by API does not match with agent site");
        Assert.assertEquals(accountBalanceUI, accountBalanceAPIRoundUp, "Failed! Balance in member site get in UI not match with API");
        log("INFO: Executed completely");
    }


}

