package membersite.pages.components.marketcontainer;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;

import java.util.ArrayList;
import java.util.List;

public class OldUIMarketContainerControl extends MarketContainerControl {
    private Label lblStartTime = Label.xpath("//div[@class='header-content default cricket']//span[contains(@class,'status ')]");
    Button btnRule = Button.xpath("//span[contains(@class,'market-rules-span')]");

    public Market getMarket(Event event, int selectionIndex, boolean isBack) {
        waitControlLoadCompletely(2);
        String selectionName = Label.xpath(String.format("(//span[text()=' %s ']/ancestor::div[@class='mini-market']//table//tbody[1]//tr[%s]//span)[1]",event.getMarketName(), selectionIndex)).getText();
        return getMarket(event, event.getMarketName(), selectionName, isBack, getOddsListLabel(event.getMarketName(),selectionIndex, isBack).get(0));
    }

    private Market getMarket(Event event, String marketName, String selectionName, boolean isBack, Label odds) {
        return new Market.Builder()
                .eventName(event.getEventName())
                .marketName(marketName)
                .selectionName(selectionName)
                .isBack(isBack)
                .btnOdds(odds)
                .build();
    }
    public void waitControlLoadCompletely(int second) {
        String title = lblStartTime.getText();
        String temp = "";
        for (int i = 0; i <= second; i++) {
            temp = lblStartTime.getText();
            if (!title.isEmpty() || !title.equals(temp))
                break;
            else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i = i + 1;
        }
    }

    public List<Label> getOddsListLabel(String marketName, int selectionIndex, boolean isBack) {
        List<Label> list = new ArrayList<>();
        String xPathOddsList = String.format("(//span[text()=' %s ']/ancestor::div[@class='mini-market']//table//tbody[1]//tr[%s]//td[@class='odds'])", marketName, selectionIndex);
        int countOddsLabel = Label.xpath(xPathOddsList).getWebElements().size();
        if (isBack) {
            for (int i = countOddsLabel / 2; i > 0; i--) {
                list.add(Label.xpath(String.format("%s[%d]//span[@class='price']",xPathOddsList, i)));
            }
        } else {
            for (int i = countOddsLabel / 2; i < countOddsLabel; i++) {
                list.add(Label.xpath(String.format("%s[%d]//span[@class='price']",xPathOddsList, i+1)));
            }
        }
        return list;
    }

    public enum Team {HOME, DRAW, AWAY}

    public enum Status {NA, IN_PLAY, COMING}

    public void clickRuleButton(){btnRule.click();}
}
