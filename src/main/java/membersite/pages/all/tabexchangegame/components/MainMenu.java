package membersite.pages.all.tabexchangegame.components;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Menu;
import org.openqa.selenium.support.PageFactory;
import membersite.pages.all.components.Header;
import membersite.pages.all.tabexchangegame.BaccaratPage;
import membersite.pages.all.tabexchangegame.BlackJackPage;
import membersite.pages.all.tabexchangegame.EGHomePage;

public class MainMenu extends Header {
    public Icon icHomeMenu = Icon.xpath("//ul[@class='navbar-nav']//li[1]");
    public Menu menuBaccarat = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'baccarat')]");
    public Menu menuBlackjack = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'blackjack')]");
    public Menu menuBullseyeRoulette = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'bullseye-roulette')]");
    public Menu menuCardDerbyRacing = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'card-derby-racing')]");
    public Menu menuHiLo = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'hi-lo')]");
    public Menu menuOmahaHi = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'omaha-hi')]");
    public Menu menuHoldEm = Menu.xpath("//ul[@class='navbar-nav']//a[contains(@href,'hold-em')]");
    public Icon icSpin = Icon.xpath("//ngx-spinner[@type='ball-clip-rotate']//div[contains(@class,'overlay')]");
    public void waitGameLoad(){
        if(icSpin.isDisplayed()){
            icSpin.waitForControlInvisible(1,3);
        }
    }
    public <T> T navigateGameFromMainMenu(String pageName, Class<T> expectedPage) {
        switch (pageName){
            case "BACCARAT":
                menuBaccarat.click();
                break;
            case "BLACKJACK":
                menuBlackjack.click();
                break;
            case "CARD DERBY RACING":
                menuCardDerbyRacing.click();
                break;
            case "HI LO":
                menuHiLo.click();
                break;
            case "OMAHA HI":
                menuOmahaHi.click();
                break;
            case "HOLD'EM":
                menuHoldEm.click();
                break;
            default:
                icHomeMenu.click();
                break;
        }
        waitGameLoad();
        return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
    }

}
