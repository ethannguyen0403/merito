package backoffice.testcases.system.productmaintenance;

import backoffice.common.BOConstants;
import backoffice.objects.bo.system.Product;
import backoffice.pages.bo.system.ProductMaintenancePage;
import backoffice.pages.bo.system.productmaintenance.MaintenanceDetailsPopup;
import backoffice.utils.system.ProductMaintenanceUtils;
import baseTest.BaseCaseMerito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceDetailsTest extends BaseCaseMerito{
    /**
     * @title: Validate that Maintenance Product popup loads correct data
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate System > Product Maintenance
     *           2. Click Action button of a product
     * @expect:  1. Data on this popup displays correctly
     *           2. Maintenance Details Popup is displayed
     */
    @Test (groups = {"smoke"})
    public void BO_System_ProductMaintenance_Popup_001(){
        log("@title: Validate that Maintenance Product popup loads correct data");
        List<Product> lstProducts = ProductMaintenanceUtils.getProducts();
        Assert.assertTrue(lstProducts.size() > 0, "ERROR: There is no product in Maintenance Product table");
        String productName = lstProducts.get(0).getProductName();
        String status = lstProducts.get(0).getStatus();

        log("Step 1: Navigate System > Product Maintenance");
        ProductMaintenancePage page = backofficeHomePage.navigateProductMaintenance();

        log("Step 2: Click Action button of a product name " + productName);
        MaintenanceDetailsPopup popup = page.action(productName);

        log("Verify 1: Data on this popup displays correctly");
        log("Verify 2: Maintenance Details Popup is displayed");
        Assert.assertTrue(popup.popupMaintenanceDetails.isDisplayed(), "ERROR: popupMaintenanceDetails is not displayed");
        String popupTitle = popup.lblTitle.getText();
        String observeProduct = popup.txtProductName.getAttribute();
        boolean isStatusCorrect = popup.ddbStatus.areOptionsMatched(BOConstants.System.ProductMaintenance.DDB_POPUP_STATUS);
        String observeStatus = popup.ddbStatus.getFirstSelectedOption().toUpperCase();
        Assert.assertTrue(isStatusCorrect, "ERROR: There is at least an item within Status ddb incorrect");
        Assert.assertEquals(popupTitle, BOConstants.System.ProductMaintenance.POPUP_TITLE, String.format("ERROR: The expected popup title is '%s' but found '%s'", BOConstants.System.ProductMaintenance.POPUP_TITLE, popupTitle));
        Assert.assertEquals(observeProduct, productName, String.format("ERROR: The expected product name is '%s' but found '%s'", productName, observeProduct));
        Assert.assertEquals(observeStatus, status, String.format("ERROR: The expected product status is '%s' but found '%s'", status, observeStatus));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Maintenance Product popup is closed when clicking Close button
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate System > Product Maintenance
     *           2. Click Action button of a product
     *           3. Click Close button
     * @expect:  1. Maintenance Product popup is closed when clicking Close button
     */
    @Test (groups = {"regression"})
    public void BO_System_ProductMaintenance_Popup_002(){
        log("@title: Validate that Maintenance Product popup is closed when clicking Close button");
        List<Product> lstProducts = ProductMaintenanceUtils.getProducts();
        Assert.assertTrue(lstProducts.size() > 0, "ERROR: There is no product in Maintenance Product table");
        String productName = lstProducts.get(0).getProductName();

        log("Step 1: Navigate System > Product Maintenance");
        ProductMaintenancePage page = backofficeHomePage.navigateProductMaintenance();

        log("Step 2: Click Action button of a product name " + productName);
        MaintenanceDetailsPopup popup = page.action(productName);

        log("Step 3: Click Close button");
        popup.clickCloseBtn();

        log("Verify 1: Maintenance Product popup is closed when clicking Close button");
        Assert.assertTrue(popup.popupMaintenanceDetails.isInvisible(2), "ERROR: popupMaintenanceDetails is still displayed");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Maintenance Product popup is closed when clicking X icon
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate System > Product Maintenance
     *           2. Click Action button of a product
     *           3. Click X icon
     * @expect:  1. Maintenance Product popup is closed when clicking Close button
     */
    @Test (groups = {"regression"})
    public void BO_System_ProductMaintenance_Popup_003(){
        log("@title: Validate that Maintenance Product popup is closed when clicking X icon");
        List<Product> lstProducts = ProductMaintenanceUtils.getProducts();
        Assert.assertTrue(lstProducts.size() > 0, "ERROR: There is no product in Maintenance Product table");
        String productName = lstProducts.get(0).getProductName();

        log("Step 1: Navigate System > Product Maintenance");
        ProductMaintenancePage page = backofficeHomePage.navigateProductMaintenance();

        log("Step 2: Click Action button of a product name " + productName);
        MaintenanceDetailsPopup popup = page.action(productName);

        log("Step 3: Click X icon");
        popup.clickXIcon();

        log("Verify 1: Maintenance Product popup is closed when clicking X icon");
        Assert.assertTrue(popup.popupMaintenanceDetails.isInvisible(2), "ERROR: popupMaintenanceDetails is still displayed when clicking X icon");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that updating product status is successful
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate System > Product Maintenance
     *           2. Click Action button of a product
     *           3. Update a product status
     * @expect:  1. Updating product status is successful
     */
    @Test (groups = {"regression"})
    public void BO_System_ProductMaintenance_Popup_004(){
        log("@title: Validate that updating product status is successful");
        List<Product> lstProducts = ProductMaintenanceUtils.getProducts();
        Assert.assertTrue(lstProducts.size() > 0, "ERROR: There is no product in Maintenance Product table");
        Product product = lstProducts.get(lstProducts.size()-1);
        String productName = product.getProductName();
        String status = product.getStatus();
        String message = "Auto updates to check";
        String updateStatus = status.toUpperCase().equals(BOConstants.System.ProductMaintenance.DDB_POPUP_STATUS.get(0).toUpperCase()) ? BOConstants.System.ProductMaintenance.DDB_POPUP_STATUS.get(1) : BOConstants.System.ProductMaintenance.DDB_POPUP_STATUS.get(0);

        try {
            log("Step 1: Navigate System > Product Maintenance");
            ProductMaintenancePage page = backofficeHomePage.navigateProductMaintenance();

            log("Step 2: Click Action button of a product name " + productName);
            MaintenanceDetailsPopup popup = page.action(productName);

            log(String.format("Step 3: Update to %s status", updateStatus));
            popup.maintain(updateStatus, message);

            List<ArrayList<String>> lstRecords = page.tblProductMaintenance.getRowsWithoutHeader(lstProducts.size(), false);
            Assert.assertEquals(lstRecords.size(), lstProducts.size(), "ERROR: lstRecords.size() doesn't equal to " + lstProducts.size());
            List<String> productOnTable = lstRecords.get(lstProducts.size()-1);

            log("Verify 1: Updating product status is successful");
            Assert.assertEquals(productOnTable.get(page.colStatus-1), updateStatus, String.format("ERROR: The expected status is '%s' but found '%s'", updateStatus, productOnTable.get(page.colStatus-1)));
            if (updateStatus.equals(BOConstants.System.ProductMaintenance.DDB_POPUP_STATUS.get(1))) {
                Assert.assertEquals(productOnTable.get(page.colMaintenanceMessage-1), message, String.format("ERROR: The expected status is '%s' but found '%s'", message, productOnTable.get(page.colMaintenanceMessage-1)));
            }
            log("INFO: Executed completely");
        } finally {
            log("Post-condition: Update original status of product name " + productName);
            ProductMaintenanceUtils.updateProduct(product, status, "");
        }
    }
}
