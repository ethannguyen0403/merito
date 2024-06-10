package membersite.testcases.proteus;

import baseTest.BaseCaseTest;
import common.MemberConstants;
import membersite.objects.proteus.Market;
import membersite.pages.components.ps38preferences.PS38PreferencesPopup;
import membersite.pages.proteus.AsianViewPage;
import membersite.pages.proteus.ProteusHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.ProteusConstant.*;
import static common.ProteusConstant.TEXT_HDP;

public class PS38PreferencesTest extends BaseCaseTest {

    @TestRails(id = "23677")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.2.0"})
    public void PS38_Preferences_TC23677(){
        log("@title: Validate PS38 Preferences menu display when the product PS38 is active");
        log("@Precondition: Login to the account active PS38 product");
        log("Step 1: Expand My Account and observer the menu");
        memberHomePage.openMyAccount();
        log("Verify 1: Verify the menu PS38 Reference display");
        Assert.assertTrue(memberHomePage.isMyAccountContains(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("PS38 Preferences")), "FAILED! Ps38 Preferences is not displayed on My account"); ;
    }

    @TestRails(id = "23678")
    @Test(groups = {"ps38_inactive", "nolan_Proteus.2024.V.2.0"})
    public void PS38_Preferences_TC23678(){
        log("@title: Validate PS38 Preferences menu display when the product PS38 is Inactive");
        log("@Precondition: Login to the account Inactive PS38 product");
        log("Step 1: Expand My Account and observer the menu");
        memberHomePage.openMyAccount();
        log("Verify 1: Verify the menu PS38 Reference is NOT display");
        Assert.assertFalse(memberHomePage.isMyAccountContains(MemberConstants.HomePage.DDB_MY_ACCOUNT.get("PS38 Preferences")), "FAILED! Ps38 Preferences displayed on My account"); ;
    }

    @TestRails(id = "23683")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.2.0"})
    public void PS38_Preferences_TC23683(){
        log("@title: Validate Accept Better Odds is checked when the setting is enable");
        log("@Precondition: Login to the account active PS38 product");
        log("Step 1: Expand My Account and click the menu PS38 Reference");
        PS38PreferencesPopup prefPopup = memberHomePage.openPS38PreferencesPopup();
        log("Step 2: Select Accept Better Odds is Enable and click Save");
        prefPopup.selectPreferences("", "", "", "Enable", "");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(DECIMAL);
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        log("Step 3: Click on PS38 product and click on an odds and observe Accept Better Odds check box in Bet Slip");
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(marketHDP, true, false);
        log("Verify 1: Verify the check box is checked");
        Assert.assertTrue(asianViewPage.chkAcceptBetterOdd.isSelected(), "FAILED! Check box accept better odd is not check");
    }

    @TestRails(id = "23684")
    @Test(groups = {"ps38", "nolan_Proteus.2024.V.2.0"})
    public void PS38_Preferences_TC23684(){
        log("@title: Validate Accept Better Odds is unchecked when the setting is disable");
        log("@Precondition: Login to the account active PS38 product");
        log("Step 1: Expand My Account and click the menu PS38 Reference");
        PS38PreferencesPopup prefPopup = memberHomePage.openPS38PreferencesPopup();
        log("Step 2: Select Accept Better Odds is Disable and click Save");
        prefPopup.selectPreferences("", "", "", "Disable", "");
        log("Step 3: Click on PS38 product and click on an odds and observe Accept Better Odds check box in Bet Slip");
        ProteusHomePage proteusHomePage =  memberHomePage.activePS38Product();
        AsianViewPage asianViewPage = proteusHomePage.selectAsianView();
        asianViewPage.selectOddsType(DECIMAL);
        asianViewPage.selectEventOnLeftMenu(EARLY_PERIOD, SOCCER);
        Market marketHDP = asianViewPage.getEventInfo(SOCCER, DECIMAL, TEXT_HDP, true, false);
        asianViewPage.clickOdds(marketHDP, true, false);
        log("Verify 1: Verify the check box is checked");
        Assert.assertFalse(asianViewPage.chkAcceptBetterOdd.isSelected(), "FAILED! Check box accept better odd is checked");
    }
}
