package api.testcases.MeritoAPI;

import api.objects.meritoAPI.result.CompetitionResult;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.GetCompetitionUtils;
import api.utils.MeritoAPI.LoginMemberUtils;
import api.utils.testraildemo.TestRails;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Objects;

public class MA004_GetListEventCompetitionTest extends BaseCaseAPI {
    /**
     * @title: Validate that can get Competition
     * @steps: 1 Access api url for merito
     * 2 Login with valid username and password
     * 3 Access get event api and intput sport id
     * @expect: Verify can get api
     */
    @TestRails(id = "420")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA004_GetCompetition_420(String username, String password) throws Exception {
        log("@title: Validate that can get Event Type");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get event api and input sport id");
        CompetitionResult resultObj = GetCompetitionUtils.getCompetitionAPI(token, "1", "false");

        log("Verify Verify can get api success");
        Assert.assertTrue(resultObj.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertTrue(Objects.nonNull(resultObj.getCompetitionList()), "FAILED! The list competition is null");
        log("INFO: Executed completely");
    }
}
