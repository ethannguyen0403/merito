package agentsite.pages.report.statementreport;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import com.paltech.element.common.*;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class StatementReport {
    public int tblReportTotalCol = 2;
    private int colCreditDetail = 2;
    private int colProfitAndLossDetail = 3;
    private int colTaxDetail = 4;
    private int colAvailableBalanceDetail = 5;
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public TextBox txtSearchFrom = TextBox.name("fromDate");
    public TextBox txtSearchTo = TextBox.name("toDate");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-calendar-layout");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-calendar-layout");
    public Button btnSubmit = Button.xpath("//div[@id='search-region']//table//tr[1]//td[8]//button");
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report table-home')]", tblReportTotalCol);
    private Table tblDetailReport = Table.xpath("//table[@class='ptable report']", 5);
    public Label lblExpandFirstRecord = Label.xpath("//table[contains(@class,'ptable report table-home')]//tbody[1]//tr[1]//td[1]//i");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public void filter(String fromDate, String toDate, boolean isSubmit) {
        if (!fromDate.isEmpty())
            dpFrom.selectDate(fromDate, "dd/MM/yyyy");
        if (!toDate.isEmpty())
            dpTo.selectDate(toDate, "dd/MM/yyyy");
        if(isSubmit) {
            btnSubmit.click();
            waitingLoadingSpinner();
        }
    }

    public void expandFirstRecord() {
        if(!lblExpandFirstRecord.isDisplayed()) {
            System.out.println("No any result for expanding");
        } else {
            lblExpandFirstRecord.click();
            waitingLoadingSpinner();
        }
    }

    public List<String> getUsersListUnderExpanded(String userName) {
        List<String> lstUserDownline = new ArrayList<>();
        int rowIndex = getRowIndexofUserName(userName);
        String xpath = "//table[contains(@class,'ptable report table-home')]//tr[%s]//td[1]";
        while (true) {
            Label lblUserName = Label.xpath(String.format(xpath, rowIndex+1));
            if (lblUserName.isDisplayed()) {
                lstUserDownline.add(lblUserName.getText().trim());
            } else {
                return lstUserDownline;
            }
            rowIndex += 1;
        }
    }

    private int getRowIndexofUserName(String username) {
        int i = 1;
        String xpath = tblReport.getxPathOfCell(1, 1, i, null);
        Label lblUserName = Label.xpath(xpath);
        while (true) {
            if (!lblUserName.isDisplayed()) {
                System.out.println("DEBUG! Username " + username + " does not exist in the table");
                return 0;
            }
            xpath = tblReport.getxPathOfCell(1, 1, i, null);
            lblUserName = Label.xpath(xpath);
            if (lblUserName.getText().trim().equals(username)) {
                return i;
            }
            i = i + 1;
        }
    }

    public void openStatementReportDetail(String userName) {
        int rowIndex = getRowIndexofUserName(userName);
        Button btnStatementReportDetails = Button.xpath(String.format("//table[contains(@class,'ptable report table-home')]//tbody[1]//tr[%s]//td[3]//a",rowIndex));
        if(btnStatementReportDetails.isDisplayed()) {
            btnStatementReportDetails.click();
            waitingLoadingSpinner();
        }
    }

    public void openSportGameDetail(String sportGameName) {
        Label lblSport = Label.xpath(tblDetailReport.getControlxPathBasedValueOfDifferentColumnOnRow(sportGameName, 1, 1,1,null,1,"span",false,false));
        if(lblSport.isDisplayed()) {
            lblSport.click();
            waitingLoadingSpinner();
        } else {
            System.out.println(String.format("Have no %s for opening", sportGameName));
        }
    }

    public List<Double> defineAvailableBalance() {
        List<Double> lstAvailableBalance = new ArrayList<>();
        List<ArrayList<String>> lstRowValues = new ArrayList<>();
        Double openingBal = getOpeningBalance();
        int rowIndex = tblDetailReport.getNumberOfRows(false, true);
        int colCount = 3;
        double creditVal;
        double profitAndLossVal;
        double taxVal;
        double previousAvailableBalanceVal;
        double currentAvailableBalanceVal;
        if(openingBal.isNaN()) {
            return null;
        } else {
            for (int i = 0; i < rowIndex; i++) {
                List<String> lstCellValue = new ArrayList<>();
                for (int j = 0; j < colCount ; j++) {
                    Label lblCellValue = Label.xpath(tblDetailReport.getxPathOfCell(1,j+2,i+1,null));
                    lstCellValue.add(j, lblCellValue.getText());
                }
                lstRowValues.add(i, (ArrayList<String>) lstCellValue);
                if (i == 0) {
                    lstAvailableBalance.add(i, openingBal);
                } else {
                    Label lblCellValue = Label.xpath(tblDetailReport.getxPathOfCell(1,colAvailableBalanceDetail,i,"span"));
                    creditVal = Double.parseDouble(lstRowValues.get(i).get(0));
                    profitAndLossVal = Double.parseDouble(lstRowValues.get(i).get(1));
                    taxVal = Double.parseDouble(lstRowValues.get(i).get(2));
                    previousAvailableBalanceVal = Double.parseDouble(lblCellValue.getText().replace(",",""));
                    currentAvailableBalanceVal = previousAvailableBalanceVal + (creditVal + profitAndLossVal + taxVal);
                    lstAvailableBalance.add(i, currentAvailableBalanceVal);
                }
            }
            return lstAvailableBalance;
        }
    }

    private Double getOpeningBalance() {
        Label lblOpeningBal = Label.xpath(tblDetailReport.getxPathOfCell(1,5,1, "span"));
        if (lblOpeningBal.isDisplayed()) {
            double openingBal = Double.parseDouble(lblOpeningBal.getText().replace(",",""));
            return openingBal;
        } else {
            return null;
        }

    }

    public void verifyTableDetailHeaderDisplayCorrect(String userType) {
        List<String> expectedHeader =
                userType.equalsIgnoreCase("CREDIT") ? AGConstant.Report.StatementReport.TABLE_DETAIL_STATEMENT_HEADER_CREDIT :
                        AGConstant.Report.StatementReport.TABLE_DETAIL_STATEMENT_HEADER_CREDIT_CASH;
        ArrayList<String> lstHeader = tblDetailReport.getHeaderNameOfRows();
        Assert.assertEquals(lstHeader, expectedHeader, "FAILED! Table Header displays incorrectly");
    }

    public void verifyAvailableBalanceShowCorrect(List<Double> lstExpectedBalance) {
        int rowIndex = tblDetailReport.getNumberOfRows(false, true);
        for (int i = 0; i < rowIndex; i++) {
            Label lblCellValue = Label.xpath(tblDetailReport.getxPathOfCell(1, colAvailableBalanceDetail, i + 1, null));
            Assert.assertEquals(Double.valueOf(lblCellValue.getText().replace(",", "")),
                    Double.valueOf(String.format("%.2f", lstExpectedBalance.get(i))), 0.01,
                    "FAILED! Available balance is not shown correctly");
        }
    }
}
