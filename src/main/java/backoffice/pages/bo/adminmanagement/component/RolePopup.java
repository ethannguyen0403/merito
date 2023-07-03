package backoffice.pages.bo.adminmanagement.component;

import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;


public class RolePopup {
    public TextBox txtRoleName = TextBox.xpath("//div[@class='modal-body']//div[@class='input-group input-group-sm']//input");
    public TextBox txtDescription = TextBox.xpath("//div[@class='modal-body']//div[@class='input-group input-group-sm mt-2']//input");
    public Button btnClose = Button.xpath("//button[@class='btn btn-sm btn-outline-secondary mr-2']");
    public Button btnSaveChanges = Button.xpath("//app-edit-role//button[@class='btn btn-sm btn-core']");

    public void inputRole(String roleName, String description) {
        if (!roleName.isEmpty())
            txtRoleName.sendKeys(roleName);
        if (!description.isEmpty())
            txtDescription.sendKeys(description);
        btnSaveChanges.click();
    }
}
