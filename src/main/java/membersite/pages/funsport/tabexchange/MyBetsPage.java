package membersite.pages.funsport.tabexchange;
import com.paltech.element.common.*;
import common.MemberConstants;
import controls.DateTimePicker;
import controls.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class MyBetsPage extends membersite.pages.all.tabexchange.MyBetsPage {
    public Label lblOrderType = Label.xpath("//div[@class='row'][2]//select[contains(@class,'select-type-order')]//preceding::label[1]");
    public TextBox lblStartDate = TextBox.xpath(String.format("//label[text()='%s']", MemberConstants.MyBetsPage.START_DATE));
    public TextBox lblEndDate = TextBox.xpath(String.format("//label[text()='%s']", MemberConstants.MyBetsPage.END_DATE));
    public Label lblProduct = Label.xpath("//div[@class='container-title row']//div[contains(@class,'title')]");

    public  Label lblProductTitle = Label.xpath("//div[@class='actions no-print']//span[@class='current-activity']");
    public Icon icDownload = Icon.xpath("//div[@class='actions no-print']//i[contains(@class,'download-alt')]");
    public Icon icPrint = Icon.xpath("//div[@class='actions no-print']//i[contains(@class,'print')]");
    public  Button btnProductActive = Button.xpath("//button[contains(@class,'btn-default')]//span[@class='action-selected']");
    public String sMnProductxPath = "//ul[@class='dropdown-menu activities']//a[text()='%s']";
    public Button btnCurrent = Button.xpath("//div[@class='filter-type shows settled exchange']//button[@data-type='OPEN']");
    public Button btnSettle = Button.xpath("//div[@class='filter-type shows settled exchange']//button[@data-type='SETTLED']");
    public Button btnUnmatched = Button.xpath("//div[@class='filter-type shows settled exchange']//button[@data-type='unmatched']");
    public Button btnMatched = Button.xpath("//div[@class='filter-type shows settled exchange']//button[@data-type='matched']");
    public Label lblAll = Label.xpath("//div[@class='options']//div[contains(@class,'date-range')]");
    public Label lblOption =Label.xpath("//div[@class='options']//div[contains(@class,'cell option')]");
    public Icon icReresh = Icon.xpath("//div[@class='options']//div[contains(@class,'refresh ')]");
    public Label lblNote = Label.xpath("//div[@class='note']");
    public Label lblNoRecord = Label.xpath("//div[@id='mybets']//table[@class='table table-hover']//td[@class='centered']//strong");
    public Icon icSpinner = Icon.xpath("//div[@id='snipper-content']");
    public membersite.controls.DropDownBox ddpStatus = membersite.controls.DropDownBox.xpath("//div[contains(@class,'settle-option')]//div[contains(@class,'status-option dropdown')][1]","//ul[@class='dropdown-menu status']//li");
    public DropDownBox ddbProduct = DropDownBox.xpath("//div[@class='row'][1]//select[contains(@class,'select-type-order')]");
    public DropDownBox ddbOrderType = DropDownBox.xpath("//div[@class='row'][2]//select[contains(@class,'select-type-order')]");
    public Button btnLoadReport = Button.xpath("//button[contains(@class,'form-control btn btn-warning btn-report-search')]");
    private int colTotal = 8;
    public int colPlaced = 1;
    public int colDescription = 2;
    public int colType = 3;
    public int colOdd = 4;
    public int colStake = 5;
    public int colLiability = 6;
    public int colProfitLoss = 7;
    public int colStatus = 8;

    public Table tblReport = Table.xpath("//div[@id='mybets']//table[@class='table table-hover']",colTotal);

    public TextBox txtStartDate = TextBox.xpath(String.format("//label[text()='%s']/following::input[1]", MemberConstants.MyBetsPage.START_DATE));
    public DateTimePicker dtpStartDate = DateTimePicker.xpath(txtStartDate,"//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");
    public TextBox txtEndDate = TextBox.xpath(String.format("//label[text()='%s']/following::input[1]", MemberConstants.MyBetsPage.END_DATE));
    public DateTimePicker dtpEndDate = DateTimePicker.xpath(txtEndDate,"//bs-datepicker-container//div[contains(@class,'bs-datepicker-container')]");

    public void filter(String productName, String orderType)
    {
        selectProduct(productName);
        searchOrderType(orderType);
        icSpinner.waitForControlInvisible();
    }

    public void searchOrderType(String orderTye){
        switch (orderTye){
            case "Current":
                btnCurrent.click();
                return;
            case "Unmatched":
                btnUnmatched.click();
                return;
            case "Matched":
                btnMatched.click();
                return;
            default:
                filterSettledOrder(orderTye);
                return;
        }
    }

    public void filterSettledOrder(String status){
        btnSettle.click();
        lblOption.click();
        ddpStatus.selectByVisibleText(status,true);
    }

    public void selectProduct(String product){
        btnProductActive.click();
        Label.xpath(String.format(sMnProductxPath,product)).click();
    }
    public boolean validateFilterStatus(String status)
    {
        List<String> lst = tblReport.getColumn(null,colStatus,false);
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
