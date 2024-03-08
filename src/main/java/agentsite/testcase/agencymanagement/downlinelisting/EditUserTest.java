package agentsite.testcase.agencymanagement.downlinelisting;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.ultils.agencymanagement.EventBetSizeSettingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.ChangePasswordPage;
import membersite.pages.MarketPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EditUserTest extends BaseCaseTest {
    @TestRails(id = "3499")
    @Test(groups = {"http_request"})
    public void Agent_AM_Downline_Listing_Edit_User_3499() throws Exception {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", _brandname);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Click on Edit icon of any agent");
        page.clickEditIcon(loginID);

        log("Verify There is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console Error display");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3523")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3523() throws Exception {
        log("@title: Validate UI in Edit User");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", _brandname);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editDownLineAgentPage = page.clickEditIcon(loginID);

        log("Verify 1. Verify UI in Edit Downline Agent is corrected");
        Assert.assertEquals(page.header.lblPageTitle.getText().trim(), AGConstant.AgencyManagement.EDIT_DOWNLINE_AGENT_TITLE, "Failed! Page title is incorrect");
        editDownLineAgentPage.accountInforSection.verifyUIDisplayedCorrect();

        log("Verify 2. Cash Balance");
        Assert.assertEquals(editDownLineAgentPage.cashBalanceInforSection.getCashSectionTitle(), AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE, "FAILED! Cash Balance Title display incorrect");

        log("Verify 3. Product Setting, select Exchange product");
        Assert.assertEquals(editDownLineAgentPage.lblProductSetting.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING, "FAILED! Product Setting Section display incorrect");

        log("Verify 4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstBetSettingHeader = editDownLineAgentPage.tblBetSettings.getHeaderNameOfRows();
        List<String> lstBetSettingOption = editDownLineAgentPage.tblBetSettings.getColumn(1, false);
        List<String> lstTaxSettingHeader = editDownLineAgentPage.tblTaxSettings.getHeaderNameOfRows();
        List<String> lstTaxSettingOption = editDownLineAgentPage.tblTaxSettings.getColumn(1, false);
        List<String> lstPositionTakingHeader = editDownLineAgentPage.tblPositionTakingListing.getHeaderNameOfRows();
        Assert.assertEquals(editDownLineAgentPage.lblBetSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING, "FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_HEADER, "FAILED! Bet Setting Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_OPTION, "FAILED! Bet Setting options in the first column does not display as expected");
        Assert.assertEquals(editDownLineAgentPage.lblTaxSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING, "FAILED! Tax Setting Section Label display incorrect");
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_HEADER_NEWUI, "FAILED! Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION, "FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(editDownLineAgentPage.lblPositionTakingListing.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING, "FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_POSITION_TAKING_HEADER, "FAILED! Position Taking Header does not display as expected");

        log("Verify 5. Submit and Cancel button");
        Assert.assertEquals(editDownLineAgentPage.editDownlineListing.btnSubmit.getText(), AGConstant.BTN_SUBMIT, "FAILED! Submit button display incorrect");
        Assert.assertEquals(editDownLineAgentPage.editDownlineListing.btnCancel.getText(), AGConstant.BTN_CANCEL, "FAILED! Cancel button display incorrect");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify Can change password
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any Member level
     * 3. Input new password in  password textbox and click Submit
     * 4. Login the account in member site with new password
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     * 2. Change password page display after login member site
     */
    @TestRails(id = "698")
    @Test(groups = {"smoke"})
    @Parameters({"password"})
    public void Agent_AM_Downline_Listing_Edit_User_698(String password) throws Exception {
        log("@title: Verify Can change password");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "All", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        log("Step 3. Input new password in  password textbox and click Submit");
        editDownLinePage.editDownlineListing.accountInforSection.inputInfo("", StringUtils.decrypt(password), "");
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");
//        log("Step 4. Login the account in member site with new password");
//        agentHomePage.logout();

       /* BaseCaseFE.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passwordEdit);
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
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any Member level
     * 3. Change account status to Inactive and click on Submit button
     * Click Ok button on Edit Member popup
     * 4. Login member site with inactive account
     * 5. Back to Agent site and click edit the account again
     * 6. Change account status to Active and Submit and click Ok button
     * 7. Login member with the account
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     * 2. Downline Listing display Account Status is Inactive
     * 3. Cannot login member site with inactive account
     * 4. Downline Listing display Account Status is Active
     * 5. Can login member Site
     */
    @TestRails(id = "699")
    @Test(groups = {"smoke"})
    public void Agent_AM_Downline_Listing_Edit_User_699() throws Exception {
        log("@title: Verify can inactive and reactive the account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        log("Step  3. Change account status to Inactive and click on Submit button\n" +
                "Click Ok button on Edit Member popup");
        editDownLinePage.accountInforSection.inputInfo("", "Inactive");
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 3 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");

        log("Verify 3. Downline Listing display Account Status is Inactive");
        page.searchDownline(loginID, "Inactive", "Member");
        Assert.assertEquals(page.getAccountStatus(loginID), "Inactive", "FAILED! Incorrect status after Inactive account");

        log("Step 4. Navigate to Downline Listing page");
        page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(loginID, "Inactive", "Member");
        editDownLinePage = page.clickEditIcon(loginID);

        log("Step 5. Change account status to Active and Submit and click Ok button");
        editDownLinePage.accountInforSection.inputInfo("", "Active");
        page.submitEditDownline();
        message = page.getMessageUpdate(true);

        log("Verify 5 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message when update account status is not correct");
        log("Verify 5. Downline Listing display Account Status is Active");
        page.searchDownline(loginID, "Active", "Member");
        Assert.assertEquals(page.getAccountStatus(loginID), "Active", "FAILED! Incorrect status after Active account");

        log("INFO: Executed completely");
    }


    /**
     * @title: Verify can Inactive and active Live Event
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any Member level
     * 3. Inactive Live and click Save button
     * 4. Login member Site and Active Inlay Page
     * 5. Repeat step 2 to 4 and Active Live
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     * 2. If In-Play Page has event, Odds is blur and un-clickable on sport page and market page
     * 3. Can add odds to bet slip when Live is active
     */
    @TestRails(id = "700")
    @Test(groups = {"smoke"})
    public void Agent_AM_Downline_Listing_Edit_User_700() throws Exception {
        log("@title: Verify can Inactive and active Live Event");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        log("Step 3. Inactive Live and click Save button");
        editDownLinePage.productStatusSettingInforSection.updateLiveNonLive(false,true);
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");

        page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(loginID, "Active", "Member");
        editDownLinePage = page.clickEditIcon(loginID);

        log("Step 5.1. Input security code");
        page.submitEditDownline();

        editDownLinePage.productStatusSettingInforSection.updateLiveNonLive(true, true);
        page.submitEditDownline();
        message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");

        log("INFO Execute completed!");
    }

    /**
     * @title: Verify can Inactive and active None-Live Event
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any Member level
     * 3. Inactive Non-Live and click Save button
     * 4. Login member Site and Active any sport that has non-play event
     * 5. Repeat step 2 to 4 and Active  None-Live
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     * 2. Active any sport that have event non-inplay and verify Odds is blur and cannot add to bet slip in sport and market page
     * 3. Can add odds to bet slip when None-Live is active
     */
    @TestRails(id = "701")
    @Test(groups = {"smoke"})
    public void Agent_AM_Downline_Listing_Edit_User_701() throws Exception {
        log("@title: Verify can update Credit Balance");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        log("Step 3. Inactive Live and click Save button");
        editDownLinePage.productStatusSettingInforSection.updateLiveNonLive(true, false);
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");
        editDownLinePage = page.clickEditIcon(loginID);
        editDownLinePage.productStatusSettingInforSection.updateLiveNonLive(true, true);
        page.submitEditDownline();
        message = page.getMessageUpdate(true);

        log("Verify 2 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");
        log("INFO Execute completed!");
    }

    /**
     * @title: Verify can Inactive and active a sport
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any Member level
     * 3. In-active any sport
     * 4. Repeat step 2 to 3 and Active Sport again and active at least 1 market of the sport
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     * 2. Login member site and verify Sport is not displayed on the left menu or main menu
     */
    @TestRails(id = "702")
    @Test(groups = {"smoke"})
    @Parameters({"username", "password"})
    public void Agent_AM_Downline_Listing_Edit_User_702(String username, String password) throws Exception {
        log("@title: Verify can Inactive and active a sport");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        log("Step 3. In-active any sport");
        editDownLinePage.productStatusSettingInforSection.updateSport("Soccer", false);
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");

        log("Step 4.1 Login agent again and search the account");
        loginAgent(sosAgentURL, agentSecurityCodeURL, username, password, environment.getSecurityCode());
        page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(loginID, "Active", "Member");
        editDownLinePage = page.clickEditIcon(loginID);

        log("Step 4.3 Repeat step 2 to 3 and Active Sport again and active at least 1 market of the sport");
        editDownLinePage.productStatusSettingInforSection.updateMarket("Soccer", "Match Odds (MATCH_ODDS)", true);
        page.submitEditDownline();
        message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");
        log("INFO Execute completed!");
    }

    /**
     * @title: Verify can Inactive and active a market type of a sport
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any Member level
     * 3. Select Soccer sport and click edit icon
     * 4. Uncheck a market (e.g.: Half Time) and submit
     * 5. Repeat step 2 to 4 and Active Sport again and active at least 1 market of the sport
     * @expect: 1 Verify Edit Member popup display with the message "Member was update successfully"
     * 2. Verify Half-Time market is uncheck after that
     * 3. Login member site and verify Soccer event not display Half Time market
     * 4. Login member site and verify Soccer event is  display with Half Time market
     */
    @TestRails(id = "703")
    @Test(groups = {"smoke"})
    public void Agent_AM_Downline_Listing_Edit_User_703() throws Exception {
        log("@title: Verify can Inactive and active a market type of a sport");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        log("Step 3. Select Soccer sport and click edit icon");
        log("Step 4. Uncheck a market (e.g.: Half Time) and submit");
        editDownLinePage.productStatusSettingInforSection.updateMarket("Soccer", "Match Odds (MATCH_ODDS)", false);
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");
        log("INFO Execute completed!");
    }

    /**
     * @title: Verify can edit User successfully if input valid min bet Setting
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any Member level
     * 3. Input Min bet  of Soccer with valid value
     * 4. Login member Site and  place Soccer bet with the stake less than the value in Step 3
     * @expect: 1. Verify can update User with valid Min Bet
     * 2. Verify message display correctly min bet when place bet with stake less than min setting
     */
    @TestRails(id = "704")
    @Test(groups = {"smoke"})
    @Parameters({"password","currency"})
    public void Agent_AM_Downline_Listing_Edit_User_704(String password, String currency) throws Exception {
        log("@title: Verify can edit User successfully if input valid min bet Setting");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();
        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        List<ArrayList<String>> lstBetSettingValidation = page.betSettingInforSection.getBetSettingValidationValueLst(currency);
//      page.editDownlinePopup.productSettingsSection.betSettingSectionExchange.getBetSettingValidationValueLst(currency);
        String minBet = Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1).replace(",","")) + 1);
        String maxBet = Integer.toString(Integer.parseInt(lstBetSettingValidation.get(1).get(1).replace(",","")) - 1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        ArrayList<String> maxBetLst = new ArrayList<String>(
                Arrays.asList(maxBet, maxBet, maxBet, maxBet, maxBet, maxBet));
        lstBetSetting.add(minBetLst);
        lstBetSetting.add(maxBetLst);

        log("Step 3. Input Min bet of Soccer with valid value");
        editDownLinePage.accountInforSection.txtPassword.sendKeys(StringUtils.decrypt(password));
        editDownLinePage.betSettingInforSection.inputBetSetting(lstBetSetting);
        page.submitEditDownline();

        log("Verify 1. Verify can update User with valid Min Bet");
        String message = page.getMessageUpdate(true);
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");
        log("INFO Execute completed!");
    }


    @TestRails(id = "3542")
    @Test(groups = {"regression_oldui"})
    public void Agent_AM_Downline_Listing_Edit_User_3542() throws Exception {
        log("@title: Verify can edit User successfully if input valid min bet Setting");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log(String.format("Step 2. Click on Edit icon of  Player: %s", loginID));
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        log("Verify 1. Verify there is no Security Code popup prompted");
        Assert.assertFalse(editDownLinePage.securityPopup.isDisplayed(), "FAILED Security popup  display when edit agent");
    }

//    @TestRails(id = "3544")
//    @Test(groups = {"interaction"})
//    public void Agent_AM_Downline_Listing_Edit_User_3544() throws Exception {
//        log("@title: Verity Exchange Product is displayed/dissappear in member site when active/inactive");
//        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
//        String userID = ProfileUtils.getProfile().getUserID();
//        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
//        String loginID = listAccount.get(0).getUserCode();
//        log("Step 1. Navigate Agency Management > Downline Listing");
//        log("Step 2. Click on Edit icon for member level");
//        log("Step 1. Navigate Agency Management > Downline Listing");
//
//        log(String.format("Step 2. Click on Edit icon of  Player: %s", loginID));
//        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);
//        editDownLinePage.activeProduct("Exchange", true);
//
//        log("Verify 1. Verify there is no Security Code popup prompted");
//        Assert.assertFalse(editDownLinePage.securityPopup.isDisplayed(), "FAILED Security popup  display when edit agent");
//    }
    @TestRails(id = "3544")
    @Test(groups = {"interaction"})
    @Parameters({"username", "password", "memberAccount"})
    public void Agent_AM_Downline_Listing_Edit_User_3544(String username, String password, String memberAccount) throws Exception {
        log("@title: Verity Lottery & Slots Product is displayed/dissappear in member site when active/inactive");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(memberAccount, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);

        log("Step 3.1 In-active Lottery & Slots product");
        editDownLinePage.productStatusSettingInforSection.updateProduct("Lottery & Slots", false);
        page.submitEditDownline();
        page.closeSubmitEditDownlinePopup();
        page.logout();

        log("Step 4.1 Login Member Site");
        loginMember(memberAccount, password);
        log("4.1 Verify Lottery & Slots product disappears");
        Assert.assertFalse(memberHomePage.header.isProductTabDisplay("Lottery & Slots"), "ERROR! Product tab is not disappeared");

        log("Step 1.2 Login agent again and search the account");
        loginAgent(username, password, true);
        page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(memberAccount, "Active", "Member");
        editDownLinePage = page.clickEditIcon(memberAccount);

        log("Step 3.3 Active Lottery & Slots product");
        editDownLinePage.productStatusSettingInforSection.updateProduct("Lottery & Slots", true);
        page.submitEditDownline();
        page.closeSubmitEditDownlinePopup();
        page.logout();

        log("Step 4.2 Login Member Site");
        loginMember(memberAccount, password);
        log("4.2 Verify Lottery & Slots product appears");
        Assert.assertTrue(memberHomePage.header.isProductTabDisplay("Lottery & Slots"), "ERROR! Product tab is not appeared");
    }
    @TestRails(id = "3546")
    @Test(groups = {"interaction"})
    @Parameters({"username", "password", "memberAccount"})
    public void Agent_AM_Downline_Listing_Edit_User_3546(String username, String password, String memberAccount) throws Exception {
        log("@title: Verity Exchange Games Product is displayed/dissappear in member site when active/inactive");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(memberAccount, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);

        log("Step 3.1 In-active Exchange Games product");
        editDownLinePage.productStatusSettingInforSection.updateProduct("Exchange Games", false);
        page.submitEditDownline();
        page.closeSubmitEditDownlinePopup();
        page.logout();

        log("Step 4.1 Login Member Site");
        loginMember(memberAccount, password);
        log("4.1 Verify Exchange Games product disappears");
        Assert.assertFalse(memberHomePage.header.isProductTabDisplay("Exchange Games"), "ERROR! Product tab is not disappeared");

        log("Step 1.2 Login agent again and search the account");
        loginAgent(username, password, true);
        page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(memberAccount, "Active", "Member");
        editDownLinePage = page.clickEditIcon(memberAccount);

        log("Step 3.3 Active Exchange Games product");
        editDownLinePage.productStatusSettingInforSection.updateProduct("Exchange Games", true);
        page.submitEditDownline();
        page.closeSubmitEditDownlinePopup();
        page.logout();

        log("Step 4.2 Login Member Site");
        loginMember(memberAccount, password);
        log("Step 4.2 Verify Exchange Games product appears");
        Assert.assertTrue(memberHomePage.header.isProductTabDisplay("Exchange Games"), "ERROR! Product tab is not appeared");
    }
    @TestRails(id = "3547")
    @Test(groups = {"interaction"})
    @Parameters({"username", "password", "memberAccount"})
    public void Agent_AM_Downline_Listing_Edit_User_3547(String username, String password, String memberAccount) throws Exception {
        log("@title: Verity Live Dealer Asian Product is displayed/dissappear in member site when active/inactive");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(memberAccount, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);

        log("Step 3.1 In-active Live Dealer Asian product");
        editDownLinePage.productStatusSettingInforSection.updateProduct("Live Dealer Asian", false);
        page.submitEditDownline();
        page.closeSubmitEditDownlinePopup();
        page.logout();

        log("Step 4.1 Login Member Site");
        loginMember(memberAccount, password);
        log("4.1 Verify Exchange Games product disappears");
        Assert.assertFalse(memberHomePage.header.isProductTabDisplay("Live Dealer Asian"), "ERROR! Product tab is not disappeared");

        log("Step 1.2 Login agent again and search the account");
        loginAgent(username, password, true);
        page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(memberAccount, "Active", "Member");
        editDownLinePage = page.clickEditIcon(memberAccount);

        log("Step 3.3 Active Live Dealer Asian product");
        editDownLinePage.productStatusSettingInforSection.updateProduct("Live Dealer Asian", true);
        page.submitEditDownline();
        page.closeSubmitEditDownlinePopup();
        page.logout();

        log("Step 4.2 Login Member Site");
        loginMember(memberAccount, password);
        log("Step 4.2 Verify Live Dealer Asian product appears");
        Assert.assertTrue(memberHomePage.header.isProductTabDisplay("Live Dealer Asian"), "ERROR! Product tab is not appeared");
    }
    @TestRails(id = "3548")
    @Test(groups = {"interaction"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Downline_Listing_Edit_User_3548(String memberAccount, String password) throws Exception {
        log("@title: Verify player display change password after agent update password");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(memberAccount, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);

        log("Step 3.1 In-active Live Dealer Asian product");
        editDownLinePage.accountInforSection.txtPassword.sendKeys(StringUtils.decrypt(password));
        page.submitEditDownline();
        page.closeSubmitEditDownlinePopup();
        page.logout();
        createDriver(memberLoginURL);
        loginMember(memberAccount, password, false, "", "", false);
        landingPage.login(memberAccount, StringUtils.decrypt(password), false);
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();
        Assert.assertTrue(changePasswordPage.btnChangePassword.isDisplayed(), "ERROR! Change Password page does not display");
        changePasswordPage.skip();
    }
    @TestRails(id = "3549")
    @Test(groups = {"interaction"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Downline_Listing_Edit_User_3549(String memberAccount, String password) throws Exception {
        log("@title: Verify player cannot login member site when the account inactive");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(memberAccount, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);

        log("Step 3.1 In-acctive the account");
        editDownLinePage.accountInforSection.selectAccountStatus(AGConstant.AgencyManagement.DownlineListing.LST_ACCOUNT_STATUS.get(2));
        page.submitEditDownline();
        page.closeSubmitEditDownlinePopup();
        page.logout();
        createDriver(memberLoginURL);
        String errorMessage = landingPage.loginInvalid(memberAccount, StringUtils.decrypt(password));
        Assert.assertEquals(errorMessage, "Your account has been Inactive, please contact your Upline for help.", "ERROR! Account inactive message is not displayed");
    }
    @TestRails(id = "3550")
    @Test(groups = {"interaction"})
    @Parameters({"memberAccount", "username", "password", "isLogin"})
    public void Agent_AM_Downline_Listing_Edit_User_3550(String memberAccount, String username, String password, boolean isLogin) throws Exception {
        log("@title: Verify player login but cannot place bet  on exchange when account is suspended");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        Event event = EventBetSizeSettingUtils.getEventList("Soccer", userID, "TODAY").get(0);

        log("Step 2. Click on Edit icon of Active Member level");
        page.searchDownline(memberAccount, "Active", "Member");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);

        log("Step 3. Suspend the account");
        editDownLinePage.accountInforSection.selectAccountStatus(AGConstant.AgencyManagement.DownlineListing.LST_ACCOUNT_STATUS.get(3));
        page.submitEditDownline();
        page.closeSubmitEditDownlinePopup();
        page.logout();

        log("Step 4. Login to member site");
        loginMember(memberAccount, password, false, "", "", false);
        memberHomePage = landingPage.login(memberAccount, StringUtils.decrypt(password), true);
        memberHomePage.closeBannerPopup();

        log("Step 5. Place bet with suspended account");
        String minBet = BetUtils.getMinBet("SOCCER", "LAY");
        MarketPage marketPage = memberHomePage.leftMenu.searchEvent(event.getEventName(), true);

        log("Step 3:Click on an Back odds without empty of the selection have the high potential win");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        memberHomePage.betsSlipContainer.placeBet("1.01", minBet);

        log("Verify suspended error message displays");
        Assert.assertTrue(memberHomePage.betsSlipContainer.isErrorDisplayed(memberHomePage.betsSlipContainer.lblSuspendedErrorMessage, "has been Suspended"), "ERROR! Suspended error message is not displayed");

        //clean up data
        loginAgent(username, password, isLogin);
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.updateStatus(memberAccount, AGConstant.AgencyManagement.DownlineListing.LST_ACCOUNT_STATUS.get(1), true);
    }
    @TestRails(id = "3524")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3524() throws Exception {
        log("@title:Verify can Suspend the account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "Active", "Member");
        EditDownLinePage editUserPage = page.clickEditIcon(loginID);

        log("Step  3. Change account status to Suspended and click on Submit button\n" +
                "     *              Click Ok button on Edit Member popup");
        editUserPage.accountInforSection.inputInfo("","", "Suspended");
        page.submitEditDownline();
        String message = editUserPage.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");
        Assert.assertEquals(page.getAccountStatus(loginID), "Suspended", String.format("FAILED! Account status of account %s is not Suspended", loginID));

        log("Verify 2. Downline Listing display Account Status is Suspended");
        editUserPage = page.clickEditIcon(loginID);

        log("Step 6. Change account status to Active and Submit and click Ok button");
        editUserPage.accountInforSection.inputInfo("","", "Active");
        page.submitEditDownline();

        log("Verify 4. Downline Listing display Account Status is Active");
        Assert.assertEquals(page.getAccountStatus(loginID), "Active", String.format("FAILED! Account status of account %s is not Suspended", loginID));

        log("INFO: Executed completely");
    }
    @TestRails(id = "3525")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3525() throws Exception {
        log("@title:Verify can Close the account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        page.searchDownline(loginID, "Active", "Member");
        EditDownLinePage editUserPage = page.clickEditIcon(loginID);

        log("Step  3. Change account status to Suspended and click on Submit button\n" +
                "     *              Click Ok button on Edit Member popup");
        editUserPage.accountInforSection.inputInfo("", "", "Closed");
        page.submitEditDownline();
        String message = editUserPage.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");
        Assert.assertFalse(page.getAccountStatusDropdown(loginID).isEnabled(), String.format("FAILED! Account status of account %s is not disable when closed", loginID));

        log("INFO: Executed completely");
    }
    @TestRails(id = "3528")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3528() throws Exception {
        log("@title:Verify cannot inactive all product");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editUserPage = page.clickEditIcon(loginID);

        log("Step  3. Inactive all products (Exchange, Exchange Game, Live Dealer Asian, Live Dealer European, Lottery & Slots)");
        //TODO: Wirte function inactive product
        //editUserPage.productSettingsSection.productStatusSettingsSection.;
        String message = editUserPage.getMessageUpdate(true);

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_ALL_PRODUCT_NOT_SELECT, "FAILED! Message update downline is not correct");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3529")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3529() throws Exception {
        log("@title:Verify navigate to Downline List if click on Cancel button");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editUserPage = page.clickEditIcon(loginID);

        log("Step 3 Click Cancel Button on Edit User page");
        editUserPage.getBtnCancel().click();

        log("Verify 1 Verify Edit Member popup display with the message \"Member was update successfully\"");
        Assert.assertEquals(page.header.lblPageTitle.getText(), AGConstant.HomePage.DOWNLINE_LISTING, "FAILED! Dowline Listing page not display when clicking Cancel from Edit User page");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3530")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3530() throws Exception {
        //TODO: implement this case
        Assert.assertTrue(false,"");
        log("INFO: Executed completely");
    }
}

