package api.testcases.B2B;

import api.objects.B2B.resultObj.WagerResultObj;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import api.testcases.BaseCaseAPI;
import api.utils.B2B.appUitls.*;
import api.utils.testraildemo.TestRails;

import java.io.UnsupportedEncodingException;

public class ReportSettledVoidWagersTest extends BaseCaseAPI {

    @TestRails(id = "453")
    @Parameters({"agentKey", "authorization","userId"})
    @Test(groups = {"smoke"})
    public void BB008_ReportSettledVoidWagersTest_453(String agentKey, String authorization,String userId) throws UnsupportedEncodingException {
        log("@title:Validate can get settled and voided bet info");
        log("Precondition Step: encrypt data");
        String json = String.format("{\"userId\":\"%s\",\"settled\":-1,\"dateFrom\":\"\",\"dateTo\":\"\",\"dateType\":\"SETTLED_DATE\",\"orderType\":\"ASC\",\"page\":1,\"rowPerPage\":100}",userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey,authorization).getMessage();

        log(String.format("Step call BB008 api with parameter %s",json));
        WagerResultObj result = GetWagerUtils.getReportResult(agentKey,token,json);

        log("Verify 1:  Verify can filter and data display correctly");
        Assert.assertEquals(result.getResult(),1,"FAILED! Status Failed when call the api");
        Assert.assertTrue(result.isFilterResultContainStatus("SETTLED,VOIDED"),"FAILED! Status is incorrect when filtering");

        log("INFO: Executed completely");

    }
    @TestRails(id = "454")
    @Parameters({"agentKey", "authorization","userId"})
    @Test(groups = {"smoke"})
    public void BB008_ReportSettledVoidWagersTest_454(String agentKey, String authorization,String userId) throws UnsupportedEncodingException {
        log("@title:Validate can get settled bet info");
        log("Precondition Step: encrypt data");
        String json = String.format("{\"userId\":\"%s\",\"settled\":1,\"dateFrom\":\"\",\"dateTo\":\"\",\"dateType\":\"SETTLED_DATE\",\"orderType\":\"ASC\",\"page\":1,\"rowPerPage\":100}",userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey,authorization).getMessage();

        log(String.format("Step 2: call BB008 api to get Settled wagers only with parameter %s",json));
        WagerResultObj result = GetWagerUtils.getReportResult(agentKey,token,json);

        log("Verify 1:  Verify can filter and data display correctly");
        Assert.assertEquals(result.getResult(),1,"FAILED! Status Failed when call the api");
        Assert.assertTrue(result.isFilterResultEqualStatus("SETTLED"),"FAILED! Status is incorrect when filtering");
        log("INFO: Executed completely");

    }
    @TestRails(id = "455")
    @Parameters({"agentKey", "authorization","userId"})
    @Test(groups = {"smoke"})
    public void BB008_ReportSettledVoidWagersTest_455(String agentKey, String authorization,String userId) throws UnsupportedEncodingException {
        log("@title:Validate can get settled and voided bet info");
        log("Precondition Step: encrypt data");
        String json = String.format("{\"userId\":\"%s\",\"settled\":0,\"dateFrom\":\"\",\"dateTo\":\"\",\"dateType\":\"SETTLED_DATE\",\"orderType\":\"ASC\",\"page\":1,\"rowPerPage\":100}",userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey,authorization).getMessage();

        log(String.format("Step 2: call BB008 api to get Voided wagers only with parameter %s",json));
        WagerResultObj result = GetWagerUtils.getReportResult(agentKey,token,json);

        log("Verify 1:  Verify can filter and data display correctly");
        Assert.assertEquals(result.getResult(),1,"FAILED! Status Failed when call the api");
        Assert.assertTrue(result.isFilterResultEqualStatus("VOIDED"),"FAILED! Status is incorrect when filtering");
        log("INFO: Executed completely");

    }
}
