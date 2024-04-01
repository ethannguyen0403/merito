package agentsite.pages.marketsmanagement.suspenunsuspendmarkets;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.*;
import org.openqa.selenium.Keys;

public class MarketDetailsPopup extends ConfirmPopup {
    public TextBox txtMarketName = TextBox.xpath("//div[@class='market-search']//input");
    public Label lblNote = Label.xpath("//span[@class='user-guide']");
    public int colMarketName = 1;
    public int colStatus = 2;
    public int colLastUpdateBy = 3;
    public int colLastUpdateDate = 4;
    public int colSelect = 5;
    public CheckBox cbSelectAll = CheckBox.xpath("//th[@class='selected-col']//input");
    public Label lblCompetitionName = Label.xpath("//div[@class='competition']/span[1]");
    public Label lblEventName = Label.xpath("//div[@class='even']/span[1]");
    public Label lblCompetitionValue = Label.xpath("//div[@class='competition']/span[2]");
    public Label lblEventNameValue = Label.xpath("//div[@class='even']/span[2]");
    public Button btnSuspend = Button.xpath("//div[@class='even']/following::div[1]/button[1]");
    public Button btnUnSuspend = Button.xpath("//div[@class='even']/following::div[1]/button[2]");
    private int totalCol = 2;
    public Table tblMarket = Table.xpath("//app-suspend-market-detail//table[contains(@class,'table ptable market-table')]", totalCol);
    private Icon iconLoadSpinner1 = Icon.xpath("//div[contains(@class, 'load-spinner')]");

    public void suspendUnsuspendMarket(String marketName, boolean isSuspend) {
        //Search the market name
        searchMarket(marketName);
        int index = getMarketIndex(marketName);
        String chbXpath = tblMarket.getxPathOfCell(1, colSelect, index, "input");
        CheckBox cbCheckBox = CheckBox.xpath(chbXpath);
        cbCheckBox.click();
        if (isSuspend)
            btnSuspend.click();
        else
            btnUnSuspend.click();
        iconLoadSpinner1.isDisplayed();
    }

    public boolean verifymarketInfo(String marketName, String status, String lastUpdateBy, String lastUpdateTime) {
        String marketStatus = tblMarket.getControlBasedValueOfDifferentColumnOnRow(marketName, 1, colMarketName, 1, "div[1]/span[1]", colStatus, null, false, false).getText();
        String actualLastUpdateBy = tblMarket.getControlBasedValueOfDifferentColumnOnRow(marketName, 1, colMarketName, 1, "div[1]/span[1]", colLastUpdateBy, null, false, false).getText();
        String actualLastUpdateTime = tblMarket.getControlBasedValueOfDifferentColumnOnRow(marketName, 1, colMarketName, 1, "div[1]/span[1]", colLastUpdateDate, null, false, false).getText();
        if (!status.equals(marketStatus)) {
            System.out.println("FAILED! Status of market is incorrect, actual is " + marketStatus + " but expected is " + status);
            return false;
        }
        if (!lastUpdateBy.isEmpty()) {
            if (!actualLastUpdateBy.equals(lastUpdateBy)) {
                System.out.println("FAILED! Last Update By of market is incorrect, actual is " + actualLastUpdateBy + " but expected is " + lastUpdateBy);
                return false;
            }
        }
        if (!lastUpdateTime.isEmpty()) {
            if (!actualLastUpdateTime.contains(lastUpdateTime)) {
                System.out.println("FAILED! Last Update Time of market is incorrect, actual is " + actualLastUpdateTime + " but expected is " + lastUpdateBy);
                return false;
            }
        }
        return true;
    }

    public void searchMarket(String marketName) {
        txtMarketName.isDisplayed();
        txtMarketName.sendKeys(marketName);
        txtMarketName.type(Keys.ENTER);
        HomePage.waitingLoadingSpinner();
    }

    private int getMarketIndex(String marketName) {
        int i = 1;
        Label lblMarket;
        while (true) {
            lblMarket = Label.xpath(tblMarket.getxPathOfCell(1, colMarketName, i, "div[1]/span[1]"));
            if (!lblMarket.isDisplayed()) {
                System.out.println("The event " + marketName + " does not display");
                return 0;
            }
            if (lblMarket.getText().trim().contains(marketName)) {
                System.out.println("Found The event " + marketName);
                return i;
            }
            i++;

        }

    }
}
