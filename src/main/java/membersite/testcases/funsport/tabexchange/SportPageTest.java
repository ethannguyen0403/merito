package membersite.testcases.funsport.tabexchange;

import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.RacingPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import util.testraildemo.TestRails;

public class SportPageTest extends BaseCaseMerito {
    /**
     * @title: Validate title is correct when clicking on Soccer
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Click on Soccer
     * @expect: 1. Verify title is sport betting markets
     *          2. Sport hightlighs section is correct
     */
    @TestRails(id = "505")
    @Test(groups = {"smoke"})
    public void SportPageTest_001(){
        log("@title: Validate title is correct when clicking on Soccer");

        log("Step1: 1. Click on Soccer");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step 1. Verify title is sport betting markets");
        Assert.assertEquals(page.lblHeadingMarketName.getText(),"Soccer betting markets","FAILED! Sport title page is incorrect");

        log("Step 2. Sport hightlighs section is corrects");
        Assert.assertEquals(page.lblHeadingSportName.getText(),"Soccer Highlights","FAILED! Sport title page is incorrect");

       log("INFO: Executed completely");
    }
    /**
     * @title: Validate title is correct when clicking on Cricket
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Click on Cricket
     * @expect: 1. Verify title is sport betting markets
     *          2. Sport hightlighs section is correct
     */
    @TestRails(id = "508")
    @Test(groups = {"smoke"})
    public void SportPageTest_002(){
        log("@title: Validate title is correct when clicking on Cricket");

        log("Step1: 1. Click on Cricket");
        SportPage page = memberHomePage.navigateCricket();

        log("Step 1. Verify title is sport betting markets");
        Assert.assertEquals(page.lblHeadingMarketName.getText(),"Cricket betting markets","FAILED! Sport title page is incorrect");

        log("Step 2. Sport hightlighs section is corrects");
        Assert.assertEquals(page.lblHeadingSportName.getText(),"Cricket Highlights","FAILED! Sport title page is incorrect");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate title is correct when clicking on Tennis
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Click on Tennis
     * @expect: 1. Verify title is sport betting markets
     *          2. Sport hightlighs section is correct
     */
    @TestRails(id = "506")
    @Test(groups = {"smoke"})
    public void SportPageTest_003(){
        log("@title: Validate title is correct when clicking on Tennis");

        log("Step1: 1. Click on Tennis");
        SportPage page = memberHomePage.navigateTennis();

        log("Step 1. Verify title is sport betting markets");
        Assert.assertEquals(page.lblHeadingMarketName.getText(),"Tennis betting markets","FAILED! Sport title page is incorrect");

        log("Step 2. Sport hightlighs section is corrects");
        Assert.assertEquals(page.lblHeadingSportName.getText(),"Tennis Highlights","FAILED! Sport highlights title is incorrect");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate title is correct when clicking on Horse Racing
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Click Horse Racing
     * @expect: 1. Verify title is sport betting markets
     *          2. Sport hightlighs section is correct
     */
    @TestRails(id = "507")
    @Test(groups = {"smoke"})
    public void SportPageTest_006(){
        log("@title: Validate title is correct when clicking on Horse Racing");
        String nextDate = DateUtils.getDate(2,"EEEE","GMT+7");

        log("Step1: 1. Click on Horse Racing");
        RacingPage page = memberHomePage.navigateHorseRacing();

        log("Step 1. Verify have nexrace session and 3 tab: Today, Tommorrow, and the next date");
        Assert.assertTrue(page.lblNextRace.getText().startsWith("Next Race"),"FAILED! Next Race title is incorrect");
        Assert.assertEquals(page.tabToday.getText(),"Today","FAILED! Today tab text is incorrect");
        Assert.assertEquals(page.tabTomorrow.getText(),"Tomorrow","FAILED! Today tab text is incorrect");
        Assert.assertEquals(page.tabNexDate.getText(),nextDate,"FAILED! Today tab text is incorrect");

        log("INFO: Executed completely");
    }

}
