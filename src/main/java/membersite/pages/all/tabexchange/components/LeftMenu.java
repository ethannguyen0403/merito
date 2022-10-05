package membersite.pages.all.tabexchange.components;

import com.paltech.element.common.*;
import membersite.common.FEMemberConstants;
import membersite.controls.DropDownBox;
import membersite.controls.MenuTree;
import membersite.controls.aposta.APLeftMenuControl;
import membersite.controls.aposta.MenuControl;
import membersite.controls.app.SATHeaderControl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import membersite.pages.all.components.Header;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.SportPage;

import java.util.ArrayList;
import java.util.List;

public class LeftMenu extends Header {
    public APLeftMenuControl apLeftMenuControl = APLeftMenuControl.xpath("//div[@class='left-menu d-block']");
    public APLeftMenuControl satLeftMenuControl = APLeftMenuControl.xpath("//app-left-menu//div[@id='left-menu']");
   public Menu leftMenu = Menu.xpath("//div[@class='left-menu d-block']");
    public Menu menuHome = Menu.xpath("//div[contains(@class,'left-menu')]");
    public Image imgLoading = Image.xpath("//div[@class='loading-icon']/img");
    // public Menu menuSport = Menu.xpath("//div[@class='up-levels']");
    public TextBox txtSearch = TextBox.id("searchMarket");
    public Label lblResult = Label.xpath("//div[contains(@class,'completer-dropdown-holder')]//span[@class='completer-list-item']");
    public Label lblNoSearchResult = Label.xpath("//div[contains(@class,'completer-dropdown-holder')]//div[@class='completer-no-results']");
    public Image imgLeftMenu = Image.xpath("//div[@class='left-menu-icon']/img");
    private String menuSubItemsXpath = "%s/following::div[@class='downs-levels']/div";
    private String allMenuXpath = "//div[contains(@class,'up-levels')]";
    private String sportActiveXpath = "(//div[contains(@class,'up-levels')]/following::div[contains(@class,'up-levels')])[1]";
    private String competitionActiveXpath = "(//div[contains(@class,'up-levels')]/following::div[contains(@class,'up-levels')])[2]";
    private String eventActiveXpath = "(//div[contains(@class,'up-levels')]/following::div[contains(@class,'up-levels')])[3]";
    private String marketActiveXpath = "%s/following::div[@class='downs-levels']//span";
    private String marketActiveXpath1 = "%s/following::div[@class='downs-levels']//a";
    private String fancyMarketsXPath = "%s/following::div[@class='downs-levels']//a[contains(@href,'WICKET_FANCY')]";
    private DropDownBox menuAllMenu = DropDownBox.xpath(allMenuXpath, String.format(menuSubItemsXpath, allMenuXpath));
    private DropDownBox menuSport = DropDownBox.xpath(sportActiveXpath, String.format(menuSubItemsXpath, sportActiveXpath));
    private DropDownBox menuCompetition = DropDownBox.xpath(competitionActiveXpath, String.format(menuSubItemsXpath, competitionActiveXpath));
    private DropDownBox menuEvent = DropDownBox.xpath(eventActiveXpath, String.format(marketActiveXpath, eventActiveXpath));
    private DropDownBox fancyMarketMenu = DropDownBox.xpath(eventActiveXpath, String.format(fancyMarketsXPath, eventActiveXpath));
    Menu menuLeftMenu = Menu.xpath("//div[@id='left-menu']");
    Label lblSportActive= Label.xpath("//div[@id='sport-nav']");
    Label lblCompetitiontActive= Label.xpath("//div[@id='competition-nav']");
    public Label lblSportsHeader = Label.xpath("//div[@id='sport-tree']//div[@class='nav']//div[contains(@class,'header')]");
    public Label lblPopularHeader = Label.xpath("//div[@id='sport-tree']//div[@class='popular-sports']//div[contains(@class,'header')]");
    public Label lblAZHeader = Label.xpath("//div[@id='sport-tree']//div[@class='all-sports']//div[contains(@class,'header')]");
    public MenuTree popularMenu= MenuTree.xpath("//div[@id='left-menu']//div[@class='popular-sports']//ul","//li");
    public void openLeftMenuAP(){
        if(!apLeftMenuControl.isDisplayed())
            apHeaderControl.clickLeftMenu();

    }
    public void clickMenu(String menuName) {
        List<WebElement> lblMenu = Label.xpath("//div[@class='up-levels']/following::div[contains(@class,'downs-levels')]/div").getWebElements();
        for (WebElement element : lblMenu) {
            if (element.getText().contains(menuName)) {
                System.out.println(String.format("Menu item %s display in the left menu", menuName));
                element.click();
                waitMenuLoading();
                break;
            }
        }
        System.out.println(String.format("Menu item %s does NOT display in the left menu", menuName));
    }

