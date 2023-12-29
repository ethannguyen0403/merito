package membersite.pages.proteus;

import com.paltech.element.common.Label;
import membersite.pages.HomePage;

public class ProteusHomePage extends HomePage {
    public Label lblView = Label.xpath("//li[contains(@class,'view-mode')]/span");
    public ProteusHomePage(String types) {
        super(types);
    }


}
