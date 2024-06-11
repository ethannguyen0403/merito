package membersite.pages.components.ps38betdetail;

import com.paltech.element.common.Label;
import controls.Table;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PS38BetDetail {
    public Table tblPS38BetDetail = Table.xpath("//app-sportsbook-bet//div[contains(@class,'table-responsive')]//table[contains(@class,'table-sm')]", 12);
    public String tblCashOutHistoryXpath = "//app-sportsbook-bet//div[contains(@class,'table-responsive')]//table[contains(@class,'table-sm')]//table[@class='mt-2 table ng-star-inserted']";
     int colStatus = 10;
     int colRiskAmount = 6;
     int colProfitLoss = 9;
     int colBetID = 1;

    public void verifyProfitLossCorrect(String betID){
        int rowIndex = findRowIndexBaseOnBetID(betID);
        List<String> rowData = tblPS38BetDetail.getRow(1, rowIndex);
        List<ArrayList<String>> dataListHistory = expandCashOutHistoryByIndex(rowIndex);
        Assert.assertTrue(Objects.nonNull(dataListHistory), "FAILED! Cash out history is not displayed");
        double riskAmount = Double.valueOf(rowData.get(colRiskAmount-1));
        double cashAmount = Double.valueOf(dataListHistory.get(2).get(1));
        Assert.assertEquals(rowData.get(colProfitLoss-1), String.format("%,.2f", cashAmount - riskAmount) , "FAILED! Value of Profit/Loss is not correct with row index: " + rowIndex);
    }

    private int findRowIndexBaseOnBetID(String betID){
        int rowIndex = 1;
        while (true) {
            Label betIDLabel = Label.xpath(tblPS38BetDetail.getControlXPathOfCell(1, colBetID, rowIndex, null));
            if (betIDLabel.isDisplayed()) {
                if (betIDLabel.getText().contains(betID)) {
                    System.out.println("FOUND correct betID at row index: " + rowIndex);
                    break;
                } else {
                    rowIndex++;
                    continue;
                }
            } else {
                System.err.println("NOT found any correct betID");
                rowIndex = -1;
                break;
            }
        }
        return rowIndex;
    }

    public void verifyCashOutHistoryCorrect(String betID){
        int rowIndex = findRowIndexBaseOnBetID(betID);
        Assert.assertEquals(tblPS38BetDetail.getControlOfCell(1, colStatus, rowIndex, "span").getText(), "Cashed Out", "FAILED! Cashed out text is not displayed");
        verifyProfitLossCorrect(betID);
    }

    public List<ArrayList<String>> expandCashOutHistoryByIndex(String betID) {
        return expandCashOutHistoryByIndex(findRowIndexBaseOnBetID(betID));
    }

    public List<ArrayList<String>> expandCashOutHistoryByIndex(int index) {
        tblPS38BetDetail.getControlOfCell(1, 10, index, "button").click();
        List<ArrayList<String>> dataList = new ArrayList<>();
        String tblCashOutXpath = String.format("(%s)[%d]", tblCashOutHistoryXpath, index);
        Table.xpath(tblCashOutXpath, 3).isDisplayed();
        // The Cashed out history table is displayed with 3 row and 3 colum data
        for (int i = 1; i <= 3; i++) {
            ArrayList<String> data = new ArrayList<>();
            String targetRowXpath = String.format("%s//tr[%d]", tblCashOutXpath, i);
            if (i == 1) {
                data.add(Label.xpath(targetRowXpath).getText().trim());
                dataList.add(data);
                continue;
            } else {
                for (int j = 1; j <= 3; j++) {
                    String targetColXpath = String.format("%s//td[%d]", targetRowXpath, j);
                    data.add(Label.xpath(targetColXpath).getText().trim());
                }
                dataList.add(data);
            }
        }
        return dataList;
    }
}
