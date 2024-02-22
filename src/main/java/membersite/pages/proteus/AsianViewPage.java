package membersite.pages.proteus;

import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import com.paltech.utils.DateUtils;
import controls.Table;
import membersite.controls.DropDownMenu;
import membersite.objects.proteus.*;
import membersite.utils.proteus.MarketUtils;
import org.testng.Assert;

import java.util.*;

import static common.MemberConstants.GMT_MINUS_4_30;
import static common.ProteusConstant.*;

public class AsianViewPage extends ProteusHomePage {
    private String leagueIndexXpath = "(//app-league-asian)[%d]";
    private String leagueNameXpath ="(//app-league-asian)[%d]//div[contains(@class,'league-name')]";
    private String firstOddsCellXpath = "(//app-league-asian)[%d]//app-event-item-parent//th[contains(@class,'odd-column')][%d]";
    public Label lblView = Label.xpath("//li[contains(@class,'view-mode')]/span");
    private String tableEventXpath = "//table[contains(@class,'odds-page') and @eventid='%d']";
    private String moreMarketXpath = "//app-league-asian//table[@eventid='%s']//th[contains(@class,'more-markets')]";
    private Label lblMoreMarketDetails = Label.xpath("(//app-market-asian//div[@class='market-detail']//div[contains(@class,'market-item')]/span)");

    public Label lblLoading = Label.xpath("//div[contains(@class,'loading')]");
    public Button btnEarlyAsian =  Button.xpath("//app-left-menu-asian//button[text()=' EARLY ']");
    public Button btnTodayAsian =  Button.xpath("//app-left-menu-asian//button[text()=' TODAY ']");
    public Button btnLiveAsian =  Button.xpath("//app-left-menu-asian//button[text()=' LIVE ']");
    public DropDownMenu ddmOddsType = DropDownMenu.xpath("//app-event-filter-desktop//ul[contains(@class,'control-list')]/li[4]","span[@class='mx-2 text-capitalize']","//ul[contains(@class,'sub-selections')]//li");
    public DropDownMenu ddmDateTime = DropDownMenu.xpath("//app-event-filter-desktop//ul[contains(@class,'control-list')]/li[1]","span[@class='mx-2 text-capitalize']","//ul[contains(@class,'sub-selections')]//li");
    public DropDownMenu ddmLeagues = DropDownMenu.xpath("//app-event-filter-desktop//ul[contains(@class,'control-list')]/li[5]","span[@class='me-2 text-capitalize']","//div[@class='leagues-box']");
    public DropDownMenu ddmSortBy = DropDownMenu.xpath("//app-event-filter-desktop//ul[contains(@class,'control-list')]/li[3]","span[@class='mx-2 text-capitalize']","//ul[contains(@class,'sub-selections')]//li");
    private Table tblMoreMarket = Table.xpath("//app-market-asian//table[contains(@class,'market-table')]", 7);
    String sportLeftMenuXpath = "//app-left-menu-asian//div[contains(@class,'live-title')]//span[text()=' Sports ']//..//following-sibling::div//div[text()='%s']";
    String marketLeftMenuXpath = "//app-left-menu-asian//div[contains(@class,'live-title')]//span[text()=' Sports ']//..//following-sibling::div//span[text()=' %s ']";
    private TextBox txtStake = TextBox.xpath("//app-bet-item//input[contains(@class,'stake-input')]");
    private Button btnPlaceBet = Button.xpath("//app-open-bets//button[contains(@class,'btn-place-bet')]");
    private Button btnOK = Button.xpath("//app-confirm-modal//button[contains(@class,'btn-ok')]");
    private TextBox txtSearchLeagueOrTeamName = TextBox.xpath("//app-event-filter-desktop//input[@formcontrolname='eventKeySearch']");
    private Button btnSearch = Button.xpath("//app-event-filter-desktop//button[contains(@class,'btn-search')]");
    public Label lblNoRecordFound = Label.xpath("//app-event-filter-desktop//div[contains(@class,'result-box show')]//div[contains(@class,'search-result-empty')]");
    private Table tblOdds = Table.xpath("//app-sport-asian//table[contains(@class,'odds-page')]", 15);
    private Button btnApply = Button.xpath("//app-event-filter-desktop//div[@class='leagues-box']//button[contains(@class,'btn-apply')]");
    private Button btnCancel = Button.xpath("//app-event-filter-desktop//div[@class='leagues-box']//button[contains(@class,'btn-cancel')]");
    private CheckBox chbSelectAll = CheckBox.xpath("//div[@class='leagues-box']//span[text()='Select all']//..//em");
    public AsianViewPage(String types) {
        super(types);
    }

    public void waitContentLoad(){
        lblLoading.waitForControlInvisible(2,3);
    }

    public void selectPeriodTab(String period) {
        if (period.equalsIgnoreCase("early")) {
            btnEarlyAsian.click();
            waitForSpinnerLoading();
        } else if (period.equalsIgnoreCase("today")) {
            btnTodayAsian.click();
            waitForSpinnerLoading();
        } else {
            btnLiveAsian.click();
            waitForSpinnerLoading();
        }
    }

