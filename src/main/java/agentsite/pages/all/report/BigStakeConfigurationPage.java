package agentsite.pages.all.report;

import com.paltech.element.common.*;
import agentsite.controls.Table;
import agentsite.pages.all.components.LeftMenu;

public class BigStakeConfigurationPage extends LeftMenu {
    public Label lblNoRecord = Label.xpath("//td[contains(@class,'noRecord')]");
    public Label lblBigStake = Label.xpath("//label[text()='Big Stake']");
    public TextBox txtBigStake = TextBox.xpath("//label[text()='Big Stake']/following::input[1]");
    public Button btnSubmit = Button.xpath("//input[@value='Submit']");
    public Label lblInfo = Label.xpath("//span[@class='pinfo']/following-sibling::label");
    public Table tblBigStakeConfiguration = Table.xpath("//table[contains(@class,'ptable report')]",3);
    public Label lblMessage = Label.xpath("//app-alert//div[contains(@class,'modal-body')]");
    public Button btnOK = Button.xpath("//app-alert//button[contains(@class,'btn-warning')]");
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class,'la-ball-clip-rotate')]");

    public String configureBigStake(String bigStake)
    {
        if(!bigStake.isEmpty())
            txtBigStake.sendKeys(bigStake);
        btnSubmit.click();
        waitingLoadingSpinner();
        return lblMessage.getText();
    }

    public void  waitingLoadingSpinner(){
        iconLoadSpinner.waitForControlInvisible(1,1);
    }


}
