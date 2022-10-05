package membersite.pages.all.tabexchangegame.controls;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import membersite.common.FEMemberConstants;
import membersite.controls.DropDownMenu;
import membersite.controls.aposta.MenuControl;
import membersite.controls.aposta.TimeZoneLabel;
import membersite.objects.AccountBalance;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import membersite.pages.all.beforelogin.popups.LoginPopup;
import membersite.pages.all.home.LandingPage;
import membersite.pages.all.tabexchange.AccountStatementPage;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.ProfitAndLossPage;
import membersite.pages.all.tabexchange.components.popups.ChangePasswordPopup;
import membersite.pages.aposta.MyBetsPage;

import java.util.List;

public class GameControl extends BaseElement {
    private String _xpath ;
    public Label lblGameTitle;
    public Image imgGame;
    public Button btnTurbo;
    public Button btnStandard;

    private GameControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
        lblGameTitle = Label.xpath(String.format("%s//p",_xpath));
        imgGame = Image.xpath(String.format("%s//img",_xpath));
        btnTurbo = Button.xpath(String.format("%s//button[contains(@class,'button-turbo')]",_xpath));
        btnStandard =  Button.xpath(String.format("%s//button[contains(@class,'button-standard')]",_xpath));
    }

    public static GameControl xpath(String xpathExpression) {
        return new GameControl(By.xpath(xpathExpression), xpathExpression);
    }

}
