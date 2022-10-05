package backoffice.pages.bo.operations.component;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import backoffice.controls.bo.ATable;


public class ConfirmMapLiveStreamingPopup {
    public Popup popupAutoMapping = Popup.xpath("//app-dialog-confirm");
    private Label lblTitle = Label.xpath("//app-dialog-confirm//h4");
    private Button btnCancel = Button.xpath("//app-dialog-confirm//button[contains(@class,'btn-cancel')]");
    private Button btnOk = Button.xpath("//app-dialog-confirm//button[contains(@class,'btn-confirm')]");
    public ATable tblConfirm = ATable.xpath("//app-dialog-confirm//div[@class='custom-table']",3);
    public Label lblNote = Label.xpath("//app-dialog-confirm//ul[@class='text-msg']");

    public String getTitle(){
        return lblTitle.getText();
    }

    public void clickCancelBtn()
    {
        btnCancel.click();
    }

    public boolean isDisplayed(){
        popupAutoMapping.isInvisible(2);
        return popupAutoMapping.isDisplayed();
    }

}
