package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import membersite.pages.proteus.ProteusHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.MemberConstants.LBL_SOCCER_SPORT;
import static common.MemberConstants.LBL_TENNIS_SPORT;
import static common.MemberConstants.PS38.LBL_ASIAN_VIEW;
import static common.MemberConstants.PS38.LBL_EURO_VIEW;
import static membersite.utils.proteus.MarketUtils.*;

public class ProteusHomePageTest extends BaseCaseTest {
    @TestRails(id = "4062")
    @Test(groups = {"ps38",})
    public void PS38_Member_TC4062() {
        log("@title: Validate player can switch to Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 3.Select Euro View");
        proteusHomePage.lblView.click();

        log("Verify View name change to Asia View");
        Assert.assertEquals(proteusHomePage.lblView.getText(),"Asian View", "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4063")
    @Test(groups = {"ps38",})
    public void PS38_Member_TC4063() {
        log("@title: Validate player can select Decimal odds type in Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 3.Select Euro View");
        proteusHomePage.lblView.click();

        log("Step 4: Select Euro View and select odds type = Decimal");

        log("Verify 1: Verify odds type change to DECIMAL");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4123")
    @Test(groups = {"Proteus.2024.V.1.0"})
    public void PS38_Member_TC4123() {
        log("@title: Validate can navigate Soccer in header menu EU view");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        proteusHomePage.switchView(LBL_EURO_VIEW);

        log("Step 3. Select Early the left menu Click on Soccer in Header menu");
        proteusHomePage.btnEarlyEuro.click();
        proteusHomePage.selectSportHeaderMenu(LBL_SOCCER_SPORT);

        log("Verify Soccer is active and Soccer Match title displays");
        Assert.assertEquals(proteusHomePage.lblSportHeader.getText(),LBL_SOCCER_SPORT, "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4124")
    @Test(groups = {"Proteus.2024.V.1.0"})
    public void PS38_Member_TC4124() {
        log("@title: Validate can navigate Tennis in header menu EU view");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        proteusHomePage.switchView(LBL_EURO_VIEW);

        log("Step 3. Select Early the left menu Click on Tennis in Header menu");
        proteusHomePage.btnEarlyEuro.click();
        proteusHomePage.selectSportHeaderMenu(LBL_TENNIS_SPORT);

        log("Verify Soccer is active and Tennis Match title displays");
        Assert.assertEquals(proteusHomePage.lblSportHeader.getText(),LBL_TENNIS_SPORT, "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4125")
    @Test(groups = {"Proteus.2024.V.1.0"})
    public void PS38_Member_TC4125() {
        log("@title: Validate can navigate Soccer Early in left menu EU view");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        proteusHomePage.switchView(LBL_EURO_VIEW);

        log("Step 3. Click on Early > Soccer in Left menu");
        proteusHomePage.btnEarlyEuro.click();
        proteusHomePage.selectSportLeftMenu(LBL_SOCCER_SPORT);

        log("Verify Soccer is active and Soccer Match title displays");
        Assert.assertEquals(proteusHomePage.lblSportHeader.getText(),LBL_SOCCER_SPORT, "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4071")
    @Test(groups = {"Proteus.2024.V.1.0"})
    public void PS38_Member_TC4071() {
        log("@title: Validate the ability to access the PS38 product on the member site");
        log("Precondition: Login member site");
        log("Step 1.Select Ps38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Validate PS38 product displays on top menu in member site , user can access into PS38 product page");
        Assert.assertEquals(proteusHomePage.lblView.getText(), LBL_ASIAN_VIEW, "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4072")
    @Test(groups = {"Proteus.2024.V.1.0_product_inactive"})
    public void PS38_Member_TC4072() {
        log("@title: Validate PS38 product doesn't display on member site");
        log("Precondition: Login member site");
        log("Step 1. Observe");
        log("Validate PS38 product doesn't displays on top menu in member site");
        try {
            memberHomePage.activePS38Product();
            Assert.assertTrue(false, "FAILED! PS38 product still display in member site while inactivated");
        } catch (NullPointerException npe) {
            Assert.assertTrue(true);
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "4126")
    @Test(groups = {"Proteus.2024.V.1.01"})
    public void PS38_Member_TC4126() {
        log("@title: Validate can add Handicap Soccer market odds to bet slip in EU view list event");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        proteusHomePage.switchView(LBL_EURO_VIEW);

        log("Step 3. Click on Early > Soccer in Left menu");
        proteusHomePage.btnEarlyEuro.click();
        proteusHomePage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        log("Verify Soccer is active and Soccer Match title displays");
        proteusHomePage.selectMarketTypeTab("Handicap");
        List<String> lstEventInfo = proteusHomePage.getFirstEventInfo();
        getMarketInfo(Integer.parseInt(lstEventInfo.get(2)), "SPREAD", 0);
//        getMarketInfo(1583785765, "SPREAD", -0.5);
//        ProteusMarket proteusMarket = proteusHomePage.getFirstEventInfo("Handicap");
        log("INFO: Executed completely");
    }
}
