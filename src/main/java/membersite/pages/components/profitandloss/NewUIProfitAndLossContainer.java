package membersite.pages.components.profitandloss;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import common.MemberConstants;
import controls.DateTimePicker;
import controls.Table;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewUIProfitAndLossContainer extends ProfitAndLossContainer {
    private Label lblStartDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][1]//label");
    private Label lblEndDate = Label.xpath("//div[contains(@class,'custom-bsDatepicker')][2]//label");
    private Label lblNote = Label.xpath("//div[@class='container-title row']/div/span");
    private Button btnExport = Button.xpath("//i[contains(@class,'fas fa-download ico-export')]");
    private Button btnLoadReport = Button.xpath("//button[contains(@class,'btn-report-search')]");
    private Button btnBack = Button.xpath("//i[contains(@class,'fas fa-arrow-left ico-export')]");
    private int colSumaryTotal = 2;
    private int colSportGame = 1;
    private int colProfitLoss = 2;
    private Table tblSport = Table.xpath("//app-profit-group-sport//table", colSumaryTotal);

    private int colMarketTableTotal = 4;
    private int colSettled = 1;
    private int colMarketID = 2;
    private int colMarketName = 3;
    private int colDetailProfitLoss = 4;
    private int colMarketTotal = 2;
    private Table tblMarket = Table.xpath("//app-profit-group-market//table", colMarketTableTotal);

    private int colWagerTotal = 10;
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
    private Table tblWager = Table.xpath("//app-profit-group-betdetail//table", colWagerTotal);
    private Table tblPS38BetDetail = Table.xpath("//app-sportsbook-bet//div[contains(@class,'table-responsive')]//table[contains(@class,'table-sm')]", 12);
    private TextBox txtStartDate = TextBox.xpath(String.format("//label[text()='%s']/following::input[1]", MemberConstants.MyBetsPage.START_DATE));
    private DateTimePicker dtpStartDate = DateTimePicker.xpath(txtStartDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    private TextBox txtEndDate = TextBox.xpath(String.format("//label[text()='%s']/following::input[1]", MemberConstants.MyBetsPage.END_DATE));
    private DateTimePicker dtpEndDate = DateTimePicker.xpath(txtEndDate, "//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");


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
            total = Double.parseDouble(totalProfitLost) + total;
            tblSport.getControlOfCell(1, colSportGame, i + 1, "span[@class='hover hyperlink']").click();
            tblMarket.isTextDisplayed("Total", 3);
            int tblMarketRows = tblMarket.getNumberOfRows(false, false);          //  List<String> lstData = tblMarket.getRow(tblMarketRows,1);
            String totalPLMarketTable = tblMarket.getRow(tblMarketRows, 1).get(1);
            if (!totalProfitLost.equals(totalPLMarketTable)) {
                System.out.println(String.format("ERROR! Total PL in Sport table does not match with Market Table expected is % s but found", totalProfitLost, totalPLMarketTable));
                return false;
            }
           /* if(!verifyMarketTableProfitLoss())
            {
                return false;
            }*/
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
            if (!verifyWagerTableProfitLoss()) {
                return false;
            }

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

    public void waitLoadReport() {
        btnLoadReport.isTextDisplayed(MemberConstants.AccountStatementPage.LOAD_REPORT, 5);
    }

    public String getStartDate() {
        return lblStartDate.getText();
    }

    public String getEndDate() {
        return lblEndDate.getText();
    }

    public String getNote() {
        return lblNote.getText();
    }

    public String getLoadReport() {
        return btnLoadReport.getText();
    }

    public List<String> getReportHeader() {
        return tblSport.getColumnNamesOfTable();
    }

    public boolean isExportButtonDisplay() {
        return btnExport.isDisplayed();
    }

    public void openBetDetailsOfSportsBook(String sportGameName, String eventName) {
        int totalRow = tblSport.getNumberOfRows(false, true);
        int colIndex = tblSport.getColumnIndexByName("Sport/Game");
        for (int i = 0; i < totalRow - 1; i++) {
            Label lblCellValue = (Label) tblSport.getLabelOfCell(1, colIndex, i + 1, "span");
            if(lblCellValue.getText().trim().equalsIgnoreCase(sportGameName)) {
                lblCellValue.click();
            }
        }
        if(tblMarket.isDisplayed()) {
            colIndex = tblMarket.getColumnIndexByName("Event Name");
            totalRow = tblMarket.getNumberOfRows(false, true);
            for (int i = 0; i < totalRow - 1; i++) {
                Label lblCellValue = (Label) tblMarket.getLabelOfCell(1, colIndex, i + 1, "span");
                if(lblCellValue.getText().trim().equalsIgnoreCase(eventName)) {
                    lblCellValue.click();
                }
            }
        }
    }

    public void verifyCommissionProteusSportsBook(double commissionConfig) {
        double expectedComm;
        List<String> lstColumnsName = Arrays.asList("Risk", "To Win", "Commission");
        List<Integer> lstColumnIndex = tblPS38BetDetail.getListColumnIndexByListName(lstColumnsName);
        Label lblToRiskCell = (Label) tblPS38BetDetail.getLabelOfCell(1, lstColumnIndex.get(0) + 1, 1, null);
        Label lblToWinCell = (Label) tblPS38BetDetail.getLabelOfCell(1, lstColumnIndex.get(1) + 1, 1, null);
        Label lblCommissionCell = (Label) tblPS38BetDetail.getLabelOfCell(1, lstColumnIndex.get(2) + 1, 1, null);
        double toRiskValue = Double.valueOf(lblToRiskCell.getText().trim());
        double toWinValue = Double.valueOf(lblToWinCell.getText().trim());
        double commissionValue = Double.valueOf(lblCommissionCell.getText().trim());
        if(toRiskValue <= toWinValue) {
            expectedComm = Math.floor((toRiskValue * commissionConfig) * 1000) / 1000;
        } else {
            expectedComm = Math.floor((toWinValue * commissionConfig) * 1000) / 1000;
        }
        Assert.assertEquals(commissionValue, expectedComm, 0.01, String.format("FAILED! Commission is not correct expected %s actual %s", commissionValue, expectedComm));
    }
}
