package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.report.components.TransactionDetailsPopupPage;
import com.paltech.element.common.*;

import java.util.ArrayList;
import java.util.List;

public class WinLossByEventPage extends HomePage {
    public static int tblReportTotalCol = 15;
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'no-record')]");
    public TextBox txtSearchFrom = TextBox.name("fromDate");
    public TextBox txtSearchTo = TextBox.name("toDate");
    public DropDownBox ddbProduct = DropDownBox.id("select-product");
    public DropDownBox ddbSport = DropDownBox.id("select-sport");
    public DropDownBox ddbCompetition = DropDownBox.id("select-competition");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");
    public Button btnToday = Button.id("btn-today");
    public Button btnYesterday = Button.id("btn-yesterday");
    public Button btnLastWeek = Button.id("btn-last-week");
    public Button btnThisWeek = Button.id("btn-this-week");
    public Button btnSubmit = Button.id("btn-submit");
    public Button btnBack = Button.xpath("//button[@name='search' and @class='pbtn submit']");
    public Label lblInfo = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public Label lblShowTotalOnly = Label.xpath("//label[@for='isShowTotal']");
    public CheckBox cbShoTotal = CheckBox.name("isShowTotal");
    public Label lblReportTitle = Label.xpath("//div[contains(@class,'downline-bar')]");
    public int colUsername = 3;
    public int colLoginId = 4;
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report')]", tblReportTotalCol);
    Row rGrandTotal = Row.xpath("//table[contains(@class,'ptable report')]//tr[contains(@class,'total gtotal')]");
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    private String rwCompetitionGroupxPath = "//table[contains(@class,'ptable report')]//tr[%s]//td[contains(@class,'sportName')]";
    private String rCompetitionTotal = "//table[contains(@class,'ptable report')]//tr[contains(@class,'total mtotal')]";
    private String turnoverPerXpath = "//span[text()='%s']/parent::td/following-sibling::td[1]";

    public WinLossByEventPage(String types) {
        super(types);
    }

    public void filter(String product, String sportName, String competition, boolean isShowTotal) {
        if (!product.isEmpty())
            ddbProduct.selectByVisibleText(product);
        if (!sportName.isEmpty())
            ddbSport.selectByVisibleText(sportName);
        if (!competition.isEmpty())
            ddbCompetition.selectByVisibleText(competition);
        if (isShowTotal)
            lblShowTotalOnly.click();
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    /**
     * This action summ all Total row of each sport to compare with Grand total row
     *
     * @param competitions
     * @return The data calculated is map with grand total row or not
     */
    public ArrayList<String> sumTotalRowAllCompetition(List<String> competitions) {
        Row r = Row.xpath(String.format("%s[%s]", rCompetitionTotal, 1));
        ArrayList<String> totalRow;
        ArrayList<String> expectedData = r.getRow(tblReportTotalCol, false);
        for (int i = 1; i < competitions.size(); i++) {
            r = Row.xpath(String.format("%s[%s]", rCompetitionTotal, i + 1));
            totalRow = r.getRow(tblReportTotalCol, false);
            for (int j = 1; j < expectedData.size(); j++) {
                if (!expectedData.get(j).isEmpty()) {
                    double data = Double.parseDouble(expectedData.get(j));
                    double data1 = Double.parseDouble(totalRow.get(j));
                    expectedData.set(j, String.format("%.2f", data + data1));
                }
            }
        }
        expectedData.set(0, "Grand Total");
        return expectedData;
    }

    public ArrayList<String> getGrandTotalRow() {
        return rGrandTotal.getRow(tblReportTotalCol, false);
    }

    public TransactionDetailsPopupPage clickTurnoverLink(String eventName) {
        Label lblTurnOver = Label.xpath(String.format(turnoverPerXpath, eventName));
        lblTurnOver.click();
        waitingLoadingSpinner();
        return new TransactionDetailsPopupPage(_type);
    }


    /**
     * Get all sport available after filter
     *
     * @return the list of sport name
     */
    public List<String> getAllCompetitions(boolean isExpand) {
        List<String> lstCompetitions = new ArrayList<>();
        int n = tblReport.getNumberOfRows(false, false);
        for (int i = 0; i < n; i++) {
            Label lbl = Label.xpath(String.format(rwCompetitionGroupxPath, i + 1));
            if (lbl.isPresent()) {
                lstCompetitions.add(lbl.getText());
                if (isExpand) {
                    if (lbl.getAttribute("class").contains("expand")) {
                        lbl.click();
                    }
                }
            }
        }
        return lstCompetitions;
    }

    public void clickOnPeriodTime(String periodTime) {
        if(periodTime.equalsIgnoreCase("today")) {
            btnToday.click();
        } else if (periodTime.equalsIgnoreCase("yesterday")) {
            btnYesterday.click();
        } else if (periodTime.equalsIgnoreCase("this week")) {
            btnThisWeek.click();
        } else if (periodTime.equalsIgnoreCase("last week")) {
            btnLastWeek.click();
        }
        waitingLoadingSpinner();
    }
}
