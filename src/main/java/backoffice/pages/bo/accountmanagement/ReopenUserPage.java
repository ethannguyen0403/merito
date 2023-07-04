package backoffice.pages.bo.accountmanagement;

import backoffice.controls.bo.ATable;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;

import java.util.List;

public class ReopenUserPage extends HomePage {
    public TextBox txtLoginId = TextBox.name("username");
    public Button btnSearch = Button.xpath("search");
    public int colUserID = 1;
    public int colLoginId = 2;
    public int colUserCode = 3;
    public int colStatus = 4;
    public int colAction = 7;
    public ATable tblUser = ATable.xpath("//div[@class='custom-table']", 7);
    private int totalColumn = 7;

    public void search(String loginID) {
        txtLoginId.sendKeys(loginID);
        btnSearch.click();
    }

    public void activeCloseAccount(String loginID, int column) {
        List<String> lstAccount = tblUser.getColumn(column, false);
        for (int i = 0; i < lstAccount.size(); i++) {
            if (lstAccount.get(i).equalsIgnoreCase(loginID)) {
                System.out.println("Click on Acitve button of according account " + loginID);
                tblUser.getControlOfCell(1, colAction, i + 1, "button").click();
                return;
            }
        }
        System.out.println(String.format("The account %s not display in the table", loginID));
    }

    public boolean verifyStatusAccount(String loginID, int column, String status) {
        List<String> lstAccount = tblUser.getColumn(column, false);
        for (int i = 0; i < lstAccount.size(); i++) {
            if (lstAccount.get(i).equalsIgnoreCase(loginID)) {
                String actualStatus = tblUser.getControlOfCell(1, colStatus, i + 1, null).getText();
                return actualStatus.equalsIgnoreCase(status);
            }
        }
        System.out.println(String.format("The account %s not display in the table", loginID));
        return false;
    }

    public boolean verifyActionButtonIsDisable(String loginID, int column) {
        List<String> lstAccount = tblUser.getColumn(column, false);
        for (int i = 0; i < lstAccount.size(); i++) {
            if (lstAccount.get(i).equalsIgnoreCase(loginID)) {
                return tblUser.getControlOfCell(1, colAction, i + 1, "button").getAttribute("class").contains("disabled");
                //return tblUser.getControlOfCell(1,colAction,i+1,"button").isEnabled();
            }
        }
        System.out.println(String.format("The account %s not display in the table", loginID));
        return false;
    }
}

