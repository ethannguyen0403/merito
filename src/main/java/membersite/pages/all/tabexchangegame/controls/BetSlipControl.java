package membersite.pages.all.tabexchangegame.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import membersite.controls.Table;
import org.openqa.selenium.By;

public class BetSlipControl extends BaseElement {
    // e.g //div[@id='betslip-container']
    static String _xpath = "";//app-bet-slip
    public Button btnPlaceBet;
    public Button btnConfirm;
    public Button btnCancelAllSelections;
    public Label lblClickOnOdds;
    public Label lblErrorMessage;
    private String xPathDynamicOdds = "%s//div[contains(@class,'%s ng-star-inserted')]//span[contains(@class,'oddsladder')]//input";
    private String xPathDynamicStake ="%s//div[contains(@class,'%s ng-star-inserted')]//span[3]//input";
    private String xPathDynamicProfitOrLiability ="%s//div[contains(@class,'%s ng-star-inserted')]//span[contains(@class,'bet-slip-profit')]";
    private CheckBox chkConfirm;
    private Table tblStake;
    private Table tblSelectedOdds;
    private Label lblTotalLiability;
    private Label lblPleaseWaitWhilst;
    private Label lblMiddleContent;
    private Label lblNodata;


    private BetSlipControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
        lblErrorMessage = Label.xpath(String.format("%s//div[contains(@class,'bet-info error')]//span",_xpath));
        btnPlaceBet = Button.xpath(String.format("%s//button[@class='btn button-primary btn-sm' and text()='Place bets']", _xpath));
        btnConfirm = Button.xpath(String.format("%s//div[contains(@class,'betslip-bottom')]/div/span[3]/button[@class='btn button-primary btn-sm']", _xpath));
        btnCancelAllSelections = Button.xpath(String.format("%s//button[@class='btn btn-sm btn-default btn-cancel']", _xpath));
        chkConfirm = CheckBox.xpath(String.format("%s//div[contains(@class,'confirmed-check')]//input", _xpath));
        tblStake = Table.xpath(String.format("%s//div[@class='footer']//table", _xpath), 5);
        tblSelectedOdds = Table.xpath(String.format("%s//div[@id='bet-back']//table[@class='bets back']", _xpath), 4);
        lblClickOnOdds = Label.xpath(String.format("%s//p[@class='guide-text in-market']", _xpath));
        lblTotalLiability = Label.xpath(String.format("%s//div[@class='footer']//span[@class='total-liability']", _xpath));
        lblPleaseWaitWhilst = Label.xpath(String.format("%s//div[@id='betslip']//p[@class='pleasewait']", _xpath));
        lblMiddleContent = Label.id("middle-content");
        lblNodata = Label.xpath(String.format("%s//div[@id='place-bets']/p",_xpath));

    }

    public static BetSlipControl xpath(String xpathExpression) {
        return new BetSlipControl(By.xpath(xpathExpression), xpathExpression);
    }

    public void inputdata(boolean isBack,String odds, String stake){
        String type = isBack?"back":"lay";
        TextBox txtStake = TextBox.xpath(String.format(xPathDynamicStake, _xpath,type));
        TextBox txtOdds = TextBox.xpath(String.format(xPathDynamicOdds, _xpath,type));
        if(!odds.isEmpty())
            txtOdds.sendKeys(odds);
        if(!stake.isEmpty())
            txtStake.sendKeys(stake);
    }



}
