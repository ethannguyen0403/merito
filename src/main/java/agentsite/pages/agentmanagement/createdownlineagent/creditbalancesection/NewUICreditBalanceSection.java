package agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection;

import com.paltech.element.common.Label;

public class NewUICreditBalanceSection extends CreditBalanceSection {
    private Label lblCreditBalanceTitle = Label.xpath("//div[@class='psection' and text()='Credit Balance ']");
    private Label lblCreditLimit = Label.xpath("(//table[contains(@class,'credit-balance-table')])[1]//body//tr[1]//td[1]");
    private Label lblAGMaxCredit = Label.xpath("(//table[contains(@class,'credit-balance-table')])[1]//body//tr[1]//td[3]");
    private Label lblMemberMaxCredit = Label.xpath("(//table[contains(@class,'credit-balance-table')])[1]//body//tr[1]//td[5]");
    public String getCreditSectionTitle() {
        return lblCreditBalanceTitle.getText();
    }

}
