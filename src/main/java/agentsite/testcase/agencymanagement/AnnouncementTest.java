package agentsite.testcase.agencymanagement;

import agentsite.pages.agentmanagement.AnnoucementPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.AGConstant.Announcement.ADD_ANNOUNCEMENT;
import static common.AGConstant.Announcement.INFO;
import static common.AGConstant.HomePage.ANNOUNCEMENT;
import static common.AGConstant.timeZone;

public class AnnouncementTest extends BaseCaseTest {
    @TestRails(id = "752")
    @Test(groups = {"http_request"})
    public void Agent_AM_Announcement_752() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management >Announcement");
        AnnoucementPage page = agentHomePage.navigateAnnoucementPage();

        log("Verify 1. Announcement page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    @TestRails(id = "753")
    @Test(groups = {"smoke_sat"})
    public void Agent_AM_Announcement_753()  {
        log("@title: Verify Announcement UI display correct");
        log("Step 1. Navigate Agency Management >Announcement");
        AnnoucementPage page = agentHomePage.navigateAnnoucementPage();

        log("Verify 1. Verify UI Announcement display correctly");
        Assert.assertEquals(page.header.lblPageTitle.getText(), ANNOUNCEMENT, "FAILED! Page title is incorrect display");
        Assert.assertEquals(page.lblInfo.getText(), INFO, "FAILED! Info label is incorrect display");
        Assert.assertEquals(page.btnAddAnnouncement.getText(), ADD_ANNOUNCEMENT, "FAILED! Add Annoucement is incorrect display");
        Assert.assertTrue(page.tblAnnouncement.isDisplayed(), "FAILED! Annoucement table is not displayed ");
        log("INFO: Executed completely");
    }

    @TestRails(id = "754")
    @Test(groups = {"smoke_sat"})
    public void Agent_AM_Announcement_754() {
        log("@title: Verify can add, update and deleted announcement");
        log("Step 1. Navigate Agency Management >Announcement");
        AnnoucementPage page = agentHomePage.navigateAnnoucementPage();
        String dateTime = DateUtils.getDate(0, "yyyy/MM/dd", timeZone) + " " + DateUtils.getMilliSeconds();
        String announcementMsg = String.format("This is announcement is created by auto scrips at %s", dateTime);

        log("Step 2. Click on Add Announcement button");
        log("Step 3. Input Announcement message and click Save button");
        page.addAnnouncement(announcementMsg);

        log("Verify 1. Verify announcement is successfully add");
        Assert.assertTrue(page.isAnnouncementDisplay(announcementMsg), "FAILED! announcement does not display");

        log("Step 4.Edit the announcement to a new message");
        page.updateAnnoucement(announcementMsg, true, String.format("New Message %s", announcementMsg), "", "", "All");

        log("Verify 2. Verify announcement is successfully updated");
        Assert.assertTrue(page.isAnnouncementDisplay(String.format("New Message %s", announcementMsg)), "FAILED! announcement does not display");

        log("Step 5.Delete announcement");
        String confirmMessage = page.deleteAnnouncement(String.format("New Message %s", announcementMsg), true);

        log("Verify 3. Verify a confirm message display and announcement is deleted after click on button");
        Assert.assertEquals(confirmMessage, "Are you sure you want to delete this announcement?", "FAILED! Confirm message not display correctly");
        Assert.assertTrue(page.isAnnouncementDisplay(String.format("New Message %s", announcementMsg)), "FAILED! announcement display after delete");

        log("INFO: Executed completely");

    }
    @TestRails(id = "3653")
    @Test(groups = {"interaction"})
    @Parameters({"username", "memberAccount", "password"})
    public void Agent_AM_Announcement_3653(String username, String memberAccount, String password) throws Exception {
        log("@title: Member display announcement when agent set for a Specific player");
        log("Step 1. Navigate Agency Management >Announcement");
        String dateTime = DateUtils.getDate(0, "yyyy/MM/dd", timeZone) + " " + DateUtils.getMilliSeconds();
        String announcementMsg = String.format("This is announcement is created by auto scrips at %s", dateTime);
        AnnoucementPage page = agentHomePage.navigateAnnoucementPage();
        String todate = DateUtils.getDate(2, "d/MMM/YYYY hh:mm", "GMT-4");
        try {
            log("Step 2. Add annoucement, active and set for a specific player and set available in today");
            page.addAnnouncement(announcementMsg);
            page.updateAnnoucement(announcementMsg, true, "", "", todate, memberAccount);

            log("Step 3 Login member site (SAT and /plus UI)");
            loginMember(memberAccount, password);
            memberHomePage.header.getMarqueeMessage();

            log("Verify 1 the announcement message display in marquee bar");
            Assert.assertEquals(memberHomePage.header.getMarqueeMessage(), announcementMsg, "Failed! Announcement message is incorrect display in member site");

        } finally {
            loginAgent(username, password, true);
            page = agentHomePage.navigateAnnoucementPage();
            log("Step Postcondition: Delete announcement");
            page.deleteAnnouncement(announcementMsg, true);
        }

        log("INFO: Executed completely");
    }

    @TestRails(id = "3654")
    @Test(groups = {"interaction"})
    @Parameters({"username", "memberAccount", "password"})
    public void Agent_AM_Announcement_3654(String username, String memberAccount, String password) throws Exception {
        log("@title: The announcement not display when inactive");
        log("Step 1. Navigate Agency Management >Announcement");
        String dateTime = DateUtils.getDate(0, "yyyy/MM/dd", timeZone) + " " + DateUtils.getMilliSeconds();
        String announcementMsg = String.format("This is announcement is created by auto scrips at %s", dateTime);
        AnnoucementPage page = agentHomePage.navigateAnnoucementPage();
        String todate = DateUtils.getDate(2, "d/MMM/YYYY hh:mm", "GMT-4");
        try {
            log("Step 2. Add announcement, set for a specific player and set from and to in today and inactive status");
            page.addAnnouncement(announcementMsg);
            page.updateAnnoucement(announcementMsg, false, "", "", todate, memberAccount);

            log("Step 3 Login member site (SAT and /plus UI)");
            loginMember(memberAccount, password);
            memberHomePage.header.getMarqueeMessage();

            log("Verify 1.The announcement message not display");
            Assert.assertFalse(memberHomePage.header.getMarqueeMessage().contains(announcementMsg), "Failed! Announcement message is incorrect display in member site");

        } finally {
            loginAgent(username, password, true);
            page = agentHomePage.navigateAnnoucementPage();
            log("Step Post-condition: Delete announcement");
            page.deleteAnnouncement(announcementMsg, true);
        }

        log("INFO: Executed completely");
    }

    @TestRails(id = "3655")
    @Test(groups = {"interaction"})
    @Parameters({"username", "memberAccount", "password"})
    public void Agent_AM_Announcement_3655(String username, String memberAccount, String password) throws Exception {
        log("@title: The announcement not display if set invalid time range");
        log("Step 1. Navigate Agency Management >Announcement");
        String dateTime = DateUtils.getDate(0, "yyyy/MM/dd", timeZone) + " " + DateUtils.getMilliSeconds();
        String announcementMsg = String.format("This is announcement is created by auto scrips at %s", dateTime);
        AnnoucementPage page = agentHomePage.navigateAnnoucementPage();
        String yesterday = DateUtils.getDate(-1, "d/MMM/YYYY hh:mm", "GMT-4");
        try {
            log("Step 2. Add announcement, active and set for a specific player and set from and to in yesterday");
            page.addAnnouncement(announcementMsg);
            page.updateAnnoucement(announcementMsg, true, "", yesterday, yesterday, memberAccount);

            log("Step 3 Login member site (SAT and /plus UI)");
            loginMember(memberAccount, password);
            memberHomePage.header.getMarqueeMessage();

            log("Verify 1 the announcement message not display");
            Assert.assertFalse(memberHomePage.header.getMarqueeMessage().contains(announcementMsg), "Failed! Announcement message is incorrect display in member site");

        } finally {
            loginAgent(username, password, true);
            page = agentHomePage.navigateAnnoucementPage();
            log("Step Postcondition: Delete announcement");
            page.deleteAnnouncement(announcementMsg, true);
        }

        log("INFO: Executed completely");
    }

}

