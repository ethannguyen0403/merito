package backoffice.pages.bo.operations.component;

import com.paltech.element.common.*;

public class VoidUnvoidRemarkPopup {
    public Popup popup = Popup.xpath("//app-void-unvoid-dialog");
    public Label lblTitle = Label.xpath("//app-void-unvoid-dialog//h1");
    public Icon iconX = Icon.xpath("//app-void-unvoid-dialog//div[@class='title']//button[@class='close']");
    public Button btnVoidUnvoid = Button.xpath("//app-void-unvoid-dialog//button[contains(@class,'btn-primary')]");
    public Button btnClose = Button.xpath("//app-void-unvoid-dialog//button[contains(@class,'btn-core')]");
    public TextBox txtRemark = TextBox.xpath("//app-void-unvoid-dialog//div[@class='content']//textarea");

    public void clickCloseBtn() {
        btnClose.click();
    }

    public void clickXIcon() {
        iconX.click();
    }

    public void confirm(String remark) {
        txtRemark.sendKeys(remark);
        btnVoidUnvoid.click();
    }

    public boolean isDisplayed() {
        popup.isInvisible(1);
        return popup.isDisplayed();
    }
}
