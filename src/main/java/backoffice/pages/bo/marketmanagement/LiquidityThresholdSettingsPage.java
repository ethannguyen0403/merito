package backoffice.pages.bo.marketmanagement;

import backoffice.controls.Table;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo._components.AppConfirmPopup;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class LiquidityThresholdSettingsPage extends HomePage {
    public TextBox txtSearchSport = TextBox.xpath("//input[@placeholder='Search sport']");
    public TextBox txtSearchMarketType = TextBox.xpath("//input[@placeholder='Search Market Type']");
    public StaticTable tblSport = StaticTable.xpath("//div[contains(@class,'pane-left')]", "div[contains(@class,'list-groupb')]", "div[contains(@class,'list-group-itemb')]"
            , "span[@class='ms-1']", 1);
    public Table tblMarketType = Table.xpath("//table[contains(@class,'table table-striped')]", 3);
    public String inputFirstNonLiveXpath =   tblMarketType.getControlOfCell(1, 2,  1, "input").getLocator().toString().replace("By.xpath: ", "");
    public String inputFirstLiveXpath =   tblMarketType.getControlOfCell(1, 3,  1, "input").getLocator().toString().replace("By.xpath: ", "");
    public AppConfirmPopup popup = AppConfirmPopup.xpath("//app-confirm");
    public Table tblDetail = Table.xpath("//app-show-details-dialog//table", 4);
    int colMarketType = 1;
    int colNonlive = 2;
    int colLive = 3;

    public void searchSport(String sportName) {
        txtSearchSport.sendKeys(sportName);
    }

    public String getFirstNonLiveValue(){
        return TextBox.xpath(inputFirstNonLiveXpath).getAttribute("value").trim();
    }

    public void searchMarketType(String marketType) {
        txtSearchMarketType.sendKeys(marketType);
        txtSearchMarketType.type(false, Keys.ENTER);
        tblMarketType.waitForAttributeChange("innerHTML", marketType, 3);
    }

    public void selectSport(String sportName) {
        List<String> lstSport = tblSport.getColumn(1, true);
        Link lnk;
        for (int i = 0; i < lstSport.size(); i++) {
            if (lstSport.get(i).equals(sportName)) {
                selectSport(i + 1);
            }
        }
    }

    public void selectSport(int index) {
        Link lnk;
        lnk = (Link) tblSport.getControlOfCell(1, 1, index, null);
        lnk.scrollToThisControl(false);
        lnk.click();
        // waitSpinIcon();
        return;
    }

    public String setThreshold(String sport, String marketType, String nonLive, String live){
        String successMessage = "";
        searchSport(sport);
        selectSport(1);
        searchMarketType(marketType);
        waitSpinIcon();
        setThreshold(marketType, nonLive, live);
        popup.confirm();
        successMessage = new AlertMessageBox().getSuccessAlert();
        return successMessage;
    }

    public void setThreshold(String marketType, String nonLive, String live) {
        List<String> lstMarketType = tblMarketType.getColumn(1, false);
        TextBox txt;
        for (int i = 0; i < lstMarketType.size(); i++) {
            if (lstMarketType.get(i).equalsIgnoreCase(marketType)) {
                if (!nonLive.isEmpty()) {
                    String xpath = tblMarketType.getControlOfCell(1, 2, i + 1, "input").getLocator().toString().replace("By.xpath: ", "");
                    txt = TextBox.xpath(xpath);
                    txt.type(false, Keys.CONTROL + "A" + Keys.DELETE);
                    txt.type(false, nonLive);
//                    txt.sendKeys(nonLive);
                    txt.type(false, Keys.ENTER);
                    popup.isDisplayed(2);
                }
                if (!live.isEmpty()) {
                    String xpath = tblMarketType.getControlOfCell(1, 3, i + 1, "input").getLocator().toString().replace("By.xpath: ", "");
                    txt = TextBox.xpath(xpath);
                    //Handle for case that old value was not removed when using clear action
                    txt.type(false, Keys.CONTROL + "A" + Keys.DELETE);
                    txt.type(false, live);
//                    txt.sendKeys(live);
                    txt.type(false, Keys.ENTER);
                    popup.isDisplayed(2);
                }
                return;
            }
        }

    }

    private int getMarketTypeIndex(String marketType) {
        int i = 1;
        Label lblMarketType;
        while (true) {
            lblMarketType = Label.xpath(tblMarketType.getxPathOfCell(1, colMarketType, i, null));
            if (!lblMarketType.isDisplayed())
                return 0;
            if (lblMarketType.getText().equalsIgnoreCase(marketType)) {
                return i;
            }
            i = i + 1;
        }
    }

    public List<String> getThreshold(String sportName, String marketType) {
        searchSport(sportName);
        selectSport(sportName);
        searchMarketType(marketType);
        List<String> lstThreshold = new ArrayList<>();
        int index = getMarketTypeIndex(marketType);
        if (index == 0)
            return null;
        lstThreshold.add(TextBox.xpath(tblMarketType.getxPathOfCell(1, colNonlive, index, "input")).getText());
        lstThreshold.add(TextBox.xpath(tblMarketType.getxPathOfCell(1, colLive, index, "input")).getText());
        return lstThreshold;
    }

    public void verifyXIcon(String marketType, boolean live, boolean nonLive) {
        int index = getMarketTypeIndex(marketType);
        if (live && nonLive) {
            //i: colum index of live and nonLive
            for (int i = colLive; i <= colNonlive; i++) {
                Label xIcon = Label.xpath(tblMarketType.getxPathOfCell(1, i, index, "span[@class='icon-remove']"));
                Assert.assertTrue(xIcon.isDisplayed(), "FAILED! X icon is not display with colum index: " + i);
            }
        } else if (live != nonLive) {
            Label xIcon = live ? Label.xpath(tblMarketType.getxPathOfCell(1, colLive, index, "span[@class='icon-remove']")) :
                    Label.xpath(tblMarketType.getxPathOfCell(1, colNonlive, index, "span[@class='icon-remove']"));
            Assert.assertTrue(xIcon.isDisplayed(), "FAILED! X icon is not display");
        } else {
            throw new AssertionError("FAILED! Either Live or NonLive should be true");
        }
    }

    public void verifyDetailLink(String marketType) {
        int index = getMarketTypeIndex(marketType);
        Label detailLink = Label.xpath(tblMarketType.getxPathOfCell(1, 4, index, "a"));
        Assert.assertTrue(detailLink.isDisplayed(), "FAILED! Detail link is not display");
    }

    public void clickOnXIcon(String marketType, boolean isLive){
        int index = getMarketTypeIndex(marketType);
        Label xIcon = isLive ? Label.xpath(tblMarketType.getxPathOfCell(1, colLive, index, "span[@class='icon-remove']")) :
                Label.xpath(tblMarketType.getxPathOfCell(1, colNonlive, index, "span[@class='icon-remove']"));
       xIcon.click();
    }

    public void clickOnDetailLink(String marketType){
        int index = getMarketTypeIndex(marketType);
        Label.xpath(tblMarketType.getxPathOfCell(1, 4, index, "a")).click();
        waitSpinIcon();
    }

    public List<String> getLogTimeDetailTable(){
        return tblDetail.getColumn(1, true);
    }

    public boolean isDetailTableSortDesByTime(){
       List<Long> milliList = new ArrayList<>();
       for(String dateTime: getLogTimeDetailTable()){
           try{
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
               Date date = sdf.parse(dateTime);
               milliList.add(date.getTime());
           }catch(ParseException e){
           }
       }
       return isSortedDescending(milliList);
    }

    private boolean isSortedDescending(List<Long> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public void clearThresholdSetting(String marketType) {
        searchMarketType(marketType);
        List<String> lstMarketType = tblMarketType.getColumn(1, false);
        for (int i = 0; i < lstMarketType.size(); i++) {
            if (lstMarketType.get(i).equalsIgnoreCase(marketType)) {
                BaseElement cellValueLive = tblMarketType.getControlOfCell(1, 2, i + 1, "span[@class='icon-remove']");
                BaseElement cellValueNonLive = tblMarketType.getControlOfCell(1, 3, i + 1, "span[@class='icon-remove']");
                if(cellValueLive.isDisplayed()) {
                    cellValueLive.click();
                    popup.isDisplayed(2);
                    popup.confirm();
                }
                if(cellValueNonLive.isDisplayed()) {
                    cellValueNonLive.click();
                    popup.isDisplayed(2);
                    popup.confirm();
                }
            }
        }

    }
}
