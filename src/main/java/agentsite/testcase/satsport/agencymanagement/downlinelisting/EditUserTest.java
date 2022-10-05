package agentsite.testcase.satsport.agencymanagement.downlinelisting;

import com.paltech.driver.DriverManager;
import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DownLineListingPage;
import agentsite.pages.all.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.ultils.agencymanagement.EventBetSizeSettingUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.paltech.utils.StringUtils.decrypt;
import static agentsite.common.AGConstant.HomePage.*;

/*import pages.sat.beforelogin.popups.LoginPopup;
import pages.sat.tabexchange.HomePage;
import pages.sat.home.ChangePasswordPage;
import pages.sat.tabexchange.InPlayPage;
import pages.sat.tabexchange.SoccerPage;
import pages.sat.tabexchange.components.SportPage;*/

public class EditUserTest extends BaseCaseMerito {
    /**
     * @title: Verify Can change password
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     *         2. Click on Edit icon of any Member level
     *         3. Input new password in  password textbox and click Submit
     *         4. Login the account in member site with new password
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     *          2. Change password page display after login member site
     */
    @Test (groups = {"smoke"})
    @Parameters({"password"})
    public void Agent_AM_Downline_Listing_Edit_User_003(String password) throws Exception {
        log("@title: Verify Can change password");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE");
        String loginID =listAccount.get(0).getLoginID();
        String passwordEdit ="1234qwert";
        String passwordDecrypt = decrypt(password);

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"All","Member");
        page.clickEditIcon(loginID);

        log("Step 3. Input new password in  password textbox and click Submit");
        page.editDownlinePopup.accInfoSection.inputInfo(passwordEdit,"","","","","","");
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");

