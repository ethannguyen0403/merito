package agentsite.pages.report.components.transactiondetailspopup;

import agentsite.controls.MenuTree;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static common.AGConstant.Report.ProfitAndLoss.LST_PRODUCT_GROUP;

public class NewUITransactionDetailsPopup extends TransactionDetailsPopup {
    private MenuTree productTabMenu = MenuTree.xpath("//app-pnl-transaction-group//ul[contains(@class,'nav-tabs')]", "/li");
    public List<String> getProductsListTab() {
        return productTabMenu.getListSubMenu().stream().sorted().collect(Collectors.toList());
    }

//    public ArrayList<String> getTotalRowData() {
//        return rowTotal.getRow(rowTotalCol, false);
//    }

    public void verifyListOfProductsTabDisplayedCorrect(String productFilterName) {
        List<String> productTabs = getProductsListTab();
        if (productFilterName.equalsIgnoreCase("select all")) {
            Assert.assertTrue(productTabs.containsAll(LST_PRODUCT_GROUP), String.format("FAILED! List product tabs %s does not contains all product group %s", productTabs, LST_PRODUCT_GROUP));
        } else {
            Assert.assertTrue(productTabs.equals(productFilterName), String.format("FAILED! List product tabs %s does not contains product group %s", productTabs, productFilterName));
        }
    }
}
