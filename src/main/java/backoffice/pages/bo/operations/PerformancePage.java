package backoffice.pages.bo.operations;

import com.paltech.element.common.*;
import backoffice.common.BOConstants;
import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;

public class PerformancePage extends HomePage {
    public Button btnCreateManageLine = Button.xpath("//div[@id='header']//button[@name='create-lines']");
    public com.paltech.element.common.DropDownBox ddpLine = com.paltech.element.common.DropDownBox.name("lines");
    public TextBox txtFrom = TextBox.xpath("//div[@id='header']//input[@name='from-date']");
    public TextBox txtTo = TextBox.xpath("//div[@id='header']//input[@name='to-date']");
    public Button btnLastWeek = Button.xpath("//div[@id='header']//button[@name='lastweek']");
    public Button btnLast30Days =  Button.xpath("//div[@id='header']//button[@name='last30d']");
    public Button btnLast90Days = Button.xpath("//div[@id='header']//button[@name='last90d']");
    public Button btnLast365Days = Button.xpath("//div[@id='header']//button[@name='last365d']");
    public Button btnSubmit = Button.xpath("//div[@id='header']//button[@name='submit']");

    /*************
     * PT SETTING
     *************/
    public Label lblPTSetting = Label.xpath("//app-pt-setting/parent::div[1]//div[contains(@class,'text-primary')]");
    public Icon icPTSettingCollapse = Icon.xpath("//app-pt-setting/parent::div[1]//i[contains(@class,'collapse show')]");
    public Label lblNoOfBEt = Label.xpath("//input[@id='noOfBet']//preceding::label[1]");
    public TextBox txtNoOfBet = TextBox.id("noOfBet");
    public TextBox txtMemberWinLossFrom = TextBox.xpath("//app-pt-setting//input[@name='from-date']");
    public TextBox txtMemberWinLossTo = TextBox.xpath("//app-pt-setting//input[@name='to-date']");
    public DropDownBox ddbCurrencyType = DropDownBox.xpath("//app-pt-setting//select[@name='currencies']");
    public Button btnSubmitPTSetting = Button.xpath("//app-pt-setting//button[@name='submit']");
    public Table tblPTSetting = Table.xpath("//app-pt-setting//table[@class='ptable table-striped']",20);
    /*************
     * LINE OVERVIEW
     *************/
    public Label lblLineOverview = Label.xpath("//app-line-overview/parent::div[1]//div[contains(@class,'text-primary')]");
    public Icon icLineOverviewCollapse = Icon.xpath("//app-line-overview/parent::div[1]//i[contains(@class,'collapse show')]");
    public Button btnMemberTree = Button.xpath("//app-line-overview//button[contains(@class,'btn-member-tree')]");
    public Label lblLineOverviewNote = Label.xpath("//app-line-overview//i[contains(@class,'fa-info-circle')]/parent::div/span");
    public Label lblGeneralInformation = Label.xpath("//app-line-overview//div[@class='row']/div[1]/div[@class='mb-2']");
    public Table tblGeneralInformation1 = Table.xpath("//app-line-overview//div[@class='row']/div[1]//table[1]",2);
    public Table tblGeneralInformation2 = Table.xpath("//app-line-overview//div[@class='row']/div[1]//table[2]",3);
    public Label lblTurnover = Label.xpath("//app-line-overview//div[@class='row']/div[2]/div[@class='mb-2'][1]");
    public Table tblTurnover = Table.xpath("//app-line-overview//div[@class='row']/div[2]//table[1]",3);
    public Label lblAverageStake = Label.xpath("//app-line-overview//div[@class='row']/div[2]/div[@class='mb-2'][2]");
    public Table tblAverageStake = Table.xpath("//app-line-overview//div[@class='row']/div[2]//table[2]",3);
    public Label lblWinLoss = Label.xpath("//app-line-overview//div[@class='row']/div[3]/div[@class='mb-2'][2]");
    public Table tblWinLoss = Table.xpath("//app-line-overview//div[@class='row']/div[3]//table[2]",4);
    public Label lblPTByTurnover = Label.xpath("//app-line-overview//div[@class='row']/div[3]/div[@class='mb-2'][1]");
    public Table tblPTByTurnover = Table.xpath("//app-line-overview//div[@class='row']/div[3]//table[1]",4);
    public Label lblPerformance = Label.xpath("//app-line-overview//div[@class='row']/div[4]/div[@class='mb-2'][1]");
    public Table tblPerformance = Table.xpath("//app-line-overview//div[@class='row']/div[4]//table[1]",4);

