package backoffice.testcases.operations.bannermanagement;

import backoffice.common.BOConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import backoffice.pages.bo.operations.BannerManagementPage;
import backoffice.pages.bo.operations.component.NewBannerPopup;
import baseTest.BaseCaseMerito;

public class NewBannerTest extends BaseCaseMerito{
    /**
     * @title: Validate that this page loading is successful
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Operations > Banner Management
     *           2. Click Create Banner button
     * @expect:  1. New Banner popup is displayed
     *           2. Data displays correctly
     */
    @Test (groups = {"regression"})
    public void BO_Operations_BannerManagement_001(){
        log("@title: Validate that this page loading is successful");
        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2: Click Create Banner button");
        NewBannerPopup popup = page.createNewBanner();

        log("Verify 1: New Banner popup is displayed");
        log("Verify 2: Data displays correctly");
        Assert.assertTrue(popup.popupNewBanner.isDisplayed(), "ERROR: popupNewBanner is not displayed");
        String expectedPopupTitle = BOConstants.Operations.BannerManagement.POPUP_CREATE_TITLE;
        String observed = popup.lblTitle.getText();
        boolean isStatusCorrectly = popup.ddbStatus.areOptionsMatched(BOConstants.Operations.BannerManagement.POPUP_DDB_STATUS);
        boolean isSequenceCorrectly = popup.ddbSequence.areOptionsMatched(BOConstants.Operations.BannerManagement.POPUP_DDB_SEQUENCE);
        Assert.assertEquals(expectedPopupTitle, observed, String.format("ERROR: The expected popup title is '%s' but found '%s'", expectedPopupTitle, observed));
        Assert.assertTrue(isStatusCorrectly, "ERROR: At least an item on Status ddb is not matched");
        Assert.assertTrue(isSequenceCorrectly, "ERROR: At least an item Sequence ddb is not matched");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that New Banner popup is closed when clicking Close button
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Operations > Banner Management
     *           2. Click Create Banner button
     *           3. Click Close button
     * @expect:  1. New Banner popup is closed successfully
     */
    @Test (groups = {"regression"})
    public void BO_Operations_BannerManagement_002(){
        log("@title: Validate that New Banner popup is closed when clicking Close button");
        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2: Click Create Banner button");
        NewBannerPopup popup = page.createNewBanner();

        log("Step 3: Click Close button");
        popup.clickCloseBtn();

        log("Verify 1: New Banner popup is closed successfully");
        Assert.assertTrue(popup.popupNewBanner.isInvisible(2), "ERROR: popupNewBanner is still displayed");
        log("INFO: Executed completely");
    }
}
