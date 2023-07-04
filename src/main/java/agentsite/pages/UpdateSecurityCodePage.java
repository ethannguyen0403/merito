package agentsite.pages;

import agentsite.controls.Table;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class UpdateSecurityCodePage extends HomePage {
    public TextBox txtOldSecurityCode = TextBox.xpath("//input[@id='oldSecurityCode']");
    public TextBox txtSecurityCode = TextBox.xpath("//input[@id='newSecurityCode']");
    public TextBox txtConfrimSecurityCode = TextBox.xpath("//input[@id='confirmSecurityCode']");
    public Button btnSubmit = Button.xpath("//button[@id='changeSecurityCode']");
    public Table tblForm = Table.xpath("//div[contains(@class,'security-code')]//table", 2);
    public Label lblGuide = Label.xpath("//div[contains(@class,'instructions')]");

    public UpdateSecurityCodePage(String types) {
        super(types);
    }

}
