package membersite.pages.popup;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import membersite.controls.aposta.APHeaderControl;
import membersite.pages.all.home.ChangePasswordPage;
import membersite.pages.all.tabexchange.HomePage;
import org.openqa.selenium.By;

public class BannerPopup extends BaseElement {
    String _xpath;
    private Button btnClose;
    private Label lblImage;

    public BannerPopup(By locator,String xpath) {
        super(locator);
        _xpath = xpath;
        btnClose = Button.xpath(String.format("%s//span[@class='close']",_xpath));
        lblImage = Label.xpath(String.format("%s/img",_xpath));
    }
    public static BannerPopup xpath(String xpathExpression) {
        return new BannerPopup(By.xpath(xpathExpression), xpathExpression);
    }

    public void closeBannerPopup()    {
        if(isDisplayed()){
            btnClose.click();
        }

    }
}
