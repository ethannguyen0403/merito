package backoffice.pages.bo.accountmanagement;

import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;
import backoffice.controls.Table;
import backoffice.pages.bo._components.AppConfirmPopup;
import backoffice.pages.bo.home.HomePage;

import java.util.List;

public class APIPlayerPage extends HomePage {
    public TextBox txtLoginID = TextBox.xpath("//input[@placeholder='Username/Login ID']");
    public Button btnAdd = Button.xpath("//button[contains(@class,'btn-default')]");
    private int totalColumn= 6;
    public int colUserCode = 2;
    public int colLoginID = 3;
    public int colStatus = 4;
    public int colAction = 6;
    public Table tblAPIAccount = Table.xpath("//table[contains(@class,'table-sm')]",totalColumn);
    public AppConfirmPopup popup = AppConfirmPopup.xpath("//app-confirm-dialog");

    public void addAPIPlayer(String loginID){
        txtLoginID.sendKeys(loginID);
        btnAdd.click();
    }

    public AppConfirmPopup removeAPIPlayer(String loginID){
        List<String> lstLoginID = tblAPIAccount.getColumn(colLoginID,false);
        for(int i = 0; i<lstLoginID.size(); i++){
            if(lstLoginID.get(i).equalsIgnoreCase(loginID)){
                tblAPIAccount.getControlOfCell(1,colAction,i+1,"i[contains(@class,'fa-trash')]").click();
                break;
            }
        }
        return popup;
    }

    public boolean isPlayerExist(String loginId){
        List<String> lstAccount = tblAPIAccount.getColumn(colLoginID,false);
        for(int i = 0; i<lstAccount.size(); i++){
            if(lstAccount.get(i).equalsIgnoreCase(loginId)){
                System.out.println(String.format("The Account %s is displayed in the list",loginId));
                return true;
            }
        }
        System.out.println(String.format("The Account %s is NOT displayed in the list",loginId));
        return false;
    }


}
