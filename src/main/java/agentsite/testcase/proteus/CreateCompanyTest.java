package agentsite.testcase.proteus;

import agentsite.pages.agentmanagement.CreateCompanyPage;
import backoffice.utils.system.CurrencyManagementUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static common.AGConstant.*;
import static common.AGConstant.AgencyManagement.CreateCompany.*;
import static common.MemberConstants.LBL_SOCCER_SPORT;

public class CreateCompanyTest extends BaseCaseTest {


    @TestRails(id = "4042")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4042(String currency) {
        log("@title: Validate in Agent site > Create Company: default value in HKD currency of PS38 Bet Setitng is correct");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.selectProduct(PS38);
        log("Verify 1: Sport dropdown with selected value = All by default");
        Assert.assertTrue(page.betSettingSectionPS38.ddbSportsPS38.getFirstSelectedOption().equals(ALL), "FAILED! Sport dropdown selected value is NOT All");
        log("Verify 2: Pregame tab is active with the checkbox \"Copy all Limits for all Sports and Leagues for Pregame ONLY from the sport below\" is ticked by default");
        page.betSettingSectionPS38.verifyPS38TabIsCorrect(PREGAME_TAB_PS38, CHECKBOX_MESSAGE_PS38_BET_SETTING);
        log("Verify 3: The list sports: Soccer, Baseball, Basketball, Football, E Sports, Others, Mix Parlay, Teaser");
        Assert.assertEquals(page.betSettingSectionPS38.tblBetSettingPS38.getColumnByBody(1, false), LIST_SPORTS_PS38_BET_SETTING, "FAILED! List sports of PS38 incorrect");
        Assert.assertEquals(page.betSettingSectionPS38.tblBetSettingPS38.getHeaderNameOfRows(), HEADER_BET_SETTING_PS38, "FAILED! Header name is incorrect ");
        log("Verify 4: Min bet value and setting 1 and >= HKD 1 for all sports\n" +
                "Max bet value and setting 10 and <= HKD 100,000,000 for all sports\n" +
                "Max per Match value and setting = 10,000,000 and <= HKD 100,000,000");
        page.betSettingSectionPS38.verifyBetSettingAllSportsConvertToCurrency(currency, 1, AMOUNT_MIN_BET_PS38_HKD, AMOUNT_MAX_BET_PS38_HKD, AMOUNT_MAX_PER_MATCH_PS38_HKD);
    }

    @TestRails(id = "4043")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4043(String currency) {
        log("@title: Validate in Agent site > Create Company: default value in HKD currency of PS38 Bet Setting , Inlay tab is correct");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.selectProduct(PS38);
        log("Step 4: Click on Inlay tab");
        log("Verify 1: Inplay tab is active with the checkbox \"Copy all Limits for all Sports and Leagues for Inlay ONLY from the sport below\" is ticked by default");
        page.betSettingSectionPS38.verifyPS38TabIsCorrect(INPLAY_TAB_PS38, CHECKBOX_MESSAGE_PS38_BET_SETTING);
        log("Verify 2: Bet Setting section display the first column with the list sports: Soccer, Baseball, Basketball, Football, E Sports, Others, Mix Parlay, Teaser and header table : Min Bet, Max Bet, Max per Match");
        Assert.assertEquals(page.betSettingSectionPS38.tblBetSettingPS38.getColumnByBody(1, false), LIST_SPORTS_PS38_BET_SETTING, "FAILED! List sports of PS38 incorrect");
        Assert.assertEquals(page.betSettingSectionPS38.tblBetSettingPS38.getHeaderNameOfRows(), HEADER_BET_SETTING_PS38, "FAILED! Header name is incorrect ");
        log("Verify 4: Min bet value and setting 1 and >= HKD 1 for all sports\n" +
                "Max bet value and setting 10 and <= HKD 100,000,000 for all sports\n" +
                "Max per Match value and setting = 10,000,000 and <= HKD 100,000,000");
        page.betSettingSectionPS38.verifyBetSettingAllSportsConvertToCurrency(currency, 1, AMOUNT_MIN_BET_PS38_HKD, AMOUNT_MAX_BET_PS38_HKD, AMOUNT_MAX_PER_MATCH_PS38_HKD);
    }

