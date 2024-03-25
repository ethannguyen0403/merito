package backoffice.pages.bo.settlement;

import backoffice.controls.Table;
import backoffice.pages.bo._components.LeftMenu;
import com.paltech.element.common.*;

import java.util.ArrayList;
import java.util.List;

public class WagerResettlementPage extends LeftMenu {
    public Label lblPageTitle = Label.id("bo-page-title");
    public DropDownBox ddbBetType = DropDownBox.xpath("//app-wager-resettlement//select[@name='bet-types']");
    public TextBox txtWagerId = TextBox.xpath("//app-wager-resettlement//input[@name='wager-id']");
    public DropDownBox ddbProduct = DropDownBox.xpath("//app-wager-resettlement//select[@name='products']");
    public Button btnSubmit = Button.xpath("//app-wager-resettlement//button[@name='submit']");
    public Button btnCheckExteraal = Button.xpath("//app-wager-resettlement//button[@name='check-external']");
    public TextBox txtMarketID = TextBox.xpath("//app-wager-resettlement//select[@name='market-id']");
    public Button btnQueue = Button.xpath("//app-wager-resettlement//button[@name='queue']");
    public RadioButton rbByWager = RadioButton.xpath("//app-wager-market-resettlement//input[@name='market-filter']/following-sibling::span");
    public RadioButton rbByMarket = RadioButton.xpath("//app-wager-market-resettlement//input[@name='wager-filter']/following-sibling::span");
    public Label lblInfo = Label.xpath("//div[contains(@class,'container-market-info')]");
    public int colWagerID = 1;
    public int colFERessult = 12;
    public int colBetfairResult = 14;
    public int colAction = 16;
    private int totalColumn = 16;
    public Table tblWagerResettlement = Table.xpath("//table[@class='table']", totalColumn);

    public void selectSettleBy(SettleBy type) {
        switch (type) {
            case WAGER:
                rbByWager.click();
                return;
            case MARKET:
                rbByMarket.click();
                return;

        }
    }

    public void searchByWager(String betType, String wagerId, String product) {
        selectSettleBy(SettleBy.WAGER);
        if (!betType.isEmpty())
            ddbBetType.selectByVisibleText(betType);
        if (!wagerId.isEmpty())
            txtWagerId.sendKeys(wagerId);
        if (!product.isEmpty())
            ddbProduct.selectByVisibleText(product);
        btnSubmit.click();
        waitSpinIcon();
    }

    public void searchByMarket(String marketID) {
        selectSettleBy(SettleBy.MARKET);

        if (!marketID.isEmpty())
            txtMarketID.sendKeys(marketID);
        btnQueue.click();
    }

    public boolean isResettleButtonDisplay(String wagerID) {
        List<ArrayList<String>> lstWagerInfo = tblWagerResettlement.getRowsWithoutHeader(1, false);
        for (int i = 0; i < lstWagerInfo.size(); i++) {
            if (lstWagerInfo.get(i).get(colWagerID - 1).equals(wagerID)) {
                String fairExchangeResult = lstWagerInfo.get(i).get(colFERessult - 1).trim();
                String betfairResult = lstWagerInfo.get(i).get(colBetfairResult - 1).trim();
               /* if(betfairResult.contains("N/A")){
                    return (tblWagerResettlement.getControlOfCell(1,colAction, i+1,"input[@type='button']").isDisplayed()==false);
                }*/
                if (!fairExchangeResult.equals(betfairResult)) {
                    return (tblWagerResettlement.getControlOfCell(1, colAction, i + 1, "input[@type='button']").isDisplayed() == true);
                }
            }
        }
        System.out.println(String.format("The wager id %s not display in the table", wagerID));
        return true;
    }

    public enum SettleBy {WAGER, MARKET}

    public enum BetType {NORMAL, FOLLOW, HEDGING, SMALL}

}
