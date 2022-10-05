package backoffice.testcases.accountmanagement;

import baseTest.BaseCaseMerito;
import com.paltech.driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import backoffice.pages.bo.accountmanagement.LoginInfoPage;

import java.util.ArrayList;
import java.util.List;

public class LoginInfoTest extends BaseCaseMerito {
    /**
     * @title: Validate display activity log if account has place bet
     * @pre-condition:
     *           1. Have an member account place bet
     *          2. Login BO
     * @steps: 1. Access Member Management > Login Info
     *          2. Select Type: Activity
     *          3. Search the account has place bet today
     * @expect: 1. Verify the account has log
     */
    @Test (groups = {"smoke"})
    @Parameters({"satMemberLoginID","memberPassword","username","password"})
    public void BO_MM_Login_Info_001(String satMemberLoginID,String memberPassword,String username, String password) throws Exception {
        log("@title: Validate display activity log if account has place bet");
        log("@pre-condition:\n" +
                "  1. Have an member account place bet\n" +
                "   2. Login BO");
       /* BaseCaseSAT.loginMemberviaUI(environment.getSatDashboardURL(),satMemberLoginID, StringUtils.decrypt(memberPassword));
        backofficeHomePage satbackofficeHomePage = new backofficeHomePage();
        String odds ="30";
        String minBet = "2";
        SportPage sportPage = satbackofficeHomePage.navigateSportMenu("Cricket", SportPage.class);
        Event event = sportPage.eventContainerControl.getEvent(false,false,3,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        sportPage.clickEvent(event);
        sportPage.marketContainerControl.waitControlLoadCompletely(5);
        Market market = sportPage.marketContainerControl.getMarket(event,1,true);
        market.getOdd().click();
        sportPage.betSlipControl.placeBet(odds,minBet);
*/

        log("Step 1. Access Member Management > Login Info");
        DriverManager.getDriver().get(backofficeUrl);
        LoginInfoPage page = backofficeHomePage.navigateLoginInfo();

        log("Step 2. Select Type: Activity");
        log("Step 3. Search the account has place bet today");
        page.search(LoginInfoPage.TYPE.ACTIVITY,satMemberLoginID,"","","", LoginInfoPage.SEARCHBUTTON.TODAY);

        log("Verify 1. Verify the account has log");
        List<ArrayList<String>>lstLoginID = page.tblLoginInfoActivity.getRowsWithoutHeader(1,false);
        Assert.assertEquals(lstLoginID.get(0).get(page.colLoginID-1),satMemberLoginID,"FAILED! the account has place bet not display in Activity log");
        Assert.assertEquals(lstLoginID.get(0).get(page.colStatus-1),"Success","FAILED! the account has place bet not display in Activity log");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate display activity log when account login successfully
     * @pre-condition:
     *          1. Have an member login success in member site
     *          2. Login BO
     * @steps: 1. Access Member Management > Login Info
     *          2. Select Type: Log
     *          3. Search the account in precondition
     * @expect: 1. Verify the account has login log with the status Success
     */
    @Test (groups = {"smoke"})
    @Parameters({"satMemberLoginID"})
    public void BO_MM_Login_Info_002(String satMemberLoginID) throws Exception {
        log("@title: Validate display activity log when account login successfully");
        log("Step 1. Access Member Management > Login Info");
        LoginInfoPage page = backofficeHomePage.navigateLoginInfo();

        log("Step 2. Select Type: Log");
        log("Step 3. Search the account in precondition");
        page.search(LoginInfoPage.TYPE.LOG,satMemberLoginID,"","","", LoginInfoPage.SEARCHBUTTON.TODAY);

        log("Verify 1. Verify the account has login log with the status Success");
        List<String> lstLoginID = page.tblLoginInfoLog.getColumn(page.colLoginID,false);
        Assert.assertEquals(lstLoginID.get(0),satMemberLoginID,"FAILED! the account has place bet not display in Activity log");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate display activity log when account login failed
     * @pre-condition:
     *           1. Have an member account just login failed
     *          2. Login BO
     * @steps:  1. Access Member Management > Login Info
     *          2. Select Type: Log
     *          3. Search the account in precondition
     * @expect: 1. Verify the account has login log with the status Failed
     */
    @Test (groups = {"smoke"})
    @Parameters({"satMemberLoginID","username","password"})
    public void BO_MM_Login_Info_003(String satMemberLoginID,String username, String password) throws Exception {
        log("@title:Validate display activity log when account login failed");
        log("@pre-condition: 1. Have an member account just login failed");
       // BaseCaseMember.loginMemberviaUI(environment.getSatDashboardURL(),satMemberLoginID,"incorrectps12");

        log("Step 1. Access Member Management > Login Info");
        DriverManager.getDriver().get(backofficeUrl);
      // Helper.loginBOIgnoreCaptcha(environment.getSosURL(),environment.getDashboardURL(),username,password,true);
        LoginInfoPage page = backofficeHomePage.navigateLoginInfo();

        log("Step 2. Select Type: Log");
        log("Step3. Search the account in precondition");
        page.search(LoginInfoPage.TYPE.LOG,satMemberLoginID,"","","", LoginInfoPage.SEARCHBUTTON.TODAY);

        log("Verify 1. Verify the account has login log with the status Failed");
        List<ArrayList<String>> lstLoginID = page.tblLoginInfoLog.getRowsWithoutHeader(1,false);
        Assert.assertEquals(lstLoginID.get(0).get(page.colLoginID-1),satMemberLoginID,"FAILED! the account has place bet not display in Activity log");
        Assert.assertEquals(lstLoginID.get(0).get(page.colLoginStatus-1),"FAILED","FAILED! Status should be FAILED");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can view IP address log
     * @pre-condition:
     *           1.Login BO
     * @steps: 1. Access Member Management > Login Info
     *          2. Select Type: Log
     *          3. Search Today log
     *     4. If have today log then click on a IP address of a log
     * @expect: 1. Verify IP Address Log table display
     */
    @Test (groups = {"smoke"})
    @Parameters({"satMemberLoginID"})
    public void BO_MM_Login_Info_004(String satMemberLoginID) throws Exception {
        log("@title: Validate can view IP address log");
        log("Step 1. Access Member Management > Login Info");
        LoginInfoPage page = backofficeHomePage.navigateLoginInfo();

        log("Step 2. Select Type: Log");
        log("Step 3. Search Today log");
        page.search(LoginInfoPage.TYPE.LOG,"","","","", LoginInfoPage.SEARCHBUTTON.TODAY);

        log("Step 4. If have today log then click on a IP address of a log");
        page.clickIPAddress(satMemberLoginID);

        log("Verify 1. Verify IP Address Log table display");
        Assert.assertTrue(page.tblIPAddress.getColumn(page.colLoginIDIP,false).contains(satMemberLoginID),"FAILED! IP Address not contains the login id");

        log("INFO: Executed completely");
    }

}
