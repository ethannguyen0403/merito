package agentsite.pages.agentmanagement.edituser;

import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class EditMarketPopup extends BaseElement {
    public TextBox txtSearchMarket;
    public Table tblMarket;
    public CheckBox cbAllMarket;
    public Button btnOK;
    public Label lblNoRecordFound;
    public Label lblTitle;
    public Button btnCancle;
    private String _xPath; //app-blocking-setting

    public EditMarketPopup(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        txtSearchMarket = TextBox.xpath(String.format("%s//div[@class='content'][1]//input", _xPath));
        tblMarket = Table.xpath(String.format("%s//perfect-scrollbar[contains(@class,'content-market')]//table", _xPath), 2);
        btnOK = Button.xpath(String.format("%s//button[@class='pbtn']", _xPath));
        btnCancle = Button.xpath(String.format("%s//button[@class='cancel']", _xPath));
        lblNoRecordFound = Label.xpath("//td[text()='No records found.']");
        lblTitle = Label.xpath("//app-blocking-setting//div[@class='title']");
        cbAllMarket = CheckBox.xpath(String.format("%s//table[@class='ptable info betTable']//tr[@class='betHead']//input", _xPath));
    }

    public static EditMarketPopup xpath(String xpathExpression) {
        return new EditMarketPopup(By.xpath(xpathExpression), xpathExpression);
    }

    public void searchMarket(String marketName) {
        txtSearchMarket.sendKeys(marketName);
        txtSearchMarket.type(false, Keys.ENTER);
    }

    public void activeMarket(String marketName, boolean isActive) {
        BaseElement element = tblMarket.getControlBasedValueOfDifferentColumnOnRow(marketName, 1, 1, 1, null, 2, "input", true, false);
        boolean ischecked = element.isSelected();
        if (ischecked != isActive) {
            element.click();
        }
        btnOK.click();
    }

    public boolean isMarketActive(String marketName) {
        BaseElement element = tblMarket.getControlBasedValueOfDifferentColumnOnRow(marketName, 1, 1, 1, null, 2, "input", true, false);
        return element.isSelected();
    }
}
