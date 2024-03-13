package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LiveDealerEuropeanPage extends CasinoHomePage{
    String xpathProducts = "//app-ezugi//div[contains(@class,'menu-product')]//a";
    public Link lnkProductsList = Link.xpath(xpathProducts);
    Label lblBalance = Label.xpath("//span[@data-e2e='balance']");

    @Override
    public List<String> getListProductsMenu() {
        List<String> lblList = new ArrayList<>();
        try {
            new ArrayList<>(lnkProductsList.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        }catch (Exception e){
            System.out.println("DEBUG! Can not get list product");
        }
        return lblList;
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
    public void selectCasinoGame() {
        openRandomGame();
    }

    public void openRandomGame() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, lnkProductsList.getWebElements().size() + 1);
        BaseElement targetGame = new BaseElement(By.xpath(String.format("(%s)[%s]", xpathProducts, randomNum)));
        targetGame.scrollToThisControl(false);
        targetGame.click();
        targetGame.jsClick();
        if(DriverManager.getDriver().getWindowHandles().size() > 1) {
            waitToNewWindowOpen(6);
            DriverManager.getDriver().switchToWindow();
            //wait for website ready to get log console precisely
            waitUntilReadyState(6);
        }
    }

    public void openGameByIndex(int index) {
        BaseElement targetGame = new BaseElement(By.xpath(String.format("(%s)[%s]", xpathProducts, index)));
        targetGame.isDisplayed();
        targetGame.scrollToThisControl(false);
        targetGame.click();
        if(DriverManager.getDriver().getWindowHandles().size() > 1) {
            waitToNewWindowOpen(6);
            DriverManager.getDriver().switchToWindow();
            //wait for website ready to get log console precisely
            waitUntilReadyState(6);
        }
    }

    public double getBalance() {
        return Double.valueOf(lblBalance.getAttribute("data-value").replace(",",""));
    }

}
