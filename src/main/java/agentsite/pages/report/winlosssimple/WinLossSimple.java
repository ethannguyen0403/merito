package agentsite.pages.report.winlosssimple;

import agentsite.controls.DropDownBox;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;

import java.util.ArrayList;
import java.util.List;

public class WinLossSimple {
    protected Button btnToday = Button.name("today");
    protected Button btnYesterday = Button.name("yesterday");
    protected Button btnLastWeek = Button.name("lastWeek");
    public Button btnSubmit = Button.name("search");
    protected Label lblYouCanSeeReportData = Label.xpath("(//span[@class='pinfo']/following::label)[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//td[@class='product-multiselect']", "//div[contains(@class,'dropdown-list')]//ul//li");

    public List<ArrayList<String>> getListWinnerInfor(){return null;}
    public List<ArrayList<String>> getListLoserInfor(){return null;}
    public void filter(String productName) {
        if (productName.equalsIgnoreCase("UnSelect All")) {
            ddbProduct.uncheckAll(true);
        }
        if (productName.equalsIgnoreCase("Select ALl"))
            ddbProduct.checkAll(true);
        if (!productName.isEmpty()) {
            ddbProduct.selectByVisibleText(productName, true, true);
        }
        btnSubmit.click();
    }
    public void verifyUIDisplaysCorrect(){}
}
