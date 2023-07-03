package backoffice.pages.bo.system.productmaintenance;

import com.paltech.element.common.*;

public class MaintenanceDetailsPopup {
    public Popup popupMaintenanceDetails = Popup.xpath("//div[contains(@class, 'maintenance-detail')]");
    public Label lblTitle = Label.id("exampleModalLabel");

    public TextBox txtProductName = TextBox.id("productName");
    public DropDownBox ddbStatus = DropDownBox.id("status");
    private TextArea txtMaintenanceMessage = TextArea.xpath("//div[@class='ql-editor ql-blank']");
    private Icon iconX = Icon.xpath("//button[@class='close']/span");
    private Button btnClose = Button.xpath("//div[contains(@class,'modal-footer')]/button[@data-dismiss='modal']");
    private Button btnConfirm = Button.xpath("//div[@class='modal-footer']/button[contains(@class,'btn-core')]");

    public void maintain(String status, String message) {
        if (!status.isEmpty()) {
            ddbStatus.selectByVisibleText(status);
        }
        if (!message.isEmpty() && !status.toLowerCase().equals("active")) {
            txtMaintenanceMessage.sendInnerText(message);
        }
        btnConfirm.click();
        // waiting for loading completely
        btnConfirm.isDisplayedShort(2);
    }

    public void clickCloseBtn() {
        btnClose.click();
    }

    public void clickXIcon() {
        iconX.click();
    }
}
