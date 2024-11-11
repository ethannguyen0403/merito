package membersite.pages.components.eventcontainer;

import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import membersite.objects.sat.Event;
import java.util.Random;

import static common.MemberConstants.MATCH_ODDS_TITLE;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class FairenterEventContainerControl extends EventContainerControl {
    private String llblSuspendXPath = String.format("//div[contains(@class,'status-overlay')]");
    private String lblListEventXPath = "//div[@class='coupon-container']//div[@class='market-container ']";
    private String lblEventStartTimeXpath = "//div[contains(@class,'start-time')]";
    private String lblTeamNameXpath = "//span[contains(@class,'home-team')]";


    // Get event has Match Odds market by default
    public Event getEventRandom(boolean isInplay, boolean isSuspend) {
        Label lblEvents = Label.xpath(lblListEventXPath);
        int lstTotal = lblEvents.getWebElements().size();
        int j = 1;
        String xpathEvent;
        Label lblSuspend;
        Label iconInPlay;
        Link lnkEventName;
        String eventName;
        Random rand = new Random();
        while (true) {
            xpathEvent = String.format("(%s)[%s]", lblListEventXPath, 1 + rand.nextInt((lstTotal - 1) + 1));
            lnkEventName = Link.xpath(xpathEvent);
            if (!lnkEventName.isPresent(2)) {
                return null;
            }
            lblSuspend = Label.xpath(String.format("%s%s", xpathEvent, llblSuspendXPath));
            iconInPlay = Label.xpath(String.format("%s%s", xpathEvent, lblEventStartTimeXpath));
            boolean _isInplay = iconInPlay.getText().equalsIgnoreCase("In-Play");
            boolean _isSuspend = lblSuspend.getText().equalsIgnoreCase("Suspended");
            if (_isInplay == isInplay && _isSuspend == isSuspend) {
                eventName = Label.xpath(String.format("%s%s", xpathEvent, lblTeamNameXpath)).getText().trim();
                return new Event.Builder()
                        .eventName(eventName)
                        .lnkEvent(lnkEventName)
                        .marketName(MATCH_ODDS_TITLE)
                        .isSuspend(isSuspend)
                        .inPlay(isInplay)
                        .build();
            }
            if (j > lstTotal)
                return null;
            j++;
        }
    }

    public void clickOnRowofEventName(String event) {
        String eventNameXpath = "//span[@class='home-team' and text()='%s']";
        Label lblEvent = Label.xpath(String.format(eventNameXpath, event));
        lblEvent.click();
        lblEvent.isDisplayedShort(3);
    }

}
