package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.MemberConstants;
import membersite.pages.components.changepasswordpopup.ChangePasswordPopup;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.MemberConstants.ChangePasswordPopup.*;
public class ChangePasswordTest extends BaseCaseTest {

    @TestRails(id="1220")
    @Test (groups = {"smoke"})
    @Parameters({"password"})
    public void Change_Password_TC1220(String password) throws Exception {
        log("@title:Validate change password after login successfully");
        log("Step 1.Select My Account > Change Password");
        log("Step 2. Input New Password and New Password Confirmation");
        log("Step 3. Current Password");
        log("Step 4. Click Save Change");
        String newPass ="test1234";
        String passDecrypt = StringUtils.decrypt(password);
        ChangePasswordPopup popup = memberHomePage.header.openChangePasswordPopup();
        try{
            String successMsg = popup.changePassword(newPass, newPass,passDecrypt);
            popup.clickCancelBtn();
            log("Verify 1. Can change password successfully");
            Assert.assertEquals(successMsg, MemberConstants.ChangePasswordPopup.MSG_SUCCESS, String.format("ERROR! Expected success message is %s but found %s", MemberConstants.ChangePasswordPopup.MSG_SUCCESS,successMsg));
        }finally{
            log("Pos_condition: Re-update password");
            popup =  memberHomePage.header.openChangePasswordPopup();
            popup.changePassword(passDecrypt,passDecrypt,newPass);
        }
        log("INFO: Executed completely");
    }

    @TestRails(id="1113")
    @Test (groups = {"regression"})
    public void Change_Password_TC1113() {
        log("@title:Validate close update password works");
        log("Step 1.Login member site");
        log("Step 2.Active My Account> Change Password");
        log("Step 3.Click close button");
        ChangePasswordPopup popup =  memberHomePage.header.openChangePasswordPopup();
        popup.clickCancelBtn();

        log("Verify 1.Change password popup disappear");
        Assert.assertFalse(popup.isDisplayed(),"Failed! The popup is display after click close button");

        log("INFO: Executed completely");
    }

    @TestRails(id="1114")
    @Test (groups = {"regression"})
    @Parameters(("brandname"))
    public void Change_Password_TC1114(String brandname) {
        log("@title:Validate can not update password if input incorrect current password\n");
        log("Step 1.Login member site");
        log("Step 2.Select My Account > Change Password");
        log("Step 3. Input New Password and New Password Confirmation");
        log("Step 4. Invalid Current Password");
        log("Step 5. Click Save Change");
        String newPass ="test1234";
        String invalidCurrentPassword = "123qwe43";
        ChangePasswordPopup popup =  memberHomePage.header.openChangePasswordPopup();
        popup.changePassword( newPass, newPass,invalidCurrentPassword);
        log("Verify 1. Error message displays");
        Assert.assertEquals(popup.getErrorMsg(),LBL_CURRENT_PASSWORD_ERROR_MSG.get(brandname),"Failed! The error message at current password is incorrect");

        log("INFO: Executed completely");
    }
    @TestRails(id="1115")
    @Test (groups = {"regression"})
    @Parameters({"brandname"})
    public void Change_Password_TC1115(String brandname) {
        log("@title:Validate message display when input incorrect password format");
        log("Step 1.Login member site");
        log("Step 2.Select My Account > Change Password");
        log("Step 3. Input new password with incorrect format");
        String newPass ="1234";
        ChangePasswordPopup popup =  memberHomePage.header.openChangePasswordPopup();
        popup.inputChangePassword(newPass, "1234qwer","");
        log("Verify 1. Error message display");
        Assert.assertEquals(popup.getNewPasswordErrorMsg(),LBL_NEW_PASSWORD_ERROR_MSG.get(brandname),"Failed! The error message at new password is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id="1116")
    @Test (groups = {"regression"})
    @Parameters({"password","brandname"})
    public void Change_Password_TC1116(String password,String brandname) throws Exception {
        log("@title:Validate message display when input password confirm incorrect");
        log("Step 1.Login member site");
        log("Step 2.Select My Account > Change Password");
        log("Step 3.Input New password and confirm password not match with new password");
        String newPass ="test1234";
        String confirnNewPass ="test12341";
        ChangePasswordPopup popup =  memberHomePage.header.openChangePasswordPopup();
        popup.changePassword( newPass, confirnNewPass,StringUtils.decrypt(password));
        log("Verify 1. Error message Confirm password is not correct display");
        Assert.assertEquals(popup.getConfirmPasswordErrorMsg(),LBL_CONFIRM_PASSWORD_ERROR_MSG.get(brandname),"Failed! The popup is display after click close button");

        log("INFO: Executed completely");
    }

    @TestRails(id="1059")
    @Test (groups = {"regression"})
    public void Change_Password_TC1059()  {
        log("@title: Validate can close Change Password popup (by X icon)");
        log("Step 1.Click My Account > Change Password");
        log("Step 2.Click x icon to  close the popup");
        ChangePasswordPopup popup =  memberHomePage.header.openChangePasswordPopup();
        popup.closePopup();

        log("Verify 1.Change password popup disappear");
        Assert.assertFalse(popup.isDisplayed(),"Failed! The popup is display after click close button");
        log("INFO: Executed completely");
    }

}
