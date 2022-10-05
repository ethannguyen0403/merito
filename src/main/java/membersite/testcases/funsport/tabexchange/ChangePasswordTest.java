package membersite.testcases.funsport.tabexchange;

import com.paltech.utils.StringUtils;
import membersite.common.FEMemberConstants;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.components.popups.ChangePasswordPopup;
import membersite.pages.funsport.tabexchange.components.popups.ChangePasswordPopupOldUI;
import baseTest.BaseCaseMerito;

public class ChangePasswordTest extends BaseCaseMerito {

    /**
     * @title: Validate change password after login successfully
     * @pre-condition   1. Login member site
     * @steps:          1.Select My Account > Change Password
     *                  2. Input New Password and New Password Confirmation
     *                  3. Current Password
     *                  4. Click Save Change
     * @expect:         1. Can change password
     */
    @Test (groups = {"smoke"})
    @Parameters({"password","skinName"})
    public void FE_Change_Password_TC001(String password,String skinName) throws Exception {
        log("@title:Validate change password after login successfully");
        log("Step 1.Select My Account > Change Password");
        log("Step 2. Input New Password and New Password Confirmation");
        log("Step 3. Current Password");
        log("Step 4. Click Save Change");
        String newPass ="test1234";
        String passDecrypt = StringUtils.decrypt(password);
        ChangePasswordPopupOldUI popup = (ChangePasswordPopupOldUI) memberHomePage.openChangePasswordPopup(skinName);
        try{
            String successMsg = popup.changePassword(passDecrypt, newPass, newPass);
            popup.clickCancelBtn();
            log("Verify 1. Can change password successfully");
            Assert.assertEquals(successMsg, FEMemberConstants.ChangePasswordPopup.MSG_SUCCESS, String.format("ERROR! Expected success message is %s but found %s", FEMemberConstants.ChangePasswordPopup.MSG_SUCCESS,successMsg));
        }finally{
            log("Pos_condition: Re-update password");
            popup = (ChangePasswordPopupOldUI)memberHomePage.openChangePasswordPopup(skinName);
            popup.changePassword(newPass,passDecrypt,passDecrypt);
        }
        log("INFO: Executed completely");
    }

}
