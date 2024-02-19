package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import membersite.objects.AccountBalance;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.ProteusBetslip;
import membersite.pages.proteus.AsianViewPage;
import membersite.pages.proteus.ProteusHomePage;
import membersite.utils.betplacement.BetUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.MemberConstants.LBL_SOCCER_SPORT;
import static common.ProteusConstant.*;

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
        log("Step 4. Pick a Match - Team Totals market");
        asianViewPage.openMoreMarkets();
        asianViewPage.selectMoreMarket(TEXT_MATCH_TOTAL);

        log("Step 5. From Decimal odds of account group A, calculate and check odds on account group E are corrected");
        log("Verify Odds of Match - Team Totals market is display correctly");
        asianViewPage.verifyMoreMarketOddsCorrect(DECIMAL,groupOdds,MARKET_TYPE_MAPPING.get("Match - Team Totals"),true);
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
        log("Step 4. Pick a Match - Team Totals market");
        asianViewPage.openMoreMarkets();
        asianViewPage.selectMoreMarket(TEXT_MATCH_TOTAL);

        log("Step 5. From Decimal odds of account group A, calculate and check odds on account group E are corrected");
        log("Verify Odds of Match - Team Totals market is display correctly");
        asianViewPage.verifyMoreMarketOddsCorrect(HONGKONG,groupOdds,MARKET_TYPE_MAPPING.get("Match - Team Totals"),true);
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
        log("Step 4. Pick a Match - Team Totals market");
        asianViewPage.openMoreMarkets();
        asianViewPage.selectMoreMarket(TEXT_MATCH_TOTAL);

        log("Step 5. From Decimal odds of account group A, calculate and check odds on account group E are corrected");
        log("Verify Odds of Match - Team Totals market is display correctly");
        asianViewPage.verifyMoreMarketOddsCorrect(MALAY,groupOdds,MARKET_TYPE_MAPPING.get("Match - Team Totals"),true);
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
        log("Step 4. Pick a Match - Team Totals market");
        asianViewPage.openMoreMarkets();
        asianViewPage.selectMoreMarket(TEXT_MATCH_TOTAL);

        log("Step 5. From Decimal odds of account group A, calculate and check odds on account group E are corrected");
        log("Verify Odds of Match - Team Totals market is display correctly");
        asianViewPage.verifyMoreMarketOddsCorrect(AMERICAN,groupOdds, MARKET_TYPE_MAPPING.get("Match - Team Totals"),true);
        log("INFO: Executed completely");
    }
    @TestRails(id = "4171")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4171() {
        log("@title: Validate toWin and toRisk correctly when placing on better negative MY odds");
        log("Precondition: Login member site-  the player active PS38 product");
        AccountBalance accountBalanceBeforeBet = BetUtils.getUserBalance();
        log("Step 1.Select Ps38 product");
        log("Step 2.Active Ps38 product active Asian View");
        ProteusHomePage proteusHomePage = memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();

        log("Step 3. Select Early the left menu and click on Soccer");
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);

        log("Step 4. Select MY odds odds type, pick a negative odds and place bet. @-0.71 with stake = 40 => toRisk = 28.4 and toWin= 40");
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);
        String eventId = asianViewPage.selectFirstNegativeOdds();
        ProteusBetslip betslipInfo = proteusHomePage.getBetSlipInfo(eventId);
        double stake = Double.valueOf(betslipInfo.getMinBet()) + 1;
        asianViewPage.placeBet(stake, true, true);

        log("Verify toRisk and toWin of this bet in Pending bet and Balance and exposure");
        proteusHomePage.switchTabBetSlip(PENDING_BETS_TAB);
        AccountBalance accountBalanceAfterBet = BetUtils.getUserBalance();
        asianViewPage.verifyToRiskToWinAndBalanceCorrect(stake, betslipInfo.getOdds(), MALAY, accountBalanceBeforeBet, accountBalanceAfterBet);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4172")
    @Test(groups = {"ps38","Proteus.2024.V.1.0"})
    public void PS38_Member_TC4172() {
        log("@title: Validate toWin and toRisk correctly when placing on better negative AM odds");
        log("Precondition: Login member site-  the player active PS38 product");
        AccountBalance accountBalanceBeforeBet = BetUtils.getUserBalance();
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
        double stake = Double.valueOf(betslipInfo.getMinBet()) + 1;
        asianViewPage.placeBet(stake, true, true);

        log("Verify toRisk and toWin of this bet in Pending bet and Balance and exposure");
        proteusHomePage.switchTabBetSlip(PENDING_BETS_TAB);
        AccountBalance accountBalanceAfterBet = BetUtils.getUserBalance();
        asianViewPage.verifyToRiskToWinAndBalanceCorrect(stake, betslipInfo.getOdds(), AMERICAN, accountBalanceBeforeBet,accountBalanceAfterBet);
        log("INFO: Executed completely");
    }
}
