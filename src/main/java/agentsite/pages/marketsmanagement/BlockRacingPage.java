package agentsite.pages.marketsmanagement;

import agentsite.controls.MenuTree;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.marketsmanagement.blockracing.BlockedUserPopup;
import com.paltech.element.common.*;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import org.testng.Assert;

import java.util.List;

import static common.AGConstant.HomePage.BLOCK_RACING;


public class BlockRacingPage extends HomePage {
    public Label lblTitle = Label.xpath("//app-block-racing//div[@class='title']");
    public DropDownBox ddbSport = DropDownBox.xpath("//app-block-racing//label[text()='Sport']//..//select[contains(@class,'block-select')]");
    public DropDownBox ddbSMAList = DropDownBox.xpath("//app-block-racing//label[text()='SMA List']//..//select[contains(@class,'block-select')]");
    public Table tblDownline = Table.xpath("//app-block-racing//table[contains(@class,'block-table')]", 1);
    public Table tblCurrentVenue = Table.xpath("//app-block-racing//table[contains(@class,'report')]", 9);
    public TextBox txtSearchVenueName = TextBox.xpath("//app-block-racing//table[contains(@class,'report')]//div[@class='searchBox']//input");
    public MenuTree mnCountries = MenuTree.xpath("//app-block-racing//tabset/ul", "//li");
    public MenuTree mnBlockingTab = MenuTree.xpath("//app-block-racing//div[@class='countryTabs']", "//li");
    public CheckBox cbSelectAllDownline = CheckBox.xpath("//app-block-racing//table[contains(@class,'block-table')]//span[contains(@class,'select-all')]/i");
    public Button btnUpdate = Button.id("bntUpdate");
    public Label lblSuccessMessage = Label.xpath("//div[@class='modal-content']//div[@class='modal-body']");
    public Button btnCloseUpdateSetting = Button.xpath("//div[@class='modal-content']//button[@class='close pull-right']");
    int colVenueName = 1;

    public BlockRacingPage(String types) {
        super(types);
    }

    public void selectBlockingTab(String tabName) {
        mnBlockingTab.clickMenu(tabName);
        waitingLoadingSpinner();
    }

    public String block(String sportName, Event event, String downline, Market market) {
        // Step 1 select blocking tab
        selectBlockingTab("Blocking");
        // Step 2 Slect sport
        ddbSport.selectByVisibleText(sportName);
        waitingLoadingSpinner();
        // Step 3 Select Country
        mnCountries.clickMenu(event.getCountryName());
        waitingLoadingSpinner();
        // Step 4 Select downline
        selectDownline(downline, true);
        // Step 5 Select Venue NAme with markets
        searchVenueName(event.getEventName());
        selectVenueMarket(event.getEventName(), market.getMarketName());
        if(btnUpdate.isClickable(5)) {
            btnUpdate.click();
            //workaround to wait since the spinner appears after few secs
            lblSuccessMessage.waitForElementToBePresent(lblSuccessMessage.getLocator(), 5);
            waitingLoadingSpinner();
            return lblSuccessMessage.getText().trim();
        }
        return "";
    }

    public void unblock(String sportName, Event event, String downline, Market market) {
        // Step 1 select blocking tab
        selectBlockingTab("Current");
        // Step 2 Slect sport
        ddbSport.selectByVisibleText(sportName);
        waitingLoadingSpinner();
        // Step 3 Select Country
        mnCountries.clickMenu(event.getCountryName());
        // Step 4 Click on cell have Venue and Market then unblock for expected user
        BlockedUserPopup blockedUserPopup = clickVenueMarketCell(event.getEventName(), market.getMarketName());
        blockedUserPopup.unblockUser(downline);
        waitingLoadingSpinner();
    }

    public void clickDownline(String downline) {
        if (!downline.equalsIgnoreCase("all")) {
            Label lblDownlineName = Label.xpath(String.format("//table[contains(@class,'block-table')]//span[contains(text(),'%s')]", downline));
            //   lblDownlineName = Label.xpath(String.format("//table[contains(@class,'block-table')]//span[contains(text(),'%s')]",downline));
            lblDownlineName.isClickable(3);
            lblDownlineName.click();
            waitingLoadingSpinner();
        }
    }

    public void checkDownline(String downline) {
        CheckBox checkbox;
        if (downline.equalsIgnoreCase("all")) {
            checkbox = cbSelectAllDownline;
        } else
            checkbox = CheckBox.xpath(String.format("//table[contains(@class,'block-table')]//span[contains(text(),'%s')]/following::span[1]/i", downline));

        checkbox.click();
        waitingLoadingSpinner();
    }

    public void selectDownline(String downline, boolean isClick) {
        if (isClick) {
            clickDownline(downline);
        }
        checkDownline(downline);
    }

    public void searchVenueName(String venueName) {
        txtSearchVenueName.sendKeys(venueName);
        waitingLoadingSpinner();
    }

