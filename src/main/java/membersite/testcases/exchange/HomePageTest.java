package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.element.common.Label;
import common.MemberConstants;
import membersite.objects.sat.Event;
import membersite.pages.MarketPage;
import membersite.pages.MyLastLoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;
import java.util.Objects;

public class HomePageTest extends BaseCaseTest {

    @TestRails(id = "1057")
    @Test(groups = {"regression"})
    public void MB_Change_Password_TC1075() {
        log("@title:Validate can navigate to My Last Logins page");
        log("Step 1 Click My Account > My Last Logins");
        MyLastLoginPage myLastLoginPage = new MyLastLoginPage(_brandname);

        log("Verify 1. My Last Logins display in new tab with");
        Assert.assertEquals(MemberConstants.MyLastLogin.TITLE_PAGE, myLastLoginPage.getTitle(), "Failed! My last login page title is incorrect displayed");

        log("Verify 2. My Last Login label\n" +
                "-Table header: Login Date & Time, Login Status, IP Address, Device Info, Country");
        Assert.assertEquals(MemberConstants.MyLastLogin.TABLE_HEADES, myLastLoginPage.tblMyLastLogin.getColumnNamesOfTable(), "Failed! Table header is incorrect");

        log("INFO: Executed completely");
    }

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
        Assert.assertEquals(memberHomePage.leftMenu.getActiveEvent(), event.getEventName(), String.format("ERROR! Expected event in left menu is %s but found %s", event.getEventName(), memberHomePage.leftMenu.getActiveEvent()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "496")
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void HomePage_496(String skinName) {
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
    public void Home_page_TC1064() {
        //TODO: implement this case
        log("@title: Validate Cricket match odds will include fancy market");
        log("INFO: Executed Completely!");
    }


    @TestRails(id = "1065")
    public void Home_page_TC1065() {
        //TODO: implement this case
        log("@title: Validate Fancy market not display match odds market");
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1066")
    public void Home_page_TC1066() {
        //TODO: implement this case
        log("@title: Validate In-Play label display");
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1069")
    public void Home_page_TC1069() {
        //TODO: implement this case
        log("@title: Validate Sport tab work");
        log("INFO: Executed Completely!");
    }
}
