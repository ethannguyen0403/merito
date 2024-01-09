package membersite.pages.proteus;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import controls.Table;
import membersite.controls.DropDownMenu;
import membersite.objects.proteus.ProteusBetslip;
import membersite.objects.proteus.ProteusEvent;
import membersite.objects.proteus.ProteusMarket;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EuroViewPage extends ProteusHomePage {
    public Label lblView = Label.xpath("//li[contains(@class,'view-mode')]/span");
    public Label lblLoading = Label.xpath("//div[contains(@class,'loading-text')]/p");
    public DropDownMenu ddmOddsType = DropDownMenu.xpath("//ul[contains(@class,'control-list')]/li[2]","span[@class='mx-2 text-uppercase']","//ul[contains(@class,'sub-selections')]//li");
    private String sportHeaderMenuXpath = "//app-slider-sport//em[contains(@class,'menu-%s')]";
    private String sportLeftMenuXpath = "//app-left-menu-euro//div[contains(@class,'menu-item')]//div[text()='%s']";
    private String marketTabXpath = "//app-sport-euro//div[contains(@class,'market-group')]//button[text()='%s']";
    public Button btnEarlyEuro = Button.xpath("//app-left-menu-euro//button[text()=' Early ']");
    private Button btnLiveEuro = Button.xpath("//app-left-menu-euro//button[text()=' Live ']");
//    private Image imgSpinner = Image.xpath("//em[contains(@class,'fa-4x fa-spin')]");
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
    private Label lblFirstSelection = Label.xpath("((//app-league-euro//table[contains(@class,'odd-page')])[1]//th[contains(@class,'odd-column')])[1]");
    private Label lblSecondSelection = Label.xpath("((//app-league-euro//table[contains(@class,'odd-page')])[1]//th[contains(@class,'odd-column')])[2]");
    private Label lblThirdSelection = Label.xpath("((//app-league-euro//table[contains(@class,'odd-page')])[1]//th[contains(@class,'odd-column')])[3]");
    public EuroViewPage(String types) {
        super(types);
    }

    public void waitContentLoad(){
        lblLoading.waitForControlInvisible(2,3);
    }

    public void selectOddsType (String oddsType){
        ddmOddsType.clickSubMenu(oddsType);
        waitForSpinnerLoading();
    }

    public String getOddsType(){
        return ddmOddsType.getSelectedItem();
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

    public void selectPeriodTab(String period) {
        if (period.equalsIgnoreCase("early")) {
           btnEarlyEuro.click();
           waitForSpinnerLoading();
        } else {
            btnLiveEuro.click();
            waitForSpinnerLoading();
        }
    }

    public ProteusEvent getFirstEventInfo() {
        ProteusEvent proteusEvent = new ProteusEvent.Builder().build();
        proteusEvent.setEventId(Integer.valueOf(tblFirstEvent.getAttribute("eventid")));
        proteusEvent.setLeagueId(Integer.valueOf(tblFirstLeague.getAttribute("leagueid")));
        proteusEvent.setLeagueName(lblFirstLeague.getText().trim());
        proteusEvent.setHomeName(lblFirstEventHomeName.getText().trim());
        proteusEvent.setAwayName(lblFirstEventAwayName.getText().trim());
        proteusEvent.setBtnFirstSelection(lblFirstSelection);
        proteusEvent.setBtnSecondSelection(lblSecondSelection);
        proteusEvent.setBtnThirdSelection(lblThirdSelection);
        if(lblFirstHDP.isDisplayed()) {
            double hdp;
            String hdpText = lblFirstHDP.getText().trim().replace(" ","");
            if(hdpText.contains(",")) {
                String[] lstHdp = hdpText.split(",");
                double firstHDP = Double.parseDouble(lstHdp[0].trim());
                double secondHDP = Double.parseDouble(lstHdp[1].trim());
                hdp = (firstHDP + secondHDP)/2;
                proteusEvent.setHDPPoint(String.valueOf(hdp));
            } else {
                proteusEvent.setHDPPoint(hdpText);
            }
        }
        return proteusEvent;
    }

    public void placeBet(ProteusEvent event, String stake, boolean isSubmit) {
        if(Objects.nonNull(event)) {
            event.getBtnFirstSelection().click();
            txtStake.sendKeys(stake);
            if(isSubmit) {
                btnPlaceBet.jsClick();
                waitForSpinnerLoading();
            }
        }
    }

    public ProteusBetslip getBetSlipInfo(String eventId) {
        String betslipRootXpath = String.format("//app-open-bets//app-bet-item//div[contains(@orderid,'eventId=%s')]", eventId);
        Label lblEventName = Label.xpath(String.format("%s%s", betslipRootXpath, "//span[@class='teams-name']"));
        Label lblSummaryInfo = Label.xpath(String.format("%s%s", betslipRootXpath, "//div[contains(@class,'bet-title')]"));
        Label lblHDPPoint = Label.xpath(String.format("%s%s", betslipRootXpath, "//div[contains(@class,'fw-semibold')]"));
        Label lblOdds = Label.xpath(String.format("%s%s", betslipRootXpath, "//div[contains(@class,'odds-text')]//span"));
        Label lblStake = Label.xpath(String.format("%s%s", betslipRootXpath, "//input[contains(@class,'stake-input')]"));
        return new ProteusBetslip.Builder().eventName(lblEventName.getText().trim())
                .summaryEventInfo(lblSummaryInfo.getText().trim())
                .hdpPoint(lblHDPPoint.getText().trim())
                .odds(lblOdds.getText().trim())
                .stake(lblStake.getAttribute("value")).build();
    }

    public void verifyBetSlipInfoShowCorrect(ProteusEvent event, ProteusMarket market, String stake, String marketType, List<Double> lstOddsConvert) {
        String hdpPoint;
        String eventName = event.getHomeName() + " vs " + event.getAwayName();
        ProteusBetslip betslip = getBetSlipInfo(String.valueOf(event.getEventId()));
        Assert.assertEquals(betslip.getEventName(), eventName, String.format("FAILED! Event Name show incorrect expected %s actual %s", eventName,betslip.getEventName()));
        if(marketType.equalsIgnoreCase("Over Under")) {
            hdpPoint = "Over " + market.getFirstHDPPoint();
            Assert.assertTrue(betslip.getSummaryEventInfo().contains("Total"), String.format("FAILED! Summary Info %s does not contains market type Total", betslip.getSummaryEventInfo()));
            Assert.assertEquals(betslip.getHDPPoint(), hdpPoint, String.format("FAILED! HDP Point does not show correct expected %s actual %s", hdpPoint, event.getHomeName()));
        } else if (marketType.equalsIgnoreCase("Handicap")) {
            hdpPoint = event.getHomeName() + " " + market.getFirstHDPPoint();
            Assert.assertTrue(betslip.getSummaryEventInfo().contains(marketType), String.format("FAILED! Summary Info %s does not contains market type %s", betslip.getSummaryEventInfo(), marketType));
            Assert.assertEquals(betslip.getHDPPoint(), hdpPoint, String.format("FAILED! HDP Point does not show correct expected %s actual %s", hdpPoint, betslip.getHDPPoint()));
        } else {
            Assert.assertTrue(betslip.getSummaryEventInfo().contains(marketType), String.format("FAILED! Summary Info %s does not contains market type %s", betslip.getSummaryEventInfo(), marketType));
            Assert.assertEquals(betslip.getHDPPoint(), event.getHomeName(), String.format("FAILED! HDP Point does not show correct expected %s actual %s", event.getHomeName(), betslip.getHDPPoint()));
        }
        Assert.assertTrue(betslip.getSummaryEventInfo().contains(event.getLeagueName()), String.format("FAILED! Summary Info %s does not contains league name %s", betslip.getSummaryEventInfo(), event.getLeagueName()));
        Assert.assertEquals(betslip.getOdds(), String.valueOf(String.format("%.3f", lstOddsConvert.get(0))), String.format("FAILED! Odds does not show correct expected %s actual %s", lstOddsConvert.get(0), betslip.getOdds()));
        Assert.assertEquals(betslip.getStake(), stake, String.format("FAILED! Stake does not show correct expected %s actual %s", stake, betslip.getStake()));
    }

    public List<Double> getListOddsFirstEvent(ProteusEvent event, String marketType) {
        List<Double> lstOdds = new ArrayList<>();
        if(marketType.equalsIgnoreCase("1x2")) {
            lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText().substring(1, 6)));
            lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText().substring(1, 6)));
            lstOdds.add(Double.valueOf(event.getBtnThirdSelection().getText().substring(1, 6)));
        } else {
            lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText().substring(1, 6)));
            lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText().substring(1, 6)));
        }
        return lstOdds;
    }

}
