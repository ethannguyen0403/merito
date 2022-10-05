package backoffice.testcases.system;

import backoffice.common.BOConstants;
import backoffice.objects.bo.system.Product;
import backoffice.pages.bo.system.ProductMaintenancePage;
import backoffice.pages.bo.system.productmaintenance.MaintenanceDetailsPopup;
import backoffice.utils.system.ProductMaintenanceUtils;
import baseTest.BaseCaseMerito;
import com.paltech.constant.Helper;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class ProductMaintenanceTest extends BaseCaseMerito{

    /**
     * @title: Validate that product names and status displays correctly
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate System > Product Maintenance
     * @expect:  1. Product names and status displays correctly
     */
    @Test (groups = {"smoke"})
    public void BO_System_ProductMaintenance_001(){
        log("@title: Validate that product names and status displays correctly");
        List<Product> lstProducts = ProductMaintenanceUtils.getProducts();
        log("Step 1: Navigate System > Product Maintenance");
        ProductMaintenancePage page = backofficeHomePage.navigateProductMaintenance();

        List<String> lstProductsOnTable = page.tblProductMaintenance.getColumn(page.colProductName, false);
        List<String> lstStatusOnTable = page.tblProductMaintenance.getColumn(page.colStatus, false);

        log("Verify 1: Product names and status displays correctly");
        Assert.assertEquals(lstProductsOnTable.size(), lstProducts.size(), String.format("ERROR: The expected no of products is '%s' but found '%s'", lstProducts.size(), lstProductsOnTable.size()));
        for(int i=0;i<lstProducts.size();i++){
            String observeStatus = lstStatusOnTable.get(i).toUpperCase();
            String observeProduct = lstProductsOnTable.get(i);
            String expectedStatus = lstProducts.get(i).getStatus();
            String expectedProduct = lstProducts.get(i).getProductName();
            Assert.assertEquals(observeProduct, expectedProduct, String.format("ERROR: The expected product is '%s' but found '%s'", expectedProduct, observeProduct));
            Assert.assertEquals(observeStatus, expectedStatus, String.format("ERROR: The expected status is '%s' but found '%s'", expectedStatus, observeStatus));
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Maintenance Details data is display correctly
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Access System> Product Maintenance
     *           2. Click on Action of Exchange product
     * @expect:  1. Verify Maintenance Details popup is display, product and status value is correctly displayed
     */
    @Test (groups = {"smoke"})
    public void BO_System_ProductMaintenance_002(){
        log("@title: Validate Maintenance Details data is display correctly");
        log("Step 1: Navigate System > Product Maintenance");
        List<Product> lstProducts = ProductMaintenanceUtils.getProducts();
        ProductMaintenancePage page = backofficeHomePage.navigateProductMaintenance();

        log("Step 2. Click on Action of Exchange product");
        for(int i=0;i<lstProducts.size();i++){
            log(String.format("Step 2.%s Click on Action of %s product",i+1, lstProducts.get(i).getProductName()));
            MaintenanceDetailsPopup popup = page.action(lstProducts.get(i).getProductName());
            log(String.format("Verify 1.%s Verify Maintenance Details popup is display, name and status is correctly displayed when open %s product",i+1, lstProducts.get(i).getProductName()));
            Assert.assertEquals(popup.txtProductName.getAttribute("value"),lstProducts.get(i).getProductName(),"FAILED! Product name does not match");
            Assert.assertTrue(popup.ddbStatus.getFirstSelectedOption().equalsIgnoreCase(lstProducts.get(i).getStatus()),"FAILED! Status name does not match");
            popup.clickCloseBtn();
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate active product can display in member
     * @pre-condition:
     *           1. Login BO successfully
     *           2. Have a member account that active all product in agent site
     * @steps:   1. Access System> Product Maintenance
     *           2. Get all product in active status
     *           3. Login member site of any brands
     * @expect:  1. Verify the product is active in member site, not display maintenance page
     */
    @Test (groups = {"smoke"})
    @Parameters({"satMemberLoginID","memberPassword"})
    public void BO_System_ProductMaintenance_003(String satMemberLoginID, String memberPassword) throws Exception {
        log("@title: Validate active product can display in member");
        log("Step 1: Navigate System > Product Maintenance");
        log("Step 2. Get all product in active status");
        List<Product> lstProducts = ProductMaintenanceUtils.getProducts();

        log("Step 3. Login member site of any brands");
        Helper.loginFairExchange(memberSOSUrl,memberLoginURL,satMemberLoginID,memberPassword,true);
//        backofficeHomePage satbackofficeHomePage = new backofficeHomePage();
//
//
//        log("Verify 1. Verify the product is active in member site, not display maintenance page");


        log("INFO: Executed completely");
    }

    /**
     * @title: There is no http responded error returned
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate System > Product Maintenance
     * @expect:  1. There is no http responded error returned
     */
    @Test (groups = {"http_request"})
    public void BO_System_ProductMaintenance_004(){
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate System > Product Maintenance");
        backofficeHomePage.navigateProductMaintenance();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that this page loading is successful
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate System > Product Maintenance
     * @expect:  1. Data on this table displays correctly
     *           2. Page title displays correctly
     */
    @Test (groups = {"regression"})
    public void BO_System_ProductMaintenance_005(){
        log("@title: Validate that this page loading is successful");
        log("Step 1: Navigate System > Product Maintenance");
        ProductMaintenancePage page = backofficeHomePage.navigateProductMaintenance();

        String pageTitle = page.lblPageTitle.getText();
        List<String> lstHeaders = page.tblProductMaintenance.getColumnNamesOfTable();
        int expectedNoColumns = BOConstants.System.ProductMaintenance.TABLE_HEADER.size();
        log("Verify 1: Page title displays correctly");
        log("Verify 2: Data on this table displays correctly");
        Assert.assertEquals(pageTitle, BOConstants.System.ProductMaintenance.TITLE, String.format("ERROR: The expected page title is '%s' but found '%s'", BOConstants.System.ProductMaintenance.TITLE, pageTitle));
        Assert.assertEquals(lstHeaders.size(), expectedNoColumns, String.format("ERROR: The expected no of columns is '%s' but found '%s'", expectedNoColumns, lstHeaders.size()));
        for (int i=0;i<expectedNoColumns;i++){
            String observed = lstHeaders.get(i);
            String expected = BOConstants.System.ProductMaintenance.TABLE_HEADER.get(i);
            Assert.assertEquals(observed, expected, String.format("ERROR: The expected column name is '%s' but found '%s'", expected, observed));
        }
        log("INFO: Executed completely");
    }


}
