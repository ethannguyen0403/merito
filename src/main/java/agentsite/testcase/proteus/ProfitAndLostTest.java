package agentsite.testcase.proteus;

import agentsite.pages.report.ProfitAndLossPage;
import agentsite.pages.report.components.TransactionDetailsPopupPage;
import baseTest.BaseCaseTest;
import com.paltech.element.common.Label;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.*;

public class ProfitAndLostTest extends BaseCaseTest {

    @TestRails(id = "9458")
    @Parameters({"memberAccount"})
    @Test(groups = {"ps38", "Cash_out"})
    public void Agent_PS38_Report_Profit_And_Loss_9458(String memberAccount) {
        log("@title: Validate showing the 'Cashed Out' text link in the report pages of agent site");
        log("Precondition: Configurated 'Allow Cash Out' of PS39 product in agent site\n" +
                "Cashed out the bet in member site");
        log("Step 1. Navigate Report > Profit And Loss");
        ProfitAndLossPage page = agentHomePage.navigateProfitAndLossPage();

        log("Step 2.Filter date range that have settled bet and select PS38");
        // Cash out bet should use fixed data for verifying
        page.filter("","15/05/2024","15/05/2024",PS38);

        log("Step 3. Get summary data then Drill down to Member level and click on the user name");
        List<ArrayList<String>> dataLst = page.drilldownToLevel("Member", false);

        log("Step 4. Sum member result column and verify it match with total column");
        TransactionDetailsPopupPage transactionPopup =  page.openTransactionDetails(memberAccount);

        log("Verify 1: Verify showing the 'Cashed Out' text link");
        Assert.assertEquals(Label.xpath(transactionPopup.tblReport.getxPathOfCell(1, transactionPopup.colStatus, 1, "span")).getText(),
                CASHED_OUT, "FAILED! Link Cashed out is not displayed");
        log("Verify 2: The dialog shows risk amount, cash out amount and Date/Time to cash out");
        transactionPopup.openCashOutHistoryPopup(1).verifyCashOutDialogShowCorrect(dataLst, memberAccount);
        log("INFO: Executed completely");
    }
}
