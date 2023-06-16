//package membersite.pages.funsport.tabexchange.components.popups;
//
//import com.paltech.driver.DriverManager;
//import com.paltech.element.common.Button;
//import com.paltech.element.common.Label;
//import com.paltech.element.common.Popup;
//import common.MemberConstants;
//import controls.Table;
//import membersite.pages.MarketPage;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyMarketPopup {
//    public Popup popupSportSetting = Popup.xpath("//div[@id='my-bet-markets']");
//    public Label lblTitle = Label.xpath("//div[@id='my-bet-markets']//div[contains(@class,'dialog-header')]");
//    public Button btnCancel = Button.xpath("//div[contains(@class,'dialog-header')]//span[@class='close-dialog']");
//    public Label lblNote = Label.xpath("//td[@id='noteGMT4']");
//    private int totalColMyMarkets = 4;
//    public int colMarketID = 1;
//    public int colMarketStartTime = 2;
//    public int colMarketName = 3;
//    public int colLiability = 4;
//    public Label lblNoRecord = Label.xpath("//td[@class='noRecord']/strong");
//    public Table tbMyMarkets = Table.xpath("//div[@id='my-bet-markets']//table[@class='table table-hover']",totalColMyMarkets);
//
//    public void clickCancelBtn(){
//        btnCancel.click();
//    }
//
//    public MarketPage navigateToMarket(String marketName){
//        int index = getRowMatch(marketName);
//        return navigateToMarket(index);
//    }
//
//    public MarketPage navigateToMarket(int index){
//        if(index <1)
//        {
//            System.out.println("Please input market index >=1 ");
//            return null;
//        }
//        tbMyMarkets.getControlOfCell(1,colMarketName,index,"a").click();
//        DriverManager.getDriver().switchToWindow();
//        return new MarketPage();
//    }
//
//    public List<String> getMarketInfo(int index){
//        return tbMyMarkets.getRowsWithoutHeader(index,false).get(index-1);
//    }
//
//    public List<String> getMarketInfo(String marketName){
//        int index = getRowMatch(marketName)+1;
//        return getMarketInfo(index);
//    }
//
//    public String getMarketURL(int index)
//    {
//        return tbMyMarkets.getControlOfCell(1,colMarketName,index,"a").getAttribute("href");
//    }
//
//    public String getMarketURL(String marketName)
//    {
//        int index = getRowMatch(marketName)+1;
//        if(index <1){
//            System.out.println(String.format("There is no row contain market name %s",marketName));
//            return null;
//        }
//        return tbMyMarkets.getControlOfCell(1,colMarketName,index,"a").getAttribute("href");
//    }
//
//    private int getRowMatch(String marketName)
//    {
//        for(int i = 0, n=tbMyMarkets.getNumberOfRows(false); i<n ;i++)
//        {
//            if(tbMyMarkets.getControlOfCell(1,colMarketName,i+1,"a").getText().contains(marketName))
//            {
//                return i+1;
//            }
//        }
//        System.out.println("There is no market display in the popup ");
//        return -1;
//    }
//
//    public String totalLiability(){
//        String total = "0.00";
//        List<ArrayList<String>> lstRecords = tbMyMarkets.getRowsWithoutHeader(2,false);
//        if (!lstRecords.get(0).get(0).trim().equals(MemberConstants.MyMarketsPopup.NO_RECORD_FOUNDS)) {
//            for (int i = 0; i < lstRecords.size(); i++) {
//                total = String.format("%.2f", Double.parseDouble(total) + Double.parseDouble(lstRecords.get(i).get(colLiability - 1)));
//            }
//        }
//        return total;
//    }
//
//    public void close(){
//        btnCancel.click();
//        popupSportSetting.waitForControlInvisible();
//
//    }
//}
