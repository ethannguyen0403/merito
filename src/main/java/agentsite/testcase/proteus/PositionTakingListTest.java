package agentsite.testcase.proteus;

import agentsite.pages.agentmanagement.PositionTakingListingPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Arrays;
import java.util.HashSet;

import static common.AGConstant.ALL;
import static common.AGConstant.AgencyManagement.PositionTakingListing.*;
import static common.AGConstant.GENERAL;
import static common.MemberConstants.*;

public class PositionTakingListTest extends BaseCaseTest {
    @TestRails(id = "4081")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4081(String directDownlineAccount) {
        log("@title: Validate can update PT setting of PS38 product for all sports successfully on Position Taking Listing ");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Filter with any player and PS38 product");
        log("Step 3. Select Sport as All then click Submit");
        page.positionTakingListingPS38.search(directDownlineAccount, "", PS38, ALL, "");
        log("Step 4. Update level Preset for any downline level of Soccer sort");
        int ptAmount = 10;
        page.positionTakingListingPS38.updatePT(directDownlineAccount, "Preset", "" + ptAmount);
        log("Verify 1: Success icon is displayed");
        Assert.assertTrue(page.positionTakingListingPS38.verifyUpdateStatus(directDownlineAccount, true), "FAILED! Success icon is not displayed");
        log("Verify 2: Updated PT setting for Soccer sport is applied correctly for all other sports");
        String levelPreset = page.positionTakingListingPS38.defineSelectPTControl("Preset", null).getText().trim().split(" ")[0];
        Assert.assertEquals(new HashSet<>(page.positionTakingListingPS38.getPTAccountListByRow(directDownlineAccount, levelPreset)), new HashSet<>(
                Arrays.asList(""+ptAmount)), "FAILED! PT is not apply for all sport");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4082")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4082(String directDownlineAccount) {
        log("@title: Validate can update PT setting of PS38 product for specific sport with all League successfully on Position Taking Listing ");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Filter with any player and PS38 product");
        log("Step 3. Select Sport as Soccer, League: All then click Submit");
        page.positionTakingListingPS38.search(directDownlineAccount, "", PS38, LBL_SOCCER_SPORT, GENERAL);
        log("Step 4. Update level Preset for any downline level of Soccer sort");
        int ptAmount = 10;
        page.positionTakingListingPS38.updatePT(directDownlineAccount, "Preset", "" + ptAmount);
        log("Verify 1: Success icon is displayed");
        Assert.assertTrue(page.positionTakingListingPS38.verifyUpdateStatus(directDownlineAccount, true), "FAILED! Success icon is not displayed");
        log("Verify 2: Updated PT setting for specific sport with all league is displayed correctly");
        String levelPreset = page.positionTakingListingPS38.defineSelectPTControl("Preset", null).getText().trim().split(" ")[0];
        Assert.assertEquals(new HashSet<>(page.positionTakingListingPS38.getPTAccountListByRow(directDownlineAccount, levelPreset)), new HashSet<>(
                Arrays.asList(""+ptAmount)), "FAILED! PT is not apply for all sport");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4083")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4083(String directDownlineAccount) {
        log("@title: Validate can update PT setting of PS38 product for specific Sport and League successfully on Position Taking Listing");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Filter with any player and PS38 product");
        log("Step 3. Select Sport as Soccer, specific League then click Submit");
        page.positionTakingListingPS38.search(directDownlineAccount, "", PS38, LBL_SOCCER_SPORT, "1");
        log("Step 4. Update level Preset for any downline level of Soccer sort");
        int ptAmount = 10;
        page.positionTakingListingPS38.updatePT(directDownlineAccount, "Preset", "" + ptAmount);
        log("Verify 1: Success icon is displayed");
        Assert.assertTrue(page.positionTakingListingPS38.verifyUpdateStatus(directDownlineAccount, true), "FAILED! Success icon is not displayed");
        log("Verify 2: Updated PT setting for specific Sport and specific League is displayed correctly");
        String levelPreset = page.positionTakingListingPS38.defineSelectPTControl("Preset", null).getText().trim().split(" ")[0];
        Assert.assertEquals(new HashSet<>(page.positionTakingListingPS38.getPTAccountListByRow(directDownlineAccount, levelPreset)), new HashSet<>(
                Arrays.asList(""+ptAmount)), "FAILED! PT is not apply for all sport");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4084")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4084(String directDownlineAccount) {
        log("@title: Validate Copy settings to all Specific Sports and Leagues checkbox when Sport is selected as All");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Filter with any player and PS38 product");
        log("Step 3. Select Sport as All");
        page.positionTakingListingPS38.search(directDownlineAccount, "", PS38, ALL, "");
        log("Verify 1: Copy settings to all Specific Sports and Leagues checkbox is ticked successfully");
        Assert.assertTrue(page.positionTakingListingPS38.chkCopyAll.isSelected(), "FAILED! The checkbox Copy all is not selected");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4085")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4085(String directDownlineAccount) {
        log("@title: Validate Copy settings to all Specific Sports and Leagues checkbox when selecting a specific sport");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Filter with any player and PS38 product");
        log("Step 3.  Select and specific Sport and specific League");
        page.positionTakingListingPS38.search(directDownlineAccount, "", PS38, LBL_SOCCER_SPORT, "1");
        log("Verify 1: Copy settings to all Specific Sports and Leagues checkbox is ticked successfully");
        Assert.assertTrue(!page.positionTakingListingPS38.chkCopyAll.isSelected(), "FAILED! The checkbox Copy all is selected");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4086")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount", "dowlineLevel"})
    public void PS38_Agent_TC4086(String directDownlineAccount, String dowlineLevel) {
        log("@title: Validate 'Min Pos', 'Max Pos', '[logged-in level] Preset' and '[logged-in level] Extra PT' dropdowns of direct downline");
        log("Step 1. Navigate Agency Management > Position Taking Listing");
        PositionTakingListingPage page = agentHomePage.navigatePositionTakingListingPage();

        log("Step 2. Filter with any player and PS38 product");
        page.positionTakingListingPS38.search(directDownlineAccount, "", PS38, "", "");
        log("Verify 1: Min Pos', 'Max Pos', '[logged-in level] Preset' and '[logged-in level] Extra PT' dropdowns of direct downline is enabled successfully for editing");
        page.positionTakingListingPS38.verifySelectPTDropdown(dowlineLevel, TABLE_SELECTION_PT);
        log("INFO: Executed completely");
    }


}
