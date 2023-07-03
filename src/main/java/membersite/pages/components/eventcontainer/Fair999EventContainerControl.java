package membersite.pages.components.eventcontainer;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import controls.Table;
import membersite.objects.sat.Event;
import membersite.utils.betplacement.FancyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class Fair999EventContainerControl extends EventContainerControl {
    //	private String _xpath = "//div[@class='container-event-info']";
    private Label lblSportHeader = Label.xpath("//div[contains(@class,'sport-header')]/h2");
    private String llblSuspendXPath = String.format("//div[contains(@class,'status-overlay')]");
    private String lblEventNameXPath = "//div[contains(@class,'info-event')]";
    private String lblListEventXPath = "//div[@class='container-event-info']//table[contains(@class,'table-odds')]/tbody/tr";
    private Label lblNoEvent = Label.xpath("//div[@class='text-center']");
    private Button btnOdds;
    private String lblEventStartTimeXpath = "//div[contains(@class,'item-child inplay-it')]";
    private String lblHomeNameXpath = "//span[contains(@class,'home-team-name')]";
    private String lblAwayNameXpath = "//div[contains(@class,'away-team-name')]";
    private String lblTotalMatchXpath = "//div[contains(@class,'total-matched')]";
    private String iconAddFavoritXPath = "//i[contains(@class,'far fa-star')]";
    private Table tblEvents = Table.xpath("//div[@class='container-event-info']//table[contains(@class,'table-odds')]", 1);
    private String lblInPlayXpath = "//div[@class='item-child inplay-it inplay-noscore' or @class='item-child inplay-it inplay-color']";

    public String getSportHeader() {
        return lblSportHeader.getText();
    }

    public List<String> getAllEventHighlight() {
        if (!lblNoEvent.isDisplayedShort(3)) {
            List<String> lstEvent = new ArrayList<>();
            Link lnkEventName = Link.xpath(lblEventNameXPath);
            int n = lnkEventName.getWebElements().size();
            String xpathEvent;
            String homeName;
            String awayName;
            String eventName;
            for (int i = 0; i < n; i++) {
                xpathEvent = String.format("(%s)[%s]", lblEventNameXPath, i + 1);
                homeName = Label.xpath(xpathEvent).getText().trim();
                awayName = Label.xpath(xpathEvent).getText().trim();
                eventName = String.format("%s v %s", homeName, awayName);
                lstEvent.add(eventName);
            }
            return lstEvent;
        }
        return null;
    }

    public Event setEventLink(Event event) {
        if (!lblNoEvent.isDisplayedShort(3)) {
            String xpathEvents = String.format(lblListEventXPath);
            int i = 1;
            int lstTotal = 100;
            Event newEvent = event;
            while (true) {
                String xpathEvent = String.format("(%s)[%s]", xpathEvents, i);
                Label lblEvent = Label.xpath(xpathEvent);
                if (!lblEvent.isPresent(2)) {
                    System.out.println("Event " + event.getEventName() + " does not display in the list");
                    return null;
                }
                Link lnkEventName = Link.xpath(String.format(lblEventNameXPath, xpathEvent));
                if (event.getEventName().contains(lnkEventName.getText().trim())) {
                    String eventStartTime = Label.xpath(String.format(lblEventStartTimeXpath, xpathEvent)).getText().trim();
                    newEvent.setLinkEvent(lnkEventName);
                    newEvent.setStartTime(eventStartTime);
                    return newEvent;
                }
                if (i > lstTotal) {
                    System.out.println("Event " + event.getEventName() + " does not display in the list");
                    return null;
                }
                i++;
            }
        } else {
            System.out.println("Event " + event.getEventName() + " does not display in the list");
            return null;
        }
    }

    public Event getEvent(boolean isSuspend, int limit, int eventIndex) {
        return getEvent(null, isSuspend, limit, eventIndex);
    }

    public Event getEvent(String inPlay, boolean isSuspend, int limit, int eventIndex) {
        boolean isInPlay = false;
        if (inPlay.isEmpty())
            isInPlay = false;
        else {
            if (inPlay.equalsIgnoreCase("In-Play"))
                isInPlay = true;
        }
        return getEvent(isInPlay, isSuspend, limit, eventIndex);
    }

    public Event getEvent(boolean isInplay, boolean isSuspend, int limit, int eventIndex) {
        if (!lblNoEvent.isDisplayed()) {
            String xpathEvents = lblListEventXPath;
            int i = Math.max(eventIndex, 1);
            Label lblEvents = Label.xpath(xpathEvents);
            int lstTotal = lblEvents.getWebElements().size();
            if (limit != 0) {
                lstTotal = limit;
            }
            String xpathEvent;
            Label lblSuspend;
            Label iconInPlay;
            Link lnkEventName;
            String eventName;
            String eventStartTime;
            while (true) {
                xpathEvent = String.format("(%s)[%s]", xpathEvents, i);
                lnkEventName = Link.xpath(String.format("%s%s", xpathEvent, lblEventNameXPath));
                if (!lnkEventName.isPresent(2)) {
                    return null;
                }
                lblSuspend = Label.xpath(String.format("%s%s", xpathEvent, llblSuspendXPath));
                iconInPlay = Label.xpath(String.format("%s%s", xpathEvent, lblInPlayXpath));
                boolean _isInplay = iconInPlay.isDisplayed();
                boolean _isSuspend = lblSuspend.isDisplayed();
                if (_isInplay == isInplay && _isSuspend == isSuspend) {
                    eventStartTime = Label.xpath(String.format("%s%s", xpathEvent, lblEventStartTimeXpath)).getText().trim();
                    eventName = Label.xpath(String.format("%s%s", xpathEvent, lblHomeNameXpath)).getText().trim();
                    return new Event.Builder()
                            .eventName(eventName)
                            .lnkEvent(lnkEventName)
                            .isSuspend(isSuspend)
                            .inPlay(isInplay)
                            .startTime(eventStartTime)
                            .build();
                }
                if (i > lstTotal)
                    return null;
                i++;
            }
        } else {
            return null;
        }

    }

    public Event getEventRandom(boolean isInplay, boolean isSuspend) {
        if (!lblNoEvent.isDisplayed()) {
            //	String xpathEvents = String.format("%s%s",lblListEventXPath, _xpath);
            Label lblEvents = Label.xpath(lblListEventXPath);
            int lstTotal = lblEvents.getWebElements().size();
            int j = 1;
            int i = 1;
            Label lblEvent;
            String xpathEvent;
            Label lblSuspend;
            Label iconInPlay;
            Link lnkEventName;
            String homeName;
            String awayName;
            String eventName;
            String eventStartTime;
            Random rand = new Random();
            while (true) {
                i = 1 + rand.nextInt((lstTotal - 1) + 1);
                xpathEvent = String.format("(%s)[%s]", lblListEventXPath, i);
                lnkEventName = Link.xpath(xpathEvent);
                if (!lnkEventName.isPresent(2)) {
                    return null;
                }
                lblSuspend = Label.xpath(String.format("%s%s", xpathEvent, llblSuspendXPath));
                iconInPlay = Label.xpath(String.format("%s%s", xpathEvent, lblEventStartTimeXpath));
                boolean _isInplay = iconInPlay.getText().equalsIgnoreCase("In-Play");
                boolean _isSuspend = lblSuspend.isDisplayed();
                if (_isInplay == isInplay && _isSuspend == isSuspend) {
                    eventStartTime = Label.xpath(String.format("%s%s", xpathEvent, lblEventStartTimeXpath)).getText().trim();
                    homeName = Label.xpath(String.format("%s%s", xpathEvent, lblHomeNameXpath)).getText().trim();
                    awayName = Label.xpath(String.format("%s%s", xpathEvent, lblAwayNameXpath)).getText().trim();
                    eventName = String.format("%s v %s", homeName, awayName);
                    return new Event.Builder()
                            .eventName(eventName)
                            .lnkEvent(lnkEventName)
                            .isSuspend(isSuspend)
                            .inPlay(isInplay)
                            .startTime(eventStartTime)
                            .build();
                }
                if (j > lstTotal)
                    return null;
                j++;
            }
        } else {
            return null;
        }

    }

    public boolean isOddsUnclickable(String eventName) {
        String xpath = String.format("//span[contains(text(),'%s')]//following::li[1]//button", eventName);
        int n = Button.xpath(String.format(xpath, eventName)).getWebElements().size();
        for (int i = 0; i < n; i++) {
            Button btnOdd = Button.xpath(String.format("(%s)[%d]", xpath, i + 1));
            if (!btnOdd.isClickable(2)) {
                System.out.println("ERROR! Odds button list in event page able to click");
                return false;
            }
        }
        return true;
    }

    public void clickEvent(Event event) {
        event.getLinkEvent().click();
    }

    public void clickOnRowofEventName(String event) {
        int i = 1;
        Link lnkEvent;
        while (true) {
            lnkEvent = (Link) tblEvents.getControlOfCell(1, 1, i, "span[contains(@class,'home-team')]");
            if (!lnkEvent.isDisplayed()) {
                System.out.println("Debug! Not found event to click");
                return;
            }
            String eventName = lnkEvent.getText().trim();
            if (eventName.equalsIgnoreCase(event)) {
                lnkEvent.click();
                lnkEvent.isDisplayedShort(2);
                return;
            }
            i++;
        }
    }

    private int getEventIndex(String eventName) {
        int i = 1;
        Link lnkHomeTeam;
        Link lnkAwayTeam;
        String homeTeamName;
        String awayTeamName;
        while (true) {
            lnkHomeTeam = (Link) tblEvents.getControlOfCell(1, 1, i, "span[contains(@class,'home-team-name')]");

            if (!lnkHomeTeam.isDisplayed()) {
                System.out.println("Debug! Event " + eventName + " not display on the page");
                return 0;
            }
            lnkAwayTeam = (Link) tblEvents.getControlOfCell(1, 1, i, "span[contains(@class,'away-team-name')]");
            homeTeamName = lnkHomeTeam.getText().trim();
            awayTeamName = lnkAwayTeam.getText().trim();
            if (eventName.contains(homeTeamName) && eventName.contains(awayTeamName)) {
                System.out.println("Debug! Found the event " + eventName + " at row" + i);
                return i;
            }
            i++;
        }
    }

    public Event getEventInfo(String eventName) {
        int index = getEventIndex(eventName);
        String xpathEvents = lblListEventXPath;
        String xpathEvent = String.format("(%s)[%s]", xpathEvents, index);
        Label lblSuspend = Label.xpath(String.format("%s%s", xpathEvent, llblSuspendXPath));
        Label iconInPlay = Label.xpath(String.format("%s%s", xpathEvent, lblInPlayXpath));
        Link lnkEventName = Link.xpath(String.format("%s%s", xpathEvent, lblEventNameXPath));
        String eventStartTime = Label.xpath(String.format("%s%s", xpathEvent, lblEventStartTimeXpath)).getText().trim();
        boolean _isInplay = iconInPlay.isDisplayed();
        boolean _isSuspend = lblSuspend.isDisplayed();
        return new Event.Builder()
                .eventName(eventName)
                .lnkEvent(lnkEventName)
                .isSuspend(_isInplay)
                .inPlay(_isSuspend)
                .startTime(eventStartTime)
                .build();
    }

    private boolean getEventIDHasPorductData(String productName, String eventID) {
        return FancyUtils.isEventHasMarketType(eventID, productName);
    }

    /**
     * To get the event that has Wicket Fancy and and click on that event
     *
     * @return
     */
    public String getEventIDHasMarketData(String product) {
        // Scan all the list event in Cricket sport page
        int i = 1;
        Link lnkEvent;
        while (true) {
            lnkEvent = (Link) tblEvents.getControlOfCell(1, 1, i, "div[@class='market-container']");
            if (!lnkEvent.isDisplayed())
                return null;
            // get event id
            String eventID = lnkEvent.getAttribute("id");

            if (getEventIDHasPorductData(product, eventID)) {
                return eventID;
            }
            i++;
            // check api tha has fany
        }
    }

    public void clickOnRowofEventID(String eventID) {
        int i = 1;
        Link lnkEvent;
        while (true) {
            lnkEvent = (Link) tblEvents.getControlOfCell(1, 1, i, "div[@class='market-container']");
            if (!lnkEvent.isDisplayed())
                return;
            if (lnkEvent.getAttribute("id").equalsIgnoreCase(eventID)) {
                lnkEvent.click();
                return;
            }
            i++;
            // check api tha has fany
        }
    }

    public enum Status {NA, IN_PLAY, COMING}



/*
	public Odd getOdd(Team team, boolean isBack, boolean isOddEmpty, int limit, int eventIndex) {
		if (lblNoEvent.isInvisible(2)) {
			String xpathEvents = String.format("%s//div[@id='content-odds']//ul[@class='list-coupon']/li[@class='vevent coming-up']", _xpath);
			int i = Math.max(eventIndex, 1);
			while(true) {
				String xpathEvent = String.format("%s[%s]", xpathEvents, i);
				Label lblEvent = Label.xpath(xpathEvent);
				if (!lblEvent.isPresent(2)){
					return null;
				}
				Label lblSuspend = Label.xpath(String.format("%s//ul/li//div[@class='status-overlay']", xpathEvent));
				if (!lblSuspend.isDisplayedShort(2)) {
					Icon iconInPlay = Icon.xpath(String.format("%s/span[@class='in-play-icon']", xpathEvent));
					Link lnkHome = Link.xpath(String.format("%s//span[@class='home-team']", xpathEvent));
					Link lnkAway = Link.xpath(String.format("%s//span[@class='away-team']", xpathEvent));
					Label lnkStartTime = Label.xpath(String.format("%s//div[@class='start-time']", xpathEvent));

					String backOrLay = isBack ? "back" : "lay";
					String homeTeam = lnkHome.getText();
					String awayTeam = lnkAway.getText();
					int iSelectedTeam = _isMatchOdds ? (team.equals(Team.HOME) ? 1 : 2) : (team.equals(Team.HOME) ? 1 : team.equals(Team.DRAW) ? 2 : 3);
					String selectedTeam = team.equals(Team.HOME) ? homeTeam : team.equals(Team.DRAW) ? Constants.BetSlip.DRAW : awayTeam;

					String xpathOdd = String.format("%s//li[@class='prices-list']//li[@class='%s-cell'][%s]//span", xpathEvent, backOrLay, iSelectedTeam);
					Link lnkSelectedOdd = Link.xpath(xpathOdd); // which odd at Back | Lay of (1 | x | 2)

					if (lnkSelectedOdd.isDisplayedShort(2)) {
						boolean isInPlayOnOdd = iconInPlay.isDisplayedShort(2);
						String oddRate = lnkSelectedOdd.getText();

						// check whether odd rate is less than 4 we will place this bet
						String checkOddRate = oddRate.isEmpty() ? "100" : oddRate;
						boolean isAllow = true;
						if(!isBack && Double.parseDouble(checkOddRate) > 4) {
							isAllow = false;
						}

						if (isOddEmpty) {
							if (oddRate.isEmpty()) {
								return getOdd(lnkSelectedOdd, oddRate, homeTeam, awayTeam, isInPlayOnOdd, selectedTeam, i);
							}
						} else {
							if (isAllow && !oddRate.isEmpty()) {
								return getOdd(lnkSelectedOdd, oddRate, homeTeam, awayTeam, isInPlayOnOdd, selectedTeam, i);
							}
						}
					}
				}
				if (i > limit) {
					return null;
				}
				i++;
			}
		}
		return null;
	}

	public Odd getOdd(Team team, boolean isBack, boolean isOddEmpty, Status statusType, int limit, int eventIndex) {
		if (lblNoEvent.isInvisible(2)) {
			String xpathEvents = String.format("%s//div[@id='content-odds']//ul[@class='list-coupon']/li[@class='vevent coming-up']", _xpath);
			int i = Math.max(eventIndex, 1);
			while(true) {
				String xpathEvent = String.format("%s[%s]", xpathEvents, i);
				Label lblEvent = Label.xpath(xpathEvent);
				if (!lblEvent.isPresent(2)){
					return null;
				}
				Label lblSuspend = Label.xpath(String.format("%s//ul/li//div[@class='status-overlay']", xpathEvent));
				if (!lblSuspend.isDisplayedShort(2)) {
					Icon iconInPlay = Icon.xpath(String.format("%s/span[@class='in-play-icon']", xpathEvent));
					Link lnkHome = Link.xpath(String.format("%s//span[@class='home-team']", xpathEvent));
					Link lnkSeparate = Link.xpath(String.format("%s//span[@class='sep']", xpathEvent));
					Link lnkAway = Link.xpath(String.format("%s//span[@class='away-team']", xpathEvent));
					Label lnkStartTime = Label.xpath(String.format("%s//div[@class='start-time']", xpathEvent));

					String backOrLay = isBack ? "back" : "lay";
					String homeTeam = lnkHome.getText();
					String separateCharacter = lnkSeparate.getText(); // v  or @
					String awayTeam = lnkAway.getText();
					String eventName = String.format("%s %s %s", homeTeam, separateCharacter, awayTeam);
					int iSelectedTeam = _isMatchOdds ? (team.equals(Team.HOME) ? 1 : 2) : (team.equals(Team.HOME) ? 1 : team.equals(Team.DRAW) ? 2 : 3);
					String selectedTeam = team.equals(Team.HOME) ? homeTeam : team.equals(Team.DRAW) ? Constants.BetSlip.DRAW : awayTeam;

					String xpathOdd = String.format("%s//li[@class='prices-list']//li[@class='%s-cell'][%s]//span", xpathEvent, backOrLay, iSelectedTeam);
					Link lnkSelectedOdd = Link.xpath(xpathOdd); // which odd at Back | Lay of (1 | x | 2)

					if (lnkSelectedOdd.isDisplayedShort(2)) {
						boolean isInPlayOnOdd = iconInPlay.isDisplayedShort(2);
						String oddRate = lnkSelectedOdd.getText();

						// check whether odd rate is less than 4 we will place this bet
						String checkOddRate = oddRate.isEmpty() ? "100" : oddRate;
						boolean isAllow = true;
						if(!isBack && Double.parseDouble(checkOddRate) > 4) {
							isAllow = false;
						}
						switch (statusType) {
							case NA:
								if (isOddEmpty) {
									if (oddRate.isEmpty()) {
										return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i);
									}
								} else {
									if (isAllow && !oddRate.isEmpty()) {
										return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i);
									}
								}
							case IN_PLAY:
								if (isInPlayOnOdd) {
									if (isOddEmpty) {
										if (oddRate.isEmpty()) {
											return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i);
										}
									} else {
										if (isAllow && !oddRate.isEmpty()) {
											return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i);
										}
									}
								}
							case COMING:
								if (!isInPlayOnOdd) {
									if (isOddEmpty) {
										if (oddRate.isEmpty()) {
											return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i);
										}
									} else {
										if (isAllow && !oddRate.isEmpty()) {
											return getOdd(lnkSelectedOdd, oddRate, eventName, isInPlayOnOdd, selectedTeam, i);
										}
									}
								}
						}
					}
				}
				if (i - eventIndex > limit) {
					return null;
				}
				i++;
			}
		}
		return null;
	}

	private Odd getOdd (Link lnkSelectedOdd, String oddRate, String homeTeam, String awayTeam, boolean isInPlay, String selectedTeam, int eventIndex) {
		return new Odd.Builder()
				.eventName(String.format("%s v %s", homeTeam, awayTeam))
				.selectedTeam(selectedTeam)
				.isInPlay(isInPlay)
				.oddRate(oddRate)
				.lnkOdd(lnkSelectedOdd)
				.eventIndex(eventIndex)
				.build();
	}

	private Odd getOdd (Link lnkSelectedOdd, String oddRate, String eventName, boolean isInPlay, String selectedTeam, int eventIndex) {
		return new Odd.Builder()
				.eventName(eventName)
				.selectedTeam(selectedTeam)
				.isInPlay(isInPlay)
				.oddRate(oddRate)
				.lnkOdd(lnkSelectedOdd)
				.eventIndex(eventIndex)
				.build();
	}

 */
}
