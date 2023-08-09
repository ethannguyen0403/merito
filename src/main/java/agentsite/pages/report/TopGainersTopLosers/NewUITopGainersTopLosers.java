package agentsite.pages.report.TopGainersTopLosers;

import agentsite.controls.Table;
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
    public Table tblTopGainers = Table.xpath("(//table[contains(@class,'table-sm')])[1]", 8);
    public Table tblTopLoser = Table.xpath("(//table[contains(@class,'table-sm')])[2]", 8);
    public Table tblBigStake = Table.xpath("(//table[contains(@class,'table-sm')])[3]", 8);
    public boolean isTopGainersHeaderTable(){
        if (tblTopGainers.getHeaderNameOfRows().equals(AGConstant.Report.TopGainersTopLosers.AGENT_SMA_TABLE_TOP_GAINERS_HEADER_NEWUI)){
            System.out.println("Display Top Gainers Header Table New UI correct");
            return true;
        }
        return false;
    }

    @Override
    public boolean isTopLosersHeaderTable() {
        if (tblTopLoser.getHeaderNameOfRows().equals(AGConstant.Report.TopGainersTopLosers.AGENT_SMA_TABLE_TOP_LOSERS_HEADER_NEWUI)){
            System.out.println("Display Top Losers Header Table New UI correct");
            return true;
        }
        return false;
    }

    @Override
    public boolean isBigStakeHeaderTable() {
        if (tblBigStake.getHeaderNameOfRows().equals(AGConstant.Report.TopGainersTopLosers.TABLE_BIG_STAKE_HEADER_NEWUI)){
            System.out.println("Display Top Losers Header Table New UI correct");
            return true;
        }
        return false;
    }
    public boolean isCheckUserDisplayInTopGainersTableCorrect(List<ArrayList<String>> winner) {
        List<ArrayList<String>> lstTopGainer = tblTopGainers.getRowsWithoutHeader(20,false);
        List<ArrayList<String>> lstTopGainerCompare = new ArrayList<>();
        ArrayList<String> winnerCompare = new ArrayList<>();
        if (!(winner.size()>0)){
            for (int i = 0; i < winner.size();i++){
            winnerCompare.add(i, String.valueOf(new ArrayList<>(Arrays.asList(
                    winner.get(0).get(colUsernameInWinLossLSimple)+" ("+winner.get(0).get(colNicknameInWinLossLSimple)+")",
                    winner.get(0).get(colWinLossInWinLossLSimple),
                    winner.get(0).get(colTaxInWinLossLSimple)
            ))));
            }
        } else {
            System.out.println("There are not Top Gainers");
            return true;
        }
        if (tblTopGainers.getColumn(1,false).get(0).equals("No records found.")){
            System.out.println("No records found.");
            return true;
        } else {
            for (int i = 0; i < lstTopGainer.size(); i++){
                lstTopGainerCompare.add(i, new ArrayList<>(Arrays.asList(
                        lstTopGainer.get(i).get(colUsercode),
                        lstTopGainer.get(i).get(colWinLoss),
                        lstTopGainer.get(i).get(colTax)
                )));
            }
        }
        if (lstTopGainerCompare.contains(winnerCompare)){
            System.out.println("Data of Top Gainers Table Display correct");
            return true;
        }
        return false;
    }

    @Override
    public boolean isCheckUserDisplayInTopLosersTableCorrect(List<ArrayList<String>> losers) {
        List<ArrayList<String>> lstTopLoser = tblTopLoser.getRowsWithoutHeader(20,false);
        List<ArrayList<String>> lstTopLoserCompare = new ArrayList<>();
        ArrayList<String> loserCompare = new ArrayList<>();
        if (!(losers.size() == 0)){
            for (int i = 0; i < losers.size();i++){
                loserCompare.add(i, String.valueOf(new ArrayList<>(Arrays.asList(
                        losers.get(0).get(colUsernameInWinLossLSimple)+" ("+losers.get(0).get(colNicknameInWinLossLSimple)+")",
                        losers.get(0).get(colWinLossInWinLossLSimple),
                        losers.get(0).get(colTaxInWinLossLSimple)
                ))));
            }
        } else {
            System.out.println("There are not Top Losers");
            return true;
        }
        if (tblTopLoser.getColumn(1,false).get(0).equals("No records found.")){
            System.out.println("No records found.");
            return true;
        } else {
            for (int i = 0; i < lstTopLoser.size(); i++){
                lstTopLoserCompare.add(i, new ArrayList<>(Arrays.asList(
                        lstTopLoser.get(i).get(colUsercode),
                        lstTopLoser.get(i).get(colWinLoss),
                        lstTopLoser.get(i).get(colTax)
                )));
            }
        }
        if (lstTopLoserCompare.contains(loserCompare)){
            System.out.println("Data of Top Losers Table Display correct");
            return true;
        }
        return false;
    }
}
