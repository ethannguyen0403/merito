package backoffice.testcases.system;

import backoffice.common.BOConstants;
import backoffice.pages.bo.system.BetFairInfoPage;
import backoffice.pages.bo.system.PrioritySettingsPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;
import java.util.Map;

import static backoffice.common.BOConstants.System.PrioritySetting.*;
import static common.MemberConstants.LBL_SOCCER_SPORT;

public class PrioritySettingTest extends BaseCaseTest {
    /**
     * @title: Validate UI display correctly when access Priority Settings
     * @pre-condition: 1. Login BO
     * @steps: 1. Navigate to System> Priority Settings
     * 2. Select to type to verify UI
     * @expect: 1. Verify UI when select on Sport
     * 2. UI is display correctly when select Competition
     * 3. UI is display correctly when select Market
     * 4. UI is display correctly when select Country Races  and verify the list sports only contains Horse racing and Greyhound Racing
     */
    @TestRails(id = "660")
    @Test(groups = {"smoke"})
    public void BO_System_Priority_Settings_660() {
        log("@title: Validate UI display correctly when access Priority Settings");
        log("Step 1. Navigate to System> Priority Settings");
        PrioritySettingsPage page = backofficeHomePage.navigatePrioritySettings();

        log("Step 2. Select type to verify UI ");
        Assert.assertEquals(page.ddbType.getFirstSelectedOption().trim(), "Sport", "FAILED! Sport should be selected by default");

        log("Verify 1. Verify UI when select on Sport");
        Assert.assertTrue(page.ddbBrand.isDisplayed(), "FAILED! Brand dropdownbox does not display");
        Assert.assertEquals(page.lblNote.getText().trim(), BOConstants.System.PrioritySetting.NOTE, "FAILED! Note message does NOT match with the expectation");
        Assert.assertEquals(page.tblPriority.getColumnNamesOfTable(), BOConstants.System.PrioritySetting.SPORT_TABLE_HEADER, "FAILED! Table header when Sport is selected is incorrect");
        // Assert.assertEquals(page.txtSearch.getAttribute("placeholder"),BOConstants.System.PrioritySetting.SEARCH_SPORT,"FAILED! Search Sport textbox place holder is incorrect");


        log("Verify 2. UI is display correctly when select Competition");
        page.selectType("Competition");
        Assert.assertEquals(page.tblPriority.getColumnNamesOfTable(), BOConstants.System.PrioritySetting.COMPETITION_TABLE_HEADER, "FAILED! Table header when Competition is selected is incorrect");
        Assert.assertTrue(page.ddbBrand.isDisplayed(), "FAILED! Brand dropdownbox does not display");
        Assert.assertEquals(page.lblNote.getText().trim(), BOConstants.System.PrioritySetting.NOTE, "FAILED! Note message does NOT match with the expectation");
        Assert.assertEquals(page.txtSearch.getAttribute("placeholder"), BOConstants.System.PrioritySetting.SEARCH_COMPETITION, "FAILED! Search Competition textbox place holder is incorrect");

        log("Verify 3. UI is display correctly when select Market");
        page.selectType("Market");
        Assert.assertEquals(page.tblPriority.getColumnNamesOfTable(), BOConstants.System.PrioritySetting.MARKET_TABLE_HEADER, "FAILED! Table header when Market is selected is incorrect");
        Assert.assertTrue(page.ddbBrand.isDisplayed(), "FAILED! Brand dropdownbox does not display");
        Assert.assertEquals(page.lblNote.getText().trim(), BOConstants.System.PrioritySetting.NOTE, "FAILED! Note message does NOT match with the expectation");
        Assert.assertEquals(page.txtSearch.getAttribute("placeholder"), BOConstants.System.PrioritySetting.SEARCH_MARKET, "FAILED! Search Market textbox place holder is incorrect");


        log("Verify 4 UI is display correctly when select Country Races  and verify the list sports only contains Horse racing and Greyhound Racing");
        page.selectType("Country");
        Assert.assertEquals(page.tblPriorityCountryRace.getColumnNamesOfTable(), BOConstants.System.PrioritySetting.COUNTRY_RACES_TABLE_HEADER, "FAILED! Country Races headers are incorrect");
        Assert.assertTrue(page.ddbBrand.isDisplayed(), "FAILED! Brand dropdownbox does not display");
        Assert.assertEquals(page.lblNote.getText().trim(), BOConstants.System.PrioritySetting.NOTE, "FAILED! Note message does NOT match with the expectation");
        Assert.assertEquals(page.txtSearch.getAttribute("placeholder"), BOConstants.System.PrioritySetting.SEARCH_COUNTRY_RACES, "FAILED! Search Country Races textbox place holder is incorrect");
        Assert.assertTrue(page.ddbSport.areOptionsMatched(BOConstants.System.PrioritySetting.DDB_SPORT_COUNTRY_RACES), "FAILED! List sport in Sport dropdown is incorrect");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search Sport
     * @pre-condition: 1. Login BO
     * @steps: 1. Navigate to System> Priority Settings
     * 2. Select to type : Sport
     * 3. Input sport in search sport textbox: Racing
     * @expect: 1. The list display 2 sports contains search key : Horse Racing and Greyhound Racing
     */
    @TestRails(id = "661")
    @Test(groups = {"smoke"})
    public void BO_System_Priority_Settings_661() {
        log("@title: Validate can search Sport");
        log("Step 1. Navigate to System> Priority Settings");
        PrioritySettingsPage page = backofficeHomePage.navigatePrioritySettings();

        log("Step 2. Select to type : Sport");
        log("Step 3. Input sport in search sport textbox: Racing");
        page.search("Sport", "", "", "Racing");

        log("Verify 1. The list display 2 sports contains search key : Horse Racing and Greyhound Racing");
        List<String> lstSport = page.tblPriority.getColumn(page.colSportName, false);
        for (int i = 1; i < lstSport.size(); i++) {
            Assert.assertTrue(lstSport.get(i).contains("Racing"), String.format("FAILED! Sport Name is %s but search key is %s", lstSport.get(i), "Racing"));
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search Competition
     * @pre-condition: 1. Login BO
     * @steps: 1. Navigate to System> Priority Settings
     * 2. Select to type : Competition
     * 3. Select Brand and sport
     * 4. Input competition in search textbox
     * @expect: 1. Verify the competition display in the list
     */
    @TestRails(id = "662")
    @Test(groups = {"smoke"})
    public void BO_System_Priority_Settings_662() {
        log("@title: Validate can search Competition");
        log("Step 1. Navigate to System> Priority Settings");
        String competitionKey = "English";
        PrioritySettingsPage page = backofficeHomePage.navigatePrioritySettings();

        log("Step 2. Select to type : Competition ");
        log("Step 3. Select Brand and sport");
        log("Step 4. Input competition in search textbox");
        page.search("Competition", "", "Soccer", competitionKey);

        log("Verify 1. Verify the competition display in the list");
        List<String> lstCompeition = page.tblPriority.getColumn(page.colSportName, false);
        for (int i = 1; i < lstCompeition.size(); i++) {
            Assert.assertTrue(lstCompeition.get(i).contains(competitionKey), String.format("FAILED! Competition Name is %s but search key is %s", lstCompeition.get(i), "Racing"));
        }

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search Market
     * @pre-condition: 1. Login BO
     * @steps: 1. Navigate to System> Priority Settings
     * 2. Select to type : Market
     * 3. Select Brand
     * 4. Input Market type in the search Market Type textbox
     * @expect: 1. Verify the list market type display in the list
     */
    @TestRails(id = "663")
    @Test(groups = {"smoke"})
    public void BO_System_Priority_Settings_663() {
        log("@title: Validate can search Market");
        log("Step 1. Navigate to System> Priority Settings");
        String marketKey = "Asian Handicap";
        PrioritySettingsPage page = backofficeHomePage.navigatePrioritySettings();

        log("Step 2. Select to type : Market ");
        log("Step 3. Select Brand");
        log("Step 4. Input Market type in the search Market Type textbox");
        page.search("Market", "", "", marketKey);

        log("Verify 1. Verify the list market type display in the list");
        Assert.assertTrue(page.isMarketNameDisplay(marketKey));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search Country Races
     * @pre-condition: 1. Login BO
     * @steps: 1. Navigate to System> Priority Settings
     * 2. Select to type : Market
     * 3. Select Brand
     * 4. Input Market type in the search Market Type textbox
     * @expect: 1. Verify the list market type display in the list
     */
    @TestRails(id = "664")
    @Test(groups = {"smoke"})
    public void BO_System_Priority_Settings_664() {
        log("@title: Validate can search Country Races");
        log("Step 1. Navigate to System> Priority Settings");
        String countryKey = "Australia";
        PrioritySettingsPage page = backofficeHomePage.navigatePrioritySettings();

        log("Step 2. Select to type : Country");
        log("Step 3. Select Brand");
        log("Step 4. Input Country Racing in the search Country textbox");
        page.search("Country", "", "", countryKey);

        log("Verify 1. Verify the list country name display in the list");
        Assert.assertTrue(page.isCountryNameDisplay(countryKey));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate select Brand works
     * @pre-condition: 1. Login BO
     * @steps: 1. Navigate to System> Priority Settings
     * 2. Select to any type
     * 3.Click on Brand dropdown and click all Select all
     * 4. Click Unselect All
     * @expect: 1. Verify All brands are displayed on the dropdown box
     * 2. Verify All brands are disappeared on the dropdown box, Select brand text is display instead
     */
    @TestRails(id = "665")
    @Test(groups = {"regression"})
    public void BO_System_Priority_Settings_665() {
        log("@title: Validate select Brand works");
        log("Step 1. Navigate to System> Priority Settings");
        PrioritySettingsPage page = backofficeHomePage.navigatePrioritySettings();
        log("Step 2. Select to default type");
        log("Step 3. Select All Brand");
        page.ddbBrand.selectAll(true);
        List<String>  brandList = page.ddbBrand.getSelectedOptions();
        log("Verify 1. Verify the list country name display in the list");
        Assert.assertTrue(brandList.containsAll(BRANDS_LIST), "FAILED! Selected brands not equal to brands of dropdown");
        log("Step 4. Unselect all Brand");
        page.ddbBrand.deSelectAll(true);
        backofficeHomePage.waitSpinIcon();
        log("Verify 2. Verify All brands are disappeared on the dropdown box, Select brand text is display instead");
        Assert.assertEquals(page.ddbBrand.getText(), SELECT_BRAND, "FAILED! Unselect all brands not working");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search and select a brand
     * @pre-condition: 1. Login BO
     * @steps: 1. Navigate to System> Priority Settings
     * 2. Select to any type
     * 3.Click on Brand dropdown
     * 4. Input a  brand and select the filtered result
     * @expect: 1. Verify the brand is added
     */
    @TestRails(id = "666")
    @Parameters({"env"})
    @Test(groups = {"smoke"})
    public void BO_System_Priority_Settings_666(String env) {
        log("@title: Validate can search and select a brand");
        log("Step 1. Navigate to System> Priority Settings");
        PrioritySettingsPage page = backofficeHomePage.navigatePrioritySettings();

        log("Step 2. Select to any type");
        log("Step 3.Click on a Brand dropdown .e.g: FunSport101");
        log("Step 4.Input a brand and select the filtered result");
        //different data of STG from prod
        String brand = env.equalsIgnoreCase("green")? "FunSport": "FunSport101";
        page.search("Sport", brand, "", "");

        log("Verify 1. Verify the brand is added");
        List<String> lstSelectedOption = page.ddbBrand.getSelectedOptions();
        Assert.assertEquals(lstSelectedOption.get(0), brand, "FAILED! Selected brand is incorrect");
        Assert.assertEquals(lstSelectedOption.size(), 1, "FAILED! There are more than 1 brand selected while only select brand: " + brand);

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify sport priority on member site is correctly displayed
     * @pre-condition: 1. Login BO
     * @steps: 1. Navigate to System> Priority Settings
     * 2. Select to Sport type
     * 3. Select SAT Sport brand
     * 4. Get sports in top 3 priority
     * 5. Login member site and verify sport is correctly priority
     * @expect: 1. Verify the sport menu in main menu is correctly priority
     */
    @TestRails(id = "667")
    @Test(groups = {"regression"})
    @Parameters({"satMemberLoginID", "memberPassword"})
    public void BO_System_Priority_Settings_667(String satMemberLoginID, String memberPassword) throws Exception {
        log("@title: Verify sport priority on member site is correctly displayed");
        log("Step 1. Navigate to System> Priority Settings");
        PrioritySettingsPage pagePriority = backofficeHomePage.navigatePrioritySettings();
        log("Step 2: Select to Sport type");
        log("Step 3: Select SAT Sport brand");
        pagePriority.search(SPORT_TYPE, SAT_BRAND, "", "");
        log("Step 4: Get sports in top 3 priority");
        Map<Integer, List<String>> prioritySport = pagePriority.getPriorityOfSport();
        log("Step 5: Login member site of SAT");
        loginMember("satsport",satMemberLoginID,memberPassword);
        memberHomePage.waitMenuLoading();
        List<String> sportList = memberHomePage.leftMenu.getLeftMenuList();
        log("Verify 1: Verify the sport menu in main menu is correctly top 3 priority");
        pagePriority.verifyPriorityOfSport(sportList, prioritySport, 3);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Competition priority  on member site is correctly displayed
     * @pre-condition: 1. Login BO
     * @steps: 1. Navigate to System> Priority Settings
     * 2. Select to Sport type
     * 3. Select SAT Sport brand
     * 4. Get competition in top 3 priority
     * 5. Access member site before login SAT and verify competition is correctly priority
     * @expect: 1. Login member site and verify Competition of Soccer is correctly priority
     */
    @TestRails(id = "668")
    @Test(groups = {"regression"})
    @Parameters({"satMemberLoginID", "memberPassword"})
    public void BO_System_Priority_Settings_668(String satMemberLoginID, String memberPassword) throws Exception {
        log("@title: Validate Competition priority  on member site is correctly displayed");
        log("Step 1: Navigate to System> Priority Settings");
        PrioritySettingsPage pagePriority = backofficeHomePage.navigatePrioritySettings();
        log("Step 2: Select Competition type,  SAT Sport brand, Soccer");
        pagePriority.search(COMPETITION_TYPE, SAT_BRAND, LBL_SOCCER_SPORT, "");
        log("Step 4: Get sports in top 3 priority");
        Map<Integer, List<String>> prioritySport = pagePriority.getPriorityOfSport();
        log("Step 5: Login member site of SAT");
        loginMember("satsport",satMemberLoginID,memberPassword);
        memberHomePage.waitMenuLoading();
        log("Step 6: Get Competition in top 3 priority");
        memberHomePage.leftMenu.clickMenu(LBL_SOCCER_SPORT);
        List<String> competitionList = memberHomePage.leftMenu.getLeftMenuList();
        log("Verify 1: Verify the competition menu in main menu is correctly top 3 priority");
        pagePriority.verifyPriorityOfSport(competitionList, prioritySport, 3);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Market priority on member site is correctly displayed
     * @pre-condition: 1. Login BO
     * @steps: 1. Navigate to System> Priority Settings
     * 2. Select to Sport type
     * 3. Select SAT Sport brand
     * 4. Get market in top 3 priority
     * 5. Login member site and verify sport is correctly priority
     * @expect: 1. Verify left menu Market under Sport is prioritized
     */
    @TestRails(id = "669")
    @Parameters({"satMemberLoginID", "memberPassword"})
    @Test(groups = {"regression"})
    public void BO_System_Priority_Settings_669(String satMemberLoginID, String memberPassword) throws Exception {
        log("@title: Validate Market priority on member site is correctly displayed ");
        log("Step 1: Navigate to System> Priority Settings");
        PrioritySettingsPage pagePriority = backofficeHomePage.navigatePrioritySettings();
        log("Step 2: Select Market type,  SAT Sport brand, Soccer");
        pagePriority.search(MARKET_TYPE, SAT_BRAND, LBL_SOCCER_SPORT, "");
        log("Step 4: Get market in top 3 priority");
        Map<Integer, List<String>> prioritySport = pagePriority.getPriorityOfSport();
        log("Step 5: Login member site of SAT");
        loginMember("satsport",satMemberLoginID,memberPassword);
        memberHomePage.waitMenuLoading();
        log("Step 6: Get Competition in top 3 priority");
        memberHomePage.leftMenu.clickMenu(LBL_SOCCER_SPORT);
        List<String> marketList = memberHomePage.leftMenu.getLeftMenuList();
        log("Verify 1: Verify the Market menu in main menu is correctly top 3 priority");
        pagePriority.verifyPriorityOfSport(marketList, prioritySport, 3);
        log("INFO: Executed completely");
    }
}
