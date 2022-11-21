package agentsite.testcase.all.home;

import com.paltech.driver.DriverManager;
import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.components.ChangePasswordPopup;
import agentsite.pages.all.home.UpdateSecurityCodePage;
import util.testraildemo.TestRails;

import java.util.ArrayList;

import static agentsite.common.AGConstant.BTN_CANCEL;
import static agentsite.common.AGConstant.BTN_SUBMIT;
import static agentsite.common.AGConstant.ChangePasswordPopup.LST_LABELS;
import static agentsite.common.AGConstant.HomePage.*;
import static agentsite.common.AGConstant.SecurityCodePage.LBL_GUIDE;
import static agentsite.common.AGConstant.SecurityCodePage.TITLE;

public class HeaderTest extends BaseCaseMerito{
    /**
     * @title: Validate Change Password popup display
     * @steps:   1. Login successfully
     * 2. Click on menu icon and select Password
     * @expect: Change Password popup display
     * - Username label and Login_Lable
     * - Current Password label + textbox
     * - New Password + Textbox + Hint icon
     * - Confirm Password + Textbox
     * Submit and Cancel icon
     */
    @Test (groups = {"regression"})
    public void Header_TC004() throws Exception {
        log("@title: Validate Change Password popup display");
        log("Step 1: Click on menu icon and select Password");
        String securityCode = StringUtils.decrypt(environment.getSecurityCode());
        ChangePasswordPopup popup = agentHomePage.clickChangePasswordHeader(securityCode);
        log("Verify: Change Password popup display");
        Assert.assertTrue(popup.isDisplayed(),"FAILED! Change Password popup does not display");
        Assert.assertTrue(popup.txtCurrentPassword.isDisplayed(),"FAILED! Current Password does not display");
        Assert.assertTrue(popup.txtNewPassword.isDisplayed(),"FAILED! New Password does not display");
        Assert.assertTrue(popup.txtConfirmPassword.isDisplayed(),"FAILED! Confirm Password does not display");
        Assert.assertEquals(popup.btnSubmit.getText(),BTN_SUBMIT,"FAILED! Submit button does not display");
        Assert.assertEquals(popup.btnCancle.getText(),BTN_CANCEL,"FAILED! Cancel button does not display");
        Assert.assertEquals(popup.getLabels(),LST_LABELS,"FAILED! The list sub menu not display: Password, Security Code, OTP");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Update Security Code page display
     * @steps:   1. Login successfully
     * 2. Click on menu icon and select Security Code
     * @expect: Update Security Code page display
     * - Security Code table header
     * -Old Security Code + Textbox
     * - Security Code
     * - Confirm Security Code
     * - Submit button
     * - Hint message in red: Security Code (SC) must be:
     * *Numeric digits only.
     * *6 digits from 0-9.
     * *At least 2 different digits in the SC.
     * *Consecutive are not allowed, for example: 123456, 456789.
     * *Back consecutive are not allowed, for example: 765432.
     */
    @Test (groups = {"regression"})
    public void Header_TC005() throws Exception {
        log("@title: Validate Update Security Code page display");
        log("Step 1: Click on menu icon and select Security Code");
        String securityCode = StringUtils.decrypt(environment.getSecurityCode());
        UpdateSecurityCodePage page = agentHomePage.clickSecurityCodeHeaderMenu(securityCode);

        log("Verify: Change Password popup display");
        Assert.assertEquals(page.lblPageTitle.getText(),TITLE,"FAILED!Page title is incorrect");
        Assert.assertTrue(page.txtOldSecurityCode.isDisplayed(),"FAILED! Current Password does not display");
        Assert.assertTrue(page.txtSecurityCode.isDisplayed(),"FAILED! New Password does not display");
        Assert.assertTrue(page.txtConfrimSecurityCode.isDisplayed(),"FAILED! Confirm Password does not display");
        Assert.assertEquals(page.btnSubmit.getText(),BTN_SUBMIT,"FAILED! Submit button does not display");
        Assert.assertEquals(page.lblGuide.getText(),LBL_GUIDE,"FAILED! Submit button does not display");
        ArrayList<String> lstLabel = page.tblForm.getHeaderNameOfRows();
        Assert.assertEquals(lstLabel.get(0),"Security Code","FAILED! Security Code header table is incorrect");
        Assert.assertEquals(lstLabel.get(1),"Old Security Code","FAILED! Old Security Code is incorrect displayed");
        Assert.assertEquals(lstLabel.get(3),"Security Code","FAILED!  Security Code is incorrect displayed");
        Assert.assertEquals(lstLabel.get(5),"Confirm Security Code","FAILED! Confirm Security Code is incorrect displayed");
        Assert.assertEquals(lstLabel.get(7),BTN_SUBMIT,"FAILED! Submit button is incorrect displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can collapse /expand left menu
     * @steps:   1. Login successfully
     * 2. Click on expand icon to expand/collapse the left menu
     * @expect: Can expand /collapse left menu
     */
    @Test (groups = {"regression"})
    public void Header_TC006(){
        log("@title: Validate can collapse /expand left menu");
        log("Step 1: Click on expand icon to expand/collapse the left menu");
        log("Verify 1: Left Menu expand by default");
        Assert.assertTrue(agentHomePage.lblAppLeftMenu.isDisplayed(),"FAILED! Left menu does not expand by default");
        agentHomePage.iconLeftMenu.collapseLeftMenu();

        log("Verify 2: Left Menu collapsed when collapsing");
        Assert.assertFalse(agentHomePage.lblAppLeftMenu.isDisplayed(),"FAILED! Left menu does not collapse after collapsed");
        agentHomePage.iconLeftMenu.expandLeftMenu();

        log("Verify 3: Left Menu expanded when expanding");
        Assert.assertTrue(agentHomePage.lblAppLeftMenu.isDisplayed(),"FAILED! Left menu does not expand after expanding");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate there are sub menu when hover on expand left menu icon
     * @steps:   1. Login successfully
     * 1. Collapse left menu
     * 2.Hover on left menu icon
     * @expect: There are 3 sub menu sections display
     * Agency Management
     * Report
     * Market Management
     * Fraud Detection (For the agent is PO level)
     */
    @Test (groups = {"regression"})
    public void Header_TC0010(){
        log("@title: Validate there are sub menu when hover on expand left menu icon");
        log("Step: 1 Collapse left menu");
        agentHomePage.iconLeftMenu.collapseLeftMenu();
        agentHomePage.iconLeftMenu.moveAndHoverOnControl();

        log("Step: 2 Hover on left menu icon");
        log("Verify 1: There are 3 sub menu sections display");
        Assert.assertTrue(agentHomePage.iconLeftMenu.icAgencyManagemnt.isDisplayed(),"FAILED! Sub Icon menu AgencyManagement not display");
        Assert.assertTrue(agentHomePage.iconLeftMenu.icReport.isDisplayed(),"FAILED! Sub Icon menu Report not display");
        Assert.assertTrue(agentHomePage.iconLeftMenu.icMarketManageent.isDisplayed(),"FAILED! Sub Icon menu Market Management not display");
        Assert.assertFalse(agentHomePage.iconLeftMenu.icFraudDetection.isDisplayed(),"FAILED! Sub Icon menu Fraud Detection shoudl not not display at Non PO level");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate there are sub menu when hover on expand left menu icon
     * @steps:   1. Login successfully
     * 1. Collapse left menu
     * 2.Hover on left menu icon
     * @expect: There are 3 sub menu sections display
     * Agency Management
     * Report
     * Market Management
     * Fraud Detection (For the agent is PO level)
     */
    @Test (groups = {"poregression"})
    public void Header_TC0010_PO(){
        log("@title: Validate there are sub menu when hover on expand left menu icon");
        log("Step: 1 Collapse left menu");
        agentHomePage.iconLeftMenu.collapseLeftMenu();

        log("Step: 2 Hover on left menu icon");
        agentHomePage.iconLeftMenu.moveAndHoverOnControl();

        log("Verify 1: There are 3 sub menu sections display");
        Assert.assertTrue(agentHomePage.iconLeftMenu.icAgencyManagemnt.isDisplayed(),"FAILED! Sub Icon menu AgencyManagement not display");
        Assert.assertTrue(agentHomePage.iconLeftMenu.icReport.isDisplayed(),"FAILED! Sub Icon menu Report not display");
        Assert.assertTrue(agentHomePage.iconLeftMenu.icMarketManageent.isDisplayed(),"FAILED! Sub Icon menu Market Management not display");
        Assert.assertTrue(agentHomePage.iconLeftMenu.icFraudDetection.isDisplayed(),"FAILED! Sub Icon menu Fraud Detection not display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can expand Agent management section in left menu
     * @steps:   1. Login successfully
     * 1. Collapse left menu
     * 2. left menu icon select Agency Management icon
     * @expect: Left menu is active Agency Management menu section
     */
    @Test (groups = {"regression"})
    public void Header_TC0011() {
        log("@title: Validate can expand Agent management section in left menu");
        log("Step: 1. Collapse left menu");
        agentHomePage.iconLeftMenu.collapseLeftMenu();

        log("Step: 2.select Agency Management icon");
        agentHomePage.iconLeftMenu.moveAndHoverOnControl();
        agentHomePage.iconLeftMenu.icAgencyManagemnt.click();

        log("Verify 1: Left menu is active Agency Management menu section");
        agentHomePage.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertTrue(agentHomePage.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT).contains(DOWNLINE_LISTING), "Failed! Downline Listing not display correctly");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can expand Report section in left menu
     * @steps:   1. Login successfully
     * 1. Collapse left menu
     * 2.Hover on left menu icon select Report icon
     * @expect: Left menu is active Report menu section
     */
    @Test (groups = {"regression"})
    public void Header_TC0012(){
        log("@title: Validate can expand Report section in left menu");
        log("Step: 1 Collapse left menu");
        agentHomePage.iconLeftMenu.collapseLeftMenu();

        log("Step: 2 Hover on left menu icon");
        agentHomePage.iconLeftMenu.moveAndHoverOnControl();

        log("Step: 3.select Report icon");
        agentHomePage.iconLeftMenu.icReport.click();

        log("Verify 1: Left menu is active Report menu section");
        Assert.assertTrue(agentHomePage.isMenuExpanded(REPORT), "FAILED! The menu is not collapsed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can expand  Market Management section in left menu
     * @steps:   1. Login successfully
     * 1. Collapse left menu
     * 2.Hover on left menu icon select Market Management icon
     * @expect: Left menu is active  Market Management menu section
     */
    @Test (groups = {"regression"})
    public void Header_TC0013(){
        log("@title: Validate can expand Market Management section in left menu");
        log("Step: 1 Collapse left menu");
        agentHomePage.iconLeftMenu.collapseLeftMenu();

        log("Step: 2 Hover on left menu icon");
        agentHomePage.iconLeftMenu.moveAndHoverOnControl();

        log("Step: 3.select Market Management icon");
        agentHomePage.iconLeftMenu.icMarketManageent.click();

        log("Verify 1: Left menu is active Market Management menu section");
        Assert.assertTrue(agentHomePage.isMenuExpanded(MARKET_MANAGEMENT), "FAILED! Market Management does not active");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can expand Fraud Detection section in left menu
     * @steps:   1. Login successfully
     * 1. Collapse left menu
     * 2.Hover on left menu icon select Fraud Detection icon
     * @expect: Left menu is active Fraud Detection menu section
     */
    @Test (groups = {"poregression"})
    public void Header_TC0014_PO(){
        log("@title: Validate can expand Fraud Detection section in left menu");
        log("Step: 1 Collapse left menu");
        agentHomePage.iconLeftMenu.collapseLeftMenu();

        log("Step: 2 Hover on left menu icon");
        agentHomePage.iconLeftMenu.moveAndHoverOnControl();

        log("Step: 3.select Fraud Detection icon");
        agentHomePage.iconLeftMenu.icFraudDetection.click();

        log("Verify 1: Left menu is active Fraud Detection menu section");
        Assert.assertTrue(agentHomePage.isMenuExpanded(FRAUD_DETECTION), "FAILED! Fraud Detection does not active");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate control on header section is correctly display
     * @steps:   1. Login successfully
     * @expect: Verify UI on Header section:
     * - Timezone, language icon and dropdown, menu,  sign out button,
     * - expand left menu icon and home menu
     */
    @TestRails(id = "673")
    @Test (groups = {"smoke"})
    public void Header_TC001(){
        log("@title: Validate control on header section is correctly display");
        log("Step 1: Log in with a valid username and password");
        log("Verify: 1 Verify UI on Header section:");
        Assert.assertTrue(agentHomePage.lblTimeZone.isDisplayed(), "ERROR: Time zone is not displayed");
        Assert.assertTrue(agentHomePage.ddbLanguage.isDisplayed(), "ERROR: Language dropdown box is not displayed");
        Assert.assertTrue(agentHomePage.iconHeaderMenu.isDisplayed(), "ERROR: Header menu is not displayed");
        Assert.assertTrue(agentHomePage.iconLeftMenu.isDisplayed(),"ERROR! Collapse left menu icon not display");
        Assert.assertTrue(agentHomePage.iconHome.isDisplayed(),"ERROR! Home icon does not display");
        Assert.assertEquals(agentHomePage.btnSignOut.getText(), AGConstant.HomePage.SIGN_OUT, "ERROR: Sign Out button display incorrectly");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can change password for login account
     * @steps:   1. Log in with a valid username and password
     *           2. Select icon menu in the right and click on Change Password
     *           3. Change password
     *           4. Login with the new password
     * @expect:  1. Can change password success
     *           2. Can login with the new password
     */
    @TestRails(id = "674")
    @Test (groups = {"smoke"})
    @Parameters({"username","password"})
    public void Header_TC015(String username, String password) throws Exception {
        log("@title:  Validate can change password for login account");
        log("Step 1: Log in with a valid username and password");
        String passDecryp = StringUtils.decrypt(password);
        String newPass = passDecryp;
        String newPassEncrypt = StringUtils.encrypt(newPass);
        String securityCode = StringUtils.decrypt(environment.getSecurityCode());

        log("Step 2. Select icon menu in the right and click on Change Password");
        ChangePasswordPopup popup = agentHomePage.clickChangePasswordHeader(securityCode);

        log("Step 2.1. Input Security code");
        popup.changePassword(passDecryp, newPass, newPass);
        String messageSuccess = DriverManager.getDriver().switchToAlert().getText();
        DriverManager.getDriver().clickAlertAccept();

        log("Verify 1. Can change password success");
        Assert.assertEquals(messageSuccess, "Change Password Successfully!", "FAILED! Success message when change password is incorrect");

        log("Step 4. Login with the new password");
        agentHomePage.logout();
        BaseCaseMerito.loginAgent(sosAgentURL, agentSecurityCodeURL, username, newPassEncrypt, environment.getSecurityCode());
        Assert.assertTrue(agentHomePage.btnSignOut.isDisplayed(), String.format("ERROR: cannot login after change password for login account", username));
        log("INFO: Executed completely");
    }

}
