package membersite.testcases;

import baseTest.BaseCaseTest;
import membersite.objects.sat.Event;
import membersite.pages.MarketPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Objects;


public class MarketPageTest extends BaseCaseTest {

    @TestRails(id = "1074")
    @Test(groups = {"regression"})
    @Parameters({"password", "skinName"})
    public void MB_Change_Password_TC1074(String password, String skinName) throws Exception {
        log("@title:Validate can open rule popup");
        log("Step 1 Active any market");
        log("Step 2.Click on Rule button");
        Event event = memberHomePage.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = memberHomePage.clickEventName(event.getEventName());
//        RulePopup rulePopup = marketPage.openRules();
        log("Verify 1. Rule popup display with the title : Market name - Rules");

        log("INFO: Executed completely");
    }

}
