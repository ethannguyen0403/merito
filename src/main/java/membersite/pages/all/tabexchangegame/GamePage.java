package membersite.pages.all.tabexchangegame;

import com.paltech.constant.StopWatch;
import com.paltech.element.common.Label;
import org.openqa.selenium.NoSuchWindowException;
import membersite.pages.all.tabexchangegame.components.MainMenu;

public class GamePage extends MainMenu {
    private Label tblHeaderTitle = Label.xpath("//app-game-header//table[@class='head-table']//tr[1]/td");
    public Label lblGameStatus = Label.xpath("//div[contains(@class,'game-status')]//span[@class='mx-auto']");
    public String getGameHeaderTitle(){
        return tblHeaderTitle.getText().trim();
    }

    public void waitUntilGameAvailable(){

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        while(stopWatch.getElapsedTime() < 300000L) {
            try {
                if(!lblGameStatus.isDisplayed()){
                    return;
                }
                System.out.println("Bet Slip waiting status disable in "+ stopWatch.getElapsedTime());
                //lblGameStatus.isInvisible(1);
            } catch (Exception var4) {

            }
        }

    }
}
