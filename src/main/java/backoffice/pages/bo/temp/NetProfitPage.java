package backoffice.pages.bo.temp;

import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;

public class NetProfitPage extends HomePage {
    public Label lblPageTitle = Label.id("bo-page-title");
    public Label lblYear = Label.xpath("//div[contains(@class,'search')]/div[@class='comboBox'][1]/label");
    public Label lblBrand = Label.xpath("//div[contains(@class,'search')]/div[@class='comboBox'][2]/div/label");
    public DropDownBox ddbYear = DropDownBox.xpath("//div[contains(@class,'search')]/div[@class='comboBox'][1]/label");
    public Button btnSearch = Button.name("search");
    public backoffice.controls.DropDownBox ddbBrand = backoffice.controls.DropDownBox.xpath("//div[contains(@class,'search')]/div[@class='comboBox'][2]//angular2-multiselect", "//div[contains(@class,'search')]/div[@class='comboBox'][2]//angular2-multiselect//li/label");
    public Label lblNotes = Label.xpath("//div[@class='infor']//label");
    public Table tblNetProfitReport = Table.xpath("//table[@class='ptable report hasDownline']", 12);
    public Image imgTurnoverChart = Image.xpath("//div[@class='col-sm-12 row']/div[1]//canvas");
    public Image imgProfitChart = Image.xpath("//div[@class='col-sm-12 row']/div[2]//canvas");

}
