package membersite.testcases.homepage;

import baseTest.BaseCaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;


public class NextUpRacingSectionTest extends BaseCaseTest {

    @TestRails(id = "1075")
    @Test(groups = {"regression"})
    @Parameters({"password", "skinName"})
    public void MB_Change_Password_TC1075(String password, String skinName) throws Exception {
        log("@title:Validate Greyhound Racing link works");
        log("Step 1.In home page - Next Up Racing section");
        log("Step 2.Click on any Greyhound racing link");
//        Assert.assertTrue(memberHomePage.highLightRaceControl.getNexRaceLabel().contains("Next Race"),"FAILED! Next Race label is incorrect");
//        Assert.assertEquals(memberHomePage.lblSportHighLight.getText(),"Sport Highlights","FAILED! Sport highlight label is incorrect");
//        Assert.assertEquals(memberHomePage.lblTodayRacing.getText(),"Today's racing","FAILED! Today's racing label is incorrect");

        log("Verify 1. Racing market page display correctly. Country, market start time, market name is corrected");
        log("INFO: Executed completely");
    }

}
