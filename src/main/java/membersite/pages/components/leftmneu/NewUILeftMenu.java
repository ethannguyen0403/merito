package membersite.pages.components.leftmneu;

import baseTest.BaseCaseTest;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;
import common.MemberConstants;
import membersite.controls.DropDownBox;
import membersite.controls.DropDownMenu;
import membersite.pages.EventPage;
import membersite.pages.MarketPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static agentsite.pages.HomePage.waitingLoadingSpinner;
import static common.MemberConstants.WICKET_BOOKMAKER_TITLE;

public class NewUILeftMenu extends LeftMenu {
    public Image imgLoading = Image.xpath("//div[@class='loading-icon']/img");
    public TextBox txtSearch = TextBox.xpath("//input[@role='combobox']");
    public Label lblResult = Label.xpath("//div[@role='listbox']//span");
    public Label lblNoSearchResult = Label.xpath("//div[contains(@class,'completer-dropdown-holder')]//div[@class='completer-no-results']");
    public DropDownMenu casinotMenu = DropDownMenu.xpath("//div[contains(@class,'level casino-menu')]", "//div[contains(@class,'active')]//span", "//div[contains(@class,'casino-sub-menu')]//div");
    private String menuSubItemsXpath = "%s/following::div[@class='downs-levels']/div";
    private String eventActiveXpath = "(//div[contains(@class,'up-levels')]/following::div[contains(@class,'up-levels')])[3]";
    private String marketActiveXpath = "%s/following::div[@class='downs-levels']//div[contains(@class, 'item-level4')]";
    private String sportActiveXpath = "(//div[contains(@class,'up-levels')]/following::div[contains(@class,'up-levels')])[1]";
    private String allMenuXpath = "//div[contains(@class,'up-levels')]";
    public DropDownMenu allSportMenu = DropDownMenu.xpath("//div[contains(@class,'up-levels')]", "//div[contains(@class,'active')]", String.format(menuSubItemsXpath, allMenuXpath));
    private String competitionActiveXpath = "(//div[contains(@class,'up-levels')]/following::div[contains(@class,'up-levels')])[2]";
    private String marketActiveXpath1 = "%s/following::div[@class='downs-levels']//a";
    private DropDownBox menuAllMenu = DropDownBox.xpath(allMenuXpath, String.format(menuSubItemsXpath, allMenuXpath));
    private DropDownBox menuEvent = DropDownBox.xpath(eventActiveXpath, String.format(marketActiveXpath, eventActiveXpath));
    private DropDownBox menuSport = DropDownBox.xpath(sportActiveXpath, String.format(menuSubItemsXpath, sportActiveXpath));
    private DropDownBox menuCompetition = DropDownBox.xpath(competitionActiveXpath, String.format(menuSubItemsXpath, competitionActiveXpath));
    private String fancyMarketsXPath = "%s/following::div[@class='downs-levels']//a[contains(@href,'WICKET_FANCY')]";
    private Label leftMenuLbl = Label.xpath("//div[@id='left-menu']");
    /* public MarketPage searchEvent(String eventName, boolean isClick) {
         Label lblResult = searchEvent(eventName);
         if (isClick) {
             lblResult.click();
         }
         return  new MarketPage();
     }*/
    public Label searchEvent(String eventName) {
        txtSearch.sendKeys(eventName);
        txtSearch.type(false, Keys.ARROW_DOWN);
        if (lblResult.isDisplayed()) {
            return lblResult;
        }
        return lblNoSearchResult;
    }

    public MarketPage searchEvent(String eventName, boolean isClick) {
        txtSearch.type(eventName);
        txtSearch.type(false, Keys.ARROW_DOWN);
        if (lblResult.isDisplayed()) {
            lblResult.click();
            waitingLoadingSpinner();
        }
        return new MarketPage(BaseCaseTest._brandname);
    }
    public void waitMenuLoading() {
        imgLoading.waitForControlInvisible();
    }

