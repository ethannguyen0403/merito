package membersite.testcases;

import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.MemberConstants;
import membersite.pages.components.changepasswordpopup.ChangePasswordPopup;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;


public class ChangePasswordTest extends BaseCaseTest {

    @Test (groups = {"smoke1"})
    @Parameters({"password","skinName"})
    public void FE_Change_Password_TC001(String password,String skinName) throws Exception {
        log("@title:Validate change password after login successfully");
        log("Step 1.Select My Account > Change Password");
        log("Step 2. Input New Password and New Password Confirmation");
        log("Step 3. Current Password");
        log("Step 4. Click Save Change");
        String newPass ="test1234";
        String passDecrypt = StringUtils.decrypt(password);
        ChangePasswordPopup popup = memberHomePage.header.openChangePasswordPopup();
        try{
            String successMsg = popup.changePassword(passDecrypt, newPass, newPass);
            popup.clickCancelBtn();
            log("Verify 1. Can change password successfully");
            Assert.assertEquals(successMsg, MemberConstants.ChangePasswordPopup.MSG_SUCCESS, String.format("ERROR! Expected success message is %s but found %s", MemberConstants.ChangePasswordPopup.MSG_SUCCESS,successMsg));
        }finally{
            log("Pos_condition: Re-update password");
            popup =  memberHomePage.header.openChangePasswordPopup();
            popup.changePassword(newPass,passDecrypt,passDecrypt);
        }
        log("INFO: Executed completely");
    }

    @TestRails(id="1113")
    @Test (groups = {"regression"})
    @Parameters({"password","skinName"})
    public void MB_Change_Password_TC1113(String password,String skinName) {
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
    @Parameters({"password","skinName"})
    public void MB_Change_Password_TC1114(String password,String skinName) {
        log("@title:Validate can not update password if input incorrect current password\n");
        log("Step 1.Login member site");
        log("Step 2.Select My Account > Change Password");
        log("Step 3. Input New Password and New Password Confirmation");
        log("Step 4. Invalid Current Password");
        log("Step 5. Click Save Change");
        String newPass ="test1234";
        String invalidCurrentPassword = "123qwe43";
        ChangePasswordPopup popup = memberHomePage.header.openChangePasswordPopup();
        popup.changePassword(invalidCurrentPassword, newPass, newPass);
        log("Verify 1. Error message \"The password is incorrect. You will be logged out after 4 failed attempts.\"");
        Assert.assertEquals(popup.getConfirmPasswordErrorMsg(),"The password is incorrect. You will be logged out after 4 failed attempts.","Failed! The popup is display after click close button");

        log("INFO: Executed completely");
    }
    @TestRails(id="1115")
    @Test (groups = {"regression"})
    @Parameters({"password","skinName"})
    public void MB_Change_Password_TC1115(String password,String skinName) throws Exception {
        log("@title:Validate message display when input incorrect password format");
        log("Step 1.Login member site");
        log("Step 2.Select My Account > Change Password");
        log("Step 3. Input new password with incorrect format");
        String newPass ="1234";
        ChangePasswordPopup popup =  memberHomePage.header.openChangePasswordPopup();
        popup.changePassword(StringUtils.decrypt(password), newPass, "1234qwer");
        log("Verify 1. Error message \" Please use between 8 and 15 alphanumeric character.\n" +
                "Must have at least 1 letter and 1 number.\" display");
        Assert.assertEquals(popup.getConfirmPasswordErrorMsg()," Please use between 8 and 15 alphanumeric character.\n" +
                "Must have at least 1 letter and 1 number.","Failed! The popup is display after click close button");

        log("INFO: Executed completely");
    }

    @TestRails(id="1116")
    @Test (groups = {"regression"})
    @Parameters({"password","skinName"})
    public void MB_Change_Password_TC1116(String password,String skinName) throws Exception {
        log("@title:Validate message display when input password confirm incorrect");
        log("Step 1.Login member site");
        log("Step 2.Select My Account > Change Password");
        log("Step 3.Input New password and confirm password not match with new password");
        String newPass ="test1234";
        String confirnNewPass ="test12341";
        ChangePasswordPopup popup =  memberHomePage.header.openChangePasswordPopup();
        popup.changePassword(StringUtils.decrypt(password), newPass, confirnNewPass);
        log("Verify 1. Error message Confirm password is not correct display");
        Assert.assertEquals(popup.getConfirmPasswordErrorMsg(),"  Confirm password is not correct","Failed! The popup is display after click close button");

        log("INFO: Executed completely");
    }

    @TestRails(id="1075")
    @Test (groups = {"regression"})
    @Parameters({"password","skinName"})
    public void MB_Change_Password_TC1075(String password,String skinName) throws Exception {
        log("@title:Validate Greyhound Racing link works");
        log("Step 1.In home page - Next Up Racing section");
        log("Step 2.Click on any Greyhound racing link");
        String newPass ="test1234";
        String confirnNewPass ="test12341";
        ChangePasswordPopup popup =  memberHomePage.header.openChangePasswordPopup();
        popup.changePassword(StringUtils.decrypt(password), newPass, confirnNewPass);
        log("Verify 1. Racing market page display correctly. Country, market start time, market name is corrected");
        Assert.assertEquals(popup.getConfirmPasswordErrorMsg(),"  Confirm password is not correct","Failed! The popup is display after click close button");

        log("INFO: Executed completely");
    }

    @TestRails(id="1059")
    @Test (groups = {"regression"})
    @Parameters({"password","skinName"})
    public void MB_Change_Password_TC1059(String password,String skinName) throws Exception {
        log("@title: Validate can close Change Password popup (by X icon)");
        log("Step 1.Click My Account > Change Password");
        log("Step 2.Click x icon to  close the popup");
        String newPass ="test1234";
        String confirnNewPass ="test12341";
        ChangePasswordPopup popup =  memberHomePage.header.openChangePasswordPopup();
        popup.changePassword(StringUtils.decrypt(password), newPass, confirnNewPass);
        log("Verify 1.2.Popup is closed");
        Assert.assertEquals(popup.getConfirmPasswordErrorMsg(),"  Confirm password is not correct","Failed! The popup is display after click close button");

        log("INFO: Executed completely");
    }

}
