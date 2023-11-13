package backoffice.controls.bo;

import backoffice.controls.Cell;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author isabella.huynh
 * @date Dec/31/2019.
 */
public class StaticTable extends BaseElement {
    private String _xpathTable;
    private int _columnNumber = 0;
    private String _url = "";
    private int _numberOfTotalPage = 0;
    private String _header = "";
    private String _json = "";
    private String _xpathBody = "";
    private String _xpathRow = "";
    private String _xpathCol = "";
    private int timeOutInSecond = 5;
    private int timeOutShort = 2;

    private StaticTable(By locator, String xpathExpression, String url, int columnNumber, String header, String json, String xpathBody, String xpathRow, String xpathCol) {
        super(locator);
        this._xpathTable = xpathExpression;
        this._url = url;
        this._columnNumber = columnNumber;
        this._header = header;
        this._json = json;
        this._xpathBody = xpathBody;
        this._xpathRow = xpathRow;
        this._xpathCol = xpathCol;
        if (url != null && url.isEmpty()) {
            this._numberOfTotalPage = 0;
        }
    }

    public static StaticTable xpath(String xpathExpression, String xpathBody, String xpathRow, String xpathCol, int columnNumber) {
        return new StaticTable(By.xpath(xpathExpression), xpathExpression, null, columnNumber, null, null, xpathBody, xpathRow, xpathCol);
    }
	/*public static StaticTable xpath(String xpathExpression, int columnNumber) {
		return new StaticTable(By.xpath(xpathExpression), xpathExpression, null, columnNumber, null, null,null,null);
	}*/


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
        String rowXpath = String.format("%s%s%s", this._xpathTable, this._xpathBody, this._xpathRow);
        int i = 1;
        while (true) {
            ARow row = ARow.xpath(String.format("%s[%d]", rowXpath, i));
            if (!row.isDisplayedShort(timeOutShort)) {
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
     * Get all values of a column if you want
     *
     * @param columnOrder The 1st column of a table starts 1
     * @param isMoved     if true, it moving down after getting value of a cell
     * @return all values of a column
     */
    public List<String> getColumn(int columnOrder, boolean isMoved) {
        return getColumn(columnOrder, -1, isMoved);
    }

    public List<String> getColumn(int columnOrder, int limit, boolean isMoved) {
        List<String> lst = new ArrayList<String>();
        if (columnOrder < 1) {
            System.out.println(String.format("Error: columnOrder %s is to be more than or equal to 1", columnOrder));
            return lst;
        }
        int i = 1;
        String cellXpath = String.format("%s//%s//%s", this._xpathTable, this._xpathBody, this._xpathRow);
        //this.click();
        while (true) {
            Cell cell = Cell.xpath(String.format("%s[%d]//%s", cellXpath, i, String.format("%s[%d]", this._xpathCol, columnOrder)));

            if (!cell.isDisplayedShort(timeOutShort)) {
                return lst;
            }
            lst.add(cell.getText(timeOutShort));
            if (lst.size() == limit) {
                return lst;
            }
            if (isMoved) {
//                if (!cell.isDisplayedShort(timeOutShort)) {
//                    cell.scrollToThisControl(true);
//                }
                cell.scrollToThisControl(true);
            }
            i += 1;
        }
    }


    /**
     * Getting column names of a table header
     *
     * @return ArrayList column names
     */
    public ArrayList<String> getColumnNamesOfTable() {
        String xpath = String.format("%s%s", this._xpathTable, "//div[contains(@class,'custom-table-header')]");
        ARow row = ARow.xpath(xpath);
        return row.getRow(this._columnNumber, true);
    }

    /**
     * Getting list of WebElements within a cell of a column of a table
     *
     * @param columnOrder    Column order within a table and the 1st column is 1
     * @param rowNumber      ARow order within a table and the header order is 0
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
        ARow row;
        String rowXpath;
        if (isHeader) {
            rowXpath = String.format("%s%s", this._xpathTable, "//div[contains(@class,'custom-table-header')]/div");
            row = ARow.xpath(rowXpath);
        } else {
            rowXpath = String.format("%s%s", this._xpathTable, _xpathRow);
            row = ARow.xpath(String.format(rowXpath, rowNumber));
        }

        // verifying during 2 seconds if the row doesn't exist it will return a dictionary
        if (!row.isDisplayedShort(2)) {
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
            cellXpath = String.format("%s//%s//%s//%s", this._xpathTable, String.format("%s[%s]", this._xpathBody, tBodyOrder), String.format("%s[%d]", this._xpathRow, rowOrder), String.format("%s[%d]", this._xpathCol, columnOrder));
        } else {
            cellXpath = String.format("%s//%s//%s//%s//%s", this._xpathTable, String.format("%s[%s]", this._xpathBody, tBodyOrder), String.format("%s[%s]", this._xpathRow, rowOrder), String.format("%s[%s]", this._xpathCol, columnOrder), subTag);
        }
        return Link.xpath(cellXpath);
    }

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
            ARow row = ARow.xpath(String.format("%s%s", this._xpathTable, "//div[contains(@class,'custom-table-header')]/div[1]"));
            if (row.isDisplayed(3)) {
                numberRows += 1;
            } else {
                return numberRows;
            }
        }
        String rowXpath = String.format("%s//%s//%s[%d]", this._xpathTable, this._xpathBody, this._xpathRow);
        int i = 1;
        while (true) {
            ARow iRow = ARow.xpath(String.format(rowXpath, i));
            if (!iRow.isDisplayedShort(3)) {
                return numberRows;
            } else {
                numberRows += 1;
                i++;
                if (isMoving) {
                    iRow.scrollDownInDistance();
                }
            }
        }
    }

    //	/**
//	 * Getting the 1st control of a row by a same text. It will return the 1st control on a row you input
//	 * @param comparedText a text of control you want to get
//	 * @param tBodyOrder if there is more than 1 table, it can be more than 1.
//	 * @param beginColumn set which column is started, but it must be less than or equal a number of columns in this table
//	 * @param rowOrder set which ARow is
//	 * @param isMoved if true, it moving down after getting value of a cell
//	 * @param subTag after td tag, you can add sub tab such as span etc.
//	 * @return a control
//	 */
//	public BaseElement getControlBySameTextOnRow(String comparedText, int tBodyOrder, int beginColumn, int rowOrder, String subTag, boolean isMoved){
//		return this.getControlOnRow(comparedText, tBodyOrder, beginColumn, rowOrder, subTag, isMoved, true);
//	}
//
//	/**
//	 * Getting the 1st control of a row by a different text. It will return the 1st control on a row you input
//	 * @param comparedText a text of control you want to get
//	 * @param tBodyOrder if there is more than 1 table, it can be more than 1.
//	 * @param beginColumn set which column is started, but it must be less than or equal a number of columns in this table
//	 * @param rowOrder set which ARow is
//	 * @param isMoved if true, it moving down after getting value of a cell
//	 * @param subTag after td tag, you can add sub tab such as span etc.
//	 * @return a control
//	 */
//	public BaseElement getControlByDifferentTextOnRow(String comparedText, int tBodyOrder, int beginColumn, int rowOrder, String subTag, boolean isMoved){
//		return this.getControlOnRow(comparedText, tBodyOrder, beginColumn, rowOrder, subTag, isMoved, true);
//	}
//
//
    public String getXPathControlBasedValueOfDifferentColumnOnRow(String value, int tBodyOrder, int columnOrder, int beginRow, String subTag, int columnOfControl, String subTagOfControl, boolean isMoved, boolean isDifferentText) {
        if (columnOrder < 1 && beginRow < 1) {
            System.out.println(String.format("Error: columnOrder %s and beginRow %s is to be more than or equal to 1", columnOrder, beginRow));
            return null;
        }
        int i = beginRow;
        String cellXpath;
        String controlXpath;
        if (subTag == null) {
            cellXpath = String.format("(%s%s%s)[1]", this._xpathTable, String.format("/%s[%s]/%s", _xpathBody, tBodyOrder, _xpathRow + "[%s]/"), String.format("%s[%s]", _xpathCol, columnOrder));
        } else {
//			cellXpath = String.format("(%s%s%s//%s)[1]", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder,"tr[%s]/"), String.format("td[%s]", columnOrder), subTag);
            cellXpath = String.format("(%s%s%s//%s)[1]", this._xpathTable, String.format("/%s[%s]/%s", _xpathBody, tBodyOrder, _xpathRow + "[%s]/"), String.format("%s[%s]", _xpathCol, columnOrder), subTag);
        }
        if (subTagOfControl == null) {
            //controlXpath = String.format("(%s%s%s)[1]", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder,"tr[%s]/"), String.format("td[%s]", columnOfControl));
            controlXpath = String.format("(%s%s%s)[1]", this._xpathTable, String.format("/%s[%s]/%s", _xpathBody, tBodyOrder, _xpathRow + "[%s]/"), String.format("%s[%s]", _xpathCol, columnOfControl));
        } else {
            //controlXpath = String.format("(%s%s%s//%s)[1]", this._xpathTable, String.format("/tbody[%s]/%s", tBodyOrder,"tr[%s]/"), String.format("td[%s]", columnOfControl), subTagOfControl);

            controlXpath = String.format("(%s%s%s//%s)[1]", this._xpathTable, String.format("/%s[%s]/%s", _xpathBody, tBodyOrder, _xpathRow + "[%s]/"), String.format("%s[%s]", _xpathCol, columnOfControl), subTagOfControl);
        }
        while (true) {
            Link lnk = Link.xpath(String.format(cellXpath, i));
            if (!lnk.isDisplayed(timeOutShort)) {
                return null;
            }
            String text = lnk.getText().trim();
            System.out.println("Text link is " + text);
            if (isDifferentText) {
                if (!text.equals(value)) {
                    return String.format(controlXpath, i);
                }
            } else {
                if (text.equals(value))
                    String.format(controlXpath, i);
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
//	private BaseElement getControlOnRow(String value, int tbodyOrder, int beginColumn, int rowOrder, String subTag, boolean isMoved, boolean isDifferentText){
//		if (beginColumn < 1 && rowOrder < 1 &&  beginColumn > _columnNumber){
//			System.out.println(String.format("Error: beginColumn %s and rowOrder %s is to be more than or equal to 1. In addition, beginColumn %s <= _columnNumber %s",
//					beginColumn, rowOrder, beginColumn, _columnNumber));
//			return null;
//		}
//		int i = beginColumn;
//		String cellXpath;
//		if (subTag == null || subTag.isEmpty()){
//			cellXpath = String.format("%s%s%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tbodyOrder, rowOrder), "td[%s]");
//		} else {
//			cellXpath = String.format("%s%s%s//%s", this._xpathTable, String.format("/tbody[%s]/tr[%s]/", tbodyOrder, rowOrder), "td[%s]", subTag);
//		}
//		while(true) {
//			if (i > _columnNumber) {
//				return null;
//			}
//			Link lnk = Link.xpath(String.format(cellXpath, i));
//			if (!lnk.isDisplayed(timeOutShort)){
//				System.out.println(String.format("Debug: Cell '%s' isn't displayed",lnk.getLocator() ));
//				return null;
//			}
//			String text = lnk.getText().trim();
//			System.out.println("Text link is " + text);
//			if (isDifferentText){
//				if (!text.equals(value))
//					return lnk;
//			} else {
//				if (text.equals(value))
//					return lnk;
//			}
//			if (isMoved){
//				lnk.scrollDownInDistance();
//			}
//			i += 1;
//		}
//	}
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
        String rowXpath = String.format("%s//%s//%s", this._xpathTable, this._xpathBody, this._xpathRow);
        int i = 1;
        while (true) {
            ARow row = ARow.xpath(String.format("%s[%d]", rowXpath, i));
            if (!row.isDisplayedShort(timeOutShort)) {
                if (!row.isPresent()) {
                    return lst;
                }
                i += 1;
                continue;
            }
            lst.add(row.getRow(this._columnNumber, false));
            // how many rows you want to get; you don't want to get all rows within a table
            if (limitedRow <= i) {
                return lst;
            }

            // moving to next row after getting the row in case row of this table are hidden visible view
            if (isMoved) {
//                row.scrollDownInDistance();
                row.scrollToThisControl(true);
            }
            i += 1;
        }
    }

    public boolean isNoRecordFound(){
        if (Label.xpath(String.format("%s//div[contains(@class,'no-record')]",_xpathTable)).isDisplayed())
            return true;
        return false;
    }

//	public Cell getCellByName(String name, boolean isMoved){
//		if(name.isEmpty()){
//			logEndAction("Error: Month or date parameter is empty");
//			return null;
//		}
//		String rowXpath = String.format("%s%s", this._xpathTable, "/tbody/tr[%s]");
//		int i = 1;
//		while(i < 6) {
//			ARow row = ARow.xpath(String.format(rowXpath, Integer.toString(i)));
//			if(!row.isDisplayed(timeOutShort)){
//				return null;
//			}
//			for (int j=1; j <= this._columnNumber; j ++){
//				Cell e = row.getCellByName(name, j);
//				if (e != null){
//					return e;
//				}
//			}
//
//			// moving to next row after getting the row in case row of this table are hidden visible view
//			if (isMoved){
//				row.scrollDownInDistance();
//			}
//			i += 1;
//		}
//		logEndAction(String.format("Debug: There is no value '%s' you want", name));
//		return null;
//	}
}
