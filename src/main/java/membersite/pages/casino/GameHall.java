package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameHall extends CasinoHomePage{
    public Image imgLogo = Image.xpath("//div[@id='app']//header//a[contains(@class,'w-logo')]//img");
    private Button btnShowHideBalance = Button.xpath("//div[@id='app']//header//li[contains(@class,'justify-self-end')]//button");
    private Label lblBalance = Label.xpath("//div[@id='app']//header//li[@class='flex-center']/span[2]");
    String xpathProducts = "//div[@id='app']//div[@class='flex-center flex-wrap']//div[contains(@class,'text-platformName')]";
    public Link lnkProductsList = Link.xpath(xpathProducts);

    public List<String> getProductsList() {
        List<String> lblList = new ArrayList<>();
        new ArrayList<>(lnkProductsList.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        return lblList;
    }

    public void openRandomGame() {
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

    public double getCasinoBalance() {
        if(lblBalance.getText().contains("*")) {
            btnShowHideBalance.click();
        }
        return Double.valueOf(lblBalance.getText().replace(",",""));
    }
}
