package backoffice.testcases.marketmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo.marketmanagement.BeforeLoginManagementPage;
import backoffice.pages.bo.marketmanagement.components.BeforeLoginManagementPopup;
import backoffice.utils.system.CurrencyManagementUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Comparator;
import java.util.List;

import static common.MeritoConstant.MEMBER_SOS_URL_SUFFIX;
import static common.MeritoConstant.MEMBER_URL_SUFFIX;

public class BeforeLoginManagementTest extends BaseCaseTest {

    /**
     * @title: Validate sport is active/deactivate effect to member site before login page
     * @pre-condition: 1. Log in BO
     * @steps: 1. Access Operations > Before Login Management
     * 2. Active/Deactive a sport
     * @expect: 1. Verify the sport display/not display in member site
     */
    @Test(groups = {"regression_invalid"})
    @Parameters({"username", "password"})
    public void BO_Operations_Before_Login_Management_001(String username, String password) throws Exception {
        /*log("@title: Validate sport is active/deactivate effect to member site before login page");
        log("Step 1. Access Operations > Before Login Management");
        String sport = "Golf";
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Active a Sport");
        page.activeSport(sport,true);
        page.logout();

        log("Verify 1. Verify the sport display in before login when the sport is active");
        DriverManager.getDriver().get(environment.getSatDashboardURL());
        backofficeHomePage satbackofficeHomePage = new backofficeHomePage();
        satbackofficeHomePage.waitMenuLoading();
        List<String> lstSport = satbackofficeHomePage.getLeftMenuList();
        boolean isSportinLeftMenu = false;
        for(int i =0; i< lstSport.size(); i++)
        {
            if(lstSport.get(i).contains(sport)){
                isSportinLeftMenu = true;
             break;
            }
        }
        Assert.assertTrue(isSportinLeftMenu,"FAILED! Sport NOT display in before login when active");

        log("Step 3. Deactive a sport");
        Helper.loginBOIgnoreCaptcha(environment.getSosURL(),environment.getDashboardURL(),username,password,true);
       // DriverManager.getDriver().getToAvoidTimeOut(environment.getDashboardURL());
        //Helper.loginBOIgnoreCaptcha(environment.getSosURL(),environment.getDashboardURL(),username,password,true);
        page = backofficeHomePage.navigateBeforeLoginManagement();
        page.activeSport(sport,false);
        AlertMessageBox messageBox = new AlertMessageBox();
        String message = messageBox.getSuccessAlert();
        Assert.assertTrue(message.contains(String.format("The status of %s has been updated successfully.",sport)),"FAILED! The message alert is incorrect display after active sport");

        page.logout();
        log("Verify 2. Verify the sport not display in before login when the sport is Deactive");
        DriverManager.getDriver().get(environment.getSatDashboardURL());
        satbackofficeHomePage = new backofficeHomePage();
        satbackofficeHomePage.waitMenuLoading();
        lstSport = satbackofficeHomePage.getLeftMenuList();
        isSportinLeftMenu=false;
        for(int i =0; i< lstSport.size(); i++)
        {
            if(lstSport.get(i).contains(sport)){
                isSportinLeftMenu = true;
                break;
            }
        }
        Assert.assertFalse(isSportinLeftMenu,"FAILED! Sport display in before login when Inactive");
        log("INFO: Executed completely");*/
    }

