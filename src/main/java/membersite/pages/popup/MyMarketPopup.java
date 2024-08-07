package membersite.pages.popup;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import common.MemberConstants;
import controls.Table;
import membersite.pages.HomePage;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.popup.mymarketpopup.MyMarketPopupContainer;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class MyMarketPopup extends HomePage {
    public MyMarketPopupContainer myMarketPopupContainer;
    public Popup popupSportSetting = Popup.xpath("//app-markets-popup");
    public Label lblTitle = Label.xpath("//div[contains(@class,'my-market-header')]/h4");
    public Button btnCancel = Button.xpath("//div[contains(@class,'my-market-header')]/button");
    public Label lblNote = Label.xpath("//td[@id='noteGMT4']");
    public int colMarketID = 1;
    public int colMarketStartTime = 2;
    public int colMarketName = 3;
    public int colLiability = 4;
    public Label lblNoRecord = Label.xpath("//td[@class='text-center']/strong");
    private int totalColMyMarkets = 4;
    public Table tbMyMarkets = Table.xpath("//table[@class='table table-hover table-bordered table-sm']", totalColMyMarkets);
    public MyMarketPopup(String types){
        super(types);
        myMarketPopupContainer = ComponentsFactory.myMarketPopupObject(types);
    }
    public void clickCancelBtn() {
        btnCancel.click();
    }

    public void navigateToMarket(String marketName) {
        int index = getRowMatch(marketName);
        tbMyMarkets.getControlOfCell(1, colMarketName, index, "a").click();
    }

    public List<String> getMarketInfo(int index) {
        lblNoRecord.waitForControlInvisible(1,2);
        return tbMyMarkets.getRowsWithoutHeader(index, false).get(index - 1);
    }

    public List<String> getMarketInfo(String marketName) {
        int index = getRowMatch(marketName) + 1;
        return getMarketInfo(index);
    }

    public String getMarketURL(int index) {
        return tbMyMarkets.getControlOfCell(1, colMarketName, index, "a").getAttribute("href");
    }

    public String getMarketURL(String marketName) {
        int index = getRowMatch(marketName) + 1;
        if (index < 1) {
            System.out.println(String.format("There is no row contain market name %s", marketName));
            return null;
        }
        return tbMyMarkets.getControlOfCell(1, colMarketName, index, "a").getAttribute("href");
    }

    private int getRowMatch(String marketName) {
        for (int i = 0, n = tbMyMarkets.getNumberOfRows(false); i < n; i++) {
            if (tbMyMarkets.getControlOfCell(1, colMarketName, i + 1, "a").getText().contains(marketName)) {
                return i + 1;
            }
        }
        System.out.println("There is no market display in the popup ");
        return -1;
    }

    public String totalLiability() {
        String total = "0.00";
        int numberOfRow = tbMyMarkets.getNumberOfRows(false);
        List<ArrayList<String>> lstRecords = tbMyMarkets.getRowsWithoutHeader(numberOfRow, false);
        if (lstRecords.get(0).get(0).trim().equals(MemberConstants.MyMarketsPopup.NO_RECORD_FOUNDS)) {
            return total;
        } else {
            for (int i = 0; i < lstRecords.size(); i++) {
                total = String.format("%.2f", Double.valueOf(total) + Double.valueOf(lstRecords.get(i).get(colLiability - 1)));
            }
        }
        return total;
    }

    public void close() {
        btnCancel.click();
        popupSportSetting.waitForControlInvisible();

    }


}
