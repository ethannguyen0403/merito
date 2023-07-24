package agentsite.pages.agentmanagement.creditbalancelisting.creditinfoaccountcolumn;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.CreditBalanceListingPage;
import com.paltech.element.common.Label;

public class NewUICreditInfoAccountColumn extends CreditInfoAccountColumn {
    int colUsername = 2;
    int colCreditGiven = 17;
    int colMaxCredit = 18;
    int colMemberMaxCredit = 19;
    int colVailabaleBalance = 20;
    public NewUICreditInfoAccountColumn() {
    }
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
        int i = getRowIndexofUserName(userName);
        String creditGiven = tblAccountList.getControlOfCell(1, colCreditGiven, i, null).getText().trim().replaceAll(",", "");
        String maxCredit = tblAccountList.getControlOfCell(1, colMaxCredit, i, null).getText().trim().replaceAll(",", "");
        String memberMaxCredit = tblAccountList.getControlOfCell(1, colMemberMaxCredit, i, null).getText().trim().replaceAll(",", "");
        String availableBalance = tblAccountList.getControlOfCell(1, colVailabaleBalance, i, null).getText().trim().replaceAll(",", "");
        return new AccountInfo.Builder()
                .creditGiven(Double.parseDouble(creditGiven))
                .maxCredit(Double.parseDouble(maxCredit))
                .memberMaxCredit(Double.parseDouble(memberMaxCredit))
                .availableBalance(Double.parseDouble(availableBalance))
                .build();
    }

}
