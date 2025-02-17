package backoffice.pages.bo.system;

import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.system.productmaintenance.BetFairAccountChangeLogPopup;
import com.paltech.element.common.Icon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BetFairAccountInfoPage extends HomePage {
    public int colAccountName = 2;
    public int colEXAvailableBalance = 4;
    public int colEXExposureLimit = 5;
    public int colEXCurrentExposure = 6;
    public int colExchangeChangeLog = 7;
    public int colEGAvailableBalance = 8;
    public int colEGExposureLimit = 9;
    public int colEGCurrentExposure = 10;
    public int colEGChangeLog = 11;
    public Table tblBFAccount = Table.xpath("//table[contains(@class,'table-sm')]", 11);
    public Icon icLoading = Icon.xpath("//div[contains(@class,'ball-clip-rotate')]");
    public BetFairAccountChangeLogPopup openViewLog(String bfAccount, String product) {
        List<String> lstAccount = tblBFAccount.getColumn(colAccountName, false);
        for (int i = 0; i < lstAccount.size(); i++) {
            if (lstAccount.get(i).equalsIgnoreCase(bfAccount)) {
                switch (product) {
                    case "EXCHANGE":
                        tblBFAccount.getControlOfCell(1, colExchangeChangeLog, i + 1, "a").click();
                        break;
                    default:
                        tblBFAccount.getControlOfCell(1, colEGChangeLog, i + 1, "a").click();
                        break;
                }
                icLoading.waitForControlInvisible();
                BetFairAccountChangeLogPopup betFairAccountChangeLogPopup = new BetFairAccountChangeLogPopup();
                return betFairAccountChangeLogPopup;
            }
        }
        return null;
    }

    public List<String> getBalanceAndExposure(String product) {
        List<ArrayList<String>> bfInfo = tblBFAccount.getRowsWithoutHeader(1, false);
        if(product.equalsIgnoreCase("exchange")) {
            String exAvailableBalance = bfInfo.get(0).get(colEXAvailableBalance - 1);
            String exExposureLimit = bfInfo.get(0).get(colEXExposureLimit - 1);
            String exCurrentExposure = bfInfo.get(0).get(colEXCurrentExposure - 1);
            return Arrays.asList(exAvailableBalance, exExposureLimit, exCurrentExposure);
        } else {
            String egAvailableBalance = bfInfo.get(0).get(colEGAvailableBalance - 1);
            String egExposureLimit = bfInfo.get(0).get(colEGExposureLimit - 1);
            String egCurrentExposure = bfInfo.get(0).get(colEGCurrentExposure - 1);
            return Arrays.asList(egAvailableBalance, egExposureLimit, egCurrentExposure);
        }
    }

}
