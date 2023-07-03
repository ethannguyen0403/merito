package agentsite.pages.marketsmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.marketsmanagement.suspenunsuspendmarkets.MarketDetailsPopup;
import com.paltech.element.common.*;
import common.AGConstant;

public class SuspendUnsuspendMarketPage extends HomePage {
    public Label lblSport = Label.xpath("//label[@translate='sport']");
    public DropDownBox ddbSport = DropDownBox.xpath("//label[@translate='sport']//parent::td//following::td[1]/select");
    public Table tblEvent = Table.xpath("//table[contains(@class,'market-table')]", 3);
    public String tabDynamic = "//a[contains(text(),'%s')]";
    public Icon icRefresh = Icon.xpath("//span[contains(@class,'outstandinglock')]");
    public Tab tabOldEvents = Tab.xpath(String.format("//a[contains(text(),'%s')]", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(0)));
    public Tab tabToday = Tab.xpath(String.format("//a[contains(text(),'%s')]", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1)));
    public Tab tabTomorrow = Tab.xpath(String.format("//a[contains(text(),'%s')]", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(2)));
    public Tab tabFuture = Tab.xpath(String.format("//a[contains(text(),'%s')]", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(3)));
    public TextBox txtEventName = TextBox.xpath("//table[contains(@class,'market-table')]//input");
    public int colEvent = 2;
    public int colMarket = 3;
    public int colCompetitionName = 1;

    public SuspendUnsuspendMarketPage(String types) {
        super(types);
    }

    private void selectEventDayTab(String date) {
        switch (date) {
            case "TODAY":
                tabToday.click();
                return;
            case "TOMORROW":
                tabTomorrow.click();
                return;
            case "FUTURE":
                tabFuture.click();
                return;
            default:
                tabOldEvents.click();
                ;
                return;
        }
    }

    public void filterEvent(String sport, String date) {
        if (!sport.isEmpty())
            ddbSport.selectByVisibleText(sport);
        if (!date.isEmpty())
            selectEventDayTab(date.toUpperCase());
        waitingLoadingSpinner();
    }

    public MarketDetailsPopup openDetailMarket(String event) {
        int index = getEVentIndex(event);
        tblEvent.getControlBasedValueOfDifferentColumnOnRow(event, 1, colEvent, index, "div[1]/span[1]", colMarket, "span", false, false).click();
        MarketDetailsPopup popup = new MarketDetailsPopup();
        popup.lblCompetitionName.isDisplayed(2);
        return popup;
    }

    public void suspendMarket(String date, String sportName, String eventName, String marketName) {
        suspendUnsuspendMarket(date, sportName, eventName, marketName, true, true);

    }

    public void unSuspendMarket(String date, String sportName, String eventName, String marketName) {
        suspendUnsuspendMarket(date, sportName, eventName, marketName, false, true);

    }

    public void suspendUnsuspendMarket(String date, String sportName, String eventName, String marketName, boolean isSuspend, boolean isClosePopup) {
        filterEvent(sportName, date);
        MarketDetailsPopup popup = openDetailMarket(eventName);
        if (isSuspend) {
            popup.suspendUnsuspendMarket(marketName, true);
        } else
            popup.suspendUnsuspendMarket(marketName, false);

        if (isClosePopup)
            popup.close();
    }

    private int getEVentIndex(String eventName) {
        int i = 1;
        Label lblEvent;
        while (true) {
            lblEvent = Label.xpath(tblEvent.getxPathOfCell(1, colEvent, i, "div[1]/span[1]"));
            if (!lblEvent.isDisplayed()) {
                System.out.println("The event " + eventName + " does not display");
                return 0;
            }
            if (lblEvent.getText().trim().contains(eventName)) {
                System.out.println("Found The event " + eventName);
                return i;
            }
            i++;

        }

    }

    public boolean verifymarketInfo(String marketName, String status, String lastUpdateBy, String lastUpdateTime) {
        MarketDetailsPopup popup = new MarketDetailsPopup();
        return popup.verifymarketInfo(marketName, status, lastUpdateBy, lastUpdateTime);
    }
}
