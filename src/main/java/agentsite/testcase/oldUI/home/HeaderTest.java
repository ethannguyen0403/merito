package agentsite.testcase.oldUI.home;

import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.home.AccountBalancePage;
import agentsite.ultils.account.ProfileUtils;

import java.util.List;

import static agentsite.common.AGConstant.*;

public class HeaderTest extends BaseCaseMerito {
    /**
     * @title: Validate menu list item display correctly
     * @steps:   1. Login successfully
     * 2. 1 Click on menu icon
     * @expect: Verify The list sub menu display:
     * - Password
     * - Security Code
     * - OTP
     */
    @Test (groups = {"regression"})
    public void Header_TC003(){
        log("@title: Validate menu list item display correctly");
        log("Step 1: Click on menu icon");
        List<String> lstMenuHeader = agentHomePage.ddlMenu.getMenuList();
        log("Verify: Verify The list sub menu display: Password, Security Code, OTP");
        Assert.assertEquals(lstMenuHeader,MENULIST,"FAILED! The list sub menu not display: Password, Security Code, OTP");
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate Account Balance page display when click on Home icon
     * @steps:   1. Login successfully
     * 2. Click on home icon
     * @expect: Account Balance page display with the title
     * example: Senior Admin Balance Info: SAI02
     * Available Balance
     * My Credit
     * My Outstanding
     * Total Outstanding
     * Today Win Loss
     * Yesterday Win Loss
     * Total Member Available Balance
     * Total Member Active/Closed/Suspended/Inactive
     */
    @Test (groups = {"regression"})
    public void Header_TC009(){
        log("@title: Validate Account Balance page display when click on Home icon");
        log("Step 1: Click on home icon");
        AccountInfo accountInfo = ProfileUtils.getProfile();
        String userCode = accountInfo.getUserCode();
        AccountBalancePage page = agentHomePage.clickHomeIcon();

        log("Verify 1: Verify Account  Balance page display after clicking on Home icon");
        String title = String.format("%s Balance Info : %s",accountInfo.getLevel(), userCode);
        Assert.assertEquals(page.lblPageTitle.getText(),title,"FAILED! Left menu does not expand by default");
        //TODO: Write test case to check Balance page data
        log("INFO: Executed completely");
    }


}
