package backoffice.testcases.adminmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo.adminmanagement.AdminUserManagementPage;
import backoffice.pages.bo.adminmanagement.RoleManagementPage;
import baseTest.BaseCaseTest;
import com.paltech.constant.Helper;
import com.paltech.driver.DriverManager;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RoleManagementTest extends BaseCaseTest {

    /**
     * @title: Validate can add, edit, Update role delete role
     * @pre-condition: 2. Login BO
     * @steps: 1. Access Admin Management > Role Management
     * 2. Click Add button, New Role popup display . Input role name and description and click Save Change
     * 3. Select the new create role and click Edit, update role name an description and click Save Change
     * 4. Select the role and click Deleted button then confirm to delete
     * @expect: 1. Verify Role is added in the list
     * 2. Verify Role info is updated
     * 3. Role is deleted
     */

    @Test(groups = {"smoke"})
    public void BO_Admin_Management_Role_Management_001() {
        log("@title: Validate can add, edit, Update role delete role");
        log("Step 1. Access Admin Management > Role Management");
        String roleName = "ATRoleTest" + StringUtils.generateNumeric(3);
        String roleEdit = roleName + "_Edit";
        String description = "The role is created by automation script";
        RoleManagementPage page = backofficeHomePage.navigateRoleManagement();
        AlertMessageBox message = new AlertMessageBox();
        try{
            log("Step 2. Click Add button, New Role popup display . Input role name and description and click Save Change");
            page.addRole(roleName, description);

            log("Verify 1. Verify Role is added in the list");
            String successMsg = message.getSuccessAlert();
            Assert.assertTrue(successMsg.contains("Role saved!"), "FAILED! Success message display incorrect");
            page.search(RoleManagementPage.Types.ROLE, roleName);
            List<ArrayList<String>> lstRole = page.tblRole.getRowsWithoutHeader(1, false);
            Assert.assertEquals(lstRole.get(0).get(0), roleName, "FAILED! Role Name not display correct after adding");
            Assert.assertEquals(lstRole.get(0).get(1), description, "FAILED!Description not display correct after adding");

            log("Step 3. Select the new create role and click Edit, update role name an description and click Save Change");
            page.editRole(roleName, roleEdit, description + "_Edit");
            successMsg = message.getSuccessAlert();
            Assert.assertTrue(successMsg.contains("Role saved!"), "FAILED! Success message display incorrect");

            log("Verify 2. Verify Role info is updated");
            lstRole = page.tblRole.getRowsWithoutHeader(1, false);
            Assert.assertEquals(lstRole.get(0).get(0), roleEdit, "FAILED! Role Name not display correct after edit");
            Assert.assertEquals(lstRole.get(0).get(1), description + "_Edit", "FAILED!Description not display correct after edit");
        } finally {
            log("Step 4. Select the role and click Deleted button then confirm to delete");
            page.deleteRole(roleEdit, true);

            log("Verify 3. Role is deleted");
            String successMsg = message.getSuccessAlert();
            Assert.assertTrue(successMsg.contains("Role is deleted successfully!"), "FAILED! Success message display incorrect after delete a role");
            Assert.assertEquals(page.lblNoRole.getText(), BOConstants.NO_RECORDS_FOUND, "FAILED! Role still display after deleted");
        }
        log("INFO: Executed completely");

    }

    /**
     * @title: Validate active/inactive permission for a role
     * @pre-condition: 2. Login BO
     * @steps: 1. Access Admin Management > Role Management
     * 2. Select a role : Testing
     * 3. In Inactive Permission section, select a item and click Add button
     * 4. Login the account belonging to Testing role
     * 5. Relogin to old account and inactive the permission for that role
     * @expect: 1. Verify the selected permission in Inactive section display in Active permission section
     * 2. Verify the account can access the page granted permission
     * 3. Verify the selected permission in Active section display in Inactive permission section
     * 4. Verify the account in Testing role cannot see the page that inactive
     */

    @Test(groups = {"smoke"})
    @Parameters({"username", "password"})
    public void BO_Admin_Management_Role_Management_005(String username, String password) throws Exception {
        log("@title: Validate active/inactive permission for a role");
        log("Step 1. Access Admin Management > Role Management");
        String roleName = "ATRoleTest" + StringUtils.generateNumeric(3);
        String description = "The role is created by automation script";
        String permission = "MAINTENANCE_READ";
        String loginID = "autoqa" + StringUtils.generateNumeric(4);
        String name = "Auto QA account";
        String email = loginID + "@yopmail.com";
        String status = "Active";
        String passDecrypt = StringUtils.decrypt(password);
        RoleManagementPage page = backofficeHomePage.navigateRoleManagement();
        try {
            page.addRole(roleName, description);
            log("Step 2. Select a role : Testing");
            log("Step 3. In Inactive Permission section, select a item and click Add button");
            page.activePermission(roleName, permission);

            log("Verify 1. Verify the selected permission in Inactive section display in Active permission section");
            List<String> lstActivePermission = page.tblActivePermission.getColumn(page.colPermissionName, false);
            Assert.assertEquals(lstActivePermission.get(0), permission, "FAILED! The active permission does not display in Active section");

            log("Step 4. Login the account belonging to Testing role");
            // Create a new account and add to Role above
            DriverManager.getDriver().switchToParentFrame();
            AdminUserManagementPage adminUserPage = backofficeHomePage.navigateAdminUserManagement();
            adminUserPage.newAdmin(loginID, name, email, passDecrypt, passDecrypt, status);
            adminUserPage.setRole(loginID, roleName);
            adminUserPage.logout();
            Helper.loginBOIgnoreCaptcha(backofficeSOSUrl, backofficeDashboardUrl, loginID, password, true);

            log("Verify 2. Verify the account can access the page granted permission");
            Assert.assertTrue(backofficeHomePage.smProductMaintenance.isDisplayed(1), "FAILED! The permission not display in the left menu");
            backofficeHomePage.logout();

            log("Step 5. Relogin to old account and inactive the permission for that role");
            Helper.loginBOIgnoreCaptcha(backofficeSOSUrl, backofficeDashboardUrl, username, password, true);
            page = backofficeHomePage.navigateRoleManagement();
            page.removePermission(roleName, permission);

            log("Verify 3. Verify the selected permission in Active section display in Inactive permission section");
            lstActivePermission = page.tblActivePermission.getColumn(page.colPermissionName, false);
            Assert.assertTrue(lstActivePermission.isEmpty(), "FAILED! The permission inactivate still display in Active section");
            page.logout();

            log("Verify 4. Verify the account in Testing role cannot see the page that inactive");
            Helper.loginBOIgnoreCaptcha(backofficeSOSUrl, backofficeDashboardUrl, loginID, password, true);
            Assert.assertFalse(backofficeHomePage.smProductMaintenance.isDisplayed(1), "FAILED! The permission display in the left menu after the permission is removed");
            backofficeHomePage.logout();
            log("INFO: Executed completely");
        } finally {
            log("Post_condition: Delete Role and account created by auto script");
            Helper.loginBOIgnoreCaptcha(backofficeSOSUrl, backofficeDashboardUrl, username, password, true);
            AdminUserManagementPage adminUserPage = backofficeHomePage.navigateAdminUserManagement();
            adminUserPage.setRole(loginID, roleName);
            adminUserPage.deleteUser(loginID, true);
            DriverManager.getDriver().switchToDefaultContent();
            page = backofficeHomePage.navigateRoleManagement();
            page.search(RoleManagementPage.Types.ROLE, roleName);
            page.deleteRole(roleName, true);


        }


    }
}
