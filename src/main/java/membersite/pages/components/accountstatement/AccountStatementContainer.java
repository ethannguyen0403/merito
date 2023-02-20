package membersite.pages.components.accountstatement;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import controls.DateTimePicker;
import controls.Table;
import membersite.objects.AccountBalance;
import membersite.utils.betplacement.BetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static common.MemberConstants.AccountStatementPage.LOAD_REPORT;
import static common.MemberConstants.AccountStatementPage.OPENING_BALANCE;

public class AccountStatementContainer{
    // This containner available for /x UI
    protected Label lblStartDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][1]//label");
    protected Label lblEndDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][2]//label");
    protected TextBox txtStartDate = TextBox.xpath("//div[contains(@class,'custom-bsDatepicker')][1]//input");
    protected DateTimePicker dtpStartDate= DateTimePicker.xpath(txtStartDate,"//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    protected TextBox txtEndDate = TextBox.xpath("//div[contains(@class,'custom-bsDatepicker')][2]//input");
    protected DateTimePicker dtpEndDate= DateTimePicker.xpath(txtEndDate,"//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    protected Button btnLoadReport = Button.xpath("//button[contains(@class,'btn-report-search')]");
    protected Label lblNote = Label.xpath("//div[@class='row'][2]//span");
    protected Button btnBack = Button.xpath("//i[@class='fas fa-arrow-left ico-export']");
    protected Button btnExport = Button.xpath("//i[@class='fas fa-download ico-export']");
    protected int colMarketID = 1;
    protected int colSettledDate = 2;
    protected int colNarration = 3;
    protected int colDebit = 4;
    protected int colCredit = 5;
    protected int colBalance = 6;
    private int totalSummaryColumn = 6;
    protected Table tblReport = Table.xpath("//app-group-market//table", totalSummaryColumn);

    private int totalDetailColumn = 8;
    protected int colBetID = 1;
    protected int colSelection = 2;
    protected int colType = 3;
    protected int colOdd = 4 ;
    protected int colStake = 5;
    protected int colPlaceDate=6;
    protected int colProfitLost = 7;
    protected int colStatus = 8;
    protected Table tblDetailReport = Table.xpath("//app-group-betdetail//table", totalDetailColumn);
    public void filter(String startDate, String endDate)
    {
    }

    public boolean verifyBalance(List<ArrayList<String>> lstAPIReport)
    {
        return false;
    }
    public Table getTblReport(){
        return  Table.xpath("",1);
    }

    public void waitLoadReport(){

    }
    public void clickNarration(){
    }
    public List<ArrayList<String>> getAPIReport(String startDate, String endDate, String timeZone){
        return null;
    }

    public String getStartDate(){return "";}

    public String getEndDate(){return "";}

    public String getNote(){return "";}
    public String getLoadReport(){return "";}
    public List<String> getReportHeader(){return null;}
}
