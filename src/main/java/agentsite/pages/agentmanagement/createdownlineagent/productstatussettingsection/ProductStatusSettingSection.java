package agentsite.pages.agentmanagement.createdownlineagent.productstatussettingsection;

import agentsite.controls.Table;
import agentsite.pages.agentmanagement.edituser.EditMarketPopup;
import com.paltech.element.common.*;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static common.AGConstant.HomePage.PRODUCTS_LIST;

public class ProductStatusSettingSection {
    private int totalSportCol = 40;
    public EditMarketPopup editMarketPopup = EditMarketPopup.xpath("//app-blocking-setting");
    public CheckBox chbLive = CheckBox.id("live");
    public CheckBox chbNoneLive = CheckBox.id("nonlive");
    public Menu mnProduct = Menu.xpath("//tabset[@id='productSetting']//ul[@class='nav nav-tabs']");
    public Table tblSportTable = Table.xpath("//div[contains(@class,'marketSettingWrapper')]//table[contains(@class,'sportTable')]", totalSportCol);
    String cbSportXPath = "//td[contains(@class,'betTitle back')]//label[@title='%s']/input";
    String cbProdudctXpath = ".//span[text()='%s']/../input[@type='checkbox']";
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getProductSettingSectionTitle() {
        return "";
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
    public void updateProducts(Map<String, Boolean> products) {
        CheckBox chbProduct;
        for (String product: PRODUCTS_LIST
             ) {
            if(products.containsKey(product))
            {
                chbProduct = CheckBox.xpath(String.format(cbProdudctXpath, product));
                boolean isCheck = chbProduct.isSelected();
                if(!isCheck == products.get(product))
                    chbProduct.click();
            }
        }
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

    public void verifyUIDisplayCorrect(String product) {
        Assert.assertTrue(mnProduct.isDisplayed(), "FAILED! Menu product does not display");
        if(product.equalsIgnoreCase(AGConstant.EXCHANGE)) {
            Assert.assertTrue(chbLive.isDisplayed(), "FAILED! Live checkbox does not display");
            Assert.assertTrue(chbNoneLive.isDisplayed(), "FAILED! Non-live checkbox does not display");
            Assert.assertTrue(tblSportTable.isDisplayed(), "FAILED! Sport table does not display");
        }
    }

    public void verifyAllProductChecked() {
        String[] xpathMenuProduct = mnProduct.getLocator().toString().split("By.xpath: ");
        String productCheckboxXpath = xpathMenuProduct[1] + "//li[%s]//input";
        Label lblProducts = Label.xpath(xpathMenuProduct[1]+"//li");
        for (int i = 0; i < lblProducts.getWebElements().size(); i++) {
            CheckBox cbProduct = CheckBox.xpath(String.format(productCheckboxXpath, i+1));
            Assert.assertTrue(cbProduct.isSelected(), "FAILED! Product checkbox is not checked");
        }
    }

    public List<String> getProductsTabs(){
        List<String> lstProduct = new ArrayList<>();
        String productCheckboxXpath = "//div[@id='product-settings']//ul[@class='nav nav-tabs']" + "//li[%s]//span[contains(@class,'tab-pro')]";
        Label lblProducts = Label.xpath("//div[@id='product-settings']//ul[@class='nav nav-tabs']"+"//li");
        for (int i = 0; i < lblProducts.getWebElements().size(); i++) {
            Label lblProduct = Label.xpath(String.format(productCheckboxXpath, i+1));
            lstProduct.add(lblProduct.getText());
        }
        return lstProduct;
    }

}
