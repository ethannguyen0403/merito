package backoffice.pages.bo.accountmanagement.component;

import backoffice.controls.Table;

public class UplineStatusPopup {
    private int totalColumn =9;
    public Table tblUplineStatus = Table.xpath("//div[@class='tables-status']/table",totalColumn);
}