    public void selectVenueMarket(String venue, String market) {
        int rowIndex = getVenueNameIndex(venue);
        int colIndex = 0;
        if (market.equalsIgnoreCase("all")) {
            colIndex = 1;
        } else
            colIndex = getMarketColIndex(market);
        CheckBox cb = CheckBox.xpath(tblCurrentVenue.getxPathOfCell(1, colIndex, rowIndex, "input"));
        if (!cb.isDisplayed()) {
            System.out.println("DEBUG! Cannot find check box at the Venue row: " + venue + " and Market column " + market);
            return;
        }
        cb.click();
    }

    public BlockedUserPopup clickVenueMarketCell(String venue, String market) {
        int rowIndex = getVenueNameIndex(venue);
        int colIndex = getMarketColIndex(market);
        CheckBox cb = CheckBox.xpath(tblCurrentVenue.getxPathOfCell(1, colIndex, rowIndex, null));
        cb.click();
        BlockedUserPopup blockedUserPopup = new BlockedUserPopup();
        blockedUserPopup.tblBlockedUser.isDisplayed();
        return blockedUserPopup;
    }

    private int getVenueNameIndex(String venueName) {
        int i = 1;
        Label lblVenueName;
        while (true) {
            lblVenueName = Label.xpath(tblCurrentVenue.getxPathOfCell(1, colVenueName, i, null));
            if (!lblVenueName.isDisplayed()) {
                System.out.println("DEBUG! The Venue " + venueName + " does not display in the table");
                return 0;
            }
            if (lblVenueName.getText().equalsIgnoreCase(venueName)) {
                System.out.println("DEBUG! Found Venue " + venueName + " at row " + i);
                return i;
            }
            i = i++;
        }
    }

    private int getMarketColIndex(String marketName) {
        List<String> lstHeaderTable = tblCurrentVenue.getHeaderNameOfRows();
        for (String col : lstHeaderTable) {
            if (col.equals(marketName)) {
                return lstHeaderTable.indexOf(marketName) + 1;
            }
        }
        System.out.println("DEBUG! Not Found the market column " + marketName + " in the table");
        return 0;
    }

    public String getNumberBlockRacing(String venue, String market) {
        int rowIndex = getVenueNameIndex(venue);
        int colIndex = getMarketColIndex(market);
        Label lblNumber = Label.xpath(tblCurrentVenue.getxPathOfCell(1, colIndex, rowIndex, "span[contains(@class,'ribbon-text')]"));
        return lblNumber.getText().trim();
    }

    public boolean isLockIconDisplay(String venue, String market) {
        int rowIndex = getVenueNameIndex(venue);
        int colIndex = getMarketColIndex(market);
        return Label.xpath(tblCurrentVenue.getxPathOfCell(1, colIndex, rowIndex, "i[contains(@class,'fa-lock')]")).isDisplayed();
    }

    public void verifyUIDisplayCorrect(boolean isCurrent) {
        if(isCurrent) {
            Assert.assertEquals(lblTitle.getText(), BLOCK_RACING, "FAILED! Page title is not displayed correct");
            Assert.assertTrue(ddbSport.isDisplayed(), "FAILED! Sport dropdown does not display");
            Assert.assertTrue(tblCurrentVenue.isDisplayed(), "FAILED! Table current blocking value does not display");
            Assert.assertTrue(txtSearchVenueName.isDisplayed(), "FAILED! Venue name search textbox does not display");
            Assert.assertTrue(mnCountries.isDisplayed(), "FAILED! Menu countries does not display");
            Assert.assertTrue(mnBlockingTab.isDisplayed(), "FAILED! Current/Blocking tab does not display");
        } else {
            Assert.assertEquals(lblTitle.getText(), BLOCK_RACING, "FAILED! Page title is not displayed correct");
            Assert.assertTrue(ddbSport.isDisplayed(), "FAILED! Sport dropdown does not display");
            Assert.assertTrue(ddbSMAList.isDisplayed(), "FAILED! SMA List dropdown does not display");
            Assert.assertTrue(tblCurrentVenue.isDisplayed(), "FAILED! Table current blocking value does not display");
            Assert.assertTrue(txtSearchVenueName.isDisplayed(), "FAILED! Venue name search textbox does not display");
            Assert.assertTrue(mnCountries.isDisplayed(), "FAILED! Menu countries does not display");
            Assert.assertTrue(mnBlockingTab.isDisplayed(), "FAILED! Current/Blocking tab does not display");
            Assert.assertTrue(cbSelectAllDownline.isDisplayed(), "FAILED! Select all checkbox does not display");
            Assert.assertTrue(tblDownline.isDisplayed(), "FAILED! Downline table does not display");
            Assert.assertTrue(btnUpdate.isDisplayed(), "FAILED! Update button does not display");
        }
    }
}
