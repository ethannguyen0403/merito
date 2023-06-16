package agentsite.pages.agentmanagement.createdownlineagent;

import com.paltech.element.BaseElement;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.RadioButton;
import org.openqa.selenium.By;

import java.util.List;

public class TransferSettingSection extends BaseElement {
    private String _xPath ="//div[@id='transfer-settings']";
    private Label lblTitle;
    private RadioButton rbDaily;
    private RadioButton rbWeekly;
    private CheckBox cbMon;
    private CheckBox cbTue;
    private CheckBox cbSat;
    private CheckBox cbWed;
    private CheckBox cbThu;
    private CheckBox cbFri;
    private CheckBox cbSun;

    public TransferSettingSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblTitle = Label.xpath(String.format("%s//div[@class='psection']",_xPath));
        rbDaily= RadioButton.id("daily");
        rbWeekly= RadioButton.id("weekly");
        cbMon = CheckBox.xpath(String.format("%s//div[@class='transfer']/div//input[2]",_xPath));
        cbTue = CheckBox.xpath(String.format("%s//div[@class='transfer']/div//input[3]",_xPath));
        cbWed = CheckBox.xpath(String.format("%s//div[@class='transfer']/div//input[4]",_xPath));
        cbThu = CheckBox.xpath(String.format("%s//div[@class='transfer']/div//input[5]",_xPath));
        cbFri = CheckBox.xpath(String.format("%s//div[@class='transfer']/div//input[6]",_xPath));
        cbSat = CheckBox.xpath(String.format("%s//div[@class='transfer']/div//input[7]",_xPath));
        cbMon = CheckBox.xpath(String.format("%s//div[@class='transfer']/div//input[8]",_xPath));

    }

    public static TransferSettingSection xpath(String xpathExpression) {
        return new TransferSettingSection(By.xpath(xpathExpression), xpathExpression);
    }

    public String getTitle(){
        return lblTitle.getText();
    }
    public void setTransfer(boolean isDaily, List<String> daysinWeek){
        if(isDaily){
            rbDaily.click();
        }
        for (String day: daysinWeek) {
            clickDay(day);
        }
    }
    private void clickDay(String date){
        switch (date){
            case "Mon":
                cbMon.click();
                return;
            case "Tue":
                cbTue.click();
                return;
            case "Wed":
                cbWed.click();
                return;
            case "Thu":
                cbThu.click();
                return;
            case "Fri":
                cbFri.click();
                return;
            case "Sat":
                cbSat.click();
                return;
            case "Sun":
                cbSun.click();
                return;
        }
    }

}