    /**
     * @title: Validate active/deactivate market type of a sport will effect to member site before login page
     * @pre-condition: 1. Log in BO
     * @steps: 1. Access Operations > Before Login Management
     * 2. Select a sport : Cricket
     * 3. Active and inactive a  market:  Tied Match
     * @expect: 1. Verify the market is active/inactive on before login member. In the left menu, select cricket and any competition, verify Tied Match market is display/disappear per setting
     */
    @Test(groups = {"regression_invalid"})
    @Parameters({"username", "password"})
    public void BO_Operations_Before_Login_Management_002(String username, String password) throws Exception {
        log("@title: Validate sport is active/deactivate effect to member site before login page");
        log("Step 1. Access Operations > Before Login Management");
        String sport = "Cricket";
        String marketTye = "Tied Match";
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Select a sport : Cricket");
        log("Step 3. Active and inactive a  market:  Tied Match");
        page.activeSport(sport, true);
        page.searchMartyType(marketTye);
        page.activeMarket(marketTye, true);
        AlertMessageBox messageBox = new AlertMessageBox();
        String message = messageBox.getSuccessAlert();
        Assert.assertTrue(message.contains(String.format("The status of %s has been updated successfully.", marketTye)), "FAILED! The message alert is incorrect display after active sport");
        /*  page.logout();*/

/*
        log("Verify 1. Verify the market is active on before login member");
        DriverManager.getDriver().get(environment.getSatDashboardURL());
        backofficeHomePage satbackofficeHomePage = new backofficeHomePage();
        satbackofficeHomePage.waitMenuLoading();
        satbackofficeHomePage.clickMenu(sport);
        List<String> lstCompetitionLeftMenu = satbackofficeHomePage.getLeftMenuList();
        satbackofficeHomePage.clickMenu(lstCompetitionLeftMenu.get(lstCompetitionLeftMenu.size()-1));
        List<String> lstEvent = satbackofficeHomePage.getLeftMenuList();
        satbackofficeHomePage.clickMenu(lstEvent.get(lstEvent.size()-1));
        List<String> lstMarket = satbackofficeHomePage.getLeftMenuList();

        boolean lstMarketLeftMenu = false;
        for(int i =0; i< lstMarket.size(); i++)
        {
            if(lstMarket.get(i).contains(marketTye)){
                lstMarketLeftMenu = true;
                break;
            }
        }
        Assert.assertTrue(lstMarketLeftMenu,"FAILED! Market NOT display in before login when active");

        log("Step 4. inactive a  market: Tied Match");
        Helper.loginBOIgnoreCaptcha(environment.getSosURL(),environment.getDashboardURL(),username,password,true);*/
        //  page = backofficeHomePage.navigateBeforeLoginManagement();
        page.activeSport(sport, true);
        page.searchMartyType(marketTye);
        page.activeMarket(marketTye, false);
        messageBox = new AlertMessageBox();
        message = messageBox.getSuccessAlert();
        Assert.assertTrue(message.contains(String.format("The status of %s has been updated successfully.", marketTye)), "FAILED! The message alert is incorrect display after active sport");

        /*log("Verify 2. Verify the sport not display in before login when the sport is inactive");
        DriverManager.getDriver().get(environment.getSatDashboardURL());
        satbackofficeHomePage.waitMenuLoading();
        satbackofficeHomePage.clickMenu(sport);
       lstCompetitionLeftMenu = satbackofficeHomePage.getLeftMenuList();
        satbackofficeHomePage.clickMenu(lstCompetitionLeftMenu.get(lstCompetitionLeftMenu.size()-1));
        lstEvent = satbackofficeHomePage.getLeftMenuList();
        satbackofficeHomePage.clickMenu(lstEvent.get(lstEvent.size()-1));
        lstMarket = satbackofficeHomePage.getLeftMenuList();
        lstMarketLeftMenu=false;
        for(int i =0; i< lstMarket.size(); i++)
        {
            if(lstMarket.get(i).contains(sport)){
                lstMarketLeftMenu = true;
                break;
            }
        }
        Assert.assertFalse(lstMarketLeftMenu,"FAILED! Sport display in before login when Inactive");*/
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search market type
     * @pre-condition: 1. Log in BO
     * @steps: 1. Access Operations > Before Login Management
     * 2. Select a sport : Ice Hockey
     * 3. Input a market type and press enter : e.g: To Qualify
     * @expect: 1. Verify the market list will contain all markets type contain search key
     */
    @TestRails(id = "632")
    @Test(groups = {"smoke"})
    public void BO_Operations_Before_Login_Management_632() {
        log("@title: Validate can search market type");
        log("Step 1. Access Operations > Before Login Management");
        String sport = "Ice Hockey";
        String marketTye = "To Qualify";
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();
        page.filter("Sport & Market Type","","","",true);

        log("Step 2. Select a sport : Ice Hockey");
        log("Step 3. Active and inactive a  market: To Qualify");
        page.activeSport(sport, true);
        page.searchMartyType(marketTye);
        log("Step 1. Verify the market list will contain all markets type contain search key");
        List<String> lstMarket = page.tblMarketType.getColumn(page.colMarketType, false);
        Assert.assertTrue(lstMarket.contains("To Qualify"), "The list market does not contains search key");
    }

    @TestRails(id = "1653")
    @Test(groups = {"regression"})
    public void BO_Operations_Before_Login_Management_1653() {
        log("@title: Validate can search and navigate to page correctly");
        log("Step 1. Access Operations > Before Login Management");
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 1. Verify page title displays Before Login Management");
        Assert.assertEquals("Before Login Management", page.lblPageTitle.getText(), "FAILED ! Page title is not displayed correctly, actual: " + page.lblPageTitle.getText());
        log("INFO: Executed completely");
    }

    @TestRails(id = "1654")
    @Test(groups = {"regression"})
    public void BO_Operations_Before_Login_Management_1654() {
        String type = "Header Menu";
        log("@title: Validate can switch type Header Menu");
        log("Step 1. Access Operations > Before Login Management");
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Filter with type = Header Menu");
        page.filter(type, "FairExchange", "", "", false);
        log("Step 1. Verify page UI displays correctly with type = Header Menu");
        Assert.assertTrue(page.isUIDisplayCorrect(type), "FAILED ! Page UI is not displayed correctly for type: " + type);

        log("INFO: Executed completely");
    }

    @TestRails(id = "1655")
    @Test(groups = {"regression"})
    public void BO_Operations_Before_Login_Management_1655() {
        String type = "Sport & Market Type";
        log("@title: Validate can switch type Sport & Market Type");
        log("Step 1. Access Operations > Before Login Management");
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Filter with type = Sport & Market Type");
        page.filter(type, "FairExchange", "", "", false);
        log("Step 1. Verify page UI displays correctly with type = Sport & Market Type");
        Assert.assertTrue(page.isUIDisplayCorrect(type), "FAILED ! Page UI is not displayed correctly for type: " + type);

        log("INFO: Executed completely");
    }

    @TestRails(id = "1656")
    @Test(groups = {"regression"})
    public void BO_Operations_Before_Login_Management_1656() {
        String type = "Header Menu";
        log("@title: Validate UI of Create Header Menu");
        log("Step 1. Access Operations > Before Login Management");
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Filter with type = Header Menu");
        page.filter(type, "FairExchange", "", "", false);
        log("Step 2. Click Create Menu button");
        BeforeLoginManagementPopup popup = page.openCreateHeaderMenu();
        log("Verify UI in Create Header Menu popup with Sub-Menu = No shows correctly");
        Assert.assertTrue(popup.isUICreateMenuDisplayCorrect(false), "FAILED ! Page UI is not displayed correctly for Sub Menu = No");
        popup.ddpSubMenu.selectByVisibleText("Yes");
        log("Verify UI in Create Header Menu popup with Sub-Menu = Yes shows correctly");
        Assert.assertTrue(popup.isUICreateMenuDisplayCorrect(true), "FAILED ! Page UI is not displayed correctly for Sub Menu = Yes");

        log("INFO: Executed completely");
    }

    @TestRails(id = "1657")
    @Test(groups = {"regression"})
    @Parameters({"feMemberLoginId", "feMemberLoginPwd", "language", "currency"})
    public void BO_Operations_Before_Login_Management_1657(String feMemberLoginId, String feMemberLoginPwd, String language, String currency) throws Exception {
        String type = "Header Menu";
        log("@title: Validate FairExchange Before Login - Header Menu show as setting");
        log("Step 1. Access Operations > Before Login Management");
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Filter with type = Header Menu for FairExchange");
        page.filter(type, "FairExchange", "", BOConstants.Operations.BannerManagement.POPUP_DDB_STATUS.get(0), true);
        String domain = page.ddpDomain.getFirstSelectedOption();

        log("Step 3. Get list active menu and sequence");
        List<List<String>> lstMenuHeader = page.getListMenuSequence();
        List<String> lstMenu = lstMenuHeader.get(0);
        List<String> lstSequence = lstMenuHeader.get(1);
        lstMenu.sort(Comparator.comparingInt(lstSequence::indexOf));
        log("Step 4. Login to member site before login");
        memberLoginURL = defineURL("fairexchangeplus", MEMBER_URL_SUFFIX.get("fairexchangeplus"));
        loginMember(feMemberLoginId, feMemberLoginPwd, false, language, currency, false);

        log("Verify. Validate header menu show with active from BO with correct sequence");
        List<String> lstHeaderMenuLogin = landingPage.getListHeaderMenu();
        for (int i = 0; i < lstMenu.size(); i++) {
            Assert.assertTrue(lstMenu.get(i).equalsIgnoreCase(lstHeaderMenuLogin.get(i + 1)), "FAILED! Header menu is not displayed correctly follow BO site");
        }

        log("INFO: Executed completely");
    }

    @TestRails(id = "1658")
    @Test(groups = {"regression"})
    @Parameters({"feMemberLoginId", "feMemberLoginPwd", "language", "currency"})
    public void BO_Operations_Before_Login_Management_1658(String feMemberLoginId, String feMemberLoginPwd, String language, String currency) throws Exception {
        String type = "Header Menu";
        log("@title: Validate FairExchange Before Login - Header Menu show as setting");
        log("Step 1. Access Operations > Before Login Management");
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Filter with type = Header Menu for FairExchange");
        page.filter(type, "FairExchange", "", BOConstants.Operations.BannerManagement.POPUP_DDB_STATUS.get(0), true);
        String domain = page.ddpDomain.getFirstSelectedOption();

        log("Step 3. Get list active menu and sequence");
        List<List<String>> lstMenuHeader = page.getListMenuSequence();
        List<String> lstMenu = lstMenuHeader.get(0);
        List<String> lstSequence = lstMenuHeader.get(1);
        lstMenu.sort(Comparator.comparingInt(lstSequence::indexOf));
        log("Step 4. Login to member site before login");
        memberLoginURL = defineURL("fairexchangeplus", MEMBER_URL_SUFFIX.get("fairexchangeplus"));
        memberSOSUrl = defineURL("fairexchangeplus", MEMBER_SOS_URL_SUFFIX);
        loginMember(feMemberLoginId, feMemberLoginPwd, true, language, currency, true);

        log("Verify. Validate header menu show with active from BO with correct sequence");
        List<String> lstHeaderMenuLogin = memberHomePage.getListHeaderMenu();
        for (int i = 0; i < lstMenu.size(); i++) {
            Assert.assertTrue(lstMenu.get(i).equalsIgnoreCase(lstHeaderMenuLogin.get(i + 1)), "FAILED! Header menu is not displayed correctly follow BO site");
        }

        log("INFO: Executed completely");
    }

    @TestRails(id = "1659")
    @Test(groups = {"regression"})
    @Parameters({"feMemberLoginId", "feMemberLoginPwd", "language", "currency"})
    public void BO_Operations_Before_Login_Management_1659(String feMemberLoginId, String feMemberLoginPwd, String language, String currency) throws Exception {
        Float rate;
        String type = "Header Menu";
        log("@title: Validate Currency show correctly");
        log("Step 1. Access Operations > Before Login Management");
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Filter with type = Header Menu for FairExchange");
        page.filter(type, "FairExchange", "", BOConstants.Operations.BannerManagement.POPUP_DDB_STATUS.get(0), true);
        String domain = page.ddpDomain.getFirstSelectedOption();
        log("Step 3. Get currency of current branch filter");
        String currencySelected = page.ddpCurrency.getFirstSelectedOption();
        rate = CurrencyManagementUtils.getCurrencyRate(currencySelected);
        memberLoginURL = defineURL("fairexchangeplus", MEMBER_URL_SUFFIX.get("fairexchangeplus"));
        log("Step 4. Login to member site");
        loginMember(feMemberLoginId, feMemberLoginPwd, false, language, currency, true);
        List<String> lst = landingPage.getBeforeLoginConfig(environment.getFairURL());

        log("Verify. Validate currency between BO site and Member site before login matched");
        Assert.assertEquals(currencySelected, lst.get(0), "FAILED! Currency between BO and Before Login member site does not match, BO: "
                + currencySelected + " Member Site: " + lst.get(0));
        Assert.assertEquals(String.valueOf(rate), lst.get(2), "FAILED! Currency Rate between BO and Before Login member site does not match, BO: "
                + rate + " Member Site: " + lst.get(2));

        log("INFO: Executed completely");
    }

    @TestRails(id = "1660")
    @Test(groups = {"regression"})
    @Parameters({"feMemberLoginId", "feMemberLoginPwd", "language", "currency"})
    public void BO_Operations_Before_Login_Management_1660(String feMemberLoginId, String feMemberLoginPwd, String language, String currency) throws Exception {
        String type = "Header Menu";
        log("@title: Validate Currency show correctly");
        log("Step 1. Access Operations > Before Login Management");
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Filter with type = Header Menu for FairExchange");
        page.filter(type, "FairExchange", "", BOConstants.Operations.BannerManagement.POPUP_DDB_STATUS.get(0), true);
        String domain = page.ddpDomain.getFirstSelectedOption();
        log("Step 3. Get currency of current branch filter");
        String languageSelected = page.ddpLanguage.getFirstSelectedOption();
        memberLoginURL = defineURL("fairexchangeplus", MEMBER_URL_SUFFIX.get("fairexchangeplus"));
        log("Step 4. Login to member site");
        loginMember(feMemberLoginId, feMemberLoginPwd, false, language, currency, true);
        List<String> lst = landingPage.getBeforeLoginConfig(environment.getFairURL());

        log("Verify. Validate language between BO site and Member site before login matched");
        Assert.assertEquals(BOConstants.Operations.LANGUAGE.get(languageSelected), lst.get(1), "FAILED! Currency between BO and Before Login member site does not match, BO: "
                + languageSelected + " Member Site: " + lst.get(1));

        log("INFO: Executed completely");
    }
}
