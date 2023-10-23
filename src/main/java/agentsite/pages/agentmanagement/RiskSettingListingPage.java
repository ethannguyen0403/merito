package agentsite.pages.agentmanagement;

import agentsite.controls.Cell;
import agentsite.controls.Table;
import com.paltech.element.common.*;
import org.testng.Assert;

import static common.AGConstant.HomePage.RISK_SETTING_LISTING;

public class RiskSettingListingPage extends DownLineListingPage {
    private int totalCol = 22;
    public Label lblTitle = Label.xpath("//app-agency-risk-setting//app-title-dashboard//div[@class='title']");
    private TextBox txtUsername = TextBox.id("username");
    private DropDownBox ddbAccountStatus = DropDownBox.id("status");
    private Button btnSubmit = Button.xpath("//app-agency-risk-setting//button[@class='pbtn search']");
    private Table tblDownline = Table.xpath("//app-agency-risk-setting//table[@class='ptable report table-responsive']", totalCol);
    private Label lblBreadcrumb = Label.xpath("//app-agency-risk-setting//span[@class='my-breadcrumb']");
    private Label lblMaxExposureValue = Label.xpath("//app-edit-risk-setting-dialog//tr[@class='creditGiven']//td[@class='phint']");
    private TextBox txtMaxExposure = TextBox.xpath("//app-edit-risk-setting-dialog//tr[@class='creditGiven']//input[contains(@class,'limited')]");
    private Button btnSubmitUpdateRisk = Button.id("submitBtn");
    private int maxExposureCol;
    public RiskSettingListingPage(String types) {
        super(types);
    }

    public void verifyUIDisplayCorrect() {
        Assert.assertEquals(lblTitle.getText().trim(), RISK_SETTING_LISTING, "FAILED! Page title does not display correct");
        Assert.assertTrue(txtUsername.isDisplayed(), "FAILED! Username textbox does not display");
        Assert.assertTrue(ddbAccountStatus.isDisplayed(), "FAILED! Account status dropdown does not display");
        Assert.assertTrue(lblBreadcrumb.isDisplayed(), "FAILED! Breadcrumb does not display");
        Assert.assertTrue(btnSubmit.isDisplayed(), "FAILED! Submit button does not display");
        Assert.assertTrue(tblDownline.isDisplayed(), "FAILED! Downline table does not display");
    }

    public void updateExposure(String exposureValue, boolean isSubmit) {
        txtMaxExposure.sendKeys(exposureValue);
        if(isSubmit) {
            btnSubmitUpdateRisk.click();
            waitingLoadingSpinner();
        }
    }

    public String getMaxExposure() {
        String[] maxExposure;
        switch (_type) {
            case "satsport":
                maxExposure = lblMaxExposureValue.getText().split("<=");
                return maxExposure[1].trim();
            default:
                maxExposure = lblMaxExposureValue.getText().split("HKD");
                return maxExposure[1].trim();
        }
    }

    public void openEditPopup(String userName) {
        searchDownline(userName, "", "");
        clickEditIcon(userName, false);
    }

    public boolean isExposureUpdatedCorrect(String username, String exposureValue) {
        searchDownline(username, "", "");
        maxExposureCol = getHeaderIndexValue("Max Exposure");
        Cell cellValue = tblDownline.getCellByName(username, false);
        int userCodeCol = Integer.parseInt(cellValue.getAttribute("cellIndex")) + 1;
        Link lblExposure = (Link) tblDownline.getControlBasedValueOfDifferentColumnOnRow(username, 1, userCodeCol, 1, null, maxExposureCol, null, false, false);
        return lblExposure.getText().equalsIgnoreCase(exposureValue);
    }

}
