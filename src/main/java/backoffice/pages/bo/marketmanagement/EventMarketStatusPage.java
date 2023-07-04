package backoffice.pages.bo.marketmanagement;

import backoffice.controls.DateTimePicker;
import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

import java.util.ArrayList;
import java.util.List;

public class EventMarketStatusPage extends HomePage {

    public TextBox txtEventDate = TextBox.xpath("//input[contains(@class,'txt-event-date')]");
    public DateTimePicker dtpEventDate = DateTimePicker.xpath(txtEventDate, "//div[contains(@class,'datepicker-open')]");
    public CheckBox cbOpenOnly = CheckBox.name("open");
    public TextBox txtSearchSport = TextBox.name("search-sport");
    public TextBox txtSearchCompetition = TextBox.name("search-competition");
    public TextBox txtSearchEventID = TextBox.name("search-event-id");
    public TextBox txtSearchEventName = TextBox.name("search-event-name");
    public TextBox txtSearchEventStatus = TextBox.name("search-event-status");
    public TextBox txtSearchMarketID = TextBox.name("search-market-id");
    public TextBox txtSearchMarketName = TextBox.name("search-market-name");
    public TextBox txtSearchMarketType = TextBox.name("search-market-type");
    public TextBox txtSearchMarketStatus = TextBox.xpath("search-market-status");
    public Table tblSport = Table.xpath("//table[contains(@class,'sport')]", 1);
    public Table tblCompetition = Table.xpath("//table[contains(@class,'competition')]", 1);
    public int colEventId = 1;
    public int colEventName = 2;
    public int colEventStatus = 3;
    public Table tblEvent = Table.xpath("//table[contains(@class,'event-info')]", 3);
    public int colMarketID = 1;
    public int colMarketName = 2;
    public Table tblMarket = Table.xpath("//table[contains(@class,'market-info')]", 6);
    public Label lblNoMarket = Label.xpath("//div[@class='result-container']/div[4]//table//tr[1]/td[@class='text-center']");

    public void searchSport(String sportName) {
        txtSearchSport.sendKeys(sportName);
    }

    public void searchCompetition(String competition) {
        txtSearchCompetition.sendKeys(competition);
    }

    public void searchEvent(String eventID, String eventName, String eventStatus) {
        if (!eventID.isEmpty())
            txtSearchEventID.sendKeys(eventID);
        if (!eventName.isEmpty())
            txtSearchEventName.sendKeys(eventName);
        if (!eventStatus.isEmpty())
            txtSearchEventStatus.sendKeys(eventStatus);
    }

    public void searchMarket(String marketID, String marketName, String marketType, String marketStatus) {
        if (!marketID.isEmpty())
            txtSearchMarketID.sendKeys(marketID);
        if (!marketName.isEmpty())
            txtSearchMarketName.sendKeys(marketName);
        if (!marketType.isEmpty())
            txtSearchMarketType.sendKeys(marketType);
        if (!marketStatus.isEmpty()) {
            txtSearchMarketStatus.sendKeys(marketStatus);
            lblNoMarket.waitForControlInvisible(1, 1);
        }

    }

    public boolean isSportDisplay(String sportName, boolean isSelect) {
        List<String> lstSport = tblSport.getColumn(1, true);
        for (int i = 0; i < lstSport.size(); i++) {
            if (lstSport.get(i).contains(sportName)) {
                if (isSelect)
                    tblSport.getControlOfCell(1, 1, i + 1, null).click();
                return true;
            }
        }
        System.out.println(String.format("The Sport %s not exist in the list", sportName));
        return false;
    }

    public boolean isCompetitionDisplay(String competition, boolean isSelect) {
        List<String> lstCompetition = tblCompetition.getColumn(1, true);
        for (int i = 0; i < lstCompetition.size(); i++) {
            if (lstCompetition.get(i).contains(competition)) {
                if (isSelect) {
                    tblCompetition.getControlOfCell(1, 1, i + 1, null).click();
                    waitSpinIcon();
                }
                return true;
            }
        }
        System.out.println(String.format("The Sport %s not exist in the list", competition));
        return false;
    }

