package agentsite.testcase.all.agencymanagement;

import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.PositionTakingListingPage;
import agentsite.pages.all.components.ConfirmPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static agentsite.common.AGConstant.*;
import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.POSITION_TAKING_LISTING;

public class PositionTakingListingTest extends BaseCaseMerito {

    @Test(groups = {"http_request"})
    public void Agent_AM_Position_Taking_Listing_001() {
        log("@title: Verify Position Taking List UI display correct");
        log("Step 1. Navigate Agency Management  > Position Taking Listing");
        agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Verify 1. Position Taking Listing page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke"})
    public void Agent_AM_Position_Taking_Listing_002() {
        log("@title: Verify Position Taking List UI display correct");
        log("Step 1. Navigate Agency Management  > Position Taking Listing");
        String userCode = ProfileUtils.getProfile().getUserCodeAndLoginID();
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Verify 1. Verify UI on Position Taking Listing display correctly");
        Assert.assertTrue(page.txtUsername.isDisplayed(), "FAILED! Username textbox not display");
        Assert.assertTrue(page.ddbAccountStatus.isDisplayed(),"FAILED! Account Status dropdown box not display");
        Assert.assertTrue(page.ddbProduct.isDisplayed(),"FAILED! Product dropdown not display");
        Assert.assertTrue(page.ddbLevel.isDisplayed(),"FAILED! Level dropdown not display");
        List<String> lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_HEADER, "FAILED! Header table not match");
        Assert.assertTrue(page.lblUsername.isDisplayed(), "FAILED! Username level does not correct");
        Assert.assertTrue(page.lblProduct.isDisplayed(), "FAILED! Product label does not correct");
        Assert.assertTrue(page.lblAccountStatus.isDisplayed(), "FAILED! Account Status label does not correct");
        Assert.assertTrue(page.lblLevel.isDisplayed(), "FAILED! Level label does not correct");
        Assert.assertEquals(page.lblBreadcrumb.getText(), userCode, "FAILED! Breadcrumb bar not display login account");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_003() {
        log("@title: Verify Position Taking List UI display correct");
        log("Step 1. Navigate Agency Management  > Position Taking Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getAllDownLineUsers(userID);
        String downlineUserName = listAccount.get(0).getUserCode();
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Step 2. Input Username and click submit button");
        page.search(downlineUserName, "", "", "");

        log("Verify 1. Verify can search by username");
        List<ArrayList<String>> lstResult = page.tblDownline.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstResult.get(0).get(page.usernameCol - 1), downlineUserName, "FAILED! Username not display as expected");
        Assert.assertEquals(lstResult.size(), 1, "FAILED! Result should display 1 record");

        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke"})
    public void Agent_AM_Position_Taking_Listing_004() {
        log("@title:Verify can search by Login ID ");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getAllDownLineUsers(userID);
        String downlineLoginID = listAccount.get(0).getLoginID();
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Step 2. Input Login ID and click submit button");
        page.search(downlineLoginID, "", "", "");

        log("Verify 1. Verify Login ID displays");
        List<ArrayList<String>> lstResult = page.tblDownline.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstResult.get(0).get(page.loginIDCol - 1), downlineLoginID, "FAILED! Username not display as expected");
        Assert.assertEquals(lstResult.size(), 1, "FAILED! Result should display 1 record");
        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_005() {
        log("@title:Verify can search by Login ID ");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String downlineLoginID = "invalid";
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Step 2. Input invalid value in Username textbox and click submit button");
        page.search(downlineLoginID, "", "", "");

        log("Verify 1. Verify \"No records found.\"");
        Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record row not display when searching in valid username");
        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_006() {
        log("@title: Verify PT Setting table display corresponding with selected product");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Step 2. Select Exchange Game product");
        page.search("", EXCHANGE_GAMES, "", "");

        log("Verify 2. Verify Position Taking table of Exchange Game display");
        List<String> lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AGConstant.AgencyManagement.PositionTakingListing.TABLE_PT_EXCHANGE_GAME_HEADER, "FAILED! Position Taking table of Exchange Game match");

        log("Step 3.Select Lottery & Slot product");
        page.search("", LOTTERY_SLOT, "", "");

        log("Verify 3. Verify Position Taking table of Lottery & Slot display");
        lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AgencyManagement.PositionTakingListing.TABLE_PT_LOTTERY_SLOT_HEADER, "FAILED! Position Taking table of Lottery & Slot incorrect");

        log("Step 4.Select Live Dealer Asian product");
        page.search("", LIVE_DEALER_ASIAN, "", "");

        log("Verify 4. Verify Position Taking table of Live Dealer Asian display");
        lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AgencyManagement.PositionTakingListing.TABLE_PT_LIVE_DEALER_ASIA_HEADER, "FAILED! Position Taking table of Live Dealer Asian incorrect");

        log("Step 5.Select Live Dealer European product");
        page.search("", LIVE_DEALER_EUROPEAN, "", "");

        log("Verify 5. Verify Position Taking table of Live Dealer European display");
        lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AgencyManagement.PositionTakingListing.TABLE_PT_LIVE_DEALER_EUROPEAN_HEADER, "FAILED! Position Taking table of Live Dealer European incorrect");

