package agentsite.pages.agentmanagement.commissionlisting;

import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;

import static agentsite.pages.agentmanagement.CommissionSettingListingPage.tblAgentCommission;

public class NewUIComissionSettingListing extends CommissionSettingListing {

    public void verifyTableHeaderDisplayCorrect(String product) {
        ArrayList<String> lstHeader = tblAgentCommission.getHeaderNameOfRows();
        if (product.equalsIgnoreCase("live dealer asian")) {
            Assert.assertEquals(lstHeader, AGConstant.AgencyManagement.CommissionSettingListing.TABLE_AGENT_HEADER_LIVE_DEALER_ASIAN_NEWUI);
        }
    }
}
