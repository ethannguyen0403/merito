package backoffice.pages.bo._components;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import backoffice.controls.bo.StaticTable;
import org.openqa.selenium.By;

public class ConfirmPopup {
   public Popup popupNotification = Popup.xpath("//app-confirm-dialog//button[contains(@class,'btn-core')]");
    Label lblTitle = Label.xpath("//app-confirm-dialog//div[@class='title']//h5");
    Label lblContent = Label.xpath("//div[@class='modal-body' or contains(@class,'content')]/p");
    Button btnClose = Button.xpath("//app-confirm-dialog//button[contains(@class,'btn-outline-secondary')]");
    Button btnConfirm = Button.xpath("//app-confirm-dialog//button[contains(@class,'btn-core')]");


    public void clickCloseBtn(){
        btnClose.click();
    }

    public void confirm(){
        btnConfirm.click();
    }

    public String getContent(){
        return lblContent.getText();
    }

    public String getTitle(){
        return lblTitle.getText();
    }

    public boolean isDisplayed(){
        popupNotification.isInvisible(2);
        return popupNotification.isDisplayed();
    }

}
