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

    @TestRails(id = "3611")
    @Test(groups = {"interaction"})
    @Parameters({"brandname", "memberAccount", "password"})
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

    @TestRails(id = "3609")
    @Test(groups = {"smoke"})
    @Parameters({"memberAccount", "password"})
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

    @TestRails(id = "3608")
    @Test(groups = {"smoke"})
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

    @TestRails(id = "3607")
    @Test(groups = {"smoke"})
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

}

