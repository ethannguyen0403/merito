package membersite.pages.funsport.home.popups;

import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

public class ErrorLoginPopup {
    public Popup popupErrorLogin = Popup.xpath("//div[@class='dialog error']");
    public Label lblContent = Label.xpath("//div[@class='dialog error']//font");
    private Icon iconX = Icon.xpath("//div[@class='dialog error']//span[@class='close-dialog']");

    public void clickXIcon(){
        iconX.click();
    }
}
