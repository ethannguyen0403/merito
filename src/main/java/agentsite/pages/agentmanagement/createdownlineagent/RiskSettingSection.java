package agentsite.pages.agentmanagement.createdownlineagent;

import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.By;

public class RiskSettingSection extends BaseElement {
    private String _xPath = "//div[@id ='EXCHANGE-risk-settings']";
    private Label lblTitle;
    private Label lblNote;
    private Table tblRate;
    private TextBox txtMaxExposure;

    public RiskSettingSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblTitle = Label.xpath(String.format("%s//div[@class='psection']", _xPath));
        lblNote = Label.xpath(String.format("%s//div[@class='extra-title']", _xPath));
        txtMaxExposure = TextBox.xpath(String.format("%s//input[contains(@class,'riskSetting')]", _xPath));

    }

    public static RiskSettingSection xpath(String xpathExpression) {
        return new RiskSettingSection(By.xpath(xpathExpression), xpathExpression);
    }

    public void inputMaxExposure(String value) {
        txtMaxExposure.sendKeys(value);
    }

    public String getNoteLabel() {
        return lblNote.getText();
    }


}
