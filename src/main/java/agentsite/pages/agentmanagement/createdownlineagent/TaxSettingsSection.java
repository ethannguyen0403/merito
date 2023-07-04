package agentsite.pages.agentmanagement.createdownlineagent;

import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class TaxSettingsSection extends BaseElement {
    public Label lblHeaderTitle;
    public Table tblTaxSetting;
    int totalColumn = 7;
    int colTitle = 1;
    int colSoccer = 2;
    int colCricket = 3;
    int colTennis = 4;
    int colBaseketball = 5;
    int colFancy = 6;
    int colOther = 7;
    private String _xPath;

    public TaxSettingsSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblHeaderTitle = Label.xpath(String.format("%s/div[@class='psection']", _xPath));
        tblTaxSetting = Table.xpath(String.format("%s//table[contains(@class,'betTable')]", _xPath), totalColumn);
    }

    public static TaxSettingsSection xpath(String xpathExpression) {
        return new TaxSettingsSection(By.xpath(xpathExpression), xpathExpression);
    }

    public String getTitle() {
        return lblHeaderTitle.getText();
    }

    public void inputBetSetting(List<ArrayList<String>> lstSetting) {
        TextBox txtSport;
        int n = lstSetting.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < lstSetting.get(i).size(); j++) {
                if (!(lstSetting.get(i).get(j)).isEmpty()) {
                    txtSport = TextBox.xpath(tblTaxSetting.getxPathOfCell(1, j + 1, i + 1, "input"));
                    txtSport.sendKeys(lstSetting.get(i).get(j));
                }
            }
        }
    }

    public List<ArrayList<String>> getBetSettingValidationValueLst(String currecyCode) {
        String value;
        List<ArrayList<String>> betSettingValidateion = tblTaxSetting.getRowsWithoutHeader(false);
        for (int i = 0; i < betSettingValidateion.size(); i++) {
            for (int j = 1; j < betSettingValidateion.get(i).size(); j++) {
                value = betSettingValidateion.get(i).get(j).split(currecyCode)[1].trim();
                betSettingValidateion.get(i).set(j, value);
            }
        }
        return betSettingValidateion;
    }

}
