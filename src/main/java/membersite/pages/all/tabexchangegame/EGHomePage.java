package membersite.pages.all.tabexchangegame;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Menu;
import org.openqa.selenium.support.PageFactory;
import membersite.pages.all.tabexchangegame.components.MainMenu;
import membersite.pages.all.tabexchangegame.controls.GameControl;

public class EGHomePage extends MainMenu {
    public Label lblPanner = Label.xpath("//div[contains(@class,'banner')]");
    public GameControl gcBaccarat = GameControl.xpath("//div[contains(@class,'col-3')][1]//div[@class='game-menu']");
    public GameControl gcBlackjack = GameControl.xpath("//div[contains(@class,'col-3')][2]//div[@class='game-menu']");
    public GameControl gcCardRacing = GameControl.xpath("//div[contains(@class,'col-3')][3]//div[@class='game-menu']");
    public GameControl gcHilo = GameControl.xpath("//div[contains(@class,'col-3')][4]//div[@class='game-menu']");
    public GameControl gcOmahahi = GameControl.xpath("//div[contains(@class,'col-3')][5]//div[@class='game-menu']");
    public GameControl gcHolem = GameControl.xpath("//div[contains(@class,'col-3')][6]//div[@class='game-menu']");

    public <T> T navigateGameFromImage(String pageName, Class<T> expectedPage) {
        switch (pageName){
            case "BACCARAT":
                gcBaccarat.imgGame.click();
                break;
            case "BLACKJACK":
                gcBlackjack.imgGame.click();
                break;
            case "CARD DERBY RACING":
                gcCardRacing.imgGame.click();
                break;
            case "HI LO":
                gcHilo.imgGame.click();
                break;
            case "OMAHA HI":
                gcOmahahi.imgGame.click();
                break;
            case "HOLD'EM":
                gcHolem.imgGame.click();
                break;

        }
        waitGameLoad();
        return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
    }
    public <T> T navigateTurboGame(String pageName, Class<T> expectedPage) {
        switch (pageName){
            case "BACCARAT":
                gcBaccarat.btnTurbo.click();
                break;
            case "BLACKJACK":
                gcBlackjack.btnTurbo.click();
                break;
            case "CARD DERBY RACING":
                gcCardRacing.btnTurbo.click();
                break;
            case "HI LO":
                gcHilo.btnTurbo.click();
                break;
            case "OMAHA HI":
                gcOmahahi.btnTurbo.click();
                break;
            case "HOLD'EM":
                gcHolem.btnTurbo.click();
                break;

        }
        waitGameLoad();
        return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
    }
    public <T> T navigateStandarGame(String pageName, Class<T> expectedPage) {
        switch (pageName){
            case "BACCARAT":
                gcBaccarat.btnStandard.click();
                break;
            case "BLACKJACK":
                gcBlackjack.btnStandard.click();
                break;
            case "CARD DERBY RACING":
                gcCardRacing.btnStandard.click();
                break;
            case "HI LO":
                gcHilo.btnStandard.click();
                break;
            case "OMAHA HI":
                gcOmahahi.btnStandard.click();
                break;
            case "HOLD'EM":
                gcHolem.btnStandard.click();
                break;

        }
        waitGameLoad();
        return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
    }
}
