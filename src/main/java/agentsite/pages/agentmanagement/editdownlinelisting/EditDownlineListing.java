package agentsite.pages.agentmanagement.editdownlinelisting;

import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.downlinelisting.EditDownlinePopup;
import com.paltech.element.common.*;
import java.util.List;

public class EditDownlineListing extends DownLineListingPage {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    protected Button btnClosePopup = Button.xpath("//app-agency-edit//button[@class='close']");
    protected Button btnSubmit = Button.xpath("//div[@class='paction']/button[@id='submitBtn']");
    protected Button btnCancel = Button.xpath("//div[@class='paction']/button[@id='cancelBtn']");
    public EditDownlineListing(String types) {
        super(types);
    }

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public void inputInfoSection(String password, String accountStatus, String firstName, String lastName, String phone, String mobile, String fax, boolean isSubmit) {
        accountInforSection.inputInfo(password, accountStatus, firstName, lastName, phone, mobile, fax);
        if (isSubmit)
            btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void setTransaction(boolean isDaily, List<String> days, boolean isSubmit) {
        transferSettingSection.setTransfer(isDaily, days);
        btnSubmit.click();
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
