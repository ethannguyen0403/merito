package api.testcases.B2B;

import api.objects.B2B.resultObj.LoginObj;
import api.testcases.BaseCaseAPI;
import api.utils.B2B.appUitls.GetTokenUtils;
import api.utils.B2B.appUitls.LoginUtils;
import api.utils.testraildemo.TestRails;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class PlayerLoginTest extends BaseCaseAPI {
    @TestRails(id = "441")
    @Parameters({"agentKey", "authorization", "userId", "username"})
    @Test(groups = {"smoke"})
    public void BB002_PlayerLoginTest_441(String agentKey, String authorization, String userId, String username) throws UnsupportedEncodingException {
        log("@title:Validate can login with desktop");
        log("Precondition Step: encrypt data");
        String json = String.format("{\"userId\":\"%s\",\"mobile\":\"false\",\"lang\":\"en_US\"}", userId);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey, authorization).getMessage();

        log("Step 2  Input userId and valid para: mobile = Desktop, language = English\" ");
        LoginObj result = LoginUtils.getLoginData(agentKey, token, json);

        log("Verify 1 Validate can generate login url with desktop");
        Assert.assertTrue(result.getResult() == 1, "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(result.getUserId(), userId, "FAILED! Incorrect User Id");
        Assert.assertEquals(result.getUserName(), username, "FAILED! Incorrect username");
        Assert.assertTrue(Objects.nonNull(result.getLoginUrl()), "FAILED! Cannot login on desktop ");

        log("INFO: Executed completely");
    }

    @TestRails(id = "442")
    @Parameters({"agentKey", "authorization", "userId", "username"})
    @Test(groups = {"smoke"})
    public void BB002_PlayerLoginTest_442(String agentKey, String authorization, String userId, String username) throws UnsupportedEncodingException {
        log("@title: Validate can login with mobile");
        log("Precondition Step: encrypt data");
        String json = String.format("{\"userId\":\"%s\",\"mobile\":\"true\",\"lang\":\"en_US\"}", userId);

        log("Step 1 Access b2b api url and get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey, authorization).getMessage();

        log("Step 2 Input agent code and secret code and authenticate code ");
        LoginObj result = LoginUtils.getLoginData(agentKey, token, json);

        log("Verify 1 Validate can generate login by mobile");
        Assert.assertTrue(result.getResult() == 1, "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(result.getUserId(), userId, "FAILED! Incorrect User Id");
        Assert.assertEquals(result.getUserName(), username, "FAILED! Incorrect username");
        Assert.assertTrue(Objects.nonNull(result.getLoginUrl()), "FAILED! Can not login on mobile");

        log("INFO: Executed completely");
    }

    @TestRails(id = "444")
    @Parameters({"agentKey", "authorization", "userId"})
    @Test(groups = {"smoke"})
    public void BB003_PlayerLogoutTest_444(String agentKey, String authorization, String userId) throws UnsupportedEncodingException {
        log("@title: Validate can logout");
        log("Precondition Step: encrypt data");
        String json = String.format("{\"userId\":\"%s\",\"mobile\":\"true\",\"lang\":\"en_US\"}", userId);

        log("Step 1 Access b2b api url and get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey, authorization).getMessage();

        log("Step 2 Call logout api");
        LoginObj result = LoginUtils.getLoginData(agentKey, token, json);

        log("Verify 1 can call logout api success");
        Assert.assertTrue(result.getResult() == 1, "FAILED! Can not logout ");
        Assert.assertEquals(result.getUserId(), userId, "FAILED! Incorrect User Id");

        log("INFO: Executed completely");
    }
}
