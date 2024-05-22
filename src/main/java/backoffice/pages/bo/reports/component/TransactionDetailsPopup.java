package backoffice.pages.bo.reports.component;

import agentsite.controls.Table;
import agentsite.pages.report.components.CashOutHistoryPopup;
import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TransactionDetailsPopup {
    public Tab tabProduct = Tab.xpath("//app-transaction-detail//ul[@class='nav nav-tabs']//li//span");
    int tblReportTotalCol = 18;
    public int colStatus = 8;
    String tblReportXpath = "//app-pnl-transaction-detail//table[contains(@class,'ptable')]";
    public Table tblReport = Table.xpath(tblReportXpath, tblReportTotalCol);

    public List<String> getProductTab() {
        List<String> lstProduct = new ArrayList<>();
        List<WebElement> tabs = tabProduct.getWebElements();
        for (WebElement element : tabs) {
            lstProduct.add(element.getText());
        }
        return lstProduct;
    }

    public CashOutHistoryPopup openCashOutHistoryPopup(int index){
        Label.xpath(tblReport.getxPathOfCell(1, colStatus, index, null)).click();
        return new CashOutHistoryPopup();
    }
}
