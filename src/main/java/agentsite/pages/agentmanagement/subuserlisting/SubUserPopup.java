package agentsite.pages.agentmanagement.subuserlisting;

import agentsite.controls.MenuTree;
import agentsite.controls.Table;
import com.paltech.element.common.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.AgencyManagement.SubUserListing.PERMISSIONS_ALL;

public class SubUserPopup {
    public Label lblTitle = Label.xpath("//div[@id='subUserDialog']//div[contains(@class,'modal-header')]");
    public Label lblUserNamePrefix = Label.xpath("//td[@class='username-selection']/span[1]");
    public DropDownBox ddbFirstCharOfUserName = DropDownBox.name("firstCharOfUserName");
    public DropDownBox ddbSecondCharOfUserName = DropDownBox.name("secondCharOfUserName");
    public TextBox txtPassword = TextBox.name("password");
    public DropDownBox ddbStatus = DropDownBox.name("status");
    public TextBox txtFirstName = TextBox.name("firstName");
    public TextBox txtLastName = TextBox.name("lastName");
    public Label lblPermission = Label.xpath("//label[@class='permission coltitle']");
    public String xPathPermission = "//label[contains(text(),'%s')]/input";
    public Button btnSubmit = Button.xpath("//div[@class='modal-footer']//button[1]");
    public Button btnCancel = Button.xpath("//button[@class='cancel']");
    public Table tblForm = Table.xpath("//table[@class='info']", 3);
    public MenuTree permisisonList = MenuTree.xpath("//div[@class='item-list']/ul", "//li");

    public String createSubUser(String userName, String password, String status, String firstName, String lastName, HashMap<String, Boolean> permissions) {
        txtPassword.isDisplayed(2);
        String getUserName;
        if (!userName.isEmpty()) {
            getUserName = userName;
            //Select username
        } else
            getUserName = String.format("%s%s%s", lblUserNamePrefix.getText(), ddbFirstCharOfUserName.getFirstSelectedOption(), ddbSecondCharOfUserName.getFirstSelectedOption());
        fillInfo(password, status, firstName, lastName, permissions, true);
        return getUserName;

    }

    public void fillInfo(String password, String status, String firstName, String lastName, HashMap<String, Boolean> permissions, boolean isSubmit) {
        if (!password.isEmpty()) {
            txtPassword.sendKeys(password);
        }
        if (!status.isEmpty()) {
            ddbStatus.selectByVisibleText(status);
        }
        if (!firstName.isEmpty()) {
            txtFirstName.sendKeys(firstName);
        }
        if (!lastName.isEmpty()) {
            txtLastName.sendKeys(lastName);
        }
        if (Objects.nonNull(permissions)) {
            enablePermission(permissions);
        }
        if (isSubmit) {
            btnSubmit.click();
        }
    }

 /*   public void enablePermission(HashMap<String, Boolean> permissions, List<String> permissionLst)
    {


        CheckBox chb;
        boolean enableFlag;
        for(int i = 0, n = permissionLst.size(); i< n; i++)
        {
            enableFlag = permissions.get(permissionLst.get(i));
            chb =CheckBox.xpath(String.format(xPathPermission,permissionLst.get(i)));
            if(Objects.nonNull(chb)){
                if(chb.getAttribute("name").equalsIgnoreCase("true") != enableFlag)
                    chb.click();
            }
        }
    } */

    public void enablePermission(HashMap<String, Boolean> permissions) {
        List<String> permissionLst = PERMISSIONS_ALL;
        CheckBox chb;
        boolean enableFlag;
        for (int i = 0, n = permissionLst.size(); i < n; i++) {
            if (permissions.containsKey(permissionLst.get(i))) {
                enableFlag = permissions.get(permissionLst.get(i));
                chb = CheckBox.xpath(String.format(xPathPermission, permissionLst.get(i)));
                if (Objects.nonNull(chb)) {
                    if (!(chb.getAttribute("name").equalsIgnoreCase(String.valueOf(enableFlag))))
                        chb.click();
                }
            }

        }
    }

}
