package agentsite.pages.agentmanagement.proteus.createdownlineagent;


import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import org.testng.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import static common.AGConstant.AgencyManagement.CreateCompany.*;

public class BetSettingSectionPS38 {

    public CheckBox chkTabPS38 = CheckBox.xpath("//app-proteus-setting//input[@type='checkbox']");
    public CheckBox chkCopyAll = CheckBox.xpath("//div[@id='EXCHANGE-bet-settings']//div[@class='pl-2 py-2']//input");
    public Label lblCheckboxPS38 = Label.xpath("//app-proteus-setting//div[@class='pl-2 py-2']");
    public DropDownBox
            ddbSportsPS38 = DropDownBox.xpath("//select[contains(@class, 'sport-select')]");
    public DropDownBox
            ddbLeaguePS38 = DropDownBox.xpath("//select[contains(@class, 'leagues-select')]");
    public Button btnView = Button.id("viewBtn");
    public Button btnAdd = Button.id("addBtn");
    public Table tblBetSettingPS38 = Table.xpath("//div[@id='EXCHANGE-bet-settings']//table[contains(@class, 'ptable info betTable')]", 4);

    public void addSport(String sport, String league, boolean isAddOrView) {
        if (!sport.isEmpty()) {
            //handle to load dropdown options list
            BaseElement dropdown = new BaseElement(ddbSportsPS38.getLocator());
            dropdown.click();
            dropdown.click();
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
            }
            ddbSportsPS38.selectByVisibleText(sport);
        }
        if (!league.isEmpty()) {
            try {
                ddbLeaguePS38.selectByVisibleText(league);
            } catch (Exception e) {
                System.out.println("Select by index: " + league);
                ddbLeaguePS38.selectByIndex(Integer.valueOf(league));
            }

        }
        if (isAddOrView) {
            if (btnAdd.isDisplayed()) {
                btnAdd.click();
            } else {
                btnView.click();
            }
        }
    }

    public String addCommaFormat(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    public int defineColIndex(String colName) {
        switch (colName.toLowerCase()) {
            case "":
                return 1;
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

    public TextBox getControlTxtBoxBetSettingPS38(String sportName, String colName, List<String> sportList) {
        int rowIndex = findRowSportIndex(sportName, sportList);
        int colIndex = defineColIndex(colName);
        if (rowIndex == -1 || colIndex == -1) {
            return null;
        }
        return TextBox.xpath(tblBetSettingPS38.getxPathOfCell(1, colIndex, rowIndex, "input"));
    }

    /**
     * @param listSport:   list of sports of Bet setting of PS38
     * @param listColName: list of colum name, e.g:["Min Bet", "Max Bet"...]
     * @param amount:      list of amount must be same size with listColName
     */
    public void updateValueInputSportPS38(List<String> listSport, List<String> listColName, List<String> amount) {
        List<String> sportList = tblBetSettingPS38.getColumn(1, false);
        for (int i = 0; i < listSport.size(); i++) {
            for (int j = 0; j < listColName.size(); j++) {
                TextBox input = getControlTxtBoxBetSettingPS38(listSport.get(i), listColName.get(j), sportList);
                if (!input.isDisplayed()) {
                    //handle for some sports don't have setting such as: Mix Parlay does not have Max Per Match
                    continue;
                }
                input.sendKeys(amount.get(j));
            }
        }
    }

    /**
     * @param listSport:   list of sports of Bet setting of PS38
     * @param listColName: list of colum name, e.g:["Min Bet", "Max Bet"...]
     */
    public boolean isSportPS38InputEnable(List<String> listSport, List<String> listColName, boolean isEnable) {
        List<String> sportList = tblBetSettingPS38.getColumn(1, false);
        for (int i = 0; i < listSport.size(); i++) {
            for (int j = 0; j < listColName.size(); j++) {
                TextBox input = getControlTxtBoxBetSettingPS38(listSport.get(i), listColName.get(j), sportList);
                if (!input.isDisplayed()) {
                    //handle for some sports don't have setting such as: Mix Parlay does not have Max Per Match
                    continue;
                }
                if (input.isEnabled() != isEnable) {
                    return false;
                }
            }
        }
        return true;
}

    private int findRowSportIndex(String sportName, List<String> sportList){
        //use list of sports to find row index of Bet Setting table
            for (int i = 0; i < sportList.size(); i++) {
                if (sportName.equalsIgnoreCase(sportList.get(i))) {
                    System.out.println("FOUND " + sportName + " at Row index: " + i + 1);
                    return i + 1;
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

    public void verifyBetSettingAllSportsConvertToCurrency(String currency, double rate, String minBet, String maxBet, String maxPerMatch) {
        //using BigDecimal for precisely in division of double
        rate = new BigDecimal(Double.toString(Double.valueOf(rate))).setScale(6, RoundingMode.HALF_UP).doubleValue();

        String amountMin = addCommaFormat(Double.valueOf(minBet.replace(",", "")) / rate);
        String amountMax = addCommaFormat(Double.valueOf(maxBet.replace(",", "")) / rate);
        String amountMaxPerMatch = addCommaFormat(Double.valueOf(maxPerMatch.replace(",", "")) / rate);

        String conditionMinBet = String.format(">= %s %s", currency, amountMin);
        String conditionMaxBet = String.format("<= %s %s", currency, amountMax);
        String conditionMaxPerMatch = String.format("<= %s %s", currency, amountMaxPerMatch);
        if (!minBet.isEmpty()) {
            verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(1), conditionMinBet);
            verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(1), amountMin);
        }
        if (!maxBet.isEmpty()) {
            verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(2), conditionMaxBet);
            verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(2), amountMax);
        }
        if (!maxPerMatch.isEmpty()) {
            verifyBetSettingLabelValuePS38AllSports(HEADER_BET_SETTING_PS38.get(3), conditionMaxPerMatch);
            verifyBetSettingInputValuePS38AllSports(HEADER_BET_SETTING_PS38.get(3), amountMaxPerMatch);
        }
    }

    public void verifyPS38TabIsCorrect(String tabName, String checkboxMessage) {
        selectPS38Tab(tabName);
        Assert.assertTrue(ddbSportsPS38.isDisplayed(), "FAILED! Sport dropdown is not displayed");
        Assert.assertTrue(chkTabPS38.isSelected(), "FAILED! Check box of PS38 is not checked");
        Assert.assertEquals(lblCheckboxPS38.getText().trim(), String.format(checkboxMessage, tabName),
                String.format("FAILED! Checkbox message of tab: %s incorrect", tabName));
        Assert.assertEquals(tblBetSettingPS38.getColumnByBody(1, false), LIST_SPORTS_PS38_BET_SETTING, "FAILED! List sports of PS38 incorrect");
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

    public void verifyBetSettingSpecificSportConvertToCurrency(String sport, double rate, String minBet, String maxBet,
                                                               String maxPerMatch) {
        List<String> sportList = tblBetSettingPS38.getColumn(1, false);
        rate = new BigDecimal(Double.toString(Double.valueOf(rate))).setScale(6, RoundingMode.HALF_UP).doubleValue();
        String amountMin = addCommaFormat(Double.valueOf(minBet.replace(",", "")) / rate);
        String amountMax = addCommaFormat(Double.valueOf(maxBet.replace(",", "")) / rate);
        String amountMaxPerMatch = addCommaFormat(Double.valueOf(maxPerMatch.replace(",", "")) / rate);

        Assert.assertEquals(getControlTxtBoxBetSettingPS38(sport, HEADER_BET_SETTING_PS38.get(1), sportList).getAttribute("value").trim(),
                amountMin,
                String.format("FAILED! Value of league: %s at colum: %s is not correct", sport, HEADER_BET_SETTING_PS38.get(1)));
        Assert.assertEquals(getControlTxtBoxBetSettingPS38(sport, HEADER_BET_SETTING_PS38.get(2), sportList).getAttribute("value").trim(),
                amountMax,
                String.format("FAILED! Value of league: %s at colum: %s is not correct", sport, HEADER_BET_SETTING_PS38.get(2)));
        Assert.assertEquals(getControlTxtBoxBetSettingPS38(sport, HEADER_BET_SETTING_PS38.get(3), sportList).getAttribute("value").trim(),
                amountMaxPerMatch,
                String.format("FAILED! Value of league: %s at colum: %s is not correct", sport, HEADER_BET_SETTING_PS38.get(3)));
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

    public void verifyNewSportAddedInPTSection(String sportName, String league, boolean isGeneral){
        List<String> newSportList = tblBetSettingPS38.getColumn(1, true);
        if(isGeneral){
            int rowNewSportIndex = newSportList.indexOf(sportName) + 1;
            Label lblNewSportRow = Label.xpath(tblBetSettingPS38.getxPathOfCell(1, 1, rowNewSportIndex, null));
            Assert.assertEquals(newSportList.indexOf(sportName), LIST_SPORTS_PS38_BET_SETTING.indexOf("Others"), "FAILED! New sport is not above Others");
            Assert.assertEquals(lblNewSportRow.getColour(), "rgba(68, 114, 196, 1)", "FAILED! New sport was not highlight in blue");
        }else {
            int rowNewSportIndex = newSportList.indexOf(league) + 1;
            Label lblNewSportRow = Label.xpath(tblBetSettingPS38.getxPathOfCell(1, 1, rowNewSportIndex, null));
            Assert.assertEquals(newSportList.indexOf(sportName), LIST_SPORTS_PS38_BET_SETTING.indexOf("Others"), "FAILED! New sport is not above Others");
            Assert.assertTrue((newSportList.indexOf(league) - newSportList.indexOf(sportName)) == 1, "FAILED! New league is not under Sport: " + sportName);
            Assert.assertEquals(lblNewSportRow.getColour(), "rgba(68, 114, 196, 1)", "FAILED! New league was not highlight in blue. League: " + league);
        }
    }
}
