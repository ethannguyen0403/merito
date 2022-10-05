package agentsite.testcase.satsport.home;

import com.paltech.driver.DriverManager;
import com.paltech.utils.StringUtils;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.components.ChangePasswordPopup;
import agentsite.pages.all.home.AccountBalancePage;
import agentsite.ultils.account.ProfileUtils;

import java.util.List;

import static agentsite.common.AGConstant.MENULISTSAT;

public class SATHeaderTest extends BaseCaseMerito {
    /**
     * @title: Validate menu list item display correctly
     * @steps:   1. Login successfully
     * 2. 1 Click on menu icon
     * @expect: Verify The list sub menu display:
     * - Password
     * - Security Code
     * - OTP
     */
    @Test (groups = {"regression"})
    public void Header_TC003(){
        log("@title: Validate menu list item display correctly");
        log("Step 1: Click on menu icon");
        List<String> lstMenuHeader = agentHomePage.ddlMenu.getMenuList();
        log("Verify: Verify The list sub menu display: Password, Security Code, OTP");
        Assert.assertEquals(lstMenuHeader,MENULISTSAT,"FAILED! The list sub menu not display: Password, Security Code, OTP");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Account Balance page display when click on Home icon
     * @steps:   1. Login successfully
     * 2. Click on home icon
     * @expect: Account Balance page display with the title
     * example: Senior Admin Balance Info: SAI02
     * Available Balance
     * My Credit
     * My Outstanding
     * Total Outstanding
     * Today Win Loss
     * Yesterday Win Loss
     * Total Member Available Balance
     * Total Member Active/Closed/Suspended/Inactive
     */
    @Test (groups = {"regression"})
    @Parameters({"levelLogin","isCredit"})
    public void Header_TC009(String levelLogin, boolean isCredit){
        log("@title: Validate Account Balance page display when click on Home icon");
        log("Step 1: Click on home icon");
        AccountInfo accountInfo = ProfileUtils.getProfile();
        String userID = accountInfo.getUserCodeAndLoginID("%s(%s)");
        AccountBalancePage page = agentHomePage.clickHomeIcon();
        log("Verify 1: Verify Account  Balance page display after clicking on Home icon");
        String title = String.format("%s Balance Info :%s",levelLogin, userID);
        Assert.assertEquals(page.lblPageTitle.getText(),title,"FAILED! Left menu does not expand by default");

        List<String> lstString =  page.defineBalanceInfo(isCredit);
        List<String> lstLabels = page.tblInfo.getColumn(1,false);
        Assert.assertEquals(lstLabels,lstString,"FAILED! Labels is incorrect displayed");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can change password for login account
     * @steps: 1. Log in with a valid username and password
     * 2. Select icon menu in the right and click on Change Password
     * 3. Change password
     * 4. Login with the new password
     * @expect: 1. Can change password success
     * 2. Can login with the new password
     */
    @Test(groups = {"satsmoke"})
    @Parameters({"username", "password"})
    public void SAT_Header_TC015(String username, String password) throws Exception {
        log("@title:  Validate can change password for login account");
        log("Step 1: Log in with a valid username and password");

        String passDecryp = StringUtils.decrypt(password);
        String newPass = StringUtils.decrypt(password);
        String newPassEncrypt = StringUtils.encrypt(newPass);

        log("Step 2. Select icon menu in the right and click on Change Password");
        log("Step 3. Change password");
        ChangePasswordPopup popup = agentHomePage.clickChangePasswordHeader("");
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
