package backoffice.pages.bo.accountmanagement;

import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;
import backoffice.controls.Table;
import backoffice.pages.bo._components.AppConfirmPopup;
import backoffice.pages.bo.home.HomePage;

import java.util.List;

public class AtlanticAccessManagementPage extends HomePage {
    TextBox txtPlayer  =TextBox.id("basic-url");
    Button btnAdd = Button.xpath("//button[contains(@class,'btn-add-player')]");
    private int totalColumn = 7;
    public int colLoginID = 2;
    public int colAction = 7;
    public Table tblAccount = Table.xpath("//table[@class='table table-bordered']",totalColumn);

    public void addPlayer(String player){
        txtPlayer.sendKeys(player);
        btnAdd.click();
    }

    public AppConfirmPopup removePlayer(String player){
        List<String> lstAccount = tblAccount.getColumn(colLoginID,false);
        for( int i = 0; i< lstAccount.size() ; i++){
            if (lstAccount.get(i).equalsIgnoreCase(player))
            {
                System.out.println(String.format("Click on Delete link of the corresponding login ID %s",player));
               tblAccount.getControlOfCell(1,colAction, i+1,null).click();
               return AppConfirmPopup.xpath("//app-atlantic-confirm-dialog");
            }
        }
        return null;
    }
    public boolean isAccountInList(String loginID){
        List<String> lstAccount = tblAccount.getColumn(colLoginID,false);
        for( String account : lstAccount){
            if (account.equalsIgnoreCase(loginID))
            {
                return true;
            }
        }
        System.out.println("The account "+ loginID + " not exist in the list");
        return false;
    }
}
