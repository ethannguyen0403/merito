package agentsite.testcase.proteus;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.CommissionSettingListingPage;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.agentmanagement.commissionlisting.CommissionSettingListing;
import agentsite.pages.agentmanagement.proteus.createdownlineagent.commissionsettingsection.CommissionSectionPS38;
import agentsite.pages.components.CommissionPopup;
import agentsite.pages.components.PositionTakingPopup;
import agentsite.pages.components.QuickSetting;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DoubleUtils;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.ODDS_GROUP;
import static common.AGConstant.PS38;
import static common.MemberConstants.LBL_SOCCER_SPORT;

public class QuickSearchTest extends BaseCaseTest {

    @TestRails(id = "16175")
    @Test(groups = {"ps38_inactive", "nolan_Proteus.2024.V.2.0"})
    public void Quick_Search_TC16175(){
        log("@title: \tValidate in Agent site > Quick Search, Commision Setting, 'PS38' does not display in Product drop-down when logged-in level isn't activated the product ");
        log("@Precondition: Login to the account Inactive PS38 product");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname);
        String directDownline = listAccount.get(0).getUserCode();
        log("Step 1: Access Quick Search > Search for an account > Commission Setting");
        agentHomePage.quickSearch.quickSearch(directDownline);
        QuickSetting quickSetting = agentHomePage.quickSearch.clickSetting();
        log("Step 2: Observe the options of 'Product' drop-down");
        CommissionPopup commissionPopup =  quickSetting.openCommission();
        log("Verify 1: Verify that the 'PS38' option does not display in Product drop-down");
        Assert.assertTrue(commissionPopup.getProductList().contains(PS38), "FAILED! Product PS38 exist in product list");
    }

    @TestRails(id = "16176")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.2.0"})
    public void Quick_Search_TC16176(){
        log("@title: Validate in Agent site > Quick Search, Commision Setting, 'PS38' does not display in Product drop-down when logged-in level isn't activated the product ");
        log("@Precondition: There is an account is not CO level and activated PS38 product");
        log("Step 1: Login to Agent site with the account at the precondition");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname);
        String directDownline = listAccount.get(0).getUserCode();
        log("Step 2: Access Quick Search and search for an indirect downline");
        log("Step 3: Access Commission Setting and observe");
        agentHomePage.quickSearch.quickSearch(directDownline);
        QuickSetting quickSetting = agentHomePage.quickSearch.clickSetting();

        CommissionPopup commissionPopup =  quickSetting.openCommission();
        log("Step 4: Select PS38 product");
        commissionPopup.ddbProduct.selectByVisibleText(PS38);
        log("Verify 1: Verify that there is no 'Odds Group' drop-down being displayed");
        Assert.assertFalse(commissionPopup.ddbOddGroups.isDisplayed(), "FAILED! Odds group dropdown is display.");
    }

        @TestRails(id = "16179")
        @Test(groups = {"ps38", "nolan_Proteus.2024.V.2.0"})
        public void Quick_Search_TC16179(){
            log("@title: Validate in Agent site > Quick Search, Commision Setting, only able to update commission for direct players");
            log("@Precondition: There is an account activated PS38 product");
            log("Step 1: Login to Agent site with the account at the precondition");
            String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
            String userID = ProfileUtils.getProfile().getUserID();
            AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
            AccountInfo inDirectDownline = DownLineListingUtils.getDownLineUsers(directDownline.getUserID(), "", "ACTIVE", _brandname).get(0);
            String indirectAccountDisplay = inDirectDownline.getUserCode();
            log("Step 2: Access Quick Search > search for an indirect player");
            log("Step 3: Access Commission Setting and observe");
            agentHomePage.quickSearch.quickSearch(indirectAccountDisplay);
            QuickSetting quickSetting = agentHomePage.quickSearch.clickSetting();

            CommissionPopup commissionPopup =  quickSetting.openCommission();
            log("Step 4: Select PS38 product");
            commissionPopup.filterPS38Product();
            log("Verify 1: Verify that there is no 'Odds Group' drop-down being displayed");
            Assert.assertFalse(commissionPopup.ddbOddGroups.isDisplayed(), "FAILED! Odds group dropdown is display.");
    }

    @TestRails(id = "16193")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.2.0"})
    public void Quick_Search_TC16193(){
        log("@title: Validate that settings updated in Quick Search > Commission Setting are reflected correctly in other pages");
        log("@Precondition: There is an account activated PS38 product");
        log("Step 1: Login to Agent site with the account at the precondition");
        String commissionAmount = StringUtils.generateNumeric(1, 4);
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownline.getUserCode();
        log("Step 2: Access Quick Search > search for an indirect player");
        log("Step 3: Access Commission Setting and observe");
        agentHomePage.quickSearch.quickSearch(directDownlineDisplay);
        QuickSetting quickSetting = agentHomePage.quickSearch.clickSetting();

        CommissionPopup commissionPopup =  quickSetting.openCommission();
        log("Step 4: Select PS38 product");
        CommissionSectionPS38 commissionSectionPS38 = commissionPopup.filterPS38Product();
        log("Step 5: Access Settings > Commission > Update any settings successfully");
        Map<String, String> commissionList= new HashMap<String, String>(){
            {
                put("A", commissionAmount);
            }
        };
        commissionSectionPS38.updateComSpecificSport(LBL_SOCCER_SPORT, "", Arrays.asList(commissionList), null);
        commissionPopup.savePopup();
        log("Step 6:  Access Commission Setting Listing page > search for the account of step 2 and observe the settings at step 3");
        CommissionSettingListingPage commissionListingPage = agentHomePage.navigateCommissionSettingListingPage();
        commissionListingPage.search(directDownlineDisplay, "", "", PS38);
        log("Verify 1: Verify that the settings updated in Quick Search are reflected correctly in Commission Setting Listing ");
        commissionListingPage.verifyCommissionUpdated(true, commissionAmount);
        log("Verify 2: Verify that the settings updated in Quick Search are reflected correctly in Downline Listing pages");
        DownLineListingPage pageDownline = agentHomePage.navigateDownlineListingPage();
        EditDownLinePage editPage = pageDownline.clickEditIcon(directDownlineDisplay, true);
        editPage.selectProduct(PS38);
        CommissionSectionPS38 commissionSection = editPage.commissionSectionPS38.expandCommissionSection("Agent",true);
        Assert.assertEquals(
                commissionSection.getAmountCommission("", "", LBL_SOCCER_SPORT, "")
                        .get(ODDS_GROUP), commissionAmount, "Failed! Odds group value is not updated.");
    }

    @TestRails(id = "17604")
    @Test(groups = {"ps38_inactive", "nolan_Proteus.2024.V.2.0"})
    public void Quick_Search_TC17604(){
        log("@title: Validate in Agent site > Quick Search, Position Taking, 'PS38' does not display in Product drop-down when logged-in level isn't activated the product");
        log("@Precondition: Account is not activated the PS38 product");
        log("Step 1: Login to Agent site with the account at the precondition");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        String directAccountDisplay = directDownline.getUserCode();
        log("Step 2: Access Quick Search > Search for an account > Position Taking");
        log("Step 3: Access Commission Setting and observe");
        agentHomePage.quickSearch.quickSearch(directAccountDisplay);
        QuickSetting quickSetting = agentHomePage.quickSearch.clickSetting();
        PositionTakingPopup popup= quickSetting.openPositionTaking();

        log("Verify 1: Verify that the 'PS38' option does not display in Product drop-down");
        Assert.assertTrue(popup.tblPT.getColumn(1, false).contains(PS38), "FAILED! Position Taking popup contains PS38 product");
    }
}
