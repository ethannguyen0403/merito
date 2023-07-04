package backoffice.pages.bo.system.productmaintenance;

import backoffice.controls.DateTimePicker;
import backoffice.controls.Table;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import com.paltech.element.common.TextBox;

public class BetFairAccountChangeLogPopup {
    public Popup popup = Popup.xpath("//app-betfair-account-info-dialog");
    public Label lblTitle = Label.xpath("//app-betfair-account-info-dialog//h4");
    public TextBox txtLogDate = TextBox.name("dp");
    public DateTimePicker dtpLogDate = DateTimePicker.xpath(txtLogDate, "//bs-days-calendar-view//bs-calendar-layout");
    public Table tblBalance = Table.xpath("//app-betfair-account-info-dialog//table[contains(@class,'table-sm')]", 3);
    public Button btnClose = Button.xpath("//button[contains( @class,'btn-sm btn-outline-secondary')]");
    public Label lblNoRecord = Label.xpath("//app-betfair-account-info-dialog//table[contains(@class,'table-sm')]//tbody/tr/td[@class='text-center']");
}
