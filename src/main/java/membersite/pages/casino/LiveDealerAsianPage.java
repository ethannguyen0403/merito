package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiveDealerAsianPage extends CasinoHomePage {
    public Link lnkProductsList = Link.xpath("//app-super-spade//a");
    Label lblBalance = Label.xpath("//*[contains(@class, 'user-balance')]");
    String xpathProducts = "//app-super-spade//a";

    @Override
    public double getBalance() {
        lblBalance.isDisplayed();
        //Get all digit in String balance
        Pattern pattern = Pattern.compile("\\d.+");
        Matcher matcher = pattern.matcher(lblBalance.getText().trim().replace(",", ""));
        matcher.find();
        return Double.valueOf(matcher.group(0));
    }

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
        openGameByIndex("1");
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

}
