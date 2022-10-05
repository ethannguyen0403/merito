package membersite.pages.all.tabexchange.components.popups;

import com.paltech.element.common.Button;
import com.paltech.element.common.Popup;

public class BannerPopup {
    private Popup poupBanner = Popup.xpath("//div[@class='main-banner-popup-content']//img");
    private Button btnClose = Button.xpath("//div[@class='main-banner-popup-content']//span[@class='close']");
    public void closePopup()
    {
        btnClose.click();
    }

    public String getBannerImg()
    {
        return poupBanner.getAttribute("src");
    }

}
