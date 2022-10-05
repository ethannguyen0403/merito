package backoffice.testcases.accountmanagement;
import baseTest.BaseCaseMerito;
import com.paltech.driver.DriverManager;
import membersite.pages.all.home.ChangePasswordPage;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo._components.NotificationPopup;
import backoffice.pages.bo.accountmanagement.ResetAccountPasswordPage;


public class ResetAccountPasswordTest extends BaseCaseMerito {
    /**
     * @title: Validate can reset password of member account
     * @pre-condition:
     *           1. Login BO
     * @steps: 1. Access Member Management > Reset Account Password
     *          2. Input member account, new password, confirm password and click submit button
     *          3. Confirm to reset password
     *          4. Login member site with the account reset password
     * @expect: 1. Validate message confirm reset password display : Confirm to reset password for [login ID]
     *          2. Verify success message display" Reset password successful for user with login ID: auto.pl01"
     *          3. Verify change password display
     */
    @Test (groups = {"smoke"})
    @Parameters({"resetAccountPW","memberPassword"})
    public void BO_MM_Reset_Account_Password_001(String resetAccountPW, String memberPassword) throws Exception {
        log("@title: Validate can reset password of member account");
        log("Step 1. Access Member Management > Reset Account Password");
        ResetAccountPasswordPage page = backofficeHomePage.navigateResetAccountPassword();
        String newPassword = "1234qwert";

        log("Step 2. Input member account, new password, confirm password and click submit button");
        page.resetAccountPassword(resetAccountPW,newPassword,newPassword);
        log("Step 3. Confirm to reset password");

        AlertMessageBox message = new AlertMessageBox();
        String confirmMsg = message.getSuccessAlert();

        log("Verify 2. Verify success message display\" Reset password successful for user with login ID: auto.pl01\"");
        Assert.assertTrue(confirmMsg.contains(String.format("Reset password successful for user with login ID: %s",resetAccountPW)),"FAILED!, Reset password success is not correct");

        log("Step 4. Login member site with the account reset password");
       BaseCaseMerito.loginMember(resetAccountPW,newPassword,true,"","",true);

        log("Verify 3. Verify change password display");
        ChangePasswordPage changePasswordPage = new ChangePasswordPage();
        Assert.assertEquals(changePasswordPage.lbltitle.getText(),"Please change your password below");
        changePasswordPage.btnSkip.click();
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate can reset password of agent account
     * @pre-condition:
     *           1. Login BO
     * @steps: 1. Access Member Management > Reset Account Password
     *          2. Input an agent account, new password, confirm password and click submit button
     *          3. Confirm to reset password
     *          4. Login agent site with the account reset password
     *          5. Input new security code
     *          6. Change password
     * @expect: 1. Validate message confirm reset password display : Confirm to reset password for [login ID]
     * 2. Verify success message display" Reset password successful for user with login ID: auto.pl02"
     * 3. Verify agent site display set security code popup
     * 4. Verify change password popup display after add new security code
     * 5. Agent is change password successfully
     */
    @Test (groups = {"smoke"})
    @Parameters({"resetAGAccountPW","memberPassword"})
    public void BO_MM_Reset_Account_Password_002(String resetAGAccountPW, String memberPassword) throws Exception {
        log("@title: Validate can reset password of agent account");
        log("Step 1. Access Member Management > Reset Account Password");
        ResetAccountPasswordPage page = backofficeHomePage.navigateResetAccountPassword();
        String newPassword = "1234qwert";

        log("Step 2. Input an agent account, new password, confirm password and click submit button");
        page.resetAccountPassword(resetAGAccountPW,newPassword,newPassword);
        log("Step 3. Confirm to reset password");
        Alert alert =  DriverManager.getDriver().switchToAlert();
        String confirmMsg = alert.getText();
        alert.accept();
        NotificationPopup notificationMsg = new NotificationPopup();
        log("Verify 1. Validate message confirm reset password display : Confirm to reset password for [login ID]");
        Assert.assertEquals(confirmMsg,String.format("Confirm to reset password for %s?",resetAGAccountPW),"FAILED! Confirm message to reset pw is not correct");

        log("Verify 2. Verify success message display\" Reset password successful for user with login ID\"");
        Assert.assertEquals(notificationMsg.getMessage(),String.format("Reset password successful for user with login ID: %s",resetAGAccountPW),"FAILED!, Reset password success is not correct");

        log("Step 4. Login agent site with the account reset password");
        log("Step 5. Input new security code");
        log("Step 6. Change password");
        String agentCodeURL = String.format("%s/password",agentLoginURL);
        BaseCaseMerito.loginAgent(sosAgentURL,agentSecurityCodeURL,resetAGAccountPW,memberPassword,environment.getSecurityCode());

        log("Verify 3. Verify agent site display set security code popup");
        log("Verify 4. Verify change password popup display after add new security code");
        log("Verify 5. Agent is change password successfully");

        log("INFO: Executed completely");
    }

}
