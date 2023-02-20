package membersite.pages.exchangegames;

import com.paltech.constant.StopWatch;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Menu;
import membersite.pages.LandingPage;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;

public class GamePage extends LandingPage {
    private Label tblHeaderTitle = Label.xpath("//app-game-header//table[@class='head-table']//tr[1]/td");
    public Label lblGameStatus = Label.xpath("//div[contains(@class,'game-status')]//span[@class='mx-auto']");
    public Icon icHomeMenu = Icon.xpath("//ul[@class='navbar-nav']//li[1]");
    public Menu menuBaccarat = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'baccarat')]");
    public Menu menuBlackjack = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'blackjack')]");
    public Menu menuBullseyeRoulette = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'bullseye-roulette')]");
    public Menu menuCardDerbyRacing = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'card-derby-racing')]");
    public Menu menuHiLo = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'hi-lo')]");
    public Menu menuOmahaHi = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'omaha-hi')]");
    public Menu menuHoldEm = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'hold-em')]");
    public Icon icSpin = Icon.xpath("//ngx-spinner[@type='ball-clip-rotate']//div[contains(@class,'overlay')]");
    public GamePage(String types) {
        super(types);
    }
    public void waitGameLoad(){
        if(icSpin.isDisplayed()){
            icSpin.waitForControlInvisible(1,3);
        }
    }
    public Object navigateGameFromMainMenu(String pageName) {
        switch (pageName){
            case "BACCARAT":
                menuBaccarat.click();
                waitGameLoad();
                return new BaccaratPage(this._type);
            case "BLACKJACK":
                menuBlackjack.click();
                waitGameLoad();
                return new BlackJackPage(this._type);
            case "CARD DERBY RACING":
                menuCardDerbyRacing.click();
                waitGameLoad();
                return new CardDerbyRacingPage(this._type);
            case "HI LO":
                menuHiLo.click();
                waitGameLoad();
                return new HiloPage(this._type);
            case "OMAHA HI":
                menuOmahaHi.click();
                waitGameLoad();
                return new OmahahiPage(this._type);
            case "HOLD'EM":
                menuHoldEm.click();
                waitGameLoad();
                return new HoldemPage(this._type);
            default:
                icHomeMenu.click();
                waitGameLoad();
                return new EGHomePage(this._type);
        }

    }


    public String getGameHeaderTitle(){
        return tblHeaderTitle.getText().trim();
    }

    public void waitUntilGameAvailable(){

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        while(stopWatch.getElapsedTime() < 3000L) {
            try {
                if(!lblGameStatus.isDisplayed()){
                    return;
                }
                System.out.println("Bet Slip waiting status disable in "+ stopWatch.getElapsedTime());
                //lblGameStatus.isInvisible(1);
            } catch (Exception var4) {

            }
        }

    }
}
