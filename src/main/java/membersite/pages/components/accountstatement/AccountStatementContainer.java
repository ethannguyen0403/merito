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

    public Button btnBack = Button.xpath("//i[@class='fas fa-arrow-left ico-export']");
    public Button btnExport = Button.xpath("//i[@class='fas fa-download ico-export']");

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
