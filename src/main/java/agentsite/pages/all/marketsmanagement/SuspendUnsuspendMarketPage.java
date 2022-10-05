package agentsite.pages.all.marketsmanagement;

import com.paltech.element.common.*;
import agentsite.common.AGConstant;
import agentsite.controls.Table;
import agentsite.pages.all.components.LeftMenu;
import agentsite.pages.all.marketsmanagement.suspenunsuspendmarkets.MarketDetailsPopup;

public class SuspendUnsuspendMarketPage extends LeftMenu {
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
    public int colCompetitionName =1;

    private void selectEventDayTab(String date){
        switch (date){
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
                tabOldEvents.click();;
                return;
        }
    }
    public void filterEvent(String sport, String date){
        if(!sport.isEmpty())
            ddbSport.selectByVisibleText(sport);
        if(!date.isEmpty())
            selectEventDayTab(date.toUpperCase());
        waitingLoadingSpinner();
    }

    public MarketDetailsPopup openDetailMarket(String event){
        tblEvent.getControlBasedValueOfDifferentColumnOnRow(event,1,colEvent,1,"div[1]/span[1]",colMarket,"span",false,false).click();
        MarketDetailsPopup popup = new MarketDetailsPopup();
        popup.lblCompetitionName.isDisplayed(2);
        return popup;
    }

}
