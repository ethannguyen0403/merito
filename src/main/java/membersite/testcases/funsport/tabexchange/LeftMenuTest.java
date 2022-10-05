package membersite.testcases.funsport.tabexchange;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.InPlayPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;

public class LeftMenuTest extends BaseCaseMerito {
    /**
     * @title: Validate In-play page not display left menu
     * @pre-condition
     *           1. Sign in
     * @steps:  1. Active Inplay page
     * @expect:  Verify left menu not display inplay page
     */
    @Test (groups = {"smoke"})
    public void LeftMenuTest_001(){
        log("@title: Validate In-play page not display left menu");
        log("Step 1. Active Inplay page");
        InPlayPage page = memberHomePage.navigateInPlay();

        log("Verify: left menu not display inplay page");
        Assert.assertFalse(page.isLeftMenuDisplay(),"FAILED! Left menu should not display in In-Play page");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Sport/Competition active on left menu is correct and sport page is correctly display
     * @pre-condition
     *           1. Sign in
     * @steps: 1. On Left menu, click on a sport
     * 2. On Left menu, click on a Competition
     * @expect: 1 Sport/Competition active on left menu is correct and sport page is correctly display
     */
    @Test (groups = {"smoke"})
    public void LeftMenuTest_002(){
        log("@title: Validate Market display corresponding when clicking on the left menu");
        log("Step 1. On Left menu, click on a sport");
        String sportName =  memberHomePage.getPopularSports().get(0);
        SportPage sportPage = memberHomePage.navigateSportPageFromLeftMenu(sportName);

        log("Verify 1: Sport active on left menu is correct and sport page is correctly display");
        Assert.assertEquals(sportPage.getSportActive(),sportName,"FAILED! Active sport in left menu is incorrect");
        Assert.assertEquals(sportPage.lblHeadingMarketName.getText(),sportName + " betting markets","FAILED Sport page is incorrect display");

        log("Step 2. On Left menu, click on a Competition");
        String competition = sportPage.getTopCompetition().get(0);
        sportPage = sportPage.navigateSportPageFromLeftMenu(competition);

        log("Verify 1: Competition active on left menu is correct and sport page is correctly display");
        Assert.assertEquals(sportPage.lblHeadingMarketName.getText(),competition + " online betting","FAILED Sport page is incorrect display");
        Assert.assertEquals(sportPage.getCompetitionActive(),competition,"FAILED! Active competition in left menu is incorrect");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate correct activate sport display in the left menu when click on sport menu
     * @pre-condition
     *           1. Sign in
     * @steps: 1. Click on any sport on main menu
     * @expect: Verify left menu display correct sport
     */
    @Test (groups = {"smoke"})
    public void LeftMenuTest_003(){
        log("@title: Validate In-play page not display left menu");
        log("Step 1. Click on any sport on main menu");
        SportPage page = memberHomePage.navigateCricket();

        log("Verify: Verify left menu display correct sport");
        Assert.assertEquals(page.getSportActive(),"Cricket","FAILED! Active Sport on the left menu is incorrect displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate left menu default display with 3 session: All sport, Popular, A-Z
     * @pre-condition
     *           1. Login member site
     * @steps:  1.Active home page
     * @expect: Validate left menu default display with 3 session: All sport, Popular, A-Z
     */
    @Test (groups = {"smoke"})
    public void LeftMenuTest_004(){
        log("@title:  Validate left menu default display with 3 session: All sport, Popular, A-Z");
        log("Step 1.Active home page");
        HomePage page = memberHomePage.navigateHomePage();

        log("Verify: Validate left menu default display with 3 session: All sport, Popular, A-Z");
        Assert.assertEquals(page.lblSportsHeader.getText().trim(),"Sports","FAILED! Left menu Sports section header is incorrect");
        Assert.assertEquals(page.lblPopularHeader.getText().trim(),"Popular","FAILED! Left menu Sports section header is incorrect");
        Assert.assertEquals(page.lblSportsHeader.getText().trim(),"A-Z","FAILED! Left menu Sports section header is incorrect");

        log("INFO: Executed completely");
    }

}
