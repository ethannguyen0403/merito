package agentsite.testcase.proteus;

import agentsite.pages.agentmanagement.CreateCompanyPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.AGConstant.*;
import static common.AGConstant.AgencyManagement.CreateCompany.*;

public class CreateCompanyTest extends BaseCaseTest {

    @TestRails(id = "4042")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4042(String currency) {
        log("@title: Validate in Agent site > Create Company: default value in HKD currency of PS38 Bet Setitng is correct");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.productStatusSettingInforSection.selectProduct(PS38);
        log("Verify 1: Sport dropdown with selected value = All by default");
        Assert.assertTrue(page.betSettingInforSection.ddbSportsPS38.getSelectedOption().contains(ALL), "FAILED! Sport dropdown selected value is NOT All");
        log("Verify 2: Pregame tab is active with the checkbox \"Copy all Limits for all Sports and Leagues for Pregame ONLY from the sport below\" is ticked by default");
        page.betSettingInforSection.verifyPS38TabIsCorrect(PREGAME_TAB_PS38, CHECKBOX_MESSAGE_PS38);
        log("Verify 3: The list sports: Soccer, Baseball, Basketball, Football, E Sports, Others, Mix Parlay, Teaser");
        Assert.assertEquals(page.betSettingInforSection.tblBetSettingPS38.getColumnByBody(1, false), LIST_SPORTS_PS38, "FAILED! List sports of PS38 incorrect");
        Assert.assertEquals(page.betSettingInforSection.tblBetSettingPS38.getHeaderNameOfRows(), HEADER_BET_SETTING_PS38, "FAILED! Header name is incorrect ");
        log("Verify 4: Min bet value and setting 1 and >= HKD 1 for all sports\n" +
                "Max bet value and setting 10 and <= HKD 100,000,000 for all sports\n" +
                "Max per Match value and setting = 10,000,000 and <= HKD 100,000,000");
        String conditionMinBet = String.format(">= %s %s", currency, AMOUNT_MIN_BET_PS38_HKD);
        String conditionMaxBet = String.format("<= %s %s", currency, AMOUNT_MAX_BET_PS38_HKD);
        String conditionMaxPerMatch = String.format("<= %s %s", currency, AMOUNT_MAX_PER_MATCH_PS38_HKD);
        page.betSettingInforSection.verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(1), conditionMinBet);
        page.betSettingInforSection.verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(1), AMOUNT_MIN_BET_PS38_HKD);

        page.betSettingInforSection.verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(2), conditionMaxBet);
        page.betSettingInforSection.verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(2), AMOUNT_MAX_BET_PS38_HKD);

        page.betSettingInforSection.verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(3), conditionMaxPerMatch);
        page.betSettingInforSection.verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(3), AMOUNT_MAX_PER_MATCH_PS38_HKD);
    }

    @TestRails(id = "4043")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4043(String currency) {
        log("@title: Validate in Agent site > Create Company: default value in HKD currency of PS38 Bet Setting , Inlay tab is correct");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.productStatusSettingInforSection.selectProduct(PS38);
        log("Step 4: Click on Inlay tab");
        log("Verify 1: Inplay tab is active with the checkbox \"Copy all Limits for all Sports and Leagues for Inlay ONLY from the sport below\" is ticked by default");
        page.betSettingInforSection.verifyPS38TabIsCorrect(INPLAY_TAB_PS38, CHECKBOX_MESSAGE_PS38);
        log("Verify 2: Bet Setting section display the first column with the list sports: Soccer, Baseball, Basketball, Football, E Sports, Others, Mix Parlay, Teaser and header table : Min Bet, Max Bet, Max per Match");
        Assert.assertEquals(page.betSettingInforSection.tblBetSettingPS38.getColumnByBody(1, false), LIST_SPORTS_PS38, "FAILED! List sports of PS38 incorrect");
        Assert.assertEquals(page.betSettingInforSection.tblBetSettingPS38.getHeaderNameOfRows(), HEADER_BET_SETTING_PS38, "FAILED! Header name is incorrect ");
        log("Verify 4: Min bet value and setting 1 and >= HKD 1 for all sports\n" +
                "Max bet value and setting 10 and <= HKD 100,000,000 for all sports\n" +
                "Max per Match value and setting = 10,000,000 and <= HKD 100,000,000");
        String conditionMinBet = String.format(">= %s %s", currency, AMOUNT_MIN_BET_PS38_HKD);
        String conditionMaxBet = String.format("<= %s %s", currency, AMOUNT_MAX_BET_PS38_HKD);
        String conditionMaxPerMatch = String.format("<= %s %s", currency, AMOUNT_MAX_PER_MATCH_PS38_HKD);
        page.betSettingInforSection.verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(1), conditionMinBet);
        page.betSettingInforSection.verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(1), AMOUNT_MIN_BET_PS38_HKD);

        page.betSettingInforSection.verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(2), conditionMaxBet);
        page.betSettingInforSection.verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(2), AMOUNT_MAX_BET_PS38_HKD);

        page.betSettingInforSection.verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(3), conditionMaxPerMatch);
        page.betSettingInforSection.verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(3), AMOUNT_MAX_PER_MATCH_PS38_HKD);

    }

    @TestRails(id = "4044")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    public void PS38_Agent_TC4044(){
        log("@title: Validate in Agent site > Create Company Min, Max, Max per Match is converted correct");
        String currency = "INR";
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.productStatusSettingInforSection.selectProduct(PS38);
        log("Verify 1: Pregame and Inlay tab, Min, Max, Max per Match value should be correctly converted\n" +
                "Min = minHKD * rateCurrencyBO = 1 /0.113398 ~ 9 INR\n" +
                "Max = maxHKD * rateCurrencyBO = 100000000 / 0.113398 ~881,849,768.073 INR\n" +
                "Max per Match = maxHKD * rateCurrencyBO = 100000000 / 0.113398 ~881,849,768.073 INR\n" +
                "\n");
        String conditionMinBet = String.format(">= %s %s", currency, AMOUNT_MIN_BET_PS38_INR);
        String conditionMaxBet = String.format("<= %s %s", currency, AMOUNT_MAX_BET_PS38_INR);
        String conditionMaxPerMatch = String.format("<= %s %s", currency, AMOUNT_MAX_PER_MATCH_PS38_INR);
        page.betSettingInforSection.verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(1), conditionMinBet);
        page.betSettingInforSection.verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(1), AMOUNT_MIN_BET_PS38_INR);

        page.betSettingInforSection.verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(2), conditionMaxBet);
        page.betSettingInforSection.verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(2), AMOUNT_MAX_BET_PS38_INR);

        page.betSettingInforSection.verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(3), conditionMaxPerMatch);
        page.betSettingInforSection.verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(3), AMOUNT_MAX_PER_MATCH_PS38_INR);
    }

    @TestRails(id = "4045")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4045(String currency) {
        log("@title: Validate in Agent site > Create Company, confirm message display if update setting in Pregame then switch to In-play tab");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.productStatusSettingInforSection.selectProduct(PS38);
        log("Step 4: Update any fields in Pregame tab then click on In-Play tab");
        page.betSettingInforSection.inputBetSettingPS38("Soccer", HEADER_BET_SETTING_PS38.get(1), "2");
        page.betSettingInforSection.selectPS38Tab(INPLAY_TAB_PS38);
        log("Verify 1: Verify confirm message display and Switch tab, Cancel button");
        Assert.assertEquals(page.getMessageUpdate(false), SWITCH_TAB_MESSAGE_PS38, "FAILED! The message when switching tab is not correct");
        Assert.assertTrue(page.btnCancelSwitchTab.isDisplayed() && page.btnSwitchTab.isDisplayed(), "FAILED! The button Cancel and Switch tabs is not displayed");
    }

    @TestRails(id = "4046")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4046(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Betsetting, confirm message display if update setting in In-Play then switch to Pregame tab");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.productStatusSettingInforSection.selectProduct(PS38);
        log("Step 4: Do not update any value in Pregame tab, just click on Inplay tab then input any value");
        page.betSettingInforSection.selectPS38Tab(INPLAY_TAB_PS38);
        page.betSettingInforSection.inputBetSettingPS38("Soccer", HEADER_BET_SETTING_PS38.get(1), "2");
        log("Step 5: Click on Pregame tab");
        page.betSettingInforSection.selectPS38Tab(PREGAME_TAB_PS38);

        log("Verify 1: Verify confirm message display and Switch tab, Cancel button");
        Assert.assertEquals(page.getMessageUpdate(false), SWITCH_TAB_MESSAGE_PS38, "FAILED! The message when switching tab is not correct");
        Assert.assertTrue(page.btnCancelSwitchTab.isDisplayed() && page.btnSwitchTab.isDisplayed(), "FAILED! The button Cancel and Switch tabs is not displayed");
    }
}
