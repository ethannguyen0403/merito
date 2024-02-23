package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import membersite.objects.AccountBalance;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.Order;
import membersite.pages.proteus.AsianViewPage;
import membersite.pages.proteus.ProteusHomePage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;

import static common.MemberConstants.GMT_MINUS_4_30;
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
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_MONEYLINE, true);

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
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_MONEYLINE, true);

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
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_MONEYLINE, true);

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
        Order order = asianViewPage.addOddToBetSlipAndPlaceBet(market, true, "minbet", false, true);

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
        Order order = asianViewPage.addOddToBetSlipAndPlaceBet(market, true, "minbet", false, true);

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
        asianViewPage.clickOdds(market, true);

        log("Validate Max per match should be calculated correctly for Am negative odds following the formula\n" +
                "\n" +
                "AM negative odds: Max per match = (setting max per match/odds) * 100");
        asianViewPage.verifyBetSlipInfo(market, market.getOdds().get(0).getTeam(), AMERICAN);
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
        asianViewPage.clickOdds(market, true);

        log("Validate Max per match should be calculated correctly for Am positive odds following the formula\n" +
                "\n" +
                "AM positive odds: Max per match = setting max per match");
        asianViewPage.verifyBetSlipInfo(market, market.getOdds().get(0).getTeam(), AMERICAN);
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
        asianViewPage.clickOdds(market, true);

        log("Validate Max per match should be calculated correctly for Am negative odds following the formula\n" +
                "\n" +
                "AM negative odds: Max per match = (setting max per match/odds) * 100");
        asianViewPage.verifyBetSlipInfo(market, market.getOdds().get(0).getTeam(), MALAY);
        asianViewPage.verifyMaxPerMatchShowCorrect(market, settingMaxPerMatch, MALAY, true);
        log("INFO: Executed completely");
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
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);

        Market market = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, true);
        asianViewPage.clickOdds(market, true);

        log("Validate Match Max value should matched correctly with Agent Site setting");
        asianViewPage.verifyBetSlipInfo(market, market.getOdds().get(0).getTeam(), DECIMAL);
        asianViewPage.verifyMaxPerMatchShowCorrect(market, settingMaxPerMatch, DECIMAL, true);
//        asianViewPage.verifyMaxPerMatchShowCorrect(betSlipInfo, settingMaxPerMatch, DECIMAL, false);
        log("INFO: Executed completely");
    }
}
