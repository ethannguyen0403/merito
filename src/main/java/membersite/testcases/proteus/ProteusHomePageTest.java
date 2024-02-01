package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import membersite.objects.proteus.*;
import membersite.pages.ProfitAndLossPage;
import membersite.pages.proteus.AsianViewPage;
import membersite.pages.proteus.EuroViewPage;
import membersite.pages.proteus.ProteusHomePage;
import membersite.utils.betplacement.BetUtils;
import membersite.utils.proteus.MarketUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.MemberConstants.*;
import static common.ProteusConstant.*;
import static membersite.utils.proteus.MarketUtils.*;

public class ProteusHomePageTest extends BaseCaseTest {

    @TestRails(id = "4062")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4062() {
        log("@title: Validate player can switch to Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 3.Select Euro View");
        proteusHomePage.selectEuroView();

        log("Verify View name change to Asia View");
        Assert.assertEquals(proteusHomePage.lblView.getText(),"Asian View", "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4063")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4063() {
        log("@title: Validate player can select Decimal odds type in Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 3.Select Euro View");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 4: Select Euro View and select odds type = Decimal");
        euroViewPage.selectOddsType(DECIMAL);

        log("Verify 1: Verify odds type change to DECIMAL");
        Assert.assertEquals(euroViewPage.getOddsType(),DECIMAL.toUpperCase(),"Failed! Odds Type is incorrect after selecting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4064")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4064() {
        log("@title: Validate player can select HONGKONG odds type in Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 3.Select Euro View");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 4: Select Euro View and select odds type = HONGKONG");
        euroViewPage.selectOddsType(HONGKONG);

        log("Verify 1: Verify odds type change to HONGKONG");
        Assert.assertEquals(euroViewPage.getOddsType(),HONGKONG.toUpperCase(),"Failed! Odds Type is incorrect after selecting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4065")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4065() {
        log("@title: Validate player can select MALAY odds type in Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3.Select Euro View");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 4: Select Euro View and select odds type = MALAY");
        euroViewPage.selectOddsType(MALAY);

        log("Verify 1: Verify odds type change to MALAY");
        Assert.assertEquals(euroViewPage.getOddsType(),MALAY.toUpperCase(),"Failed! Odds Type is incorrect after selecting");

        log("INFO: Executed completely");
    }
    @TestRails(id = "4066")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4066() {
        log("@title: Validate player can select AMERICAN odds type in Euro View");
        log("Precondition: The player is active PS38 product");
        log("Step 1. Login member site");
        log("Step 2.Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3.Select Euro View");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 4: Select Euro View and select odds type = AMERICAN");
        euroViewPage.selectOddsType(AMERICAN);

        log("Verify 1: Verify odds type change to AMERICAN");
        Assert.assertEquals(euroViewPage.getOddsType(),AMERICAN.toUpperCase(),"Failed! Odds Type is incorrect after selecting");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4071")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4071() {
        log("@title: Validate the ability to access the PS38 product on the member site");
        log("Precondition: Login member site");
        log("Step 1.Select Ps38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Validate PS38 product displays on top menu in member site , user can access into PS38 product page");
        Assert.assertEquals(proteusHomePage.lblView.getText(), EURO_VIEW, "FAILED! Cannot access PS39 product");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4072")
    @Test(groups = {"ps38_inactive_product","Proteus.2024.V.1.0_product_inactive"})
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

    @TestRails(id = "4128")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4128() {
        log("@title: Validate odds of 1x2 market display in Decimal when selecting Malay odds in EU view list event");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 3. Select Early the left menu and click on Soccer");
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectItemOnLeftMenu(LBL_SOCCER_SPORT);
        euroViewPage.selectMarketTypeTab(TEXT_1X2);

        log("Step 4. Get Decimal odds and change odds type to Malay");
        ProteusGeneralEvent event = euroViewPage.getFirstEventInfo();
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_1X2), null);

        //workaround to get odds group of current user
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        proteusHomePage.selectEuroView();
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectItemOnLeftMenu(LBL_SOCCER_SPORT);
        euroViewPage.selectMarketTypeTab(TEXT_1X2);
        //end workaround

        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(DECIMAL));
        euroViewPage.selectOddsType(MALAY);

        log("Verify Check odds when changing to Malay is same as odds when selected Decimal odds type");
        List<Double> lstOddsActual = euroViewPage.getListOddsFirstEvent(event, TEXT_1X2);
        Assert.assertTrue(lstOddsActual.equals(lstOddsConvert), String.format("FAILED! Odds List between DEC and MY type is not the same expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }
    @TestRails(id = "4129")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4129() {
        log("@title: Validate odds of 1x2 market display in Decimal when selecting HK odds in EU view list event");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Step 3. Select Early the left menu and click on Soccer");
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectItemOnLeftMenu(LBL_SOCCER_SPORT);
        euroViewPage.selectMarketTypeTab(TEXT_1X2);

        log("Step 4. Get Decimal odds and change odds type to HK");
        ProteusGeneralEvent event = euroViewPage.getFirstEventInfo();
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_1X2), null);

        //workaround to get odds group of current user
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        proteusHomePage.selectEuroView();
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectItemOnLeftMenu(LBL_SOCCER_SPORT);
        euroViewPage.selectMarketTypeTab(TEXT_1X2);
        //end workaround

        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(DECIMAL));
        euroViewPage.selectOddsType(HONGKONG);

        log("Verify Check odds when changing to HK is same as odds when selected Decimal odds type");
        List<Double> lstOddsActual = euroViewPage.getListOddsFirstEvent(event, TEXT_1X2);
        Assert.assertTrue(lstOddsActual.equals(lstOddsConvert), String.format("FAILED! Odds List between DEC and MY type is not the same expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4151")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4151() {
        log("@title: Validate Player group E display the correct Decimal odds in 1x2 market in Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select Decimal odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_1X2);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_1X2), null);
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(DECIMAL));

        log("Verify Odds of 1x2 market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_1X2);
        asianViewPage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4152")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4152() {
        log("@title: Validate Player group E display the correct Hongkong odds in 1x2 market in Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select Hongkong odds type");
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_1X2);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_1X2), null);
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(DECIMAL));

        log("Verify Odds of 1 x2 market is displayed with the same odds in Decimal\n" +
                "For market 1x2 with odds HK/MY they will have the same odds as DEC");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_1X2);
        asianViewPage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4153")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4153() {
        log("@title: Validate Player group E display the correct Malay odds in 1x2 market in Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select Malay odds type");
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_1X2);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_1X2), null);
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(DECIMAL));

        log("Verify Odds of 1 x2 market is displayed with the same odds in Decimal\n" +
                "For market 1x2 with odds HK/MY they will have the same odds as DEC");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_1X2);
        asianViewPage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4154")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4154() {
        log("@title: Validate Player group E display the correct American odds in 1x2 market in Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_1X2);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_1X2), null);
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(AMERICAN));

        log("Verify Odds of 1x2 market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_1X2);
        asianViewPage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4155")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4155() {
        log("@title: Validate Player group E display the correct Decimal odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_OVER_UNDER);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(DECIMAL));

        log("Verify Odds of Over Under market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_OVER_UNDER);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4156")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4156() {
        log("@title: Validate Player group E display the correct Hong Kong odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_OVER_UNDER);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(HONGKONG));

        log("Step 4. Select Hongkong odds type");
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);

        log("Verify Odds of Over Under market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_OVER_UNDER);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4157")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4157() {
        log("@title: Validate Player group E display the correct Malay odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_OVER_UNDER);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(MALAY));

        log("Step 4. Select Hongkong odds type");
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);

        log("Verify Odds of Over Under market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_OVER_UNDER);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4158")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4158() {
        log("@title: Validate Player group E display the correct American odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_OVER_UNDER);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(AMERICAN));

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        log("Verify Odds of Over Under market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_OVER_UNDER);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4159")
    @Test(groups = {"ps38_groupC","Proteus.2024.V.1.0_group_C"})
    public void PS38_Member_TC4159() {
        log("@title: Validate Player group C display the correct Decimal odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_OVER_UNDER);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(DECIMAL));

        log("Verify Odds of Over Under market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_OVER_UNDER);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4160")
    @Test(groups = {"ps38_groupC","Proteus.2024.V.1.0_group_C"})
    public void PS38_Member_TC4160() {
        log("@title: Validate Player group E display the correct Hong Kong odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_OVER_UNDER);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(HONGKONG));

        log("Step 4. Select Hongkong odds type");
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);

        log("Verify Odds of Over Under market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_OVER_UNDER);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4161")
    @Test(groups = {"ps38_groupC","Proteus.2024.V.1.0_group_C"})
    public void PS38_Member_TC4161() {
        log("@title: Validate Player group E display the correct Malay odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_OVER_UNDER);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(MALAY));

        log("Step 4. Select Hongkong odds type");
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);

        log("Verify Odds of Over Under market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_OVER_UNDER);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4162")
    @Test(groups = {"ps38_groupC","Proteus.2024.V.1.0_group_C"})
    public void PS38_Member_TC4162() {
        log("@title: Validate Player group E display the correct American odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_OVER_UNDER);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(AMERICAN));

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        log("Verify Odds of Over Under market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_OVER_UNDER);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4163")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4163() {
        log("@title: Validate Player group E display the correct Decimal odds in Handicap market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_HDP);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_HDP), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(DECIMAL));

        log("Verify Odds of HDP market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_HDP);
        asianViewPage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4164")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4164() {
        log("@title: Validate Player group E display the correct Hong Kong odds in Handicap market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_HDP);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_HDP), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(HONGKONG));

        log("Step 4. Select Hongkong odds type");
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);

        log("Verify Odds of HDP market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_HDP);
        asianViewPage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4165")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4165() {
        log("@title: Validate Player group E display the correct Malay odds in Handicap market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_HDP);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_HDP), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(MALAY));

        log("Step 4. Select Hongkong odds type");
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);

        log("Verify Odds of HDP market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_HDP);
        asianViewPage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4166")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4166() {
        log("@title: Validate Player group E display the correct American odds in HDP market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_HDP);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_HDP), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(AMERICAN));

        log("Step 4. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        log("Verify Odds of HDP market is display correctly based on user group");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_HDP);
        asianViewPage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
//        Assert.assertTrue(lstOddsConvert.containsAll(lstOddsActual), String.format("FAILED! Odds List does not show correct expected %s actual %s", lstOddsConvert, lstOddsActual));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4167")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4167() {
        log("@title: Validate Player group E display the correct Decimal odds in Match - Team Totals market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select Decimal odds type and pick a Match - Team Totals market");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_HDP);
        proteusHomePage.openMoreMarkets(String.valueOf(event.getEventId()));
        proteusHomePage.selectMoreMarket(TEXT_MATCH_TOTAL);
        ProteusTeamTotalEvent proteusTeamTotalEvent = asianViewPage.getFirstMatchTeamTotalEventInfo();

        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusMarket marketHome = getMatchTeamTotalMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), proteusTeamTotalEvent.getHomeGoals(), true);
        ProteusMarket marketAway = getMatchTeamTotalMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), proteusTeamTotalEvent.getAwayGoals(), false);
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());

        //convert odds of Home/Away to group
        List<Double> lstBaseOddsHome = new ArrayList<>();
        lstBaseOddsHome.add(marketHome.getFirstOdds());
        lstBaseOddsHome.add(marketHome.getSecondOdds());
        List<Double> lstOddsConvertMarket1 = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOddsHome,ODDS_TYPE_MAPPING.get(DECIMAL));
        List<Double> lstBaseOddsAway = new ArrayList<>();
        lstBaseOddsAway.add(marketAway.getFirstOdds());
        lstBaseOddsAway.add(marketAway.getSecondOdds());
        List<Double> lstOddsConvertMarket2 = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOddsAway,ODDS_TYPE_MAPPING.get(DECIMAL));

        //get odds list Home/Away from market
        List<Double> lstActualOddsHome = new ArrayList<>();
        List<Double> lstActualOddsAway = new ArrayList<>();
        lstActualOddsHome.add(proteusTeamTotalEvent.getHomeOver());
        lstActualOddsHome.add(proteusTeamTotalEvent.getHomeUnder());
        lstActualOddsAway.add(proteusTeamTotalEvent.getAwayOver());
        lstActualOddsAway.add(proteusTeamTotalEvent.getAwayUnder());

        log("Verify Odds of HDP market is display correctly based on user group");
        proteusHomePage.compareOddsShowCorrect(lstOddsConvertMarket1, lstActualOddsHome, 0.01);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvertMarket2, lstActualOddsAway, 0.01);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4168")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4168() {
        log("@title: Validate Player group E display the correct Hong Kong odds in Match - Team Totals market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select HongKong odds type and pick a Match - Team Totals market");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_HDP);
        proteusHomePage.openMoreMarkets(String.valueOf(event.getEventId()));
        proteusHomePage.selectMoreMarket(TEXT_MATCH_TOTAL);
        ProteusTeamTotalEvent proteusTeamTotalEvent = asianViewPage.getFirstMatchTeamTotalEventInfo();

        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusMarket marketHome = getMatchTeamTotalMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), proteusTeamTotalEvent.getHomeGoals(), true);
        ProteusMarket marketAway = getMatchTeamTotalMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), proteusTeamTotalEvent.getAwayGoals(), false);
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());

        //convert odds of Home/Away to group
        List<Double> lstBaseOddsHome = new ArrayList<>();
        lstBaseOddsHome.add(marketHome.getFirstOdds());
        lstBaseOddsHome.add(marketHome.getSecondOdds());
        List<Double> lstOddsConvertMarket1 = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOddsHome,ODDS_TYPE_MAPPING.get(HONGKONG));
        List<Double> lstBaseOddsAway = new ArrayList<>();
        lstBaseOddsAway.add(marketAway.getFirstOdds());
        lstBaseOddsAway.add(marketAway.getSecondOdds());
        List<Double> lstOddsConvertMarket2 = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOddsAway,ODDS_TYPE_MAPPING.get(HONGKONG));

        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);
        proteusHomePage.selectMoreMarket(TEXT_MATCH_TOTAL);
        proteusTeamTotalEvent = asianViewPage.getFirstMatchTeamTotalEventInfo();
        //get odds list Home/Away from market
        List<Double> lstActualOddsHome = new ArrayList<>();
        List<Double> lstActualOddsAway = new ArrayList<>();
        lstActualOddsHome.add(proteusTeamTotalEvent.getHomeOver());
        lstActualOddsHome.add(proteusTeamTotalEvent.getHomeUnder());
        lstActualOddsAway.add(proteusTeamTotalEvent.getAwayOver());
        lstActualOddsAway.add(proteusTeamTotalEvent.getAwayUnder());

        log("Verify Odds of HDP market is display correctly based on user group");
        proteusHomePage.compareOddsShowCorrect(lstOddsConvertMarket1, lstActualOddsHome, 0.01);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvertMarket2, lstActualOddsAway, 0.01);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4169")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4169() {
        log("@title: Validate Player group E display the correct Malay odds in Match - Team Totals market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select Malay odds type and pick a Match - Team Totals market");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_HDP);
        proteusHomePage.openMoreMarkets(String.valueOf(event.getEventId()));
        proteusHomePage.selectMoreMarket(TEXT_MATCH_TOTAL);
        ProteusTeamTotalEvent proteusTeamTotalEvent = asianViewPage.getFirstMatchTeamTotalEventInfo();

        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusMarket marketHome = getMatchTeamTotalMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), proteusTeamTotalEvent.getHomeGoals(), true);
        ProteusMarket marketAway = getMatchTeamTotalMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), proteusTeamTotalEvent.getAwayGoals(), false);
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());

        //convert odds of Home/Away to group
        List<Double> lstBaseOddsHome = new ArrayList<>();
        lstBaseOddsHome.add(marketHome.getFirstOdds());
        lstBaseOddsHome.add(marketHome.getSecondOdds());
        List<Double> lstOddsConvertMarket1 = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOddsHome,ODDS_TYPE_MAPPING.get(MALAY));
        List<Double> lstBaseOddsAway = new ArrayList<>();
        lstBaseOddsAway.add(marketAway.getFirstOdds());
        lstBaseOddsAway.add(marketAway.getSecondOdds());
        List<Double> lstOddsConvertMarket2 = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOddsAway,ODDS_TYPE_MAPPING.get(MALAY));

        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);
        proteusHomePage.selectMoreMarket(TEXT_MATCH_TOTAL);
        proteusTeamTotalEvent = asianViewPage.getFirstMatchTeamTotalEventInfo();
        //get odds list Home/Away from market
        List<Double> lstActualOddsHome = new ArrayList<>();
        List<Double> lstActualOddsAway = new ArrayList<>();
        lstActualOddsHome.add(proteusTeamTotalEvent.getHomeOver());
        lstActualOddsHome.add(proteusTeamTotalEvent.getHomeUnder());
        lstActualOddsAway.add(proteusTeamTotalEvent.getAwayOver());
        lstActualOddsAway.add(proteusTeamTotalEvent.getAwayUnder());

        log("Verify Odds of HDP market is display correctly based on user group");
        proteusHomePage.compareOddsShowCorrect(lstOddsConvertMarket1, lstActualOddsHome, 0.01);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvertMarket2, lstActualOddsAway, 0.01);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4170")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4170() {
        log("@title: Validate Player group E display the correct American odds in Match - Team Totals market Asian View");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select American odds type and pick a Match - Team Totals market");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_HDP);
        proteusHomePage.openMoreMarkets(String.valueOf(event.getEventId()));
        proteusHomePage.selectMoreMarket(TEXT_MATCH_TOTAL);
        ProteusTeamTotalEvent proteusTeamTotalEvent = asianViewPage.getFirstMatchTeamTotalEventInfo();

        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        ProteusMarket marketHome = getMatchTeamTotalMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), proteusTeamTotalEvent.getHomeGoals(), true);
        ProteusMarket marketAway = getMatchTeamTotalMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_MATCH_TOTAL), proteusTeamTotalEvent.getAwayGoals(), false);
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());

        //convert odds of Home/Away to group
        List<Double> lstBaseOddsHome = new ArrayList<>();
        lstBaseOddsHome.add(marketHome.getFirstOdds());
        lstBaseOddsHome.add(marketHome.getSecondOdds());
        List<Double> lstOddsConvertMarket1 = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOddsHome,ODDS_TYPE_MAPPING.get(AMERICAN));
        List<Double> lstBaseOddsAway = new ArrayList<>();
        lstBaseOddsAway.add(marketAway.getFirstOdds());
        lstBaseOddsAway.add(marketAway.getSecondOdds());
        List<Double> lstOddsConvertMarket2 = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOddsAway,ODDS_TYPE_MAPPING.get(AMERICAN));

        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);
        proteusHomePage.selectMoreMarket(TEXT_MATCH_TOTAL);
        proteusTeamTotalEvent = asianViewPage.getFirstMatchTeamTotalEventInfo();
        //get odds list Home/Away from market
        List<Double> lstActualOddsHome = new ArrayList<>();
        List<Double> lstActualOddsAway = new ArrayList<>();
        lstActualOddsHome.add(proteusTeamTotalEvent.getHomeOver());
        lstActualOddsHome.add(proteusTeamTotalEvent.getHomeUnder());
        lstActualOddsAway.add(proteusTeamTotalEvent.getAwayOver());
        lstActualOddsAway.add(proteusTeamTotalEvent.getAwayUnder());

        log("Verify Odds of HDP market is display correctly based on user group");
        proteusHomePage.compareOddsShowCorrect(lstOddsConvertMarket1, lstActualOddsHome, 0.01);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvertMarket2, lstActualOddsAway, 0.01);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4174")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4174() {
        log("@title: Validate to commission is correctly in Profit and loss report when bet is full settled for Verify high commission");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1. Active Ps38 product active Asian View");
        log("Step 2. Select any Fottboal today event");
        log("Step 3. Select AM odds odds type, pick a negative odds and place bet. @-103 with stake = 20 => toRisk = 20.6 and toWin=20");
        log("Step 4. Wait the order is settled");
        //Step 1 > Step 4 already has prepared fixed data as precondition (STG/PROD Football 13/01/2024, Soccer 23/01/2024)
        String startDate = "2024-01-13";
        String endDate = "2024-01-14";
        double commissionConfigValue = 0.025;
        ProfitAndLossPage page = memberHomePage.header.openProfitAndLoss(_brandname);
        page.filter(startDate, endDate);
        page.openBetDetailsOfSportsBook("PS38 - Football", "NFL / Houston Texans vs Cleveland Browns");

        log("Verify Commission of the order = min (toWin, toRisk)=min(20.6,20)*2.5% = 20*2.5% = 0.5");
        page.verifyCommissionProteusSportsBook(commissionConfigValue);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4173")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4173() {
        log("@title: Validate to commission is correctly in Profit and loss report when bet is full settled for Soccer commission");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1. Active Ps38 product active Asian View");
        log("Step 2. Select any Soccer today event");
        log("Step 3. Select AM odds odds type, pick a negative odds and place bet. @-103 with stake = 20 => toRisk = 20.6 and toWin=20");
        log("Step 4. Wait the order is settled");
        //Step 1 > Step 4 already has prepared fixed data as precondition (STG/PROD Football 13/01/2024, Soccer 23/01/2024)
        String startDate = "2024-01-23";
        String endDate = "2024-01-24";
        double commissionConfigValue = 0.02;
        ProfitAndLossPage page = memberHomePage.header.openProfitAndLoss(_brandname);
        page.filter(startDate, endDate);
        page.openBetDetailsOfSportsBook("PS38 - Soccer", "England - EFL Cup / Chelsea vs Middlesbrough");

        log("Verify Commission of the order = min (toWin, toRisk)=min(20.6,20)*2.5% = 20*2.5% = 0.5");
        page.verifyCommissionProteusSportsBook(commissionConfigValue);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4171")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4171() {
        log("@title: Validate toWin and toRisk correctly when placing on better negative MY odds");
        log("Precondition: Login member site-  the player active PS38 product");
        String stake = "20";
        double exposureBeforePlaceBet = Double.valueOf(BetUtils.getUserBalance().getExposure());
        double balanceBeforePlaceBet = Double.valueOf(BetUtils.getUserBalance().getBalance());
        log("Step 1.Select Ps38 product");
        log("Step 2.Active Ps38 product active Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select MY odds odds type, pick a negative odds and place bet. @-0.71 with stake = 40 => toRisk = 28.4 and toWin= 40");
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);
        String eventId = asianViewPage.selectFirstNegativeOdds();
        ProteusBetslip betslipInfo = proteusHomePage.getBetSlipInfo(eventId);
        asianViewPage.placeBet(stake, true);

        log("Verify toRisk and toWin of this bet in Pending bet and Balance and exposure");
        proteusHomePage.switchTabBetSlip(PENDING_BETS_TAB);
        asianViewPage.verifyToRiskToWinCorrect(stake, betslipInfo.getOdds(), MALAY);
        double exposureAfterPlaceBet = Double.valueOf(BetUtils.getUserBalance().getExposure());
        double balanceAfterPlaceBet = Double.valueOf(BetUtils.getUserBalance().getBalance());
        List<Double> lstToRiskToWin = asianViewPage.calculateToRiskToWin(stake, betslipInfo.getOdds(), MALAY);
        double expectedBalance = balanceBeforePlaceBet - lstToRiskToWin.get(0);
        double expectedExposure = exposureBeforePlaceBet - lstToRiskToWin.get(0);
        Assert.assertEquals(expectedExposure, exposureAfterPlaceBet, 0.01, String.format("FAILED! Exposure kept is not correct expected %s actual %s", expectedExposure, exposureAfterPlaceBet));
        Assert.assertEquals(expectedBalance, balanceAfterPlaceBet, 0.01, String.format("FAILED! Balance is not correct expected %s actual %s", expectedBalance, balanceAfterPlaceBet));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4172")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4172() {
        log("@title: Validate toWin and toRisk correctly when placing on better negative AM odds");
        log("Precondition: Login member site-  the player active PS38 product");
        String stake = "20";
        double exposureBeforePlaceBet = Double.valueOf(BetUtils.getUserBalance().getExposure());
        double balanceBeforePlaceBet = Double.valueOf(BetUtils.getUserBalance().getBalance());
        log("Step 1.Select Ps38 product");
        log("Step 2.Active Ps38 product active Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select AM odds odds type, pick a negative odds and place bet. @-103 with stake = 20 => toRisk = 20.6 and toWin=20");
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);
        String eventId = asianViewPage.selectFirstNegativeOdds();
        ProteusBetslip betslipInfo = proteusHomePage.getBetSlipInfo(eventId);
        asianViewPage.placeBet(stake, true);

        log("Verify toRisk and toWin of this bet in Pending bet and Balance and exposure");
        proteusHomePage.switchTabBetSlip(PENDING_BETS_TAB);
        asianViewPage.verifyToRiskToWinCorrect(stake, betslipInfo.getOdds(), AMERICAN);
        double exposureAfterPlaceBet = Double.valueOf(BetUtils.getUserBalance().getExposure());
        double balanceAfterPlaceBet = Double.valueOf(BetUtils.getUserBalance().getBalance());
        List<Double> lstToRiskToWin = asianViewPage.calculateToRiskToWin(stake, betslipInfo.getOdds(), AMERICAN);
        double expectedBalance = balanceBeforePlaceBet - lstToRiskToWin.get(0);
        double expectedExposure = exposureBeforePlaceBet - lstToRiskToWin.get(0);
        Assert.assertEquals(expectedExposure, exposureAfterPlaceBet, 0.01, String.format("FAILED! Exposure kept is not correct expected %s actual %s", expectedExposure, exposureAfterPlaceBet));
        Assert.assertEquals(expectedBalance, balanceAfterPlaceBet, 0.01, String.format("FAILED! Balance is not correct expected %s actual %s", expectedBalance, balanceAfterPlaceBet));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4073")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4073() {
        log("@title: Validate top menu displays correctly items on PS38 product");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View and observe top menu");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();

        log("Verify The items on top menu displays correctly (refer to PS38 Sport)");
        List<String> lstHeaders = euroViewPage.getSportsHeaderMenuList();
        List<String> lstActiveSports = MarketUtils.getListActiveSports();
        lstActiveSports.add(0,"Favourites");
        Assert.assertEquals(lstHeaders, lstActiveSports, String.format("FAILED! List Header is not matched expected %s actual %s", lstHeaders, lstActiveSports));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4074")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4074() {
        log("@title: Validate the league list or team names displays correctly when input the match values in search textbox");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Input the match values in search textbox and Observe");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        AsianViewPage asianView = proteusHomePage.selectAsianView();
        ProteusGeneralEvent event = asianView.getFirstEventInfo(TEXT_1X2);
        asianView.searchLeagueOrTeamName(event.getLeagueName());

        log("Verify The league list or team names displays below search textbox, user can view league or team name corresponding after clicking on any");
        List<String> lstSearchResult = asianView.getListSearchResult();
        for (int i = 0; i < lstSearchResult.size(); i++) {
            Assert.assertTrue(lstSearchResult.get(i).equalsIgnoreCase(event.getLeagueName().trim()),String.format("FAILED! List search result %s does not contain all input value %s", lstSearchResult.get(i), event.getLeagueName()));
        }
        asianView.selectFirstSearchOption();
        ProteusGeneralEvent event2 = asianView.getFirstEventInfo(TEXT_1X2);
        Assert.assertEquals(event.getLeagueName(), event2.getLeagueName(), String.format("FAILED! Selected league is not correct expected %s actual %s", event.getLeagueName(), event2.getLeagueName()));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4075")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4075() {
        log("@title: Validate the league list or team names displays corectly when input the un-match values in search textbox");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Input the un-match values in search textbox and Observe");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        AsianViewPage asianView = proteusHomePage.selectAsianView();
        asianView.searchLeagueOrTeamName("no record search");

        log("Verify Displays the message \"No records found.\"  below search textbox");
        Assert.assertEquals(asianView.lblNoRecordFound.getText().trim(), NO_RECORDS_FOUND, String.format("FAILED! No record found displays incorrectly expected %s actual %s", asianView.lblNoRecordFound.getText().trim(), NO_RECORDS_FOUND));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4076")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4076() {
        log("@title: Validate the filter result displays correctly after filtering Time");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Active Ps38 product active Asian View");
        String date = DateUtils.getDate(1, "yyyy-MM-dd", GMT_MINUS_4_30);
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Click All Times dropdown and Select any time range ");
        asianViewPage.filterByDate(date);
        log("Verify The filter result displays correctly and correspond to the filter condition");
        asianViewPage.verifyFilterByDateShowCorrect(date);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4079")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4079() {
        log("@title: Validate the filter result displays correctly after filtering Odds");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Select Decimal odds type, get odds and calculate to account group E");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_OVER_UNDER);
        ProteusMarket market = getMarketInfo(event.getEventId(), MARKET_TYPE_MAPPING.get(TEXT_OVER_UNDER), Double.valueOf(event.getHDPPoint()));
        String oddsGroup = proteusHomePage.getCurrentUserOddsGroup(event.getEventId());
        List<Double> lstBaseOdds = new ArrayList<>();
        lstBaseOdds.add(market.getFirstOdds());
        lstBaseOdds.add(market.getSecondOdds());
        lstBaseOdds.add(market.getThirdOdds());
        List<Double> lstOddsConvert = proteusHomePage.getListOddsByGroup(oddsGroup, lstBaseOdds,ODDS_TYPE_MAPPING.get(HONGKONG));

        log("Step 5. Click on any Odds type (HK) and observe");
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);

        log("Verify The filter result displays correctly and correspond to the filter condition");
        List<Double> lstOddsActual = asianViewPage.getListOddsFirstEvent(event, TEXT_OVER_UNDER);
        proteusHomePage.compareOddsShowCorrect(lstOddsConvert, lstOddsActual, 0.01);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4080")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4080() {
        log("@title: Validate the filter result displays correctly after filtering Leagues");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Active Ps38 product active Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Click on any League name filter and Observe");
        ArrayList<String> lstLeagues = getListLeagues(EARLY_PERIOD);
        String leagueExpected = lstLeagues.get(0);
        asianViewPage.filterByLeague(leagueExpected, false);
        ProteusGeneralEvent event = asianViewPage.getFirstEventInfo(TEXT_1X2);

        log("Verify The filter result displays correctly and correspond to the filter condition");
        Assert.assertTrue(event.getLeagueName().equalsIgnoreCase(leagueExpected), String.format("FAILED! Filter by League is not correct expected %s actual %s", leagueExpected, event.getLeagueName()));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4078")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4078() {
        log("@title: Validate the filter result displays correctly after filtering By Time");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Active Ps38 product active Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);

        log("Step 4. Click on By Time and observe");
        asianViewPage.selectSortBy(" By Time ");
        log("Verify The filter result displays correctly and correspond to the filter condition");
        asianViewPage.verifySortByTimeShowCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "4037")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4037() {
        log("@title: Validate cannot place bet when place bet in member site smaller than min bet setting");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        String minStake = "1.00";
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        log("Step 3. Placing bet with Stake/Risk smaller than min bet > observe");
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectItemOnLeftMenu(LBL_SOCCER_SPORT);
        ProteusGeneralEvent event = euroViewPage.getFirstEventInfo();
        euroViewPage.placeBet(event, minStake, true, false);

        log("Validate An error message should display as:\n" +
                "Your stake cannot be lower than the minimum bet. It has been automatically adjusted to the minimum bet amount.");
        String errorMessage = proteusHomePage.lblPlaceBetError.getText();
        Assert.assertEquals(errorMessage, MIN_STAKE_ERROR_MSG, String.format("FAILED! Stake error message does not show correct expected %s actual %s", errorMessage, MIN_STAKE_ERROR_MSG));
    }

    @TestRails(id = "4038")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4038() {
        log("@title: Validate cannot place bet when place bet in member site greater than max bet setting");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Euro View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        EuroViewPage euroViewPage = proteusHomePage.selectEuroView();
        euroViewPage.selectPeriodTab(EARLY_PERIOD);
        euroViewPage.selectItemOnLeftMenu(LBL_SOCCER_SPORT);
        ProteusGeneralEvent event = euroViewPage.getFirstEventInfo();

        log("Step 3. Placing bet with Stake/Risk greater than max bet > observe");
        euroViewPage.placeBet(event, "", false, false);
        ProteusBetslip betSlipInfo = euroViewPage.getBetSlipInfo(String.valueOf(event.getEventId()));
        String maxBet = betSlipInfo.getMaxBet();
        double maxBetStake = Double.valueOf(maxBet.replace(",","")) + 1;
        euroViewPage.inputStake(String.valueOf(event.getEventId()), String.valueOf(maxBetStake));
        euroViewPage.btnPlaceBet.click();

        log("Validate An error message should display as:\n" +
                "Your stake cannot be greater than the maximum bet. It has been automatically adjusted to the maximum bet amount.");
        String errorMessage = proteusHomePage.lblPlaceBetError.getText();
        Assert.assertEquals(errorMessage, MAX_STAKE_ERROR_MSG, String.format("FAILED! Stake error message does not show correct expected %s actual %s", errorMessage, MAX_STAKE_ERROR_MSG));
    }

    @TestRails(id = "4146")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4146() {
        log("@title: Validate Max per match is calculated correctly for AM negative odds");
        log("Precondition: Login Member site with account that already config Max per match setting for any sport of PS38 product");
        log("Step 1.Select Ps38 product and select AM odds");
        log("Step 2.Click any AM negative odd of sport that already config Max per match and observe");
        double settingMaxPerMatch = 500.0;
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        String eventId = asianViewPage.selectFirstNegativeOdds();
        ProteusBetslip betslipInfo = proteusHomePage.getBetSlipInfo(eventId);
        double oddsValue = Double.parseDouble(betslipInfo.getOdds().replace("−",""));
        double matchMaxExpected = (settingMaxPerMatch / oddsValue) * 100;
        log("Validate Max per match should be calculated correctly for Am negative odds following the formula\n" +
                "\n" +
                "AM negative odds: Max per match = (setting max per match/odds) * 100");
        Assert.assertEquals(Double.valueOf(betslipInfo.getMaxMatch()), Double.valueOf(Math.floor(matchMaxExpected * 100 / 100)), 0.01, String.format("FAILED! Stake error message does not show correct expected %s actual %s", betslipInfo.getMaxMatch(), matchMaxExpected));
    }

    @TestRails(id = "20264")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC20264() {
        log("@title: Validate Max per match is calculated correctly for AM positive odds");
        log("Precondition: Login Member site with account that already config Max per match setting for any sport of PS38 product");
        log("Step 1.Select Ps38 product and select AM odds");
        log("Step 2.Click any AM positive odd of sport that already config Max per match and observe");
        double settingMaxPerMatch = 500.0;
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        String eventId = asianViewPage.selectFirstPositiveOdds();
        ProteusBetslip betslipInfo = proteusHomePage.getBetSlipInfo(eventId);
        log("Validate Max per match should be calculated correctly for Am positive odds following the formula\n" +
                "\n" +
                "AM positive odds: Max per match = setting max per match");
        Assert.assertEquals(betslipInfo.getMaxMatch(), String.format("%.2f", settingMaxPerMatch), String.format("FAILED! Stake error message does not show correct expected %s actual %s", betslipInfo.getMaxMatch(), settingMaxPerMatch));
    }

    @TestRails(id = "4147")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4147() {
        log("@title: Validate Max per match is calculated correctly for MY negative odds");
        log("Precondition: Login Member site with account that already config Max per match setting for any sport of PS38 product");
        log("Step 1.Select Ps38 product and select AM odds");
        log("Step 2.Click any AM negative odd of sport that already config Max per match and observe");
        double settingMaxPerMatch = 500.0;
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        String eventId = asianViewPage.selectFirstNegativeOdds();
        ProteusBetslip betslipInfo = proteusHomePage.getBetSlipInfo(eventId);
        double oddsValue = Double.parseDouble(betslipInfo.getOdds().replace("−",""));
        double matchMaxExpected = settingMaxPerMatch / oddsValue;
        log("Validate Max per match should be calculated correctly for Am negative odds following the formula\n" +
                "\n" +
                "AM negative odds: Max per match = (setting max per match/odds) * 100");
        Assert.assertEquals(Double.valueOf(betslipInfo.getMaxMatch()), Double.valueOf(Math.floor(matchMaxExpected * 100) / 100), 0.01, String.format("FAILED! Stake error message does not show correct expected %s actual %s", betslipInfo.getMaxMatch(), matchMaxExpected));
    }

    @TestRails(id = "4144")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4144() {
        log("@title: Validate Max per match field should match with agent setting");
        log("Precondition: Login Member site with account that already config Max per match setting for any sport of PS38 product");
        log("Step 1.Select Ps38 product and select AM odds");
        log("Step 2. Click any odd of sport that already config Max per match");
        double settingMaxPerMatch = 500.0;
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        asianViewPage.selectPeriodTab(EARLY_PERIOD);
        asianViewPage.selectSportLeftMenu(LBL_SOCCER_SPORT);
        String eventId = asianViewPage.selectFirstPositiveOdds();

        log("Step 3. Compare Max Per Match with Agent site");
        ProteusBetslip betSlipInfo = asianViewPage.getBetSlipInfo(eventId);

        log("Validate Match Max value should matched correctly with Agent Site setting");
        Assert.assertEquals(betSlipInfo.getMaxMatch(), String.format("%.2f", settingMaxPerMatch), String.format("FAILED! Stake error message does not show correct expected %s actual %s", betSlipInfo.getMaxMatch(), settingMaxPerMatch));
    }
}
