package backoffice.pages.bo.system;

import backoffice.controls.Table;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;

public class ProviderCurrencyMappingPage extends HomePage {
    private int totalColumn=3;
    public StaticTable tblEzugi = StaticTable.xpath("//app-currency-mapping//div[@class='col'][1]//div[@class='custom-table']","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
    public StaticTable tblFancy = StaticTable.xpath("//app-currency-mapping//div[@class='col'][2]//div[@class='custom-table']","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
    public StaticTable tblSuperspade = StaticTable.xpath("//app-currency-mapping//div[@class='col'][3]//div[@class='container'][1]//div[@class='custom-table']","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
    public StaticTable tblDigient = StaticTable.xpath("//app-currency-mapping//div[@class='col'][3]//div[@class='container'][2]//div[@class='custom-table']","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
    public StaticTable tblSupernowa = StaticTable.xpath("//app-currency-mapping//div[@class='col'][3]//div[@class='container'][3]//div[@class='custom-table']","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
}
