package api.testcases.B2B;

import api.objects.B2B.resultObj.PlayerInfoObj;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import api.testcases.BaseCaseAPI;
import api.utils.B2B.appUitls.GetTokenUtils;
import api.utils.B2B.appUitls.PlayerStatusUpdateUtils;
import api.utils.testraildemo.TestRails;

import java.io.UnsupportedEncodingException;

public class PlayerStatusUpdateTest extends BaseCaseAPI {

    @TestRails(id = "447")
    @Parameters({"agentKey", "authorization","userId","username"})
    @Test(groups = {"smoke"})
    public void BB006_PlayerStatusUpdateTest_447(String agentKey, String authorization,String userId,String username) throws UnsupportedEncodingException {
        log("@title:Validate can inactive player account");
        log("Precondition Step: encrypt data");
        String json =String.format("{\"userId\":\"%s\",\"status\":\"INACTIVE\"}",userId);
        String jsonActive =String.format("{\"userId\":\"%s\",\"status\":\"ACTIVE\"}",userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey,authorization).getMessage();

        log("Step2  call api to update status of b2b account to inactive");
        PlayerInfoObj result = PlayerStatusUpdateUtils.updateStatus(agentKey,token,json);

        log("Verify 1 Verify can call api success and status is inactive");
        Assert.assertEquals(result.getResult(),1,"FAILED! Success result should be 1");
        Assert.assertEquals(result.getUserId(),userId,"FAILED! Incorrect User Id");
        Assert.assertEquals(result.getStatus(),"INACTIVE","FAILED!Status is incorrect");

        log("Post-Condition: Active account again");
        PlayerStatusUpdateUtils.updateStatus(agentKey,token,jsonActive);
        log("INFO: Executed completely");

    }
    @TestRails(id = "449")
    @Parameters({"agentKey", "authorization","userId","username"})
    @Test(groups = {"smoke"})
    public void BB006_PlayerStatusUpdateTest_449(String agentKey, String authorization,String userId,String username) throws UnsupportedEncodingException {
        log("@title: Validate can suspend player account");
        log("Precondition Step: encrypt data");
        String json =String.format("{\"userId\":\"%s\",\"status\":\"SUSPENDED\"}",userId);
        String jsonActive =String.format("{\"userId\":\"%s\",\"status\":\"ACTIVE\"}",userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey,authorization).getMessage();

        log("Step2 call api to update status of b2b account to suspend");
        PlayerInfoObj result = PlayerStatusUpdateUtils.updateStatus(agentKey,token,json);

        log("Verify 1 Verify can call api success and status is suspended");
        Assert.assertEquals(result.getResult(),1,"FAILED! Success result should be 1");
        Assert.assertEquals(result.getUserId(),userId,"FAILED! Incorrect User Id");
        Assert.assertEquals(result.getStatus(),"SUSPENDED","FAILED!Status is incorrect");

        log("Post-Condition: Active account again");
        PlayerStatusUpdateUtils.updateStatus(agentKey,token,jsonActive);
        log("INFO: Executed completely");

    }
    @TestRails(id = "450")
    @Parameters({"agentKey", "authorization","userId","username"})
    @Test(groups = {"smoke"})
    public void BB006_PlayerStatusUpdateTest_450(String agentKey, String authorization,String userId,String username) throws UnsupportedEncodingException {
        log("@title: Validate can suspend player account");
        String json =String.format("{\"userId\":\"%s\",\"status\":\"INACTIVE\"}",userId);
        String jsonActive =String.format("{\"userId\":\"%s\",\"status\":\"ACTIVE\"}",userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey,authorization).getMessage();

        log("Precondition Step:Inactive status of an account");
        PlayerStatusUpdateUtils.updateStatus(agentKey,token,json);

        log("Step 2 call api to update status of b2b account to active");
        PlayerInfoObj result =  PlayerStatusUpdateUtils.updateStatus(agentKey,token,jsonActive);

        log("Verify 1 Verify can call api success and status is suspended");
        Assert.assertEquals(result.getResult(),1,"FAILED! Success result should be 1");
        Assert.assertEquals(result.getUserId(),userId,"FAILED! Incorrect User Id");
        Assert.assertEquals(result.getStatus(),"ACTIVE","FAILED!Status is incorrect");

        log("INFO: Executed completely");

    }


}
