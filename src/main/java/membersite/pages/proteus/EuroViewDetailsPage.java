package membersite.pages.proteus;

import com.paltech.element.common.Label;
import membersite.objects.proteus.Market;
public class EuroViewDetailsPage extends ProteusHomePage {
    private Label lblLeagueName = Label.xpath("//div[@class='league-name']");
    private Label lblEventName = Label.xpath("//div[@class='event-desc']/h2");
    public EuroViewDetailsPage(String types) {
        super(types);
    }

   public void verifyPageContainsMarketInfo(Market market) {

    }

}
