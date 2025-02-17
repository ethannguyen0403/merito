package backoffice.pages.bo.system;

import backoffice.pages.bo._components.LeftMenu;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class BetFairInfoPage extends LeftMenu {
    public Label lblMeritoBetID = Label.xpath("//div[@class='custom-table-body']//div[@class='custom-table-row']/div[1]");
    public Label lblBetFairBetID = Label.xpath("//div[@class='custom-table-body']//div[@class='custom-table-row']/div[2]");
    TextBox txtMeritoBetID = TextBox.xpath("//input[contains(@class,'form-control')]");
    DropDownBox ddbProduct = DropDownBox.xpath("//select[contains(@class,'form-control')]");
    Button btnSearch = Button.xpath("//button[contains(@class,'btn-core')]");

    public void searchInfo(String meritoBetID, String product) {
        txtMeritoBetID.sendKeys(meritoBetID);
        ddbProduct.selectByVisibleText(product);
        btnSearch.click();
    }

}
