package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiveDealerAsianPage extends CasinoHomePage {
    public Link lnkProductsList = Link.xpath("//app-super-spade//a");
    Label lblBalance = Label.xpath("//*[contains(@class, 'user-balance')]");
    String xpathProducts = "//app-super-spade//a";

    public double getBalance() {
        lblBalance.isDisplayed();
        //Get all digit in String balance
        Pattern pattern = Pattern.compile("\\d.+");
        Matcher matcher = pattern.matcher(lblBalance.getText().trim().replace(",", ""));
        matcher.find();
        return Double.valueOf(matcher.group(0));
    }
    public List<String> getProductsList() {
        List<String> lblList = new ArrayList<>();
        new ArrayList<>(lnkProductsList.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        return lblList;
    }

    public void openGameByIndex(String index) {
        BaseElement targetGame = new BaseElement(By.xpath(String.format("(%s)[%s]", xpathProducts, index)));
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
//                Thread.sleep(2000);
//            } catch (Exception e) {
//            }
//        } while (!DriverManager.getDriver().executeJavascripts("return document.readyState").equalsIgnoreCase("complete") && timeCount > 0);
//    }
}
