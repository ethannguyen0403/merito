package agentsite.pages.marketsmanagement.blockunblockevents;

import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;

public class UnblockSchedulePopup {
    public Button btnSave = Button.xpath("//button[.text='Save']");
    public Button btnClose = Button.xpath("//button[.text='Cancel']");
    public DropDownBox ddbSelectTime = DropDownBox.name("brandName");
    public Label lblTitle = Label.xpath("//div[@class='modal-header']//h6");

    public void unblockSchedule(String time) {
        if (!time.isEmpty()) {
            ddbSelectTime.selectByVisibleText(time);
            btnSave.click();
        }
    }
}
