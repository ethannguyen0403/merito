package agentsite.pages.agentmanagement.creditbalancelisting.creditbalancelisting;

import agentsite.objects.agent.account.AccountInfo;

public class NewUICreditBalanceListing extends CreditBalanceListing {

    int colCreditGiven = 17;
    int colMaxCredit = 18;
    int colMemberMaxCredit = 19;
    int colVailabaleBalance = 20;

    public NewUICreditBalanceListing(String types) {
        super(types);
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
