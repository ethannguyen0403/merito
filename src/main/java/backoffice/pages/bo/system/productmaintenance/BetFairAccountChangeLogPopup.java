package backoffice.pages.bo.system.productmaintenance;

import backoffice.common.BOConstants;
import backoffice.controls.DateTimePicker;
import backoffice.controls.Table;
import com.paltech.element.common.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class BetFairAccountChangeLogPopup {
    public Popup popup = Popup.xpath("//app-betfair-account-info-dialog");
    public Label lblTitle = Label.xpath("//app-betfair-account-info-dialog//h4");
    public TextBox txtLogDate = TextBox.name("dp");
    public DateTimePicker dtpLogDate = DateTimePicker.xpath(txtLogDate, "//bs-days-calendar-view//bs-calendar-layout");
    public Table tblBalance = Table.xpath("//app-betfair-account-info-dialog//table[contains(@class,'table-sm')]", 3);
    public Button btnClose = Button.xpath("//button[contains( @class,'btn-sm btn-outline-secondary')]");
    public Label lblNoRecord = Label.xpath("//app-betfair-account-info-dialog//table[contains(@class,'table-sm')]//tbody/tr/td[@class='text-center']");
    int colExposure = 2;
    int colAvailableBalance = 3;

    public void verifyChangeLogBalanceAndExposure(List<String> lstBalanceAndExposure, String product, boolean isClosePopup) {
        List<ArrayList<String>> balanceInfo = tblBalance.getRowsWithoutHeader(1, false);
        List<String> lstExposure = tblBalance.getColumn(colExposure,10,false);
        List<String> lstAvailableBal= tblBalance.getColumn(colAvailableBalance,10,false);
        if (balanceInfo.get(0).get(0).equalsIgnoreCase(BOConstants.NO_RECORDS_FOUND)) {
            Assert.assertTrue(lblNoRecord.getText().equalsIgnoreCase(BOConstants.NO_RECORDS_FOUND), "FAILED! No record message is incorrect");
        } else {
            if(product.equalsIgnoreCase("exchange")) {
//                Assert.assertEquals(balanceInfo.get(0).get(1), lstBalanceAndExposure.get(2), "FAILED! Exchange Exposure display not correct");
                Assert.assertTrue(lstExposure.contains(lstBalanceAndExposure.get(2)), "FAILED! Exchange Exposure display not correct");
//                Assert.assertEquals(balanceInfo.get(0).get(2), lstBalanceAndExposure.get(0), "FAILED! Exchange Available Balance is incorrect");
                Assert.assertTrue(lstAvailableBal.contains(lstBalanceAndExposure.get(0)), "FAILED! Exchange Available Balance is incorrect");
            } else {
//                Assert.assertEquals(balanceInfo.get(0).get(1), String.format("%.2f", Double.parseDouble(lstBalanceAndExposure.get(2))), "FAILED! Exchange Game Exposure display not correct");
                Assert.assertTrue(lstExposure.contains(String.format("%.2f", Double.parseDouble(lstBalanceAndExposure.get(2)))), "FAILED! Exchange Game Exposure display not correct");
                Assert.assertTrue(lstAvailableBal.contains(lstBalanceAndExposure.get(0)), "FAILED! Exchange Game Available Balance is incorrect");
            }
        }
        if(isClosePopup) {
            btnClose.click();
        }
    }
}
