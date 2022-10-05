package membersite.controls.aposta;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import membersite.common.FEMemberConstants;
import membersite.controls.DropDownBox;
import membersite.controls.DropDownMenu;
import membersite.objects.AccountBalance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.home.LandingPage;
import membersite.pages.all.tabexchange.AccountStatementPage;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.ProfitAndLossPage;
import membersite.pages.all.tabexchange.components.popups.ChangePasswordPopup;
import membersite.pages.aposta.MyBetsPage;

import java.util.List;

public class APLeftMenuControl extends BaseElement {
    private String _xpath ;
    public Image imgLoading = Image.xpath("//div[@class='loading-icon']/img");
    public TextBox txtSearch = TextBox.id("searchMarket");
    public Label lblResult = Label.xpath("//div[contains(@class,'completer-dropdown-holder')]//span[@class='completer-list-item']");
    public Label lblNoSearchResult = Label.xpath("//div[contains(@class,'completer-dropdown-holder')]//div[@class='completer-no-results']");
    private Button btnBack = Button.xpath("//a[contains(@class,'up-menu-back-btn')]");
    private String menuSubItemsXpath = "%s/following::div[@class='downs-levels']/div";
    private String eventActiveXpath = "(//div[contains(@class,'up-levels')]/following::div[contains(@class,'up-levels')])[3]";
    private String marketActiveXpath = "%s/following::div[@class='downs-levels']//span";
    private String sportActiveXpath = "(//div[contains(@class,'up-levels')]/following::div[contains(@class,'up-levels')])[1]";
    private String allMenuXpath = "//div[contains(@class,'up-levels')]";
    private String competitionActiveXpath = "(//div[contains(@class,'up-levels')]/following::div[contains(@class,'up-levels')])[2]";
    private String marketActiveXpath1 = "%s/following::div[@class='downs-levels']//a";
    private DropDownBox menuAllMenu = DropDownBox.xpath(allMenuXpath, String.format(menuSubItemsXpath, allMenuXpath));
    private DropDownBox menuEvent = DropDownBox.xpath(eventActiveXpath, String.format(marketActiveXpath, eventActiveXpath));
    private DropDownBox menuSport = DropDownBox.xpath(sportActiveXpath, String.format(menuSubItemsXpath, sportActiveXpath));
    private DropDownBox menuCompetition = DropDownBox.xpath(competitionActiveXpath, String.format(menuSubItemsXpath, competitionActiveXpath));
    public DropDownMenu allSportMenu = DropDownMenu.xpath("//div[contains(@class,'up-levels')]","//div[contains(@class,'active')]", String.format(menuSubItemsXpath, allMenuXpath));
    public DropDownMenu casinotMenu = DropDownMenu.xpath("//div[contains(@class,'level casino-menu')]","//div[contains(@class,'active')]//span", "//div[contains(@class,'casino-sub-menu')]//div");
    private APLeftMenuControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
    }

    public static APLeftMenuControl xpath(String xpathExpression) {
        return new APLeftMenuControl(By.xpath(xpathExpression), xpathExpression);
    }

    public Label searchEvent(String eventName) {
        txtSearch.sendKeys(eventName);
        txtSearch.type(false, Keys.ARROW_DOWN);
        if (lblResult.isDisplayed()) {
            return lblResult;
        }
        return lblNoSearchResult;
    }

    public <T> T clickLeftMenuItem(String pageName, Class<T> expectedPage) {
        allSportMenu.clickSubMenu(pageName,false);
        waitMenuLoading();
        return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
    }


    public void clickBack(){
        btnBack.click();
        waitMenuLoading();
    }

    public String getActiveEvent() {
        return menuEvent.getText().trim();
    }
    public String getActiveSport() {
        waitMenuLoading();
        return menuSport.getText().trim();
    }
    public void waitMenuLoading() {
        imgLoading.waitForControlInvisible();
    }
    public void clickSport(String sportName) {
        menuAllMenu.selectByVisibleText(sportName, false);
    }
    public void clickEvent(int eventIndex) {
        menuCompetition.selectByIndex(eventIndex);
    }
    public void clickMarket(int marketIndex) {
        Link lnk = Link.xpath(String.format("(%s)[%d]", String.format(marketActiveXpath1, eventActiveXpath), marketIndex));
        lnk.click();
        //menuEvent.selectByIndex(marketIndex);
    }
}
