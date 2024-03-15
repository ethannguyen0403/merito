package agentsite.pages.report.components.transactiondetailspopup;

import agentsite.controls.Row;
import java.util.List;

public class TransactionDetailsPopup {
    protected Row rowTotal = Row.xpath("//table[contains(@class,'ptable table-responsive report')]//tr[@class='ng-star-inserted']");
    protected int rowTotalCol = 8;
    public List<String> getProductsListTab() {
        return null;
    }

//    public ArrayList<String> getTotalRowData() {
//        return null;
//    }

    public boolean isColumnDataMatchedWithTotal(String columnName, List<String> lstLevel) {
        return false;
    }

    public void verifyListOfProductsTabDisplayedCorrect(String productFilterName) {
    }

}
