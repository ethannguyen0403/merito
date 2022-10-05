package agentsite.pages.all.report;

import com.paltech.element.common.*;
import agentsite.controls.Table;
import agentsite.pages.all.components.LeftMenu;
import agentsite.pages.all.report.components.BetListPopup;

import java.util.ArrayList;
import java.util.List;

public class AnalysisOfRunningMarketsPage extends LeftMenu {
    public Label lblNoRecord = Label.xpath("//a[contains(@class,'noRecord')]");
    public String tableXpathbySport = "//div[@id='%s']//table";
    public Table tblSportData = Table.xpath("//div[@id='Politics']//table",1);
    public String lblSportByText = "//h4//span[contains(text(),'%s')]";
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    public String competitionEventXpaht ="strong";
    public String marketXpath = "span[contains(@class,'market')]";
    public String eventStartimeXpath = "span[contains(@class,'event')]";

    public Table tblMarketInfo = Table.xpath("//table[contains(@class,'table detailodd d-table')]",9);
    public Table tblDownline = Table.xpath("//table[contains(@id,'table-1')]",5);


    public List<String> getSport(){
        List<String> lstSport = new ArrayList<>();
        String sportXpath = "//div[@id='accordions']//div[contains(@class,'panel panel-default')]";
        int sportsNumber = Label.xpath(sportXpath).getWebElements().size();

        for(int i = 0;i < sportsNumber; i++)
        {
            lstSport.add(Label.xpath(String.format("%s[%s]%s",sportXpath,i+1,"//h4//span")).getText());
        }
        return lstSport;
    }

    public void expandSport(String sport){
        Label.xpath(String.format(lblSportByText,sport)).click();
        waitingLoadingSpinner();
    }
    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(0,1);
    }

    public boolean isListEventDisplay(String sportName)
    {
        return Table.xpath(String.format(tableXpathbySport,sportName),2).isDisplayed();
    }

    public List<ArrayList<String>> getMarketInfo(String sport){
        List<ArrayList<String>> marketList = new ArrayList<>();
        List<String> rowInfo = new ArrayList<>();
        Table tbl = Table.xpath(String.format(tableXpathbySport,sport),2);
        int totalRow = tbl.getNumberOfRows(false,false);
        for(int i = 0; i< totalRow ; i++)
        {
            // get competition, event name info
            rowInfo.add( tbl.getControlOfCell(1,1,i+1,competitionEventXpaht).getText());

            // get market name info
            rowInfo.add(tbl.getControlOfCell(1,1,i+1 ,marketXpath).getText());

            // get event startime info
            rowInfo.add(tbl.getControlOfCell(1,1,i+1 ,eventStartimeXpath).getText());

            //get total bet
            rowInfo.add(tbl.getControlOfCell(1,2,i+1 ,eventStartimeXpath).getText());
            marketList.add((ArrayList<String>) rowInfo);
        }

        return marketList;
    }

    public void openMarketInfo(String sport, String event,String market){
        Table tbl = Table.xpath(String.format(tableXpathbySport,sport),2);
        int totalRow = tbl.getNumberOfRows(false,false);
        Link lnk;
        String eventCompetitionActual;
        String makerActual;
        for(int i = 0; i<totalRow ;i++)
        {
            lnk =(Link)tbl.getControlOfCell(1,1,i+1,competitionEventXpaht);
            eventCompetitionActual = lnk.getText();
            makerActual = tbl.getControlOfCell(1,1,i+1 ,marketXpath).getText();
            if(eventCompetitionActual.equals(event) && makerActual.equals(market)){
                lnk.click();
                waitingLoadingSpinner();
                return;
            }
        }

    }
    public BetListPopup openBetlist(String sport, String event,String market){
        Table tbl = Table.xpath(String.format(tableXpathbySport,sport),2);
        String eventCompetitionActual = tbl.getControlOfCell(1,1,1,competitionEventXpaht).getText();
        String makerActual = tbl.getControlOfCell(1,1,1,marketXpath).getText();
        if(eventCompetitionActual.equals(event) && makerActual.equals(market)){
            tbl.getControlOfCell(1,1,1,competitionEventXpaht).click();
        }

        return new BetListPopup();
    }



}
