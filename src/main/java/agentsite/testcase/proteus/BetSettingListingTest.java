package agentsite.testcase.proteus;

import agentsite.pages.agentmanagement.BetSettingListingPage;
import agentsite.pages.agentmanagement.PositionTakingListingPage;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Arrays;
import java.util.HashSet;

import static common.AGConstant.ALL;
import static common.AGConstant.AgencyManagement.PositionTakingListing.TABLE_SELECTION_PT;
import static common.AGConstant.GENERAL;
import static common.MemberConstants.LBL_SOCCER_SPORT;
import static common.MemberConstants.PS38;

public class BetSettingListingTest extends BaseCaseTest {
    @TestRails(id = "4031")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4031(String directDownlineAccount) {
        log("@title: Validate can update Min Bet, Max Bet and Max Per Match of PS38 product for all sport successfully ");
        log("Step Precondition. Login Agent Site with PO level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();

        log("Step 2. Select PS38 product and Sport as ALL > click Submit");
        page.filterPS38Product(directDownlineAccount,"","All",PS38,"ALL","");

        log("Step 3. Select an agency/player");
        log("Step 4: Enter valid Min Bet. Max Bet and Max Per Match > click Update");

        log("Verify 1: Updated Min Bet, Max Bet and Max Per Match is displayed correctly on Bet Setting list");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4032")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4032(String directDownlineAccount) {
        log("@title: Validate update bet setting with valid min bet Setting ");
        log("Step Precondition. Login Agent Site with PO level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        log("Step 2. Select PS38 product and Sport as ALL > click Submit");

        log("Step 3. Select an agency/player");
        log("Step 4: Enter valid Min Bet > click Update");

        log("Verify 1: Updated Min Bet is displayed correctly on Bet Setting list");

        log("INFO: Executed completely");
    }
    @TestRails(id = "4033")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4033(String directDownlineAccount) {
        log("@title: Validate update bet setting with valid max bet Setting");
        log("Step Precondition. Login Agent Site with PO level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        log("Step 2. Select PS38 product and Sport as ALL > click Submit");

        log("Step 3. Select an agency/player");
        log("Step 4: Enter valid Max Bet > click Update");

        log("Verify 1: Updated Max Bet is displayed correctly on Bet Setting list");

        log("INFO: Executed completely");
    }
    @TestRails(id = "4034")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4034(String directDownlineAccount) {
        log("@title: Validate update bet setting with valid Max Per Match Setting");
        log("Step Precondition. Login Agent Site with PO level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        log("Step 2. Select PS38 product and Sport as ALL > click Submit");

        log("Step 3. Select an agency/player");
        log("Step 4: Enter valid Max Per Match > click Update");

        log("Verify 1: Updated Max Per Match is displayed correctly on Bet Setting list");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4035")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4035(String directDownlineAccount) {
        log("@title: Validate can update Min Bet, Max Bet and Max Per Match of PS38 product for specific sport with all League successfully");
        log("Step Precondition. Login Agent Site with PO level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        log("Step 2. Select PS38 product and Sport as ALL > click Submit");

        log("Step 3. Select sport as Soccer and All as League > click Submit");
        log("Step 4:Select an agency/player");
        log("Step 5: Enter valid Min Bet. Max Bet and Max Per Match > click Update");

        log("Verify 1: Updated Min Bet, Max Bet and Max Per Match is applied correctly for specific sport with all League correctly");

        log("INFO: Executed completely");
    }
    @TestRails(id = "4036")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4036(String directDownlineAccount) {
        log("@title: Validate can update Min Bet, Max Bet and Max Per Match of PS38 product for specific Sport and League successfully");
        log("Step Precondition. Login Agent Site with PO level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        log("Step 2. Select PS38 product and Sport as ALL > click Submit");

        log("Step 3. Select sport as Soccer and any League not All > click Submit");
        log("Step 4: Select an agency/player");
        log("Step 5: Enter valid Min Bet. Max Bet and Max Per Match > click Update");

        log("Verify 1: Updated Min Bet, Max Bet and Max Per Match is applied correctly for specific sport and Leauge correctly");

        log("INFO: Executed completely");
    }


}
