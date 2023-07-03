package backoffice.testcases.system;

import backoffice.pages.bo.system.ProviderCurrencyMappingPage;
import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class ProviderCurrencyMappingTest extends BaseCaseTest {

    /**
     * @title: Validate data in Ezugi display correctly
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Provider Currency Mapping
     * 2. Check provider currency mapping of Ezugi
     * @expect: 1. Verify Ezugi provider currency is corrected
     */
    @TestRails(id = "651")
    @Test(groups = {"smoke"})
    public void BO_System_Provider_Currency_Mapping_651() {
        log("@title: Validate data in Ezugi Live Dealer European display correctly");
        log("Step  1. Access Tool > Provider Currency Mapping");
        List<ArrayList<String>> lstProviderCurrencies = ProviderCurrencyMappingUltils.getProviderCurrencyMapping("EZUGI");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();
        log("Step 2. Check provider currency mapping of Ezugi");
        page.ddnProduct.selectByVisibleContainsText("Ezugi");
        page.btnSubmit.click();
        log("Verify 1. Verify Ezugi provider currency is corrected");
        Assert.assertEquals(page.tblEzugi.getRowsWithoutHeader(50, true), lstProviderCurrencies, "FAILED! List Currency of Ezugi is incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data in 27 Fancy display correctly
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Provider Currency Mapping
     * 2. Check provider currency mapping of 27 Fancy
     * @expect: 1. Verify 27 Fancy provider currency is corrected
     */
    @TestRails(id = "652")
    @Test(groups = {"smoke"})
    public void BO_System_Provider_Currency_Mapping_652() {
        log("@title: Validate data in 27 Fancy display correctly");
        log("Step  1. Access Tool > Provider Currency Mapping");
        List<ArrayList<String>> lstProviderCurrencies = ProviderCurrencyMappingUltils.getProviderCurrencyMapping("FANCY");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();
        log("Step 2. Check provider currency mapping of 27 Fancy");
        log("Verify 1. Verify 27 Fancy provider currency is corrected");
        Assert.assertEquals(page.tblFancy.getRowsWithoutHeader(50, true), lstProviderCurrencies, "FAILED! List Currency of 27 Fancy is incorret");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data in Superspade display correctly
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Provider Currency Mapping
     * 2. Check provider currency mapping of Superspade
     * @expect: 1. Verify Superspade provider currency is corrected
     */
    @TestRails(id = "653")
    @Test(groups = {"smoke"})
    public void BO_System_Provider_Currency_Mapping_653() {
        log("@title: Validate data in Superspade display correctly");
        log("Step  1. Access Tool > Provider Currency Mapping");
        List<ArrayList<String>> lstProviderCurrencies = ProviderCurrencyMappingUltils.getProviderCurrencyMapping("SUPER_SPADE");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();

        log("Step 2. Check provider currency mapping of Superspade");
        page.ddnProduct.selectByVisibleContainsText("Superspade");
        page.btnSubmit.click();
        log("Verify 1. Verify Superspade provider currency is corrected");
        Assert.assertEquals(page.tblSuperspade.getRowsWithoutHeader(50, true), lstProviderCurrencies, "FAILED! List Currency of Superspade is incorret");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data in Digient display correctly
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Provider Currency Mapping
     * 2. Check provider currency mapping of Digient
     * @expect: 1. Verify Digient provider currency is corrected
     */
    @TestRails(id = "654")
    @Test(groups = {"smoke"})
    public void BO_System_Provider_Currency_Mapping_654() {
        log("@title: Validate data in Digient display correctly");
        log("Step  1. Access Tool > Provider Currency Mapping");
        List<ArrayList<String>> lstProviderCurrencies = ProviderCurrencyMappingUltils.getProviderCurrencyMapping("DIGIENT");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();

        log("Step 2. Check provider currency mapping of Digient");
        page.ddnProduct.selectByVisibleContainsText("Digient");
        page.btnSubmit.click();
        log("Verify 1. Verify Digient provider currency is corrected");
        Assert.assertEquals(page.tblDigient.getRowsWithoutHeader(50, true), lstProviderCurrencies, "FAILED! List Currency of Digient is incorret");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data in Supernowa display correctly
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Provider Currency Mapping
     * 2. Check provider currency mapping of Supernowa
     * @expect: 1. Verify Supernowa provider currency is corrected
     */
    @TestRails(id = "655")
    @Test(groups = {"smoke"})
    public void BO_System_Provider_Currency_Mapping_655() {
        log("@title: Validate data in Supernowa display correctly");
        log("Step  1. Access Tool > Provider Currency Mapping");
        List<ArrayList<String>> lstProviderCurrencies = ProviderCurrencyMappingUltils.getProviderCurrencyMapping("VERONICA");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();

        log("Step 2. Check provider currency mapping of Supernowa");
        page.ddnProduct.selectByVisibleContainsText("Supernowa");
        page.btnSubmit.click();
        log("Verify 1. Verify Supernowa provider currency is corrected");
        Assert.assertEquals(page.tblSupernowa.getRowsWithoutHeader(50, true), lstProviderCurrencies, "FAILED! List Currency of Supernowa is incorret");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate product list display correctly in Product dropdown
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Provider Currency Mapping Page
     *         2. Observer the list provider in Product dropdown
     * @expect: 1. verify the list product is correctly display and with the format "product-name (provider name)"
     *              e.g. CMD Sportsbook (CMD Sportsbook) …
     */
    @TestRails(id = "3868")
    @Test(groups = {"Regression"})
    public void BO_System_Provider_Currency_Mapping_3868() {
        log("@title: Validate product list display correctly in Product dropdown");
        log("Step 1. Access Provider Currency Mapping Page");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();
        String productName= "PS38 Sports";
        String providerName= "Pinnacle Sportsbook";
        log("Step 2. Observer the list provider in Product dropdown");
        page.ddnProduct.selectByVisibleContainsText(productName);
        log("Verify 1: the list product is correctly display and with the format \"product-name (provider name)");
        Assert.assertEquals(page.getProductIntoDropdown(productName),productName+" ("+providerName+")");
    }
}