    public List<String> getLeftMenuList() {
        List<WebElement> lblMenu = Label.xpath("//div[@class='up-levels']/following::div[contains(@class,'downs-levels')]/div").getWebElements();
        List<String> lstMenu = new ArrayList<>();
        for (WebElement element : lblMenu) {
            lstMenu.add(element.getText().trim());
        }
        return lstMenu;
    }


    public String getSportAll() {
        return menuAllMenu.getText();
    }

    public void clickSportAll() {
        menuAllMenu.click();
    }

    public void clickSport(String sportName) {
        menuAllMenu.selectByVisibleText(sportName, false);
    }

    public void clickCompetition(String competition) {
        if (menuSport.getOptionByIndex(0).equals(FEMemberConstants.HomePage.NO_EVENT_AVAILABLE)) {
            System.out.println("Sport has no competition");
            return;
        }
        menuSport.selectByVisibleText(competition, false);
        imgLoading.isInvisible(300);
    }

    public void clickSport(int sportIndex) {
        menuAllMenu.selectByIndex(sportIndex);
    }

    public void clickCompetition(int competitionIndex) {
        menuSport.selectByIndex(competitionIndex);
    }

    public void clickEvent(int eventIndex) {
        menuCompetition.selectByIndex(eventIndex);
    }

    public void clickMarket(int marketIndex) {
        Link lnk = Link.xpath(String.format("(%s)[%d]", String.format(marketActiveXpath1, eventActiveXpath), marketIndex));
        lnk.click();
        //menuEvent.selectByIndex(marketIndex);
    }

    public void clickEvent(int sportIndex, int competitionIndex, int eventIndex) {
        clickSport(sportIndex);
        clickCompetition(competitionIndex);
        clickEvent(eventIndex);
    }

    public void clickEvent(String event) {
        menuCompetition.selectByVisibleText(event, false);
        imgLoading.isInvisible(300);
    }

    public void clickMarket(String marketName) {
        Label lb = Label.xpath(String.format(marketActiveXpath1, eventActiveXpath));
        int fcMenuTotalMarket = lb.getWebElements().size();
        Label lblFancyItem;
        Link lnk;
        for (int i = 0; i < fcMenuTotalMarket; i++) {
            lblFancyItem = Label.xpath(String.format("(%s)[%d]/preceding::span[1]", String.format(marketActiveXpath1, eventActiveXpath), i + 1));
            if (lblFancyItem.getText().trim().equalsIgnoreCase(marketName)) {
                lnk = Link.xpath(String.format("(%s)[%d]", String.format(marketActiveXpath1, eventActiveXpath), i + 1));
                lnk.click();
                imgLoading.isInvisible(300);
                return;
            }
        }
        System.out.println(String.format("*** Debug: Cannot click on the market %s", marketName));
    }

    public List<String> getSports() {
        clickSportAll();
        return menuAllMenu.getOptions(false);
    }

    public List<String> getCompetitions(String sportName) {
        clickSport(sportName);
        return menuSport.getOptions(false);
    }

    public List<String> getEvents(String competitionName) {
        clickCompetition(competitionName);
        return menuCompetition.getOptions(false);
    }

    public List<String> getMarkets() {
        return menuEvent.getOptions(false);
    }

    public List<String> getFancyMarkets() {
        Label lb = Label.xpath(String.format(fancyMarketsXPath, eventActiveXpath));
        int fcMenuTotalMarket = lb.getWebElements().size();
        List<String> lstFCMarket = new ArrayList<>();
        Label lblFancyItem;
        for (int i = 0; i < fcMenuTotalMarket; i++) {
            lblFancyItem = Label.xpath(String.format("(%s)[%d]/preceding::span[1]", String.format(fancyMarketsXPath, eventActiveXpath), i + 1));
            lstFCMarket.add(lblFancyItem.getText().trim());
        }
        return lstFCMarket;
    }

