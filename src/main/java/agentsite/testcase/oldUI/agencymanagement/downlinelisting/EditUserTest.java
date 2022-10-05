package agentsite.testcase.oldUI.agencymanagement.downlinelisting;

import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DownLineListingPage;
import agentsite.pages.oldUI.agentmanagement.EditDownLinePageOldUI;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.paltech.utils.StringUtils.decrypt;
import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.DOWNLINE_LISTING;


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
        String loginID =listAccount.get(0).getUserCode();
        String passwordEdit ="1234qwert";
        String passwordDecrypt = decrypt(password);

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"All","Member");
        EditDownLinePageOldUI editDownlinePage = (EditDownLinePageOldUI)page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        Assert.assertEquals(editDownlinePage.getUserName(),loginID,"Failed! Username display is incorrect");
        log("Step 3. Input new password in  password textbox and click Submit");
        editDownlinePage.accInfoSection.inputInfo(passwordEdit,"","","","","","");
        editDownlinePage.btnSubmit.click();
        String message = editDownlinePage.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");

        log("Step 4. Login the account in member site with new password");
       /* agentHomePage.logout();

        BaseCaseFEOLD.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passwordEdit);
        log("Verify 2. Change password page display after login member site");
        ChangePasswordPage changePaswordPage = new ChangePasswordPage();

        log("Verify 4. Verify change password page display after login");
        Assert.assertEquals(changePaswordPage.lbltitle.getText(),"Please change your password below","FAILED! Change password page not display when agent update password for player");

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
        String loginID =listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"Active","Member");
        EditDownLinePageOldUI editDownLinePageOldUI = (EditDownLinePageOldUI) page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        log("Step  3. Change account status to Inactive and click on Submit button\n" +
                "     *              Click Ok button on Edit Member popup");
        editDownLinePageOldUI.accInfoSection.inputInfo("","Inactive","","","","","");
        editDownLinePageOldUI.btnSubmit.click();
        String message = editDownLinePageOldUI.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");

        log("Verify 2. Downline Listing display Account Status is Inactive");
        page.searchDownline(loginID,"Inactive","Member");
        Assert.assertEquals(page.getAccountStatus(loginID),"Inactive", "FAILED! Incorrect status after Inactive account");
       /* agentHomePage.logout();

        log("Step 4. Login member site with inactive account");
        BaseCaseFEOLD.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passDecrypt);

        log("Verify 3. Cannot login member site with inactive account");
        LoginPopup loginPopup = new LoginPopup();
        Assert.assertEquals(loginPopup.lblErrorMessage.getText(), FEMemberConstants.LoginPage.MSG_LOGIN_INACTIVE_ACCOUNT,"FAILED! Can login member site when account is inactive");*/

        log("Step 5. Back to Agent site and click edit the account again");
        loginAgent(sosAgentURL,agentSecurityCodeURL,username,password,environment.getSecurityCode());

        log("Step 6. Navigate to Downline Listing page");
        page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        page.searchDownline(loginID, "Inactive", "Member");
        editDownLinePageOldUI = (EditDownLinePageOldUI) page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 6. Change account status to Active and Submit and click Ok button");
        editDownLinePageOldUI.accInfoSection.inputInfo("","Active","","","","","");
        editDownLinePageOldUI.btnSubmit.click();
        message = page.getMessageUpdate(true);

        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message when update account status is not correct");
        log("Verify 4. Downline Listing display Account Status is Active");
        page.searchDownline(loginID,"Active","Member");
        Assert.assertEquals(page.getAccountStatus(loginID),"Active", "FAILED! Incorrect status after Active account");
       /* agentHomePage.logout();
        log("Step 7. Login member with the account");

        BaseCaseFEOLD.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passDecrypt);
        log("Verify 5. Can login member Site - Login is not display");
        Assert.assertFalse(loginPopup.popupLogin.isDisplayed(), "ERROR: Login popup still display after signed in");*/

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can Inactive and active Live Event
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     *          2. Click on Edit icon of any Member level
     *          3. Inactive Live and click Save button
     *           4. Repeat step 2 to 3 and Active Live
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully" and Live check box is Checked/Unchecked
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_Downline_Listing_Edit_User_012() throws Exception {
        log("@title: Verify can Inactive and active Live Event");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE");
       String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Search an account");
        page.searchDownline(loginID,"Active","Member");

        log("Step 3. Click on Edit icon of any Member level");
        EditDownLinePageOldUI editDownLinePageOldUI = (EditDownLinePageOldUI) page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 3. Inactive Live and click Save button");
        editDownLinePageOldUI.updateLiveNoneLive(false,true,true);
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");
        Assert.assertFalse(editDownLinePageOldUI.isLiveCbChecked(),"FAILED! Live checkbox should be unchecked");

        log("Step 5. Repeat step 2 to 4 and Active Live");
        editDownLinePageOldUI.updateLiveNoneLive(true,true,true);
        message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");
        Assert.assertTrue(editDownLinePageOldUI.isLiveCbChecked(),"FAILED! Live checkbox should be checked");

        log("INFO Execute completed!");
        //TODO: Should add test case verify Live/Nolive affect in membersite old UI
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
    @Test (groups = {"fairenter24"})
    @Parameters({"username","password"})
    public void Agent_AM_Downline_Listing_Edit_User_013(String username,String password) throws Exception {
        log("@title: Verify can update Credit Balance");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE");
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Search an account");
        page.searchDownline(loginID,"Active","Member");

        log("Step 3. Click on Edit icon of any Member level");
        EditDownLinePageOldUI editDownLinePageOldUI = (EditDownLinePageOldUI) page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 3. Inactive None Live and click Save button");
        editDownLinePageOldUI.updateLiveNoneLive(true,false,true);
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");
        Assert.assertFalse(editDownLinePageOldUI.isNoneLiveCbChecked(),"FAILED! Live checkbox should be unchecked");

        log("Step 5. Repeat step 2 to 4 and Active Live");
        editDownLinePageOldUI.updateLiveNoneLive(true,true,true);
        message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");
        Assert.assertTrue(editDownLinePageOldUI.isNoneLiveCbChecked(),"FAILED! Live checkbox should be checked"); }

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
    public void Agent_AM_Downline_Listing_Edit_User_014() throws Exception {
        log("@title: Verify can Inactive and active a sport");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE");
        String loginID = listAccount.get(2).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"Active","Member");
        EditDownLinePageOldUI editDownLinePageOldUI = (EditDownLinePageOldUI) page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 3. In-active any sport");
        editDownLinePageOldUI.updateSport("Soccer",false,true);
        String message = page.getMessageUpdate(true);
        editDownLinePageOldUI = (EditDownLinePageOldUI) page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");
        Assert.assertTrue(editDownLinePageOldUI.isSportActive("Soccer"),"FAILED! Soccer Sport does not active ");

        log("Step 4.3 Repeat step 2 to 3 and Active Sport again and active at least 1 market of the sport");
        editDownLinePageOldUI.updateMarket("Soccer","Match Odds (MATCH_ODDS)",true,true);
        message = page.getMessageUpdate(true);
        editDownLinePageOldUI = (EditDownLinePageOldUI) page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");
        Assert.assertTrue(editDownLinePageOldUI.isSportActive("Soccer"),"FAILED! Soccer Sport does not active ");
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
    public void Agent_AM_Downline_Listing_Edit_User_015() throws Exception {
        log("@title: Verify can Inactive and active a market type of a sport");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE");
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"Active","Member");
        EditDownLinePageOldUI editDownLinePageOldUI = (EditDownLinePageOldUI) page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 3. Select Soccer sport and click edit icon");
        log("Step 4. Uncheck a market (e.g.: Half Time) and submit");
        editDownLinePageOldUI.updateMarket("Soccer","Half Time (HALF_TIME)",false,true);
        String message = page.getMessageUpdate(true);
        editDownLinePageOldUI = (EditDownLinePageOldUI) page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");
        Assert.assertFalse(editDownLinePageOldUI.isMarketActive("Soccer","Half Time (HALF_TIME)",true));

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
    @Parameters({"username","password","currency"})
    public void Agent_AM_Downline_Listing_Edit_User_024(String username,String password,String currency) throws Exception {
        log("@title: Verify can edit User successfully if input valid min bet Setting");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String passDecryp = StringUtils.decrypt(password);
        String userID = ProfileUtils.getProfile().getUserID();
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE");
        String loginID = listAccount.get(0).getUserCode();
        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID,"Active","Member");
        EditDownLinePageOldUI editDownLinePageOldUI = (EditDownLinePageOldUI) page.clickEditIconOldUI(loginID,StringUtils.decrypt(environment.getSecurityCode()));

        List<ArrayList<String>> lstBetSettingValidation =editDownLinePageOldUI.productSettingsSection.betSettingSectionExchange.getBetSettingValidationValueLst(currency);
        String minBet =Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1)) + 1);
        String maxBet =Integer.toString(Integer.parseInt(lstBetSettingValidation.get(1).get(1).replace(",",""))-1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        ArrayList<String> maxBetLst = new ArrayList<String>(
                Arrays.asList(maxBet, maxBet, maxBet, maxBet, maxBet, maxBet));
        lstBetSetting.add(minBetLst);
        lstBetSetting.add(maxBetLst);

        log("Step 3. Input Min bet of Soccer with valid value");
        editDownLinePageOldUI.accInfoSection.txtPassword.sendKeys(password);
        editDownLinePageOldUI.productSettingsSection.betSettingSectionExchange.inputBetSetting(lstBetSetting);
        editDownLinePageOldUI.btnSubmit.click();

        log("Verify 1. Verify can update User with valid Min Bet");
        String message = page.getMessageUpdate(true);
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS,"FAILED! Message update downline is not correct");
        /*agentHomePage.logout();

        log("Verify 2. Verify message display correctly min bet when place bet with stake less than min setting");
        BaseCaseFEOLD.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passDecryp);
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

