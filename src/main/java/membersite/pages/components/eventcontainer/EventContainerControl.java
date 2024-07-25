package membersite.pages.components.eventcontainer;

import membersite.objects.sat.Event;

import java.util.List;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class EventContainerControl {

    public String getSportHeader() {
        return "";
    }

    public List<String> getAllEventHighlight() {
        return null;
    }

    public Event setEventLink(Event event) {
        return null;
    }

    public Event getEvent(boolean isSuspend, int limit, int eventIndex) {
        return getEvent(null, isSuspend, limit, eventIndex);
    }

    public Event getEvent(String inPlay, boolean isSuspend, int limit, int eventIndex) {
        boolean isInPlay = false;
        return getEvent(isInPlay, isSuspend, limit, eventIndex);
    }

    public Event getEvent(boolean isInplay, boolean isSuspend, int limit, int eventIndex) {
        return null;
    }

    // get Event has Match Odds Market by default
    public Event getEventRandom(boolean isInplay, boolean isSuspend) {
        return null;
    }

    public Event getEventMatchOddsRandom(String sportId, String currency, boolean isInplay, boolean isSuspend) {
        return null;
    }

    public Event getEventGoalLineRandom(String sportId, String currency) {
        return null;
    }

    public Event getEventInningRunRandom(String sportId, String currency) {
        return null;
    }

    public Event getEventHandicapRandom(String sportId, String currency) {
        return null;
    }

    public boolean isOddsUnclickable(String eventName) {
        return false;
    }

    public void clickEvent(Event event) {
        event.getLinkEvent().click();
    }

    public void clickOnRowofEventName(String event) {

    }

//    public MarketPage clickOnRowofEventName(String eventName) {
//        return new MarketPage();
//    }

    private int getEventIndex(String eventName) {
        return 0;
    }

    public Event getEventInfo(String eventName) {
        return null;
    }

    /**
     * To get the event that has Wicket Fancy and and click on that event
     *
     * @return
     */
    public String getEventIDHasMarketData(String product) {
        return "";
    }

    public void clickOnRowofEventID(String eventID) {

    }

    public boolean isEventDisplay(String eventName){
        return false;
    }

}
