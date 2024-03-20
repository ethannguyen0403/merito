package membersite.pages.casino;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.utils.WSUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.*;


public class LotterySlotsPage extends CasinoHomePage {

    public Label lblHeaderMenu = Label.xpath("//div[contains(@class,'lottery-game-menu')]//ul/li");
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

    @Override
    public double getBalance() {
        return getBalanceFromLogConsole(getConsoleLog("_url"));
    }

    @Override
    public void checkBalance(double actual, double expected, double BORate) {
        Assert.assertEquals(actual * BORate, expected, "FAILED! Balance of Casino game not equals to balance user");
    }

    public double getBalanceFromLogConsole(List<String> logConsole){
        double balance = -1;
        // console log contains url with response has balance info
        // extract url from console log
        String endpoint = logConsole.get(0).split("_url :>> ")[1].replace("\"", "").trim();
        Map<String, String> headersParam = new HashMap<String, String>() {
            {
                put("Accept", Configs.HEADER_JSON);
            }
        };
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithDynamicHeaders(endpoint, null, headersParam);
        if (Objects.nonNull(jsonObject)) {
            balance = jsonObject.getDouble("balance");
        }
        return balance;
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

}
