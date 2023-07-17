package backoffice.pages.bo.paymentmanagement;

import backoffice.controls.DateTimePicker;
import backoffice.controls.Row;
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
    public List<ArrayList<String>> getDataInTable(){
        if (Row.xpath("//div[@class='custom-table-body custom-scroll-body ng-star-inserted']//div[@class='ps-content']").isDisplayed()){
            List<ArrayList<String>> lstData = new ArrayList<>();
            Row lstRow = Row.xpath("//div[@class='custom-table-row ng-star-inserted']");
            for (int i = 0; i < lstRow.getWebElements().size();i++){
                int n = i + 1;
                Label lblData = Label.xpath(String.format("//div[@class='ps-content']/div[@class='custom-table-row ng-star-inserted'][%s]",n));
                lblData.scrollToThisControl(true);
                Row row = Row.xpath(String.format("//div[@class='ps-content']/div[@class='custom-table-row ng-star-inserted'][%s]/div",n));
                ArrayList<String> lstString = new ArrayList<String>();
                for (int j = 0; j < row.getWebElements().size();j++){
                    int u = j + 1;
                    lstString.add(j,Label.xpath(String.format("//div[@class='ps-content']/div[@class='custom-table-row ng-star-inserted'][%s]/div[%s]",n,u)).getText());
                }
                lstData.add(i, lstString);
            }
            return lstData;
        } else {
            System.out.println("No records found");
        }
        return null;
    }

    public boolean checkTransactionDateInFilterRange(String fromDay, String toDay, List<String> lstTransactionDate) {
        if (Label.xpath("//div[@class='custom-table-body custom-scroll-body ng-star-inserted']").isDisplayed()){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd\nHH:mm:ss");
            LocalDateTime fromDayTime = LocalDateTime.parse(fromDay+"\n00:00:00",format);
            LocalDateTime toDayTime = LocalDateTime.parse(toDay+"\n00:00:00",format);
            for (int i = 0; i < lstTransactionDate.size(); i++){
                if (LocalDateTime.parse(lstTransactionDate.get(i),format).isAfter(fromDayTime)  && LocalDateTime.parse(lstTransactionDate.get(i),format).isBefore(toDayTime)){
                    return true;
                }
                if (LocalDateTime.parse(lstTransactionDate.get(i),format).isEqual(fromDayTime)  || LocalDateTime.parse(lstTransactionDate.get(i),format).isEqual(toDayTime)){
                    return true;
                }
            }
            System.out.println("Transaction date is not in the filter range");
            return false;
        } else {
            System.out.println("No records found");
            return false;
        }
    }

    public boolean checkAccountsSelectLabels(String brand, String agent, String status, List<String> lstBrand, List<String> lstAgent, List<String> lstStatus) {
        if (Label.xpath("//div[@class='custom-table-body custom-scroll-body ng-star-inserted']").isDisplayed()){
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
            if (!agent.isEmpty() && !lstAgent.isEmpty()){
                for (int i = 0; i < lstAgent.size();i++){
                    if (!lstAgent.get(i).substring(0,lstAgent.get(i).indexOf("(")-1).equals(agent)){
                        System.out.println(lstAgent.get(i) + " difference from" + agent );
                        return false;
                    } else {
                        return true;
                    }
                }
            }
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

    public boolean checkAgentOnPaymentConfigurationByBrandName(String brandname, List<String> lstAgent) {
        List<String> lstAgentOnPayment = new ArrayList<>();
        for (int i = 0; i < PaymentConfigurationUtils.getListBrandWithAgent().size(); i++){
            if (PaymentConfigurationUtils.getListBrandWithAgent().get(i).get(0).equals(brandname)){
                lstAgentOnPayment.add(PaymentConfigurationUtils.getListBrandWithAgent().get(i).get(1));
            }
        }
        int o = 0;
        for (int i = 0; i < lstAgentOnPayment.size(); i++){
            for (int j = 0; j < lstAgent.size(); j++){
                if (lstAgent.get(j).equals(lstAgentOnPayment.get(i))){
                    o++;
                }
            }
        }
        if (lstAgent.size() == o){
            return true;
        }
        return false;
    }
    public List<String> getListTransactionSorted(List<String> lstTransaction) {
        List<LocalDateTime> myDates = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd\nHH:mm:ss");
        for (int i = 0; i < lstTransaction.size(); i++){
            myDates.add(LocalDateTime.parse(lstTransaction.get(i),format));
        }
        Collections.sort(myDates,Collections.reverseOrder());

        ArrayList<String> lstSorted = new ArrayList<String>();
        for (int i = 0; i < myDates.size(); i++){
            lstSorted.add(String.valueOf(myDates.get(i)).replace("T","\n"));
            if (lstSorted.get(i).length() < 17){
                lstSorted.set(i,lstSorted.get(i)+":00");
            }
        }
        return lstSorted;
    }
}
