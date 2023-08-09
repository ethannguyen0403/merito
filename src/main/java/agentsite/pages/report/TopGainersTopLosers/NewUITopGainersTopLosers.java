package agentsite.pages.report.TopGainersTopLosers;

import agentsite.controls.Table;
import com.paltech.element.common.Label;
import common.AGConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewUITopGainersTopLosers extends TopGainersTopLosers{
    int colUsernameInWinLossLSimple = 1;
    int colNicknameInWinLossLSimple = 2;
    int colWinLossInWinLossLSimple = 5;
    int colTaxInWinLossLSimple = 6;
    int colUsercode = 1;
    int colWinLoss = 4;
    int colTax = 5;
    String lblNoRecordsFoundXpath = "(//td[contains(text(),'No records found.')])[%s]";
    Label lblNoRecordsFoundTopGainers = Label.xpath(String.format(lblNoRecordsFoundXpath,"1"));
    Label lblNoRecordsFoundTopLosers = Label.xpath(String.format(lblNoRecordsFoundXpath,"2"));
    Label lblNoRecordsFoundBigStake = Label.xpath(String.format(lblNoRecordsFoundXpath,"3"));
    public Table tblTopGainers = Table.xpath("(//table[contains(@class,'table-sm')])[1]", 8);
    public Table tblTopLoser = Table.xpath("(//table[contains(@class,'table-sm')])[2]", 8);
    public Table tblBigStake = Table.xpath("(//table[contains(@class,'table-sm')])[3]", 8);
    public boolean isHeaderTableDisplayCorrect(String tableName){
        ArrayList<String> tblHeader = null;
        List<String> lstHeader = null;
        switch (tableName) {
            case "Top Gainers":
                tblHeader = tblTopGainers.getHeaderNameOfRows();
                lstHeader = AGConstant.Report.TopGainersTopLosers.AGENT_SMA_TABLE_TOP_GAINERS_HEADER_NEWUI;
            case "Top Losers":
                tblHeader = tblTopLoser.getHeaderNameOfRows();
                lstHeader = AGConstant.Report.TopGainersTopLosers.AGENT_SMA_TABLE_TOP_LOSERS_HEADER_NEWUI;
            case "Big Stake":
                tblHeader = tblBigStake.getHeaderNameOfRows();
                lstHeader = AGConstant.Report.TopGainersTopLosers.TABLE_BIG_STAKE_HEADER_NEWUI;
        }
        if (tblHeader.equals(lstHeader)){
            System.out.println("Display "+tableName+" Header Table Old UI correct");
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserDisplayInTableCorrect(String tableName, List<ArrayList<String>> lstWinLose) {
        List<ArrayList<String>> lstTopWinLoserTable = new ArrayList<>();
        List<ArrayList<String>> lstWinLossSimple = new ArrayList<>();
        if (lstWinLose.size() > 0){
            for (int i = 0; i < 1;i++){
                lstWinLossSimple.add(i, new ArrayList<>(Arrays.asList(
                        lstWinLose.get(i).get(colUsernameInWinLossLSimple)+" ("+lstWinLose.get(0).get(colNicknameInWinLossLSimple)+")",
                        lstWinLose.get(i).get(colWinLossInWinLossLSimple),
                        lstWinLose.get(i).get(colTaxInWinLossLSimple)
                )));
            }
        } else {
            System.out.println("There are not "+ tableName);
            return true;
        }

        List<ArrayList<String>> lstToptable = new ArrayList<>();
        Label lblNoRecordsFound = null;
        switch (tableName) {
            case "Top Gainers":
                lstToptable = tblTopGainers.getRowsWithoutHeader(20, false);
                lblNoRecordsFound = lblNoRecordsFoundTopGainers;
                break;
            case "Top Losers":
                lstToptable = tblTopLoser.getRowsWithoutHeader(20, false);
                lblNoRecordsFound = lblNoRecordsFoundTopLosers;
                break;
        }
        if (lblNoRecordsFound.getText().equals("No records found.")){
            System.out.println("No records found.");
            return true;
        } else {
            for (int i = 0; i < lstToptable.size(); i++){
                lstTopWinLoserTable.add(i, new ArrayList<>(Arrays.asList(
                        lstToptable.get(i).get(colUsercode),
                        lstToptable.get(i).get(colWinLoss),
                        lstToptable.get(i).get(colTax)
                )));
            }
        }
        if (lstTopWinLoserTable.containsAll(lstWinLossSimple)){
            System.out.println("Data of "+tableName+" Table Display correct");
            return true;
        }
        return false;
    }
}
