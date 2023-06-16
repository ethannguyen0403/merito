package backoffice.testcases.operations;

import backoffice.common.BOConstants;
import backoffice.pages.bo.operations.LiveStreamingManagementPage;
import backoffice.pages.bo.operations.component.AutoMappingPopup;
import backoffice.pages.bo.operations.component.ConfirmMapLiveStreamingPopup;
import backoffice.utils.operations.LiveStreamingManagementUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import membersite.objects.sat.Event;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiveStreamingManagementTest extends BaseCaseTest {

    /**
     * @title: Validate Auto Mapping popup data display
     * @pre-condition: 1. Log in BO
     * @steps: 1. Access Operations >Live Streaming Management
     * 2. Select today, Sport
     * 3. Select All data in Competition and event dropdown boxes in  Fair Exchange  and Provider
     * 4. Click on Auto map
     * 5. Click Cancel button
     * @expect: 1. Verify the title is Auto Mapping
     * 2. Verify Table header display : Start date, Fair Competition, Fair Event Name, Provider Competition, Provider Event Name
     * 3. Auto mapping popup is disappear
     */
    @TestRails(id = "641")
    @Test(groups = {"smoke"})
    public void BO_Operations_Live_Streaming_Management_002() {
        log("@title: Validate Auto Mapping popup data display");
        log("Step 1. Access Operations >Live Streaming Management");
        LiveStreamingManagementPage page = backofficeHomePage.navigateLiveStreamingManagement();
        log("Step 2. Select today, Sport");
        log("Step 3. Select All data in Competition and event dropdown boxes in  Fair Exchange  and Provider");
        log("Step 4. Click on Auto map");
        AutoMappingPopup popup = page.clickAutoMap();
        log("Verify 1. Verify the title is Auto Mapping");
        Assert.assertEquals(popup.getTitle(), BOConstants.Operations.LiveStreamingManagement.TITLE_AUTO_MAPPING, "FAIELD! Auto Mapping title is incorrect");

        log("Verify 2. Verify Table header display : Start date, Fair Competition, Fair Event Name, Provider Competition, Provider Event Name");
        Assert.assertEquals(popup.tblAutoMapping.getColumnNamesOfTable(), BOConstants.Operations.LiveStreamingManagement.TABLE_AUTO_MAPPING, "FAILED! Auto mapping table header is incorrect");

        log("Step 5. Click Cancel button");
        popup.clickCancelBtn();

        log("Verify 3. Auto mapping popup is disappear");
        Assert.assertFalse(popup.isDisplayed(), "FAILED! Auto Mapping popup still display after click Cancel");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate map confirmation info is display correctly
     * @pre-condition: 1. Log in BO
     * @steps: 1. Access Operations >Live Streaming Management
     * 2. Select today, Sport
     * 3. Select All data in Competition and event dropdown boxes in  Fair Exchange  and Provider
     * 4. Select an event in Fair Exchange table and an event in Provider table
     * 5. Click Map button
     * 6. Click Cancel
     * @expect: 1. Verify confirm popup display with the title: Are you sure to map those events?
     * 2 The table with 2 rows, 1 row contains Merito event and 1 is Provider event which is the data selected in step 2
     * 3. There is a hint if the 2 selected event is suggested is not match e.g:
     * + The 2 selected event names look like different match.
     * +The 2 selected events have different open dates.
     * 4. Confirm popup is closed and the 2 event is not mapped
     */
    @TestRails(id = "642")
    @Test(groups = {"smoke"})
    public void BO_Operations_Live_Streaming_Management_003() {
        log("@title: Validate map confirmation info is display correctly");
        log("Step 1. Access Operations >Live Streaming Management");
        LiveStreamingManagementPage page = backofficeHomePage.navigateLiveStreamingManagement();
        List<String> lstSport = LiveStreamingManagementUtils.getSportLiveCenter();
        String today = DateUtils.getDate(1, "dd-MM-YYYY", BOConstants.GMT_FOUR);
        String date = DateUtils.getDate(1, "YYYY-MM-dd", BOConstants.GMT_FOUR);
        Event fEEvent = LiveStreamingManagementUtils.getListEvent(today, "1").get(0);
        Event lCEvent = LiveStreamingManagementUtils.getListLCEvent(today, "1").get(0);

        log("Step 2. Select today, Sport");
        log("Step 3. Select All data in Competition and event dropdown boxes in Fair Exchange and Provider");
        page.filter(date, lstSport.get(0), "", "", "", "");

        log("Step 4. Select an event in Fair Exchange table and an event in Provider table");
        log("Step 5. Click Map button");
        List<String> expectedMappedList = page.getMappedEventList(page.colMappedListEventCol);
        ConfirmMapLiveStreamingPopup popup = page.map(fEEvent.getEventName(), lCEvent.getEventName());

        log("Verify 1. Verify confirm popup display with the title: Are you sure to map those events?");
        Assert.assertEquals(popup.getTitle(), BOConstants.Operations.LiveStreamingManagement.TITLE_CONFIRM_MAPPING_POPUP, "FAILED! Confirm mapping popup title is incorrect");

        log("Verify 2 The table with 2 rows, 1 row contains Merito event and 1 is Provider event which is the data selected in step 2");
        List<ArrayList<String>> mapEventInfoActual = popup.tblConfirm.getRows(false);
        String feEventStartTime = DateUtils.convertMillisToDateTime(fEEvent.getStartTime(), "dd/MM/YYYY HH:mm:ss");
        String lcEventStartTime = DateUtils.convertMillisToDateTime(lCEvent.getStartTime(), "dd/MM/YYYY HH:mm:ss");
        Assert.assertEquals(mapEventInfoActual.get(0), new ArrayList<String>(Arrays.asList("", "Start date", "Event name")), "FAILED! Header Table is incorrect");
        Assert.assertEquals(mapEventInfoActual.get(1), new ArrayList<String>(Arrays.asList("Merito event", feEventStartTime, fEEvent.getEventName())), "FAILED! Merito event info is incorrect");
        Assert.assertEquals(mapEventInfoActual.get(2), new ArrayList<String>(Arrays.asList("Provider event", lcEventStartTime, lCEvent.getEventName())), "FAILED! Provider event info is incorrect");

        log("Verify 3. There is a hint if the 2 selected event is suggested is not match");
        Assert.assertEquals(popup.lblNote.getText(), BOConstants.Operations.LiveStreamingManagement.CONFIRM_MAPPING_NOTE_MESSAGE, "FAILED! Note is not matched with the expected");

        log("Step 6. Click Cancel");
        popup.clickCancelBtn();

        log("Verify 4. Confirm popup is closed and the 2 event is not mapped");
        Assert.assertFalse(popup.isDisplayed(), "FAILED! the confirm popup does not disappear after clicking cancel");
        Assert.assertEquals(page.getMappedEventList(page.colMappedListEventCol), expectedMappedList, "FAILED! These Event is mapped although cancel confirmation");

        log("INFO: Executed completely");
    }

}
