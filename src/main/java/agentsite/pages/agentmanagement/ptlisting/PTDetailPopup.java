package agentsite.pages.agentmanagement.ptlisting;

import agentsite.controls.Table;
import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.TextBox;

import java.util.Objects;

public class PTDetailPopup {
    DropDownBox ddbSport = DropDownBox.xpath("//app-pt-player-listing-detail//select");
    TextBox txtPT = TextBox.xpath("//app-pt-player-listing-detail//input");
    Button btnAdd = Button.xpath("//app-pt-player-listing-detail//button[contains(@class,'addSport')]");
    int tblSportTotalCol = 3;
    Table tblSport = Table.xpath("//app-pt-player-listing-detail//table[contains(@class,'ptable')]", tblSportTotalCol);
    Button btnClose = Button.xpath("//app-pt-player-listing-detail//h4//button");
    private int colSport = 1;
    private int colPT = 2;
    private int colAction = 3;

    public Object clickAction(String sportName, String action) {
        Button btn;
        switch (action) {
            case "EDIT":
                btn = Button.xpath(tblSport.getControlxPathBasedValueOfDifferentColumnOnRow(sportName, 1, colSport, 1, null, colAction, "button[1]", false, false));
                btn.click();
                return null;
            default:
                btn = Button.xpath(tblSport.getControlxPathBasedValueOfDifferentColumnOnRow(sportName, 1, colSport, 1, null, colAction, "button[2]", false, false));
                if (Objects.isNull(btn)) {
                    System.err.println(String.format("ERROR: Cannot get Delete button at group name %s", sportName));
                    return null;
                }
                btn.click();
                return new ConfirmPopup();
        }
    }

    public void addSport(String sportName, String pt) {
        ddbSport.isDisplayed(2);
        ddbSport.selectByVisibleText(sportName);
        txtPT.sendKeys(pt);
        btnAdd.click();
        tblSport.isTextDisplayed(sportName, 2);
    }

    public void editSport(String sportName, String pt) {
        ddbSport.isDisplayed(2);
        clickAction(sportName, "Edit".toUpperCase());
        TextBox txtPTofSport = TextBox.xpath(tblSport.getControlxPathBasedValueOfDifferentColumnOnRow(sportName, 1, colSport, 1, null, colPT, "input[1]", false, false));
        txtPTofSport.sendKeys(pt);
        clickAction(sportName, "Edit".toUpperCase());
    }


    public boolean verifySportPT(String sportName, String pt) {
        tblSport.isTextDisplayed(pt, 1);
        String actualPT = tblSport.getControlBasedValueOfDifferentColumnOnRow(sportName, 1, colSport, 1, null, colPT, null, false, false).getText();
        if (!actualPT.equals(pt)) {
            System.out.println(String.format("FAILED! Expected PT of %s is %s but found %s", sportName, actualPT, pt));
            return false;
        }
        return true;
    }

    public void closePopup() {
        btnClose.click();
    }

}
