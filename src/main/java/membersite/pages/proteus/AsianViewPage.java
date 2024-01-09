package membersite.pages.proteus;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import controls.Table;
import membersite.controls.DropDownMenu;
import membersite.objects.proteus.ProteusEvent;

import java.util.ArrayList;
import java.util.List;

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

    public ProteusEvent getFirstEventInfo(String marketType) {
        double hdp;
        ProteusEvent proteusEvent = new ProteusEvent.Builder().build();
        proteusEvent.setEventId(Integer.valueOf(tblFirstEvent.getAttribute("eventid")));
        proteusEvent.setLeagueName(lblFirstLeague.getText().trim());
        proteusEvent.setHomeName(lblFirstEventHomeName.getText().trim());
        proteusEvent.setAwayName(lblFirstEventAwayName.getText().trim());
        if(marketType.equalsIgnoreCase("1x2")) {
            Label lblFirstSelection = Label.xpath(String.format(firstSelectionXpath, 1));
            Label lblSecondSelection = Label.xpath(String.format(secondSelectionXpath, 1));
            proteusEvent.setBtnFirstSelection(lblFirstSelection);
            proteusEvent.setBtnSecondSelection(lblSecondSelection);
            proteusEvent.setBtnThirdSelection(lblThirdSelection);
        } else if (marketType.equalsIgnoreCase("Handicap")) {
            Label lblFirstSelection = Label.xpath(String.format(firstSelectionXpath, 2));
            Label lblSecondSelection = Label.xpath(String.format(secondSelectionXpath, 2));
            Label lblFirstHDP = Label.xpath(String.format(firstHDPXpath, 1));
            proteusEvent.setBtnFirstSelection(lblFirstSelection);
            proteusEvent.setBtnSecondSelection(lblSecondSelection);
            String hdpText = lblFirstHDP.getText().trim().replaceAll("[\nu]","");
            if(hdpText.contains("-")) {
                String[] lstHdp = hdpText.split("-");
                double firstHDP = Double.parseDouble(lstHdp[0].trim());
                double secondHDP = Double.parseDouble(lstHdp[1].trim());
                hdp = 0 - ((firstHDP + secondHDP)/2);
                proteusEvent.setHDPPoint(String.valueOf(hdp));
            } else {
                proteusEvent.setHDPPoint(hdpText);
            }
        } else {
            Label lblFirstSelection = Label.xpath(String.format(firstSelectionXpath, 3));
            Label lblSecondSelection = Label.xpath(String.format(secondSelectionXpath, 3));
            Label lblFirstHDP = Label.xpath(String.format(firstHDPXpath, 2));
            proteusEvent.setBtnFirstSelection(lblFirstSelection);
            proteusEvent.setBtnSecondSelection(lblSecondSelection);
            String hdpText = lblFirstHDP.getText().trim().replaceAll("[\nu]","");
            if(hdpText.contains("-")) {
                String[] lstHdp = hdpText.split("-");
                double firstHDP = Double.parseDouble(lstHdp[0].trim());
                double secondHDP = Double.parseDouble(lstHdp[1].trim());
                hdp = 0 - ((firstHDP + secondHDP)/2);
                proteusEvent.setHDPPoint(String.valueOf(hdp));
            } else {
                proteusEvent.setHDPPoint(hdpText);
            }
        }
        return proteusEvent;
    }

    public List<Double> getListOddsFirstEvent(ProteusEvent event, String marketType) {
        List<Double> lstOdds = new ArrayList<>();
        if(marketType.equalsIgnoreCase("1x2")) {
            //workaround in case odds contains special character
            if(event.getBtnFirstSelection().getText().length() > 5) {
                lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText().substring(1,6)));
                lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText().substring(1,6)));
                lstOdds.add(Double.valueOf(event.getBtnThirdSelection().getText().substring(1,6)));
            } else {
                lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText()));
                lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText()));
                lstOdds.add(Double.valueOf(event.getBtnThirdSelection().getText()));
            }
        } else {
            if(event.getBtnFirstSelection().getText().length() > 5) {
                lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText().substring(1,6)));
                lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText().substring(1,6)));
            } else {
                lstOdds.add(Double.valueOf(event.getBtnFirstSelection().getText()));
                lstOdds.add(Double.valueOf(event.getBtnSecondSelection().getText()));
            }
        }
        return lstOdds;
    }
}
