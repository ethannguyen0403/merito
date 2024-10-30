package agentsite.pages.agentmanagement.eventbetsizesetting;


import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.*;
import com.paltech.utils.DateUtils;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS;
import static common.AGConstant.timeZone;

public class OldUIEventBetSizeSetting extends EventBetSizeSetting {
    public DropDownBox ddbSport = DropDownBox.xpath("//label[@translate='sport']//following::select[1]");
    public String tabDynamic = "//a[contains(text(),'%s')]";

    public int colMinMax = 4;
    public int colFancyMinMax = 5;
    public int colBookmakerMinMax = 6;
    public TextBox txtEventName = TextBox.xpath("//input[@placeholder='Search By Event Name']");
    public TextBox txtCompetitionName = TextBox.xpath("//input[@placeholder='Search By Competition Name']");
    public TextBox txtEventID = TextBox.xpath("//input[@placeholder='Search By Event ID']");

    public OldUIEventBetSizeSetting(String types) {
        super(types);
    }

    public void filter(String controlLevel, String sportName, String tab) {
        if (!controlLevel.isEmpty())
            ddbSADLevel.selectByVisibleText(controlLevel);
        if (!sportName.isEmpty())
            ddbSport.selectByVisibleText(sportName);
        if (!tab.isEmpty())
            clickTab(tab);
        waitingLoadingSpinner();
    }

