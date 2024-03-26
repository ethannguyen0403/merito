package backoffice.pages.bo.operations;

import backoffice.controls.DateTimePicker;
import backoffice.controls.bo.ATable;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.operations.component.VoidUnvoidPopup;
import backoffice.pages.bo.operations.component.VoidUnvoidRemarkPopup;
import com.paltech.element.common.*;
import membersite.objects.AccountBalance;

import java.util.ArrayList;
import java.util.List;

public class WagerVoidUnvoidPage extends HomePage {
    public CheckBox cbWager = CheckBox.xpath("//input[@value='WAGER_PAGE']");
    public CheckBox cbMarket = CheckBox.xpath("//input[@value='MARKET_PAGE']");
    public DropDownBox ddbProduct = DropDownBox.name("products");
    public CheckBox cbWagerID = CheckBox.xpath("//input[@value='BY_WAGERID']");
    public CheckBox cbUsername = CheckBox.xpath("//input[@value='BY_USERNAME']");
    public CheckBox cbEvent = CheckBox.xpath("//input[@value='BY_EVENTLIST']");
    public TextBox txtUsername = TextBox.xpath("//input[contains(@class,'input-username')]");
    public TextBox txtFrom = TextBox.name("startDate");
    public TextBox txtTo = TextBox.name("endDate");
    public Button btnSearch = Button.xpath("//app-wager-void-unvoid//button[@name='search']");
    public Button btnSearchByMarket = Button.xpath("//app-market-void-unvoid//button[contains(@class,'btn btn-sm btn-core')]");
    public TextBox txtWagerID = TextBox.name("wager-id");
    public TextBox txtEventDate = TextBox.name("eventDate");
    public DateTimePicker dtpEvent = DateTimePicker.xpath(txtEventDate, "//bs-days-calendar-view");
    public DropDownBox ddbSport = DropDownBox.xpath("//label[contains(text(),'Sport')]/following-sibling::select");
    public DropDownBox ddbCompetition = DropDownBox.xpath("//label[contains(text(),'Competition')]/following-sibling::select");
    public DropDownBox ddbEvent = DropDownBox.xpath("//label[contains(text(),'Event')]/following-sibling::select");
    public int colWagerID = 2;
    public int colUsername = 3;
    public int colNickname = 4;
    public int colPalce = 5;
    public int colDescription = 6;
    public int colStatus = 11;
    public int colBFWagerID = 12;
    public int colBFStatus = 13;
    public int colAction = 14;
    public int colRemark = 15;
    public Label lblNoRecord = Label.xpath("//div[@class='custom-table norecord']//div[contains(@class,'no-record')]");
    public TextBox txtWagerIDByMarket = TextBox.xpath("//label[contains(text(),'Market ID')]/parent::div/following-sibling::div//input");
    public int colMarketID = 2;
    public int colActionMarket = 6;
    public int colRemarkMarket = 7;
    public int colMarketType = 3;
    public int colDescriptionMarket = 4;
    public int colStatusMarket = 5;
    private int totalColumn = 15;
    public ATable tblWager = ATable.xpath("//div[@class='custom-table']", totalColumn);
    private int totalColumnMarket = 7;
    public ATable tblMarket = ATable.xpath("//div[@class='custom-table']", totalColumnMarket);

    public void voidBy(VOIDBY voidBy) {
        switch (voidBy) {
            case WAGER:
                cbWager.click();
                return;
            case MARKET:
                cbMarket.click();
                return;
        }
    }

    public void searchBy(SEARCHBY searchType) {
        switch (searchType) {
            case WAGERID:
                cbWagerID.click();
                return;
            case USERNAME:
                cbUsername.click();
                return;
            case EVENTLIST:
                cbEvent.click();
                return;
        }
    }

    public void searchByWager(String product, String wagerID) {
        voidBy(VOIDBY.WAGER);
        searchBy(SEARCHBY.WAGERID);
        ddbProduct.selectByVisibleText(product);
        txtWagerID.sendKeys(wagerID);
        btnSearch.click();
    }

