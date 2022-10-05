package backoffice.pages.bo.temp;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import backoffice.controls.bo.ATable;
import backoffice.pages.bo.home.HomePage;

import java.util.List;

public class PositionTakingConfigurationPage extends HomePage {
    public TextBox txtUserName = TextBox.xpath("//input[@placeholder='Username / Login ID']");
    public Button btnSearch = Button.xpath("//button[contains(@class,'btn-sm btn-core')]");
    private int totalColumn= 10;
    public int colUserName = 2;
    public int colLoginID = 3;

    public ATable tblAccountPTInfo = backoffice.controls.bo.ATable.xpath("//div[@class='custom-table']",totalColumn);
    public Label lblNote = Label.xpath("//div[contains(@class,'info')]/label");

    public void search(String username){
        txtUserName.sendKeys(username);
        btnSearch.click();
    }

    public boolean isAccountExist(String usercode){
        List<String> lstAccount = tblAccountPTInfo.getColumn(colUserName,false);
        for(int i = 0; i<lstAccount.size(); i++){
            if(lstAccount.get(i).equalsIgnoreCase(usercode)){
                System.out.println(String.format("The Account %s is displayed in the list",usercode));
                return true;
            }
        }
        System.out.println(String.format("The Account %s is NOT displayed in the list",usercode));
        return false;
    }


}
