package membersite.pages.components.underagegamblingpopup;


import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import membersite.pages.components.loginform.SATLoginPopup;

public class SATUnderageGamblingPopup extends UnderageGamblingPopup {
    private Label lblTitle = Label.xpath("//div[@class='confirm-container']//div[@class='text1']");
    private Label lblContent = Label.xpath("//div[@class='confirm-container']//div[@class='text2']");
    private Button btnConfirm = Button.xpath("//button[contains(@class,'btn-verification btn-confirm')]");
    private Button btnExit = Button.xpath("//div[@class='confirm-container']//button[@class='btn-verification']");

    public SATLoginPopup clickConfirmation() {
        btnConfirm.click();
        return new SATLoginPopup();
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
