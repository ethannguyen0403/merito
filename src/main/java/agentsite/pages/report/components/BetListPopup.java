package agentsite.pages.report.components;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

public class BetListPopup {
    public int tblReportTotalCol = 8;
    public int colNo = 1;
    public int colUserCode = 2;
    public int colLoginID = 3;
    public int colPlaced = 4;
    public int colDescription = 5;
    public int colType = 6;
    public int colOdds = 7;
    public int colStake = 8;
    Popup popup = Popup.xpath("//div[contains(@class,'modal-dialog')]");
    Button btnFullScreen = Button.xpath("//button[@class='fullScreen']");
    Button btnClosePopup = Button.xpath("//div[contains(@class,'modal-dialog')]//button[@class='close']");
    Button btnClose = Button.xpath("//button[contains(@class,'btn-cancel')]");
    Label lblTitle = Label.xpath("//div[contains(@class,'modal-dialog')]//div[@class='modal-header']/div[@class='ng-binding']");

    public void closePopup() {
        btnClosePopup.click();
    }

    public void fullScreenPopup() {
        btnFullScreen.click();
    }

    public boolean isDisplay() {
        return popup.isDisplayed();
    }
}
