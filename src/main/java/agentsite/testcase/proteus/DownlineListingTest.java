package agentsite.testcase.proteus;

import agentsite.objects.agent.proteus.PS38PTSetting;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import static common.AGConstant.*;
import static common.AGConstant.AgencyManagement.CommissionSettingListing.*;
import static common.AGConstant.AgencyManagement.CreateCompany.*;
import static common.AGConstant.PS38;
import static common.MemberConstants.*;


import agentsite.pages.agentmanagement.proteus.createdownlineagent.commissionsettingsection.CommissionSectionPS38;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.*;

public class DownlineListingTest extends BaseCaseTest {

    @TestRails(id = "4039")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"downlineAccount"})
    public void PS38_Agent_TC4039(String downlineAccount)  {
        log("@title: Validate can update PT setting of PS38 product for all sports successfully on Edit Downline Listing ");
        log("Precondition: Login Agent Site with PO level");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button");
        page.searchDownline(downlineAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(downlineAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Position Taking");
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);
        log("Step 4: Select Sport as All and click View");
        editPage.editDownlineListing.positionTakingSectionPS38.addOrViewSport("All", "");
        log("Step 5: Update PT for any market of Soccer sport then click Submit");
        String positionValue = StringUtils.generateNumeric(10,20);
        PS38PTSetting ptSettingSoccer = new PS38PTSetting.Builder().sport(LBL_SOCCER_SPORT).ps38Tab(PREGAME_TAB_PS38).betTime(FULL_TIME).betType("1X2")
                .pos("Preset").amountPT(Double.valueOf(positionValue)).build();
        editPage.editDownlineListing.positionTakingSectionPS38.updateProteusPTMarket(Arrays.asList(ptSettingSoccer), false);
        page.submitEditDownlinePS38(true);

        log("Verify 1: Updated PT is applied correctly for all sports");
        PS38PTSetting ptSettingTennis = new PS38PTSetting.Builder().sport("Baseball").ps38Tab(PREGAME_TAB_PS38).betTime(FULL_TIME).betType("ML")
                .pos("Preset").amountPT(Double.valueOf(positionValue)).build();

        page.clickEditIcon(downlineAccount, true);
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);
        editPage.editDownlineListing.positionTakingSectionPS38.verifyProteusPTMarket(Arrays.asList(ptSettingTennis));
    }

    @TestRails(id = "4040")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"downlineAccount"})
    public void PS38_Agent_TC4040(String downlineAccount)  {
        log("@title: Validate can update PT setting of PS38 product for specific sport with all League successfully on Edit Downline Listing");
        log("Precondition: Login Agent Site with PO level");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button");
        page.searchDownline(downlineAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(downlineAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Position Taking");
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);

        log("Step 4: Select Sport as Soccer and and League is General and click View");
        editPage.editDownlineListing.positionTakingSectionPS38.addOrViewSport("Soccer", GENERAL);
        log("Step 5: Update PT for any market of Soccer sport then click Submit");
        String positionValue = StringUtils.generateNumeric(10,20);
        PS38PTSetting ptSettingSoccer = new PS38PTSetting.Builder().sport(LBL_SOCCER_SPORT).ps38Tab(PREGAME_TAB_PS38).betTime(FULL_TIME).betType("1X2")
                .pos("Preset").amountPT(Double.valueOf(positionValue)).build();

        editPage.editDownlineListing.positionTakingSectionPS38.updateProteusPTMarket(Arrays.asList(ptSettingSoccer), false);
        page.submitEditDownlinePS38(true);

        log("Verify 1: Updated PT is applied correctly for all sports");
        page.clickEditIcon(downlineAccount, true);
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);
        editPage.editDownlineListing.positionTakingSectionPS38.verifyProteusPTMarket(Arrays.asList(ptSettingSoccer));
    }

    @TestRails(id = "4041")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"downlineAccount"})
    public void PS38_Agent_TC4041(String downlineAccount)  {
        log("@title: Validate can update PT setting of PS38 product for specific Sport and League successfully on Edit Downline Listing");
        log("Precondition: Login Agent Site with PO level");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button");
        page.searchDownline(downlineAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(downlineAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Position Taking");
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);

        editPage.editDownlineListing.positionTakingSectionPS38.addOrViewSport("Soccer", "1");
        String leagueName = editPage.editDownlineListing.positionTakingSectionPS38.ddbLeague.getFirstSelectedOption();
        log("Step 4: Select Sport as Soccer and and League is " +  leagueName +" and click Add");

        log("Step 5: Update PT for any market of " + leagueName + " sport then click Submit");
        String positionValue = StringUtils.generateNumeric(10,20);
        PS38PTSetting ptSettingSoccer = new PS38PTSetting.Builder().sport(leagueName).ps38Tab(PREGAME_TAB_PS38).betTime(FULL_TIME).betType("1X2")
                .pos("Preset").amountPT(Double.valueOf(positionValue)).build();
        editPage.editDownlineListing.positionTakingSectionPS38.updateProteusPTMarket(Arrays.asList(ptSettingSoccer), false);
        page.submitEditDownlinePS38(true);

        log("Verify 1: Updated PT is applied correctly for all sports");
        page.clickEditIcon(downlineAccount, true);
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);
        editPage.editDownlineListing.positionTakingSectionPS38.verifyProteusPTMarket(Arrays.asList(ptSettingSoccer));
    }


    @TestRails(id = "4059")
    @Test(groups = {"ps38"})
    @Parameters({"memberAccount","password"})
    public void PS38_Agent_TC4059(String memberAccount,String password) throws Exception {
        log("@title: Validate PS38 product does not display in member site if the product is inactive");
        log("Precondition: The agent account that has the currency is supported PS38 product");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("The agent the not active PS38 product for his downline");
        page.searchDownline(memberAccount,"","");

        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);
        editDownLinePage.inActiveProduct("PS38",true);

        log("Step 1. Login Member site the account under agent in precondition");
        log("Step 2. Observe product name");
        loginMember(memberAccount, password);

        log("Verify 1: Verify the product PS38 does not display");
        Assert.assertFalse(memberHomePage.isProductDisplayed("PS38"), "ERROR! PS38 product should not display as expected");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4060")
    @Test(groups = {"ps38"})
    @Parameters({"memberAccount","password"})
    public void PS38_Agent_TC4060(String memberAccount,String password) throws Exception {
        log("@title: Validate PS38 product displays in member site if the product is active");
        log("Precondition: The agent account that has the currency is supported PS38 product");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("The agent active PS38 product for his downline agent/ player");
        page.searchDownline(memberAccount,"","");
        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);
        editDownLinePage.activeProduct("PS38",true);

        log("Step 1. Login Member site the account under agent in precondition");
        log("Step 2. Observe product name");
        loginMember(memberAccount, password);

        log("Verify 1: Verify the product PS38 displays");
        Assert.assertTrue(memberHomePage.isProductDisplayed("PS38"), "ERROR! PS38 product should display as expected");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4130")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    @Parameters({"downlineAccount"})
    public void PS38_Agent_TC4130(String downlineAccount)  {
        log("@title: Validate in Agent site > Edit downline UI Commission is displayed correctly");
        log("Precondition: Login agent at Agent level: CO that active PS38 product");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button");
        page.searchDownline(downlineAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(downlineAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Commission");
        page.productStatusSettingInforSection.selectProduct(PS38);
        log("Verify 1: Odds Group dropdown: should follow upline setting if having no setting yet");
        Assert.assertTrue(editPage.commissionSectionPS38.ddbOddsGroup.isDisplayed(), "FAILED! Odds Group dropdown is not displayed");
        CommissionSectionPS38 commissionSection = editPage.commissionSectionPS38.expandCommissionSection("Agent",true);
        log("Verify 2: Checkbox:Apply soccer games setting to other commission types and uncheck by default");
        Assert.assertEquals(editPage.commissionSectionPS38.lblApply.getText().trim(),  CHECKBOX_APPLY_SOCCER_PS38, "FAILED! Check box label is not correct");
        log("Verify 3: Table with headers: Group, Commission on, Group A, Group B,  Group C, Group D, Group E\n" +
                "Row under Group column is: Soccer Games, Very high commission, High commission , Normal commission , Parlays, Teasers");
        Assert.assertEquals(commissionSection.getTableHeader(),  TABLE_HEADER_COMMISSION_SECTION, "FAILED! Table commission header is not correct");
        Assert.assertEquals(commissionSection.getTableColumnList(1),
                TABLE_COLUMN_GROUP_COMMISSION_SECTION, "FAILED! Column list value are not correct");
        log("Verify 4: All row under Commission on column are: Volume");
        Assert.assertEquals(new HashSet<>(commissionSection.getTableColumnList(2)), new HashSet<>(Arrays.asList("Volume"))
                , "FAILED! Column list value are not correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4189")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    @Parameters({"directCOMemberAccount"})
    public void PS38_Agent_TC4189(String directCOMemberAccount)  {
        log("@title: Validate can set commission for direct players on specific sport with general league");
        log("Precondition: Login Agent site with CO level");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button. Player account: " + directCOMemberAccount);
        page.searchDownline(directCOMemberAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(directCOMemberAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Commission");
        editPage.productStatusSettingInforSection.selectProduct(PS38);
        CommissionSectionPS38 commissionSection = editPage.commissionSectionPS38.expandCommissionSection("Member",true);
        log("Step 4: Select any specific sport and leagues is General and click Add");
        String amountCommission = String.format("%.2f", commissionSection.randomDouble(0.01, 0.09));
        Map<String, String> commissionList= new HashMap<String, String>(){
            {
                put("", amountCommission);
            }
        };
        commissionSection.addSport(LBL_SOCCER_SPORT, GENERAL);
        log("Step 5: Select commission on added sport/league below then Submit it");
        commissionSection.updateComSpecificSport(LBL_SOCCER_SPORT, GENERAL, Arrays.asList(commissionList), TABLE_COLUMN_GROUP_COMMISSION_SECTION.get(0));
        page.submitEditDownlinePS38(true);
        log("Verify 1: Commission for sport: Soccer with general league is set successfully");
        page.clickEditIcon(directCOMemberAccount, true);
        editPage.productStatusSettingInforSection.selectProduct(PS38);
        Assert.assertEquals(
                commissionSection.getAmountCommission(null, TABLE_COLUMN_GROUP_COMMISSION_SECTION.get(0), LBL_SOCCER_SPORT, GENERAL)
                        .get(TABLE_COLUMN_GROUP_COMMISSION_SECTION.get(0)), amountCommission, "Failed! Commission is not updated.");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4190")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    @Parameters({"directCOMemberAccount"})
    public void PS38_Agent_TC4190(String directCOMemberAccount)  {
        log("@title: Validate can set commission for direct players on specific sport with specific league");
        log("Precondition: Login Agent site with CO level");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button. Player account: " + directCOMemberAccount);
        page.searchDownline(directCOMemberAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(directCOMemberAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Commission");
        editPage.productStatusSettingInforSection.selectProduct(PS38);
        CommissionSectionPS38 commissionSection = editPage.commissionSectionPS38.expandCommissionSection("Member",true);
        log("Step 4:  Select any specific sport and specific leagues and click Add");
        String amountCommission = String.format("%.2f", commissionSection.randomDouble(0.01, 0.09));
        Map<String, String> commissionList= new HashMap<String, String>(){
            {
                put("", amountCommission);
            }
        };
        commissionSection.addSport(LBL_SOCCER_SPORT, "1");
        String league = commissionSection.getLeague();
        log(String.format("Step 5: Select commission on added sport: %s league: %s below then Submit it", LBL_SOCCER_SPORT, league));
        commissionSection.updateComSpecificSport(LBL_SOCCER_SPORT, league, Arrays.asList(commissionList), TABLE_COLUMN_GROUP_COMMISSION_SECTION.get(0));
        page.submitEditDownlinePS38(true);

        log("Verify 1: Commission for sport: Soccer with league " +league + " is set successfully");
        page.clickEditIcon(directCOMemberAccount, true);
        editPage.productStatusSettingInforSection.selectProduct(PS38);
        Assert.assertEquals(
                commissionSection.getAmountCommission(null, TABLE_COLUMN_GROUP_COMMISSION_SECTION.get(0), LBL_SOCCER_SPORT, league)
                        .get(TABLE_COLUMN_GROUP_COMMISSION_SECTION.get(0)), amountCommission, "Failed! Commission is not updated.");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4191")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    @Parameters({"directCOMemberAccount"})
    public void PS38_Agent_TC4191(String directCOMemberAccount)  {
        log("@title: Validate can set odd groups for direct players on specific sport with general league");
        log("Precondition: Login Agent site with CO level");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button. Player account: " + directCOMemberAccount);
        page.searchDownline(directCOMemberAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(directCOMemberAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Commission");
        editPage.productStatusSettingInforSection.selectProduct(PS38);
        CommissionSectionPS38 commissionSection = editPage.commissionSectionPS38.expandCommissionSection("Member",true);
        log("Step 4:  Select any specific sport and leagues is General and click Add");
        String groupValue = String.valueOf("ABCDE".charAt(new Random().nextInt(5)));
        Map<String, String> commissionList= new HashMap<String, String>(){
            {
                put(groupValue, "");
            }
        };
        commissionSection.addSport(LBL_SOCCER_SPORT, GENERAL);
        String league = commissionSection.getLeague();
        log(String.format("Step 5: Select odds group on added sport: %s league: %s below then Submit it", LBL_SOCCER_SPORT, GENERAL));
        commissionSection.updateComSpecificSport(LBL_SOCCER_SPORT, league, Arrays.asList(commissionList), null);
        page.submitEditDownlinePS38(true);

        log("Verify 1: Odds group for sport: Soccer with league " +league + " is set successfully");
        page.clickEditIcon(directCOMemberAccount, true);
        editPage.productStatusSettingInforSection.selectProduct(PS38);
        Assert.assertEquals(
                commissionSection.getAmountCommission(null, null, LBL_SOCCER_SPORT, league)
                        .get(ODDS_GROUP), groupValue, "Failed! Odds group value is not updated.");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4192")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    @Parameters({"directCOMemberAccount"})
    public void PS38_Agent_TC4192(String directCOMemberAccount)  {
        log("@title: Validate can set odd groups for direct players on specific sport with specific league");
        log("Precondition: Login Agent site with CO level");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button. Player account: " + directCOMemberAccount);
        page.searchDownline(directCOMemberAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(directCOMemberAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Commission");
        editPage.productStatusSettingInforSection.selectProduct(PS38);
        CommissionSectionPS38 commissionSection = editPage.commissionSectionPS38.expandCommissionSection("Member",true);
        log("Step 4:  Select any specific sport and leagues is General and click Add");
        String groupValue = String.valueOf("ABCDE".charAt(new Random().nextInt(5)));
        Map<String, String> commissionList= new HashMap<String, String>(){
            {
                put(groupValue, "");
            }
        };
        commissionSection.addSport(LBL_SOCCER_SPORT, "1");
        String league = commissionSection.getLeague();
        log(String.format("Step 5: Select odds group on added sport: %s league: %s below then Submit it", LBL_SOCCER_SPORT, league));
        commissionSection.updateComSpecificSport(LBL_SOCCER_SPORT, league, Arrays.asList(commissionList), null);
        page.submitEditDownlinePS38(true);

        log("Verify 1: Odds group for sport: Soccer with league " +league + " is set successfully");
        page.clickEditIcon(directCOMemberAccount, true);
        editPage.productStatusSettingInforSection.selectProduct(PS38);
        Assert.assertEquals(
                commissionSection.getAmountCommission(null, null, LBL_SOCCER_SPORT, league)
                        .get(ODDS_GROUP), groupValue, "Failed! Odds group value is not updated.");
        log("INFO: Executed completely");
    }
}

