package agentsite.pages.agentmanagement.depositwithdrawal;

import com.paltech.element.common.Label;

public class DepositToPopup extends DepositPopup {
    public Label lblYourBalanceName = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][1]//span[@class='left']");
    public Label lblYourBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][1]//div[@class='amount']");
    public Label lblYourNewBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][1]//div[@class='tip']");
    public Label lblMemberBalanceName = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][2]//span[@class='left']");
    public Label lblMemberBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][2]//div[@class='amount']");
    public Label lblMemberNewBalance = Label.xpath("//div[@class='adjust-table-body']/div[contains(@class,'adjust-row')][2]//div[@class='tip']");

    public Label lblMessage = Label.xpath("//div[contains(@class,'adjust-mgs')]/span");
    public Label lblAmountError = Label.xpath("//div[@class='adjust-mgs failed']/span");
    public Label lblAmountSuccess = Label.xpath("//div[@class='adjust-mgs success']/span");

    public void clickXIcon() {
        iconClose.click();
    }

    public void clickCancelBtn() {
        btnCancel.click();
    }

    public double getNewMemberCashBalance() {
        String newCashBalance = lblMemberNewBalance.getText().split(":")[1].trim();
        newCashBalance = newCashBalance.replace(",", "").trim();
        return Double.parseDouble(newCashBalance);
    }

    public double getMemberCashBalance() {
        String newCashBalance = lblMemberBalance.getText().replace(",", "").trim();
        return Double.parseDouble(newCashBalance);
    }

    public double getNewYourCashBalance() {
        String newCashBalance = lblYourNewBalance.getText().split(":")[1].trim();
        newCashBalance = newCashBalance.replace(",", "").trim();
        return Double.parseDouble(newCashBalance);
    }

    public double getYourCashBalance() {
        String newCashBalance = lblYourBalance.getText().replace(",", "").trim();
        return Double.parseDouble(newCashBalance);
    }

    public String getMessage (){
        return lblMessage.getText().trim();
    }

}
