package membersite.testcases.aposta;

import com.paltech.utils.DateUtils;
import membersite.common.FEMemberConstants;
import membersite.objects.AccountBalance;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.AccountStatementPage;
import membersite.pages.all.tabexchange.ProfitAndLossPage;
import membersite.pages.all.tabexchange.RacingPage;
import membersite.pages.all.tabexchange.SportPage;
import membersite.pages.all.tabexchangegame.EGHomePage;
import membersite.pages.all.tablivedealer.AsianRoomPage;
import membersite.pages.all.tablivedealer.EuropeanRoomPage;
import membersite.pages.all.tablivedealer.components.LiveDealer;
import membersite.pages.all.tablotteryslot.LotterySlotsPage;
import membersite.pages.aposta.MyBetsPage;
import baseTest.BaseCaseMerito;
import membersite.utils.betplacement.BetUtils;

import java.util.List;
import java.util.Objects;

public class HeaderSectionTest extends BaseCaseMerito {

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
    @Test (groups = {"smoke"})
    @Parameters({"currency"})
    public void HeaderSection_TC001(boolean isCredit,String currency) {
        log("@title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly");
        log("Step 1: Get the info: Credit, Balance, Outstanding of  credit cash account from api");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        log("Verify 1: Left menu icon and Logo");
        String logoImgActual = memberHomePage.apHeaderControl.imgLogo.getAttribute("src");
        Assert.assertTrue(memberHomePage.apHeaderControl.imgLeftMenu.isDisplayed(), "ERROR:Left menu icon is not display");
        Assert.assertTrue(logoImgActual.contains(environment.getDirectusURL()),"FAILED! Log0 image not display");

        log("Verify 2: Timezone, account menu icon displayed");
        Assert.assertTrue(memberHomePage.apHeaderControl.ddmTimeZone.isDisplayed(),"FAILED! Timezone dropdown menu not display");
        Assert.assertTrue(memberHomePage.apHeaderControl.ddmAccount.isDisplayed(),"FAILED! Account dropdown menu not display");
        Assert.assertTrue(memberHomePage.apHeaderControl.ddmLanguage.isDisplayed(),"FAILED! Account dropdown menu not display");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = memberHomePage.apHeaderControl.getUserBalance();
        Assert.assertEquals(memberHomePage.apHeaderControl.lblBal.getText(), FEMemberConstants.APHeader.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",memberHomePage.apHeaderControl.lblBal.getText(), FEMemberConstants.APHeader.BALANCE));
        Assert.assertEquals(memberHomePage.apHeaderControl.lblBalCurrency.getText(), currency,String.format("ERROR: Expected is Balance currency is %s but found %s",memberHomePage.apHeaderControl.lblBalCurrency.getText(), FEMemberConstants.APHeader.BALANCE));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));
        Assert.assertEquals(memberHomePage.apHeaderControl.lblLiability.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",memberHomePage.apHeaderControl.lblLiability.getText(), FEMemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(memberHomePage.apHeaderControl.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(memberHomePage.apHeaderControl.lblLiabilityValue.getColour("color").equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0,  1)' but found %s", memberHomePage.apHeaderControl.lblLiability.getColour("color")));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate list menu in my account is corrected
     * @pre-condition
     *          1. Login member site
     * @steps:  1. Click on My Account
     * @expect: 1. The list menu display correctly
     *          - Login Account
     *          - Account Statement
     *          - My Bets
     *          - Profit & Loss
     *          - My Last Logins
     *          - Change Password
     *          - Logout
     */
    @Test(groups = {"smoke"})
    @Parameters({"username"})
    public void HeaderSection_TC005(String username) {
        log("@title: Validate list menu in my account is corrected");
        log("Step 1. Click on My Account");
        List<String> lstAccountMenu = memberHomePage.apHeaderControl.ddmAccount.getOptions();

        log("Verify 1: The list My account menu display correctly" );
        Assert.assertEquals(lstAccountMenu.get(0),username,String.format("ERROR: Expected login account is %s but found %s", username,lstAccountMenu.get(0)));
        Assert.assertEquals(lstAccountMenu.get(1), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"),
                String.format("ERROR: Expected login account is %s but found %s", FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"),lstAccountMenu.get(1)));
        Assert.assertEquals(lstAccountMenu.get(2), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets"),String.format("ERROR: Expected %s but found %s",
                FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets"),lstAccountMenu.get(2)));
        Assert.assertEquals(lstAccountMenu.get(3), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Profit & Loss"),
                String.format("ERROR: Expected %s but found %s", FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Profit & Loss"),lstAccountMenu.get(3)));
        Assert.assertEquals(lstAccountMenu.get(4), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Last Logins"),
                String.format("ERROR: Expected %s but found %s", FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Last Logins"),lstAccountMenu.get(4)));
        Assert.assertEquals(lstAccountMenu.get(5), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"),
                String.format("ERROR: Expected %s but found %s", FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"),lstAccountMenu.get(5)));
        Assert.assertEquals(lstAccountMenu.get(6), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"),
                String.format("ERROR: Expected %s but found %s", FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"),lstAccountMenu.get(6)));
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
    @Test(groups = {"smoke"})
    @Parameters({"currency"})
    public void HeaderSection_TC006(String currency){
        log("@title: Validate can navigate to Account Statement page");
        log("Step 1. Click My Account > Account Statement");
        AccountBalance balanceAPI = BetUtils.getUserBalance();
        AccountStatementPage page = memberHomePage.apHeaderControl.openAccountStatementPage();
        page.btnLoadReport.isTextDisplayed(FEMemberConstants.AccountStatementPage.LOAD_REPORT,20);

        log("Verify 1.Verify Header section: Leftmenu icon, logo, My Account, Balance and Liability");
        String logoImgActual = page.apHeaderControl.imgLogo.getAttribute("src");
        Assert.assertFalse(page.apHeaderControl.imgLeftMenu.isDisplayed(), "ERROR:Left menu icon should not display");
        Assert.assertTrue(logoImgActual.contains(environment.getDirectusURL()),"FAILED! Log0 image not display");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.apHeaderControl.getUserBalance();
        Assert.assertEquals(page.apHeaderControl.lblBal.getText(), FEMemberConstants.APHeader.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",memberHomePage.apHeaderControl.lblBal.getText(), FEMemberConstants.APHeader.BALANCE));
        Assert.assertEquals(page.apHeaderControl.lblBalCurrency.getText(), currency,String.format("ERROR: Expected is Balance currency is %s but found %s",memberHomePage.apHeaderControl.lblBalCurrency.getText(), FEMemberConstants.APHeader.BALANCE));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));
        Assert.assertEquals(page.apHeaderControl.lblLiability.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",memberHomePage.apHeaderControl.lblLiability.getText(), FEMemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.apHeaderControl.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 1. Account Statement page is display in new tab\n" +
                "- Start Date, End Date Calendar, and Load report button\n" +
                "- Note: Date will be based on time zone IST\n" +
                "Table header: Market ID, Settled Date, Narration, Debit, Credit, Balance");
        Assert.assertEquals(page.lblStartDate.getText(), FEMemberConstants.AccountStatementPage.START_DATE, String.format("ERROR! Expected Start Date but found %s",page.lblStartDate.getText()));
        Assert.assertEquals(page.lblEndDate.getText(), FEMemberConstants.AccountStatementPage.END_DATE, String.format("ERROR! Expected End Date but found %s",page.lblEndDate.getText()));
        Assert.assertEquals(page.lblNote.getText(), FEMemberConstants.AccountStatementPage.NOTES, String.format("ERROR! Current Note label shows %s",page.lblNote.getText()));
        Assert.assertEquals(page.btnLoadReport.getText(), FEMemberConstants.AccountStatementPage.LOAD_REPORT,String.format("ERROR! Expected Load Report but found %s",page.btnLoadReport.getText()));
        List<String> tblHeaders = page.tblReport.getColumnNamesOfTable(1);
        Assert.assertEquals(tblHeaders.size(), FEMemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER.size(), String.format("ERROR: The expected no of columns is %s but found %s", FEMemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER.size(),tblHeaders.size()));
        Assert.assertEquals(tblHeaders, FEMemberConstants.AccountStatementPage.TABLE_SUMMARY_HEADER,"ERROR! Account Statement header not match with the expected");
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
    @Test(groups = {"smoke"})
    @Parameters({"currency"})
    public void HeaderSection_TC007(String currency){
        log("@title: Validate can navigate to My Bet");
        log("Step 1. Click My Account > My Bets");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        MyBetsPage page =  memberHomePage.apHeaderControl.openMyBetPage();

        log("Verify 1. My Bets page is display ");
        Assert.assertTrue(page.getPageUrl().contains("report/my-bet"), String.format("ERROR: My Bet page ulr incorrect %s", page.getPageUrl()));

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.apHeaderControl.getUserBalance();
        Assert.assertEquals(page.apHeaderControl.lblBal.getText(), FEMemberConstants.APHeader.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",memberHomePage.apHeaderControl.lblBal.getText(), FEMemberConstants.APHeader.BALANCE));
        Assert.assertEquals(page.apHeaderControl.lblBalCurrency.getText(), currency,String.format("ERROR: Expected is Balance currency is %s but found %s",memberHomePage.apHeaderControl.lblBalCurrency.getText(), FEMemberConstants.APHeader.BALANCE));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));
        Assert.assertEquals(page.apHeaderControl.lblLiability.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",memberHomePage.apHeaderControl.lblLiability.getText(), FEMemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.apHeaderControl.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));
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
    @Test(groups = {"smoke"})
    @Parameters({"currency"})
    public void HeaderSection_TC008(String currency){
        log("@title: Validate can navigate to Profit & Loss");
        log("Step 1. Click My Account > Profit & Loss");
        AccountBalance balanceAPI = BetUtils.getUserBalance();
        ProfitAndLossPage page = memberHomePage.apHeaderControl.openProfitLossPage();

        log("Verify 1: Verify Header Balance, Liability of the page is corrected");
        AccountBalance balanceUI = page.apHeaderControl.getUserBalance();
        Assert.assertEquals(page.apHeaderControl.lblBal.getText(), FEMemberConstants.APHeader.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",memberHomePage.apHeaderControl.lblBal.getText(), FEMemberConstants.APHeader.BALANCE));
        Assert.assertEquals(page.apHeaderControl.lblBalCurrency.getText(), currency,String.format("ERROR: Expected is Balance currency is %s but found %s",memberHomePage.apHeaderControl.lblBalCurrency.getText(), FEMemberConstants.APHeader.BALANCE));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));
        Assert.assertEquals(page.apHeaderControl.lblLiability.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",memberHomePage.apHeaderControl.lblLiability.getText(), FEMemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.apHeaderControl.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 2 Profit & Loss page display in new tab\n" +
                "1. Start Date, End Date, Load Report\n" +
                "Note: Date will be based on time zone IST\n" +
                "2. Table with the header: Sport/Game, Profit/Loss");
        Assert.assertEquals(page.lblStartDate.getText(), FEMemberConstants.AccountStatementPage.START_DATE, String.format("ERROR! Expected Start Date but found %s",page.lblStartDate.getText()));
        Assert.assertEquals(page.lblEndDate.getText(), FEMemberConstants.AccountStatementPage.END_DATE, String.format("ERROR! Expected End Date but found %s",page.lblEndDate.getText()));
        Assert.assertEquals(page.lblNote.getText(), FEMemberConstants.AccountStatementPage.NOTES, String.format("ERROR! Current Note label shows %s",page.lblNote.getText()));
        Assert.assertEquals(page.btnLoadReport.getText(), FEMemberConstants.AccountStatementPage.LOAD_REPORT,String.format("ERROR! Expected Load Report but found %s",page.btnLoadReport.getText()));
        Assert.assertTrue(page.btnExport.isDisplayed(),"ERROR! Export button is not displayed");
        List<String> tblHeaders = page.tblSport.getColumnNamesOfTable(1);
        Assert.assertEquals(tblHeaders.size(), FEMemberConstants.ProfitAndLossPage.TABLE_SUMMARY_HEADER.size(), String.format("ERROR: The expected no of columns is %s but found %s", FEMemberConstants.ProfitAndLossPage.TABLE_SUMMARY_HEADER.size(),tblHeaders.size()));
        Assert.assertEquals(tblHeaders, FEMemberConstants.ProfitAndLossPage.TABLE_SUMMARY_HEADER,"ERROR: The expected not match with the actual!" );

      log("INFO: Executed completely");

    }

    /**
     * @title: Validate no console error when active Exchange Game product
     * @precondition: 1. Exchange Game is active for the account
     *                2. Login member site
     * @step: 1. Click on Exchange Games
     * @expect: 1. No console error display
     */
    @Test(groups = {"http_request"})
    public void HeaderSection_TC016(){
        log("@title: Validate no console error when active Exchange Game product");
        log("Step 1. Click on Exchange Games");
        EGHomePage exchangeHomePage = memberHomePage.switchExchangeGameTab();
       /* exchangememberHomePage.lblOmahaHiTitle.isDisplayed();

        log("Verify 1. No console error display");
        logBug("FAILED by bug #44567 ");
        boolean isError = hasHTTPRespondedOK();*/
       // Assert.assertTrue(isError, "ERROR: There are some response request error when active Exchange Game tab");
        log("INFO: Executed completely");
    }


    /**
     * @title: Validate no console error when active Live Dealer Asian product
     * @precondition:   1.Live Dealer Asian is active for the account
     *                  2. Login member site
     * @step:           1. Click on Live Dealer > Asian Room
     * @expect:         1. No console error display
     */
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

    /**
     * @title: Validate no console error when active Live Dealer European product
     * @precondition:   1.Live Dealer European is active for the account
     *                  2. Login member site
     * @step:           1. Click on Live Dealer > Asian Room
     * @expect:         1. No console error display
     */
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


    /**
     * @title: Validate no console error when clicking on sports menu
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Click on Sport menu: Home, In-Play, Soccer, Cricket, Tennis
     * @expect:  1. There is no console display when active sport menu
     */
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

    /**
     * @title: Validate can navigate to Soccer menu
     * @pre-condition
     *          1. Soccer sport is active for the login account
     *          2. Login member site
     * @steps:   1. Click on Soccer menu
     * @expect:  1. Soccer page display with the title: Soccer Highlights
     */
    @Test (groups = {"smoke"})
    public void HeaderSection_TC021(){
        log("@title: Validate can navigate to Soccer menu");
        log("Step 1. Click on Soccer menu");
        log("Verify: 1. Soccer page display with the title: Soccer Highlights");
        String sportName = "Soccer";
        SportPage page = memberHomePage.navigateSportMenu(sportName, SportPage.class);

        if(Objects.nonNull(page)){
            Assert.assertEquals(page.eventContainerControl.getSportHeader(),
                    String.format(FEMemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
                    String.format("ERROR! Expected Sport title is %s but found %s",String.format(FEMemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
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
    @Test(groups = {"smoke","we"})
    public void HeaderSection_TC022(){
        log("@title: Validate can navigate to Tennis menu");
        log("Step 1. Click on Tennis menu");
        log("Verify: 1. Tennis page display with the title: Tennis Highlights");
        String sportName = "Tennis";
        SportPage page = memberHomePage.navigateSportMenu(sportName, SportPage.class);
        if(Objects.nonNull(page)){
            Assert.assertEquals(page.eventContainerControl.getSportHeader(),
                    String.format(FEMemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
                    String.format("ERROR! Expected Sport title is %s but found %s",String.format(FEMemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
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
    @Test (groups = {"smoke"})
    public void HeaderSection_TC023(){
        log("@title: Validate can navigate to Horse Racing menu");
        log("Step 1. Click on Horse Racing menu");

        String sportName = "Horse Racing";
        memberHomePage.apHeaderControl.clickLeftMenu();
        RacingPage page = memberHomePage.apLeftMenuControl.clickLeftMenuItem(sportName,RacingPage.class);
        log("Verify: 1. Display 3 tabs: Today (active by default), Tomorrow, Next date");
        Assert.assertEquals(page.racingContainer.tabToday.getText(),"Today", String.format("ERROR! Expected is Today tab but found %s",page.racingContainer.tabToday.getText()));
        Assert.assertEquals(page.racingContainer.tabTomorrow.getText(),"Tomorrow", String.format("ERROR! Expected is Tomorrow tab but found %s",page.racingContainer.tabTomorrow.getText()));
       Assert.assertEquals(page.racingContainer.tabNextTomorrow.getText(),DateUtils.getDate(2,"EEEE","IST"),
               String.format("ERROR! Expected is %s tab but found %s",DateUtils.getDate(2,"EEEE","IST"),page.racingContainer.tabNextTomorrow.getText()));
       Assert.assertEquals(page.getActiveSport(),sportName,String.format("ERROR! Sport active in the left menu not is Horse Racing but found %s",page.getActiveSport()));
       log("INFO: Executed completely");
    }

    /**
     * @title: Validate can navigate to Cricket menu
     * @pre-condition
     *          1. Soccer sport is active for the login account
     *          2. Login member site
     * @steps:   1. Click on Cricket menu
     * @expect:  1. Cricket page display with the title: Cricket Highlights
     */
    @Test(groups = {"smoke"})
    public void HeaderSection_TC024(){
        log("@title: Validate can navigate to Cricket menu");
        log("Step 1. Click on Cricket menu");
        log("Verify: 1. Cricket page display with the title: Cricket Highlights");
        String sportName = "Cricket";
        SportPage page = memberHomePage.apHeaderControl.navigateSportMenu(sportName, SportPage.class);

        if(Objects.nonNull(page)){
            Assert.assertEquals(page.eventContainerControl.getSportHeader(),
                    String.format(FEMemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
                    String.format("ERROR! Expected Sport title is %s but found %s",String.format(FEMemberConstants.HomePage.SPORT_HIGHLIGHT_LABEL,sportName),
                            page.eventContainerControl.getSportHeader()));
        }else{
            log("SKIP! There is no Cricket menu display");
        }
        log("INFO: Executed completely");
    }



    /**
     * @title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Live Dealer Asian product
     * @pre-condition 1. Login member site
     * @steps: 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api
     *         2. Click Live Dealer Product
     * @expect: 1. Home Icon, lolog icon, timezone label
     *          2. Verify label: Credit, Balance, Outstanding
     *          3. Verify Credit, Balance, Outstanding of the player are corrected
     *          4. Outstanding should in red color
     */
    @Test (groups = {"lottery"})
    @Parameters({"skinName","currency"})
    public void HeaderSection_TC027(String skinName,String currency){
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
        Assert.assertEquals(page.lblMyBets.getText(),FEMemberConstants.Header.MY_BETS,"Failed! My Bets label is incorrect displayed");
        Assert.assertEquals(page.lblMyMarkets.getText(),FEMemberConstants.Header.MY_MARKET,"Failed! My Markets label is incorrect displayed");
        Assert.assertEquals(page.lblMyAccount.getText(),FEMemberConstants.Header.My_ACCOUNT,"Failed! My Account label is incorrect displayed");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.getUserBalance();
        Assert.assertEquals(page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE));
        Assert.assertEquals(page.lblBalanceCurrency.getText(), currency,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));

        Assert.assertEquals(page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(page.lblLiability.getColour("color").equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0, 1)' but found %s", page.lblLiability.getColour("color")));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Lottery&Slots product
     * @pre-condition 1. Login member site
     * @steps: 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api
     *          2. Click Lottery and Slots Product
     * @expect: 1. Home Icon, lolog icon, timezone label
     *          2. Verify label: Credit, Balance, Outstanding
     *          3. Verify Credit, Balance, Outstanding of the player are corrected
     *          4. Outstanding should in red color
     */
    @Test (groups = {"lottery"})
    @Parameters({"skinName","currency"})
    public void HeaderSection_TC028(String skinName,String currency){
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
        Assert.assertEquals(page.lblMyBets.getText(),FEMemberConstants.Header.MY_BETS,"Failed! My Bets label is incorrect displayed");
        Assert.assertEquals(page.lblMyMarkets.getText(),FEMemberConstants.Header.MY_MARKET,"Failed! My Markets label is incorrect displayed");
        Assert.assertEquals(page.lblMyAccount.getText(),FEMemberConstants.Header.My_ACCOUNT,"Failed! My Account label is incorrect displayed");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.getUserBalance();
        Assert.assertEquals(page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE));
        Assert.assertEquals(page.lblBalanceCurrency.getText(), currency,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));

        Assert.assertEquals(page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(page.lblLiability.getColour("color").equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0, 1)' but found %s", page.lblLiability.getColour("color")));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Exchange Game product
     * @pre-condition 1. Login member site
     * @steps: 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api
     *          2. Click Exchange Game Product
     * @expect: 1. Home Icon, lolog icon, timezone label
     *          2. Verify label: Credit, Balance, Outstanding
     *          3. Verify Credit, Balance, Outstanding of the player are corrected
     *          4. Outstanding should in red color
     */
    @Test (groups = {"smoke"})
    @Parameters({"skinName","currency"})
    public void HeaderSection_TC029(String skinName, String currency){
        log("@title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Exchange Game product");
        log("Step 1. Get the info: Credit, Balance, Outstanding of credit cash account from api");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        log("Step 2. Click Exchange Game Product");
        if(!memberHomePage.isProductActive("Exchange Games")){
            log("SKIP! Exchange Games product is NOT active for this account");
            return;
        }
        memberHomePage.apHeaderControl.clickLeftMenu();
        EGHomePage page = memberHomePage.apLeftMenuControl.clickLeftMenuItem(FEMemberConstants.HomePage.PRODUCTS.get("EXCH_GAMES"),EGHomePage.class);

        log("Verify Verify url exchange game");
        Assert.assertTrue(page.getPageUrl().contains("exchange-game"),"FAILED! Echange game url is not contains exchange-game");

        log("Verify 1.Verify Header section: Leftmenu icon, logo, My Account, Balance and Liability");
        String logoImgActual = page.apHeaderControl.imgLogo.getAttribute("src");
        Assert.assertTrue(page.apHeaderControl.imgLeftMenu.isDisplayed(), "ERROR:Left menu icon should not display");
        Assert.assertTrue(logoImgActual.contains(environment.getDirectusURL()),"FAILED! Log0 image not display");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.apHeaderControl.getUserBalance();
        Assert.assertEquals(page.apHeaderControl.lblBal.getText(), FEMemberConstants.APHeader.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",memberHomePage.apHeaderControl.lblBal.getText(), FEMemberConstants.APHeader.BALANCE));
        Assert.assertEquals(page.apHeaderControl.lblBalCurrency.getText(), currency,String.format("ERROR: Expected is Balance currency is %s but found %s",memberHomePage.apHeaderControl.lblBalCurrency.getText(), FEMemberConstants.APHeader.BALANCE));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));
        Assert.assertEquals(page.apHeaderControl.lblLiability.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",memberHomePage.apHeaderControl.lblLiability.getText(), FEMemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.apHeaderControl.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open My Bet page when clicking on Liability
     * @pre-condition 1. Login member site
     * @steps:  1. Click on Liability
     * @expect: 1.My Bet Page display
     */
    @Test (groups = {"smoke"})
    public void HeaderSection_TC041(){
        log("@title: Verify can open My Bet page when clicking on Liability");
        log("Step 1. Click on Liability");
        MyBetsPage myBetsPage =memberHomePage.apHeaderControl.clickLiabilityValue();

        log("1.Verify My Bet Page display after clicking on Liability");
        Assert.assertEquals(myBetsPage.lblProductTile.getText(),FEMemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get("Exchange"),"FAILED! Exchange product title is not display on My bet page by default");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open Account Statement page when clicking on Bal
     * @pre-condition 1. Login member site
     * @steps:  1 Click on Bal
     * @expect: 1.My Bet Page display
     */
    @Test (groups = {"smoke"})
    public void HeaderSection_TC042(){
        log("@title: Verify can open Account Statement page when clicking on Bal");
        log("Step 1 Click on Bal");
        AccountStatementPage accountStatementPage =memberHomePage.apHeaderControl.clickBalanceValue();

        log("1.Verify My Bet Page display after clicking on Liability");
        Assert.assertTrue(accountStatementPage.getPageUrl().contains("report/account-statement"),"FAILED! Account Statment does not display when clicking on Balance");

        log("INFO: Executed completely");
    }
}
