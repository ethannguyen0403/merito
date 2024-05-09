package backoffice.testcases.system;

import backoffice.common.BOConstants;
import backoffice.pages.bo.system.CurrencyCountryMappingPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class CurrencyCountryMappingTest extends BaseCaseTest {

    /**
     * @title: Validate UI display correctly when access Currency Country Mapping
     * @pre-condition: 1. Log in BO
     * @steps: 1. Access Operations > Currency - Country Mapping
     * @expect: 1. Verify UI of Currencies and Countries section is correctly
     * 2. Save button is disable
     */
    @TestRails(id = "656")
    @Test(groups = {"smoke"})
    public void BO_System_Currency_Country_Mapping_656() {
        log("@title: Validate UI display correctly when access Currency Country Mapping");
        log("Step 1. Access Operations > Currency - Country Mapping");
        CurrencyCountryMappingPage page = backofficeHomePage.navigateCurrencyCountryMapping();

        log("Verify 1. Verify UI of Currencies and Countries section is correctly");
        Assert.assertEquals(page.lblCurrenciesTableName.getText(), BOConstants.Operations.CurrencyCountryMapping.CURRENCIES_TITLE, "FAILED! The currencies title not display as expectation");
        Assert.assertEquals(page.lblCountriesTableName.getText(), BOConstants.Operations.CurrencyCountryMapping.COUNTRIES_TITLE, "FAILED! The countries title not display as expectation");
        Assert.assertEquals(page.tblCurrencies.getColumnNamesOfTable(), BOConstants.Operations.CurrencyCountryMapping.TABLE_HEADER_CURRENCIES, "FAILED! Currencies Table header does not display as expectation");
        Assert.assertEquals(page.tblCountries.getColumnNamesOfTable(), BOConstants.Operations.CurrencyCountryMapping.TABLE_HEADER_COUNTRIES, "FAILED! Countries Table header does not display as expectation");

        log("Verify 2. Save button is disable");
        Assert.assertTrue(page.btnSave.getAttribute("class").contains("disabled"), "FAILED! Save button is disabled by default");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Map/unmap countries works
     * @pre-condition: 1. Log in BO
     * @steps: 1. Access Operations > Currency - Country Mapping
     * 2. Select a currency and map the ip IT  Test Country
     * 3. Login member site with the account has the currency that mapping IP
     * 4.Back to BO and unmap the IP
     * 5. Re-login member site
     * @expect: 1. Verify can map IP successfully
     * 2. Can login member from IT test country IP
     * 3. Verify can unmap IP successfully
     * 4. Can not login from IT test country IP when unmapping
     */
    @TestRails(id = "657")
    @Test(groups = {"regression"})
    @Parameters({"fsMemberLoginID", "username", "password"})
    public void BO_System_Currency_Country_Mapping_657(String fsMemberLoginID, String username, String password) throws Exception {
        //this case should run with context brandname = funsport
        log("@title: Validate Map/unmap countries works");
        String currencyCode = "PTI";
        String itTestCountry = "IT Test Country";
        log("Step 1 Access Operations > Currency - Country Mapping");
        CurrencyCountryMappingPage pageMapping = backofficeHomePage.navigateCurrencyCountryMapping();
        log(String.format("Step 2: Select a currency %s and Unmap the ip IT Test Country", currencyCode));
        pageMapping.mapCurrency(currencyCode, itTestCountry, false, true);
        try {
            log("Verify 1. Can not login from IT test country IP when unmapping");
            loginMember("", "", false, "", "", false);
            Assert.assertEquals(landingPage.loginInvalid(fsMemberLoginID, StringUtils.decrypt(password)), BOConstants.Operations.CurrencyCountryMapping.ERROR_LOGIN_MSG, "FAILED! Login member successfully");
        } finally {
            log(String.format("Step 3: Select a currency %s and map the ip IT Test Country", currencyCode));
            loginBackoffice(username,password, true);
            backofficeHomePage.navigateCurrencyCountryMapping();
            pageMapping.mapCurrency(currencyCode, itTestCountry, true, true);

            log("Verify 1. Can login from IT test country IP when mapping");
            loginMember(fsMemberLoginID, password);
            Assert.assertTrue(memberHomePage.isLoginSuccess(), "FAILED! Login unsuccessfully");
            log("@Post condition: Re mapping currency with country");
            log("INFO: Executed Completely!");
        }
    }

}
