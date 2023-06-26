package membersite.pages.components.accountstatement;

import com.paltech.element.common.*;
import com.paltech.utils.DateUtils;
import controls.DateTimePickerOldUI;
import controls.Table;
import membersite.objects.AccountBalance;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static common.MemberConstants.AccountStatementPage.OPENING_BALANCE;

public class OldUIAccountStatementContainer extends AccountStatementContainer{
    // This is for /xch UI
    public  Label lblProductTitle = Label.xpath("//div[@class='actions no-print']//span[@class='current-activity']");
    public Icon icDownload = Icon.xpath("//div[@class='actions no-print']//i[contains(@class,'download-alt')]");
    public Icon icPrint = Icon.xpath("//div[@class='actions no-print']//i[contains(@class,'print')]");
    public  Button btnProductActive = Button.xpath("//button[contains(@class,'btn-default')]//span[@class='action-selected']");
    public  Button btnMainWalletStatement = Button.xpath("//button[contains(@class,'btn-default')]//span[@class='wallet-selected']");
    public String sMnProductxPath = "//ul[@class='dropdown-menu activities']//a[text()='%s']";
    public Button btnCurrent = Button.xpath("//div[@class='filter-type shows settled exchange']//button[@data-type='OPEN']");
    public Button btnSettle = Button.xpath("//div[@class='filter-type shows settled exchange']//button[@data-type='SETTLED']");
    public Button btnUnmatched = Button.xpath("//div[@class='filter-type shows settled exchange']//button[@data-type='unmatched']");
    public Button btnMatched = Button.xpath("//div[@class='filter-type shows settled exchange']//button[@data-type='matched']");
    public Label lblAll = Label.xpath("//div[@class='options']//div[contains(@class,'date-range')]");
    private Label lblOption =Label.xpath("//div[@class='options']//div[contains(@class,'option__cell')]");
    private RadioButton rbChooseADateRangeFrom =RadioButton.id("date-from-to");
    private TextBox txtStartDate = TextBox.id("fromDate");
    private DateTimePickerOldUI dtpStartDate= DateTimePickerOldUI.xpath(txtStartDate,"//div[contains(@class,'datepicker datepicker-dropdown')]");
    private TextBox txtEndDate = TextBox.id("toDate");
    private DateTimePickerOldUI dtpEndDate= DateTimePickerOldUI.xpath(txtEndDate,"//div[contains(@class,'datepicker datepicker-dropdown')]");

