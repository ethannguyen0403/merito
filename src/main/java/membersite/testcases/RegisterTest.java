package membersite.testcases;

import baseTest.BaseCaseMerito;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.MemberConstants;
import membersite.pages.all.tabexchange.components.popups.ChangePasswordPopup;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class RegisterTest extends BaseCaseTest {

    @Test (groups = {"smoke1"},invocationCount = 2)
    @Parameters({"password","skinName"})
    public void FE_Change_Password_TC001(String password,String skinName) throws Exception {

    }


}
