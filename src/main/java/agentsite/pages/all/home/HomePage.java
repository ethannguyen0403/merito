package agentsite.pages.all.home;

import agentsite.controls.Table;
import agentsite.pages.all.agentmanagement.SubUserListingPage;
import agentsite.pages.all.components.LeftMenu;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.SUB_USER_LISTING;

public class HomePage extends LeftMenu {
    private int totalCol = 2;
    public int colName = 1;
    public int colValue = 2;
    Table tblSMAInfo = Table.xpath("//table[@class='ptable report ng-scope']", totalCol);

    public AccountBalancePage navigateAccountBalance(String brandName) {
        AccountBalancePage page;
        if (brandName.equalsIgnoreCase("satsport")) {
            clickHomeIcon();
        } else {
            btnMyAccount.click();
            if (lblSubUserMenu.isDisplayed()) {
                lblSummary.click();
            }
        }
        waitingLoadingSpinner();
        return new AccountBalancePage();
    }

}
