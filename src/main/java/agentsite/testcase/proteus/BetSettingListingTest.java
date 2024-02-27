package agentsite.testcase.proteus;

import agentsite.pages.agentmanagement.BetSettingListingPage;
import baseTest.BaseCaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.MemberConstants.PS38;
import static common.ProteusConstant.PREGAME;

public class BetSettingListingTest extends BaseCaseTest {
    @TestRails(id = "4031")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4031(String directDownlineAccount) {
        log("@title: Validate can update Min Bet, Max Bet and Max Per Match of PS38 product for all sport successfully ");
        log("Step Precondition. Login Agent Site with SMA level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();

        log("Step 2. Select PS38 product and Sport as ALL > click Submit");
        page.filterPS38Product(directDownlineAccount,"","All",PS38,"ALL","");

        log("Step 3. Select an agency/player");
        log("Step 4: Enter valid Min Bet. Max Bet and Max Per Match > click Update");
        page.updateBetSettingPS38(directDownlineAccount,PREGAME,true,"2","5000","5000",true);

        log("Verify 1: Updated Min Bet, Max Bet and Max Per Match is displayed correctly on Bet Setting list");
        page.verifyBetSettingPS38UpdateSucceed(directDownlineAccount,"All","","2","5000","5000");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4032")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4032(String directDownlineAccount) {
        log("@title: Validate update bet setting with valid min bet Setting ");
        log("Step Precondition. Login Agent Site with SMA level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        log("Step 2. Select PS38 product and Sport as ALL > click Submit");
        page.filterPS38Product(directDownlineAccount,"","All",PS38,"ALL","");

        log("Step 3. Select an agency/player");
        log("Step 4: Enter valid Min Bet > click Update");
        page.updateBetSettingPS38(directDownlineAccount,PREGAME,true,"3",null,null,true);

        log("Verify 1: Updated Min Bet is displayed correctly on Bet Setting list");
        page.verifyBetSettingPS38UpdateSucceed(directDownlineAccount,"All","General","3",null,null);
        log("INFO: Executed completely");
    }
    @TestRails(id = "4033")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4033(String directDownlineAccount) {
        log("@title: Validate update bet setting with valid max bet Setting");
        log("Step Precondition. Login Agent Site with SMA level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        log("Step 2. Select PS38 product and Sport as ALL > click Submit");
        page.filterPS38Product(directDownlineAccount,"","All",PS38,"ALL","");

        log("Step 3. Select an agency/player");
        log("Step 4: Enter valid Max Bet > click Update");
        page.updateBetSettingPS38(directDownlineAccount,PREGAME,true,null,"4500",null,true);

        log("Verify 1: Updated Max Bet is displayed correctly on Bet Setting list");
        page.verifyBetSettingPS38UpdateSucceed(directDownlineAccount,"All","General",null,"4500",null);
        log("INFO: Executed completely");
    }
    
    @TestRails(id = "4034")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4034(String directDownlineAccount) {
        log("@title: Validate update bet setting with valid Max Per Match Setting");
        log("Step Precondition. Login Agent Site with SMA level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        log("Step 2. Select PS38 product and Sport as ALL > click Submit");
        page.filterPS38Product(directDownlineAccount,"","All",PS38,"ALL","");

        log("Step 3. Select an agency/player");
        log("Step 4: Enter valid Max Per Match > click Update");
        page.updateBetSettingPS38(directDownlineAccount,PREGAME,true,null,null,"5002",true);

        log("Verify 1: Updated Max Per Match is displayed correctly on Bet Setting list");
        page.verifyBetSettingPS38UpdateSucceed(directDownlineAccount,"All","General",null,null,"5002");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4035")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4035(String directDownlineAccount) {
        log("@title: Validate can update Min Bet, Max Bet and Max Per Match of PS38 product for specific sport with all League successfully");
        log("Step Precondition. Login Agent Site with SMA level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        log("Step 2. Select PS38 product");
        log("Step 3. Select sport as Soccer and All as League > click Submit");
        page.filterPS38Product(directDownlineAccount,"","All",PS38,"Soccer","General");

        log("Step 4:Select an agency/player");
        log("Step 5: Enter valid Min Bet. Max Bet and Max Per Match > click Update");
        page.updateBetSettingPS38(directDownlineAccount,PREGAME,true,"4","4560","4560",true);

        log("Verify 1: Updated Min Bet, Max Bet and Max Per Match is applied correctly for specific sport with all League correctly");
        page.verifyBetSettingPS38UpdateSucceed(directDownlineAccount,"Soccer","General","4","4560","4560");

        log("INFO: Executed completely");
    }
    @TestRails(id = "4036")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"directDownlineAccount"})
    public void PS38_Agent_TC4036(String directDownlineAccount) {
        log("@title: Validate can update Min Bet, Max Bet and Max Per Match of PS38 product for specific Sport and League successfully");
        log("Step Precondition. Login Agent Site with SMA level");
        log("Step 1. Click Bet Setting Listing");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();

        log("Step 2. Select PS38 product ");
        log("Step 3. Select sport as Soccer and any League not All > click Submit");
        page.filterPS38Product(directDownlineAccount,"","All",PS38,"Soccer","AFC - Cup");

        log("Step 4: Select an agency/player");
        log("Step 5: Enter valid Min Bet. Max Bet and Max Per Match > click Update");
        page.updateBetSettingPS38(directDownlineAccount,PREGAME,true,"1","5001","5001",true);

        log("Verify 1: Updated Min Bet, Max Bet and Max Per Match is applied correctly for specific sport and Leauge correctly");
        page.verifyBetSettingPS38UpdateSucceed(directDownlineAccount,"Soccer","AFC - Cup","1","5001","5001");

        log("INFO: Executed completely");
    }


}
