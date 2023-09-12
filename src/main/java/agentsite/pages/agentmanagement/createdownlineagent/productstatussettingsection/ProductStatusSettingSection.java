package agentsite.pages.agentmanagement.createdownlineagent.productstatussettingsection;

import agentsite.controls.Table;
import agentsite.pages.agentmanagement.edituser.EditMarketPopup;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductStatusSettingSection {
    private int totalSportCol = 40;
    public EditMarketPopup editMarketPopup = EditMarketPopup.xpath("//app-blocking-setting");
    public CheckBox chbLive = CheckBox.id("live");
    public CheckBox chbNoneLive = CheckBox.id("nonlive");
    public Table tblSportTable = Table.xpath("//div[contains(@class,'marketSettingWrapper')]//table[contains(@class,'sportTable')]", totalSportCol);
    String cbSportXPath = "//td[contains(@class,'betTitle back')]//label[@title='%s']/input";
    String cbProdudctXpath = ".//span[text()='%s']/../input[@type='checkbox']";
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }
    private Label lblProductSettingTitle = Label.xpath("//div[text()='Product Settings']");

    public String getProductSettingSectionTitle() {
        return lblProductSettingTitle.getText().trim();
    }


    public List<String> getExchangeSportList() {
        return tblSportTable.getHeaderNameOfRows();
    }
    public void updateLiveNonLive(boolean isLive, boolean isNonLive) {
        boolean isLiveActive = true;
        if (Objects.isNull(chbLive.getAttribute("checked")))
            isLiveActive = false;
        boolean isNoneLiveActive = true;
        if (Objects.isNull(chbNoneLive.getAttribute("checked")))
            isNoneLiveActive = false;
        if (isLiveActive != isLive)
            chbLive.click();
        if (isNoneLiveActive != isNonLive)
            chbNoneLive.click();
    }

    public void updateSport(String sportName, boolean isActive) {
        CheckBox chbSport = CheckBox.xpath(String.format(cbSportXPath, sportName));
        boolean isCheck = chbSport.isSelected();
        if (!isCheck == isActive)
            chbSport.click();
    }

    public void updateMarket(String sportName, String marketName, boolean isActive) {
        openEditMarketOfSport(sportName);
        editMarketPopup.txtSearchMarket.isDisplayed();
        editMarketPopup.searchMarket(marketName);
        editMarketPopup.activeMarket(marketName, isActive);
    }

    public void updateProduct(String productName, boolean isActive) {
        CheckBox chbProduct = CheckBox.xpath(String.format(cbProdudctXpath, productName));
        boolean isCheck = chbProduct.isSelected();
        if (!isCheck == isActive)
            chbProduct.click();
    }

    public void selectProduct(String productName) {
        Label lblProduct = Label.xpath(String.format("//span[text()='%s']", productName));
        lblProduct.click();
    }
    public void searchMarketOfSport(String sportName, String marketName) {
        openEditMarketOfSport(sportName);
        editMarketPopup.txtSearchMarket.isDisplayed();
        editMarketPopup.searchMarket(marketName);
    }

    public void openEditMarketOfSport(String sportName) {
        ArrayList<String> headerList = tblSportTable.getHeaderNameOfRows();
        int i = 1;
        for (String sport : headerList) {
            if (sport.equalsIgnoreCase(sportName)) {
                System.out.println(String.format("Product Setting - Click on Edit icon of %s", sportName));
                Link lnk = (Link) tblSportTable.getControlOfCell(1, i, 1, "span[@class='editmarket']");
                //  Label lblEdit = Label.xpath(String.format("//div[contains(@class,'marketSettingWrapper')]//table[contains(@class,'sportTable')]//tbody//tr[1]//td[%d]//span[@class='editmarket']",i));
                lnk.click();
                break;
            }
            i = i + 1;
        }
    }

}
