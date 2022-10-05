package backoffice.testcases.system;

import org.testng.Assert;
import org.testng.annotations.Test;
import backoffice.pages.bo.system.ProviderCurrencyMappingPage;
import baseTest.BaseCaseMerito;
import backoffice.utils.tools.ProviderCurrencyMappingUltils;
import java.util.ArrayList;
import java.util.List;

public class ProviderCurrencyMappingTest extends BaseCaseMerito{

    /**
     * @title: Validate data in Ezugi display correctly
     * @pre-condition:
     *          1.Login BO
     * @steps: 1. Access Tool > Provider Currency Mapping
     *          2. Check provider currency mapping of Ezugi
     * @expect: 1. Verify Ezugi provider currency is corrected
     */
    @Test (groups = {"smoke"})
    public void BO_System_Provider_Currency_Mapping_001(){
        log("@title: Validate data in Ezugi display correctly");
        log("Step  1. Access Tool > Provider Currency Mapping");
        List<ArrayList<String>> lstProviderCurrencies =ProviderCurrencyMappingUltils.getProviderCurrencyMapping("EZUGI");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();

        log("Step 2. Check provider currency mapping of Ezugi");
        log("Verify 1. Verify Ezugi provider currency is corrected");
        Assert.assertEquals(page.tblEzugi.getRowsWithoutHeader(50,true),lstProviderCurrencies,"FAILED! List Currency of Ezugi is incorret");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data in 27 Fancy display correctly
     * @pre-condition:
     *          1.Login BO
     * @steps: 1. Access Tool > Provider Currency Mapping
     *          2. Check provider currency mapping of 27 Fancy
     * @expect: 1. Verify 27 Fancy provider currency is corrected
     */
    @Test (groups = {"smoke"})
    public void BO_System_Provider_Currency_Mapping_002(){
        log("@title: Validate data in 27 Fancy display correctly");
        log("Step  1. Access Tool > Provider Currency Mapping");
        List<ArrayList<String>> lstProviderCurrencies =ProviderCurrencyMappingUltils.getProviderCurrencyMapping("FANCY");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();

        log("Step 2. Check provider currency mapping of 27 Fancy");
        log("Verify 1. Verify 27 Fancy provider currency is corrected");
        Assert.assertEquals(page.tblFancy.getRowsWithoutHeader(50,true),lstProviderCurrencies,"FAILED! List Currency of 27 Fancy is incorret");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data in Superspade display correctly
     * @pre-condition:
     *          1.Login BO
     * @steps: 1. Access Tool > Provider Currency Mapping
     *          2. Check provider currency mapping of Superspade
     * @expect: 1. Verify Superspade provider currency is corrected
     */
    @Test (groups = {"smoke"})
    public void BO_System_Provider_Currency_Mapping_003(){
        log("@title: Validate data in Superspade display correctly");
        log("Step  1. Access Tool > Provider Currency Mapping");
        List<ArrayList<String>> lstProviderCurrencies =ProviderCurrencyMappingUltils.getProviderCurrencyMapping("SUPER_SPADE");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();

        log("Step 2. Check provider currency mapping of Superspade");
        log("Verify 1. Verify Superspade provider currency is corrected");
        Assert.assertEquals(page.tblSuperspade.getRowsWithoutHeader(50,true),lstProviderCurrencies,"FAILED! List Currency of Superspade is incorret");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data in Digient display correctly
     * @pre-condition:
     *          1.Login BO
     * @steps: 1. Access Tool > Provider Currency Mapping
     *          2. Check provider currency mapping of Digient
     * @expect: 1. Verify Digient provider currency is corrected
     */
    @Test (groups = {"smoke"})
    public void BO_System_Provider_Currency_Mapping_004(){
        log("@title: Validate data in Digient display correctly");
        log("Step  1. Access Tool > Provider Currency Mapping");
        List<ArrayList<String>> lstProviderCurrencies =ProviderCurrencyMappingUltils.getProviderCurrencyMapping("DIGIENT");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();

        log("Step 2. Check provider currency mapping of Digient");
        log("Verify 1. Verify Digient provider currency is corrected");
        Assert.assertEquals(page.tblDigient.getRowsWithoutHeader(50,true),lstProviderCurrencies,"FAILED! List Currency of Digient is incorret");
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate data in Supernowa display correctly
     * @pre-condition:
     *          1.Login BO
     * @steps: 1. Access Tool > Provider Currency Mapping
     *          2. Check provider currency mapping of Supernowa
     * @expect: 1. Verify Supernowa provider currency is corrected
     */
    @Test (groups = {"smoke"})
    public void BO_System_Provider_Currency_Mapping_005(){
        log("@title: Validate data in Supernowa display correctly");
        log("Step  1. Access Tool > Provider Currency Mapping");
        List<ArrayList<String>> lstProviderCurrencies =ProviderCurrencyMappingUltils.getProviderCurrencyMapping("VERONICA");
        ProviderCurrencyMappingPage page = backofficeHomePage.navigateProviderCurrencyMapping();

        log("Step 2. Check provider currency mapping of Supernowa");
        log("Verify 1. Verify Supernowa provider currency is corrected");
        Assert.assertEquals(page.tblSupernowa.getRowsWithoutHeader(50,true),lstProviderCurrencies,"FAILED! List Currency of Supernowa is incorret");
        log("INFO: Executed completely");
    }
}
