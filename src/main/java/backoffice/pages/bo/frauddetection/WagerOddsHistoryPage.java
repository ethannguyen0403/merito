package backoffice.pages.bo.frauddetection;

import backoffice.controls.Table;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Image;
import com.paltech.element.common.TextBox;
import backoffice.pages.bo.home.HomePage;

public class WagerOddsHistoryPage extends HomePage {
    public TextBox txtWagerId = TextBox.name("wagerID");
    public DropDownBox ddbWithin = DropDownBox.xpath("//div[@class='row header-market']//select");
    public Button btnView = Button.xpath("//input[contains(@class,'mb-2 btn btn-warning')]");
    public Image imgPriceVolumeChart = Image.xpath("//canvas[@id='container-chart']");
    public Table tblWagerOddsHistory= Table.xpath("//table[contains(@class,'table table-odds')]",3);

    public void search(String wagerID, String within){
        txtWagerId.sendKeys(wagerID);
        if(!within.isEmpty())
            ddbWithin.selectByVisibleText(within);
        btnView.click();
    }


}
