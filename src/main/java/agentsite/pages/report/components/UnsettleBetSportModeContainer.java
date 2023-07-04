package agentsite.pages.report.components;

import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.NO_RECORD_FOUND;

public class UnsettleBetSportModeContainer extends BaseElement {
    String lstSportLblXpath;
    String lstSportLblDetailXpath;
    Label lblNoRecord;
    int totalColumnSummary = 3;
    private String _xPath;

    public UnsettleBetSportModeContainer(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lstSportLblXpath = String.format("%s//div[@id='accordion']", _xPath);
        lstSportLblDetailXpath = "//h4//div[contains(@class,'justify-content-between')]/div";
        lblNoRecord = Label.xpath(String.format("%s//div[@class='no-record']", _xPath));
    }

    public static UnsettleBetSportModeContainer xpath(String xpathExpression) {
        return new UnsettleBetSportModeContainer(By.xpath(xpathExpression), xpathExpression);
    }

    public boolean isNoReport() {
        return lblNoRecord.isDisplayed();
    }

    public List<String> getAllSports() {
        int n = getSport();
        List<String> lstSport = new ArrayList<>();
        if (!isNoReport()) {
            lstSport.add(NO_RECORD_FOUND);
            return lstSport;
        }
        for (int i = 0; i < n; i++) {
            lstSport.add(Label.xpath(String.format("(%s)[%s]%s", lstSportLblXpath, i + 1, lstSportLblDetailXpath)).getText());
        }
        return lstSport;
    }

    private int getSport() {
        Label lblSport = Label.xpath(lstSportLblXpath);
        return lblSport.getWebElements().size();
    }

    public void expandSport(String sport) {
        int n = getSport();
        String sportName = "";
        Label lblExpand;
        for (int i = 0; i < n; i++) {
            sportName = Label.xpath(String.format("(%s)[%s]%s", lstSportLblXpath, i + 1, lstSportLblDetailXpath)).getText();
            if (sport.equalsIgnoreCase(sportName)) {
                lblExpand = Label.xpath(String.format("(%s)[%s]%s%s", lstSportLblXpath, i + 1, lstSportLblDetailXpath, "//following-sibling::div/i[1]"));
                if (lblExpand.getAttribute("class").contains("fa-angle-right")) {
                    lblExpand.click();
                }
            }
        }
    }

    private int getIndexofSport(String sport) {
        int n = getSport();
        String sportName = "";
        for (int i = 0; i < n; i++) {
            sportName = Label.xpath(String.format("(%s)[%s]%s", lstSportLblXpath, i + 1, lstSportLblDetailXpath)).getText();
            if (sport.equalsIgnoreCase(sportName))
                return i + 1;
        }
        System.out.println("There is no sport has data");
        return 0;
    }

    public void clickEvent(String sport) {
        int index = getIndexofSport(sport);
        Table tbl = Table.xpath(String.format("(%s)[%s]//table", lstSportLblXpath, index), totalColumnSummary);
        tbl.getControlOfCell(1, 1, 1, "span//strong").click();

    }

    public List<ArrayList<String>> getdataofSport(String sport) {
        expandSport(sport);
        int index = getIndexofSport(sport);
        Table tbl = Table.xpath(String.format("(%s)[%s]//table", lstSportLblXpath, index), totalColumnSummary);
        return tbl.getRowsWithoutHeader(false);
    }


}
