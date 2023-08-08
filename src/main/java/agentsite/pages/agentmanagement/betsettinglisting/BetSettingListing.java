package agentsite.pages.agentmanagement.betsettinglisting;

import agentsite.controls.Row;
import agentsite.controls.Table;
import com.paltech.element.common.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BetSettingListing {
    int totalColum = 10;
    protected int soccerCol = 10;
    protected int cricketCol = 11;
    protected int tennisCol = 12;
    protected int basketballCol = 13;
    protected int fancyCol = 14;
    protected int otherCol = 15;
    public int updateStatusCol = 16;
    public int chbCol = 6;
    public int usernameCol = 2;
    private String successIcon = "//span[contains(@class,'psuccess')]";
    private String errorIcon = "//span[contains(@class,'perror')]";
    public Table tblDownline = Table.xpath("//table[contains(@class,'ptable report')]", totalColum);
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public TextBox txtMinBet = TextBox.xpath("(//table[@class='ptable info search-region']//input)[1]");
    public TextBox txtMaxBet = TextBox.xpath("(//table[@class='ptable info search-region']//input)[2]");
    public TextBox txtMaxLiabilityPerMarket = TextBox.xpath("(//table[@class='ptable info search-region']//input)[3]");
    public TextBox txtMaxWinPerMarket = TextBox.xpath("(//table[@class='ptable info search-region']//input)[4]");
    public Button btnUpdate = Button.xpath("//button[contains(@class,'directDownline')]");
    private String xPathSport = "//label[@class='sportName' and contains(text(),'%s')]/preceding::input[1]";

    public TextBox txtUsername = TextBox.id("userName");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//label[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//label[text()='Product']//following::select[1]");
    public DropDownBox ddbLevel = DropDownBox.id("userLevel");
    public Button btnSubmit = Button.xpath("//button[@class='pbtn search']");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public List<ArrayList<String>> defineActualDataForOneAccount(HashMap<String, Boolean> sportList, double minBet, double maxBet, double maxLiabilityPerMarket, double maxWinPerMarket) {
        return null;
    };
    public List<ArrayList<String>> getBetSettingofAccount(HashMap<String, Boolean> sportList) {
        //init table column
        int sportColumn = getSpotColumn(sportList);
        totalColum = sportColumn + totalColum;
        tblDownline = Table.xpath("//table[contains(@class,'ptable report')]", totalColum);
        List<ArrayList<String>> listInfo = new ArrayList<ArrayList<String>>();

        String rowXpath = String.format("%s%s", this.tblDownline.getLocator().toString().replace("By.xpath: ", ""), "//tbody/tr[%s]");

        // Get total row of the table
        int totalRows = tblDownline.getNumberOfRows(false, false);
        for (int i = 0; i < totalRows; i++) {

            Row row = Row.xpath(String.format(rowXpath, (i + 1)));
            //1,5,9,12
            if (i % 4 == 0) {
                listInfo.add(row.getRow(totalColum, false));
            } else {
                listInfo.add(row.getRow(sportColumn + 1, false));
            }
        }
        return listInfo;
    }

    public int getSpotColumn(HashMap<String, Boolean> map) {
        int i = 1;
        if (map.get("Soccer")) {
            soccerCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Cricket")) {
            cricketCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Tennis")) {
            tennisCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Basketball")) {
            basketballCol = (totalColum - 1) + i;
            i = i + 1;
        }
        if (map.get("Fancy")) {
            fancyCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Other")) {
            otherCol = (totalColum - 1) + i;
            i = i + 1;
        }
        return i - 1;
    }

    public boolean verifyUpdateStatus(List<ArrayList<String>> lstData, boolean isSuccess, HashMap<String, Boolean> sportList) {
        int sportColumn = getSpotColumn(sportList);
        int totalColum = 10;
        totalColum = sportColumn + totalColum;
        tblDownline = Table.xpath("//table[contains(@class,'ptable report table-responsive')]", totalColum);
        updateStatusCol = totalColum;
        String cell_xpath;
        for (int i = 0; i < lstData.size(); i++) {
            if (i % 4 == 0) {
                cell_xpath = String.format("%s//tr[%s]//td[%s]", "//table[contains(@class,'ptable report table-responsive')]", i + 1, updateStatusCol);
                Label lblIcon;
                if (isSuccess) {
                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, successIcon));
                } else {
                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, errorIcon));
                }
                if (!lblIcon.isDisplayed())
                    return false;
            }
        }
        return true;
    }

    public void updateBetSetting(String loginID, int minBet, int maxBet, int maxLiabilityPerMarket, int maxWinPerMarket) {

        //input Min, Max, Max Liability per Market, Max Win Per Market
        inputValue(minBet, maxBet, maxLiabilityPerMarket, maxWinPerMarket);

        // Select the checkbox corresponding with login ID
        String chbDownlinexPath = tblDownline.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, usernameCol, 1, null, chbCol, "input[@id='cItem']", false, false);
        CheckBox chb = CheckBox.xpath(chbDownlinexPath);
        chb.click();

        //Click update
        btnUpdate.click();
        waitingLoadingSpinner();
    }

    public void inputValue(int min, int max, int maxLiabilityperMarket, int maxWinPerMarket) {
        if (min != -1)
            txtMinBet.sendKeys(Integer.toString(min));
        if (max != -1)
            txtMaxBet.sendKeys(Integer.toString(max));
        if (maxLiabilityperMarket != -1)
            txtMaxLiabilityPerMarket.sendKeys(Integer.toString(maxLiabilityperMarket));
        if (maxWinPerMarket != -1)
            txtMaxWinPerMarket.sendKeys(Integer.toString(maxWinPerMarket));
    }

    public void enableSport(HashMap<String, Boolean> map) {
        CheckBox chb = CheckBox.xpath(String.format(xPathSport, "Soccer"));
        if (!map.get("Soccer")) {
            chb.click();
            //waitingLoadingSpinner();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Cricket"));
        if (!map.get("Cricket")) {
            chb.click();
            //waitingLoadingSpinner();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Tennis"));
        if (!map.get("Tennis")) {
            chb.click();
            //waitingLoadingSpinner();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Basketball"));
        if (!map.get("Basketball")) {
            chb.click();
            //waitingLoadingSpinner();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Fancy"));
        if (!map.get("Fancy")) {
            chb.click();
            //  waitingLoadingSpinner();
        }

        chb = CheckBox.xpath(String.format(xPathSport, "Other"));
        if (!map.get("Other")) {
            chb.click();
            //  waitingLoadingSpinner();
        }
        waitingLoadingSpinner();
    }

    public void search(String username, String level, String accountStatus, String product) {
        if (!username.isEmpty())
            txtUsername.sendKeys(username);
        if (!accountStatus.isEmpty())
            ddbAccountStatus.selectByVisibleText(accountStatus);
        if (!product.isEmpty())
            ddbProduct.selectByVisibleText(product);
        if (!level.isEmpty())
            ddbLevel.selectByVisibleText(level);
        btnSubmit.click();
        waitingLoadingSpinner();
    }
}
