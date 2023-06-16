package agentsite.pages.marketsmanagement;

import agentsite.controls.MenuTree;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.marketsmanagement.blockracing.BlockedUserPopup;
import com.paltech.element.common.*;
import org.openqa.selenium.Keys;

import java.util.List;


public class BlockRacingPage extends HomePage {
    public Label lblTitle = Label.xpath("//app-blocking-log-event//div[@class='title']");
    public DropDownBox ddbSport = DropDownBox.xpath("//app-block-racing//select[contains(@class,'block-select')]");
    public Table tblDownline = Table.xpath("//app-block-racing//table[contains(@class,'block-table')]", 1);
    public Table tblCurrentVenue = Table.xpath("//app-block-racing//table[contains(@class,'report')]",9);
    int colVenueName = 1;
   public TextBox txtSearchVenueName = TextBox.xpath("//app-block-racing//table[contains(@class,'report')]//div[@class='searchBox']");
    public MenuTree mnCountries = MenuTree.xpath("//app-block-racing//tabset/ul","//li");
    public MenuTree mnBlockingTab=MenuTree.xpath("//app-block-racing//div[@class='countryTabs']","//li");
    public CheckBox cbSelectAllDownline = CheckBox.xpath("//app-block-racing//table[contains(@class,'block-table')]//span[contains(@class,'select-all')]/i");
    public Button btnUpdate = Button.id("bntUpdate");
    public BlockRacingPage(String types) {
        super(types);
    }
    public void selectBlockingTab(String tabName){
        mnBlockingTab.clickMenu(tabName);
        waitingLoadingSpinner();
    }

    public void block(String sportName, String country, String downline, String venueName, String marketName){
        // Step 1 select blocking tab
        selectBlockingTab("Blocking");
        // Step 2 Slect sport
        ddbSport.selectByVisibleText(sportName);
        waitingLoadingSpinner();
        // Step 3 Select Country
        mnCountries.clickMenu(country);
        // Step 4 Select downline
        selectDownline(downline,true);
        // Step 5 Select Venue NAme with markets
        searchVenueName(venueName);
        selectVenueMarket(venueName,marketName);

        btnUpdate.click();
        waitingLoadingSpinner();
    }

    public void unblock(String sportName, String country, String downline, String venueName, String marketName){
        // Step 1 select blocking tab
        selectBlockingTab("Current");
        // Step 2 Slect sport
        ddbSport.selectByVisibleText(sportName);
        waitingLoadingSpinner();
        // Step 3 Select Country
        mnCountries.clickMenu(country);
        // Step 4 Click on cell have Venue and Market then unblock for expected user
        BlockedUserPopup blockedUserPopup = clickVenueMarketCell(venueName,marketName);
        blockedUserPopup.unblockUser(downline);
        waitingLoadingSpinner();
    }
    public void clickDownline(String downline){
        if(!downline.equalsIgnoreCase("all")) {
            Label lblDownlineName = Label.xpath(String.format("//table[contains(@class,'block-table')]//span[contains(text(),'%s')]",downline));
            //   lblDownlineName = Label.xpath(String.format("//table[contains(@class,'block-table')]//span[contains(text(),'%s')]",downline));
            lblDownlineName.isClickable(3);
            lblDownlineName.click();
            waitingLoadingSpinner();
        }
    }

    public void checkDownline(String downline){
        CheckBox checkbox;
        if(downline.equalsIgnoreCase("all")){
            checkbox = cbSelectAllDownline;
        }else
            checkbox =  CheckBox.xpath(String.format("//table[contains(@class,'block-table')]//span[contains(text(),'%s')]/following::span[1]/i",downline));

        checkbox.click();
        waitingLoadingSpinner();
    }
    public void selectDownline(String downline, boolean isClick)
    {
        if(isClick){
            clickDownline(downline);
        }
        checkDownline(downline);
    }

    public void searchVenueName(String venueName){
        txtSearchVenueName.sendKeys(venueName);
        txtSearchVenueName.type(Keys.ENTER);
    }

    public void selectVenueMarket(String venue, String market){
        int rowIndex = getVenueNameIndex(venue);
        int colIndex =0;
        if(market.equalsIgnoreCase("all")){
            colIndex = 1;
        }else
            colIndex = getMarketColIndex(market);
        CheckBox cb = CheckBox.xpath(tblCurrentVenue.getxPathOfCell(1,colIndex,rowIndex,"input"));
        if(!cb.isDisplayed()){
            System.out.println("DEBUG! Cannot find check box at the Venue row: "+ venue + " and Market column " + market);
           return;
        }
        cb.click();
    }
    public BlockedUserPopup clickVenueMarketCell(String venue, String market){
        int rowIndex = getVenueNameIndex(venue);
        int  colIndex = getMarketColIndex(market);
        CheckBox cb = CheckBox.xpath(tblCurrentVenue.getxPathOfCell(1,colIndex,rowIndex,null));
        cb.click();
        BlockedUserPopup blockedUserPopup = new BlockedUserPopup();
        blockedUserPopup.tblBlockedUser.isDisplayed();
        return blockedUserPopup;
    }
    private int getVenueNameIndex(String venueName){
        int i = 1;
        Label lblVenueName;
        while (true){
            lblVenueName = Label.xpath(tblCurrentVenue.getxPathOfCell(1,colVenueName,i,null));
            if(!lblVenueName.isDisplayed())
            {
                System.out.println("DEBUG! The Venue "+ venueName +" does not display in the table");
                return 0;
            }
            if(lblVenueName.getText().equalsIgnoreCase(venueName)) {
                System.out.println("DEBUG! Found Venue "+ venueName +" at row "+i);
                return i;
            }
            i = i++;
        }
    }

    private int getMarketColIndex(String marketName){
        List<String> lstHeaderTable = tblCurrentVenue.getHeaderNameOfRows();
        for (String col: lstHeaderTable ) {
            if(col.equals(marketName)){
                return lstHeaderTable.indexOf(marketName) + 1;
            }
        }
        System.out.println("DEBUG! Not Found the market column "+ marketName +" in the table");
        return 0;
    }

    public String getNumberBlockRacing(String venue, String market){
        int rowIndex = getVenueNameIndex(venue);
        int colIndex = getMarketColIndex(market);
        Label lblNumber = Label.xpath(tblCurrentVenue.getxPathOfCell(1,colIndex,rowIndex,"span[contains(@class,'ribbon-text')]"));
        return lblNumber.getText().trim();
    }
    public boolean isLockIconDisplay(String venue, String market){
        int rowIndex = getVenueNameIndex(venue);
        int colIndex = getMarketColIndex(market);
        return Label.xpath(tblCurrentVenue.getxPathOfCell(1,colIndex,rowIndex,"i[contains(@class,'fa-lock')]")).isDisplayed();
    }

}
