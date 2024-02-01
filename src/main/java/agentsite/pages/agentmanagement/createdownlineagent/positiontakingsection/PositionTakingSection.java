package agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.PRODUCT_NAME_TO_CODE;

public class PositionTakingSection {
    protected int totalColumnExchange = 13;
    protected int totalColumnExchangeGames = 8;
    protected int totalOther = 2;
    protected String positionTakingTableXpath = "//div[@id='%s-position-taking']//table";
    protected String ptAllCheckboxXpath = "//div[@id='%s-position-taking']//td[contains(@class,'ptCheckAll')]//input";

    protected String mapProductNameToCode(String productName) {
        return PRODUCT_NAME_TO_CODE.get(productName);
    }
    public String getPositionTakingSectionTitle(String product) {return ""; }

    public void verifyUIDisplayCorrect(String product) {
    }
}