    public boolean isCompetitionDisplay(List<ArrayList<String>> competition) {
        List<String> lstCompetition = tblCompetition.getColumn(1, true);
        for (int i = 0; i < lstCompetition.size(); i++) {
            if (!lstCompetition.get(i).contains(competition.get(i).get(1))) {
                System.out.println(String.format("The Sport %s not exist in the list", competition.get(i).get(1)));
                return false;
            }
        }
        return true;
    }

    public boolean isEventDisplay(String eventID, String eventName, boolean isSelect) {
        List<String> lstEvent = new ArrayList<>();
        if (!eventID.isEmpty()) {
            lstEvent = tblEvent.getColumn(colEventId, false);
            for (int i = 0; i < lstEvent.size(); i++) {
                if (lstEvent.get(i).contains(eventID)) {
                    if (isSelect)
                        tblEvent.getControlOfCell(1, colEventId, i + 1, null).click();
                    return true;
                }
            }
        }
        if (!eventName.isEmpty()) {
            lstEvent = tblEvent.getColumn(colEventName, false);
            for (int i = 0; i < lstEvent.size(); i++) {
                if (lstEvent.get(i).contains(eventName)) {
                    if (isSelect)
                        tblEvent.getControlOfCell(1, colEventName, i + 1, null).click();
                    return true;
                }
            }
        }
        System.out.println(String.format("The Event ID or Event Name %s %s not exist in the list", eventID, eventName));
        return false;
    }

    public boolean isEventDisplay(List<ArrayList<String>> lstEventName) {
        List<String> lstEvent = tblEvent.getColumn(colEventName, false);
        for (int i = 0; i < lstEvent.size(); i++) {
            if (!lstEvent.get(i).contains(lstEventName.get(i).get(1))) {
                System.out.println(String.format("The Event Name %s not exist in the list", lstEventName.get(i).get(1)));
                return false;
            }
        }
        return true;
    }

    public boolean verifyEventsStatus(String status) {
        List<String> lstEventStatus = tblEvent.getColumn(colEventStatus, false);
        for (int i = 0; i < lstEventStatus.size(); i++) {
            if (!lstEventStatus.get(i).equalsIgnoreCase(status)) {
                System.out.println(String.format("The Event Name %s not exist in the list", status));
                return false;
            }
        }
        return true;
    }

    public boolean isMarketDisplay(List<ArrayList<String>> lstMarket) {
        List<String> lstMarkets = tblMarket.getColumn(colMarketName, false);
        for (int i = 0; i < lstMarkets.size(); i++) {
            if (!lstMarkets.get(i).contains(lstMarket.get(i).get(1))) {
                System.out.println(String.format("The Event Name %s not exist in the list", lstMarket.get(i).get(1)));
                return false;
            }
        }
        return true;
    }

    public boolean isMarketDisplay(String marketID, String marketName, boolean isSelect) {
        List<String> lstMarket = new ArrayList<>();
        if (!marketID.isEmpty()) {
            lstMarket = tblMarket.getColumn(colMarketID, false);
            for (int i = 0; i < lstMarket.size(); i++) {
                if (lstMarket.get(i).contains(marketID)) {
                    if (isSelect)
                        tblMarket.getControlOfCell(1, colMarketID, i + 1, null).click();
                    return true;
                }
            }
        }
        if (!marketName.isEmpty()) {
            lstMarket = tblMarket.getColumn(colMarketName, false);
            for (int i = 0; i < lstMarket.size(); i++) {
                if (lstMarket.get(i).contains(marketName)) {
                    if (isSelect)
                        tblMarket.getControlOfCell(1, colMarketName, i + 1, null).click();
                    return true;
                }
            }
        }
        System.out.println(String.format("The Event ID or Event Name %s %s not exist in the list", marketID, marketName));
        return false;
    }
}
