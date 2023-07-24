package agentsite.pages.agentmanagement.creditbalancelisting.creditinfosmalevel;

import agentsite.controls.Table;
import agentsite.objects.agent.account.AccountInfo;

public class CreditInfoSMALevel {
    public Table tblAccountList = Table.xpath("//table[contains(@class,'report')]", 22);
    public AccountInfo getCreditInfoAccount(String userName) {
        return new AccountInfo.Builder()
                .build();
    }
}
