package membersite.pages.components.highlightracecontainer;

import com.paltech.element.common.Label;
import com.paltech.element.common.Link;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Isabella.Huynh
 * created on Nov/21/2019
 */
public class OldUIHighLightRaceContainer extends HighLightRaceContainer {
    static String _xpath = "";
    private Label lblNetxRace = Label.xpath("%s//div[contains(@class,'comingup')]//h2");
    private Link lnkFirstRace = Link.xpath("%s//a[@class='clickThrough first-race']");
    private Label lblCommingUp = Label.xpath("%s//div[@class='comingup-races-content']/span");

    public String getNexRaceLabel() {
        return lblNetxRace.getText();
    }

    public List<String> getComingUpRaceInfo(int raceIndex) {
        List<String> raceInfoList = new ArrayList<>();
        Link lnlRace = Link.xpath(String.format("//div[@id='coming-up-race']//ul/li[%s]/a", raceIndex));
        raceInfoList.add(lnlRace.getText());
        raceInfoList.add(lnlRace.getAttribute("title"));
        raceInfoList.add(lnlRace.getAttribute("href"));
        raceInfoList.add(lnlRace.getAttribute("data-event-id"));
        raceInfoList.add(lnlRace.getAttribute("data-market-id"));
        raceInfoList.add(lnlRace.getAttribute("data-venue"));
        return raceInfoList;
    }
//	public RacingMarketPage clickCommingUpRace(int raceIndex){
//		Link lnlRace = Link.xpath(String.format("//div[@id='coming-up-race']//ul/li[%s]/a",raceIndex));
//		lnlRace.click();
//		return new RacingMarketPage();
//	}


}
