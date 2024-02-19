package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.utils.StringUtils;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Pragmatic {

    public Label lblHeaderMenu = Label.xpath("//app-pragmatic//div[contains(@class, 'pragmatic-game-menu')]//ul/li");
    String xpathImageGameList = "//a[@class='game-element-container']";

    public List<String> getListHeaderMenu() {
        List<String> lblList = new ArrayList<>();
        new ArrayList<>(lblHeaderMenu.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        return lblList;
    }

    public void openRandomGame() {
        String index = StringUtils.generateNumeric(0, Label.xpath(xpathImageGameList).getWebElements().size());
        openGameByIndex(index);
    }

    public void openGameByIndex(String index) {
        BaseElement targetGame = new BaseElement(By.xpath(String.format("(%s)[%s]", xpathImageGameList, index)));
        targetGame.isDisplayed();
        targetGame.scrollToThisControl(false);
        targetGame.jsClick();
        waitToNewWindowOpen(6);
        DriverManager.getDriver().switchToWindow();
        //wait for website ready to get log console precisely
        waitUntilReadyState(6);
    }

    private void waitToNewWindowOpen(int timeCount) {
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

    private void waitUntilReadyState(int timeCount) {
        do {
            timeCount--;
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        } while (!DriverManager.getDriver().executeJavascripts("return document.readyState").equalsIgnoreCase("complete") && timeCount > 0);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }
}

