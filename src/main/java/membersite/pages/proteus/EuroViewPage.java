package membersite.pages.proteus;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import controls.Table;
import membersite.controls.DropDownMenu;
import membersite.objects.proteus.*;
import membersite.utils.proteus.MarketUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.ProteusConstant.*;

public class EuroViewPage extends ProteusHomePage {
    public Label lblView = Label.xpath("//li[contains(@class,'view-mode')]/span");
    public Label lblLoading = Label.xpath("//div[contains(@class,'loading-text')]/p");
    public DropDownMenu ddmOddsType = DropDownMenu.xpath("//ul[contains(@class,'control-list')]/li[contains(@class,'odd')]","//span[@class='mx-2 text-uppercase']","//ul[contains(@class,'sub-selections')]//li");
    private String sportHeaderMenuXpath = "//app-slider-sport//em[contains(@class,'menu-%s')]";
    private String sportLeftMenuXpath = "//app-left-menu-euro//div[contains(@class,'menu-item')]//div[text()='%s']";
    private String marketTabXpath = "//app-sport-euro//div[contains(@class,'market-group')]//button[text()='%s']";
    private String eventTableXpath = "//app-league-euro//table[contains(@class,'odd-page')]";
    private String tableEventXpath = "//table[contains(@class,'odd-pages') and @eventid='%s']";
    public Button btnEarlyEuro = Button.xpath(String.format("//app-left-menu-euro//button[text()=' %s ']",EARLY_PERIOD));
    private Button btnLiveEuro = Button.xpath(String.format("//app-left-menu-euro//button[text()=' %s ']",LIVE_PERIOD));
    public Label lblSportHeader = Label.xpath("(//app-sport-euro//div[contains(@class,'sport-header')]//h3)[1]");
    private TextBox txtStake = TextBox.xpath("//app-bet-item//input[contains(@class,'stake-input')]");
    public Button btnPlaceBet = Button.xpath("//app-open-bets//button[contains(@class,'btn-place-bet')]");
    private Label lblCompetitionInfo = Label.xpath("(//app-sport-euro//app-league-euro//th[@class='opponent-column'])[1]");
    private Label lblHomeNameInfo = Label.xpath("(//app-sport-euro//app-league-euro//table[contains(@class,'odd-pages')]//tr)[1]//th[3]//div[@class='opponent']");
    private Table tblFirstLeague = Table.xpath("(//app-league-euro/table[contains(@class,'league-header')])[1]", 5);
    private Table tblFirstEvent = Table.xpath(String.format("(%s)[1]", eventTableXpath), 6);
    private Label lblFirstLeague = Label.xpath("(//app-league-euro/table[contains(@class,'league-header')])[1]//th[contains(@class,'opponent-column')]");
    private Label lblFirstEventHomeName = Label.xpath(String.format("(%s)[1]//div[@class='opponent']/div[1]", eventTableXpath));
    private Label lblFirstEventAwayName = Label.xpath(String.format("(%s)[1]//div[@class='opponent']/div[2]", eventTableXpath));
    private Label lblFirstHDP = Label.xpath(String.format("((%s)[1]//th[contains(@class,'odd-column')]//span[contains(@class,'d-lg-inline')])[1]", eventTableXpath));
    private Label lblFirstSelection = Label.xpath(String.format("((%s)[1]//th[contains(@class,'odd-column')])[1]", eventTableXpath));
    private Label lblSecondSelection = Label.xpath(String.format("((%s)[1]//th[contains(@class,'odd-column')])[2]", eventTableXpath));
    private Label lblThirdSelection = Label.xpath(String.format("((%s)[1]//th[contains(@class,'odd-column')])[3]", eventTableXpath));

    private String leagueIndexXpath = "//app-league-euro[%d]";
    private String eventIndexXpath = "//app-league-euro[%d]//app-event-item-parent-euro[%d]//table";
    private String leagueNameXpath ="//app-league-euro[%d]//th[@class='opponent-column']//div[contains(@class,'text-row')]";
    private String timeColumXpath = "//app-league-euro[%d]/app-event-item-parent-euro[%d]//th[contains(@class,'time-column')]";
    private String homeTeamXpath ="//app-league-euro[%d]/app-event-item-parent-euro[%d]//div[@class='opponent']/div[1]";
    private String awayTeamXpath ="//app-league-euro[%d]/app-event-item-parent-euro[%d]//div[@class='opponent']/div[2]";
    private String firstOddsCellXpath = "//app-league-euro[%d]/app-event-item-parent-euro[%d]//th[contains(@class,'odd-column')][1]";
    private String oddsHomeXpath ="//th[contains(@class,'odd-column')][%s]//span[contains(@class,'odd-number')][1]//span";


