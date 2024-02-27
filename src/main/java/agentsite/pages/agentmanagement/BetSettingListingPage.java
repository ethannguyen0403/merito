package agentsite.pages.agentmanagement;

import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.betsettinglisting.BetSettingListing;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.components.ConfirmPopup;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.AgencyManagement.BetSettingListing.PS38_COPY_SETTING_TO_ALL_SPECIFIC_SPORT_LEAGUES;
import static common.AGConstant.AgencyManagement.BetSettingListing.PS38_DEFAULT_SPORT_LIST;

public class BetSettingListingPage extends HomePage {
    private static int totalColum = 19;
    private static int soccerCol;
    private static int cricketCol;
    private static int fancyCol;
    private static int virtualCricketCol;
    private static int bookmakerCol;
    private static int tennisCol;
    private static int basketballCol;
    private static int otherCol;
    private static int decimalCricketCol;
    private static int chbCol = 6;
    public int usernameCol = 2;
    public static Table tblDownline = Table.xpath("//table[contains(@class,'ptable report')]", totalColum);
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
    public Row sportsRow = Row.xpath("//div[@id='betSetting']//table[@class='ptable info ng-star-inserted']/tr");
    public Label lblTitlePage = Label.xpath("//app-title-dashboard//div[@class='title']//label");
    public BetSettingListing betSettingListing;
    // UI when select PS38 product
    public DropDownBox ddbSport = DropDownBox.xpath("//label[text()='Sport']//following::select[1]");
    public DropDownBox ddbLeagues = DropDownBox.xpath("//label[text()='Leagues']//following::select[1]");
    public CheckBox cbCopySettingToAllSpecificSportLeagues = CheckBox.xpath("//label[contains(text(),'"+PS38_COPY_SETTING_TO_ALL_SPECIFIC_SPORT_LEAGUES+"')]/input");
    public Label lblPregameTab = Label.xpath("//ul[@class='list-unstyled']/li/div[1]");
    public Label lblInplayTab = Label.xpath("//ul[@class='list-unstyled']/li/div[2]");
    public TextBox txtMaxPerMatch = TextBox.xpath("(//table[@class='ptable info search-region']//input)[3]");
    public Label lblValuePrefixWithDirect  = Label.xpath("(//table[@class='ptable info search-region'])//tr[2]//span");
    private int colPS83UpDateStatus = 3;
    private int colPS83Settings = 10;
    private int totalMergCols = 9;

    public BetSettingListingPage(String types) {
        super(types);
        _type = types;
        betSettingListing = ComponentsFactory.betSettingPage(_type);
    }

