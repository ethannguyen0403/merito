package agentsite.pages.agentmanagement.proteus.createdownlineagent;


import agentsite.objects.agent.proteus.PS38PTSetting;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import controls.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.AGConstant.AgencyManagement.CreateCompany.*;

public class PositionTakingSectionPS38 {

    public DropDownBox ddbSport = DropDownBox.xpath("//app-proteus-ptsetting//select[contains(@class, 'sport-select')]");
    public DropDownBox ddbLeague = DropDownBox.xpath("//app-proteus-ptsetting//select[contains(@class, 'leagues-select')]");

    public Button btnAddOrView = Button.xpath("//app-proteus-ptsetting//button[contains(@class, 'pbtn')]");
    public CheckBox chkCopyAll = CheckBox.xpath("//app-proteus-ptsetting//label[contains(text(), 'Position Taking')]//input");
    public Label lblCopyAll = Label.xpath("//app-proteus-ptsetting//label[contains(text(), 'Position Taking')]");
    public Button btnPositionSection = Button.xpath("//app-proteus-ptsetting//div[contains(@class, 'psection')]//i");
    BaseElement blkPTContainer = new BaseElement(By.xpath("//app-proteus-ptsetting//div[contains(@class, 'proteus-container settings')]"));
    private String xpathLblExpandSport = "//div[contains(@class, 'sport-title') and contains(., '%s')]";
    // define Pregame or In play table base on Sport
    private String xpathSportTable = "//div[contains(@class, 'sport-title') and contains(., '%s')]/following-sibling::div/table[contains(., '%s')]";
    private int tolTalCol = 10;
    public Table tblFirstPregame = Table.xpath("(//div[contains(@class, 'sport-title')]/following-sibling::div/table[1])[1]", tolTalCol);
    public Table tblFirstInPlay = Table.xpath("(//div[contains(@class, 'sport-title')]/following-sibling::div/table[2])[1]", tolTalCol);
    private Map<String, Integer> indexPos = new HashMap<String, Integer>() {
        {
            put("Min Position", 1);
            put("Max Position", 2);
            put("Preset", 3);
            put("Extra PT", 4);
        }
    };

    public void verifyPTSectionUI(){
        expandPositionSection(true);
        Assert.assertEquals(lblCopyAll.getText().trim(), CHECKBOX_MESSAGE_PS38_PT, "FAILED! Check box Coppy All message is not correct");
        Assert.assertEquals(getSportList(), LIST_SPORTS_PS38_PT, "FAILED! The list sport sections: Soccer, Baseball, Basketball, Football, E Sports, Others, Mix Parlay, Teaser is not correct");
        Assert.assertEquals(tblFirstPregame.getHeaderNameOfRows(), HEADER_PREGAME_TABLE, "FAILED! Pregame table: Header is NOT grouped by Full time and 1st Haft Full time contains: 1X2, HDP, OU, TT, Others, Outright 1st Haft: 1X2, HDP, O");
        Assert.assertEquals(tblFirstInPlay.getHeaderNameOfRows(), HEADER_INPLAY_TABLE, "FAILED! Header is grouped by Full time and 1st Haft Full time contains: 1X2, HDP, OU 1st Haft: 1X2, HDP, OU");
    }

