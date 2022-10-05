package backoffice.pages.bo.system;

import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.system.productmaintenance.BetFairAccountChangeLogPopup;

import java.util.List;

public class BetFairAccountInfoPage extends HomePage {
    public int colAccountName = 2;
    public int colEXAvailableBalance = 4;
    public int colEXExposureLimit = 5;
    public int colEXCurrentExposure = 6;
    public int colExchangeChangeLog =7;
    public int colEGAvailableBalance = 8;
    public int colEGExposureLimit = 9;
    public int colEGCurrentExposure = 10;
    public int colEGChangeLog = 11;
    public Table tblBFAccount = Table.xpath("//table[contains(@class,'table-sm')]",11);

    public BetFairAccountChangeLogPopup openViewLog(String bfAccount, String product){
        List<String> lstAccount = tblBFAccount.getColumn(colAccountName,false);
        for(int i= 0;i<lstAccount.size();i++){
            if(lstAccount.get(i).equalsIgnoreCase(bfAccount)){
                switch (product){
                    case "EXCHANGE":
                        tblBFAccount.getControlOfCell(1,colExchangeChangeLog,i+1,"a").click();
                        break;
                    default:
                        tblBFAccount.getControlOfCell(1,colEGChangeLog,i+1,"a").click();
                        break;
                }
                return new BetFairAccountChangeLogPopup();
            }
        }
        return null;
    }

}
