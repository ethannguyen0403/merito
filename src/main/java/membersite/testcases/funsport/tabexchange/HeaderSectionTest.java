package membersite.testcases.funsport.tabexchange;

import membersite.common.FEMemberConstants;
import membersite.controls.funsport.OddPageControl;
import membersite.objects.AccountBalance;
import membersite.objects.funsport.Odd;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.components.popups.MyMarketPopup;
import membersite.pages.all.tablivedealer.components.LiveDealer;
import membersite.pages.all.tablotteryslot.LotterySlotsPage;
import membersite.pages.funsport.tabexchange.AccountStatementPage;
import membersite.pages.funsport.tabexchange.MyBetsPage;
import membersite.pages.funsport.tabexchange.SearchResultPage;
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
    @Test(groups = {"smoke"})
    @Parameters({"currency"})
    public void HeaderSection_TC001(String currency) {
        log("@title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly");
        log("Step 1: Get the info: Credit, Balance, Outstanding of  credit cash account from api");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        log("Verify 1: Logo display in header section");
        Assert.assertTrue(memberHomePage.fsHeaderControl.imgLogo.isDisplayed(), "ERROR: Logo icon is not display");
        Assert.assertEquals(memberHomePage.fsHeaderControl.lnkMyBetsHistory.getText(),"My Bets/History","FAILED! My Het/History link not display");
        Assert.assertEquals(memberHomePage.fsHeaderControl.lnkMyMarkets.getText(),"My Markets","FAILED! My Market Label not display");
        Assert.assertEquals(memberHomePage.fsHeaderControl.txtFindTeams.getAttribute("placeholder"),"Find teams, competitions, races and more...","FAILED!Placeholder of search textbox display");
        Assert.assertEquals(memberHomePage.fsHeaderControl.ddbMyAccount.isDisplayed(),"Failed! My Account is incorrect displayed");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = memberHomePage.getUserBalance();
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(memberHomePage.lblLiabilityValue.getColour("color").equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0,  1)' but found %s", memberHomePage.lblLiabilityValue.getColour("color")));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can open my markets
     * pre-condition
     *          1. Login member site
     * @steps: 1. Click on  My Markets menu
     *         2. Close the popup
     * @expect: 1. Can Open May markets successfully
     *          - Popup Title: My Markets
     *          - Note: Date will be based on time zone IST (for FE old UI (FS, F24) and White Labels)
     *           - table with the header: Market ID, Market Start Time, Market Name (Reload button), Liability
     *      2. Popup is closed
     */
    @Test(groups = {"smoke"})
    public void HeaderSection_TC003() {
        log("@title: Validate can open my markets");
        log("Step 1: Get the info: Credit, Balance, Outstanding of  credit cash account from api");
        MyMarketPopup popup = memberHomePage.fsHeaderControl.openMyMarketPopup();

        log("Verify 1:  - Popup Title: My Markets\n" +
                "   - Note: Date will be based on time zone IST (for SAT and White Labels)\n" +
                "   - table with the header: Market ID, Market Start Time, Market Name (Reload button), Liability");
        Assert.assertEquals(popup.lblTitle.getText(), FEMemberConstants.MyMarketsPopup.TITLE, String.format("ERROR: Expected title is %s but found %s", FEMemberConstants.MyMarketsPopup.TITLE, popup.lblTitle.getText()));
        Assert.assertEquals(popup.lblNote.getText(), FEMemberConstants.MyMarketsPopup.NOTES, String.format("ERROR: Expected title is %s but found %s", FEMemberConstants.MyMarketsPopup.NOTES, popup.lblNote.getText()));
        List<String> lstMyMarketsHeaders = popup.tbMyMarkets.getColumnNamesOfTable(2);
        Assert.assertEquals(lstMyMarketsHeaders.size(), FEMemberConstants.MyMarketsPopup.TABLE_MY_MARKETS_HEADER.size(), String.format("ERROR: The expected no of columns is %s but found %s", FEMemberConstants.MyMarketsPopup.TABLE_MY_MARKETS_HEADER.size(), lstMyMarketsHeaders.size()));

        for (int i = 0; i < FEMemberConstants.MyMarketsPopup.TABLE_MY_MARKETS_HEADER.size(); i++) {
            String observed = lstMyMarketsHeaders.get(i);
            String expected = FEMemberConstants.MyMarketsPopup.TABLE_MY_MARKETS_HEADER.get(i);
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
    @Test(groups = {"smoke"})
    public void HeaderSection_TC004() {
        log("@title: Validate Liability in My market and outstanding is synchronized");
        log("Step 1. Get outstanding value");
        log("Step 2. Click on My market");
        log("Step 3. Sum liability of all market");
        AccountBalance balanceUI = memberHomePage.getUserBalance();
        MyMarketPopup popup = memberHomePage.fsHeaderControl.openMyMarketPopup();

        log("Verify 1:  Liability matched with outstanding\n" +
                "   If My markets display \"No records found\", Outstanding should be 0.00");
        if(popup.lblNoRecord.isDisplayed()){
            Assert.assertEquals(popup.lblNoRecord.getText(), FEMemberConstants.MyMarketsPopup.NO_RECORD_FOUNDS);
        }else {
            Assert.assertEquals(balanceUI.getExposure(), popup.totalLiability(), String.format("ERROR: Expected total Liability in My Market: %s is not matched with Outstanding:%s", popup.totalLiability(), balanceUI.getExposure()));
        }
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
    public void FE_HeaderSection_TC005(String username) {
        log("@title: Validate list menu in my account is corrected");
        log("Step 1. Click on My Account");
        memberHomePage.fsHeaderControl.ddbMyAccount.click();

        log("Verify 1: The list My account menu display correctly" );
        List<String> lst =memberHomePage.fsHeaderControl.getMyAccountListMenu();
        Assert.assertEquals(lst.get(0),FEMemberConstants.Header.My_ACCOUNT,String.format("ERROR: Expected login account is %s but found %s", FEMemberConstants.Header.My_ACCOUNT,lst.get(0)));
        Assert.assertEquals(lst.get(1),username,String.format("ERROR: Expected login account is %s but found %s", username,lst.get(1)));
        Assert.assertEquals(lst.get(2), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Details"),
                String.format("ERROR: Expected Configure Nickname is %s but found %s", FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Details"),lst.get(2)));
        Assert.assertEquals(lst.get(3), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Last Logins"),
                String.format("ERROR: Expected Configure Timezone is %s but found %s",FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Last Logins"),lst.get(3)));
        Assert.assertEquals(lst.get(4), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets/History"),String.format("ERROR: Expected %s but found %s",
                FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("My Bets/History"),lst.get(4)));
        Assert.assertEquals(lst.get(5), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"),
                String.format("ERROR: Expected login account is %s but found %s", FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Account Statement"),lst.get(5)));
        Assert.assertEquals(lst.get(6), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"),
                String.format("ERROR: Expected %s but found %s", FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Change Password"),lst.get(6)));
        Assert.assertEquals(lst.get(7), FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"),
                String.format("ERROR: Expected %s but found %s", FEMemberConstants.HomePage.DDB_MY_ACCOUNT.get("Logout"),lst.get(7)));
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
    public void FE_HeaderSection_TC006(){
        log("@title: Validate can navigate to Account Statement page");
        log("Step 1. Click My Account > Account Statement");
        AccountBalance balanceAPI = BetUtils.getUserBalance();
        AccountStatementPage page = memberHomePage.fsHeaderControl.openAccountStatement();
        page.btnLoadReport.isTextDisplayed(FEMemberConstants.AccountStatementPage.LOAD_REPORT,20);

        log("Verify 1.Verify Header section: Leftmenu icon, logo, My Account, Balance and Liability");
        Assert.assertTrue(page.fsHeaderControl.imgLogo.isDisplayed(),"FAILED! Log0 image not display");
        Assert.assertEquals( page.fsHeaderControl.ddbMyAccount.getSelectedOption(),FEMemberConstants.Header.My_ACCOUNT,"Failed! My Account label is incorrect displayed");
        Assert.assertFalse(page.fsHeaderControl.lnkMyMarkets.isDisplayed(),"Failed!My Markets label should not displayed in Account Statement page");
        Assert.assertFalse(page.fsHeaderControl.lnkMyBetsHistory.isDisplayed(),"Failed!My Bets History should not displayed in Account Statement page");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.getUserBalance();
      //  Assert.assertEquals(page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));
      //  Assert.assertEquals(page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING));
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
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can navigate to My Bet/History
     * @precondition: 1. Login member site
     * @step: 1. Click My Bet/History
     * @expect: 1. My Bets page is display in new tab
     *  -  Product dropdown box,
     *   - Order Type dropdown box, Start Date, End Date, Load Report button     *
     *  - Start Date, End Date Calendar, and Load report button
     *  - Note: Date will be based on time zone IST
     *  - Table with header: Market Name, Bet ID, Event ID, Selection, Type, Odds, Stake, Profit/Loss, Status, Placed Date, IP Address
     */
    @Test(groups = {"smoke"})
    public void FE_HeaderSection_TC007(){
        log("@title: Validate can navigate to My Bet");
        log("Step 1. Click My Account > My Bets");
        List <String> productList = memberHomePage.translateProductsActive(BetUtils.getProductInfo());

        MyBetsPage page = memberHomePage.fsHeaderControl.openMyBets();
        page.btnLoadReport.isTextDisplayed(FEMemberConstants.AccountStatementPage.LOAD_REPORT,20);
        List <String> myBetproductList = page.translateProductsActive(productList);

        log("Verify 1. My Bets page is display in new tab\n" +
                "-  Product dropdown box include all active product\n" +
                "- Order Type dropdown box, Start Date, End Date, Load Report button\n" +
                "- Start Date, End Date Calendar, and Load report button\n" +
                "- Note: Date will be based on time zone IST");
        Assert.assertEquals(page.lblOrderType.getText(), FEMemberConstants.MyBetsPage.ORDER_TYPE, String.format("ERROR! Expected Start Date but found %s",page.lblOrderType.getText()));
        Assert.assertEquals(page.lblStartDate.getText(), FEMemberConstants.MyBetsPage.START_DATE, String.format("ERROR! Expected Start Date but found %s",page.lblStartDate.getText()));
        Assert.assertEquals(page.lblEndDate.getText(), FEMemberConstants.MyBetsPage.END_DATE, String.format("ERROR! Expected End Date but found %s",page.lblEndDate.getText()));
        Assert.assertEquals(page.lblNote.getText(), FEMemberConstants.MyBetsPage.NOTES, String.format("ERROR! Current Note label shows %s",page.lblNote.getText()));
        Assert.assertEquals(page.btnLoadReport.getText(), FEMemberConstants.MyBetsPage.LOAD_REPORT,String.format("ERROR! Expected Load Report but found %s",page.btnLoadReport.getText()));
        Assert.assertEquals(page.ddbProduct.getFirstSelectedOption().trim(), FEMemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get(productList.get(0)),
                String.format("ERROR!Product dropdown default value is %s", page.ddbProduct.getFirstSelectedOption().trim()));
        Assert.assertTrue(page.ddbProduct.areOptionsMatched(myBetproductList),"ERROR! List products not match with expected");
        List<String> tblHeaders = page.tblReport.getColumnNamesOfTable(1);

        log("Verify: Table with header: Market Name, Bet ID, Event ID, Selection, Type, Odds, Stake, Profit/Loss, Status, Placed Date, IP Address");
        Assert.assertEquals(tblHeaders.size(), FEMemberConstants.MyBetsPage.TABLE_HEADER.size(), String.format("ERROR: The expected no of columns is %s but found %s", FEMemberConstants.MyBetsPage.TABLE_HEADER.size(),tblHeaders.size()));
        Assert.assertEquals(tblHeaders, FEMemberConstants.MyBetsPage.TABLE_HEADER,"ERROR! Header not match with the expected!");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Active product is display
     * @precondition: 1. Login member site
     * @step: 1. Get all product is active for login account
     * @expect: 1. Validate Product menu display active data
     * "EXCHANGE": Exchange product display
     * "SUPER_SPADE" Live Dealer Asian product, "DIGIENT": Live Dealer European product,
     * Live Dealer Asian and European is group under Live Dealer
     *  "EZUGI" Lottery & Slots display
     *  "EXCH_GAMES": Exchange game product
     */
    @Test(groups = {"regression"})
    public void FE_HeaderSection_TC015(){
        log("@title: Validate Active product is display");
        log("Step 1. Get all product is active for login account");

        log("Verify 1. Validate Product menu display active data\n" +
                "     * \"EXCHANGE\": Exchange product display\n" +
                "     * \"SUPER_SPADE\" Live Dealer Asian product, \"DIGIENT\": Live Dealer European product,\n" +
                "     * Live Dealer Asian and European is group under Live Dealer\n" +
                "     *  \"EZUGI\" Lottery & Slots display\n" +
                "     *  \"EXCH_GAMES\": Exchange game product");

        log("Verify: Table with header: Market Name, Bet ID, Event ID, Selection, Type, Odds, Stake, Profit/Loss, Status, Placed Date, IP Address");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate no console error when active Exchange Game product
     * @precondition: 1. Exchange Game is active for the account
     * 2. Login member site
     * @step: 1. Click on Exchange Games
     * @expect: 1. No console error display
     */
    @Test(groups = {"regression"})
    public void FE_HeaderSection_TC016(){
        log("@title: Validate no console error when active Exchange Game product");
        log("Step 1. Click on Exchange Games");

        log("Verify 1. No console error display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate no console error when active Lottery & Slots product
     * @precondition: 1.Lottery & Slots is active for the account
     * 2. Login member site
     * @step: 1. Click on Lottery & Slots product
     * @expect: 1. No console error display
     */
    @Test(groups = {"regression"})
    public void FE_HeaderSection_TC017(){
        log("@title: Validate no console error when active Lottery & Slots product");
        log("Step  1. Click on Lottery & Slots product");

        log("Verify 1. No console error display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate no console error when active live Dealer Asian product
     * @precondition: 1 Live Dealer Asian is active for the account
     * 2. Login member site
     * @step: 1. Click on Live Dealer Asian product
     * @expect: 1. No console error display
     */
    @Test(groups = {"regression"})
    public void FE_HeaderSection_TC018(){
        log("@title: Validate no console error when active  Live Dealer Asian product");
        log("Step  1. Click on Live Dealer Asian product");

        log("Verify 1. No console error display");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate no console error when active live Dealer European product
     * @precondition: 1 Live Dealer European is active for the account
     * 2. Login member site
     * @step: 1. Click on Live Dealer European product
     * @expect: 1. No console error display
     */
    @Test(groups = {"regression"})
    public void FE_HeaderSection_TC019(){
        log("@title: Validate no console error when active  Live Dealer European product");
        log("Step  1. Click on Live Dealer European product");

        log("Verify 1. No console error display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate no console error when clicking on sports menu
     * @precondition: 1. Sports should unblock some events
     * 2. Login member site
     * @step: 1. Click on Sport menu: Home, In-Play, Soccer, Cricket, Tennis
     * @expect: There is no console display when active sport menu
     */
    @Test(groups = {"regression"})
    public void FE_HeaderSection_TC020(){
        log("@title: Validate no console error when clicking on sports menu");
        log("Step 1. Click on Sport menu: Home, In-Play, Soccer, Cricket, Tennis");

        log("Verify 1. There is no console display when active sport menu");

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
    @Test (groups = {"smoke"}, priority=3)
    public void FE_HeaderSection_TC025(){
        log("@title: Validate can navigate to correct market when click on market in My market");
        log("Step 1. Click on My market ");
        MyMarketPopup popup = memberHomePage.fsHeaderControl.openMyMarketPopup();
        List<String> marketInfo = popup.getMarketInfo(1);
        if (marketInfo.get(0).equals(FEMemberConstants.MyMarketsPopup.NO_RECORD_FOUNDS))
        {
            log(String.format("SKIPPED! Due to %s", FEMemberConstants.MyMarketsPopup.NO_RECORD_FOUNDS));
            return;
        }
        log("Step 2. Click on market in my market popup");
        MarketPage page = (MarketPage) popup.navigateToMarket(1);
        page.marketOddControl.isInvisible(10);

        log("Verify: 1. The corresponding market is navigate");
        String actual =page.marketOddControl.lblMarketTitle.getText();
        Assert.assertEquals(actual, marketInfo.get(popup.colMarketName-1),String.format("ERROR: Click on %s but my market display the title %s",marketInfo.get(popup.colMarketName-1),actual));
        log("INFO: Executed completely");
    }
    
    /**
     * @title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Live Dealer product
     * @precondition: 1.Login member site
     * @step: 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api
     * 2. Click Live Dealer Product
     * @expect: 1. Home Icon, lolog icon, timezone label
     * 2. Verify label: Credit, Balance, Outstanding
     * 3. Verify Credit, Balance, Outstanding of the player are corrected
     * 4. Outstanding should in red color
     */
    @Test (groups = {"lottery"})
    @Parameters({"currency"})
    public void FE_HeaderSection_TC027(String currency){
        log("@title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Live Dealer product");
        log("Step 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        log("Step 2. Click Live Dealer Product");
        if(!memberHomePage.isProductActive("Live Dealer Asian")){
            log("SKIP! Live Dealer Asian product is NOT active for this account");
            return;
        }
        LiveDealer page = memberHomePage.switchLiveDealerTab();

        log("Verify 1:Logo is displayed");
        Assert.assertTrue(page.fsHeaderControl.imgLogo.isDisplayed(), "ERROR:Logo is not display");

        log("Verify 2:Label: Annoucment icon, My Bet, My Market, My Account");
        Assert.assertTrue(page.fsHeaderControl.lnkMyBetsHistory.isDisplayed(),"Failed! My Bets/History Link label should display");
        Assert.assertTrue(page.fsHeaderControl.lnkMyMarkets.isDisplayed(),"Failed! My Market Link label should display");
        Assert.assertTrue(page.iconAnnouncement.isDisplayed(),"Failed!Announcement icon should display");
        Assert.assertEquals(page.fsHeaderControl.ddbMyAccount.isDisplayed(),"Failed! My Account label should display");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.getUserBalance();
        Assert.assertEquals(page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE));
        Assert.assertEquals(page.lblBalanceCurrency.getText(), currency,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(),currency));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));

        Assert.assertEquals(page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(page.lblLiabilityValue.getColour("color").equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0, 1)' but found %s", page.lblLiabilityValue.getColour("color")));

        log("INFO: Executed completely");
    }
    /**
     * @title:Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Lottery&Slots product
     * @precondition: 1.Login member site
     * @step: 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api
     * 2. Click Live Dealer Product
     * @expect: 1. Home Icon, logo icon, timezone label
     * 2. Verify label: Credit, Balance, Outstanding
     * 3. Verify Credit, Balance, Outstanding of the player are corrected
     * 4. Outstanding should in red color
     */
    @Test (groups = {"lottery"})
    @Parameters({"currency"})
    public void FE_HeaderSection_TC028(String currency){
        log("@title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Lottery & Slot product");
        log("Step 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        log("Step 2. Click Lottery and Slots Product");
        if(!memberHomePage.isProductActive("Live Dealer Asian")){
            log("SKIP! Live Dealer Asian product is NOT active for this account");
            return;
        }
        LotterySlotsPage page = memberHomePage.switchLotterySlotsTab();

        log("Verify 1:Logo is displayed");
        Assert.assertTrue(page.imgLogo.isDisplayed(), "ERROR:Logo is not display");

        log("Verify 2:Label: Annoucment icon, My Bet, My Market, My Account");
        Assert.assertTrue(page.fsHeaderControl.lnkMyBetsHistory.isDisplayed(),"Failed! My Bets/History Link label should display");
        Assert.assertTrue(page.fsHeaderControl.lnkMyMarkets.isDisplayed(),"Failed! My Market Link label should display");
        Assert.assertEquals(page.fsHeaderControl.ddbMyAccount.isDisplayed(),"Failed! My Account label should display");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.getUserBalance();
        Assert.assertEquals(page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE));
        Assert.assertEquals(page.lblBalanceCurrency.getText(), currency,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(),currency));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));

        Assert.assertEquals(page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(page.lblLiabilityValue.getColour("color").equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0, 1)' but found %s", page.lblLiabilityValue.getColour("color")));

        log("INFO: Executed completely");
    }
    /**
     * @title:Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Exchange Game product
     * @precondition: 1.Login member site
     * @step: 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api
     * 2. Click Exchange Game  Product
     * @expect: 1. Home Icon, logo icon, timezone label
     * 2. Verify label: Credit, Balance, Outstanding
     * 3. Verify Credit, Balance, Outstanding of the player are corrected
     * 4. Outstanding should in red color
     */
    @Test (groups = {"lottery"})
    @Parameters({"currency"})
    public void FE_HeaderSection_TC029(String currency){
        log("@title: Validate Credit, Balance, Outstanding of Credit Cash account  display correctly when active Exchange Game product");
        log("Step 1. Get the info: Credit, Balance, Outstanding of  credit cash account from api");
        AccountBalance balanceAPI = BetUtils.getUserBalance();

        log("Step 2. Click Lottery and Slots Product");
        if(!memberHomePage.isProductActive("Live Dealer Asian")){
            log("SKIP! Live Dealer Asian product is NOT active for this account");
            return;
        }
        LotterySlotsPage page = memberHomePage.switchLotterySlotsTab();

        log("Verify 1:Logo is displayed");
        Assert.assertTrue(page.imgLogo.isDisplayed(), "ERROR:Logo is not display");

        log("Verify 2:Label: Annoucment icon, My Bet, My Market, My Account");
        Assert.assertTrue(page.fsHeaderControl.lnkMyBetsHistory.isDisplayed(),"Failed! My Bets/History Link label should display");
        Assert.assertTrue(page.fsHeaderControl.lnkMyMarkets.isDisplayed(),"Failed! My Market Link label should display");
        Assert.assertTrue(page.iconAnnouncement.isDisplayed(),"Failed!Announcement icon should display");
        Assert.assertEquals( page.fsHeaderControl.ddbMyAccount.isDisplayed(),"Failed! My Account label should display");

        log("Verify 3: Verify Credit, Balance, Outstanding of the player are corrected");
        AccountBalance balanceUI = page.getUserBalance();
        Assert.assertEquals(page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(), FEMemberConstants.Header.BALANCE));
        Assert.assertEquals(page.lblBalanceCurrency.getText(), currency,String.format("ERROR: Expected is Balance label is %s but found %s",page.lblBalanceTitle.getText(),currency));
        Assert.assertTrue(balanceAPI.getBalance().equals(balanceUI.getBalance()),String.format("ERROR: The expected  balance is '%s' but found '%s'",balanceAPI.getBalance(), balanceUI.getBalance()));

        Assert.assertEquals(page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING,String.format("ERROR: Expected is Liability label is %s but found %s",page.lblOutstandingTitle.getText(), FEMemberConstants.Header.OUTSTANDING));
        Assert.assertEquals(page.lblLiabilityCurrency.getText(), currency,String.format("ERROR: Expected is Liability currency is incorrect"));
        Assert.assertTrue(balanceAPI.getExposure().equals(balanceUI.getExposure()),String.format("ERROR: The expected  Liability is '%s' but found '%s'",balanceAPI.getExposure(), balanceUI.getExposure()));

        log("Verify 4: Outstanding should in red color");
        Assert.assertTrue(page.lblLiabilityValue.getColour("color").equals("rgba(255, 0, 0, 1)"),String.format("ERROR: Expected Outstanding color is 'Red - HEX[#ff000] rgba(255, 0, 0, 1)' but found %s", page.lblLiabilityValue.getColour("color")));
        log("INFO: Executed completely");
    }

    /**
     * @title: Can search event
     * @precondition: 1.Login member site
     * @step: 1/ Input a active event in search textbox
     * @expect: 1. Search result contain event
     */
    @Test(groups = {"smoke"})
    public void FE_HeaderSection_TC030(){
        log("@title: Can search event");
        Odd odd = memberHomePage.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        log("Step 1/ Input a active event in search textbox");
        SearchResultPage searchPage = memberHomePage.fsHeaderControl.findEvent(odd.getEventName());

        log("Verify 1. Search result contain event");
        Assert.assertEquals(searchPage.lblSearchResult.getText(),"Search results","FAILED! Search result page not display");
        Assert.assertTrue(searchPage.lblResultContains.getAttribute("innerHTML").contains(odd.getEventName()),"FAILED! Result not contains search key");

        log("INFO: Executed completely");
    }

    /**
     * @title: Can active event page from search page
     * @precondition: 1 Login member site
     * @step: 1/ Input and active event in search textbox
     * 2/ Click on event in search page
     * @expect: 1. Verify event page display correctly
     */
    @Test(groups = {"smoke"})
    public void FE_HeaderSection_TC031(){
       log("@title: Can active event page from search page");
        Odd odd = memberHomePage.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        log("Step 1/ Input a active event in search textbox");
        SearchResultPage searchPage = memberHomePage.fsHeaderControl.findEvent(odd.getEventName());

        log("Step 2  Click on event in search page");
        MarketPage eventPage =searchPage.clickEventResult(odd.getEventName());

        log("Verify 1. Verify event page display correctly");
        if(Objects.nonNull(eventPage)){
            Assert.assertEquals(eventPage.marketOddControl.lblEventTitle.getText(),odd.getEventName(),"FAILED! Event page title not match with search key");
        }else
            Assert.assertFalse(true,"FAILED! Event exist but have no result");

        log("INFO: Executed completely");
    }
}