    public List<String> getSportList(){
        List<String> lblList = new ArrayList<>();
        Label lblSportList = Label.xpath("//div[contains(@class, 'sport-title')]");
        try {
            new ArrayList<>(lblSportList.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        }catch (Exception e){
            System.out.println("DEBUG! Can not get list product");
        }
        return lblList;
    }

    public void updateProteusPTMarket(List<PS38PTSetting> listPT, boolean isAll) {
        expandPositionSection(true);

        for (PS38PTSetting ptSettings : listPT) {
            expandSport(ptSettings.getSport(), true);

            Table tblPT = Table.xpath(String.format(xpathSportTable, ptSettings.getSport(), ptSettings.getPS38Tab()), tolTalCol);
            int colIndex = findColIndexOfBetTypeProteusTable(ptSettings.getPS38Tab(), ptSettings.getBetTime(), ptSettings.getBetType());
            int rowIndex = indexPos.get(ptSettings.getPos());
            String ptAmount = String.valueOf(ptSettings.getAmountPT()).replaceAll(".0", "");

            if (isAll) {
                CheckBox chkAll = CheckBox.xpath(
                        String.format(xpathSportTable, ptSettings.getSport(), ptSettings.getPS38Tab() + "/thead//tr[2]//th[1]"));
                if (!chkAll.isSelected())
                    chkAll.select();
            }
            DropDownBox ddbPT = DropDownBox.xpath(tblPT.getControlXPathOfCell(1, colIndex, rowIndex, "select"));
            //Handle for sometimes dropdown list value is not load
            new BaseElement(ddbPT.getLocator()).click();
            new BaseElement(ddbPT.getLocator()).click();
            ddbPT.selectByVisibleText(ptAmount);
        }
    }

    public void verifyProteusPTMarket(List<PS38PTSetting> listPT) {
        expandPositionSection(true);
        for (PS38PTSetting ptSettings : listPT) {
            expandSport(ptSettings.getSport(), true);

            Table tblPT = Table.xpath(String.format(xpathSportTable, ptSettings.getSport(), ptSettings.getPS38Tab()), tolTalCol);
            int colIndex = findColIndexOfBetTypeProteusTable(ptSettings.getPS38Tab(), ptSettings.getBetTime(), ptSettings.getBetType());
            // get row index of PT setting such as: Min Position, Max Position...
            int rowIndex = indexPos.get(ptSettings.getPos());
            //assert pt value base on object PS38PTSetting
            Assert.assertEquals(DropDownBox.xpath(tblPT.getControlXPathOfCell(1, colIndex, rowIndex, "select")).getFirstSelectedOption(),
                    String.valueOf(ptSettings.getAmountPT()).replace(".0", ""),
                    String.format("FAILED! PT of sport %s at %s table - %s - %s is not correct", ptSettings.getSport(),
                            ptSettings.getPS38Tab(), ptSettings.getBetTime(), ptSettings.getBetType()));
        }
    }

    public void addOrViewSport(String sport, String league){
        expandPositionSection(true);
        ddbSport.selectByVisibleText(sport);
        if(!league.isEmpty()){
            try {
                ddbLeague.selectByVisibleText(league);
            }catch (Exception e){
                System.out.println("Select League by Index: " + league);
                ddbLeague.selectByIndex(Integer.valueOf(league));
            }
        }
        btnAddOrView.click();
    }

    public int findColIndexOfBetTypeProteusTable(String tableName, String betTime, String betType) {
        int startIndex;
        if (tableName.equalsIgnoreCase(PREGAME_TAB_PS38)) {
            startIndex = betTime.equalsIgnoreCase(FULL_TIME) ? 2 : 8;
        } else {
            startIndex = betTime.equalsIgnoreCase(FULL_TIME) ? 2 : 5;
        }
        switch (betType.toUpperCase()) {
            case "1X2":
            case "ML":
            case "MIX PARLAY":
            case "TEASERS":
                return startIndex;
            case "HDP":
                return startIndex + 1;
            case "OU":
                return startIndex + 2;
            case "TT":
                return startIndex + 3;
            case "Others":
                return startIndex + 4;
            case "Outright":
                return startIndex + 5;
            default:
                System.out.println("NOT found matched bet type with: " + betType);
                return -1;
        }
    }

    public void expandPositionSection(boolean isExpanded) {
        if(!btnPositionSection.isDisplayed()){
            return;
        }
        if (isExpanded) {
            if(!blkPTContainer.isDisplayed())
                btnPositionSection.click();
        } else {
            if(blkPTContainer.isDisplayed())
                btnPositionSection.click();
        }
    }

    public void expandSport(String sportName, boolean isExpanded) {
        Label lblExpandSport = Label.xpath(String.format(xpathLblExpandSport, sportName));
        BaseElement blkSport = new BaseElement(By.xpath(String.format(xpathLblExpandSport, sportName) + "/following-sibling::div"));
        if (isExpanded) {
            if (!blkSport.isDisplayed())
                lblExpandSport.click();
            lblExpandSport.scrollToThisControl(true);

        } else {
            if (blkSport.isDisplayed())
                lblExpandSport.click();
        }
    }

    public boolean verifyAllPTDropdownState(List<String> sportList, List<String> tableList, boolean isEnable) {
        if(chkCopyAll.isSelected()){
            chkCopyAll.deSelect();
        }
        for (String sportName : sportList) {
            for (String table : tableList) {
                BaseElement ptTable = Label.xpath(String.format(xpathSportTable, sportName, table));
                //handle in case sport only has 1 table pregame or in-play
                if (!ptTable.isDisplayed()) {
                    continue;
                }
                if (!verifyAllPTDropdownState(sportName, table, isEnable))
                    return false;
            }
        }
        return true;
    }

    public boolean verifyAllPTDropdownState(String sportName, String tableName, boolean isEnable) {
        expandSport(sportName, true);
        String ptDropdownXpath = String.format("%s//%s", xpathSportTable, "select");
        for (WebElement ptEle : Label.xpath(String.format(ptDropdownXpath, sportName, tableName)).getWebElements()) {
            if (ptEle.isEnabled() != isEnable) {
                return false;
            }
        }
        return true;
    }

}
