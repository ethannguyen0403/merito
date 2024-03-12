package backoffice.testcases.adminmanagement;

import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo.adminmanagement.AdminProfilePage;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;


public class AdminProfileTest extends BaseCaseTest {

    /**
     * @title: Validate can update profile
     * @pre-condition: 2. Login BO
     * @steps: 1. Access Admin Management > Admin Profile
     * 2. Input value Current password, Password, Confirm Password
     * 3. Click Save button
     * @expect: 1. Verify the info display correctly:
     * - Login ID, User Code, Name, Email
     * 2. Message: Update success display
     */
    @TestRails(id = "638")
    @Test(groups = {"smoke"})
    @Parameters({"username", "password"})
    public void BO_Admin_Management_Admin_Profile_638(String username, String password) throws Exception {
        log("@title: Validate can search Odds Matched History without http error returned");
        log("Step 1. Access Admin Management > Admin Profile");
        String name = "autoAcc" + StringUtils.generateNumeric(4);
        String email = name + "@pal.net.vn";
        String passDecrypt = StringUtils.decrypt(password);
        AdminProfilePage page = backofficeHomePage.navigateAdminProfile();

        try {
            log("Step 2. Input value Current password, Password, Confirm Password");
            log("Step 3. Click Save button");
            page.editProfile(name, email, passDecrypt, passDecrypt, passDecrypt);
            String messgage = new AlertMessageBox().getSuccessAlert();
            log("Verify 1. Verify the info display correctly: Login ID, User Code, Name, Email");
            Assert.assertEquals(page.txtLoginID.getAttribute("value"), username, "FAILED! Incorrect login ID");
            Assert.assertEquals(page.txtName.getAttribute("value"), name, "FAILED! Incorrect Name");
            Assert.assertEquals(page.txtEmail.getAttribute("value"), email, "FAILED! Incorrect email");

            log("Verify 2. Message: Update success display");
            Assert.assertTrue(messgage.contains("Update Succeed"), "FAILED! Success message display incorrect");

            log("INFO: Executed completely");
        } finally {
            log("Post-Condition: Reupdate profile");
            page.editProfile("", "", passDecrypt, passDecrypt, passDecrypt);
        }
    }

}
