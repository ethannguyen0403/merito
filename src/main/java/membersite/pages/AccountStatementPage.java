package membersite.pages;

import controls.Table;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.accountstatement.AccountStatementContainer;

import java.util.ArrayList;
import java.util.List;

public class AccountStatementPage extends HomePage {
    public AccountStatementContainer accountStatementContainer;

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

    public void clickNarrationOnTheFirstRow() {
        accountStatementContainer.clickNarration();
    }

    public void waitLoadReport() {
        accountStatementContainer.waitLoadReport();
    }
}
