package backoffice.testcases.system;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import backoffice.pages.bo.system.BetFairInfoPage;
import baseTest.BaseCaseMerito;

public class BetfairInformationTest extends BaseCaseMerito{
    /**
     * @title: Validate can search BF information according with Merito info
     * @pre-condition:
     *          1. Login BO
     * @steps:   1. Access System>Betfair Information
     *          2. Input merito bet id and select product Exchange
     *          3. Click Search button
     * @expect:  1. Verify Betfair Bet ID display according with merito bet id
     */
    @Test (groups = {"smoke"})
    @Parameters("env")
    public void BO_System_Betfair_Information_001(String env){
        log("@title: Validate can search BF information according with Merito info");
        String meritoBetID = "61366";
        String betFairBetID= "213840481572" ;
        if(env.equalsIgnoreCase("green"))
        {
            meritoBetID = "30136697";
            betFairBetID = "213342416253";
        }

        log("Step 1. Access System > Betfair Information");
        BetFairInfoPage page = backofficeHomePage.navigateBetFairInfo();

        log("Step 2. Input Merito bet id and select product Exchange");
        log("Step 3. Click Search button");
        page.searchInfo(meritoBetID,"Exchange");

        log("Verify 1. Verify Betfair Bet ID display according with merito bet id");
        Assert.assertEquals(page.lblMeritoBetID.getText(),meritoBetID,String.format("FAILED! Merito Bet ID not display as searching. Expected is %s but found %s",meritoBetID,page.lblMeritoBetID.getText()));
        Assert.assertEquals(page.lblBetFairBetID.getText(),betFairBetID,String.format("FAILED! BetFair Bet ID is not correct. Expected is %s but found %s", betFairBetID, page.lblBetFairBetID.getText()));

        log("INFO: Executed completely");
    }



}
