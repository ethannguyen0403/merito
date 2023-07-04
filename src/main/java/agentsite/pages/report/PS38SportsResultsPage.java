package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.pages.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PS38SportsResultsPage extends HomePage {

    public Label lblPS38SportsResults = Label.xpath("//label[text()='PS38 Sports Results']");
    public TextBox txtDate = TextBox.cssSelector("div.filter-match-date input");
    public Label lblNoRecordFound = Label.cssSelector("td.no-record");
    public Button btnToday = Button.name("today");
    public Button btnYesterday = Button.name("yesterday");
    private DateTimePicker txtDateTime = DateTimePicker.xpath(txtDate, "//bs-days-calendar-view");
    private DropDownBox ddnSports = DropDownBox.id("select-sport");
    private Button btnSearch = Button.name("search");

    public PS38SportsResultsPage(String types) {
        super(types);
    }


    public void searchByDateAndSports(String date, String sports) {
        txtDateTime.selectDate(date, "dd/MM/yyyy");
        ddnSports.selectByVisibleContainsText(sports);
        btnSearch.click();
    }

    public void searchToday(String sports) {
        ddnSports.selectByVisibleContainsText(sports);
        btnToday.click();
    }

    public String getTodayDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        dateFormat.format(cal.getTime());
        return dateFormat.format(cal.getTime());
    }

    public void searchYesterday(String sports) {
        ddnSports.selectByVisibleContainsText(sports);
        btnYesterday.click();
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public String getYesterdayDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(yesterday());
    }
}
