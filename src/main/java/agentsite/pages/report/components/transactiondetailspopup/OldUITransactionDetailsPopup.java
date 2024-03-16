package agentsite.pages.report.components.transactiondetailspopup;

import agentsite.controls.MenuTree;
import agentsite.ultils.report.ReportslUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class OldUITransactionDetailsPopup extends TransactionDetailsPopup {
    private int rowTotalCol = 16;
    private MenuTree productTabMenu = MenuTree.xpath("//app-pnl-transaction-detail//ul[contains(@class,'nav-tabs')]", "/li");
    public List<String> getProductsListTab() {
        return productTabMenu.getListSubMenu().stream().sorted().collect(Collectors.toList());
    }

    public ArrayList<String> getTotalRowData() {
        if(rowSummaryTotal.isDisplayed()) {
            return rowSummaryTotal.getRow(rowTotalCol, false);
        } else {
            return rowTotal.getRow(rowTotalCol, false);
        }
    }

    public void verifyListOfProductsTabDisplayedCorrect(String productFilterName) {
        List<String> lstAllProducts = ReportslUtils.getAllProducts(ReportslUtils.getProductActive());
        lstAllProducts.add("Follow Bets");
        List<String> productTabs = getProductsListTab();
        if (productFilterName.equalsIgnoreCase("select all")) {
            Assert.assertTrue(productTabs.containsAll(lstAllProducts), String.format("FAILED! List product tabs %s does not contains all product group %s", productTabs, lstAllProducts));
        } else {
            Assert.assertTrue(productTabs.equals(productFilterName), String.format("FAILED! List product tabs %s does not contains product group %s", productTabs, productFilterName));
        }
    }
}