    public String getActiveEvent() {
        //waitMenuLoading();
        return menuEvent.getText().trim();
    }

    public String getActiveSport() {
        waitMenuLoading();
        return menuSport.getText().trim();
    }

    public String getActiveCompetition() {
        waitMenuLoading();
        return menuCompetition.getText().trim();
    }

    public String getMarketByIndex(int index) {
        waitMenuLoading();
        Link lnk = Link.xpath(String.format("(%s)[%d]", String.format(marketActiveXpath, eventActiveXpath), index));
        return lnk.getText();
        //return menuEvent.getOptionByIndex(index);
    }

    public void expandLeftMenu() {
        if(imgLeftMenu.getAttribute("src").contains("/collapse-menu.svg")){
            imgLeftMenu.click();
        }
        imgLeftMenu.isInvisible(2);
    }

    public void collapseLeftMenu() {
        if(imgLeftMenu.getAttribute("src").contains("/menu.svg")){
            imgLeftMenu.click();
        }
        imgLeftMenu.isInvisible(2);
    }

    public void searchEvent(String eventName, boolean isClick) {
        Label lblResult = searchEvent(eventName);
        if (isClick) {
            lblResult.click();
        }
    }

    public Label searchEvent(String eventName) {
        if (txtSearch.isDisplayed()) {
            expandLeftMenu();
        }
        txtSearch.sendKeys(eventName);
        //String aa = lblResult.getAttribute("innerHTML");
        txtSearch.type(false, Keys.ARROW_DOWN);
        if (lblResult.isDisplayed()) {
            return lblResult;
        }
        return lblNoSearchResult;
    }

    public void waitMenuLoading() {
        imgLoading.waitForControlInvisible();
    }

    public MarketPage openMarketPage(String marketName){
        Label lblMarket = Label.xpath(String.format("//span[contains(@class,'marketName') and contains(text(),'%s')]",marketName));
        lblMarket.click();
        MarketPage page = new MarketPage();
        page.oddsSpinIcon.isInvisible(5);
        return new MarketPage();
    }

    public Boolean isLeftMenuDisplay(){
        menuLeftMenu.isInvisible(2);
        return menuLeftMenu.isDisplayed();
    }

    public String getSportActive(){
        return lblSportActive.getText().trim();
    }

    public List<String> getPopularSports(){
        return popularMenu.getListSubMenu();
    }

    public SportPage navigateSportPageFromLeftMenu(String sport){
        String popularSportxpath = "//div[@id='sport-tree']//div[@class='popular-sports']//ul/li";
        int n = Label.xpath(popularSportxpath).getWebElements().size();
        List<String> lstSport = new ArrayList<String>();
        for(int i =0; i< n ;i++){
            if(Label.xpath(String.format("%s[%s]/div",popularSportxpath,i+1)).getText().trim().equalsIgnoreCase(sport))
            {
                Label.xpath(String.format("%s[%s]/div",popularSportxpath,i+1)).click();
                return new SportPage();
            }
        }
        System.out.println(sport +" not display in left menu - Popuplar list");
        return null;
    }

    public List<String> getTopCompetition(){
        String popularSportxpath = "//div[@id='sport-tree']//div[@class='top-competitions']//ul/li";
        int n = Label.xpath(popularSportxpath).getWebElements().size();
        List<String> lstSport = new ArrayList<String>();
        for(int i =0; i< n ;i++){
            lstSport.add(Label.xpath(String.format("%s[%s]/div",popularSportxpath,i+1)).getText().trim());
        }
        return lstSport;
    }
    public String getCompetitionActive(){
        return lblCompetitiontActive.getText().trim();
    }

    public SportPage navigateCompetitonFromLeftMenu(String competition){
        String popularSportxpath = "//div[@id='sport-tree']//div[@class='popular-sports']//ul/li";
        int n = Label.xpath(popularSportxpath).getWebElements().size();
        List<String> lstSport = new ArrayList<String>();
        for(int i =0; i< n ;i++){
            if(Label.xpath(String.format("%s[%s]/div",popularSportxpath,i+1)).getText().trim().equalsIgnoreCase(competition))
            {
                Label.xpath(String.format("%s[%s]/div",popularSportxpath,i+1)).click();
                return new SportPage();
            }
        }
        System.out.println(competition +" not display in left menu - Popuplar list");
        return null;
    }
}


