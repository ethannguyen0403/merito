package agentsite.testcase.all.agencymanagement;

import agentsite.controls.DateTimePickerOld;
import com.paltech.utils.DateUtils;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.AnnoucementPage;
import agentsite.pages.all.components.ConfirmPopup;

import static agentsite.common.AGConstant.Announcement.ADD_ANNOUNCEMENT;
import static agentsite.common.AGConstant.Announcement.INFO;
import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.ANNOUNCEMENT;

public class AnnouncementTest extends BaseCaseMerito {
    @Test(groups = {"http_request"})
    public void Agent_AM_Announcement_001() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management >Announcement");
        AnnoucementPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, ANNOUNCEMENT, AnnoucementPage.class);

        log("Verify 1. Announcement page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke"})
    @Parameters({"username", "memberAccount", "password"})
    public void Agent_AM_Announcement_002(String username, String memberAccount, String password) throws Exception {
        log("@title: Verify Announcement UI display correct");
        log("Step 1. Navigate Agency Management >Announcement");
        AnnoucementPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, ANNOUNCEMENT, AnnoucementPage.class);

        log("Verify 1. Verify UI Announcement display correctly");
        Assert.assertEquals(page.getPageTitle(), ANNOUNCEMENT, "FAILED! Page title is incorrect display");
        Assert.assertEquals(page.lblInfo.getText(), INFO, "FAILED! Info label is incorrect display");
        Assert.assertEquals(page.btnAddAnnouncement.getText(), ADD_ANNOUNCEMENT, "FAILED! Add Annoucement is incorrect display");
        Assert.assertTrue(page.tblAnnouncement.isDisplayed(), "FAILED! Annoucement table is not displayed ");
        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke"})
    @Parameters({"username", "memberAccount", "password"})
    public void Agent_AM_Announcement_003(String username, String memberAccount, String password) throws Exception {
        log("@title: Verify can add, update and deleted announcement");
        log("Step 1. Navigate Agency Management >Announcement");
        AnnoucementPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, ANNOUNCEMENT, AnnoucementPage.class);
        String announcementMsg = "This is announcement is created by auto scrips at 16/02/2022 05:14:15";

        log("Step 2. Click on Add Announcement button");
        log("Step 3. Input Announcement message and click Save button");
        page.addAnnouncement(announcementMsg);

        log("Verify 1. Verify announcement is successfully add");
        Assert.assertTrue(page.isAnnouncementDisplay(announcementMsg), "FAILED! announcement does not display");

        log("Step 4.Edit the announcement to a new message");
        page.updateAnnoucement(announcementMsg, true, String.format("New Message %s", announcementMsg), "", "", "All");

        log("Verify 2. Verify announcement is successfully updated");
        Assert.assertTrue(page.isAnnouncementDisplay(announcementMsg), "FAILED! announcement does not display");

        log("Step 5.Delete announcement");
        String confirmMessage = page.deleteAnnouncement(announcementMsg,true);

        log("Verify 3. Verify a confirm message display and announcement is deleted after click on button");
        Assert.assertEquals(confirmMessage, "Are you sure you want to delete this announcement?", "FAILED! Confirm message not display correctly");
        Assert.assertFalse(page.isAnnouncementDisplay(announcementMsg), "FAILED! announcement display after delete");

        log("INFO: Executed completely");

    }

    @Test(groups = {"interaction"})
    @Parameters({"username", "memberAccount", "password"})
    public void Agent_AM_Announcement_004(String username, String memberAccount, String password) throws Exception {
        log("@title: Member display announcement when agent set for a Specific player");
        log("Step 1. Navigate Agency Management >Announcement");
        //String announcementMsg = "This is announcement is created by auto scrips " + DateUtils.getMilliSeconds();
        String announcementMsg= "This is announcement is created by auto scrips 1665731123774";
        AnnoucementPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, ANNOUNCEMENT, AnnoucementPage.class);
        String todate = DateUtils.getDate(2,"d/MMM/YYYY hh:mm","GMT-4");
        try {
            log("Step 2. Add annoucement, active and set for a specific player and set available in today");
            page.addAnnouncement(announcementMsg);
            page.updateAnnoucement(announcementMsg,true,"","",todate,memberAccount);

            log("Step 3 Login member site (SAT and /plus UI)");
            loginMember(memberAccount,password);
            memberHomePage.getMarqueeMessage();

            log("Verify 1 the announcement message display in marquee bar");
            Assert.assertEquals(memberHomePage.getMarqueeMessage(),announcementMsg,"Failed! Announcement message is incorrect display in member site");

        }finally {
            loginAgent(username,password,true);
            page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, ANNOUNCEMENT, AnnoucementPage.class);
            log("Step Postcondition: Delete announcement");
            page.deleteAnnouncement(announcementMsg,true);
        }

        log("INFO: Executed completely");
    }
    @Test(groups = {"interaction"})
    @Parameters({"username", "memberAccount", "password"})
    public void Agent_AM_Announcement_005(String username, String memberAccount, String password) throws Exception {
        log("@title: The announcement not display when inactive");
        log("Step 1. Navigate Agency Management >Announcement");
        String announcementMsg = "This is announcement is created by auto scrips " + DateUtils.getMilliSeconds();
        AnnoucementPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, ANNOUNCEMENT, AnnoucementPage.class);
        String todate = DateUtils.getDate(2,"d/MMM/YYYY hh:mm","GMT-4");
        try {log("Step 2. Add announcement, set for a specific player and set from and to in today and inactive status");
            page.addAnnouncement(announcementMsg);
            page.updateAnnoucement(announcementMsg,false,"","",todate,memberAccount);

            log("Step 3 Login member site (SAT and /plus UI)");
            loginMember(memberAccount,password);
            memberHomePage.getMarqueeMessage();

            log("Verify 1.The announcement message not display");
            Assert.assertFalse(memberHomePage.getMarqueeMessage().contains(announcementMsg),"Failed! Announcement message is incorrect display in member site");

        }finally {
            loginAgent(username,password,true);
            page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, ANNOUNCEMENT, AnnoucementPage.class);
            log("Step Post-condition: Delete announcement");
            page.deleteAnnouncement(announcementMsg,true);
        }

        log("INFO: Executed completely");
    }
    @Test(groups = {"interaction"})
    @Parameters({"username", "memberAccount", "password"})
    public void Agent_AM_Announcement_006(String username, String memberAccount, String password) throws Exception {
        log("@title: The announcement not display if set invalid time range");
        log("Step 1. Navigate Agency Management >Announcement");
        String announcementMsg = "This is announcement is created by auto scrips " + DateUtils.getMilliSeconds();
        AnnoucementPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, ANNOUNCEMENT, AnnoucementPage.class);
        String yesterday = DateUtils.getDate(-1,"d/MMM/YYYY hh:mm","GMT-4");
        try {
            log("Step 2. Add announcement, active and set for a specific player and set from and to in yesterday");
            page.addAnnouncement(announcementMsg);
            page.updateAnnoucement(announcementMsg,true,"",yesterday,yesterday,memberAccount);

            log("Step 3 Login member site (SAT and /plus UI)");
            loginMember(memberAccount,password);
            memberHomePage.getMarqueeMessage();

            log("Verify 1 the announcement message not display");
            Assert.assertFalse(memberHomePage.getMarqueeMessage().contains(announcementMsg),"Failed! Announcement message is incorrect display in member site");

        }finally {
            loginAgent(username,password,true);
            page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, ANNOUNCEMENT, AnnoucementPage.class);
            log("Step Postcondition: Delete announcement");
            page.deleteAnnouncement(announcementMsg,true);
        }

        log("INFO: Executed completely");
    }

}

