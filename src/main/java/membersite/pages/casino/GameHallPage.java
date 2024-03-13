package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.concurrent.ThreadLocalRandom;

public class GameHallPage extends CasinoHomePage{
    public Image imgLogo = Image.xpath("//div[@id='app']//header//a[contains(@class,'w-logo')]//img");
    private Button btnShowHideBalance = Button.xpath("//div[@id='app']//header//li[contains(@class,'justify-self-end')]//button");
    private Label lblBalance = Label.xpath("//div[@id='app']//header//li[@class='flex-center']/span[2] | //div[@id='app']//header//li[@class='flex-center']/span[1]");
    String xpathProducts = "//div[@id='app']//div[@class='flex-center flex-wrap']//div[contains(@class,'text-platformName')]";
    private Label lblLoadingText = Label.xpath("//div[@class='loading-text']");
    public Link lnkProductsList = Link.xpath(xpathProducts);

    public void waitFrameLoad() {
        lblLoadingText.waitForControlInvisible(2, 7);
    }

    @Override
    public boolean verifyCasinoDisplay() {
        return isLogoDisplayed();
    }

    public boolean isLogoDisplayed() {
        switchToLastFrame();
        return imgLogo.isDisplayed();
    }

    @Override
    public void selectCasinoGame() {
        openRandomGame();
    }

    public void openRandomGame() {
        switchToLastFrame();
        int randomNum = ThreadLocalRandom.current().nextInt(1, lnkProductsList.getWebElements().size() + 1);
        BaseElement targetGame = new BaseElement(By.xpath(String.format("(%s)[%s]", xpathProducts, randomNum)));
        targetGame.scrollToThisControl(false);
        targetGame.click();
        if(DriverManager.getDriver().getWindowHandles().size() > 1) {
            waitToNewWindowOpen(6);
            DriverManager.getDriver().switchToWindow();
            //wait for website ready to get log console precisely
            waitUntilReadyState(6);
        }
    }

    @Override
    public int getListProductSize() {
        int listSize = 0;
        try {
            listSize = lnkProductsList.getWebElements().size();
        } catch (Exception e) {
            System.out.println("Products list size NOT FOUND");
        }
        return listSize;
    }

    @Override
    public double getBalance() {
        switchToLastFrame();
        if(lblBalance.getText().contains("*")) {
            btnShowHideBalance.jsClick();
        }
        return Double.valueOf(lblBalance.getText().replace(",",""));
    }

    @Override
    public void checkBalance(double actual, double expected, double BORate) {
        Assert.assertEquals(actual * BORate, expected, "FAILED! Balance of Casino game not equals to balance user");
    }
}
