package backoffice.pages.bo.temp;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.RadioButton;
import com.paltech.element.common.TextBox;
import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;


public class NetProfitConfigurationPage extends HomePage {
    public enum Type {INRToHKDRATE, SATRATE, AARATE, CASINORATE, EXCLUDEAG, SPECIALSETTING}

    public Label lblPageTitle = Label.id("bo-page-title");
    public Label lblINRToHKDRate = Label.xpath("//input[@value='INR_HKD']/parent::label");
    public Label lblAARate = Label.xpath("//input[@value='AA_RATE']/parent::label");
    public Label lblSATRate = Label.xpath("//input[@value='SAT_RATE']/parent::label");
    public Label lblCasinoRate = Label.xpath("//input[@value='CASINO_VIR']/parent::label");
    public Label lblExcludeAgent = Label.xpath("//input[@value='EXC_AGENT']/parent::label");
    public Label lblSpecialSetting = Label.xpath("//input[@value='SPC_SETTING']/parent::label");

    public RadioButton rbINRToHKDRate = RadioButton.xpath("//input[@value='INR_HKD']");
    public RadioButton rbAARate = RadioButton.xpath("//input[@value='AA_RATE']");
    public RadioButton rbSATRate = RadioButton.xpath("//input[@value='SAT_RATE']");
    public RadioButton rbCasioVirtualCurrency = RadioButton.xpath("//input[@value='CASINO_VIR']");
    public RadioButton rbExcludeAgent = RadioButton.xpath("//input[@value='EXC_AGENT']");
    public RadioButton rbSpecialSetting = RadioButton.xpath("//input[@value='SPC_SETTING']");
    public Button btnSubmit = Button.xpath("//button[text()='Submit']");
    public Button btnCancel = Button.xpath("//button[text()='Cancel']");

    /*************
     * INR to HKD Rate
     *************/

    public TextBox txtToHKD = TextBox.xpath("//app-inr-to-hkd-rate//input");
    public Table tblRateINRToHKD = Table.xpath("//app-inr-to-hkd-rate//table[@class='ptable report hasDownline']",3);

    /*************
     * AA Rate
     *************/
    public TextBox txtUserName = TextBox.xpath("//app-aa-rate//label[text()='UserName:']/parent::div/input");
    public TextBox txtPlayerRate = TextBox.xpath("//app-aa-rate//label[text()='Player Rate:']/parent::div/input");
    public TextBox txtPortalRate = TextBox.xpath("//app-aa-rate//label[text()='Portal Rate:']/parent::div/input");
    public Table tblAARate = Table.xpath("//app-aa-rate//table[@class='ptable report hasDownline']",7);

    /*************
     * SAT Rate
     *************/
    public Table tblSATRAte = Table.xpath("//app-sat-rate//table[@class='ptable report hasDownline']",4);
    public TextBox txtUsernameLoginID = TextBox.xpath("//app-sat-rate//label[contains(text(),'Username')]/parent::div/input");
    public TextBox txtDealRate = TextBox.xpath("//app-sat-rate//label[text()='Deal Rate:']/parent::div/input");
    public TextBox txtBrand = TextBox.xpath("//app-sat-rate//label[text()='Brand']/parent::div/select");

    /*************
     * SAT Rate
     *************/
    public TextBox txtBrandExclude = TextBox.xpath("//app-exclude-agent//label[text()='Brand']/parent::div/select");
    public TextBox txtUsernameExclude_Agent = TextBox.xpath("//app-exclude-agent//label[text()='UserName']/parent::div/input");
    public Button btnAddAgent = Button.xpath("//app-exclude-agent//div[@class='btnSearch']/button");
    public Table tblExcludeAgent = Table.xpath("//app-exclude-agent//table[@class='ptable report hasDownline']",8);

    public void selectRate(Type type){
        switch (type){
            case AARATE:
                rbAARate.click();
                break;
            case SATRATE:
                rbSATRate.click();
                break;
            case EXCLUDEAG:
                rbExcludeAgent.click();
                break;
            case CASINORATE:
                rbCasioVirtualCurrency.click();
                break;
            case SPECIALSETTING:
                rbSpecialSetting.click();
                break;
            default:
                rbINRToHKDRate.click();
                break;
        }
    }
}
