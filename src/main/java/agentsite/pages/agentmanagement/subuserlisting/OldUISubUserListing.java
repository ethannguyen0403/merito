package agentsite.pages.agentmanagement.subuserlisting;

import agentsite.controls.Table;
import agentsite.ultils.account.SubAccountUtils;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.AgencyManagement.SubUserListing.PERMISSIONS_ALL;

public class OldUISubUserListing extends SubUserListing {

    public int colUsername = 2;
    private String successIcon = "//span[contains(@class,'psuccess')]";



    public OldUISubUserListing(String types) {
        super(types);
    }

    public String createSubUser(String username, String password, String status, String firstName, String lastName, HashMap<String, Boolean> permissions) {
        SubUserPopup popup = openCreateUserPopup();
        String returnUsername = popup.createSubUser(username, password, status, firstName, lastName, permissions);
        waitingLoadingSpinner();
        return returnUsername;
    }

    public String createSubUser(String username, String password) {
        SubUserPopup popup = openCreateUserPopup();
        String returnUsername = popup.createSubUser(username, password, "", "", "", null);
        waitingLoadingSpinner();
        return returnUsername;
    }

    public String getActiveSubUser(String username, String password) {
        // Create a new sub if have no sub account
        JSONArray jsonArrayListUser = SubAccountUtils.getListSubUser();
        if (Objects.isNull(jsonArrayListUser)) {
            return createSubUser(username, password);
        }
        // Get a sub account in active status
        for (int i = 0; i < jsonArrayListUser.length(); i++) {
            if (jsonArrayListUser.getJSONObject(0).getString("status").equals("ACTIVE")) ;
            {
                return jsonArrayListUser.getJSONObject(0).getString("userCode");
            }
        }
        return null;
    }

    public SubUserPopup openEditSubUser(String userName) {
        // Click Edit button according username
        tblSubUSer.getControlBasedValueOfDifferentColumnOnRow(userName, 1, 2, 1, null, 3,
                "span[@class='pedit']", true, false).click();
        return new SubUserPopup();
    }

    public SubUserPopup openCreateUserPopup() {
        btnCreate.click();
        return new SubUserPopup();
    }

    public void editSubUser(String username, String password, String status, String firstName, String lastName, HashMap<String, Boolean> permissions) {
        SubUserPopup subUserPopup = openEditSubUser(username);
        subUserPopup.fillInfo(password, status, firstName, lastName, permissions, true);
    }

    public boolean isSubUserPermissionSettingCorrect(String userName, HashMap<String, Boolean> permissions) {
        List<String> permissionlst = PERMISSIONS_ALL;
        String cell_xpath;
        Label lblIcon;
        List<String> lstData = tblSubUSer.getColumn(colUsername, true);
        for (int i = 0; i < lstData.size(); i++) {
            if (lstData.get(i).contains(userName)) {
                for (int j = 0, m = permissionlst.size(); j < m; j++) {
                    cell_xpath = String.format("%s//tbody//tr[%s]//td[%s]", "//table[contains(@class,'ptable report')]", i + 1, j + 7);
                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, successIcon));
                    if (permissions.containsKey(permissionlst.get(j))) {
                        if (permissions.get(permissionlst.get(j))) {
                            if (!lblIcon.isDisplayed())
                                return false;
                        } else {
                            if (lblIcon.isDisplayed())
                                return false;
                        }
                    }
                }
                return true;
            }
        }
        System.out.println(String.format("The new sub account %s not exist in the table", userName));
        return false;
    }
}
