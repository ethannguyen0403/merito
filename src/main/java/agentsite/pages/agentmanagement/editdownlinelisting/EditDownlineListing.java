package agentsite.pages.agentmanagement.editdownlinelisting;

import agentsite.pages.agentmanagement.DownLineListingPage;
import com.paltech.element.common.*;
import java.util.List;

public class EditDownlineListing extends DownLineListingPage {
    protected Button btnClosePopup = Button.xpath("//app-agency-edit//button[@class='close']");
    public Button btnSubmit = Button.xpath("//div[@class='paction']/button[@id='submitBtn']");
    public Button btnCancel = Button.xpath("//div[@class='paction']/button[@id='cancelBtn']");
    public EditDownlineListing(String types) {
        super(types);
    }

    public void inputInfoSection(String password, String accountStatus, String firstName, String lastName, String phone, String mobile, String fax, boolean isSubmit) {
        accountInforSection.inputInfo(password, accountStatus, firstName, lastName, phone, mobile, fax);
        if (isSubmit)
            btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void setTransaction(boolean isDaily, List<String> days, boolean isSubmit) {
        transferSettingInforSection.setTransfer(isDaily, days);
        if(isSubmit) {
            btnSubmit.click();
        }
    }

    public void enableDisableSport(String sportName, boolean isEnable) {
        String xpath = String.format("//div[@id='product-settings']//ul[@class='nav nav-tabs']//span[text()='%s']//..//input", sportName);
        CheckBox chbSport = CheckBox.xpath(xpath);
        if (chbSport.isDisplayed()) {
            String isChecking = chbSport.getAttribute("checked", false);
            if(isEnable) {
                if (isChecking == null) {
                    chbSport.click();
                }
            } else {
                if (isChecking != null) {
                    chbSport.click();
                }
            }
        } else {
            System.out.println("There is no checkbox for sport " + sportName);
        }
    }

    public void clickSubmit() {
    }

    public DownLineListingPage closeEditDownlinePopup() {
        return null;
    }
    public void verifyUIDisplayCorrect() {
    }
}
