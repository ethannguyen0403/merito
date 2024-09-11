package backoffice.testcases.accountmanagement;

import backoffice.pages.bo.accountmanagement.PlayerInfoPage;
import backoffice.pages.bo.accountmanagement.component.UplineStatusPopup;
import backoffice.utils.tools.PlayerInfoUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerInfoTest extends BaseCaseTest {

    /**
     * @title: Validate can view player info
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Player Info
     * 2. Input a account and View
     * @expect: 1. Verify account info is display correctly
     */
    @Test(groups = {"smoke"})
    @Parameters({"satMemberLoginID","Maintenance.2024.V.6.0"})
    public void BO_Account_Management_Player_Info_001(String satMemberLoginID) {
        log("@title: Validate can view player info");
        log("Step 1. Access Tool > Player Info");
        List<String> apiInfo = PlayerInfoUtils.getInfo(satMemberLoginID);
        PlayerInfoPage page = backofficeHomePage.navigatePlayerInfo();

        log("Step 2. Input a account and View");
        page.viewPlayer(satMemberLoginID);

        log("Verify 1. Verify account info is display correctly");
        List<String> lstActual = page.tblPlayerInfo.getColumn(2, 16, false);
        Assert.assertEquals(lstActual.get(0), apiInfo.get(0), "FAILED! Login Id is incorrect");
        Assert.assertEquals(lstActual.get(1), apiInfo.get(1), "FAILED! Username is incorrect");
        Assert.assertEquals(lstActual.get(2), apiInfo.get(3), "FAILED! Login Id is incorrect");
        Assert.assertEquals(String.format("%.2f", Double.parseDouble(lstActual.get(7))), String.format("%.2f", Double.parseDouble(apiInfo.get(6))), "FAILED! Balance is incorrect");
        Assert.assertEquals(Double.parseDouble(lstActual.get(8)), Double.parseDouble(apiInfo.get(7)), "FAILED! Exposure is incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate view upline status of a player
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Player Info
     * 2. Input a account and View
     * 3. Click on Show button in Update Status row
     * @expect: 1. Verify upline status popup display correct with correct information
     */
    @Test(groups = {"smoke11"})
    @Parameters({"satMemberLoginID"})
    public void BO_Account_Management_Player_Info_002(String satMemberLoginID) {
        log("@title: Validate view upline status of a player");
        log("Step 1. Access Tool > Player Info");
        List<ArrayList<String>> expectedData = new ArrayList<>();
        expectedData.add(0, new ArrayList<String>(Arrays.asList("PO", "SA", "ACTIVE", "ACTIVE", "ACTIVE", "ACTIVE", "ACTIVE", "ACTIVE", "ACTIVE")));
        expectedData.add(1, new ArrayList<String>(Arrays.asList("CO", "SAA", "ACTIVE", "ACTIVE", "ACTIVE", "INACTIVE", "ACTIVE", "ACTIVE", "ACTIVE")));
        expectedData.add(2, new ArrayList<String>(Arrays.asList("PART", "SAA0", "ACTIVE", "ACTIVE", "ACTIVE", "INACTIVE", "ACTIVE", "ACTIVE", "ACTIVE")));
        expectedData.add(3, new ArrayList<String>(Arrays.asList("CORP", "SAA00", "ACTIVE", "ACTIVE", "ACTIVE", "INACTIVE", "ACTIVE", "ACTIVE", "ACTIVE")));
        expectedData.add(4, new ArrayList<String>(Arrays.asList("SMA", "05H", "ACTIVE", "ACTIVE", "ACTIVE", "INACTIVE", "ACTIVE", "ACTIVE", "ACTIVE")));
        expectedData.add(5, new ArrayList<String>(Arrays.asList("MA", "05H00", "ACTIVE", "ACTIVE", "ACTIVE", "INACTIVE", "ACTIVE", "ACTIVE", "ACTIVE")));
        expectedData.add(6, new ArrayList<String>(Arrays.asList("AG", "05H0000", "ACTIVE", "ACTIVE", "ACTIVE", "INACTIVE", "ACTIVE", "ACTIVE", "ACTIVE")));
        expectedData.add(7, new ArrayList<String>(Arrays.asList("PL", "05H0000000", "ACTIVE", "ACTIVE", "ACTIVE", "INACTIVE", "ACTIVE", "ACTIVE", "ACTIVE")));
        expectedData.add(8, new ArrayList<String>(Arrays.asList("Playable", "Playable", "Playable", "Hidden", "Playable", "Playable", "Playable")));

        /* List<ArrayList<String>> expectedData= PlayerInfoUtils.getUpline(satMemberLoginID);*/
        PlayerInfoPage page = backofficeHomePage.navigatePlayerInfo();

        log("Step 2. Input a account and View");
        page.viewPlayer(satMemberLoginID);
        log("Step 3. Click on Show button in Update Status row");
        UplineStatusPopup popup = page.showUpline();

        log("Verify 1. Verify upline status popup display correct with correct information");
        List<ArrayList<String>> uplineData = popup.tblUplineStatus.getRowsWithoutHeader(10, false);
        Assert.assertEquals(uplineData, expectedData, String.format("FAILED! Upline info of the account %s is incorrect", satMemberLoginID));

        log("INFO: Executed completely");
    }


}
