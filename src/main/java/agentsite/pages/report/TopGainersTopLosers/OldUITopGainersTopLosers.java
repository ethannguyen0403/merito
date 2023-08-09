package agentsite.pages.report.TopGainersTopLosers;

import agentsite.controls.Table;
import agentsite.ultils.report.TopGainerLoserUtils;
import common.AGConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OldUITopGainersTopLosers extends TopGainersTopLosers{
    int colUsernameInWinLossLSimple = 1;
    int colNicknameInWinLossLSimple = 2;
    int colWinLossInWinLossLSimple = 4;
    int colTaxInWinLossLSimple = 5;
    int colUsercode = 0;
    int colWinLoss = 3;
    int colTax = 4;
    public Table tblTopGainers = Table.xpath("(//table[contains(@class,'table-sm')])[1]", 7);
    public Table tblTopLoser = Table.xpath("(//table[contains(@class,'table-sm')])[2]", 7);
    public Table tblBigStake = Table.xpath("(//table[contains(@class,'table-sm')])[3]", 7);
    public boolean isTopGainersHeaderTable(){
        if (tblTopGainers.getHeaderNameOfRows().equals(AGConstant.Report.TopGainersTopLosers.AGENT_SAD_TABLE_TOP_GAINERS_HEADER_OLDUI)){
            System.out.println("Display Top Gainers Header Table Old UI correct");
            return true;
        }
        return false;
    }

    @Override
    public boolean isTopLosersHeaderTable() {
        if (tblTopLoser.getHeaderNameOfRows().equals(AGConstant.Report.TopGainersTopLosers.AGENT_SAD_TABLE_TOP_LOSERS_HEADER_OLDUI)){
            System.out.println("Display Top Losers Header Table Old UI correct");
            return true;
        }
        return false;
    }

    @Override
    public boolean isBigStakeHeaderTable() {
        if (tblBigStake.getHeaderNameOfRows().equals(AGConstant.Report.TopGainersTopLosers.TABLE_BIG_STAKE_HEADER_OLDUI)){
            System.out.println("Display Top Losers Header Table Old UI correct");
            return true;
        }
        return false;
    }

    @Override
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
    public boolean isCheckUserDisplayInBigStakeTableCorrect(String bigStake) {
        List<String> lstStake = new ArrayList<>();
        if (tblBigStake.getColumn(1,false).get(0).equals("No records found.")){
            System.out.println("No records found.");
            return true;
        } else {
            lstStake = tblBigStake.getColumn(3,20,true);
            for (int i = 0; i < lstStake.size();i++){
                if (Double.parseDouble(lstStake.get(i).split("\n")[1]) < Double.parseDouble(bigStake)){
                    System.out.println("FAILED! Data of Big Stake displayed Incorrect");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isCheckUserDisplayInTopLosersTableCorrect(List<ArrayList<String>> losers) {
        List<ArrayList<String>> lstTopLoser = tblTopGainers.getRowsWithoutHeader(20,false);
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
            System.out.println("There are not Top Gainers");
            return true;
        }
        if (tblTopGainers.getColumn(1,false).get(0).equals("No records found.")){
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
            System.out.println("Data of Top Gainers Table Display correct");
            return true;
        }
        return false;
    }


}
