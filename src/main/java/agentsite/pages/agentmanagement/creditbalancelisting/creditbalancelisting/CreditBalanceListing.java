package agentsite.pages.agentmanagement.creditbalancelisting.creditbalancelisting;

import agentsite.controls.Table;
import agentsite.objects.agent.account.AccountInfo;
import com.paltech.element.common.Label;

public class CreditBalanceListing {
    private int colUsername = 2;
    public Table tblAccountList = Table.xpath("//table[contains(@class,'report')]", 22);
    public int getRowIndexofUserName(String username) {
        int i = 1;
        String xpath = tblAccountList.getxPathOfCell(1, colUsername, i, null);
        Label lblUserName = Label.xpath(xpath);
        while (true) {
            if (!lblUserName.isDisplayed()) {
                System.out.println("DEBUG! Username " + username + " does not exist in the table");
                return 0;
            }
            xpath = tblAccountList.getxPathOfCell(1, colUsername, i, null);
            lblUserName = Label.xpath(xpath);
            if (lblUserName.getText().trim().equals(username)) {
                return i;
            }
            i = i + 1;
        }
    }
    public AccountInfo getCreditInfoAccount(String userName) {
        return null;
    }
}
