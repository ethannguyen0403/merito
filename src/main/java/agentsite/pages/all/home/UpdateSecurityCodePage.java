package agentsite.pages.all.home;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import agentsite.controls.Table;
import agentsite.pages.all.components.BasePage;

public class UpdateSecurityCodePage extends BasePage {
    public TextBox txtOldSecurityCode = TextBox.xpath("//input[@id='oldSecurityCode']");
    public TextBox txtSecurityCode = TextBox.xpath("//input[@id='newSecurityCode']");
    public TextBox txtConfrimSecurityCode = TextBox.xpath("//input[@id='confirmSecurityCode']");
    public Button btnSubmit = Button.xpath("//button[@id='changeSecurityCode']");
    public Table tblForm = Table.xpath("//div[contains(@class,'security-code')]//table",2);
    public Label lblGuide = Label.xpath("//div[contains(@class,'instructions')]");

}
