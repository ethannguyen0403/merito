package agentsite.testcase.satsport.agencymanagement;

import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.CreateUserPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.EventBetSizeSettingUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.CREATE_USER;

public class CreateUserTest extends BaseCaseMerito {
    /**
     * @title: Validate can Create User for Fair999 100 accounts
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     *          2. Input required field and click on Submit button
     * @expect: 1. Popup Create Downline with the message "Downline was created successfully"
     *          2. Validate the popup is disappear when click on OK button
     *          3. Valid can login member site with the created account
     *          4. Verify change password page display after login
     *          5. Click on Skip Home page display
     */
    @Test (groups = {"fair999"})
    public void Agent_AM_CreateUser_100() throws Exception {
        log("@title: Validate can Create User successfully");
        log("Step 1. Navigate Agency Management > Create User");
        // String loginID = "ATPLlhLx";
        // String password = "1234qwer";
        //  CreateUserPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT,CREATE_USER, CreateUserPage.class);
        CreateUserPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER, CreateUserPage.class);
        for (int i = 0; i < 100; i++) {

            String loginID = String.format("ATPL%s", StringUtils.generateAlphabetic(5));
            String password = "1111qqqq";
            log("Step 2. Input required field and click on Submit button");

            page.createUser(loginID, password, "10", "");

            log("Verify 1. Popup Create Downline with the message \"Downline was created successfully\"");
            Assert.assertTrue(page.successPopup.isDisplayed(), "FAILED! Success popup does not display after create user");
        Assert.assertEquals(page.successPopup.getContentMessage(),AGConstant.AgencyManagement.CreateUser.CREATE_USER_SUCCEESS_MESSAGE,"FAILED! Success message after create user is incorrect");

        log("Verify 2. Validate the popup is disappear when click on OK button");
        page.successPopup.close();}

       /* Assert.assertFalse(page.successPopup.isPopupDisplay(),"FAILED! Create Downline popup not disappear after clicking OK button");

        log("Verify 3. Valid can login member site with the created account");
        DriverManager.getDriver().switchToParentFrame();
        page.logout();

        loginMemberviaUI(loginID,password);
        ChangePasswordPage changePaswordPage = new ChangePasswordPage();

        log("Verify 4. Verify change password page display after login");
        Assert.assertEquals(changePaswordPage.getPageUrl(),String.format("%s#/2/change-password",environment.getMemberSiteURL()),"FAILED! Change password page not display when login member with new account");

        log("Verify 5. Click on Skip Home page display");
        pages.sat.tabexchange.HomePage memberHomePage = changePaswordPage.skip();
        memberagentHomePage.waitMenuLoading();
        Assert.assertTrue(memberagentHomePage.ddbMyAccount.isDisplayed(5), "ERROR: ddbMyAccount doesn't display after signed in");*/

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate if input incorrect Login ID format
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     *           2. Input incorrect Login ID format
     * @expect: 1. Message "Login ID is invalid." display next to Cancel button
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateUser_007() {
        log("@title: Validate if input incorrect Login ID format");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER, CreateUserPage.class);

        log("Step 2. Input incorrect Login ID format");
        page.createUser("1111","1234qwer");

