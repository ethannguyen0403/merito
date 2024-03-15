package agentsite.pages.report.winlosssimple;

import agentsite.controls.Table;

import java.util.ArrayList;
import java.util.List;

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
}
