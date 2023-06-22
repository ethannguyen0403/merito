package agentsite.pages.report;

import agentsite.pages.HomePage;
import com.paltech.element.common.Label;

public class LastestBetsPage extends HomePage {
    public Label lblLastestBets = Label.xpath("//div[text()='Latest Bets List']");

    public LastestBetsPage(String types) {
        super(types);
    }

    public boolean isDisplayedLastestBetsPage(){
        return lblLastestBets.isDisplayed();
    }
}
