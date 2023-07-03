package membersite.pages.components.accountstatement;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;
import com.paltech.utils.DateUtils;
import common.MemberConstants;
import controls.DateTimePicker;
import controls.Table;
import membersite.objects.AccountBalance;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static common.MemberConstants.AccountStatementPage.LOAD_REPORT;
import static common.MemberConstants.AccountStatementPage.OPENING_BALANCE;
import static membersite.utils.betplacement.BetUtils.getUserBalance;

public class NewUIAccountStatementContainer extends AccountStatementContainer {
    // This containner available for /x UI
    private Label lblStartDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][1]//label");
    private Label lblEndDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][2]//label");
    private TextBox txtStartDate = TextBox.xpath("//div[contains(@class,'custom-bsDatepicker')][1]//input");
    private DateTimePicker dtpStartDate = DateTimePicker.xpath(txtStartDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    private TextBox txtEndDate = TextBox.xpath("//div[contains(@class,'custom-bsDatepicker')][2]//input");
    private DateTimePicker dtpEndDate = DateTimePicker.xpath(txtEndDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    private Button btnLoadReport = Button.xpath("//button[contains(@class,'btn-report-search')]");
    private Label lblNote = Label.xpath("//div[@class='row'][2]//span");
    private Button btnBack = Button.xpath("//i[@class='fas fa-arrow-left ico-export']");
    private Button btnExport = Button.xpath("//i[@class='fas fa-download ico-export']");
    private int colMarketID = 1;
    private int colSettledDate = 2;
    private int colNarration = 3;
    private int colDebit = 4;
    private int colCredit = 5;
    private int colBalance = 6;
    private int totalSummaryColumn = 6;
    private Table tblReport = Table.xpath("//app-group-market//table", totalSummaryColumn);

    private int totalDetailColumn = 8;
    private int colBetID = 1;
    private int colSelection = 2;
    private int colType = 3;
    private int colOdd = 4;
    private int colStake = 5;
    private int colPlaceDate = 6;
    private int colProfitLost = 7;
    private int colStatus = 8;
    private Table tblDetailReport = Table.xpath("//app-group-betdetail//table", totalDetailColumn);


    public void filter(String startDate, String endDate) {
        tblReport.isDisplayed();
        tblReport.getColumnNamesOfTable();
        if (startDate.equalsIgnoreCase(DateUtils.getDate(0, "yyyy-MM-dd", "IST"))) {
            dtpStartDate.selectDate(startDate, "yyyy-MM-dd");
        }
        if (endDate.equalsIgnoreCase(DateUtils.getDate(0, "yyyy-MM-dd", "IST"))) {
            dtpEndDate.selectDate(endDate, "yyyy-MM-dd");
        }
        btnLoadReport.click();
        btnLoadReport.isTextDisplayed(LOAD_REPORT, 5);
    }

    public boolean verifyBalance(List<ArrayList<String>> lstAPIReport) {
        int recordNumber = tblReport.getNumberOfRows(false, false);
        List<String> lst = new ArrayList<String>();
        tblReport.getRow(1, 1);
        String expectedBalance;
        String exepectedNarration;
        String settleDate;
        for (int i = 1; i < recordNumber; i++) {
            lst = tblReport.getRow(i, 1);
            if (lst.get(colNarration - 1).equals(OPENING_BALANCE)) {
                System.out.println("Verify Opening balance row");
                return veifyOpeningBalanceRow(lst);
            }
            if (!lst.get(colMarketID - 1).equalsIgnoreCase(lstAPIReport.get(i - 1).get(6))) {
                System.out.println(String.format("FAILED! Expected Market ID at row %s is %s but found %s", i, lstAPIReport.get(i - 1).get(6), lst.get(colMarketID - 1)));
                return false;
            }
            if (!lst.get(colDebit - 1).equals(getDebitFromAPI(lstAPIReport.get(i - 1).get(14)))) {
                System.out.println(String.format("FAILED! Expected Debit at row %s is %s but found %s", i, getDebitFromAPI(lstAPIReport.get(i - 1).get(14)), lst.get(colDebit - 1)));
                return false;
            }
            if (!lst.get(colCredit - 1).equals(String.format("%.2f", Double.valueOf(lstAPIReport.get(i - 1).get(15))))) {
                System.out.println(String.format("FAILED! Expected Credit at row %s is %s but found %s", i, String.format("%.2f", Double.valueOf(lstAPIReport.get(i - 1).get(15))), lst.get(colCredit - 1)));
                return false;
            }
            expectedBalance = String.format(Locale.getDefault(), "%,.2f", Double.valueOf(lstAPIReport.get(i - 1).get(11).replaceAll(",", "")));
            if (!lst.get(colBalance - 1).equals(expectedBalance)) {
                System.out.println(String.format("FAILED! Expected Balance at row %s is %s but found %s", i, expectedBalance, lst.get(colBalance - 1)));
                return false;
            }
            exepectedNarration = defineNarration(lstAPIReport.get(i - 1).get(10), lstAPIReport.get(i - 1).get(2), lstAPIReport.get(i - 1).get(12), lstAPIReport.get(i - 1).get(9));
            if (!lst.get(colNarration - 1).equals(exepectedNarration)) {
                System.out.println(String.format("FAILED! Expected Balance at row %s is %s but found %s", i, exepectedNarration, lst.get(colNarration - 1)));
                return false;
            }
        }
        return true;
    }

    private String getDebitFromAPI(String debit) {
        double debitValue = Double.valueOf(debit);
        if (debitValue < 0)
            debitValue = debitValue * (-1);
        return String.format("%.2f", Double.valueOf(debitValue));
    }

    private String defineNarration(String type, String competition, String event, String marketName) {
        switch (type) {
            case "TAX":
                return String.format("Tax Deduction on %s / %s / %s", competition, event, marketName);
            default:
                return String.format("PL of Market: %s / %s / %s", competition, event, marketName);
        }
    }

    private boolean veifyOpeningBalanceRow(List<String> data) {
        if (!data.get(colNarration - 1).equals(OPENING_BALANCE)) {
            System.out.println(String.format("FAILED! OPENING BALANCE lable display incorrect. UI is %s", data.get(colNarration - 1)));
            return false;
        }
        if (!data.get(colDebit - 1).equals("0.00")) {
            System.out.println(String.format("FAILED! Debit is incorrect.Actual debit is %s", data.get(colDebit - 1)));
            return false;
        }
        if (!data.get(colCredit - 1).equals(data.get(colBalance - 1))) {
            System.out.println(String.format("FAILED! Credit value not equal Balance value. Credit is %s while Balance is %s", data.get(colCredit - 1), data.get(colBalance - 1)));
            return false;
        }

        AccountBalance acBalance = getUserBalance();
        String balance = String.format("%.2f", Double.parseDouble(acBalance.getBalance()) + Double.parseDouble(acBalance.getExposure()));
        if (!data.get(colBalance - 1).equals(balance)) {
            System.out.println(String.format("FAILED! Balance on header not match with the report. Header is %s but report show %s", balance, data.get(colBalance - 1)));
            return false;
        }
        return true;
    }

    public Table getTblReport() {
        return tblReport;
    }

    public void waitLoadReport() {
        btnLoadReport.isTextDisplayed(MemberConstants.AccountStatementPage.LOAD_REPORT, 5);
    }

    public void clickNarration() {
        Link plofMarketLnk = (Link) tblReport.getControlOfCell(1, colNarration, 1, "span[@class='hover hyperlink']");
        if (!plofMarketLnk.isDisplayed()) {
            System.out.println("The report has no event settled");
            return;
        }
        plofMarketLnk.click();
    }

    public List<ArrayList<String>> getAPIReport(String startDate, String endDate, String timeZone) {
        return BetUtils.getAccountStatement(startDate, endDate, timeZone);
    }

    public String getStartDate() {
        return lblStartDate.getText();
    }

    public String getEndDate() {
        return lblEndDate.getText();
    }

    public String getNote() {
        return lblNote.getText();
    }

    public String getLoadReport() {
        return btnLoadReport.getText();
    }

    public List<String> getReportHeader() {
        return tblReport.getColumnNamesOfTable(1);
    }


}
