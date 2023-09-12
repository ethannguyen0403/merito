package agentsite.pages.agentmanagement.createdownlineagent.transfersettingsection;

import com.paltech.element.BaseElement;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.RadioButton;
import org.openqa.selenium.By;

import java.util.List;

public class TransferSettingSection {
//    private String _xPath = "//div[@id='transfer-settings']";
//    private Label lblTitle = Label.xpath("//div[@id='transfer-settings']//div[@class='psection']");
    private RadioButton rbDaily = RadioButton.id("daily");
    private RadioButton rbWeekly = RadioButton.id("weekly");;
    private CheckBox cbMon = CheckBox.xpath("//div[@id='transfer-settings']//div[@class='transfer']/div//input[2]");
    private CheckBox cbTue = CheckBox.xpath("//div[@id='transfer-settings']//div[@class='transfer']/div//input[3]");
    private CheckBox cbWed = CheckBox.xpath("//div[@id='transfer-settings']//div[@class='transfer']/div//input[4]");
    private CheckBox cbThu = CheckBox.xpath("//div[@id='transfer-settings']//div[@class='transfer']/div//input[5]");
    private CheckBox cbFri = CheckBox.xpath("//div[@id='transfer-settings']//div[@class='transfer']/div//input[6]");
    private CheckBox cbSat = CheckBox.xpath("//div[@id='transfer-settings']//div[@class='transfer']/div//input[7]");
    private CheckBox cbSun = CheckBox.xpath("//div[@id='transfer-settings']//div[@class='transfer']/div//input[8]");

    public void setTransfer(boolean isDaily, List<String> daysinWeek) {
        if (isDaily) {
            rbDaily.click();
        }
        for (String day : daysinWeek) {
            clickDay(day);
        }
    }

    private void clickDay(String date) {
        switch (date) {
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
        }
    }

}
