package agentsite.pages.agentmanagement.followbets;

import agentsite.controls.Table;
import agentsite.pages.components.ConfirmPopup;
import agentsite.ultils.account.ProfileUtils;
import com.paltech.element.common.*;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class NewUIFollowBets extends FollowBets{
    //level PO
    public int colTblPlayerAgentList = 5;
    private int colTblByPlayer = 11;
    public Table tblAddAgentPlayer = Table.xpath("//app-follow-bygroup/div[1]/div[2]//table[contains(@class,'table-sm ptable')]", colTblPlayerAgentList);
    public Table tblByPlayer = Table.xpath("//app-follow-byplayer//table[contains(@class,'table-sm ptable')]", colTblByPlayer);
    private int colUsername = 1;
    private int colAddAgentPlayerAction = 5;

    //level SMA
    private TextBox txtSmartPlayerFilter = TextBox.xpath("//app-follow-bet-sma//div[text()='Smart Player']//following-sibling::input");
    private Button btnSearch = Button.xpath("//app-follow-bet-sma//button[text()='Search']");
    private Button btnFollowBetConfig = Button.xpath("//app-follow-bet-sma//button[text()='Follow Bet Configuration']");
    private Table tblResultSmartPlayer = Table.xpath("//app-follow-bet-sma//table[contains(@class,'table-sm')]",13);
    private TextBox txtSmartPlayerConfig = TextBox.xpath("//app-follow-bet-configuration//input[@formcontrolname='smartPlayer']");
    private Button btnSwitchFollowStatus = Button.xpath("//app-follow-bet-configuration//span[@class='slider round']");
    private DropDownBox ddbAccountToFollow = DropDownBox.xpath("//app-follow-bet-configuration//select[@formcontrolname='accountToFollow']");
    private RadioButton rdFollowAllSport = RadioButton.xpath("//app-follow-bet-configuration//input[@id='radio1']");
    private RadioButton rdFollowSpecificSport = RadioButton.xpath("//app-follow-bet-configuration//input[@id='radio2']");
    private TextBox txtFollowStakePercent = TextBox.xpath("//app-follow-bet-configuration//input[@formcontrolname='followStake']");
    private TextBox txtSoccerStakePercent = TextBox.xpath("//app-follow-bet-configuration//input[@formcontrolname='followSoccer']");
    private TextBox txtCricketStakePercent = TextBox.xpath("//app-follow-bet-configuration//input[@formcontrolname='followCricket']");
    private TextBox txtTennisStakePercent = TextBox.xpath("//app-follow-bet-configuration//input[@formcontrolname='followTennis']");
    private TextBox txtBasketballStakePercent = TextBox.xpath("//app-follow-bet-configuration//input[@formcontrolname='followBasketball']");
    private TextBox txtHorseRacingStakePercent = TextBox.xpath("//app-follow-bet-configuration//input[@formcontrolname='followHorseRacing']");
    private TextBox txtOthersStakePercent = TextBox.xpath("//app-follow-bet-configuration//input[@formcontrolname='followOther']");
    private Button btnSave = Button.xpath("//app-follow-bet-configuration//button[text()='Save']");
    private Button btnClose = Button.xpath("//app-follow-bet-configuration//button[@class='close']");
    Button btnOK = Button.xpath("//div[contains(@class,'modal-footer')]//button[contains(text(),'Ok') or contains(@class,'btn-warning')or contains(text(),'OK')]");
    public NewUIFollowBets(String types) {
        super(types);
    }

    //level PO
    @Override
    public boolean isTblPlayerAgentListByGroupDisplayCorrect() {
        if (tblAddAgentPlayer.getHeaderNameOfRows().equals(AGConstant.AgencyManagement.FollowBets.PLAYER_AGENT_LIST_TABLE_HEADER_NEWUI)){
            System.out.println("Player/Agent List Header Table display correct");
            return true;
        }
        System.out.println("Player/Agent List Header Table display incorrect");
        return false;
    }

    @Override
    public boolean isHeaderTableByPlayerDisplayCorrect() {
        if (tblByPlayer.getHeaderNameOfRows().equals(AGConstant.AgencyManagement.FollowBets.PLAYER_TABLE_HEADER_NEWUI)){
            System.out.println("Header Table display correct");
            return true;
        }
        System.out.println("Header Table display incorrect");
        return false;
    }

    @Override
    public ConfirmPopup removeAgentPlayerByGroup(String loginID) {
        Button btn = Button.xpath(tblAddAgentPlayer.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, colUsername, 1, null, colAddAgentPlayerAction, "button[1]", false, false));
        btn.click();
        return new ConfirmPopup();
    }

    //level SMA
    public void filterSmartPlayer(String smartPlayer) {
        txtSmartPlayerFilter.sendKeys(smartPlayer);
        btnSearch.click();
    }

    public void verifyFilterResultCorrect(String smartPlayer) {
        int totalRow = tblResultSmartPlayer.getNumberOfRows(false, true);
        List<String> lstUsers = tblResultSmartPlayer.getColumn(1, totalRow, true);
        for (int i = 0; i < lstUsers.size(); i++) {
            Assert.assertTrue(lstUsers.get(i).contains(smartPlayer), String.format("FAILED! Result list %s is not contains smart player %s", lstUsers.get(i), smartPlayer));
        }
    }

    public void addFollowBetConfigAllSports(String smartPlayer, String accountToFollow, boolean isFollowStatus, String followStakePercent, boolean isCloseAlert) {
        btnFollowBetConfig.click();
        txtSmartPlayerConfig.sendKeys(smartPlayer);
        ddbAccountToFollow.selectByVisibleText(accountToFollow);
        if(isFollowStatus) {
            btnSwitchFollowStatus.click();
        }
        rdFollowAllSport.click();
        txtFollowStakePercent.sendKeys(followStakePercent);
        btnSave.click();
        if(isCloseAlert) {
            if(btnOK.isDisplayed()) {
                btnOK.click();
            }
        }
        waitingLoadingSpinner();
    }
    public void addFollowBetConfigSpecificSport(String smartPlayer, String accountToFollow, boolean isFollowStatus, String lstFollowStakePercent, boolean isCloseAlert) {
        btnFollowBetConfig.click();
        txtSmartPlayerConfig.sendKeys(smartPlayer);
        ddbAccountToFollow.selectByVisibleText(accountToFollow);
        if(isFollowStatus) {
            btnSwitchFollowStatus.click();
        }
        rdFollowSpecificSport.click();
        String [] lstSportsPercent = lstFollowStakePercent.split(",");
        txtSoccerStakePercent.sendKeys(lstSportsPercent[0]);
        txtCricketStakePercent.sendKeys(lstSportsPercent[1]);
        txtTennisStakePercent.sendKeys(lstSportsPercent[2]);
        txtBasketballStakePercent.sendKeys(lstSportsPercent[3]);
        txtHorseRacingStakePercent.sendKeys(lstSportsPercent[4]);
        txtOthersStakePercent.sendKeys(lstSportsPercent[5]);
        btnSave.click();
        if(isCloseAlert) {
            if(btnOK.isDisplayed()) {
                btnOK.click();
            }
        }
        waitingLoadingSpinner();
    }


    public void verifyFollowConfigAllSportsAdded(String smartPlayer, String accountToFollow, boolean isFollowStatus, String followStakePercent) {
        String userCode = ProfileUtils.getProfile().getUserCode();
        String updateTime = DateUtils.getDateFollowingGMT("GMT -4", "dd/MM/yyyy");
        List<ArrayList<String>> lstConfig = tblResultSmartPlayer.getRowsWithoutHeader(1, false);
        for (int i = 0; i < lstConfig.size(); i++) {
            Assert.assertEquals(lstConfig.get(i).get(0),smartPlayer,String.format("FAILED! Config username is not correct expected %s but found %s", smartPlayer, lstConfig.get(i).get(0)));
            Assert.assertEquals(lstConfig.get(i).get(3),followStakePercent,String.format("FAILED! Config percent Soccer is not correct expected %s but found %s", followStakePercent, lstConfig.get(i).get(3)));
            Assert.assertEquals(lstConfig.get(i).get(4),followStakePercent,String.format("FAILED! Config percent Cricket is not correct expected %s but found %s", followStakePercent, lstConfig.get(i).get(4)));
            Assert.assertEquals(lstConfig.get(i).get(5),followStakePercent,String.format("FAILED! Config percent Tennis is not correct expected %s but found %s", followStakePercent, lstConfig.get(i).get(5)));
            Assert.assertEquals(lstConfig.get(i).get(6),followStakePercent,String.format("FAILED! Config percent Basketball is not correct expected %s but found %s", followStakePercent, lstConfig.get(i).get(6)));
            Assert.assertEquals(lstConfig.get(i).get(7),followStakePercent,String.format("FAILED! Config percent Horse Racing is not correct expected %s but found %s", followStakePercent, lstConfig.get(i).get(7)));
            Assert.assertEquals(lstConfig.get(i).get(8),followStakePercent,String.format("FAILED! Config percent Other is not correct expected %s but found %s", followStakePercent, lstConfig.get(i).get(8)));
            Assert.assertEquals(lstConfig.get(i).get(9),accountToFollow,String.format("FAILED! Config Account to Follow is not correct expected %s but found %s", accountToFollow, lstConfig.get(i).get(9)));
            Assert.assertEquals(lstConfig.get(i).get(10),userCode,String.format("FAILED! Config Last Updated By is not correct expected %s but found %s", userCode, lstConfig.get(i).get(10)));
            Assert.assertTrue(lstConfig.get(i).get(11).contains(updateTime),String.format("FAILED! Config Last Updated Date is not correct expected %s but found %s", updateTime, lstConfig.get(i).get(11)));
        }
    }

    public void verifyFollowConfigSpecificSportAdded(String smartPlayer, String accountToFollow, boolean isFollowStatus, String lstFollowStakePercent) {
        String userCode = ProfileUtils.getProfile().getUserCode();
        String updateTime = DateUtils.getDateFollowingGMT("GMT +7", "dd/MM/yyyy");
        List<ArrayList<String>> lstConfig = tblResultSmartPlayer.getRowsWithoutHeader(1, false);
        String [] lstSportsPercent = lstFollowStakePercent.split(",");
        for (int i = 0; i < lstConfig.size(); i++) {
            Assert.assertEquals(lstConfig.get(i).get(0),smartPlayer,String.format("FAILED! Config username is not correct expected %s but found %s", smartPlayer, lstConfig.get(i).get(0)));
            Assert.assertEquals(lstConfig.get(i).get(3),lstSportsPercent[0],String.format("FAILED! Config percent Soccer is not correct expected %s but found %s", lstSportsPercent[0], lstConfig.get(i).get(3)));
            Assert.assertEquals(lstConfig.get(i).get(4),lstSportsPercent[1],String.format("FAILED! Config percent Cricket is not correct expected %s but found %s", lstSportsPercent[1], lstConfig.get(i).get(4)));
            Assert.assertEquals(lstConfig.get(i).get(5),lstSportsPercent[2],String.format("FAILED! Config percent Tennis is not correct expected %s but found %s", lstSportsPercent[2], lstConfig.get(i).get(5)));
            Assert.assertEquals(lstConfig.get(i).get(6),lstSportsPercent[3],String.format("FAILED! Config percent Basketball is not correct expected %s but found %s", lstSportsPercent[3], lstConfig.get(i).get(6)));
            Assert.assertEquals(lstConfig.get(i).get(7),lstSportsPercent[4],String.format("FAILED! Config percent Horse Racing is not correct expected %s but found %s", lstSportsPercent[4], lstConfig.get(i).get(7)));
            Assert.assertEquals(lstConfig.get(i).get(8),lstSportsPercent[5],String.format("FAILED! Config percent Other is not correct expected %s but found %s", lstSportsPercent[5], lstConfig.get(i).get(8)));
            Assert.assertEquals(lstConfig.get(i).get(9),accountToFollow,String.format("FAILED! Config Account to Follow is not correct expected %s but found %s", accountToFollow, lstConfig.get(i).get(9)));
            Assert.assertEquals(lstConfig.get(i).get(10),userCode,String.format("FAILED! Config Last Updated By is not correct expected %s but found %s", userCode, lstConfig.get(i).get(10)));
            Assert.assertTrue(lstConfig.get(i).get(11).contains(updateTime),String.format("FAILED! Config Last Updated Date is not correct expected %s but found %s", updateTime, lstConfig.get(i).get(11)));
        }
    }

    public void removeFollowConfig(String smartPlayer) {
        int totalRow = tblResultSmartPlayer.getNumberOfRows(false, true);
        List<String> lstUsers = tblResultSmartPlayer.getColumn(1, totalRow, true);
        String removeButtonXpath = "//app-follow-bet-sma//table[contains(@class,'table-sm')]//tbody[1]//tr[%s]//td[13]//button[text()='Remove']";
        for (int i = 0; i < lstUsers.size(); i++) {
            if(lstUsers.get(i).equalsIgnoreCase(smartPlayer)) {
                ConfirmPopup popup = new ConfirmPopup();
                Button btnRemove = Button.xpath(String.format(removeButtonXpath, i + 1));
                btnRemove.click();
                popup.confirm();
            }
        }
    }

    public String getAddFollowConfigAlertMessage() {return lblErrorContentAlert.getText().trim();}
}