    public void selectOddsType (String oddsType){
        oddsType = String.format(" %s ",oddsType);
        ddmOddsType.clickSubMenu(oddsType);
        waitForSpinnerLoading();
    }

//    public String selectFirstNegativeOdds() {
//        Label lblFirstOdds = Label.xpath("(//span[contains(@class,'color-negative-odd')])[1]/span");
//        Row rowEventSection = Row.xpath("(//span[contains(@class,'color-negative-odd')])[1]/span//ancestor::th");
//        lblFirstOdds.click();
//        return rowEventSection.getAttribute("eventid");
//    }
//
//    public String selectFirstPositiveOdds() {
//        Label lblFirstOdds = Label.xpath("(//span[contains(@class,'odd-number')]//span[not(contains(text(),'−'))])[1]");
//        Row rowEventSection = Row.xpath("(//span[contains(@class,'odd-number')]//span[not(contains(text(),'−'))])[1]//ancestor::th");
//        lblFirstOdds.click();
//        return rowEventSection.getAttribute("eventid");
//    }

    public void placeBet(double stake, boolean isSubmit, boolean isConfirm) {
        txtStake.sendKeys(String.valueOf(stake));
        if(isSubmit) {
            btnPlaceBet.jsClick();
            if(isConfirm) {
                btnOK.waitForElementToBePresent(btnOK.getLocator());
                btnOK.jsClick();
                waitForSpinnerLoading();
            }
        }
    }

    public void searchLeagueOrTeamName(String leagueOrTeamName) {
        txtSearchLeagueOrTeamName.sendKeys(leagueOrTeamName);
        btnSearch.click();
        waitForSpinnerLoading();
    }

    public List<String> getListSearchResult() {
        List<String> lstResult = new ArrayList<>();
        Label lblOptions = Label.xpath("//app-event-filter-desktop//div[contains(@class,'result-box show')]//li");
        for (int i = 0; i < lblOptions.getWebElements().size(); i++) {
            Label lblOption = Label.xpath(String.format("(//app-event-filter-desktop//div[contains(@class,'result-box show')]//li)[%s]",i + 1));
            lstResult.add(lblOption.getText().trim());
        }
        return lstResult;
    }

    public void selectFirstSearchOption() {
        Label lblOption = Label.xpath("(//app-event-filter-desktop//div[contains(@class,'result-box show')]//li)[1]");
        lblOption.click();
        waitForSpinnerLoading();
    }

    public void filterByDate(String date) {
        //add space before and after the value input
        ddmDateTime.clickSubMenu(String.format("%s%s%s"," ",date," "));
        waitForSpinnerLoading();
    }

    public void filterByLeague(String leagueName, boolean isSelectAll) {
        if(!isSelectAll) {
            ddmLeagues.click();
            chbSelectAll.click();
            btnCancel.click();
        }
        ddmLeagues.clickSubMenu(leagueName);
        btnApply.click();
        waitForSpinnerLoading();
    }

    public void verifyFilterByDateShowCorrect(String date) {
        Date expectedDate = DateUtils.convertToDate(date, "yyyy-MM-dd");
        String xpathDateColumn = "(//app-sport-asian//table[contains(@class,'odds-page')])[%s]//th[1]//div[contains(@class,'time')]";
        List<String> lstDate = new ArrayList<>();
        String year = String.valueOf(DateUtils.getYear(GMT_MINUS_4_30));
        for (int i = 0; i < tblOdds.getWebElements().size(); i++) {
            Label lblDate = Label.xpath(String.format(xpathDateColumn, i + 1));
            String dateActual = String.format("%s-%s", year, lblDate.getText());
            lstDate.add(dateActual);
        }
        for (int i = 0; i < lstDate.size(); i++) {
            Date actualDate = DateUtils.convertToDate(lstDate.get(i), "yyyy-MM-dd");
            Assert.assertFalse(actualDate.before(expectedDate), String.format("FAILED! Date filter is not correct expected %s but actual %s", expectedDate, actualDate));
        }
    }

    public void selectSortBy(String sortBy) {
        ddmSortBy.clickSubMenu(sortBy);
        waitForSpinnerLoading();
    }

