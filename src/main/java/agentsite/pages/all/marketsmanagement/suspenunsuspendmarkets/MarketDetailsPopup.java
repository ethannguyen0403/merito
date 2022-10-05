package agentsite.pages.all.marketsmanagement.suspenunsuspendmarkets;

import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import agentsite.controls.Table;
import agentsite.pages.all.components.DialogPopup;

public class MarketDetailsPopup extends DialogPopup {
    public TextBox txtMarketName = TextBox.xpath("//div[@class='market-search']//input");
    public Label lblNote = Label.xpath("//span[@class='user-guide']");
    private int totalCol = 2;
    public int colMarketName = 1;
    public int colStatus = 2;
    public int colLastUpdateBy = 3;
    public int colLastUpdateDate = 4;
    public int colSelect = 5;
    public Table tblMarket = Table.xpath("//app-suspend-market-detail//table[contains(@class,'table ptable market-table')]", totalCol);
    public CheckBox cbSelectAll = CheckBox.xpath("//th[@class='selected-col']//input");
    public Label lblCompetitionName = Label.xpath("//div[@class='competition']/span[1]");
    public Label lblEventName = Label.xpath("//div[@class='even']/span[1]");
    public Label lblCompetitionValue = Label.xpath("//div[@class='competition']/span[2]");
    public Label lblEventNameValue = Label.xpath("//div[@class='even']/span[2]");
    public Button btnSuspend = Button.xpath("//div[@class='even']/following::div[1]/button[1]");
    public Button btnUnSuspend = Button.xpath("//div[@class='even']/following::div[1]/button[2]");

    public void suspendMarket (String marketName){
        String chbXpath = tblMarket.getControlxPathBasedValueOfDifferentColumnOnRow(
                marketName,1,colMarketName,1,"div[1]/span[1]",colSelect,"input",false,false);
        CheckBox cbCheckBox = CheckBox.xpath(chbXpath);
        cbCheckBox.click();
        btnSuspend.click();
    }

    public boolean verifymarketInfo(String marketName, String status, String lastUpdateBy, String lastUpdateTime){
        String marketStatus =  tblMarket.getControlBasedValueOfDifferentColumnOnRow(marketName,1,colMarketName,1,"div[1]/span[1]",colStatus,null,false,false).getText();
        String actualLastUpdateBy =  tblMarket.getControlBasedValueOfDifferentColumnOnRow(marketName,1,colMarketName,1,"div[1]/span[1]",colLastUpdateBy,null,false,false).getText();
        String actualLastUpdateTime =  tblMarket.getControlBasedValueOfDifferentColumnOnRow(marketName,1,colMarketName,1,"div[1]/span[1]",colLastUpdateDate,null,false,false).getText();
        if(!status.equals(marketStatus))
        {
            System.out.println("FAILED! Status of market is incorrect, actual is "+ marketStatus +" but expected is "+ status);
            return false;
        }
        if(!lastUpdateBy.isEmpty()){
            if(!actualLastUpdateBy.equals(lastUpdateBy))
            {
                System.out.println("FAILED! Last Update By of market is incorrect, actual is "+ actualLastUpdateBy +" but expected is "+ lastUpdateBy);
                return false;
            }
        }
        if(!lastUpdateTime.isEmpty()){
            if(!actualLastUpdateTime.contains(lastUpdateTime))
            {
                System.out.println("FAILED! Last Update Time of market is incorrect, actual is "+ actualLastUpdateTime +" but expected is "+ lastUpdateBy);
                return false;
            }
        }
        return true;
    }

}
