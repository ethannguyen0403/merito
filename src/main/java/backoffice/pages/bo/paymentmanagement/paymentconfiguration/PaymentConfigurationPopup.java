package backoffice.pages.bo.paymentmanagement.paymentconfiguration;

import agentsite.controls.Table;
import backoffice.controls.Row;
import backoffice.pages.bo.paymentmanagement.PaymentConfigurationPage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PaymentConfigurationPopup  {
    String lblTitle = "//span[text()='Payment Configuration Log - %s']";
    Table tblData = Table.xpath("//div[@class='modal-body']//div[@class='ps-content']",3);
    Button btnClose = Button.xpath("//button[text()='Close']");
    public String getTitlePopupByUsername(String username){
        return Label.xpath(String.format(lblTitle,username)).getText();
    }

    public List<ArrayList<String>> getLogOfUser() {
        List<ArrayList<String>> lstData = new ArrayList<>();
        Row lstRow = Row.xpath("//div[@class='modal-body']//div[@class='ps-content']/div");
        for (int i = 0; i < lstRow.getWebElements().size(); i++){
                int u = i + 1;
                Label lblData = Label.xpath(String.format("//div[@class='modal-body']//div[@class='ps-content']/div[%s]/div[1]",u));
                lblData.scrollToThisControl(true);
                Row row = Row.xpath(String.format("//div[@class='modal-body']//div[@class='ps-content']/div[%s]/div",u));
                ArrayList<String> lstString = new ArrayList<String>();
                for (int j = 0; j < row.getWebElements().size();j++){
                    int n = j + 1;
                    lstString.add(j,Label.xpath(String.format("//div[@class='modal-body']//div[@class='ps-content']/div[%s]/div[%s]",u,n)).getText());
                }
                lstData.add(i, lstString);
        }
        return lstData;
    }
    public PaymentConfigurationPage clickToClosePopup(){
        btnClose.click();
        return new PaymentConfigurationPage();
    }

}
