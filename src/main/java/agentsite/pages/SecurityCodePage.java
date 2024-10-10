package agentsite.pages;

import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class SecurityCodePage extends LoginPage {
    public TextBox txtSecurityCode = TextBox.xpath("//input[@id='securityCode']");
    public TextBox txtNewSecurityCode = TextBox.xpath("//table[contains(@class,'login-table')]//tr[2]//input");
    public TextBox txtConfirmSecurityCode = TextBox.xpath("//input[@ng-model='securityCodeConfirm']");
    public Button btnSubmit = Button.xpath("//table[contains(@class,'login-table')]//span[text()='Submit']");
    public Button btnBackToLoginPage = Button.xpath("//table[contains(@class,'login-table')]//span[text()='Back To Login Page']");
    public Label lblTitle = Label.xpath("//*[contains(@class,'login-title')]");
    private Image imgSpin = Image.xpath("//div[@id='spinDiv']");

    public SecurityCodePage(String types) {
        super(types);
    }

    public ConfirmPopup setInvalidSecurityCode(String securityCode) {
        setSecurityCode(securityCode, securityCode);
        return new ConfirmPopup();
    }

    public void setSecurityCode(String securityCode, String confirmSecurityCode) {
        imgSpin.isInvisible(5);
        btnSubmit.waitForElementToBePresent(btnSubmit.getLocator(), 3);
        if (txtSecurityCode.isDisplayed()) {
            txtSecurityCode.sendKeys(securityCode);
        } else {
            txtNewSecurityCode.sendKeys(securityCode);
            txtConfirmSecurityCode.sendKeys(confirmSecurityCode);
        }
        btnSubmit.click();
    }

    public agentsite.pages.LoginPage clickBackBtn() {
        btnBackToLoginPage.click();
        return new LoginPage(_type);
    }
}
