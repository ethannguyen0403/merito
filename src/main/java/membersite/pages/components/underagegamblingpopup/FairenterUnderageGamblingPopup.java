package membersite.pages.components.underagegamblingpopup;


import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import membersite.pages.components.loginform.FairenterLoginPopup;
import membersite.pages.components.loginform.FunLoginPopup;

public class FairenterUnderageGamblingPopup extends UnderageGamblingPopup{
    private Button btnConfirm = Button.xpath("//button[contains(@class,'confirmAction')]");
    private Button btnExit = Button.xpath("//button[contains(@class,'exitAction')]");
    private Label lblContent = Label.xpath("//div[@class='ageVerify-container']//div[@class='sub-text-msg']");
    private Label lblTitle = Label.xpath("//div[@class='ageVerify-container']//div[@class='text-msg']");
    public FairenterLoginPopup clickConfirmation() {
        btnConfirm.click();
        return new FairenterLoginPopup();
    }
    public String getContent(){
        return lblContent.getText();
    }
    public String getTitle(){
        return lblTitle.getText();
    }
    public void clickExit() { btnExit.click(); }
}
