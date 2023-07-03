package api.testcases.MeritoAPI;

import api.objects.meritoAPI.result.EventListTypeResult;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.GetEventListTypeUtils;
import api.utils.MeritoAPI.LoginMemberUtils;
import api.utils.testraildemo.TestRails;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Objects;

public class MA003_GetListEventTypesTest extends BaseCaseAPI {
    /**
     * @title: Validate that can get Event Type
     * @steps: 1 Access api url for merito
     * 2 Login with valid username and password
     * 3 Access get event api and intput sport id
     * @expect: Verify can get api
     */
    @TestRails(id = "419")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA003_GetListEventTypes_419(String username, String password) throws Exception {
        log("@title: Validate that can get Event Type");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get event api and intput sport id");
        EventListTypeResult resultObj = GetEventListTypeUtils.getEventTypeAPI(token, "1");

        log("Verify Verify can get api success");
        Assert.assertTrue(resultObj.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(resultObj.getSportType().getId(), 1, "FAILED! Soccer id is incorrect");
        Assert.assertEquals(resultObj.getSportType().getName(), "Soccer", "FAILED! Soccer is not display");
        Assert.assertTrue(Objects.nonNull(resultObj.getMarketCount()), "FAILED! Market count is null");
        log("INFO: Executed completely");
    }
}
