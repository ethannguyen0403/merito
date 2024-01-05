package membersite.pages.proteus;

import com.paltech.element.common.Label;
import membersite.pages.HomePage;

import static common.ProteusConstant.EURO_VIEW;

public class AsianViewPage extends ProteusHomePage {
    public Label lblView = Label.xpath("//li[contains(@class,'view-mode')]/span");
    public Label lblLoading = Label.xpath("//div[contains(@class,'loading')]");

    public AsianViewPage(String types) {
        super(types);
    }

    public void waitContentLoad(){
        lblLoading.waitForControlInvisible(2,3);
    }


}