      /*  log("Step 4. Login the account in member site with new password");
        agentHomePage.logout();
        BaseCaseMember.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passwordEdit);
        log("Verify 2. Change password page display after login member site");
        ChangePasswordPage changePaswordPage = new ChangePasswordPage();

        log("Verify 4. Verify change password page display after login");
        Assert.assertEquals(changePaswordPage.getPageUrl(),String.format("%s#/2/change-password",environment.getMemberSiteURL()),"FAILED! Change password page not display when agent update password for player");

        log("Post-condition: Update password to old");
        changePaswordPage.changePassword(passwordEdit,passwordDecrypt);*/

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can inactive and reactive the account
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Downline Listing
     *          2. Click on Edit icon of any Member level
     *          3. Change account status to Inactive and click on Submit button
     *              Click Ok button on Edit Member popup
     *          4. Login member site with inactive account
     *          5. Back to Agent site and click edit the account again
     *          6. Change account status to Active and Submit and click Ok button
     *          7. Login member with the account
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     *          2. Downline Listing display Account Status is Inactive
     *          3. Cannot login member site with inactive account
     *          4. Downline Listing display Account Status is Active
     *          5. Can login member Site
     */
    @Test (groups = {"smoke"})
    @Parameters({"username","password"})
    public void Agent_AM_Downline_Listing_Edit_User_004(String username,String password) throws Exception {
        log("@title: Verify can inactive and reactive the account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String passDecrypt = StringUtils.decrypt(password);
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE");
        String loginID =listAccount.get(1).getLoginID();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"Active","Member");
        page.clickEditIcon(loginID);

        log("Step  3. Change account status to Inactive and click on Submit button\n" +
                "     *              Click Ok button on Edit Member popup");
        page.editDownlinePopup.accInfoSection.inputInfo("","Inactive","","","","","");
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");

        log("Verify 2. Downline Listing display Account Status is Inactive");
        page.searchDownline(loginID,"Inactive","Member");
        Assert.assertEquals(page.getAccountStatus(loginID),"Inactive", "FAILED! Incorrect status after Inactive account");
      /*  agentHomePage.logout();

        log("Step 4. Login member site with inactive account");
        BaseCaseMember.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passDecrypt);

        log("Verify 3. Cannot login member site with inactive account");
        LoginPopup loginPopup = new LoginPopup();
        Assert.assertEquals(loginPopup.lblErrorMessage.getText(), FEMemberConstants.LoginPage.MSG_LOGIN_INACTIVE_ACCOUNT,"FAILED! Can login member site when account is inactive");

        log("Step 5. Back to Agent site and click edit the account again");
        loginAgent(environment.getSosURL(),environment.getSecurityCodeUrl(),username,password,environment.getSecurityCode());*/

        log("Step 6. Change account status to Active and Submit and click Ok button");
        page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        page.searchDownline(loginID, "Inactive", "Member");
        page.clickEditIcon(loginID);
        page.editDownlinePopup.accInfoSection.inputInfo("","Active","","","","","");
        page.submitEditDownline();
        message = page.getMessageUpdate(true);

        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message when update account status is not correct");
        log("Verify 4. Downline Listing display Account Status is Active");
        page.searchDownline(loginID,"Active","Member");
        Assert.assertEquals(page.getAccountStatus(loginID),"Active", "FAILED! Incorrect status after Active account");

       /* log("Step 7. Login member with the account");
        agentHomePage.logout();

        BaseCaseMember.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passDecrypt);
        HomePage memberHomePage = new HomePage();

        log("Verify 5. Can login member Site");
        Assert.assertTrue(memberagentHomePage.ddbMyAccount.isDisplayed(5), "ERROR: ddbMyAccount doesn't display after signed in");*/

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can Inactive and active Live Event
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     *          2. Click on Edit icon of any Member level
     *          3. Inactive Live and click Save button
     *          4. Login member Site and Active Inlay Page
     *          5. Repeat step 2 to 4 and Active Live
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     *          2. If In-Play Page has event, Odds is blur and un-clickable on sport page and market page
     *          3. Can add odds to bet slip when Live is active
     */
    @Test (groups = {"smoke"})
    @Parameters({"username","password"})
    public void Agent_AM_Downline_Listing_Edit_User_012(String username,String password) throws Exception {
        log("@title: Verify can Inactive and active Live Event");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String passwordDecrypt = StringUtils.decrypt(password);
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE");
       String loginID = listAccount.get(1).getLoginID();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"Active","Member");
        page.clickEditIcon(loginID);

        log("Step 3. Inactive Live and click Save button");
        page.editDownlinePopup.productSettingsSection.productStatusSettingsSection.updateLiveNonLive(false,true);
        page.submitEditDownline();

        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");

        log("Precondition step: Unblock today market for Soccer, Tennis and Cricket sport");
        DriverManager.getDriver().switchToParentFrame();
        BlockUnblockEventPage blockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        blockEventPage.searchDownline(loginID);
        blockEventPage.filter("","Soccer","Today");
        blockEventPage.blockUnblockEvent(loginID,"all","Unblock Now","",1);
        blockEventPage.filter("","Tennis","Today");
        blockEventPage.blockUnblockEvent("","all","Unblock Now","",1);
        blockEventPage.filter("","Cricket","Today");
        blockEventPage.blockUnblockEvent("","all","Unblock Now","",1);
       /* agentHomePage.logout();

        log(String.format("Step 4. Login member Site with account %s and Active Inlay Page", loginID));
        DriverManager.getDriver().getToAvoidTimeOut(environment.getMemberSiteURL());
        loginMemberviaUI(loginID,passwordDecrypt);
        memberHomePage = new pages.sat.tabexchange.HomePage();
        pages.sat.tabexchange.InPlayPage inPlayPage = memberagentHomePage.navigateSportMenu("In-Play", pages.sat.tabexchange.InPlayPage.class);

        Event event = inPlayPage.eventContainerControl.getEvent(true,false,0,1);
        if(Objects.isNull(event))
        {
            throw new SkipException("INFO: There is no event in In-play Event");
        }

        log("Verify 2.1. If In-Play Page has event, Odds is blur and un-clickable on sport page ");
        Assert.assertFalse(inPlayPage.eventContainerControl.isOddsUnclickable(event.getEventName()),"FAILED! Inplay odds is not blue and is click able");
        log(String.format("Step 4.1 click on the event ", event.getEventName()));
        inPlayPage.eventContainerControl.clickEvent(event.getEventName());

        log("Verify 2.2. If In-Play Page has event, Odds is blur and un-clickable on market page");
        Assert.assertFalse(inPlayPage.marketContainerControl.verifyOddsIsClickable(event),"FAILED! Market page In-play event can click on odds when inactive Live ");
        inPlayPage.logout();*/

        log("Step 5. Repeat step 2 to 4 and Active Live");
        loginAgent(sosAgentURL,agentSecurityCodeURL,username,password,environment.getSecurityCode());

        page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        page.searchDownline(loginID, "Active", "Member");
        page.clickEditIcon(loginID);
        page.editDownlinePopup.productSettingsSection.productStatusSettingsSection.updateLiveNonLive(true,true);
        page.submitEditDownline();
        message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");

        /*log("Verify 3. Can add odds to bet slip when Live is active");
        agentHomePage.logout();
        loginMemberviaUI(loginID,password);
        memberHomePage = new HomePage();
        inPlayPage = memberagentHomePage.navigateSportMenu("In-Play", InPlayPage.class);
        event = inPlayPage.eventContainerControl.getEvent(true,false,0,1);

        inPlayPage.eventContainerControl.clickEvent(event.getEventName());
        Assert.assertTrue(inPlayPage.marketContainerControl.verifyOddsIsClickable(event),"FAILED! Market page In-play event can NOT click on odds when active Live Setting");*/

        log("INFO Execute completed!");
    }

