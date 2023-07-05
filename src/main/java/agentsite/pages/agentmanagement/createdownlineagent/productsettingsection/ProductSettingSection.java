package agentsite.pages.agentmanagement.createdownlineagent.productsettingsection;

import agentsite.controls.MenuTree;
import agentsite.controls.Table;
import com.paltech.element.common.Icon;

import java.util.List;

public class ProductSettingSection {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public MenuTree mnProductSetting = MenuTree.xpath("//tabset[@id='productSetting']/ul","/li");
    public Table tblSports = Table.xpath("//div[contains(@class,'marketSettingWrapper')]//table[contains(@class,'sportTable')]",28);
    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public String getProductSettingSectionTitle() {return "";}
    public List<String> getExchangeSportList() {
        return tblSports.getHeaderNameOfRows();
    }
}
