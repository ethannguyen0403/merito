package backoffice.pages.bo.system;

import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;

public class ProviderCurrencyMappingPage extends HomePage {
    public DropDownBox ddnProduct = DropDownBox.xpath("//select");
    public Button btnSubmit = Button.xpath("//button[text()='Submit']");
    private int totalColumn = 3;
    public StaticTable tblEzugi = StaticTable.xpath("//app-currency-mapping", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);
    public StaticTable tblFancy = StaticTable.xpath("//app-currency-mapping", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);
    public StaticTable tblSuperspade = StaticTable.xpath("//app-currency-mapping", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);
    public StaticTable tblDigient = StaticTable.xpath("//app-currency-mapping", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);
    public StaticTable tblSupernowa = StaticTable.xpath("//app-currency-mapping", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);
    private String productIntoDropdown = "//option[contains(text(),'%s')]";

    public String getProductIntoDropdown(String productName) {
        return Label.xpath(String.format(productIntoDropdown, productName)).getText();
    }

}
