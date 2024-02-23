package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Label;

public class SupernowaCasino {
    public Label lblTitle = Label.xpath("(//app-casino-games//div[@class='slider-header']//h4)[1]");
    public Label lblFirstGame = Label.xpath("(//h4[contains(text(), 'Supernowa')]/ancestor::div[@class='provider-list-slider']//div[@class='card-body'])[1]//a");
    public SupernowaCasino() {
        // wait for iframe load
        try {
            Thread.sleep(3000);
            DriverManager.getDriver().switchToFrame(0);
        }catch (Exception e){
        }
    }

    public void openFirstSupernowaGame(){
        lblFirstGame.jsClick();
        // wait for game loading on screen
        try {
            Thread.sleep(4000);
        }catch (Exception e){
        }
    }
}
