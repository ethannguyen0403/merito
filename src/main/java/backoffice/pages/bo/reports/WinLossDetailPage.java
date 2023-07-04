package backoffice.pages.bo.reports;

import backoffice.controls.DateTimePicker;
import backoffice.controls.DropDownBox;
import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.reports.component.TransactionDetailsPopup;
import com.paltech.element.common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinLossDetailPage extends HomePage {
    public TextBox txtDateFrom = TextBox.xpath("//app-winloss-detail//input[@name='from-date']");
    public TextBox txtDateTo = TextBox.xpath("//app-winloss-detail//input[@name='to-date']");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtDateFrom, "//div[contains(@class,'bs-calendar-container')]");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtDateTo, "//div[contains(@class,'bs-calendar-container')]");
    public com.paltech.element.common.DropDownBox ddbTypeCurrency = com.paltech.element.common.DropDownBox.xpath("//div[contains(@class,'currency')]//select");
    public DropDownBox ddbProduct = DropDownBox.xpath("//angular2-multiselect[contains(@name,'product')]", "//div[contains(@class,'dropdown-list')]//li/label");
    public Label lblProductError = Label.xpath("//div[contains(@class, 'search-region')]//div[@class='error-message'][2]");
    public DropDownBox ddbPortal = DropDownBox.xpath("//angular2-multiselect[contains(@name,'portals')]", "//div[contains(@class,'dropdown-list')]//li/label");
    public Label lblPortalError = Label.xpath("//div[contains(@class, 'search-region')]//div[@class='error-message'][3]");
    public CheckBox chkShowTotalOnly = CheckBox.xpath("//div[@id='winLossDetailType']//input[@name='show-total-only']");
    public int colUsername = 1;
    public int colLevel = 2;
    public int colTotalWager = 4;
    public int colTurnOverL = 5;
    public Table tblWinLossDetail = Table.xpath("//table[@class='ptable report']", 6);
    Button btnYesterday = Button.xpath("//div[@id='winLossDetailType']//button[@name='yesterday']");
    Button btnLastWeek = Button.xpath("//div[@id='winLossDetailType']//button[@name='lastweek']");
    Button btnSubmit = Button.xpath("//div[@id='winLossDetailType']//button[@name='submit']");
    private Label lblRangeDate = Label.xpath("//table[@class='ptable report']//span[@class='ml-5']");

    public void filter() {
        filter("");
    }

    public void filter(String product) {
        if (!product.isEmpty()) {
            ddbProduct.deSelectAll(true);
            ddbProduct.selectByVisibleText(product, true);
        }
        btnSubmit.click();
        // waiting for loading data completely
        btnSubmit.isInvisible(2);
    }

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
        waitSpinIcon();
    }

    public void filterWithLastWeek() {
        btnLastWeek.click();
        // waiting for loading
        btnLastWeek.isInvisible(2);
        waitSpinIcon();
    }

    public void filterWithYesterday() {
        btnYesterday.click();
        // waiting for loading
        btnYesterday.isInvisible(2);
        waitSpinIcon();
    }

    public String getDateFrom() {
        String[] arr = lblRangeDate.getText().split("~");
        return arr.length > 0 ? arr[0].trim() : "";
    }

    public String getDateTo() {
        String[] arr = lblRangeDate.getText().split("~");
        return arr.length > 1 ? arr[1].trim() : "";
    }

    public List<Integer> getTotalWager() {
        List<Integer> lstInfo = new ArrayList<>();
        List<String> lstTotalWager = tblWinLossDetail.getColumn(colTotalWager, false);
        if (lstTotalWager.size() == 0) {
            return null;
        }
        int totalWager = 0;
        int totalWagerOnLastRow = lstTotalWager.size() == 0 ? 0 : Integer.parseInt(lstTotalWager.get(lstTotalWager.size() - 1).replace(",", ""));
        for (int i = 0; i < lstTotalWager.size() - 1; i++) {
            totalWager += Integer.parseInt(lstTotalWager.get(i));
        }
        lstInfo.add(totalWager);
        lstInfo.add(totalWagerOnLastRow);
        return lstInfo;
    }

    public double getTurnOverL() {
        List<String> lstTurnOverL = tblWinLossDetail.getColumn(colTurnOverL, false);
        double turnOverL = 0;
        for (int i = 0; i < lstTurnOverL.size() - 1; i++) {
            turnOverL += Double.parseDouble(lstTurnOverL.get(i));
        }
        return turnOverL;
    }

    public void drillDown(String level) {
        int i = 1;
        while (i <= 7) {
            List<String> lstLelvel = tblWinLossDetail.getColumn(colLevel, false);
            if (!lstLelvel.get(0).equals(level)) {
                Link lnk = (Link) tblWinLossDetail.getControlOfCell(1, colUsername, 1, "span[@class='downline']");
                if (lnk.isDisplayed()) {
                    lnk.click();
                    waitSpinIcon();
                }
            } else {
                return;
            }
            i = i + 1;
        }
    }

    public boolean isUsernameAsHyperlink(String level) {
        List<String> lstLelvel = tblWinLossDetail.getColumn(colLevel, false);
        for (int i = 0; i < lstLelvel.size() - 1; i++) {
            Link lnk = (Link) tblWinLossDetail.getControlOfCell(1, colUsername, i + 1, "span[@class='downline']");
            if (!lstLelvel.get(i).equals(level))
                return false;
            if (lstLelvel.get(i).equals("Member"))
                if (!Objects.isNull(lnk))
                    return false;
        }
        return true;
    }

    public TransactionDetailsPopup openTransactionDetail(String username) {
        List<String> lstLelvel = tblWinLossDetail.getColumn(colUsername, false);
        for (int i = 0; i < lstLelvel.size() - 1; i++) {
            if (lstLelvel.get(i).equals(username)) {
                Link lnk = (Link) tblWinLossDetail.getControlOfCell(1, colTotalWager, i + 1, null);
                if (Objects.nonNull(lnk)) {
                    lnk.click();
                    return new TransactionDetailsPopup();
                }
            }
        }
        System.out.println(String.format("The username %s does not display in the table", username));
        return null;
    }

    public enum Product {ALL, EXCHANGE}
}
