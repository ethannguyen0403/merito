package agentsite.pages.agentmanagement.proteus.createdownlineagent;


import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.*;

public class BetSettingSectionPS38 {

    public CheckBox chkTabPS38 = CheckBox.xpath("//app-proteus-setting//input[@type='checkbox']");
    public Label lblCheckboxPS38 = Label.xpath("//app-proteus-setting//div[@class='pl-2 py-2']");
    public DropDownBox
            ddbSportsPS38 = DropDownBox.xpath("//select[contains(@class, 'sport-select')]");
    public DropDownBox
            ddbLeaguePS38 = DropDownBox.xpath("//select[contains(@class, 'leagues-select')]");
    public Button btnView = Button.id("viewBtn");
    public Button btnAdd = Button.id("addBtn");
    public Table tblBetSettingPS38 = Table.xpath("//div[@id='EXCHANGE-bet-settings']//table[contains(@class, 'ptable info betTable')]", 4);
    private static List<String> sportBetSettingList;

    public void inputBetSettingPS38(String sportName, String colName, String amount) {
        getControlTxtBoxBetSettingPS38(sportName, colName).sendKeys(amount);
    }

    private void handleDropdownNotLoad(By locator){
        BaseElement dropdown = new BaseElement(locator);
        dropdown.click();
        dropdown.click();
        try{
            Thread.sleep(1500);
        }catch (Exception e){
        }
    }

    public void addSport(String sport, String league, boolean isAddOrView) {
        if (!sport.isEmpty()){
            //handle to load dropdown options list
            BaseElement dropdown = new BaseElement(ddbSportsPS38.getLocator());
            dropdown.click();
            dropdown.click();
            try{
                Thread.sleep(1500);
            }catch (Exception e){
            }
            ddbSportsPS38.selectByVisibleText(sport);
        }
        if (!league.isEmpty()) {
                try {
                    ddbLeaguePS38.selectByVisibleText(league);
                }catch (Exception e){
                    System.out.println("Select by index: " + league);
                    ddbLeaguePS38.selectByIndex(Integer.valueOf(league));
                }

        }
        if(isAddOrView){
            if(btnAdd.isDisplayed()){
                btnAdd.click();
                //clear sport list when adding new sport
                sportBetSettingList = null;
            }else {
                btnView.click();
            }
        }
    }

