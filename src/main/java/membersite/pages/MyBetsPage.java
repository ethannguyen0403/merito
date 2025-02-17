package membersite.pages;

import membersite.objects.Wager;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.mybet.MyBetsContainer;
import membersite.pages.components.ps38betdetail.PS38BetDetail;

import java.util.ArrayList;
import java.util.List;

public class MyBetsPage extends HomePage {


    public MyBetsContainer myBetsContainer;
    public PS38BetDetail ps38BetDetail = new PS38BetDetail();

    public MyBetsPage(String types) {
        super(types);
        myBetsContainer = ComponentsFactory.myBetsContainerObject(types);
    }

    public void clickDownload() {
        myBetsContainer.clickDownload();
        // to wait file is download in 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyProfitLossCorrect(String betID){
        ps38BetDetail.verifyProfitLossCorrect(betID);
    }

    public String getNoDataMesage() {
        return myBetsContainer.getNoRecord();
    }

    public void selectProduct(String product) {
        myBetsContainer.selectProduct(product);
    }

    public String getNote() {
        return myBetsContainer.getNote();
    }

    public String getProduct() {
        return myBetsContainer.getProduct();
    }

    public String getOrderType() {
        return myBetsContainer.getOrderType();
    }

    public String getStartDate() {
        return myBetsContainer.getStartDate();
    }

    public String getEndDate() {
        return myBetsContainer.getEndDate();
    }

    public String getLoadReport() {
        return myBetsContainer.getLoadReport();
    }

    public List<String> getTableHeaders() {
        return myBetsContainer.getTableHeaders();
    }

    public boolean verifyWagerDisplayInReport(String wagerID) {
        return myBetsContainer.verifyWagerDisplayInReport(wagerID);
    }

    public void verifyWagerInfo(Wager wager) {
        myBetsContainer.verifyWagerInfo(wager);
    }

    public void filter(String productName, String orderType) {
        myBetsContainer.filter(productName, orderType, "", "");
        waitPageLoad();
    }

    public void filter(String productName, String orderType, String startDate, String endDate) {
        myBetsContainer.filter(productName, orderType, startDate, endDate);
    }

    public boolean validateFilterStatus(String status) {
        return myBetsContainer.validateFilterStatus(status);
    }

    public List<String> translateProductsActive(List<String> productCode) {
        return myBetsContainer.translateProductsActive(productCode);
    }

    public List<ArrayList<String>> getReportIndex(int index, boolean isMove) {
        return myBetsContainer.getReportIndex(index, isMove);
    }
    public List<String> getReportColumnValue(int rowIndex, String columnName) {
        return myBetsContainer.getReportColumnValue(rowIndex, columnName);
    }

    public void verifyListBetFollowStatus(List<String> lstBet, String status) { myBetsContainer.verifyListBetFollowStatus(lstBet, status);}
    public void verifyListBetNotFollowStatus(List<String> lstBet, String status) { myBetsContainer.verifyListBetNotFollowStatus(lstBet, status);}
}
