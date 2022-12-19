package membersite.pages.all.tabexchangegame.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

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
