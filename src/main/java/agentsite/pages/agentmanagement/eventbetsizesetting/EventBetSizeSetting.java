package agentsite.pages.agentmanagement.eventbetsizesetting;

import agentsite.controls.MenuTree;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EventBetSizeSetting extends HomePage {
    public int totalColumn = 6;
    public int colCompetitionName = 1;
    public int colEventName = 2;
    public int colEventID = 3;
    public Label lblNoRecordFound = Label.xpath("//td[text()='No records found.']");
    public DropDownBox ddbSADLevel = DropDownBox.xpath("//label[@translate='sport']//following::select[2]");
    public MenuTree mnTabs = MenuTree.xpath("//ul[contains(@class,'nav-tabs')]", "//li");
    public Label lblSport = Label.xpath("//label[@translate='sport']");
    public Table tblEvent = Table.xpath("//table[contains(@class,'table ptable report table--fixed table-striped')]", totalColumn);
    public Button btnSubmit = Button.xpath("//button[@class='btn-submit']");
    public Button btnCancel = Button.xpath("//button[@class='btn-cancel1']");

    public EventBetSizeSetting(String types) {
        super(types);
    }

    public void filter(String controlLevel, String sportName, String tab) {
    }

    public void updateMinMax(String eventID, String product, String min, String max) {
    }

    public void inputMinMax(String eventID, String product, String min, String max) {
    }

    public TextBox getMinTextBoxofEvent(String eventID, String product) {
        return null;
    }

    public ConfirmPopup openErrorPopup(String eventID, String product) {
        return null;
    }

    public TextBox getMaxTextBoxofEvent(String eventID, String product) {
        return null;
    }

    public String getMinMax(String eventID, String product) {
        return "";
    }

    public void clickTab(String action) {
    }

    public void searchEventInfo(String competitionName, String eventName, String eventID) {

    }

    public List<ArrayList<String>> getTableInfo() {
        return null;
    }

    public boolean searchResultContainsKey(List<String> lstResult, String searchKey) {
        return false;
    }

    public void verifyResultFilteredByPeriod(String periodTab) throws ParseException {
    }

    public void verifyEventStartDisplay(String eventStart) {
    }

}
