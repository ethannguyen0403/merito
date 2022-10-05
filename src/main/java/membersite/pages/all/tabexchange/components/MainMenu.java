package membersite.pages.all.tabexchange.components;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.Menu;
import membersite.controls.MenuTree;
import membersite.controls.aposta.APLeftMenuControl;
import membersite.controls.aposta.MenuControl;
import membersite.controls.app.SATHeaderControl;
import org.openqa.selenium.support.PageFactory;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.InPlayPage;
import membersite.pages.all.tabexchange.RacingPage;
import membersite.pages.all.tabexchange.SportPage;
import membersite.pages.funsport.tabexchange.*;
import membersite.pages.funsport.tabexchange.components.popups.SportsSettingPopup;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends LeftMenu {
    private String sportMenuXpath = "//a//div[contains(text(),'%s')]";
    private Menu menuSports = Menu.xpath("//app-sport-menu-bar//ul[@class='navbar-nav']//a");
    private Link lnkSportMenu = Link.xpath("//app-sport-menu-bar//ul[@class='navbar-nav']//a");
    private Label lblNoEvent = Label.xpath("//div[@class='text-center']");
    private Menu menuInPlay = Menu.id("inplay");
    private  Menu menuFavorites = Menu.xpath("//a[@id='multi-market']");
    private Menu menuActiveFavorites= Menu.xpath("//a[@id='multi-market'] and contains(@class,'active')]");
    private Menu menuCricket = Menu.xpath("//a[@sport-id='4']");
    private Menu menuSoccer = Menu.xpath("//a[@sport-id='1']");
    private Menu menuActiveSoccer = Menu.xpath("//a[@sport-id='1' and contains(@class,'active')]");
    private Menu menuActiveTennis= Menu.xpath("//a[@sport-id='2' and contains(@class,'active')]");
    private Menu menuTennis = Menu.xpath("//a[@sport-id='2']");
    private Menu menuBasketBall = Menu.xpath("//a[@sport-id='7522']");
    private Menu menuActiveBasketBall = Menu.xpath("//a[@sport-id='7522' and contains(@class,'active')]");
    Menu menuHorseRacing = Menu.xpath("//a[@sport-id='7']");
    private Menu menuSetting = Menu.id("sport-setting");
    public MenuTree mainMenu= MenuTree.xpath("//div[@id='nav-menu']//ul","//li");

    /**
     * This is open main sport menu with the corresponding page
     * @param pageName : ex: Soccer, Home, In-Play, Basketball, Cricket
     * @param expectedPage: the return page : ex memberHomePage.class, SoccerPage.class, In-PlayPage.class
     * @param <T>
     * @return
     */
    public <T> T navigateSportMenu(String pageName, Class<T> expectedPage) {
        if(pageName.equalsIgnoreCase("HOME")){
            menuSports.click();
            return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
        }
        Menu menu = Menu.xpath(String.format(sportMenuXpath, pageName));
        if(!menu.isDisplayed(5)){
            System.out.println(String.format("There is no %s menu display", pageName));
            return null ;
        }
        lblNoEvent.isDisplayedShort(3);
        menu.click();
        imgLoading.isInvisible(300);
        return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
    }

    public List<String> getMainSportsMenu()
    {
        lnkSportMenu.isPresent(5);
        List<String> menu = new ArrayList<>();
        int total = lnkSportMenu .getWebElements().size();
        String sport ="";
        for(int i = 0; i< total ; i++)
        {
              sport = lnkSportMenu.getWebElements().get(i).getText();
            if(sport=="")
            {
                menu.add("Home");
            }else {
                menu.add(sport.trim());
            }
        }
        return menu;
    }
    public HomePage navigateHomePage(){
        mainMenu.clickMenu("Home");
        return new HomePage();
    }

    public InPlayPage navigateInPlay(){
        menuInPlay.click();
        return new InPlayPage();
    }

    public SportPage navigateCricket(){
        menuCricket.click();
        return new SportPage();
    }

    public SportPage navigateSoccer(){
        menuSoccer.isDisplayed();
        menuSoccer.click();
        if (menuActiveSoccer.isInvisible(3)) {
            menuSoccer.click();
        }
        return new SportPage();
    }

    public SportPage navigateTennis(){
        menuTennis.isDisplayed();
        menuTennis.click();
        if (menuActiveTennis.isInvisible(3)) {
            menuTennis.click();
        }
        return new SportPage();
    }
    public RacingPage navigateHorseRacing(){
        menuHorseRacing.isDisplayed();
        menuHorseRacing.click();
        if (menuHorseRacing.isInvisible(3)) {
            menuHorseRacing.click();
        }
        return new RacingPage();
    }

    public SportsSettingPopup openSetting(){
        menuSetting.clickExpand();
        return new SportsSettingPopup();
    }

    public BasketBallPage navigateBasketBall(){
        menuBasketBall.click();
        if (menuActiveBasketBall.isInvisible(3)) {
            menuBasketBall.click();
        }
        return new BasketBallPage();
    }

    public FavoritePage navigateFavourite(){
        menuFavorites.click();
        if (menuActiveFavorites.isInvisible(3)) {
            menuFavorites.click();
        }
        return new FavoritePage();
    }
}
