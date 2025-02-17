package membersite.pages.components.leftmneu;

import com.paltech.element.common.Label;
import membersite.pages.MarketPage;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LeftMenu {
   /* public MarketPage searchEvent(String eventName, boolean isClick) {
        Label lblResult = searchEvent(eventName);
        if (isClick) {
            lblResult.click();
        }
        return  new MarketPage();
    }*/

    public Label searchEvent(String eventName) {
        return null;
    }

    public MarketPage searchEvent(String eventName, boolean isClick) {
        return null;
    }

    public void clickBack() {
    }

    public void waitMenuLoading() {
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
        return null;
    }

    public String getActiveEvent() {
        return "";
    }

    public void clickMarket(String marketName) {
    }

    public void openFancyMarket(String fancyMarketType, String marketName) {

    }

    public void openBookmakerMarket(String bookMakerMarketType) {
    }

    public void clickMenuIndex(int index) {
    }

    public void clickCompetition(String competition) {
    }

    public void clickCompetition(int index) {
    }

    public void clickSport(String sportName) {
    }

    public boolean isLeftMenuDisplay() {
        return false;
    }

    public String getActiveSport() {
        return "";
    }

    public void clickEvent(int eventIndex) {
    }

    public void clickEvent(String eventName) {
    }

    public List<String> getMarketList(){
        return null;
    }
/*

    public String getSportAll() {
        return menuAllMenu.getText();
    }

    public void clickSportAll() {
        menuAllMenu.click();
    }



    public void clickCompetition(String competition) {
        if (menuSport.getOptionByIndex(0).equals(MemberConstants.HomePage.NO_EVENT_AVAILABLE)) {
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



    public void collapseLeftMenu() {
        if(imgLeftMenu.getAttribute("src").contains("/menu.svg")){
            imgLeftMenu.click();
        }
        imgLeftMenu.isInvisible(2);
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
    }*/
}


