package controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import membersite.controls.Cell;
import membersite.controls.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Issablla on Nov/26/2018.
 */
public class Table extends BaseElement {
    public int timeOutInSecond = 5;
    protected String _xpathTable = null;
    protected int _columnNumber = 0;
    protected String _url = "";
    protected int _numberOfTotalPage = 0;
    protected String _header = "";
    protected String _json = "";

    public Table(By locator, String xpathExpression, String url, int columnNumber, String header, String json) {
        super(locator);
        this._xpathTable = xpathExpression;
        this._url = url;
        this._columnNumber = columnNumber;
        this._header = header;
        this._json = json;
        if (url != null && url.isEmpty()) {
            this._numberOfTotalPage = 0;
        }
    }

    public static Table xpath(String xpathExpression, int columnNumber) {
        return new Table(By.xpath(xpathExpression), xpathExpression, null, columnNumber, null, null);
    }

    public static Table xpath(String xpathExpression, String url, int columnNumber, String header, String json) {
        return new Table(By.xpath(xpathExpression), xpathExpression, url, columnNumber, header, json);
    }

    /**
     * Get values of rows within a table. Specially, you can get all rows in a table as well as a limit number of rows you want to get
     *
     * @param limitedRow a number of rows are limited to be got
     * @param isMoved    yes if you want to move down in a distance in order that the invisible column can be displayed
     * @return [[], [], []]
     */
    public List<ArrayList<String>> getRows(int limitedRow, boolean isMoved) {
        return this.getRows(limitedRow, true, isMoved);
    }

    public List<ArrayList<String>> getRowsWithoutHeader(int limitedRow, boolean isMoved) {
        return this.getRows(limitedRow, false, isMoved);
    }

    /**
     * Getting column names of a table header
     *
     * @return ArrayList column names
     */
    public ArrayList<String> getColumnNamesOfTable() {
        String xpath = String.format("%s%s", this._xpathTable, "/thead/tr[1]");
        agentsite.controls.Row row = agentsite.controls.Row.xpath(xpath);
        return row.getRow(this._columnNumber, true);
    }

    /**
     * Get info of expected row index.
     *
     * @return []
     */
    public ArrayList<String> getRow(int index) {
        return getRow(1, index);
    }

    /**
     * Getting column names of a table that header contains more than 1 row
     *
     * @return ArrayList column names
     * @author isabella.huynh
     */
    public ArrayList<String> getHeaderNameOfRows() {
        ArrayList<String> lstRow = new ArrayList<String>();
        String xpath;
        Row row;
        int i = 1;
        while (true) {
            xpath = String.format("%s%s", this._xpathTable, String.format("/thead/tr[%s]", i));
            row = Row.xpath(xpath);
            if (!row.isDisplayedShort(2)) {
                if (!row.isDisplayed()) {
                    return lstRow;
                }
            } else {
                lstRow.addAll(row.getRow(this._columnNumber, true));
                i++;
            }
        }

    }

    /**
     * Get info of expected tbody and row index.
     *
     * @return []
     */
    public ArrayList<String> getRow(int tBodyOrder, int rowIndex) {
        Row row = Row.xpath(String.format("%s%s", this._xpathTable, String.format("/tbody[%d]/tr[%d]", tBodyOrder, rowIndex)));
        if (!row.isDisplayed(timeOutInSecond)) {
            return null;
        }
        return row.getRow(this._columnNumber, false);
    }

    /**
     * Get all values of rows within a table containing header names.
     *
     * @param isMoved yes if you want to move down in a distance in order that the invisible column can be displayed
     * @return [[], [], []]
     */
    public List<ArrayList<String>> getRows(boolean isMoved) {
        List<ArrayList<String>> lst = new ArrayList<ArrayList<String>>();

        // adding column names of this table
        lst.add(getColumnNamesOfTable());

        // adding value of each row into list
        String rowXpath = String.format("%s%s", this._xpathTable, "/tbody/tr[%s]");
        int i = 1;
        while (true) {
            Row row = Row.xpath(String.format(rowXpath, Integer.toString(i)));
            if (!row.isDisplayed(timeOutInSecond)) {
                return lst;
            }
            lst.add(row.getRow(this._columnNumber, false));

            // moving to next row after getting the row in case row of this table are hidden visible view
            if (isMoved) {
                row.scrollDownInDistance();
            }
            i += 1;
        }
    }

    /**
     * Search row index that have cell contain the expected value
     *
     * @param value
     * @param columnIndex
     * @return
     */
    public int getRow(String value, int columnIndex) {
        int index = 0;
        for (String i : getColumn(columnIndex, true)) {
            if (i.equals(value))
                return index;
            index++;
        }
        System.out.println(String.format("There is no row matched with expected value %s", value));
        return -1;
    }

