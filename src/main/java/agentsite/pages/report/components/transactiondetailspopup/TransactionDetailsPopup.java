package agentsite.pages.report.components.transactiondetailspopup;

import agentsite.controls.Row;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailsPopup {
    protected Row rowTotal = Row.xpath("//table[contains(@class,'ptable table-responsive report')]//tr[@class='ng-star-inserted']");
    protected Row rowSummaryTotal = Row.xpath("//table[contains(@class,'ptable table-responsive report')]//tr[@class='summary ng-star-inserted']");
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
    public ArrayList<String> getTotalRowData() {
        return null;
    }
}
