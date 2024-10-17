package membersite.controls;

import com.paltech.constant.StopWatch;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import controls.Table;
import membersite.objects.sat.FancyMarket;
import org.openqa.selenium.By;

import java.util.Objects;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class FancyContainerControl extends BaseElement {
    private String _xpath;
    //private static boolean isWicket;
    private int totalColumn = 5;
    private int colMarketName = 1;
    private int colMinMax = 2;
    private int colLayOdds = 3;
    private int colBackOdds = 4;
    private int colBook = 5;
    private String lblMarketNameXpath = "span[contains(@class,'market-name')][1]";
    private Label lblTitle = Label.xpath(String.format("%s//div[@class='title']", _xpath));
    private String tblXpath = "//wicket-fancy-odds//span[contains(text(),'%s')]//ancestor::table";
    private String tblCentralFancyXpath = "//central-fancy-odds//span[contains(text(),'%s')]//ancestor::table";
    private String tblFancyXpath = "//fancy-odds//span[contains(text(),'%s')]//ancestor::table";
    private String tblArtemisFancyXpath = "//app-artemis-fancy-odds//span[contains(text(),'%s')]//ancestor::table";
    private Table tblFCMarket = Table.xpath(String.format("%s//table", _xpath), totalColumn);
    private String minMaxXPath = "//div[@class='value-mm-bet']";
    private String marketRow = "//tr[contains(@class,'%s')]";// market ID
    private String oddsNoCell = "//td[contains(@class,'cell-lay fancy-odds')]";
    private String oddsYesCell = "//td[contains(@class,'cell-back fancy-odds')]";
    private String lblMarketName = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'fancy-liability')]//span[@class='market-name']";
    private String lblLiabilityValue = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'fancy-liability')]//span[@class='fancy-liability-value']";
    ;
    private String lblMinMaxValue = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'fancy-odds-frist')]//div[@class='value-mm-bet']/div";
    private String btnLay = "cell-lay fancy-odds";
    private String btnBack = "cell-back fancy-odds";
    private String lblHandicap = "(//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'cell-lay fancy-odds')]//div[contains(@class,'pending-odds')]/span)[1]";
    private String lblPayout = "(//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'cell-lay fancy-odds')]//div[contains(@class,'pending-odds')]/span)[2]";
    private String bookIcon = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'border-top border-bottom')]//i[contains(@class,'ladder-book')]";
    private String expandIcon = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'border-top border-bottom')]//i[contains(@class,'fa-chevron-ups')]";
    private String colaspeIcon = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'border-top border-bottom')]//i[contains(@class,'fa-chevron-down')]";

    public FancyContainerControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
        // Define if that market is suspended" There are no markets available!"

    }

    public static FancyContainerControl xpath(String xpathExpression) {
        return new FancyContainerControl(By.xpath(xpathExpression), xpathExpression);
    }

    public String getTitle() {
        return lblTitle.getText();
    }


    private Table getFancyMarketRow(FancyMarket fcMarket) {
        Table tblMarket;
        String xpathTable;
        xpathTable = defineFancyTableXpath(fcMarket.getMaketType());
        tblMarket = Table.xpath(String.format(xpathTable, fcMarket.getMarketName()), totalColumn);
        if (!tblMarket.isDisplayed()) {
            System.out.println(String.format("Debug: NOT found fancy market : %s in the Fancy container", fcMarket.getMarketName()));
            return null;
        }
        return tblMarket;
    }

    public void clickFancyOdds(FancyMarket fcMarket, boolean isBack) {
        Table tbl = getFancyMarketRow(fcMarket);
        Link lnk;
        if (Objects.isNull(tbl))
            return;
        if (isBack) {
            lnk = (Link) tbl.getControlOfCell(1, colBackOdds, 1, null);
        } else
            lnk = (Link) tbl.getControlOfCell(1, colLayOdds, 1, null);
        lnk.click();

    }

    public void clickArtemisFancyOdds(FancyMarket fcMarket, boolean isBack, int runnerNo) {
        Table tbl;
        Link lnk;
        if(fcMarket.getMarketName().equalsIgnoreCase("Multi Bet")) {
            tbl =Table.xpath("//app-artemis-multi-market//table", totalColumn);
            if (Objects.isNull(tbl))
                return;
            lnk = (Link) tbl.getControlOfCell(1, colBackOdds, runnerNo, null);
            lnk.click();
        } else {
            tbl = getFancyMarketRow(fcMarket);
            if (Objects.isNull(tbl))
                return;
            if (isBack) {
                lnk = (Link) tbl.getControlOfCell(1, colBackOdds, runnerNo + 1, null);
            } else
                lnk = (Link) tbl.getControlOfCell(1, colLayOdds, runnerNo + 1, null);
            lnk.click();
        }

    }

    private boolean waitSuspendLabelDisapper(Table tblMarket) {
        Label lblSuspend = Label.xpath(String.format("%s//div[contains(@class,'suspended')]/span", tblMarket.getLocator().toString().replace(" By.xpath: ", "")));
        if (!lblSuspend.isDisplayed()) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            // wait suspend lable display in 3minutes
            while (stopWatch.getElapsedTime() < 300000L) {
                if (!lblSuspend.isDisplayed()) {
                    return true;
                }
                System.out.println("Wait suspend status disappear in  " + stopWatch.getElapsedTime());
            }
        }
        return false;
    }

    public String getMinMaxOFFancyMarket(FancyMarket fcMarket) {
        Table tbl = getFancyMarketRow(fcMarket);
        if (Objects.isNull(tbl)) {
            return null;
        }
        return tbl.getControlOfCell(1, colMinMax, 1, "div[contains(@class,'value-mm-bet')]/div").getText();

    }

    public String defineFancyTableXpath(String marketType) {
        switch (marketType) {
            case "WICKET_FANCY":
                return tblXpath;
            case "CENTRAL_FANCY":
                return tblCentralFancyXpath;
            case "ARTEMIS_FANCY":
                return tblArtemisFancyXpath;
            default:
                return tblFancyXpath;
        }
    }

    public FancyMarket getFancyMarketInfo(FancyMarket fcMarket) {
        FancyMarket newFancy = fcMarket;
        Table tblMarket;
        String xpathTable;
        xpathTable = defineFancyTableXpath(fcMarket.getMaketType());
        String marketName;
        int i = 1;
        while (true) {
            tblMarket = Table.xpath(String.format(xpathTable, fcMarket.getMarketName()), totalColumn);
            if (!tblMarket.isDisplayed()) {
                System.out.println(String.format("Debug: NOT found fancy market : %s in the Fancy container", fcMarket.getMarketName()));
                return newFancy;
            }
            marketName = tblMarket.getControlOfCell(1, colMarketName, 1, lblMarketNameXpath).getText().trim();
            //remove all whitespace to handle case double space in string (e.g. 6 OVER RUNS AS-W ADV and 6 OVER RUNS AS-W  ADV) -> loop forever
            if (marketName.replaceAll("\\s", "").equalsIgnoreCase(fcMarket.getMarketName().replaceAll("\\s", ""))) {
                System.out.println(String.format("Debug: found fancy market : %s at row %d", fcMarket.getMarketName(), i));
                String minMax = tblMarket.getControlOfCell(1, colMinMax, 1, "div[contains(@class,'value-mm-bet')]/div" +
                        "").getText();
                String[] minMaxArr = minMax.split("/");
                newFancy.setMin(Integer.parseInt(minMaxArr[0].trim().replaceAll(",", "")));
                newFancy.setMmax(Integer.parseInt(minMaxArr[1].trim().replaceAll(",", "")));
                newFancy.setBtnYes((Link) tblMarket.getControlOfCell(1, colBackOdds, 1, null));
                newFancy.setBtnYes((Link) tblMarket.getControlOfCell(1, colLayOdds, 1, null));
                Link lblOdd = (Link) tblMarket.getControlOfCell(1, colBackOdds, 1, "span[1]");
                Link lblRate = (Link) tblMarket.getControlOfCell(1, colBackOdds, 1, "span[2]");
                Link lblOddLay = (Link) tblMarket.getControlOfCell(1, colLayOdds, 1, "span[1]");
                Link lblRateLay = (Link) tblMarket.getControlOfCell(1, colLayOdds, 1, "span[2]");
                Link lnkLiability = (Link) tblMarket.getControlOfCell(1, colMarketName, 1, "div[contains(@class,'liability')]//span[contains(@class,'fancy-liability-value')]");
                String rate;
//                waitSuspendLabelDisapper(tblMarket);
                if (lblOdd.isDisplayed())
                    newFancy.setOddsYes(Double.parseDouble(lblOdd.getText().trim().replaceAll(",", "")));
                if (lblRate.isDisplayed()) {
                    rate = lblRate.getText().trim().replace(":", "");
                    newFancy.setRateYes(Integer.parseInt(rate));
                }

                if (lblOddLay.isDisplayed())
                    newFancy.setOddsNo(Double.parseDouble(lblOddLay.getText().trim().replaceAll(",", "")));
                if (lblRateLay.isDisplayed()) {
                    rate = lblRateLay.getText().trim().replace(":", "");
                    newFancy.setRateNo(Integer.parseInt(rate));
                }

                if (lnkLiability.isDisplayed())
                    newFancy.setMarketLiability(Double.parseDouble(lnkLiability.getText().trim()));
                if(i==50) {
                    // prevent loop forever
                    System.out.println("DEBUG: Cannot find the market "+ newFancy.getMarketName());
                    return null;
                }
                return newFancy;
                            }
            i++;
        }
    }

    public FancyMarket getArtemisFancyRunnerMarketInfo(FancyMarket fcMarket, int runnerNo) {
        FancyMarket newFancy = fcMarket;
        Table tblMarket;
        String xpathTable;
        String marketName;
        //get market info for Artermis Single/Multi Runner
        if (!fcMarket.getMarketName().equalsIgnoreCase("Multi Bet")) {
            xpathTable = defineFancyTableXpath(fcMarket.getMaketType());
            tblMarket = Table.xpath(String.format(xpathTable, fcMarket.getMarketName()), totalColumn);
            if (!tblMarket.isDisplayed()) {
                System.out.println(String.format("Debug: NOT found fancy market : %s in the Fancy container", fcMarket.getMarketName()));
                return newFancy;
            }
            marketName = tblMarket.getControlOfCell(1, colMarketName, 1, lblMarketNameXpath).getText().trim();
            //remove all whitespace to handle case double space in string (e.g. 6 OVER RUNS AS-W ADV and 6 OVER RUNS AS-W  ADV) -> loop forever
            if (marketName.replaceAll("\\s", "").equalsIgnoreCase(fcMarket.getMarketName().replaceAll("\\s", ""))) {
                if (fcMarket.getNumberOfActiveRunner() > 1) {
                    String minMax = tblMarket.getControlOfCell(1, colMinMax, 1, null).getText();
                    String[] minMaxArr = minMax.split("/");
                    String[] minArr = minMaxArr[0].split(":");
                    String[] maxArr = minMaxArr[1].split(":");
                    newFancy.setMin(Integer.parseInt(minArr[1].trim().replaceAll(",", "")));
                    newFancy.setMmax(Integer.parseInt(maxArr[1].trim().replaceAll(",", "")));
                    newFancy.setBtnYes((Link) tblMarket.getControlOfCell(1, colBackOdds, runnerNo + 1, null));
                    newFancy.setBtnNo((Link) tblMarket.getControlOfCell(1, colLayOdds, runnerNo + 1, null));
                    newFancy.setSelection(tblMarket.getControlOfCell(1, colMarketName, runnerNo + 1, "span").getText().trim());
                    Link lblOdd = (Link) tblMarket.getControlOfCell(1, colBackOdds, runnerNo + 1, "span");
                    Link lblOddLay = (Link) tblMarket.getControlOfCell(1, colLayOdds, runnerNo + 1, "span");
                    Link lnkForecast = (Link) tblMarket.getControlOfCell(1, colMarketName, runnerNo + 1, "div[contains(@class,'artemis-liability')]//span");
                    if (lblOdd.isDisplayed()) {
                        newFancy.setOddsYes(Double.parseDouble(lblOdd.getText().trim()));
                        newFancy.setRateYes(Integer.parseInt(lblOdd.getText().trim()));
                    }
                    if (lblOddLay.isDisplayed()) {
                        newFancy.setOddsNo(Double.parseDouble(lblOddLay.getText().trim()));
                        newFancy.setRateNo(Integer.parseInt(lblOddLay.getText().trim()));
                    }
                    if (lnkForecast.isDisplayed())
                        newFancy.setMarketLiability(Double.parseDouble(lnkForecast.getText().trim()));
                    return newFancy;
                } else {
                    return getFancyMarketInfo(fcMarket);
                }
            }

        }
        //get market info for Artermis Multi Bet
        else {
            tblMarket = Table.xpath("//app-artemis-multi-market//table", totalColumn);
            if (!tblMarket.isDisplayed()) {
                System.out.println(String.format("Debug: NOT found fancy market : %s in the Fancy container", fcMarket.getMarketName()));
                return newFancy;
            }
            String minMax = tblMarket.getControlOfCell(1, colMinMax, 1, "div[@class='value-mm-bet']").getText();
            String[] minMaxArr = minMax.split("/");
            newFancy.setMin(Integer.parseInt(minMaxArr[0].trim().replaceAll(",", "")));
            newFancy.setMmax(Integer.parseInt(minMaxArr[1].trim().replaceAll(",", "")));
            newFancy.setBtnYes((Link) tblMarket.getControlOfCell(1, colBackOdds, 1, "span"));
            Link lblOdd = (Link) tblMarket.getControlOfCell(1, colBackOdds, 1, "span");
            if (lblOdd.isDisplayed()) {
                newFancy.setOddsYes(Double.parseDouble(lblOdd.getText().trim()));
                newFancy.setRateYes(Integer.parseInt(lblOdd.getText().trim()));
            }
            return newFancy;
        }
        return newFancy;
    }
}

