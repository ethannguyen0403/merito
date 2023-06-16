package backoffice.testcases.adminmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo.adminmanagement.AdminUserManagementPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AdminUserManagementTest extends BaseCaseTest {
    /**
     * @title: Validate can add edit and delete Admin
     * @pre-condition: 2. Login BO
     * @steps: 1. Access Admin Management > Admin User Management
     * 2. Click New Amin button and input the require fields: Login ID, Name, Email, Password, Confirm Password. Status. Click save change
     * 3. Select a new created account and click Edit button, update name and status of the account and click save change
     * 4. Select the account and click deleted button and confirm
     * @expect: 1.  Verify message "The user with login id [loginID] has been processed successfully." when create/edit/deleted
     * 2. Verify The account is created with correct info
     * 3. Verify can update account info
     * 4. Verify can deleted account
     */
    @Test(groups = {"smoke"})
    @Parameters("password")
    public void BO_Admin_Management_Admin_User_Management_001(String password) throws Exception {
        log("@title: Validate can add edit and delete Admin");
        log("Step 1. Access Admin Management > Admin User Management");
        String loginID = "autoqa" + StringUtils.generateNumeric(4);
        String name = "Auto QA account";
        String email = loginID + "@yopmail.com";
        String status = "Active";
        String pw = StringUtils.decrypt(password);
        AdminUserManagementPage page = backofficeHomePage.navigateAdminUserManagement();

        log("Step 2. Click New Amin button and input the require fields: Login ID, Name, Email, Password, Confirm Password. Status. Click save change");
        page.newAdmin(loginID, name, email, pw, pw, status);
        AlertMessageBox messageBox = new AlertMessageBox();
        String message = messageBox.getSuccessAlert();

        log("Verify 1.1  Verify message \"The user with login id [loginID] has been processed successfully.\" when created");
        Assert.assertTrue(message.contains(String.format(BOConstants.AdminManagement.AdminUserManagement.SUCCESS_MSG, loginID)), "FAILED! The success message after create a new user is incorrect");

        log("Verify 2. Verify The account is created with correct info");
        page.searchAdminUser(loginID, "", "", "", "Active");
        List<ArrayList<String>> lstAdminUser = page.tblAdminUser.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstAdminUser.get(0).get(page.colLoginId - 1), loginID, "FAILED! Login ID not display correct after adding");
        Assert.assertEquals(lstAdminUser.get(0).get(page.colName - 1), name, "FAILED! Name not display correct after adding");
        Assert.assertEquals(lstAdminUser.get(0).get(page.colEmail - 1), email, "FAILED!Email not display correct after adding");

        log("Step 3. Select a new created account and click Edit button, update name and status of the account and click save change");
        page.editUser(loginID, name + "_E", "", "", "", "Inactive");
        message = messageBox.getSuccessAlert();

        log("Verify 1.2  Verify message \"The user with login id [loginID] has been processed successfully.\" when edit");
        Assert.assertTrue(message.contains(String.format(BOConstants.AdminManagement.AdminUserManagement.SUCCESS_MSG, loginID)), "FAILED! The success message after edit a new user is incorrect");

        log("Verify 3. Verify can update account info");
        page.searchAdminUser(loginID, "", "", "", "Inactive");
        lstAdminUser = page.tblAdminUser.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstAdminUser.get(0).get(page.colLoginId - 1), loginID, "FAILED! Login ID not display correct after adding");
        Assert.assertEquals(lstAdminUser.get(0).get(page.colName - 1), name + "_E", "FAILED! Name not display correct after adding");
        Assert.assertEquals(lstAdminUser.get(0).get(page.colEmail - 1), email, "FAILED!Email not display correct after adding");

        log("Step 4. Select the account and click deleted button and confirm");
        page.deleteUser(loginID, true);

        log("Verify 1.3  Verify message \"The user with login id [loginID] has been processed successfully.\" when deleted");
        message = messageBox.getSuccessAlert();
        Assert.assertTrue(message.contains(String.format(BOConstants.AdminManagement.AdminUserManagement.SUCCESS_MSG, loginID)), "FAILED! The success message after edit a new user is incorrect");

        log("Verify 4. Verify can deleted account");
        page.searchAdminUser(loginID, "", "", "", "All");
        lstAdminUser = page.tblAdminUser.getRowsWithoutHeader(1, false);
        Assert.assertTrue(lstAdminUser.isEmpty(), "FAILED! Login ID not display correct after adding");

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate can search admin user by Login ID
     * @pre-condition: 2. Login BO
     * @steps: 1. Access Admin Management > Admin User Management
     * 2. Input a login id and press enter
     * @expect: 1. Verify the list admin user display as search criteria
     */
    @Test(groups = {"smoke"})
    @Parameters("username")
    public void BO_Admin_Management_Admin_User_Management_002(String username) {

        log("@title: Validate can search admin user by Login ID");
        log("Step 1. Access Admin Management > Admin User Management");
        AdminUserManagementPage page = backofficeHomePage.navigateAdminUserManagement();

        log("Step 2. Input a login id and press enter");
        page.searchAdminUser(username, "", "", "", "All");

        log("Verify 1. Verify the list admin user display as search criteria");
        Assert.assertTrue(page.verifySearchResult(username), "FAILED! Result not match with search criteria");

        log("INFO: Executed completely");
    }

}
