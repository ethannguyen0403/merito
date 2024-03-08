package agentsite.testcase.agencymanagement.downlinelisting;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.agentmanagement.TransferPage;
import agentsite.pages.components.ConfirmPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class EdiMarketTest extends BaseCaseTest {

    @TestRails(id = "3562")
    @Test(groups = {"interaction"})
    @Parameters({"downlineAccount", "password"})
    public void Agent_AM_Downline_Listing_Edit_Market_3562(String downlineAccount, String password) throws Exception {
        log("@title: Cannot transfer in a day in a weekly not allow to transfer");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getAllDownLineUsers(_brandname, "", userID);
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userName = DownLineListingUtils.getAccountInfoInList(lstUsers, downlineAccount).getUserCode();
        String date = DateUtils.getDate(1, "d", "GMT-4");
        List<String> lstDate = new ArrayList<>();
        lstDate.add(date);

        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Click on Edit icon of any agent level 02R0001");
        EditDownLinePage editDownLineAgentPage = page.downlineListing.clickEditIcon(userName);

        log("Step 3. In Account Balance Transfer Condition, Select Weekly  and a day is not today then click update button");
        editDownLineAgentPage.editDownlineListing.setTransaction(false, lstDate, true);

        log("Step 4 Login agent with account 02R0001 level > Transfer");
        loginAgent(downlineAccount, password, true);

        log("5 Do transfer for an account");
        TransferPage transferPage = agentHomePage.navigateTransferPage(environment.getSecurityCode());
        userID = ProfileUtils.getProfile().getUserID();
        lstUsers = DownLineListingUtils.getAllDownLineUsers(_brandname, "", userID);
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        userName = DownLineListingUtils.getAccountInfoInList(lstUsers, downlineAccount).getUserCode();
        AccountInfo accountInfoBeforeTransfer = transferPage.transferPage.getTransferInfo(userName);
        double amount = transferPage.transferPage.defineAmountBasedOnTransferableBalance(0.1, accountInfoBeforeTransfer.getTransferableBalance());

        log("Step 2 Transfer an amount " + amount + " for a account" + userName);
        transferPage.transferPage.transfer(userName, String.format("%.2f", amount));

        log("Verify Verify cannot transfer, an X icon display in Update status,  and click on the icon the message \"You are not allowed to perform transfer today. Please contact your upline\"");
        Assert.assertFalse(transferPage.transferPage.isAccountTransferredError(userName), "Failed! Error icon does not display when transfer not at setting schedule");

        ConfirmPopup dialogPopup = transferPage.transferPage.clickUpdateStatusColumn(userName);
        Assert.assertEquals(dialogPopup.getContentMessage(), "You are not allowed to perform transfer today. Please contact your upline", "Failed! Message Error is incorrect when transfer does not follow setting schedule");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3561")
    @Test(groups = {"interaction"})
    @Parameters({"downlineAccount", "password"})
    public void Agent_AM_Downline_Listing_Edit_Market_3561(String downlineAccount, String password) throws Exception {
        //TODO: implement test this case
        Assert.assertTrue(false,"");
        log("INFO: Executed completely");
    }

}

