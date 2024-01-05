package membersite.pages.proteus;

import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import controls.Table;
import membersite.pages.HomePage;

import java.util.ArrayList;
import java.util.List;

public class ProteusHomePage extends HomePage {
    private String sportHeaderMenuXpath = "//app-slider-sport//em[contains(@class,'menu-%s')]";
    private String sportLeftMenuXpath = "//app-left-menu-euro//div[contains(@class,'menu-item')]//div[text()='%s']";
    private String marketTabXpath = "//app-sport-euro//div[contains(@class,'market-group')]//button[text()='%s']";
    public Label lblView = Label.xpath("//li[contains(@class,'view-mode')]/span");
    public Button btnEarlyEuro = Button.xpath("//app-left-menu-euro//button[text()=' Early ']");
    private Button btnLiveEuro = Button.xpath("//app-left-menu-euro//button[text()=' Live ']");
    private Image imgSpinner = Image.xpath("//em[contains(@class,'fa-4x fa-spin')]");
    public Label lblSportHeader = Label.xpath("(//app-sport-euro//div[contains(@class,'sport-header')]//h3)[1]");
    private TextBox txtStake = TextBox.xpath("//app-bet-item//input[contains(@class,'stake-input')]");
    private Button btnPlaceBet = Button.xpath("//app-open-bets//button[contains(@class,'btn-place-bet')]");
    private Label lblCompetitionInfo = Label.xpath("(//app-sport-euro//app-league-euro//th[@class='opponent-column'])[1]");
    private Label lblHomeNameInfo = Label.xpath("(//app-sport-euro//app-league-euro//table[contains(@class,'odd-pages')]//tr)[1]//th[3]//div[@class='opponent']");
    private Table tblFirstLeague = Table.xpath("(//app-league-euro/table[contains(@class,'league-header')])[1]", 5);
    private Table tblFirstEvent = Table.xpath("(//app-league-euro//table[contains(@class,'odd-page')])[1]", 6);
    private Label lblFirstLeague = Label.xpath("(//app-league-euro/table[contains(@class,'league-header')])[1]//th[contains(@class,'opponent-column')]");
    private Label lblFirstEventHomeName = Label.xpath("(//app-league-euro//table[contains(@class,'odd-page')])[1]//div[@class='opponent']/div[1]");
    private Label lblFirstEventAwayName = Label.xpath("(//app-league-euro//table[contains(@class,'odd-page')])[1]//div[@class='opponent']/div[2]");
    private Label lblFirstHDP = Label.xpath("((//app-league-euro//table[contains(@class,'odd-page')])[1]//th[contains(@class,'odd-column')]//span[contains(@class,'d-lg-inline')])[1]");
    public ProteusHomePage(String types) {
        super(types);
    }

    public void switchView(String view) {
        if(lblView.getText().equalsIgnoreCase(view)) {
            lblView.click();
            waitForSpinnerLoading();
        }
    }

    public void waitForSpinnerLoading() {
        imgSpinner.waitForControlInvisible(2, 5);
    }

    public void selectSportHeaderMenu(String sportName) {
        Button btnSport = Button.xpath(String.format(sportHeaderMenuXpath, sportName));
        btnSport.click();
        waitForSpinnerLoading();
    }

    public void selectSportLeftMenu(String sportName) {
        Button btnSport = Button.xpath(String.format(sportLeftMenuXpath, sportName));
        btnSport.click();
        waitForSpinnerLoading();
    }

    public void selectMarketTypeTab(String marketType) {
        Label lblMarketType = Label.xpath(String.format(marketTabXpath, marketType));
        lblMarketType.click();
        waitForSpinnerLoading();
    }

    public List<String> getFirstEventInfo() {
        List<String> lstEventInfo = new ArrayList<>();
        lstEventInfo.add(tblFirstLeague.getAttribute("leagueid"));
        lstEventInfo.add(lblFirstLeague.getText().trim());
        lstEventInfo.add(tblFirstEvent.getAttribute("eventid"));
        lstEventInfo.add(lblFirstEventHomeName.getText().trim());
        lstEventInfo.add(lblFirstEventAwayName.getText().trim());
        if(lblFirstHDP.isDisplayed()) {
            String hdpText = lblFirstHDP.getText().trim();
            if(hdpText.contains(",")) {
                String[] lstHdp = hdpText.split(",");
                double firstHDP = Double.parseDouble(lstHdp[0].trim());
                double secondHDP = Double.parseDouble(lstHdp[1].trim());

            }
        }
        return lstEventInfo;
    }

//    public void placeBet(ProteusMarket market, String stake) {
//        market.getBtnFirstSelection().click();
//        txtStake.sendKeys(stake);
//        btnPlaceBet.click();
//        waitForSpinnerLoading();
//    }

//    public void clickOddsSelection(ProteusMarket market, int selectionIndex) {
//        switch (selectionIndex) {
//            case 1:
//                market.getBtnFirstSelection().click();
//                break;
//            case 2:
//                market.getBtnSecondSelection().click();
//                break;
//            case 3:
//                market.getBtnThirdSelection().click();
//                break;
//            default:
//                System.out.println("Invalid selection index should be from 1 to 3");
//                break;
//        }
//    }
}
