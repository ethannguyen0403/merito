package agentsite.pages.agentmanagement.proteus.createdownlineagent.commissionsettingsection;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import controls.Table;

import java.util.List;
import java.util.Map;

public class CommissionSectionPS38Agent extends CommissionSectionPS38 {
    protected Table tblCommission = Table.xpath("//div[@id='PROTEUS-commission-settings']//table", 7);
    protected String oddGroupDropdownXpath = "//div[@id='PROTEUS-commission-settings']//table/tbody//tr[contains(., '%s')]/td[%d]//select";

    @Override
    public List<String> getTableHeader() {
        return tblCommission.getHeaderNameOfRows();
    }

    @Override
    public List<String> getTableColumnList(int colIndex) {
        return tblCommission.getColumn(colIndex, false);
    }

    @Override
    public void updateComSpecificSport(String sport, String league, List<Map<String, String>> amountCommission, String commissionName) {
        for (Map<String, String> entries : amountCommission) {
            for (Map.Entry<String, String> entry : entries.entrySet()) {
                DropDownBox ddbCommission = DropDownBox.xpath(String.format(oddGroupDropdownXpath, sport, defineColIndexOffGroupOdds(entry.getKey())));
                if (!entry.getValue().isEmpty()) {
                    ddbCommission.selectByVisibleText(entry.getValue());
//                    BaseElement element = new BaseElement(ddbCommission.getLocator());
//                    element.click();
//                    ddbCommission.selectByVisibleText(entry.getValue());
//                    element.click();
                }
            }
        }
    }

    private int defineColIndexOffGroupOdds(String groupOdds) {
        switch (groupOdds.toUpperCase()) {
            case "Group A":
            case "A":
                return 3;
            case "Group B":
            case "B":
                return 4;
            case "Group C":
            case "C":
                return 5;
            case "Group D":
            case "D":
                return 6;
            case "Group E":
            case "E":
                return 7;
            default:
                return 0;
        }
    }
}
