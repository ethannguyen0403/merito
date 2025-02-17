package membersite.pages.components.mybet;

import com.paltech.element.common.*;
import common.MemberConstants;
import controls.DateTimePicker;
import controls.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewViewMyBetsContainer extends MyBetsContainer {
    public Label lblOrderType = Label.xpath("//div[@class='row'][2]//select[contains(@class,'select-type-order')]//preceding::label[1]");
    public TextBox lblStartDate = TextBox.xpath(String.format("//label[text()='%s']", MemberConstants.MyBetsPage.START_DATE));
    public TextBox lblEndDate = TextBox.xpath(String.format("//label[text()='%s']", MemberConstants.MyBetsPage.END_DATE));
    public Label lblProduct = Label.xpath("//div[@class='container-title row']//div[contains(@class,'title')]");
    public Label lblNote = Label.xpath("//div[@class='container-title row']/div/span");
    public DropDownBox ddbProduct = DropDownBox.xpath("//div[@class='row'][1]//select[contains(@class,'select-type-order')]");
    public DropDownBox ddbOrderType = DropDownBox.xpath("//div[@class='row'][2]//select[contains(@class,'select-type-order')]");
    public Button btnLoadReport = Button.xpath("//button[contains(@class,'form-control btn btn-warning btn-report-search')]");
    public int colStatus = 9;
    public int colMarketName = 1;
    public int colBetID = 2;
    public int colEventID = 3;
    public int colSelection = 4;
    public int colType = 5;
    public int colOdd = 6;
    public int colStake = 7;
    public int colProfitLoss = 8;
    public int colPlaceDate = 10;
    public int colIPAddress = 11;
    public Link lnkPagination = Link.xpath("//ul[@class='pagination']//li");
    public Label lblNoRecord = Label.xpath("//table[@class='table table-sm']/tbody/tr[1]/td[@class='text-center']");
    public TextBox txtStartDate = TextBox.xpath(String.format("//label[text()='%s']/following::input[1]", MemberConstants.MyBetsPage.START_DATE));
    public DateTimePicker dtpStartDate = DateTimePicker.xpath(txtStartDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    public TextBox txtEndDate = TextBox.xpath(String.format("//label[text()='%s']/following::input[1]", MemberConstants.MyBetsPage.END_DATE));
    public DateTimePicker dtpEndDate = DateTimePicker.xpath(txtEndDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    private int colTotal = 12;
    public Table tblReport = Table.xpath("//table[@class='table table-sm']", colTotal);
    private Button btnDownload = Button.xpath("//em[contains(@class,'fas fa-download ico-export')]");
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
            if (status.equalsIgnoreCase("Settled")) {
                if (!sts.equalsIgnoreCase("Won")) {
                    if (!sts.equalsIgnoreCase("Lost")) {
                        System.out.println(String.format("ERROR! Expected status is %s but found %s", status, sts));
                        return false;
                    }

                }
            } else {
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

    public void clickDownload() {
        btnDownload.click();
    }
}
