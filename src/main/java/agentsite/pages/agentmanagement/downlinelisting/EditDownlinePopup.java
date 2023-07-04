package agentsite.pages.agentmanagement.downlinelisting;

import agentsite.pages.agentmanagement.createdownlineagent.AccountInfoSection;
import agentsite.pages.agentmanagement.createdownlineagent.CreditBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.ProductSettingsSection;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

public class EditDownlinePopup extends BaseElement {
    public Label lblTitle;
    public Button btnOK = Button.xpath("//button[contains(@class,'btn btn-warning')]");
    public AccountInfoSection accInfoSection;
    public ProductSettingsSection productSettingsSection;
    public CreditBalanceSection balanceSection;
    public Button btnSubmit;
    private String _xPath = "//div[@id='account']//app-agency-account-ui";
    private Label lblMessage = Label.xpath("//div[contains(@class,'modal-body-fit-with-content')]");

    public EditDownlinePopup(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblTitle = Label.xpath(String.format("%s//div[@class='title']", _xPath));
        accInfoSection = AccountInfoSection.xpath(String.format("%s//div[@id='account']", _xPath));
        productSettingsSection = ProductSettingsSection.xpath(String.format("%s//div[@id='product-settings']", _xPath));
        balanceSection = CreditBalanceSection.xpath(String.format("%s//div[@id='credit-balance-setting']", _xPath));
        btnSubmit = Button.xpath(String.format("%s//button[contains(@id,'submitBtn')]", _xPath));
    }

    public static EditDownlinePopup xpath(String xpathExpression) {
        return new EditDownlinePopup(By.xpath(xpathExpression), xpathExpression);
    }

    public String getMessage() {
        return lblMessage.getText();

    }

    public String getTitle() {
        lblTitle.isDisplayed(1);
        return lblTitle.getText();
    }


}
