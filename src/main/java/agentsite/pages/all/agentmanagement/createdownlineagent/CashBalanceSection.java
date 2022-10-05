package agentsite.pages.all.agentmanagement.createdownlineagent;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import agentsite.controls.Table;
import org.openqa.selenium.By;

public class CashBalanceSection extends BaseElement {
    private String _xPath ="//app-credit-setting-exchange";
    public Label lblTitle ;
    public Table tblCashBalance;
    public TextBox txtCreditInitiation;
    public TextBox txtFirstTimeDeposit;
    public TextBox txtMaxPlayerCredit;
    public Label lblMaxPlayerCredit;
    public Label lblFirstTimeDepositValue;
    public Label lblMaxPlayerCreditValue ;

    public TextBox txtInitiationDeposit;
    public TextBox txtMemberMaxCredit;
    public Label lblCreditLimit;
    public TextBox txtCreditLimit;

    public CashBalanceSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblTitle = Label.xpath(String.format("%s//div[@class='psection']", _xPath));
        tblCashBalance = Table.xpath(String.format("%s//table[contains(@class,'credit-balance-table')]", _xPath), 2);
        txtCreditInitiation = TextBox.xpath(String.format("%s//input[contains(@name,'CreditReference')]", _xPath));
        txtFirstTimeDeposit = TextBox.xpath(String.format("%s//input[contai//input[contains(@name,'playerMaxCredit')]ns(@name,'cashBalance')]", _xPath));
        txtMaxPlayerCredit = TextBox.xpath(String.format("%s//input[contains(@name,'playerMaxCredit')]", _xPath));
        lblMaxPlayerCredit = Label.xpath(String.format("%s//span[contains(@class,'playerMaxCredit')]", _xPath));
        lblFirstTimeDepositValue = Label.xpath(String.format("%s//span[contains(@class,'playerMaxCredit')]", _xPath));
        lblMaxPlayerCreditValue = Label.xpath(String.format("%s//span[contains(@class,'creditLimit')]", _xPath));

    }

    public static CashBalanceSection xpath(String xpathExpression) {
        return new CashBalanceSection(By.xpath(xpathExpression), xpathExpression);
    }

    public int getMaxPlayerLitmitCredit(){
        String value =lblMaxPlayerCreditValue.getText();
        int convertoInt = (int)(Double.parseDouble(value.split("<=")[1].replace(",","").trim()));
        return convertoInt;
    }
    public void updateFirstTimeDeposit(String value)
    {
        if(!value.isEmpty())
             txtFirstTimeDeposit.sendKeys(value);
    }

    public double getFirstTimeDepositLimit(String currency){
        String creditLimit = lblCreditLimit.getText();

        if(currency.isEmpty()){
            creditLimit= creditLimit.split(currency)[1].trim();
        }else
            creditLimit= creditLimit.split("<=")[1].replace(",","").trim();

        return Double.valueOf(creditLimit.replaceAll(",", "").toString());
    }
    public String getMaxPlayerCredit(){
        return txtMaxPlayerCredit.getAttribute("value");
    }
}
