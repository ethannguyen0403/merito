package agentsite.pages.agentmanagement.depositwithdrawal;

import agentsite.controls.Table;

import java.util.ArrayList;
import java.util.List;

public class OldUIDepositWithdraw extends DepositWithdraw{
    int totalCol = 13;
    int colSubBalance = 9;
    int colTotalBalance = 8;
    int colAvailableBalance = 10;
    public Table tblWithdrawalDeposit = Table.xpath("//table[contains(@class,'ptable report table-responsive')]", totalCol);
    public boolean isTotalBalanceCalculatedCorrect() {
        List<ArrayList<String>> lstData = tblWithdrawalDeposit.getRowsWithoutHeader(20,false);
        for (int i = 0; i < lstData.size(); i++){
            double totalBalance = Double.parseDouble(lstData.get(i).get(colSubBalance)) + Double.parseDouble(lstData.get(i).get(colAvailableBalance));
            if (lstData.get(i).get(colTotalBalance).equals(String.format("%,.2f",totalBalance))){
                return true;
            }
        }
        return false;
    }
}
