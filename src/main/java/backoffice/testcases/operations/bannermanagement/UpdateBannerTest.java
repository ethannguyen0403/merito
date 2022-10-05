package backoffice.testcases.operations.bannermanagement;

import com.paltech.utils.DateUtils;
import backoffice.common.BOConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import backoffice.pages.bo.operations.BannerManagementPage;
import backoffice.pages.bo.operations.component.UpdateBannerPopup;
import baseTest.BaseCaseMerito;

import java.util.ArrayList;
import java.util.List;

public class UpdateBannerTest extends BaseCaseMerito{
    /**
     * @title: Validate that loading this page  is successful
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Operations > Banner Management
     *           2. Click Update button of a banner
     * @expect:  1. Update Banner popup is displayed
     *           2. Data displays correctly
     */
    @Test (groups = {"regression"})
    public void BO_Operations_BannerManagement_Update_001(){
        log("@title: Validate that loading this page is successful");
        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        List<ArrayList<String>> lstRecord = page.tblBannerManagement.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstRecord.size(), 1, "ERROR: lstRecord.size() doesn't equal to 1");
        String sequence = lstRecord.get(0).get(page.colSequence-1);
        String validFromOnTable = DateUtils.formatDate(lstRecord.get(0).get(page.colValidFrom-1), BOConstants.DASH_DD_MM_YYYY, BOConstants.SLASH_DD_MM_YYYY);
        String validTillOnTable = DateUtils.formatDate(lstRecord.get(0).get(page.colValidTill-1), BOConstants.DASH_DD_MM_YYYY, BOConstants.SLASH_DD_MM_YYYY);

        log("Step 2: Click Update button of sequence " + sequence);
        UpdateBannerPopup popup = (UpdateBannerPopup)page.action(BannerManagementPage.Actions.UPDATE, sequence);

        log("Verify 1: Update Banner popup is displayed");
        log("Verify 2: Data displays correctly");
        Assert.assertTrue(popup.popupUpdateBanner.isDisplayed(), "ERROR: popupUpdateBanner is not displayed");
        String expectedPopupTitle = BOConstants.Operations.BannerManagement.POPUP_UPDATE_TITLE;
        String observed = popup.lblTitle.getText();
        String validFrom = popup.txtValidFrom.getAttribute();
        String validTo = popup.txtValidTo.getAttribute();
        validTo = DateUtils.getPreviousDate(validTo, BOConstants.SLASH_DD_MM_YYYY);
        boolean isStatusCorrectly = popup.ddbStatus.areOptionsMatched(BOConstants.Operations.BannerManagement.POPUP_DDB_STATUS);
        boolean isSequenceCorrectly = popup.ddbSequence.areOptionsMatched(BOConstants.Operations.BannerManagement.POPUP_DDB_SEQUENCE);
        Assert.assertEquals(expectedPopupTitle, observed, String.format("ERROR: The expected popup title is '%s' but found '%s'", expectedPopupTitle, observed));
        Assert.assertEquals(validFromOnTable, validFrom, String.format("ERROR: The expected valid from is '%s' but found '%s'", validFromOnTable, validFrom));
        Assert.assertEquals(validTillOnTable, validTo, String.format("ERROR: The expected valid from is '%s' but found '%s'", validTillOnTable, validTo));
        Assert.assertTrue(isStatusCorrectly, "ERROR: At least an item on Status ddb is not matched");
        Assert.assertTrue(isSequenceCorrectly, "ERROR: At least an item Sequence ddb is not matched");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Update Banner popup is closed when clicking Close button
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Operations > Banner Management
     *           2. Click Update Banner button
     *           3. Click Close button
     * @expect:  1. New Banner popup is closed successfully
     */
    @Test (groups = {"regression"})
    public void BO_Operations_BannerManagement_Update_002(){
        log("@title: Validate that New Banner popup is closed when clicking Close button");
        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        List<ArrayList<String>> lstRecord = page.tblBannerManagement.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstRecord.size(), 1, "ERROR: lstRecord.size() doesn't equal to 1");
        String sequence = lstRecord.get(0).get(page.colSequence-1);
        log("Step 2: Click Update button of sequence " + sequence);
        UpdateBannerPopup popup = (UpdateBannerPopup)page.action(BannerManagementPage.Actions.UPDATE, sequence);

        log("Step 3: Click Close button");
        popup.clickCloseBtn();

        log("Verify 1: Update Banner popup is closed successfully");
        Assert.assertTrue(popup.popupUpdateBanner.isInvisible(2), "ERROR: popupUpdateBanner is still displayed");
        log("INFO: Executed completely");
    }
}
