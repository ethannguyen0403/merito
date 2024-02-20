package agentsite.pages.agentmanagement.proteus.createdownlineagent;


import agentsite.objects.agent.proteus.PS38PTSetting;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import controls.Table;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.AGConstant.AgencyManagement.CreateCompany.*;

public class PositionTakingSectionPS38 {

    public DropDownBox ddbSport = DropDownBox.xpath("//app-proteus-ptsetting//select[contains(@class, 'sport-select')]");
    public DropDownBox ddbLeague = DropDownBox.xpath("//app-proteus-ptsetting//select[contains(@class, 'leagues-select')]");

    public Button btnAddOrView = Button.xpath("//app-proteus-ptsetting//button[contains(@class, 'pbtn')]");
    public CheckBox chkCopyAll = CheckBox.xpath("//app-proteus-ptsetting//label[contains(text(), 'Position Taking')]//input");
    public Button btnPositionSection = Button.xpath("//app-proteus-ptsetting//div[contains(@class, 'psection')]//i");
    BaseElement blkPTContainer = new BaseElement(By.xpath("//app-proteus-ptsetting//div[contains(@class, 'proteus-container settings')]"));
    private String xpathLblExpandSport = "//div[contains(@class, 'sport-title') and contains(., '%s')]";
    private String xpathSportTable = "//div[contains(@class, 'sport-title') and contains(., '%s')]/following-sibling::div/table[contains(., '%s')]";
    private int tolTalCol = 10;
    private Map<String, Integer> indexPos = new HashMap<String, Integer>() {
        {
            put("Min Position", 1);
            put("Max Position", 2);
            put("Preset", 3);
            put("Extra PT", 4);
        }
    };


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
            int rowIndex = indexPos.get(ptSettings.getPos());
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

}