    public void searchByUsername(String product, String username, String fromDate, String toDate) {
        voidBy(VOIDBY.WAGER);
        searchBy(SEARCHBY.USERNAME);
        ddbProduct.selectByVisibleText(product);
        txtUsername.sendKeys(username);
        if (!fromDate.isEmpty())
            txtFrom.sendKeys(fromDate);
        if (!toDate.isEmpty())
            txtTo.sendKeys(toDate);
        btnSearch.click();
    }

    public void searchByEventList(String product, String eventDate, String sport, String competition, String event) {
        voidBy(VOIDBY.WAGER);
        searchBy(SEARCHBY.EVENTLIST);
        ddbProduct.selectByVisibleText(product);
        if (!eventDate.isEmpty())
            txtEventDate.sendKeys(eventDate);
        //dtpEvent.currentMonthWithDate(eventDate);
        if (!sport.isEmpty())
            ddbSport.selectByVisibleText(sport);
        if (!competition.isEmpty()) {
            ddbCompetition.selectByVisibleText(competition);
        }
        if (!event.isEmpty()) {
            ddbEvent.selectByVisibleText(event);
        }
        btnSearch.click();
    }

    public void searchByEventList(String product, String eventDate, int sport, int competition, int event) {
        voidBy(VOIDBY.WAGER);
        searchBy(SEARCHBY.EVENTLIST);
        ddbProduct.selectByVisibleText(product);
        if (!eventDate.isEmpty())
            txtEventDate.sendKeys(eventDate);
        //dtpEvent.currentMonthWithDate(eventDate);
        if (sport > 0) {
            if (ddbSport.getOptions().size() > 1) {
                ddbSport.selectByIndex(sport);
            } else {
                System.out.println("There is no sport");
                return;
            }

        }
        if (competition > 0) {
            ddbCompetition.selectByIndex(competition);
        }
        if (event > 0) {
            ddbEvent.selectByIndex(event);
        }
        btnSearch.click();
    }

    public void searchByMarket(String marketID) {
        voidBy(VOIDBY.MARKET);
        txtWagerIDByMarket.sendKeys(marketID);
        btnSearchByMarket.click();
    }

    public Object clickAction(ACTION ac, int row) {
        Link lnk;
        switch (ac) {
            case VOID:
                lnk = (Link) tblWager.getControlOfCell(1, colAction, row, "a[@data-action='VOID']");
                lnk.click();
                return new VoidUnvoidPopup();
            case UNVOID:
                lnk = (Link) tblWager.getControlOfCell(1, colAction, row, "a[@data-action='UNVOID']");
                lnk.click();
                return new VoidUnvoidPopup();
            default:
                lnk = (Link) tblWager.getControlOfCell(1, colRemark, row, "a");
                lnk.click();
                return new VoidUnvoidRemarkPopup();
        }
    }

    public void voidUnvoidWager(String wagerID, ACTION action, String remark) {
        List<String> wagerLst = tblWager.getColumn(colWagerID, false);
        if (lblNoRecord.isDisplayed()) {
            System.out.println(String.format("Wager ID does not exist int the result", wagerID));
            return;
        }
        for (int i = 0; i < wagerLst.size(); i++) {
            if (wagerLst.get(i).equals(wagerID)) {
                VoidUnvoidPopup popup = (VoidUnvoidPopup) clickAction(action, i + 1);
                popup.confirm(remark);
                return;
            }
        }
    }

    public AccountBalance calculateBalanceVoidUnvoidWager(AccountBalance balance, String wagerProfit, boolean isVoided) {
        AccountBalance returnBal = balance;
        double bal = Double.parseDouble(balance.getBalance());
        double exposure = Double.parseDouble(balance.getExposure());
        double profit = Double.parseDouble(wagerProfit);
        double balanceAfterVoidUnvoid;
        if (isVoided)
            balanceAfterVoidUnvoid = bal - profit;
        else
            balanceAfterVoidUnvoid = bal + profit;
        //  returnBal.setBalance(Double.toString(balanceAfterVoidUnvoid));
        return returnBal;
    }

    public List<ArrayList<String>> getFristWagerInfo(){
        return tblWager.getRowsWithoutHeader(1,false);
    }
    public enum ACTION {VOID, UNVOID, REMARK}

    public enum VOIDBY {WAGER, MARKET}

    public enum SEARCHBY {WAGERID, USERNAME, EVENTLIST}
}
