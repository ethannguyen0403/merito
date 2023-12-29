package agentsite.pages.report;

import agentsite.controls.DateTimePicker;
import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.report.components.TransactionDetailsPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

import java.util.ArrayList;
import java.util.List;

public class WinLossBySportAndMarketTypePage extends HomePage {
    public static int tblReportTotalCol = 18;
    //   public Label lblPageTitle = Label.xpath("//div[@class='title']");
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'no-record')]");
    public TextBox txtSearchFrom = TextBox.id("fromDate");
    public TextBox txtSearchTo = TextBox.id("toDate");
    public TextBox txtUsername = TextBox.id("filter-username");
    public agentsite.controls.DropDownBox ddbProduct = agentsite.controls.DropDownBox.xpath("//select[@id='select-product']", "//option");
    // public DropDownBox ddbProduct = DropDownBox.id("select-product");
    //  public agentsite.controls.DropDownBox ddbProduct = agentsite.controls.DropDownBox.xpath("//select[@id='select-product']","//option/span");
//  public DropDownBox ddbSport = DropDownBox.xpath("//select[@id='select-sportId']");
    public agentsite.controls.DropDownBox ddbSport = agentsite.controls.DropDownBox.xpath("//select[@id='select-sportId']", "//option");
    public DateTimePicker dpFrom = DateTimePicker.xpath(txtSearchFrom, "//bs-days-calendar-view");
    public DateTimePicker dpTo = DateTimePicker.xpath(txtSearchTo, "//bs-days-calendar-view");
    public Button btnToday = Button.id("btn-today");
    public Button btnYesterday = Button.id("btn-yesterday");
    public Button btnLastWeek = Button.id("btn-last-week");
    public Button btnSubmit = Button.id("btn-submit");
    public Label lblInfo = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public Label lblShowTotalOnly = Label.xpath("//label[@for='isShowTotal']");
    public Label lblReportTitle = Label.xpath("//div[contains(@class,'downline-bar')]");
    public int colUsername = 3;
    public int colLoginId = 4;
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report')]", tblReportTotalCol);
    public String rwSportGroupxPath = "//td[contains(@class,'sportName')]";
    public String rMarketPerSport = "//tr[@class='%s']";
    public String rwTotalRowOfSport = "//tr[contains(@class,'total mtotal')]";
    public Row rGrandTotal = Row.xpath("//tr[contains(@class,'gtotal')]");
    private int colTurnover = 2;
    private String turnoverPerSportAndMarketXpath = "//tr[@class='%s']//span[text()='%s']//following::td[contains(@ng-bind,'localTurnover')][1]";

    public WinLossBySportAndMarketTypePage(String types) {
        super(types);
    }

    public void filter(String product, String sportName, boolean isShowTotal) {
        if (!product.isEmpty())
            ddbProduct.selectByVisibleText(product);
        if (!sportName.isEmpty())
            ddbSport.selectByVisibleText(sportName);
        if (isShowTotal)
            lblShowTotalOnly.click();
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    /**
     * Expand all Sports
     */
    private void expandAllSports() {
        int totalSport = Label.xpath(rwSportGroupxPath).getWebElements().size();
        Label lblSportName;
        for (int i = 0; i < totalSport; i++) {
            lblSportName = Label.xpath(String.format("(%s)[%s]", rwSportGroupxPath, i + 1));
            if (lblSportName.getAttribute("class").contains("expand")) {
                lblSportName.click();
            }
        }
    }

    /**
     * Expand all sports and get data
     *
     * @return
     */
    public List<ArrayList<String>> getReportData() {
        expandAllSports();
        return tblReport.getRowsWithoutHeader(false);
    }

    /**
     * This action summ all Total row of each sport to compare with Grand total row
     *
     * @param listData
     * @return The data calculated is map with grand total row or not
     */
    public ArrayList<String> sumTotalAllSports(List<ArrayList<String>> listData) {
        ArrayList<String> lstTotal = new ArrayList<>();
        int totalRow = listData.size();
        double total = 0;
        int index = 0;
        // Get the total row data of the Fist Sport
        for (int i = 0; i < totalRow; i++) {
            if (listData.get(i).get(0).equalsIgnoreCase("Total")) {
                lstTotal = listData.get(i);
                index = i;
                break;
            }
        }
        // Sum the fist total row wwith other row
        for (int i = index + 1; i < totalRow; i++) {
            if (listData.get(i).get(0).equalsIgnoreCase("Total")) {
                for (int j = 1; j < listData.get(i).size(); j++) {
                    total = Double.parseDouble(lstTotal.get(j)) + Double.parseDouble(listData.get(i).get(j));
                    lstTotal.set(j, String.format("%.2f", total));
                }
            }
        }

        lstTotal.set(0, "Grand Total");
        return lstTotal;
    }

    /**
     * Get all sport available after filter
     *
     * @return the list of sport name
     */
    public boolean dataMatchTransactionDetail(List<ArrayList<String>> listData) {
        int totalRow = listData.size();
        TransactionDetailsPopup transactionDetailsPopup = new TransactionDetailsPopup();
        String sport = "";
        for (int i = 0; i < totalRow; i++) {
            // Click on turnover of market row
            if (listData.get(i).size() != 1) {
                if (!listData.get(i).get(0).equalsIgnoreCase("Total")) {
                    System.out.println(String.format("**Step: Click on Turnover of %s and %s", sport, listData.get(i).get(0)));
                    tblReport.getControlOfCell(1, colTurnover, i + 1, null).click();
                    if (!transactionDetailsPopup.verifyDataMatchSummary(sport, listData.get(i)))
                        return false;
                }
            } else
                sport = listData.get(i).get(0);
        }
        return true;
    }

    /**
     * This action will sum all data of a market of a sport to compare with Total row of the according input Sport
     *
     * @param sport
     * @return Total row expected
     */
    public ArrayList<String> sumMarketOfSport1(String sport) {
        List<ArrayList<String>> marketsData = getSportData(sport);
        ArrayList<String> lstTotal = new ArrayList<>();
        int n = marketsData.size();
        int m = marketsData.get(0).size();
        lstTotal = marketsData.get(0);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                double data = Double.parseDouble(lstTotal.get(j));
                double data1 = Double.parseDouble(marketsData.get(i).get(j));
                lstTotal.set(j, String.format("%.2f", data + data1));
            }
        }
        lstTotal.set(0, "Grand Total");
        return lstTotal;
    }

    public int getTotalSportInReport() {
        return Row.xpath(rwSportGroupxPath).getWebElements().size();
    }

    public int getMarketNumberofSport(String sportName) {
        return Row.xpath(String.format(rMarketPerSport, sportName)).getWebElements().size();
    }

    /**
     * Get all sport available after filter
     *
     * @return the list of sport name
     */
    public List<String> getSportList() {
        List<String> lsSportName = new ArrayList<>();
        int n = getTotalSportInReport();
        for (int i = 0; i < n; i++) {
            lsSportName.add(i, Label.xpath(String.format("(%s)[%s]", rwSportGroupxPath, i + 1)).getText());
        }
        return lsSportName;
    }

    /**
     * Get total row of a Sport
     *
     * @param sportIndex
     * @return total row data
     */
    public ArrayList<String> getTotalRowOfSport(int sportIndex) {
        Icon iconExpandCollapseSport = Icon.xpath(String.format("(%s)[%s]", rwSportGroupxPath, sportIndex));
        if(iconExpandCollapseSport.getAttribute("class").contains("wl-expand")) {
            iconExpandCollapseSport.click();
        }
        return Row.xpath(String.format("(%s)[%s]",rwTotalRowOfSport, sportIndex)).getRow(tblReportTotalCol, false);
    }

    public ArrayList<String> getGrandTotalRow() {
        return rGrandTotal.getRow(tblReportTotalCol, false);
    }


    /**
     * Get all market row data of a sport
     *
     * @param sportName
     * @return Market rows data of according sport
     */
    public List<ArrayList<String>> getSportData(String sportName) {
        List<ArrayList<String>> sportData = new ArrayList<>();
        int n = getMarketNumberofSport(sportName);
        Row rMarket;
        String xPath = String.format(rMarketPerSport, sportName);
        for (int i = 0; i < n; i++) {
            rMarket = Row.xpath(String.format("%s[%s]", xPath, i + 1));
            sportData.add(i, rMarket.getRow(tblReportTotalCol, false));
        }
        return sportData;
    }

    /**
     * This action summ all Total row of each sport to compare with Grand total row
     *
     * @param sports
     * @return The data calculated is map with grand total row or not
     */
    public ArrayList<String> sumSportData(List<String> sports) {
        ArrayList<String> totalRow = new ArrayList<>();
        ArrayList<String> expectedData = getTotalRowOfSport(1);
        if(sports.size() > 1) {
            for (int i = 1; i < sports.size(); i++) {
                totalRow = getTotalRowOfSport(i+1);
                for (int j = 1; j < expectedData.size(); j++) {
                    if (!expectedData.get(j).isEmpty()) {
                        double data = Double.parseDouble(expectedData.get(j));
                        double data1 = Double.parseDouble(totalRow.get(j));
                        expectedData.set(j, String.format("%.2f", data + data1));
                    }
                }
            }
        }
        expectedData.set(0, "Grand Total");
        return expectedData;
    }

    /**
     * This action will sum all data of a market of a sport to compare with Total row of the according input Sport
     *
     * @param sport
     * @return Total row expected
     */
    public ArrayList<String> sumMarketOfSport(String sport) {
        List<ArrayList<String>> marketsData = getSportData(sport);
        ArrayList<String> lstTotal = new ArrayList<>();
        int n = marketsData.size();
        int m = marketsData.get(0).size();
        lstTotal = marketsData.get(0);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                double data = Double.parseDouble(lstTotal.get(j));
                double data1 = Double.parseDouble(marketsData.get(i).get(j));
                lstTotal.set(j, String.format("%.2f", data + data1));
            }
        }
        lstTotal.set(0, "Total");
        return lstTotal;
    }

    public TransactionDetailsPopup clickTurnoverLink(String sportName, String marketName) {
        Label lblTurnOver = Label.xpath(String.format(turnoverPerSportAndMarketXpath, sportName, marketName));
        lblTurnOver.click();
        waitingLoadingSpinner();
        return new TransactionDetailsPopup();
    }

   /* public String getFristSelectedOption(DropDownBox dropDownBox){
        List<WebElement> options = DriverManager.getDriver().findElement(dropDownBox.getLocator()).findElements(By.tagName("option"));
        if()
        for (WebElement option : options) {
            return option.findElement(By.name("span")).getText();
        }
        return null;
    }*/

}
