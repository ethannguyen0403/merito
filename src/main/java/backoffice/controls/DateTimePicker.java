package backoffice.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author by Liam.Ho
 * created on Nov/9/2019
 */
public class DateTimePicker extends BaseElement {
    static String _xpath = null;
    private Button btnPrevious;
    private Button btnNext;
    private Label lblMonthYear;
    private Table tblCalender;
    private TextBox txtDate;
    private Table tblCalenderDate;
    private Label lblMonth;
    private Label lblYear;
    private Table tblCalenderMonth;
    private Table tblCalenderYear;

    private DateTimePicker(TextBox txtBox, By locator, String xpath) {
        super(locator);
        txtDate = txtBox;
        _xpath = xpath;
        btnPrevious = Button.xpath(String.format("%s//%s", _xpath, "button[@class='previous']"));
        btnNext = Button.xpath(String.format("%s//%s", _xpath, "button[@class='next']"));
        lblMonthYear = Label.xpath(String.format("%s//%s", _xpath, "th[@class='datepicker-switch']"));
        tblCalender = Table.xpath(String.format("%s//%s", _xpath, "table[contains(@class, 'years')]"), 7);
        lblMonth = Label.xpath(String.format("%s//%s", _xpath, "button[@class='current ng-star-inserted']"));
        lblYear = Label.xpath(String.format("%s//%s", _xpath, "button[@class='current']"));
        tblCalenderDate = Table.xpath(String.format("%s//%s", _xpath, "table[@class='days weeks']"), 7);
        tblCalenderMonth = Table.xpath(String.format("%s//%s", _xpath, "table[@class='months']"), 3);
        tblCalenderYear = Table.xpath(String.format("%s//%s", _xpath, "table[@class='years']"), 4);
    }

    public static DateTimePicker xpath(TextBox txtBox, String xpathExpression) {
        return new DateTimePicker(txtBox, By.xpath(xpathExpression), xpathExpression);
    }

    /**
     * Clicking a date of an early month
     *
     * @param month must be less than 0
     *              how many early months are but depending on the current month of the picker
     *              e.g. if this picker is Jan 2018, 2 early month is Nov 2017
     * @param date  it should be 1 to 28
     */
    public void previousMonthWithDate(int month, String date) {
        if (month > 0 || date.isEmpty() || Integer.parseInt(date) > 31) {
            logEndAction(String.format("Error: Date parameter '%s' is empty || month %s  is more than 0 || date %s is > 31", month, date, date));
            return;
        }
        logStartAction(String.format("click date '%s' on Calender", date));
        txtDate.isDisplayed();
        txtDate.isInvisible(2);
        txtDate.click();
        if (month == 0) {
            currentMonthWithDate(date);
        } else {
            while (month < 0) {
                this.btnPrevious.click();
                month++;
            }
            this.clickDate(date, "Previous");
        }
    }

    /**
     * Clicking on a date on the current month of the picker
     *
     * @param date it should be 1 to 28
     */
    public void currentMonthWithDate(String date) {
        logStartAction(String.format("click date '%s' of current month on Calender", date));
        txtDate.click();
        this.clickDate(date, "Current");
    }

    /**
     * Clicking a date of a later month but it depends on the picker
     *
     * @param month how many next months are but depending on the current month of the picker
     *              e.g. if this picker is Jan 2018, 2 later month is March 2018
     * @param date  it should be 1 to 28
     */
    public void nextMonthWithDate(int month, String date) {
        if (month < 1 || date.isEmpty() || Integer.parseInt(date) > 31) {
            logEndAction(String.format("Error: Date parameter '%s' is empty || month %s  is less than 1 || date %s is > 31", month, date, date));
            return;
        }
        logStartAction(String.format("click date '%s' on Calender", date));
        txtDate.click();
        if (month == 0) {
            currentMonthWithDate(date);
        } else {
            while (month > 0) {
                if (this.btnNext.isClickable(2)) {
                    this.btnNext.click();
                }
                month--;
            }
            this.clickDate(date, "Next");
        }
    }

