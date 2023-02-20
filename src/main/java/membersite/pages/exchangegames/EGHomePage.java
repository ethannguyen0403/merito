package membersite.pages.exchangegames;

import com.paltech.element.common.Label;
import membersite.pages.all.tabexchangegame.controls.GameControl;

public class EGHomePage extends GamePage {
    public Label lblPanner = Label.xpath("//div[contains(@class,'banner')]");
    public GameControl gcBaccarat = GameControl.xpath("//div[contains(@class,'col-3')][1]//div[@class='game-menu']");
    public GameControl gcBlackjack = GameControl.xpath("//div[contains(@class,'col-3')][2]//div[@class='game-menu']");
    public GameControl gcCardRacing = GameControl.xpath("//div[contains(@class,'col-3')][3]//div[@class='game-menu']");
    public GameControl gcHilo = GameControl.xpath("//div[contains(@class,'col-3')][4]//div[@class='game-menu']");
    public GameControl gcOmahahi = GameControl.xpath("//div[contains(@class,'col-3')][5]//div[@class='game-menu']");
    public GameControl gcHolem = GameControl.xpath("//div[contains(@class,'col-3')][6]//div[@class='game-menu']");

    public EGHomePage(String types) {
        super(types);
    }

    public Object navigateGameFromImage(String pageName) {
        switch (pageName){
            case "BACCARAT":
                gcBaccarat.imgGame.click();
                waitGameLoad();
                return new BaccaratPage(this._type);
            case "BLACKJACK":
                gcBlackjack.imgGame.click();
                waitGameLoad();
                return new BlackJackPage(this._type);
            case "CARD DERBY RACING":
                gcCardRacing.imgGame.click();
                waitGameLoad();
                return new CardDerbyRacingPage(this._type);
            case "HI LO":
                gcHilo.imgGame.click();
                waitGameLoad();
                return new HiloPage(this._type);
            case "OMAHA HI":
                gcOmahahi.imgGame.click();
                waitGameLoad();
                return new OmahahiPage(this._type);
            case "HOLD'EM":
                gcHolem.imgGame.click();
                waitGameLoad();
                return new HoldemPage(this._type);
            default:
                return this;
        }
    }
    public Object navigateTurboGame(String pageName) {
        switch (pageName){
            case "BACCARAT":
                gcBaccarat.btnTurbo.click();
                waitGameLoad();
                return new BaccaratPage(this._type);
            case "BLACKJACK":
                gcBlackjack.btnTurbo.click();
                waitGameLoad();
                return new BlackJackPage(this._type);
            case "CARD DERBY RACING":
                gcCardRacing.btnTurbo.click();
                waitGameLoad();
                return new CardDerbyRacingPage(this._type);
            case "HI LO":
                gcHilo.btnTurbo.click();
                waitGameLoad();
                return new HiloPage(this._type);
            case "OMAHA HI":
                gcOmahahi.btnTurbo.click();
                waitGameLoad();
                return new OmahahiPage(this._type);
            case "HOLD'EM":
                gcHolem.btnTurbo.click();
                waitGameLoad();
                return new HoldemPage(this._type);
            default:
                return this;
        }
    }
    public Object navigateStandarGame(String pageName) {
        switch (pageName){
            case "BACCARAT":
                gcBaccarat.btnStandard.click();
                waitGameLoad();
                return new BaccaratPage(this._type);
            case "BLACKJACK":
                gcBlackjack.btnStandard.click();
                waitGameLoad();
                return new BlackJackPage(this._type);
            case "CARD DERBY RACING":
                gcCardRacing.btnStandard.click();
                waitGameLoad();
                return new CardDerbyRacingPage(this._type);
            case "HI LO":
                gcHilo.btnStandard.click();
                waitGameLoad();
                return new HiloPage(this._type);
            case "OMAHA HI":
                gcOmahahi.btnStandard.click();
                waitGameLoad();
                return new OmahahiPage(this._type);
            case "HOLD'EM":
                gcHolem.btnStandard.click();
                waitGameLoad();
                return new HoldemPage(this._type);
            default:
                return null;
        }
    }
}
