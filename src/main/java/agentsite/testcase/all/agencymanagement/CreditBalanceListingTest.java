package agentsite.testcase.all.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.all.agentmanagement.CreditBalanceListingPage;
import agentsite.pages.all.agentmanagement.TransferPage;
import baseTest.BaseCaseMerito;
import com.paltech.utils.DoubleUtils;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreditBalanceListingTest extends BaseCaseMerito {

    @Test (groups = {"interaction"})
    @Parameters({"brandname","memberAccount","password"})
    public void Agent_AM_Credit_Balance_Listing_0006(String brandName,String memberAccount,String password) throws Exception {
        log("@title: Verify player balance is correct after update credit in agent");
        log("Step 1. Navigate Agency Management > Transfer");
        CreditBalanceListingPage page = agentHomePage.navigateCreditBalanceListingPage(environment.getSecurityCode());
        page.filter(memberAccount,"","");
        AccountInfo creditInfoBeforeUpdate = page.getCreditInfoAccount(memberAccount);
        double updateCredit = creditInfoBeforeUpdate.getCreditGiven() -1;

        log("Step 2 Transfer an amount for a player");
        log("Step 3. Update valid Credit Given");
        page.updateCreditSetting(memberAccount,String.format("%.2f",updateCredit),"","");

        AccountInfo creditInfoAftereUpdate = page.getCreditInfoAccount(memberAccount);
        double userBalanceinAgent =creditInfoAftereUpdate.getAvailableBalance();

        log("Step 3 Login member site");
        loginMember(memberAccount,password,true,"","",true);
        String accountBalanceAPIOriginal = String.format("%.2f", Double.parseDouble(BetUtils.getUserBalance().getBalance()));
        String accountBalanceAPIRoundUp = String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(Double.parseDouble(BetUtils.getUserBalance().getBalance())));
        String accountBalanceUI = memberHomePage.getPlayerBalance(brandName).getBalance();

        log("Verify Verify Player balance is correct");
        Assert.assertEquals(accountBalanceAPIOriginal,String.format("%.2f",userBalanceinAgent),"Failed! Balance in member site get by API does not match with agent site");
        Assert.assertEquals(accountBalanceUI,accountBalanceAPIRoundUp,"Failed! Balance in member site get in UI not match with API");
        log("INFO: Executed completely");
    }
}

