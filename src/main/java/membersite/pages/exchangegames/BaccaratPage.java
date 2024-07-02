package membersite.pages.exchangegames;

import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import controls.Table;
import membersite.pages.exchangegames.controls.BetSlipControl;
import membersite.pages.exchangegames.controls.MyBetControl;

import java.util.List;

public class BaccaratPage extends GamePage {
    public Table tbtOdds = Table.xpath("//app-game-odds//table[contains(@class,'tb-game-odds')]", 8);
    public Label lblMyBetsTab = Label.xpath("//app-bet-slip//ul[contains(@class,'nav-tabs ')]/li[2]//span");
    public BetSlipControl betSlipControl = BetSlipControl.xpath("//app-bet-slip");
    public MyBetControl myBetControl = MyBetControl.xpath("//div[@class='open-bets']");
    public int backColumn = 4;
    public int laycolumn = 6;
    public int selectionCol = 1;

    public BaccaratPage(String types) {
        super(types);
    }

    public boolean clickOdds(String selection, boolean isBack) {
        List<String> lstSelection = tbtOdds.getColumn(selectionCol, false);
        Link lnk;
        for (int i = 0; i < lstSelection.size(); i++) {
            if (lstSelection.get(i).equalsIgnoreCase(selection)) {
                if (isBack) {
                    lnk = (Link) tbtOdds.getControlOfCell(1, backColumn, i + 1, "div[contains(@class,'price')]");
                } else {
                    lnk = (Link) tbtOdds.getControlOfCell(1, laycolumn, i + 1, "div[contains(@class,'price')]");
                }
                try {
                    waitUntilGameAvailable();
                    lnk.click();
                } catch (NullPointerException ex) {
                    System.out.println("--------DEBUG! Try click odd again-------");
                    waitUntilGameAvailable();
                    lnk.click();
                }
                return true;

            }
        }
        return false;
    }

    public void placeBet(String selection, boolean isBack, String odds, String stake) {
        addOddsToBetSlip(selection, isBack, odds, stake);
        betSlipControl.btnPlaceBet.click();
        if (betSlipControl.btnConfirm.isPresent(3)) {
            betSlipControl.btnConfirm.click();
        }
    }

    public void addOddsToBetSlip(String selection, boolean isBack, String odds, String stake) {
        if (!clickOdds(selection, isBack)) {
            System.out.println("DEBUG! Cannot click on Odds!");
        }
        betSlipControl.inputdata(isBack, odds, stake);
    }

    public String isUnmatchedBetDisplayed() {
        MyBetControl myBetControl1 = activeMyBet();
        myBetControl1.unmatchedBetControl.isDisplayed(4);
        return myBetControl1.unmatchedBetControl.getBetUnmatch();
    }

    public MyBetControl activeMyBet() {
        if(!lblMyBetsTab.getAttribute("class").contains("nav-link active")) {
            lblMyBetsTab.click();
            return MyBetControl.xpath("//div[@class='open-bets']");
        } else {
            return MyBetControl.xpath("//div[@class='open-bets']");
        }
    }

}
