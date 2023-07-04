package api.testcases.B2B;

import api.objects.B2B.resultObj.WagerResultObj;
import api.testcases.BaseCaseAPI;
import api.utils.B2B.appUitls.GetTokenUtils;
import api.utils.B2B.appUitls.GetWagerUtils;
import api.utils.testraildemo.TestRails;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

public class ReportMatchUnMatchWagersTest extends BaseCaseAPI {

    @TestRails(id = "456")
    @Parameters({"agentKey", "authorization", "userId"})
    @Test(groups = {"smoke"})
    public void BB009_ReportMatchUnMatchWagersTest_456(String agentKey, String authorization, String userId) throws UnsupportedEncodingException {
        log("@title:Call api to get all matched/unmatched bet");
        log("Precondition Step: encrypt data");
        String json = String.format("{\"userId\":\"%s\",\"matched\":\"-1\",\"dateFrom\":\"\",\"dateTo\":\"\",\"dateType\":\"PLACED_DATE\",\"orderType\":\"ASC\",\"page\":1,\"rowPerPage\":100}", userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey, authorization).getMessage();

        log(String.format("Step call BB009 api with parameter %s", json));
        WagerResultObj result = GetWagerUtils.getMatchedWagerResultByStatus(agentKey, token, json, "All");

        log("Verify 1:  Verify can filter and data display correctly");
        Assert.assertEquals(result.getResult(), 1, "FAILED! Status Failed when call the api");
        Assert.assertTrue(result.isFilterResultContainStatus("MATCHED,UNMATCHED,CANCELLED,LAPSED"), "FAILED! Status is incorrect when filtering");

        log("INFO: Executed completely");

    }

    @TestRails(id = "457")
    @Parameters({"agentKey", "authorization", "userId"})
    @Test(groups = {"smoke"})
    public void BB009_ReportMatchUnMatchWagersTest_457(String agentKey, String authorization, String userId) throws UnsupportedEncodingException {
        log("@title:Validate can get matched bet info");
        log("Precondition Step: encrypt data");
        String json = String.format("{\"userId\":\"%s\",\"matched\":\"1\",\"dateFrom\":\"\",\"dateTo\":\"\",\"dateType\":\"PLACED_DATE\",\"orderType\":\"ASC\",\"page\":1,\"rowPerPage\":100}", userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey, authorization).getMessage();

        log(String.format("Step 2: call BB009 api to get Settled wagers only with parameter %s", json));
        WagerResultObj result = GetWagerUtils.getMatchedWagerResultByStatus(agentKey, token, json, "MATCHED");

        log("Verify 1:  Verify can filter and data display correctly");
        Assert.assertEquals(result.getResult(), 1, "FAILED! Status Failed when call the api");
        Assert.assertTrue(result.isFilterResultEqualStatus("MATCHED"), "FAILED! Status is incorrect when filtering");
        log("INFO: Executed completely");

    }

    @TestRails(id = "458")
    @Parameters({"agentKey", "authorization", "userId"})
    @Test(groups = {"smoke"})
    public void BB009_ReportMatchUnMatchWagersTest_458(String agentKey, String authorization, String userId) throws UnsupportedEncodingException {
        log("@title:@title:Validate can get unmatched bet info");
        log("Precondition Step: encrypt data");
        String json = String.format("{\"userId\":\"%s\",\"matched\":\"0\",\"dateFrom\":\"\",\"dateTo\":\"\",\"dateType\":\"PLACED_DATE\",\"orderType\":\"ASC\",\"page\":1,\"rowPerPage\":100}", userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey, authorization).getMessage();

        log(String.format("Step 2: call BB009 api to get Voided wagers only with parameter %s", json));
        WagerResultObj result = GetWagerUtils.getMatchedWagerResultByStatus(agentKey, token, json, "UNMATCHED");

        log("Verify 1:  Verify can filter and data display correctly");
        Assert.assertEquals(result.getResult(), 1, "FAILED! Status Failed when call the api");
        Assert.assertTrue(result.isFilterResultEqualStatus("UNMATCHED"), "FAILED! Status is incorrect when filtering");
        log("INFO: Executed completely");

    }
}
