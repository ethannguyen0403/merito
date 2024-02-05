package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import membersite.objects.AccountBalance;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.Odds;
import membersite.pages.proteus.AsianViewPage;
import membersite.pages.proteus.EuroViewPage;
import membersite.pages.proteus.ProteusHomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.MemberConstants.LBL_SOCCER_SPORT;
import static common.MemberConstants.LBL_TENNIS_SPORT;
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
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, DECIMAL);

        log("Verify Odds of 1x2 market is display correctly based on user group");
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_MONEYLINE);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, DECIMAL);

        log("Verify Odds of 1 x2 market is displayed with the same odds in Decimal\\n\" +\n" +
                "                \"For market 1x2 with odds HK/MY they will have the same odds as DEC");
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_MONEYLINE);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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

        log("Step 5. From Malay odds off account group A, calculate and check odds on account group E is correct");
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, DECIMAL);

        log("Verify Odds of 1 x2 market is displayed with the same odds in Decimal\\n\" +\n" +
                "                \"For market 1x2 with odds HK/MY they will have the same odds as DEC");
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_MONEYLINE);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, AMERICAN);

        log("Verify American Odds of 1x2 market is display correctly based on user group");
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_MONEYLINE);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, DECIMAL);

        log("Verify American Odds of OverUnder market is display correctly based on user group");
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_OVER_UNDER);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, HONGKONG);

        log("Verify Hongkong Odds of OverUnder market is display correctly based on user group");
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_OVER_UNDER);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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

        log("Step 6. From Malay odds off account group A, calculate and check odds on account group E is correct");
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, MALAY);

        log("Verify Malay Odds of OverUnder market is display correctly based on user group");
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_OVER_UNDER);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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

        log("Step 6. From Malay odds off account group A, calculate and check odds on account group E is correct");
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, AMERICAN);

        log("Verify American Odds of OverUnder market is display correctly based on user group");
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_OVER_UNDER);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, DECIMAL);

        log("Verify American Odds of OverUnder market is display correctly based on user group");
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_OVER_UNDER);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group C is correct");
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, HONGKONG);

        log("Verify Hongkong Odds of OverUnder market is display correctly based on user group");
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_OVER_UNDER);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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

        log("Step 6. From Malay odds off account group A, calculate and check odds on account group C is correct");
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, MALAY);

        log("Verify Malay Odds of OverUnder market is display correctly based on user group");
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_OVER_UNDER);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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

        log("Step 6. From Malay odds off account group A, calculate and check odds on account group C is correct");
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, AMERICAN);

        log("Verify American Odds of OverUnder market is display correctly based on user group");
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_OVER_UNDER);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, DECIMAL);

        log("Verify Decimal Odds of Handicap market is display correctly based on user group");
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_HDP);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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

        log("Step 6. From Decimal odds off account group A, calculate and check odds on account group E is correct");
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, HONGKONG);

        log("Verify Hongkong Odds of Handicap market is display correctly based on user group");
        asianViewPage.selectOddsType(ASIAN_HONGKONG_ODDS);
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_HDP);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
        log("INFO: Executed completely");
    }

    @TestRails(id = "4165")
    @Test(groups = {"ps38_groupe","Proteus.2024.V.1.0_groupe"})
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

        log("Step 6. From Malay odds off account group A, calculate and check odds on account group E is correct");
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, MALAY);

        log("Verify Malay Odds of Handicap market is display correctly based on user group");
        asianViewPage.selectOddsType(ASIAN_MALAY_ODDS);
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_HDP);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
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

        log("Step 6. From Malay odds off account group A, calculate and check odds on account group E is correct");
        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, AMERICAN);

        log("Verify American Odds of Handicap market is display correctly based on user group");
        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);
        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_HDP);
        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
        log("INFO: Executed completely");
    }

//    @TestRails(id = "4167")
//    @Test(groups = {"ps38","Proteus.2024.V.1.01"})
//    public void PS38_Member_TC4167() {
//        log("@title: Validate Player group E display the correct American odds in Handicap market Asian View");
//        log("Precondition: Login member site-  the player active PS38 product and belong to group E");
//        log("Step 1. Login member site");
//        log("Step 2. Active PS38 product");
//        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
//
//        log("Step 3. Select Asian View and select odds type = Decimal");
//        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
//        asianViewPage.selectOddsType(ASIAN_DECIMAL_ODDS);
//
//        log("Step 4. Select Early the left menu and click on Soccer");
//        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD,SOCCER);
//        String eventId = asianViewPage.getFirstEventId();
//        proteusHomePage.openMoreMarkets(eventId);
//        proteusHomePage.selectMoreMarket(TEXT_MATCH_TOTAL);
//        List<Market> lstMarkets = asianViewPage.getEventInfoMoreMarket(eventId, "SOCCER", DECIMAL, MARKET_TYPE_MAPPING.get("Match - Team Totals"),"HOME");

//        Market marketBase = asianViewPage.getEventInfo(SOCCER,DECIMAL,TEXT_HDP,true);
//
//        log("Step 6. From Malay odds off account group A, calculate and check odds on account group E is correct");
//        List<Odds> lstOddsConvert = proteusHomePage.convertOddsToGroup(marketBase, groupOdds, AMERICAN);
//
//        log("Verify American Odds of Handicap market is display correctly based on user group");
//        asianViewPage.selectOddsType(ASIAN_AMERICAN_ODDS);
//        Market marketUI = asianViewPage.getEventInfoUI(marketBase.getEventId(), TEXT_HDP);
//        asianViewPage.verifyOddsShowCorrect(lstOddsConvert, marketUI.getOdds());
//        log("INFO: Executed completely");
//    }

}
