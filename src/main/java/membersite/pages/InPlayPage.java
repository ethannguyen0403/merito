package membersite.pages;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import membersite.objects.sat.Event;
import org.openqa.selenium.By;

import java.util.Random;

import static common.MemberConstants.MATCH_ODDS_TITLE;

public class InPlayPage extends HomePage {
    private String eventContainerListXpath = "//div[contains(@class, 'vevent meto-bd-white')]//div[@class='market-container']";
    private String lblEventStartTimeXpath = "//div[contains(@class,'item-child inplay-it')]";
    private String lblHomeNameXpath = "//div[contains(@class,'home-team-name')]";
    private String lblAwayNameXpath = "//div[contains(@class,'away-team-name')]";

    public InPlayPage(String types) {
        super(types);
    }

    public Event getEventRandom(boolean isInPlay){
        int eventListSize = new BaseElement(By.xpath(eventContainerListXpath)).getWebElements().size();
        int startIndex = 1;
        String xpathEvent;
        Label iconInPlay;
        Link lnkEventName;
        String homeName;
        String awayName;
        String eventName;
        String eventStartTime;
        Random rand = new Random();
        while(true){
            xpathEvent = String.format("(%s)[%s]", eventContainerListXpath, 1 + rand.nextInt((eventListSize - 1) + 1));
            lnkEventName = Link.xpath(xpathEvent);
            if (!lnkEventName.isPresent(3)) {
                return null;
            }
            iconInPlay = Label.xpath(String.format("%s%s", xpathEvent, lblEventStartTimeXpath));
            boolean isInplay = iconInPlay.getText().equalsIgnoreCase("In-Play");
            if (isInplay == isInPlay) {
                eventStartTime = Label.xpath(String.format("%s%s", xpathEvent, lblEventStartTimeXpath)).getText().trim();
                homeName = Label.xpath(String.format("%s%s", xpathEvent, lblHomeNameXpath)).getText().trim();
                awayName = Label.xpath(String.format("%s%s", xpathEvent, lblAwayNameXpath)).getText().trim();
                eventName = String.format("%s v %s", homeName, awayName);
                return new Event.Builder()
                        .eventName(eventName)
                        .lnkEvent(lnkEventName)
                        .marketName(MATCH_ODDS_TITLE)
                        .isSuspend(false)
                        .inPlay(isInplay)
                        .startTime(eventStartTime)
                        .build();
            }
            if (startIndex > eventListSize)
                return null;
            startIndex++;
        }
    }
}
