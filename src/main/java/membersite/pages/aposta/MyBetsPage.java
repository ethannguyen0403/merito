package membersite.pages.aposta;
import com.paltech.element.common.*;
import common.MemberConstants;
import controls.DateTimePicker;
import membersite.controls.DropDownMenu;
import controls.Table;
import membersite.pages.all.components.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyBetsPage extends Header {
    public RadioButton rbSettled = RadioButton.id("SETTLED");
    public RadioButton rbMatched = RadioButton.id("MATCHED");
    public RadioButton rbSUnmatched = RadioButton.id("UNMATCHED");
    public RadioButton rbVoided = RadioButton.id("VOIDED");
    public RadioButton rbLapsed = RadioButton.id("LAPSED");
    public RadioButton rbCancelled = RadioButton.id("CANCELLED");
    public Label lblSettled = Label.xpath("//label[@for='SETTLED']");
    public Label lblMatched = Label.xpath("//label[@for='MATCHED']");
    public Label lblSUnmatched = Label.xpath("//label[@for='UNMATCHED']");
    public Label lblVoided = Label.xpath("//label[@for='VOIDED']");
    public Label lblLapsed = Label.xpath("//label[@for='LAPSED']");
    public Label lblCancelled = Label.xpath("//label[@for='CANCELLED']");
    public Label lblNote = Label.xpath("//div[@class='container-title row']/div/span");
    public Button btnLoadReport = Button.xpath("//button[contains(@class,' btn-report-search')]");
    public Button btnPnL = Button.xpath("//button[contains(@class,' btn-pnl')]");
    private int colTotal = 13;
    public int colMarketName = 1;
    public int colSelection = 2;
    public int colType = 3;
    public int colOdd = 4;
    public int colTurnover = 5;
    public int colProfitLoss = 6;
    public int colTax = 7;
    public int colStatus = 8;
    public int colPlacedDate = 9;
    public int colSettledDate = 10;
    public int colBetID = 11;
    public int colEventID = 12;
    public int colIPAddress = 13;

    public DropDownMenu menuProduct = DropDownMenu.xpath("//div[contains(@class,'product-tab')]","","//ul[contains(@class,'nav nav-tabs')]//li");
    public Table tblReport = Table.xpath("//table[@class='table table-sm']",colTotal);
    public Link lnkPagination = Link.xpath("//ul[@class='pagination']//li");
    public Label lblNoRecord = Label.xpath("//table[@class='table table-sm']/tbody/tr[1]/td[@class='text-center']");

    public TextBox txtDateRange = TextBox.xpath("//input[contains(@class,'txt-event-date')]");
    public DateTimePicker dtpStartDate = DateTimePicker.xpath(txtDateRange,"//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");

    public Label lblProductTile = Label.xpath("//div[contains(@class,'container-title')]//div[contains(@class,'font-weight-bold title')]");

    public void filter(String productName, String orderType){
        filter(productName, orderType,"", "");

    }
    public void filter(String productName, String orderType, String startDate, String endDate)
    {
        menuProduct.clickSubMenu(productName);

        selectOrderType(orderType);

        if(!startDate.isEmpty()){
            dtpStartDate.selectDate(startDate,"yyyy-MM-dd");
        }
        if(!endDate.isEmpty()){
            dtpStartDate.selectDate(endDate,"yyyy-MM-dd");
        }
        btnLoadReport.click();
        btnLoadReport.isTextDisplayed(MemberConstants.MyBetsPage.LOAD_REPORT,10);
    }

    public void selectOrderType(String orderType){
        switch (orderType.toUpperCase()){
            case "SETTLED":
               rbSettled.click();
                return;
            case "MATCHED":
              rbMatched.click();
                return;
            case "UNMATCHED":
                rbSUnmatched.click();
                return;
            case "CANCELLED":
                rbCancelled.click();
                return;
            case "VOIDED":
                rbVoided.click();
                return;
            case "LAPSED":
                rbLapsed.click();
                return;
        }
    }


    public boolean validateFilterStatus(String status)
    {
        List<String> lst = tblReport.getColumn("/tbody[%s]//",colStatus,false);
        for (String sts:lst){
            if(status.equalsIgnoreCase("Settled"))
            {
                if(!sts.equalsIgnoreCase("Won")){
                    if(!sts.equalsIgnoreCase("Lost")){
                        System.out.println(String.format("ERROR! Expected status is %s but found %s", status, sts));
                        return false;
                    }

                }
            }else{
            if (!sts.equals(status)) {
                System.out.println(String.format("ERROR! Expected status is %s but found %s", status, sts));
                return false;
            }
            }
        }
        return true;
    }

    public List<String> translateProductsActive(List<String> productCode)
    {
        if(Objects.nonNull(productCode))
        {
            List<String> productTranslate = new ArrayList<>();
            for(int i=0, n = productCode.size(); i<n; i++)
            {
                productTranslate.add(MemberConstants.MyBetsPage.DDB_PRODUCT_FILTER.get(productCode.get(i)));
            }
            return productTranslate;
        }else{
            System.out.println("There is no product active!");
            return null;
        }
    }

}
