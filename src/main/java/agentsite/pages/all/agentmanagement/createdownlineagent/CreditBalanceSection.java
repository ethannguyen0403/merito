package agentsite.pages.all.agentmanagement.createdownlineagent;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import agentsite.controls.Table;
import org.openqa.selenium.By;

public class CreditBalanceSection extends BaseElement {
    private String _xPath ="//app-credit-setting-exchange";
    public Label lblCashBalanceTitle;
    public Table tblCashBalance;
    public TextBox txtInitiationDeposit;
    public Label lblMaxCredit ;
    public TextBox txtCreditLimit;
    public TextBox txtDownlineAGMaxCredit;
    public TextBox txtMemberMaxCredit;
    public Label lblDownlineAGMaxCreditLimit  ;
    public Label lblMemberMaxCreditLimit ;
    public Label lblCreditLimit;

    public CreditBalanceSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblCashBalanceTitle = Label.xpath(String.format("%s//div[@class='psection']",_xPath));
        tblCashBalance = Table.xpath(String.format("%s//table[contains(@class,'credit-balance-table')]",_xPath),2);
        txtInitiationDeposit = TextBox.xpath(String.format("%s//input[contains(@name,'cashBalance')]",_xPath));
        lblMaxCredit = Label.xpath(String.format("%s//span[contains(@class,'creditLimit')]",_xPath));
        txtCreditLimit = TextBox.xpath(String.format("%s//input[@name='creditLimit']",_xPath));
        txtDownlineAGMaxCredit = TextBox.xpath(String.format("%s//input[@name='smaMaxCredit']",_xPath));
        txtMemberMaxCredit = TextBox.xpath(String.format("%s//input[@name='memberMaxCredit']",_xPath));
        lblDownlineAGMaxCreditLimit = Label.xpath(String.format("%s//table[contains(@class,'credit-balance-table')]//span[contains(@class,'maxCredit')]",_xPath));
        lblMemberMaxCreditLimit = Label.xpath(String.format("%s//table[contains(@class,'credit-balance-table')]//span[contains(@class,'memberMaxCredit')]",_xPath));
       lblCreditLimit = Label.xpath(String.format("%s//table[contains(@class,'credit-balance-table')]//span[contains(@class,'creditLimit')]",_xPath));
    }

    public static CreditBalanceSection xpath(String xpathExpression) {
        return new CreditBalanceSection(By.xpath(xpathExpression), xpathExpression);
    }
    public int getMaxPlayerLitmitCredit(){
        String value =lblMemberMaxCreditLimit.getText();
        int convertoInt = (int)(Double.parseDouble(value.split("<=")[1].replace(",","").trim()));
        return convertoInt;
    }
    public void updateCashBalance (String maxPlayerCredit)
    {
        if(!maxPlayerCredit.isEmpty())
             txtMemberMaxCredit.sendKeys(maxPlayerCredit);
    }

    public double getCreditLimit(String currency){
        String creditLimit  = lblCreditLimit.getText().split(currency)[1].trim();
        return Double.valueOf(creditLimit.replaceAll(",", "").toString());
    }
}
