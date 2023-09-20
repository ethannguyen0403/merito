package agentsite.pages.agentmanagement.createdownlineagent.risksettingsection;

import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.testng.Assert;

public class RiskSettingSection {
    public TextBox txtMaxExposure = TextBox.xpath("//input[@name='riskSetting']");
    public Icon iconInfo = Icon.xpath("//div[@id='EXCHANGE-risk-settings']//table//span[@class='pinfo']");
    public Label lblExtraInfo = Label.xpath("//div[@id='EXCHANGE-risk-settings']//table//span[@class='extra-title']");
    public Label lblMaxExposure = Label.xpath("//div[@id='EXCHANGE-risk-settings']//table//td[text()='Max Exposure']");


    public String getRiskSettingTitle() {
        return "";
    }

    public void verifyUIDisplayCorrect() {
        Assert.assertTrue(txtMaxExposure.isDisplayed(), "FAILED! Max Exposure textbox does not display");
        Assert.assertTrue(iconInfo.isDisplayed(), "FAILED! Info icon does not display");
        Assert.assertTrue(lblExtraInfo.isDisplayed(), "FAILED! Extra Info label does not display");
        Assert.assertTrue(lblMaxExposure.isDisplayed(), "FAILED! Max Exposure label does not display");
    }
}
