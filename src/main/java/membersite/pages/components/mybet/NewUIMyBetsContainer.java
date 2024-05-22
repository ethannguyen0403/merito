package membersite.pages.components.mybet;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import common.MemberConstants;
import controls.DateTimePicker;
import controls.Table;
import membersite.objects.Wager;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NewUIMyBetsContainer extends MyBetsContainer {
    private String xPathSelection = "//div[contains(@class,'%s') and contains(@class,'row-open-bet')]";
    private Label lblOrderType = Label.xpath("//div[@class='row'][2]//select[contains(@class,'select-type-order')]//preceding::label[1]");
    private TextBox lblStartDate = TextBox.xpath(String.format("//label[text()='%s']", MemberConstants.MyBetsPage.START_DATE));
    private TextBox lblEndDate = TextBox.xpath(String.format("//label[text()='%s']", MemberConstants.MyBetsPage.END_DATE));
    private Label lblProduct = Label.xpath("//div[@class='container-title row']//div[contains(@class,'title')]");
    private Label lblNote = Label.xpath("//div[@class='container-title row']/div/span");
    private DropDownBox ddbProduct = DropDownBox.xpath("//div[@class='row'][1]//select[contains(@class,'select-type-order')]");
    private DropDownBox ddbOrderType = DropDownBox.xpath("//div[@class='row'][2]//select[contains(@class,'select-type-order')]");
    private Button btnLoadReport = Button.xpath("//button[contains(@class,'form-control btn btn-warning btn-report-search')]");
    private Label lblErrorMessage = Label.xpath("//div[contains(@class,'bet-info error')]");
    private Link lnkCancelAll = Link.xpath("//div[contains(@class,'cancel-all-bet')]//a");
    private int colTotal = 12;
    private int colStatus = 9;
    private int colMarketName = 1;
    private int colBetID = 2;
    private int colEventID = 3;
    private int colSelection = 4;
    private int colType = 5;
    private int colOdd = 6;
    private int colStake = 7;
    private int colProfitLoss = 8;
    private int colPlaceDate = 10;
    private int colIPAddress = 11;

    private Table tblReport = Table.xpath("//table[@class='table table-sm']", colTotal);
    private Link lnkPagination = Link.xpath("//ul[@class='pagination']//li");
    private Label lblNoRecord = Label.xpath("//table[@class='table table-sm']/tbody/tr[1]/td[@class='text-center']");

    private TextBox txtStartDate = TextBox.xpath(String.format("//label[text()='%s']/following::input[1]", MemberConstants.MyBetsPage.START_DATE));
    private DateTimePicker dtpStartDate = DateTimePicker.xpath(txtStartDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    private TextBox txtEndDate = TextBox.xpath(String.format("//label[text()='%s']/following::input[1]", MemberConstants.MyBetsPage.END_DATE));
    private DateTimePicker dtpEndDate = DateTimePicker.xpath(txtEndDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    private Button btnDownload = Button.xpath("//i[contains(@class,'fas fa-download ico-export')]");

/*

    public boolean verifyWagerDisplayInReport(String wagerID){
        if(lblNoRecord.isDisplayed()){
            return lblNoRecord.getText().equals(FEMemberConstants.NO_RECORD_FOUND);
        }else {
            List<ArrayList<String>> lstRecords = tblReport.getRowsWithoutHeader(1,false);
           return (StringUtils.isListContainText(lstRecords,wagerID, 1), "ERROR! Expected Order ID not display but found ");
        }
        return false;
    }
*/
    public void verifyWagerInfo(Wager wager) {
        List<String> lstColumnName = Arrays.asList("Market Name", "Selection", "Odds", "Stake");
        if(!lblNoRecord.isDisplayed()){
            List<ArrayList<String>> lstRecords = tblReport.getRowsWithoutHeader(1,false);
            List<Integer> lstColumnIndex = tblReport.getListColumnIndexByListName(lstColumnName);
            Assert.assertTrue(lstRecords.get(0).get(lstColumnIndex.get(0)).contains(wager.getMarketName()), "FAILED! Market Name does not show correct");
            Assert.assertTrue(lstRecords.get(0).get(lstColumnIndex.get(1)).equals(wager.getRunnerName()), "FAILED! Selection does not show correct");
            if(wager.getPayout() != 0 && wager.getPayout() != 100.0 && wager.getNumberOfActiveRunner() < 2) {
                Assert.assertTrue(lstRecords.get(0).get(lstColumnIndex.get(2)).equals(String.format("%,.0f:%,.0f",wager.getOdds(),wager.getPayout())), "FAILED! Odds does not show correct");
            } else {
                Assert.assertTrue(lstRecords.get(0).get(lstColumnIndex.get(2)).equals(String.format("%,.0f",wager.getOdds())), "FAILED! Odds does not show correct");
            }
            Assert.assertTrue(lstRecords.get(0).get(lstColumnIndex.get(3)).equals(String.format("%,.2f",wager.getStake())), "FAILED! Stake does not show correct");
        } else {
            Assert.assertTrue(false, "No record found");
        }
    }
    public void filter(String productName, String orderType) {
        filter(productName, orderType, "", "");
    }

    public void filter(String productName, String orderType, String startDate, String endDate) {
        ddbProduct.isDisplayed();
        if (!ddbProduct.getFirstSelectedOption().contains(productName)) {
            ddbProduct.selectByVisibleContainsText(productName);
        }
        ddbOrderType.selectByVisibleText(orderType);

        if (!startDate.isEmpty()) {
            dtpStartDate.selectDate(startDate, "yyyy-MM-dd");
        }
        if (!endDate.isEmpty()) {
            dtpEndDate.selectDate(endDate, "yyyy-MM-dd");
        }

        btnLoadReport.click();
        btnLoadReport.isTextDisplayed(MemberConstants.MyBetsPage.LOAD_REPORT, 10);
    }

    public boolean validateFilterStatus(String status) {
        List<String> lst = tblReport.getColumn("/tbody[%s]//", colStatus, false);
        for (String sts : lst) {
            if (status.equalsIgnoreCase("Settled") || status.equalsIgnoreCase("Cashed Out")) {
                if (!sts.equalsIgnoreCase("Won") || !sts.equalsIgnoreCase("Cashed Out")) {
                    if (!sts.equalsIgnoreCase("Lost")) {
                        System.out.println(String.format("ERROR! Expected status is %s but found %s", status, sts));
                        return false;
                    }
                }
            }
            else {
                if (!sts.equals(status)) {
                    System.out.println(String.format("ERROR! Expected status is %s but found %s", status, sts));
                    return false;
                }
            }
        }
        return true;
    }

    public List<String> translateProductsActive(List<String> productCode) {
        if (Objects.nonNull(productCode)) {
            List<String> productTranslate = new ArrayList<>();
            for (int i = 0, n = productCode.size(); i < n; i++) {
                productTranslate.add(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get(productCode.get(i)));
            }
            return productTranslate;
        } else {
            System.out.println("There is no product active!");
            return null;
        }
    }

    public void waitLoadReport() {
        btnLoadReport.isTextDisplayed(MemberConstants.AccountStatementPage.LOAD_REPORT, 5);
    }

    public void selectProduct(String product) {
        ddbProduct.selectByVisibleText(product);
    }

    public String getNote() {
        return lblNote.getText();
    }

    public String getProduct() {
        return lblProduct.getText();
    }

    public String getOrderType() {
        return lblOrderType.getText();
    }

    public String getStartDate() {
        return lblStartDate.getText();
    }

    public String getEndDate() {
        return lblEndDate.getText();
    }

    public String getLoadReport() {
        return btnLoadReport.getText();
    }

    public List<String> getTableHeaders() {
        ArrayList<String> lstHeader = tblReport.getHeaderNameOfRows();
        return lstHeader;
    }

    public List<ArrayList<String>> getReportIndex(int index, boolean isMove) {
        return tblReport.getRowsWithoutHeader(index, isMove);
    }

    public List<String> getReportColumnValue(int rowIndex, String columnName) {
        List<String> lstValue = new ArrayList<>();
        if(rowIndex > 1) {
            int colIndex = tblReport.getColumnIndexByName(columnName);
            for (int i = 0; i < rowIndex; i++) {
                lstValue.add(tblReport.getLabelOfCell(i + 1, colIndex, 1, null).getText().trim());
            }

        } else {
            System.out.println(String.format("Invalid row index value %s", rowIndex));
            return null;
        }
        return lstValue;
    }

    public String getNoRecord() {
        if (lblNoRecord.isDisplayed())
            return lblNoRecord.getText();
        return "";
    }

    public void clickDownload() {
        btnDownload.click();
    }

}