    public String getActiveEvent() {
        waitMenuLoading();
        return menuEvent.getText().trim();
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
                waitMenuLoading();
                return;
            }
        }
        System.out.println(String.format("*** Debug: Cannot click on the market %s", marketName));
    }

    public void clickFancyMarket(String fancyMarketType, String subMarketName) {
        Label lblExpand = Label.xpath(String.format("//span[text()='%s']//..//em[contains(@class,'fa-chevron')]", fancyMarketType));
        if (lblExpand.isDisplayed()) {
            lblExpand.scrollToThisControl(true);
            if (lblExpand.getAttribute("className").contains("down")) {
                lblExpand.click();
            }
        }
        Label lblSubMenu = Label.xpath(String.format("%s/following::div[@class='downs-levels']//span[text()='%s']", eventActiveXpath, subMarketName));
        if (lblSubMenu.getText().equalsIgnoreCase(subMarketName)) {
            lblSubMenu.scrollToThisControl(true);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lblSubMenu.doubleClick();
            imgLoading.isInvisible(300);
        }
    }

    public void clickBookmakerMarket(String bookmakerType) {
        Label lblExpand = Label.xpath(String.format("%s/following::div[@class='downs-levels']//span[text()='%s']//..//em[contains(@class,'fa-chevron')]", eventActiveXpath, WICKET_BOOKMAKER_TITLE));
        if (lblExpand.isDisplayed()) {
            lblExpand.scrollToThisControl(true);
            if (lblExpand.getAttribute("className").contains("down")) {
                lblExpand.click();
            }
        }
        Label lblSubMenu = Label.xpath(String.format("%s/following::div[@class='downs-levels']//span[text()='%s']", eventActiveXpath, bookmakerType));
        if (lblSubMenu.isDisplayed()) {
            lblSubMenu.scrollToThisControl(true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lblSubMenu.doubleClick();
            imgLoading.isInvisible(300);
        }
    }
    public void openFancyMarket(String fancyMarketType, String marketName) {
        clickFancyMarket(fancyMarketType, marketName);
        //workaround for Central Fancy, cannot click properly so need to reclick
        if(fancyMarketType.equalsIgnoreCase("Central Fancy")) {
            clickFancyMarket(fancyMarketType, marketName);
        }
    }

    public void openBookmakerMarket(String bookMakerMarketType) {
        clickBookmakerMarket(bookMakerMarketType);
    }

    public void clickCompetition(String competition) {
        if (menuSport.getOptionByIndex(0).equals(MemberConstants.HomePage.NO_EVENT_AVAILABLE)) {
            System.out.println("Sport has no competition");
            return;
        }
        menuSport.selectByVisibleText(competition, false);
        imgLoading.isInvisible(300);
    }

    @Override
    public void clickCompetition(int index) {
        menuSport.selectByIndex(index);
    }

    public void clickMenuIndex(int marketIndex) {
        Link lnk = Link.xpath(String.format("(%s)[%d]", String.format(marketActiveXpath1, eventActiveXpath), marketIndex));
        lnk.click();
    }

    public void clickSport(String sportName) {
        clickMenu(sportName);
    }

    public boolean isLeftMenuDisplay() {
        return leftMenuLbl.isDisplayed();
    }

    public String getActiveSport() {
        waitMenuLoading();
        return menuSport.getText().trim();
    }

    public void clickEvent(int eventIndex) {
        menuCompetition.selectByIndex(eventIndex);
    }

    public void clickEvent(String eventName) {
        if (menuSport.getOptionByIndex(0).equals(MemberConstants.HomePage.NO_EVENT_AVAILABLE)) {
            System.out.println("Sport has no competition");
            return;
        }
        menuSport.selectByVisibleText(eventName, false);
        imgLoading.isInvisible(300);
    }

    @Override
    public List<String> getMarketList() {
        return menuEvent.getOptions(false);
    }

    public List<String> getLeftMenuList() {
        List<WebElement> lblMenu = Label.xpath("//div[@class='up-levels']/following::div[contains(@class,'downs-levels')]/div").getWebElements();
        List<String> lstMenu = new ArrayList<>();
        for (WebElement element : lblMenu) {
            lstMenu.add(element.getText().trim());
        }
        return lstMenu;
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

  /*



    public void clickMarket(int marketIndex) {
        Link lnk = Link.xpath(String.format("(%s)[%d]", String.format(marketActiveXpath1, eventActiveXpath), marketIndex));
        lnk.click();
        //menuEvent.selectByIndex(marketIndex);
    }*/


    //////////////

   /* public void openLeftMenuAP(){
        if(!apLeftMenuControl.isDisplayed())
            apHeaderControl.clickLeftMenu();

    }*/
  /*  public void clickMenu(String menuName) {
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

   *//* public void expandLeftMenu() {
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
*//*
    public MarketPage searchEvent(String eventName, boolean isClick) {
        Label lblResult = searchEvent(eventName);
        if (isClick) {
            lblResult.click();
        }
        return  new MarketPage();
    }

   *//* public Label searchEvent(String eventName) {
        if (!txtSearch.isDisplayed()) {
            expandLeftMenu();
        }
        txtSearch.sendKeys(eventName);
        //String aa = lblResult.getAttribute("innerHTML");
        txtSearch.type(false, Keys.ARROW_DOWN);
        if (lblResult.isDisplayed()) {
            return lblResult;
        }
        return lblNoSearchResult;
    }*//*

     *//* public void waitMenuLoading() {
        imgLoading.waitForControlInvisible();
    }*//*

    public MarketPage openMarketPage(String marketName){
        Label lblMarket = Label.xpath(String.format("//span[contains(@class,'marketName') and contains(text(),'%s')]",marketName));
        lblMarket.click();
        MarketPage page = new MarketPage();
        page.oddsSpinIcon.isInvisible(5);
        return new MarketPage();
    }

   *//* public Boolean isLeftMenuDisplay(){
        menuLeftMenu.isInvisible(2);
        return menuLeftMenu.isDisplayed();
    }

    public String getSportActive(){
        return lblSportActive.getText().trim();
    }

    public List<String> getPopularSports(){
        return popularMenu.getListSubMenu();
    }*//*

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
  *//*  public String getCompetitionActive(){
        return lblCompetitiontActive.getText().trim();
    }*//*

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
    }*/
}
