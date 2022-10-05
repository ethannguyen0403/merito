package backoffice.pages.bo._components;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

public class AlertMessageBox {
    public Popup popup = Popup.xpath("//app-alert");
    Label lblSuccessAlertContent = Label.xpath("//div[contains(@class,'alert-success')]");
    Label lblErrorAlertContent = Label.xpath(" //div[contains(@class,'alert-danger')]");
    Button btnClose = Button.xpath("//button[contains(@class,'close')]");

    public void clickCloseBtn(){
        btnClose.click();
    }

    public String getSuccessAlert(){
        return lblSuccessAlertContent.getText();
    }
    public String getErrorAlert(){
        return lblErrorAlertContent.getText();
    }


    public boolean isDisplayed(){
        popup.isInvisible(2);
        return popup.isDisplayed();
    }

}
