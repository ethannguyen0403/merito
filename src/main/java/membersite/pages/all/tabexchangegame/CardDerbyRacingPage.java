package membersite.pages.all.tabexchangegame;


import com.paltech.element.common.Label;

public class CardDerbyRacingPage extends GamePage {
    private Label tblHeaderTitle = Label.xpath("//app-ex-card-derby-racing//div[@class='header-name']");
    public String getGameHeaderTitle(){
        return tblHeaderTitle.getText().trim();
    }
}
