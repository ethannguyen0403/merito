package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;
import java.util.Set;

public class CasinoPage {
    public boolean verifyConsoleLogNotContainValue(List<String> values) {
        List<LogEntry> entriesConsole = DriverManager.getDriver().manage().logs().get(LogType.BROWSER).getAll();
        for (String value : values) {
            for (LogEntry entry : entriesConsole) {
                if (entry.getMessage().contains(value)) {
                    System.out.println("Log console contains the value: " + value);
                    return false;
                }
            }
        }
        return true;
    }

    protected void waitToNewWindowOpen(int timeCount) {
        int windowSize = 1;
        while (windowSize == 1 && timeCount > 0) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            Set<String> handles = DriverManager.getDriver().getWindowHandles();
            windowSize = handles.size();
            timeCount--;
        }
    }

    protected void waitUntilReadyState(int timeCount) {
        do {
            timeCount--;
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        } while (!DriverManager.getDriver().executeJavascripts("return document.readyState").equalsIgnoreCase("complete") && timeCount > 0);
    }
}
