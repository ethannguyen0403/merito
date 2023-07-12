package backoffice.pages.bo.paymentmanagement;

import backoffice.controls.DateTimePicker;
import backoffice.pages.bo.home.HomePage;
import backoffice.utils.paymentmanagement.PaymentConfigurationUtils;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DepositWithdrawalTransactionsPage extends HomePage {
    public Label lblTitlePage = Label.xpath("//div[@id='header']//span[text()='Deposit/Withdrawal Transactions']");
    String lblName = "//label[text()='%s']";
    public Button btnTabActive = Button.xpath("//li[@class='nav-item cursor-pointer']//a[@class='nav-link active']");
    public TextBox txbFrom = TextBox.name("from-date");
    public TextBox txbTo = TextBox.name("to-date");
    DateTimePicker dtpFrom = DateTimePicker.xpath(txbFrom,"//bs-datepicker-container");
    DateTimePicker dtpTo = DateTimePicker.xpath(txbTo,"//bs-datepicker-container");
    public DropDownBox ddnBrand = DropDownBox.xpath("//label[text()='Brand']/parent::div/select");
    public DropDownBox ddnAgent = DropDownBox.xpath("//label[text()='Agent']/parent::div/select");
    public DropDownBox ddnStatus = DropDownBox.xpath("//label[text()='Status']/parent::div/select");
    public TextBox txbUsername = TextBox.name("username");
    public Button btnSearch = Button.name("submit");
    public Button btnWithdrawalTab = Button.xpath("//li[@class='nav-item cursor-pointer'][2]");
    public Button btnDepositTab = Button.xpath("//li[@class='nav-item cursor-pointer'][1]");
    public ArrayList<String> getTabName(){
        ArrayList<String> lstTab = new ArrayList<String>();
        List<WebElement> lstColumn = DriverManager.getDriver().findElements(By.xpath("//li[@class='nav-item cursor-pointer']/a"));
        for (int i = 1; i <= lstColumn.size(); i++){
            lstTab.add(Label.xpath(String.format("//li[@class='nav-item cursor-pointer'][%s]/a",i)).getText());
        }
        return lstTab;
    }
    public Label getLblByName (String lblName){
        return Label.xpath(String.format(this.lblName, lblName));
    }
    public ArrayList<String> getHeaderTableName(){
        ArrayList<String> lstData = new ArrayList<String>();
        List<WebElement> lstColumn = DriverManager.getDriver().findElements(By.xpath("//div[@class='custom-table-header']/div/div/span"));
        for (int i = 1; i <= lstColumn.size(); i++){
            lstData.add(Label.xpath(String.format("//div[@class='custom-table-header']/div/div[%s]/span",i)).getText());
        }
        return lstData;
    }
    public int getNumberAgentSameAsPaymentConfigurationPage(List<String> lstAgent){
        int agent = 0;
        for (int i = 0; i < lstAgent.size();i++){
            for (int o = 0; o < PaymentConfigurationUtils.getUsername().size(); o++){
                if (lstAgent.get(i).equals(PaymentConfigurationUtils.getUsername().get(o)))
                {
                    agent++;
                    break;
                }
            }
        }
        return agent;
    }
}
