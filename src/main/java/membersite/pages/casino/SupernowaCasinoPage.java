package membersite.pages.casino;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.Label;
import com.paltech.utils.WSUtils;
import membersite.utils.casino.CasinoUtils;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static common.CasinoConstant.SUPERNOWA;

public class SupernowaCasinoPage extends CasinoHomePage {
    public Label lblTitle = Label.xpath("(//app-casino-games//div[@class='slider-header']//h4)[1]");
    public Label lblFirstGame = Label.xpath("(//h4[contains(text(), 'Supernowa')]/ancestor::div[@class='provider-list-slider']//div[@class='card-body'])[1]//a");
    private Label lblLoadingText = Label.xpath("//div[@class='loading-text']");
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

    @Override
    public double getBalance() {
        double balance = -1;
        String launchURL = CasinoUtils.getLaunchURLCasino("Supernowa Casino");
        String token = launchURL.split("Token=")[1];
        String urlCasino = launchURL.split("lobby?")[0];
        String endpoint = String.format("%sapi/Game/GetLobby?token=%s", urlCasino, token);
        Map<String, String> headersParam = new HashMap<String, String>() {
            {
                put("Accept", Configs.HEADER_JSON);
            }
        };
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithDynamicHeaders(endpoint, null, headersParam);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("playerDetail")) {
                JSONObject jsnPlayer = jsonObject.getJSONObject("playerDetail");
                balance = jsnPlayer.getDouble("balance");
            }
        }
        return balance;
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
