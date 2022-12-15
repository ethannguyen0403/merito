package backoffice.pages.bo.system;

import backoffice.controls.Table;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;

public class ProviderCurrencyMappingPage extends HomePage {
    private int totalColumn=3;
    public StaticTable tblEzugi = StaticTable.xpath("//app-currency-mapping//span[contains(text(),'Live Dealer European')]//following::div[@class='custom-table'][1]","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
    public StaticTable tblFancy = StaticTable.xpath("//app-currency-mapping//div[contains(text(),'27 Fancy')]//following::div[@class='custom-table'][1]","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
    public StaticTable tblSuperspade = StaticTable.xpath("//app-currency-mapping//div[contains(text(),'Superspade')]//following::div[@class='custom-table'][1]","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
    public StaticTable tblDigient = StaticTable.xpath("//app-currency-mapping//div[contains(text(),'Digient')]//following::div[@class='custom-table'][1]","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
    public StaticTable tblSupernowa = StaticTable.xpath("//app-currency-mapping//div[contains(text(),'Supernowa')]//following::div[@class='custom-table'][1]","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
}
