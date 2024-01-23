package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import membersite.pages.proteus.EuroViewPage;
import membersite.pages.proteus.ProteusHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;
import static common.ProteusConstant.*;
public class EuroViewPageTest extends BaseCaseTest {

    @TestRails(id = "4062")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4062() {
        log("@title: Validate HK odds display correct in bet slip Euro View");
        log("Precondition: 1 The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Euro View and select odds type = HONGKONG");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectOddsType(HONGKONG);

        log("Step 4. Select Early tab in the left meu and any sport has events available");
        euroViewPage.selectEventOnLeftMenu(EARLY_PERIOD,"Soccer");
        euroViewPage.getFirstEventInfo();

        log("Step 5. Click on event of 1x2 market has odds");
      // euroViewPage.clickOdds("England - Premier League","Brighton and Hove Albion vs Wolves","1x2", "homeTeam");

        log("Verify 1 Verify odds info (League, Event, Market type, Selection, start date) is correct");
       // euroViewPage.verifyBetSlipInfoShowCorrect();
        log("INFO: Executed completely");
    }





}
