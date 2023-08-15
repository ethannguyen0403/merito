package agentsite.pages.agentmanagement.downlinelisting;

import agentsite.controls.Cell;
import agentsite.pages.agentmanagement.EditDownLinePage;
import com.paltech.element.common.Link;

import static baseTest.BaseCaseTest._brandname;

public class OldUIDownlineListing extends DownlineListing {
//    private int userCodeCol = 3;

    public EditDownLinePage clickEditIcon(String loginID, boolean inputSecurityCode) {
        editCol = getHeaderIndexValue("Edit");
        Cell cellValue = tblDowlineListing.getCellByName(loginID, false);
        int userCodeCol = Integer.parseInt(cellValue.getAttribute("cellIndex")) + 1;
        Link lnkEdit = (Link) tblDowlineListing.getControlBasedValueOfDifferentColumnOnRow(loginID, 1, userCodeCol, 1, null, editCol, "a[contains(@class,'pedit')]", false, false);
        if (lnkEdit.isClickable(1)) {
            lnkEdit.click();
        }
        waitingLoadingSpinner();
        return new EditDownLinePage(_brandname);
    }

    public EditDownLinePage clickEditIcon(String loginID) throws Exception {
        return clickEditIcon(loginID, false);
    }

}
