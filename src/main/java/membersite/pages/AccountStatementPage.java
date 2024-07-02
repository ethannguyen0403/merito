package membersite.pages;

import com.paltech.element.common.Icon;
import common.MemberConstants;
import controls.Table;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.accountstatement.AccountStatementContainer;
import membersite.pages.components.ps38betdetail.PS38BetDetail;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AccountStatementPage extends HomePage {
    public Icon homeIcon = Icon.xpath("//*[@class='fas fa-home fa-2x']");
    public AccountStatementContainer accountStatementContainer;
    public PS38BetDetail ps38BetDetail = new PS38BetDetail();

    public AccountStatementPage(String types) {
        super(types);
        accountStatementContainer = ComponentsFactory.accountStatementContainerObject(types);
    }

    public List<ArrayList<String>> getAPIReport(String startDate, String endDate, String timeZone) {
        return accountStatementContainer.getAPIReport(startDate, endDate, timeZone);
    }

    public Table getReportTable() {
        return accountStatementContainer.getTblReport();
    }

    public void filter(String startDate, String endDate) {
        accountStatementContainer.filter(startDate, endDate);
        waitPageLoad();
    }


    public boolean verifyBalance(List<ArrayList<String>> lstAPIReport) {
        return accountStatementContainer.verifyBalance(lstAPIReport);
    }

    public controls.Table getTblReport() {
        return accountStatementContainer.getTblReport();
    }

    public List<String> getHeadersDetails(){
        return accountStatementContainer.getReportHeader();
    }

    public List<String> getReportDetailHeader(){
        return accountStatementContainer.getReportDetailHeader();
    }

    public void clickNarrationOnTheFirstRow() {
        accountStatementContainer.clickNarration();
    }


    public List<ArrayList<String>> expandCashOutHistoryByIndex(int index){
      return  ps38BetDetail.expandCashOutHistoryByIndex(index);
    }

    public List<ArrayList<String>> expandCashOutHistoryByIndex(String betID){
        return  ps38BetDetail.expandCashOutHistoryByIndex(betID);
    }

    public void verifyCashOutHistoryCorrect(String betID){
       ps38BetDetail.verifyCashOutHistoryCorrect(betID);
    }

    public void waitLoadReport() {
        accountStatementContainer.waitLoadReport();
    }

    public void verifyHeaderOfTableReport() {
        List<String> tblHeaders = accountStatementContainer.getReportHeader();
        if(_type.equalsIgnoreCase("satsport") || _type.equalsIgnoreCase("fairexchange")) {
            Assert.assertEquals(tblHeaders.size(), MemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER.size(), String.format("ERROR: The expected no of columns is %s but found %s", MemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER.size(), tblHeaders.size()));
            Assert.assertEquals(tblHeaders, MemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER_NEWUI, "ERROR! Account Statement header not match with the expected");
        } else {
            Assert.assertEquals(tblHeaders.size(), MemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER.size(), String.format("ERROR: The expected no of columns is %s but found %s", MemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER.size(), tblHeaders.size()));
            Assert.assertEquals(tblHeaders, MemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER, "ERROR! Account Statement header not match with the expected");
        }
    }
}
