package membersite.pages.proteus;

import com.paltech.element.common.Label;
import membersite.controls.DropDownMenu;
import membersite.pages.HomePage;

public class EuroViewPage extends ProteusHomePage {
    public Label lblView = Label.xpath("//li[contains(@class,'view-mode')]/span");
    public Label lblLoading = Label.xpath("//div[contains(@class,'loading-text')]/p");
    public DropDownMenu ddmOddsType = DropDownMenu.xpath("//ul[contains(@class,'control-list')]/li[2]","span[@class='mx-2 text-uppercase']","//ul[contains(@class,'sub-selections')]//li");
    public EuroViewPage(String types) {
        super(types);
    }

    public void waitContentLoad(){
        lblLoading.waitForControlInvisible(2,3);
    }

    public void selectOddsType (String oddsType){
        ddmOddsType.clickSubMenu(oddsType);
        waitContentLoad();
    }

    public String getOddsType(){
        return ddmOddsType.getSelectedItem();
    }





}
