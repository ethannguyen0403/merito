package agentsite.pages.agentmanagement.commissionlisting;

import agentsite.controls.Table;
import com.paltech.element.common.*;


public class CommissionSettingListing {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public int tblAgentCommissionTotalCol = 10;

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }
    public Table tblAgentCommission = Table.xpath("//div[@class='downline-listings']//table", tblAgentCommissionTotalCol);

    public void verifyTableHeaderDisplayCorrect(String product) {}

}
