package membersite.pages.exchangegames;

import com.paltech.element.common.Label;
public class CardDerbyRacingPage extends GamePage {
    private Label tblHeaderTitle = Label.xpath("//app-ex-card-derby-racing//div[@class='header-name']");

    public CardDerbyRacingPage(String types) {
        super(types);
    }

    public String getGameHeaderTitle(){
        return tblHeaderTitle.getText().trim();
    }
}
