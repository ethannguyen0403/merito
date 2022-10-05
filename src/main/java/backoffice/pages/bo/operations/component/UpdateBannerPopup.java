package backoffice.pages.bo.operations.component;

import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import com.paltech.element.common.TextBox;

public class UpdateBannerPopup {
    public Popup popupUpdateBanner = Popup.xpath("//div[contains(@class,'modal-content dialog-page')]");
    public Label lblTitle = Label.xpath("//div[contains(@class,'modal-content dialog-page')]//h4");

    public DropDownBox ddbStatus = DropDownBox.xpath("//div[contains(@class,'modal-body')]//div[3]//select");
    public DropDownBox ddbSequence = DropDownBox.xpath("//div[contains(@class,'modal-body')]//div[5]//select");
    TextBox txtImageLink = TextBox.xpath("//div[contains(@class,'modal-body')]//div[2]//input");
    public TextBox txtValidFrom = TextBox.xpath("//div[contains(@class,'modal-body')]//div[6]//input");
    public TextBox txtValidTo = TextBox.xpath("//div[contains(@class,'modal-body')]//div[7]//input[@placement='top']");

    private Icon iconX = Icon.xpath("//button[@class='close close-button']/span");
    private Button btnSave = Button.xpath("//div[contains(@class, 'modal-footer')]//button[@class='btn btn-sm btn-core']");
    private Button btnClose = Button.xpath("//div[contains(@class,'modal-footer')]/button[@data-dismiss='modal']");

    public void updateBanner(String status, String sequence) {
        if(!status.isEmpty()) {
            ddbStatus.selectByVisibleText(status);
        }
        if(!sequence.isEmpty()) {
            ddbSequence.selectByVisibleText(sequence);
        }
        btnSave.click();
        // waiting for loading completely
        btnSave.isDisplayedShort(2);
    }

    public void clickCloseBtn() {
        btnClose.click();
    }

    public void clickXIcon() {
        iconX.click();
    }

}
