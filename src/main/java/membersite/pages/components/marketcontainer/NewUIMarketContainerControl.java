package membersite.pages.components.marketcontainer;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;
import com.paltech.utils.DateUtils;
import common.MemberConstants;
import membersite.controls.FancyContainerControl;
import membersite.controls.FancyContainerControlOldUI;
import membersite.controls.FancyLadderForecastControl;
import membersite.objects.sat.Event;
import membersite.objects.sat.FancyMarket;
import membersite.objects.sat.Market;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static common.MemberConstants.MATCH_ODDS_TITLE;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class NewUIMarketContainerControl extends MarketContainerControl {
    public static String _xpath;
    public Label oddsSpinIcon = Label.xpath("//div[@id='odds-content']//div[@class='snipper-content']");
    public FancyContainerControl wcFancyContainerControl = FancyContainerControl.xpath("//span[text()='Wicket Fancy']//ancestor::div[contains(@class,'fancy-container')]");
    public FancyContainerControlOldUI odlUIFancyContainerControl = FancyContainerControlOldUI.xpath("//div[@id='fair-27-fancy']");
    public FancyContainerControl centralFancyContainerControl = FancyContainerControl.xpath("//span[text()='Central Fancy']//ancestor::div[contains(@class,'fancy-container')]");
    public FancyContainerControl fancyContainerControl = FancyContainerControl.xpath("//span[text()='Fancy']//ancestor::div[contains(@class,'fancy-container')]");
    private FancyLadderForecastControl wcFancyLadderControl = FancyLadderForecastControl.xpath("//wicket-fancy-odds//div[@class='fancy-rate']");
    private FancyLadderForecastControl fancyLadderControl = FancyLadderForecastControl.xpath("//fancy-odds//div[@class='fancy-rate']");
    private FancyLadderForecastControl artemisFancyLadderControl = FancyLadderForecastControl.xpath("//app-artemis-fancy-odds//div[@class='fancy-rate']");
    private FancyLadderForecastControl centralFancyLadderControl = FancyLadderForecastControl.xpath("//central-fancy-odds//div[@class='fancy-rate']");

    private Label lblEventStarTime = Label.xpath("//div[contains(@class,'market-wrapper-info')]//div[@class='float-left']");
    private Label lblInplay = Label.xpath("//div[contains(@class,'text-inplay')]");
    private Button btnRule = Button.xpath("//span[contains(@class,'market-rules-span')]");
    private Label lblMatched = Label.xpath("//span[@class='total-matched-label']");
    private Label lblTotalSelections = Label.xpath("//span[contains(@class,'selections-count')]");
    private String xPathForeCastLable = "//div[contains(@class,'runner-name')]//span[contains(@class,'forecast-liability-container')]";
    // WWicket Fancy section
    private Label lblWcFancyMarketName = Label.xpath("//div[contains(@class,'fancy-container')]//span[@class='market-name']");
    private Label lblEventMarketName = Label.xpath("//div[contains(@class,'highlight-page market')]//div[contains(@class,'title')]");
    private Label lblSuspend = Label.xpath("//div[contains(@class,'highlight-page market')]//span[contains(@class,'suspended-label')]");
    private String lblOddContainerxPath = "//div[contains(@class,'highlight-page market')]//div[contains(@class,'table-odds')]";
    private String lblSelectionListXPath = "//div[contains(@class,'highlight-page market')]//div[contains(@class,'table-odds')]//div[contains(@class, 'market-runner')]/div";
    private String lblSelectionName = "//div[contains(@class,'runner-name')]//span[2]";
    private String lblOddListXPath = "//div[contains(@class,'cell-odds')]";
    private String lblOddItem = "//div[contains(@class,'pending-odds')]";
    private Tab tabWicketFancy = Tab.xpath(String.format("//div[contains(@class,'container-market-info')]//span[text()='%s']", MemberConstants.WICKET_FANCY_TITLE));
    private Tab tabCentralFancy = Tab.xpath(String.format("//div[contains(@class,'container-market-info')]//span[text()='%s']", MemberConstants.CENTRAL_FANCY_TITLE));
    private Tab tabManualOdds = Tab.xpath(String.format("//div[contains(@class,'container-market-info')]//span[text()='%s']", MemberConstants.CENTRAL_BOOKMAKER_TITLE));
    private Tab tabWicketBookmaker = Tab.xpath(String.format("//div[contains(@class,'container-market-info')]//span[text()='%s']", MemberConstants.WICKET_BOOKMAKER_TITLE));
    private Tab tabFancy = Tab.xpath(String.format("//div[contains(@class,'fancy-container')]//span[text()='%s']", MemberConstants.FANCY_TITLE));
    public Tab tabMarketContainer = Tab.xpath("//app-event-page//div[@class='container-market-info']");
    public List<ArrayList<String>> getUIForeCast() {
        List<ArrayList<String>> forecastList = new ArrayList<>();
        int totalSelection = getTotalSelection();
        String forecastValue;
        String selectionName;
        for (int i = 0; i < totalSelection; i++) {
            forecastValue = Label.xpath(String.format("(%s)[%d]", xPathForeCastLable, i + 1)).getText();
            selectionName = Label.xpath(String.format("%s[%s]%s", lblSelectionListXPath, i + 1, lblSelectionName)).getText();
            forecastList.add(new ArrayList<String>(Arrays.asList(selectionName, forecastValue)));
        }
        return forecastList;
    }

    public List<String> getListSelection() {
        List<String> selectionlst = new ArrayList<>();
        for (WebElement e : Label.xpath(String.format("%s%s", lblSelectionListXPath, lblSelectionName)).getWebElements()) {
            selectionlst.add(e.getText());
        }
        return selectionlst;
    }

    public boolean isLblInPlayDisplay() {
        return lblInplay.isDisplayed();
    }

	/*public List<String> calculateForecast(List<String> forecasteBeforePlaceMatched ,int placedSelection, boolean isBack, String profit, String liabilty)
	{
		Double _profit = Double.parseDouble(profit);
		Double _liability = Double.parseDouble(liabilty)*(-1);
		List<String> forecastList = new ArrayList<>();

		for(int i = 0; i< forecasteBeforePlaceMatched.size(); i++)
		{
			if(forecasteBeforePlaceMatched.get(i) == "") {
				forecastList.add("0");
			}
			Double _currentForecast = Double.parseDouble(forecasteBeforePlaceMatched.get(i));
			if(placedSelection == i+1) {
				if(isBack)
					forecastList.add(String.format("%.2f", _currentForecast + _profit));
				else
					forecastList.add(String.format("%.2f", _currentForecast + _liability));
			}
			else
			{
				if(isBack)
					forecastList.add(String.format("%.2f", _currentForecast + _liability));
				else
					forecastList.add(String.format("%.2f", _currentForecast + _profit));
			}
		}
		return forecastList;
	}
*/

    public String getEventStartTime() {
        return lblEventStarTime.getText();
    }

    public String getRuleButton() {
        return btnRule.getText();
    }

    public void clickRuleButton() {
        btnRule.click();
    }
    public void openFancyLadderForecast(FancyMarket fcMarket) {
        String xpathTableFC = String.format(fancyContainerControl.defineFancyTableXpath(fcMarket.getMaketType()), fcMarket.getMarketName());
        String xpathBody = String.format("%s%s",xpathTableFC,"/tbody/tr/td[5]//*[contains(@class,'ladder-book')]");
        Label lblLadder = Label.xpath(String.format("(%s)[1]", xpathBody));
        if (!lblLadder.isDisplayed()) {
            System.out.println(String.format("Debug: NOT found ladder forecast icon in fancy market : %s", fcMarket.getMarketName()));
        } else {
            lblLadder.click();
        }
    }

    public boolean isLadderForecastDisplay(FancyMarket fcMarket) {
        switch (fcMarket.getMaketType()) {
            case "WICKET_FANCY":
                return wcFancyLadderControl.getTitle().equalsIgnoreCase(fcMarket.getMarketName());
            case "ARTEMIS_FANCY":
                return artemisFancyLadderControl.getTitle().equalsIgnoreCase(fcMarket.getMarketName());
            case "CENTRAL_FANCY":
                return centralFancyLadderControl.getTitle().equalsIgnoreCase(fcMarket.getMarketName());
            default:
                return fancyLadderControl.getTitle().equalsIgnoreCase(fcMarket.getMarketName());
        }
    }
    public String getTotalMatched() {
        return lblMatched.getText();
    }

    private int getTotalSelection() {
        return Label.xpath(String.format("%s%s", lblSelectionListXPath, lblSelectionName)).getWebElements().size();
    }

    @Override
    public List<Label> getOddsListLabel(String marketName, int selectionIndex, boolean isBack) {
        List<Label> list = new ArrayList<>();
       // String xPathOddsList = String.format("(%s)[%d]%s", lblSelectionListXPath, selectionIndex, lblOddListXPath);
        String xPathOddsList = String.format("(//span[text()='%s']/ancestor::ul/following::div[1]//tab[contains(@class,'active')]//div[contains(@class,'market-runner')]//div[contains(@class,'market-container')])[%d]//div[contains(@class,'cell-odds')]", marketName, selectionIndex);
        int countOddsLabel = Label.xpath(xPathOddsList).getWebElements().size();
        if (isBack) {
            for (int i = countOddsLabel / 2; i > 0; i--) {
                list.add(Label.xpath(String.format("%s[%d]//div[contains(@class,'pending-odds')]",xPathOddsList, i)));
            }
        } else {
            for (int i = countOddsLabel / 2; i < countOddsLabel; i++) {
                list.add(Label.xpath(String.format("%s[%d]//div[contains(@class,'pending-odds')]",xPathOddsList, i+1)));
            }
        }
        return list;
    }

    public List<Label> getAllOddsLabelByMarket(String marketName, boolean isBack) {
        List<Label> list = new ArrayList<>();
        String xpathMarket = String.format("//app-event-page//ul[@role='tablist']//a[@role='tab']//span[text()='%s']//ancestor::tabset[contains(@customclass,'event-page')]", marketName);
        Label lblSelections = Label.xpath(xpathMarket + "//div[contains(@class,'table-odds')]//div[contains(@class, 'market-runner')]/div");
        int totalSelection = lblSelections.getWebElements().size();
        for (int i = 0; i < totalSelection; i++) {
            String xPathOddsList = String.format("(//span[text()='%s']/ancestor::ul/following::div[1]//tab[contains(@class,'active')]//div[contains(@class,'market-runner')]//div[contains(@class,'market-container')])[%d]//div[contains(@class,'cell-odds')]", marketName, i + 1);
            int countOddsLabel = Label.xpath(xPathOddsList).getWebElements().size();
            if (isBack) {
                for (int j = countOddsLabel / 2; j > 0; j--) {
                    //just get the best odds then break
                    list.add(Label.xpath(String.format("%s[%d]//div[contains(@class,'pending-odds')]",xPathOddsList, j)));
                    break;
                }
            } else {
                for (int j = countOddsLabel / 2; j < countOddsLabel; j++) {
                    //just get the best odds then break
                    list.add(Label.xpath(String.format("%s[%d]//div[contains(@class,'pending-odds')]",xPathOddsList, j+1)));
                    break;
                }
            }
        }
        return list;
    }

    public List<Label> getCellOddsListLabel(int selectionIndex, boolean isBack) {
        List<Label> list = new ArrayList<>();
        String xPathOddsList = String.format("(%s)[%d]%s", lblSelectionListXPath, selectionIndex, lblOddListXPath);
        if(Label.xpath(xPathOddsList).isDisplayed()) {
            int countOddsLabel = Label.xpath(xPathOddsList).getWebElements().size();
            if (isBack) {
                for (int i = countOddsLabel / 2; i > 0; i--) {
                    list.add(Label.xpath(String.format("(%s)[%d]", xPathOddsList, i)));
                }
            } else {
                for (int i = countOddsLabel / 2; i < countOddsLabel; i++) {
                    list.add(Label.xpath(String.format("(%s)[%d]", xPathOddsList, i + 1)));
                }
            }
            return list;
        }
        return list;
    }

    private Double getOdds(String xpath) {
        Label lblOdd = Label.xpath(xpath);
        Double oddsValue = !lblOdd.getText().isEmpty() ? Double.parseDouble(lblOdd.getText()) : 1.01;
        return oddsValue;
    }

    public String getTitle(boolean isEventName) {
        String title = getTitle();
        return lblEventMarketName.getText();
    }

    public String getTitle() {
        lblEventMarketName.isDisplayed();
        return lblEventMarketName.getText().trim();
    }

    public int getSelectionHaveMinOdds(String marketName,boolean isBack) {
        String xpathMarket = String.format("//app-event-page//ul[@role='tablist']//a[@role='tab']//span[text()='%s']//ancestor::tabset[contains(@customclass,'event-page')]", marketName);
        Label lblSelections = Label.xpath(xpathMarket + "//div[contains(@class,'table-odds')]//div[contains(@class, 'market-runner')]/div");
        int totalSelection = lblSelections.getWebElements().size();
        int index = 1;
        Double odd = 0.0;
        Double min = Double.parseDouble(getOddsListLabel(marketName,index, isBack).get(0).getText());

        // find the selection have BACK/ODDS odds is min
        for (int i = 0; i < totalSelection; i++) {

            odd = Double.parseDouble(getOddsListLabel(marketName,i + 1, isBack).get(0).getText());
            if (min > odd) {
                min = odd;
                index = i + 1;
            }
        }
        return index;
    }

    public void waitControlLoadCompletely(int second) {
        String title = lblEventMarketName.getText();
        String temp = "";
        for (int i = 0; i <= second; i++) {
            temp = lblEventMarketName.getText();
            if (!title.isEmpty() || !title.equals(temp))
                break;
            else {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i = i + 1;
        }
    }

    public Market getMarket(Event event, int selectionIndex, boolean isBack) {
        waitControlLoadCompletely(2);
        String selectionName = Label.xpath(String.format("((//span[text()='%s']/ancestor::ul/following::div[1]//tab[contains(@class,'active')]//div[contains(@class,'market-runner')]//div[contains(@class,'market-container')])[%d]//div[contains(@class,'runner-name')]//span)[2]",event.getMarketName(), selectionIndex)).getText();
        return getMarket(event, event.getMarketName(), selectionName, isBack, getOddsListLabel(event.getMarketName(),selectionIndex, isBack).get(0));
    }

    public UnderageGamblingPopup clickOdd(Market market) {
        market.getBtnOdd().click();
        return new UnderageGamblingPopup();
    }

    private Market getMarket(Event event, String marketName, String selectionName, boolean isBack, Label odds) {
        return new Market.Builder()
                .eventName(event.getEventName())
                .marketName(marketName)
                .selectionName(selectionName)
                .isBack(isBack)
                .btnOdds(odds)
                .build();
    }

    public String convertDate(String date) {
        date = date.replace("\n", " ");
        String prefit;
        if (date.contains(MemberConstants.STARTINGIN))
            prefit = MemberConstants.STARTINGIN;
        else if (date.contains(MemberConstants.STARTINGSOON))
            prefit = MemberConstants.STARTINGSOON;
        else
            prefit = date.trim().split(" ")[0];
        String returnDate = "";
        switch (prefit) {
            case MemberConstants.TODAY:
                returnDate = date.replace(MemberConstants.TODAY, String.format("%s,", DateUtils.getDate(0, "MMM dd", MemberConstants.TIMEZONE)));
                break;
            case MemberConstants.TOMORROW:
                returnDate = date.replace(MemberConstants.TOMORROW, String.format("%s,", DateUtils.getDate(1, "MMM dd", MemberConstants.TIMEZONE)));
                break;
            case MemberConstants.STARTINGIN:
                returnDate = date.replace(MemberConstants.STARTINGIN, String.format("%s,", DateUtils.getDate(0, "MMM dd", MemberConstants.TIMEZONE)));
                break;
            case MemberConstants.STARTINGSOON:
                returnDate = date.replace(MemberConstants.STARTINGSOON, String.format("%s,", DateUtils.getDate(0, "MMM dd", MemberConstants.TIMEZONE)));
                break;
            default:
                returnDate = DateUtils.convertToDate(date, "MMM dd, hh:mm").toString();
                //returnDate = String.format("%s,",DateUtils.getDate(0, "MMM dd hr:mn",Constants.TIMEZONE));
                // default statements
        }
        return returnDate;
    }

    public boolean verifyOddsIsClickable(boolean isClickable) {
        int getTotalSelection = getTotalSelection();
        for (int i = 0; i < getTotalSelection - 1; i++) {
            List<Label> lblBackOdds = getCellOddsListLabel(i + 1, true);
            List<Label> lblLayOdds = getCellOddsListLabel(i + 1, false);
            for (int j = 0; j < lblBackOdds.size() - 1; j++) {
                if(isClickable) {
                    if (lblBackOdds.get(j).getAttribute("outerHTML").contains("disable-odds")) {
                        System.out.println("Market Page - Back Odds buttons are not clickable");
                        return false;
                    }

                    if (lblLayOdds.get(j).getAttribute("outerHTML").contains("disable-odds")) {
                        System.out.println("Market Page - Lay Odds buttons are not clickable");
                        return false;
                    }
                } else {
                    if (!lblBackOdds.get(j).getAttribute("outerHTML").contains("disable-odds")) {
                        System.out.println("Market Page - Back Odds buttons are not clickable");
                        return false;
                    }

                    if (!lblLayOdds.get(j).getAttribute("outerHTML").contains("disable-odds")) {
                        System.out.println("Market Page - Lay Odds buttons are not clickable");
                        return false;
                    }
                }

            }
        }
        System.out.println("Market Page - Odds buttons are clickable");
        return true;
    }

    /**
     * get the info [Sport id, event id, market id, market type]
     *
     * @return
     */
    public List<String> getInfoFromURL() {
        List<String> infoURLLst = new ArrayList<>();
        String url = DriverManager.getDriver().getCurrentUrl();
        //https://fastg.beatus88.com/x/#/1/home/exchange/sport/market?sid=1&eid=30742993&mid=185825782&mtype=MATCH_ODDS
        url = url.split("sid=")[1];
        String temp = url.split("&", 0)[0];
        infoURLLst.add(temp);
        url = url.split("eid=")[1];
        temp = url.split("&", 0)[0];
        infoURLLst.add(temp);
        url = url.split("mid=")[1];
        temp = url.split("&", 0)[0];
        infoURLLst.add(temp);
        url = url.split("mtype=")[1];
        temp = url.split("&", 0)[0];
        infoURLLst.add(temp);
        return infoURLLst;
    }

    public void clickOdds(Market market) {
        Label lblOdds = market.getBtnOdd();
    }

    public void activeProduct(String products) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        switch (products) {
            case "Wicket Fancy":
                tabWicketFancy.click();
                return;
            case "Central Fancy":
                tabCentralFancy.click();
                return;
            case "Manual Odds":
                tabManualOdds.click();
                return;
            case "Fancy":
                tabFancy.click();
                return;
            default:
                tabWicketBookmaker.click();
                return;
        }
    }

    public FancyMarket getFancyMarketInfo(FancyMarket fcMarket) {
        switch (fcMarket.getMaketType()) {
            case "WICKET_FANCY":
                return wcFancyContainerControl.getFancyMarketInfo(fcMarket);
            case "CENTRAL_FANCY":
                return centralFancyContainerControl.getFancyMarketInfo(fcMarket);
            default:
                return fancyContainerControl.getFancyMarketInfo(fcMarket);
        }
    }

    public FancyMarket getArtemisFancyMarketInfo(FancyMarket fcMarket, int runnerNo) {
        return fancyContainerControl.getArtemisFancyRunnerMarketInfo(fcMarket, runnerNo);
    }

    public enum Status {NA, IN_PLAY, COMING}

    public boolean isMarketInfoSectionDisplayed() {
        return tabMarketContainer.isDisplayed();
    }
}