    public void updateMinMax(String eventID, String product, String min, String max) {
        inputMinMax(eventID, product, min, max);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void inputMinMax(String eventID, String product, String min, String max) {
        keyInMinMaxTextbox(getMinTextBoxofEvent(eventID, product), min);
        keyInMinMaxTextbox(getMaxTextBoxofEvent(eventID, product), max);

    }

    private void keyInMinMaxTextbox(TextBox txt, String value) {
        if (!Objects.isNull(value)) {
            if (!value.isEmpty())
                txt.sendKeys(value);
                // remove Min if not input min
            else
                txt.sendKeys(Keys.BACK_SPACE);
        }
    }

    private int getColumnMinMaxbyProduct(String product) {
        switch (product) {
            case "Fancy":
                return colFancyMinMax;
            case "Bookmaker":
                return colBookmakerMinMax;
            default:
                return colMinMax;
        }
    }

    public TextBox getMinTextBoxofEvent(String eventID, String product) {
        int columnMinMax = getColumnMinMaxbyProduct(product);
        return TextBox.xpath(tblEvent.getControlxPathBasedValueOfDifferentColumnOnRow(eventID, 1, colEventID, 1, null, columnMinMax, "input[1]", false, false));
    }

    private Icon getErrorIconOfMinMaxColumn(String eventID, String product) {
        int columnMinMax = getColumnMinMaxbyProduct(product);
        return Icon.xpath(tblEvent.getControlxPathBasedValueOfDifferentColumnOnRow(eventID, 1, colEventID, 1, null, columnMinMax, "span[contains(@class,'icon-error')][1]", false, false));
    }

    public ConfirmPopup openErrorPopup(String eventID, String product) {
        Icon icError = getErrorIconOfMinMaxColumn(eventID, product);
        icError.click();
        return new ConfirmPopup();
    }

    public TextBox getMaxTextBoxofEvent(String eventID, String product) {
        int columnMinMax = getColumnMinMaxbyProduct(product);
        return TextBox.xpath(tblEvent.getControlxPathBasedValueOfDifferentColumnOnRow(eventID, 1, colEventID, 1, null, columnMinMax, "input[2]", false, false));
    }

    public String getMinMax(String eventID, String product) {
        return String.format("%s-%s", getMinTextBoxofEvent(eventID, product).getAttribute("value"), getMaxTextBoxofEvent(eventID, product).getAttribute("value"));
    }

    /**
     * Click on tab
     *
     * @param action Tab name
     */
    public void clickTab(String action) {
        Tab tab;
        switch (action.toLowerCase()) {
            case "old events":
                tab = Tab.xpath(String.format(tabDynamic, TAB_DAYS.get(0)));
                tab.click();
                return;
            case "today":
                tab = Tab.xpath(String.format(tabDynamic, TAB_DAYS.get(1)));
                tab.click();
                return;
            case "tomorrow":
                tab = Tab.xpath(String.format(tabDynamic, TAB_DAYS.get(2)));
                tab.click();
                return;
            case "future":
                tab = Tab.xpath(String.format(tabDynamic, TAB_DAYS.get(3)));
                tab.click();
                return;
        }
        tblEvent.waitForAttributeChange("innerhtml", "", timeOutShortInSeconds);

        return;
    }

    public void searchEventInfo(String competitionName, String eventName, String eventID) {
        if (!competitionName.isEmpty())
            txtCompetitionName.sendKeys(competitionName);
        if (!eventName.isEmpty())
            txtEventName.sendKeys(eventName);
        if (!eventID.isEmpty())
            txtEventID.sendKeys(eventID);
        tblEvent.waitForAttributeChange("innerHTML", eventName, timeOutShortInSeconds);
        waitingLoadingSpinner();

    }

    public List<ArrayList<String>> getTableInfo() {
        int resultNumber = tblEvent.getNumberOfRows(false, true);
        return tblEvent.getRowsWithoutHeader(resultNumber, true);
    }

    public boolean searchResultContainsKey(List<String> lstResult, String searchKey) {
        for (String result : lstResult) {
            if (!result.contains(searchKey)) {
                System.out.println("FAILED! result index value " + result + " not contains search key " + searchKey);
                return false;
            }
        }
        return true;
    }

    public void verifyResultFilteredByPeriod(String periodTab) throws ParseException {
        List<ArrayList<String>> lstInfo = getTableInfo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(periodTab.equalsIgnoreCase(TAB_DAYS.get(0))) {
            String dateCompare = DateUtils.getDate(0, "yyyy-MM-dd HH:mm:ss", timeZone);
            Date dateCompareParsed = sdf.parse(dateCompare);
            for (int i = 0; i < lstInfo.size(); i++) {
                String dateResult = lstInfo.get(i).get(1).split("\n")[1];
                Date dateResultParsed = sdf.parse(dateResult);
                Assert.assertTrue(dateResultParsed.before(dateCompareParsed), String.format("FAILED! Day from result %s is after today %s", dateResult, dateCompare));
            }
        } else if (periodTab.equalsIgnoreCase(TAB_DAYS.get(1))) {
            String dateCompare = DateUtils.getDate(0, "yyyy-MM-dd HH:mm:ss", timeZone);
            Date dateCompareParsed = sdf.parse(dateCompare);
            for (int i = 0; i < lstInfo.size(); i++) {
                String event = lstInfo.get(i).get(1).split("\n")[0];
                if(!event.contains("In-Play")) {
                    String dateResult = lstInfo.get(i).get(1).split("\n")[1];
                    Date dateResultParsed = sdf.parse(dateResult);
                    if((dateResultParsed.getYear() != dateCompareParsed.getYear()) || (dateResultParsed.getMonth() != dateCompareParsed.getMonth()) || (dateResultParsed.getDate() != dateCompareParsed.getDate())) {
                        Assert.assertTrue(false, String.format("FAILED! Day from result %s is not equal today %s", dateResult, dateCompare));
                    }
                }
            }
        } else if (periodTab.equalsIgnoreCase(TAB_DAYS.get(2))) {
            String dateCompare = DateUtils.getDate(0, "yyyy-MM-dd HH:mm:ss", timeZone);
            Date dateCompareParsed = sdf.parse(dateCompare);
            for (int i = 0; i < lstInfo.size(); i++) {
                String dateResult = lstInfo.get(i).get(1).split("\n")[1];
                Date dateResultParsed = sdf.parse(dateResult);
                Assert.assertTrue(dateResultParsed.after(dateCompareParsed), String.format("FAILED! Day from result %s is not after today %s", dateResult, dateCompare));
            }
        } else if (periodTab.equalsIgnoreCase(TAB_DAYS.get(3))) {
            String dateCompare = DateUtils.getDate(1, "yyyy-MM-dd HH:mm:ss",timeZone);
            Date dateCompareParsed = sdf.parse(dateCompare);
            for (int i = 0; i < lstInfo.size(); i++) {
                String dateResult = lstInfo.get(i).get(1).split("\n")[1];
                Date dateResultParsed = sdf.parse(dateResult);
                Assert.assertTrue(dateResultParsed.after(dateCompareParsed), String.format("FAILED! Day from result %s is not after tomorrow %s", dateResult, dateCompare));
            }
        }
    }

    @Override
    public void verifyEventStartDisplay(String eventStartEx) {
        int rowNumber = tblEvent.getNumberOfRows(false,true);
        for (int i = 1; i <= rowNumber; i++){
            String openDateAc = Label.xpath(tblEvent.getxPathOfCell(1,colEventName,i,"span[@class='open-date']")).getText();
            if (openDateAc.compareTo(eventStartEx) > 0){
                String competition = Label.xpath(tblEvent.getxPathOfCell(1,colCompetitionName,i,"span[2]")).getText();
                Assert.assertTrue(false,String.format("FAILED! Event start display incorrect at Competetition: %s",competition));
            }
        }
    }
}
