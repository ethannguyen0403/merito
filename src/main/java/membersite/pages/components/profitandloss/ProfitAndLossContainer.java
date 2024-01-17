package membersite.pages.components.profitandloss;

import java.util.List;

public class ProfitAndLossContainer {


    public void filter(String startDate, String endDate) {
    }

    public boolean verifyProfitLostMatchedWithDetails(int rowIndex) {
        return false;
    }

    private boolean verifyMarketTableProfitLoss() {
        return false;
    }

    private boolean verifyWagerTableProfitLoss() {
        return false;
    }

    private String calculateWagerProfitLoss(String odds, String stake, String type, String status) {
        return "";
    }

    public void waitLoadReport() {
    }

    public String getStartDate() {
        return "";
    }

    public String getEndDate() {
        return "";
    }

    public String getNote() {
        return "";
    }

    public String getLoadReport() {
        return "";
    }

    public List<String> getReportHeader() {
        return null;
    }

    public boolean isExportButtonDisplay() {
        return false;
    }

    public void openBetDetailsOfSportsBook(String sportGameName, String eventName) {}
    public void verifyCommissionProteusSportsBook(double commissionConfig) {}
}
