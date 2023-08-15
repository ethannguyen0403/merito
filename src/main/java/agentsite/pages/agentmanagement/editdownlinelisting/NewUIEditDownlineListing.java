package agentsite.pages.agentmanagement.editdownlinelisting;

import agentsite.pages.agentmanagement.DownLineListingPage;
import com.paltech.element.common.Button;

public class NewUIEditDownlineListing extends EditDownlineListing {
    private Button btnCloseAlert = Button.xpath("//app-alert//button[@class='close']");
    public NewUIEditDownlineListing(String types) {
        super(types);
    }

    public void clickSubmit() {
        btnSubmit.click();
        waitingLoadingSpinner();
        btnCloseAlert.click();
        waitingLoadingSpinner();
    }
    public DownLineListingPage closeEditDownlinePopup() {
        btnClosePopup.click();
        return new DownLineListingPage(_type);
    }

}
