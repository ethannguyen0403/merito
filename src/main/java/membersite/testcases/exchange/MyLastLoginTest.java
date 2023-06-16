package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import common.MemberConstants;
import membersite.pages.MyLastLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class MyLastLoginTest extends BaseCaseTest {

    @TestRails(id="1057")
    @Test (groups = {"regression"})
    public void MB_Change_Password_TC1075() {
        log("@title:Validate can navigate to My Last Logins page");
        log("Step 1 Click My Account > My Last Logins");
        MyLastLoginPage myLastLoginPage = new MyLastLoginPage(_brandname);

        log("Verify 1. My Last Logins display in new tab with");
        Assert.assertEquals(MemberConstants.MyLastLogin.TITLE_PAGE,myLastLoginPage.getTitle(),"Failed! My last login page title is incorrect displayed");

        log("Verify 2. My Last Login label\n" +
                "-Table header: Login Date & Time, Login Status, IP Address, Device Info, Country");
        Assert.assertEquals(MemberConstants.MyLastLogin.TABLE_HEADES,myLastLoginPage.tblMyLastLogin.getColumnNamesOfTable(),"Failed! Table header is incorrect");

        log("INFO: Executed completely");
    }

}
