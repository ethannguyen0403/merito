package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;

import java.util.ArrayList;
import java.util.List;

public class Evolution extends CasinoHomePage {

    public Label lblProducts = Label.xpath("//div[contains(@class, 'menu-product')]//a");
    public Tab tabEvolution =
            Tab.xpath(String.format("//div[contains(@class, 'european-room')]//span[text()='%s']", CasinoProduct.EVOLUTION.toString()));
    Label lblBalance = Label.xpath("//div[contains(@class, 'Balance-')]//span");

    public List<String> getListProductsMenu() {
        List<String> lblList = new ArrayList<>();
        new ArrayList<>(lblProducts.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        return lblList;
    }

    public void selectProduct(String product) {
        Label lblProduct = Label.xpath(String.format("//div[contains(@class, 'menu-product')]//a[contains(., '%s')]", product));
        lblProduct.click();
        waitToNewWindowOpen(6);
        DriverManager.getDriver().switchToWindow();
        //wait for website ready to get log console precisely
        waitUntilReadyState(6);
    }

    public double getBalance() {
        DriverManager.getDriver().switchToFrame(0);
        lblBalance.isDisplayed();
        return Double.valueOf(lblBalance.getText().replace("$", ""));
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
//                Thread.sleep(2000);
//            } catch (Exception e) {
//            }
//        } while (!DriverManager.getDriver().executeJavascripts("return document.readyState").equalsIgnoreCase("complete") && timeCount > 0);
//    }
}