    private Button btnOK = Button.xpath("//app-confirm-modal//button[contains(@class,'btn-ok')]");
    public EuroViewPage(String types) {
        super(types);
    }

    /**
     * Get the first market info in the UI
     * @return
     */
    public Market getEventInfo(String sportName, String oddsType) {
        int leagueIndex = 1;
        Market market;
        while (true)
        {
            market = getEventInfo(sportName,oddsType,leagueIndex);
            if(Objects.nonNull(market))
                return market;
            leagueIndex = leagueIndex + 1;
        }
    }

    /**
     * Get the first market available under a league
     * @param leagueIndex
     * @return
     */
    public Market getEventInfo(String sportName, String oddsType,int leagueIndex) {
        int eventIndex = 1;
        Market market;
        while (true)
        {
            market = getEventInfo(sportName,oddsType,leagueIndex,eventIndex);
            if(Objects.nonNull(market))
                return market;
            eventIndex = eventIndex + 1;
        }
    }

    /**
     * Get event info with odds type = Decimal odds bases on inputed index, if index = 0 mean get random
     * @param leagueIndex
     * @param eventIndex
     * @return a Market Info
     */
    private Market getEventInfo(String sportName, String oddsType, int leagueIndex, int eventIndex) {
        Market market;
        Label lblLeague = Label.xpath(String.format(leagueIndexXpath,leagueIndex));
        if (!lblLeague.isDisplayed())
            return null;
        // get event id from UI xpath property
        String eventID = Label.xpath(String.format(firstOddsCellXpath,leagueIndex,eventIndex)).getAttribute("eventid");
        String oddsKey = Label.xpath(String.format(firstOddsCellXpath,leagueIndex,eventIndex)).getAttribute("key");


        //handle incase no odds display in the UI, move to the next row
        if(Objects.isNull(eventID)) {
            return null;
        }
        String leagueName = Label.xpath(String.format(leagueNameXpath,leagueIndex)).getText();
        // Get the market info from API with the eventID get from UI
        market = MarketUtils.getMarketByOddsKey(oddsType,Integer.valueOf(eventID),oddsKey);
        market.setLeagueName(leagueName);
        // Get more info of the event in other API: league Name, home, away,event startTime
        String sportID = SPORTBOOK_SPORT_ID.get(sportName.toUpperCase());
        Market temp =  MarketUtils.getEventInfoUnderLeague(Integer.valueOf(sportID),leagueName,eventID);
        market.setSportName(sportName);
        market.setEventName(String.format("%s vs %s",temp.getHomeName(),temp.getAwayName()));
        market.setHomeName(temp.getHomeName());
        market.setAwayName(temp.getAwayName());
        market.setEventStartTime(temp.getEventStartTime());
        return market;
    }

    protected int defineOddsColumn(Market market, String selection){
        String sport = market.getSportName();
        String marketType = market.getBetType();
        // For Soccer/ Handball, UI has three column 1-HOME X-DRAW 2-AWAY, other sport only has 2 columns: 1-HOME 2-AWAY
        if(marketType.equalsIgnoreCase("MONEYLINE")){
            if(("Soccer, Handball").contains(sport)){
                switch (selection){
                    case "DRAW":
                        return 2;
                    case "AWAY":
                        return 3;
                    default:
                        return 1;
                }
            }
        }
        if(("HOME, OVER").contains(selection))
            return 1;
        return 2;
    }

    public void clickOdds(Market market, String selection){
        // get the row index has expected ID
        String eventXpath = getEventIndexXpath(String.valueOf(market.getEventId()));
        int column = defineOddsColumn(market,selection);
        oddsHomeXpath = String.format(oddsHomeXpath,column);
        Label lblOdds = Label.xpath(String.format("%s%s",eventXpath,oddsHomeXpath));
        System.out.println(String.format("Click on Odds %s of Team(%s) Event (%s) under League (%s)",lblOdds.getText(),market.getOddsInfoBySelection(selection),market.getEventName(), market.getLeagueName()));
        lblOdds.click();
    }