    @TestRails(id = "4044")
    @Parameters({"BOLoginId", "BOLoginPwd", "username", "password"})
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    public void PS38_Agent_TC4044(String BOLoginId, String BOLoginPwd, String username, String password) throws Exception {
        log("@title: Validate in Agent site > Create Company Min, Max, Max per Match is converted correct");

        //Login BO to get currency rate
        loginBackoffice(BOLoginId, BOLoginPwd, true);
        String currency = "INR";
        Double rate = new BigDecimal(Double.toString(Double.valueOf(CurrencyManagementUtils.getCurrencyRate(currency))
        )).setScale(6, RoundingMode.HALF_UP).doubleValue();

        log("Precondition: Log in successfully by PO level");
        loginAgent(username, password, true);
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.selectProduct(PS38);
        log("Verify 1: Pregame and Inlay tab, Min, Max, Max per Match value should be correctly converted\n" +
                "Min = minHKD * rateCurrencyBO = 1 /0.113398 ~ 9 INR\n" +
                "Max = maxHKD * rateCurrencyBO = 100000000 / 0.113398 ~881,849,768.073 INR\n" +
                "Max per Match = maxHKD * rateCurrencyBO = 100000000 / 0.113398 ~881,849,768.073 INR\n" +
                "\n");
        page.betSettingSectionPS38.verifyBetSettingAllSportsConvertToCurrency(currency, rate, AMOUNT_MIN_BET_PS38_HKD, AMOUNT_MAX_BET_PS38_HKD, AMOUNT_MAX_PER_MATCH_PS38_HKD);
    }

    @TestRails(id = "4045")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4045(String currency) {
        log("@title: Validate in Agent site > Create Company, confirm message display if update setting in Pregame then switch to In-play tab");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.selectProduct(PS38);
        log("Step 4: Update any fields in Pregame tab then click on In-Play tab");
        page.betSettingSectionPS38.updateValueInputSportPS38(Arrays.asList(LBL_SOCCER_SPORT), Arrays.asList(HEADER_BET_SETTING_PS38.get(1)), Arrays.asList("2"));
        page.betSettingSectionPS38.selectPS38Tab(INPLAY_TAB_PS38);
        log("Verify 1: Verify confirm message display and Switch tab, Cancel button");
        Assert.assertEquals(page.getMessageUpdate(false), SWITCH_TAB_MESSAGE_PS38, "FAILED! The message when switching tab is not correct");
        Assert.assertTrue(page.btnCancelSwitchTab.isDisplayed() && page.btnSwitchTab.isDisplayed(), "FAILED! The button Cancel and Switch tabs is not displayed");
    }

    @TestRails(id = "4046")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4046(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Betsetting, confirm message display if update setting in In-Play then switch to Pregame tab");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.selectProduct(PS38);
        log("Step 4: Do not update any value in Pregame tab, just click on Inplay tab then input any value");
        page.betSettingSectionPS38.selectPS38Tab(INPLAY_TAB_PS38);
        page.betSettingSectionPS38.updateValueInputSportPS38(Arrays.asList(LBL_SOCCER_SPORT), Arrays.asList(HEADER_BET_SETTING_PS38.get(1)), Arrays.asList("2"));
        log("Step 5: Click on Pregame tab");
        page.betSettingSectionPS38.selectPS38Tab(PREGAME_TAB_PS38);

        log("Verify 1: Verify confirm message display and Switch tab, Cancel button");
        Assert.assertEquals(page.getMessageUpdate(false), SWITCH_TAB_MESSAGE_PS38, "FAILED! The message when switching tab is not correct");
        Assert.assertTrue(page.btnCancelSwitchTab.isDisplayed() && page.btnSwitchTab.isDisplayed(), "FAILED! The button Cancel and Switch tabs is not displayed");
    }

    @TestRails(id = "4047")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4047(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, can switch to new tab if confirm to switch");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.selectProduct(PS38);
        log("Step 4: Update any fields in Pregame tab then click on In-Play tab");
        page.betSettingSectionPS38.updateValueInputSportPS38(Arrays.asList(LBL_SOCCER_SPORT), Arrays.asList(HEADER_BET_SETTING_PS38.get(1)), Arrays.asList("2"));
        log("Step 5: Click on Inplay tab");
        page.betSettingSectionPS38.selectPS38Tab(INPLAY_TAB_PS38);
        log("Step 6: Click on Switch tab button");
        page.btnSwitchTab.click();

        log("Verify 1: Verify user is able switch to Inplay tab");
        Assert.assertEquals(page.betSettingSectionPS38.lblCheckboxPS38.getText().trim(), String.format(CHECKBOX_MESSAGE_PS38_BET_SETTING, INPLAY_TAB_PS38),
                String.format("FAILED! Checkbox message of tab: %s incorrect", INPLAY_TAB_PS38));
    }

    @TestRails(id = "4048")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4048(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, can not switch to new tab if do not confirm to switch");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.selectProduct(PS38);
        log("Step 4: Update any fields in Pregame tab then click on In-Play tab");
        page.betSettingSectionPS38.updateValueInputSportPS38(Arrays.asList(LBL_SOCCER_SPORT), Arrays.asList(HEADER_BET_SETTING_PS38.get(1)), Arrays.asList("2"));
        log("Step 5: Click on Inplay tab");
        page.betSettingSectionPS38.selectPS38Tab(INPLAY_TAB_PS38);
        log("Step 6: Click on Cancel button");
        page.btnCancelSwitchTab.click();

        log("Verify 1: Verify user is still at Pregame tab");
        Assert.assertEquals(page.betSettingSectionPS38.lblCheckboxPS38.getText().trim(), String.format(CHECKBOX_MESSAGE_PS38_BET_SETTING, PREGAME_TAB_PS38),
                String.format("FAILED! Checkbox message of tab: %s incorrect", PREGAME_TAB_PS38));
    }

