package controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import membersite.controls.Cell;
import membersite.controls.Row;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author by Isabella
 * created on Nov/9/2019
 */
public class DateTimePickerOldUI extends BaseElement {
	static String _xpath = null;
	private Button btnPrevious;
	private Button btnNext;
	private Label lblMonthYear;
//	private Label lblYear;
	private Table tblCalenderMonth;
	private Table tblCalender;
	private Table tblCalenderDate;
	private TextBox txtDate;
	private Button btnMonthPrevious;
	private Button btnMonthNext;
	private DateTimePickerOldUI(TextBox txtBox, By locator, String xpath) {
		super(locator);
		txtDate = txtBox;
		_xpath = xpath;
        btnPrevious = Button.xpath(String.format("%s//%s", _xpath, "th[@class='prev']"));
        btnNext = Button.xpath(String.format("%s//%s", _xpath, "th[@class='next']"));
        lblMonthYear = Label.xpath(String.format("%s//%s", _xpath, "th[@class='datepicker-switch']"));
		tblCalenderDate = Table.xpath(String.format("%s//%s", _xpath, "div[@class='datepicker-days']/table"), 7);
		tblCalenderMonth =Table.xpath(String.format("%s//%s", _xpath, "div[@class='datepicker-months']/table"), 1);
        tblCalender = Table.xpath(String.format("%s//%s", _xpath, "table[@class='datepicker-years']/table"), 4);
		btnMonthPrevious = Button.xpath(String.format("%s//%s", _xpath, "div[@class='datepicker-months']/table//th[@class='prev']"));
		btnMonthNext = Button.xpath(String.format("%s//%s", _xpath, "div[@class='datepicker-months']/table//th[@class='next']"));
	}
	
	public static DateTimePickerOldUI xpath(TextBox txtBox, String xpathExpression) {
		return new DateTimePickerOldUI(txtBox, By.xpath(xpathExpression), xpathExpression);
	}

	/**
	 * Clicking a date of an early month
	 * @param month must be less than 0
	 * 			how many early months are but depending on the current month of the picker
	 * 			e.g. if this picker is Jan 2018, 2 early month is Nov 2017
	 * @param date it should be 1 to 28
	 */
	public void previousMonthWithDate(int month, String date){
		if (month > 0 || date.isEmpty() || Integer.parseInt(date) > 31 ){
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
			while(month < 0){
				this.btnPrevious.click();
				month++;
			}
			this.clickDate(date, "Previous");
		}
	}

	/**
	 * Clicking on a date on the current month of the picker
	 * @param date it should be 1 to 28
	 */
	public void currentMonthWithDate(String date) {
		logStartAction(String.format("click date '%s' of current month on Calender", date));
		txtDate.click();
		this.clickDate(date, "Current");
	}

	/**
	 * Clicking a date of a later month but it depends on the picker
	 * @param month how many next months are but depending on the current month of the picker
	 *              e.g. if this picker is Jan 2018, 2 later month is March 2018
	 * @param date it should be 1 to 28
	 */
	public void nextMonthWithDate(int month, String date){
		if (month < 1 || date.isEmpty() || Integer.parseInt(date) > 31 ){
			logEndAction(String.format("Error: Date parameter '%s' is empty || month %s  is less than 1 || date %s is > 31", month, date, date));
			return;
		}
		logStartAction(String.format("click date '%s' on Calender", date));
		txtDate.click();
		if (month == 0) {
			currentMonthWithDate(date);
		} else {
			while(month > 0) {
				if (this.btnNext.isClickable(2)){
					this.btnNext.click();
				}
				month--;
			}
			this.clickDate(date, "Next");
		}
	}

	public void selectDate(String input,String format)
	{
		txtDate.click();
		String currentYearMonth = lblMonthYear.getText().trim();
		String currentYear = currentYearMonth.split(" ")[1];
		String currentMonth = currentYearMonth.split(" ")[0];
		SimpleDateFormat sd = new SimpleDateFormat(format);

		try {
			Date date = sd.parse(input);
			sd = new SimpleDateFormat("yyyy");
			String y =  sd.format(date);
			sd = new SimpleDateFormat("MMM");
			String mm =  sd.format(date);
			sd = new SimpleDateFormat("d");
			String dd =  sd.format(date);

			int year = Integer.parseInt(y)-Integer.parseInt(currentYear);

			// click year
			if(!currentYear.equals(y)){
				lblMonthYear.click();
				if(year < 0){
					btnMonthPrevious.click();
				}else
					btnMonthPrevious.click();
				if(!currentMonth.equals(mm))
				{
				//	lblMonthYear.click();
					clickMonth(mm);
				}
				//clickDate(y,"");
			}
			clickDay(dd,false);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	/*******************
	 * Private methods
	 *******************/
	private void clickDate(String date, String btnName){
		Cell e = tblCalender.getCellByName(date, false);
		if (e != null){
			e.click();
			logEndAction(String.format("clicked date '%s' on Calendar", date));
		} else {
			logEndAction(String.format("cannot click %s button on on Calendar because Cell is null", btnName));
		}
	}

	private void clickMonth(String month){
		if(month.isEmpty()){
			logEndAction("Error: Month or date parameter is empty");
			return ;
		}
		int i= 1;
		Label monthLable;

		while (true){
			monthLable = Label.xpath(String.format("%s%s", tblCalenderMonth._xpathTable, String.format("/tbody/tr[1]/td[1]/span[%s]",i)));
			if (!monthLable.isDisplayed()){
				logEndAction(String.format("cannot click %s button on on Calendar because Cell is null", month));
				return;
			}
			if(monthLable.getText().equals(month)){
				monthLable.click();
				logEndAction(String.format("clicked date '%s' on Calendar", month));
			}
			i = i+1;
		}
	}


	private void clickDay(String name, boolean isMoved){
		if(name.isEmpty()){
			logEndAction("Error: Month or date parameter is empty");
			return ;
		}
		String rowXpath = String.format("%s%s", tblCalenderDate._xpathTable, "/tbody/tr[%s]");
		int i = 1;
		int rows = tblCalenderDate.getNumberOfRows(false,isMoved);
		while(i <= rows) {
			Row row = Row.xpath(String.format(rowXpath, Integer.toString(i)));
			if(!row.isDisplayed(3)){
				return ;
			}
			for (int j=2; j <= tblCalenderDate._columnNumber+1; j ++) {
				Cell e = row.getCellByName(name, j);
				if (Objects.nonNull(e)) {
					if (Label.xpath(e.getLocator().toString().split("By.xpath: ")[1]).getAttribute("class").contains("old day")) {
						continue;
					}
					e.click();
					logEndAction(String.format("clicked date '%s' on Calendar", name));
					return;
				}
			}
			// moving to next row after getting the row in case row of this table are hidden visible view
			if (isMoved){
				row.scrollDownInDistance();
			}
			i += 1;
		}
		logEndAction(String.format("Debug: There is no value '%s' you want", name));
		return ;
	}
}
