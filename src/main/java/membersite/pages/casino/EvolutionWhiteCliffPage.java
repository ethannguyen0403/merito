package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.*;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EvolutionWhiteCliffPage extends CasinoHomePage{

    public Icon iconLogo = Icon.xpath("//*[@alt='casino-logo']");
    String imgItemsListXpath = "//*[contains(@data-role, 'grid-list-item')]";
    Label lblBalance = Label.xpath("//*[contains(@data-role,'balance') and not(contains(@class, 'title'))]");
    Button btnPlay = Button.xpath("//button[@data-role='play-button']");
    public EvolutionWhiteCliffPage() {
        // wait for iframe load
        try {
            Thread.sleep(5000);
            DriverManager.getDriver().switchToFrame(0);
            DriverManager.getDriver().switchToFrame(0);
        } catch (Exception e) {
        }
    }

    @Override
    public boolean verifyCasinoDisplay() {
        return iconLogo.isDisplayed();
    }

    @Override
    public void selectCasinoGame() {
        openGameByIndex("1");
    }

    public void openGameByIndex(String index) {
        int count = 5;
        try {
            // switch to subframe
            Thread.sleep(4000);
            DriverManager.getDriver().switchToFrame(0);
            DriverManager.getDriver().switchToFrame(0);
        } catch (Exception e) {
        }
        Image imgItem = Image.xpath(String.format("(%s)[%s]", imgItemsListXpath, index));
        imgItem.scrollToThisControl(true);
        do{
            imgItem.click();
            count--;
        }while (!btnPlay.isDisplayed() && count > 0);
        btnPlay.jsClick();
    }

    @Override
    public void checkBalance(double actual, double expected, double BORate) {
        Assert.assertEquals(actual * BORate, expected, "FAILED! Balance of Casino game not equals to balance user");
    }

    @Override
    public double getBalance() {
        int count = 5;
        while (!lblBalance.isDisplayed() && count >0){
            count--;
        }
        //Get all digit in String balance
        Pattern pattern = Pattern.compile("\\d.+");
        // handle for special Unicode char in balance String
        Matcher matcher = pattern.matcher(lblBalance.getText().replace("⁦⁦", "").replace("⁩", "").trim().replace(",", ""));
        matcher.find();
        return Double.valueOf(matcher.group(0));
    }
}
