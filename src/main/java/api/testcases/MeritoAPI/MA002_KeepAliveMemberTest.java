package api.testcases.MeritoAPI;

import api.testcases.BaseCaseAPI;

public class MA002_KeepAliveMemberTest extends BaseCaseAPI {
  /*
    *//**
     * @title: Validate that can keep alive
     * @steps:   1/ Access api url for merito
     * 2/ Login with valid username and password
     * @expect:  1/ Verify can login success
     *//*
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA001_Member_Login_001(String username, String password) throws Exception {
        log("@title:  Validate that can keep alive");
        log("Step '1/ Login ");
        String passDes = StringUtils.decrypt(password);
        List<String> result = LoginMemberUtils.loginAPISuccess("https://apistg.beatus88.com/",username,passDes);

        log("Verify 1Verify can login success");
        Assert.assertEquals(result.get(0),"true","FAILED! isSuccess should be true but cannnot get as expected");
        log("INFO: Executed completely");
    }*/
}