    public List<ArrayList<String>> defineActualDataForOneAccount(HashMap<String, Boolean> sportList, double minBet, double maxBet, double maxLiabilityPerMarket, double maxWinPerMarket) {
        return betSettingListing.defineActualDataForOneAccount(sportList, minBet, maxBet, maxLiabilityPerMarket, maxWinPerMarket);
    };
    public static List<ArrayList<String>> getBetSettingofAccount(HashMap<String, Boolean> sportList) {
        //init table column
        int sportColumn = getSpotColumn(sportList);
        totalColum = sportColumn + totalColum;
        tblDownline = Table.xpath("//table[contains(@class,'ptable report')]", totalColum);
        List<ArrayList<String>> listInfo = new ArrayList<ArrayList<String>>();

        String rowXpath = String.format("%s%s", tblDownline.getLocator().toString().replace("By.xpath: ", ""), "//tbody/tr[%s]");

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

    public static int getSpotColumn(HashMap<String, Boolean> map) {
        int i = 1;
        if (map.get("Soccer")) {
            soccerCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Cricket")) {
            cricketCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Fancy")) {
            fancyCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Virtual Cricket")) {
            virtualCricketCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Bookmaker")) {
            bookmakerCol = (totalColum - 1) + i;
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
        if (map.get("Other")) {
            otherCol = (totalColum - 1) + i;
            i = i + 1;
        }
        if (map.get("Decimal Cricket")) {
            decimalCricketCol = (totalColum - 1) + i;
            i = i + 1;
        }
        return i - 1;
    }

    public boolean verifyUpdateStatus(List<ArrayList<String>> lstData, boolean isSuccess, HashMap<String, Boolean> sportList) {
        return betSettingListing.verifyUpdateStatus(lstData, isSuccess, sportList);
    }

    public void selectAccount(String username){
        // Select the checkbox corresponding with login ID
        String chbDownlinexPath = tblDownline.getControlxPathBasedValueOfDifferentColumnOnRow(username, 1, usernameCol, 1, null, chbCol, "input[@id='cItem']", false, false);
        CheckBox chb = CheckBox.xpath(chbDownlinexPath);
        chb.click();
    }

    public void updateBetSetting(String loginID, int minBet, int maxBet, int maxLiabilityPerMarket, int maxWinPerMarket) {

        //input Min, Max, Max Liability per Market, Max Win Per Market
        inputValue(minBet, maxBet, maxLiabilityPerMarket, maxWinPerMarket);

        selectAccount(loginID);

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
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Cricket"));
        if (!map.get("Cricket")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Fancy"));
        if (!map.get("Fancy")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Virtual Cricket"));
        if (!map.get("Virtual Cricket")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Bookmaker"));
        if (!map.get("Bookmaker")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Decimal Cricket"));
        if (!map.get("Bookmaker")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Tennis"));
        if (!map.get("Tennis")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Basketball"));
        if (!map.get("Basketball")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Other"));
        if (!map.get("Other")) {
            chb.click();
        }
        waitingLoadingSpinner();
    }

    public void search(String username, String level, String accountStatus, String product) {
        inputSearchFilter(username,level,accountStatus,product);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    private void inputSearchFilter(String username, String level, String accountStatus, String product){
        if (!username.isEmpty())
            txtUsername.sendKeys(username);
        if (!accountStatus.isEmpty())
            ddbAccountStatus.selectByVisibleText(accountStatus);
        if (!product.isEmpty())
            ddbProduct.selectByVisibleText(product);
        if (!level.isEmpty())
            ddbLevel.selectByVisibleText(level);
    }

    // Start method for PS38 product
    public void filterPS38Product(String username, String level, String accountStatus, String product, String sport, String league){
        inputSearchFilter(username,level,accountStatus,product);
        if (!sport.isEmpty())
            ddbSport.selectByVisibleText(sport);
        if (!league.isEmpty())
            ddbLeagues.selectByVisibleText(level);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void updateBetSettingPS38(String username,String pregameOrInplay, boolean isCopySetting, String min, String max, String maxPerMatch, boolean isConfirm){
        boolean currentCopySetting = cbCopySettingToAllSpecificSportLeagues.isSelected();
        if(currentCopySetting != isCopySetting)
            cbCopySettingToAllSpecificSportLeagues.click();
        if (pregameOrInplay.equalsIgnoreCase("PREGAME"))
            lblPregameTab.click();
        else
            lblInplayTab.click();
        selectAccount(username);
        if(Objects.nonNull(min))
            txtMinBet.sendKeys(min);
        if(Objects.nonNull(max))
            txtMaxBet.sendKeys(max);
        if(Objects.nonNull(maxPerMatch))
            txtMaxPerMatch.sendKeys(maxPerMatch);
        btnUpdate.click();
        ConfirmPopup popup = new ConfirmPopup();

        if(!popup.isPopupDisplay()) {
            System.out.println("Display: " + popup.isPopupDisplay());
            System.err.println("There is no confirm popup display to do confirm action");
            return;
        }
        else {
            if(isConfirm) {
                popup.confirm();
                waitingLoadingSpinner();
            }
            else
                popup.cancel();
        }
    }
    public void verifyBetSettingPS38UpdateSucceed(String username,String sportName, String leagueName,  String min, String max, String maxPerMatch){
        String updateIconXpath = tblDownline.getControlxPathBasedValueOfDifferentColumnOnRow(username, 1, usernameCol, 1, null, colPS83UpDateStatus, "span", false, false);
        // Check Update Status is column of the according account is display Green check
        Assert.assertTrue(Label.xpath(updateIconXpath).getAttribute("class").contains("psuccess")," Failed! Update status column should display green check sign");

        // Check valued of column Username and Setting Column is corrected
        String usernameActual = Label.xpath(tblDownline.getxPathOfCell(1,usernameCol,1,"span")).getText();
        Assert.assertEquals(usernameActual,username," Failed! Username is incorrect");
        String minSetting = Label.xpath(tblDownline.getxPathOfCell(1,colPS83Settings,1,null)).getText();
        Assert.assertEquals(minSetting,"Min Bet"," Failed! Min Bet is incorrect");
        String maxSetting = Label.xpath(tblDownline.getxPathOfCell(1,colPS83Settings - totalMergCols,2,null)).getText();
        Assert.assertEquals(maxSetting,"Max Bet"," Failed! Max Bet is incorrect");
        String maxPerMatchSetting = Label.xpath(tblDownline.getxPathOfCell(1,colPS83Settings - totalMergCols ,3,null)).getText();
        Assert.assertEquals(maxPerMatchSetting,"Max Per Match"," Failed! Max Per Match is incorrect");

        // Check value of sport/leagues coumn is correct (Soccer-General, Baseball-General,...)
        verifyMinMaxMaxPerMatchValuesBasedOnSport(sportName,leagueName,min,max,maxPerMatch);
    }

    private void verifyMinMaxMaxPerMatchValuesBasedOnSport(String sport, String league,String min, String max, String maxPerMatch){
        int column;
        // check the min/max/max per match value of according selectedsports in Bet Setting PS38
        if (!sport.equalsIgnoreCase("All"))
        {
            column = defineColumnOfSport(sport,league);
            verifyMinMaxMaxPerMatchValuesofColumn(column,min,max,maxPerMatch);
            return;
        }
        // check the min/max/max per match value of all default sports in Bet Setting PS38
        String maxPerMatchExpected = "";
        for (String s: PS38_DEFAULT_SPORT_LIST) {
            column = defineColumnOfSport(s,"General");
            maxPerMatchExpected = maxPerMatch;
            if(s.equalsIgnoreCase("Mix Parlay") || s.equalsIgnoreCase("Teaser")){
                // handle for Max Per Match is empty for Mix Parlay and Teaser
                maxPerMatchExpected = "";
            }
            System.out.println("Verify Min / Max /Max Per Match of sport: "+ s);
            verifyMinMaxMaxPerMatchValuesofColumn(column,min,max,maxPerMatchExpected);
        }
        return;
    }
    private void verifyMinMaxMaxPerMatchValuesofColumn(int column,String min, String max, String maxPerMatch){
        if(Objects.nonNull(min)){
            String minExpected = String.format("%,.2f",Double.parseDouble(min));
            String minSetting = Label.xpath(tblDownline.getxPathOfCell(1,column,1,null)).getText();
            Assert.assertEquals(minSetting,minExpected," Failed! Min Bet value is incorrect");
        }
        if(Objects.nonNull(max)){
            String maxExpected = String.format("%,.2f",Double.parseDouble(max));
            String maxSetting = Label.xpath(tblDownline.getxPathOfCell(1,column - totalMergCols,2,null)).getText();
            Assert.assertEquals(maxSetting,maxExpected," Failed! Max Bet value is incorrect");
        }

        if(Objects.nonNull(maxPerMatch)){
            String maxPerMatchExpected = maxPerMatch;
            if(!maxPerMatch.equals("")){
                maxPerMatchExpected = String.format("%,.2f",Double.parseDouble(maxPerMatch));
            }
            String maxPerMatchSetting = Label.xpath(tblDownline.getxPathOfCell(1,column-totalMergCols ,3,null)).getText();
            Assert.assertEquals(maxPerMatchSetting,maxPerMatchExpected," Failed! Max Per Match value of is incorrect");
        }

    }


    private int defineColumnOfSport(String sport, String league){
        String headerXpath ="//table[contains(@class,'ptable report')]//thead/tr/th";
        int totalHeader = Label.xpath(headerXpath).getWebElements().size();
        // start column index will start from Setting column
        int startIndex = colPS83Settings +1;
        for(int i = startIndex; i <= totalHeader; i++){
            Label lblSport = Label.xpath(String.format("//table[contains(@class,'ptable report')]//thead/tr/th[%d]/div[1]",i));
            Label lblleague = Label.xpath(String.format("//table[contains(@class,'ptable report')]//thead/tr/th[%d]/div[2]",i));
            if(!lblSport.isDisplayed()){
                continue;
            }
            if(lblSport.getText().equals(sport)){
                if(lblleague.getText().equals(league)) {
                    System.out.println(String.format("DEBUG: Found the column has sport: %s and league: %s at column index %d", sport, league,i));
                    return i;
                }
                else
                    continue;
            }else
                continue;

        }
        System.err.println(String.format("DEBUG: Not found the column has sport: %s and league: %s in the table header", sport, league));
        return -1;
    }


    // End method for PS38 product
}
