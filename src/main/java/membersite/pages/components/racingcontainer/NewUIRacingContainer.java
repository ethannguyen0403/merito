package membersite.pages.components.racingcontainer;

import com.paltech.element.common.*;
import membersite.controls.RacingMarketControl;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class NewUIRacingContainer extends RacingContainer {
    public Tab tabToday = Tab.xpath("//div[@class='sport-groups']/ul/li[1]");
    public Tab tabTomorrow = Tab.xpath("//div[@class='sport-groups']/ul/li[2]");
    public Tab tabNextTomorrow = Tab.xpath("//div[@class='sport-groups']/ul/li[3]");
    public Icon icExpandCountry = Icon.xpath("//i[@class='fas fa-caret-down']");
    public Icon icCollapseCountry = Icon.xpath("//i[@class='fas fa-caret-down']");
    private String _xpath = "//div[contains(@class,'highlight-page')]/app-sport-highlight-racing";
    private String xPathRacingCountryTitle = "//div[@class='highlight-racing']//div[contains(@class,'racing')]//div[@class='title']";
    //private Label lblRacingCountryTitle = Label.xpath("//div[@class='racing'][2]/div[@class='title']//span");
    private String xPathTrackName = "//span[.='%s']/ancestor::div[contains(@class,'racing')][1]//span[@class='track-name']";
    //private Label lblRacingTitle = Label.xpath("//div[@class='racing'][2]/div[@class='tracks-list']//div[@class='single-track'][1]//span[@class='track-name']");
    private String xPathRacingLink = "//a[.='%s']/ancestor::div[contains(@class,'single-track')]//div[@class='races-list']//a";
    //private Label lblRacingList = Label.xpath("//div[@class='racing'][2]/div[@class='tracks-list']//div[@class='single-track'][1]/div[@class='races-list']//a[@class='meto-text-leftmenu']");

    private String lblSuspendXPath = "%s//div[contains(@class,'status-overlay')]";
    private String lblInplayXPath = "%s//div[contains(@class,'text-inplay')][2]";
    private String lblEventNameXPath = "%s//span[@class='home-team']";
    private String lblListEventXPath = "%s//li[@class='vevent coming-up']";
    private Label lblNoRace = Label.xpath("//div[@class='not-found']");
    private Button btnOdds;

    public String getCountry(int index) {
        return getAllCountry().get(index);
    }

    public boolean isNoRace() {
        return lblNoRace.isDisplayed();
    }

    public List<String> getAllCountry() {
        List<String> countryLst = new ArrayList<>();
        Label lbCountries = Label.xpath(xPathRacingCountryTitle);
        List<WebElement> lstWeb = lbCountries.getWebElements();
        Label country;
        for (int i = 1, n = lstWeb.size(); i <= n; i++) {
            country = Label.xpath(String.format("(%s)[%d]", xPathRacingCountryTitle, i));
            countryLst.add(country.getText().trim());
        }
        return countryLst;
    }

    public void expandAllCountrry() {
        for (WebElement e : icExpandCountry.getWebElements()) {
            e.click();
        }
    }

    public void collapseAllCountry() {
        for (WebElement e : icCollapseCountry.getWebElements()) {
            e.click();
        }
    }

    public List<String> getAllTrackName(String country) {
        String xpathTrack = String.format(xPathTrackName, country);
        List<String> tracklst = new ArrayList<>();
        Label lblTrackNames = Label.xpath(xpathTrack);
        List<WebElement> lstWeb = lblTrackNames.getWebElements();
        Label lblTrackName;
        for (int i = 1, n = lstWeb.size(); i <= n; i++) {
            lblTrackName = Label.xpath(String.format("(%s)[%d]", xpathTrack, i));
            tracklst.add(lblTrackName.getText());
        }
        return tracklst;
    }

    public List<String> getAllRacingList(String country, String trackName) {
        List<String> lst = new ArrayList<>();
        String xpathRace = String.format(xPathRacingLink, trackName);
        Label lblRaces = Label.xpath(xpathRace);
        List<WebElement> lstWeb = lblRaces.getWebElements();
        Link lnkRace;
        for (int i = 1, n = lstWeb.size(); i <= n; i++) {
            lnkRace = Link.xpath(String.format("(%s)[%d]", xpathRace, i));
            lst.add(lnkRace.getText());
        }
        return lst;
    }

    public RacingMarketControl clickRacing(String country, String trackName, String race) {
        List<String> lst = getAllRacingList(country, trackName);
        String xpathRace = String.format(xPathRacingLink, trackName);
        for (String r : lst) {
            if (race.equals(r.trim())) {
                Link lnkRaces = Link.xpath(String.format("%s[.=' %s ']", xpathRace, race));
                lnkRaces.click();
                return RacingMarketControl.xpath("//app-racing-market");
            }
        }
        System.out.println(String.format("FAILED! Cannot find the race link %s to click on", race));
        return null;
    }

    public enum Status {NA, IN_PLAY, COMING}


}
