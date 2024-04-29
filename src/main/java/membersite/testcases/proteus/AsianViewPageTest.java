package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import membersite.objects.AccountBalance;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.Order;
import membersite.pages.components.ps38preferences.PS38PreferencesPopup;
import membersite.pages.proteus.AsianViewPage;
import membersite.pages.proteus.ProteusHomePage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;

import static common.MemberConstants.GMT_MINUS_4_30;
import static common.MemberConstants.LBL_TENNIS_SPORT;
import static common.MemberConstants.PS38PreferencesPopup.*;
import static common.ProteusConstant.*;
import static membersite.utils.proteus.MarketUtils.getListLeagues;

public class AsianViewPageTest extends BaseCaseTest {

    @TestRails(id = "4151")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4151(String groupOdds) {
        log("@title: Validate Player group E display the correct Decimal odds in 1x2 market in Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(DECIMAL);
        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_MONEYLINE, true);

        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify Odds of 1x2 market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(DECIMAL, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }
    @TestRails(id = "4152")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4152(String groupOdds) {
        log("@title: Validate Player group E display the correct HongKong odds in 1x2 market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Hongkong");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);
        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,HONGKONG,TEXT_MONEYLINE, true);

        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify Odds of 1 x2 market is displayed with the same odds in Decimal\\n\" +\n" +
                "                \"For market 1x2 with odds HK/MY they will have the same odds as DEC");
        asianViewPage.verifyOddsShowCorrect(DECIMAL, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4153")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4153(String groupOdds) {
        log("@title: Validate Player group E display the correct Malay odds in 1x2 market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Malay");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);
        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,MALAY,TEXT_MONEYLINE, true);

        log("Step 5. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify Odds of 1 x2 market is displayed with the same odds in Decimal\\n\" +\n" +
                "                \"For market 1x2 with odds HK/MY they will have the same odds as DEC");
        asianViewPage.verifyOddsShowCorrect(DECIMAL, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4154")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4154(String groupOdds) {
        log("@title: Validate Player group E display the correct American odds in 1x2 market in Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_MONEYLINE, true);

        log("Step 5. Select American odds type");
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify American Odds of 1x2 market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(AMERICAN, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4155")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4155(String groupOdds) {
        log("@title: Validate Player group E display the correct Decimal odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_OVER_UNDER,true);

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify American Odds of OverUnder market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(DECIMAL, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4156")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4156(String groupOdds) {
        log("@title: Validate Player group E display the correct Hongkong odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_OVER_UNDER,true);
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify Hongkong Odds of OverUnder market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(HONGKONG, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4157")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4157(String groupOdds) {
        log("@title: Validate Player group E display the correct Malay odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_OVER_UNDER,true);
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);

        log("Step 6. From Malay odds off account group A, calculate and check odds on account group E is correct");
        log("Verify Malay Odds of OverUnder market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(MALAY, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4158")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4158(String groupOdds) {
        log("@title: Validate Player group E display the correct American odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_OVER_UNDER,true);
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        log("Step 6. From Malay odds off account group A, calculate and check odds on account group E is correct");
        log("Verify American Odds of OverUnder market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(AMERICAN, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4159")
    @Test(groups = {"ps38_groupc","Proteus.2024.V.1.0_groupc"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4159(String groupOdds) {
        log("@title: Validate Player group C display the correct Decimal odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group C");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_OVER_UNDER,true);

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group C is correct");
        log("Verify American Odds of OverUnder market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(DECIMAL, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4160")
    @Test(groups = {"ps38_groupc","Proteus.2024.V.1.0_groupc"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4160(String groupOdds) {
        log("@title: Validate Player group C display the correct Hongkong odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group C");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_OVER_UNDER,true);
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group C is correct");
        log("Verify Hongkong Odds of OverUnder market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(HONGKONG, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4161")
    @Test(groups = {"ps38_groupc","Proteus.2024.V.1.0_groupc"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4161(String groupOdds) {
        log("@title: Validate Player group C display the correct Malay odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group C");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_OVER_UNDER,true);
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);
        log("Step 6. From Malay odds off account group A, calculate and check odds on account group C is correct");
        log("Verify Malay Odds of OverUnder market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(MALAY, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4162")
    @Test(groups = {"ps38_groupc","Proteus.2024.V.1.0_groupc"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4162(String groupOdds) {
        log("@title: Validate Player group C display the correct American odds in Over Under market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group C");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_OVER_UNDER,true);
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        log("Step 6. From Malay odds off account group A, calculate and check odds on account group C is correct");
        log("Verify American Odds of OverUnder market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(AMERICAN, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4163")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4163(String groupOdds) {
        log("@title: Validate Player group E display the correct Decimal odds in Handicap market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_HDP,true);

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify Decimal Odds of Handicap market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(DECIMAL, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4164")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4164(String groupOdds) {
        log("@title: Validate Player group E display the correct Hongkong odds in Handicap market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_HDP,true);
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify Hongkong Odds of Handicap market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(HONGKONG, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4165")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4165(String groupOdds) {
        log("@title: Validate Player group E display the correct Malay odds in Handicap market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_HDP,true);
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify Malay Odds of Handicap market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(MALAY, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4166")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4166(String groupOdds) {
        log("@title: Validate Player group E display the correct American odds in Handicap market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site");
        log("Step 2. Active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 3. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 4. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_HDP,true);
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify American Odds of Handicap market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(AMERICAN, groupOdds, marketBase, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4167")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4167(String groupOdds) {
        log("@title: Validate Player group E display the correct Decimal odds in Match - Team Totals market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site and active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 2. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_MONEYLINE, true);

        log("Step 4. Pick a Match - Team Totals market");
        asianViewPage.openMoreMarkets(market);
        asianViewPage.selectMoreMarket(TEXT_MATCH_TOTAL);

        log("Step 5. From Decimal odds of account group A, calculate and check odds on account group E are corrected");
        log("Verify Odds of Match - Team Totals market is display correctly");
        asianViewPage.verifyMoreMarketOddsCorrect(market, DECIMAL,groupOdds,MARKET_TYPE_MAPPING.get("Match - Team Totals"),true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4168")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4168(String groupOdds) {
        log("@title: Validate Player group E display the correct Hongkong odds in Match - Team Totals market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site and active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 2. Select Asian View and select odds type = Decimal");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_MONEYLINE, true);
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);

        log("Step 4. Pick a Match - Team Totals market");
        asianViewPage.openMoreMarkets(market);
        asianViewPage.selectMoreMarket(TEXT_MATCH_TOTAL);

        log("Step 5. From Decimal odds of account group A, calculate and check odds on account group E are corrected");
        log("Verify Odds of Match - Team Totals market is display correctly");
        asianViewPage.verifyMoreMarketOddsCorrect(market, HONGKONG,groupOdds,MARKET_TYPE_MAPPING.get("Match - Team Totals"),true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4169")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4169(String groupOdds) {
        log("@title: Validate Player group E display the correct Malay odds in Match - Team Totals market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site and active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 2. Select Asian View and select odds type = Malay");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_MONEYLINE, true);
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);

        log("Step 4. Pick a Match - Team Totals market");
        asianViewPage.openMoreMarkets(market);
        asianViewPage.selectMoreMarket(TEXT_MATCH_TOTAL);

        log("Step 5. From Decimal odds of account group A, calculate and check odds on account group E are corrected");
        log("Verify Odds of Match - Team Totals market is display correctly");
        asianViewPage.verifyMoreMarketOddsCorrect(market, MALAY,groupOdds,MARKET_TYPE_MAPPING.get("Match - Team Totals"),true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4170")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4170(String groupOdds) {
        log("@title: Validate Player group E display the correct American odds in Match - Team Totals market Asian View");
        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
        log("Step 1. Login member site and active PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();

        log("Step 2. Select Asian View and select odds type = American");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_MONEYLINE, true);
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        log("Step 4. Pick a Match - Team Totals market");
        asianViewPage.openMoreMarkets(market);
        asianViewPage.selectMoreMarket(TEXT_MATCH_TOTAL);

        log("Step 5. From Decimal odds of account group A, calculate and check odds on account group E are corrected");
        log("Verify Odds of Match - Team Totals market is display correctly");
        asianViewPage.verifyMoreMarketOddsCorrect(market, AMERICAN,groupOdds, MARKET_TYPE_MAPPING.get("Match - Team Totals"),true);
        log("INFO: Executed completely");
    }
    @TestRails(id = "4171")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Member_TC4171(String currency) {
        log("@title: Validate toWin and toRisk correctly when placing on better negative MY odds");
        log("Precondition: Login member site-  the player active PS38 product");
        AccountBalance userBalance = BetUtils.getUserBalance();
        log("Step 1.Select Ps38 product");
        log("Step 2.Active Ps38 product active Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);

        log("Step 4. Select MY odds odds type, pick a negative odds and place bet. @-0.71 with stake = 40 => toRisk = 28.4 and toWin= 40");
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);

        Market market = asianViewPage.getEventInfo(SOCCER, MALAY, TEXT_HDP, true, true);
        Order order = asianViewPage.addOddToBetSlipAndPlaceBet(market, true, "minbet", false, true, true);

        log("Verify toRisk and toWin of this bet in Pending bet and Balance and exposure");
        asianViewPage.verifyPendingBetInfo(order, currency);
        asianViewPage.verifyUserBalanceAfterPlacePS38(asianViewPage.calculateExpecteBalance(userBalance, order));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4172")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Member_TC4172(String currency) {
        log("@title: Validate toWin and toRisk correctly when placing on better negative AM odds");
        log("Precondition: Login member site-  the player active PS38 product");
        AccountBalance userBalance = BetUtils.getUserBalance();
        log("Step 1.Select Ps38 product");
        log("Step 2.Active Ps38 product active Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);

        log("Step 4. Select AM odds odds type, pick a negative odds and place bet. @-103 with stake = 20 => toRisk = 20.6 and toWin=20");
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        Market market = asianViewPage.getEventInfo(SOCCER, AMERICAN, TEXT_HDP, true, true);
        Order order = asianViewPage.addOddToBetSlipAndPlaceBet(market, true, "minbet", false, true, true);

        log("Verify toRisk and toWin of this bet in Pending bet and Balance and exposure");
        asianViewPage.verifyPendingBetInfo(order, currency);
        asianViewPage.verifyUserBalanceAfterPlacePS38(asianViewPage.calculateExpecteBalance(userBalance, order));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4079")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    @Parameters({"groupOdds"})
    public void PS38_Member_TC4079(String groupOdds) {
        log("@title: Validate the filter result displays correctly after filtering Odds");
        log("Precondition: Login member site-  the player active PS38 product");
        log("Step 1.Select Ps38 product");
        log("Step 2.Select Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);

        log("Step 4. Select Decimal odds type, get odds and calculate to account group E");
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_HDP,true);
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        log("Verify Hongkong Odds of Handicap market is display correctly based on user group");
        asianViewPage.verifyOddsShowCorrect(HONGKONG, groupOdds, marketBase, true);
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
        asianView.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);

        Market marketBase = asianView.getEventInfo(SOCCER,DECIMAL,TEXT_MONEYLINE,true);
        asianView.searchLeagueOrTeamName(marketBase.getLeagueName());

        log("Verify The league list or team names displays below search textbox, user can view league or team name corresponding after clicking on any");
        asianView.verifySearchByLeagueDropdownCorrect(marketBase.getLeagueName());
        asianView.selectFirstSearchOption();

        Market marketBase2 = asianView.getEventInfoUI(marketBase, true);
        Assert.assertEquals(marketBase.getLeagueName(), marketBase2.getLeagueName(), String.format("FAILED! Selected league is not correct expected %s actual %s", marketBase.getLeagueName(), marketBase2.getLeagueName()));
        log("INFO: Executed completely");
    }

    @TestRails(id = "4075")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4075() {
        log("@title: Validate the league list or team names displays correctly when input the un-match values in search textbox");
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
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);

        log("Step 4. Click All Times dropdown and Select any time range ");
        asianViewPage.filterByDate(date);
        log("Verify The filter result displays correctly and correspond to the filter condition");
        asianViewPage.verifyFilterByDateShowCorrect(date);
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
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);

        log("Step 4. Click on any League name filter and Observe");
        ArrayList<String> lstLeagues = getListLeagues(EARLY_PERIOD);
        String leagueExpected = lstLeagues.get(0);
        asianViewPage.filterByLeague(leagueExpected, false);

        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_MONEYLINE,true);

        log("Verify The filter result displays correctly and correspond to the filter condition");
        Assert.assertTrue(marketBase.getLeagueName().equalsIgnoreCase(leagueExpected), String.format("FAILED! Filter by League is not correct expected %s actual %s", leagueExpected, marketBase.getLeagueName()));
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
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);

        log("Step 4. Click on By Time and observe");
        asianViewPage.selectSortBy(" By Time ");
        log("Verify The filter result displays correctly and correspond to the filter condition");
        asianViewPage.verifySortByTimeShowCorrect();
        log("INFO: Executed completely");
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
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        Market market = asianViewPage.getEventInfo(SOCCER, AMERICAN, TEXT_HDP, true, true);
        asianViewPage.clickOdds(market, true, true);

        log("Validate Max per match should be calculated correctly for Am negative odds following the formula\n" +
                "\n" +
                "AM negative odds: Max per match = (setting max per match/odds) * 100");
        asianViewPage.verifyBetSlipInfo(market, true, AMERICAN);
        asianViewPage.verifyMaxPerMatchShowCorrect(market, settingMaxPerMatch, AMERICAN, true);
        log("INFO: Executed completely");
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
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        Market market = asianViewPage.getEventInfo(SOCCER, AMERICAN, TEXT_HDP, true, false);
        asianViewPage.clickOdds(market, true, false);

        log("Validate Max per match should be calculated correctly for Am positive odds following the formula\n" +
                "\n" +
                "AM positive odds: Max per match = setting max per match");
        asianViewPage.verifyBetSlipInfo(market, false, AMERICAN);
        asianViewPage.verifyMaxPerMatchShowCorrect(market, settingMaxPerMatch, AMERICAN, false);
        log("INFO: Executed completely");
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
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);

        Market market = asianViewPage.getEventInfo(SOCCER, MALAY, TEXT_HDP, true, true);
        asianViewPage.clickOdds(market, true, true);

        log("Validate Max per match should be calculated correctly for Am negative odds following the formula\n" +
                "\n" +
                "AM negative odds: Max per match = (setting max per match/odds) * 100");
        asianViewPage.verifyBetSlipInfo(market,true, MALAY);
        asianViewPage.verifyMaxPerMatchShowCorrect(market, settingMaxPerMatch, MALAY, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4144")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4144() {
        log("@title: Validate Max per match field should match with agent setting");
        log("Precondition: Login Member site with account that already config Max per match setting for any sport of PS38 product");
        log("Step 1.Select Ps38 product and select DEC odds");
        log("Step 2. Click any odd of sport that already config Max per match");
        double settingMaxPerMatch = 500.0;
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(market, true, false);

        log("Validate Match Max value should matched correctly with Agent Site setting");
        asianViewPage.verifyBetSlipInfo(market, false, DECIMAL);
        asianViewPage.verifyMaxPerMatchShowCorrect(market, settingMaxPerMatch, DECIMAL, false);
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

    @TestRails(id = "23659")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23659(){
        log("@title: Validate cannot add more than 10 bet into bet slip");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectSportOnLeftMenu(SOCCER);
        log("Step 4: Click on odds of 11 different markets to bet slip");
        asianViewPage.addMultiBetsToBetSlip(11);
        log("Verify 1: Verify the popup message \"Maximum is 10 selections\" display");
        Assert.assertEquals(asianViewPage.confirmModulePopup.getContent(), MAX_SELECTIONS_MSG, "FAILED! Pop up maximum selection is not displayed correctly");
        log("Step 5: Click on OK button");
        asianViewPage.confirmModulePopup.confirm();
        log("Verify 2: Verify the popup message is no longer displayed");
        Assert.assertFalse(asianViewPage.confirmModulePopup.isDisplayed(),"FAILED! Pop up still displayed");
    }

    @TestRails(id = "23660")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23660(){
        log("@title: Validate Remove All popup display when clicking Remove button in bet slip");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3:  Click on odds of 2 different markets to bet slip");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);

        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        Market marketOU = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_OVER_UNDER, true, false);
        asianViewPage.clickOdds(marketHDP, true, false);
        asianViewPage.clickOdds(marketOU, true, false);
        log("Step 4: Click on Remove All button");
        proteusHomePage.removeBetsByRemoveAll(false);
        log("Verify 1: Verify Remove all popup display with the message \"Do you want to empty you Bet Slip?\"");
        Assert.assertEquals(asianViewPage.confirmModulePopup.getContent(), REMOVE_ALL_MSG, "FAILED! Pop up Remove all is not displayed correctly");
    }

    @TestRails(id = "23661")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23661(){
        log("@title: Validate nothing happen when cancelling to empty bet slip");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);

        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(market, true, false);
        log("Step 4: Click on Remove All button");
        asianViewPage.removeBetsByRemoveAll(false);
        log("Step 5: Cancel remove all popup");
        asianViewPage.confirmModulePopup.cancelPopup();
        log("Verify 1:  Verify the popup is no longer display, Bet slip still display the odds in bet slip");
        Assert.assertFalse(asianViewPage.confirmModulePopup.isDisplayed(),"FAILED! Pop up still displayed");
        proteusHomePage.verifyBetSlipInfo(market, false, DECIMAL);
    }


    @TestRails(id = "23662")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23662(){
        log("@title: Validate bet slip is empty when confirm to remove all");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);

        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        Market marketOU = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_OVER_UNDER, true, false);
        asianViewPage.clickOdds(marketHDP, true, false);
        asianViewPage.clickOdds(marketOU, true, false);
        log("Step 4: Click on Remove All button");
        log("Step 5: Confirm remove all popup");
        asianViewPage.removeBetsByRemoveAll(true);
        log("Verify 1:   Verify the popup is no longer display, Bet Slip are empty:\n" +
                "\n" +
                "Bet Slip title does not display number\n" +
                "Display the message \"No bets selected yet.\"\n" +
                "Display the message \"Click on the respective odds to place a new bet.\"");
        asianViewPage.verifyBetSlipIsEmpty();
    }

    @TestRails(id = "23663")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23663(){
        log("@title: Validate can remove bet out bet slip when click on the according remove icon");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Add an odds to bet slip");
        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(market, true, false);
        log("Step 5: Click on x icon of the added bet");
        asianViewPage.removeAddedBets(market);
        log("Verify 1:   Verify the popup is no longer display, Bet Slip are empty:\n" +
                "\n" +
                "Bet Slip title does not display number\n" +
                "Display the message \"No bets selected yet.\"\n" +
                "Display the message \"Click on the respective odds to place a new bet.\"");
        asianViewPage.verifyBetSlipIsEmpty();
    }

    @TestRails(id = "23664")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23664(){
        log("@title: Validate bet slip and place bet number are update accordingly when removing a bet out bet slip");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Click on odds of 2 different markets to bet slip");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        Market marketOU = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_OVER_UNDER, true, false);
        asianViewPage.clickOdds(marketHDP, true, false);
        asianViewPage.clickOdds(marketOU, true, false);
        log("Step 4: Click on x icon of the 2nd bet");
        asianViewPage.removeAddedBets(marketOU);
        log("Verify 1: Verify Bet slip title: Bet Slip 1");
        Assert.assertEquals(asianViewPage.lblBetSlipTab.getText() + asianViewPage.lblBetSlipTabNumber.getText(), BET_SLIP_TAB +1, "FAILED! Bet slip tab not correct");
        log("Verify 2: Verify Place bet button: PLACE 1 BET");
        Assert.assertEquals(asianViewPage.btnPlaceBet.getText(), String.format(PLACE_BET_BUTTON_TEXT, 1), "FAILED! Button place bet text is not correct");
        log("Verify 3: Bet Slip just display info of the 1st order");
        asianViewPage.verifyBetSlipInfo(marketHDP,false, DECIMAL);
    }


    @TestRails(id = "23665")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23665(){
        log("@title: Validate Insufficient balance error message display when placed on Decimal odds with the stake over available balance ");
        log("Step 1: Select PS38 product");
        double userBalanceOver = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", "")) + 100 ;
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: SSelect Asian view and Decimal odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(DECIMAL);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Add an odds to bet slip and input stake");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.addOddToBetSlipAndPlaceBetWithoutSetOrder(marketHDP, true , String.valueOf(userBalanceOver),true, true, false);
        log("Verify 1: Verify message \"Insufficient balance for placing bet! Order ID: [order id]\"");
        asianViewPage.verifyErrorMsgOverBalance();
    }

    @TestRails(id = "23666")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23666(){
        log("@title: Validate Insufficient balance error message display when placed on negative Malay odds with the risk over user balance");
        log("Step 1: Select PS38 product");
        double userBalance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: SSelect Asian view and Malay odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(MALAY);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Add an negative odds to bet slip and input stake");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, MALAY, TEXT_HDP, true, true);
        // make sure riskValue is always bigger than userBalance
        double riskValue = userBalance + marketHDP.getToRisk(userBalance, MALAY, true);

        asianViewPage.addOddToBetSlipAndPlaceBetWithoutSetOrder(marketHDP, true , String.valueOf(riskValue),false, false, true);
        log("Verify 1: Verify message \"Insufficient balance for placing bet! Order ID: [order id]\"");
        asianViewPage.verifyErrorMsgOverBalance();
    }

    @TestRails(id = "23667")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23667(){
        log("@title: Validate Insufficient balance error message display when placed on positive Malay odds with the risk over user balance");
        log("Step 1: Select PS38 product");
        double userBalance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: SSelect Asian view and Malay odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(MALAY);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Add an positive odds to bet slip and input stake");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, MALAY, TEXT_HDP, true, false);
        // make sure riskValue is always bigger than userBalance
        double riskValue = userBalance + marketHDP.getToRisk(userBalance, MALAY, false);;

        asianViewPage.addOddToBetSlipAndPlaceBetWithoutSetOrder(marketHDP, true , String.valueOf(riskValue),false, false, false);
        log("Verify 1: Verify message \"Insufficient balance for placing bet! Order ID: [order id]\"");
        asianViewPage.verifyErrorMsgOverBalance();
    }

    @TestRails(id = "23668")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23668(){
        log("@title: Validate Insufficient balance error message display when placed on negative American odds with the risk over user balance");
        log("Step 1: Select PS38 product");
        double userBalance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view and American odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(AMERICAN);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Add a negative odds to bet slip and input stake that calculate risk greater than user balance");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, AMERICAN, TEXT_HDP, true, true);
        //calculate for risk bigger than userBalance
        double riskValue = userBalance + marketHDP.getToRisk(userBalance, AMERICAN, true);;

        asianViewPage.addOddToBetSlipAndPlaceBetWithoutSetOrder(marketHDP, true , String.valueOf(riskValue),false, false, true);
        log("Verify 1: Verify message \"Insufficient balance for placing bet! Order ID: [order id]\"");
        asianViewPage.verifyErrorMsgOverBalance();
    }

    @TestRails(id = "23669")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23669(){
        log("@title: Validate Insufficient balance error message display when placed on positive American odds with the risk over user balance");
        log("Step 1: Select PS38 product");
        double userBalance = Double.valueOf(memberHomePage.getUserBalance().getBalance().replace(",", ""));
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view and American odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(AMERICAN);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Add a negative odds to bet slip and input stake that calculate risk greater than user balance");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, AMERICAN, TEXT_HDP, true, false);
        //calculate for risk bigger than userBalance
        double riskValue = userBalance + (userBalance * marketHDP.getToRisk(userBalance, AMERICAN, false));

        asianViewPage.addOddToBetSlipAndPlaceBetWithoutSetOrder(marketHDP, true , String.valueOf(riskValue),false, false, false);
        log("Verify 1: Verify message \"Insufficient balance for placing bet! Order ID: [order id]\"");
        asianViewPage.verifyErrorMsgOverBalance();
    }

    @TestRails(id = "23670")
    @Parameters({"currency"})
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23670(String currency){
        log("@title: Validate confirm bets message is correct when place on Decimal odds");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view and Decimal odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(DECIMAL);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Add an odds to bet slip and input stake");
        log("Step 5: Click place bet");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        Order order = asianViewPage.addOddToBetSlipAndPlaceBet(marketHDP, true , "minbet",true, false, false);

        String win = String.format("%,.2f", marketHDP.getToRisk(order.getStake(), DECIMAL, false)).replaceAll("[.0]+$", "");
        String risk = String.format("%,.2f",  marketHDP.getToWin(order.getStake(), DECIMAL, false)).replaceAll("[.0]+$", "");

        log("Verify 1: Verify a confirm message display correctly with to risk and to win value\n" +
                "\"Are you sure you want to risk ... INR to win ... INR?\"");
        Assert.assertEquals(asianViewPage.confirmModulePopup.getContent().trim(), String.format(CONFIRM_PLACE_BET_MSG, risk, currency, win, currency), "FAILED! Confirm place bet message is not correct");
    }

    @TestRails(id = "23671")
    @Parameters({"currency"})
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23671(String currency){
        log("@title: Validate confirm bets message is correct when place on HongKong odds");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view and Hong Kong odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Click on odds of Handicap market and input valid stake");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, HONGKONG, TEXT_HDP, true, false);
        Order order = asianViewPage.addOddToBetSlipAndPlaceBet(marketHDP, true , "minbet",true, false, false);

        String risk = String.format("%,.2f", marketHDP.getToRisk(order.getStake(), HONGKONG, false)).replaceAll("[.0]+$", ""); // remove trailing zero eg:15.00 , 16.50 for verifying
        String win = String.format("%,.2f",  marketHDP.getToWin(order.getStake(), HONGKONG, false)).replaceAll("[.0]+$", "");

        log("Verify 1: Verify a confirm message display correctly with to risk and to win value\n" +
                "\"Are you sure you want to risk ... INR to win ... INR?\"");
        Assert.assertEquals(asianViewPage.confirmModulePopup.getContent().trim(), String.format(CONFIRM_PLACE_BET_MSG, risk, currency, win, currency), "FAILED! Confirm place bet message is not correct");
    }

    @TestRails(id = "23672")
    @Parameters({"currency"})
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23672(String currency){
        log("@title: Validate confirm bets message is correct when place on Malay negative odds");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view and Hong Kong odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(MALAY);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Click on positive odds of Handicap market and input valid stake");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, MALAY, TEXT_HDP, true, true);
        Order order = asianViewPage.addOddToBetSlipAndPlaceBet(marketHDP, true , "minbet",false, false, true);

        String risk = String.format("%,.2f", marketHDP.getToRisk(order.getStake(), MALAY, true)).replaceAll("[.0]+$", "");
        String win = String.format("%,.2f",  marketHDP.getToWin(order.getStake(), MALAY, true)).replaceAll("[.0]+$", "");

        log("Verify 1: Verify a confirm message display correctly with to risk and to win value\n" +
                "\"Are you sure you want to risk ... INR to win ... INR?\"");
        Assert.assertEquals(asianViewPage.confirmModulePopup.getContent().trim(), String.format(CONFIRM_PLACE_BET_MSG, risk, currency, win, currency), "FAILED! Confirm place bet message is not correct");
    }

    @TestRails(id = "23673")
    @Parameters({"currency"})
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23673(String currency){
        log("@title: Validate confirm bets message is correct when place on Malay positive odds");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view and Hong Kong odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(MALAY);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Click on positive odds of Handicap market and input valid stake");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, MALAY, TEXT_HDP, true, false);
        Order order = asianViewPage.addOddToBetSlipAndPlaceBet(marketHDP, true , "minbet",true, false, false);

        String risk = String.format("%,.2f", marketHDP.getToRisk(order.getStake(), MALAY, false)).replaceAll("[.0]+$", "");
        String win = String.format("%,.2f",  marketHDP.getToWin(order.getStake(), MALAY, false)).replaceAll("[.0]+$", "");

        log("Verify 1: Verify a confirm message display correctly with to risk and to win value\n" +
                "\"Are you sure you want to risk ... INR to win ... INR?\"");
        Assert.assertEquals(asianViewPage.confirmModulePopup.getContent().trim(), String.format(CONFIRM_PLACE_BET_MSG, risk, currency, win, currency).replaceAll("[.0]+$", ""), "FAILED! Confirm place bet message is not correct");
    }

    @TestRails(id = "23674")
    @Parameters({"currency"})
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23674(String currency){
        log("@title: Validate confirm bets message is correct when place on American negative odds");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view and American odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(AMERICAN);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Click on negative odds of Handicap market and input valid stake");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, AMERICAN, TEXT_HDP, true, true);
        Order order = asianViewPage.addOddToBetSlipAndPlaceBet(marketHDP, true , "minbet",true, false, true);

        String risk = String.format("%,.2f",  marketHDP.getToRisk(order.getStake(), AMERICAN, true)).replaceAll("[.0]+$", "");
        String win = String.format("%,.2f", marketHDP.getToWin(order.getStake(), AMERICAN, true)).replaceAll("[.0]+$", "");

        log("Verify 1: Verify a confirm message display correctly with to risk and to win value\n" +
                "\"Are you sure you want to risk ... INR to win ... INR?\"");
        Assert.assertEquals(asianViewPage.confirmModulePopup.getContent().trim(), String.format(CONFIRM_PLACE_BET_MSG, risk, currency, win, currency), "FAILED! Confirm place bet message is not correct");
    }

    @TestRails(id = "23675")
    @Parameters({"currency"})
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23675(String currency){
        log("@title: Validate confirm bets message is correct when place on American positive odds");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view and American odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(AMERICAN);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Click on positive odds of Handicap market and input valid stake");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, AMERICAN, TEXT_HDP, false, false);
        Order order = asianViewPage.addOddToBetSlipAndPlaceBet(marketHDP, false , "minbet",true, false, false);

        String win = String.format("%,.2f",  marketHDP.getToRisk(order.getStake(), AMERICAN, false)).replaceAll("[.0]+$", "");
        String risk = String.format("%,.2f", marketHDP.getToWin(order.getStake(), AMERICAN, false)).replaceAll("[.0]+$", "");

        log("Verify 1: Verify a confirm message display correctly with to risk and to win value\n" +
                "\"Are you sure you want to risk ... INR to win ... INR?\"");
        Assert.assertEquals(asianViewPage.confirmModulePopup.getContent().trim(), String.format(CONFIRM_PLACE_BET_MSG, risk, currency, win, currency), "FAILED! Confirm place bet message is not correct");
    }

    @TestRails(id = "23676")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23676(){
        log("@title: Validate confirm bets message is disappeared when clicking cancel");
        log("Step 1: Select PS38 product");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Step 2: Select Asian view and Decimal odds");
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(DECIMAL);
        log("Step 3: Select Sport Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 4: Add an odds to bet slip and input valid stale");
        log("Step 5: Click place bet");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.addOddToBetSlipAndPlaceBet(marketHDP, true , "minbet",true, false, false);
        log("Step 6: A confirm bet popup display and click on cancel button");
        asianViewPage.confirmModulePopup.cancelPopup();
        log("Verify 1:Verify the confirm bets popup is disappear and bet slip still display the order");
        Assert.assertFalse(asianViewPage.confirmModulePopup.isDisplayed(), "FAILED! Confirm popup still displayed after cancel");
        }

    @TestRails(id = "23680")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23680(){
        log("@title: Validate Odds Type display correctly when the setting is Hong Kong - Asian View ");
        log("Step 1: Expand My Account and click the menu PS38 Reference");
        PS38PreferencesPopup prefPopup = memberHomePage.openPS38PreferencesPopup();
        log("Step 2: Select Odds type Hong Kong and Default View is Asian then click save");
        prefPopup.selectPreferences("Hong Kong", "", ASIAN, "", "");
        log("Step 3: Click on PS38 product and check the selected odds type Odds type");
        ProteusHomePage page =  memberHomePage.activePS38Product();
        log("Verify 1: Verify View label displays Euro View and odds type is HK Odds");
        Assert.assertEquals(page.lblView.getText().trim(), EURO_VIEW, "FAILED! View is not correct");
        Assert.assertEquals(new AsianViewPage(_brandname).ddmOddsType.getText().trim(), ODDS_TYPE_LABEL_MAPPING.get(HONGKONG), "FAILED! Label Odds is not correct");
    }

    @TestRails(id = "23681")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23681(){
        log("@title: Validate Odds Type display correctly when the setting is Malay - Asian View");
        log("Step 1: Expand My Account and click the menu PS38 Reference");
        PS38PreferencesPopup prefPopup = memberHomePage.openPS38PreferencesPopup();
        log("Step 2: Select Odds type Malay and Default View is Asian then click save");
        prefPopup.selectPreferences(MALAY, "", ASIAN, "", "");
        log("Step 3: Click on PS38 product and check the selected odds type Odds type");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Verify 1: Verify View label displays Euro View and odds type is Malay Odds");
        Assert.assertEquals(proteusHomePage.lblView.getText().trim(), EURO_VIEW, "FAILED! View is not correct");
        Assert.assertEquals(new AsianViewPage(_brandname).ddmOddsType.getText().trim(), ODDS_TYPE_LABEL_MAPPING.get(MALAY), "FAILED! Label Odds is not correct");
    }

    @TestRails(id = "23682")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23682(){
        log("@title: Validate Odds Type display correctly when the setting is American - Asian View");
        log("Step 1: Expand My Account and click the menu PS38 Reference");
        PS38PreferencesPopup prefPopup = memberHomePage.openPS38PreferencesPopup();
        log("Step 2: Select Odds type Hong Kong and Default View is Asian then click save");
        prefPopup.selectPreferences(AMERICAN, "", ASIAN, "", "");
        log("Step 3: Click on PS38 product and check the selected odds type American type");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        log("Verify 1: Verify View label displays Euro View and odds type is HK Odds");
        Assert.assertEquals(proteusHomePage.lblView.getText().trim(), EURO_VIEW, "FAILED! View is not correct");
        Assert.assertEquals(new AsianViewPage(_brandname).ddmOddsType.getText().trim(), ODDS_TYPE_LABEL_MAPPING.get(AMERICAN), "FAILED! Label Odds is not correct");
    }

    @TestRails(id = "23692")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23692(){
        log("@title: Validate default page is display correctly as setting is Today - Matches");
        log("Step 1: Expand My Account and click the menu PS38 Reference");
        PS38PreferencesPopup prefPopup = memberHomePage.openPS38PreferencesPopup();
        log("Step 2: Select Default Page is \"Today - Matches\"then click save");
        prefPopup.selectPreferences("", DDPAGE_TODAY_MATCHES, "", "", "");
        log("Step 3: Click on PS38 product and Asian View and click on the first sport Today tab in the left menu (Soccer)");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectSportOnLeftMenu(SOCCER);
        log("Verify 1: Verify the sport title is : SOCCER - TODAY MATCHES");
        asianViewPage.verifySportTitleCorrect(SOCCER, TODAY_PERIOD, DDPAGE_TODAY_MATCHES);
        log("Step 4: Click on Tennis sport and Today tab in the left menu");
        asianViewPage.selectSportOnLeftMenu(TENNIS);
        log("Verify 2: Verify the sport title is : SOCCER - TODAY MATCHES");
        asianViewPage.verifySportTitleCorrect(TENNIS, TODAY_PERIOD, DDPAGE_TODAY_MATCHES);
        log("INFO: Executed completely");
    }

    @TestRails(id = "23693")
    @Test(groups = {"ps38","Proteus.2024.V.2.0"})
    public void PS38_Member_TC23693(){
        log("@title: Validate default page is display correctly as setting is Today - Money Line/1x2");
        log("Step 1: Expand My Account and click the menu PS38 Reference");
        PS38PreferencesPopup prefPopup = memberHomePage.openPS38PreferencesPopup();
        log("Step 2:Select Default Page is \"Today -  Money Line/1x2\"then click save");
        prefPopup.selectPreferences("", DDPAGE_MONEY_LINE_MATCHES, "", "", "");
        log("Step 3: Click on PS38 product and Asian View and click on the first sport Today tab in the left menu (Soccer)");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        try{
            asianViewPage.selectSportOnLeftMenu(SOCCER);
            log("Verify 1: Verify the sport title is : SOCCER - TODAY MATCHES");
            asianViewPage.verifySportTitleCorrect(SOCCER, TODAY_PERIOD, DDPAGE_MONEY_LINE_MATCHES);
            log("Step 4: Click on Tennis sport and Today tab in the left menu");
            asianViewPage.selectSportOnLeftMenu(TENNIS);
            log("Verify 2: Verify the sport title is : SOCCER - TODAY MATCHES");
            asianViewPage.verifySportTitleCorrect(TENNIS, TODAY_PERIOD, DDPAGE_MONEY_LINE_MATCHES);
            log("INFO: Executed completely");
        }finally {
        log("@Post-condition: Set PS38 Preference to default with options: \"Today - Matches\"");
            memberHomePage.openPS38PreferencesPopup();
            prefPopup.selectPreferences("", DDPAGE_TODAY_MATCHES, "", "", "");
    }
    }
}
