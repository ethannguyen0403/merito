package agentsite.pages.agentmanagement.createdownlineagent.accountbalancetransferconditionsection;

import com.paltech.element.common.*;
import org.testng.Assert;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;

public class AccountBalanceTransferConditionInforSection {
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

    public void verifyUIDisplayCorrect() {
        Assert.assertTrue(rdWeekly.isDisplayed(),"FAILED! Weekly radio button does not display");
        Assert.assertTrue(rdDaily.isDisplayed(),"FAILED! Daily radio button does not display");
        String[] namesOfDays = DateFormatSymbols.getInstance().getShortWeekdays();
        for (int i = 1; i < namesOfDays.length; i++) {
            CheckBox cbDayOfWeek = CheckBox.xpath(String.format(cbDaysOfWeekXpath,Arrays.asList(namesOfDays).get(i)));
            Assert.assertTrue(cbDayOfWeek.isDisplayed(), String.format("FAILED! Checkbox %s is not displayed", Arrays.asList(namesOfDays).get(i)));
        }
    }
}
