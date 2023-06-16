package agentsite.pages.riskmanagement;

import agentsite.controls.DateTimePicker;
import agentsite.controls.RadioGroup;
import agentsite.controls.Table;
import agentsite.pages.HomePage;

import com.paltech.element.common.*;

import java.util.List;

public class VolumeMonitorPage extends HomePage {
   public Label lblLevel = Label.xpath("//div[@class='search-region']//label[@for='levelFilter']");
   public Label lblSport = Label.xpath("//div[@class='search-region']//label[@for='sportSelection']");
   public DropDownBox ddbLevel = DropDownBox.id("levelFilter");
   public agentsite.controls.DropDownBox ddpSport = agentsite.controls.DropDownBox.xpath("//angular2-multiselect//div[contains(@class,'selected-list')]",
          "//div[contains(@class,'dropdown-list')]//div[@class='list-area']//ul[@class='lazyContainer']//label");
   public TextBox txtFrom = TextBox.name("fromDate");
   public TextBox txtTo = TextBox.name("toDate");
   public DateTimePicker dtpFrom = DateTimePicker.xpath(txtFrom,"//div[@class='bs-calendar-container']");
   public DateTimePicker dtpTo = DateTimePicker.xpath(txtTo,"//di;v[@class='bs-calendar-container']");
   public Label lblNote = Label.xpath("//label[@for='iconInfoTime']");
   public Button btnToday = Button.xpath("//div[@class='action-btn']/button[1]");
    public Button btnYesterday = Button.xpath("//div[@class='action-btn']/button[2]");
    public Button btnLast7Days = Button.xpath("//div[@class='action-btn']/button[3]");
    public Button btnSubmit = Button.xpath("//div[@class='action-btn']/button[4]");
    public RadioGroup cbBetType = RadioGroup.xpath("//div[contains(@class,'bettype-group')]/div");
    public CheckBox cbSelectAll = CheckBox.id("ALL");
    public CheckBox cbMatched = CheckBox.id("matched");
    public CheckBox cbUnMatched = CheckBox.id("unmatched");
    public CheckBox cbSettled = CheckBox.id("settled");
    public CheckBox cbVoided = CheckBox.id("voided");
    public CheckBox cbLapsed = CheckBox.id("lapsed");
    public Label lblSelectAll  = Label.xpath("//label[@for='ALL']");
    public CheckBox lblMatched = CheckBox.id("//label[@for='matched']");
    public CheckBox lblUnmatched = CheckBox.id("//label[@for='unmatched']");
    public CheckBox lblSettled = CheckBox.id("//label[@for='settled']");
    public CheckBox lblVoided = CheckBox.id("//label[@for='voided']");
    public CheckBox lblLapsed = CheckBox.id("//label[@for='lapsed']");
    public int colLevel = 5;
    public int colUsername =2;
    public int colBetHistory =16;
    public Table tblReport = Table.xpath("//div[@class='downline-table']/table",16);
    public Button btnRefreshButton = Button.xpath("//div[contains(@class,'refresh-icon-container')]/button");
    public Label lblNoRecord = Label.xpath("//div[@class='downline-table']/table//td[contains(@class,'text-center')]");
    public Table tblBetHistory = Table.xpath("//div[contains(@class,'bet-history-table-container')]//table",12);

    public VolumeMonitorPage(String types){
        super(types);
    }

    public void filter(String level, String sport, String betType, String from, String to){
        if(!level.isEmpty())
            ddbLevel.selectByVisibleText(level);
        if(!sport.isEmpty())
            ddpSport.selectByVisibleText(sport,true,false);
        if(!betType.isEmpty()){
            if(!betType.equalsIgnoreCase("Select All")){
            cbBetType.clickRadio("Select All");
            cbBetType.clickRadio(betType);}
        }
        if(!from.isEmpty())
            dtpFrom.selectDate(from,"dd/MM/yyyy");
        if(!to.isEmpty())
            dtpTo.selectDate(to,"dd/MM/yyyy");
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void drilldownToLevel(String level)
    {
        if(lblNoRecord.isDisplayed())
            return;

        Link lnkUserName = (Link)tblReport.getControlOfCell(1,colUsername,1,null);
        String currentLevel = tblReport.getColumn(colLevel,1,false).get(0);
        boolean flag = false;
        while (!flag) {
            if (currentLevel.equalsIgnoreCase(level)) {
                flag = true;
            } else {
                lnkUserName.click();
                waitingLoadingSpinner();
                currentLevel = tblReport.getColumn(colLevel,1,false).get(0);
            }
        }
    }

    public void clickBetHistory(String username){
        System.out.println("Step debug: Click on the Bet History in the row username is "+username);
        tblReport.getControlBasedValueOfDifferentColumnOnRow(username,1,colUsername,1,null,colBetHistory, "span",false,false).click();
        waitingLoadingSpinner();

    }

    public boolean isBetHistoryofUserName(String username){
        List<String> listUserName = tblBetHistory.getColumn(colUsername,false);
        for (String user: listUserName) {
            if(!user.equalsIgnoreCase(username)){
                System.out.println("FAILED! Bet History display mismatch username. Actual is "+ user +" but expected is" +username);
                return false;
            }
        }
        return true;
    }

}
