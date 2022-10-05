package backoffice.pages.bo.system;


import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import backoffice.controls.DropDownBox;
import backoffice.controls.bo.ATable;
import backoffice.controls.bo.StaticTable;
import org.openqa.selenium.Keys;
import backoffice.pages.bo._components.LeftMenu;

import java.util.List;

public class PrioritySettingsPage extends LeftMenu {
    public enum TYPE {SPORT, COMPETITION, MARKET,COUNTRYRACES}
    public com.paltech.element.common.DropDownBox ddbType = com.paltech.element.common.DropDownBox.xpath("//select[@name='types']");
    public Button btnSubmit = Button.name("submit");
    public DropDownBox ddbBrand = DropDownBox.xpath("//angular2-multiselect[@name='brands']//div[@class='cuppa-dropdown']","//ul[contains(@class,'lazyContainer')]//label");
   /* public RadioButton rbSport = RadioButton.id("sport");
    public RadioButton rbCompetition = RadioButton.id("competition");
    public RadioButton rbMarket = RadioButton.id("market");
    public RadioButton rbCountryRaces = RadioButton.id("country");*/

    public com.paltech.element.common.DropDownBox ddbSport = com.paltech.element.common.DropDownBox.name("sports");
    public TextBox txtSearch = TextBox.xpath("//div[contains(@class,'table-wrapper')]//div[contains(@class,'custom-table-body')]//input");
    private int totalColumns = 5;
    public int colEmpty = 1;
    public int colPriority = 2;
    public int colSportName =3;
    public int colLastUpdateBy = 4;
    public int colLastUpdateTime = 5;
    public int colCountryName = 4;
    public StaticTable tblPriority = StaticTable.xpath("//div[contains(@class,'table-wrapper')]","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalColumns);
    public StaticTable tblPriorityCountryRace = StaticTable.xpath("//div[contains(@class,'table-wrapper')]","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",6);
    public Label lblNote = Label.xpath("//div[@class='note']");

       public void selectType(String type){
       ddbType.selectByVisibleContainsText(type);
    }

    public void search(String type, String brand, String sport,  String searchValue){
        if(!type.isEmpty()) {
            selectType(type);
        }
        if(!brand.isEmpty())
        {
            ddbBrand.selectAll(true);
            waitSpinIcon();
            ddbBrand.deSelectAll(false);
            waitSpinIcon();
            ddbBrand.selectByVisibleText(brand,false);
            waitSpinIcon();
            ddbBrand.click();
        }
        if(!sport.isEmpty())
        {
            ddbSport.selectByVisibleText(sport);
            waitSpinIcon();
        }
        if(!searchValue.isEmpty()) {
            txtSearch.sendKeys(searchValue);
            txtSearch.type(false, Keys.ENTER);
            waitSpinIcon();
        }

    }

    public boolean verifyListPriority(List<String> prioritySetting, List<String> lstDisplay){
        int x = -1;
        for(String setting : prioritySetting){
            String marketName = setting.split("\\(")[0];
            int index = lstDisplay.indexOf(marketName.trim());
            if(index >= 0){
                if(index < x){
                    return false;
                }
                else {
                    x = index;
                }
            }
        }
        return true;
    }

}
