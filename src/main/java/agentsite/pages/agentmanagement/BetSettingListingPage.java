package agentsite.pages.agentmanagement;

import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BetSettingListingPage extends HomePage {
    public TextBox txtUsername = TextBox.id("userName");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//label[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//label[text()='Product']//following::select[1]");
    public DropDownBox ddbLevel = DropDownBox.id("userLevel");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public int totalColum = 10;
    public int usernameCol = 2;
    public int loginIDCol = 3;
    public int accountStatusCol = 4;
    public int exchangeCol = 5;
    public int chbCol = 6;
    public int levelCol = 7;
    public int currencyCol = 8;
    public int settingsCol = 9;
    public int soccerCol = 10;
    public int cricketCol = 11;
    public int tennisCol = 12;
    public int basketballCol = 13;
    public int fancyCol = 14;
    public int otherCol = 15;
    public int updateStatusCol = 16;
    public Table tblDownline = Table.xpath("//table[contains(@class,'ptable report')]", totalColum);

    private Label iconLoadSpinner = Label.xpath("//div[contains(@class,'la-ball-clip-rotate')]");

    public TextBox txtMinBet = TextBox.xpath("(//table[@class='ptable info search-region']//input)[1]");
    public TextBox txtMaxBet = TextBox.xpath("(//table[@class='ptable info search-region']//input)[2]");
    public TextBox txtMaxLiabilityPerMarket = TextBox.xpath("(//table[@class='ptable info search-region']//input)[3]");
    public TextBox txtMaxWinPerMarket = TextBox.xpath("(//table[@class='ptable info search-region']//input)[4]");

    public Label lblBreadcrumb = Label.xpath("//span[@class='my-breadcrumb']");
    public Button btnUpdate = Button.xpath("//button[contains(@class,'directDownline')]");
    private String xPathSport = "//label[@class='sportName' and contains(text(),'%s')]/preceding::input[1]";
    private String successIcon = "//span[contains(@class,'psuccess')]";
    private String errorIcon = "//span[contains(@class,'perror')]";

    public BetSettingListingPage(String types) {
        super(types);
    }
    public void search(String username, String level, String accountStatus, String product){
        if(!username.isEmpty())
            txtUsername.sendKeys(username);
        if(!accountStatus.isEmpty())
            ddbAccountStatus.selectByVisibleText(accountStatus);
        if(!product.isEmpty())
            ddbProduct.selectByVisibleText(product);
        if(!level.isEmpty())
            ddbLevel.selectByVisibleText(level);
        btnSearch.click();
        waitingLoadingSpinner();
    }

    public void enableSport(HashMap<String, Boolean> map)
    {
       CheckBox chb =CheckBox.xpath(String.format(xPathSport,"Soccer"));
        if(!map.get("Soccer"))
        {
            chb.click();
            //waitingLoadingSpinner();
        }
        chb =CheckBox.xpath(String.format(xPathSport,"Cricket"));
        if(!map.get("Cricket"))
        {
            chb.click();
            //waitingLoadingSpinner();
        }
        chb =CheckBox.xpath(String.format(xPathSport,"Tennis"));
        if(!map.get("Tennis"))
        {
            chb.click();
            //waitingLoadingSpinner();
        }
        chb =CheckBox.xpath(String.format(xPathSport,"Basketball"));
        if(!map.get("Basketball"))
        {
            chb.click();
            //waitingLoadingSpinner();
        }
        chb =CheckBox.xpath(String.format(xPathSport,"Fancy"));
        if(!map.get("Fancy"))
        {
            chb.click();
          //  waitingLoadingSpinner();
        }

        chb =CheckBox.xpath(String.format(xPathSport,"Other"));
        if(!map.get("Other"))
        {
            chb.click();
          //  waitingLoadingSpinner();
        }
    }

    public int getSpotColumn(HashMap<String, Boolean> map)
    {
        int i = 1;
        if(map.get("Soccer")) {
            soccerCol = (totalColum -1) + i;
            i = i +1;
        }

        if(map.get("Cricket")) {
            cricketCol = (totalColum -1) + i;
            i = i +1;
        }

        if(map.get("Tennis")) {
            tennisCol = (totalColum -1) + i;
            i = i + 1;
        }

        if(map.get("Basketball")){
            basketballCol = (totalColum -1) + i;
            i = i + 1;
        }
        if(map.get("Fancy")){
            fancyCol = (totalColum -1) + i;
            i = i + 1;
        }

        if(map.get("Other")){
            otherCol = (totalColum -1) + i;
            i = i + 1;
        }
        return i-1;
    }

    /**
     * Update value for Min Bet, Max Bet, Max Liability Per Market, maxWinPermarket. If input value = -1 we will ignore the input
     * @param loginID
     * @param minBet
     * @param maxBet
     * @param maxLiabilityPerMarket
     * @param maxWinPerMarket
     */
    public void updateBetSetting(String loginID,int minBet, int maxBet, int maxLiabilityPerMarket, int maxWinPerMarket){

        //input Min, Max, Max Liability per Market, Max Win Per Market
        inputValue(minBet, maxBet, maxLiabilityPerMarket, maxWinPerMarket);

        // Select the checkbox corresponding with login ID
        String chbDownlinexPath = tblDownline.getControlxPathBasedValueOfDifferentColumnOnRow(loginID,1,usernameCol,1,null,chbCol,"input[@id='cItem']",false,false);
        CheckBox chb = CheckBox.xpath(chbDownlinexPath);
        chb.click();

        //Click update
        btnUpdate.click();
        waitingLoadingSpinner();
    }

    public void inputValue (int min, int max, int maxLiabilityperMarket, int maxWinPerMarket)
    {
        if(min != -1)
            txtMinBet.sendKeys(Integer.toString(min));
        if(max != -1)
            txtMaxBet.sendKeys(Integer.toString(max));
        if(maxLiabilityperMarket != -1)
            txtMaxLiabilityPerMarket.sendKeys(Integer.toString(maxLiabilityperMarket));
        if(maxWinPerMarket != -1)
            txtMaxWinPerMarket.sendKeys(Integer.toString(maxWinPerMarket));
    }

    public boolean verifyUpdateStatus(List<ArrayList<String>> lstData,boolean isSuccess,HashMap<String, Boolean> sportList)
    {
        int sportColumn = getSpotColumn(sportList);
        int totalColum = 10;
        totalColum = sportColumn + totalColum;
        tblDownline = Table.xpath("//table[contains(@class,'ptable report table-responsive')]",totalColum);
        updateStatusCol = totalColum;
        String cell_xpath;
        for(int i = 0; i< lstData.size(); i++)
        {
            if(i % 4 ==0) {
                cell_xpath = String.format("%s//tr[%s]//td[%s]", "//table[contains(@class,'ptable report table-responsive')]", i + 1, updateStatusCol);
                Label lblIcon;
                if (isSuccess) {
                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, successIcon));
                } else {
                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, errorIcon));
                }
                if(!lblIcon.isDisplayed())
                    return false;
            }
        }
        return true;
    }
    public List<ArrayList<String>> getBetSettingofAccount(HashMap<String, Boolean> sportList)
    {
        //init table column
        int sportColumn = getSpotColumn(sportList);
        totalColum = sportColumn + totalColum;
        tblDownline = Table.xpath("//table[contains(@class,'ptable report')]",totalColum);
        List<ArrayList<String>> listInfo =  new ArrayList<ArrayList<String>>();

        String rowXpath = String.format("%s%s", this.tblDownline.getLocator().toString().replace("By.xpath: ",""), "//tbody/tr[%s]");

        // Get total row of the table
        int totalRows = tblDownline.getNumberOfRows(false,false);
        for (int i=0; i < totalRows; i ++){

            Row row = Row.xpath(String.format(rowXpath, (i + 1)));
            //1,5,9,12
             if(i % 4 == 0) {
                 listInfo.add(row.getRow(totalColum,false));
             }
            else{
                 listInfo.add(row.getRow(sportColumn + 1,false));
             }
        }
        return listInfo;
    }
    public List<ArrayList<String>> defineActualDataForOneAccount(HashMap<String, Boolean> sportList,double minBet, double maxBet, double maxLiabilityPerMarket, double maxWinPerMarket)
    {
        // this function define data for an account
        List<ArrayList<String>> lstExpectedData = getBetSettingofAccount(sportList);
        lstExpectedData.get(0).set(soccerCol - 1, String.format("%.2f",minBet));
        lstExpectedData.get(0).set(cricketCol - 1, String.format("%.2f",minBet));
        lstExpectedData.get(0).set(tennisCol - 1, String.format("%.2f",minBet));
        lstExpectedData.get(0).set(basketballCol - 1, String.format("%.2f",minBet));
        lstExpectedData.get(0).set(fancyCol - 1 , String.format("%.2f",minBet));
        lstExpectedData.get(0).set(otherCol - 1, String.format("%.2f",minBet));
        lstExpectedData.get(1).set(1,String.format("%.2f",maxBet));
        lstExpectedData.get(1).set(2,String.format("%.2f",maxBet));
        lstExpectedData.get(1).set(3,String.format("%.2f",maxBet));
        lstExpectedData.get(1).set(4,String.format("%.2f",maxBet));
        lstExpectedData.get(1).set(5,String.format("%.2f",maxBet));
        lstExpectedData.get(1).set(6,String.format("%.2f",maxBet));
        lstExpectedData.get(2).set(1,String.format("%.2f",maxLiabilityPerMarket));
        lstExpectedData.get(2).set(2,String.format("%.2f",maxLiabilityPerMarket));
        lstExpectedData.get(2).set(3,String.format("%.2f",maxLiabilityPerMarket));
        lstExpectedData.get(2).set(4,String.format("%.2f",maxLiabilityPerMarket));
        lstExpectedData.get(2).set(5,String.format("%.2f",maxLiabilityPerMarket));
        lstExpectedData.get(2).set(6,String.format("%.2f",maxLiabilityPerMarket));
        lstExpectedData.get(3).set(1,String.format("%.2f",maxWinPerMarket));
        lstExpectedData.get(3).set(2,String.format("%.2f",maxWinPerMarket));
        lstExpectedData.get(3).set(3,String.format("%.2f",maxWinPerMarket));
        lstExpectedData.get(3).set(4,String.format("%.2f",maxWinPerMarket));
        lstExpectedData.get(3).set(5,String.format("%.2f",maxWinPerMarket));
        lstExpectedData.get(3).set(6,String.format("%.2f",maxWinPerMarket));
        return lstExpectedData;
    }
    public void waitingLoadingSpinner()
    {
        iconLoadSpinner.waitForControlInvisible(1,1);
    }
}