    public void verifySortByTimeShowCorrect() {
        Label lblLeagues = Label.xpath("//app-sport-asian//div[@class='league']");
        for (int i = 0; i < lblLeagues.getWebElements().size(); i++) {
            Label lblRows = Label.xpath(String.format("(//app-sport-asian//div[@class='league'])[%s]//table[contains(@class,'odds-page')]//th[contains(@class,'time')]", i + 1));
            String xpathDate = "((//app-sport-asian//div[@class='league'])[%s]//table[contains(@class,'odds-page')]//th[contains(@class,'time')])[%s]//div[1]";
            String xpathTime = "((//app-sport-asian//div[@class='league'])[%s]//table[contains(@class,'odds-page')]//th[contains(@class,'time')])[%s]//div[2]";
            List<String> lstDateTime = new ArrayList<>();
            String year = String.valueOf(DateUtils.getYear(GMT_MINUS_4_30));
            for (int j = 0; j < lblRows.getWebElements().size(); j++) {
                Label lblDate = Label.xpath(String.format(xpathDate, i + 1,j + 1));
                Label lblTime = Label.xpath(String.format(xpathTime, i + 1,j + 1));
                String dateTime = String.format("%s-%s %s",year, lblDate.getText(),lblTime.getText());
                lstDateTime.add(dateTime);
            }
            for (int k = 0; k < lstDateTime.size(); k++) {
                if(k == lstDateTime.size() - 1) {
                    System.out.println(String.format("Last index %s of list size %s: cannot continue compare", k, lstDateTime.size()));
                    break;
                } else {
                    Date actualDateCurrent = DateUtils.convertToDate(lstDateTime.get(k), "yyyy-MM-dd HH:mm");
                    Date actualDateNext = DateUtils.convertToDate(lstDateTime.get(k + 1), "yyyy-MM-dd HH:mm");
                    if(actualDateCurrent.getTime() > actualDateNext.getTime()) {
                        Assert.assertTrue(false, String.format("FAILED! Date is not sorted correctly %s compare to %s", actualDateCurrent, actualDateNext));
                    } else {
                        Assert.assertTrue(true, "Date is sorted ASC correctly");
                    }
                }
            }
        }
    }

    public void selectEventOnLeftMenu(String period, String sportName, String marketType){
        if (!sportName.isEmpty())
            selectSportOnLeftMenu(sportName);
        selectPeriodTab(period);
        if(!marketType.isEmpty())
            selectMarketOnLeftMenu(marketType);
    }
    public void selectEventOnLeftMenu(String period,String sportName){
        selectEventOnLeftMenu(period,sportName,"");
    }
    public void selectSportOnLeftMenu(String sport) {
        Button btnSport = Button.xpath(String.format(sportLeftMenuXpath, sport));
        if(!btnSport.isDisplayed())
            System.err.println(String.format("Cannot found %s in the left menu",sport));
        btnSport.click();
        waitForSpinnerLoading();
    }

    public void selectMarketOnLeftMenu(String marketName) {
        Button btnMarket = Button.xpath(String.format(marketLeftMenuXpath, marketName));
        if(!btnMarket.isDisplayed())
            System.err.println(String.format("Cannot found %s in the left menu",marketName));
        btnMarket.click();
        waitForSpinnerLoading();
    }