    public Icon icReresh = Icon.xpath("//div[@class='options']//div[contains(@class,'refresh ')]");
    public Label lblNote = Label.xpath("//div[@class='note']");
    public Label lblStartDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][1]//label");
    public Label lblEndDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][2]//label");

    public Button btnLoadReport = Button.xpath("//button[contains(@class,'btn-report-search')]");
    public Button btnBack = Button.xpath("//i[@class='fas fa-arrow-left ico-export']");
    public Button btnExport = Button.xpath("//i[@class='fas fa-download ico-export']");
    public int colSportGame = 1;
    public int colRemarks = 2;
    public int colTurnover = 3;
    public int colProfitLoss = 4;
    public int colTaxCommission = 5;
    public int colBalance = 6;
    private int totalSummaryColumn = 6;
    private Table tblReport = Table.xpath("//div[@id='account-statement']//table", totalSummaryColumn);
    private int totalDetailColumn = 8;
    public int colBetID = 1;
    public int colSelection = 2;
    public int colType = 3;
    public int colOdd = 4 ;
    public int colStake = 5;
    public int colPlaceDate=6;
    public int colProfitLost = 7;
    public int colStatus = 8;
    public Table tblDetailReport = Table.xpath("//div[@class='d-none d-lg-block']//table[@class='table table-striped table-hover table-sm']", totalDetailColumn);
    private int colSettledDate = 2;

    public void filter(String startDate, String endDate)
    {
        lblOption.click();
        rbChooseADateRangeFrom.waitForControlInvisible(1,1);
        rbChooseADateRangeFrom.click();
        if(!startDate.isEmpty()){
        if(!startDate.equalsIgnoreCase(DateUtils.getDate(0,"yyyy-MM-dd","GMT-04"))){
            dtpStartDate.selectDate(startDate,"yyyy-MM-dd");
        }}
        if(!endDate.isEmpty()){
            if(!endDate.equalsIgnoreCase(DateUtils.getDate(0,"yyyy-MM-dd","GMT-04"))){
                dtpEndDate.selectDate(endDate,"yyyy-MM-dd");
            }
        }
      tblReport.waitForControlInvisible(1,1);
    }
    public boolean verifyBalance(List<ArrayList<String>> lstAPIReport)
    {
        int recordNumber = tblReport.getNumberOfRows(false,false);
        List<String> lst = new ArrayList<String>();
        tblReport.getRow(1,1);
        String expectedBalance ;
        String exepectedNarration;
        String settleDate;
        for(int i = 1; i< recordNumber; i++) {
            lst = tblReport.getRow(i,1);
            if(lst.get(colRemarks-1).equals(OPENING_BALANCE))
            {
                System.out.println("Verify Opening balance row");
                return veifyOpeningBalanceRow(lstAPIReport.get(0));
            }
            if(!lst.get(colSportGame - 1).equalsIgnoreCase(lstAPIReport.get(i-1).get(6))) {
                System.out.println(String.format("FAILED! Expected Market ID at row %s is %s but found %s",i,lstAPIReport.get(i-1).get(6),lst.get(colSportGame-1)));
                return false;
            }
            settleDate = DateUtils.formatDate(lstAPIReport.get(i-1).get(4),"E MMM dd HH:mm:ss Z yyyy","YYYY-MM-dd");
            if(!lst.get(colSettledDate-1).equals(settleDate)) {
                System.out.println(String.format("FAILED! Expected Settle Date at row %s is %s but found %s",i+1, settleDate,lst.get(colSettledDate-1)));
                //FAILED! Expected Settle Date at row 2 is Mon Jul 26 22:30:00 ICT 2021 but found 2021-07-26
                return false;
            }
            if(!lst.get(colProfitLoss -1).equals(getDebitFromAPI(lstAPIReport.get(i-1).get(14)))) {
                System.out.println(String.format("FAILED! Expected Debit at row %s is %s but found %s",i,getDebitFromAPI(lstAPIReport.get(i-1).get(14)),lst.get(colProfitLoss -1)));
                return false;
            }
            if(!lst.get(colTaxCommission -1).equals(String.format("%.2f",Double.valueOf(lstAPIReport.get(i-1).get(15))))) {
                System.out.println(String.format("FAILED! Expected Credit at row %s is %s but found %s", i ,String.format("%.2f",Double.valueOf(lstAPIReport.get(i-1).get(15))), lst.get(colTaxCommission - 1)));
                return false;
            }
            expectedBalance = String.format( Locale.getDefault(),"%,.2f",Double.valueOf(lstAPIReport.get(i-1).get(11).replaceAll(",", "")));
            if(!lst.get(colBalance-1).equals(expectedBalance)) {
                System.out.println(String.format("FAILED! Expected Balance at row %s is %s but found %s",i, expectedBalance,lst.get(colBalance-1)));
                return false;
            }
            exepectedNarration = defineNarration(lstAPIReport.get(i-1).get(10),lstAPIReport.get(i-1).get(2),lstAPIReport.get(i-1).get(12),lstAPIReport.get(i-1).get(9));
            if(!lst.get(colTurnover-1).equals(exepectedNarration)) {
                System.out.println(String.format("FAILED! Expected Balance at row %s is %s but found %s",i, exepectedNarration,lst.get(colTurnover-1)));
                return false;
            }
            //  tblReport.getControlOfCell(1, colNarration, i + 1, "span[@class='hover hyperlink'").click();
        }
        return true;
    }

    private String getDebitFromAPI(String debit){
        double debitValue = Double.valueOf(debit);
        if(debitValue < 0 )
            debitValue = debitValue *(-1);
        return  String.format("%.2f",Double.valueOf(debitValue));
    }

    private String defineNarration(String type, String competition, String event, String marketName){
        switch (type){
            case "TAX":
                return  String.format("Tax Deduction on %s / %s / %s",competition,event,marketName);
            default:
                return String.format("PL of Market: %s / %s / %s",competition,event,marketName);
        }
    }

    private boolean veifyOpeningBalanceRow(List<String> data)
    {
        List<String> lst =  tblReport.getRow(1,1);
        if(!lst.get(colSportGame -1).equals(""))
        {
            System.out.println(String.format("FAILED! Sport/Game is incorrect.Actual debit is %s",lst.get(colSportGame -1)));
            return false;
        }
        if(lst.get(colRemarks -1).equals("Open Balance"))
        {
            System.out.println(String.format("FAILED! OPENING BALANCE lable display incorrect. UI is %s",data.get(colRemarks -1 )));
            return false;
        }
        if(!lst.get(colTurnover -1).equals(""))
        {
            System.out.println(String.format("FAILED! Turnover value not equal Balance value. Credit is %s while Balance is %s","",lst.get(colTurnover -1)));
            return false;
        }
        if(!lst.get(colBalance -1).equals(data.get(colBalance-1)))
        {
            System.out.println(String.format("FAILED! Balance on header not match with the report. Header is %s but report show %s",lst.get(colBalance -1),data.get(colBalance-1)));
            return false;
        }
        return true;
    }

    public Table getTblReport(){
        return tblReport;
    }
    public List<ArrayList<String>> getAPIReport(String startDate, String endDate, String timeZone){
      //  return BetUtils.getAccountStatementOld(startDate,endDate,timeZone);
        return null;
    }

    public void waitLoadReport(){
        tblReport.waitForControlInvisible(1,2);
    }
}
