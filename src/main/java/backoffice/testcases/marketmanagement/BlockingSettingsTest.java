package backoffice.testcases.marketmanagement;

import backoffice.pages.bo.marketmanagement.BlockingSettingsPage;
import backoffice.utils.operations.BlockingSettingsUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class BlockingSettingsTest extends BaseCaseTest {

    /**
     * @title: Validate default data in PO Account, Brand, Default Event, Level to control Blocking and Unblock Schedule - Bet able before 25m display correctly
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Operations > Blocking Settings
     * @expect: 1. Verify the list data in PO account column is correctly and according brand name is correct
     * 2. Default event, Level to control blocking, Unblock Schedule - Betable before 25m is display correct by default
     */
    @TestRails(id = "633")
    @Test(groups = {"smoke"})
    public void BO_Market_Management_Blocking_Settings_633() {
        log("@title: Validate default data in PO Account, Brand, Default Event, Level to control Blocking and Unblock Schedule - Bet able before 25m display correctly");
        log("Step 1. Access Operations > Blocking Settings");
        BlockingSettingsPage page = backofficeHomePage.navigateBlockingSettings();
        List<ArrayList<String>> lstConfig = BlockingSettingsUtils.getBlockingSetting();
        page.tblBlockingSetting.isDisplayed(2);

        log("Verify 1. Verify the list data in PO account column is correctly and according brand name is correct");
        log("Verify 2. Default event, Level to control blocking, Unblock Schedule - Betable before 25m is display correct by default");
        Assert.assertTrue(page.verifyDataCorrect(lstConfig), "FAILED! Default data in the blocking Setting not display correctly");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate sport is active by default in edit sport popup and agent correctly
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Operations > Blocking Settings
     * 2. Get Sports are activate that display in Sport column according with a PO account
     * 3. Click on Edit sport accordingly and verify active status
     * 4. Login agent site PO account> Create Company
     * @expect: 1. Verify If the sport is active the check box in the right side is checked, otherwise is unchecked
     * 2. Verify agent site > create company > All sports is active correct as BO setting
     */
//    @Test(groups = {"regression"})
    public void BO_Market_Management_Blocking_Settings_004() {
        log("@title: Validate sport is active by default in edit sport popup and agent correctly");
        log("Step 1. Access Operations > Blocking Settings");
        //TODO: implement this case
        BlockingSettingsPage page = backofficeHomePage.navigateBlockingSettings();

        log("Step 2. Get Sports are activate that display in Sport column according with a PO account");
        log("Step 3. Click on Edit sport accordingly and verify active status");
        log("Step 4. Login agent site PO account> Create Company");

        log("Verify 1. Verify If the sport is active the check box in the right side is checked, otherwise is unchecked");

        log("Verify 2. Verify agent site > create company > All sports is active correct as BO setting");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Market is active by default correctly in edit market popup
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Operations > Blocking Settings
     * 2. Get Market are activate that display in Market Type column according with a PO account
     * 3. Click on Edit sport accordingly and verify active status
     * @expect: 1. Verify If the sport is active the check box in the right side is checked, otherwise is unchecked
     */
//    @Test(groups = {"regression"})
    public void BO_Market_Management_Blocking_Settings_005() {
        log("@title: Validate Market is active by default correctly in edit market popup");
        log("Step 1. Access Operations > Blocking Settings");
        //TODO: implement this case
//        BlockingSettingsPage page = backofficeHomePage.navigateBlockingSettings();

        log("Step 2. Get Market are activate that display in Market Type column according with a PO account");
        log("Step 3. Click on Edit sport accordingly and verify active status");

        log("Verify 1. Verify If the sport is active the check box in the right side is checked, otherwise is unchecked");

        log("INFO: Executed completely");
    }
}
