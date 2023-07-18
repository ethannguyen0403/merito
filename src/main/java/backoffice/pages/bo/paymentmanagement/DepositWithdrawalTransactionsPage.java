package backoffice.pages.bo.paymentmanagement;

import backoffice.controls.DateTimePicker;
import backoffice.controls.Row;
import backoffice.controls.Table;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import backoffice.utils.paymentmanagement.PaymentConfigurationUtils;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
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
    Table tblBody = Table.xpath("//div[@class='custom-table-body custom-scroll-body ng-star-inserted']",20);
    public StaticTable tblDeposit = StaticTable.xpath("//div[@class='custom-table currency-table mt-2 ng-star-inserted']","div[@class='custom-table-body custom-scroll-body ng-star-inserted']",
            "div[@class='custom-table-row ng-star-inserted']","div[contains(@class,'custom-table-cell')]",9);
    public StaticTable tblWithDrawal = StaticTable.xpath("//div[@class='custom-table currency-table mt-2 ng-star-inserted']","div[@class='custom-table-body custom-scroll-body ng-star-inserted']",
            "div[@class='custom-table-row ng-star-inserted']","div[contains(@class,'custom-table-cell')]",14);
    public ArrayList<String> getTabName(){
        ArrayList<String> lstTab = new ArrayList<String>();
        Row lstColumn = Row.xpath("//li[@class='nav-item cursor-pointer']/a");
        for (int i = 1; i <= lstColumn.getWebElements().size(); i++){
            lstTab.add(Label.xpath(String.format("//li[@class='nav-item cursor-pointer'][%s]/a",i)).getText());
        }
        return lstTab;
    }
    public Label getLblByName (String lblName){
        return Label.xpath(String.format(this.lblName, lblName));
    }
    public ArrayList<String> getHeaderTableName(){
        ArrayList<String> lstData = new ArrayList<String>();
        Row lstColumn = Row.xpath("//div[@class='custom-table-header']/div/div/span");
        for (int i = 1; i <= lstColumn.getWebElements().size(); i++){
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

    public void searchData(String fromDay, String toDay, String brand, String agent, String status, String username) {
        if (!fromDay.isEmpty()){
            dtpFrom.selectDate(fromDay,"yyyy-MM-dd");
        }
        if (!toDay.isEmpty()){
            dtpTo.selectDate(toDay,"yyyy-MM-dd");
        }
        if (!brand.isEmpty()){
            ddnBrand.selectByVisibleText(brand);
        }
        if (!agent.isEmpty()){
            ddnAgent.selectByVisibleText(agent);
        }
        if (!status.isEmpty()){
            ddnStatus.selectByVisibleText(status);
        }
        if (!username.isEmpty()){
            txbUsername.sendKeys(username);
        }
        btnSearch.click();
    }

    public boolean checkTransactionDateInFilterRange(String fromDay, String toDay, List<String> lstTransactionDate) {
        if (tblBody.isDisplayed()){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd\nHH:mm:ss");
            LocalDateTime fromDayTime = LocalDateTime.parse(fromDay+"\n00:00:01",format);
            LocalDateTime toDayTime = LocalDateTime.parse(toDay+"\n23:59:59",format);
            for (int i = 0; i < lstTransactionDate.size(); i++){
                if (LocalDateTime.parse(lstTransactionDate.get(i),format).isBefore(fromDayTime) || LocalDateTime.parse(lstTransactionDate.get(i),format).isAfter(toDayTime)){
                    System.out.println("Transaction date is not in the filter range");
                    return false;
                }
            }
            return true;
        } else {
            System.out.println("No records found");
            return false;
        }
    }

    public boolean verifySearchResultByBrand(String brand, List<String> lstBrand) {
        if (tblBody.isDisplayed()){
            if (!brand.isEmpty() && !lstBrand.isEmpty()){
                for (int i = 0; i < lstBrand.size();i++){
                    if (!lstBrand.get(i).equals(brand)){
                        System.out.println(lstBrand.get(i) + " difference from "+brand);
                        return false;
                    } else {
                        return true;
                    }
                }
            }
       } else {
            System.out.println("No records found");
            return true;
        }
        return false;
    }
    public boolean verifySearchResultByUserName(String username, List<String> lstUsername){
        if (tblBody.isDisplayed()){
            if (!username.isEmpty() && !lstUsername.isEmpty()){
                for (int i = 0; i < lstUsername.size();i++){
                    if (!lstUsername.get(i).substring(0,lstUsername.get(i).indexOf("(")-1).equals(username)){
                        System.out.println(lstUsername.get(i) + " difference from" + username );
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else {
            System.out.println("No records found");
            return true;
        }
        return false;
    }
    public boolean verifySearchResultByStatus(String status, List<String> lstStatus){
        if (tblBody.isDisplayed()){
            if (!status.isEmpty() && !lstStatus.isEmpty()){
                for (int i = 0; i < lstStatus.size();i++){
                    if (!lstStatus.get(i).equals(status)){
                        System.out.println(lstStatus.get(i) + " difference from" + status );
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else {
            System.out.println("No records found");
            return true;
        }
        return false;
    }

    public List<String> getListSorted(List<String> lstTransaction) {
        ArrayList<String> myDates = new ArrayList<>();
        for (int i = 0; i < lstTransaction.size(); i++){
            myDates.add(lstTransaction.get(i));
        }
        Collections.sort(myDates,Collections.reverseOrder());
        return myDates;
    }
}
