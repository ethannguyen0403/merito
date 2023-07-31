package agentsite.testcase.agencymanagement;

import agentsite.pages.agentmanagement.CreditBalanceListingPage;
import agentsite.pages.agentmanagement.SubUserListingPage;
import agentsite.pages.agentmanagement.subuserlisting.SubUserPopup;
import baseTest.BaseCaseTest;
import common.AGConstant;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.HashMap;
import java.util.List;

import static common.AGConstant.BTN_CANCEL;
import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.HomePage.*;
import static common.AGConstant.SubUserListing.*;

public class SubUserListingTest extends BaseCaseTest {
    /**
     * @title: Can create Sub user successfully with full permissions
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Sub User Listing
     * 2. Click on Create button
     * 3. Input all info and check all permission
     * 4. Click Submit
     * @expect: 1. Verify Sub user is created with all permission
     * 2. Verify can login agent with new sub account
     */
    @Test(groups = {"smoke"})
    @Parameters("password")
    public void Agent_AM_Sub_User_Listing_0004(String password) throws Exception {
        log("@title: Can create Sub user successfully with full permissions");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        String pwDecrypt = StringUtils.decrypt(password);

        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", true);
                put("Update Account", true);
                put("View Account", true);
                put("Report", true);
                put("Transfer & Deposit/Withdraw", true);
                put("Account Balance", true);
            }
        };

        log("Step 2. Click on Create button");
        log("Step 3. Input all info and check all permission");
        log("Step 4. Click Submit");
        String subUsername = page.createSubUser("", pwDecrypt, "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);
        log("Verify 1. Verify Sub user is created with all permission");
        Assert.assertTrue(page.verifySubUserInfo(subUsername, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Verify can login agent with new sub account");
        loginNewAccount(sosAgentURL, agentNewAccURL, subUsername, password, "112233");
        Assert.assertEquals(agentHomePage.leftMenu.lblLoginID.getText(), subUsername, "Failed!, Login ID lable display incorrect");
        log("INFO: Executed completely");
    }


    @Test(groups = {"http_request"})
    public void Agent_AM_Sub_User_Listing_001() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        log("Step 2. Click on Create button");
        agentHomePage.navigateSubUserListingPage();

        log("Verify 1. Sub User Listing page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error displayed");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Sub_User_Listing_002() {
        log("@title: Verify Sub User Listing UI display correct");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();

        log("Verify 1. Verify UI Sub User Listing display correctly");
        Assert.assertEquals(page.btnCreate.getText(), "Create", "FAILED! Create button incorrect display");
        Assert.assertEquals(page.tblSubUSer.getHeaderNameOfRows(), TBL_SUB_USER_TABLE_NONPO, "FAILED! Header table column is incorrect display");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Sub_User_Listing_003() {
        log("@title: Verify Create Sub User UI popup display correct");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();

        log("Step 2. Click on Create button");
        SubUserPopup popup = page.openCreateUserPopup();

        log("Verify 1. Verify UI Sub User Listing display correctly");
        Assert.assertEquals(popup.lblTitle.getText(), "Create Sub User", "FAILED! Popup title is incorrect");
        Assert.assertEquals(popup.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect display");
        Assert.assertEquals(popup.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is incorrect display");
        Assert.assertTrue(popup.txtPassword.isDisplayed(), "FAILED! Password textbox not display");
        Assert.assertTrue(popup.ddbStatus.isDisplayed(), "FAILED! Status dropdown box not dipslay");
        Assert.assertTrue(popup.txtFirstName.isDisplayed(), "FAILED! First Name textbox not display");
        Assert.assertTrue(popup.txtLastName.isDisplayed(), "FAILED! Last Name textbox not display");
        Assert.assertEquals(popup.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is incorrect display");
        List<String> lstPermisison = popup.permisisonList.getListSubMenu();
        Assert.assertEquals(lstPermisison, PERMISSION_LIST_SAD, "FAILED! List Permission on Create Sub User");
        List<String> lstFormHeader = popup.tblForm.getColumn(1, false);
        Assert.assertEquals(lstFormHeader, CREATE_SUB_USER_FORM, "FAILED! Labels Create Sub User");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters("password")
    public void Agent_AM_Sub_User_Listing_005(String password) throws Exception {
        log("@title:Verify Sub account only setting for a create account permission");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", true);
                put("Update Account", false);
                put("View Account", false);
                put("Report", false);
                put("Transfer & Deposit/Withdraw", false);
                put("Account Balance", true);
                put("Markets Management", false);
                put("Fraud Detection", false);
            }
        };
        String pwDecrypt = StringUtils.decrypt(password);
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        String userName = page.getActiveSubUser("", pwDecrypt);

        log("2. Click on Edit for any account and only active Create Account permission");
        log("3. Click Submit");
        page.editSubUser(userName, pwDecrypt, "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);

        log("Verify 1.Verify Green check only display for Create Account and Account Balance column\n" +
                "2. Verify there are menu is available when login sub account\n" +
                "- Create Downline Agent\n" +
                "- Create User");
        Assert.assertTrue(page.verifySubUserInfo(userName, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Verify can login agent with new sub account");
        loginNewAccount(sosAgentURL, agentNewAccURL, userName, password, "112233");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstSubMennu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMennu.get(0), CREATE_DOWNLINE_AGENT, "Failed! Create Downline Agent not display correctly");
        Assert.assertEquals(lstSubMennu.get(0), CREATE_USER, "Failed! Create User not display correctly");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters("password")
    public void Agent_AM_Sub_User_Listing_006(String password) throws Exception {
        log("@title: Verify Sub account only setting for Update Account permission");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", false);
                put("Update Account", true);
                put("View Account", false);
                put("Report", false);
                put("Transfer & Deposit/Withdraw", false);
                put("Account Balance", true);
                put("Markets Management", false);
                put("Fraud Detection", false);
            }
        };
        String pwDecrypt = StringUtils.decrypt(password);
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        String userName = page.getActiveSubUser("", pwDecrypt);

        log("2.Click on Edit for any account and only active Update Account permission");
        log("3. Click Submit");
        page.editSubUser(userName, pwDecrypt, "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);

        log("Verify 1.Verify Green check only display for Create Account and Account Balance column\n" +
                "2. Verify there are menu is available when login sub account\n" +
                "- Create Downline Agent\n" +
                "- Create User\n" +
                "- Downline Listing");
        Assert.assertTrue(page.verifySubUserInfo(userName, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Verify can login agent with new sub account");
        loginNewAccount(sosAgentURL, agentNewAccURL, userName, password, "112233");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstSubMennu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMennu.get(0), DOWNLINE_LISTING, "Failed! Downline Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(1), EVENT_BET_STIE_SETTINGS, "Failed! Event Bet Size Settings not display correctly");
        Assert.assertEquals(lstSubMennu.get(2), POSITION_TAKING_LISTING, "Failed! Position Taking Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(3), COMMISSION_LISTING, "Failed!Commission Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(4), BET_SETTING_LISTING, "Failed! Bet Setting Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(5), TAX_SETTING_LISTING, "Failed! Tax Settings not display correctly");
        Assert.assertEquals(lstSubMennu.get(6), "Announcement", "Failed! Announcement not display correctly");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters("password")
    public void Agent_AM_Sub_User_Listing_007(String password) throws Exception {
        log("@title: Verify Sub account only setting for Report permission");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", false);
                put("Update Account", false);
                put("View Account", false);
                put("Report", true);
                put("Transfer & Deposit/Withdraw", false);
                put("Account Balance", true);
                put("Markets Management", false);
                put("Fraud Detection", false);
            }
        };
        String pwDecrypt = StringUtils.decrypt(password);
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        String userName = page.getActiveSubUser("", pwDecrypt);

        log("2. Click on Edit for any account and only acitve Report permission");
        log("3. Click Submit");
        page.editSubUser(userName, "", "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);

        log("Verify 1. Verify Green check only display for Report and Account Balance column ");
        Assert.assertTrue(page.verifySubUserInfo(userName, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Only Report section display with enough menu and verify Sub account only see the menu when login");
        loginNewAccount(sosAgentURL, agentNewAccURL, userName, password, "112233");
        agentHomePage.leftMenu.leftMenuList.expandMenu(REPORT);
        Assert.assertTrue(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(REPORT), "FAILED! Report menu does not display");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(AGENCY_MANAGEMENT), "FAILED! Agency Management menu does not display");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(MARKET_MANAGEMENT), "FAILED! Markets Management menu does not display");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters("password")
    public void Agent_AM_Sub_User_Listing_008(String password) throws Exception {
        log("@title: Verify Sub account only setting for Transfer & Deposit/Withdraw permission");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", false);
                put("Update Account", false);
                put("View Account", false);
                put("Report", false);
                put("Transfer & Deposit/Withdraw", true);
                put("Account Balance", true);
                put("Markets Management", false);
                put("Fraud Detection", false);
            }
        };
        String pwDecrypt = StringUtils.decrypt(password);
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        String userName = page.getActiveSubUser("", pwDecrypt);

        log("Step 2. Click on Edit for any account and only acitve Transfer & Deposit/Withdraw permission");
        log("Step 3. Click Submit");
        page.editSubUser(userName, pwDecrypt, "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);

        log("Verify 1. Verify Green check only display forTransfer & Deposit/Withdraw and Account Balance column");
        Assert.assertTrue(page.verifySubUserInfo(userName, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Only  Agency Management only display Deposit/Withdraw ");
        loginNewAccount(sosAgentURL, agentNewAccURL, userName, password, "112233");
        List<String> lstSubMennu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMennu.get(0), DEPOSIT_WITHDRAW, "Failed! Downline Listing not display correctly");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(REPORT), "FAILED! Report menu does not display");
        Assert.assertTrue(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(AGENCY_MANAGEMENT), "FAILED! Agency Management menu does not display");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(MARKET_MANAGEMENT), "FAILED! Markets Management menu does not display");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters("password")
    public void Agent_AM_Sub_User_Listing_009(String password) throws Exception {
        log("@title: Verify Sub account only setting for Markets Management permission");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", false);
                put("Update Account", false);
                put("View Account", false);
                put("Report", false);
                put("Transfer & Deposit/Withdraw", false);
                put("Account Balance", true);
                put("Markets Management", true);
                put("Fraud Detection", false);
            }
        };
        String pwDecrypt = StringUtils.decrypt(password);
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        String userName = page.getActiveSubUser("", pwDecrypt);

        log("2.Click on Edit for any account and only active Markets Management permission");
        page.editSubUser(userName, pwDecrypt, "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);

        log("Verify 1. Verify Green check only display for Markets Management and Account Balance column");
        Assert.assertTrue(page.verifySubUserInfo(userName, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify2. Only Markets Management  section display with enough menu and verify Sub account only see the menu when login");
        loginNewAccount(sosAgentURL, agentNewAccURL, userName, password, "112233");
        agentHomePage.leftMenu.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstSubMennu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);
        Assert.assertEquals(lstSubMennu.get(0), BLOCK_RACING, "Failed! Block Racing not display correctly");
        Assert.assertEquals(lstSubMennu.get(1), BLOCK_UNBLOCK_EVENT, "Failed!Block/Unblock Events not display correctly");
        Assert.assertEquals(lstSubMennu.get(2), BLOCK_UNBLOCK_COMPETITION, "Failed! Block/Unblock Competitions not display correctly");
        Assert.assertEquals(lstSubMennu.get(3), CURRENT_BLOCKING, "Failed! Current Blocking not display correctly");
        Assert.assertEquals(lstSubMennu.get(4), BLOCKING_LOG, "Failed! Blocking Log not display correctly");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(REPORT), "FAILED! Markets Management menu does not display");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(AGENCY_MANAGEMENT), "FAILED! Markets Management menu does not display");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3645")
    @Test(groups = {"http_request"})
    public void Agent_SAD_Sub_User_Listing_3645() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        log("Step 2. Click on Create button");
        agentHomePage.navigateSubUserListingPage();

        log("Verify 1. Sub User Listing page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error displayed");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3646")
    @Test(groups = {"regression_sat"})
    public void Agent_SAD_Sub_User_Listing_3646() {
        log("@title: Verify Sub User Listing UI display correct");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();

        log("Verify 1. Verify UI Sub User Listing display correctly");
        Assert.assertEquals(page.btnCreate.getText(), "Create", "FAILED! Create button incorrect display");
        Assert.assertEquals(page.tblSubUSer.getHeaderNameOfRows(), TBL_SUB_USER_TABLE_NONPO, "FAILED! Header table column is incorrect display");
        Assert.assertTrue(page.btnCreate.isEnabled(), "FAILED! Creat button not enable");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3647")
    @Test(groups = {"regression_sat"})
    public void Agent_SAD_Sub_User_Listing_3647() {
        log("@title: Verify Create Sub User UI popup display correct");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();

        log("Step 2. Click on Create button");
        SubUserPopup popup = page.openCreateUserPopup();

        log("Verify 1. Verify UI Sub User Listing display correctly");
        Assert.assertEquals(popup.lblTitle.getText(), "Create Sub User", "FAILED! Popup title is incorrect");
        Assert.assertTrue(popup.lblUserNamePrefix.isDisplayed(), "FAILED! Username prefix not display");
        Assert.assertTrue(popup.ddbFirstCharOfUserName.isDisplayed(), "FAILED! First char of username dropdown box not display");
        Assert.assertTrue(popup.ddbSecondCharOfUserName.isDisplayed(), "FAILED! Second char of username dropdown box not display");
        Assert.assertEquals(popup.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect display");
        Assert.assertEquals(popup.btnCancel.getText(), BTN_CANCEL, "FAILED! Cancel button is incorrect display");
        Assert.assertTrue(popup.txtPassword.isDisplayed(), "FAILED! Password textbox not display");
        Assert.assertTrue(popup.ddbStatus.isDisplayed(), "FAILED! Status dropdown box not dipslay");
        Assert.assertTrue(popup.txtFirstName.isDisplayed(), "FAILED! First Name textbox not display");
        Assert.assertTrue(popup.txtLastName.isDisplayed(), "FAILED! Last Name textbox not display");
        Assert.assertEquals(popup.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is incorrect display");
        List<String> lstPermisison = popup.permisisonList.getListSubMenu();
        Assert.assertEquals(lstPermisison, PERMISSION_LIST_SAD, "FAILED! List Permission on Create Sub User");
        List<String> lstFormHeader = popup.tblForm.getColumn(1, false);
        Assert.assertEquals(lstFormHeader, CREATE_SUB_USER_FORM, "FAILED! Labels Create Sub User");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3648")
    @Test(groups = {"regression_sat"})
    @Parameters("password")
    public void Agent_SAD_Sub_User_Listing_3648(String password) throws Exception {
        log("@title:Verify Sub account only setting for a create account permission");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", true);
                put("Update Account", false);
                put("View Account", false);
                put("Report", false);
                put("Transfer & Deposit/Withdraw", false);
                put("Account Balance", true);
                put("Markets Management", false);
                put("Fraud Detection", false);
            }
        };
        String pwDecrypt = StringUtils.decrypt(password);
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        String userName = page.getActiveSubUser("", pwDecrypt);

        log("Step 2. Click on Edit for any account and only active Create Account permission");
        log("Step 3. Click Submit");
        page.editSubUser(userName, pwDecrypt, "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);

        log("Verify 1.Verify Green check only display for Create Account and Account Balance column");
        Assert.assertTrue(page.verifySubUserInfo(userName, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Verify there are menu is available when login sub account\n" +
                "- Create Downline Agent\n" +
                "- Create User");
        loginNewAccount(sosAgentURL, agentNewAccURL, userName, password, "112233");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstSubMennu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMennu.get(0), CREATE_DOWNLINE_AGENT, "Failed! Create Downline Agent not display correctly");
        Assert.assertEquals(lstSubMennu.get(1), CREATE_USER, "Failed! Create User not display correctly");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3649")
    @Test(groups = {"regression_sat"})
    @Parameters("password")
    public void Agent_SAD_Sub_User_Listing_3649(String password) throws Exception {
        log("@title: Verify Sub account only setting for Update Account permission");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", false);
                put("Update Account", true);
                put("View Account", false);
                put("Report", false);
                put("Transfer & Deposit/Withdraw", false);
                put("Account Balance", true);
                put("Markets Management", false);
                put("Fraud Detection", false);
            }
        };
        String pwDecrypt = StringUtils.decrypt(password);
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        String userName = page.getActiveSubUser("", pwDecrypt);
        log("2.Click on Edit for any account and only active Update Account permission");
        log("3. Click Submit");
        page.editSubUser(userName, pwDecrypt, "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);

        log("Verify 1.Verify Green check only display for Create Account and Account Balance column");
        Assert.assertTrue(page.verifySubUserInfo(userName, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Verify there are menu is available when login sub account\n" +
                "- Create Downline Agent\n" +
                "- Create User\n" +
                "- Downline Listing");
        loginNewAccount(sosAgentURL, agentNewAccURL, userName, password, "112233");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstSubMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMenu.get(0), DOWNLINE_LISTING, "Failed! Downline Listing not display correctly");
        Assert.assertEquals(lstSubMenu.get(1), EVENT_BET_STIE_SETTINGS, "Failed! Event Bet Size Settings not display correctly");
        Assert.assertEquals(lstSubMenu.get(2), POSITION_TAKING_LISTING, "Failed! Position Taking Listing not display correctly");
        Assert.assertEquals(lstSubMenu.get(4), COMMISSION_LISTING, "Failed!Commission Listing not display correctly");
        Assert.assertEquals(lstSubMenu.get(5), BET_SETTING_LISTING, "Failed! Bet Setting Listing not display correctly");
        Assert.assertEquals(lstSubMenu.get(6), TAX_SETTING_LISTING, "Failed! Tax Settings not display correctly");
        Assert.assertEquals(lstSubMenu.get(7), ANNOUNCEMENT, "Failed! Announcement not display correctly");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3650")
    @Test(groups = {"regression_sat"})
    @Parameters("password")
    public void Agent_SAD_Sub_User_Listing_3650(String password) throws Exception {
        log("@title: Verify Sub account only setting for Report permission");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", false);
                put("Update Account", false);
                put("View Account", false);
                put("Report", true);
                put("Transfer & Deposit/Withdraw", false);
                put("Account Balance", true);
                put("Markets Management", false);
                put("Fraud Detection", false);
            }
        };
        String pwDecrypt = StringUtils.decrypt(password);
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        String userName = page.getActiveSubUser("", pwDecrypt);

        log("2. Click on Edit for any account and only acitve Report permission");
        log("3. Click Submit");
        page.editSubUser(userName, "", "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);

        log("Verify 1. Verify Green check only display for Report and Account Balance column ");
        Assert.assertTrue(page.verifySubUserInfo(userName, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Only Report section display with enough menu and verify Sub account only see the menu when login");
        loginNewAccount(sosAgentURL, agentNewAccURL, userName, password, "112233");
        agentHomePage.leftMenu.leftMenuList.expandMenu(REPORT);
        Assert.assertTrue(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(REPORT), "FAILED! Report menu does not display");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(AGENCY_MANAGEMENT), "FAILED! Agency Management menu does not display");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(MARKET_MANAGEMENT), "FAILED! Markets Management menu does not display");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3651")
    @Test(groups = {"regression_sat"})
    @Parameters("password")
    public void Agent_SAD_Sub_User_Listing_3651(String password) throws Exception {
        log("@title: Verify Sub account only setting for Transfer & Deposit/Withdraw permission");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", false);
                put("Update Account", false);
                put("View Account", false);
                put("Report", false);
                put("Transfer & Deposit/Withdraw", true);
                put("Account Balance", true);
                put("Markets Management", false);
                put("Fraud Detection", false);
            }
        };
        String pwDecrypt = StringUtils.decrypt(password);
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        String userName = page.getActiveSubUser("", pwDecrypt);

        log("Step 2. Click on Edit for any account and only acitve Transfer & Deposit/Withdraw permission");
        log("Step 3. Click Submit");
        page.editSubUser(userName, pwDecrypt, "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);

        log("Verify 1. Verify Green check only display forTransfer & Deposit/Withdraw and Account Balance column");
        Assert.assertTrue(page.verifySubUserInfo(userName, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Only Agency Management only display Deposit/Withdraw");
        loginNewAccount(sosAgentURL, agentNewAccURL, userName, password, "112233");
        List<String> lstSubMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMenu.get(0), DEPOSIT_WITHDRAW, "Failed! Downline Listing not display correctly");
        Assert.assertTrue(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(AGENCY_MANAGEMENT), "FAILED! Agency Management menu does not display");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(REPORT), "FAILED! Report menu display");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(MARKET_MANAGEMENT), "FAILED! Markets Management menu display");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3652")
    @Test(groups = {"regression_sat"})
    @Parameters("password")
    public void Agent_SAD_Sub_User_Listing_3652(String password) throws Exception {
        log("@title: Verify Sub account only setting for Markets Management permission");
        log("Step 1. Navigate Agency Management > Sub User Listing");
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Create Account", false);
                put("Update Account", false);
                put("View Account", false);
                put("Report", false);
                put("Transfer & Deposit/Withdraw", false);
                put("Account Balance", true);
                put("Markets Management", true);
                put("Fraud Detection", false);
            }
        };
        String pwDecrypt = StringUtils.decrypt(password);
        SubUserListingPage page = agentHomePage.navigateSubUserListingPage();
        String userName = page.getActiveSubUser("", pwDecrypt);

        log("2.Click on Edit for any account and only active Markets Management permission");
        page.editSubUser(userName, pwDecrypt, "Active", StringUtils.generateAlphabetic(4), StringUtils.generateAlphabetic(4), permissions);

        log("Verify 1. Verify Green check only display for Markets Management and Account Balance column");
        Assert.assertTrue(page.verifySubUserInfo(userName, permissions), "FAILED the list permission is incorrect");
        page.logout();

        log("Verify 2. Only Markets Management  section display with enough menu and verify Sub account only see the menu when login");
        loginNewAccount(sosAgentURL, agentNewAccURL, userName, password, "112233");
        agentHomePage.leftMenu.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstSubMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);
        Assert.assertEquals(lstSubMenu.get(0), BLOCK_RACING, "Failed! Block Racing not display correctly");
        Assert.assertEquals(lstSubMenu.get(1), BLOCK_UNBLOCK_EVENT, "Failed!Block/Unblock Events not display correctly");
        Assert.assertEquals(lstSubMenu.get(2), BLOCK_UNBLOCK_COMPETITION, "Failed! Block/Unblock Competitions not display correctly");
        Assert.assertEquals(lstSubMenu.get(3), CURRENT_BLOCKING, "Failed! Current Blocking not display correctly");
        Assert.assertEquals(lstSubMenu.get(4), BLOCKING_LOG, "Failed! Blocking Log not display correctly");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(REPORT), "FAILED! Markets Management menu does not display");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(AGENCY_MANAGEMENT), "FAILED! Markets Management menu does not display");

        log("INFO: Executed completely");
    }
}

