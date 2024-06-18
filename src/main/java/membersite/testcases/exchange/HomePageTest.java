package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.element.common.Label;
import common.MemberConstants;
import membersite.objects.sat.Event;
import membersite.pages.AccountStatementPage;
import membersite.pages.MarketPage;
import membersite.pages.MyLastLoginPage;
import membersite.pages.SportPage;
import membersite.pages.components.changepasswordpopup.ChangePasswordPopup;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;
import java.util.Objects;

import static common.MemberConstants.*;
import static common.MemberConstants.Header.MY_BETS;

public class HomePageTest extends BaseCaseTest {

//    @TestRails(id = "1057")
//    @Test(groups = {"regression"})
//    public void MB_Change_Password_TC1075() {
//        log("@title:Validate can navigate to My Last Logins page");
//        log("Step 1 Click My Account > My Last Logins");
//        MyLastLoginPage myLastLoginPage = new MyLastLoginPage(_brandname);
//
//        log("Verify 1. My Last Logins display in new tab with");
//        Assert.assertEquals(MemberConstants.MyLastLogin.TITLE_PAGE, myLastLoginPage.getTitle(), "Failed! My last login page title is incorrect displayed");
//
//        log("Verify 2. My Last Login label\n" +
//                "-Table header: Login Date & Time, Login Status, IP Address, Device Info, Country");
//        Assert.assertEquals(MemberConstants.MyLastLogin.TABLE_HEADES, myLastLoginPage.tblMyLastLogin.getColumnNamesOfTable(), "Failed! Table header is incorrect");
//
//        log("INFO: Executed completely");
//    }

    @TestRails(id = "1075")
    @Test(groups = {"regression"})
    @Parameters({"password"})
    public void HomePage_1075() {
        log("@title:Validate Greyhound Racing link works");
        log("Step 1.In home page - Next Up Racing section");
        memberHomePage.clickProduct(MemberConstants.EXCHANGE);

        log("Step 2.Click on any Greyhound racing link");
        memberHomePage.header.clickMainMenu("Home");
        MarketPage marketPage = memberHomePage.clickFristNextUpHR();
        if (Objects.isNull(marketPage)) {
            log("Skip TC as have no next up racing for Greyhound");
            return;
        }

        log("Verify 1. Racing market page display correctly. Country, market start time, market name is corrected");
        Assert.assertEquals(marketPage.marketOddControl.getTitle(), "", "Failed! Market page is incorrect");

        log("INFO: Executed completely");
    }

    @TestRails(id = "494")
    @Test(groups = {"smoke"})
    public void HomePage_494() {
        log("@title: Validate that can search event name in search textbox");
        log("Step 1. Get any event name of the sport has data");
        Event event = memberHomePage.eventContainerControl.getEvent(false, false, 20, 1);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }

        log("Step 2. Input event name in search textbox");
        Label lblResult = memberHomePage.leftMenu.searchEvent(event.getEventName());
        String searchResult = lblResult.getText();

        log("Step 3. Click on the event in the search list");
        lblResult.click();

        log("Verify 1. Event display in  the search list");
        Assert.assertEquals(searchResult, event.getEventName(), String.format("ERROR! Can not search event"));

