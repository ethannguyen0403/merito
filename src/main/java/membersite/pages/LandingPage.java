package membersite.pages;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.footer.Footer;
import membersite.pages.components.header.Header;
import membersite.pages.components.loginform.LoginPopup;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;


public class LandingPage extends BasePage{
    private String _type;
    public Footer footer;
    public Header header;
    public LandingPage(String types){
        _type = types;
        footer = ComponentsFactory.footerObject(_type);
        header= ComponentsFactory.headerObject(_type);

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

}
