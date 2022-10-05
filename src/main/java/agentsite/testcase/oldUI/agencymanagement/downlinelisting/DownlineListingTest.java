package agentsite.testcase.oldUI.agencymanagement.downlinelisting;
import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DownLineListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.DOWNLINE_LISTING;

public class DownlineListingTest extends BaseCaseMerito {
    /**
     * @title: Validate can search downline with Username
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     *          2. Input a Username exist indirect/direct downline
     * @expect: 1. Corresponding account display in the list
     */
    @Test (groups = {"smoke"})
    @Parameters("level")
    public void Agent_AM_Downline_Listing_006(String level) {
        log("@title: Validate can search downline with Username ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,level);
        String loginID =listAccount.get(0).getLoginID();

        log("Step 2. Input a Username exist indirect/direct downline");
        page.searchDownline(loginID,"","");

        log("Verify 1. Corresponding account display in the list");
        int totalRow = page.tblDowlineListing.getNumberOfRows(false,false);
        List<String> lstRecord = page.tblDowlineListing.getColumn(page.loginIDCol,false);
        Assert.assertEquals(totalRow,1,String.format("Failed!There are more than 1 records when search login ID %s",loginID));
        Assert.assertEquals(lstRecord.get(0),loginID,String.format("Failed! Expected login id %s display but found %s",loginID,lstRecord.get(0)));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can change password from the table
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     *         2. Select agent account in any level
     *         3. Click change password icon
     *         4. Update password
     * @expect: 1. Verify can change password successfully
     */
    @Test (groups = {"smoke"})
    @Parameters({"level","password"})
    public void Agent_AM_Downline_Listing_020(String level,String password) throws Exception {
        log("@title: Validate can change password from the table");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,level);
        String loginID =listAccount.get(0).getLoginID();

        log("Step 2. Select agent account in any level");
        log("Step 3. Click change password icon");
        log("Step 4. Update password");
        try {
            String message = page.changePassword(loginID, "1234qwert");
            log("Verify 1. Verify can change password successfully");
            Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_CHANGE_PASSWORD_SUCCESS, "FAILED, Success message is incorrect when updating password");
            log("INFO: Executed completely");
        }finally {
            log("Post Condition: Re-change to old pw");
            page.changePassword(loginID, StringUtils.decrypt(password));
        }


    }

}

