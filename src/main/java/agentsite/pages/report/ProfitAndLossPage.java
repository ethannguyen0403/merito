package agentsite.pages.report;


import agentsite.controls.DateTimePicker;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.report.components.TransactionDetailsPopupPage;
import agentsite.pages.report.profitandloss.ProfitAndLoss;
import com.paltech.element.common.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfitAndLossPage extends HomePage {
    public static List<String> downlineLevelList = new ArrayList<>();
    public Label lblTimeZone = Label.xpath("//div[@id='search-region']//table//tr[1]/td[1]");
    public Label lblFrom = Label.xpath("//div[@id='search-region']//table//tr[1]/td[3]");
    public Label lblTo = Label.xpath("//div[@id='search-region']//table//tr[1]/td[5]");
    public Label lblProduct = Label.xpath("//div[@id='search-region']//table//tr[1]/td[7]");
    public DropDownBox ddbTimeZone = DropDownBox.id("timezone");
    public TextBox txtSearchTo = TextBox.xpath("//div[@id='search-region']//table//tr[1]/td[6]//input");
    public TextBox txtSearchFrom = TextBox.xpath("//div[@id='search-region']//table//tr[1]/td[4]//input");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-calendar-layout");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-calendar-layout");
    public agentsite.controls.DropDownBox ddbProduct = agentsite.controls.DropDownBox.xpath("//angular2-multiselect//div[contains(@class,'selected-list')]", "//div[contains(@class,'dropdown-list')]//div[@class='list-area']//ul[@class='lazyContainer']//label");
    public Button btnToday = Button.name("today");
    public Button btnYesterday = Button.name("yesterday");
    public Button btnLastWeek = Button.name("lastWeek");
    public Button btnSubmit = Button.name("search");
    public Label lblDownLineBar = Label.xpath("//div[@class='downline-bar']");
    public Label lblProductErrorMassage = Label.xpath("//div[@id='search-region']//div[@class='error']");
    public Label lblYouCanSeeReportData = Label.xpath("(//span[@class='pinfo']/following::label)[1]");
    public Label lblForISTTimeZoneReportAvailable = Label.xpath("(//span[@class='pinfo']/following::label)[2]");
    public Label lblUplineProfitAndLoss = Label.xpath("//div[@class='paymenTitle'][1]");
    public Label lblDownlineProfitAndLoss = Label.xpath("//div[@class='paymenTitle'][2]");
    public Table tblUplineProfitAndLoss = Table.xpath("//table[contains(@class ,'ptable report backlayTable')][1]", 7);
    public int colUserName = 2;
    public int colLevel = 5;
    public int colUsername = 2;
    public int colLoginID = 3;
    public int colFirstName = 4;
    public int colBackTurnover = 6;
    public int colBackPnl = 7;
    public int colLayTurnover = 8;
    public int colLayPnl = 9;
    public int colTotalTax = 10;
    public int colBalance = 12;
    public Table tblDownLineProfitAndLoss = Table.xpath("//table[contains(@class , 'ptable report backlayTable')][2]", 12);
    public Label lblNoRecordDowLinePL = Label.xpath("//table[contains(@class , 'ptable report backlayTable')][2]//td[@class='no-record']");
    public ProfitAndLoss profitAndLoss;
    public TransactionDetailsPopupPage transactionDetailsPopupPage;
    public ProfitAndLossPage(String types) {
        super(types);
        profitAndLoss = ComponentsFactory.profitAndLoss(types);
        transactionDetailsPopupPage = new TransactionDetailsPopupPage(types);
    }

    /**
     * Filter function
     *
     * @param timeZone
     * @param from:       index: 0 today, -1 yesterday
     * @param to
     * @param productName
     */
    public void filter(String timeZone, String from, String to, String productName) {
        if (!timeZone.isEmpty())
            ddbTimeZone.selectByVisibleText(timeZone);
        if (!from.isEmpty())
            dpFrom.selectDate(from,"dd/MM/yyyy");
        if (!to.isEmpty())
            dpTo.selectDate(to,"dd/MM/yyyy");
        if (!productName.isEmpty())
            filterbyProduct(productName);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void filterbyProduct(String productName) {
        if (!productName.isEmpty()) {
            if (productName.equalsIgnoreCase("UnSelect All")) {
                ddbProduct.uncheckAll(true);
            }
            if (productName.equalsIgnoreCase("Select ALl"))
                ddbProduct.checkAll(true);
            ddbProduct.selectByVisibleText(productName, true, false);
        }
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public List<ArrayList<String>> drilldownToLevel(String level, boolean isOpenTransactionReport) {
        List<ArrayList<String>> data = new ArrayList<>();
        if (lblNoRecordDowLinePL.isDisplayed())
            return data;
        Link lnkUserName = (Link) tblDownLineProfitAndLoss.getControlOfCell(1, colUserName, 2, "a");
        List<String> lstLevel = tblDownLineProfitAndLoss.getColumn(colLevel, 2, false);
        String levelDownline = lstLevel.get(1);
        downlineLevelList.add(lstLevel.get(0));
        boolean flag = false;
        while (!flag) {
            if (levelDownline.equalsIgnoreCase(level)) {
                flag = true;
                data = tblDownLineProfitAndLoss.getRowsWithoutHeader(3, false);
                downlineLevelList.add(lstLevel.get(1));
                if (isOpenTransactionReport)
                    lnkUserName.click();
            } else {
                lnkUserName.click();
                waitingLoadingSpinner();
                lstLevel = tblDownLineProfitAndLoss.getColumn(colLevel, 2, false);
                levelDownline = lstLevel.get(1);
                downlineLevelList.add(lstLevel.get(0));
            }

        }
        return data;
    }

    /**
     * Drilldown to the expected level and return the username of according level
     *
     * @param level
     * @return username of level drilldown
     */
    public String drilldown(String level) {
        Link lnkUserName = (Link) tblDownLineProfitAndLoss.getControlOfCell(1, colUserName, 2, "a");
        List<String> lstLevel = tblDownLineProfitAndLoss.getColumn(colLevel, 2, false);
        String levelDownline = lstLevel.get(1);
        while (true) {
            if (!levelDownline.equalsIgnoreCase(level)) {
                lnkUserName.click();
                waitingLoadingSpinner();
                lnkUserName = (Link) tblDownLineProfitAndLoss.getControlOfCell(1, colUserName, 2, "a");
                levelDownline = tblDownLineProfitAndLoss.getColumn(colLevel, 2, false).get(1);

            } else
                return lnkUserName.getText();
        }
    }

    public TransactionDetailsPopupPage openTransactionDetails(String userName) {
        Link lnkUserName = (Link) tblDownLineProfitAndLoss.getControlOfCell(1, colUserName, 2, "a");
        if (lnkUserName.getText().equals(userName))
            lnkUserName.click();
        waitingLoadingSpinner();
        return new TransactionDetailsPopupPage(_type);
    }

    public List<String> getProductDataDropdown() {
        return ddbProduct.getAllOption(true).stream().sorted().collect(Collectors.toList());
    }

    public void verifyUIDisplayCorrect(boolean isLevelLoginPO) {
        profitAndLoss.verifyUIDisplayCorrect(isLevelLoginPO);
    }

    /**
     * Verify member balance (include tax) between summary and details of member result
     */
    public void verifyMemberResultSummaryAndDetails() {
        List<ArrayList<String>> list = drilldownToLevel("Member", true);
        int colBalSummary = tblDownLineProfitAndLoss.getColumnIndexByName("Balance");
        double balanceIncludeTax = Double.valueOf(list.get(1).get(colBalSummary)) + Double.valueOf(list.get(1).get(colBalSummary + 1));
        ArrayList<String> totalRowData = transactionDetailsPopupPage.getTotalRowData();
        int colBalDetails = transactionDetailsPopupPage.tblReport.getColumnIndexByName("Member Result");
        Assert.assertEquals(Double.valueOf(totalRowData.get(colBalDetails-9)), balanceIncludeTax, "FAILED! Balance of member in Downine Profit and Loss not match with Total- Member result in Transaction Details");
    }
}
