package membersite.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * @author isabella.huynh
 * created on 3/10/2020
 */
public class MiniMyBetControl extends BaseElement {
    private String _xpath;
    private int colMarket = 1;
    private int colOdds = 2;
    private int colStake = 3;
    private int colPnl = 4;
    private String headerXpath;//= String.format("%s//div[@class='row bets-header']", _xpath);
    private String bodyXpath;


    private MiniMyBetControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
        headerXpath = String.format("%s//div[@class='row bets-header']", _xpath);
        bodyXpath = String.format("%s/div/div[@class='row']", _xpath);
        //app-fancy-bets/div/div[@class='row']/div[1]
    }

    public static MiniMyBetControl xpath(String xpathExpression) {
        return new MiniMyBetControl(By.xpath(xpathExpression), xpathExpression);
    }

    public List<String> getHeaderRow() {
        List<String> headerLst = new ArrayList<>();
        headerLst.add(Label.xpath(String.format("%s//span[%s]", headerXpath, colMarket)).getText());
        headerLst.add(Label.xpath(String.format("%s//span[%s]", headerXpath, colOdds)).getText());
        headerLst.add(Label.xpath(String.format("%s//span[%s]", headerXpath, colStake)).getText());
        headerLst.add(Label.xpath(String.format("%s//span[%s]", headerXpath, colPnl)).getText());
        return headerLst;
    }

    public List<ArrayList> getMatchBets() {
        List<ArrayList> lstWagers = new ArrayList<>();

        if (!this.isDisplayed()) {
            System.out.println("===DEBUG! Mini My bet does NOT display!===");
            return null;
        }
        int i = 1;
        Label LblCell;
        while (true) {
            LblCell = Label.xpath(String.format("%s/div[%s]", bodyXpath, i));
            if (!LblCell.isDisplayed())
                return lstWagers;
            //get market name
            for (int j = 1; j <= 2; j++) {
                ArrayList rowLst = new ArrayList();
                LblCell = Label.xpath(String.format("%s/div[%s]/div[%s]", bodyXpath, i, j));
                if (!LblCell.isDisplayed())
                    break;
                ;
                for (int index = 1; index <= colPnl; index++) {

                    LblCell = Label.xpath(String.format("%s/div[%s]/div[%s]/span[%s]", bodyXpath, i, j, index));
                    if (!LblCell.isDisplayed()) {
                        break;
                    }
                    rowLst.add(LblCell.getText().trim());
                }
                lstWagers.add(rowLst);
            }

            i++;
        }
    }
}
