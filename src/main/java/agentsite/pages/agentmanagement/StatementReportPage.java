package agentsite.pages.agentmanagement;

import agentsite.controls.Cell;
import agentsite.controls.DateTimePicker;
import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.report.statementreport.StatementReport;
import com.paltech.element.common.*;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class StatementReportPage extends HomePage {
    public Label lblNoRecord = Label.xpath("//tfoot[@class='no-record']//td");
    public TextBox txtSearchFrom = TextBox.name("fromDate");
    public TextBox txtSearchTo = TextBox.name("toDate");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-calendar-layout");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-calendar-layout");
    public Button btnToday = Button.xpath("//div[@id='search-region']//table//tr[1]//td[5]//button");
    public Button btnYesterday = Button.xpath("//div[@id='search-region']//table//tr[1]//td[6]//button");
    public Button btnLastWeek = Button.xpath("//div[@id='search-region']//table//tr[1]//td[7]//button");
    public Button btnSubmit = Button.xpath("//div[@id='search-region']//table//tr[1]//td[8]//button");
    public Label lblInfo = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public Label lnlBreadcrumbSearchTitle = Label.xpath("//span[contains(@class,'my-breadcrumb')]/span[2]");
    public Label lnkHome = Label.xpath("//span[contains(@class,'my-breadcrumb')]/span[1]");
    public Label lblYouCanSeeReportData = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public int tblReportTotalCol = 2;
    public int colUsername = 1;
    public int colLoginId = 2;
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report table-home')]", tblReportTotalCol);
    public Table tblDetailReport = Table.xpath("//table[@class='ptable report']", 5);
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    public Label lblExpandFirstRecord = Label.xpath("//table[contains(@class,'ptable report table-home')]//tbody[1]//tr[1]//td[1]//i");
    public StatementReport statementReport;
    public StatementReportPage(String types) {
        super(types);
        _type = types;
        statementReport = ComponentsFactory.statementReportPage(_type);

    }

    public Table detailStatement(String username) {
        String xPath = tblReport.getControlxPathBasedValueOfDifferentColumnOnRow(username, 1, 1, 1, null, 3, null, false, false);
        Icon.xpath(xPath).click();
        waitingLoadingSpinner();
        return tblDetailReport;
    }

//    public void filter(String fromDate, String toDate, boolean isSubmit) {
//        if (!fromDate.isEmpty())
//            dpFrom.selectDate(fromDate, "dd/MM/yyyy");
//        if (!toDate.isEmpty())
//            dpTo.selectDate(toDate, "dd/MM/yyyy");
//        if(isSubmit) {
//            btnSubmit.click();
//            waitingLoadingSpinner();
//        }
//    }

//    public void expandFirstRecord() {
//        if(!lblExpandFirstRecord.isDisplayed()) {
//            System.out.println("No any result for expanding");
//        } else {
//            lblExpandFirstRecord.click();
//            waitingLoadingSpinner();
//        }
//    }

//    public List<String> getUsersListUnderExpanded(String userName) {
//        List<String> lstUserDownline = new ArrayList<>();
//        int rowIndex = getRowIndexofUserName(userName);
//        String xpath = "//table[contains(@class,'ptable report table-home')]//tr[%s]//td[1]";
//        while (true) {
//            Label lblUserName = Label.xpath(String.format(xpath, rowIndex+1));
//            if (lblUserName.isDisplayed()) {
//                lstUserDownline.add(lblUserName.getText().trim());
//            } else {
//                return lstUserDownline;
//            }
//            rowIndex += 1;
//        }
//    }

//    private int getRowIndexofUserName(String username) {
//        int i = 1;
//        String xpath = tblReport.getxPathOfCell(1, 1, i, null);
//        Label lblUserName = Label.xpath(xpath);
//        while (true) {
//            if (!lblUserName.isDisplayed()) {
//                System.out.println("DEBUG! Username " + username + " does not exist in the table");
//                return 0;
//            }
//            xpath = tblReport.getxPathOfCell(1, 1, i, null);
//            lblUserName = Label.xpath(xpath);
//            if (lblUserName.getText().trim().equals(username)) {
//                return i;
//            }
//            i = i + 1;
//        }
//    }

//    public void openStatementReportDetail(String userName) {
//        int rowIndex = getRowIndexofUserName(userName);
//        Button btnStatementReportDetails = Button.xpath(String.format("//table[contains(@class,'ptable report table-home')]//tbody[1]//tr[%s]//td[3]//a",rowIndex));
//        if(btnStatementReportDetails.isDisplayed()) {
//            btnStatementReportDetails.click();
//            waitingLoadingSpinner();
//        }
//    }

}
