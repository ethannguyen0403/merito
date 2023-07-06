package backoffice.pages.bo.reports;

import backoffice.controls.DateTimePicker;
import backoffice.controls.DropDownBox;
import backoffice.controls.Row;
import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.reports.component.TransactionDetailsPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;

import java.util.List;
import java.util.Objects;

public class PunterPerformancePage extends HomePage {
    public TextBox txtDateFrom = TextBox.name("from-date");
    public TextBox txtDateTo = TextBox.name("to-date");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtDateFrom, "//div[contains(@class,'bs-calendar-container')]");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtDateTo, "//div[contains(@class,'bs-calendar-container')]");
    public com.paltech.element.common.DropDownBox ddbTypeCurrency = com.paltech.element.common.DropDownBox.xpath("//div[contains(@class,'currency')]//select");
    public DropDownBox ddbProduct = DropDownBox.xpath("//div[contains(@class,'product')]//div[@class='selected-list']", "//div[contains(@class,'product')]//div[contains(@class,'dropdown-list')]//li/label");
    public Label lblProductError = Label.xpath("//div[contains(@class, 'search-region')]//div[@class='error-message'][2]");
    public DropDownBox ddbPortal = DropDownBox.xpath("//div[contains(@class,'portal')]//div[@class='selected-list']", "//div[contains(@class,'portal')]//div[contains(@class,'dropdown-list')]//li/label");
    public Label lblPortalError = Label.xpath("//div[contains(@class, 'search-region')]//div[@class='error-message'][3]");
    public int colUsername = 1;
    public int colLevel = 3;
    public int colTotalWager = 4;
    public Table tblReport = Table.xpath("//table[@class='ptable report']", 17);
//    public Table tblHeader = Table.xpath("//table[@class='ptable report']/thead",20);
    public Button btnSubmit = Button.name("submit");
    private Label lblRangeDate = Label.xpath("//table[@class='ptable report']//span[@class='ml-5']");
    public Label lblNoRecordsFound = Label.xpath("//tbody//td[contains(text(),'No records found.')]");
    public Label lblAtLeast1 = Label.xpath("//div[contains(text(),'Please select at least 1')]");
    public Label lblYouCanSeeReport = Label.xpath("//label[contains(text(),'You can see report data up to 6 months.')]");
    String lblTableHeader = "//thead//th[text()='%s']";
    public Label lblDateRange = Label.xpath("//span[text()='Punter Perfomance']/parent::span/following-sibling::span");
    public Button btnHome = Button.xpath("//table//span[text()='Home']");
    public void filter(String from, String to, String product, String portal, String typeCurrency) {
        if (!from.isEmpty()) {
            dpFrom.selectDate(from, "dd/MM/yyyy");
        }
        if (!to.isEmpty())
            dpTo.selectDate(to, "dd/MM/yyyy");
        if (!product.isEmpty()) {
            ddbProduct.deSelectAll(true);
            ddbProduct.selectByVisibleText(product, true);
        }
        if (!portal.isEmpty()) {
            ddbPortal.deSelectAll(true);
            ddbPortal.selectByVisibleText(portal, true);
            ddbPortal.click();
        }
        if (!typeCurrency.isEmpty())
            ddbTypeCurrency.selectByVisibleText(typeCurrency);
        btnSubmit.click();
        // waiting for loading data completely
        btnSubmit.isInvisible(2);
    }

    public String getDateFrom() {
        String[] arr = lblRangeDate.getText().split("~");
        return arr.length > 0 ? arr[0].trim() : "";
    }

    public String getDateTo() {
        String[] arr = lblRangeDate.getText().split("~");
        return arr.length > 1 ? arr[1].trim() : "";
    }

    public void drillDown(String level) {
        while (true) {
            List<String> lstLelvel = tblReport.getColumn(colLevel, false);
            if (!lstLelvel.get(0).equals(level)) {
                Link lnk = (Link) tblReport.getControlOfCell(1, colUsername, 1, "span[@class='downline']");
                if (!lnk.isDisplayed())
                    return;
                lnk.click();
                waitSpinIcon();
            } else {
                return;
            }
        }
    }

    public boolean isUsernameAsHyperlink(String level) {
        List<String> lstLelvel = tblReport.getColumn(colLevel, false);
        for (int i = 0; i < lstLelvel.size() - 1; i++) {
            Link lnk = (Link) tblReport.getControlOfCell(1, colUsername, i + 1, "span[@class='downline']");
            if (!lstLelvel.get(i).equals(level))
                return false;
            if (lstLelvel.get(i).equals("Member"))
                if (!Objects.isNull(lnk))
                    return false;
        }
        return true;
    }

    public TransactionDetailsPopup openTransactionDetail(String username) {
        List<String> lstLelvel = tblReport.getColumn(colUsername, false);
        for (int i = 0; i < lstLelvel.size() - 1; i++) {
            if (lstLelvel.get(i).equals(username)) {
                Link lnk = (Link) tblReport.getControlOfCell(1, colTotalWager, i + 1, null);
                if (Objects.nonNull(lnk)) {
                    lnk.click();
                    return new TransactionDetailsPopup();
                }
            }
        }
        System.out.println(String.format("The username %s does not display in the table", username));
        return null;
    }

    public List<String> getHeaderName() {
        List<String> lstHeaderA = tblReport.getHeaderNameOfRows();
        lstHeaderA.remove(0);
        return lstHeaderA;
    }

    public boolean isDisplayData() {
        if (isUsernameAsHyperlink("Portal")){
            System.out.println("There is data display");
            return true;
        } else if (lblNoRecordsFound.isDisplayed()){
            System.out.println("No records found.");
            return true;
        }
        return false;
    }

    public enum Product {ALL, EXCHANGE}

}
