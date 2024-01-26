package agentsite.pages.agentmanagement.proteus.createdownlineagent;


import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;

import java.util.Map;

import static common.AGConstant.AgencyManagement.CreateCompany.*;

public class PositionTakingSectionPS38 {

    public DropDownBox ddbSport = DropDownBox.xpath("//app-proteus-ptsetting//select[contains(@class, 'sport-select')]");
    public DropDownBox ddbLeague = DropDownBox.xpath("//app-proteus-ptsetting//select[contains(@class, 'leagues-select')]");

    public Button btnView = Button.xpath("//app-proteus-ptsetting//button[contains(@class, 'pbtn')]");
    public CheckBox chkCopyAll = CheckBox.xpath("//app-proteus-ptsetting//label[contains(text(), 'Position Taking')]//input");
    public Button btnPositionSection = Button.xpath("//app-proteus-ptsetting//div[contains(@class, 'psection')]//i");
    private int tolTalCol = 10;
    public final static String FULL_TIME = "Full time";
    public final static String FIRST_HALF= "1st Half";


    public void updatePTMarket(String sportName, Map<String, String> betType, String ps38TabName, String position, String amount){
        expandSport(sportName, true);
        DropDownBox ddbPosition = getDropDownPTControl(sportName, betType, ps38TabName, position, "select");
        new BaseElement(ddbPosition.getLocator()).scrollToThisControl(false);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new BaseElement(ddbPosition.getLocator()).click();new BaseElement(ddbPosition.getLocator()).click();
        ddbPosition.selectByVisibleText(amount);
    }

    public void addSport(String sport, String league){
        expandPositionSection(true);
        ddbSport.selectByVisibleText(sport);
        if(!league.isEmpty()){
            try {
                ddbLeague.selectByVisibleText(league);
            }catch (Exception e){
                ddbLeague.selectByIndex(Integer.valueOf(league));
            }
        }
        btnView.click();
    }


    /***
     * @param sportName name of Sport. E.g: Soccer
     * @param betType bet type E.g: Full time-HDP, 1st Half-HDP. This variable should contain one entry
     * @param ps38TabName table Ingame or Pregame
     * @param position name of Position. E.g: Min Position, Max Position...*/
    public DropDownBox getDropDownPTControl(String sportName, Map<String, String> betType, String ps38TabName, String position, String subtag){
        int colIndex = findColIndexBetType(sportName, betType, ps38TabName) + 1;
        int rowIndex = findRowIndexPositionLabel(position);
        return DropDownBox.xpath(getTableControlBySport(sportName,tolTalCol, ps38TabName).getxPathOfCell(1, colIndex, rowIndex, subtag));
    }

    /**
     * @param betType bet type E.g: Full time-HDP, 1st Half-HDP*/
    public int findColIndexBetType(String sportName, Map<String, String> betType, String ps38TabName) {
        String xpathBetType = getTableControlBySport(sportName, tolTalCol, ps38TabName).getLocator().toString().replace("By.xpath: ", "");
        String lblXpathFullTime = String.format("%s/thead//tr[1]//th[2]", xpathBetType);
        int colIndex = -1;
        //use attribute colspan to define total colum of Full time or 1st Half
        int totalBetColFullTime = Integer.valueOf(Label.xpath(lblXpathFullTime).getAttribute("colspan"));
        for (Map.Entry<String, String> entry : betType.entrySet()) {
            int startColIndex = 1;
            //Because the bet type of Full time and 1st Half could be the same so if it is 1st Half, the startIndex should start with total Cols of Full time
            if (entry.getKey().equalsIgnoreCase(FIRST_HALF)) {
                startColIndex += totalBetColFullTime;
            }
            while (true) {
                Label lblBetType = Label.xpath(String.format("%s/thead/tr[2]/th[%s]", xpathBetType, startColIndex));
                if (lblBetType.getText().trim().equalsIgnoreCase(entry.getValue())) {
                    System.out.println(
                            String.format("FOUND col index %s at Value: %s of sport: %s at tab: %s", startColIndex, betType, sportName, ps38TabName));
                    return startColIndex;
                }
                startColIndex++;
                if (!lblBetType.isDisplayed()) {
                    System.out.println(
                            String.format("NOT Found col index at Value: %s of sport: %s at tab: %s", betType, sportName, ps38TabName));
                    return colIndex;
                }
            }
        }
        System.out.println(String.format("NOT Found col index at Value: %s of sport: %s at tab: %s", betType, sportName, ps38TabName));
        return colIndex;
    }

    public int findRowIndexPositionLabel(String positionLabel) {
        if (positionLabel.contains("Min Position")) {
            return 1;
        } else if (positionLabel.contains("Max Position")) {
            return 2;
        } else if (positionLabel.contains("Preset")) {
            return 3;
        } else if (positionLabel.contains("Extra PT")) {
            return 4;
        } else {
            System.out.println("NOT Found row index with label: " + positionLabel);
            return -1;
        }
    }

    public void expandPositionSection(boolean isExpanded) {
        //expand
        if (isExpanded && !ddbSport.isDisplayed()) {
            btnPositionSection.click();
        }
        //collapse
        if (!isExpanded && ddbSport.isDisplayed()) {
            btnPositionSection.click();
        }

    }

    public void expandSport(String sportName, boolean isExpanded){
        Button btnExpandSport = Button.xpath(String.format("//div[contains(@class, 'sport-title') and contains(., '%s')]//div[contains(@class, 'toggle-active')]", sportName));
        if(isExpanded){
            if(btnExpandSport.getAttribute("class").contains("plightExpand")){
                btnExpandSport.click();
            }
        }else {
            if(!btnExpandSport.getAttribute("class").contains("plightExpand")){
                btnExpandSport.click();
            }
        }
    }

    public Table getTableControlBySport(String sportName, int totalCol, String ps38TabName) {
        switch (ps38TabName) {
            case PREGAME_TAB_PS38:
                return Table.xpath(
                        String.format("//div[contains(@class, 'sport-title') and contains(., '%s')]/following::table[1]", sportName),
                        totalCol);
            case INPLAY_TAB_PS38:
                return Table.xpath(
                        String.format("//div[contains(@class, 'sport-title') and contains(., '%s')]/following::table[2]", sportName),
                        totalCol);
            default:
                return null;
        }
    }

}
