package agentsite.pages.all.agentmanagement.createdownlineagent;

import com.paltech.element.BaseElement;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import agentsite.controls.MenuTree;
import org.openqa.selenium.By;
import agentsite.pages.oldUI.agentmanagement.createdownlineagent.TaxSettingsSection;

import java.util.List;

public class ProductSettingsSection extends BaseElement {
    private String _xPath = "//div[@id='EXCHANGE-bet-settings']";
    public Label lblProductSetting = Label.xpath(String.format("%s/div[@class='psection']", _xPath));
    public ProductStatusSettingsSection productStatusSettingsSection = ProductStatusSettingsSection.xpath(String.format("%s//div[contains(@class,'subsetting product-status')]", _xPath));
    public BetSettingsSection betSettingSectionExchange = BetSettingsSection.xpath(String.format("%s//div[@id='EXCHANGE-bet-settings']", _xPath));
    public BetSettingsSection betSettingSectionExchangeGame = BetSettingsSection.xpath(String.format("%s//div[@id='EXCH_GAMES-bet-settings']", _xPath));
    public TaxSettingsSection taxSettingSectionExchange = TaxSettingsSection.xpath(String.format("%s//div[@id='EXCHANGE-tax-settings']", _xPath));
    public TaxSettingsSection taxSettingSectionExchangeGame = TaxSettingsSection.xpath(String.format("%s//div[@id='EXCH_GAMES-bet-settings']", _xPath));
    public MenuTree mnProduct = MenuTree.xpath(String.format("%s//tabset[@id='productSetting']/ul", _xPath), "/li");

    public ProductSettingsSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
    }

    public static ProductSettingsSection xpath(String xpathExpression) {
        return new ProductSettingsSection(By.xpath(xpathExpression), xpathExpression);
    }

    public List<String> getExchangeSportList() {
        return productStatusSettingsSection.tblSportTable.getHeaderNameOfRows();
    }

    public void activeProduct(String productName){
    //tabset[@id='productSetting']/ul
           int i = 1;
        Label lblProduct = Label.xpath(String.format("//tabset[@id='productSetting']/ul/li[%s]",i));
        CheckBox cbox;
        while (true)
        {
            if(!lblProduct.isDisplayed()) {
                System.out.println("Cannot found product name: "+ productName);
                return;
            }
            if(lblProduct.getText().trim().equalsIgnoreCase(productName)){
                cbox = CheckBox.xpath(String.format("//tabset[@id='productSetting']/ul/li[%s]//input",i));
                if(cbox.isSelected()){
                    cbox.click();
                }
                System.out.println("Product "+ productName+" is active");
                return;
            }
             i= i+1;

        }

    }
   /* public void updateMarket(String sportName, String marketName, boolean isActive, boolean isSubmit)
    {
        Table tblSportTable = Table.xpath("//div[contains(@class,'marketSettingWrapper')]//table[contains(@class,'sportTable')]",40);
        ArrayList<String> headerList = tblSportTable.getHeaderNameOfRows();

        int i = 1;
        for(String sport: headerList){
            if (sport.equalsIgnoreCase(sportName))
            {
                System.out.println(String.format("Product Setting - Click on Edit icon of %s",sportName));
                Label lblEdit = Label.xpath(String.format("//div[contains(@class,'marketSettingWrapper')]//table[contains(@class,'sportTable')]//tbody//tr[1]//td[%d]//span[@class='editmarket']",i));
                lblEdit.click();
                break;
            }
            i=i+1;
        }
        EditMarketPopup popup = new EditMarketPopup();
        popup.txtSearchMarket.isDisplayed();
        popup.searchMarket(marketName);
        popup.activeMarket(marketName,isActive);
    }*/


}
