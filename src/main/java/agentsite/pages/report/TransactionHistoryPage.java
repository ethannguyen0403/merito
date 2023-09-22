package agentsite.pages.report;

import agentsite.controls.Cell;
import agentsite.controls.DateTimePicker;
import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.report.components.TransactionDetailsPopup;
import com.paltech.element.common.*;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryPage extends HomePage {
    public static int tblReportTotalCol = 6;
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'align_center')]");
    public TextBox txtSearchFrom = TextBox.id("fromDate");
    public TextBox txtSearchTo = TextBox.xpath("//input[contains(@ng-model,'toSearchDate')]");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");
    public Label lblFrom = Label.xpath("//div[@id='search-region']//table//tr[1]/td[1]");
    public Label lblTo = Label.xpath("//div[@id='search-region']//table//tr[1]/td[3]");
    public Label lblProduct = Label.xpath("//div[@id='search-region']//table//tr[1]/td[5]");
    //public DropDownBox ddbSport = DropDownBox.xpath("//label[text()='Sport']/following::select[1]");
    //public DropDownBox ddbCompetition = DropDownBox.xpath("//label[text()='Competition']/following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.id("select-product");
    public Button btnToday = Button.name("today");
    public Button btnYesterday = Button.name("yesterday");
    public Button btnLastWeek = Button.name("lastWeek");
    public Button btnSubmit = Button.name("search");
    public Label lblInfo = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public int colCompetitionName = 2;
    public int colVolume = 3;
    public int colPnL = 4;
    public int colTax = 5;
    public int colDetails = 6;
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report')]", tblReportTotalCol);
    private String sportRows = "//td[contains(@class,'sportName')]";
    //private String competitionRow = "//tr[contains(@ng-repeat,'sport.competitions')]";
    private String competitionRow = "//tr[contains(@class,'ng-star-inserted')]";

    public TransactionHistoryPage(String types) {
        super(types);
    }

    public void filter(String product) {
        if (!product.isEmpty())
            ddbProduct.selectByVisibleText(product);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public List<String> getSportsData() {
        List<String> lstSport = new ArrayList<>();
        int totalSport = Label.xpath(sportRows).getWebElements().size();
        for (int i = 0; i < totalSport; i++) {
            lstSport.add(Label.xpath(String.format("%s[%s]", sportRows, i + 1)).getText());
        }
        return lstSport;
    }

    public boolean verifyVolumePnLTaxMatchWithDetail() {
        String competition;
        String volume;
        String pnl;
        String tax;
        int row = Row.xpath(competitionRow).getWebElements().size();
        Row rowElement;
        for (int i = 0; i < row; i++) {

            rowElement = Row.xpath(String.format("%s[%s]%s", competitionRow, i + 1, sportRows));
            if (rowElement.isDisplayed())
                continue;
            rowElement = Row.xpath(String.format("%s[%s]", competitionRow, i + 1));
            if (rowElement.isDisplayed()) {
                competition = Cell.xpath(String.format("%s[%s]//td[%s]", competitionRow, i + 1, colCompetitionName)).getText();
                volume = Cell.xpath(String.format("%s[%s]//td[%s]", competitionRow, i + 1, colVolume)).getText();
                pnl = Cell.xpath(String.format("%s[%s]//td[%s]", competitionRow, i + 1, colPnL)).getText();
                tax = Cell.xpath(String.format("%s[%s]//td[%s]", competitionRow, i + 1, colTax)).getText();
                Icon.xpath(String.format("%s[%s]//td[%s]/a", competitionRow, i + 1, colDetails)).click();
                waitingLoadingSpinner();
                TransactionDetailsPopup popup = new TransactionDetailsPopup();

                double sumPlayerStake = popup.sumPlayerStake();
                String getTotalPnlLoginLevel = popup.getTotalRowData().get(1);
                String getTotalTaxLoginLevel = popup.getMarketTax().get(11);
                if (!String.format("%.2f", sumPlayerStake).equals(volume)) {
                    System.err.println(String.format("FAILED! Volume in summary of competition %s is %s but sum total player stake in transaction detail is %s",
                            competition, volume, String.format("%.2f", sumPlayerStake)));
                    return false;
                }
                if (!getTotalPnlLoginLevel.equals(pnl)) {
                    System.err.println(String.format("FAILED! Pnl in summary of competition %s is %s but Total Pnl of login account column in transaction detail is %s",
                            competition, volume, getTotalPnlLoginLevel));
                    return false;
                }
                // 11 is the login column resut: SAD Result
                if (!getTotalTaxLoginLevel.equals(tax)) {
                    System.err.println(String.format("FAILED! Tax in summary of competition %s is %s but Total tax of market at login account column in transaction detail is %s",
                            competition, volume, getTotalTaxLoginLevel));
                    return false;
                }
                popup.clickCloseButton();
            } else
                break;
        }
        return true;
    }

  /*  public boolean verifyVolumePnLTaxMatchWithDetail() {
        String competition;
        String volume;
        String pnl;
        String tax;
        int row = Row.xpath(competitionRow).getWebElements().size();
        Row rowElement;
        for (int i = 0; i < row; i++) {
            rowElement = Row.xpath(String.format("%s[%s]", competitionRow, i + 1));
            if (rowElement.isDisplayed()) {
                competition = Cell.xpath(String.format("%s[%s]//td[%s]", competitionRow, i + 1, colCompetitionName)).getText();
                volume = Cell.xpath(String.format("%s[%s]//td[%s]", competitionRow, i + 1, colVolume)).getText();
                pnl = Cell.xpath(String.format("%s[%s]//td[%s]", competitionRow, i + 1, colPnL)).getText();
                tax = Cell.xpath(String.format("%s[%s]//td[%s]", competitionRow, i + 1, colTax)).getText();
                Icon.xpath(String.format("%s[%s]//td[%s]/a", competitionRow, i + 1, colDetails)).click();
                waitingLoadingSpinner();
                TransactionDetailsPopup popup = new TransactionDetailsPopup();

                double sumPlayerStake = popup.sumPlayerStake();
                String getTotalPnlLoginLevel = popup.getTotalRowData().get(1);
                String getTotalTaxLoginLevel = popup.getMarketTax().get(11);
                if (!String.format("%.2f", sumPlayerStake).equals(volume))
                {
                    System.err.println(String.format("FAILED! Volume in summary of competition %s is %s but sum total player stake in transaction detail is %s",
                            competition,volume,String.format("%.2f", sumPlayerStake)));
                    return false;
                }
                if (! getTotalPnlLoginLevel.equals(pnl))
                {
                    System.err.println(String.format("FAILED! Pnl in summary of competition %s is %s but Total Pnl of login account column in transaction detail is %s",
                            competition,volume,getTotalPnlLoginLevel));
                    return false;
                }
                // 11 is the login column resut: SAD Result
                if (! getTotalTaxLoginLevel.equals(tax))
                {
                    System.err.println(String.format("FAILED! Tax in summary of competition %s is %s but Total tax of market at login account column in transaction detail is %s",
                            competition,volume,getTotalTaxLoginLevel));
                    return false;
                }
                popup.clickCloseButton();
            }else
                break;
        }
        return true;
    }*/



}
