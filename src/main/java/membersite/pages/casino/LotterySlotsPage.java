package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Label;

import java.util.*;


public class LotterySlotsPage extends CasinoHomePage {

    public Label lblHeaderMenu = Label.xpath("//div[contains(@class,'lottery-game-menu')]//ul/li");
    Label lblBalance = Label.xpath("//div[(contains(@style,'Balance'))]//..//following-sibling::div//div[@dir='auto']");


    @Override
    public List<String> getListProductsMenu() {
        List<String> lblList = new ArrayList<>();
        try {
            new ArrayList<>(lblHeaderMenu.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        }catch (Exception e){
            System.out.println("DEBUG! Can not get list product");
        }
        return lblList;
    }

    @Override
    public int getListProductSize() {
        int listSize = 0;
        try {
            listSize = lblHeaderMenu.getWebElements().size();
        } catch (Exception e) {
            System.out.println("Products list size NOT FOUND");
        }
        return listSize;
    }

    @Override
    public void selectCasinoGame() {
        switchProductMenu("Table games");
        openGameByName("Baccarat");
    }

    private void switchProductMenu(String productName) {
        Label lblProduct = Label.xpath(String.format("//div[contains(@class,'lottery-game-menu')]//ul/li//a[text()='%s']",productName));
        lblProduct.click();
    }

    @Override
    public double getBalance() {
        lblBalance.waitForElementToBePresent(lblBalance.getLocator(), 5);
        return Double.valueOf(lblBalance.getText());
    }

    private void openGameByName(String name) {
        Label lblGameName = Label.xpath(String.format("//a[@class='game-element-container']//span[@class='game-name' and text()='%s']", name));
        lblGameName.scrollToThisControl(false);
        lblGameName.jsClick();
        waitToNewWindowOpen(6);
        DriverManager.getDriver().switchToWindow();
        waitUntilReadyState(6);
    }

}
