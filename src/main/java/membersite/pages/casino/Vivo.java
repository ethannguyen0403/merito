package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Vivo extends CasinoHomePage{
    String xpathProducts = "//div[@id='games-view']//div[@class='table game-thumb']";
    private Label lblBalanceValue = Label.xpath("//div[@class='user-balance-button']//span[2]");
    public Link lnkHeaderProductsLst = Link.xpath("//div[@id='categories-view']//div[@class='category ui-button selected' or @class='category ui-button']");
    Link lnkGameLst = Link.xpath(xpathProducts);
    Link lnkLimitFirstRow = Link.xpath("//div[@class='table-limits']/div[1]");
    Label lblBalance = Label.xpath("//div[@id='header-view']//div[@class='user-balance-button']//span[2]");
    private Image imgSpinner = Image.xpath("//div[@id='loader-screen-view']");
    public void waitFrameLoad() {
        imgSpinner.waitForControlInvisible(2, 7);
    }

    public List<String> getProductsList() {
        switchToLastFrame();
        List<String> lblList = new ArrayList<>();
        new ArrayList<>(lnkHeaderProductsLst.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        return lblList;
    }

    public void openRandomGame() {
        switchToLastFrame();
        int randomNum = ThreadLocalRandom.current().nextInt(1, lnkGameLst.getWebElements().size() + 1);
        BaseElement targetGame = new BaseElement(By.xpath(String.format("(%s)[%s]", xpathProducts, randomNum)));
        targetGame.scrollToThisControl(false);
        targetGame.click();
        if(lnkLimitFirstRow.isDisplayed()) {
            lnkLimitFirstRow.click();
        }
        if(DriverManager.getDriver().getWindowHandles().size() > 1) {
            waitToNewWindowOpen(6);
            DriverManager.getDriver().switchToWindow();
            //wait for website ready to get log console precisely
            waitUntilReadyState(6);
        }
    }

    public double getCasinoBalance() {
        switchToLastFrame();
        return Double.valueOf(lblBalance.getText().replace(",",""));
    }
}
