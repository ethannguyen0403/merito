package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.AccountBalancePage;
import agentsite.pages.agentmanagement.TransferPage;
import agentsite.pages.components.ConfirmPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import membersite.objects.AccountBalance;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.AgencyManagement.Transfer.*;
import static common.AGConstant.HomePage.TRANSFER;
import static common.AGConstant.LBL_USERNAME_PLACE_HOLDER_SAT;

public class TransferTest extends BaseCaseTest {
    @TestRails(id = "3612")
    @Test(groups = {"http_request"})
    public void Agent_AM_Transfer_3612(){
        log("@title: Verify available balance of PL in member site is updated when agent transfer successfully");
        log("Step 1. Navigate Agency Management > Transfer");
        agentHomePage.navigateTransferPage(environment.getSecurityCode());

        log("Verify Transfer page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }


    @TestRails(id = "3614")
    @Test(groups = {"regression_credit"})
    @Parameters({"memberAccount"})
    public void Agent_AM_Transfer_3614( String memberAccount) {
        log("@title: Verify Balance of login account is updated after transfer");
        log("Precondition Step Log in successfully by SAD that belonging to Credit line");
        log("Step 1. Navigate Agency Management > Transfer");
        TransferPage page = agentHomePage.navigateTransferPage(environment.getSecurityCode());
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getDownLineUsers(userID, "SMA", _brandname);
        String userName = DownLineListingUtils.getAccountInfoInList(lstUsers, memberAccount).getUserCode();
        AccountInfo accountInfoBeforeTransfer = page.transferPage.getTransferInfo(userName);
        double amount = page.transferPage.defineAmountBasedOnTransferableBalance(0.1, accountInfoBeforeTransfer.getTransferableBalance());
        AccountInfo beforeTransfer = page.transferPage.getTransferInfo(userName);

        log("Step 2. Select an account and click on Transferable Balance number");
        log("Step 3. Enter valid amount and click submit button");
        page.transferPage.transfer("0OS", String.format("%.2f", amount));

        log("Verify: Validate Transferable Balance and Total Balance are updated");
        page.transferPage.verifyTransferInfo(userName,beforeTransfer,amount);

        log("INFO: Executed completely");
    }
    @TestRails(id = "3615")
    @Test(groups = {"regression_credit"})
    @Parameters({"memberAccount"})
    public void Agent_AM_Transfer_3615( String memberAccount) {
        log("@title: Verify Balance of login account is updated after transfer");
        log("Precondition Step Log in successfully by SAD that belonging to Credit line");
        log("Step 1. Navigate Agency Management > Transfer");
        TransferPage page = agentHomePage.navigateTransferPage(environment.getSecurityCode());
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getDownLineUsers(userID, "SMA", _brandname);
        String userName = DownLineListingUtils.getAccountInfoInList(lstUsers, memberAccount).getUserCode();

        log("Step 2. Select a downline");
        page.transferPage.selectUser(userName);

        log("Step 3. Click on Transfer button");
        ConfirmPopup popup = page.transferPage.clickTransferButton();
        String actualMessage = popup.getContentMessage();

        log("Verify: Validate message \"Full transfer will be applied to all selected users. Would you like to proceed with the transfer?\"");
        Assert.assertEquals(actualMessage,FULL_TRANSFER_CONFIRM_MSG,"Failed! The message is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3613")
    @Test(groups = {"regression_credit"})
    public void Agent_AM_Transfer_3613() {
        log("@title: Verify Balance of login account is updated after transfer");
        log("Precondition Step Log in successfully by SAD that belonging to Credit line");
        log("Step 1. Navigate Agency Management > Transfer");
        TransferPage page = agentHomePage.navigateTransferPage(environment.getSecurityCode());
        ArrayList<String> lstHeader = page.transferPage.tblAccountList.getHeaderNameOfRows();
        List<String> lstAccountStatus = page.transferPage.ddpAccountStatus.getOptions();
        List<String> lstLevel = page.transferPage.ddpLevel.getOptions();
        log("Verify: Validate UI on Transfer display correctly");
        Assert.assertEquals(page.transferPage.txtUserName.getAttribute("placeholder"),LBL_USERNAME_PLACE_HOLDER_SAT,"Failed");
        Assert.assertEquals(lstAccountStatus,LST_ACCOUNT_STATUS,"List Account Status is incorrect");
        Assert.assertEquals(lstLevel,LST_LEVEL,"Failed! List level incorrect");
        Assert.assertEquals(page.transferPage.lblAllYesterDayBalance.getText(),ALL_YESTERDAY_BALANCE,"Failed! Incorrect label: You are allowed to transfer on today");
        Assert.assertEquals(page.transferPage.lblYouAreAllowToTransferOnToday.getText(),YOU_ARE_ALLOW_TO_TRANSFER_ON_TODAY_MSG,"Failed! Incorrect label: You are allowed to transfer on today");
        Assert.assertEquals(page.transferPage.lblTransferableBalanceIsCalculatedUpToYesterday.getText(), AGConstant.AgencyManagement.Transfer.TRANSFERABLE_BALANCE_IS_CALCULATED_TO_YESTERDAY_MSG,"Failed!Incorrect label Transferable Balance is calculated up to Yesterday");
        Assert.assertEquals(lstHeader,TABLE_HEADER,"Failed! table header is incorrect");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3616")
    @Test(groups = {"regression_credit"})
    public void Agent_AM_Transfer_3616() {
        log("@title: Verify Balance of login account is updated after transfer");
        log("Precondition Step Log in successfully by SAD that belonging to Credit line");
        log("Step 1. Navigate Agency Management > Transfer");
        TransferPage page = agentHomePage.navigateTransferPage("");

        log("Verify: Validate security popup display and only access when input correct security code");
        Assert.assertTrue(page.securityPopup.isDisplayed(),"Failed! Security popup dose not display when login for old UI");
        page.confirmSecurityCode(environment.getSecurityCode());
        Assert.assertEquals(page.header.lblPageTitle.getText(),TRANSFER);

        log("INFO: Executed completely");
    }

    @TestRails(id = "3617")
    @Test(groups = {"interaction_credit"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Transfer_3617(String memberAccount, String password) throws Exception {
        log("@title: Verify available balance of PL in member site is updated when agent transfer successfully");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getDownLineUsers(userID, "PL", _brandname);
        String userName = DownLineListingUtils.getAccountInfoInList(lstUsers, memberAccount).getUserCode();

        log("Step 1. Navigate Agency Management > Transfer");
        TransferPage page = agentHomePage.navigateTransferPage(environment.getSecurityCode());
        AccountInfo accountInfoBeforeTransfer = page.transferPage.getTransferInfo(userName);
        double amount = page.transferPage.defineAmountBasedOnTransferableBalance(0.1, accountInfoBeforeTransfer.getTransferableBalance());

        log("Step 2 Transfer an amount " + amount + " for a player" + userName);
        page.transferPage.transfer(userName, String.format("%.2f", amount));
        AccountInfo accountInfoAfterTransfer = page.transferPage.getTransferInfo(userName);
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
    @Test(groups = {"interaction_credit"})
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
        AccountInfo accountInfoBeforeTransfer = page.transferPage.getTransferInfo(userName);

        double amount = page.transferPage.defineAmountBasedOnTransferableBalance(0.1, accountInfoBeforeTransfer.getTransferableBalance());
        accountLoginInfoExpected.setDownlineBalance(accountLoginInfoExpected.getDownlineBalance() - amount);
        accountLoginInfoExpected.setYesterdayDownlineBalance(accountLoginInfoExpected.getYesterdayDownlineBalance() - amount);

        log("Step 2 Transfer an amount " + amount + " for downline" + userName);
        page.transferPage.transfer(userName, String.format("%.2f", amount));

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

