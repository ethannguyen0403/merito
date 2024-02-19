package membersite.pages.proteus;

import com.paltech.element.common.Label;
import com.paltech.element.common.Menu;
import membersite.objects.proteus.Market;
import membersite.objects.proteus.Odds;
import org.testng.Assert;

import java.util.Locale;

import static common.ProteusConstant.AMERICAN;

public class EuroViewDetailsPage extends EuroViewPage {
    public Label lblLeagueName = Label.xpath("//div[@class='league-name']");
    private Label lblEventName = Label.xpath("//div[@class='event-desc']/h2");
    public Menu menuTab = Menu.xpath("//div[contains(@class,'market-list')]","//div[contains(@class,'market-item')]");
    public EuroViewDetailsPage(String types) {
        super(types);
    }
    public void clickMenuTab(String menu){
        int i = 1;
        while (true){
            Label lblMenu = Label.xpath(String.format("//div[contains(@class,'market-item')][%d]", i));
            if (!lblMenu.isDisplayed())
            {
                System.out.println("Not found menu tab " +menu);
                return;
            }
            if(lblMenu.getText().trim().equalsIgnoreCase(menu))
            {
                lblMenu.click();
                return;
            }
            i = i +1;
        }

    }

   public void verifyPageContainsMarketInfo(Market market) {
        String leagueName = lblLeagueName.getText().trim();
        String eventName = lblEventName.getText().trim();
        // get the info of the market
        String expectedMarketName = String.format("%s - %s",definePeriod(market), defineMarketName(market));
        String marketXpathRoot = getXpathHasExpectedMarket(expectedMarketName);
        Assert.assertEquals(leagueName,market.getLeagueName(),"Failed! League is incorrect");
        Assert.assertEquals(eventName,market.getEventName(),"Failed! Event is incorrect");
        Assert.assertEquals(Label.xpath(marketXpathRoot).getText().trim(),expectedMarketName,"Failed! Market is incorrect");
       for (Odds o: market.getOdds()) {
           // Just handle to verify on Match 1x2 and 1st Haft 1x2 Market
           int columIndex = defineOddsColumn(market,o.getTeam());
           ////div[contains(@class,'collapse-header')][2]//following::table[1]//th[@key='1584906527|0|0|MONEYLINE|NONE|straight|malay'][3]//span[contains(@class,'readonly')]
           String homeTeam = Label.xpath(String.format("%s//following::table[1]//th[@key='%s'][%d]//span[contains(@class,'readonly')]",marketXpathRoot,market.getOddsKey(),columIndex)).getText().trim();
           String odds = Label.xpath(String.format("%s//following::table[1]//th[@key='%s'][%d]//span[contains(@class,'odds')]",marketXpathRoot,market.getOddsKey(),columIndex)).getText().trim();
           Assert.assertEquals(homeTeam, defineSelectionName(market,o.getTeam()),String.format("Failed! Team name in %s selectionis incorrect",o.getTeam()));
           String expectedOdds = String.format("@%.3f",o.getOdds());
           if(market.getOddsFormat().equalsIgnoreCase(AMERICAN)){
               expectedOdds = String.format("@%.0f",o.getOdds());
           }
           Assert.assertEquals(odds,expectedOdds,String.format("Failed! Team name in %s selection is incorrect",o.getTeam()));
           //// Other market Handicap, Over Under, Corner not handle yet

       }
    }

    /**
     * Get the xPath section index that has the expected market: example Match - 1X2
     * @param market
     * @return
     */
    private String getXpathHasExpectedMarket(String market){
        int i = 1;
        String xpath ;
        while (true){
            xpath =String.format("//div[contains(@class,'collapse-header')][%d]", i);
            Label lblMarket = Label.xpath(String.format(xpath, i));
            if (!lblMarket.isDisplayed())
            {
                System.out.println("Not found the market " + market +" in the list" );
                return "";
            }
            if(lblMarket.getText().trim().equalsIgnoreCase(market))
            {
                return xpath;
            }
            i = i +1;
        }
    }

}
