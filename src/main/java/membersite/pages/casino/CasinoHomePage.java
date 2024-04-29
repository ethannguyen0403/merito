package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;

import java.util.*;
import java.util.logging.Level;

public class CasinoHomePage {

    public double getBalance() {
        return -1;
    }

    public void checkBalance(double actual, double expected, double BORate) {
        Assert.assertEquals(actual * BORate, expected, "FAILED! Balance of Casino game not equals to balance user");
    }

    public List<String> getListProductsMenu() {
        return null;
    }

    public void selectCasinoGame() {
    }

    public boolean verifyCasinoDisplay() {
        return false;
    }

    public int getListProductSize() {
        return -1;
    }

    public boolean verifyConsoleLogNotContainValue(List<String> values) {
        List<LogEntry> entriesConsole = DriverManager.getDriver().manage().logs().get(LogType.BROWSER).getAll();
        for (String value : values) {
            for (LogEntry entry : entriesConsole) {
                // Get logs that are the errors(WARNING, SEVERE)
                if(entry.getLevel().equals(Level.SEVERE)){
                    if (entry.getMessage().contains(value)) {
                        System.out.println("Log console contains the value: " + value);
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public List<String> getConsoleLog(String value){
        List<String> consoleLog = new ArrayList<>();
        try{
            Thread.sleep(3000);
        }catch (Exception e){
        }
        List<LogEntry> entriesConsole = DriverManager.getDriver().manage().logs().get(LogType.BROWSER).getAll();
        for (LogEntry entry : entriesConsole) {
            if (entry.getMessage().contains(value)) {
                consoleLog.add(entry.getMessage());
            }
        }
        return consoleLog;
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

    protected void switchToLastFrame() {
        int countIframesInPage = DriverManager.getDriver().findElements(By. tagName("iframe")). size();
        DriverManager.getDriver().switchToFrame(countIframesInPage-1);
    }

}