        log("Verify 2. Left menu display event including it's market");
        Assert.assertEquals(memberHomePage.leftMenu.getActiveEvent(), event.getEventName(),
                String.format("ERROR! Expected event in left menu is %s but found %s", event.getEventName(),
                        memberHomePage.leftMenu.getActiveEvent()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "1054")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void HomePage_1054() {
        log("@title: Validate cannot search sport/competition/market in search textbox");
        log("Step 1: Get any sport that have event available");
        String searchResult = "";
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        Event event = sportPage.getEvent(false, false, 1, 1);
        if (Objects.isNull(event)) {
            throw new SkipException("DEBUG: There is no event available");
        }
        log("Step 2: Input sport and observe result list");
        searchResult = memberHomePage.leftMenu.searchEvent(LBL_CRICKET_SPORT).getText().trim();
        log("Verify 1: No result found display when search sport invalid input value");
        Assert.assertEquals(searchResult, NO_RESULTS_FOUND, "FAILED! Result does not match");

        log("Step 3: Input Competition and observe result list");
        searchResult = memberHomePage.leftMenu.searchEvent(event.getCompetitionName()).getText().trim();
        log("Verify 2: No result found display when search Competition invalid input value");
        Assert.assertEquals(searchResult, NO_RESULTS_FOUND, "FAILED! Result does not match");

        log("Step 4: Input Market and observe result list");
        searchResult = memberHomePage.leftMenu.searchEvent(event.getMarketName()).getText().trim();
        log("Verify 3: No result found display when search Market invalid input value");
        Assert.assertEquals(searchResult, NO_RESULTS_FOUND, "FAILED! Result does not match");
        log("INFO: Executed completely");
    }

    @TestRails(id = "1055")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void HomePage_1055() {
        log("@title: Validate Left menu display all sport available when active Home/In play menu");
        log("Step 1: Click on Home menu");
        List<String> sportsLeftMenuList = memberHomePage.leftMenu.getLeftMenuList();
        List<String> sportsHeaderList = memberHomePage.getListHeaderMenu();
        log("Verify 1: Sports display all sports");
        Assert.assertTrue(sportsLeftMenuList.containsAll(sportsHeaderList), "FAILED! Sports is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "1056")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void HomePage_1056() {
        log("@title: Validate Left menu navigation is correctly");
        List<String> eventList;
        List<String> competitionList;
        log("Step 1: Click on any sport then get it's competitions");
        memberHomePage.leftMenu.waitMenuLoading();
        memberHomePage.leftMenu.clickSport(LBL_TENNIS_SPORT);
        competitionList = memberHomePage.leftMenu.getLeftMenuList();
        if (Objects.isNull(competitionList)) {
            throw new AssertionError("FAILED! List of left menu is null");
        }
        log("Step 2: Click on any competition and get event list");
        memberHomePage.leftMenu.clickCompetition(0);
        eventList = memberHomePage.leftMenu.getLeftMenuList();
        log("Step 3: Click on any event and get market list");
        memberHomePage.leftMenu.clickEvent(0);
        log("Step 4: Click on any market");
        memberHomePage.leftMenu.clickMarket(memberHomePage.leftMenu.getMarketList().get(0));
        log("Step 5: Click on select competition at  step 2");
        memberHomePage.leftMenu.clickCompetition(0);
        log("Verify 1: At step 5 verify only event list display");
        Assert.assertEquals(eventList, memberHomePage.leftMenu.getLeftMenuList(), "FAILED! NOT only event list display not correct");
        log("Step 6: Click on the sport again");
        memberHomePage.leftMenu.clickSport(LBL_TENNIS_SPORT);
        log("Verify 2: At step 6 verify only competition display as step 1");
        Assert.assertEquals(competitionList, memberHomePage.leftMenu.getLeftMenuList(),
                "FAILED! NOT only competition list display not correct");
        log("INFO: Executed completely");
    }

    @TestRails(id = "1058")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void HomePage_1058() {
        log("@title: Validate can open Change Password popup (by close button)");
        log("Step 1: Click My Account > Change Password");
        memberHomePage.waitMenuLoading();
        ChangePasswordPopup popup = memberHomePage.header.openChangePasswordPopup();
        log("Verify 1: Update Password popup display:\n" +
                "Update Password header\n" +
                "\n" +
                "New Password, New Password Confirmation, Current Password textbox, Close, Save Change button");
        popup.verifyChangePasswordUI();
        log("Step 2: Click Close button to close the popup");
        popup.closePopup();
        log("Verify 2: Change password popup disappear");
        Assert.assertFalse(popup.isDisplayed(), "Failed! The popup is display after click close button");
        log("INFO: Executed completely");
    }

    @TestRails(id = "1060")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void HomePage_1060() {
        log("@title: Validate Account Statement, My Bets, Profit & Loss, My Last Login is active in the same tab ");
        log("Step 1: Click My Account > Account Statement");
        memberHomePage.waitMenuLoading();
        memberHomePage.header.openAccountStatement(_brandname);
        log("Verify 1: Account Statement display in new tab");
        memberHomePage.header.verifyPageOpenInNewTab("account-statement", true);
        log("Step 2: Click My Account > My Bets");
        memberHomePage.header.openMyBets(_brandname);
        log("Verify 2: My Bets display in new tab");
        memberHomePage.header.verifyPageOpenInNewTab("my-bet", true);
        log("Step 3: Click My Account > Profit & Loss");
        memberHomePage.header.openProfitAndLoss(_brandname);
        log("Verify 3: Profit & Loss display in new tab");
        memberHomePage.header.verifyPageOpenInNewTab("profit-loss", true);
        log("Step 4: Click My Account > My Last Login");
        memberHomePage.header.openMyLastLogins(_brandname);
        log("Verify 4: My Last Login display in new tab");
        memberHomePage.header.verifyPageOpenInNewTab("user-activities", true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "1061")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void HomePage_1061() {
        log("@title: Validate Home page display when clicking on the logo");
        log("Step 1: Click My Account > Account Statement");
        AccountStatementPage accountStatementPage = memberHomePage.header.openAccountStatement(_brandname);
        log("Step 2: Click Logo Image");
        accountStatementPage.homeIcon.click();
        log("Verify 1: Home page is displayed");
        Assert.assertEquals(memberHomePage.header.getMyBetsLabel(), MY_BETS, "FAILED! Home page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "496")
    @Test(groups = {"smoke", "nolan_stabilize"})
    public void HomePage_496() {
        log("@title: Validate can collapse/expand left menu");
        log("Step 1. Click on Menu icon in the top left corner");
        memberHomePage.expandLeftMenu();

        log("Verify 1. Left menu is expanded");
        Assert.assertTrue(memberHomePage.isLeftMenuDisplay(), String.format("ERROR! Left menu is not display when expanding left menu"));

        log("Step 2. Re-click on menu icon");
        memberHomePage.collapsedLeftMenu();

        log("Verify 1. Left menu is collapsed");
        Assert.assertTrue(memberHomePage.isLeftMenuDisplay(), String.format("ERROR! Menu display when collapsing left menu"));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Left menu display correct sport when active a sport
     * @pre-condition 1. Login member site
     * @steps: 1. Click on the sport in main menu
     * @expect: 1. Left menu display corresponding sport
     */
    @TestRails(id = "497")
    @Test(groups = {"regression"})
    public void HomePage_497() {
        log("@title: Validate Left menu display correct sport when active a sport");
        log("Step 1. Click on Exchange product");
        memberHomePage.clickProduct(MemberConstants.EXCHANGE);
        log("Step 2. Click on Soccer on header main menu");
        memberHomePage.header.clickMainMenu("Soccer");

        log("Verify 1 Left menu active Soccer sport");
        Assert.assertEquals(memberHomePage.leftMenu.getActiveSport(), "Soccer", "Failed! Active sport is incorrect");

        log("Step 3. Click on Tennis on header main menu");
        memberHomePage.header.clickMainMenu("Tennis");

        log("Verify 2 Left menu active Tennis sport");
        Assert.assertEquals(memberHomePage.leftMenu.getActiveSport(), "Tennis", "Failed! Active sport is incorrect");

        log("Step 4. Click on  Cricket on header main menu");
        memberHomePage.header.clickMainMenu("Cricket");

        log("Verify 3 Left menu active Cricket sport");
        Assert.assertEquals(memberHomePage.leftMenu.getActiveSport(), "Cricket", "Failed! Active sport is incorrect");

        log(" INFO: Executed Completely!");
    }

    @TestRails(id = "498")
    @Test(groups = {"regression"})
    public void FE_LeftMenu_TC498() {
        log("@title: Validate odds section is active when clicking on market");
        log("Step 1.Expand the left menu and select a sport > competition > Event ");
        memberHomePage.leftMenu.clickSport("Soccer");
        memberHomePage.leftMenu.clickCompetition("");
        String eventName = memberHomePage.leftMenu.getActiveEvent();
        memberHomePage.leftMenu.clickEvent(0);

        log("Step 2. Click on any market ");
        String marketName = memberHomePage.leftMenu.getLeftMenuList().get(0);
        String marketTitle = String.format("%s/ %s", eventName, marketName);
        MarketPage marketPage = memberHomePage.clickMarketonLeftMenu(marketName);

        log("Verify 1. Market page display with correct event name and market name");
        Assert.assertEquals(marketPage.marketOddControl.getTitle(), marketTitle, "ERROR! Market title does not match with the expected");
        log("INFO:Executed Completely!");
    }

    @TestRails(id = "499")
    @Test(groups = {"regression"})
    public void FE_LeftMenu_TC499() {
        log("@title: Validate Market display corresponding when clicking on the left menu");
        log("Step  1. Active any market of the Cricket");
        log("Step  2. On Left menu, click on the market list");
        memberHomePage.leftMenu.clickSport("Cricket");
        memberHomePage.leftMenu.clickCompetition("");
        String eventName = memberHomePage.leftMenu.getActiveEvent();
        memberHomePage.leftMenu.clickEvent(0);
        List<String> marketList = memberHomePage.leftMenu.getLeftMenuList();
        log("Verified  1. Market title displays with the corresponding with the left menu");
        for (int i = 0, n = marketList.size(); i < n; i++) {
            MarketPage marketPage = memberHomePage.clickMarketonLeftMenu(marketList.get(i));
            String marketTitle = String.format("%s/ %s", eventName, marketList.get(i));
            Assert.assertEquals(marketPage.marketOddControl.getTitle(), marketTitle, String.format("Failed! Market Page is incorrect"));
        }
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1064")
//    @Test(groups = {"interaction"})
    public void Home_page_TC1064() {
        //TODO: implement this case
        log("@title: Validate Cricket match odds will include fancy market");
        log("INFO: Executed Completely!");
    }


    @TestRails(id = "1065")
//    @Test(groups = {"interaction"})
    public void Home_page_TC1065() {
        //TODO: implement this case
        log("@title: Validate Fancy market not display match odds market");
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1066")
//    @Test(groups = {"interaction"})
    public void Home_page_TC1066() {
        //TODO: implement this case
        log("@title: Validate In-Play label display");
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1069")
//    @Test(groups = {"interaction"})
    public void Home_page_TC1069() {
        //TODO: implement this case
        log("@title: Validate Sport tab work");
        log("INFO: Executed Completely!");
    }
}
