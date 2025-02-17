package agentsite.pages.report;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class BigStakeConfigurationPage extends HomePage {
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'noRecord')]");
    public Label lblBigStake = Label.xpath("//label[text()='Big Stake']");
    public TextBox txtBigStake = TextBox.xpath("//label[text()='Big Stake']/following::input[1]");
    public Button btnSubmit = Button.xpath("//button[@id='btn-submit']");
    public Label lblInfo = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public Table tblBigStakeConfiguration = Table.xpath("//table[contains(@class,'ptable report')]", 3);
    public Label lblMessage = Label.xpath("//app-alert//div[contains(@class,'modal-body')]");
    public Button btnOK = Button.xpath("//app-alert//button[text()='OK']");
    int colBigStakeValue = 2;

    public BigStakeConfigurationPage(String types) {
        super(types);
    }

    public String configureBigStake(String bigStake) {
        if (!bigStake.isEmpty())
            txtBigStake.sendKeys(bigStake);
        btnSubmit.click();
        waitingLoadingSpinner();
        lblMessage.isDisplayedShort(2);
        return lblMessage.getText();
    }

    public String getBigStakeCurrently(){
        return tblBigStakeConfiguration.getColumn(colBigStakeValue,3,false).get(0);
    }

}
