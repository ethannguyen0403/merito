package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import membersite.utils.casino.PragmaticUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class PragmaticPage extends CasinoHomePage {

    public Label lblHeaderMenu = Label.xpath("//app-pragmatic//div[contains(@class, 'pragmatic-game-menu')]//ul/li");
    String xpathImageGameList = "//a[@class='game-element-container']";

    @Override
    public List<String> getListProductsMenu() {
        List<String> lblList = new ArrayList<>();
        new ArrayList<>(lblHeaderMenu.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        return lblList;
    }

    @Override
    public int getListProductSize() {
        return lblHeaderMenu.getWebElements().size();
    }

    @Override
    public void selectCasinoGame() {
        openGameByIndex("1");
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

    @Override
    public double getBalance() {
       return PragmaticUtils.getBalance();
    }

    @Override
    public void checkBalance(double actual, double expected, double BORate) {
        Assert.assertEquals(actual * BORate, expected, "FAILED! Balance of Casino game not equals to balance user");
    }
}

