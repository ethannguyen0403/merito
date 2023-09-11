package agentsite.pages.agentmanagement.createdownlineagent.accountbalancetransferconditionsection;

import com.paltech.element.common.*;

import java.util.List;

public class AccountBalanceTransferConditionInforSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    RadioButton rdDaily = RadioButton.id("daily");
    RadioButton rdWeekly = RadioButton.id("weekly");
    String cbDaysOfWeekXpath = "//label[text()=' %s']//input";
    public String getAccountBalanceTransferConditionTitle() { return "";}

    public void setTransferPeriod(boolean isDaily, List<String> lstDaysOfWeek) {
        if (isDaily) {
            rdDaily.click();
        } else {
            rdWeekly.click();
            if(!lstDaysOfWeek.isEmpty()) {
                for (int i = 0; i < lstDaysOfWeek.size(); i++) {
                    String xpath = String.format(cbDaysOfWeekXpath, lstDaysOfWeek.get(0));
                    CheckBox cbDayOfWeek = CheckBox.xpath(xpath);
                    cbDayOfWeek.click();
                }
            }
        }
    }
}
