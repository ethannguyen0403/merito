package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.RadioGroup;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.report.components.UnsettleBetSportModeContainer;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class UnsettledBetPage extends HomePage {
    public TextBox txtFrom = TextBox.id("fromDate");
    public TextBox txtTo = TextBox.id("toDate");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtFrom, "//bs-calendar-layout");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtTo, "//bs-calendar-layout");
    public Label lblStake = Label.xpath("//div[@class='stake-all']");

    // Last Bets Mode
    public TextBox txtUsername = TextBox.xpath("//app-last-bets-mode//input[@id='username']");
    public TextBox txtWagerID = TextBox.xpath("//app-last-bets-mode//input[@id='wagerid']");
    public DropDownBox ddbSport = DropDownBox.xpath("//app-last-bets-mode//select[@name='sport']");
    public DropDownBox ddbEvent = DropDownBox.xpath("//app-last-bets-mode//select[@name='event']");
    public Button btnSearch = Button.id("pbtn-submit");
    public RadioGroup rdModeGroup = RadioGroup.xpath("//div[@id='unsettled-bet']//div[@name='check-box-view-mode']/div[@class='form-filter']");
    public RadioGroup rdBetType = RadioGroup.xpath("//div[@id='unsettled-bet']//div[@name='check-box-bet-type']/div[@class='form-filter']");
    public Label lblInfo = Label.xpath("//div[@id='unsettled-bet']//i//..//..//span");

    public int tbtLastBetsModeTotalCol = 12;
    public int colUsername = 7;
    public int colLoginID = 3;
    public int colStaus = 5;
    public Table tblLastBetsMode = Table.xpath("//div[contains(@class,'last-bet-table')]//table", tbtLastBetsModeTotalCol);
    public Label lblLastBetsModeNoRecord = Label.xpath("//div[contains(@class,'last-bet-table')]//table//td[@class='text-center']");

    public Label lblNoRecord = Label.xpath("//td[@class='text-center']");
    public Label lblSatkeAll = Label.xpath("//div[@class='form-control select']");
    public TextBox txtStakeFrom = TextBox.id("stakeFrom");
    public TextBox txtStakeTo = TextBox.id("stakeTo");
    public DateTimePicker dtpStartDate = DateTimePicker.xpath(txtStakeTo, "//bs-days-calendar-view");
    public DateTimePicker dtpEndDate = DateTimePicker.xpath(txtStakeTo, "//bs-days-calendar-view");

    // Sport Mode
    public Label lblNoRecordSportMode = Label.xpath("//app-sport-mode//div[@class='no-record']");
    public Label lblSport = Label.xpath("//app-hierarchy-mode//select[@name='sport']");
    public Table tblSportMode = Table.xpath("(//table[contains(@class,'table-responsive')])[1]", 2);
    public Label lblFirstRowTotalBetNumber = Label.xpath("(//table[contains(@class,'table-responsive')])[1]//tr[1]//td[2]//span[contains(@class,'event')]");

    public Table tblBetList = Table.xpath("//div[@class='bet-list-detail-table-sport']//table", 10);
    public UnsettleBetSportModeContainer unsettleBetSportModeContainer = UnsettleBetSportModeContainer.xpath("//app-sport-mode");


    // Hierarchy Mode
    public Label lblBreadcrumn = Label.xpath("//span[@class='my-breadcrumb']");
    public DropDownBox ddpSportHiearchyMode = DropDownBox.xpath("//app-hierarchy-mode//select[@name='sport']");
    public DropDownBox ddpEventHiearchyMode = DropDownBox.xpath("//app-hierarchy-mode//select[@name='event']");
    public int tblHierarchyModeTotalCo = 18;
    public Table tblHierarchyMode = Table.xpath("//app-hierarchy-mode//table", tblHierarchyModeTotalCo);

    public UnsettledBetPage(String types) {
        super(types);
    }

    public void selectMode(String mode) {
        rdModeGroup.clickRadio(mode);
        waitingLoadingSpinner();
    }

    public void selectStatus(String status) {
        rdBetType.clickRadio(status);
    }

    public void search(String status, String stake, String username, String wagerID, String sportName, String eventName, String fromDate,String toDate) {
        selectStatus(status);
        if (!stake.isEmpty()) {
            lblStake.click();
            String stakeFrom = stake.split(",")[0];
            String stakeTo = stake.split(",")[1];
            txtStakeFrom.sendKeys(stakeFrom);
            txtStakeFrom.sendKeys(stakeTo);
        }
        if (!username.isEmpty())
            txtUsername.sendKeys(username);
        if (!wagerID.isEmpty())
            txtWagerID.sendKeys();
        if (!sportName.isEmpty())
            ddbSport.selectByVisibleText(sportName);
        if (!eventName.isEmpty())
            ddbEvent.selectByVisibleText(eventName);
        if(!fromDate.isEmpty())
            dpFrom.selectDate(fromDate,"dd/MM/yyyy");
        if(!toDate.isEmpty())
            dpTo.selectDate(toDate,"dd/MM/yyyy");
        btnSearch.click();
        waitingLoadingSpinner();
    }

    public void verifySearchLastBetsMode(List<ArrayList<String>> data, String loginId, String status, String stake, String wagerID, String sportName, String eventName) {
        for (int i = 0; i < data.size(); i++) {
            if (!loginId.isEmpty())
                Assert.assertEquals(data.get(i).get(colUsername - 1), loginId, String.format("FAILED! Expected login ID %s but found %s", loginId, data.get(i).get(colUsername - 1)));
            if (!status.isEmpty())
                Assert.assertEquals(data.get(i).get(colStaus - 1), status, String.format("FAILED! Expected Status %s but found %s", status, data.get(i).get(colStaus - 1)));
        }
    }

    public List<String> getFirstBetInfo() {
        List<String> data = new ArrayList<>();
        String competitionAndEvent = tblSportMode.getControlOfCell(1, 1, 1, "strong").getText();
        data.add(competitionAndEvent.split(",")[0]);
        data.add(competitionAndEvent.split(",")[1]);
        data.add(tblSportMode.getControlOfCell(1, 1, 1, "span[contains(@class,'market')]").getText());
        data.add(tblSportMode.getControlOfCell(1, 1, 1, "span[contains(@class,'event')]").getText());
        return data;
    }


}