    /**
     * Looking all league in the UI has the expected event ID
     * @param eventID
     * @return
     */
    private String getEventIndexXpath(String eventID){
        int leagueIndex = 1;
        Label lblLeague;
        while (true){
            lblLeague = Label.xpath(String.format(leagueIndexXpath,leagueIndex));
            if (!lblLeague.isDisplayed())
                return "";
            // find the row has the expected event id
            int eventIndex = getEventIndexUnderALeague(leagueIndex,eventID);
            if(eventIndex != 0)
                return String.format("//app-league-euro[%d]//app-event-item-parent-euro[%d]",leagueIndex,eventIndex);
            leagueIndex = leagueIndex +1;
        }
    }

    /**
     * For each league index, find the event has the expected ID
     * @param leagueIndex
     * @param eventID
     * @return
     */
    private int getEventIndexUnderALeague(int leagueIndex,String eventID){
        int eventIndex = 1;
        Label lblEvent;
        while (true) {
            lblEvent = Label.xpath(String.format(firstOddsCellXpath, leagueIndex, eventIndex));
            if (!lblEvent.isDisplayed())
                return 0;
            if(Objects.isNull(lblEvent.getAttribute("eventid"))) {
                eventIndex = eventIndex + 1;
                continue;
            }
            else {
                if (lblEvent.getAttribute("eventid").equalsIgnoreCase(eventID))
                    return eventIndex;
            }
            eventIndex = eventIndex + 1;

        }
    }

    /**
     * Click on an Odds and input stake then click place bet button
     * @param market
     * @param selection
     * @param stake
     * @param isAcceptBetterOdds
     * @param isPlace
     * @return
     */
    public Order addOddToBetSlipAndPlaceBet(Market market, String selection, String stake, boolean isAcceptBetterOdds, boolean isPlace){
        // click odds
        clickOdds(market, selection);
        //input stake and click place bet and confirm
        Order order = placeNoBet(market,stake,isAcceptBetterOdds,isPlace);
        if(isPlace) {
            // set Odd info of the team name that placed on
            order.setOdds(market.getOddsInfoBySelection(selection));
            return order;
        } else {
            return null;
        }
    }

    public void waitContentLoad(){
        lblLoading.waitForControlInvisible(2,3);
    }

    public void selectOddsType (String oddsType){
        oddsType = String.format(" %s ",oddsType);
        ddmOddsType.clickSubMenu(oddsType);
        waitForSpinnerLoading();
    }

    public String getOddsType(){
        return ddmOddsType.getSelectedItem();
    }

    public void selectSportHeaderMenu(String sportName) {
        Button btnSport = Button.xpath(String.format(sportHeaderMenuXpath, sportName));
        btnSport.click();
        waitForSpinnerLoading();
    }

    /**
     * This method select all item in left menu in ALL SPORT section: Sport, Country, League,or Event
     * @param menu
     */
    public void selectItemOnLeftMenu(String menu) {
        Button btnSport = Button.xpath(String.format(sportLeftMenuXpath, menu));
        if(!btnSport.isDisplayed())
            System.err.println(String.format("Cannot found %s in the left menu",menu));
        btnSport.click();
        waitForSpinnerLoading();
    }

    /**
     * This method select an event in the left menu. To select sport, only input sportName. To select eventNAme, input all fields with valid infor
     * @param sportName
     * @param country
     * @param leagueName
     * @param eventName
     */
    public void selectEventOnLeftMenu(String period,String sportName, String country, String leagueName, String eventName){
        selectPeriodTab(period);
        if (!sportName.isEmpty())
            selectItemOnLeftMenu(sportName);
        if(!country.isEmpty())
            selectItemOnLeftMenu(country);
        if(!leagueName.isEmpty())
            selectItemOnLeftMenu(leagueName);
        if(!eventName.isEmpty())
            selectItemOnLeftMenu(eventName);
    }
    public void selectEventOnLeftMenu(String period,String sportName){
        selectEventOnLeftMenu(period,sportName,"", "","");
    }
    public void selectEventOnLeftMenu(String period,String sportName, String country){
        selectEventOnLeftMenu(period,sportName,country, "","");
    }
    public void selectMarketTypeTab(String marketType) {
        Label lblMarketType = Label.xpath(String.format(marketTabXpath, marketType));
        lblMarketType.click();
        waitForSpinnerLoading();
    }


    public void selectPeriodTab(String period) {
        if (period.equalsIgnoreCase("early")) {
           btnEarlyEuro.click();
           waitForSpinnerLoading();
        } else {
            btnLiveEuro.click();
            waitForSpinnerLoading();
        }
    }