    public String addCommaFormat(double number){
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    public int defineColIndex(String colName){
        switch (colName.toLowerCase()){
            case "min bet":
                return 2;
            case "max bet":
                return 3;
            case "max per match":
                return 4;
            default:
                System.out.println("Not match value col of BetSetting table");
                return -1;
        }
    }

    public TextBox getControlTxtBoxBetSettingPS38(String sportName, String colName) {
        if (sportBetSettingList == null) {
            setSportList();
        }
        int rowIndex = findRowSportIndex(sportName);
        int colIndex = defineColIndex(colName);
        if(rowIndex == -1 | colIndex == -1){
            return null;
        }
        return TextBox.xpath(tblBetSettingPS38.getxPathOfCell(1, colIndex, rowIndex, "input"));
    }
    /**@param listSport: list of sports of Bet setting of PS38
     * @param listColName: list of colum name, e.g:["Min Bet", "Max Bet"...]
     * @param amount: list of amount must be same size with listColName*/
    public void updateValueInputSportPS38(List<String> listSport, List<String> listColName, List<String> amount) {
        for (int i = 0; i < listSport.size(); i++) {
            for (int j = 0; j < listColName.size(); j++) {
                TextBox input = getControlTxtBoxBetSettingPS38(listSport.get(i), listColName.get(j));
                if (!input.isDisplayed()) {
                    //handle for some sports don't have setting such as: Mix Parlay does not have Max Per Match
                    continue;
                }
                input.sendKeys(amount.get(j));
            }
        }
    }

    /**@param listSport: list of sports of Bet setting of PS38
     * @param listColName: list of colum name, e.g:["Min Bet", "Max Bet"...]*/
    public boolean isSportPS38InputEnable(List<String> listSport, List<String> listColName, boolean isEnable) {
        if (isEnable) {
            for (int i = 0; i < listSport.size(); i++) {
                for (int j = 0; j < listColName.size(); j++) {
                    TextBox input = getControlTxtBoxBetSettingPS38(listSport.get(i), listColName.get(j));
                    if (!input.isDisplayed()) {
                        //handle for some sports don't have setting such as: Mix Parlay does not have Max Per Match
                        continue;
                    }
                    if (!input.isEnabled()) {
                        return false;
                    }
                }
            }
        } else {
            for (int i = 0; i < listSport.size(); i++) {
                for (int j = 0; j < listColName.size(); j++) {
                    TextBox input = getControlTxtBoxBetSettingPS38(listSport.get(i), listColName.get(j));
                    if (!input.isDisplayed()) {
                        continue;
                    }
                    if (input.isEnabled()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void setSportList(){
            sportBetSettingList = tblBetSettingPS38.getColumn(1, false);
    }

    private int findRowSportIndex(String sportName){
        if(sportBetSettingList == null){
            System.out.println("SPORT LIST IS NOT SET");
        }else {
            for (int i = 0; i< sportBetSettingList.size(); i++){
                if(sportName.equalsIgnoreCase(sportBetSettingList.get(i))){
                    System.out.println("FOUND " + sportName +" at Row index: "+ i+1);
                    return i + 1;
                }
            }
        }
        return -1;
    }

    public void selectPS38Tab(String tabName){
        Label tabPS38 = Label.xpath(String.format("//div[contains(@class, 'tab-proteus') and contains(text(),'%s')]", tabName));
        tabPS38.click();
        // wait 1s for verifying pop up when switching PS38 tab
        try{
            Thread.sleep(1000);
        }catch (Exception e){
        }
    }

    public void verifyPS38TabIsCorrect(String tabName, String checkboxMessage) {
        selectPS38Tab(tabName);
        Assert.assertTrue(chkTabPS38.isSelected(), "FAILED! Check box of PS38 is not checked");
        Assert.assertEquals(lblCheckboxPS38.getText().trim(), String.format(checkboxMessage, tabName),
                String.format("FAILED! Checkbox message of tab: %s incorrect", tabName));
    }

    public void verifyBetSettingLabelValuePS38AllSports(String colName, String expectedValue){
        int colIndex = tblBetSettingPS38.getColumnIndexByName(colName);
        Set<String> valueLabelList = new HashSet<>(tblBetSettingPS38.getColumn(colIndex, true));
        valueLabelList.remove("");
        Assert.assertTrue(valueLabelList.size() == 1, String.format("FAILED! List value: %s not apply for all sports. List value: %s", colName, valueLabelList));
        Assert.assertEquals(valueLabelList, new HashSet<>(Arrays.asList(expectedValue)), String.format("FAILED! List value: %s not correct. Actual: %s, expected: %s", colName, valueLabelList, expectedValue));
    }

    public void verifyBetSettingInputValuePS38AllSports(String colName, String expectedValue){
        Set<String> valueLabelList = new HashSet<>(getInputValuePS38AllSports(colName));
        Assert.assertTrue(valueLabelList.size() == 1, String.format("FAILED! List value: %s not apply for all sports. List value: %s", colName, valueLabelList));
        Assert.assertEquals(valueLabelList, new HashSet<>(Arrays.asList(expectedValue)), String.format("FAILED! List value: %s not correct. Actual: %s, expected: %s", colName, valueLabelList, expectedValue));
    }

    public List<String> getInputValuePS38AllSports(String colName){
        List<String> listValue = new ArrayList<>();
        int colIndex = tblBetSettingPS38.getColumnIndexByName(colName);
        int index = 1;
        while(true){
            TextBox lblInputValue = TextBox.xpath(tblBetSettingPS38.getxPathOfCell(1,colIndex, index, "input"));
            if(!lblInputValue.isDisplayed()){
                System.out.println("NOT found cell locator");
                return listValue;
            }
            if(lblInputValue.isDisplayed()){
                listValue.add(lblInputValue.getAttribute("value"));
            }
            index++;
        }
    }
}
