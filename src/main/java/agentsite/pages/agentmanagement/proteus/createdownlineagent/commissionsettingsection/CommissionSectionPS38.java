package agentsite.pages.agentmanagement.proteus.createdownlineagent.commissionsettingsection;


import com.paltech.element.common.*;
import controls.Table;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommissionSectionPS38 {
    private String _accountType;
    public Button btnCommissionSection = Button.xpath("//div[contains(@id, 'PROTEUS-commission-settings')]/div[contains(@class, 'psection')]//i");
    public DropDownBox ddbOddsGroup = DropDownBox.xpath("//div[@id='PROTEUS-commission-settings']//div[contains(text(), 'Odds Group')]/select");
    public CheckBox chkApply = CheckBox.xpath("//div[@id='PROTEUS-commission-settings']//input");
    public Label lblApply = Label.xpath("//div[@id='PROTEUS-commission-settings']//label/span");
    //Isa improve
    public Label lblCommission = Label.xpath("//div[contains(@id, 'PROTEUS-commission-settings')]/div[contains(@class, 'psection')]");
    private Icon icCommissionCollapseExpand = Icon.xpath("//div[contains(@id, 'PROTEUS-commission-settings')]/div[contains(@class, 'psection')]//i");
    private Label lblApplySoccerGamesSettingtoOther = Label.xpath("//div[contains(@id, 'PROTEUS-commission-settings')]/label//span");
    private CheckBox cbApplySoccerGamesSettingtoOther = CheckBox.xpath("//div[contains(@id, 'PROTEUS-commission-settings')]/label//input");
    int totalColCommissionGroup = 6;
    private Table tblCommissionGroup = Table.xpath("//div[contains(@id, 'PROTEUS-commission-settings')]//table",totalColCommissionGroup);
    DropDownBox ddpOddsGroup = DropDownBox.xpath("//");

    //End Isa

    public String getAccountType(){
        return _accountType;
    }

    public double randomDouble(double min, double max){
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public void verifyCommissionUICorrect(){
    }

/**
 * @return return Map with value of Odds group and commission. E.g: Odds Group > A, High commission > 0.3*/
    public Map<String, String> getAmountCommission(String oddGroupName, String commissionName, String sport, String league){
        return null;
    }

    public List<String> getTableHeader(){
        return null;
    }
    public List<String> getTableColumnList(int colIndex){
        return null;
    }
    /**
     * @param amountCommission with key(): Odds group value such as: A, B, C..., value(): amount Commission*/
    public void updateComSpecificSport(String sport, String league, List<Map<String, String>> amountCommission, String commissionName){
    }

    public void addSport(String sport, String league) {
    }
    public String getLeague(){
        return "";
    }

    /**
     * @param accountType define which is agent account or player account. Input: Agent or Member*/
    public CommissionSectionPS38 expandCommissionSection(String accountType, boolean isExpanded) {
        this._accountType = accountType;
        if (isExpanded) {
            if (btnCommissionSection.getAttribute("class").contains("fa-chevron-down")) {
                btnCommissionSection.click();
            }
        } else {
            if (btnCommissionSection.getAttribute("class").contains("fa-chevron-up")) {
                btnCommissionSection.click();
            }
        }
        btnCommissionSection.scrollToThisControl(true);
        return switchTypeCommissionSection(accountType);
    }

    private CommissionSectionPS38 switchTypeCommissionSection(String accountType){
        if(accountType.toLowerCase().contains("member")){
            return new CommissionSectionPS38Member();
        }else {
            return new CommissionSectionPS38Agent();
        }
    }


    ////

    /**
     * Expand or collapse Commission Section
     * @param isExpanded : input value = true: expand, input value = false -> colapse
     */
    public void expandCollapseCommissionSection( boolean isExpanded) {
        String attribute = icCommissionCollapseExpand.getAttribute("class");
        boolean isConmmissionExpanded = attribute.contains("fa-chevron-up");
        if (isExpanded != isConmmissionExpanded) {
            lblCommission.click();
        }
    }
    /////
}
