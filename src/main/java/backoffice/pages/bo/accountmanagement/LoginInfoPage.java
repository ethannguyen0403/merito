package backoffice.pages.bo.accountmanagement;

import backoffice.controls.DateTimePicker;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.RadioButton;
import com.paltech.element.common.TextBox;

import java.util.List;

public class LoginInfoPage extends HomePage {
    public RadioButton rbActivity = RadioButton.xpath("//input[@value='activity']");
    public RadioButton rbLog = RadioButton.xpath("//input[@value='log']");
    public TextBox txtLoginIdActivity = TextBox.name("username");
    public TextBox txtLoginIdLog = TextBox.xpath("//input[contains(@class, 'user-name')]");
    public DropDownBox ddbStatus = DropDownBox.name("status");
    public TextBox txtDateRangeFrom = TextBox.name("from-date");
    public DateTimePicker dtpDateRangeFrom = DateTimePicker.xpath(txtDateRangeFrom, "//bs-days-calendar-view");
    public TextBox txtDateRangeTo = TextBox.name("to-date");
    public DateTimePicker dtpDateRangeTo = DateTimePicker.xpath(txtDateRangeTo, "//bs-days-calendar-view");
    public Button btnToday = Button.xpath("//button[contains(text(), 'Today')]");
    public Button btnLast7Days = Button.xpath("//button[contains(text(), 'Last 7 Days')]");
    public Button btnLast30Days = Button.xpath("//button[contains(text(), 'Last 30 Days')]");
    public Button btnSearch = Button.xpath("//button[contains(text(), 'Search')]");
    public int colLoginID = 1;
    public int colDateTime = 2;
    public int colStatus = 3;
    public int colIPAdress = 4;
    public int colISP = 5;
    public int colCity = 6;
    public int colSate = 7;
    public int colCountry = 8;
    public int colLoginStatus = 4;
    public int colLogIPAddress = 5;
    public int colLoginIDIP = 2;
    private int totalColumn = 8;
    public StaticTable tblLoginInfoActivity = StaticTable.xpath("//div[@class='m-4 table-wrapper']//div[@class='custom-table']", "perfect-scrollbar//div[@class='ps-content']", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);
    public StaticTable tblIPAddress = StaticTable.xpath("//app-login-log/div/div[2]/div[2]//div[@class='custom-table']", "perfect-scrollbar//div[@class='ps-content']", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);
    private int totalColumnLog = 6;
    public StaticTable tblLoginInfoLog = StaticTable.xpath("//div[@class='m-4 table-wrapper']//div[@class='custom-table']", "perfect-scrollbar//div[@class='ps-content']", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumnLog);
    private int totalColumnIP = 5;

    public void selectType(TYPE type) {
        switch (type) {
            case LOG:
                rbLog.click();
                break;
            case ACTIVITY:
                rbActivity.click();
                break;
        }
    }

    public void clickSearch(SEARCHBUTTON btn) {
        switch (btn) {
            case TODAY:
                btnToday.click();
                return;
            case LAST7DAYS:
                btnLast7Days.click();
                return;
            case LAST30DAYS:
                btnLast30Days.click();
                return;
            default:
                btnSearch.click();
                return;
        }
    }

    public void search(TYPE type, String loginID, String status, String from, String to, SEARCHBUTTON btn) {
        selectType(type);
        if (!loginID.isEmpty()) {
            TextBox targetTxt = type.equals(TYPE.LOG) ? txtLoginIdLog : txtLoginIdActivity;
            targetTxt.sendKeys(loginID);
        }
        if (!status.isEmpty())
            ddbStatus.selectByVisibleText(status);
        if (!from.isEmpty())
            dtpDateRangeFrom.selectDate(from, "dd/MM/yyyy");
        if (!to.isEmpty())
            dtpDateRangeTo.selectDate(to, "dd/MM/yyyy");
        clickSearch(btn);
    }

    public void clickIPAddress(String loginId) {
        List<String> lstLogin = tblLoginInfoLog.getColumn(colLoginID, false);
        for (int i = 0; i < lstLogin.size(); i++) {
            if (lstLogin.get(i).equals(loginId)) {
                System.out.println(String.format("Click on IP address link according login ID %s in row %s", loginId, i + 1));
                tblLoginInfoLog.getControlOfCell(1, colLogIPAddress, i + 1, null).click();
                return;
            }
        }
        System.out.println(String.format("Login ID not have login log", loginId));
    }

    public enum TYPE {ACTIVITY, LOG}

    public enum SEARCHBUTTON {TODAY, LAST7DAYS, LAST30DAYS, SEARCH}
}

