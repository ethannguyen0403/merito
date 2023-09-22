package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.report.components.PositionTakingDetailPopup;
import com.paltech.element.common.*;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PositionTakingReportPage extends HomePage {
    public TextBox txtSearchFrom = TextBox.id("fromDate");
    public TextBox txtSearchTo = TextBox.id("toDate");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");
    public DropDownBox ddbProduct = DropDownBox.id("select-product");
    public Button btnToday = Button.id("btn-today");
    public Button btnYesterday = Button.id("btn-yesterday");
    public Button btnLastWeek = Button.id("btn-last-week last-week");
    public Button btnSubmit = Button.id("btn-submit");
    public int tblReportTotalCol = 6;
    public int colCompetitionName = 2;
    public int colVolumn = 3;
    public int colPnL = 4;
    public int colTax = 5;
    public int colDetail = 6;
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report')]", tblReportTotalCol);
    public String competitionRow = "//table[contains(@class,'ptable report')]//tbody/tr[contains(@ng-repeat,'competition')]";
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'noRecord')]");
    public Label lblYouCanSeeReportData = Label.xpath("//span[@class='pinfo']/following-sibling::label");

    public PositionTakingReportPage(String types) {
        super(types);
    }

    public void filter(String productName) {
        if (!productName.isEmpty()) {
            ddbProduct.selectByVisibleText(productName);
        }
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public boolean verifyDataMatchWithDetail() {
        List<WebElement> lsCompetitionRow = Row.xpath(competitionRow).getWebElements();
        List<String> dataObserve = new ArrayList<>();
        List<String> dataExpected = new ArrayList<>();
        int n = lsCompetitionRow.size();
        for (int i = 0; i < n; i++) {
            Row r = Row.xpath(String.format("%s[%s]", competitionRow, i + 1));
            dataObserve = r.getRow(tblReportTotalCol, false);
            PositionTakingDetailPopup popup = openPositionTakingDetails(dataObserve.get(colCompetitionName - 1));
            dataExpected = popup.getTotalVolumnPnlTax();
            if (!dataObserve.get(colVolumn - 1).equals(dataExpected.get(0))) {
                System.out.println(String.format("Total volumn is %s but sum in detail popup is %s", dataObserve.get(colVolumn - 1), dataExpected.get(0)));
                return false;
            }
            if (!dataObserve.get(colPnL - 1).equals(dataExpected.get(1))) {
                System.out.println(String.format("Total Pnl is %s but sum in detail popup is %s", dataObserve.get(colPnL - 1), dataExpected.get(1)));
                return false;
            }
            if (!dataObserve.get(colTax - 1).equals(dataExpected.get(2))) {
                System.out.println(String.format("Total Tax is %s but sum in detail popup is %s", dataObserve.get(colTax - 1), dataExpected.get(2)));
                return false;
            }
            popup.closePopup();
        }
        return true;
    }

    public PositionTakingDetailPopup openPositionTakingDetails(String competitionName) {
        List<WebElement> lsCompetitionRow = Row.xpath(competitionRow).getWebElements();
        String detailIconXpath = "//td[6]/a";
        int n = lsCompetitionRow.size();
        for (int i = 0; i < n; i++) {
            Row r = Row.xpath(String.format("%s[%s]", competitionRow, i + 1));
            if (r.getRow(tblReportTotalCol, false).get(colCompetitionName - 1).equals(competitionName)) {
                Icon.xpath(String.format("%s[%s]%s", competitionRow, i + 1, detailIconXpath)).click();
                waitingLoadingSpinner();
                break;
            }
        }
        return new PositionTakingDetailPopup();
    }

}
