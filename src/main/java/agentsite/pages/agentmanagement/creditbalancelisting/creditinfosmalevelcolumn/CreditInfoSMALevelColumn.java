package agentsite.pages.agentmanagement.creditbalancelisting.creditinfosmalevelcolumn;

import agentsite.controls.Table;
import agentsite.objects.agent.account.AccountInfo;

public class CreditInfoSMALevelColumn {
    public Table tblAccountList = Table.xpath("//table[contains(@class,'report')]", 22);
    public AccountInfo getCreditInfoAccount(String userName) {
        return new AccountInfo.Builder()
                .build();
    }
}