    /**
     * Get all values of a column if you want
     *
     * @param rowTag      Incase row is /tr but some style row can be /tbody [ex: /tbody[%s]/]
     * @param columnOrder The 1st column of a table starts 1
     * @param isMoved     if true, it moving down after getting value of a cell
     * @return all values of a column
     */
    public List<String> getColumn(String rowTag, int columnOrder, boolean isMoved) {
        String rowXPath = rowTag;
        if (rowXPath == null) {
            rowXPath = "/tbody/tr[%s]/";// default row tag is /tbody/tr[1]/
        }
        List<String> lst = new ArrayList<String>();
        if (columnOrder < 1) {
            System.out.println(String.format("Error: columnOrder %s is to be more than or equal to 1", columnOrder));
            return lst;
        }
        int i = 1;
        String cellXpath = String.format("%s%s%s", this._xpathTable, rowXPath, String.format("td[%s]", columnOrder));
        while (true) {
            Cell cell = Cell.xpath(String.format(cellXpath, i));
            if (!cell.isDisplayed(timeOutInSecond)) {
                return lst;
            }
            lst.add(cell.getText(timeOutInSecond));
            if (isMoved) {
                cell.scrollDownInDistance();
            }
            i += 1;
        }
    }

    /**
     * Get all values of a column if you want
     *
     * @param columnOrder The 1st column of a table starts 1
     * @param isMoved     if true, it moving down after getting value of a cell
     * @return all values of a column
     */
    public List<String> getColumn(int columnOrder, boolean isMoved) {
        return getColumn("/tbody/tr[%s]/", columnOrder, isMoved);
    }


    /**
     * Getting column names of a table header
     *
     * @return ArrayList column names
     */
    public ArrayList<String> getColumnNamesOfTable(int rowIndex) {
        String xpath = String.format("%s%s", this._xpathTable, String.format("/thead/tr[%d]", rowIndex));
        Row row = Row.xpath(xpath);
        return row.getRow(this._columnNumber, true);
    }

    /**
     * Getting list of WebElements within a cell of a column of a table
     *
     * @param columnOrder    Column order within a table and the 1st column is 1
     * @param rowNumber      Row order within a table and the header order is 0
     * @param tagName        within a cell of a table, there are usually a tag name after td tag such as p or span
     * @param isHeader       true if it is header
     * @param isScrolled     if true, be allow to scroll up/down this control
     * @param isAlignedToTop if true, the top of the element will be aligned to the top of the visible area of the scrollable ancestor
     *                       if false, the bottom of the element will be aligned to the bottom of the visible area of the scrollable ancestor.
     *                       Default is true
     * @return a list of WebElements of a cell
     */
    public List<WebElement> getWebElementsOfCell(int columnOrder, int rowNumber, String tagName, boolean isHeader, boolean isScrolled, boolean isAlignedToTop) {
        if (columnOrder < 1) {
            System.out.println(String.format("Error: columnOrder %s is to be more than or equal to 1", columnOrder));
            return null;
        }
        if (isScrolled) {
            this.scrollToThisControl(isAlignedToTop);
        }

        // creating a row
        Row row;
        String rowXpath;
        if (isHeader) {
            rowXpath = String.format("%s%s", this._xpathTable, "/thead/tr");
            row = Row.xpath(rowXpath);
        } else {
            rowXpath = String.format("%s%s", this._xpathTable, "/tbody/tr[%s]");
            row = Row.xpath(String.format(rowXpath, rowNumber));
        }

        // verifying during 2 seconds if the row doesn't exist it will return a dictionary
        if (!row.isDisplayed(2)) {
            return null;
        }
        return row.getWebElementsOfCell(columnOrder, isHeader, tagName);
    }

