package agentsite.testcase.satsport.agencymanagement;

import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.PositionTakingListingPage;
import agentsite.ultils.account.ProfileUtils;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.POSITION_TAKING_LISTING;

public class PositionTakingListingTest extends BaseCaseMerito {
    /**
     * @title: Verify Position Taking List UI display correct
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Position Taking Listing
     * @expect: 1. Verify UI on Position Taking Listing display correctly
     */
    @TestRails(id="707")
    @Test(groups = {"smoke"})
    @Parameters("username")
    public void Agent_AM_Position_Taking_Listing_002(String username) {
        log("@title: Verify Position Taking List UI display correct");
        log("Step 1. Navigate Agency Management  > Position Taking Listing");
        String userCode = ProfileUtils.getProfile().getUserCode();
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);

        log("Verify 1. Verify UI on Position Taking Listing display correctly");
        Assert.assertTrue(page.txtUsername.isDisplayed(),"FAILED! Username textbox not display");
        Assert.assertTrue(page.ddbAccountStatus.isDisplayed(),"FAILED! Account Status dropdown box not display");
        Assert.assertTrue(page.ddbProduct.isDisplayed(),"FAILED! Product dropdown not display");
        Assert.assertTrue(page.ddbLevel.isDisplayed(),"FAILED! Level dropdown not display");
        List<String> lstHeader = page.tblDownline.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, AGConstant.AgencyManagement.PositionTakingListing.SAT_TABLE_PT_HEADER, "FAILED! Header table not match");
        Assert.assertTrue(page.lblUsername.isDisplayed(), "FAILED! Username level does not correct");
        Assert.assertTrue(page.lblProduct.isDisplayed(), "FAILED! Product label does not correct");
        Assert.assertTrue(page.lblAccountStatus.isDisplayed(),"FAILED! Account Status label does not correct");
        Assert.assertTrue(page.lblLevel.isDisplayed(), "FAILED! Level label does not correct");
        Assert.assertEquals(page.lblBreadcrumb.getText(),String.format("%s (%s)",userCode,username),"FAILED! Breadcrumb bar not display login account");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can search by login ID
     * @pre-condition:
     *           1. Log in successfully by SAD level
     *
     * @steps: 1. Navigate Agency Management > Position Taking Listing
     *          2. Input Login ID and click submit button
     *
     * @expect: 1. Verify Login ID displays
     */
    @TestRails(id="708")
    @Test (groups = {"smoke"})
    public void Agent_AM_Position_Taking_Listing_004() {
        log("@title:Verify can search by login ID");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);
        List<String> usernameLst = page.tblDownline.getColumn(page.usernameCol, false);

        log("Step 2. Input Login ID and click submit button");
        page.search(usernameLst.get(0),"","","");

        log("Verify 1. Verify Login ID displays");
        List<ArrayList<String>> lstResult = page.tblDownline.getRowsWithoutHeader(1,false);
        Assert.assertEquals(lstResult.get(0).get(page.usernameCol-1),usernameLst.get(0),"FAILED! Username not display as expected");
        Assert.assertEquals(lstResult.size(),1,"FAILED! Result should display 1 record");
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can update PT  for all sports
     * @pre-condition:
     *           1. Log in successfully by SAD level
     *
     * @steps: 1. Navigate Agency Management > Position Taking Listing
     *         2. Select a downline and select all sport
     *         3. Update SAD Preset and click Update
     *
     * @expect: 1. Verify PT for Soccer, Cricket, Tennis, Basketball Other is updated and there is a green check in Update Status column
     */
    @TestRails(id="709")
    @Test (groups = {"smoke"})
    public void Agent_AM_Position_Taking_Listing_012() {
        log("@title: Verify can update PT for all sports");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);
        List<String> usernameLst = page.tblDownline.getColumn(page.usernameCol, false);
        int PT = 10;

        log("Step  2. Select a downline and select all sport");
        page.search(usernameLst.get(0),"","","");
        List<String> lstPTInfoBeforeUpdate = page.getPTofAccount(usernameLst.get(0));
        lstPTInfoBeforeUpdate.set(page.soccerCol - 1,Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.cricketCol -1,Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.fancytCol -1,Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.tennisCol -1,Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.basketballCol -1,Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.horseRacingCol -1,Integer.toString(PT));
        lstPTInfoBeforeUpdate.set(page.otherCol -1,Integer.toString(PT));

        HashMap<String, Boolean> sport = new HashMap<String, Boolean>()
        {
            {
                put("Soccer", true);
                put("Cricket",true);
                put("Fancy",true);
                put("Tennis",true);
                put("Basketball",true);
                put("Horse Racing",true);
                put("Other", true);
            }
        };

        log("Step 3. Update SAD Preset  and click Update");
        page.updatePT(usernameLst.get(0),PT,sport);

        log("Verify 1. Verify PT for Soccer, Cricket, Tennis, Basketball Other is updated and there is a green check in Update Status column");

        List<String> lstPTInfo = page.getPTofAccount(usernameLst.get(0));
        Assert.assertEquals(lstPTInfo,lstPTInfoBeforeUpdate,"FAILED! Position Taking Listing Data does not match");
        Assert.assertTrue(page.verifyUpdateStatus(usernameLst.get(0),true),"FAILED! Update Status not display green check");
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can update PT  for selected sport
     * @pre-condition:
     *           1. Log in successfully by SAD level
     *
     * @steps: 1. Navigate Agency Management > Position Taking Listing
     *         2. Select a downline and only select Soccer sport
     *         3. Update SAD Preset and click update button
     *
     * @expect: 1. Verify only PT of soccer is updated, other sport is not affected
     */
    @TestRails(id="710")
    @Test (groups = {"smoke"})
    public void Agent_AM_Position_Taking_Listing_014() {
        log("@title: Verify can update PT  for selected sport");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, POSITION_TAKING_LISTING, PositionTakingListingPage.class);
        List<String> usernameLst = page.tblDownline.getColumn(page.loginIDCol, false);
        int PT = 2;

        log("Step 2. Select a downline and only select Soccer sport");
        page.search(usernameLst.get(0),"","","");
        List<String> lstPTInfoBeforeUpdate = page.getPTofAccount(usernameLst.get(0));
        lstPTInfoBeforeUpdate.set(page.soccerCol - 1,Integer.toString(PT));
        HashMap<String, Boolean> sport = new HashMap<String, Boolean>()
        {
            {
                put("Soccer", true);
                put("Cricket",false);
                put("Fancy",false);
                put("Tennis",false);
                put("Basketball",false);
                put("Horse Racing",false);
                put("Other", false);
            }
        };
        log("Step 3. Update SAD Preset and click update button");
        page.updatePT(usernameLst.get(0),PT,sport);

        log("Verify 1. Verify Login ID displays");
        page.enableSport(sport);
        List<String> lstPTInfo = page.getPTofAccount(usernameLst.get(0));
        Assert.assertEquals(lstPTInfo,lstPTInfoBeforeUpdate,"FAILED! Position Taking Listing Data does not match");
        Assert.assertTrue(page.verifyUpdateStatus(usernameLst.get(0),true),"FAILED! Update Status not display green check");
        log("INFO: Executed completely");
    }

}

