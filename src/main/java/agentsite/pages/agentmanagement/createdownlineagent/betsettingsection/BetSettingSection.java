package agentsite.pages.agentmanagement.createdownlineagent.betsettingsection;

import agentsite.controls.DropDownBox;
import agentsite.controls.Table;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.testng.Assert;

import java.util.*;

public class BetSettingSection {
    int totalColumn = 9;
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    protected Label lblMinBetEG = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCH_GAMES-bet-settings']//td[text()='Min Bet']");
    protected Label lblMaxBetEG = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCH_GAMES-bet-settings']//td[text()='Max Bet']");
    protected Label lblMaxLiabilityEG = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCH_GAMES-bet-settings']//td[text()='Max Liability ']");
    protected Label lblMaxWinEG = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCH_GAMES-bet-settings']//td[text()='Max Win ']");
    protected Label lblMinBetEX = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCHANGE-bet-settings']//td[text()='Min Bet']");
    protected Label lblMaxBetEX = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCHANGE-bet-settings']//td[text()='Max Bet']");
    protected Label lblMaxLiabilityEX = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCHANGE-bet-settings']//td[text()='Max Liability ']");
    protected Label lblMaxWinEX = Label.xpath("//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCHANGE-bet-settings']//td[text()='Max Win ']");
    public CheckBox chkTabPS38 = CheckBox.xpath("//app-proteus-setting//input[@type='checkbox']");
    public DropDownBox ddbSportsPS38 = DropDownBox.xpath("//select[contains(@class, 'sport-select')]","//select[contains(@class, 'sport-select')]");
    public Table tblBetSettingEX = Table.xpath("//div[@id='EXCHANGE-bet-settings']//table[contains(@class,'betTable')]",totalColumn);
    public Table tblBetSettingEG = Table.xpath("//div[@id='EXCH_GAMES-bet-settings']//table[contains(@class,'betTable')]",8);
    public Table tblBetSetting = Table.xpath("(//div[@id[starts-with(.,'product-settings')]]//div[@id='EXCHANGE-bet-settings']//table[contains(@class,'betTable')])[1]", totalColumn);
    public Table tblBetSettingPS38 = Table.xpath("//div[@id='EXCHANGE-bet-settings']//table[contains(@class, 'ptable info betTable')]", 4);

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getBetSettingSectionTitle(String product) {return ""; }

    public List<ArrayList<String>> getBetSettingValidationValueLst(String currecyCode) {
        return null;
    }
    public void inputBetSetting(List<ArrayList<String>> lstSetting) {
        TextBox txtSport;
        int n = lstSetting.size();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < lstSetting.get(i).size(); j++) {
                if (!(lstSetting.get(i).get(j)).isEmpty()) {
                    txtSport = TextBox.xpath(tblBetSetting.getxPathOfCell(1, j + 1, i + 1, "input"));
                    txtSport.sendKeys(lstSetting.get(i).get(j));
                }
            }
        }
    }

    public void inputBetSettingPS38(String sportName, String colName, String amount) {
        int rowIndex = findRowSportIndex(sportName);
        int colIndex = tblBetSettingPS38.getColumnIndexByName(colName);
        TextBox.xpath(tblBetSettingPS38.getxPathOfCell(1, colIndex, rowIndex, "input")).sendKeys(amount);
    }

    private int findRowSportIndex(String sportName){
        int index = 1;
        while(true){
            Label lblSport = Label.xpath(tblBetSettingPS38.getxPathOfCell(1,1, index, null));
            if(!lblSport.isDisplayed()){
                System.out.println("NOT found cell locator");
                return -1;
            }
            if(lblSport.getText().trim().equalsIgnoreCase(sportName)){
                System.out.println("Found cell value: " + sportName);
                return index;
            }
            index++;
        }
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
        Label lblCheckboxPS38 = Label.xpath("//app-proteus-setting//div[@class='pl-2 py-2']");
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

    public void verifyUIDisplayCorrect(String product) {
    }

}
