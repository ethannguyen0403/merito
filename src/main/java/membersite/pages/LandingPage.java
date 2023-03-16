package membersite.pages;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.Image;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.footer.Footer;
import membersite.pages.components.header.Header;
import membersite.pages.components.leftmneu.LeftMenu;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;
import membersite.pages.popup.MyMarketPopup;

import java.util.ArrayList;
import java.util.List;


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

    public HomePage signin(String username, String password,String email, String currency, String phone){
        header.signin(username,password,email,currency,phone);
        HomePage homePage = new HomePage(_type);
//        homePage.closeBannerPopup();
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

    public void waitPageLoad(){header.waitSpinLoad();}

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

    public List<String> getListBanners(String brandType) {
        List<String> lstBannerSrc = new ArrayList<>();
        int i = 1;
        while(true) {
            String imgSrc;
            String xpathLocator;
            Image imgLocator;
            switch (brandType.toLowerCase()) {
                case "old view":
                    xpathLocator = String.format("//div[@class='carousel slide']//slide[%s]//a",i);
                    imgLocator = Image.xpath(xpathLocator);
                    if (!imgLocator.isDisplayedShort(2)){
                        return lstBannerSrc;
                    }
                    imgSrc = imgLocator.getWebElement().getCssValue("background-image");
                    imgSrc = imgSrc.split("img")[1];
                    lstBannerSrc.add(imgSrc);
                    break;
                case "new view":
                    xpathLocator = String.format("//div[@class='carousel slide']//slide[%s]//a//img",i);
                    imgLocator = Image.xpath(xpathLocator);
                    if (!imgLocator.isDisplayedShort(2)){
                        return lstBannerSrc;
                    }
                    imgSrc = imgLocator.getWebElement().getAttribute("src");
                    imgSrc = imgSrc.split("img")[1];
                    lstBannerSrc.add(imgSrc);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + brandType.toLowerCase());
            }
            i+=1;
        }
    }

}
