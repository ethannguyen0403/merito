package membersite.pages.all.tabexchange;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import membersite.common.FEMemberConstants;
import membersite.controls.MenuTree;
import membersite.controls.aposta.APMainContentControl;
import membersite.controls.funsport.HighLightRaceControl;
import membersite.controls.funsport.MarketOddControl;
import membersite.controls.funsport.OddPageControl;
import membersite.controls.sat.*;
import membersite.objects.AccountBalance;
import membersite.objects.funsport.Odd;
import membersite.objects.sat.Event;
import membersite.pages.all.tabexchange.components.MainMenu;
import membersite.pages.all.tabexchangegame.EGHomePage;
import membersite.utils.betplacement.BetUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

//public class HomePage extends SportPage {
public class HomePage extends MainMenu {
    public enum BetType {BACK, LAY}
    public enum Sports {SOCCER, BASKETBALL, TENNIS, CRICKET,OTHER}
    public Label lblSportHighLight = Label.xpath("//div[contains(@class,'sport-highlight-content')]//h2");
    public Label lblHeadingMarketName = Label.xpath("//div[@id='middle-content']//h1");
    public APMainContentControl apHomeContainerControl = APMainContentControl.xpath("//div[contains(@class,'main-content-wrapper')]//div[@class='main-content']");
    public EventContainerControl eventContainerControl = EventContainerControl.xpath("//div[@class='container-event-info']");
    public SATEventContainerControl eventContainerControl_SAT = SATEventContainerControl.xpath("//div[@class='container-event-info']");
    public MarketOddControl marketOddControl = MarketOddControl.xpath("//div[@id='fullMarketOdds']", false);
    public SATMarketContainerControl marketContainerControl_SAT = SATMarketContainerControl.xpath("//div[contains(@class,'highlight-page market')]");
    public MarketContainerControl marketContainerControl = MarketContainerControl.xpath("//div[contains(@class,'highlight-page market')]");
    public RacingMarketControl racingMarketControl = RacingMarketControl.xpath("//app-racing-market");
    public RacingContainerControl racingContainerControl = RacingContainerControl.xpath("//div[contains(@class,'highlight-page')]/app-sport-highlight-racing");
    public FancyContainerControl wcFancyContainerControl = FancyContainerControl.xpath("//div[contains(@class,'fancy-container')]");
    public WicketBookmakerContainerControl wcBookmakerContainerControl = WicketBookmakerContainerControl.xpath("//app-wicket-bookmarker-market");
    public BetSlipControl betSlipControl = BetSlipControl.xpath("//div[contains(@class,'container-bet-slip')]");
    private Popup poupBanner = Popup.xpath("//div[@class='main-banner-popup-content']//img");
    private Button btnClose = Button.xpath("//div[@class='main-banner-popup-content']//span[@class='close']");
    public RacingContainerControl racingContainer = RacingContainerControl.xpath("//div[@class='container-event-info']");
    private Label lblNextUpRacing = Label.xpath("//app-next-racing//div[@class='mod-header']/span");
    public Label lblHorseRacing = Label.xpath("//app-next-racing//div[contains(@class,'comingup-races-content')][1]/span");
    public Label lblGreyRacing = Label.xpath("//app-next-racing//div[contains(@class,'comingup-races-content')][2]/span");
    public  String  lnkHRRaceListXpath = "(/app-next-racing//span[contains(.,'Horse Racing')]//parent::div//a";
    public  String lnkGRRaceList = "//app-next-racing//span[contains(.,'Greyhound Racing')]/parent::div//a";
    public Image imgHomeBanner = Image.xpath("//app-banner//div[@class='carousel-inner']/slide//a");
    // funsport UI
    public Label lblHeadingSportName = Label.xpath("//div[@id='sport-highlight']/div[@class='sport-highlight-content']/h2");
    public Label lblMarketName = Label.xpath("//div[@id='content-odds']//div[@class='runner-names-heading']"); // 1x2
    public Label lblTodayRacing = Label.xpath("//div[@id='today-racing']//div[@class='main-title']");
    public membersite.controls.funsport.BetSlipControl betSlipControlOldUI = membersite.controls.funsport.BetSlipControl.xpath("//div[@id='betslip-container']");
    public membersite.controls.funsport.MyBetControl myBetControloldUI = membersite.controls.funsport.MyBetControl.xpath("//div[@id='openbets']");
    public HighLightRaceControl highLightRaceControl = HighLightRaceControl.xpath("//div[@id='high-light-race']");
    public OddPageControl oddPageControl = OddPageControl.xpath("//div[@id='odds-content']", false);
    public MenuTree sportHighLightMenu = MenuTree.xpath("//div[@id='sport-highlight']//ul[@class='sport-groups']","//li");
    // end funsport UI
    public void clickOdd(Odd odd) {
        System.out.println("Debug: Click on Odd of the event: "+ odd.getEventName() +"Selection "+odd.getSelectedTeam() +"Odd Type"+odd.getOdd().getAttribute(""));
        odd.getOdd().click();
    }

