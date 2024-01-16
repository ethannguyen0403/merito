package membersite.pages.proteus;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import controls.Table;
import membersite.controls.DropDownMenu;
import membersite.objects.proteus.ProteusGeneralEvent;
import membersite.objects.proteus.ProteusTeamTotalEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AsianViewPage extends ProteusHomePage {
    public Label lblView = Label.xpath("//li[contains(@class,'view-mode')]/span");
    public Label lblLoading = Label.xpath("//div[contains(@class,'loading')]");
    public Button btnEarlyAsian =  Button.xpath("//app-left-menu-asian//button[text()=' EARLY ']");
    public Button btnTodayAsian =  Button.xpath("//app-left-menu-asian//button[text()=' TODAY ']");
    public Button btnLiveAsian =  Button.xpath("//app-left-menu-asian//button[text()=' LIVE ']");
    public DropDownMenu ddmOddsType = DropDownMenu.xpath("//app-event-filter-desktop//ul[contains(@class,'control-list')]/li[4]","span[@class='mx-2 text-capitalize']","//ul[contains(@class,'sub-selections')]//li");
    private Table tblFirstEvent = Table.xpath("(//app-league-asian//table[contains(@class,'odds-page')])[1]", 10);
    private Label lblFirstLeague = Label.xpath("(//app-league-asian//div[contains(@class,'league-name')])[1]");
    private Label lblFirstEventAwayName = Label.xpath("(//app-league-asian//div[contains(@class,'weak-team')])[1]");
    private Label lblFirstEventHomeName = Label.xpath("(//app-league-asian//div[contains(@class,'strong-team')])[1]");
    private String firstSelectionXpath = "((//app-league-asian//th[contains(@class,'odd-column')])[%s]//span[contains(@class,'odd-number')])[1]";
    private String secondSelectionXpath =  "((//app-league-asian//th[contains(@class,'odd-column')])[%s]//span[contains(@class,'odd-number')])[2]";
    private Label lblThirdSelection =  Label.xpath("((//app-league-asian//th[contains(@class,'odd-column')])[1]//span[contains(@class,'odd-number')])[3]");
    private String firstHDPXpath = "((//app-league-asian//table)[1]//div[contains(@class,'normal hdp')])[%s]";
    private Table tblMoreMarket = Table.xpath("//app-market-asian//div[@class='market-detail']/div[2]//table[contains(@class,'table market')]", 6);
    String sportLeftMenuXpath = "//app-left-menu-asian//div[contains(@class,'live-title')]//span[text()=' Sports ']//..//following-sibling::div//div[text()='%s']";
    public AsianViewPage(String types) {
        super(types);
    }

    public void waitContentLoad(){
        lblLoading.waitForControlInvisible(2,3);
    }

    public void selectPeriodTab(String period) {
        if (period.equalsIgnoreCase("early")) {
            btnEarlyAsian.click();
            waitForSpinnerLoading();
        } else if (period.equalsIgnoreCase("today")) {
            btnTodayAsian.click();
            waitForSpinnerLoading();
        } else {
            btnLiveAsian.click();
            waitForSpinnerLoading();
        }
    }

    public void selectSportLeftMenu(String sportName) {
        Button btnSport = Button.xpath(String.format(sportLeftMenuXpath, sportName));
        btnSport.click();
        waitForSpinnerLoading();
    }
    public void selectOddsType (String oddsType){
        ddmOddsType.clickSubMenu(oddsType);
        waitForSpinnerLoading();
    }

    public ProteusGeneralEvent getFirstEventInfo(String marketType) {
        double hdp;
        ProteusGeneralEvent proteusGeneralEvent = new ProteusGeneralEvent.Builder().build();
        proteusGeneralEvent.setEventId(Integer.valueOf(tblFirstEvent.getAttribute("eventid")));
        proteusGeneralEvent.setLeagueName(lblFirstLeague.getText().trim());
        proteusGeneralEvent.setHomeName(lblFirstEventHomeName.getText().trim());
        proteusGeneralEvent.setAwayName(lblFirstEventAwayName.getText().trim());
        if(marketType.equalsIgnoreCase("1x2")) {
            Label lblFirstSelection = Label.xpath(String.format(firstSelectionXpath, 1));
            Label lblSecondSelection = Label.xpath(String.format(secondSelectionXpath, 1));
            proteusGeneralEvent.setBtnFirstSelection(lblFirstSelection);
            proteusGeneralEvent.setBtnSecondSelection(lblSecondSelection);
            proteusGeneralEvent.setBtnThirdSelection(lblThirdSelection);
        } else if (marketType.equalsIgnoreCase("Handicap")) {
            Label lblFirstSelection = Label.xpath(String.format(firstSelectionXpath, 2));
            Label lblSecondSelection = Label.xpath(String.format(secondSelectionXpath, 2));
            Label lblFirstHDP = Label.xpath(String.format(firstHDPXpath, 1));
            proteusGeneralEvent.setBtnFirstSelection(lblFirstSelection);
            proteusGeneralEvent.setBtnSecondSelection(lblSecondSelection);
            String hdpText = lblFirstHDP.getText().trim().replaceAll("[\nu]","");
            if(hdpText.contains("-")) {
                String[] lstHdp = hdpText.split("-");
                double firstHDP = Double.parseDouble(lstHdp[0].trim());
                double secondHDP = Double.parseDouble(lstHdp[1].trim());
                hdp = 0 - ((firstHDP + secondHDP)/2);
                proteusGeneralEvent.setHDPPoint(String.valueOf(hdp));
            } else {
                proteusGeneralEvent.setHDPPoint(hdpText);
            }
        } else {
            Label lblFirstSelection = Label.xpath(String.format(firstSelectionXpath, 3));
            Label lblSecondSelection = Label.xpath(String.format(secondSelectionXpath, 3));
            Label lblFirstHDP = Label.xpath(String.format(firstHDPXpath, 2));
            proteusGeneralEvent.setBtnFirstSelection(lblFirstSelection);
            proteusGeneralEvent.setBtnSecondSelection(lblSecondSelection);
            String hdpText = lblFirstHDP.getText().trim().replaceAll("[\nu]","");
            if(hdpText.contains("-")) {
                String[] lstHdp = hdpText.split("-");
                double firstHDP = Double.parseDouble(lstHdp[0].trim());
                double secondHDP = Double.parseDouble(lstHdp[1].trim());
                hdp = 0 - ((firstHDP + secondHDP)/2);
                proteusGeneralEvent.setHDPPoint(String.valueOf(hdp));
            } else {
                proteusGeneralEvent.setHDPPoint(hdpText);
            }
        }
        return proteusGeneralEvent;
    }

    public List<Double> getListOddsFirstEvent(ProteusGeneralEvent event, String marketType) {
        List<Double> lstOdds = new ArrayList<>();
        if(marketType.equalsIgnoreCase("1x2")) {
            //workaround in case odds contains special character
            if(event.getBtnFirstSelection().getText().length() > 5) {
                lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText().substring(1,6)));
                lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText().substring(1,6)));
                lstOdds.add(Double.valueOf(event.getBtnThirdSelection().getText().substring(1,6)));
            } else {
                //handle for odds AM
                if(event.getBtnFirstSelection().getText().contains("−") || event.getBtnFirstSelection().getText().contains("+")) {
                    lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText().replaceAll("[−+]","")));
                    lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText().replaceAll("[−+]","")));
                    lstOdds.add(Double.valueOf(event.getBtnThirdSelection().getText().replaceAll("[−+]","")));
                } else {
                    lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText()));
                    lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText()));
                    lstOdds.add(Double.valueOf(event.getBtnThirdSelection().getText()));
                }
            }
        } else {
            if(event.getBtnFirstSelection().getText().length() > 5) {
                lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText().substring(1,6)));
                lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText().substring(1,6)));
            } else {
                //handle for odds AM
                if(event.getBtnFirstSelection().getText().contains("−") || event.getBtnFirstSelection().getText().contains("+")) {
                    lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText().replaceAll("[−+]", "")));
                    lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText().replaceAll("[−+]", "")));
                } else {
                    lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText()));
                    lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText()));
                }
            }
        }
        return lstOdds;
    }

    public ProteusTeamTotalEvent getFirstMatchTeamTotalEventInfo() {
        ProteusTeamTotalEvent proteusTeamTotalEvent = new ProteusTeamTotalEvent.Builder().build();
        proteusTeamTotalEvent.setEventId(Integer.valueOf(tblFirstEvent.getAttribute("eventid")));
        proteusTeamTotalEvent.setLeagueName(lblFirstLeague.getText().trim());
        proteusTeamTotalEvent.setHomeName(lblFirstEventHomeName.getText().trim());
        proteusTeamTotalEvent.setAwayName(lblFirstEventAwayName.getText().trim());
        ArrayList<String> lstRow = tblMoreMarket.getRow(1);
        if(Objects.nonNull(lstRow)) {
            proteusTeamTotalEvent.setHomeGoals(Double.valueOf(lstRow.get(0)));
            proteusTeamTotalEvent.setHomeOver(Double.valueOf(lstRow.get(1)));
            proteusTeamTotalEvent.setHomeUnder(Double.valueOf(lstRow.get(2)));
            proteusTeamTotalEvent.setAwayGoals(Double.valueOf(lstRow.get(3)));
            proteusTeamTotalEvent.setAwayOver(Double.valueOf(lstRow.get(4)));
            proteusTeamTotalEvent.setAwayUnder(Double.valueOf(lstRow.get(5)));
        }
        return proteusTeamTotalEvent;
    }
}
