package backoffice.pages.bo.paymentmanagement;

import backoffice.controls.Row;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.paymentmanagement.paymentconfiguration.PaymentConfigurationPopup;
import com.paltech.element.common.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class PaymentConfigurationPage extends HomePage {
    public DropDownBox ddnPaymentMethod = DropDownBox.xpath("//select[@name='payment-method']");
    TextBox txbAgent = TextBox.xpath("//input[@name='username']");
    public Label lblAlertAgentDoesNotExist = Label.xpath("//span[contains(text(),'does not exist in the System!')]");
    public Label lblAlertAgentAlreadyAdd = Label.xpath("//span[contains(text(),'is already added')]");
    public Label lblAlertOnly1Level = Label.xpath("//span[contains(text(),'Only 1 level in a single line is allowed to configure')]");
    public Button btnAdd = Button.xpath("//button[text()='Add']");
    String btnRemove = "//div[text()=' %s ']/parent::div//span[@class='cursor-pointer p-2'][1]";
    String btnEye = "//div[text()=' %s ']/parent::div//span[@class='cursor-pointer p-2'][2]";
    public Button btnOK = Button.xpath("//button[text()='OK']");
    public Label lblScrollTo = Label.xpath("//div[text()='6']");
    Popup ppLog = Popup.xpath("//modal-container");
    public Label lblPaymentConfiguration = Label.xpath("//span[@id='bo-page-title']");
    public Label lblPaymentMethod = Label.xpath("//label[text()='Payment Method']");
    public Label lblAgent = Label.xpath("//label[text()='Agent']");
    public void addAgent(String paymentMethod, String userAgentName) {
        if (!paymentMethod.isEmpty()){
            ddnPaymentMethod.selectByVisibleContainsText(paymentMethod);
        }
        if (!userAgentName.isEmpty()){
            txbAgent.sendKeys(userAgentName);
        }
        btnAdd.click();
    }
    public void clickToRemoveByUsername(String username) {
        Button btnRemoveByUsername = Button.xpath(String.format(btnRemove,username));
        if (btnRemoveByUsername.isClickable(2)){
            btnRemoveByUsername.click();
        } else {
            lblScrollTo.scrollToThisControl(true);
            btnRemoveByUsername.click();
        }


    }

    public PaymentConfigurationPopup clickToViewLogByUsername(String username) {
        Button btnViewLog = Button.xpath(String.format(btnEye,username));
        if (btnViewLog.isClickable(2)){
            btnViewLog.click();
        } else {
            lblScrollTo.scrollToThisControl(true);
        }
        return new PaymentConfigurationPopup();
    }
    public boolean isDisplayLogPopup(){
        return ppLog.isDisplayed(3000);
    }
    public List<ArrayList<String>> getAllDataOnTable() {
        List<ArrayList<String>> lstData = new ArrayList<>();
        Row lstRow = Row.xpath("//div[@class='ps-content']/div[@class='custom-table-row ng-star-inserted']");
        for (int i = 0; i < lstRow.getWebElements().size(); i++){
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
    }

    public ArrayList<String> getListUpdateDateSorted(ArrayList<String> lstData) {
        ArrayList<LocalDateTime> myDates = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < lstData.size(); i++){
            myDates.add(LocalDateTime.parse(lstData.get(i),format));
        }
        Collections.sort(myDates);

        ArrayList<String> lstSorted = new ArrayList<String>();
        for (int i = 0; i < myDates.size(); i++){
            lstSorted.add(String.valueOf(myDates.get(i)));
        }
        return lstSorted;
    }
    public ArrayList<String> getHeaderTableName(){
        ArrayList<String> lstData = new ArrayList<String>();
        Row rowHeadTableName = Row.xpath("//div[@class='custom-table-header']/div/div/span");
        for (int i = 1; i <= rowHeadTableName.getWebElements().size(); i++){
            lstData.add(Label.xpath(String.format("//div[@class='custom-table-header']/div/div[%s]/span",i)).getText());
        }
        return lstData;
    }

}
