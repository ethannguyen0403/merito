package api.testcases.MeritoAPI;

import api.objects.meritoAPI.AccountBalance;
import api.objects.meritoAPI.AccountStatement;
import api.objects.meritoAPI.result.AccountStatementResult;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.AccountStatementUtils;
import api.utils.MeritoAPI.GetAccoutBalanceUtils;
import api.utils.MeritoAPI.LoginMemberUtils;
import api.utils.testraildemo.TestRails;
import com.paltech.utils.DateUtils;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MA014_GetAccountStatementTest extends BaseCaseAPI {


    /**
     * @title: Validate can get account statement
     * @Percondition: Login api and get token code
     * @steps: 1/ Access get event api get Account statement
     * 2/ Input valid info and submit
     * @expect: Validate can get account statement
     */

    @TestRails(id = "438")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke123"})
    public void MA014_AccountStatementTest_438(String username, String password) throws Exception {
        log("@title: Validate can get account statement");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();
        String today = DateUtils.getDate(0, "yyyy-MM-dd", "GMT-4");

        log("Step 3 Access get account statement api");
        AccountBalance balance = GetAccoutBalanceUtils.getAccountBalanceAPI(token);
        double balanceAcc = balance.getAvailableBalance() + balance.getExposure() + balance.getRetainedTax();
        AccountStatementResult resultObj = AccountStatementUtils.getAccountStatementResult(token, today, today);

        log(String.format("Verify can get account statement"));
        Assert.assertTrue(resultObj.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        AccountStatement accountStatement = resultObj.getAccountStatementList().get(resultObj.getAccountStatementList().size() - 1);
        Assert.assertEquals(String.format("%.,2f", accountStatement.getBalance()), String.format("%.,2f", balanceAcc), "FAILED! The avialbale balance is incorrect");

        log("INFO: Executed completely");

    }


}