    @TestRails(id = "4049")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4049(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, UI in Pregame tab when checkbox \"Copy all Limits for all Sport...\" is ticked ");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 and observe bet setting");
        page.selectProduct(PS38);

        log("Verify 1: In Pregame tab, only Min, Max, Max per Match textbox of Soccer is enable, other is disable");
        List<String> betSettingList = new ArrayList<>(HEADER_BET_SETTING_PS38);
        betSettingList.remove(0); // remove blank cell in header list
        Assert.assertTrue(page.betSettingSectionPS38.isSportPS38InputEnable(Arrays.asList(LIST_SPORTS_PS38_BET_SETTING.get(0)), betSettingList, true),
                String.format("FAILED! Sport row: %s is not Enable", LIST_SPORTS_PS38_BET_SETTING.get(0)));

        List<String> sportsList = new ArrayList<>(LIST_SPORTS_PS38_BET_SETTING);
        sportsList.remove(0); //remove first sport because it is enable by default
        Assert.assertTrue(page.betSettingSectionPS38.isSportPS38InputEnable(sportsList, betSettingList, false),
                "FAILED! Some sport rows is not Disable. List sport row: " + sportsList);
    }

    @TestRails(id = "4050")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4050(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, UI in Pregame tab when checkbox \"Copy all Limits for all Sport...\" is unticked");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 ");
        page.selectProduct(PS38);
        log("Step 4: Uncheck tick box ");
        page.betSettingSectionPS38.chkTabPS38.deSelect();
        log("Verify 1: In Pregame tab. All texboxes are enable to edit");
        List<String> betSettingList = new ArrayList<>(HEADER_BET_SETTING_PS38);
        betSettingList.remove(0); // remove blank cell in header list
        Assert.assertTrue(page.betSettingSectionPS38.isSportPS38InputEnable(LIST_SPORTS_PS38_BET_SETTING, betSettingList, true),
                "FAILED! Some sport rows is not enable. List sport row: " + LIST_SPORTS_PS38_BET_SETTING);
    }

    @TestRails(id = "4051")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4051(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, UI in Inplay tab when checkbox \"Copy all Limits for all Sport...\" is ticked");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38 ");
        page.selectProduct(PS38);
        log("Step 4: Select tab InPlay");
        page.betSettingSectionPS38.selectPS38Tab(INPLAY_TAB_PS38);

        log("Verify 1: In Inplay tab, only Min, Max textbox of Soccer is enable, other is disable");
        List<String> betSettingList = new ArrayList<>(Arrays.asList(HEADER_BET_SETTING_PS38.get(1), HEADER_BET_SETTING_PS38.get(2)));
        Assert.assertTrue(page.betSettingSectionPS38.isSportPS38InputEnable(Arrays.asList(LIST_SPORTS_PS38_BET_SETTING.get(0)), betSettingList, true),
                String.format("FAILED! Sport row: %s is not Enable", LIST_SPORTS_PS38_BET_SETTING.get(0)));

        List<String> sportsList = new ArrayList<>(LIST_SPORTS_PS38_BET_SETTING);
        sportsList.remove(0); //remove first sport because it is enable by default
        Assert.assertTrue(page.betSettingSectionPS38.isSportPS38InputEnable(sportsList, betSettingList, false),
                "FAILED! Some sport rows is not Disable. List sport row: " + sportsList);
    }

    @TestRails(id = "4052")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4052(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, UI in Inplay tab when checkbox \"Copy all Limits for all Sport...\" is unticked");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38");
        page.selectProduct(PS38);
        log("Step 4: Select tab InPlay");
        page.betSettingSectionPS38.selectPS38Tab(INPLAY_TAB_PS38);
        log("Step 5: Uncheck tick box");
        page.betSettingSectionPS38.chkTabPS38.deSelect();

        log("Verify 1: In Inplay tab. All Min, Max texboxes are enable to edit, Max per Match is disable");
        Assert.assertTrue(page.betSettingSectionPS38.isSportPS38InputEnable(
                        LIST_SPORTS_PS38_BET_SETTING, Arrays.asList(HEADER_BET_SETTING_PS38.get(1), HEADER_BET_SETTING_PS38.get(2)), true),
                "FAILED! Some sport rows with Min, max are not enable. List sport row: " + LIST_SPORTS_PS38_BET_SETTING);
        Assert.assertTrue(!page.betSettingSectionPS38.isSportPS38InputEnable(LIST_SPORTS_PS38_BET_SETTING, Arrays.asList(HEADER_BET_SETTING_PS38.get(3)), true),
                "FAILED! Some sport rows with Match per match is not disable. List sport row: " + LIST_SPORTS_PS38_BET_SETTING);
    }

    @TestRails(id = "4053")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4053(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, Max per Match value in In-Play tab is correct as Pregame tab");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38");
        page.selectProduct(PS38);
        log("Step 4: Select tab InPlay");
        page.betSettingSectionPS38.selectPS38Tab(INPLAY_TAB_PS38);

        log("Verify 1: Check Max per Match value is corrected as value in Pregame tab");
        page.betSettingSectionPS38.verifyBetSettingSpecificSportConvertToCurrency(LBL_SOCCER_SPORT, 1, AMOUNT_MIN_BET_PS38_HKD, AMOUNT_MAX_BET_PS38_HKD, AMOUNT_MAX_PER_MATCH_PS38_HKD);
    }

    @TestRails(id = "4054")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4054(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, League dropdown display when select a sport");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38");
        page.selectProduct(PS38);

        log("Step 4: Select sport dropdown with sport: " + LBL_SOCCER_SPORT);
        page.betSettingSectionPS38.addSport(LBL_SOCCER_SPORT, "", false);

        log("Verify 1: Verify League dropdown displays");
        Assert.assertTrue(page.betSettingSectionPS38.ddbLeaguePS38.isDisplayed(), "FAILED! League dropdown is NOT displayed");
    }

    @TestRails(id = "4055")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4055(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, League dropdown does not display when sport is Other, Teaser, Mix Parlay");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38");
        page.selectProduct(PS38);
        log("Step 4: Select sport dropdown with sport: Teaser" );
        page.betSettingSectionPS38.addSport("Teaser", "", false);

        log("Verify 1: Verify League dropdown displays");
        Assert.assertTrue(!page.betSettingSectionPS38.ddbLeaguePS38.isDisplayed(), "FAILED! League dropdown is displayed");
    }

    @TestRails(id = "4056")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4056(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, Add button display if a League does not add");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38");
        page.selectProduct(PS38);

        log("Step 4: Select sport dropdown with sport: Alpine Skiing and a league not added yet");
        log("*Note: sport should not included in default sports list: \"Soccer\", \"Baseball\", \"Basketball\", \"Football\", \"E Sports\", \"Others\", \"Mix Parlay\", \"Teaser\"\n");
        page.betSettingSectionPS38.addSport("Alpine Skiing", "", false);
        log("Verify 1: Verify the button Add display");
        Assert.assertTrue(page.betSettingSectionPS38.btnAdd.isDisplayed(), "FAILED! Add button is NOT displayed");
    }

    @TestRails(id = "4057")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4057(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, View button display if a League was added");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38");
        page.selectProduct(PS38);

        log("Step 4: Select sport dropdown with sport: Alpine Skiing and a league added");
        page.betSettingSectionPS38.addSport("Alpine Skiing", "2", true);

        log("Verify 1: Verify the button View  displays");
        Assert.assertTrue(page.betSettingSectionPS38.btnView.isDisplayed(), "FAILED! View button is not displayed");
    }

    @TestRails(id = "4058")
    @Test(groups = {"ps38_po", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4058(String currency) {
        log("@title: Validate in Agent site > Create Company, Ps38 Bet Setting, Special league inherit the setting of the according general sport");
        log("Precondition: Log in successfully by PO level");
        log("Step 1: Navigate Agency Management > Create Company");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());
        log("Step 2: Select base currency: " + currency);
        page.accountInforSection.selectCurrency(currency);
        log("Step 3: Under Product Settings , select product PS38");
        page.selectProduct(PS38);
        log("Step 4:  Select Sport = Soccer, League = General. Observe the setting of General row");
        page.betSettingSectionPS38.addSport(LBL_SOCCER_SPORT, GENERAL, true);
        log("Step 5: Add other league");
        page.betSettingSectionPS38.addSport("", "1", true);

        String lblLeagueSetting = String.format("%s: %s", LBL_SOCCER_SPORT,  page.betSettingSectionPS38.ddbLeaguePS38.getFirstSelectedOption().trim());
        log(String.format("Verify 1: The league: %s is add under sport and inherit value and setting of General sport", lblLeagueSetting));
        page.betSettingSectionPS38.verifyBetSettingSpecificSportConvertToCurrency(lblLeagueSetting, 1, AMOUNT_MIN_BET_PS38_HKD, AMOUNT_MAX_BET_PS38_HKD, AMOUNT_MAX_PER_MATCH_PS38_HKD);
    }
}
