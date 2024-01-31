package membersite.pages;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import common.MemberConstants;
import controls.DateTimePicker;
import controls.Table;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.profitandloss.ProfitAndLossContainer;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ProfitAndLossPage extends HomePage {

    public Label lblNoRecord = Label.xpath("//table[contains(@class,'table-sm')]//tbody/tr[1]/td[@class='text-center']");
    public int colSportGame = 1;
    public int colMarketName = 3;
    public ProfitAndLossContainer profitAndLossContainer;
    private Label lblStartDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][1]//label");
    private Label lblEndDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][2]//label");
    private Label lblNote = Label.xpath("//div[@class='container-title row']/div/span");
    private Button btnExport = Button.xpath("//i[contains(@class,'fa-download')]");
    private Button btnLoadReport = Button.xpath("//button[contains(@class,'btn-report-search')]");
    private Button btnBack = Button.xpath("//i[contains(@class,'fa-arrow-left')]");
    private int colSumaryTotal = 2;
    public Table tblSport = Table.xpath("//app-profit-group-sport//table", colSumaryTotal);
    private int colProfitLoss = 2;
    private int colMarketTableTotal = 4;
    public Table tblMarket = Table.xpath("//app-profit-group-market//table", colMarketTableTotal);
    private int colSettled = 1;
    private int colMarketID = 2;
    private int colDetailProfitLoss = 4;
    private int colMarketTotal = 2;
    private int colWagerTotal = 10;
    public Table tblWager = Table.xpath("//app-profit-group-betdetail//table", colWagerTotal);
    private int colBetID = 1;
    private int colPlaceDate = 2;
    private int colSelection = 3;
    private int colMatchOdds = 4;
    private int colStake = 5;
    private int colType = 6;
    private int colStatus = 7;
    private int colWagerPL = 8;
    private int colTax = 9;
    private int colNet = 10;
    private int colTotal = 1;
    private int colWagerTotalPL = 2;
    private int colWagerTotalTax = 3;
    private int colWagerTotalNet = 4;
    private TextBox txtStartDate = TextBox.xpath(String.format("//label[text()='%s']/following::input[1]", MemberConstants.MyBetsPage.START_DATE));
    private DateTimePicker dtpStartDate = DateTimePicker.xpath(txtStartDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    private TextBox txtEndDate = TextBox.xpath(String.format("//label[text()='%s']/following::input[1]", MemberConstants.MyBetsPage.END_DATE));
    private DateTimePicker dtpEndDate = DateTimePicker.xpath(txtEndDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");

    public ProfitAndLossPage(String types) {

        super(types);
        profitAndLossContainer = ComponentsFactory.profitAndLossContainerObject(types);
    }

    public void clickDownload() {
        btnExport.click();
        // to wait file is download in 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickBackButton() {
        btnBack.click();
    }

    public void clickFirstSport() {
        if (lblNoRecord.isDisplayed()) {
            System.out.println("Skip step as there is no data");
            return;
        }
        tblSport.getControlOfCell(1, colSportGame, 1, "span[@class='hover hyperlink']").click();
    }

    public void clickFirstEvent() {
        if (lblNoRecord.isDisplayed()) {
            System.out.println("Skip step as there is no data");
            return;
        }
        tblMarket.getControlOfCell(1, colMarketName, 1, "span[@class='hover hyperlink']").click();
    }

    public void filter(String startDate, String endDate) {
        tblSport.isDisplayed();
        tblSport.getColumnNamesOfTable();
        dtpStartDate.selectDate(startDate, "yyyy-MM-dd");
        dtpEndDate.selectDate(endDate, "yyyy-MM-dd");
        btnLoadReport.click();
        btnLoadReport.isTextDisplayed(MemberConstants.MyBetsPage.LOAD_REPORT, 5);
    }

    public boolean verifyProfitLostMatchedWithDetails(int rowIndex) {
        List<ArrayList<String>> lst = tblSport.getRowsWithoutHeader(rowIndex, false);
        if (lst.get(0).get(0).equals(MemberConstants.NO_RECORD_FOUND)) {
            System.out.println("INFO! By passed VP as no data display");
            return true;
        }
        double total = 0;
        int totalSummaryRow = lst.size();
        for (int i = 0, n = totalSummaryRow - 1; i < n; i++) {
            String totalProfitLost = lst.get(i).get(colProfitLoss - 1);
            total = Math.floor((Double.parseDouble(totalProfitLost) + total)  * 100) / 100;
            tblSport.getControlOfCell(1, colSportGame, i + 1, "span[@class='hover hyperlink']").click();
            // Add wait time to wait data load => Will improve when finding the solution
            //tblMarket.isTextDisplayed("Total", 3);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int tblMarketRows = tblMarket.getNumberOfRows(false, false);          //  List<String> lstData = tblMarket.getRow(tblMarketRows,1);
            String totalPLMarketTable = tblMarket.getRow(tblMarketRows, 1).get(1);
            if (!totalProfitLost.equals(totalPLMarketTable)) {
                System.out.println(String.format("ERROR! Total PL in Sport table does not match with Market Table expected is % s but found", totalProfitLost, totalPLMarketTable));
                return false;
            }
//            if (!verifyMarketTableProfitLoss()) {
//                return false;
//            }
            btnBack.click();
        }
        double totalActual = Double.parseDouble(lst.get(totalSummaryRow - 1).get(colProfitLoss - 1));
        if (!(total == totalActual)) {
            System.out.println(String.format("ERROR! Sum Sport Profit/Los on Sport table does not match with the Total. Summay is %f bug display %f", total, totalActual));
            return false;
        }
        return true;
    }

    private boolean verifyMarketTableProfitLoss() {
        int totalSummaryRow = tblMarket.getNumberOfRows(false, false);
        List<ArrayList<String>> lstDetail = tblMarket.getRowsWithoutHeader(totalSummaryRow - 1, false);
        double total = 0;
        List<String> lstDetailTotalRow = tblMarket.getRow(2, 1);
        // String tt = lstDetailTotalRow.get(colMarketTotal-1);
        for (int i = 0; i < lstDetail.size(); i++) {
            String profitLoss = lstDetail.get(i).get(colDetailProfitLoss - 1);
            total = Double.parseDouble(profitLoss) + total;
            tblMarket.getControlOfCell(1, colMarketName, i + 1, "span[contains(@class,'hover hyperlink')]").click();
            tblWager.isTextDisplayed("Total", 3);
            int wagerTableTotalRow = tblWager.getNumberOfRows(false, false);
            String totalNet = tblWager.getRow(wagerTableTotalRow).get(colWagerTotalNet - 1);
            if (!profitLoss.equals(totalNet)) {
                System.out.println(String.format("ERROR! Profit in row %s in Market Table not match with total in Wager table details expected %s but found %s", i, profitLoss, totalNet));
                return false;
            }
//            if (!verifyWagerTableProfitLoss()) {
//                return false;
//            }

        }
        btnBack.click();
        if (!(total == Double.parseDouble(lstDetailTotalRow.get(colMarketTotal - 1)))) {
            System.out.println(String.format("ERROR! Market Table - Total row is equal summary the profit and loss of each sport: %s", false));
            return false;
        }
        return true;
    }

    private boolean verifyWagerTableProfitLoss() {
        int totalSummaryRow = tblWager.getNumberOfRows(false, false);
        List<ArrayList<String>> lstDetail = tblWager.getRowsWithoutHeader(totalSummaryRow, false);
        double total = 0;
        for (int i = 0; i < totalSummaryRow - 1; i++) {
            String stake = lstDetail.get(i).get(colStake - 1);
            String odd = lstDetail.get(i).get(colMatchOdds - 1);
            String type = lstDetail.get(i).get(colType - 1);
            String status = lstDetail.get(i).get(colStatus - 1);
            String wagerPL = calculateWagerProfitLoss(odd, stake, type, status);
            String profitLoss = lstDetail.get(i).get(colWagerPL - 1);

            if (!wagerPL.equals(profitLoss)) {
                System.out.println(String.format("ERROR! Calculate profit/Loss at row %d is incorrect, expected %s is but display %s", i + 1, wagerPL, profitLoss));
                return false;
            }
            total = Double.parseDouble(profitLoss) + total;

        }
        String totalPL = lstDetail.get(totalSummaryRow - 1).get(colWagerTotalPL - 1);
        String tax = lstDetail.get(totalSummaryRow - 1).get(colWagerTotalTax - 1);
        String net = lstDetail.get(totalSummaryRow - 1).get(colWagerTotalNet - 1);
        if (!(total == Double.parseDouble(totalPL)) ? true : false) {
            System.out.println("ERROR! Total row in Market Table is NOT equal summary the profit and loss of each wagers");
            return false;
        }
        String netExpected = String.format("%.2f", Double.parseDouble(totalPL) + Double.parseDouble(tax));
        if (!netExpected.equals(net)) {
            System.out.println(String.format("ERROR! Wager Table - Total row: Net does Not equal TotalPL - Tax", netExpected, net));
            return false;
        }
        btnBack.click();
        return true;
    }

    private String calculateWagerProfitLoss(String odds, String stake, String type, String status) {
        boolean isBack = type.equalsIgnoreCase("back") ? true : false;
        boolean isWon = status.equalsIgnoreCase("Won") ? true : false;
        Double _odd = Double.parseDouble(odds);
        Double _stake = Double.parseDouble(stake);
        double profit = isBack ? _stake * (_odd - 1) : _stake;
        double loss = isBack ? _stake : _stake * (_odd - 1);
        if (isWon)
            return String.format("%.2f", profit);
        return String.format("%.2f", loss * (-1));
    }

    public void openBetDetailsOfSportsBook(String sportGameName, String eventName) {
        profitAndLossContainer.openBetDetailsOfSportsBook(sportGameName, eventName);
    }
    public void verifyCommissionProteusSportsBook(double commissionConfig) {
        profitAndLossContainer.verifyCommissionProteusSportsBook(commissionConfig);
    }

}
