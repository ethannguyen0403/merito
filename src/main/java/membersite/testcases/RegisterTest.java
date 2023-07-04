package membersite.testcases;

import baseTest.BaseCaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RegisterTest extends BaseCaseTest {

    @Test(groups = {"smoke1"}, invocationCount = 2)
    @Parameters({"password", "skinName"})
    public void FE_Change_Password_TC001(String password, String skinName) throws Exception {

    }


}
