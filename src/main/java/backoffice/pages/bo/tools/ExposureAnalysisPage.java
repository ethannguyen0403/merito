package backoffice.pages.bo.tools;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import backoffice.pages.bo.home.HomePage;

public class ExposureAnalysisPage extends HomePage {
    public TextBox txtLoginID = TextBox.xpath("//input[@type='text']");
    public Button btnAnalyse = Button.xpath("//input[@type='button']");
    public Label lblUserInfo = Label.xpath("//div[contains(@class,'card-body')]//div[contains(@class,'left-info')]");

    public void search(String loginId){
        txtLoginID.sendKeys(loginId);
        btnAnalyse.click();
    }


}
