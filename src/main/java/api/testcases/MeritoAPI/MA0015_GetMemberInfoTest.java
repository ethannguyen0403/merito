package api.testcases.MeritoAPI;

import api.objects.meritoAPI.MemberInfo;
import api.objects.meritoAPI.result.ResultObject;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.GetMemberInfoUtils;
import api.utils.MeritoAPI.LoginMemberUtils;
import api.utils.testraildemo.TestRails;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MA0015_GetMemberInfoTest extends BaseCaseAPI {
    /**
     * @title: Validate that can login api success
     * @steps: 1/ Access api url for merito
     * 2/ Login with valid username and password
     * @expect: 1/ Verify can login success
     */
    @TestRails(id = "417")
    @Parameters({"username", "password", "currency"})
    @Test(groups = {"smoke"})
    public void MA0015_Get_Member_Info_417(String username, String password, String currency) throws Exception {
        log("@title: Validate that can login api success");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String passDes = StringUtils.decrypt(password);
        ResultObject resultObject = LoginMemberUtils.loginAPISuccess(username, passDes);

        log("Verify 1 Verify can login success");
        MemberInfo memberInfo = GetMemberInfoUtils.getMembrInfo(resultObject.getMessage());
        Assert.assertTrue(memberInfo.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(memberInfo.getCurrency(), currency, "FAILED! currency is incorrect");
        Assert.assertEquals(memberInfo.getLstBetSetting().size(), 6, "FAILED! List sport setting size is incorrect");
        log("INFO: Executed completely");
    }
}