    /*************
     * TOP PERFORMERS
     *************/
    public Label lblTopPerformance = Label.xpath("//app-top-performers/parent::div[1]//div[contains(@class,'text-primary')]");
    public Icon icTopPerformanceCollapse = Icon.xpath("//app-top-performers/parent::div[1]//i[contains(@class,'collapse show')]");
    public Tab tabTurnover = Tab.xpath("//app-top-performers//section[@id='tabs']//a[@name='turnover']");
    public Tab tabWinLoss = Tab.xpath("//app-top-performers//section[@id='tabs']//a[@name='winloss']");
    public Tab tabPerformance = Tab.xpath("//app-top-performers//section[@id='tabs']//a[@name='performance']");
    public Label lblTopPerformanceNote = Label.xpath("//app-top-performers//i[contains(@class,'fa-info-circle')]/parent::div/span");
    public Button btnMember = Button.xpath("//app-top-performers//button[@name='member']");
    public Button btnLine = Button.xpath("//app-top-performers//button[@name='line']");
    public Table tblTopPerformer = Table.xpath("//app-top-performers//table",9);


    /*************
     * Create New Line section UI
     *************/
    public Label lblCreateNewLine = Label.xpath("//div[@class='text-primary font-weight-bold mw-fit mr-2']");
    public Button btnBack = Button.xpath("//div[@class='text-primary font-weight-bold mw-fit ml-2']");
    public DropDownBox ddbBrand = DropDownBox.xpath(String.format("//span[text()='%s']/parent::div/following::select[1]", BOConstants.Reports.Performance.BRAND));
    public DropDownBox ddbLevel = DropDownBox.xpath(String.format("//span[text()='%s']/parent::div/following::select[1]", BOConstants.Reports.Performance.LEVEL));
    public DropDownBox ddbUplineId = DropDownBox.xpath(String.format("//span[text()='%s']/parent::div/following::select[1]", BOConstants.Reports.Performance.UPLINE_ID));
    public TextBox txtMappedAccountID = TextBox.xpath(String.format("//span[text()='%s']/parent::div/following::input[1]", BOConstants.Reports.Performance.MAPPED_ACCOUNT_ID));
    public TextBox txtLineName = TextBox.xpath(String.format("//span[text()='%s']/parent::div/following::input[1]", BOConstants.Reports.Performance.LINE_NAME));
    public Button btnCreate= Button.xpath("//button[contains(@class,'btn btn-sm btn-core ml-2')]");

    public Table tblLine = Table.xpath("//table[@class='ptable table-striped']",6);
    String txtSearch ="//input[@placeholder='%s']";
    public TextBox txtSearchBrand = TextBox.xpath(String.format(txtSearch,BOConstants.Reports.Performance.SEARCH_BRAND));
    public TextBox txtSearchLevel = TextBox.xpath(String.format(txtSearch,BOConstants.Reports.Performance.SEARCH_LEVEL));
    public TextBox txtSearchLine = TextBox.xpath(String.format(txtSearch,BOConstants.Reports.Performance.SEARCH_LINE));
    public TextBox txtSearchUplineID= TextBox.xpath(String.format(txtSearch,BOConstants.Reports.Performance.SEARCH_UPLINE_ID));
    public TextBox txtSearhID = TextBox.xpath(String.format(txtSearch,BOConstants.Reports.Performance.SEARCH_MAPPED_ACCOUNT_ID));



}
