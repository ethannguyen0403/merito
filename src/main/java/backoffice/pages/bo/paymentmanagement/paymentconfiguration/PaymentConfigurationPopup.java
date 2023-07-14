package backoffice.pages.bo.paymentmanagement.paymentconfiguration;

import agentsite.controls.Table;
import backoffice.controls.Row;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.paymentmanagement.PaymentConfigurationPage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PaymentConfigurationPopup  {
    String lblTitle = "//span[text()='Payment Configuration Log - %s']";
    public StaticTable tblPopup = StaticTable.xpath("//div[@class='modal-body']//div[@class='custom-table currency-table']","div[@class='custom-table-body custom-scroll-body ng-star-inserted']",
            "div[@class='custom-table-row ng-star-inserted']","div[contains(@class,'custom-table-cell')]",3);
    Button btnClose = Button.xpath("//button[text()='Close']");
    public String getTitlePopupByUsername(String username){
        return Label.xpath(String.format(lblTitle,username)).getText();
    }

    public PaymentConfigurationPage clickToClosePopup(){
        btnClose.click();
        return new PaymentConfigurationPage();
    }

}
