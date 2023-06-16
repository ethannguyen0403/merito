package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import common.MemberConstants;
import membersite.objects.AccountBalance;
import membersite.pages.*;
import membersite.pages.exchangegames.EGHomePage;
import membersite.pages.popup.MyMarketPopup;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;
import java.util.Objects;

import static common.MemberConstants.TIMEZONE_BRAND;

public class HeaderSectionTest extends BaseCaseTest {

    /**
     * @title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly
     * pre-condition
     *           1. Login member site
     * @steps:   1. Get the info: Credit, Balance, Outstanding of  credit cash account from api
     * @expect: 1. Left menu icon and Logo
     *          2. Label: Time zone, Credit, Balance, Outstanding, My Markets, My Account
     *          3. Verify Credit, Balance, Outstanding of the player are corrected
     *          4. Outstanding should in red color
     */
    @TestRails(id="500")
    @Test (groups = {"smoke"})
    public void HeaderSection_TC500() {
        log("@title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly");
        log("Step 1: Get the info: Credit, Balance, Outstanding of  credit cash account from api");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        log("Verify 1: Left menu icon and Logo");
        String logoImgActual = memberHomePage.header.getLogoSrc();
        Assert.assertTrue(memberHomePage.header.isLeftMenuIcondisplay(), "ERROR:Left menu icon is not display");
        Assert.assertTrue(logoImgActual.contains(environment.directusURL),"FAILED! Log0 image not display");

        log("Verify 2: Label: Time zone, Credit, Balance, Outstanding, My Markets, My Account");
        AccountBalance balanceUI = memberHomePage.header.getUserBalance();
        Assert.assertEquals(memberHomePage.header.getBalanceLabel(), MemberConstants.Header.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",memberHomePage.header.getBalanceLabel(), MemberConstants.Header.BALANCE));
        Assert.assertEquals(memberHomePage.header.getLiabilityLabel(), MemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",memberHomePage.header.getLiabilityLabel(), MemberConstants.Header.OUTSTANDING));

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(memberHomePage.header.getLiabilityTextColor().equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0,  1)' but found %s", memberHomePage.header.getLiabilityTextColor()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can open my markets
     * @pre-condition
     *           1. Login member site
     * @steps: 1. Click on  My Markets menu
     *         2. Close the popup
     * @expect: 1. Can Open May markets successfully
     *          - Popup Title: My Markets
     *          - Note: Date will be based on time zone IST (for SAT and White Labels)
     *          - table with the header: Market ID, Market Start Time, Market Name (Reload button), Liability
     *          2. Popup is closed
     */
    @TestRails(id="501")
    @Test (groups = {"smokeOldUI","smokeNewUI"})
    public void HeaderSection_TC501() {
        log("@title: Validate can open my markets");
        log("Step 1. Click on  My Markets menu");
        MyMarketPopup popup = memberHomePage.header.openMyMarketPopup();

        log("Verify 1:  - Popup Title: My Markets\n" +
                "   - Note: Date will be based on time zone IST (for SAT and White Labels)\n" +
                "   - table with the header: Market ID, Market Start Time, Market Name (Reload button), Liability");
        Assert.assertEquals(popup.lblTitle.getText(), MemberConstants.MyMarketsPopup.TITLE, String.format("ERROR: Expected title is %s but found %s", MemberConstants.MyMarketsPopup.TITLE, popup.lblTitle.getText()));
        Assert.assertEquals(popup.lblNote.getText(),TIMEZONE_BRAND.get(_brandname), String.format("ERROR: Expected title is %s but found %s", MemberConstants.MyMarketsPopup.NOTES, popup.lblNote.getText()));
        List<String> lstMyMarketsHeaders = popup.tbMyMarkets.getColumnNamesOfTable(2);
        Assert.assertEquals(lstMyMarketsHeaders.size(), MemberConstants.MyMarketsPopup.TABLE_MY_MARKETS_HEADER.size(), String.format("ERROR: The expected no of columns is %s but found %s", MemberConstants.MyMarketsPopup.TABLE_MY_MARKETS_HEADER.size(), lstMyMarketsHeaders.size()));

        for (int i = 0; i < MemberConstants.MyMarketsPopup.TABLE_MY_MARKETS_HEADER.size(); i++) {
            String observed = lstMyMarketsHeaders.get(i);
            String expected = MemberConstants.MyMarketsPopup.TABLE_MY_MARKETS_HEADER.get(i);
            Assert.assertTrue(observed.contains(expected), String.format("ERROR: The expected column name is '%s' but found '%s'", expected, observed));
        }

        log("Step2 :Close the popup");
        popup.close();

        log("Verify 2: Popup is closed");
        Assert.assertFalse(popup.lblTitle.isDisplayedShort(3),"ERROR: The popup closed is not successfully");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Liability in My market and outstanding is synchronized
     * @pre-condition
     *           1. Login member site
     * @steps:   1. Get outstanding value
     *           2. Click on My market
     *           3. Sum liability of all market
     * @expect: 1. Liability matched with outstanding
     *    If My markets display "No records found", Outstanding should be 0.00
     */
    @Test(groups = {"regression"})
    public void HeaderSection_TC004() {
        log("@title: Validate Liability in My market and outstanding is synchronized");
        log("Step 1. Get outstanding value");
        log("Step 2. Click on My market");
        log("Step 3. Sum liability of all market");
        AccountBalance balanceUI = memberHomePage.header.getUserBalance();
        MyMarketPopup popup = memberHomePage.header.openMyMarketPopup();

        log("Verify 1:  Liability matched with outstanding\n" +
                "   If My markets display \"No records found\", Outstanding should be 0.00");
        if(popup.lblNoRecord.isDisplayed()){
            Assert.assertEquals(popup.lblNoRecord.getText(), MemberConstants.MyMarketsPopup.NO_RECORD_FOUNDS);
        }else {
            Assert.assertEquals(balanceUI.getExposure(), popup.totalLiability(), String.format("ERROR: Expected total Liability in My Market: %s is not matched with Outstanding:%s", popup.totalLiability(), balanceUI.getExposure()));
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can navigate to Account Statement page
     * @precondition: 1. Login member site
     * @step: 1. Click My Account > Account Statement
     * @expect: 1. Account Statement page is display in new tab
     * - Start Date, End Date Calendar, and Load report button
     * - Note: Date will be based on time zone IST
     * Table header: Market ID, Settled Date, Narration, Debit, Credit, Balance
     */
    @TestRails(id="502")
    @Test(groups = {"smoke"})
    public void HeaderSection_TC502(){
        log("@title: Validate can navigate to Account Statement page");
        log("Step 1. Click My Account > Account Statement");
        AccountStatementPage page = memberHomePage.header.openAccountStatement(_brandname);

        log("Verify 1. Account Statement page is display in new tab\n" +
                "- Start Date, End Date Calendar, and Load report button\n" +
                "- Note: Date will be based on time zone IST\n" +
                "Table header: Market ID, Settled Date, Narration, Debit, Credit, Balance");
        Assert.assertEquals(page.accountStatementContainer.getStartDate(), MemberConstants.AccountStatementPage.START_DATE, String.format("ERROR! Expected Start Date but found %s",page.accountStatementContainer.getStartDate()));
        Assert.assertEquals(page.accountStatementContainer.getEndDate(), MemberConstants.AccountStatementPage.END_DATE, String.format("ERROR! Expected End Date but found %s",page.accountStatementContainer.getEndDate()));
        Assert.assertEquals(page.accountStatementContainer.getNote(), String.format(MemberConstants.AccountStatementPage.NOTES,TIMEZONE_BRAND.get(_brandname)), String.format("ERROR! Current Note label shows %s",page.accountStatementContainer.getNote()));
        Assert.assertEquals(page.accountStatementContainer.getLoadReport(), MemberConstants.AccountStatementPage.LOAD_REPORT,String.format("ERROR! Expected Load Report but found %s",page.accountStatementContainer.getLoadReport()));
        List<String> tblHeaders = page.accountStatementContainer.getReportHeader();
        Assert.assertEquals(tblHeaders.size(), MemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER.size(), String.format("ERROR: The expected no of columns is %s but found %s", MemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER.size(),tblHeaders.size()));
        Assert.assertEquals(tblHeaders, MemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER,"ERROR! Account Statement header not match with the expected");
    }

    /**
     * @title: Validate can navigate to My Bet
     * @precondition: 1. Login member site
     * @step: 1. Click My Account > My Bets
     * @expect: 1. My Bets page is display in new tab
     *  -  Product dropdown box,
     *   - Order Type dropdown box, Start Date, End Date, Load Report button     *
     *  - Start Date, End Date Calendar, and Load report button
     *  - Note: Date will be based on time zone IST
     *  - Table with header: Market Name, Bet ID, Event ID, Selection, Type, Odds, Stake, Profit/Loss, Status, Placed Date, IP Address
     */
    @TestRails(id="503")
    @Test(groups = {"smoke"})
    public void HeaderSection_TC503(){
        log("@title: Validate can navigate to My Bet");
        log("Step 1. Click My Account > My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);

        log("Verify 1. 1. My Bets page is display in new tab\n" +
                "     *  -  Product dropdown box,\n" +
                "     *   - Order Type dropdown box, Start Date, End Date, Load Report button     *\n" +
                "     *  - Start Date, End Date Calendar, and Load report button\n" +
                "     *  - Note: Date will be based on time zone IST\n" +
                "     *  - Table with header: Market Name, Bet ID, Event ID, Selection, Type, Odds, Stake, Profit/Loss, Status, Placed Date, IP Address");
        Assert.assertTrue(page.getPageUrl().contains("report/my-bet"), String.format("ERROR: My Bet page ulr incorrect %s", page.getPageUrl()));

    }

    /**
     * @title: Validate can navigate to Profit & Loss
     * @precondition: 1. Login member site
     * @step: 1. Click My Account > Profit & Loss
     * @expect: 1. Profit & Loss page display in new tab
     *          - Start Date, End Date, Load Report
     *          - Note: Date will be based on time zone IST
     *          - Table with the header: Sport/Game, Profit/Loss
     */
    @TestRails(id="504")
    @Test(groups = {"smoke"})
    public void HeaderSection_TC504(){
        log("@title: Validate can navigate to Profit & Loss");
        log("Step 1. Click My Account > Profit & Loss");
        ProfitAndLossPage page = memberHomePage.header.openProfitAndLoss(_brandname);

        log("Verify 1 Profit & Loss page display in new tab\n" +
                "1. Start Date, End Date, Load Report\n" +
                "Note: Date will be based on time zone IST\n" +
                "2. Table with the header: Sport/Game, Profit/Loss");
        Assert.assertEquals(page.profitAndLossContainer.getStartDate(), MemberConstants.AccountStatementPage.START_DATE, String.format("ERROR! Expected Start Date but found %s",page.profitAndLossContainer.getStartDate()));
        Assert.assertEquals(page.profitAndLossContainer.getEndDate(), MemberConstants.AccountStatementPage.END_DATE, String.format("ERROR! Expected End Date but found %s",page.profitAndLossContainer.getEndDate()));
        Assert.assertEquals(page.profitAndLossContainer.getNote(),String.format("Note : Date will be based on time zone %s",TIMEZONE_BRAND.get(_brandname)), String.format("ERROR! Current Note label shows %s",page.profitAndLossContainer.getNote()));
        Assert.assertEquals(page.profitAndLossContainer.getLoadReport(), MemberConstants.AccountStatementPage.LOAD_REPORT,String.format("ERROR! Expected Load Report but found %s",page.profitAndLossContainer.getLoadReport()));
        Assert.assertTrue(page.profitAndLossContainer.isExportButtonDisplay(),"ERROR! Export button is not displayed");
        List<String> tblHeaders = page.profitAndLossContainer.getReportHeader();
        Assert.assertEquals(tblHeaders.size(), MemberConstants.ProfitAndLossPage.TABLE_SUMMARY_HEADER.size(), String.format("ERROR: The expected no of columns is %s but found %s", MemberConstants.ProfitAndLossPage.TABLE_SUMMARY_HEADER.size(),tblHeaders.size()));
        Assert.assertEquals(tblHeaders, MemberConstants.ProfitAndLossPage.TABLE_SUMMARY_HEADER,"ERROR: The expected not match with the actual!" );
        log("INFO: Executed completely");

    }


    /**
     * @title: Validate can navigate to Soccer menu
     * @pre-condition
     *          1. Soccer sport is active for the login account
     *          2. Login member site
     * @steps:   1. Click on Soccer menu
     * @expect:  1. Soccer page display with the title: Soccer Highlights
     */
    @TestRails(id="505")
    @Test (groups = {"smoke"})
    public void HeaderSection_TC505(){
        log("@title: Validate can navigate to Soccer menu");
        log("Step 1. Click on Soccer menu");
        log("Verify: 1. Soccer page display with the title: Soccer Highlights");
        String sportName = "Soccer";
        SportPage page = memberHomePage.header.navigateSportMenu(sportName,_brandname);

        if(Objects.nonNull(page)){
            Assert.assertEquals(page.eventContainerControl.getSportHeader(),
                    String.format(MemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
                    String.format("ERROR! Expected Sport title is %s but found %s",String.format(MemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
                            page.eventContainerControl.getSportHeader()));
        }else{
            log("SKIP! There is no Soccer menu display");
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can navigate to Tennis menu
     * @pre-condition
     *          1. Soccer sport is active for the login account
     *          2. Login member site
     * @steps:   1. Click on Tennis menu
     * @expect:  1. Tennis page display with the title: Tennis Highlights
     */
    @TestRails(id="506")
    @Test(groups = {"smoke"})
    public void HeaderSection_TC506(){
        log("@title: Validate can navigate to Tennis menu");
        log("Step 1. Click on Tennis menu");
        log("Verify: 1. Tennis page display with the title: Tennis Highlights");
        String sportName = "Tennis";
        SportPage page = memberHomePage.header.navigateSportMenu(sportName,_brandname);
        if(Objects.nonNull(page)){
            Assert.assertEquals(page.eventContainerControl.getSportHeader(),
                    String.format(MemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
                    String.format("ERROR! Expected Sport title is %s but found %s",String.format(MemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
                            page.eventContainerControl.getSportHeader()));
        }else{
            log("SKIP! There is no Tennis menu display");
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can navigate to Horse Racing menu
     * @pre-condition
     *          1. Horse Racing sport is active for the login account
     *          2. Login member site
     * @steps:  1. Click on Horse Racing menu
     * @expect: 1. Display 3 tabs: Today (active by default), Tomorrow, Next date
     *          2. Racing list display
     */
  /*  @TestRails(id="507")
    @Test (groups = {"smoke"})
    public void HeaderSection_TC507(){
        log("@title: Validate can navigate to Horse Racing menu");
        log("Step 1. Click on Horse Racing menu");

        String sportName = "Horse Racing";
        RacingPage page =  memberHomePage.header.navigateRacing(sportName,_brandname);
        log("Verify: 1. Display 3 tabs: Today (active by default), Tomorrow, Next date");
        Assert.assertEquals(page.racingContainer.tabToday.getText(),"Today", String.format("ERROR! Expected is Today tab but found %s",page.racingContainer.tabToday.getText()));
        Assert.assertEquals(page.racingContainer.tabTomorrow.getText(),"Tomorrow", String.format("ERROR! Expected is Tomorrow tab but found %s",page.racingContainer.tabTomorrow.getText()));
       Assert.assertEquals(page.racingContainer.tabNextTomorrow.getText(),DateUtils.getDate(2,"EEEE","IST"),
               String.format("ERROR! Expected is %s tab but found %s",DateUtils.getDate(2,"EEEE","IST"),page.racingContainer.tabNextTomorrow.getText()));
       Assert.assertEquals(page.leftMenu.getActiveSport(),sportName,String.format("ERROR! Sport active in the left menu not is Horse Racing but found %s",page.leftMenu.getActiveSport()));
       log("INFO: Executed completely");
    }*/

    /**
     * @title: Validate can navigate to Cricket menu
     * @pre-condition
     *          1. Soccer sport is active for the login account
     *          2. Login member site
     * @steps:   1. Click on Cricket menu
     * @expect:  1. Cricket page display with the title: Cricket Highlights
     */
    @TestRails(id="508")
    @Test(groups = {"smoke"})
    public void HeaderSection_TC508(){
        log("@title: Validate can navigate to Cricket menu");
        log("Step 1. Click on Cricket menu");
        log("Verify: 1. Cricket page display with the title: Cricket Highlights");
        String sportName = "Cricket";
        Assert.assertTrue(true,"By pass this test case");
        SportPage page = memberHomePage.header.navigateSportMenu(sportName, _brandname);

        if(Objects.nonNull(page)){
            Assert.assertEquals(page.eventContainerControl.getSportHeader(),
                    String.format(MemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
                    String.format("ERROR! Expected Sport title is %s but found %s",String.format(MemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
                            page.eventContainerControl.getSportHeader()));
        }else{
            log("SKIP! There is no Cricket menu display");
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can navigate to correct market when click on market in My market
     * @pre-condition   1. Have some unmatched/Match bets
     *                  2. Login member site
     * @steps:          1. Click on My market
     *                  2. Click on market in my market popup
     * @expect:         1. The corresponding market is navigate
     */
    @TestRails(id="509")
    @Test (groups = {"smoke"}, priority=3)
    public void HeaderSection_C509(){
        log("@title: Validate can navigate to correct market when click on market in My market");
        log("Step 1. Click on My market ");
        MyMarketPopup popup = memberHomePage.header.openMyMarketPopup();
        List<String> marketInfo = popup.getMarketInfo(1);
        if (marketInfo.get(0).equals(MemberConstants.MyMarketsPopup.NO_RECORD_FOUNDS))
        {
            log(String.format("SKIPPED! Due to %s", MemberConstants.MyMarketsPopup.NO_RECORD_FOUNDS));
            return;
        }
        log("Step 2. Click on market in my market popup");
        MarketPage page = memberHomePage.openMarketInMyMarketPopup(marketInfo.get(0));

        log("Verify: 1. The corresponding market is navigate");
        String actual = String.format("%s / %s",page.marketOddControl.getTitle(), page.marketOddControl.getTitle());
        Assert.assertEquals(actual, marketInfo.get(popup.colMarketName-1),String.format("ERROR: Click on %s but my market display the title %s",marketInfo.get(popup.colMarketName-1),actual));
        log("INFO: Executed completely");
    }

    @TestRails(id="510")
    @Test (groups = {"smoke"})
    public void HeaderSection_C510(){
        log("@title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Exchange Game product");
        log("Step 1. Get the info: Credit, Balance, Outstanding of credit cash account from api");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        log("Step 2. Click Exchange Game Product");
        if(!memberHomePage.header.isProductActive("EXCH_GAMES")){
            log("SKIP! Exchange Games product is NOT active for this account");
            return;
        }
        EGHomePage page = memberHomePage.openExchangeGame();

        log("Verify 1: Left menu icon and Logo");
        String logoImgActual = page.header.getLogoSrc();
        Assert.assertTrue(page.header.isLeftMenuIcondisplay(), "ERROR:Left menu icon is not display");
        Assert.assertTrue(logoImgActual.contains(environment.directusURL),"FAILED! Log0 image not display");

        log("Verify 2: Label: Time zone, Credit, Balance, Outstanding, My Markets, My Account");
        AccountBalance balanceUI = page.header.getUserBalance();
        Assert.assertEquals(page.header.getBalanceLabel(), MemberConstants.Header.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",page.header.getBalanceLabel(), MemberConstants.Header.BALANCE));
        Assert.assertEquals(page.header.getLiabilityLabel(), MemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",page.header.getLiabilityLabel(), MemberConstants.Header.OUTSTANDING));

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(page.header.getLiabilityTextColor().equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0,  1)' but found %s", page.header.getLiabilityTextColor()));
        log("INFO: Executed completely");
    }

/*
    */
/**
     * @title: Validate no console error when active Exchange Game product
     * @precondition: 1. Exchange Game is active for the account
     *                2. Login member site
     * @step: 1. Click on Exchange Games
     * @expect: 1. No console error display
     *//*

    @Test(groups = {"http_request"})
    public void HeaderSection_TC016(){
        log("@title: Validate no console error when active Exchange Game product");
        log("Step 1. Click on Exchange Games");
        EGHomePage exchangeHomePage = memberHomePage.header.openExchangeGame();
        */
/*exchangememberHomePage.lblOmahaHiTitle.isDisplayed();


        log("Verify 1. No console error display");
        logBug("FAILED by bug #44567 ");
        boolean isError = hasHTTPRespondedOK();
        Assert.assertTrue(isError, "ERROR: There are some response request error when active Exchange Game tab");*//*

        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate Active product is display
     * @pre-condition 1. Login member site
     * @steps:   1. Get all product is active for login account
     * @expect: 1. Validate Product menu display active data
     *          "EXCHANGE": Exchange product display
     *          "SUPER_SPADE" Live Dealer Asian product, "DIGIENT": Live Dealer European product,
     *          Live Dealer Asian and European is group under Live Dealer
     *          "EZUGI" Lottery & Slots display
     *          "EXCH_GAMES": Exchange game product
     *//*

    @Test(groups = {"lottery","we"})
    public void HeaderSection_TC015(){
        log("@title: Validate Active products are display");
        log("Step 1. Get all product is active for login account");
        List<String> products = BetUtils.getProductInfo();

        log("Verify: 1. Validate Product menu display active data\n" +
                "\"EXCHANGE\": Exchange product display\n" +
                "\"SUPER_SPADE\" Live Dealer Asian product, \"DIGIENT\": Live Dealer European product,\n" +
                "Live Dealer Asian and European is group under Live Dealer\n" +
                " \"EZUGI\" Lottery & Slots display\n" +
                " \"EXCH_GAMES\": Exchange game product");
        for (String product: products) {
            Assert.assertTrue(memberHomePage.isProductTabDisplay(MemberConstants.HomePage.PRODUCTS.get(product)));
        }
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate no console error when active Live Dealer Asian product
     * @precondition:   1.Live Dealer Asian is active for the account
     *                  2. Login member site
     * @step:           1. Click on Live Dealer > Asian Room
     * @expect:         1. No console error display
     *//*

    @Test(groups = {"http_request"})
    public void HeaderSection_TC018(){
        log("@title: Validate no console error when active Live Dealer Asian product");
        log("Step 1.  Click on Live Dealer > Asian Room");
        AsianRoomPage page = memberHomePage.switchLiveDealerTab().switchAsianRoomTab();
        log("Verify 1. No console error display");
        boolean isError = hasHTTPRespondedOK();
        Assert.assertTrue(isError, "ERROR: There are some response request error when active Live Dealer Asian tab");
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate no console error when active Live Dealer European product
     * @precondition:   1.Live Dealer European is active for the account
     *                  2. Login member site
     * @step:           1. Click on Live Dealer > Asian Room
     * @expect:         1. No console error display
     *//*

    @Test(groups = {"http_request"})
    public void HeaderSection_TC019(){
        log("@title: Validate no console error when active Live Dealer European product");
        log("Step 1.  Click on Live Dealer > Asian Room");
        EuropeanRoomPage page = memberHomePage.switchLiveDealerTab().switchEuropeansRoomTab();
        log("Verify 1. No console error display");
        boolean isError = hasHTTPRespondedOK();
        Assert.assertTrue(isError, "ERROR: There are some response request error when active Live Dealer European tab");
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate no console error when clicking on sports menu
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Click on Sport menu: Home, In-Play, Soccer, Cricket, Tennis
     * @expect:  1. There is no console display when active sport menu
     *//*

    @Test (groups = {"http_request"})
    public void HeaderSection_TC020(){
        log("@title: Validate no console error when clicking on sports menu");
        log("Step 1: Click on Sport menu");
        log("Verify: There is no http requests error when navigate to the corresponding page");

        List<String> menuLst = memberHomePage.getMainSportsMenu();
        for(int i=0, n = menuLst.size() ; i<n; i++)
        {
            log(String.format("Verify: There is no http requests error when navigate to %s page",menuLst.get(i)));
            memberHomePage.navigateSportMenu(menuLst.get(i), SportPage.class);
            boolean isError = hasHTTPRespondedOK();
            Assert.assertTrue(isError, String.format("ERROR: There are some response request error when navigate %s page",menuLst.get(i)));
        }
        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Live Dealer Asian product
     * @pre-condition 1. Login member site
     * @steps: 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api
     *         2. Click Live Dealer Product
     * @expect: 1. Home Icon, lolog icon, timezone label
     *          2. Verify label: Credit, Balance, Outstanding
     *          3. Verify Credit, Balance, Outstanding of the player are corrected
     *          4. Outstanding should in red color
     *//*

    @Test (groups = {"lottery"})
    @Parameters({"currency"})
    public void HeaderSection_TC027(String currency){
        log("@title:Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Live Dealer product");
        log("Step 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        log("Step 2. Click Lottery and Slots Product");
        if(!memberHomePage.isProductActive("Live Dealer Asian")){
            log("SKIP! Live Dealer Asian product is NOT active for this account");
            return;
        }
        LiveDealer page = memberHomePage.switchLiveDealerTab();

        log("Verify 1: Left menu icon and Logo");
        String logoImgActual = page.satHeaderControl.imgLogo.getAttribute("src");
        Assert.assertTrue(page.imgLeftMenu.isDisplayed(), "ERROR:Left menu icon is not display");
        Assert.assertTrue(logoImgActual.contains(environment.getDirectusURL()),"FAILED! Log0 image not display");

        log("Verify 2:Label: Annoucment icon, My Bet, My Market, My Account");
        Assert.assertEquals(page.lblMyBets.getText(), MemberConstants.Header.MY_BETS,"Failed! My Bets label is incorrect displayed");
        Assert.assertEquals(page.lblMyMarkets.getText(), MemberConstants.Header.MY_MARKET,"Failed! My Markets label is incorrect displayed");
        Assert.assertEquals(page.lblMyAccount.getText(), MemberConstants.Header.My_ACCOUNT,"Failed! My Account label is incorrect displayed");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.getUserBalance();
        Assert.assertEquals(page.lblBalanceTitle.getText(), MemberConstants.Header.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), MemberConstants.Header.BALANCE));
        Assert.assertEquals(page.lblBalanceCurrency.getText(), currency,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), MemberConstants.Header.BALANCE));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));

        Assert.assertEquals(page.lblOutstandingTitle.getText(), MemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",page.lblOutstandingTitle.getText(), MemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(page.lblLiability.getColour("color").equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0, 1)' but found %s", page.lblLiability.getColour("color")));

        log("INFO: Executed completely");
    }

    */
/**
     * @title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Lottery&Slots product
     * @pre-condition 1. Login member site
     * @steps: 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api
     *          2. Click Lottery and Slots Product
     * @expect: 1. Home Icon, lolog icon, timezone label
     *          2. Verify label: Credit, Balance, Outstanding
     *          3. Verify Credit, Balance, Outstanding of the player are corrected
     *          4. Outstanding should in red color
     *//*

    @Test (groups = {"lottery"})
    @Parameters({"currency"})
    public void HeaderSection_TC028( String currency){
        log("@title:Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Lottery&Slots product");
        log("Step 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        log("Step 2. Click Lottery and Slots Product");
        if(!memberHomePage.isProductActive("Lottery & Slot")){
            log("SKIP! Lottery & Slot product is NOT active for this account");
            return;
        }
        LotterySlotsPage page = memberHomePage.switchLotterySlotsTab();

        log("Verify 1: Left menu icon and Logo");
        String logoImgActual = page.imgLogo.getAttribute("src");
        Assert.assertTrue(page.imgLeftMenu.isDisplayed(), "ERROR:Left menu icon is not display");
        Assert.assertTrue(logoImgActual.contains(environment.getDirectusURL()),"FAILED! Log0 image not display");

        log("Verify 2:Label: Annoucment icon, My Bet, My Market, My Account");
        Assert.assertEquals(page.lblMyBets.getText(), MemberConstants.Header.MY_BETS,"Failed! My Bets label is incorrect displayed");
        Assert.assertEquals(page.lblMyMarkets.getText(), MemberConstants.Header.MY_MARKET,"Failed! My Markets label is incorrect displayed");
        Assert.assertEquals(page.lblMyAccount.getText(), MemberConstants.Header.My_ACCOUNT,"Failed! My Account label is incorrect displayed");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.getUserBalance();
        Assert.assertEquals(page.lblBalanceTitle.getText(), MemberConstants.Header.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), MemberConstants.Header.BALANCE));
        Assert.assertEquals(page.lblBalanceCurrency.getText(), currency,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), MemberConstants.Header.BALANCE));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));

        Assert.assertEquals(page.lblOutstandingTitle.getText(), MemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",page.lblOutstandingTitle.getText(), MemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(page.lblLiability.getColour("color").equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0, 1)' but found %s", page.lblLiability.getColour("color")));

        log("INFO: Executed completely");
    }
*/



}
