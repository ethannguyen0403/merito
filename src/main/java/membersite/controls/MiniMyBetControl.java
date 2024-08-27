package membersite.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
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
    private String headerXpath;
    private String bodyXpath;
    private String bodyFancyBetSlipXpath;
    private String bodyBookmakerBetSlipXpath;

    private MiniMyBetControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
        headerXpath = String.format("%s//div[@class='row bets-header']", _xpath);
        bodyXpath = String.format("%s/div/div[@class='row']", _xpath);
        bodyFancyBetSlipXpath = String.format("%s/div//div[contains(@class,'row mx-0')]", _xpath);
        bodyBookmakerBetSlipXpath = String.format("%s/div//div[contains(@class,'mx-0 row selection')]", _xpath);
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

    //get matched bets for BM/ FC
    public List<ArrayList> getMatchBets(boolean isFancy) {
        List<ArrayList> lstWagers = new ArrayList<>();
        if (isFancy)
            colPnl = 5;
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
                for (int index = 1; index <= colPnl; index++) {

                    LblCell = Label.xpath(String.format("%s/div[%s]/div[%s]/span[%s]", bodyXpath, i, j, index));
                    if (!LblCell.isDisplayed()) {
                        break;
                    }
                    rowLst.add(LblCell.getText().trim());
                }
                LblCell = Label.xpath(String.format("%s/div[%s]/div[%s]", bodyXpath, i, j));
                boolean isBack = LblCell.getAttribute("class").contains("back");
                if(isBack) {
                    rowLst.add("BACK");
                } else {
                    rowLst.add("LAY");
                }
                lstWagers.add(rowLst);
            }

            i++;
        }
    }

    //get betslip info BM/ FC
    public List<ArrayList> getBetSlipInfo(boolean isFancy) {
        Label lblCellValue = null;
        TextBox txtCellValue;
        ArrayList rowLst = new ArrayList();
        try {
            if (isFancy) {
                for (int i = 1; i <= lblCellValue.xpath(bodyFancyBetSlipXpath).getWebElements().size(); i++) {
                    if (i % 2 != 0) {
                        //add market name
                        lblCellValue = Label.xpath(String.format("(%s)[%s]//span", bodyFancyBetSlipXpath, i));
                        rowLst.add(lblCellValue.getText().trim());
                    } else {
                        //add selection name
                        lblCellValue = Label.xpath(String.format("(%s)[%s]//strong", bodyFancyBetSlipXpath, i));
                        rowLst.add(lblCellValue.getText().trim());
                        //add odds info
                        txtCellValue = TextBox.xpath(String.format("(%s)[%s]/span[1]//input", bodyFancyBetSlipXpath, i));
                        rowLst.add(txtCellValue.getAttribute("value").trim());
                        //add stake info
                        txtCellValue = TextBox.xpath(String.format("(%s)[%s]//input[contains(@class,'stake input-betslip')]", bodyFancyBetSlipXpath, i));
                        rowLst.add(txtCellValue.getAttribute("value").trim());
                        //add liability info
                        //define xpath of back liability, if not found will get value of lay liability
                        lblCellValue = Label.xpath(String.format("(%s)[%s]//span[contains(@class,'profit-text')]", bodyFancyBetSlipXpath, i));
                        if(lblCellValue.isDisplayed()) {
                            rowLst.add(lblCellValue.getText().trim());
                        } else {
                            lblCellValue = Label.xpath(String.format("(%s)[%s]//span[contains(@class,'liability-text')]", bodyFancyBetSlipXpath, i));
                            rowLst.add(lblCellValue.getText().trim());
                        }

                    }
                }
                return rowLst;
            } else {
                for (int i = 1; i <= lblCellValue.xpath(bodyBookmakerBetSlipXpath).getWebElements().size(); i++) {
                    //add market name
                    lblCellValue = Label.xpath(String.format("(%s)[%s]//span[@class='runner-market']", bodyBookmakerBetSlipXpath, i));
                    rowLst.add(lblCellValue.getText().trim());
                    //add odds
                    txtCellValue = TextBox.xpath(String.format("(%s)[%s]//div[contains(@class,'odds-ladder')]//input", bodyBookmakerBetSlipXpath, i));
                    rowLst.add(txtCellValue.getAttribute("value").trim());
                    //add stake
                    txtCellValue = TextBox.xpath(String.format("(%s)[%s]//input[contains(@class,'input-betslip stake')]", bodyBookmakerBetSlipXpath, i));
                    rowLst.add(txtCellValue.getAttribute("value").trim());
                    //add liability
                    //define xpath of back liability, if not found will get value of lay liability
                    lblCellValue = Label.xpath(String.format("(%s)[%s]//span[contains(@class,'profit-text')]", bodyBookmakerBetSlipXpath, i));
                    if (lblCellValue.isDisplayed()) {
                        rowLst.add(lblCellValue.getText().trim());
                    } else {
                        lblCellValue = Label.xpath(String.format("(%s)[%s]//span[contains(@class,'liability-text')]", bodyBookmakerBetSlipXpath, i));
                        rowLst.add(lblCellValue.getText().trim());
                    }

                }
                return rowLst;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<ArrayList> getMatchNormalBets() {
        List<ArrayList> lstWagers = new ArrayList<>();
        int i = 1;
        Label LblCell;
        this.bodyXpath = String.format("%s/div//div[contains(@class,'row')]", _xpath);
        while (true) {
            LblCell = Label.xpath(String.format("(%s)[%s]", bodyXpath, i));
            if (!LblCell.isDisplayed())
                return lstWagers;
            //get market name
            ArrayList rowLst = new ArrayList();
            for (int j = 1; j <= colPnl; j++) {
                LblCell = Label.xpath(String.format("(%s)[%s]/span[%s]", bodyXpath, i, j));
                if (!LblCell.isDisplayed()) {
                    break;
                }
                rowLst.add(LblCell.getText().trim());
            }
            LblCell = Label.xpath(String.format("(%s)[%s]", bodyXpath, i));
            boolean isBack = LblCell.getAttribute("class").contains("back");
            if (isBack) {
                rowLst.add("BACK");
            } else {
                rowLst.add("LAY");
            }
            lstWagers.add(rowLst);
            i++;
        }
    }

    public List<ArrayList> getUnmatchNormalBets() {
        List<ArrayList> lstWagers = new ArrayList<>();
        int i = 1;
        Label LblCell;
        this._xpath = "//app-unmatched-bets";
        this.bodyXpath = String.format("%s/div//div[contains(@class,'row')][1]", _xpath);
        while (true) {
            LblCell = Label.xpath(String.format("(%s)[%s]", bodyXpath, i));
            if (!LblCell.isDisplayed())
                return lstWagers;
            //get market name
            ArrayList rowLst = new ArrayList();
            for (int j = 1; j <= colPnl; j++) {
                LblCell = Label.xpath(String.format("(%s)[%s]/span[%s]", bodyXpath, i, j));
                if (!LblCell.isDisplayed()) {
                    break;
                }
                rowLst.add(LblCell.getText().trim());
            }
            LblCell = Label.xpath(String.format("(%s)[%s]", bodyXpath, i));
            boolean isBack = LblCell.getAttribute("class").contains("back");
            if (isBack) {
                rowLst.add("BACK");
            } else {
                rowLst.add("LAY");
            }
            lstWagers.add(rowLst);
            i++;
        }
    }
}
