package agentsite.testcase.satsport.agencymanagement;

import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DownLineListingPage;
import agentsite.pages.all.agentmanagement.EditDownLinePage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.DOWNLINE_LISTING;

public class EditUserTest extends BaseCaseMerito {


    @Test(groups = {"satregression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_Edit_User_005(String brandname) {
        log("@title:Verify can Suspend  the account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "Active", "Member");
        EditDownLinePage editUserPage = (EditDownLinePage) page.clickEditIcon(loginID, "");

        log("Step  3. Change account status to Suspended and click on Submit button\n" +
                "     *              Click Ok button on Edit Member popup");
        editUserPage.inputInfoSection("", "Suspended", "", "", "", "", "", true);
        String message = editUserPage.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");
        Assert.assertEquals(page.getAccountStatus(loginID), "Suspended", String.format("FAILED! Account status of account %s is not Suspended", loginID));

        log("Verify 2. Downline Listing display Account Status is Suspended");
        editUserPage = (EditDownLinePage) page.clickEditIcon(loginID, "");

        log("Step 6. Change account status to Active and Submit and click Ok button");
        editUserPage.inputInfoSection("", "Suspended", "", "", "", "", "", true);

        log("Verify 4. Downline Listing display Account Status is Active");
        Assert.assertEquals(page.getAccountStatus(loginID), "Active", String.format("FAILED! Account status of account %s is not Suspended", loginID));

        log("INFO: Executed completely");
    }

    @Test(groups = {"satregression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_Edit_User_006(String brandname) {
        log("@title:Verify can Close the account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "Active", "Member");
        EditDownLinePage editUserPage = (EditDownLinePage) page.clickEditIcon(loginID, "");

        log("Step  3. Change account status to Suspended and click on Submit button\n" +
                "     *              Click Ok button on Edit Member popup");
        editUserPage.inputInfoSection("", "Closed", "", "", "", "", "", true);
        String message = editUserPage.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");
        Assert.assertFalse(page.getAccountStatusDropdown(loginID).isEnabled(), String.format("FAILED! Account status of account %s is not disable when closed", loginID));

        log("INFO: Executed completely");
    }

    @Test(groups = {"satregression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_Edit_User_009(String brandname) {
        log("@title:Verify cannot inactive all product");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editUserPage = (EditDownLinePage) page.clickEditIcon(loginID, "");

        log("Step  3. Inactive all products (Exchange, Exchange Game, Live Dealer Asian, Live Dealer European, Lottery & Slots)");
        //TODO: Wirte function inactive product
        //editUserPage.productSettingsSection.productStatusSettingsSection.;
        String message = editUserPage.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_ALL_PRODUCT_NOT_SELECT, "FAILED! Message update downline is not correct");
        log("INFO: Executed completely");
    }

    @Test(groups = {"satregression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_Edit_User_010(String brandname) {
        log("@title:Verify navigate to Downline List if click on Cancel button");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editUserPage = (EditDownLinePage) page.clickEditIcon(loginID, "");

        log("Step 3 Click Cancel Button on Edit User page");
        editUserPage.btnCancel.click();

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(page.getPageTitle(), AGConstant.HomePage.DOWNLINE_LISTING, "FAILED! Dowline Listing page not display when clicking Cancel from Edit User page");
        log("INFO: Executed completely");
    }

}

