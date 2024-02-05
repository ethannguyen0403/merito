package membersite.pages.proteus;

import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import com.paltech.utils.DateUtils;
import controls.Table;
import membersite.controls.DropDownMenu;
import membersite.controls.Row;
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
    public Label lblLoading = Label.xpath("//div[contains(@class,'loading')]");
    public Button btnEarlyAsian =  Button.xpath("//app-left-menu-asian//button[text()=' EARLY ']");
    public Button btnTodayAsian =  Button.xpath("//app-left-menu-asian//button[text()=' TODAY ']");
    public Button btnLiveAsian =  Button.xpath("//app-left-menu-asian//button[text()=' LIVE ']");
    public DropDownMenu ddmOddsType = DropDownMenu.xpath("//app-event-filter-desktop//ul[contains(@class,'control-list')]/li[4]","span[@class='mx-2 text-capitalize']","//ul[contains(@class,'sub-selections')]//li");
    public DropDownMenu ddmDateTime = DropDownMenu.xpath("//app-event-filter-desktop//ul[contains(@class,'control-list')]/li[1]","span[@class='mx-2 text-capitalize']","//ul[contains(@class,'sub-selections')]//li");
    public DropDownMenu ddmLeagues = DropDownMenu.xpath("//app-event-filter-desktop//ul[contains(@class,'control-list')]/li[5]","span[@class='me-2 text-capitalize']","//div[@class='leagues-box']");
    public DropDownMenu ddmSortBy = DropDownMenu.xpath("//app-event-filter-desktop//ul[contains(@class,'control-list')]/li[3]","span[@class='mx-2 text-capitalize']","//ul[contains(@class,'sub-selections')]//li");
    private Table tblMoreMarket = Table.xpath("//app-market-asian//table[contains(@class,'market-table')]", 7);
    private Table tblFirstEvent = Table.xpath("(//app-league-asian//table[contains(@class,'odds-page')])[1]", 10);
    private Label lblFirstLeague = Label.xpath("(//app-league-asian//div[contains(@class,'league-name')])[1]");
    private Label lblFirstEventAwayName = Label.xpath("(//app-league-asian//div[contains(@class,'weak-team')])[1]");
    private Label lblFirstEventHomeName = Label.xpath("(//app-league-asian//div[contains(@class,'strong-team')])[1]");
    private String firstSelectionXpath = "((//app-league-asian//th[contains(@class,'odd-column')])[%s]//span[contains(@class,'odd-number')])[1]";
    private String secondSelectionXpath =  "((//app-league-asian//th[contains(@class,'odd-column')])[%s]//span[contains(@class,'odd-number')])[2]";
    private Label lblThirdSelection =  Label.xpath("((//app-league-asian//th[contains(@class,'odd-column')])[1]//span[contains(@class,'odd-number')])[3]");
    private String firstHDPXpath = "((//app-league-asian//table)[1]//div[contains(@class,'normal hdp')])[%s]";
