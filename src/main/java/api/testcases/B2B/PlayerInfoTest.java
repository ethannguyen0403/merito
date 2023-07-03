package api.testcases.B2B;

import api.objects.B2B.resultObj.PlayerInfoObj;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import api.testcases.BaseCaseAPI;
import api.utils.B2B.appUitls.GetTokenUtils;
import api.utils.B2B.appUitls.PlayerInfoUtils;
import api.utils.testraildemo.TestRails;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class PlayerInfoTest extends BaseCaseAPI {

    @TestRails(id = "445")
    @Parameters({"agentKey", "authorization","userId","username"})
    @Test(groups = {"smoke"})
    public void BB003_PlayerInfoTest_445(String agentKey, String authorization,String userId,String username) throws UnsupportedEncodingException {
        log("@title:Validate can get player info");
        log("Precondition Step: encrypt data");
        String json =String.format("{\"userId\":\"%s\"}",userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey,authorization).getMessage();

        log("Step  Input valid user id and call api get info ");
        PlayerInfoObj result = PlayerInfoUtils.getPlayerInfo(agentKey,token,json);

        log("Verify 1 Verify can get token success");
        Assert.assertEquals(result.getResult(),1,"FAILED! Success result should be 1");
        Assert.assertEquals(result.getUserId(),userId,"FAILED! Incorrect User Id");
        Assert.assertEquals(result.getUserName(),username,"FAILED! Incorrect User name");
        Assert.assertEquals(result.getStatus(),"ACTIVE","FAILED! Incorrect status");
        Assert.assertTrue(Objects.nonNull(result.getBalance()),"FAILED! cannot get balance");
        Assert.assertTrue(Objects.nonNull(result.getExposure()),"FAILED! Cannot get exposure value");
        Assert.assertTrue(Objects.nonNull(result.getCreateDate()),"FAILED! Cannot get created date value");
        log("INFO: Executed completely");
    }


}
