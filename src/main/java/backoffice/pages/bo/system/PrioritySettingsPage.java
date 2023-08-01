package backoffice.pages.bo.system;


import backoffice.controls.DropDownBox;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo._components.LeftMenu;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

import java.util.List;

public class PrioritySettingsPage extends LeftMenu {
    public com.paltech.element.common.DropDownBox ddbType = com.paltech.element.common.DropDownBox.xpath("//app-priority-setting//select[@name='types']");
    public Button btnSubmit = Button.name("submit");
    public DropDownBox ddbBrand = DropDownBox.xpath("//angular2-multiselect[@name='brands']//div[@class='cuppa-dropdown']", "//ul[contains(@class,'lazyContainer')]//label");
    public com.paltech.element.common.DropDownBox ddbSport = com.paltech.element.common.DropDownBox.xpath("//app-priority-setting//select[@name='sports']");
   /* public RadioButton rbSport = RadioButton.id("sport");
    public RadioButton rbCompetition = RadioButton.id("competition");
    public RadioButton rbMarket = RadioButton.id("market");
    public RadioButton rbCountryRaces = RadioButton.id("country");*/
    public TextBox txtSearch = TextBox.xpath("//div[contains(@class,'table-wrapper')]//div[contains(@class,'custom-table-body')]//input");
    public int colEmpty = 1;
    public int colPriority = 2;
    public int colMarketName = 3;
    public int colSportName = 3;
    public int colLastUpdateBy = 4;
    public int colLastUpdateTime = 5;
    public int colCountryName = 4;
    public StaticTable tblPriorityCountryRace = StaticTable.xpath("//div[contains(@class,'table-wrapper')]", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", 6);
    public Label lblNote = Label.xpath("//div[@class='note']");
    private int totalColumns = 5;
    public StaticTable tblPriority = StaticTable.xpath("//div[contains(@class,'table-wrapper')]", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumns);

    public void selectType(String type) {
        ddbType.selectByVisibleContainsText(type);
    }

    public void search(String type, String brand, String sport, String searchValue) {
        if (!type.isEmpty()) {
            selectType(type);
        }
        if (!brand.isEmpty()) {
            ddbBrand.selectAll(true);
            waitSpinIcon();
            ddbBrand.deSelectAll(false);
            waitSpinIcon();
            ddbBrand.selectByVisibleText(brand, false);
            waitSpinIcon();
            ddbBrand.click();
        }
        if (!sport.isEmpty()) {
            ddbSport.selectByVisibleText(sport);
            waitSpinIcon();
        }
        if (!searchValue.isEmpty()) {
            txtSearch.sendKeys(searchValue);
        }
        tblPriority.click();
        waitSpinIcon();

    }

    public boolean verifyListPriority(List<String> prioritySetting, List<String> lstDisplay) {
        int x = -1;
        for (String setting : prioritySetting) {
            String marketName = setting.split("\\(")[0];
            int index = lstDisplay.indexOf(marketName.trim());
            if (index >= 0) {
                if (index < x) {
                    return false;
                } else {
                    x = index;
                }
            }
        }
        return true;
    }

    public boolean isMarketNameDisplay(String marketType){
        List<String> lstMarket = tblPriority.getColumn(colMarketName, false);
        for (int i = 0; i < lstMarket.size(); i++){
            if (lstMarket.get(i).equals(marketType)){
                return true;
            }
        }
        System.out.println(marketType + " not display");
        return false;
    }

    public boolean isCountryNameDisplay(String country){
        List<String> lstCountry = tblPriorityCountryRace.getColumn(colCountryName, false);
        for (int i = 0; i < lstCountry.size(); i++){
            if (lstCountry.get(i).equals(country)){
                return true;
            }
        }
        System.out.println(country + " not display");
        return false;
    }
    public enum TYPE {SPORT, COMPETITION, MARKET, COUNTRYRACES}

}
