package agentsite.pages.agentmanagement.edituser;

import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;

public class EditMarketPopup extends BaseElement {
    public TextBox txtSearchMarket;
    public Table tblMarket;
    public CheckBox cbAllMarket;
    public Button btnOK;
    public Label lblNoRecordFound;
    public Label lblTitle;
    public Button btnCancel;
    public Icon iconClose;
    private String _xPath; //app-blocking-setting

    public EditMarketPopup(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        txtSearchMarket = TextBox.xpath(String.format("%s//div[@class='content'][1]//input", _xPath));
        tblMarket = Table.xpath(String.format("%s//perfect-scrollbar[contains(@class,'content-market')]//table", _xPath), 2);
        btnOK = Button.xpath(String.format("%s//button[@class='pbtn']", _xPath));
        btnCancel = Button.xpath(String.format("%s//button[@class='cancel']", _xPath));
        lblNoRecordFound = Label.xpath("//td[text()='No records found.']");
        lblTitle = Label.xpath("//app-blocking-setting//div[@class='title']");
        cbAllMarket = CheckBox.xpath(String.format("%s//table[@class='ptable info betTable']//tr[@class='betHead']//input", _xPath));
        iconClose = Icon.xpath(String.format("%s//button[@class='close']", _xPath));
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

    public boolean isResultContainsPrefixMarket(String prefixMarketName, List<String> lstResult) {
        for (int i = 0; i < lstResult.size(); i++) {
            if(!lstResult.get(i).contains(prefixMarketName)) {
                System.out.println(String.format("FAILED! List market contains market that does not have prefix %s", prefixMarketName));
                return false;
            }
        }
        return true;
    }

    public void clickCheckboxAllMarket(boolean isChecked) {
        boolean isChecking = cbAllMarket.isSelected();
        if(isChecked) {
            if(!isChecking) {
                cbAllMarket.click();
            }
        } else {
            if(isChecking) {
                cbAllMarket.click();
            }
        }

    }

    public boolean isAllMarketChecked() {
        int totalRow = tblMarket.getNumberOfRows(false, true);
        for (int i = 0; i < totalRow; i++) {
            String xpathCell = tblMarket.getxPathOfCell(1, 2, i+1, "input");
            CheckBox cbMarket = CheckBox.xpath(xpathCell);
            cbMarket.moveToTheControl();
            if(!cbMarket.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllMarketUnChecked() {
        int totalRow = tblMarket.getNumberOfRows(false, true);
        for (int i = 0; i < totalRow; i++) {
            String xpathCell = tblMarket.getxPathOfCell(1, 2, i+1, "input");
            CheckBox cbMarket = CheckBox.xpath(xpathCell);
            cbMarket.moveToTheControl();
            if(cbMarket.isSelected()) {
                return false;
            }
        }
        return true;
    }

}
