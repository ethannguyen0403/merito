package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Label;

public class SupernowaCasinoPage extends CasinoHomePage {
    public Label lblTitle = Label.xpath("(//app-casino-games//div[@class='slider-header']//h4)[1]");
    public Label lblFirstGame = Label.xpath("(//h4[contains(text(), 'Supernowa')]/ancestor::div[@class='provider-list-slider']//div[@class='card-body'])[1]//a");
    private Label lblLoadingText = Label.xpath("//div[@class='loading-text']");
    private Label lblBalance = Label.xpath("//div[@class='dropdown profile-dropdown']//span[2]");
    public SupernowaCasinoPage() {
        // wait for iframe load
        try {
            Thread.sleep(3000);
            DriverManager.getDriver().switchToFrame(0);
        }catch (Exception e){
        }
    }

    public void openFirstSupernowaGame(){
        lblFirstGame.click();
        lblFirstGame.jsClick();
        // wait for game loading on screen
        try {
            Thread.sleep(4000);
        }catch (Exception e){
        }
    }

    public double getBalance() {
        waitFrameLoad();
        String balanceText = lblBalance.getText().trim().split(" ")[1].replace(",","");
        return Double.valueOf(balanceText);
    }

    @Override
    public void selectCasinoGame() {
        openFirstSupernowaGame();
    }

    @Override
    public boolean verifyCasinoDisplay() {
        return lblTitle.isDisplayed();
    }
    public void waitFrameLoad() {
        lblLoadingText.waitForControlInvisible(2, 7);
    }
}
