package backoffice.pages.bo.system;


import backoffice.controls.DropDownBox;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo._components.LeftMenu;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.testng.Assert;

import java.util.*;

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
            //remove all selected default brand on page
            ddbBrand.selectAll(true);
            waitSpinIcon();
            ddbBrand.deSelectAll(true);
            waitSpinIcon();
            ddbBrand.selectByVisibleText(brand, true);
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

    public Map<Integer, List<String>> getPriorityOfSport() {
        Map<Integer, List<String>> prioritySport = new LinkedHashMap<>();
        List<String> orderPriority = tblPriority.getColumn(colPriority, true);
        List<String> orderSport = tblPriority.getColumn(colSportName, true);
        orderPriority.remove(0);
        orderSport.remove(0);
        if (orderPriority.size() == orderSport.size()) {
            for (int i = 0; i < orderPriority.size(); i++) {
                //sometime have sport has the same priority
                if (prioritySport.get(Integer.valueOf(orderPriority.get(i))) == null) {
                    prioritySport.put(Integer.valueOf(orderPriority.get(i)), new ArrayList<>(Arrays.asList(orderSport.get(i))));
                }else {
                    prioritySport.get(Integer.valueOf(orderPriority.get(i))).add(orderSport.get(i));
                }
            }
            return prioritySport;
        }
        System.out.println("ERROR! List of priority and list of sport is not equals.");
        return null;
    }

    public void verifyPriorityOfSport(List<String> actualSport, Map<Integer, List<String>> prioritySport, int limit) {
        int countSport = 0;
        //sometime have multi sport/competition has the same priority
        for (Map.Entry<Integer, List<String>> entry : prioritySport.entrySet()) {
            if (countSport == limit) {
                break;
            }
            if (entry.getValue().size() > 1) {
                int count = 0;
                for (int y = countSport; y < entry.getValue().size(); y++) {
                    Assert.assertEquals(actualSport.get(y), entry.getValue().get(count),
                            "FAILED! The priority is not correct. Index: " + entry.getKey());
                    count++;
                }
            } else {
                Assert.assertEquals(actualSport.get(countSport), entry.getValue().get(0),
                        "FAILED! The priority is not correct. Index: " + entry.getKey());
            }
            countSport++;
        }
    }

    public enum TYPE {SPORT, COMPETITION, MARKET, COUNTRYRACES}

}
