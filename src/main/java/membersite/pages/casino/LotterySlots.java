package membersite.pages.casino;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.utils.StringUtils;
import com.paltech.utils.WSUtils;
import org.json.JSONObject;
import org.openqa.selenium.By;

import java.util.*;


public class LotterySlots extends CasinoHomePage {

    public Label lblHeaderMenu = Label.xpath("//div[contains(@class,'lottery-game-menu')]//ul/li");
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

//    private void waitToNewWindowOpen(int timeCount) {
//        int windowSize = 1;
//        while (windowSize == 1 && timeCount > 0) {
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//            }
//            Set<String> handles = DriverManager.getDriver().getWindowHandles();
//            windowSize = handles.size();
//            timeCount--;
//        }
//    }
//
//    private void waitUntilReadyState(int timeCount) {
//        do {
//            timeCount--;
//            try {
//                Thread.sleep(4000);
//            } catch (Exception e) {
//            }
//        } while (!DriverManager.getDriver().executeJavascripts("return document.readyState").equalsIgnoreCase("complete") && timeCount > 0);
//    }
}
