package backoffice.pages.bo.marketmanagement;

import backoffice.controls.Table;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo._components.AppConfirmPopup;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.SPORT_SOCCER;

public class LiquidityThresholdSettingsPage extends HomePage {
    public TextBox txtSearchSport = TextBox.xpath("//input[@placeholder='Search sport']");
    public TextBox txtSearchMarketType = TextBox.xpath("//input[@placeholder='Search Market Type']");
    public StaticTable tblSport = StaticTable.xpath("//div[contains(@class,'pane-left')]", "div[@class='list-groupb']", "div[contains(@class,'list-group-itemb')]" +
            "", "span[@class='ml-1']", 1);
    public Table tblMarketType = Table.xpath("//table[contains(@class,'table table-striped')]", 3);
    public String inputFirstNonLiveXpath =   tblMarketType.getControlOfCell(1, 2,  1, "input").getLocator().toString().replace("By.xpath: ", "");
    public String inputFirstLiveXpath =   tblMarketType.getControlOfCell(1, 3,  1, "input").getLocator().toString().replace("By.xpath: ", "");
    public AppConfirmPopup popup = AppConfirmPopup.xpath("//app-comfirm-dialog");
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

    public void setThreshold(String sport, String marketType, String nonLive, String live){
        searchSport(sport);
        searchSport(SPORT_SOCCER);
        selectSport(1);
        searchMarketType(marketType);
        waitSpinIcon();
        setThreshold(marketType, nonLive, live);
        popup.confirm();
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

}
