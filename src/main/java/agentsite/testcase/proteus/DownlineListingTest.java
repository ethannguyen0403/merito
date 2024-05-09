package agentsite.testcase.proteus;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.objects.agent.proteus.PS38PTSetting;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import static common.AGConstant.*;
import static common.AGConstant.AgencyManagement.CommissionSettingListing.*;
import static common.AGConstant.AgencyManagement.CreateCompany.*;
import static common.AGConstant.PS38;
import static common.MemberConstants.*;


import agentsite.pages.agentmanagement.proteus.createdownlineagent.commissionsettingsection.CommissionSectionPS38;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
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
        editPage.selectProduct(PS38);
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
        editPage.selectProduct(PS38);
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
        editPage.selectProduct(PS38);

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
        editPage.selectProduct(PS38);
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
        editPage.selectProduct(PS38);

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
        editPage.selectProduct(PS38);
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
        editDownLinePage.activeInactiveProduct("PS38",false,true);

        log("Step 1. Login Member site the account under agent in precondition");
        log("Step 2. Observe product name");
        loginMember(memberAccount, password);

        log("Verify 1: Verify the product PS38 does not display");
        Assert.assertFalse(memberHomePage.isProductDisplayed("PS38"), "ERROR! PS38 product should not display as expected");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4060")
    @Test(groups = {"ps38","isa"})
    @Parameters({"memberAccount","password"})
    public void PS38_Agent_TC4060(String memberAccount,String password) throws Exception {
        log("@title: Validate PS38 product displays in member site if the product is active");
        log("Precondition: The agent account that has the currency is supported PS38 product");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("The agent active PS38 product for his downline agent/ player");
        page.searchDownline(memberAccount,"","");
        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);
        editDownLinePage.activeInactiveProduct("PS38",true,true);

        log("Step 1. Login Member site the account under agent in precondition");
        log("Step 2. Observe product name");
        loginMember(memberAccount, password);

        log("Verify 1: Verify the product PS38 displays");
        Assert.assertTrue(memberHomePage.isProductDisplayed("PS38"), "ERROR! PS38 product should display as expected");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4130")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    public void PS38_Agent_TC4130()  {
        log("@title: Validate in Agent site > Edit downline UI Commission is displayed correctly");
        log("Precondition: Login agent at Agent level: CO that active PS38 product");
        log("Step 1: Access Agent > Downline Listing");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2: Filter with any player and click Edit button");
        page.searchDownline(directDownlineDisplay, "", "");
        EditDownLinePage editPage = page.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Click on PS38 product and scroll down to Commission");
        page.selectProduct(PS38);
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
    public void PS38_Agent_TC4189()  {
        log("@title: Validate can set commission for direct players on specific sport with general league");
        log("Precondition: Login Agent site with CO level");
        log("Step 1: Access Agent > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownlinePL = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownlinePL.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button. Player account: " + directDownlineDisplay);
        page.searchDownline(directDownlineDisplay, "", "");
        EditDownLinePage editPage = page.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Click on PS38 product and scroll down to Commission");
        editPage.selectProduct(PS38);
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
        page.clickEditIcon(directDownlineDisplay, true);
        editPage.selectProduct(PS38);
        Assert.assertEquals(
                commissionSection.getAmountCommission(null, TABLE_COLUMN_GROUP_COMMISSION_SECTION.get(0), LBL_SOCCER_SPORT, GENERAL)
                        .get(TABLE_COLUMN_GROUP_COMMISSION_SECTION.get(0)), amountCommission, "Failed! Commission is not updated.");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4190")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    public void PS38_Agent_TC4190()  {
        log("@title: Validate can set commission for direct players on specific sport with specific league");
        log("Precondition: Login Agent site with CO level");
        log("Step 1: Access Agent > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownlinePL = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownlinePL.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button. Player account: " + directDownlineDisplay);
        page.searchDownline(directDownlineDisplay, "", "");
        EditDownLinePage editPage = page.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Click on PS38 product and scroll down to Commission");
        editPage.selectProduct(PS38);
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
        page.clickEditIcon(directDownlineDisplay, true);
        editPage.selectProduct(PS38);
        Assert.assertEquals(
                commissionSection.getAmountCommission(null, TABLE_COLUMN_GROUP_COMMISSION_SECTION.get(0), LBL_SOCCER_SPORT, league)
                        .get(TABLE_COLUMN_GROUP_COMMISSION_SECTION.get(0)), amountCommission, "Failed! Commission is not updated.");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4191")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    public void PS38_Agent_TC4191()  {
        log("@title: Validate can set odd groups for direct players on specific sport with general league");
        log("Precondition: Login Agent site with CO level");
        log("Step 1: Access Agent > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownlinePL = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownlinePL.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button. Player account: " + directDownlineDisplay);
        page.searchDownline(directDownlineDisplay, "", "");
        EditDownLinePage editPage = page.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Click on PS38 product and scroll down to Commission");
        editPage.selectProduct(PS38);
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
        page.clickEditIcon(directDownlineDisplay, true);
        editPage.selectProduct(PS38);
        Assert.assertEquals(
                commissionSection.getAmountCommission(null, null, LBL_SOCCER_SPORT, league)
                        .get(ODDS_GROUP), groupValue, "Failed! Odds group value is not updated.");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4192")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    public void PS38_Agent_TC4192()  {
        log("@title: Validate can set odd groups for direct players on specific sport with specific league");
        log("Precondition: Login Agent site with CO level");
        log("Step 1: Access Agent > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownlinePL = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownlinePL.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button. Player account: " + directDownlineDisplay);
        page.searchDownline(directDownlineDisplay, "", "");
        EditDownLinePage editPage = page.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Click on PS38 product and scroll down to Commission");
        editPage.selectProduct(PS38);
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
        page.clickEditIcon(directDownlineDisplay, true);
        editPage.selectProduct(PS38);
        Assert.assertEquals(
                commissionSection.getAmountCommission(null, null, LBL_SOCCER_SPORT, league)
                        .get(ODDS_GROUP), groupValue, "Failed! Odds group value is not updated.");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29541")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.3.0"})
    public void PS38_Agent_TC29541()  {
        log("@title: Agent Site - PS38 - Edit Member - Validate UI in Bet Settings Section");
        log("Precondition: There is a player active PS38 product");
        log("Step 1: Active Downline Listing page and search the player in precondition");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownlinePL = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownlinePL.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(directDownlineDisplay, "", "");
        log("Step 2: Click on Edit icon in Edit column and input security code if required");
        EditDownLinePage editPage = page.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Select PS38 in Product Settings section");
        editPage.selectProduct(PS38);
        log("Verify 1: Verify UI in Pregame tab of Bet Setting section");
        editPage.betSettingSectionPS38.verifyPS38TabIsCorrect(PREGAME_TAB_PS38, CHECKBOX_MESSAGE_PS38_BET_SETTING);
    }

    @TestRails(id = "29543")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.3.0"})
    public void PS38_Agent_TC29543()  {
        log("@title: Agent Site - PS38 - Edit Member - Validate UI in Position Taking Section");
        log("Precondition: There is a player active PS38 product");
        log("Step 1: Active Downline Listing page and search the player in precondition");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownlinePL = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownlinePL.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(directDownlineDisplay, "", "");
        log("Step 2: Click on Edit icon in Edit column and input security code if required");
        EditDownLinePage editPage = page.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Select PS38 in Product Settings section");
        editPage.selectProduct(PS38);
        log("Step 3: Expand in Position Taking section then validate UI");
        log("Verify 1: Verify Sport label, Sport dropdown list and View button, Sport dropdown display All value by default\n" +
                "Message\" Copy all Position Taking from the first betting market for the sport below.\"\n" +
                "The list sport sections: Soccer, Baseball, Basketball, Football, E Sports, Others, Mix Parlay, Teaser\n" +
                "Expand a sport, verify UI table of sport:\n" +
                "Pregame table: Header is grouped by Full time and 1st Haft\n" +
                "Full time contains: 1X2, HDP, OU, TT, Others, Outright\n" +
                "1st Haft: 1X2, HDP, OU\n" +
                "Under Pregame column is Preset of the login level: e.g. SMA Preset\n" +
                "Inplay table: Header is grouped by Full time and 1st Haft\n" +
                "Full time contains: 1X2, HDP, OU\n" +
                "1st Haft: 1X2, HDP, OU\n" +
                "Under Pregame column is Preset of the login level: e.g. SMA Preset\n" +
                "Mix Parlay and Teasers only has Pregame table:\n" +
                "Mix Parlay: Fulltime (Mix Parlay) SMA Preset\n" +
                "Teaser: Fulltime (Teasers) SMA Preset");
        editPage.positionTakingSectionPS38.verifyPTSectionUI();
    }

    @TestRails(id = "29544")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.3.0"})
    public void PS38_Agent_TC29544()  {
        log("@title: Agent Site - PS38 - Edit Member - Validate can add a new sport and General league in Bet Setting");
        log("Precondition: There is a player active PS38 product");
        log("Step 1: Active Downline Listing page and search the player in precondition");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownlinePL = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownlinePL.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(directDownlineDisplay, "", "");
        log("Step 2: Click on Edit icon in Edit column and input security code if required");
        EditDownLinePage editPage = page.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Select PS38 in Product Settings section");
        editPage.selectProduct(PS38);
        log("Step 4:  Select a sport which not belonging to the default sport, and select  League: General then click Add button");
        page.betSettingSectionPS38.addSport("Alpine Skiing", GENERAL, true);
        log("Verify 1: Verify the Sport is added into the list sport table, It display before Others and the row is highlighted");
        page.betSettingSectionPS38.verifyNewSportAddedInPTSection("Alpine Skiing", "", true);
    }

    @TestRails(id = "29545")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.3.0"})
    public void PS38_Agent_TC29545()  {
        log("@title: Agent Site - PS38 - Edit Member - Validate can add a new sport with the league that is not general in Bet Setting");
        log("Precondition: There is a player active PS38 product");
        log("Step 1: Active Downline Listing page and search the player in precondition");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownlinePL = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownlinePL.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(directDownlineDisplay, "", "");
        log("Step 2: Click on Edit icon in Edit column and input security code if required");
        EditDownLinePage editPage = page.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Select PS38 in Product Settings section");
        editPage.selectProduct(PS38);
        log("Step 4:  Select a sport which not belonging to the default sport, and select  League: General then click Add button");
        page.betSettingSectionPS38.addSport("Alpine Skiing", "", true);
        String league = page.betSettingSectionPS38.ddbLeaguePS38.getFirstSelectedOption();
        log("Verify 1: Verify the Sport is added into the list sport table, It display before Others and the row is highlighted");
        page.betSettingSectionPS38.verifyNewSportAddedInPTSection("Alpine Skiing", league, true);
    }

    @TestRails(id = "29546")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.3.0"})
    public void PS38_Agent_TC29546()  {
        log("@title: Agent Site - PS38 - Edit Member - Validate Checkbox \"Copy all Limits for all Sport\" state when selecting a league in Bet Setting");
        log("Precondition: There is a player active PS38 product");
        log("Step 1: Active Downline Listing page and search the player in precondition");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownlinePL = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownlinePL.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(directDownlineDisplay, "", "");
        log("Step 2: Click on Edit icon in Edit column and input security code if required");
        EditDownLinePage editPage = page.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Select PS38 in Product Settings section");
        editPage.selectProduct(PS38);
        log("Step 4: Select a Soccer and a specific league: AFC - Asian Cup");
        page.betSettingSectionPS38.addSport(SPORT_SOCCER, "AFC - Asian Cup", false);
        log("Verify 1: Verify the checkbox \"Copy all limit for all Sports…\" is unchecked");
        Assert.assertFalse(page.betSettingSectionPS38.chkCopyAll.isEnabled(), "FAILED! Check box Copy All is checked");
    }

    @TestRails(id = "29547")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.3.0"})
    public void PS38_Agent_TC29547()  {
        log("@title: Agent Site - PS38 - Edit Member - Validate UI when tick/untick the checkbox \"Copy all Limits..\"in Bet Setting for Pregame and Inplay tab");
        log("Precondition: There is a player active PS38 product");
        log("Step 1: Active Downline Listing page and search the player in precondition");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownlinePL = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String directDownlineDisplay = directDownlinePL.getUserCode();
        DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
        downLineListingPage.searchDownline(directDownlineDisplay, "", "");
        log("Step 2: Click on Edit icon in Edit column and input security code if required");
        EditDownLinePage editPage = downLineListingPage.clickEditIcon(directDownlineDisplay, true);
        log("Step 3: Select PS38 in Product Settings section");
        editPage.selectProduct(PS38);

        log("Step 4: In Pregame tab, uncheck the checkbox \"Copy all limit for all Sports…\"");
        downLineListingPage.betSettingSectionPS38.selectPS38Tab(PREGAME_TAB_PS38);
        downLineListingPage.betSettingSectionPS38.chkCopyAll.deSelect();
        log("Verify 1: When the checkbox is unchecked, all  textbox is enable to input valued");
        List<String> betSettingList = new ArrayList<>(HEADER_BET_SETTING_PS38);
        betSettingList.remove(0); // remove blank cell in header list
        List<String> sportsList = downLineListingPage.betSettingSectionPS38.tblBetSettingPS38.getColumn(1, true);

        Assert.assertTrue(downLineListingPage.betSettingSectionPS38.isSportPS38InputEnable(sportsList, betSettingList, true), "FAILED! All input values are not enable");
        log("Step 5: In Pregame tab, check the checkbox \"Copy all limit for all Sports…\"");
        downLineListingPage.betSettingSectionPS38.chkCopyAll.select();
        log("Verify 2: When the checkbox is checked, only Min Bet, Max Bet and Max Per Match of Soccer text boxes are enable to input value");
        sportsList.remove(SPORT_SOCCER);
        Assert.assertTrue(downLineListingPage.betSettingSectionPS38.isSportPS38InputEnable(Arrays.asList(SPORT_SOCCER), betSettingList, true), "FAILED! The soccer sport is not enable");
        Assert.assertTrue(downLineListingPage.betSettingSectionPS38.isSportPS38InputEnable(sportsList, betSettingList, false), "FAILED! Others sports rows are not disable");

        log("Step 6: In In play tab, uncheck the checkbox \"Copy all limit for all Sports…\"");
        downLineListingPage.betSettingSectionPS38.selectPS38Tab(INPLAY_TAB_PS38);
        downLineListingPage.betSettingSectionPS38.chkCopyAll.deSelect();
        betSettingList.remove(2);// remove option Max per Match to Header list
        log("Verify 3: When the checkbox is unchecked, all  textbox except for the textbox in Max Per Match column is enable to input value\n");
        Assert.assertTrue(downLineListingPage.betSettingSectionPS38.isSportPS38InputEnable(sportsList, betSettingList, true), "FAILED! All input values except Max per match are not enable");
        // assert for Colum Max per Match disable
        Assert.assertTrue(downLineListingPage.betSettingSectionPS38.isSportPS38InputEnable(sportsList, Arrays.asList(HEADER_BET_SETTING_PS38.get(3)), false), "FAILED! Input values of Max per match are not enable");
        log("Step 7: In In play tab, check the checkbox \"Copy all limit for all Sports…\"");
        downLineListingPage.betSettingSectionPS38.chkCopyAll.select();
        log("Verify 4: When the checkbox is checked, only  Min Bet Soccer textbox is enable to input value");
        sportsList.remove(SPORT_SOCCER);
        Assert.assertTrue(downLineListingPage.betSettingSectionPS38.isSportPS38InputEnable(Arrays.asList(SPORT_SOCCER), betSettingList, true), "FAILED! The soccer sport is not enable");
        Assert.assertTrue(downLineListingPage.betSettingSectionPS38.isSportPS38InputEnable(sportsList, betSettingList, false), "FAILED! Others sports rows are not disable");

    }

    @TestRails(id = "29548")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.3.0"})
    public void PS38_Agent_TC29548()  {
        log("@title: Agent Site - PS38 - Edit Member - Validate cannot update Bet Setting for indirect player ");
        log("Precondition: There is a player active PS38 product. The player has SMA > Agent > Player");
        log("Step 1: Login agent site Agent level, the indirect agent (SMA) of the player in precondition");
        // get indirect member account
        String userID = ProfileUtils.getProfile().getUserID();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        AccountInfo indirectDownline = DownLineListingUtils.getAllDownLineUsers(_brandname,"", directDownline.getUserID()).get(0);
        AccountInfo indirectDownlinePL = DownLineListingUtils.getDownLineUsers(indirectDownline.getUserID(), "PL", "ACTIVE", _brandname).get(0);
        String indirectDownlineDisplayPL = indirectDownlinePL.getUserCode();

        log("Step 2: Active Downline Listing page and search the player in precondition");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(indirectDownlineDisplayPL, "", "");
        log("Step 3: Click on Edit icon in Edit column and input security code if required");
        EditDownLinePage editPage = page.clickEditIcon(indirectDownlineDisplayPL, true);
        log("Step 4: Select PS38 in Product Settings section and verify UI of Bet Setting");
        editPage.selectProduct(PS38);

        log("Verify 1: Verify all controls under bet setting is disable: Sport dropdown, Copy all Limit… checkbox, all min/max/max per match textbox");
        List<String> betSettingList = new ArrayList<>(HEADER_BET_SETTING_PS38);
        betSettingList.remove(0); // remove blank cell in header list
        List<String> sportsList = editPage.betSettingSectionPS38.tblBetSettingPS38.getColumn(1, true);

        Assert.assertFalse(editPage.betSettingSectionPS38.ddbSportsPS38.isEnabled(),"FAILED! The sport dropdown is enable");
        Assert.assertFalse(editPage.betSettingSectionPS38.chkCopyAll.isEnabled(),"FAILED! The Copy all Limit… checkbox is enable");
        Assert.assertTrue(editPage.betSettingSectionPS38.isSportPS38InputEnable(sportsList, betSettingList, false), "FAILED! All input values are not disable");
    }
}

