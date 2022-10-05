package backoffice.pages.bo.adminmanagement;

import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo._components.AppConfirmPopup;
import backoffice.pages.bo.adminmanagement.component.RolePopup;
import backoffice.pages.bo.adminmanagement.component.UserPopup;
import backoffice.pages.bo.home.HomePage;

import java.util.ArrayList;
import java.util.List;

public class AdminUserManagementPage extends HomePage {
    public enum Actions{ADD,EDIT,DELETE}

    public enum Types{ROLE, ACTIVEPERMISSION, INACTIVEPERMISSION}

    Button btnAdd = Button.xpath("//i[contains(@class,'fa-user-plus')]");
    Button btnEdit = Button.xpath("//i[contains(@class,'user-edit')]");
    Button btnDelete = Button.xpath("//i[contains(@class,'fa-trash-alt')]");
    TextBox txtLoginID = TextBox.xpath("//app-admin-users//div[contains(@class,'search-header')]//div[contains(@class,'custom-table-cell')][2]//input");
    TextBox txtUserCode = TextBox.xpath("//app-admin-users//div[contains(@class,'search-header')]//div[contains(@class,'custom-table-cell')][3]//input");
    TextBox txtName = TextBox.xpath("//app-admin-users//div[contains(@class,'search-header')]//div[contains(@class,'custom-table-cell')][4]//input");
    TextBox txtEmail = TextBox.xpath("//app-admin-users//div[contains(@class,'search-header')]//div[contains(@class,'custom-table-cell')][5]//input");
    DropDownBox ddbStatus = DropDownBox.xpath("//app-admin-users//div[contains(@class,'search-header')]//div[contains(@class,'custom-table-cell')][6]/select");
    Button tbnSave = Button.xpath("//i[@class='fa-save fas']");
    TextBox txtRoleName = TextBox.xpath("//div[contains(@class,'role-table')]//div[@class='custom-table-header']/div[@class='custom-table-row'][2]/div[3]//input");
    TextBox txtRoleDescription = TextBox.xpath("//div[contains(@class,'role-table')]//div[@class='custom-table-header']/div[@class='custom-table-row'][2]/div[4]//input");
    public Label lblNoRole = Label.xpath("//app-admin-users//div[@class='custom-table']//div[contains(@class,'text-center')]");

    public int colLoginId = 2;
    public int colName = 4;
    public int colEmail = 5;
    public int colActive = 6;
    public StaticTable tblAdminUser = StaticTable.xpath("//app-admin-users//div[@class='custom-table']","perfect-scrollbar//div[@class='ps-content']","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",6);
    public int colRoleName = 3;
    public int colChb = 1;
    public int colDetails = 5;
    public StaticTable tblRole = StaticTable.xpath("//app-roles//div[@class='custom-table']","perfect-scrollbar//div[@class='ps-content']","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",5);

    public Object clickAction(Actions action){
        switch (action){
            case ADD:
                btnAdd.click();
                return new UserPopup();
            case EDIT:
                btnEdit.click();
                return new UserPopup();
            case DELETE:
                btnDelete.click();
                return AppConfirmPopup.xpath("//app-confirm-dialog");
        }
        return null;
    }

    public void newAdmin(String loginID, String name, String email, String password, String confirmPassword, String status){
        UserPopup popup = (UserPopup) clickAction(Actions.ADD);
        popup.inputData(loginID,name,email,password,confirmPassword,status);
    }

    public void editUser(String loginID, String name, String email, String password, String confirmPassword, String status ){
        selectUser(loginID);
        UserPopup popup = (UserPopup) clickAction(Actions.EDIT);
        popup.inputData(loginID,name,email,password,confirmPassword,status);
    }

    public void deleteUser(String loginID, boolean isConfirmDelete ){
        selectUser(loginID);
        AppConfirmPopup popup =(AppConfirmPopup) clickAction(Actions.DELETE);
        if(isConfirmDelete){
            popup.confirm();
        }
    }

    public void selectUser( String name){
        List<String> lstObject = tblAdminUser.getColumn(colLoginId, false);
        for(int i = 0; i < lstObject.size(); i++){
            if (lstObject.get(i).equalsIgnoreCase(name)){
                tblAdminUser.getControlOfCell(1,colLoginId,i+1,null).click();
                return;
            }
        }
    }

    public void selectRole( String name){
        List<String> lstObject = tblRole.getColumn(colRoleName, false);
        for(int i = 0; i < lstObject.size(); i++){
            if (lstObject.get(i).equalsIgnoreCase(name)){
                tblRole.getControlOfCell(1,colChb,i+1,"input[@type='checkbox']").click();
                return;
            }
        }
    }

    public void searchAdminUser(String loginID, String userCode, String name, String email, String status){
        if(!loginID.isEmpty())
            txtLoginID.sendKeys(loginID);
        if(!userCode.isEmpty())
            txtUserCode.sendKeys(userCode);
        if(!name.isEmpty())
            txtName.sendKeys(name);
        if(!email.isEmpty())
            txtEmail.sendKeys(email);
        if(!status.isEmpty())
            ddbStatus.selectByVisibleText(status);

    }

    public void searchRole(String name, String description){
        if(!name.isEmpty())
            txtRoleName.sendKeys(name);
        if(!description.isEmpty())
            txtRoleDescription.sendKeys(description);
    }

    public void setRole(String loginId, List<String> roles){
        selectUser(loginId);
        for(int i = 0; i< roles.size(); i++){
            setRole("",roles.get(i));
        }
    }

    public void setRole(String loginId, String role){
        if(!loginId.isEmpty()) {
            txtLoginID.sendKeys(loginId);
            selectUser(loginId);
        }
        searchRole(role,"");
        selectRole(role);
        tbnSave.click();
    }

    public boolean verifySearchResult(String loginID){
        List<String> lstResult = tblAdminUser.getColumn(colLoginId,true);
        for(int i =0; i< lstResult.size(); i++){
            if(!lstResult.get(i).contains(loginID)){
                System.out.println(String.format("Login ID:%s at row %d not contains the search criteria: %s",lstResult.get(i),i+1, loginID));
                return false;
            }
        }
        return true;
    }
}