//    private Table tblMoreMarket = Table.xpath("//app-market-asian//div[@class='market-detail']/div[2]//table[contains(@class,'table market')]", 6);
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

    public void selectSportLeftMenu(String sportName) {
        Button btnSport = Button.xpath(String.format(sportLeftMenuXpath, sportName));
        btnSport.click();
        waitForSpinnerLoading();
    }
    public void selectOddsType (String oddsType){
        oddsType = String.format(" %s ",oddsType);
        ddmOddsType.clickSubMenu(oddsType);
        waitForSpinnerLoading();
    }

    public ProteusGeneralEvent getFirstEventInfo(String marketType) {
        double hdp;
        ProteusGeneralEvent proteusGeneralEvent = new ProteusGeneralEvent.Builder().build();
        proteusGeneralEvent.setEventId(Integer.valueOf(tblFirstEvent.getAttribute("eventid")));
        proteusGeneralEvent.setLeagueName(lblFirstLeague.getText().trim());
        proteusGeneralEvent.setHomeName(lblFirstEventHomeName.getText().trim());
        proteusGeneralEvent.setAwayName(lblFirstEventAwayName.getText().trim());
        if(marketType.equalsIgnoreCase("1x2")) {
            Label lblFirstSelection = Label.xpath(String.format(firstSelectionXpath, 1));
            Label lblSecondSelection = Label.xpath(String.format(secondSelectionXpath, 1));
            proteusGeneralEvent.setBtnFirstSelection(lblFirstSelection);
            proteusGeneralEvent.setBtnSecondSelection(lblSecondSelection);
            proteusGeneralEvent.setBtnThirdSelection(lblThirdSelection);
        } else if (marketType.equalsIgnoreCase("Handicap")) {
            Label lblFirstSelection = Label.xpath(String.format(firstSelectionXpath, 2));
            Label lblSecondSelection = Label.xpath(String.format(secondSelectionXpath, 2));
            Label lblFirstHDP = Label.xpath(String.format(firstHDPXpath, 1));
            proteusGeneralEvent.setBtnFirstSelection(lblFirstSelection);
            proteusGeneralEvent.setBtnSecondSelection(lblSecondSelection);
            String hdpText = lblFirstHDP.getText().trim().replaceAll("[\nu]","");
            if(hdpText.contains("-")) {
                String[] lstHdp = hdpText.split("-");
                double firstHDP = Double.parseDouble(lstHdp[0].trim());
                double secondHDP = Double.parseDouble(lstHdp[1].trim());
                hdp = 0 - ((firstHDP + secondHDP)/2);
                proteusGeneralEvent.setHDPPoint(String.valueOf(hdp));
            } else {
                proteusGeneralEvent.setHDPPoint(hdpText);
            }
        } else {
            Label lblFirstSelection = Label.xpath(String.format(firstSelectionXpath, 3));
            Label lblSecondSelection = Label.xpath(String.format(secondSelectionXpath, 3));
            Label lblFirstHDP = Label.xpath(String.format(firstHDPXpath, 2));
            proteusGeneralEvent.setBtnFirstSelection(lblFirstSelection);
            proteusGeneralEvent.setBtnSecondSelection(lblSecondSelection);
            String hdpText = lblFirstHDP.getText().trim().replaceAll("[\nu]","");
            if(hdpText.contains("-")) {
                String[] lstHdp = hdpText.split("-");
                double firstHDP = Double.parseDouble(lstHdp[0].trim());
                double secondHDP = Double.parseDouble(lstHdp[1].trim());
                hdp = (firstHDP + secondHDP)/2;
                proteusGeneralEvent.setHDPPoint(String.valueOf(hdp));
            } else {
                proteusGeneralEvent.setHDPPoint(hdpText);
            }
        }
        return proteusGeneralEvent;
    }

    public List<Double> getListOddsFirstEvent(ProteusGeneralEvent event, String marketType) {
        List<Double> lstOdds = new ArrayList<>();
        if(marketType.equalsIgnoreCase("1x2")) {
            lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText().replaceAll("[⠀−+]","")));
            lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText().replaceAll("[⠀−+]","")));
            lstOdds.add(Double.valueOf(event.getBtnThirdSelection().getText().replaceAll("[⠀−+]","")));
        } else {
            lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText().replaceAll("[⠀−+]","")));
            lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText().replaceAll("[⠀−+]","")));
        }
        return lstOdds;
    }

    public ProteusTeamTotalEvent getFirstMatchTeamTotalEventInfo() {
        ProteusTeamTotalEvent proteusTeamTotalEvent = new ProteusTeamTotalEvent.Builder().build();
        proteusTeamTotalEvent.setEventId(Integer.valueOf(tblFirstEvent.getAttribute("eventid")));
        proteusTeamTotalEvent.setLeagueName(lblFirstLeague.getText().trim());
        proteusTeamTotalEvent.setHomeName(lblFirstEventHomeName.getText().trim());
        proteusTeamTotalEvent.setAwayName(lblFirstEventAwayName.getText().trim());
        ArrayList<String> lstRow = tblMoreMarket.getRow(1);
        if(Objects.nonNull(lstRow)) {
            proteusTeamTotalEvent.setHomeGoals(Double.valueOf(lstRow.get(0)));
            proteusTeamTotalEvent.setHomeOver(Double.valueOf(lstRow.get(1).replaceAll("[⠀−+]","")));
            proteusTeamTotalEvent.setHomeUnder(Double.valueOf(lstRow.get(2).replaceAll("[⠀−+]","")));
            proteusTeamTotalEvent.setAwayGoals(Double.valueOf(lstRow.get(3)));
            proteusTeamTotalEvent.setAwayOver(Double.valueOf(lstRow.get(4).replaceAll("[⠀−+]","")));
            proteusTeamTotalEvent.setAwayUnder(Double.valueOf(lstRow.get(5).replaceAll("[⠀−+]","")));
        }
        return proteusTeamTotalEvent;
    }

    public String selectFirstNegativeOdds() {
        Label lblFirstOdds = Label.xpath("(//span[contains(@class,'color-negative-odd')])[1]/span");
        Row rowEventSection = Row.xpath("(//span[contains(@class,'color-negative-odd')])[1]/span//ancestor::th");
        lblFirstOdds.click();
        return rowEventSection.getAttribute("eventid");
    }

    public String selectFirstPositiveOdds() {
        Label lblFirstOdds = Label.xpath("(//span[contains(@class,'odd-number')]//span[not(contains(text(),'−'))])[1]");
        Row rowEventSection = Row.xpath("(//span[contains(@class,'odd-number')]//span[not(contains(text(),'−'))])[1]//ancestor::th");
        lblFirstOdds.click();
        return rowEventSection.getAttribute("eventid");
    }

    public void placeBet(String stake, boolean isSubmit) {
        txtStake.sendKeys(stake);
        if(isSubmit) {
            btnPlaceBet.jsClick();
            btnOK.waitForElementToBePresent(btnOK.getLocator());
            btnOK.jsClick();
            waitForSpinnerLoading();
        }
    }

    public ProteusBetslip getFirstPendingBetSlipInfo() {
        String firstBetXpath = "(//app-bet-slip//app-pending-bets//div[contains(@class,'pending-item')])[1]";
        Label lblEventName = Label.xpath(String.format("%s%s", firstBetXpath, "//div[contains(@class,'event-name')]"));
        Label lblSummaryInfo = Label.xpath(String.format("%s%s", firstBetXpath, "//div[contains(@class,'league-name')]"));
        Label lblOdds = Label.xpath(String.format("%s%s", firstBetXpath, "//span[contains(@class,'odd')]"));
        Label lblStake = Label.xpath(String.format("%s%s", firstBetXpath, "//span[text()='Stake: ']//following-sibling::span"));
        Label lblRisk = Label.xpath(String.format("%s%s", firstBetXpath, "//span[text()='Risk: ']//following-sibling::span"));
        Label lblWin = Label.xpath(String.format("%s%s", firstBetXpath, "//span[text()='Win: ']//following-sibling::span"));
        String[] oddsSplit = lblOdds.getText().trim().split("@");
        String[] odds = oddsSplit[1].split(" ");
        String[] stake = lblStake.getText().trim().split("HKD");
        String[] risk = lblRisk.getText().trim().split("HKD");
        String[] win = lblWin.getText().trim().split("HKD");
        return new ProteusBetslip.Builder().eventName(lblEventName.getText().trim())
                .summaryEventInfo(lblSummaryInfo.getText().trim())
                .odds(odds[0])
                .stake(stake[1].trim())
                .toRisk(risk[1].trim())
                .toWin(win[1].trim())
                .build();
    }

    public void verifyToRiskToWinCorrect(String stake, String odds, String oddsType) {
        ProteusBetslip betslip = getFirstPendingBetSlipInfo();
        List<Double> lstRiskWin = calculateToRiskToWin(stake, odds, oddsType);
        Assert.assertEquals(Double.valueOf(betslip.getToRisk()), lstRiskWin.get(0), 0.01, "FAILED!");
        Assert.assertEquals(Double.valueOf(betslip.getToWin()), lstRiskWin.get(1), 0.01, "FAILED!");
    }

    public List<Double> calculateToRiskToWin(String stake, String odds, String oddsType) {
        List<Double> lstRiskWin = new ArrayList<>();
        if(oddsType.equalsIgnoreCase("Malay")) {
            if(odds.contains("−")) {
                String oddsFormat = odds.replace("−","");
                double toRisk = Math.floor(Double.valueOf(stake) * Double.valueOf(oddsFormat) * 100) / 100;
                double toWin = Math.floor(toRisk / Double.valueOf(oddsFormat) * 100) / 100;
                lstRiskWin.add(toRisk);
                lstRiskWin.add(toWin);
            }
            return lstRiskWin;
        } else if (oddsType.equalsIgnoreCase("American")) {
            if(odds.contains("−")) {
                String oddsFormat = odds.replace("−","");
                double toRisk = Math.floor((Double.valueOf(stake) * Double.valueOf(oddsFormat) / 100) * 100) / 100;
                double toWin = Math.floor((toRisk / Double.valueOf(oddsFormat) * 100) * 100) / 100;
                lstRiskWin.add(toRisk);
                lstRiskWin.add(toWin);
            }
            return lstRiskWin;
        }
        return lstRiskWin;
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

    public Market getEventInfoUI(int eventId, String marketType) {
        Market market = new Market.Builder().build();
        List<Odds> lstOddsObject = new ArrayList<>();
        String rootXpath = String.format(tableEventXpath, eventId);
        Table tblEvent = Table.xpath(rootXpath, 7);
        Label lblLeague = Label.xpath(String.format("%s%s", tblEvent.getLocator().toString().replace("By.xpath: ",""),"//ancestor::app-league-asian//div[contains(@class,'league-name')]"));
        Label lblEventStartTime = Label.xpath(String.format("%s%s", rootXpath, "//th[contains(@class,'time-column')]"));
        Label lblHomeName = Label.xpath(String.format("%s%s", rootXpath, "//th[contains(@class,'opponent')]//div[contains(@class,'weak-team')]"));
        Label lblAwayName = Label.xpath(String.format("%s%s", rootXpath, "//th[contains(@class,'opponent')]//div[contains(@class,'strong-team')]"));
        Label lblOdds = Label.xpath(String.format("(%s%s)[%d]%s", rootXpath, "//th[contains(@class,'odd-column')]", defineOddsColumn(marketType, true), "//span[contains(@class,'odd-number')]"));
        //add odds from UI to list odds object
        for (int i = 0; i < lblOdds.getWebElements().size(); i++) {
            Label lblOddsValue = Label.xpath(String.format("(%s)[%s]", lblOdds.getLocator().toString().replace("By.xpath: ", ""), i + 1));
            //handle special character " -+" > ignore all, just get value
            lstOddsObject.add(i, new Odds.Builder().odds(Double.valueOf(lblOddsValue.getText().replaceAll("[⠀+−]",""))).build());
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
            } else if (marketType.equalsIgnoreCase("HANDICAP")) {
                return 2;
            } else {
                return 3;
            }
        } else {
            if (marketType.equalsIgnoreCase("MONEYLINE")) {
                return 4;
            } else if (marketType.equalsIgnoreCase("HANDICAP")) {
                return 5;
            } else {
                return 6;
            }
        }
    }

    public void verifyOddsShowCorrect(List<Odds> lstOddsExpected, List<Odds> lstOddsActual) {
        //sort before verifying cause order odds between API and UI is different HOME/AWAY/DRAW
        lstOddsExpected.sort(Comparator.comparingDouble(Odds::getOdds));
        lstOddsActual.sort(Comparator.comparingDouble(Odds::getOdds));
        for (int i = 0; i < lstOddsExpected.size(); i++) {
            Assert.assertEquals(lstOddsExpected.get(i).getOdds(), lstOddsActual.get(i).getOdds(), String.format("FAILED! Odds does not show correct expected %s actual %s", lstOddsExpected.get(i).getOdds(), lstOddsActual.get(i).getOdds()));
        }
    }