    /**
     * @title: Verify can Inactive and active None-Live Event
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     *          2. Click on Edit icon of any Member level
     *          3. Inactive Non-Live and click Save button
     *          4. Login member Site and Active any sport that has non-play event
     *          5. Repeat step 2 to 4 and Active  None-Live
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     *          2. Active any sport that have event non-inplay and verify Odds is blur and cannot add to bet slip in sport and market page
     *          3. Can add odds to bet slip when None-Live is active
     */
    @Test (groups = {"smoke"})
    @Parameters({"username","password"})
    public void Agent_AM_Downline_Listing_Edit_User_013(String username,String password) throws Exception {
        log("@title: Verify can update Credit Balance");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE");
        String loginID = listAccount.get(1).getLoginID();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"Active","Member");
        page.clickEditIcon(loginID);

        log("Step 3. Inactive Live and click Save button");
        page.editDownlinePopup.productSettingsSection.productStatusSettingsSection.updateLiveNonLive(true,false);
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");

        log("Precondition step: Unblock today market for Soccer, Tennis and Cricket sport");
        DriverManager.getDriver().switchToParentFrame();
        BlockUnblockEventPage blockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        blockEventPage.searchDownline(loginID);
        blockEventPage.filter("","Soccer","Today");
        blockEventPage.blockUnblockEvent(loginID,"all","Unblock Now","",1);
        blockEventPage.filter("","Tennis","Today");
        blockEventPage.blockUnblockEvent("loginID","all","Unblock Now","",1);

       /* log("Step 4. Login member Site and Active Inlay Page");
        agentHomePage.logout();
        loginMemberviaUI(loginID,password);
        HomePage memberHomePage = new HomePage();
        Event event = memberagentHomePage.eventContainerControl.getEvent(false,false,0,1);
        if(Objects.isNull(event))
        {
            throw new SkipException("INFO: There is no event in Inplay Event");
        }

        log("Verify 2.1 If In-Play Page has event, Odds is blur and un-clickable on sport page ");
        Assert.assertFalse(memberagentHomePage.eventContainerControl.isOddsUnclickable(event.getEventName()),"FAILED! Inplay odds is not blue and is click able");
        log(String.format("Step 4.1 click on the event ", event.getEventName()));

        memberagentHomePage.eventContainerControl.clickEvent(event.getEventName());
        log("Verify 2.2. If In-Play Page has event, Odds is blur and un-clickable on market page");
        Assert.assertFalse(memberagentHomePage.marketContainerControl.verifyOddsIsClickable(event),"FAILED! Market page In-play event can click on odds when inactive Live ");
        memberagentHomePage.logout();*/

        log("Step 5. Repeat step 2 to 4 and Active Live");
        loginAgent(sosAgentURL, agentSecurityCodeURL, username, password, environment.getSecurityCode());
        page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        page.clickEditIcon(loginID);
        page.editDownlinePopup.productSettingsSection.productStatusSettingsSection.updateLiveNonLive(true,true);
        page.submitEditDownline();
        message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");


