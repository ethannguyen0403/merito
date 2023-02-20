package membersite.pages;
import com.paltech.driver.DriverManager;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.footer.Footer;
import membersite.pages.components.header.Header;
import membersite.pages.components.leftmneu.LeftMenu;
import membersite.pages.components.loginform.LoginPopup;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;
import membersite.pages.popup.MyMarketPopup;
import org.openqa.selenium.support.PageFactory;


public class LandingPage extends BasePage{
    protected String _type;
    public Footer footer;
    public Header header;
    public LeftMenu leftMenu;
    public LandingPage(String types){
        _type = types;
        footer = ComponentsFactory.footerObject(_type);
        header= ComponentsFactory.headerObject(_type);
        leftMenu = ComponentsFactory.lefMenuObject(_type);
    }
    public HomePage login(String username, String password,boolean skipByDefault){
        header.login(username,password,skipByDefault);
        HomePage homePage = new HomePage(_type);
        homePage.closeBannerPopup();
        return new HomePage(_type);
    }

    public String loginInvalid(String username, String password){
       return header.loginInvalid(username,password);
    }

    public UnderageGamblingPopup clickLogin(){
        return  header.clickLogin();
    }

    public void clickLogo(){
        header.clickLogo();
    }

    public void waitPageLoad(){}

    public MyMarketPopup openMyMarket(){
        return header.openMyMarketPopup();
    }

    public MarketPage openMarketInMyMarketPopup(String marketName){
        MyMarketPopup myMarketPopup = new MyMarketPopup();
        if(!myMarketPopup.tbMyMarkets.isDisplayed()){
            myMarketPopup = openMyMarket();
        }
        myMarketPopup.navigateToMarket(marketName);
        DriverManager.getDriver().switchToWindow();
        return new MarketPage(_type);
    }

    public SportPage navigateSportHeaderMenu(String sportName){
        return header.navigateSportMenu(sportName,this._type);
    }

    public MyBetsPage openMyBet(){
        return header.openMyBets(this._type);
    }

}