    /**
     * getting a control at column and row
     *
     * @param tBodyOrder  beginning value is 1
     * @param columnOrder beginning value is 1
     * @param rowOrder    beginning value is 1
     * @param subTag      span | a etc.
     * @return BaseElement
     */
    public BaseElement getControlOfCell(int tBodyOrder, int columnOrder, int rowOrder, String subTag) {
        if (columnOrder < 1 || rowOrder < 1) {
            System.out.println(String.format("Error: columnOrder or rowOrder is  %s or %s to be more than or equal to 1", columnOrder, rowOrder));
            return null;
        }

        String cellXpath;
        if (subTag == null) {
            cellXpath = String.format("%s%s%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tBodyOrder, rowOrder), String.format("td[%s]", columnOrder));
        } else {
            cellXpath = String.format("%s%s%s//%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tBodyOrder, rowOrder), String.format("td[%s]", columnOrder), subTag);
        }
        return Link.xpath(cellXpath);
    }

    public String getControlXPathOfCell(int tBodyOrder, int columnOrder, int rowOrder, String subTag) {
        if (columnOrder < 1 || rowOrder < 1) {
            System.out.println(String.format("Error: columnOrder or rowOrder is  %s or %s to be more than or equal to 1", columnOrder, rowOrder));
            return null;
        }

        String cellXpath;
        if (subTag == null) {
            cellXpath = String.format("%s%s%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tBodyOrder, rowOrder), String.format("td[%s]", columnOrder));
        } else {
            cellXpath = String.format("%s%s%s//%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tBodyOrder, rowOrder), String.format("td[%s]", columnOrder), subTag);
        }
        return cellXpath;
    }

    /**
     * getting a control at column and row
     *
     * @param tBodyOrder  beginning value is 1
     * @param columnOrder beginning value is 1
     * @param rowOrder    beginning value is 1
     * @param subTag      span | a etc.
     * @return BaseElement
     */
    public BaseElement getLabelOfCell(int tBodyOrder, int columnOrder, int rowOrder, String subTag) {
        if (columnOrder < 1 || rowOrder < 1) {
            System.out.println(String.format("Error: columnOrder or rowOrder is  %s or %s to be more than or equal to 1", columnOrder, rowOrder));
            return null;
        }

        String cellXpath;
        if (subTag == null) {
            cellXpath = String.format("%s%s%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tBodyOrder, rowOrder), String.format("td[%s]", columnOrder));
        } else {
            cellXpath = String.format("%s%s%s//%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tBodyOrder, rowOrder), String.format("td[%s]", columnOrder), subTag);
        }
        return Label.xpath(cellXpath);
    }
	/*public String getControlOfCell(int tBodyOrder, int columnOrder, int rowOrder, String subTag)
	{
		if(columnOrder < 1 || rowOrder < 1){
			System.out.println(String.format("Error: columnOrder or rowOrder is  %s or %s to be more than or equal to 1", columnOrder, rowOrder));
			return null;
		}

		String cellXpath;
		if (subTag == null){
			cellXpath = String.format("%s%s%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tBodyOrder, rowOrder), String.format("td[%s]", columnOrder));
		} else {
			cellXpath = String.format("%s%s%s//%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tBodyOrder, rowOrder), String.format("td[%s]", columnOrder), subTag);
		}
		return cellXpath;
	}*/

    /**
     * description: header is a row #1, row number 2 and next rows contain data of table.
     *
     * @param isMoving moving down if isMoving is true
     * @return a number of rows
     */
    public int getNumberOfRows(boolean isMoving) {
        return getNumberOfRows(true, isMoving);
    }

    public int getNumberOfRows(boolean isCountHeaderRow, boolean isMoving) {
        int numberRows = 0;
        if (isCountHeaderRow) {
            Row row = Row.xpath(String.format("%s%s", this._xpathTable, "//thead//tr[1]"));
            if (row.isDisplayed(3)) {
                numberRows += 1;
            } else {
                return numberRows;
            }
        }
        String rowXpath = String.format("%s%s", this._xpathTable, "//tbody//tr[%d]");
        Row rows = Row.xpath(String.format("%s%s", this._xpathTable, "//tbody//tr"));
        return rows.getWebElements().size();
		/*
		int i = 1;
		while (true){
			Row iRow = Row.xpath(String.format(rowXpath, i));
			if (!iRow.isDisplayed(3)){
				return numberRows;
			} else{
				numberRows +=1;
				i++;
				if (isMoving) {
					iRow.scrollDownInDistance();
				}
			}
		}*/
    }

    /**
     * Getting the 1st control of a row by a same text. It will return the 1st control on a row you input
     *
     * @param comparedText a text of control you want to get
     * @param tBodyOrder   if there is more than 1 table, it can be more than 1.
     * @param beginColumn  set which column is started, but it must be less than or equal a number of columns in this table
     * @param rowOrder     set which Row is
     * @param isMoved      if true, it moving down after getting value of a cell
     * @param subTag       after td tag, you can add sub tab such as span etc.
     * @return a control
     */
    public BaseElement getControlBySameTextOnRow(String comparedText, int tBodyOrder, int beginColumn, int rowOrder, String subTag, boolean isMoved) {
        return this.getControlOnRow(comparedText, tBodyOrder, beginColumn, rowOrder, subTag, isMoved, true);
    }

    /**
     * Getting the 1st control of a row by a different text. It will return the 1st control on a row you input
     *
     * @param comparedText a text of control you want to get
     * @param tBodyOrder   if there is more than 1 table, it can be more than 1.
     * @param beginColumn  set which column is started, but it must be less than or equal a number of columns in this table
     * @param rowOrder     set which Row is
     * @param isMoved      if true, it moving down after getting value of a cell
     * @param subTag       after td tag, you can add sub tab such as span etc.
     * @return a control
     */
    public BaseElement getControlByDifferentTextOnRow(String comparedText, int tBodyOrder, int beginColumn, int rowOrder, String subTag, boolean isMoved) {
        return this.getControlOnRow(comparedText, tBodyOrder, beginColumn, rowOrder, subTag, isMoved, true);
    }

    /**
     * Get a control of a cell based on value of the different cell on the same row
     *
     * @param value
     * @param tBodyOrder
     * @param columnOrder
     * @param beginRow
     * @param subTag
     * @param columnOfControl
     * @param subTagOfControl
     * @param isMoved
     * @param isDifferentText
     * @return
     */
//	public BaseElement getControlBasedValueOfDifferentColumnOnRow(String value, int tBodyOrder, int columnOrder, int beginRow, String subTag, int columnOfControl, String subTagOfControl, boolean isMoved, boolean isDifferentText){
//		if (columnOrder < 1 && beginRow < 1 ){
//			System.out.println(String.format("Error: columnOrder %s and beginRow %s is to be more than or equal to 1", columnOrder, beginRow));
//			return null;
//		}
//		int i = beginRow;
//		String cellXpath;
//		String controlXpath;
//		if (subTag == null){
//			cellXpath = String.format("%s%s%s", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder,"tr[%s]/"), String.format("td[%s]", columnOrder));
//		} else {
//			cellXpath = String.format("%s%s%s//%s", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder,"tr[%s]/"), String.format("td[%s]", columnOrder), subTag);
//		}
//		if (subTagOfControl == null){
//			controlXpath = String.format("%s%s%s", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder,"tr[%s]/"), String.format("td[%s]", columnOfControl));
//		} else {
//			controlXpath = String.format("%s%s%s//%s", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder,"tr[%s]/"), String.format("td[%s]", columnOfControl), subTagOfControl);
//		}
//		while(true) {
//			Link lnk = Link.xpath(String.format(cellXpath, i));
//			if (!lnk.isDisplayed(timeOutInSecond)){
//				return null;
//			}
//			String text = lnk.getText().trim();
//			System.out.println("Text link is " + text);
//			if (isDifferentText){
//				if (!text.equals(value)){
//					return Link.xpath(String.format(controlXpath, i));
//				}
//			} else {
//				if (text.equals(value))
//					return Link.xpath(String.format(controlXpath, i));
//			}
//			if (isMoved){
//				lnk.scrollDownInDistance();
//			}
//			i += 1;
//		}
//	}
    public BaseElement getControlBasedValueOfDifferentColumnOnRow(String value, int tBodyOrder, int columnOrder, int beginRow, String subTag, int columnOfControl, String subTagOfControl, boolean isMoved, boolean isDifferentText) {
        if (columnOrder < 1 && beginRow < 1) {
            System.out.println(String.format("Error: columnOrder %s and beginRow %s is to be more than or equal to 1", columnOrder, beginRow));
            return null;
        }
        int i = beginRow;
        String cellXpath;
        String controlXpath;
        if (subTag == null) {
            cellXpath = String.format("(%s%s%s)[1]", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder, "tr[%s]/"), String.format("td[%s]", columnOrder));
        } else {
            cellXpath = String.format("(%s%s%s//%s)[1]", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder, "tr[%s]/"), String.format("td[%s]", columnOrder), subTag);
        }
        if (subTagOfControl == null) {
            controlXpath = String.format("(%s%s%s)[1]", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder, "tr[%s]/"), String.format("td[%s]", columnOfControl));
        } else {
            controlXpath = String.format("(%s%s%s//%s)[1]", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder, "tr[%s]/"), String.format("td[%s]", columnOfControl), subTagOfControl);
        }
        while (true) {
            Link lnk = Link.xpath(String.format(cellXpath, i));
            if (!lnk.isDisplayed(timeOutInSecond)) {
                return null;
            }
            String text = lnk.getText().trim();
            System.out.println("Text link is " + text);
            if (isDifferentText) {
                if (!text.equals(value)) {
                    return Link.xpath(String.format(controlXpath, i));
                }
            } else {
                if (text.equals(value))
                    return Link.xpath(String.format(controlXpath, i));
            }
            if (isMoved) {
                lnk.scrollDownInDistance();
            }
            i += 1;
        }
    }

    /******
     * Private methods
     */
    private BaseElement getControlOnRow(String value, int tbodyOrder, int beginColumn, int rowOrder, String subTag, boolean isMoved, boolean isDifferentText) {
        if (beginColumn < 1 && rowOrder < 1 && beginColumn > _columnNumber) {
            System.out.println(String.format("Error: beginColumn %s and rowOrder %s is to be more than or equal to 1. In addition, beginColumn %s <= _columnNumber %s",
                    beginColumn, rowOrder, beginColumn, _columnNumber));
            return null;
        }
        int i = beginColumn;
        String cellXpath;
        if (subTag == null || subTag.isEmpty()) {
            cellXpath = String.format("%s%s%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tbodyOrder, rowOrder), "td[%s]");
        } else {
            cellXpath = String.format("%s%s%s//%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tbodyOrder, rowOrder), "td[%s]", subTag);
        }
        while (true) {
            if (i > _columnNumber) {
                return null;
            }
            Link lnk = Link.xpath(String.format(cellXpath, i));
            if (!lnk.isDisplayed(timeOutInSecond)) {
                System.out.println(String.format("Debug: Cell '%s' isn't displayed", lnk.getLocator()));
                return null;
            }
            String text = lnk.getText().trim();
            System.out.println("Text link is " + text);
            if (isDifferentText) {
                if (!text.equals(value))
                    return lnk;
            } else {
                if (text.equals(value))
                    return lnk;
            }
            if (isMoved) {
                lnk.scrollDownInDistance();
            }
            i += 1;
        }
    }

    private List<ArrayList<String>> getRows(int limitedRow, boolean isHeaderGot, boolean isMoved) {
        List<ArrayList<String>> lst = new ArrayList<ArrayList<String>>();
        if (limitedRow < 1) {
            System.out.println("There is no row returned");
            return lst;
        }
        // adding column names of this table into list
        if (isHeaderGot) {
            lst.add(getColumnNamesOfTable());
        }
        // adding values of each row into list
        String rowXpath = String.format("%s%s", this._xpathTable, "/tbody/tr[%s]");
        int i = 1;
        while (true) {
            Row row = Row.xpath(String.format(rowXpath, Integer.toString(i)));
            if (!row.isDisplayedShort(timeOutInSecond)) {
                if (!row.isPresent()) {
                    return lst;
                }
                i += 1;
                continue;
            }
            lst.add(row.getRow(this._columnNumber, false));

            // moving to next row after getting the row in case row of this table are hidden visible view
            if (isMoved) {
                row.scrollDownInDistance();
            }

            // how many rows you want to get; you don't want to get all rows within a table
            if (i >= limitedRow) {
                return lst;
            }
            i += 1;
        }
    }

    public Cell getCellByName(String name, boolean isMoved) {
        if (name.isEmpty()) {
            logEndAction("Error: Month or date parameter is empty");
            return null;
        }
        String rowXpath = String.format("%s%s", this._xpathTable, "/tbody/tr[%s]");
        int i = 1;
        int rows = getNumberOfRows(false, isMoved);
        while (i <= rows) {
            Row row = Row.xpath(String.format(rowXpath, Integer.toString(i)));
            if (!row.isDisplayed(timeOutInSecond)) {
                return null;
            }
            for (int j = 1; j <= this._columnNumber; j++) {
                Cell e = row.getCellByName(name, j);
                if (e != null) {
                    return e;
                }
            }

            // moving to next row after getting the row in case row of this table are hidden visible view
            if (isMoved) {
                row.scrollDownInDistance();
            }
            i += 1;
        }
        logEndAction(String.format("Debug: There is no value '%s' you want", name));
        return null;
    }

    public int getColumnIndexByName(String columnName) {
        ArrayList<String> lstHeader = getHeaderNameOfRows();
        for (int i = 0, n = lstHeader.size(); i < n; i++) {
            if (lstHeader.get(i).equals(columnName))
                return i + 1;
        }
        return -1;
    }

    public List<Integer> getListColumnIndexByListName(List<String> lstColumnName) {
        ArrayList<String> lstHeader = getHeaderNameOfRows();
        List<Integer> lstIndex = new ArrayList<>();
        for (int i = 0; i < lstColumnName.size(); i++) {
            lstIndex.add(lstHeader.indexOf(lstColumnName.get(i)));
        }
        return lstIndex;
    }
}
