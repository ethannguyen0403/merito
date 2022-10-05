package membersite.controls.aposta;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import membersite.common.FEMemberConstants;
import membersite.controls.DropDownMenu;
import membersite.controls.SlideBanner;
import membersite.objects.AccountBalance;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.home.LandingPage;
import membersite.pages.all.tabexchange.AccountStatementPage;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.ProfitAndLossPage;
import membersite.pages.all.tabexchange.components.popups.ChangePasswordPopup;
import membersite.pages.aposta.MyBetsPage;

import java.util.List;

public class APMainContentControl extends BaseElement {
    private String _xpath ;
   public SlideBanner bannerSlide = SlideBanner.xpath("//div[@class='carousel slide']","//slide//a");
    public Image imgCricket = Image.xpath("//app-banner-sport//div[contains(@class,'card-game-item')][1]//img");
    public Image imgFootball = Image.xpath("//app-banner-sport//div[contains(@class,'card-game-item')][6]//img");
    public Image imgExchangeGame = Image.xpath("//app-banner-sport//div[contains(@class,'card-game-item')][2]//img");
    public Image imgCasino = Image.xpath("//app-banner-sport//div[contains(@class,'card-game-item')][3]//img");
    public Image imgTeenPati = Image.xpath("//app-banner-sport//div[contains(@class,'card-game-item')][4]//img");
    public Image imgSlots = Image.xpath("//app-banner-sport//div[contains(@class,'card-game-item')][5]//img");
    public Image imgSuperNowa = Image.xpath("//app-banner-sport//div[contains(@class,'card-game-item')][7]//img");
    public Image imgLiveDealer = Image.xpath("//app-banner-sport//div[contains(@class,'card-game-item')][8]//img");
    private APMainContentControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
    }

    public static APMainContentControl xpath(String xpathExpression) {
        return new APMainContentControl(By.xpath(xpathExpression), xpathExpression);
    }

    public <T> T navigateMenuImage(String pageName, Class<T> expectedPage) {
        switch (pageName){
            case "CRICKET":
                imgCricket.click();
                break;
            case "SOCCER":
                imgFootball.click();
                break;
            case "EXCHANGE GAME":
                imgExchangeGame.click();
                break;
            case "SLOTS":
                imgSlots.click();
                break;
            case "CASINO":
                imgCasino.click();
                break;
            case "TEENPATI":
                imgTeenPati.click();
                break;
            case "LIVE DEALER":
                imgLiveDealer.click();
                break;
            case "SUPERNOWA":
                imgSuperNowa.click();
                break;
        }
        return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
    }

}
