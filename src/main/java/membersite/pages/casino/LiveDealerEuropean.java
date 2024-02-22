package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class LiveDealerEuropean extends CasinoPage{
    String xpathProducts = "//app-ezugi//div[contains(@class,'menu-product')]//a";
    Link lnkProductsList = Link.xpath(xpathProducts);
    Label lblBalance = Label.xpath("//span[@data-e2e='balance']");

//    public LiveDealerEuropean(String types, CasinoProduct product) {
//        super(types, product);
//    }

    public List<String> getProductsList() {
        List<String> lblList = new ArrayList<>();
        new ArrayList<>(lnkProductsList.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        return lblList;
    }

    public void openGameByIndex(String index) {
        BaseElement targetGame = new BaseElement(By.xpath(String.format("(%s)[%s]", xpathProducts, index)));
        targetGame.isDisplayed();
        targetGame.scrollToThisControl(false);
        targetGame.click();
        waitToNewWindowOpen(6);
        DriverManager.getDriver().switchToWindow();
        //wait for website ready to get log console precisely
        waitUntilReadyState(6);
    }

    public String getUserBalance() {
        return lblBalance.getAttribute("data-value").replace(",","");
    }
}
