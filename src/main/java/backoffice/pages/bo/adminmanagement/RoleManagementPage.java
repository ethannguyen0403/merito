package backoffice.pages.bo.adminmanagement;

import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo._components.AppConfirmPopup;
import backoffice.pages.bo.adminmanagement.component.RolePopup;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

import java.util.ArrayList;
import java.util.List;

public class RoleManagementPage extends HomePage {
    public Label lblNoRole = Label.xpath("//app-role-manager//div[@class='custom-table']//div[contains(@class,'text-center')]");
    public StaticTable tblRole = StaticTable.xpath("//app-role-manager//div[@class='custom-table']", "perfect-scrollbar//div[@class='ps-content']", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", 3);
    public int colPermissionName = 2;
    public StaticTable tblActivePermission = StaticTable.xpath("//app-permission-manager//div[contains(@class,'col-md-5 table-block')]//div[@class='custom-table']", "perfect-scrollbar//div[@class='ps-content']", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", 3);
    public StaticTable tblInactivePermission = StaticTable.xpath("//app-permission-manager//div[contains(@class,'col-md-6 table-block')]//div[@class='custom-table']", "perfect-scrollbar//div[@class='ps-content']", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", 3);
    Button btnAdd = Button.xpath("//i[contains(@class,'fa-user-plus')]");
    Button btnEdit = Button.xpath("//i[contains(@class,'user-edit')]");
    Button btnDelete = Button.xpath("//i[contains(@class,'fa-trash-alt')]");
    TextBox txtRoleName = TextBox.xpath("//app-role-manager//input");
    TextBox txtActivePermissionName = TextBox.xpath("//app-permission-manager//div[contains(@class,'col-md-5 table-block')]//input[contains(@class,'txt-search')]");
    TextBox txtInactivePermissionName = TextBox.xpath("//app-permission-manager//div[contains(@class,'col-md-6 table-block')]//input[contains(@class,'txt-search')]");
    Button btnAddPermission = Button.xpath("//button[contains(@class,'btn-add')]");
    Button btnRemovePermission = Button.xpath("//button[contains(@class,'btn-remove')]");

    public Object clickAction(Actions action) {
        switch (action) {
            case ADD:
                btnAdd.click();
                return new RolePopup();
            case EDIT:
                btnEdit.click();
                return new RolePopup();
            case DELETE:
                btnDelete.click();
                return AppConfirmPopup.xpath("//app-comfirm-dialog");
        }
        return null;
    }

    public void addRole(String name, String description) {
        RolePopup popup = (RolePopup) clickAction(Actions.ADD);
        popup.inputRole(name, description);
    }

    public void editRole(String role, String newName, String newDesctiption) {
        select(Types.ROLE, role);
        RolePopup popup = (RolePopup) clickAction(Actions.EDIT);
        popup.inputRole(newName, newDesctiption);
    }

    public void deleteRole(String role, boolean isConfirmDelete) {
        select(Types.ROLE, role);
        AppConfirmPopup popup = (AppConfirmPopup) clickAction(Actions.DELETE);
        if (isConfirmDelete) {
            popup.confirm();
        }
    }

    public void select(Types type, String name) {
        List<String> lstObject = new ArrayList<>();
        switch (type) {
            case ROLE:
                lstObject = tblRole.getColumn(1, false);
                break;
            case ACTIVEPERMISSION:
                lstObject = tblActivePermission.getColumn(colPermissionName, false);
                break;
            case INACTIVEPERMISSION:
                lstObject = tblInactivePermission.getColumn(colPermissionName, false);
                break;
        }
        for (int i = 0; i < lstObject.size(); i++) {
            if (lstObject.get(i).equalsIgnoreCase(name)) {
                switch (type) {
                    case ROLE:
                        tblRole.getControlOfCell(1, 1, i + 1, null).click();
                        break;
                    case ACTIVEPERMISSION:
                        tblActivePermission.getControlOfCell(1, 1, i + 1, "span[@class='checkmark']").click();
                        break;
                    case INACTIVEPERMISSION:
                        tblInactivePermission.getControlOfCell(1, 1, i + 1, "span[@class='checkmark']").click();
                        break;
                }
                return;
            }
        }
    }

    public void search(Types type, String name) {
        switch (type) {
            case ROLE:
                txtRoleName.sendKeys(name);
                break;
            case ACTIVEPERMISSION:
                txtActivePermissionName.sendKeys(name);
                break;
            case INACTIVEPERMISSION:
                txtInactivePermissionName.sendKeys(name);
                break;
        }
    }

    public void activePermission(String role, String permission) {
        search(Types.ROLE, role);
        select(Types.ROLE, role);
        search(Types.INACTIVEPERMISSION, permission);
        select(Types.INACTIVEPERMISSION, permission);
        btnAddPermission.click();
    }

    public void removePermission(String role, String permission) {
        search(Types.ROLE, role);
        select(Types.ROLE, role);
        search(Types.ACTIVEPERMISSION, permission);
        select(Types.ACTIVEPERMISSION, permission);
        btnRemovePermission.click();
    }

    public enum Actions {ADD, EDIT, DELETE}

    public enum Types {ROLE, ACTIVEPERMISSION, INACTIVEPERMISSION}
}
