package backoffice.pages.bo.paymentmanagement;

import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.paymentmanagement.paymentconfiguration.PaymentConfigurationPopup;
import com.paltech.element.common.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class PaymentConfigurationPage extends HomePage {
    public DropDownBox ddnPaymentMethod = DropDownBox.xpath("//select[@name='payment-method']");
    TextBox txbAgent = TextBox.xpath("//input[@name='username']");
    public Button btnAdd = Button.xpath("//button[text()='Add']");
    String btnRemove = "//div[text()=' %s ']/parent::div//span[@class='cursor-pointer p-2'][1]";
    String btnEye = "//div[text()=' %s ']/parent::div//span[@class='cursor-pointer p-2'][2]";
    public Button btnOK = Button.xpath("//button[text()='OK']");
    public Label scrollTo6 = Label.xpath("//div[text()='6']");
    public Label scrollTo3 = Label.xpath("//div[text()='3']");
    public Label scrollTo2 = Label.xpath("//div[text()='2']");
    public Label scrollTo1 = Label.xpath("//div[text()='1']");
    Popup ppLog = Popup.xpath("//modal-container");
    public Label lblPaymentConfiguration = Label.xpath("//span[@id='bo-page-title']");
    public Label lblPaymentMethod = Label.xpath("//label[text()='Payment Method']");
    public Label lblAgent = Label.xpath("//label[text()='Agent']");
    private int totalColumn = 10;
    public StaticTable tblReport = StaticTable.xpath("//div[@class='custom-table currency-table']","div[@class='custom-table-body custom-scroll-body ng-star-inserted']",
            "div[@class='custom-table-row ng-star-inserted']","div[contains(@class,'custom-table-cell')]",totalColumn);

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
        }
    }

    public PaymentConfigurationPopup clickToViewLogByUsername(String username) {
        Button btnViewLog = Button.xpath(String.format(btnEye,username));
        if (btnViewLog.isClickable(2)){
            btnViewLog.click();
        } else {
            scrollTo6.scrollToThisControl(true);
        }
        return new PaymentConfigurationPopup();
    }
    public boolean isDisplayLogPopup(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ppLog.isDisplayed(3);
    }

    public List<String> getListUpdateDateSorted(List<String> lstData) {
        ArrayList<String> myDates = new ArrayList<>();
        for (int i = 0; i < lstData.size(); i++){
            myDates.add(lstData.get(i));
        }
        Collections.sort(myDates,Collections.reverseOrder());
        return myDates;
    }

    public String getUplineOfAgent(List<ArrayList<String>> lstData, String username) {
        String lineAgent = null;
        String upLineAgent = null;
        for (int i = 0; i < lstData.size();i++){
            if (lstData.get(i).get(3).equals(username)){
                lineAgent = lstData.get(i).get(6);
                break;
            }
        }
        for (int i = 0; i < lineAgent.length();i++){
            if (String.valueOf(lineAgent.charAt(i)).equals("/")) {
                upLineAgent = lineAgent.substring(0,i);
                break;
            }
        }
        return upLineAgent;
    }

    public void scrollToTopTable() {
        scrollTo3.scrollToThisControl(true);
        scrollTo2.scrollToThisControl(true);
        scrollTo1.scrollToThisControl(true);
    }
}