        log("Verified 1. Message \"Login ID is invalid.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(),AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_INVALID,String.format("FAILED! Expected error message is %s but found",AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate if input incorrect Change Password format
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Create User
     *          2. Input correct Login ID and incorrect password format
     * @expect: 1. Message "Password is invalid." display next to Cancel button
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateUser_008()   {
        log("@title: Validate if input incorrect Change Password format");
        log("Step 1. Navigate Agency Management > Create User");
        String loginID = String.format("ATPL%s",StringUtils.generateAlphabetic(4));
        String password = "p@ssword";
        CreateUserPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER, CreateUserPage.class);

        log("Step  2. Input correct Login ID and incorrect password format");
        page.createUser(loginID,password);

        log("Verified 1. Message \"Password is invalid.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(),AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID,String.format("FAILED! Expected error message is %s but found",AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate cannot create downline with the exist Login ID
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Create User
     *          2. Input Login ID that exist in the system and correct password then click submit
     * @expect: 1. Popup Create Downline with the message "Login ID already exist."
     */
    @Test (groups = {"smoke"})
    @Parameters({"memberAccount"})
    public void Agent_AM_CreateUser_009(String memberAccount)   {
        log("@title: Validate cannot create downline with the exist Login ID");
        log("Step 1. Navigate Agency Management > Create User");
        String password = "1234qwer";
        CreateUserPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER, CreateUserPage.class);

        log("Step 2. Input Login ID that exist in the system and correct password then click submit");
        page.createUser(memberAccount,password);

        log("Verified  1. Popup Create Downline with the message \"Login ID already exist.\"");
        Assert.assertEquals(page.successPopup.getContentMessage(),AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_EXIST,String.format("FAILED! Expected error message is %s but found",AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_EXIST,page.successPopup.getContentMessage()));

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate cannot create downline if input invalid Min Bet Setting
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     *         2. Input valid Login ID and Password
     *         3. Input invalid Min bet setting less than required
     * @expect: 1. Message "Min Bet is invalid." and the valid is highlight
     */
    @Test (groups = {"smoke"})
    @Parameters({"currency"})
    public void Agent_AM_CreateUser_010(String currency)   {
        log("@title: Validate cannot  create downline if input invalid Min Bet Setting");
        log("Step 1. Navigate Agency Management > Create User");
        AccountInfo acc = ProfileUtils.getProfile();
//        List<String> betSettingInfo = EventBetSizeSettingUtils.getUserBetSetting("EXCHANGE",acc.getUserID(),"SOCCER");
//        int min = Integer.parseInt(String.format("%.0f", Double.parseDouble(betSettingInfo.get(0)))) ;
//        int max = (Integer.parseInt(String.format("%.0f", Double.parseDouble(betSettingInfo.get(1)))) +min);
        String loginID = String.format("ATPL%s",StringUtils.generateAlphabetic(4));
        String password = "1234qwer";
        CreateUserPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER, CreateUserPage.class);
        
        log("Step Get list validation setting");
        List<ArrayList<String>> lstBetSettingValidation = page.productSettingsSection.betSettingSectionExchange.getBetSettingValidationValueLst(currency);
        String minBet =Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1))-1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        lstBetSetting.add(minBetLst);

        log("Step 2. Input valid Login ID and Password");
        log("Step 3. Input invalid Min bet setting less than required");
        page.accInfoSection.inputInfo(loginID,password,"");
        page.productSettingsSection.betSettingSectionExchange.inputBetSetting(lstBetSetting);
        page.btnSubmit.click();

