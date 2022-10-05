package membersite.controls.sat;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class RacingContainerControl extends BaseElement {
    private String _xpath = "//div[contains(@class,'highlight-page')]/app-sport-highlight-racing";
    public Tab tabToday = Tab.xpath("//div[@class='sport-groups']/ul/li[1]");
    public Tab tabTomorrow = Tab.xpath("//div[@class='sport-groups']/ul/li[2]");
    public Tab tabNextTomorrow = Tab.xpath("//div[@class='sport-groups']/ul/li[3]");
    public Icon icExpandCountry = Icon.xpath("//i[@class='fas fa-caret-down']");
    public Icon icCollapseCountry = Icon.xpath("//i[@class='fas fa-caret-down']");

    private  String xPathRacingCountryTitle = "//div[@class='highlight-racing']//div[contains(@class,'racing')]//div[@class='title']";
    //private Label lblRacingCountryTitle = Label.xpath("//div[@class='racing'][2]/div[@class='title']//span");
    private String xPathTrackName = "//span[.='%s']/ancestor::div[contains(@class,'racing')][1]//span[@class='track-name']";
    //private Label lblRacingTitle = Label.xpath("//div[@class='racing'][2]/div[@class='tracks-list']//div[@class='single-track'][1]//span[@class='track-name']");
private String xPathRacingLink = "//a[.='%s']/ancestor::div[contains(@class,'single-track')]//div[@class='races-list']//a";
    //private Label lblRacingList = Label.xpath("//div[@class='racing'][2]/div[@class='tracks-list']//div[@class='single-track'][1]/div[@class='races-list']//a[@class='meto-text-leftmenu']");

    private String lblSuspendXPath;
    private String lblInplayXPath;
    private String lblEventNameXPath;
    private String lblListEventXPath;
    public Label lblNoRace;
    private Button btnOdds;
    public enum Status {NA, IN_PLAY, COMING}


    private RacingContainerControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
        lblNoRace = Label.xpath("//div[@class='not-found']"); // Define if exist the label "No races available yet"

        lblSuspendXPath = "%s//div[contains(@class,'status-overlay')]";
        lblInplayXPath= "%s//div[contains(@class,'text-inplay')][2]";
        lblEventNameXPath = "%s//span[@class='home-team']";
        lblListEventXPath ="%s//li[@class='vevent coming-up']";

    }

    public static RacingContainerControl xpath(String xpathExpression) {
        return new RacingContainerControl(By.xpath(xpathExpression), xpathExpression);
    }

    public String getCountry(int index)
    {
        return getAllCountry().get(index);
    }

    public List<String>  getAllCountry()
    {
        List<String> countryLst = new ArrayList<>();
        Label lbCountries =  Label.xpath(xPathRacingCountryTitle);
        List<WebElement> lstWeb = lbCountries.getWebElements();
        Label country;
        for(int i = 1, n = lstWeb.size(); i<=n; i++)
        {
            country = Label.xpath(String.format("(%s)[%d]",xPathRacingCountryTitle,i));
            countryLst.add(country.getText().trim());
        }
        return countryLst;
    }

    public void expandAllCountrry()
    {
        for(WebElement e : icExpandCountry.getWebElements())
        {
            e.click();
        }
    }
    public void collapseAllCountry(){
        for(WebElement e : icCollapseCountry.getWebElements())
        {
            e.click();
        }
    }

    public List<String> getAllTrackName(String country)
    {
        String xpathTrack = String.format(xPathTrackName,country);
        List<String> tracklst = new ArrayList<>();
        Label lblTrackNames =  Label.xpath(xpathTrack);
        List<WebElement> lstWeb = lblTrackNames.getWebElements();
        Label lblTrackName;
        for(int i = 1, n = lstWeb.size(); i<=n; i++)
        {
            lblTrackName = Label.xpath(String.format("(%s)[%d]",xpathTrack,i));
            tracklst.add(lblTrackName.getText());
        }
        return tracklst;
    }

    public List<String> getAllRacingList(String country, String trackName){
        List<String> lst = new ArrayList<>();
        String xpathRace = String.format(xPathRacingLink,trackName);
        Label lblRaces =  Label.xpath(xpathRace);
        List<WebElement> lstWeb = lblRaces.getWebElements();
        Link lnkRace;
        for(int i = 1, n = lstWeb.size(); i<=n; i++)
        {
            lnkRace = Link.xpath(String.format("(%s)[%d]",xpathRace,i));
            lst.add(lnkRace.getText());
        }
        return lst;
    }

    public RacingMarketControl clickRacing(String country, String trackName, String race)
    {
         List<String> lst = getAllRacingList(country, trackName);
        String xpathRace = String.format(xPathRacingLink,trackName);
         for(String r : lst)
         {
             if(race.equals(r.trim()))
             {
                 Link lnkRaces = Link.xpath(String.format("%s[.=' %s ']",xpathRace,race));
                 lnkRaces.click();
                 return  RacingMarketControl.xpath("//app-racing-market");
             }
         }
         System.out.println(String.format("FAILED! Cannot find the race link %s to click on",race));
         return null;
    }



}
