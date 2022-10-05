package backoffice.pages.bo._components;

import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

public class NotificationPopup {
    public Popup popupErrorMessage = Popup.xpath("//div[contains(@class,'alert-danger')]");
    private Label lblContent = Label.xpath("//div[@class='ui-pnotify-text']");
    private Label lblTitle = Label.xpath("//div[@class='ui-pnotify-title']");

    public String getMessage(){
        return lblContent.getText();
    }
    public String getTitle (){
        return lblTitle.getText();
    }
}
