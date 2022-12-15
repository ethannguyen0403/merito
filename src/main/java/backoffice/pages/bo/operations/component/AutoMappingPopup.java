package backoffice.pages.bo.operations.component;

import com.paltech.element.common.*;
import backoffice.controls.bo.ATable;


public class AutoMappingPopup {
    public Popup popupAutoMapping = Popup.xpath("//app-dialog-auto-map");
    private Label lblTitle = Label.xpath("//app-dialog-auto-map//h4");
    private Button btnCancel = Button.xpath("//app-dialog-auto-map//button[contains(@class,'btn-cancel')]");
    private Button btnOk = Button.xpath("//app-dialog-auto-map//button[contains(@class,'btn-confirm')]");
    public ATable tblAutoMapping = ATable.xpath("//app-dialog-auto-map//div[@class='custom-table']",7);

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
