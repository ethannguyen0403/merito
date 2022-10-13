package agentsite.testcase.all.agencymanagement.downlinelisting;

import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.all.agentmanagement.DownLineListingPage;
import agentsite.pages.all.agentmanagement.EditDownLinePage;
import agentsite.pages.all.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseMerito;
import com.paltech.utils.StringUtils;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.home.ChangePasswordPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.*;
import static com.paltech.utils.StringUtils.decrypt;

public class EdiMarketTest extends BaseCaseMerito {

    @Test(groups = {"interaction1"})
    @Parameters({"downlineAccount","password"})
    public void Agent_AM_Downline_Listing_Edit_Market_013(String downlineAccount, String password) throws Exception {
        log("@title: Cannot transfer in a day in a weekly not allow to transfer");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getAllDownLineUsers(userID);
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userName = DownLineListingUtils.getAccountInfoInList(lstUsers,downlineAccount).getUserCode();

        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Click on Edit icon of any agent level 02R0001");
        EditDownLinePage editDownLineAgentPage = page.clickEditIcon(userName);

        log("Step 3. In Account Balance Transfer Condition, Select Weekly  and a day is not today then click update button");
        log("Step 4 Login agent with account 02R0001 level > Transfer");
        log("5 Do transfer for an account");


        log("Verify Verify cannot transfer, an X icon display in Update status,  and click on the icon the message \"You are not allowed to perform transfer today. Please contact your upline\"");

        log("INFO: Executed completely");
    }
}

