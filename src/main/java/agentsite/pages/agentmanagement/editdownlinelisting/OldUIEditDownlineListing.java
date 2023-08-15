package agentsite.pages.agentmanagement.editdownlinelisting;

import agentsite.pages.agentmanagement.DownLineListingPage;
import com.paltech.element.common.Button;

public class OldUIEditDownlineListing extends EditDownlineListing {
    private Button btnCloseAlert = Button.xpath("//app-comfirm//button[@class='close']");
    private Button btnCancel = Button.xpath("//button[@id='cancelBtn']");
    public OldUIEditDownlineListing(String types) {
        super(types);
    }

    public void clickSubmit() {
        btnSubmit.click();
        waitingLoadingSpinner();
        btnCloseAlert.click();
        waitingLoadingSpinner();
    }
    public DownLineListingPage closeEditDownlinePopup() {
        btnCancel.click();
        return new DownLineListingPage(_type);
    }

}