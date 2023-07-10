package backoffice.pages.bo.paymentmanagement;

import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.paymentmanagement.paymentconfiguration.PaymentConfigurationPopup;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    String lblTitleOfPopupByUsername = "//span[text()='Payment Configuration Log - %s']";
    public Label lblPaymentConfiguration = Label.xpath("//span[@id='bo-page-title']");
    public Label lblPaymentMethod = Label.xpath("//label[text()='Payment Method']");
    public Label lblAgent = Label.xpath("//label[text()='Agent']");
    public void filter(String paymentMethod, String userAgentName) {
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
        lblScrollTo.scrollToThisControl(true);
        btnRemoveByUsername.click();
    }

    public PaymentConfigurationPopup clickToViewLogByUsername(String username) {
        Button btnViewLog = Button.xpath(String.format(btnEye,username));
        lblScrollTo.scrollToThisControl(true);
        btnViewLog.click();
        return new PaymentConfigurationPopup();
    }
    public boolean isDisplayTitleOfPopup(String username){
        return Label.xpath(String.format(lblTitleOfPopupByUsername,username)).isDisplayed(3000);
    }
    public ArrayList<String> getListUsernameAndUpdateDate() {
        ArrayList<String> lstData = new ArrayList<String>();
        List<WebElement> lstRow = DriverManager.getDriver().findElements(By.xpath("//div[@class='ps-content']/div[@class='custom-table-row ng-star-inserted']"));
        for (int i = 0; i < lstRow.size(); i++){
            int n = i + 1;
            Label lblData = Label.xpath(String.format("//div[@class='ps-content']/div[@class='custom-table-row ng-star-inserted'][%s]",n));
            lblData.scrollToThisControl(true);
            lstData.add(Label.xpath(String.format("//div[@class='ps-content']/div[@class='custom-table-row ng-star-inserted'][%s]/div[9]",n)).getText());
        }
        return lstData;
    }

    public ArrayList<String> getListSortedByUsernameAndUpdateDate(ArrayList<String> lstData) {
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
        List<WebElement> lstColumn = DriverManager.getDriver().findElements(By.xpath("//div[@class='custom-table-header']/div/div/span"));
        for (int i = 1; i <= lstColumn.size(); i++){
            lstData.add(Label.xpath(String.format("//div[@class='custom-table-header']/div/div[%s]/span",i)).getText());
        }
        return lstData;
    }

}
