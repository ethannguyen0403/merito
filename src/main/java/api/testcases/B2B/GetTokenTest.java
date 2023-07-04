package api.testcases.B2B;

import api.objects.B2B.resultObj.ResultB2BObj;
import api.testcases.BaseCaseAPI;
import api.utils.B2B.appUitls.GetTokenUtils;
import api.utils.testraildemo.TestRails;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Objects;


public class GetTokenTest extends BaseCaseAPI {

    @TestRails(id = "439")
    @Parameters({"agentKey", "secretKey", "authorization"})
    @Test(groups = {"smoke"})
    public void BB001_GetToken_439(String agentKey, String secretKey, String authorization) {
        log("@title: Can get authorization ");
        log("Step 1 Access b2b api url");
        log("Step 2/Input agent code and secret code then get authorization");
        ResultB2BObj result = GetTokenUtils.getAuthorization(agentKey, secretKey);

        log("Verify 1 Verify can get authorization with success status");
        Assert.assertEquals(result.getResult(), 1, "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(result.getMessage(), authorization, "FAILED! Authorization valued is incorrect");
        log("INFO: Executed completely");
    }

    @TestRails(id = "440")
    @Parameters({"agentKey", "authorization"})
    @Test(groups = {"smoke"})
    public void BB001_GetToken_440(String agentKey, String authorization) {
        log("@title: Validate Can get token  ");
        log("Step 1 Access b2b api url");
        log("Step 2 Input agent code and secret code and authenticate code ");
        ResultB2BObj result = GetTokenUtils.getTokenValue(agentKey, authorization);

        log("Verify 1 Verify can get token success");
        Assert.assertEquals(result.getResult(), 1, "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertTrue(Objects.nonNull(result.getMessage()), "FAILED! Can get token ");
        log("INFO: Executed completely");
    }
}
