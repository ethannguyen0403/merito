package agentsite.pages.report.winlosssimple;

import agentsite.controls.Table;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.Report.*;
import static common.AGConstant.Report.LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS_WITHOUT_DOT;
import static common.AGConstant.Report.WinLossSimple.TABLE_HEADER_NEWUI;

public class NewUIWinLossSimple extends WinLossSimple{
    int totalCol = 7;
    int colWinLoss = 5;
    public Table tblSMA = Table.xpath("(//table[contains(@class, 'backlayTable')])[1]", totalCol);
    public List<ArrayList<String>> getListWinnerInfor(){
        List<ArrayList<String>> lstData = tblSMA.getRowsWithoutHeader(20,true);
        List<ArrayList<String>> lstWin = new ArrayList<>();
        for (int i = 0; i < (lstData.size() - 1); i++){
            if (Double.parseDouble(lstData.get(i).get(colWinLoss)) > 0.00){
                lstWin.add(lstData.get(i));
            }
        }
        return lstWin;
    }
    public List<ArrayList<String>> getListLoserInfor(){
        List<ArrayList<String>> lstData = tblSMA.getRowsWithoutHeader(20,true);
        List<ArrayList<String>> lstLosers = new ArrayList<>();
        for (int i = 0; i < (lstData.size() - 1); i++){
            if (Double.parseDouble(lstData.get(i).get(colWinLoss)) < 0.00){
                lstLosers.add(lstData.get(i));
            }
        }
        return lstLosers;
    }
    public void verifyUIDisplaysCorrect(){
        Assert.assertEquals(btnToday.getText(), BTN_TODAY, "Failed! Today button is incorrect");
        Assert.assertEquals(btnYesterday.getText(), BTN_YESTERDAY, "Failed! Yesterday button is incorrect");
        Assert.assertEquals(btnLastWeek.getText(), LAST_WEEK, "Failed! Last Week button is incorrect");
        Assert.assertEquals(btnSubmit.getText(), BTN_SUBMIT, "Failed! Submit button is incorrect");
        Assert.assertEquals(lblYouCanSeeReportData.getText(), LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS_WITHOUT_DOT, "Failed! Submit button is incorrect");
        Assert.assertEquals(tblSMA.getColumnNamesOfTable(), TABLE_HEADER_NEWUI, "FAILED! Header title is incorrect");
    }
}
