package backoffice.testcases.proteus.reports;

import backoffice.pages.bo.reports.WinLossDetailPage;
import backoffice.pages.bo.reports.component.TransactionDetailsPopup;
import baseTest.BaseCaseTest;
import com.paltech.element.common.Label;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.AGConstant.*;
import static common.AGConstant.Report.ProfitAndLoss.*;

public class WinLossDetailPS38Test extends BaseCaseTest {
    @TestRails(id = "9459")
    @Parameters({"memberAccount"})
    @Test(groups = {"ps38", "Cash_out"})
    public void BO_Report_WinLossDetail_PS38_9459(String memberAccount) {
        log("@title: Validate showing the 'Cashed Out' text link in the report pages of BO site ");
        log("@Precondition: Configurated 'Allow Cash Out' of PS39 product in agent site\n" +
                "Cashed out the bet in member site");
        log("Step 1: In BO site, navigate to win loss detail page");
        WinLossDetailPage page = backofficeHomePage.navigateWinLossDetails();

        log("Step 2. Filter bet data at pre-condition");
        // Cash out bet should use fixed data for verifying
        page.filter("15/05/2024", "15/05/2024", PS38, ALL, ALL);

        log("Step 3. Open transaction detail");
        TransactionDetailsPopup detailsPopup = page.openTransactionDetail(memberAccount);

        log("Verify 1: Showing the 'Cashed Out' text link");
        Assert.assertEquals(Label.xpath(detailsPopup.tblReport.getxPathOfCell(1, detailsPopup.colStatus, 1, null)).getText(), CASHED_OUT, "FAILED! Cash out link is not displayed" );

        log("Verify 2: The dialog shows risk amount (F), cash out amount (L), cash out amount (F)  and Date/Time to cash out");
        Assert.assertEquals(detailsPopup.tblReport.getHeaderNameOfRows(), HEADER_CASH_OUT_HISTORY_DIALOG_BO, "FAILED! Header of Cash out history dialog is not correct");

        log("INFO: Executed completely");
    }
}
