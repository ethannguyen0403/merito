package backoffice.testcases.temp;

import org.testng.Assert;
import org.testng.annotations.Test;
import baseTest.BaseCaseMerito;

public class LoginActivityTest extends BaseCaseMerito{
    /**
     * @title: There is no http responded error returned
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Reports > Win Loss Detail
     * @expect:  1. There is no http responded error returned
     */
    @Test (groups = {"http_request"})
    public void BO_MM_LoginActivity_001(){
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Reports > Win Loss Details");
        backofficeHomePage.navigateLoginActivity();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

}
