package api.testcases.B2B;

import api.objects.B2B.resultObj.ListPlayerInfoObj;
import api.testcases.BaseCaseAPI;
import api.utils.B2B.appUitls.GetTokenUtils;
import api.utils.B2B.appUitls.PlayerInfoUtils;
import api.utils.testraildemo.TestRails;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

public class PlayerInfoListTest extends BaseCaseAPI {

    @TestRails(id = "446")
    @Parameters({"agentKey", "authorization", "userId"})
    @Test(groups = {"smoke"})
    public void BB003_PlayerInfoListTest_446(String agentKey, String authorization, String userId) throws UnsupportedEncodingException {
        log("@title:Validate can get player info");
        log("Precondition Step: encrypt data");
        String json = "{\"userId\":\"\",\"matched\":-1,\"dateFrom\":\"\",\"dateTo\":\"\",\"dateType\":\"PLACED_DATE\",\"orderType\":\"ASC\",\"page\":1,\"rowPerPage\":100}";

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey, authorization).getMessage();

        log("Step  Input valid user id and call api get info ");
        ListPlayerInfoObj result = PlayerInfoUtils.getListPlayerInfo(agentKey, token, json);

        log("Verify 1 Verify can get token success");
        Assert.assertEquals(result.getResult(), 1, "FAILED! Success result should be 1");
        Assert.assertFalse(result.getAccountList().isEmpty(), "FAILED! The list player info is null");

        log("INFO: Executed completely");
    }
}
