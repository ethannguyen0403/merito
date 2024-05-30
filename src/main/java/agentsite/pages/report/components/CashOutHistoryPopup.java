package agentsite.pages.report.components;

import agentsite.controls.Table;
import com.paltech.element.common.Label;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.Report.ProfitAndLoss.HEADER_CASH_OUT_HISTORY_DIALOG;

public class CashOutHistoryPopup {

    Table tblCashOut = Table.xpath("//app-cash-out-dialog//table", 3);
    int colRiskAmount = 1;
    int colCashOutAmount = 2;
    int colDateTime = 3;


    public CashOutHistoryPopup(){
        try {
            // wait for locator of popup is visible in viewport
            Thread.sleep(300);
        }catch (InterruptedException e){
        }
    }

    public void verifyCashOutDialogShowCorrect(List<ArrayList<String>> dataList, String memberName){
        Assert.assertEquals(tblCashOut.getHeaderNameOfRows(), HEADER_CASH_OUT_HISTORY_DIALOG, "FAILED! Header of Cash out history dialog is not correct");
        for(ArrayList<String> data: dataList){
            if(!data.get(1).equalsIgnoreCase(memberName)){
                //skip list that not contain target member
                continue;
            }
        double cashOutAmount = Double.valueOf(data.get(6))  + Double.valueOf(data.get(7));
            Assert.assertEquals(data.get(6), Label.xpath(tblCashOut.getxPathOfCell(1, colRiskAmount, 1, null)).getText(), "FAILED! Risk Amount value is not correct");
            Assert.assertEquals(String.format("%.2f", cashOutAmount), Label.xpath(tblCashOut.getxPathOfCell(1, colCashOutAmount, 1, null)).getText(), "FAILED! Cash Out Amount value is not correct");
        }
    }
}
