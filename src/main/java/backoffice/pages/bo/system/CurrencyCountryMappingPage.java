package backoffice.pages.bo.system;

import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.*;
import backoffice.controls.bo.StaticTable;

import java.util.List;

public class CurrencyCountryMappingPage extends HomePage {
    public Button btnSave = Button.xpath("//div[contains(@class,'btn-save')]/i");
    public Label lblCurrenciesTableName = Label.xpath("//div[contains(@class,'currency-table')]/preceding-sibling::div[@class='title-header']/div[@class='title-left']");
    public Label lblCountriesTableName = Label.xpath("//div[@class='custom-table']/preceding-sibling::div[@class='title-header']/div[@class='title-left']");
    private int currencyTotalColumn = 3;
    public int colCurrencyCode = 2;
   // public ATable tblCurrencies = ATable.xpath("//div[contains(@class,'currency-table')]", currencyTotalColumn);
    public StaticTable tblCurrencies = StaticTable.xpath("//div[contains(@class,'currency-table')]","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]", currencyTotalColumn);
    private int countryTotalColumn = 4;
    public int colCountryName = 3;
    public int coldStatus = 4;
    //public ATable tblCountries = ATable.xpath("//div[@class='custom-table']", countryTotalColumn);
    public StaticTable tblCountries = StaticTable.xpath("//div[@class='custom-table']","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]", countryTotalColumn);
    public TextBox txtCountryName = TextBox.xpath("//div[@class='custom-table']//input[@type='text']");
    public DropDownBox txtStatus = DropDownBox.xpath("//div[@class='custom-table']//select");

    public void mapCurrency(String currencyCode, String countryName, boolean isMap){
            // Select currency code
            selectCurrency(currencyCode);

            //Search Country Name
            txtCountryName.sendKeys(countryName);

            //Select Country
            selectCountry(countryName, isMap);

            btnSave.click();
    }
    public void selectCurrency(String currencyCode){
            List<String> lstCurrencyCode = tblCurrencies.getColumn(colCurrencyCode, true);
            for (int i=0;i<lstCurrencyCode.size();i++) {
                    String observe = lstCurrencyCode.get(i);
                    Link lnk;
                    if (observe.equals(currencyCode)) {
                        tblCurrencies.click();
                            lnk = (Link)tblCurrencies.getControlOfCell(1, colCurrencyCode, i+1, null);
                            lnk.scrollDownInDistance();
                            lnk.click();
                            return;
                    }
            }
    }

    public void selectCountry(String countryName, boolean isActive)
    {
            List<String> lstCountries = tblCountries.getColumn(colCountryName, true);
            for (int i=0;i<lstCountries.size();i++) {
                    String observe = lstCountries.get(i);
                    Link lnk;
                    if (observe.equals(countryName)) {
                            lnk = (Link)tblCountries.getControlOfCell(1,coldStatus, i+1, "span");
                            lnk.scrollDownInDistance();
                            if(!(lnk.isSelected() && isActive)) {
                                lnk.click();
                            }
                        return;
                    }
            }
    }

}
