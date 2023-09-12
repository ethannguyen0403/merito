package agentsite.pages.agentmanagement.subuserlisting;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.Button;

import java.util.HashMap;

public class SubUserListing extends HomePage {
    public int tblSubUserTotalCol = 14;
    public Button btnCreate = Button.id("btnCreateSubUser");
    public Table tblSubUSer = Table.xpath("//table[contains(@class,'ptable report')]", tblSubUserTotalCol);
    public SubUserListing(String types) {
        super(types);
    }

    public String createSubUser(String username, String password, String status, String firstName, String lastName, HashMap<String, Boolean> permissions) {
        return null;
    }

    public String createSubUser(String username, String password) {
        return null;
    }

    public String getActiveSubUser(String username, String password) {
       return null;
    }

    public SubUserPopup openEditSubUser(String userName) {
        return null;
    }

    public SubUserPopup openCreateUserPopup() {
        return null;
    }

    public void editSubUser(String username, String password, String status, String firstName, String lastName, HashMap<String, Boolean> permissions) {
    }

    public boolean isSubUserPermissionSettingCorrect(String userName, HashMap<String, Boolean> permissions) {
        return false;
    }

}
