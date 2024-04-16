package membersite.pages.components.ps38preferences;

import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import membersite.pages.HomePage;

public class PS38PreferencesPopup {
    DropDownBox ddbOddsType = DropDownBox.id("pS38OddsType");
    DropDownBox ddbPage = DropDownBox.id("pS38DefaultPage");
    DropDownBox ddbView = DropDownBox.id("pS38DefaultView");
    DropDownBox ddbBetterOdd = DropDownBox.id("pS38AcceptBetterOdds");
    DropDownBox ddbLanguage = DropDownBox.id("pS38DefaultLanguage");
    Button btnSave = Button.xpath("//app-user-configure-dialog//button[@class='save-button']");
    public PS38PreferencesPopup() {
        //wait for element completely visible in view port
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

    public void selectPreferences(String oddsType, String defaultPage, String defaultView, String acceptBetterOdds,
                                  String defaultLanguage) {
        if (!oddsType.isEmpty()) {
            ddbOddsType.selectByVisibleText(oddsType);
        }
        if (!defaultPage.isEmpty()) {
            ddbPage.selectByVisibleText(defaultPage);
        }
        if (!defaultView.isEmpty()) {
            ddbView.selectByVisibleText(defaultView);
        }
        if (!acceptBetterOdds.isEmpty()) {
            ddbBetterOdd.selectByVisibleText(acceptBetterOdds);
        }
        if (!defaultLanguage.isEmpty()) {
            ddbLanguage.selectByVisibleText(defaultLanguage);
        }
        btnSave.click();
        HomePage.waitPageLoad();
    }

}