     public void placeBet(Market market, String stake, String selection, boolean isSubmit, boolean isConfirm) {
        if(Objects.nonNull(market)) {
            // click odds
            clickOdds(market, selection);
            btnPlaceBet.waitForElementToBePresent(btnPlaceBet.getLocator(), 2);
            inputStake(String.valueOf(market.getEventId()), stake);
            if(isSubmit) {
                btnPlaceBet.jsClick();
                btnOK.waitForElementToBePresent(btnOK.getLocator());
                if(isConfirm) {
                    btnOK.jsClick();
                    waitForSpinnerLoading();
                }
            }
        }
    }

    public void inputStake(String eventId, String stake) {
        String betslipRootXpath = String.format("//app-open-bets//app-bet-item//div[contains(@orderid,'eventId=%s')]", eventId);
        TextBox txtStake = TextBox.xpath(String.format("%s%s", betslipRootXpath, "//input[contains(@class,'stake-input')]"));
        txtStake.sendKeys(stake);
        txtStake.waitForAttributeChange("value", stake, 2);
    }

    public List<String> getSportsHeaderMenuList() {
        List<String> lstHeader = new ArrayList<>();
        Label lblSportHeaders = Label.xpath("//app-slider-sport//li");
        for (int i = 0; i < lblSportHeaders.getWebElements().size(); i++) {
            Label lblHeaderItem = Label.xpath(String.format("(//app-slider-sport//li)[%s]", i + 1));
            lstHeader.add(lblHeaderItem.getText().trim());
        }
        return lstHeader;
    }

    public Market getEventInfoUI(int eventId) {
        Market market = new Market.Builder().build();
        List<Odds> lstOddsObject = new ArrayList<>();
        String rootXpath = String.format(tableEventXpath, eventId);
        Table tblEvent = Table.xpath(rootXpath, 7);
        Label lblLeague = Label.xpath(String.format("%s%s", tblEvent.getLocator().toString().replace("By.xpath: ",""),"//ancestor::app-league-euro//table[contains(@class,'league-header')]//th[@class='opponent-column']"));
        Label lblEventStartTime = Label.xpath(String.format("%s%s", rootXpath, "//th[contains(@class,'time-column')]"));
        Label lblEventName = Label.xpath(String.format("%s%s", rootXpath, "//div[contains(@class,'opponent')]"));
        Label lblOdds = Label.xpath(String.format("%s%s", rootXpath, "//span[contains(@class,'odd-number')]"));
        //add odds from UI to list odds object
        for (int i = 0; i < lblOdds.getWebElements().size(); i++) {
            Label lblOddsValue = Label.xpath(String.format("(%s)[%s]", lblOdds.getLocator().toString().replace("By.xpath: ", ""), i + 1));
            lstOddsObject.add(i, new Odds.Builder().odds(Double.valueOf(lblOddsValue.getText().replace("⠀", ""))).build());
        }
        market.setOdds(lstOddsObject);
        market.setLeagueName(lblLeague.getText().trim());
        market.setEventStartTime(lblEventStartTime.getText().trim());
        market.setEventName(lblEventName.getText().trim().replace("\n"," v "));
        return market;
    }

    public void verifyOddsShowCorrect(List<Odds> lstOddsExpected, List<Odds> lstOddsActual) {
        for (int i = 0; i < lstOddsExpected.size(); i++) {
            Assert.assertEquals(lstOddsExpected.get(i).getOdds(), lstOddsActual.get(i).getOdds(), String.format("FAILED! Odds does not show correct expected %s actual %s", lstOddsExpected.get(i).getOdds(), lstOddsActual.get(i).getOdds()));
        }
    }

    public EuroViewDetailsPage opentDetail(Market market){
        String eventXpath = getEventIndexXpath(String.valueOf(market.getEventId()));
        Label.xpath( String.format("%s%s",eventXpath,homeTeamXpath)).click();
        EuroViewDetailsPage euroViewDetailsPage = new EuroViewDetailsPage(_type);
        euroViewDetailsPage.lblLeagueName.isDisplayed();
        return euroViewDetailsPage;
    }

    public void verifyTopMenuShowCorrect() {
        List<String> lstHeaders = getSportsHeaderMenuList();
        List<String> lstActiveSports = MarketUtils.getListActiveSports();
        lstActiveSports.add(0,"Favourites");
        Assert.assertEquals(lstHeaders, lstActiveSports, String.format("FAILED! List Header is not matched expected %s actual %s", lstHeaders, lstActiveSports));
    }
}
