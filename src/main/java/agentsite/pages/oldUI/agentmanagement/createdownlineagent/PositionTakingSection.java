package agentsite.pages.oldUI.agentmanagement.createdownlineagent;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import agentsite.controls.Table;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class PositionTakingSection extends BaseElement {
    private String _xPath;
    public Label lblHeaderTitle;
    int totalColumn = 8;
    int colTitle = 1;
    int colSoccer = 2;
    int colCricket = 3;
    int colFancy= 4;
    int colTennis = 5;
    int colBaseketball =6;
    int colRacing =6;
    int colOther = 8;
    public Table tblPositionTaking ;

    public PositionTakingSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblHeaderTitle = Label.xpath(String.format("%s/div[@class='psection']", _xPath));
        tblPositionTaking = Table.xpath(String.format("%s//table", _xPath), totalColumn);
    }

    public static PositionTakingSection xpath(String xpathExpression) {
        return new PositionTakingSection(By.xpath(xpathExpression), xpathExpression);
    }
    public String getTitle(){
        return lblHeaderTitle.getText();
    }
    public void inputBetSetting(List<ArrayList<String>> lstSetting){
        TextBox txtSport;
        int n = lstSetting.size();
        for(int i = 0; i<n; i++){
            for (int j = 1; j<lstSetting.get(i).size();j++){
                if(!(lstSetting.get(i).get(j)).isEmpty()){
                txtSport = TextBox.xpath(tblPositionTaking.getxPathOfCell(1,j+1,i+1,"input"));
                txtSport.sendKeys(lstSetting.get(i).get(j));
                }
            }
        }
    }

    public List<ArrayList<String>> getBetSettingValidationValueLst(String currecyCode){
     String value;
        List<ArrayList<String>> betSettingValidateion = tblPositionTaking.getRowsWithoutHeader(false);
        for( int i = 0 ; i< betSettingValidateion.size();i++)
        {
            for(int j = 1; j<betSettingValidateion.get(i).size(); j++){
                value = betSettingValidateion.get(i).get(j).split(currecyCode)[1].trim();
                betSettingValidateion.get(i).set(j,value);
            }
        }
        return betSettingValidateion;
    }

}
