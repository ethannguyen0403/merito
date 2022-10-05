package membersite.pages.funsport.home;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import membersite.pages.all.components.LoginHeader;
import membersite.pages.funsport.home.popups.FairenterLoginPopup;

public class LandingPage extends LoginHeader {
    public Button btnExit = Button.xpath("//button[contains(@class,'exitAction')]/span");
    public Button btnConfirm = Button.xpath("//button[contains(@class,'btn btn-login confirmAction')]");
    public Label lnlAgeVerifyLableMsg = Label.xpath("//div[@class='ageVerify-container']//div[@class='text-msg']");
    public Label lnlAgeVerifyLableSubMsg = Label.xpath("//div[@class='ageVerify-container']//div[@class='sub-text-msg']");

    public FairenterLoginPopup openFairenterLoginPopup(){
        btnConfirm.click();
        return new FairenterLoginPopup();
    }

}
