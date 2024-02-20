package backoffice.testcases.system;

import backoffice.common.BOConstants;
import backoffice.pages.bo.operations.component.ConfirmIPMapppingPopup;
import backoffice.pages.bo.operations.component.NewIPPopup;
import backoffice.pages.bo.system.CountryIPMappingPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class CountryIPMappingTest extends BaseCaseTest {

    /**
     * @title: Validate UI Country - IP Mapping display correctly
     * @pre-condition: 1. Log in BO
     * @steps: 1. Access Operations > Country - IP Mapping
     * 2. Select a country :Albania
     * 3. Click on New IP button
     * 4. Input valid IP: 192.168.10.120
     * 5. Click Save Change button
     * 6. Click Close
     * 7. Click Close New IP popup
     * @expect: 1 Verify UI of Countries and IP section is correct
     * 2. The Ips title display as the format : Ips: Albania
     * 3. The popup display with the title: New IP - Albania, Save Change button is disable by default
     * 4. Save Change button is enable
     * 5. Add Confirmation is displayed with the message: Are you sure to add this IP 192.168.10.120 to Albania?
     * 6. Confirmation popup is closed
     * 7. New IP popup is close
     * 8. The ip is not added without confirm
     */
    @TestRails(id = "650")
    @Test(groups = {"smoke"})
    public void BO_System_Country_IP_Mapping_650() throws InterruptedException {
        log("@title: Validate UI Country - IP Mapping display correctly");
        log("Step 1. Access Operations > Country - IP Mapping");
        String countryName = "Albania";
        String ip = "192.168.10.120";
        CountryIPMappingPage page = backofficeHomePage.navigateCountryIPMapping();

        log("Verify 1 Verify UI of Countries and IP section is correct");
        Assert.assertEquals(page.lblCountriesTableName.getText(), BOConstants.Operations.CountryIPMapping.COUNTRIES_TITLE, "FAILED! Countries Table Title is incorrect");
        Assert.assertEquals(page.tblCountries.getColumnNamesOfTable(), BOConstants.Operations.CountryIPMapping.TABLE_HEADER_COUNTRIES, "FAILED! Countries Table header is incorrect");
        Assert.assertEquals(page.tblIP.getColumnNamesOfTable(), BOConstants.Operations.CountryIPMapping.TABLE_IP_COUNTRIES, "FAILED! IP Table header is incorrect");

        log("Step 2. Select a country :Albania");
        page.searchCountryName(countryName);
        page.waitSpinIcon();
        page.selectCountry(countryName);

        log("Verify 2. The Ips title display as the format : Ips: Albania");
        Assert.assertEquals(page.lblIPTableName.getText(), String.format(BOConstants.Operations.CountryIPMapping.IP_TITLE, countryName), "FAILED! IPs title is incorrect");

        log("Step 3. Click on New IP button");
        String noRecordMsg = page.lblNoRecordIPTable.getText();
        //List<String> lstIP = page.tblIP.getColumn(1,false);
        NewIPPopup popup = page.openNewIP();

        log("Verify 3. The popup display with the title: New IP - Albania, Save Change button is disable by default");
        Assert.assertTrue(popup.lblTitle.getText().contains(String.format("New IP - %s", countryName)), "FAILED! New IP popup title is incorrect");
        Assert.assertFalse(popup.btnSave.isEnabled(), "FAILED! Save Change button should be disable by default");

        log("Step 4. Input valid IP: 192.168.10.120");
        popup.txtIP.sendKeys(ip);

        log("Verify 4. Save Change button is enable");
        Assert.assertTrue(popup.btnSave.isEnabled(), "FAILED! Save Change button should be enable when data inputted");

        log("Step 5. Click Save Change button");
        popup.btnSave.click();
        ConfirmIPMapppingPopup confirmPopup = new ConfirmIPMapppingPopup();

        log("Verify 5. Add Confirmation is displayed with the message: Are you sure to add this IP 192.168.10.120 to Albania?");
        Assert.assertEquals(confirmPopup.getContent(), String.format(BOConstants.Operations.CountryIPMapping.CONFIRM_MSG, ip, countryName), "FAILED! Confirm message display incorrect");

        log("Step 6. Click Close");
        confirmPopup.clickCloseBtn();

        log("Verify 6. Confirmation popup is closed");
        Assert.assertFalse(confirmPopup.isDisplayed(), "FAILED! the confirm popup still display after closed");

        log("Step 7. Click Close New IP popup");
        popup.clickCloseBtn();

        log("Verify 7. New IP popup is close");
        Assert.assertFalse(popup.isDisplayed(), "FAILED! the New IP popup still display after closed");

        log("Verify 8. The ip is not added without confirm");
        List<String> lstIPActual = page.tblIP.getColumn(1, false);
        Assert.assertEquals(page.lblNoRecordIPTable.getText(), noRecordMsg, "FAILED! the list IP is changed after added without confirm");
        log("INFO: Executed completely");
    }
}
