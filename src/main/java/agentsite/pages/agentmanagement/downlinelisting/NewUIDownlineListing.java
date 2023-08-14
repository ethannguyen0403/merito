package agentsite.pages.agentmanagement.downlinelisting;

import agentsite.controls.Cell;
import agentsite.pages.agentmanagement.EditDownLinePage;
import com.paltech.element.common.Link;
import com.paltech.utils.StringUtils;


import static baseTest.BaseCaseTest._brandname;
import static baseTest.BaseCaseTest.environment;

public class NewUIDownlineListing extends DownlineListing {
//    private int userCodeCol = 2;

    public EditDownLinePage clickEditIcon(String loginID, boolean inputSecurityCode) {
        editCol = getHeaderIndexValue("Edit");
        Cell cellValue = tblDowlineListing.getCellByName(loginID, false);
        int userCodeCol = Integer.parseInt(cellValue.getAttribute("cellIndex")) + 1;
        Link lnkEdit = (Link) tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, editCol, "a[contains(@class,'pedit')]", false, false);
        if (lnkEdit.isClickable(1)) {
            lnkEdit.click();
        }
        if (inputSecurityCode) {
            try {
                confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        waitingLoadingSpinner();
        return new EditDownLinePage(_brandname);
    }

    public EditDownLinePage clickEditIcon(String loginID) throws Exception {
        return clickEditIcon(loginID, true);
    }


}
