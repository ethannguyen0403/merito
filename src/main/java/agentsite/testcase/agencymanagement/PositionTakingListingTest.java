package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.PositionTakingListingPage;
import agentsite.pages.components.ConfirmPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;
import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.*;

public class PositionTakingListingTest extends BaseCaseTest {

    @Test(groups = {"http_request"})
    public void Agent_AM_Position_Taking_Listing_001() {
        log("@title: Verify Position Taking List UI display correct");
        log("Step 1. Navigate Agency Management  > Position Taking Listing");
        agentHomePage.navigatePositionTakingListingPage();

        log("Verify 1. Position Taking Listing page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    @TestRails(id = "707")
    @Test(groups = {"smoke_newui"})
    public void Agent_AM_Position_Taking_Listing_707() {
        log("@title: Verify Position Taking List UI display correct");
        log("Step 1. Navigate Agency Management  > Position Taking Listing");
        String userCode = ProfileUtils.getProfile().getUserCodeAndLoginID();
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Verify 1. Verify UI on Position Taking Listing display correctly");
        Assert.assertTrue(page.txtUsername.isDisplayed(), "FAILED! Username textbox not display");
        Assert.assertTrue(page.ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box not display");
        Assert.assertTrue(page.ddbProduct.isDisplayed(), "FAILED! Product dropdown not display");
        Assert.assertTrue(page.ddbLevel.isDisplayed(), "FAILED! Level dropdown not display");
        List<String> lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AgencyManagement.PositionTakingListing.TABLE_PT_EXCHANGE_HEADER_NEWUI, "FAILED! Header table not match");
        Assert.assertTrue(page.lblUsername.isDisplayed(), "FAILED! Username level does not correct");
        Assert.assertTrue(page.lblProduct.isDisplayed(), "FAILED! Product label does not correct");
        Assert.assertTrue(page.lblAccountStatus.isDisplayed(), "FAILED! Account Status label does not correct");
        Assert.assertTrue(page.lblLevel.isDisplayed(), "FAILED! Level label does not correct");
        Assert.assertEquals(page.lblBreadcrumb.getText(), userCode, "FAILED! Breadcrumb bar not display login account");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4136")
    @Test(groups = {"smoke_sat"})
    public void Agent_AM_Position_Taking_Listing_4136() {
        log("@title: Verify Position Taking List UI display correct");
        log("Step 1. Navigate Agency Management  > Position Taking Listing");
        String userCode = ProfileUtils.getProfile().getUserCodeAndLoginID();
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Verify 1. Verify UI on Position Taking Listing display correctly");
        Assert.assertTrue(page.txtUsername.isDisplayed(), "FAILED! Username textbox not display");
        Assert.assertTrue(page.ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box not display");
        Assert.assertTrue(page.ddbProduct.isDisplayed(), "FAILED! Product dropdown not display");
        Assert.assertTrue(page.ddbLevel.isDisplayed(), "FAILED! Level dropdown not display");
        List<String> lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AgencyManagement.PositionTakingListing.TABLE_PT_EXCHANGE_HEADER_OLDUI, "FAILED! Header table not match");
        Assert.assertTrue(page.lblUsername.isDisplayed(), "FAILED! Username level does not correct");
        Assert.assertTrue(page.lblProduct.isDisplayed(), "FAILED! Product label does not correct");
        Assert.assertTrue(page.lblAccountStatus.isDisplayed(), "FAILED! Account Status label does not correct");
        Assert.assertTrue(page.lblLevel.isDisplayed(), "FAILED! Level label does not correct");
        Assert.assertEquals(page.lblBreadcrumb.getText(), userCode, "FAILED! Breadcrumb bar not display login account");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3597")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Position_Taking_Listing_3597(String brandname) {
        log("@title: Validate can search by username");
        log("Step 1. Navigate Agency Management  > Position Taking Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getAllDownLineUsers(brandname, "", userID);
        String downlineUserName = listAccount.get(0).getUserCode();
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Input Username and click submit button");
        page.positionTakingListing.search(downlineUserName, "", "", "");

        log("Verify 1. Verify can search by username");
        List<ArrayList<String>> lstResult = page.tblDownline.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstResult.get(0).get(page.usernameCol - 1), downlineUserName, "FAILED! Username not display as expected");
        Assert.assertEquals(lstResult.size(), 1, "FAILED! Result should display 1 record");

        log("INFO: Executed completely");
    }

    @TestRails(id = "708")
    @Test(groups = {"smoke"})
    public void Agent_AM_Position_Taking_Listing_708() {
        log("@title:Verify can search by Login ID ");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getAllDownLineUsers(_brandname, "", userID);
        String downlineLoginID = listAccount.get(0).getLoginID();
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Input Login ID and click submit button");
        page.positionTakingListing.search(downlineLoginID, "", "", "");

        log("Verify 1. Verify Login ID displays");
        List<ArrayList<String>> lstResult = page.tblDownline.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstResult.get(0).get(page.loginIDCol - 1), downlineLoginID, "FAILED! Username not display as expected");
        Assert.assertEquals(lstResult.size(), 1, "FAILED! Result should display 1 record");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3598")
    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_3598() {
        log("@title:Validate no result if input invalid Username or Password ");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String downlineLoginID = "invalid";
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Input invalid value in Username textbox and click submit button");
        page.positionTakingListing.search(downlineLoginID, "", "", "");

        log("Verify 1. Verify \"No records found.\"");
        Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record row not display when searching in valid username");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3599")
    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_3599() {
        log("@title: Verify PT Setting table display corresponding with selected product");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Select Exchange Game product");
        page.positionTakingListing.search("", "", EXCHANGE_GAMES, "");

        log("Verify 2. Verify Position Taking table of Exchange Game display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(EXCHANGE_GAMES), "FAILED! Position Taking table of Exchange Games does not match");

        log("Step 3.Select Game Hall product");
        page.positionTakingListing.search("", "", GAME_HALL, "");

        log("Verify 3. Verify Position Taking table of Game Hall display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(GAME_HALL), "FAILED! Position Taking table of Game Hall does not match");

        log("Step 4.Select Live Dealer Asian product");
        page.positionTakingListing.search("", "", LIVE_DEALER_ASIAN, "");

        log("Verify 4. Verify Position Taking table of Live Dealer Asian display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(LIVE_DEALER_ASIAN), "FAILED! Position Taking table of Live Dealer Asian does not match");

        log("Step 5.Select Live Dealer European product");
        page.positionTakingListing.search("", "", LIVE_DEALER_EUROPEAN, "");

        log("Verify 5. Verify Position Taking table of Live Dealer European display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(LIVE_DEALER_EUROPEAN), "FAILED! Position Taking table of Live Dealer European does not match");

        log("Step 6.Select Evolution product");
        page.positionTakingListing.search("", "", EVOLUTION, "");

        log("Verify 6. Verify Position Taking table of Evolution display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(EVOLUTION), "FAILED! Position Taking table of Evolution does not match");

        log("Step 7.Select Supernowa Casino product");
        page.positionTakingListing.search("", "", SUPERNOWA_CASINO, "");

        log("Verify 7. Verify Position Taking table of Supernowa Casino display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(SUPERNOWA_CASINO), "FAILED! Position Taking table of Supernowa Casino does not match");

        log("Step 8.Select Exchange product");
        page.positionTakingListing.search("", "", EXCHANGE, "");

        log("Verify 8. Verify Position Taking table of Exchange display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(EXCHANGE), "FAILED! Position Taking table of Exchange does not match");

        log("Step 9.Select Vivo product");
        page.positionTakingListing.search("", "", VIVO, "");

        log("Verify 9. Verify Position Taking table of Vivo display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(VIVO), "FAILED! Position Taking table of Vivo does not match");

        log("Step 10.Select Pragmatic product");
        page.positionTakingListing.search("", "", PRAGMATIC, "");

        log("Verify 10. Verify Position Taking table of Pragmatic display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(PRAGMATIC), "FAILED! Position Taking table of Pragmatic does not match");

        log("Step 11.Select ION product");
        page.positionTakingListing.search("", "", ION, "");

        log("Verify 11. Verify Position Taking table of ION display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(ION), "FAILED! Position Taking table of ION does not match");

        log("Step 12.Select CMD Sportsbook product");
        page.positionTakingListing.search("", "", CMD_SPORTSBOOK, "");

        log("Verify 12. Verify Position Taking table of CMD Sportsbook display");
        Assert.assertTrue(page.positionTakingListing.isTableHeaderProductDisplayCorrect(CMD_SPORTSBOOK), "FAILED! Position Taking table of CMD Sportsbook does not match");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3600")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Position_Taking_Listing_3600(String brandname) {
        log("@title:Verify filter member level display correctly");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String member = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0).getUserCode();
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Filter level = member");
        page.positionTakingListing.search(member, "", "", "Member");

        log("Verify 1. Verify only member display in the list");
        List<String> lstResult = page.tblDownline.getColumn(page.usernameCol, false);
        Assert.assertTrue(lstResult.size() == 2, "FAILED! Result should display 1 record");
        Assert.assertEquals(lstResult.get(0), member, "FAILED! Filter member level incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3601")
    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_3601() {
        log("@title:Verify filter agent level display correctly");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        String agent = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0).getUserCode();
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. 2. Filter level = agent");
        page.positionTakingListing.search(agent, "", "", "Agent");

        log("Verify 1. Verify only direct agent account display in the list");
        List<String> lstResult = page.tblDownline.getColumn(page.usernameCol, 1, false);
        Assert.assertTrue(lstResult.size() == 1, "FAILED! Result should display 1 record");
        Assert.assertEquals(lstResult.get(0), agent, "FAILED! Filter member level incorrect");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3602")
    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_3602() {
        log("@title: Validate filter all level display correctly");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Filter level = all");
        page.positionTakingListing.search("", "", "", MEMBER);
        List<String> lstResultMember = page.tblDownline.getColumnByBody(page.levelCol, false);
        page.positionTakingListing.search("", "", "", AGENT);
        List<String> lstResultAgent = page.tblDownline.getColumnByBody(page.levelCol, false);
        page.positionTakingListing.search("", "", "", ALL);

        log(" Verify 1 Change PT Setting popup display with the message \"Please select at least 1 user");
        List<String> lstResultAll = page.tblDownline.getColumnByBody(page.levelCol, false);
        Assert.assertTrue(lstResultAll.size()==lstResultMember.size() + lstResultAgent.size(), "FAILED! Result should display 1 record");
        Assert.assertTrue(lstResultAll.contains(lstResultAgent.get(0)), "Failed! Filter All level does not contains Agent level");
        Assert.assertTrue(lstResultAll.contains(lstResultMember.get(0)), "Failed! Filter All level does not contains Member level");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3603")
    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_3603() {
        log("@title:Verify can active/inactive sport column");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Uncheck /Check on Soccer checkbox");
        log("Verify 1. Verify Soccer checkbox is disappear/display when unchecked/checked");
       page.positionTakingListing.unCheckCheckbox("Soccer");
        List<String> lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Soccer"), "FAILED, Pt Table header contains Soccer after uncheck Soccer checkbox");

        page.positionTakingListing.checkCheckbox("Soccer");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Soccer"), "FAILED, Pt Table header not contains Soccer after check Soccer checkbox");


        log("Step 3. Uncheck /Check on Cricket checkbox");
        log("Verify 2. Verify Cricket checkbox is disappear/display when unchecked/checked");
        page.positionTakingListing.unCheckCheckbox("Cricket");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Cricket"), "FAILED, Pt Table header contains Cricket after uncheck Cricket checkbox");

        page.positionTakingListing.checkCheckbox("Cricket");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Cricket"), "FAILED, Pt Table header not contains Cricket after check Cricket checkbox");

        log("Step 4. Uncheck /Check on Fancy checkbox");
        log("Verify 3. Verify Fancy checkbox is disappear/display when unchecked/checked");
       page.positionTakingListing.unCheckCheckbox("Fancy");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Fancy"), "FAILED, Pt Table header contains Fancy after uncheck Fancy checkbox");

        page.positionTakingListing.checkCheckbox("Fancy");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Fancy"), "FAILED, Pt Table header not contains Fancy after check Fancy checkbox");

        log("Step 5. Uncheck /Check on Tennis checkbox");
        log("Verify 4. Verify Tennis checkbox is disappear/display when unchecked/checked");
       page.positionTakingListing.unCheckCheckbox("Tennis");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Tennis"), "FAILED, Pt Table header contains Tennis after uncheck Tennis checkbox");

        page.positionTakingListing.checkCheckbox("Tennis");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Tennis"), "FAILED, Pt Table header not contains Tennis after check Tennis checkbox");

        log("Step 6. Uncheck /Check on Basketball checkbox");
        log("Verify 5. Verify Basketball checkbox is disappear/display when unchecked/checked");
       page.positionTakingListing.unCheckCheckbox("Basketball");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Basketball"), "FAILED, Pt Table header contains Basketball after uncheck Basketball checkbox");

        page.positionTakingListing.checkCheckbox("Basketball");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Basketball"), "FAILED, Pt Table header not contains Basketball after check Basketball checkbox");


        log("Step 7. Uncheck /Check on Horse Racing checkbox");
        log("Verify 6. Verify Horse Racing checkbox is disappear/display when unchecked/checked");
       page.positionTakingListing.unCheckCheckbox("Horse Racing");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Horse Racing"), "FAILED, Pt Table header contains Horse Racing after uncheck Horse Racing checkbox");

        page.positionTakingListing.checkCheckbox("Horse Racing");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Horse Racing"), "FAILED, Pt Table header not contains Horse Racing after check Horse Racing checkbox");

        log("Step 8. Uncheck /Check on Greyhound Racing checkbox");
        log("Verify 7. Verify Greyhound Racing checkbox is disappear/display when unchecked/checked");
       page.positionTakingListing.unCheckCheckbox("Greyhound Racing");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Greyhound Racing"), "FAILED, Pt Table header contains Greyhound Racing after uncheck Greyhound Racing checkbox");

        page.positionTakingListing.checkCheckbox("Greyhound Racing");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Greyhound Racing"), "FAILED, Pt Table header not contains Greyhound Racing after check Greyhound Racing checkbox");

        log("Step 9. Uncheck /Check on Other checkbox");
        log("Verify 8. Verify Other checkbox is disappear/display when unchecked/checked");
       page.positionTakingListing.unCheckCheckbox("Other");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Other"), "FAILED, Pt Table header contains Other after uncheck Other checkbox");

        page.positionTakingListing.checkCheckbox("Other");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Other"), "FAILED, Pt Table header not contains Other after check Other checkbox");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3604")
    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_3604() {
        log("@title: Verify Select All checkbox works");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Uncheck /Check on Select All checkbox");
        log("Verify 1. Verify Select All checkbox is disappear/display when unchecked/checked");
       page.positionTakingListing.unCheckCheckbox("Select All");
        List<String> lstResult = page.tblDownline.getHeaderList();
        Assert.assertFalse(lstResult.contains("Soccer"), "FAILED, Pt Table header contains Soccer after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Cricket"), "FAILED, Pt Table header contains Cricket after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Fancy"), "FAILED, Pt Table header contain sFancy after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Tennis"), "FAILED, Pt Table header contains Tennis after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Basketball"), "FAILED, Pt Table header contains Baseketball after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Horse Racing"), "FAILED, Pt Table header contains Horse Racing after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Greyhound Racing"), "FAILED, Pt Table header contains Greyhound Racing after uncheck Select All checkbox");
        Assert.assertFalse(lstResult.contains("Other"), "FAILED, Pt Table header contains Other after uncheck Select All checkbox");

        page.positionTakingListing.checkCheckbox("Select All");
        lstResult = page.tblDownline.getHeaderList();
        Assert.assertTrue(lstResult.contains("Soccer"), "FAILED, Pt Table header contains Soccer after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Cricket"), "FAILED, Pt Table header contains Cricket after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Fancy"), "FAILED, Pt Table header contain sFancy after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Tennis"), "FAILED, Pt Table header contains Tennis after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Basketball"), "FAILED, Pt Table header contains Baseketball after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Horse Racing"), "FAILED, Pt Table header contains Horse Racing after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Greyhound Racing"), "FAILED, Pt Table header contains Greyhound Racing after uncheck Select All checkbox");
        Assert.assertTrue(lstResult.contains("Other"), "FAILED, Pt Table header contains Other after uncheck Select All checkbox");

        log("INFO: Executed completely");
    }

    @TestRails(id = "709")
    @Test(groups = {"smoke"})
    public void Agent_AM_Position_Taking_Listing_709() {
        log("@title: Verify can update PT for all sports");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String member = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();
        int PT = 10;

        log("Step  2. Select a downline and select all sport");
        page.positionTakingListing.search(member, "", "", "");
        List<String> lstPTInfoExpected = page.positionTakingListing.definePTSettingList(member, PT);

        log("Step 3. Update SAD Preset  and click Update");
        page.positionTakingListing.updatePT(member, PT, AgencyManagement.PositionTakingListing.SPORT_COLUMN_TRUE);

        log("Verify 1. Verify PT for Soccer, Cricket, Fancy Tennis, Basketball, Horse Racing, Other is updated and there is a green check in Update Status column");
        List<String> lstPTInfo = page.positionTakingListing.getPTofAccount(member);
        Assert.assertEquals(lstPTInfo, lstPTInfoExpected, "FAILED! Position Taking Listing Data does not match");
        Assert.assertTrue(page.positionTakingListing.verifyUpdateStatus(member, true), "FAILED! Update Status not display green check");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3605")
    @Test(groups = {"regression"})
    public void Agent_AM_Position_Taking_Listing_3605() {
        log("@title: Verify the popup message display when updating PT without select any user");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Not select any user and update SAT Preset and click Update");
        log("Step 3. Click OK button");
        page.positionTakingListing.updatePT("", 2, null);

        log(" Verify 1 Change PT Setting popup display with the message \"Please select at least 1 user");
        ConfirmPopup popup = new ConfirmPopup();
        Assert.assertEquals(popup.getTitle(), "Change PT Setting", "FAILED! Popup title is incorrect");
        Assert.assertEquals(popup.getContentMessage(), "Please select at least 1 user");

        popup.close();
        Assert.assertFalse(popup.isPopupDisplay(), "Failed! Popup still display after close");

        log("INFO: Executed completely");
    }

    @TestRails(id = "710")
    @Test(groups = {"smoke"})
    public void Agent_AM_Position_Taking_Listing_710() {
        log("@title: Verify can update PT  for selected sport");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String downline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();
        int PT = 2;

        log("Step 2. Select a downline and only select Soccer sport");
        page.positionTakingListing.search(downline, "", "", "");
        List<String> lstPTInfoBeforeUpdate = page.positionTakingListing.getPTofAccount(downline);
        lstPTInfoBeforeUpdate.set(page.soccerCol - 1, Integer.toString(PT));
        try {
            AgencyManagement.PositionTakingListing.SPORT_COLUMN_FALSE.put("Soccer", true);
            log("Step 3. Update SAD Preset and click update button");
            page.positionTakingListing.updatePT(downline, PT, AgencyManagement.PositionTakingListing.SPORT_COLUMN_FALSE);

            log("Verify 1. Verify Login ID displays");
            page.positionTakingListing.enableSport(AgencyManagement.PositionTakingListing.SPORT_COLUMN_FALSE);
            List<String> lstPTInfo = page.positionTakingListing.getPTofAccount(downline);
            Assert.assertEquals(lstPTInfo, lstPTInfoBeforeUpdate, "FAILED! Position Taking Listing Data does not match");
            Assert.assertTrue(page.positionTakingListing.verifyUpdateStatus(downline, true), "FAILED! Update Status not display green check");
            log("INFO: Executed completely");
        } finally {
            AgencyManagement.PositionTakingListing.SPORT_COLUMN_FALSE.put("Soccer", false);
        }

    }



}

