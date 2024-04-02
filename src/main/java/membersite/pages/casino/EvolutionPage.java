package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Label;
import com.paltech.element.common.Tab;
import org.testng.Assert;

import static common.CasinoConstant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvolutionPage extends CasinoHomePage {

    public Label lblProducts = Label.xpath("//div[contains(@class, 'menu-product')]//a");
    public Tab tabEvolution =
            Tab.xpath(String.format("//div[contains(@class, 'european-room')]//span[text()='%s']", EVOLUTION));
    Label lblBalance = Label.xpath("//div[contains(@class, 'Balance-')]//span");

    public List<String> getListProductsMenu() {
        List<String> lblList = new ArrayList<>();
        try {
            new ArrayList<>(lblProducts.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        }catch (Exception e){
            System.out.println("DEBUG! Can not get list product");
        }
        return lblList;
    }

    public void selectCasinoGame() {
        List<String> product = getListProductsMenu();
        Label lblProduct = Label.xpath(String.format("//div[contains(@class, 'menu-product')]//a[contains(., '%s')]", product.get(0)));
        lblProduct.click();
        waitToNewWindowOpen(6);
        DriverManager.getDriver().switchToWindow();
        //wait for website ready to get log console precisely
        waitUntilReadyState(6);
    }

    public double getBalance() {
        DriverManager.getDriver().switchToFrame(0);
        lblBalance.isDisplayed();
        //Get all digit in String balance
        Pattern pattern = Pattern.compile("\\d.+");
        Matcher matcher = pattern.matcher(lblBalance.getText().trim().replace(",", ""));
        matcher.find();
        return Double.valueOf(matcher.group(0));
    }
}