    public Market getEventInfo(String sportName, String oddsType, String marketType, boolean isFullMatch) {
        int leagueIndex = 1;
        Market market;
        while (true)
        {
            market = getEventInfo(sportName,oddsType,leagueIndex, marketType, isFullMatch);
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
    public Market getEventInfo(String sportName, String oddsType,int leagueIndex, String marketType, boolean isFullMatch) {
        int eventIndex = 1;
        Market market;
        while (true)
        {
            market = getEventInfo(sportName,oddsType,leagueIndex,eventIndex, marketType, isFullMatch);
            if(Objects.nonNull(market))
                return market;
            eventIndex = eventIndex + 1;
        }
    }

    /**
     * Get the market available under a league with odds condition is negative or positive
     * @param
     * @return
     */
    public Market getEventInfo(String sportName, String oddsType, String marketType, boolean isFullMatch, boolean isNegativeOdds) {
        int leagueIndex = 1;
        Market market;
        while (true)
        {
            market = getEventInfo(sportName,oddsType,leagueIndex, marketType, isFullMatch, isNegativeOdds);
            if(Objects.nonNull(market))
                return market;
            leagueIndex = leagueIndex + 1;
        }
    }

    /**
     * Get event info with odds type = Decimal odds bases on inputed index, if index = 0 mean get random
     * @param leagueIndex
     * @param eventIndex
     * @return a Market Info
     */
    private Market getEventInfo(String sportName, String oddsType, int leagueIndex, int eventIndex, String marketType, boolean isFullMatch) {
        Market market;
        Label lblLeague = Label.xpath(String.format(leagueIndexXpath,leagueIndex));
        if (!lblLeague.isDisplayed())
            return null;
        // get event id from UI xpath property
        String eventID = Label.xpath(String.format(firstOddsCellXpath,leagueIndex, defineOddsColumn(marketType, isFullMatch))).getAttribute("eventid");
        String oddsKey = Label.xpath(String.format(firstOddsCellXpath,leagueIndex, defineOddsColumn(marketType, isFullMatch))).getAttribute("key");


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
    /**
     * Get event info of odds condition negative or positive bases on inputted league index
     * @param leagueIndex
     * @return a Market Info
     */
    private Market getEventInfo(String sportName, String oddsType, int leagueIndex, String marketType, boolean isFullMatch, boolean isNegativeOdds) {
        Market market;
        Label lblLeague = Label.xpath(String.format(leagueIndexXpath,leagueIndex));
        if (!lblLeague.isDisplayed())
            return null;
        // get event id from UI xpath property
        String eventID = Label.xpath(String.format(firstOddsCellXpath,leagueIndex, defineOddsColumn(marketType, isFullMatch))).getAttribute("eventid");
        String oddsKey = Label.xpath(String.format(firstOddsCellXpath,leagueIndex, defineOddsColumn(marketType, isFullMatch))).getAttribute("key");

        //handle incase no odds display in the UI, move to the next row
        if(Objects.isNull(eventID)) {
            return null;
        }
        String leagueName = Label.xpath(String.format(leagueNameXpath,leagueIndex)).getText();
        // Get the market info from API with the eventID get from UI
        market = MarketUtils.getMarketByOddsKey(oddsType,Integer.valueOf(eventID),oddsKey, isNegativeOdds);
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

    public Market getEventInfoUI(Market marketBase, boolean isFullMatch) {
        Market market = new Market.Builder().build();
        List<Odds> lstOddsObject = new ArrayList<>();
        String rootXpath = String.format(tableEventXpath, marketBase.getEventId());
        Table tblEvent = Table.xpath(rootXpath, 7);
        Label lblLeague = Label.xpath(String.format("%s%s", tblEvent.getLocator().toString().replace("By.xpath: ",""),"//ancestor::app-league-asian//div[contains(@class,'league-name')]"));
        Label lblEventStartTime = Label.xpath(String.format("%s%s", rootXpath, "//th[contains(@class,'time-column')]"));
        Label lblHomeName = Label.xpath(String.format("%s%s", rootXpath, "//th[contains(@class,'opponent')]//div[contains(@class,'weak-team')]"));
        Label lblAwayName = Label.xpath(String.format("%s%s", rootXpath, "//th[contains(@class,'opponent')]//div[contains(@class,'strong-team')]"));
        Label lblOdds = Label.xpath(String.format("(%s%s)[%d]%s", rootXpath, "//th[contains(@class,'odd-column')]", defineOddsColumn(MARKET_CODE_MAPPING.get(marketBase.getBetType()), isFullMatch), "//span[contains(@class,'odd-number')]"));
        //add odds from UI to list odds object
        for (int i = 0; i < lblOdds.getWebElements().size(); i++) {
            Label lblOddsValue = Label.xpath(String.format("(%s)[%s]", lblOdds.getLocator().toString().replace("By.xpath: ", ""), i + 1));
            String oddsValue = lblOddsValue.getText();
            //handle special character " -+" from UI cannot add to list double
            if(oddsValue.contains("⠀") || oddsValue.contains("−") || oddsValue.contains("+")) {
                oddsValue = oddsValue.replace("⠀","");
                oddsValue = oddsValue.replace("−","-");
                oddsValue = oddsValue.replace("+","+");
            }
            lstOddsObject.add(i, new Odds.Builder().odds(Double.parseDouble(oddsValue)).build());
        }
        market.setOdds(lstOddsObject);
        market.setLeagueName(lblLeague.getText().trim());
        market.setEventStartTime(lblEventStartTime.getText().trim());
        market.setEventName(String.format("%s v %s", lblHomeName.getText().trim(), lblAwayName.getText().trim()));
        return market;

    }

    private int defineOddsColumn(String marketType, boolean isFullMatch){
        if(isFullMatch) {
            if (marketType.equalsIgnoreCase("MONEYLINE")) {
                return 1;
            } else if (marketType.equalsIgnoreCase("HANDICAP") || marketType.equalsIgnoreCase("SPREAD")) {
                return 2;
            } else {
                return 3;
            }
        } else {
            if (marketType.equalsIgnoreCase("MONEYLINE")) {
                return 4;
            } else if (marketType.equalsIgnoreCase("HANDICAP") || marketType.equalsIgnoreCase("SPREAD")) {
                return 5;
            } else {
                return 6;
            }
        }
    }

    public void verifyOddsShowCorrect(String oddsType, String oddsGroup, Market marketBase, boolean isFullMatch) {
        Market marketUI = getEventInfoUI(marketBase, isFullMatch);
        List<Odds> lstOddsUI = marketUI.getOdds();
        List<Odds> lstOddsApiConverted = marketBase.getConvertedOddsByGroup(oddsType, oddsGroup);
        //sort before verifying cause order odds between API and UI is different HOME/DRAW/AWAY > HOME/AWAY/DRAW
        lstOddsUI.sort(Comparator.comparingDouble(Odds::getOdds));
        lstOddsApiConverted.sort(Comparator.comparingDouble(Odds::getOdds));
        if(oddsType.equalsIgnoreCase("American")) {
            for (int i = 0; i < lstOddsUI.size(); i++) {
                Assert.assertEquals(lstOddsUI.get(i).getOdds(),  Double.valueOf(String.format("%.0f",lstOddsApiConverted.get(i).getOdds())), "FAILED! Odds does not show correct expected");
            }
        } else {
            for (int i = 0; i < lstOddsUI.size(); i++) {
                Assert.assertEquals(lstOddsUI.get(i).getOdds(), Double.valueOf(String.format("%.3f", lstOddsApiConverted.get(i).getOdds())), "FAILED! Odds does not show correct expected");
            }
        }
    }

    public void openMoreMarkets(Market market) {
        int eventId = market.getEventId();
        Label lblMoreMarket = Label.xpath(String.format(moreMarketXpath, eventId));
        if(lblMoreMarket.isDisplayed()) {
            lblMoreMarket.click();
            waitForSpinnerLoading();
        }
    }

    public void selectMoreMarket(String marketName) {
        for (int i = 0; i < lblMoreMarketDetails.getWebElements().size(); i++) {
            String xpath = lblMoreMarketDetails.getLocator().toString().replace("By.xpath: ","") + String.format("[%s]", i + 1);
            Label lblMarketName = Label.xpath(xpath);
            if(lblMarketName.getText().trim().contains(marketName)) {
                lblMarketName.click();
            }
        }
    }
    public List<Market> getEventInfoMoreMarket(String eventId, String oddsType, String marketType) {
        int leagueIndex = 1;
        List<Market> lstMarket;
        while (true)
        {
            lstMarket = getEventInfoMoreMarket(eventId,oddsType, marketType, true);
            if(Objects.nonNull(lstMarket))
                return lstMarket;
            leagueIndex = leagueIndex + 1;
        }
    }

    /**
     * Get event info with odds type = Decimal odds bases on inputed index, if index = 0 mean get random
     * @param
     * @return a Market Info
     */
    public List<Market> getEventInfoMoreMarket(String eventId, String oddsType, String marketType, boolean isFullMatch) {
        return MarketUtils.getListMarketByMarketType(oddsType,Integer.valueOf(eventId), marketType, isFullMatch);
    }

    public void verifyMoreMarketOddsCorrect(Market market, String oddsType, String oddsGroup, String marketType, boolean isFullMatch) {
        String eventId = String.valueOf(market.getEventId());
        //always get odds from DEC as base odds to calculate for other odds type
        List<Market> lstMarket = getEventInfoMoreMarket(eventId, DECIMAL, marketType, isFullMatch);
        //handle for Team Totals markets
        if(marketType.equalsIgnoreCase(MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL))) {
            verifyTeamTotalOddsMoreMarket(lstMarket, oddsType, oddsGroup);
        }
        //handle for HDP and OverUnder markets
        else {
            //handle for HDP market
            if(marketType.equalsIgnoreCase(MARKET_TYPE_MAPPING.get(TEXT_HDP))) {
                verifyHandicapOddsMoreMarket(lstMarket, oddsType, oddsGroup);
            }
            //handle for OverUnder market
            else if (marketType.equalsIgnoreCase(MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER))) {
                verifyOverUnderOddsMoreMarket(lstMarket, oddsType, oddsGroup);
            }
        }
    }

    private int defineOddsMoreMarketColumn(String marketType, String selection){
        if(marketType.equalsIgnoreCase(MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL))) {
            if (selection.equalsIgnoreCase("HOME OVER")) {
                return 2;
            } else if (selection.equalsIgnoreCase("HOME UNDER")) {
                return 3;
            } else if (selection.equalsIgnoreCase("AWAY OVER")){
                return 5;
            } else {
                return 6;
            }
        } else if (marketType.equalsIgnoreCase(MARKET_TYPE_MAPPING.get(TEXT_HDP))){
            if (selection.equalsIgnoreCase("HOME")) {
                return 3;
            } else {
                return 4;
            }
        } else {
            if (selection.equalsIgnoreCase("OVER")) {
                return 6;
            } else {
                return 7;
            }
        }
    }

    public void verifyTeamTotalOddsMoreMarket(List<Market> lstMarket, String oddsType, String oddsGroup) {
        String xpathRow = "(//app-market-asian//table//tbody)[%s]//td[%s]/span";
        for (int i = 0; i < lstMarket.size(); i++) {
            List<Odds> lstOdds = lstMarket.get(i).getConvertedOddsByGroup(oddsType, oddsGroup);
            //verify for HOME section
            if(lstMarket.get(i).getTeam().equalsIgnoreCase("HOME")) {
                Label lblOddsHomeOver = Label.xpath(String.format(xpathRow, 1, defineOddsMoreMarketColumn(MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), "HOME OVER")));
                Label lblOddsHomeUnder = Label.xpath(String.format(xpathRow, 1, defineOddsMoreMarketColumn(MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), "HOME UNDER")));
                String oddsHomeOver = lblOddsHomeOver.getText();
                String oddsHomeUnder = lblOddsHomeUnder.getText();
                //handle to replace special character from UI for negative/positive odds actual −253/ 253 , expected -253/ 253 but return assert failed
                if(oddsType.equalsIgnoreCase("Malay") || oddsType.equalsIgnoreCase("American")) {
                    oddsHomeOver = oddsHomeOver.replace("⠀","");
                    oddsHomeOver = oddsHomeOver.replace("−","-");
                    oddsHomeOver = oddsHomeOver.replace("+","+");
                    oddsHomeUnder = oddsHomeUnder.replace("⠀","");
                    oddsHomeUnder = oddsHomeUnder.replace("−","-");
                    oddsHomeUnder = oddsHomeUnder.replace("+","+");
                }
                for (Odds o: lstOdds
                ) {
                    //handle for AM > odds does not show decimal place and list odds return does not have "+"
                    if(oddsType.equalsIgnoreCase("American")) {
                        String oddsExpected;
                        if(o.getOdds() >= 0) {
                            oddsExpected = String.format("%s%.0f","+",o.getOdds());
                        } else {
                            oddsExpected = String.format("%.0f",o.getOdds());
                        }
                        if(o.getSide().equalsIgnoreCase("OVER")) {
                            Assert.assertEquals(oddsExpected, oddsHomeOver, String.format("FAILED! Odds not show correct expected %s actual %s", oddsExpected, oddsHomeOver));
                        } else {
                            Assert.assertEquals(oddsExpected, oddsHomeUnder, String.format("FAILED! Odds not show correct expected %s actual %s", oddsExpected, oddsHomeUnder));
                        }
                    } else {
                        if(o.getSide().equalsIgnoreCase("OVER")) {
                            Assert.assertEquals(String.format("%.3f",o.getOdds()), oddsHomeOver, String.format("FAILED! Odds not show correct expected %s actual %s", String.format("%.3f",o.getOdds()), oddsHomeOver));
                        } else {
                            Assert.assertEquals(String.format("%.3f",o.getOdds()), oddsHomeUnder, String.format("FAILED! Odds not show correct expected %s actual %s", String.format("%.3f",o.getOdds()), oddsHomeUnder));
                        }
                    }

                }
            }
            //verify for AWAY section
            else {
                Label lblOddsAwayOver = Label.xpath(String.format(xpathRow, 1, defineOddsMoreMarketColumn(MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), "AWAY OVER")));
                Label lblOddsAwayUnder = Label.xpath(String.format(xpathRow, 1, defineOddsMoreMarketColumn(MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), "AWAY UNDER")));
                String oddsAwayOver = lblOddsAwayOver.getText();
                String oddsAwayUnder = lblOddsAwayUnder.getText();
                //handle to replace special character from UI for negative/positive odds actual −253/ 253/ +253 , expected -253/ 253/ +253 but return assert failed
                if(oddsType.equalsIgnoreCase("Malay") || oddsType.equalsIgnoreCase("American")) {
                    oddsAwayOver = oddsAwayOver.replace("⠀","");
                    oddsAwayOver = oddsAwayOver.replace("−","-");
                    oddsAwayOver = oddsAwayOver.replace("+","+");
                    oddsAwayUnder = oddsAwayUnder.replace("⠀","");
                    oddsAwayUnder = oddsAwayUnder.replace("−","-");
                    oddsAwayUnder = oddsAwayUnder.replace("+","+");

                }
                for (Odds o: lstOdds
                ) {
                    //handle for AM > odds does not show decimal place and list odds return does not have "+"
                    if(oddsType.equalsIgnoreCase("American")) {
                        String oddsExpected;
                        if(o.getOdds() >= 0) {
                            oddsExpected = String.format("%s%.0f","+",o.getOdds());
                        } else {
                            oddsExpected = String.format("%.0f",o.getOdds());
                        }
                        if(o.getSide().equalsIgnoreCase("OVER")) {
                            Assert.assertEquals(oddsExpected, oddsAwayOver, String.format("FAILED! Odds not show correct expected %s actual %s", oddsExpected, oddsAwayOver));
                        } else {
                            Assert.assertEquals(oddsExpected, oddsAwayUnder, String.format("FAILED! Odds not show correct expected %s actual %s", oddsExpected, oddsAwayUnder));
                        }
                    } else {
                        if(o.getSide().equalsIgnoreCase("OVER")) {
                            Assert.assertEquals(String.format("%.3f",o.getOdds()), oddsAwayOver, String.format("FAILED! Odds not show correct expected %s actual %s", String.format("%.3f",o.getOdds()), oddsAwayOver));
                        } else {
                            Assert.assertEquals(String.format("%.3f",o.getOdds()), oddsAwayUnder, String.format("FAILED! Odds not show correct expected %s actual %s", String.format("%.3f",o.getOdds()), oddsAwayUnder));
                        }
                    }
                }
            }
        }
    }

    public void verifyHandicapOddsMoreMarket(List<Market> lstMarket, String oddsType, String oddsGroup) {
        String xpathRow = "(//app-market-asian//table//tbody)[%s]//td[%s]/span";
        lstMarket.sort(Comparator.comparingDouble(Market::getHandicap));
        for (int i = 0; i < tblMoreMarket.getWebElements().size(); i++) {
            //verify for HDP market
            Label lblOddsHome = Label.xpath(String.format(xpathRow, i + 1, defineOddsMoreMarketColumn(MARKET_TYPE_MAPPING.get(TEXT_HDP), "HOME")));
            Label lblOddsAway = Label.xpath(String.format(xpathRow, i + 1, defineOddsMoreMarketColumn(MARKET_TYPE_MAPPING.get(TEXT_HDP), "AWAY")));
            String oddsHome = lblOddsHome.getText();
            String oddsAway = lblOddsAway.getText();
            //handle to replace special character from UI for negative/positive odds actual −253/ 253 , expected -253/ 253 but return assert failed
            if(oddsType.equalsIgnoreCase("Malay") || oddsType.equalsIgnoreCase("American")) {
                oddsHome = oddsHome.replace("⠀","");
                oddsHome = oddsHome.replace("−","-");
                oddsHome = oddsHome.replace("+","+");
                oddsAway = oddsAway.replace("⠀","");
                oddsAway = oddsAway.replace("−","-");
                oddsAway = oddsAway.replace("+","+");
            }
            if(oddsHome.isEmpty() && oddsAway.isEmpty()) {
                System.out.println("The current record have no odds for verifying");
                break;
            }
            List<Odds> lstOdds = lstMarket.get(i).getConvertedOddsByGroup(oddsType, oddsGroup);
            for (Odds o : lstOdds
            ) {
                //handle for AM > odds does not show decimal place and list odds return does not have "+"
                if(oddsType.equalsIgnoreCase("American")) {
                    String oddsExpected;
                    if (o.getOdds() >= 0) {
                        oddsExpected = String.format("%s%.0f", "+", o.getOdds());
                    } else {
                        oddsExpected = String.format("%.0f", o.getOdds());
                    }
                    if (o.getTeam().equalsIgnoreCase("HOME")) {
                        Assert.assertEquals(oddsExpected, oddsHome, String.format("FAILED! Odds not show correct expected %s actual %s", oddsExpected, oddsHome));
                    } else {
                        Assert.assertEquals(oddsExpected, oddsAway, String.format("FAILED! Odds not show correct expected %s actual %s", oddsExpected, oddsAway));
                    }
                } else {
                    if (o.getTeam().equalsIgnoreCase("HOME")) {
                        Assert.assertEquals(String.format("%.3f", o.getOdds()), oddsHome, String.format("FAILED! Odds not show correct expected %s actual %s", String.format("%.3f", o.getOdds()), oddsHome));
                    } else {
                        Assert.assertEquals(String.format("%.3f", o.getOdds()), oddsAway, String.format("FAILED! Odds not show correct expected %s actual %s", String.format("%.3f", o.getOdds()), oddsAway));
                    }
                }

            }
        }
    }

    public void verifyOverUnderOddsMoreMarket(List<Market> lstMarket, String oddsType, String oddsGroup) {
        String xpathRow = "(//app-market-asian//table//tbody)[%s]//td[%s]/span";
        lstMarket.sort(Comparator.comparingDouble(Market::getHandicap).reversed());
        for (int i = 0; i < tblMoreMarket.getWebElements().size(); i++) {
            //verify for OverUnder market
            Label lblOddsOver = Label.xpath(String.format(xpathRow, i+1, defineOddsMoreMarketColumn(MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), "OVER")));
            Label lblOddsUnder = Label.xpath(String.format(xpathRow, i+1, defineOddsMoreMarketColumn(MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), "UNDER")));
            String oddsOver = lblOddsOver.getText();
            String oddsUnder = lblOddsUnder.getText();
            //handle to replace special character from UI for negative/positive odds actual −253/ 253 , expected -253/ 253 but return assert failed
            if(oddsType.equalsIgnoreCase("Malay") || oddsType.equalsIgnoreCase("American")) {
                oddsOver = oddsOver.replace("⠀","");
                oddsOver = oddsOver.replace("−","-");
                oddsOver = oddsOver.replace("+","+");
                oddsUnder = oddsUnder.replace("⠀","");
                oddsUnder = oddsUnder.replace("−","-");
                oddsUnder = oddsUnder.replace("+","+");
            }
            if(oddsOver.isEmpty() && oddsUnder.isEmpty()) {
                System.out.println("The current record have no odds for verifying");
                break;
            }
            List<Odds> lstOdds = lstMarket.get(i).getConvertedOddsByGroup(oddsType, oddsGroup);
            for (Odds o : lstOdds
            ) {
                //handle for AM > odds does not show decimal place and list odds return does not have "+"
                if(oddsType.equalsIgnoreCase("American")) {
                    String oddsExpected;
                    if (o.getOdds() >= 0) {
                        oddsExpected = String.format("%s%.0f", "+", o.getOdds());
                    } else {
                        oddsExpected = String.format("%.0f", o.getOdds());
                    }
                    if (o.getSide().equalsIgnoreCase("OVER")) {
                        Assert.assertEquals(oddsExpected, oddsOver, String.format("FAILED! Odds not show correct expected %s actual %s",oddsExpected, oddsOver));
                    } else {
                        Assert.assertEquals(oddsExpected, oddsUnder, String.format("FAILED! Odds not show correct expected %s actual %s", oddsExpected, oddsUnder));
                    }
                } else {
                    if (o.getSide().equalsIgnoreCase("OVER")) {
                        Assert.assertEquals(String.format("%.3f", o.getOdds()), oddsOver, String.format("FAILED! Odds not show correct expected %s actual %s",String.format("%.3f", o.getOdds()), oddsOver));
                    } else {
                        Assert.assertEquals(String.format("%.3f", o.getOdds()), oddsUnder, String.format("FAILED! Odds not show correct expected %s actual %s", String.format("%.3f", o.getOdds()), oddsUnder));
                    }
                }

            }
        }
    }

    public Order addOddToBetSlipAndPlaceBet(Market market, String selection, boolean isFullMatch, String stake, boolean isAcceptBetterOdds, boolean isPlace){
        // click odds
        clickOdds(market, isFullMatch);
        //input stake and click place bet and confirm
        Order order = placeNoBet(market,stake,isAcceptBetterOdds,isPlace);
        // set Odd info of the team name that placed on
        order.setOdds(market.getOddsInfoBySelection(selection));
        return order;
    }

    public Order addOddToBetSlipAndPlaceBet(Market market, boolean isFullMatch, String stake, boolean isAcceptBetterOdds, boolean isPlace){
        // click odds
        clickOdds(market, isFullMatch);
        //input stake and click place bet and confirm
        Order order = placeNoBet(market,stake,isAcceptBetterOdds,isPlace);
        if (isPlace) {
            // set Odd info of the team name that placed on
            order.setOdds(market.getOdds().get(0));
            return order;
        } else {
            return null;
        }
    }

    public void clickOdds(Market market, boolean isFullMatch){
        //handle when get negative/positive odds we base on odds team to know which selection should click
        int rowIndex = 0;
        String oddsHomeXpath ="//th[contains(@class,'odd-column')][%s]//span[contains(@class,'odd-number')]";
        if(market.getOdds().get(0).getTeam().equalsIgnoreCase("HOME")) {
            rowIndex = 1;
        } else {
            rowIndex = 2;
        }
        // get the row index has expected ID
        String eventXpath = getEventIndexXpath(String.valueOf(market.getEventId()));
        int column = defineOddsColumn(market.getBetType(),isFullMatch);
        oddsHomeXpath = String.format(oddsHomeXpath,column);
        Label lblOdds = Label.xpath(String.format("(%s%s)[%s]",eventXpath,oddsHomeXpath, rowIndex));
        lblOdds.click();
    }

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
                return String.format("//app-league-asian[%d]//app-event-item-parent[%d]",leagueIndex,eventIndex);
            leagueIndex = leagueIndex +1;
        }
    }
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

    public void verifySearchByLeagueDropdownCorrect(String leagueName) {
        List<String> lstSearchResult = getListSearchResult();
        for (int i = 0; i < lstSearchResult.size(); i++) {
            Assert.assertTrue(lstSearchResult.get(i).toUpperCase().contains(leagueName.trim()),String.format("FAILED! List search result %s does not contain all input value %s", lstSearchResult.get(i), leagueName.trim()));
        }
    }

    public void verifyMaxPerMatchShowCorrect(Market market, double settingMaxPerMatch, String oddsType, boolean isNegativeOdds) {
        String rootXpath = "(//app-bet-slip//div[contains(@class,'bet-slip-item')])[%s]";
        String betslipXpath = String.format(rootXpath,1);
//        String minBet = Label.xpath(String.format("%s%s", betslipXpath,lblMinBetXpath)).getText();
//        String maxBet = Label.xpath(String.format("%s%s", betslipXpath,lblMaxBetXpath)).getText();
        String matchMax = Label.xpath(String.format("%s%s", betslipXpath,lblMatchMaxXpath)).getText();
        if(isNegativeOdds) {
            double oddsValue;
            double matchMaxExpected;
            if(oddsType.equalsIgnoreCase("American")) {
                oddsValue = Double.parseDouble(String.valueOf(market.getOdds().get(0).getOdds()));
                matchMaxExpected = (settingMaxPerMatch / oddsValue) * 100;
                Assert.assertEquals(Double.valueOf(matchMax), Double.valueOf(Math.floor(Math.abs(matchMaxExpected) * 100) / 100), 0.01, String.format("FAILED! Max Per Match does not show correct expected %s actual %s", matchMax, matchMaxExpected));
            } else if (oddsType.equalsIgnoreCase("Malay")) {
                oddsValue = Double.parseDouble(String.valueOf(market.getOdds().get(0).getOdds()));
                matchMaxExpected = settingMaxPerMatch / oddsValue;
                Assert.assertEquals(Double.valueOf(matchMax), Double.valueOf(Math.floor(Math.abs(matchMaxExpected) * 100) / 100), 0.01, String.format("FAILED! Max Per Match does not show correct expected %s actual %s", matchMax, matchMaxExpected));
            } else {
                Assert.assertEquals(matchMax, String.format("%.2f", settingMaxPerMatch), String.format("FAILED! Max Per Match does not show correct expected %s actual %s", matchMax, settingMaxPerMatch));
            }
        } else {
            if(oddsType.equalsIgnoreCase("American")) {
                Assert.assertEquals(matchMax, String.format("%.2f", settingMaxPerMatch), String.format("FAILED! Max Per Match does not show correct expected %s actual %s", matchMax, settingMaxPerMatch));
            } else {
                Assert.assertEquals(matchMax, String.format("%.2f", settingMaxPerMatch), String.format("FAILED! Max Per Match does not show correct expected %s actual %s", matchMax, settingMaxPerMatch));
            }
        }
    }
}
