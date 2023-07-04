package agentsite.pages;

import com.paltech.driver.DriverManager;


public class BasePage {
    public final int timeOutShortInSeconds = 3;

    public String getPageUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public String getBrowserTitle() {
        return DriverManager.getDriver().getTitle();
    }

    public void closeBrowserTab() {
        DriverManager.getDriver().close();
        DriverManager.getDriver().switchTo().window(DriverManager.getDriver().getWindowHandle());
    }

    public void switchToPreviousTab() {
        String wh1 = DriverManager.getDriver().getWindowHandle();
        for (String handle : DriverManager.getDriver().getWindowHandles()) {
            if (!handle.equals(wh1)) {
                DriverManager.getDriver().switchTo().window(handle);
            }
        }
    }


}