    public void selectDate(String input, String format) {
        txtDate.click();
        String currentYear = lblYear.getText().trim();
        String currentMonth = lblMonth.getText().trim();
        SimpleDateFormat sd = new SimpleDateFormat(format);

        try {
            Date date = sd.parse(input);
            sd = new SimpleDateFormat("yyyy");
            String y = sd.format(date);
            sd = new SimpleDateFormat("MMMM");
            String mm = sd.format(date);
            sd = new SimpleDateFormat("d");
            String dd = sd.format(date);
            // click year
            if (!currentYear.equals(y)) {
                lblYear.click();
                clickYear(y, "");
                clickMonth(mm);
            }
            //
            if (!currentMonth.equals(mm) && currentYear.equals(y)) {
                lblMonth.click();
                clickMonth(mm);
            }
            clickDay(dd, false);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /*******************
     * Private methods
     *******************/
    private void clickDate(String date, String btnName) {
        Cell e = tblCalender.getCellByName(date, false);
        if (e != null) {
            e.click();
            logEndAction(String.format("clicked year '%s' on Calendar", date));
        } else {
            logEndAction(String.format("cannot click %s button on on Calendar because Cell is null", btnName));
        }
    }

    private void clickYear(String year, String btnName) {
        Cell e = tblCalenderYear.getCellByName(year, false);
        if (e != null) {
            e.click();
            logEndAction(String.format("clicked date '%s' on Calendar", year));
        } else {
            logEndAction(String.format("cannot click %s button on on Calendar because Cell is null", btnName));
        }
    }

    private void clickMonth(String month) {
        Cell e = tblCalenderMonth.getCellByName(month, false);
        if (e != null) {
            e.click();
            logEndAction(String.format("clicked date '%s' on Calendar", month));
        } else {
            logEndAction(String.format("cannot click %s button on on Calendar because Cell is null", month));
        }
    }


    private void clickDay(String name, boolean isMoved) {
        if (name.isEmpty()) {
            logEndAction("Error: Month or date parameter is empty");
            return;
        }
        Label lblDay = Label.xpath(String.format("%s//td[@role='gridcell']//span[text()='%s' and not(contains(@class,'disable'))]",tblCalenderDate._xpathTable,name));
        if(!lblDay.isDisplayed()) {
            logEndAction(String.format("Debug: There is no value '%s' you want", name));
            return;
        }
        lblDay.click();
//        lblDay.click();
//        String rowXpath = String.format("%s%s", tblCalenderDate._xpathTable, "/tbody/tr[%s]");
//        int i = 1;
//        int rows = 6;//tblCalenderDate.getNumberOfRows(false,isMoved);
//        while (i <= rows) {
//            Row row = Row.xpath(String.format(rowXpath, i));
//            if (!row.isDisplayed(3)) {
//                return;
//            }
//            for (int j = 2; j <= tblCalenderDate._columnNumber + 1; j++) {
//                Cell e = row.getCellByName(name, j);
//                if (Objects.nonNull(e)) {
//                    if (Label.xpath(e.getLocator().toString().split("By.xpath: ")[1] + "//span[@class='is-other-month']").isDisplayed()) {
//                        continue;
//                    }
//                    //Label lbl = Label.xpath(e.getLocator().toString().split("By.xpath: ")[1]+"//span");
//                    if (Label.xpath(e.getLocator().toString().split("By.xpath: ")[1] + "//span").isDisplayed()) {
//                        e.click();
//                        logEndAction(String.format("clicked date '%s' on Calendar", name));
//                        return;
//                    }
//                }
//            }
//
//            // moving to next row after getting the row in case row of this table are hidden visible view
//            if (isMoved) {
//                row.scrollDownInDistance();
//            }
//            i += 1;
//        }
//        logEndAction(String.format("Debug: There is no value '%s' you want", name));
//        return;
    }
}
