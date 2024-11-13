package agentsite.pages.riskmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.testng.Assert;

public class MonitoredAccountsPage extends HomePage {
    private Label lblTitlePage = Label.xpath("//app-title-dashboard//div[@class='title']//label");
    private DropDownBox ddbStatus =  com.paltech.element.common.DropDownBox.xpath("//div[contains(@class,'monitor-status')]//select");
    private TextBox txtUsername = TextBox.xpath("//div[contains(@class,'user-name-search')]//input");
    private Button btnSubmit = Button.xpath("//button[@class='pbtn search']");
    private Table tblResult = Table.xpath("//table[contains(@class,'account-monitoring-table')]", 10);
    private Label lblNoRecordFound = Label.xpath("//span[text()='No records found.']");
    private String rowStatusXpath = "//table[contains(@class,'account-monitoring-table')]//tbody[1]//tr[%s]//td[8]//select";
    private String rowUserXpath = "//table[contains(@class,'account-monitoring-table')]//tbody[1]//tr[%s]//td[2]";
    public MonitoredAccountsPage(String types) {
        super(types);
    }

    public void filter(String status, String username, boolean isSubmit) {
        waitingLoadingSpinner();
        if(!status.isEmpty()) {
            ddbStatus.selectByVisibleText(status);
        }
        if(!username.isEmpty()) {
            txtUsername.sendKeys(username);
        }
        if (isSubmit) {
            btnSubmit.click();
            waitingLoadingSpinner();
        }
    }

    public void verifyFilterResultCorrect(String status, String username) {
        if(lblNoRecordFound.isDisplayed()) {
            Assert.assertTrue(false, "FAILED! No record found appears after filtering");
        }
        int totalRow = tblResult.getNumberOfRows(false, true);
        if(!status.isEmpty()) {
            for (int i = 0; i < totalRow; i++) {
                String value = DropDownBox.xpath(String.format(rowStatusXpath,i+1)).getFirstSelectedOption();
                //handle case user is Suspected Fraud as highest status priority
                if(!value.equalsIgnoreCase("Suspected Fraud")) {
                    Assert.assertEquals(status, value, "FAILED! Status is not displayed correct");
                } else {
                    Assert.assertEquals("Suspected Fraud", value, "FAILED! Suspected Fraud is not displayed correct");
                }
            }
        }
        if (!username.isEmpty()) {
            for (int i = 0; i < totalRow; i++) {
                String value = Label.xpath(String.format(rowUserXpath,i+1)).getText();
                Assert.assertTrue(value.contains(username), "FAILED! Result does not contains username");
            }
        }
    }

    public void verifyNoRecordFoundDisplay() {
        Assert.assertTrue(lblNoRecordFound.isDisplayed(), "FAILED! No record found does not display");
    }

}
