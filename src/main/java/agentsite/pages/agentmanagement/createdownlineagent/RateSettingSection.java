package agentsite.pages.agentmanagement.createdownlineagent;

import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.By;

public class RateSettingSection extends BaseElement {
    public TextBox txtRate;
    private String _xPath = "//div[contains(@class,'ratesetting')]";
    private Label lblTitle;
    private Table tblRate;

    public RateSettingSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblTitle = Label.xpath(String.format("%s//div[@class='psection']", _xPath));
        txtRate = TextBox.xpath(String.format("%s//input[contains(@class,'rate-input')]", _xPath));

        tblRate = Table.xpath(String.format("%s//table[@class='ptable info credit-balance-table']", _xPath), 2);
    }

    public static RateSettingSection xpath(String xpathExpression) {
        return new RateSettingSection(By.xpath(xpathExpression), xpathExpression);
    }

    public String getRateLimit() {
        return tblRate.getColumn(2, false).get(0);
    }

    public String getRateLabel() {
        return tblRate.getColumn(1, false).get(0);
    }

    public String getRateTitle() {
        return lblTitle.getText();
    }

}
