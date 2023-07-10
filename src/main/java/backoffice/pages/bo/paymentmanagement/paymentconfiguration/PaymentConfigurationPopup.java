package backoffice.pages.bo.paymentmanagement.paymentconfiguration;

import agentsite.controls.Table;
import backoffice.pages.bo.paymentmanagement.PaymentConfigurationPage;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PaymentConfigurationPopup  {
    String lblTitle = "//span[text()='Payment Configuration Log - %s']";
    Table tblData = Table.xpath("//div[@class='modal-body']//div[@class='ps-content']",3);
    Button btnClose = Button.xpath("//button[text()='Close']");
    public Label lblTitlebyUsername(String username){
        return Label.xpath(String.format(lblTitle,username));
    }

    public List<ArrayList<String>> getAllData() {
        List<ArrayList<String>> lstData = new ArrayList<>();
        List<WebElement> lstRow = DriverManager.getDriver().findElements(By.xpath("//div[@class='modal-body']//div[@class='ps-content']/div"));
        for (int i = 0; i < lstRow.size(); i++){
            int n = i + 1;
                Label lblData = Label.xpath(String.format("//div[@class='modal-body']//div[@class='ps-content']/div[%s]/div[1]",n));
                lblData.scrollToThisControl(true);
                lstData.add(i,new ArrayList<String>(
                        Arrays.asList(
                                Label.xpath(String.format("//div[@class='modal-body']//div[@class='ps-content']/div[%s]/div[1]",n)).getText(),
                                Label.xpath(String.format("//div[@class='modal-body']//div[@class='ps-content']/div[%s]/div[2]",n)).getText(),
                                Label.xpath(String.format("//div[@class='modal-body']//div[@class='ps-content']/div[%s]/div[3]",n)).getText()+".0"
                        )));
        }
        return lstData;
    }
    public PaymentConfigurationPage clickToClose(){
        btnClose.click();
        return new PaymentConfigurationPage();
    }

}
