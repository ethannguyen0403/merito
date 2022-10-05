package agentsite.pages.all.fraudetection;

import com.paltech.element.common.Button;
import com.paltech.element.common.RadioButton;
import com.paltech.element.common.TextBox;
import agentsite.pages.all.components.LeftMenu;

public class FraudDetectionPage extends LeftMenu {

    //input[@value='BY_EVENT_DATE']
    public RadioButton rbPlacedDate = RadioButton.xpath("//input[@value='BY_PLACED_DATE']");
    public RadioButton rbEventDate = RadioButton.xpath("//input[@value='BY_PLACED_DATE']");
    public Button btnSubmitPlaceDate = Button.xpath("//button[@id='searchByPlacedDate']");
    public TextBox taContent = TextBox.xpath("//textarea[contains(@class,'form-control')]");
}
