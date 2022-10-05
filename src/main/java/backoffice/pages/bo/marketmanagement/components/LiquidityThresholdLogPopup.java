package backoffice.pages.bo.marketmanagement.components;

import backoffice.controls.bo.StaticTable;

public class LiquidityThresholdLogPopup {
    private int totalColumn = 4;
    public StaticTable tblLog  = StaticTable.xpath("//app-show-history-dialog//div[contains(@class,'table-wrapper')]","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumn);
}
