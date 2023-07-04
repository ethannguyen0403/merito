package backoffice.pages.bo.system;

import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.operations.component.NewIPPopup;
import com.paltech.element.common.*;

import java.util.List;

public class CountryIPMappingPage extends HomePage {
    public Button btnNewIP = Button.xpath("//button[contains(@class,'btn-sm')]");
    public Label lblCountriesTableName = Label.xpath("//app-country-ip-mapping//div[contains(@class,'col')][1]//div[@class='mt-4 table-wrapper'][1]//span[contains(@class,'title-left')]");
    public Label lblIPTableName = Label.xpath("//app-country-ip-mapping//div[contains(@class,'mt-4 table-wrapper ')]//span[contains(@class,'title-left')]");
    public int colCurrencyCode = 2;
    public StaticTable tblCountries = StaticTable.xpath("//app-country-ip-mapping//div[contains(@class,'col')][1]//div[@class='custom-table'][1]", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", 3);

    public int colCountryName = 3;
    //public ATable tblIP = ATable.xpath("//app-country-ip-mapping/div/div[2]//div[@class='custom-table']", 3);
    public StaticTable tblIP = StaticTable.xpath("//app-country-ip-mapping//div[contains(@class,'col')][2]//div[@class='custom-table'][1]", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", 3);
    public TextBox txtCountryName = TextBox.xpath("//app-country-ip-mapping//div[contains(@class,'col')][1]//div[@class='custom-table'][1]//input[@type='text']");
    public TextBox txtSearchIP = TextBox.xpath("//app-country-ip-mapping//div[contains(@class,'col')][21]//div[@class='custom-table'][1]//input[@type='text']");
    public DropDownBox txtStatus = DropDownBox.xpath("//div[@class='custom-table']//select");
    public Label lblNoRecordIPTable = Label.xpath("//app-country-ip-mapping/div/div[2]//div[@class='custom-table']/div[contains(@class,'body')]//div[contains(@class,'no-record')]");

    public void selectCountry(String countryName) {
        List<String> lstCountries = tblCountries.getColumn(colCountryName, true);
        for (int i = 0; i < lstCountries.size(); i++) {
            String observe = lstCountries.get(i);
            Link lnk;
            if (observe.equals(countryName)) {
                lnk = (Link) tblCountries.getControlOfCell(1, colCountryName, i + 1, null);
                lnk.scrollToThisControl(true);
                lnk.click();
                return;
            }
        }
    }

    public NewIPPopup openNewIP() {
        btnNewIP.click();
        return new NewIPPopup();
    }

}
