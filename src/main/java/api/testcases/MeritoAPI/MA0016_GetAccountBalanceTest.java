package api.testcases.MeritoAPI;

import com.paltech.utils.StringUtils;
import api.objects.meritoAPI.AccountBalance;
import api.objects.meritoAPI.result.ResultObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.GetAccoutBalanceUtils;
import api.utils.MeritoAPI.LoginMemberUtils;
import api.utils.testraildemo.TestRails;

import java.util.Objects;

public class MA0016_GetAccountBalanceTest extends BaseCaseAPI {
    /**
     * @title: Validate that get Balance Info
     * @steps:   1/ call api get balance info
     * @expect:  Verify can get api success
     */
    @TestRails(id = "418")
    @Parameters({"username", "password","currency"})
    @Test(groups = {"smoke"})
    public void MA0016_GetAccountBalance_418(String username, String password,String currency) throws Exception {
        log("@title: Validate that get Balance Info");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String passDes = StringUtils.decrypt(password);
        ResultObject resultObject = LoginMemberUtils.loginAPISuccess(username,passDes);

        log("Step 2  call api get balance info");
        AccountBalance accountBalanceObj = GetAccoutBalanceUtils.getAccountBalanceAPI(resultObject.getMessage());
        log("Verify Verify can get api success");

        Assert.assertTrue(accountBalanceObj.getIsSuccess(),"FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(accountBalanceObj.getCurrency(),currency,"FAILED! currency is incorrect");
        Assert.assertTrue(Objects.nonNull(accountBalanceObj.getAvailableBalance()),"FAILED! List sport setting size is incorrect");
        Assert.assertTrue(Objects.nonNull(accountBalanceObj.getRetainedTax()),"FAILED! List sport setting size is incorrect");
        Assert.assertTrue(Objects.nonNull(accountBalanceObj.getExposure()),"FAILED! List sport setting size is incorrect");
        log("INFO: Executed completely");
    }
}
