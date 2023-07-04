package membersite.pages.components.underagegamblingpopup;


import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import membersite.pages.components.loginform.FunLoginPopup;

public class FunsportUnderageGamblingPopup extends UnderageGamblingPopup {
    private Label lblTitle = Label.xpath("//div[@class='title-popup']//b");
    private Label lblContent = Label.xpath("//div[@class='content-popup']/p");
    private Button btnConfirm = Button.xpath("//button[contains(@class,'btn-in-out')]");
    private Button btnExit = Button.xpath("//button[contains(@class,'btn-in-out exit')]");

    public FunLoginPopup clickConfirmation() {
        btnConfirm.click();
        return new FunLoginPopup();
    }

    public String getContent() {
        return lblContent.getText();
    }

    public String getTitle() {
        return lblTitle.getText();
    }

    public void clickExit() {
        btnExit.click();
    }
}
