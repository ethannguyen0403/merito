package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.AccountBalancePage;
import agentsite.pages.agentmanagement.TransferPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import membersite.objects.AccountBalance;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class TransferTest extends BaseCaseTest {

    @TestRails(id = "3617")
    @Test(groups = {"interaction"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Transfer_3617(String memberAccount, String password) throws Exception {
        log("@title: Verify available balance of PL in member site is updated when agent transfer successfully");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getDownLineUsers(userID, "PL", _brandname);
        String userName = DownLineListingUtils.getAccountInfoInList(lstUsers, memberAccount).getUserCode();

        log("Step 1. Navigate Agency Management > Transfer");
        TransferPage page = agentHomePage.navigateTransferPage(environment.getSecurityCode());
        AccountInfo accountInfoBeforeTransfer = page.getTransferInfo(userName);
        double amount = page.defineAmountBasedOnTransferableBalance(0.1, accountInfoBeforeTransfer.getTransferableBalance());

        log("Step 2 Transfer an amount " + amount + " for a player" + userName);
        page.transfer(userName, String.format("%.2f", amount));
        AccountInfo accountInfoAfterTransfer = page.getTransferInfo(userName);
        String memberBalanceExpectedAfterTransfer = String.format("%,.2f", accountInfoAfterTransfer.getCreditGiven() + accountInfoAfterTransfer.getCreditUsed());
        String memberExposureExpectedAfterTransfer = String.format("%,.2f", accountInfoAfterTransfer.getTotalPlayerOutstanding());

        log("Step 3 Login member site");
        loginMember(memberAccount, password);
        AccountBalance playerAccountBalance = memberHomePage.header.getUserBalance();

        log("Verify Verify Player balance is correct ");
        Assert.assertEquals(playerAccountBalance.getBalance(), memberBalanceExpectedAfterTransfer, " FAILD! Player available balance is incorrect after agent transfer " + String.format("%.2f", amount));
        Assert.assertEquals(playerAccountBalance.getExposure(), memberExposureExpectedAfterTransfer, " FAILD! Player available balance is incorrect after agent transfer " + String.format("%.2f", amount));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3618")
    @Test(groups = {"interaction"})
    @Parameters({"memberAccount"})
    public void Agent_AM_Transfer_3618( String memberAccount) {
        log("@title: Verify Balance of login account is updated after transfer");
        log("Step 1. Navigate Agency Management > Transfer");
        AccountInfo accountLoginInfo = ProfileUtils.getProfile();
        String currencyCode = accountLoginInfo.getCurrencyCode();
        List<AccountInfo> lstUsers = DownLineListingUtils.getDownLineUsers(accountLoginInfo.getUserID(), "PL", _brandname);
        String userName = DownLineListingUtils.getAccountInfoInList(lstUsers, memberAccount).getUserCode();

        AccountBalancePage accountBalancePage = agentHomePage.navigateAccountBalance();
        AccountInfo accountLoginInfoExpected = accountBalancePage.getInfoCreditLoginBalance(currencyCode);

        TransferPage page = accountBalancePage.navigateTransferPage(environment.getSecurityCode());
        AccountInfo accountInfoBeforeTransfer = page.getTransferInfo(userName);

        double amount = page.defineAmountBasedOnTransferableBalance(0.1, accountInfoBeforeTransfer.getTransferableBalance());
        accountLoginInfoExpected.setDownlineBalance(accountLoginInfoExpected.getDownlineBalance() - amount);
        accountLoginInfoExpected.setYesterdayDownlineBalance(accountLoginInfoExpected.getYesterdayDownlineBalance() - amount);

        log("Step 2 Transfer an amount " + amount + " for downline" + userName);
        page.transfer(userName, String.format("%.2f", amount));

        log("Step 3 Active account balance page ");
        accountBalancePage = page.navigateAccountBalance();
        AccountInfo accountLoginInfoActual = accountBalancePage.getInfoCreditLoginBalance(currencyCode);

        log("Verify: balance in account balance page is update after transfer: Downline Balance,Total Balance, Transferable balance ");
        Assert.assertEquals(accountLoginInfoActual.getDownlineBalance(), accountLoginInfoExpected.getDownlineBalance(), "FAILED! Downline Balance is incorrect");
        Assert.assertEquals(accountLoginInfoActual.getYesterdayDownlineBalance(), accountLoginInfoExpected.getYesterdayDownlineBalance(), "FAILED! Yesterday Downline Balance is incorrect");
        Assert.assertEquals(accountLoginInfoActual.getTotalBalance(), accountLoginInfoExpected.getTotalBalance(), "FAILED! Total Balance is incorrect");
        Assert.assertEquals(accountLoginInfoActual.getTransferableBalance(), accountLoginInfoExpected.getTransferableBalance(), "FAILED! Transfer Balance is incorrect");
        Assert.assertEquals(accountLoginInfoActual.getMyOutstanding(), accountLoginInfoExpected.getMyOutstanding(), "FAILED! My Outstanding is incorrect");
        Assert.assertEquals(accountLoginInfoActual.getTotalOustanding(), accountLoginInfoExpected.getTotalOustanding(), "FAILED! Total Outstanding is incorrect");
        Assert.assertEquals(accountLoginInfoActual.getTodayWinLoss(), accountLoginInfoExpected.getTodayWinLoss(), "FAILED! Today Win Loss is incorrect");
        Assert.assertEquals(accountLoginInfoActual.getYesterdayWinLoss(), accountLoginInfoExpected.getYesterdayWinLoss(), "FAILED! Yesterday Win Loss is incorrect");
        Assert.assertEquals(accountLoginInfoActual.getCreditGiven(), accountLoginInfoExpected.getCreditGiven(), "FAILED! My Credit is incorrect");

        log("INFO: Executed completely");
    }
}