        /*log("Verify 3. Can add odds to bet slip when Live is active");
        agentHomePage.logout();
        loginMemberviaUI(loginID,password);
        memberHomePage = new HomePage();
        event = memberagentHomePage.eventContainerControl.getEvent(false,false,0,1);
        memberagentHomePage.eventContainerControl.clickEvent(event.getEventName());
        Assert.assertTrue(memberagentHomePage.marketContainerControl.verifyOddsIsClickable(event),"FAILED! Market page In-play event can NOT click on odds when active Live Setting");
    */}

    /**
     * @title: Verify can Inactive and active a sport
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Downline Listing
     *          2. Click on Edit icon of any Member level
     *          3. In-active any sport
     *          4. Repeat step 2 to 3 and Active Sport again and active at least 1 market of the sport
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     *          2. Login member site and verify Sport is not displayed on the left menu or main menu
     */
    @Test (groups = {"smoke"})
    @Parameters({"username","password"})
    public void Agent_AM_Downline_Listing_Edit_User_014(String username,String password) throws Exception {
        log("@title: Verify can Inactive and active a sport");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String passDecryp = StringUtils.decrypt(password);
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE");
        String loginID = listAccount.get(2).getLoginID();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"Active","Member");
        page.clickEditIcon(loginID);

        log("Step 3. In-active any sport");
        page.editDownlinePopup.productSettingsSection.productStatusSettingsSection.updateSport("Soccer",false);
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");

        log("Precondition step: Unblock today market for Soccer");
        DriverManager.getDriver().switchToParentFrame();
        BlockUnblockEventPage blockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        blockEventPage.searchDownline(loginID);
        blockEventPage.filter("","Soccer","Today");
        blockEventPage.selectDownline(loginID,true);
        Assert.assertEquals(blockEventPage.lblNoEvent.getText(),AGConstant.NO_RECORD_FOUND);

       /* agentHomePage.logout();
        log("Step 4. Login member Site and Active Inlay Page");
        BaseCaseMember.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passDecryp);
        HomePage memberHomePage = new HomePage();
        List<String> listSportMenu = memberagentHomePage.getMainSportsMenu();

        log("Verify 2. Login member site and verify Sport is not displayed on the left menu or main menu");
        Assert.assertFalse(listSportMenu.contains("Soccer"),"FAILED! Sport should be not display in main menu when the sport is inactive in agent site");
        memberagentHomePage.logout();*/

        log("Step 4. Repeat step 2 to 3 and Active Sport again and active at least 1 market of the sport");
        loginAgent(sosAgentURL, agentSecurityCodeURL, username, password, environment.getSecurityCode());
        page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        page.searchDownline(loginID, "Active", "Member");
        page.clickEditIcon(loginID);
        page.editDownlinePopup.productSettingsSection.productStatusSettingsSection.updateMarket("Soccer","Match Odds (MATCH_ODDS)",true);
        page.submitEditDownline();

        message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");

        log("Precondition step: Unblock today market for Soccer");
        DriverManager.getDriver().switchToParentFrame();
        blockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        blockEventPage.searchDownline(loginID);
        blockEventPage.filter("","Soccer","Today");
        blockEventPage.blockUnblockEvent(loginID,"all","Unblock Now");
        /*agentHomePage.logout();

        log("Verify 3. Login member site and verify Sport is displayed on the left menu or main menu");
        BaseCaseMember.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passDecryp);
        memberHomePage = new HomePage();
        listSportMenu = memberagentHomePage.getMainSportsMenu();
        Assert.assertTrue(listSportMenu.contains("Soccer"),"FAILED! Sport should be not display in main menu when the sport is inactive in agent site");*/
    }

    /**
     * @title: Verify can Inactive and active a market type of a sport
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Downline Listing
     *          2. Click on Edit icon of any Member level
     *          3. Select Soccer sport and click edit icon
     *          4. Uncheck a market (e.g.: Half Time) and submit
     *          5. Repeat step 2 to 4 and Active Sport again and active at least 1 market of the sport
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     *          2. Verify Half-Time market is uncheck after that
     *          3. Login member site and verify Soccer event not display Half Time market
     *          4. Login member site and verify Soccer event is  display with Half Time market
     */
    @Test (groups = {"smoke"})
    @Parameters({"username","password"})
    public void Agent_AM_Downline_Listing_Edit_User_015(String username,String password) throws Exception {
        log("@title: Verify can Inactive and active a market type of a sport");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String passDecryp = StringUtils.decrypt(password);
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE");
        String loginID = listAccount.get(0).getLoginID();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"Active","Member");
        page.clickEditIcon(loginID);

        log("Step 3. Select Soccer sport and click edit icon");
        log("Step 4. Uncheck a market (e.g.: Half Time) and submit");
        page.editDownlinePopup.productSettingsSection.productStatusSettingsSection.updateMarket("Soccer","Half Time (HALF_TIME)",false);
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");

        DriverManager.getDriver().switchToParentFrame();
        BlockUnblockEventPage blockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        blockEventPage.searchDownline(loginID);
        blockEventPage.filter("","Soccer","Today");
        blockEventPage.blockUnblockEvent(loginID,"all","Unblock Now");
       /* agentHomePage.logout();

        log("Verify 2. Verify Half-Time market is uncheck after that");
        log("Verify 3. Login member site and verify Soccer event not display Half Time market");
        BaseCaseMember.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passDecryp);
        memberHomePage = new HomePage();

        SoccerPage socerPage = memberagentHomePage.navigateSportMenu("Soccer",SoccerPage.class);
        List<String> lstCompetition = socerPage.getLeftMenuList();
        if(lstCompetition.isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        socerPage.clickMenu(lstCompetition.get(lstCompetition.size()-1));
        List<String> lstEvent = socerPage.getLeftMenuList();
        socerPage.clickMenu(lstEvent.get(lstEvent.size()-1));
        List<String> lstMarket = socerPage.getLeftMenuList();
        Assert.assertTrue(lstMarket.contains("Half Time"),"FAILED! Sport should be not display in main menu when the sport is inactive in agent site");*/

    }

    /**
     * @title: Verify can edit User successfully if input valid min bet Setting
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     *          2. Click on Edit icon of any Member level
     *          3. Input Min bet  of Soccer with valid value
     *          4. Login member Site and  place Soccer bet with the stake less than the value in Step 3
     * @expect: 1. Verify can update User with valid Min Bet
     *          2. Verify message display correctly min bet when place bet with stake less than min setting
     */
    @Test (groups = {"smoke"})
    @Parameters({"username","password"})
    public void Agent_AM_Downline_Listing_Edit_User_024(String username,String password) throws Exception {
        log("@title: Verify can edit User successfully if input valid min bet Setting");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String passDecryp = StringUtils.decrypt(password);
        String userID = ProfileUtils.getProfile().getUserID();
        List<String> betSettingInfo = EventBetSizeSettingUtils.getUserBetSetting("EXCHANGE",userID,"SOCCER");
        int min = Integer.parseInt(String.format("%.0f", Double.parseDouble(betSettingInfo.get(0)))) ;
        int max = (Integer.parseInt(String.format("%.0f", Double.parseDouble(betSettingInfo.get(1)))) + min);
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE");
        String loginID = listAccount.get(0).getLoginID();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"Active","Member");
        page.clickEditIcon(loginID);

        log("Step 3. Input Min bet of Soccer with valid value");
        List<ArrayList<String>> lstBetSettingValidation = page.editDownlinePopup.productSettingsSection.betSettingSectionExchange.getBetSettingValidationValueLst("");
        String minBet =Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1)) + 1);
        String maxBet =Integer.toString(Integer.parseInt(lstBetSettingValidation.get(1).get(1))-1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        ArrayList<String> maxBetLst = new ArrayList<String>(
                Arrays.asList(maxBet, maxBet, maxBet, maxBet, maxBet, maxBet));
        lstBetSetting.add(minBetLst);
        lstBetSetting.add(maxBetLst);
        page.editDownlinePopup.accInfoSection.txtPassword.sendKeys(password);
        page.editDownlinePopup.productSettingsSection.betSettingSectionExchange.inputBetSetting(lstBetSetting);
        page.submitEditDownline();
        //page.updateBetSetting("Soccer","3","","","",true);

        log("Verify 1. Verify can update User with valid Min Bet");
        String message = page.getMessageUpdate(true);
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");
        log("Unblock Soccer event");
        DriverManager.getDriver().switchToParentFrame();
        BlockUnblockEventPage blockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        blockEventPage.searchDownline(loginID);
        blockEventPage.filter("","Soccer","Today");
        blockEventPage.blockUnblockEvent(loginID,"all","Unblock Now");
       /* agentHomePage.logout();

        log("Step 4. Login member Site and place Soccer bet with the stake less than the value in Step 3");
        loginMemberviaUI(loginID,password);

        log("Verify 2. Verify message display correctly min bet when place bet with stake less than min setting");
        BaseCaseMember.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passDecryp);
        String odds ="20.00";
        String stake = Integer.toString(Integer.parseInt(minBet)-1);
        memberHomePage = new HomePage();
        SoccerPage socerPage = memberagentHomePage.navigateSportMenu("Soccer",SoccerPage.class);
        Event event = socerPage.eventContainerControl.getEvent(false,false,3,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        socerPage.clickEvent(event);

        log("Step 3:Click on an Back odds without empty of the selection have the high potential win");
        Market market = socerPage.marketContainerControl.getMarket(event,1,true);
        market.getOdd().click();

        log("Step 4. Input stake and click submit");
        socerPage.betSlipControl.placeBet(odds,stake);

        log("Verify: Error Cannot place bet display");
        String actualError = socerPage.myBetControl.getPlaceBetErrorMessage();
        String expectedError = String.format(FEMemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",Double.parseDouble(minBet)),String.format("%(,.2f",Double.parseDouble(maxBet)),String.format("%.2f",Double.parseDouble(stake)));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));*/
    }
}

