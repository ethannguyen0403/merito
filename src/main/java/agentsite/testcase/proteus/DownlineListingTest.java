package agentsite.testcase.proteus;

import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import static common.AGConstant.*;
import static common.AGConstant.AgencyManagement.CreateCompany.*;


import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.HashMap;
import java.util.Map;

public class DownlineListingTest extends BaseCaseTest {

    @TestRails(id = "4039")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"downlineAccount"})
    public void PS38_Agent_TC4039(String downlineAccount)  {
        log("@title: Validate can update PT setting of PS38 product for all sports successfully on Edit Downline Listing ");
        log("Precondition: Login Agent Site with PO level");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button");
        page.searchDownline(downlineAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(downlineAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Position Taking");
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);
        editPage.editDownlineListing.positionTakingSectionPS38.expandPositionSection(true);
        log("Step 4: Select Sport as All and click View");
        editPage.editDownlineListing.positionTakingSectionPS38.addSport("All", "");
        log("Step 5: Update PT for any market of Soccer sport then click Submit");
        String positionValue = StringUtils.generateNumeric(10,20);
        Map<String, String> betType = new HashMap<>();
        betType.put("Full time", "1X2");

        editPage.editDownlineListing.positionTakingSectionPS38.updatePTMarket("Soccer", betType, PREGAME_TAB_PS38, "Preset", positionValue);
        page.submitEditDownlinePS38(true);

        log("Verify 1: Updated PT is applied correctly for all sports");
        betType.put("Full time", "ML");
        page.clickEditIcon(downlineAccount, true);
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);
        editPage.editDownlineListing.positionTakingSectionPS38.expandPositionSection(true);
        editPage.editDownlineListing.positionTakingSectionPS38.expandSport("Baseball", true);
        Assert.assertEquals(editPage.editDownlineListing.positionTakingSectionPS38.getDropDownPTControl("Baseball", betType, PREGAME_TAB_PS38, "Preset", "select")
                .getFirstSelectedOption(), positionValue, "FAILED! PT of others sports is not updated accordingly");
    }

    @TestRails(id = "4040")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"downlineAccount"})
    public void PS38_Agent_TC4040(String downlineAccount)  {
        log("@title: Validate can update PT setting of PS38 product for specific sport with all League successfully on Edit Downline Listing");
        log("Precondition: Login Agent Site with PO level");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button");
        page.searchDownline(downlineAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(downlineAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Position Taking");
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);
        editPage.editDownlineListing.positionTakingSectionPS38.expandPositionSection(true);
        log("Step 4: Select Sport as Soccer and and League is General and click View");
        editPage.editDownlineListing.positionTakingSectionPS38.addSport("Soccer", "General");
        log("Step 5: Update PT for any market of Soccer sport then click Submit");
        String positionValue = StringUtils.generateNumeric(10,20);
        Map<String, String> betType = new HashMap<>();
        betType.put("Full time", "1X2");

        editPage.editDownlineListing.positionTakingSectionPS38.updatePTMarket("Soccer", betType, PREGAME_TAB_PS38, "Preset", positionValue);
        page.submitEditDownlinePS38(true);

        log("Verify 1: Updated PT is applied correctly for all sports");
        page.clickEditIcon(downlineAccount, true);
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);
        editPage.editDownlineListing.positionTakingSectionPS38.expandPositionSection(true);
        editPage.editDownlineListing.positionTakingSectionPS38.expandSport("Soccer", true);
        Assert.assertEquals(editPage.editDownlineListing.positionTakingSectionPS38.getDropDownPTControl("Soccer", betType, PREGAME_TAB_PS38, "Preset", "select")
                .getFirstSelectedOption(), positionValue, "FAILED! PT of Soccer sports is not updated accordingly");
    }

    @TestRails(id = "4041")
    @Test(groups = {"ps38", "Proteus.2024.V.1.0"})
    @Parameters({"downlineAccount"})
    public void PS38_Agent_TC4041(String downlineAccount)  {
        log("@title: Validate can update PT setting of PS38 product for specific Sport and League successfully on Edit Downline Listing");
        log("Precondition: Login Agent Site with PO level");
        log("Step 1: Access Agent > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        log("Step 2: Filter with any player and click Edit button");
        page.searchDownline(downlineAccount, "", "");
        EditDownLinePage editPage = page.clickEditIcon(downlineAccount, true);
        log("Step 3: Click on PS38 product and scroll down to Position Taking");
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);
        editPage.editDownlineListing.positionTakingSectionPS38.expandPositionSection(true);
        editPage.editDownlineListing.positionTakingSectionPS38.addSport("Soccer", "1");
        String leagueName = editPage.editDownlineListing.positionTakingSectionPS38.ddbLeague.getFirstSelectedOption();
        log("Step 4: Select Sport as Soccer and and League is " +  leagueName +" and click Adđ");
        editPage.editDownlineListing.positionTakingSectionPS38.btnView.click();
        log("Step 5: Update PT for any market of " + leagueName + " sport then click Submit");
        String positionValue = StringUtils.generateNumeric(10,20);
        Map<String, String> betType = new HashMap<>();
        betType.put("Full time", "1X2");

        editPage.editDownlineListing.positionTakingSectionPS38.updatePTMarket(leagueName, betType, PREGAME_TAB_PS38, "Preset", positionValue);
        page.submitEditDownlinePS38(true);

        log("Verify 1: Updated PT is applied correctly for all sports");
        page.clickEditIcon(downlineAccount, true);
        editPage.editDownlineListing.productStatusSettingInforSection.selectProduct(PS38);
        editPage.editDownlineListing.positionTakingSectionPS38.expandPositionSection(true);
        editPage.editDownlineListing.positionTakingSectionPS38.expandSport(leagueName, true);
        Assert.assertEquals(editPage.editDownlineListing.positionTakingSectionPS38.getDropDownPTControl(leagueName, betType, PREGAME_TAB_PS38, "Preset", "select")
                .getFirstSelectedOption(), positionValue, "FAILED! PT of Soccer sports is not updated accordingly");
    }


    @TestRails(id = "4059")
    @Test(groups = {"ps38"})
    @Parameters({"memberAccount","password"})
    public void PS38_Agent_TC4059(String memberAccount,String password) throws Exception {
        log("@title: Validate PS38 product does not display in member site if the product is inactive");
        log("Precondition: The agent account that has the currency is supported PS38 product");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("The agent the not active PS38 product for his downline");
        page.searchDownline(memberAccount,"","");

        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);
        editDownLinePage.inActiveProduct("PS38",true);

        log("Step 1. Login Member site the account under agent in precondition");
        log("Step 2. Observe product name");
        loginMember(memberAccount, password);

        log("Verify 1: Verify the product PS38 does not display");
        Assert.assertFalse(memberHomePage.isProductDisplayed("PS38"), "ERROR! PS38 product should not display as expected");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4060")
    @Test(groups = {"ps38"})
    @Parameters({"memberAccount","password"})
    public void PS38_Agent_TC4060(String memberAccount,String password) throws Exception {
        log("@title: Validate PS38 product displays in member site if the product is active");
        log("Precondition: The agent account that has the currency is supported PS38 product");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("The agent active PS38 product for his downline agent/ player");
        page.searchDownline(memberAccount,"","");
        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.clickEditIcon(memberAccount);
        editDownLinePage.activeProduct("PS38",true);

        log("Step 1. Login Member site the account under agent in precondition");
        log("Step 2. Observe product name");
        loginMember(memberAccount, password);

        log("Verify 1: Verify the product PS38 displays");
        Assert.assertTrue(memberHomePage.isProductDisplayed("PS38"), "ERROR! PS38 product should display as expected");

        log("INFO: Executed completely");
    }

}

