package api.testcases.MeritoAPI;

import api.objects.meritoAPI.result.ResultObject;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.LoginMemberUtils;
import api.utils.testraildemo.TestRails;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class MA001_LoginMemberTest extends BaseCaseAPI {
    /**
     * @title: Validate that can login api success
     * @steps: 1/ Access api url for merito
     * 2/ Login with valid username and password
     * @expect: 1/ Verify can login success
     */
    @TestRails(id = "415")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA001_Keep_Alive_415(String username, String password) throws Exception {

        log("@title: Validate that can login api success");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String passDes = StringUtils.decrypt(password);
        ResultObject result = LoginMemberUtils.loginAPISuccess(username, passDes);

        log("Verify 1 Verify can login success");
        Assert.assertTrue(result.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        log("INFO: Executed completely");
    }
}
