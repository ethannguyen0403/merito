package agentsite.pages.agentmanagement.proteus.createdownlineagent.commissionsettingsection;

import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.AGConstant.AgencyManagement.CommissionSettingListing.HEADERS_COMMISSION_PS38;
import static common.AGConstant.AgencyManagement.CommissionSettingListing.ODDS_GROUP;

public class CommissionSectionPS38Member extends CommissionSectionPS38 {
    protected DropDownBox ddbSport = DropDownBox.xpath("//app-proteus-sport-league//select[contains(@class, 'sport-select')]");
    public DropDownBox ddbLeague = DropDownBox.xpath("//app-proteus-sport-league//select[contains(@class, 'leagues-select')]");
    protected Button btnAdd = Button.xpath("//app-proteus-sport-league//button");
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    private String xpathSpecificSportBlock =
            "//app-proteus-sport-league//div[contains(@class, 'sport-title') and contains(.,'%s')]/following::div[contains(@class, 'sport-section-item') and contains(.,'%s')][1]";
    String tblCommissionXpath = "//app-proteus-member-commission-setting//table";

    @Override
    public String getLeague() {
        return ddbLeague.getFirstSelectedOption().trim();
    }

    @Override
    public Map<String, String> getAmountCommission(String oddGroupName, String commissionName, String sport, String league) {
        String oddsGroup = DropDownBox.xpath(String.format(xpathSpecificSportBlock, sport, league) +
                "/following::span[contains(., 'Odds Group')][1]/following::select[1]").getFirstSelectedOption();
        String comAmount = DropDownBox.xpath(String.format(xpathSpecificSportBlock, sport, league) +
                String.format("/following::span[contains(., '%s')][1]/following::select[1]", commissionName)).getFirstSelectedOption();
        return new HashMap<String, String>() {
            {
                if (!oddsGroup.isEmpty() || oddsGroup != null)
                    put(ODDS_GROUP, oddsGroup);
                if (!comAmount.isEmpty() || comAmount != null)
                    put(commissionName, comAmount);
            }
        };
    }

    private void waitLoadingSpinner() {
        while (iconLoadSpinner.isDisplayed()) {
            iconLoadSpinner.waitForControlInvisible(1, 1);
        }
    }

    @Override
    public void updateComSpecificSport(String sport, String league, List<Map<String, String>> amountCommission, String commissionName) {
        DropDownBox ddbOddsGroup = DropDownBox.xpath(String.format(xpathSpecificSportBlock, sport, league) +
                "/following::span[contains(., 'Odds Group')][1]/following::select[1]");
        for (Map<String, String> entries : amountCommission) {
            for (Map.Entry<String, String> entry : entries.entrySet()) {
                if (!entry.getKey().isEmpty()) {
                    ddbOddsGroup.selectByVisibleText(entry.getKey());
                }
                DropDownBox ddbCommission = DropDownBox.xpath(String.format(xpathSpecificSportBlock, sport, league) +
                        String.format("/following::span[contains(., '%s')][1]/following::select[1]", commissionName));
                if (!entry.getValue().isEmpty()) {
                    BaseElement element = new BaseElement(ddbCommission.getLocator());
                    element.click();
                    ddbCommission.selectByVisibleText(entry.getValue());
                    element.click();
                }
            }
        }

    }

    @Override
    public void verifyCommissionUICorrect() {
    BaseElement ddbList = new BaseElement  (By.xpath("//div[@id='PROTEUS-commission-settings']//select"));
    Assert.assertTrue(Label.xpath("//div[@id='PROTEUS-commission-settings']//div[contains(., 'Odds Group')]").isDisplayed(), "FAILED! Odd group dropdown is not displayed");
    Assert.assertTrue(ddbLeague.isDisplayed(), "FAILED! League dropdown is not displayed");
    Assert.assertTrue(ddbLeague.isDisplayed(), "FAILED! League dropdown is not displayed");
    // Verify all controls in Commission section are disabled.
    for(WebElement dropdown: ddbList.getWebElements()){
        Assert.assertTrue(!dropdown.isEnabled(), "FAILED! Dropdown is not disable for indirect down line");
    }
    // Verify header list of commission table
        List<String> headerList = new ArrayList<>();
        for(WebElement lblHeader: Label.xpath(String.format("%s//tr//th", tblCommissionXpath)).getWebElements()){
            headerList.add(lblHeader.getText().replaceAll("\\d+","").replace("\n", "").replace(".", ""));
        }
        Assert.assertEquals(headerList.toString(), HEADERS_COMMISSION_PS38, "FAILED! Header of commission section table PS38 is not correct");
    }

    @Override
    public void addSport(String sport, String league) {
        if (Label.xpath(String.format(xpathSpecificSportBlock, sport, league)).isDisplayed()) {
            System.out.println(String.format("Sport: %s with League: %s was added", sport, league));
            return;
        }
        if (!sport.isEmpty()) {
            waitLoadingSpinner();
            ddbSport.selectByVisibleText(sport);
        }
        if (!league.isEmpty()) {
            try {
                ddbLeague.selectByVisibleText(league);
            } catch (Exception e) {
                System.out.println("Select dropdown by index: " + league);
                ddbLeague.selectByIndex(Integer.valueOf(league));
            }
            btnAdd.click();
            waitLoadingSpinner();
        }
    }
}
