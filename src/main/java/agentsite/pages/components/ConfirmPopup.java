package agentsite.pages.components;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import org.testng.Assert;

public class ConfirmPopup {
    Popup popup = Popup.xpath("//div[contains(@class,'dialog')]");

    Label lblTitle = Label.xpath("//div[contains(@class,'dialog')]//div[contains(@class,'modal-header')]/div");
    Button btnClose = Button.xpath("//div[contains(@class,'dialog')]//div[contains(@class,'modal-header')]/button");
    Label lblContent = Label.xpath("//div[contains(@class,'dialog')]//div[contains(@class,'modal-body')]");
    Button btnOK = Button.xpath("//div[contains(@class,'modal-footer')]//button[contains(text(),'Ok') or contains(@class,'btn-warning')or contains(text(),'OK')]");
    Button btnCancel = Button.xpath("//div[contains(@class,'dialog')]//div[contains(@class,'footer')]//button[contains(@class,'cancel')]");


    public String getContentMessage() {
        lblContent.isDisplayed();
        return lblContent.getText();
    }

    public boolean isPopupDisplay() {
        return popup.isDisplayedShort(3);
    }

    public void confirm() {
        btnOK.click();
    }

    public void cancel() {
        btnCancel.click();
    }

    public void close() {
        btnClose.click();
    }

    public String getTitle() {
        return lblTitle.getText();
    }

    public void verifyErrorMsgShowCorrect(String message) {
        lblTitle.waitForElementToBePresent(lblTitle.getLocator(), 2);
        Assert.assertEquals(getTitle(), "Error", "FAILED! Error popup title is incorrect");
        Assert.assertEquals(getContentMessage(), message, "FAILED! Error message is incorrect");
    }
}
