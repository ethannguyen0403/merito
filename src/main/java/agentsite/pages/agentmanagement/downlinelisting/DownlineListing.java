package agentsite.pages.agentmanagement.downlinelisting;

import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.components.SuccessPopup;
import com.paltech.element.common.*;
import java.util.List;


public class DownlineListing {
    public String getAccountStatus(String userCode) {
        return null;
    }

    public SuccessPopup updateAccountStatus(String userCode, String status) {
        return null;
    }

    public boolean isAccountStatusCorrect(String userCode, String expectedStatus) {
        return false;
    }

    public DropDownBox getAccountStatusDropdown(String userCode) {
        return null;
    }

    public List<String> getAccountStatus() {
        return null;
    }

    public void closeSubmitEditDownlinePopup() {
    }

    public EditDownLinePage clickEditIcon(String loginID, boolean inputSecurityCode) {
        return null;
    }

    public EditDownLinePage clickEditIcon(String loginID) throws Exception {
        return null;
    }

    public void verifyUIDisplayCorrect() {
    }

    public String changePassword(String loginID, String newPassword) throws InterruptedException {return "";}

    public List<String> getAccountUserName() {
        return null;
    }
}
