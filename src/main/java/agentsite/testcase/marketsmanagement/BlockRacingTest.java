package agentsite.testcase.marketsmanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.marketsmanagement.BlockRacingPage;
import agentsite.pages.marketsmanagement.blockracing.BlockedUserPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.ultils.maketmanagement.BlockRacingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Objects;

import static common.AGConstant.MarketsManagement.BlockRacing.LBL_UPDATE_SUCCESS_MSG;
import static common.AGConstant.timeZone;

public class BlockRacingTest extends BaseCaseTest {
    /**
     * @title: Validate there is no http responded error returned
     * @pre-condition: 1. Log in successfully from PO level
     * @steps: 1. Navigate Markets Management >Block Racing
     * @expect: 1. Verify there is no console error display
     */
    @TestRails(id = "772")
    @Test(groups = {"http_request"})
    public void Agent_MM_Block_Racing_TC772() {
        log("@title: Validate there is no http responded error returned");

        log("Step 1. Navigate Markets Management >Block Racing");
        agentHomePage.navigateBlockRacingPage();

        log("Verify  1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3696")
    @Test(groups = {"regression_po"})
    public void Agent_MM_Block_Racing_TC3696() {
        log("@title: Validate Block Racing - Current tab UI display correctly");
        log("Step 1. Navigate Markets Management > Block Racing");
        BlockRacingPage blockRacingPage = agentHomePage.navigateBlockRacingPage();
        log("Step 2. Active Current tab");
        blockRacingPage.selectBlockingTab("Current");

        log("Validate  Current tab UI display correctly");
        blockRacingPage.verifyUIDisplayCorrect(true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3697")
    @Test(groups = {"regression_po"})
    public void Agent_MM_Block_Racing_TC3697() {
        log("@title: Validate Block Racing - Blocking tab UI display correctly");
        log("Step 1. Navigate Markets Management > Block Racing");
        BlockRacingPage blockRacingPage = agentHomePage.navigateBlockRacingPage();
        log("Step 2. Active Blocking tab");
        blockRacingPage.selectBlockingTab("Blocking");

        log("Validate  Current tab UI display correctly");
        blockRacingPage.verifyUIDisplayCorrect(true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3698")
    @Test(groups = {"regression","MER.Maintenance.2024.V.6.0"})
    public void Agent_MM_Block_Racing_TC3698() {
        log("@title: Validate can Block for Horse Racing");
        String sportName = "Horse Racing";
        AccountInfo accountInfo = ProfileUtils.getProfile();
        AccountInfo accountUserInfo = DownLineListingUtils.getAllDownLineUsers(_brandname, accountInfo.getUserCode(), accountInfo.getUserID()).get(0);
        log("Step 1. Navigate Markets Management > Block Racing");
        BlockRacingPage blockRacingPage = agentHomePage.navigateBlockRacingPage();
        log("Step 2. Active Blocking tab and select Horse Racing");
        log("Step 3. Select downline and  select venue name");
        log("Step 4. Click Update button");
        Event event = BlockRacingUtils.getEventListRacing(sportName).get(0);
        Market market = BlockRacingUtils.getMarketListRacing(event, sportName).get(0);
        if (Objects.isNull(event)) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for " + sportName);
        }
        String updatedMsg = blockRacingPage.block(sportName, event, accountUserInfo.getUserCode(),market);
        String blockDateTime = DateUtils.getDate(0, "yyyy-MM-dd HH:mm", timeZone);

        log("Validate the message update successfully");
        Assert.assertEquals(updatedMsg,LBL_UPDATE_SUCCESS_MSG, "FAILED! Update setting success message does not display correct");
        blockRacingPage.btnCloseUpdateSetting.click();
        BlockedUserPopup blockedUserPopup = null;
        try {
            log("Step 5. Click Current tab  and search the venue name");
            blockRacingPage.selectBlockingTab("Current");
            blockRacingPage.searchVenueName(event.getEventName());

            log("Validate block icon  display at the venue name\n" +
                    "Click cell, the block info display correctly");
            blockedUserPopup = blockRacingPage.clickVenueMarketCell(event.getEventName(), market.getMarketName());
            blockedUserPopup.verifyBlockedInfoDisplayCorrect(accountUserInfo, accountInfo, blockDateTime);
            log("INFO: Executed completely");
        } finally {
            log("Post-condition: Unblock the blocked event");
            blockedUserPopup.unblockUser(accountUserInfo.getUserCode());
        }
    }

    @TestRails(id = "3699")
    @Test(groups = {"regression","MER.Maintenance.2024.V.6.0"})
    public void Agent_MM_Block_Racing_TC3699() {
        log("@title: Validate can Block for Greyhound Racing");
        String sportName = "Greyhound Racing";
        AccountInfo accountInfo = ProfileUtils.getProfile();
        AccountInfo accountUserInfo = DownLineListingUtils.getAllDownLineUsers(_brandname, accountInfo.getUserCode(), accountInfo.getUserID()).get(0);
        log("Step 1. Navigate Markets Management > Block Racing");
        BlockRacingPage blockRacingPage = agentHomePage.navigateBlockRacingPage();
        log("Step 2. Active Blocking tab and select Greyhound Racing");
        log("Step 3. Select downline and  select venue name");
        log("Step 4. Click Update button");
        Event event = BlockRacingUtils.getEventListRacing(sportName).get(0);
        Market market = BlockRacingUtils.getMarketListRacing(event, sportName).get(0);
        if (Objects.isNull(event)) {
            throw new SkipException("INFO: Skipping this test case as have no event in today for " + sportName);
        }
        String updatedMsg = blockRacingPage.block(sportName, event, accountUserInfo.getUserCode(), market);
        String blockDateTime = DateUtils.getDate(0, "yyyy-MM-dd HH:mm", timeZone);

        log("Validate the message update successfully");
        Assert.assertEquals(updatedMsg,LBL_UPDATE_SUCCESS_MSG, "FAILED! Update setting success message does not display correct");
        blockRacingPage.btnCloseUpdateSetting.click();
        BlockedUserPopup blockedUserPopup = null;
        try {
            log("Step 5. Click Current tab  and search the venue name");
            blockRacingPage.selectBlockingTab("Current");
            blockRacingPage.searchVenueName(event.getEventName());

            log("Validate block icon  display at the venue name\n" +
                    "Click cell, the block info display correctly");
            blockedUserPopup = blockRacingPage.clickVenueMarketCell(event.getEventName(), market.getMarketName());
            blockedUserPopup.verifyBlockedInfoDisplayCorrect(accountUserInfo, accountInfo, blockDateTime);
        } finally {
            log("Post-condition: Unblock the blocked event");
            blockedUserPopup.unblockUser(accountUserInfo.getUserCode());
            log("INFO: Executed completely");
        }
    }

}
