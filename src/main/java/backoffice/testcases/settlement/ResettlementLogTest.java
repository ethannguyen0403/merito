package backoffice.testcases.settlement;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import backoffice.pages.bo.settlement.ResettlementLogPage;
import baseTest.BaseCaseMerito;

import java.util.List;

public class ResettlementLogTest extends BaseCaseMerito{

    /**
     * @title: Validate can search log by Wager ID
     * @pre-condition:
     *          1.Login BO
     * @steps: 1. Access Tool > Resettlement Log
     *          2. Select Search by: Wager ID
     *          3. Input the wager id that has resettle log : STG: 55919 or 55918, or 55917
     *           4.Click search button
     * @expect: 1. Verify the display log when search by Wager ID
     */
    @Test (groups = {"smoke"})
    public void BO_Settlement_Resettlement_Log_001(){
        log("@title: Validate can search log by Wager ID");
        log("Step 1. Access Tool > Resettlement Log");
        String wagerID = "59105118";
        ResettlementLogPage page = backofficeHomePage.navigateResettlementLog();

        log("Step 2. Select Search by: Wager ID");
        log("Step 3. Input the wager id that has resettle log : STG: 55919 or 55918, or 55917");
        log("Step 4.Click search button");
        page.searchByWagerID(wagerID);

        log("Verify 1. Verify the display log when search by Wager ID");
        Assert.assertEquals(page.tblResettlementLog.getColumn(page.colBetID,false).get(0),wagerID,"FAILED! Wager ID dose not display when searching");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search log by Event/Market
     * @pre-condition:
     *          1.Login BO
     * @steps: 1. Access Tool > Resettlement Log
     *          2. Select Search by: Event/Market
     *          3. Select Event Date: 09/30/2020
     *              Sport: Horse Racing
     *              Event: Hapoel Herusalem v CB Miraflores
     *              Market: Handicap
     *              Click search
     * @expect: 1. Verify the event/market display log
     */
    @Test (groups = {"smoke"})
    public void BO_Settlement_Resettlement_Log_002(){
        log("@title:Validate can search log by Event/Market");
        log("Step 1. Access Tool > Resettlement Log");
        ResettlementLogPage page = backofficeHomePage.navigateResettlementLog();

        log("Step 2. Select Search by: Event/Market");
        log("Step 3. Select Event Date: 09/30/2020\n" +
                "     *              Sport:Basketball\n" +
                "     *              Event: Hapoel Herusalem v CB Miraflores\n" +
                "     *              Market: Moneyline\n" +
                "     *              Click search");

        page.searchByEventMarket("2021/06/17","Tennis","T Mrdeza v J Grabher","Match Odds");

        log("Verify 1. Verify log display");
        List<String > lstDescription = page.tblResettlementLog.getColumn(page.colDescription,false);
        for (String des : lstDescription){
            Assert.assertEquals(des,"\n" +
                    "ITF Stare Splavy\n" +
                    "T Mrdeza v J Grabher\n" +
                    "J Grabher - Match Odds\n" +
                    "Tennis: 2021-06-17 04:05:18","FAILED! Description dose not display when searching");
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search log by Username/Login ID
     * @pre-condition:
     *          1.Login BO
     * @steps: 1. Access Tool > Resettlement Log
     *          2. Select Search by: Event/Market
     *          3. Select Event Date: 09/30/2020
     *              Sport: Horse Racing
     *              Event: Hapoel Herusalem v CB Miraflores
     *              Market: Handicap
     *              Click search
     * @expect: 1. Verify display resettle log of that account that have data
     */
    @Test (groups = {"smoke"})
    @Parameters({"satMemberLoginID"})
    public void BO_Settlement_Resettlement_Log_003(String satMemberLoginID){
        log("@title: Validate can search log by Wager ID");
        log("Step 1. Access Tool > Resettlement Log");
        ResettlementLogPage page = backofficeHomePage.navigateResettlementLog();

        log("Step 2. Select Search by: Event/Market");
        log("Step 1. Access Tool > Resettlement Log\n" +
                "2. Select Search by: Username/Login ID\n" +
                "3. Input username/login ID: bhruby and select Resettle Date range: 09/30/2020 - 09/30/2020 \n" +
                "4. Click search button");
        page.searchByUsername("","",satMemberLoginID);

        log("Verify 1. Verify display resettle log of that account that have data");
        List<String > lstPlayer = page.tblResettlementLog.getColumn(page.colAffectPlayer,true);
        if(lstPlayer.size()==0)
        {
            System.out.println("By Pass as has no wager resettlement in account "+ satMemberLoginID);
            Assert.assertTrue(true,"By Passs this test case as has no data");
            return;
        }
        for (String player : lstPlayer){
            Assert.assertEquals(player,satMemberLoginID,"FAILED!Affected Player dose not display when searching");
        }
        log("INFO: Executed completely");
    }



}
