package backoffice.testcases.system;

import backoffice.common.BOConstants;
import backoffice.pages.bo.system.CurrencyCountryMappingPage;
import baseTest.BaseCaseTest;
import com.paltech.constant.Helper;
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
    public void BO_System_Currency_Country_Mapping_001() {
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
    @Test(groups = {"smoke"})
    @Parameters({"fsMemberLoginID", "username", "password"})
    public void BO_System_Currency_Country_Mapping_004(String fsMemberLoginID, String username, String password) throws Exception {
        log("@title: Validate Map/unmap countries works");
        log("Step 1. Access Operations > Currency - Country Mapping");
        String currencyCode = "PTI";
        String vietNameCountry = "Vietnam";
        String itTestCountry = "IT Test Country";
        CurrencyCountryMappingPage page;
        //TODO: implement this case
        Assert.assertTrue(false, "Need to implement this case");
        log("INFO: Executed Completely!");
        try {
            // log("Step 2. Select a currency and map the ip IT Test Country amd Vietnam");
            // default ip is mapped in post condition:
//            log("Step 1. Navigate to Curency Country Mapping ");
//            // switch to old tab and unmap IP
//            page = backofficeHomePage.navigateCurrencyCountryMapping();
//
//            log("Step 2.1 UnMap PTI to Viet Nam ");
//            page.mapCurrency(currencyCode, vietNameCountry, false);
//
//            log("Step 2.2 UnMap PTI to IT Test Country");
//            page.mapCurrency(currencyCode, itTestCountry, false);
//
//            log("Verify 3. Verify can unmap IP successfully");
//            Assert.assertEquals(page.lblSuccessAlert.getText(), BOConstants.Operations.CurrencyCountryMapping.SUCCESS_MSG, "FAILED! Success message does not correct");
//            page.logout();
//            log("Verify 1. Verify can map IP successfully");
//            log("Step 5. Re-login member site");
            //BaseCaseMember.loginMemberviaUI(environment.getFunsportLoginURL(),fsMemberLoginID,StringUtils.decrypt(password));
           /* login(fsMemberLoginID,StringUtils.decrypt(password));
            LandingPage landingPage = new LandingPage();

            log("Verify 4. Can not login from IT test country IP when unmapping" +
                    "The message - [Login fail. Your currency does not match with your location.] display after login");
            LoginPopup popup = landingPage.fsHeaderControl.clickLoginBtn();
            popup.lblErrorMessage.isTextDisplayed("Login fail. Your currency does not match with your location.",3);
            Assert.assertEquals(popup.lblErrorMessage.getText(),"Login fail. Your currency does not match with your location.","FAILED! Mapping error not display");
*/
//            log("INFO: Executed completely");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//
//            Helper.loginBOIgnoreCaptcha(backofficeSOSUrl, backofficeDashboardUrl, username, password, true);
//            page = backofficeHomePage.navigateCurrencyCountryMapping();
//            log("Step PostCondition 1. Active IP for PTI currency by default");
//            page.mapCurrency(currencyCode, vietNameCountry, true);
//            page.mapCurrency(currencyCode, itTestCountry, true);

//            log("Step Post-condition 2 Logout BO");
//            page.logout();
//
//            log("Step Post-condition 2  Login member site with the account has the currency that mapping IP");
//            BaseCaseMember.loginMemberviaUI(environment.getFunsportLoginURL(),fsMemberLoginID, StringUtils.decrypt(password));
//            .tabexchange.backofficeHomePage exchangeHome = new pages.fairexchange.tabexchange.backofficeHomePage();
//
//            log("Verify Post-condition: Can login member from IT test country IP");
//            Assert.assertTrue(exchangeHome.ddbMyAccount.isDisplayed(),"FAILED! Cannot login successfull");
//            exchangeHome.logout();
        }
    }

}
