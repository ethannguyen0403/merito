package membersite.pages.components.deposit.transactionhistory;

import agentsite.controls.Table;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import com.paltech.utils.DateUtils;
import common.MemberConstants;
import controls.DateTimePicker;
import org.testng.Assert;
import org.testng.SkipException;

import java.text.ParseException;
import java.util.*;

import static membersite.pages.LandingPage.waitPageLoad;

public class NewUITransactionHistoryContainer extends TransactionHistoryContainer {
    private int totalCol = 8;
    private TextBox txtStartDate = TextBox.xpath(String.format("//app-transaction//label[text()='%s']//following::input[1]", MemberConstants.MyBetsPage.START_DATE));
    private TextBox txtEndDate = TextBox.xpath(String.format("//app-transaction//label[text()='%s']//following::input[1]", MemberConstants.MyBetsPage.END_DATE));
    private DateTimePicker dtpStartDate = DateTimePicker.xpath(txtStartDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    private DateTimePicker dtpEndDateDate = DateTimePicker.xpath(txtEndDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    private Button btnSearch = Button.xpath("//app-transaction//button[text()='Search']");

    private Table tblTransaction = Table.xpath("(//app-transaction//table)[1]", totalCol);
    private Label lblNoRecordFound = Label.xpath("(//app-transaction//td[text()='No record found.'])[1]");
    private Button btnPagination = Button.xpath("(//app-transaction//pagination)[1]");
    public void filterTransactionHistory(String startDate, String endDate) {
        if(!startDate.isEmpty()) {
            dtpStartDate.selectDate(startDate, "yyyy-MM-dd");
        }
        if(!endDate.isEmpty()) {
            dtpEndDateDate.selectDate(endDate, "yyyy-MM-dd");
        }
        btnSearch.click();
        waitPageLoad();
    }

    public void clickPeriodTime(String periodTime) {
        Button btnTimePeriod = Button.xpath(String.format("//app-transaction//button[text()='%s']", periodTime));
        btnTimePeriod.click();
        try {
            btnPagination.waitForElementToBePresent(btnPagination.getLocator(), 2);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public List<String> getDataListByRefNo(String refNo) {
        List<String> lstInfo = new ArrayList<>();
        int totalRow = tblTransaction.getNumberOfRows(false, true);
        for (int i = 0; i < totalRow; i++) {
            int colRefNo = tblTransaction.getColumnIndexByName("Reference No.");
            String refNoVal = tblTransaction.getControlOfCell(1, colRefNo, i + 1, null).getText();
            if (refNoVal.equalsIgnoreCase(refNo)) {
                for (int j = 1; j < totalCol; j++) {
                    lstInfo.add(tblTransaction.getControlOfCell(1, j + 1, i + 1, null).getText().trim());
                }
                return lstInfo;
            }
        }
        System.out.println(String.format("Cannot find data list result by inputted Ref No %s", refNo));
        return lstInfo;
    }

    public void verifyTransactionHistoryInfo(String refNo, String type, String status, String startBalance, String amount, String endBalance, String transactionDate) {
        List<String> lstData = getDataListByRefNo(refNo);
        String dateTime = lstData.get(6).substring(0, lstData.get(6).length()-3);
        lstData.remove(6);
        lstData.add(dateTime);
        List<String> lstExpected = Arrays.asList(refNo, type, status, startBalance, amount, endBalance, transactionDate);
        Assert.assertEquals(lstData, lstExpected, "FAILED! ");
    }

    public void verifyFilterDateTimeShowCorrect(String startDate, String endDate) throws ParseException {
        if(lblNoRecordFound.isDisplayed()) {
            throw new SkipException("Have no transaction record found for testing");
        }
        int totalRow = tblTransaction.getNumberOfRows(false, true);
        int colTxnDate = tblTransaction.getColumnIndexByName("Transaction Date");
        for (int i = 0; i < totalRow; i++) {
            Assert.assertTrue(DateUtils.isDateBetweenPeriod(tblTransaction.getControlOfCell(1, colTxnDate, i + 1, null).getText().trim(), startDate, endDate, "yyyy-MM-dd"));
        }
    }

    public String getValueOfEndDate() {
        return txtEndDate.getAttribute("value");
    }

}
