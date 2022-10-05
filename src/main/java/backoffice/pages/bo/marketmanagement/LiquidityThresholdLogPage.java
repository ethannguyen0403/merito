package backoffice.pages.bo.marketmanagement;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.marketmanagement.components.LiquidityThresholdLogPopup;

import java.util.List;

public class LiquidityThresholdLogPage extends HomePage {
    public TextBox txtUsername =TextBox.xpath("//input[@placeholder='Username / Login ID']");
    public Button btnSearch = Button.xpath("//app-liquidity-threshold-log//div[contains(@class,'search-container')]//button[@class='btn btn-sm btn-core']");
    private int totalColumn = 11;
    public int colLoginID = 3;
    public int colHistory = 11;
    public StaticTable tblLog = StaticTable.xpath("//app-liquidity-threshold-log//div[contains(@class,'table-wrapper')]","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
    public Label lblNoRecord = Label.xpath("//div[contains(@class,'no-record')]//div[contains(@class,'text-center')]");

    public void search(String username){
        btnSearch.isDisplayed();
        txtUsername.sendKeys(username);
        btnSearch.click();
        waitSpinIcon();
    }

    public LiquidityThresholdLogPopup openViewLog(String username){
        List<String> lstAccount = tblLog.getColumn(colLoginID, false);
        for(int i =0 ; i< lstAccount.size();i++){
            if(lstAccount.get(i).equals(username)){
                tblLog.getControlOfCell(1,colHistory, i+1,"i[contains(@class,'far fa-eye')]").click();
                LiquidityThresholdLogPopup popup = new LiquidityThresholdLogPopup();
                popup.tblLog.isDisplayed(1);
                return popup;
            }
        }
        System.out.println("Does not found the account "+ username);
        return null;
    }
}
