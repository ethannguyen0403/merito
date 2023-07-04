package backoffice.pages.bo.accountmanagement;

import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;

import java.util.List;

public class CryptoAccessManagementPage extends HomePage {
    public int colLoginID = 2;
    TextBox txtPlayer = TextBox.id("basic-url");
    Button btnAdd = Button.xpath("//button[contains(@class,'btn-add-player')]");
    private int totalColumn = 7;
    public Table tblAccount = Table.xpath("//table[@class='table table-bordered']", totalColumn);

    public void addPlayer(String player) {
        txtPlayer.sendKeys(player);
        btnAdd.click();
    }

    public boolean isAccountAdded(String loginID) {
        List<String> lstAccount = tblAccount.getColumn(colLoginID, false);
        for (String account : lstAccount) {
            if (account.equalsIgnoreCase(loginID)) {
                return true;
            }
        }
        System.out.println("The account " + loginID + "not exist in the list");
        return false;
    }

}
