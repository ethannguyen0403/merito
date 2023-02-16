package membersite.testcases.enhance;

import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.MemberConstants;
import membersite.pages.components.changepasswordpopup.ChangePasswordPopup;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.MemberConstants.ChangePasswordPopup.LBL_CONFIRM_PASSWORD_ERROR_MSG;
import static common.MemberConstants.ChangePasswordPopup.LBL_CURRENT_PASSWORD_ERROR_MSG;

public class HomePageTest extends BaseCaseTest {
    @TestRails(id="1075")
    @Test (groups = {"regression3"})
    @Parameters({"password","skinName"})
    public void Change_Password_TC1075(String password,String skinName) throws Exception {
        log("@title:Validate Greyhound Racing link works");
        log("Step 1.In home page - Next Up Racing section");
        log("Step 2.Click on any Greyhound racing link");
        String newPass ="test1234";
        String confirnNewPass ="test12341";
        ChangePasswordPopup popup =  memberHomePage.header.openChangePasswordPopup();
        popup.changePassword(StringUtils.decrypt(password), newPass, confirnNewPass);
        log("Verify 1. Racing market page display correctly. Country, market start time, market name is corrected");
        Assert.assertEquals(popup.getConfirmPasswordErrorMsg(),"Password does not match the confirm password","Failed! The popup is display after click close button");

        log("INFO: Executed completely");
    }

}
