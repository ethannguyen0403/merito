package agentsite.pages.all.components;

import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;


public class BasePage {
    public final int timeOutShortInSeconds = 3;
    private Icon iconLoadSpinner1 = Icon.xpath("//div[contains(@class, 'load-spinner')]");
    public Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public Label lblTitle = Label.xpath("//div[@id='main-page']/div[contains(@class, 'title')]");
    public Label lblPageTitle = Label.xpath("//app-title-dashboard//div[contains(@class, 'title')]");

    public String getPageTitle() {
        return lblPageTitle.getText().trim();
    }

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(1, 1);
    }

}
