package agentsite.pages.report.components;

import agentsite.controls.MenuTree;
import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.report.components.transactiondetailspopup.TransactionDetailsPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

import java.util.ArrayList;
import java.util.List;

public class TransactionDetailsPopupPage extends HomePage {
    public int tblReportTotalCol = 18;
    public int colUsername = 1;
    public int colLoginId = 2;
    public int colSettled = 3;
    public int colDescription = 4;
    public int colType = 5;
    public int colOdds = 6;
    public int colPlayerStake = 7;
    public int colStatus = 13;
    public int colProfitLossOriginal = 9;
    public MenuTree productTabMenu = MenuTree.xpath("//app-pnl-transaction-detail//ul[contains(@class,'nav-tabs')]", "/li");
    Popup popup = Popup.xpath("//div[contains(@class,'multiProductDialog'])");
    Button btnFullScreen = Button.xpath("//div[contains(@class,'modal-header')]/button[contains(@class,'fullScreen')]");
    Button btnClosePopup = Button.xpath("//div[contains(@class,'modal-header')]//button[@class='close']");
    Button btnClose = Button.xpath("//button[contains(@class,'btn-cancel')]");
    Label lblTitle = Label.xpath("//div[@class='otp-dialog ng-scope']//div[@class='modal-header']/div[@class='ng-binding']");
    String tblReportXpath = "//div[@class='modal-content']//table[contains(@class,'ptable')]";
    public Table tblReport = Table.xpath(tblReportXpath, tblReportTotalCol);
    Row taxRow = Row.xpath("//table[contains(@class,'table-responsive')]//tr[contains(@class,'TAX_INFO')]");
    Row rowTotal = Row.xpath("//table[contains(@class,'ptable table-responsive report')]//tr[@class='ng-star-inserted']");
    Label lblExport = Label.id("export-title");
    Icon icExport = Icon.xpath("//span[@class='exportExcel enabled']");
    Label lblDisplayItem = Label.xpath("//div[contains(@class,'displaying-items')]");
    private int staticColTotal = 9;
    private int rowTotalCol = 8;
    public TransactionDetailsPopup transactionDetailsPopup;
    public TransactionDetailsPopupPage(String types) {
        super(types);
        transactionDetailsPopup = ComponentsFactory.transactionDetailsPopup(types);
    }
    public List<String> getProductsListTab() {
        return transactionDetailsPopup.getProductsListTab();
    }
    public void closePopup() {
        btnClose.click();
    }

    public void fullScreenPopup() {
        btnFullScreen.click();
    }

    public ArrayList<String> getTotalRowData() {
        return transactionDetailsPopup.getTotalRowData();
    }

    public Table defineReportCol(List<String> levelList) {
        int levelCount = levelList.size();
        tblReportTotalCol = staticColTotal + (levelCount * 3);
        return Table.xpath(tblReportXpath, tblReportTotalCol);
    }

    public boolean isColumnDataMatchedWithTotal(String columnName, List<String> lstLevel) {
        Table table = defineReportCol(lstLevel);
        fullScreenPopup();
//        int col = table.getColumnIndexByName(columnName) + 1;// there is Cashout column is hide so need to inclue 1 more column
        int col = table.getColumnIndexByName(columnName);
        if (col == -1) {
            System.out.println(String.format("Column name %s not be found in the table", columnName));
            return false;
        }
        List<String> lstData = table.getColumn(col, true);
        double memberResult = 0.00;
        double totalResult = Double.valueOf(getTotalRowData().get(col - staticColTotal));
        for (int i = 0; i < lstData.size(); i++) {
            String value = lstData.get(i);
            if (value.isEmpty() || value.equalsIgnoreCase("-"))
                continue;
            else
                memberResult = memberResult + Double.valueOf(value);
        }
        //format to 2f string to avoid case decimal place too long
        return String.format("%.2f", totalResult) == String.format("%.2f", memberResult);
    }


    public double sumPlayerStake() {
        waitingLoadingSpinner();
        int col = tblReport.getColumnIndexByName("Player Turnover");
        double totalPlayerStake = 0.0;
        List<ArrayList<String>> data = tblReport.getRowsWithoutHeader(false);
        for (int i = 0; i < data.size() - 2; i++) {
            String stake = data.get(i).get(col - 1);
            if (stake.contains("-"))
                continue;
            else
                totalPlayerStake = totalPlayerStake + Double.parseDouble(stake);
        }
        return totalPlayerStake;
    }

    /**
     * This verify Transaction detail with Desciption column contain expected Sport and Market and Sum Turnover is correct
     *
     * @param sport
     * @param lstSummaryMarketTurnoverAndWinLoss
     * @return
     */
    public boolean verifyDataMatchSummary(String sport, ArrayList<String> lstSummaryMarketTurnoverAndWinLoss) {
        List<ArrayList<String>> data = tblReport.getRowsWithoutHeader(false);
        String descriptionCell;
        String market = lstSummaryMarketTurnoverAndWinLoss.get(0);
        double totalPlayerStake = 0.0;
        double turnoverSummary = Double.parseDouble(lstSummaryMarketTurnoverAndWinLoss.get(1));
        for (int i = 0; i < data.size(); i++) {
            descriptionCell = data.get(i).get(colDescription - 1);
            if (!descriptionCell.contains(sport) && !descriptionCell.contains(market)) {
                System.out.println(String.format("FAILED! Expected Description col contain Sport: %s and Market:%s but found %s", sport, market, descriptionCell));
                return false;
            }

            if (data.get(i).get(0).equalsIgnoreCase("Total")) {
                break;
            }
            String stake = data.get(i).get(colPlayerStake - 1);
            if (stake.contains("-"))
                continue;
            //   String stake = data.get(i).get(colPlayerStake - 1);
            totalPlayerStake = totalPlayerStake + Double.parseDouble(stake);
        }
        if (totalPlayerStake != turnoverSummary) {
            System.out.println(String.format("FAILED! Expected Turnover in summary page of sport: %s market:%s is %.2f but sum in detail transaction is %.2f", sport, market, turnoverSummary, totalPlayerStake));
            return false;
        }
        closePopup();
        return true;
    }

    public ArrayList<String> getMarketTax() {
        return taxRow.getRow(tblReportTotalCol, false);
    }

    public void clickCloseButton() {
        btnClose.click();
    }

    public void verifyListOfProductsTabDisplayedCorrect(String productFilterName) {
        transactionDetailsPopup.verifyListOfProductsTabDisplayedCorrect(productFilterName);
    }

    public CashOutHistoryPopup openCashOutHistoryPopup(int index){
        Label.xpath(tblReport.getxPathOfCell(1, colStatus, index, "span")).click();
        return new CashOutHistoryPopup();
    }

}
