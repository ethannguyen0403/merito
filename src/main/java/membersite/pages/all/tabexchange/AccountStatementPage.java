package membersite.pages.all.tabexchange;
import com.paltech.element.common.TextBox;
import controls.DateTimePicker;
import controls.Table;
import membersite.objects.AccountBalance;
import membersite.pages.all.components.Header;
import com.paltech.element.common.Label;
import com.paltech.element.common.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static common.MemberConstants.AccountStatementPage.LOAD_REPORT;
import static common.MemberConstants.AccountStatementPage.OPENING_BALANCE;

public class AccountStatementPage extends Header {
    public Label lblStartDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][1]//label");
    public Label lblEndDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][2]//label");
    public TextBox txtStartDate = TextBox.xpath("//div[contains(@class,'custom-bsDatepicker')][1]//input");
    public DateTimePicker dtpStartDate= DateTimePicker.xpath(txtStartDate,"//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    public TextBox txtEndDate = TextBox.xpath("//div[contains(@class,'custom-bsDatepicker')][2]//input");
    public DateTimePicker dtpEndDate= DateTimePicker.xpath(txtEndDate,"//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    public Button btnLoadReport = Button.xpath("//button[contains(@class,'btn-report-search')]");
    public Label lblNote = Label.xpath("//div[@class='row'][2]//span");
    public Button btnBack = Button.xpath("//i[@class='fas fa-arrow-left ico-export']");
    public Button btnExport = Button.xpath("//i[@class='fas fa-download ico-export']");
    public int colMarketID = 1;
    public int colSettledDate = 2;
    public int colNarration = 3;
    public int colDebit = 4;
    public int colCredit = 5;
    public int colBalance = 6;
    private int totalSummaryColumn = 6;
    public Table tblReport = Table.xpath("//app-group-market//table", totalSummaryColumn);

    private int totalDetailColumn = 8;
    public int colBetID = 1;
    public int colSelection = 2;
    public int colType = 3;
    public int colOdd = 4 ;
    public int colStake = 5;
    public int colPlaceDate=6;
    public int colProfitLost = 7;
    public int colStatus = 8;
    public Table tblDetailReport = Table.xpath("//app-group-betdetail//table", totalDetailColumn);

    public void filter(String startDate, String endDate)
    {
        tblReport.isDisplayed();
        tblReport.getColumnNamesOfTable();
        dtpStartDate.selectDate(startDate,"yyyy-MM-dd");
        dtpEndDate.selectDate(endDate,"yyyy-MM-dd");
        btnLoadReport.click();
        btnLoadReport.isTextDisplayed(LOAD_REPORT,5);
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
            if(lst.get(colNarration-1).equals(OPENING_BALANCE))
            {
                System.out.println("Verify Opening balance row");
                return veifyOpeningBalanceRow(lst);
            }
            if(!lst.get(colMarketID - 1).equalsIgnoreCase(lstAPIReport.get(i-1).get(6))) {
                System.out.println(String.format("FAILED! Expected Market ID at row %s is %s but found %s",i,lstAPIReport.get(i-1).get(6),lst.get(colMarketID-1)));
                return false;
            }
         /*   settleDate = DateUtils.formatDate(lstAPIReport.get(i-1).get(4),"E MMM dd HH:mm:ss Z yyyy","YYYY-MM-dd");
            if(!lst.get(colSettledDate-1).equals(settleDate)) {
                System.out.println(String.format("FAILED! Expected Settle Date at row %s is %s but found %s",i+1, settleDate,lst.get(colSettledDate-1)));
                //FAILED! Expected Settle Date at row 2 is Mon Jul 26 22:30:00 ICT 2021 but found 2021-07-26
                return false;
            }*/
            if(!lst.get(colDebit-1).equals(getDebitFromAPI(lstAPIReport.get(i-1).get(14)))) {
                System.out.println(String.format("FAILED! Expected Debit at row %s is %s but found %s",i,getDebitFromAPI(lstAPIReport.get(i-1).get(14)),lst.get(colDebit-1)));
                return false;
            }
            if(!lst.get(colCredit-1).equals(String.format("%.2f",Double.valueOf(lstAPIReport.get(i-1).get(15))))) {
                System.out.println(String.format("FAILED! Expected Credit at row %s is %s but found %s", i ,String.format("%.2f",Double.valueOf(lstAPIReport.get(i-1).get(15))), lst.get(colCredit - 1)));
                return false;
            }
             expectedBalance = String.format( Locale.getDefault(),"%,.2f",Double.valueOf(lstAPIReport.get(i-1).get(11).replaceAll(",", "")));
            if(!lst.get(colBalance-1).equals(expectedBalance)) {
                System.out.println(String.format("FAILED! Expected Balance at row %s is %s but found %s",i, expectedBalance,lst.get(colBalance-1)));
                return false;
            }
            exepectedNarration = defineNarration(lstAPIReport.get(i-1).get(10),lstAPIReport.get(i-1).get(2),lstAPIReport.get(i-1).get(12),lstAPIReport.get(i-1).get(9));
            if(!lst.get(colNarration-1).equals(exepectedNarration)) {
                System.out.println(String.format("FAILED! Expected Balance at row %s is %s but found %s",i, exepectedNarration,lst.get(colNarration-1)));
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
        if(!data.get(colNarration -1 ).equals(OPENING_BALANCE))
        {
            System.out.println(String.format("FAILED! OPENING BALANCE lable display incorrect. UI is %s",data.get(colNarration -1 )));
            return false;
        }
        if(!data.get(colDebit -1).equals("0.00"))
        {
            System.out.println(String.format("FAILED! Debit is incorrect.Actual debit is %s",data.get(colDebit -1 )));
            return false;
        }
        if(!data.get(colCredit -1).equals(data.get(colBalance -1)))
        {
            System.out.println(String.format("FAILED! Credit value not equal Balance value. Credit is %s while Balance is %s",data.get(colCredit -1),data.get(colBalance -1)));
            return false;
        }

        AccountBalance acBalance = getUserBalance();
        String balance = String.format("%.2f",Double.parseDouble(acBalance.getBalance()) + Double.parseDouble(acBalance.getExposure()));
        if(!data.get(colBalance -1).equals(balance))
        {
            System.out.println(String.format("FAILED! Balance on header not match with the report. Header is %s but report show %s",balance,data.get(colBalance -1)));
            return false;
        }
        return true;
    }

}