    public void clickSportHighlight(String sportName){sportHighLightMenu.clickMenu(sportName);    }

    public List<String> getSportHighLightTab(){return sportHighLightMenu.getListSubMenu();    }

    public void clickEvent(Event event)    {
        String appName = BetUtils.getAppName();
        if(appName.equals("satsport")){
            eventContainerControl_SAT.clickEvent(event);
        }else
            eventContainerControl.clickEvent(event);
        waitMenuLoading();
    }

    public MarketPage clickEventName(String event)    {
        String appName = BetUtils.getAppName();
        if(appName.equals("satsport")){
            eventContainerControl.clickOnRowofEventNameSAT(event);
        }else
            eventContainerControl.clickOnRowofEventName(event);
        waitMenuLoading();
        return new MarketPage();
    }

    public void closeBannerPopup()    {
        if(isPopupBannerDisplay()){
            btnClose.click();
            isPopupBannerDisplay();
            waitMenuLoading();
        }

    }

    public AccountStatementPage openAccountStatementPage(){
        String appName = BetUtils.getAppName();
        if(appName.equals("satsport")){
            return satHeaderControl.openAccountStatement();
        }else
            return apHeaderControl.openAccountStatementPage();
    }

    public String getMarqueeMessage() {
        String announcementMessage = "";
        if(this.lblMarquee.isDisplayed()){
            announcementMessage = this.lblMarquee.getText();
        }
        return announcementMessage;
    }

    public boolean isPopupBannerDisplay(){
        return poupBanner.isDisplayed();
    }

    public String getBannerImg()
    {
        return poupBanner.getAttribute("src");
    }

    public List<String> getBannerImageUrl(){
        List<String> lstImgUrl = new ArrayList<>();
        List<WebElement> lstImg = imgHomeBanner.getWebElements();
        for (WebElement img:lstImg){
            lstImgUrl.add(img.getAttribute("style"));
        }
        return lstImgUrl;
    }

    public void openLeftMenu(){
        if(!apLeftMenuControl.isDisplayed()){
            apHeaderControl.clickLeftMenu();
        }
    }

    public SportPage activeSportInLefMenu(String sportName){
        SportPage exPage;
        openLeftMenu();
        exPage = apLeftMenuControl.clickLeftMenuItem(sportName,SportPage.class);
        return exPage;
    }

    public EGHomePage activeExchangeGame(String brand){
        EGHomePage exPage;
        if(brand.equalsIgnoreCase("aposta") || brand.equalsIgnoreCase("fair999new"))
        {
            openLeftMenu();
            exPage = apLeftMenuControl.clickLeftMenuItem(FEMemberConstants.HomePage.PRODUCTS.get("EXCH_GAMES"),EGHomePage.class);

        }
        else
            exPage = switchExchangeGameTab();
        exPage.gcHolem.isDisplayed();
        return exPage;
    }

    public EGHomePage openExchangeGame(String brand){
        EGHomePage exPage = activeExchangeGame(brand);
        DriverManager.getDriver().switchToFrame(0);
        return exPage;
    }

    public String defineErrorMessage(double stake,double minStake, double maxStake, AccountBalance accountBalance){
       // double balance = Double.valueOf(accountBalance.getBalance().replaceAll(",", ""));
        if (stake < minStake || stake > maxStake )
              return String.format(FEMemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",minStake),String.format("%,.2f",maxStake),String.format("%,.2f",stake));
        return FEMemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE;

    }

    public String defineErrorMessageOldUI(double stake,double minStake, double maxStake, AccountBalance accountBalance){
        double balance = Double.valueOf(accountBalance.getBalance().replaceAll(",", ""));
        if(stake > balance) {
            if (balance < maxStake) {
                return FEMemberConstants.BetSlip.ERROR_INSUFFICIENT_BALANCE_OLD_UI;
            }
        }
         return FEMemberConstants.BetSlip.ERROR_STAKE_NOT_VALID_MAX_BET;

    }

    public Event getEventInfo(String eventName, String brandName){
        switch (brandName){
            case "satsport":
                return eventContainerControl_SAT.getEventInfo(eventName);
            default:
                return eventContainerControl.getEventInfo(eventName);
        }
    }

}
