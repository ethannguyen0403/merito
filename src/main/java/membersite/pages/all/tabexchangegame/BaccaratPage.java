package membersite.pages.all.tabexchangegame;


import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import membersite.controls.Table;
import membersite.pages.all.tabexchangegame.controls.BetSlipControl;
import membersite.pages.all.tabexchangegame.controls.MyBetControl;

import java.util.List;

public class BaccaratPage extends GamePage {
    public Table tbtOdds = Table.xpath("//app-game-odds//table[contains(@class,'tb-game-odds')]",8);
    public Label lblMyBetsTab = Label.xpath("//app-bet-slip//ul[contains(@class,'nav-tabs ')]/li[2]");
    public BetSlipControl betSlipControl = BetSlipControl.xpath("//app-bet-slip");
    public MyBetControl myBetControl = MyBetControl.xpath("//div[@class='open-bets']");
    public int backColumn = 4;
    public int laycolumn = 6;
    public int selectionCol = 1;

    public boolean clickOdd(String selection,boolean isBack) {
        List<String> lstSelection = tbtOdds.getColumn(selectionCol, false);
        Link lnk;
        for (int i = 0; i < lstSelection.size(); i++) {
            if (lstSelection.get(i).equalsIgnoreCase(selection)) {
                if (isBack) {
                    lnk = (Link) tbtOdds.getControlOfCell(1, backColumn, i + 1, "div[contains(@class,'price')]");
                } else {
                    lnk = (Link) tbtOdds.getControlOfCell(1, laycolumn, i + 1, "div[contains(@class,'price')]");
                }
                try{
                    waitUntilGameAvailable();
                    lnk.click();
                }catch (NullPointerException ex)
                {
                    System.out.println("--------DEBUG! Try click odd again-------");
                    waitUntilGameAvailable();
                    lnk.click();
                }
                return true;

            }
        }
            return false;
        }

    public void placebet(String selection, boolean isBack,String odds, String stake){
       addOddToBetSlip(selection,isBack,odds,stake);
       betSlipControl.btnPlaceBet.click();
        if(betSlipControl.btnConfirm.isDisplayed(2)){
            betSlipControl.btnConfirm.click();
        }
    }

    public void addOddToBetSlip(String selection, boolean isBack,String odds, String stake){
        if (!clickOdd(selection, isBack)) {
            System.out.println("DEBUG! Cannot click on Odds!");
        }
        betSlipControl.inputdata(isBack,odds,stake);
    }

    public String getUmatchedBetId(){
        MyBetControl myBetControl1= activeMyBet();
        return myBetControl1.unmatchedBetControl.getBetId();
    }

    public MyBetControl activeMyBet(){
        lblMyBetsTab.click();
        return MyBetControl.xpath("//div[@class='open-bets']");
    }

}
