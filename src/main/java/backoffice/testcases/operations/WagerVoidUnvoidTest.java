package backoffice.testcases.operations;

import backoffice.common.BOConstants;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo.operations.WagerVoidUnvoidPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class WagerVoidUnvoidTest extends BaseCaseTest {

    /**
     * @title: Validate can search void/un-void wager by Wager ID
     * @pre-condition: 1.Login member and search any wager settled
     * 2.Login BO
     * @steps: 1. Access Operations > Wager Void/Un-void
     * 2. Select void by Wager
     * 3. Select Exchange Product
     * 4. Search by: Wager ID
     * 5. Input the wager ID get in member site and click search button
     * @expect: 1. Verify Wager info display correctly as precondition
     */
    @Test(groups = {"smoke"})
    @Parameters("wagerID")
    public void BO_Operations_Wager_Void_Unvoid_001(String wagerID) {
        log("@title: Validate can search void/un-void wager by Wager ID");
        log("Step 1. Access Operations > Wager Void/Un-void");
        //String wagerID ="58291";
        WagerVoidUnvoidPage page = backofficeHomePage.navigateWagerVoidUnvoid();

        log("Step 2. Select void by Wager");
        log("Step 3. Select Exchange Product");
        log("Step  4. Search by: Wager ID");
        log("Step 5. Input the wager ID get in member site and click search button");
        page.searchByWager("Exchange", wagerID);

        log("Verify 1. Verify Wager info display correctly as precondition");
        List<String> lstWagerInfo = page.tblWager.getColumn(page.colWagerID, false);
        Assert.assertEquals(lstWagerInfo.get(0), wagerID, "FAILED! Result table not display the searching data");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search void/un-void wager by Nick Name
     * @pre-condition: 1.Login member and search any wager settled and get place date
     * 2.Login BO
     * @steps: 1. Access Operations > Wager Void/Un-void
     * 2. Select void by Wager
     * 3. Select Exchange Product
     * 4. Search by: Nick Name
     * 5. Input Nick name and place date range then click Search button
     * @expect: 1. Verify Wager info display correctly as pre-condition and has place date in search date range
     */
    @TestRails(id = "643")
    @Test(groups = {"smoke"})
    @Parameters("satMemberLoginID")
    public void BO_Operations_Wager_Void_Unvoid_643(String satMemberLoginID) {
        log("@title: Validate can search void/un-void wager by Nick Name");
        log("Step 1. Access Operations > Wager Void/Un-void");
        String toDate = DateUtils.getDate(0, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        String fromDate = DateUtils.getDate(-45, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        WagerVoidUnvoidPage page = backofficeHomePage.navigateWagerVoidUnvoid();

        log("Step 2. Select void by Wager");
        log("Step 3. Select Exchange Product");
        log("Step 4. Search by: Nick Name");
        log("Step 5. Input Nick name and place date range then click Search button");
        page.searchByUsername("Exchange", satMemberLoginID, fromDate, toDate);

        log("Verify 1. Verify Wager info display correctly as pre-condition and has place date in search date range");
        List<String> lstWagerInfo = page.tblWager.getColumn(page.colNickname, false);
        for (String acutalUsername : lstWagerInfo) {
            Assert.assertEquals(acutalUsername, satMemberLoginID, "FAILED! Result table not display the searching data");
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search void/un-void wager by Event List
     * @pre-condition: 1.Login member and search any wager settled and get the info: Sport, Competition, Event of that wager
     * 2.Login BO
     * @steps: 1. Access Operations > Wager Void/Un-void
     * 2. Select void by Wager
     * 3. Select Exchange Product
     * 4. Search by: Event List
     * 5. Select Event Date, Sport, Competition, Event and click Search
     * @expect: 1. Verify Wager info display correctly with search criteria
     */
    @TestRails(id = "644")
    @Test(groups = {"smoke"})
    public void BO_Operations_Wager_Void_Unvoid_644() {
        log("@title: Validate can search void/un-void wager by Event List");
        log("Step 1. Access Operations > Wager Void/Un-void");
        // String eventName = "Heilongjiang Lava Spring v Sichuan Jiuniu FC";
        WagerVoidUnvoidPage page = backofficeHomePage.navigateWagerVoidUnvoid();

        log("Step 2. Select void by Wager");
        log("Step 3. Select Exchange Product");
        log("Step 4. Search by: Event List");
        log("Step 5. Select Event Date, Sport, Competition, Event and click Search");
        page.searchByEventList("Exchange", "", 1, 1, 1);
        String eventName = page.ddbEvent.getFirstSelectedOption().trim();

        log("Verify 1. Verify Wager info display correctly as pre-condition and has place date in search date range");
        List<String> lstWagerInfo = page.tblWager.getColumn(page.colDescription, false);
        for (String description : lstWagerInfo) {
            Assert.assertTrue(description.contains(eventName), "FAILED! Result table not display the searching data");
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search wager void/un-void by market ID
     * @pre-condition: 1.Login member and search any wager settled and get market id
     * 2.Login BO
     * @steps: 1. Access Operations > Wager Void/Un-void
     * 2. Select void by Wager
     * 3. Select Exchange Product
     * 4. Search by: Market
     * 5. Input Market ID and click Search
     * @expect: 1. Verify all wagers belong to the market are display
     */
    @TestRails(id = "645")
    @Test(groups = {"smoke"})
    public void BO_Operations_Wager_Void_Unvoid_645() {
        log("@title: Validate can search wager void/un-void by market ID");
        log("Step 1. Access Operations > Wager Void/Un-void");
        String marketID = "174479114";
        WagerVoidUnvoidPage page = backofficeHomePage.navigateWagerVoidUnvoid();

        log("Step 2. Select void by Wager");
        log("Step 3. Select Exchange Product");
        log("Step 4. Search by: Market");
        log("Step 5. Input Market ID and click Search");
        page.searchByMarket(marketID);

        log("Verify 1. Verify all wagers belong to the market are display");
        List<String> lstWagerInfo = page.tblMarket.getColumn(page.colMarketID, false);
        Assert.assertEquals(lstWagerInfo.get(0), marketID, "FAILED! Result table not display the searching data");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate void/unvoid exchange wager in settled status
     * @pre-condition: 1.Login member and search any wager settled and get the current balance and exposure of the account
     * 2.Login BO
     * @steps: 1. Access Operations > Wager Void/Un-void
     * 2. Select void by, Wager and search by wager
     * 3. Click on void and enter remark then click Void
     * 4.Search the wager and unvoid
     * @expect: 1. Verify the message  display "Void Wager successful. Report might take 30 minutes to regenerate."
     * 2. In member site > My Bet >  the wager is in void status. Balance and exposure is deducted base on profit/loss of the wager. Example: Account A balance : 50, Outstanding: 0
     * If void bet won 5 => Balance = 45, outstanding :0
     * if void bet lose 5 => Balance =55 and Outstanding: 0
     * 3. Verify the message  display after unvoid "Un-void Wager successful. Report might take 30 minutes to regenerate.". Verify the balance in member site is reverted as precondition. Bet is in settled status
     */
    @Test(groups = {"deprecated"})// this tc should handle in member site
    @Parameters({"satMemberLoginID", "memberPassword", "username", "password"})
    public void BO_Operations_Wager_Void_Unvoid_005(String satMemberLoginID, String memberPassword, String username, String password) throws Exception {

        /*log("@title: Validate void/unvoid exchange wager in settled status");
        String starDate = DateUtils.getDate(-45,"yyyy-MM-dd",BOConstants.GMT_IST);
        String endDate = DateUtils.getDate(0,"yyyy-MM-dd",BOConstants.GMT_IST);
        Helper.loginFairExchange(environment.getSatSOSURL(),environment.getSatDashboardURL(),satMemberLoginID,memberPassword,true);
        backofficeHomePage satMemberbackofficeHomePage = new backofficeHomePage();
        AccountBalance accountBalance = utils.betplacement.BetUtils.getUserBalance(  true);

        MyBetsPage myBetsPage = satMemberbackofficeHomePage.openMyBets();
        myBetsPage.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("SETTLED"),starDate,endDate);
        if(myBetsPage.tblReport.getRowsWithoutHeader(1,false).get(0).get(0).equals(Constants.NO_RECORD_FOUND)){
            Assert.assertTrue(true,"By Passed as there is no data when filter Settled wagers");
            return;
        }

        List<ArrayList<String>> wagerInfo= myBetsPage.tblReport.getRowsWithoutHeader(1,false);
        boolean type = wagerInfo.get(0).get(myBetsPage.colType -1).equalsIgnoreCase("BACK")?true:false;
        String stake= wagerInfo.get(0).get(myBetsPage.colStake - 1);
        String odds= wagerInfo.get(0).get(myBetsPage.colOdd - 1);
        String wagerID = wagerInfo.get(0).get(myBetsPage.colBetID-1);
        String profit = wagerInfo.get(0).get(myBetsPage.colProfitLoss-1);

        Order order =  new Order.Builder()
                .isBack(type)
                .stake(stake)
                .odds(odds)
                .build();
        myBetsPage.logout();

        log("Step 1. Access Operations > Wager Void/Un-void");
        Helper.loginBOIgnoreCaptcha(environment.getSosURL(),environment.getDashboardURL(),username,password,true);
        WagerVoidUnvoidPage page = backofficeHomePage.navigateWagerVoidUnvoid();

        log("Step 2. Select void by, Wager and search by wager");
        page.searchByWager("Exchange", wagerID);

        log("Step 3. Click on void and enter remark then click Void");
        page.voidUnvoidWager(wagerID, WagerVoidUnvoidPage.ACTION.VOID,"Automation test Void the wager");
        AlertMessageBox alert = new AlertMessageBox();

        log("Verify 1. Verify the message  display \"Void Wager successful. Report might take 30 minutes to regenerate.\"");
        Assert.assertTrue(alert.getSuccessAlert().contains(String.format(BOConstants.Operations.VoidUnvoidWager.SUCCESS_MESSAGE,"Void")),"FAILED! Success message not display as expectation");
        AccountBalance userBalanceAfterVoid = page.calculateBalanceVoidUnvoidWager(accountBalance,profit,true);

        log("Verify 2. In member site > My Bet >  the wager is in void status. Balance and exposure is deducted base on profit/loss of the wager");
        Helper.loginFairExchange(environment.getSatSOSURL(),environment.getSatDashboardURL(),satMemberLoginID,memberPassword,true);
        AccountBalance actualBalanceAfterVoid = satMemberbackofficeHomePage.getUserBalance();
        Assert.assertEquals(actualBalanceAfterVoid.getBalance(),userBalanceAfterVoid.getBalance(),"FAILED! Balance after Void wager is incorrect");
        Assert.assertEquals(actualBalanceAfterVoid.getExposure(),accountBalance.getExposure(),"FAILED! Balance after Void wager is incorrect");

        myBetsPage = satMemberbackofficeHomePage.openMyBets();
        myBetsPage.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("VOIDED"),starDate,endDate);
        List<String> lstVoidedWager = myBetsPage.tblReport.getColumn(myBetsPage.colBetID,false);
        Assert.assertTrue(lstVoidedWager.contains(wagerID),String.format("FAILED! Voided wager %s not display in Voided list", wagerID));

        log("Step 4.Search the wager and unvoid");
        Helper.loginBOIgnoreCaptcha(environment.getSosURL(),environment.getDashboardURL(),username,password,true);
        page.voidUnvoidWager(wagerID, WagerVoidUnvoidPage.ACTION.UNVOID,"Automation test unvoid the wager");

        log("Verify 3. Verify the message  display after unvoid \"Un-void Wager successful. Report might take 30 minutes to regenerate." +
                "\". Verify the balance in member site is reverted as precondition. Bet is in settled status");
        Assert.assertTrue(alert.getSuccessAlert().contains(String.format(BOConstants.Operations.VoidUnvoidWager.SUCCESS_MESSAGE,"Un-void")),"FAILED! Success message not display as expectation");

        log("Verify 4. In member site > My Bet >  the wager is in void status. Balance and exposure is correct after unvoid");
        Helper.loginFairExchange(environment.getSatSOSURL(),environment.getSatDashboardURL(),satMemberLoginID,memberPassword,true);
        actualBalanceAfterVoid = satMemberbackofficeHomePage.getUserBalance();
        Assert.assertEquals(actualBalanceAfterVoid.getBalance(),userBalanceAfterVoid.getBalance(),"FAILED! Balance after Void wager is incorrect");
        String expectedExposureAfterUnvoid = String.format("%.2f",Double.parseDouble(accountBalance.getExposure() )+ Double.parseDouble(order.getLiability()));
        Assert.assertEquals(actualBalanceAfterVoid.getExposure(),expectedExposureAfterUnvoid,"FAILED! Balance after Void wager is incorrect");

        myBetsPage = satMemberbackofficeHomePage.openMyBets();
        myBetsPage.filter(DDB_PRODUCT_FILTER.get("Exchange"),DDB_ORDER_TYPE_FILTER.get("MATCHED"),starDate,endDate);
        lstVoidedWager = myBetsPage.tblReport.getColumn(myBetsPage.colBetID,false);
        Assert.assertTrue(lstVoidedWager.contains(wagerID),String.format("FAILED! Voided wager %s not display in Voided list", wagerID));

        log("INFO: Executed completely");*/
    }

    /**
     * @title: Validate void/unvoid exchange wager in Match status on the market have 1 wager
     * @pre-condition: 1.Login member and have a matched bet on a market , get balance and exposure of the account
     * 2.Login BO
     * @steps: 1. Access Operations > Wager Void/Un-void
     * 2. Select void by, Wager and search by wager
     * 3. Click on void and enter remark then click Void
     * 4.Search the wager and unvoid
     * @expect: 1. Verify the message  display "Void Wager successful. Report might take 30 minutes to regenerate."
     * 2. In member site > My Bet >  the wager is in void status. Balance and exposure is deducted base on profit/loss of the wager. Example: Account A balance : 50, Outstanding: 0
     * If void bet won 5 => Balance = 45, outstanding :0
     * if void bet lose 5 => Balance =55 and Outstanding: 0
     * 3. Verify the message  display after unvoid "Un-void Wager successful. Report might take 30 minutes to regenerate.".
     */
    @TestRails(id = "646")
    @Test(groups = {"smoke"})
    @Parameters({"wagerID"})
    public void BO_Operations_Wager_Void_Unvoid_646(String wagerID) {
        log("@title:Validate void/unvoid exchange wager in Match status on the market have 1 wager");
        log("Step 1. Access Operations > Wager Void/Un-void");
        // String wagerID ="101851";
        WagerVoidUnvoidPage page = backofficeHomePage.navigateWagerVoidUnvoid();

        log("Step 2. Select void by, Wager and search by wager");
        page.searchByWager("Exchange", wagerID);

        log("Step 3. Click on void and enter remark then click Void");
        page.voidUnvoidWager(wagerID, WagerVoidUnvoidPage.ACTION.VOID, "Automation test Void the wager");
        AlertMessageBox alert = new AlertMessageBox();

        log("Verify 1. Verify the message  display \"Void Wager successful. Report might take 30 minutes to regenerate.\"");
        Assert.assertTrue(alert.getSuccessAlert().contains(String.format(BOConstants.Operations.VoidUnvoidWager.SUCCESS_MESSAGE, "Void")), "FAILED! Success message not display as expectation");

        log("Verify 2. In member site > My Bet >  the wager is in void status. Balance and exposure is deducted base on profit/loss of the wager");

        log("Step 4.Search the wager and unvoid");
        page.voidUnvoidWager(wagerID, WagerVoidUnvoidPage.ACTION.UNVOID, "Automation test unvoid the wager");

        log("Verify 3. Verify the message  display after unvoid \"Un-void Wager successful. Report might take 30 minutes to regenerate.\"");
        alert = new AlertMessageBox();
        Assert.assertTrue(alert.getSuccessAlert().contains(String.format(BOConstants.Operations.VoidUnvoidWager.SUCCESS_MESSAGE, "Un-void")), "FAILED!Unvoid message not display as expectation");

        log("INFO: Executed completely");
    }

}