        log("Step 6.Select Evolution product");
        page.search("", EVOLUTION, "", "");

        log("Verify 6. Verify Position Taking table of Evolution display");
        lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AgencyManagement.PositionTakingListing.TABLE_PT_EVOLUTION_HEADER, "FAILED! Position Taking table of Evolution incorrect");

        log("Step 7.Select Supernowa Casino product");
        page.search("", SUPERNOWA_CASION, "", "");

        log("Verify 7. Verify Position Taking table of Supernowa Casino display");
        lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AgencyManagement.PositionTakingListing.TABLE_PT_SUPERNOWA_HEADER, "FAILED! Position Taking table of Supernowa Casino incorrect");

        log("Step 8.Select Exchange product");
        page.search("", EXCHANGE, "", "");

        log("Verify 8. Verify Position Taking table of Exchange display");
        lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AgencyManagement.PositionTakingListing.TABLE_PT_HEADER, "FAILED! Position Taking table of Exchange incorrect");

        log("INFO: Executed completely");
    }


    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_007() {
        log("@title:Verify filter member level display correctly");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String member = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE").get(0).getUserCode();
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Step 2. Filter level = member");
        page.search(member, "", "", "Member");

        log("Verify 1. Verify only member display in the list");
        List<String> lstResult = page.tblDownline.getColumn(page.usernameCol, false);
        Assert.assertTrue(lstResult.size() == 2, "FAILED! Result should display 1 record");
        Assert.assertEquals(lstResult.get(0), member, "FAILED! Filter member level incorrect");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_008() {
        log("@title:Verify filter agent level display correctly");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        String agent = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE").get(0).getUserCode();
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Step 2. 2. Filter level = agent");
        page.search(agent, "", "", "Agent");

        log("Verify 1. Verify only direct agent account display in the list");
        List<String> lstResult = page.tblDownline.getColumn(page.usernameCol, false);
        Assert.assertTrue(lstResult.size() == 1, "FAILED! Result should display 1 record");
        Assert.assertEquals(lstResult.get(0), agent, "FAILED! Filter member level incorrect");
        log("INFO: Executed completely");
    }


    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_010() {
        log("@title:Verify can active/inactive sport column");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Step 2. Uncheck /Check on Soccer checkbox");
        log("Verify 1. Verify Soccer checkbox is disappear/display when unchecked/checked");
        page.unCheckCheckbox("Soccer");
        List<String> lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Soccer"), "FAILED, Pt Table header contains Soccer after uncheck Soccer checkbox");

        page.checkCheckbox("Soccer");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Soccer"), "FAILED, Pt Table header not contains Soccer after check Soccer checkbox");


        log("Step 3. Uncheck /Check on Cricket checkbox");
        log("Verify 2. Verify Cricket checkbox is disappear/display when unchecked/checked");
        page.unCheckCheckbox("Cricket");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Cricket"), "FAILED, Pt Table header contains Cricket after uncheck Cricket checkbox");

        page.checkCheckbox("Cricket");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Cricket"), "FAILED, Pt Table header not contains Cricket after check Cricket checkbox");

        log("Step 4. Uncheck /Check on Fancy checkbox");
        log("Verify 3. Verify Fancy checkbox is disappear/display when unchecked/checked");
        page.unCheckCheckbox("Fancy");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Fancy"), "FAILED, Pt Table header contains Fancy after uncheck Fancy checkbox");

        page.checkCheckbox("Fancy");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Fancy"), "FAILED, Pt Table header not contains Fancy after check Fancy checkbox");

        log("Step 5. Uncheck /Check on Tennis checkbox");
        log("Verify 4. Verify Tennis checkbox is disappear/display when unchecked/checked");
        page.unCheckCheckbox("Tennis");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Tennis"), "FAILED, Pt Table header contains Tennis after uncheck Tennis checkbox");

        page.checkCheckbox("Tennis");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Tennis"), "FAILED, Pt Table header not contains Tennis after check Tennis checkbox");

        log("Step 6. Uncheck /Check on Basketball checkbox");
        log("Verify 5. Verify Basketball checkbox is disappear/display when unchecked/checked");
        page.unCheckCheckbox("Basketball");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Basketball"), "FAILED, Pt Table header contains Basketball after uncheck Basketball checkbox");

        page.checkCheckbox("Basketball");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Basketball"), "FAILED, Pt Table header not contains Basketball after check Basketball checkbox");


        log("Step 7. Uncheck /Check on Horse Racing checkbox");
        log("Verify 6. Verify Horse Racing checkbox is disappear/display when unchecked/checked");
        page.unCheckCheckbox("Horse Racing");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Horse Racing"), "FAILED, Pt Table header contains Horse Racing after uncheck Horse Racing checkbox");

        page.checkCheckbox("Horse Racing");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Horse Racing"), "FAILED, Pt Table header not contains Horse Racing after check Horse Racing checkbox");

        log("Step 8. Uncheck /Check on Greyhound Racing checkbox");
        log("Verify 7. Verify Greyhound Racing checkbox is disappear/display when unchecked/checked");
        page.unCheckCheckbox("Greyhound Racing");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Greyhound Racing"), "FAILED, Pt Table header contains Greyhound Racing after uncheck Greyhound Racing checkbox");

        page.checkCheckbox("Greyhound Racing");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Greyhound Racing"), "FAILED, Pt Table header not contains Greyhound Racing after check Greyhound Racing checkbox");

        log("Step 9. Uncheck /Check on Other checkbox");
        log("Verify 8. Verify Other checkbox is disappear/display when unchecked/checked");
        page.unCheckCheckbox("Other");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Other"), "FAILED, Pt Table header contains Other after uncheck Other checkbox");

        page.checkCheckbox("Other");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Other"), "FAILED, Pt Table header not contains Other after check Other checkbox");

        log("INFO: Executed completely");
    }


    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_011() {
        log("@title: Verify Select All checkbox works");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Step 2. Uncheck /Check on Select All checkbox");
        log("Verify 1. Verify Select All checkbox is disappear/display when unchecked/checked");
        page.unCheckCheckbox("Select All");
        List<String> lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Soccer"), "FAILED, Pt Table header contains Soccer after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Cricket"), "FAILED, Pt Table header contains Cricket after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Fancy"), "FAILED, Pt Table header contain sFancy after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Tennis"), "FAILED, Pt Table header contains Tennis after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Baseketball"), "FAILED, Pt Table header contains Baseketball after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Horse Racing"), "FAILED, Pt Table header contains Horse Racing after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Greyhound Racing"), "FAILED, Pt Table header contains Greyhound Racing after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Other"), "FAILED, Pt Table header contains Other after uncheck Select All checkbox");

        page.checkCheckbox("Select All");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Soccer"), "FAILED, Pt Table header contains Soccer after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Cricket"), "FAILED, Pt Table header contains Cricket after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Fancy"), "FAILED, Pt Table header contain sFancy after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Tennis"), "FAILED, Pt Table header contains Tennis after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Baseketball"), "FAILED, Pt Table header contains Baseketball after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Horse Racing"), "FAILED, Pt Table header contains Horse Racing after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Greyhound Racing"), "FAILED, Pt Table header contains Greyhound Racing after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Other"), "FAILED, Pt Table header contains Other after uncheck Select All checkbox");

        log("INFO: Executed completely");
    }


    @Test(groups = {"smoke"})
    public void Agent_AM_Position_Taking_Listing_012() {
        log("@title: Verify can update PT for all sports");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        HashMap<String, Boolean> sportMap = new HashMap<String, Boolean>() {
            {
                put("Soccer", true);
                put("Cricket", true);
                put("Fancy", true);
                put("Tennis", true);
                put("Basketball", true);
                put("Horse Racing", true);
                put("Greyhound Racing", false);
                put("Other", true);
            }
        };
        String userID = ProfileUtils.getProfile().getUserID();
        String member = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE").get(0).getUserCode();
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        int PT = 10;

        log("Step  2. Select a downline and select all sport");
        page.search(member, "", "", "");
        List<String> lstPTInfoBeforeUpdate = page.getPTofAccount(member);
        lstPTInfoBeforeUpdate.set(page.soccerCol - 1, Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.cricketCol - 1, Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.fancytCol - 1, Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.tennisCol - 1, Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.basketballCol - 1, Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.horseRacingCol - 1, Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.otherCol - 1, Integer.toString(PT));

        log("Step 3. Update SAD Preset  and click Update");
        page.updatePT(member, PT, sportMap);

        log("Verify 1. Verify PT for Soccer, Cricket, Fancy Tennis, Basketball, Horse Racing, Other is updated and there is a green check in Update Status column");
        List<String> lstPTInfo = page.getPTofAccount(member);
        Assert.assertEquals(lstPTInfo, lstPTInfoBeforeUpdate, "FAILED! Position Taking Listing Data does not match");
        Assert.assertTrue(page.verifyUpdateStatus(member, true), "FAILED! Update Status not display green check");
        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_013() {
        log("@title: Verify the popup message display when updating PT without select any user");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Step 2. Not select any user and update SAT Preset and click Update");
        log("Step 3. Click OK button");
        page.updatePT("", 2, null);

        log(" Verify 1 Change PT Setting popup display with the message \"Please select at least 1 user");
        ConfirmPopup popup = new ConfirmPopup();
        Assert.assertEquals(popup.getTitle(), "Change PT Setting", "FAILED! Popup title is incorrect");
        Assert.assertEquals(popup.getContentMessage(), "Please select at least 1 user");

        popup.close();
        Assert.assertFalse(popup.isPopupDisplay(), "Failed! Popup still display after close");

        log("INFO: Executed completely");
    }


    @Test(groups = {"smoke"})
    public void Agent_AM_Position_Taking_Listing_014() {
        log("@title: Verify can update PT  for selected sport");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String downline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE").get(0).getUserCode();
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        int PT = 2;

        log("Step 2. Select a downline and only select Soccer sport");
        page.search(downline, "", "", "");
        List<String> lstPTInfoBeforeUpdate = page.getPTofAccount(downline);
        lstPTInfoBeforeUpdate.set(page.soccerCol - 1, Integer.toString(PT));
        HashMap<String, Boolean> sport = new HashMap<String, Boolean>() {
            {
                put("Soccer", true);
                put("Cricket",false);
                put("Fancy",false);
                put("Tennis",false);
                put("Basketball",false);
                put("Horse Racing", false);
                put("Greyhound Racing", false);
                put("Other", false);
            }
        };
        log("Step 3. Update SAD Preset and click update button");
        page.updatePT(downline,PT,sport);

        log("Verify 1. Verify Login ID displays");
        page.enableSport(sport);
        List<String> lstPTInfo = page.getPTofAccount(downline);
        Assert.assertEquals(lstPTInfo,lstPTInfoBeforeUpdate,"FAILED! Position Taking Listing Data does not match");
        Assert.assertTrue(page.verifyUpdateStatus(downline,true),"FAILED! Update Status not display green check");
        log("INFO: Executed completely");
    }

}