//    public String getFirstEventId() {
//        return Label.xpath(String.format(firstOddsCellXpath, 1, 1)).getAttribute("eventid");
//    }
//
//    public List<Market> getEventInfoMoreMarket(String eventId, String sportName, String oddsType, String marketType, String selection) {
//        int leagueIndex = 1;
//        List<Market> lstMarket;
//        while (true)
//        {
//            lstMarket = getEventInfoMoreMarket(eventId, sportName,oddsType, marketType,selection, leagueIndex);
//            if(Objects.nonNull(lstMarket))
//                return lstMarket;
//            leagueIndex = leagueIndex + 1;
//        }
//    }
//
//    /**
//     * Get event info with odds type = Decimal odds bases on inputed index, if index = 0 mean get random
//     * @param leagueIndex
//     * @return a Market Info
//     */
//    private List<Market> getEventInfoMoreMarket(String eventId, String sportName, String oddsType, String marketType, String selection, int leagueIndex) {
//        List<Market> lstMarket = new ArrayList<>();
//        Market market;
//        String keyId = "";
//        Label lblLeague = Label.xpath(String.format(leagueIndexXpath,leagueIndex));
//        if (!lblLeague.isDisplayed())
//            return null;
//        String oddsRowXpath = String.format("//span[contains(@eventid,'%s')]", eventId);
//        String leagueName = Label.xpath(String.format(leagueNameXpath,leagueIndex)).getText();
//        int rowCount = tblMoreMarket.getNumberOfRows(false, true);
//        for (int i = 0; i < rowCount; i++) {
//            Label lblOddsKey = Label.xpath(String.format("(%s)[%s]%s", tblMoreMarket.getLocator().toString().replace("By.xpath: ",""), i + 1, oddsRowXpath));
//            List<String> lstKeys = new ArrayList<>();
//            for (int j = 0; j < lblOddsKey.getWebElements().size(); j++) {
//                lstKeys.add(Label.xpath(String.format("(%s)[%s]", lblOddsKey.getLocator().toString().replace("By.xpath: ",""), j + 1)).getAttribute("key"));
//            }
//            if (marketType.equalsIgnoreCase(MARKET_TYPE_MAPPING.get("Match - Team Totals"))) {
//                if(!selection.isEmpty()) {
//                    for(String s: lstKeys) {
//                        if(s.contains(selection)) {
//                            keyId = s;
//                            break;
//                        }
//                    }
//                }
//            } else {
//                for(String s: lstKeys) {
//                    if(s.contains(marketType)) {
//                        keyId = s;
//                        break;
//                    }
//                }
//            }
//
//            market = MarketUtils.getMarketByOddsKey(oddsType,Integer.valueOf(eventId),keyId);
//            market.setLeagueName(leagueName);
//            String sportID = SPORTBOOK_SPORT_ID.get(sportName.toUpperCase());
//            Market temp =  MarketUtils.getEventInfoUnderLeague(Integer.valueOf(sportID),leagueName,eventId);
//            market.setSportName(sportName);
//            market.setEventName(String.format("%s vs %s",temp.getHomeName(),temp.getAwayName()));
//            market.setHomeName(temp.getHomeName());
//            market.setAwayName(temp.getAwayName());
//            market.setEventStartTime(temp.getEventStartTime());
//            lstMarket.add(market);
//        }
////        String eventID = Label.xpath(String.format(rootXpath,eventId)).getAttribute("eventid");
////        String oddsKey = Label.xpath(String.format(firstOddsCellXpath,leagueIndex)).getAttribute("key");
////
////        //handle incase no odds display in the UI, move to the next row
////        if(Objects.isNull(eventID)) {
////            return null;
////        }
//        // Get the market info from API with the eventID get from UI
////        market = MarketUtils.getMarketByOddsKey(oddsType,Integer.valueOf(eventID),oddsKey);
////        market.setLeagueName(leagueName);
//        // Get more info of the event in other API: league Name, home, away,event startTime
////        String sportID = SPORTBOOK_SPORT_ID.get(sportName.toUpperCase());
////        Market temp =  MarketUtils.getEventInfoUnderLeague(Integer.valueOf(sportID),leagueName,eventID);
////        market.setSportName(sportName);
////        market.setEventName(String.format("%s vs %s",temp.getHomeName(),temp.getAwayName()));
////        market.setHomeName(temp.getHomeName());
////        market.setAwayName(temp.getAwayName());
////        market.setEventStartTime(temp.getEventStartTime());
//        return lstMarket;
//    }
}
