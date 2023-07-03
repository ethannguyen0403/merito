package api.testcases.MeritoAPI;

import com.paltech.utils.StringUtils;
import api.objects.meritoAPI.result.CompetitionResult;
import api.objects.meritoAPI.Event;
import api.objects.meritoAPI.result.EventListResult;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.GetCompetitionUtils;
import api.utils.MeritoAPI.GetEventsUtils;
import api.utils.MeritoAPI.LoginMemberUtils;
import api.utils.testraildemo.TestRails;

import java.util.Objects;

public class MA005_GetListEventsTest extends BaseCaseAPI {
    /**
     * @title: Validate that can get Event Type
     * @steps:
     * 1 Access api url for merito
     * 2 Login with valid username and password
     * 3 Access get event api and intput sport id
     * @expect: Verify can get api
     */
    @TestRails(id = "422")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA005_GetListEvents_001(String username, String password) throws Exception {
        log("@title: Validate that can get Event Type");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token= LoginMemberUtils.loginAPISuccess(username,passDes).getMessage();
        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token,sportID,"false");

        log("Step 3 Access get event api and intput sport id");
        int competitionId = competitionResult.getCompetitionList().get(0).getId();
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token,sportID,"", Integer.toString(competitionId));

        log("Verify Verify can get api success");
        Assert.assertTrue(resultObj.getIsSuccess(),"FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertTrue(Objects.nonNull(resultObj.getEventList()),"FAILED! Market count is null");
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate search with exactly event
     * @steps:
     * 1 Access api url for merito
     * 2 Login with valid username and password
     * 3 Access get event api and intput sport id, event id, competition id
     * @expect: Verify can get api
     */
    @TestRails(id = "416")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA005_GetListEvents_002(String username, String password) throws Exception {
        log("@title: Validate that can get Event Type");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token= LoginMemberUtils.loginAPISuccess(username,passDes).getMessage();
        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token,sportID,"false");

        log("Step 2.1 Search all event of a competition");
        int competitionId = competitionResult.getCompetitionList().get(0).getId();
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token,sportID,"", Integer.toString(competitionId));
        Event event= resultObj.getEventList().get(0);

        log("Step 3 Access get event api and intput sport id, event id, competition id");
        EventListResult resultObj1 = GetEventsUtils.getEventsAPI(token,sportID,Integer.toString(event.getId()), Integer.toString(competitionId));

        log("Verify Verify can get api success");
        Assert.assertTrue(resultObj1.getIsSuccess(),"FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(resultObj1.getEventList().size(),1,"FAILED! The list event has more then 1 when search with event id");
        Assert.assertEquals(resultObj1.getEventList().get(0).getId(),event.getId(),"FAILED! The event info is not displayed as corrected");
        Assert.assertEquals(resultObj1.getEventList().get(0).getName(),event.getName(),"FAILED! The event info is not displayed as corrected");
        Assert.assertEquals(resultObj1.getEventList().get(0).getCountryCode(),event.getCountryCode(),"FAILED! The event info is not displayed as corrected");
        Assert.assertEquals(resultObj1.getEventList().get(0).getMarketCount(),event.getMarketCount(),"FAILED! The event info is not displayed as corrected");
        Assert.assertEquals(resultObj1.getEventList().get(0).getTimeZone(),event.getTimeZone(),"FAILED! The event info is not displayed as corrected");
        log("INFO: Executed completely");
    }
}
