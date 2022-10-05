package agentsite.pages.oldUI.agentmanagement.createdownlineagent;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;
import org.openqa.selenium.By;

public class ProductSettingsSection extends BaseElement {
    private String _xPath;//="//div[@id='product-settings']";
    private Label lblProductSetting;
    public ProductStatusSettingsSection productStatusSettingsSection;
    public BetSettingsSection betSettingSectionExchange ;
    public TaxSettingsSection taxSettingSectionExchange ;
    public PositionTakingSection positionTakingSectionExchange;
    public EGBetSettingsSection betSettingSectionExchangeGame;
    public EGTaxSettingsSection taxSettingSectionExchangeGame;
    public EGPositionTakingSection positionTakingSectionExchangeGame;
    public Tab tabExchangeGames ;

    public ProductSettingsSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblProductSetting =Label.xpath(String.format("%s//div[@class='psection']",_xPath));
        productStatusSettingsSection = ProductStatusSettingsSection.xpath(String.format("%s//div[contains(@class,'subsetting product-status')]",_xPath));
        betSettingSectionExchange = BetSettingsSection.xpath(String.format("%s//div[@id='EXCHANGE-bet-settings']",_xPath));
        taxSettingSectionExchange = TaxSettingsSection.xpath(String.format("%s//div[@id='EXCHANGE-tax-settings']",_xPath));
        positionTakingSectionExchange = PositionTakingSection.xpath(String.format("%s//div[@id='EXCHANGE-position-taking']",_xPath));
        betSettingSectionExchangeGame = EGBetSettingsSection.xpath(String.format("%s//div[@id='EXCH_GAMES-bet-settings']",_xPath));
        taxSettingSectionExchangeGame = EGTaxSettingsSection.xpath(String.format("%s//div[@id='EXCH_GAMES-tax-settings']",_xPath));
        positionTakingSectionExchangeGame = EGPositionTakingSection.xpath(String.format("%s//div[@id='EXCH_GAMES-position-taking']",_xPath));
        tabExchangeGames = Tab.xpath(String.format("%s//span[text()='Exchange Games']",_xPath));
    }

    public static ProductSettingsSection xpath(String xpathExpression) {
        return new ProductSettingsSection(By.xpath(xpathExpression), xpathExpression);
    }

    public String getTitle(){
        return lblProductSetting.getText();
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