        log("Verified  1. Message \"Min Bet is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(),AGConstant.AgencyManagement.CreateUser.LBL_MIN_INVALID,String.format("FAILED! Expected error message is %s but found",AGConstant.AgencyManagement.CreateUser.LBL_MIN_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate cannot create downline if input invalid Max Bet Setting
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     *         2. Input valid Login ID and Password
     *         3. Input invalid Max bet setting greater than required
     *
     * @expect: 1. Message "Max Bet is invalid." and the valid is highlight
     */
    @Test (groups = {"smoke"})
    @Parameters({"currency"})
    public void Agent_AM_CreateUser_010_1(String currency)   {
        log("@title: Validate cannot  create downline if input invalid= -Max Bet Setting");
        log("Step 1. Navigate Agency Management > Create User");
        AccountInfo acc = ProfileUtils.getProfile();
        List<String> betSettingInfo = EventBetSizeSettingUtils.getUserBetSetting("EXCHANGE",acc.getUserID(),"SOCCER");
        int max = (Integer.parseInt(String.format("%.0f", Double.parseDouble(betSettingInfo.get(1)))) +1);
        String loginID = String.format("ATPL%s",StringUtils.generateAlphabetic(4));
        String password = "1234qwer";
        CreateUserPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER, CreateUserPage.class);
        log("Step Get list validation setting");
        log("Step Get list validation setting");
        List<ArrayList<String>> lstBetSettingValidation = page.productSettingsSection.betSettingSectionExchange.getBetSettingValidationValueLst(currency);
        String minBet =Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1)));
        String maxBet =String.format("%.2f",Double.parseDouble(lstBetSettingValidation.get(1).get(1))+1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        ArrayList<String> maxBetLst = new ArrayList<String>(
                Arrays.asList(maxBet, maxBet, maxBet, maxBet, maxBet, maxBet));
        lstBetSetting.add(minBetLst);
        lstBetSetting.add(maxBetLst);

        log("Step 2. Input valid Login ID and Password");
        log("Step 3. Input invalid Min bet setting less than required");
        page.accInfoSection.inputInfo(loginID,password,"");
        page.productSettingsSection.betSettingSectionExchange.inputBetSetting(lstBetSetting);
        page.btnSubmit.click();

        log("Verified 1. Message \"Max Bet is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(),AGConstant.AgencyManagement.CreateUser.LBL_MAX_INVALID,String.format("FAILED! Expected error message is %s but found",AGConstant.AgencyManagement.CreateUser.LBL_MAX_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");

    }
   

    /**
     * @title: Validate cannot create downline if  deposit/ update credit limit over the valid
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     *         2. Input valid Login ID and Password
     *        3. Input First Time Deposit value greater than the required value
     * @expect: 1. For Credit Cash line, display the message "Balance Deposit is invalid."
     *
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateUser_011()   {
        log("@title: Validate cannot  create downline if  deposit/ update credit limit over the valid");
        log("Step 1. Navigate Agency Management > Create User");
        String loginID = String.format("ATPL%s",StringUtils.generateAlphabetic(4));
        String password = "1234qwer";
        CreateUserPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER, CreateUserPage.class);

        log("Step 2. Input valid Login ID and Password");
        log("Step 3. Input First Time Deposit value greater than the required value");
        String defineFirstTimeDepositValue = String.format("%.2f",page.cashBalanceSection.getFirstTimeDepositLimit("") +1);
        page.createUser(loginID,password,"",defineFirstTimeDepositValue);

        log("Verified  1. For Credit Cash line, display the message \"Balance Deposit is invalid.\"");
        Assert.assertEquals(page.lblErrorMsg.getText(),AGConstant.AgencyManagement.CreateUser.LBL_BALANCE_DEPOSIT_INVALID,String.format("FAILED! Expected error message is %s but found",AGConstant.AgencyManagement.CreateUser.LBL_BALANCE_DEPOSIT_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can Create User successfully
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Create User
     *          2. Input required field and click on Submit button
     * @expect: 1. Popup Create Downline with the message "Downline was created successfully"
     *          2. Validate the popup is disappear when click on OK button
     *          3. Valid can login member site with the created account
     *          4. Verify change password page display after login
     *          5. Click on Skip Home page display
     */
    @Test (groups = {"smoke"})
    @Parameters("password")
    public void Agent_AM_CreateUser_004(String password) throws Exception {
        log("@title: Validate can Create User successfully");
        log("Step 1. Navigate Agency Management > Create User");
        String loginID = String.format("ATPL%s",StringUtils.generateAlphabetic(4));
        String passwordDecrypt =StringUtils.decrypt(password);

        CreateUserPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER, CreateUserPage.class);

        log("Step 2. Input required field and click on Submit button");
        page.createUser(loginID,passwordDecrypt);

        log("Verify 1. Popup Create Downline with the message \"Downline was created successfully\"");
        Assert.assertTrue(page.successPopup.isDisplayed(),"FAILED! Success popup does not display after create user");
        Assert.assertEquals(page.successPopup.getContentMessage(),AGConstant.AgencyManagement.CreateUser.CREATE_USER_SUCCEESS_MESSAGE,"FAILED! Success message after create user is incorrect");

        log("Verify 2. Validate the popup is disappear when click on OK button");
        page.successPopup.close();
        Assert.assertFalse(page.successPopup.isDisplayed(),"FAILED! Create Downline popup not disappear after clicking OK button");

        log("Verify 3. Valid can login member site with the created account");
        page.logout().txtUsername.isDisplayed(1);

       /* BaseCaseMember.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passwordDecrypt);
        ChangePasswordPage changePaswordPage = new ChangePasswordPage();
        changePaswordPage.btnChangePassword.isDisplayed(1);

        log("Verify 4. Verify change password page display after login");
        Assert.assertEquals(changePaswordPage.getPageUrl(),String.format("%s#/2/change-password",environment.getMemberSiteURL()),"FAILED! Change password page not display when login member with new account");

        log("Verify 5. Click on Skip Home page display");
        pages.sat.tabexchange.HomePage memberHomePage = changePaswordPage.skip();
        Assert.assertTrue(memberagentHomePage.ddbMyAccount.isDisplayed(5), "ERROR: ddbMyAccount doesn't display after signed in");*/

        log("INFO: Executed completely");
    }
}

