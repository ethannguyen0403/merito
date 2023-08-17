package agentsite.pages.agentmanagement;


import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.followbets.GroupDetailsPopup;
import agentsite.pages.agentmanagement.followbets.PlayerDetailsPopup;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.components.ConfirmPopup;
import agentsite.pages.agentmanagement.followbets.FollowBets;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.RadioButton;
import com.paltech.element.common.TextBox;
import common.AGConstant;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FollowBetsPage extends HomePage {
    public RadioButton rbByGroup = RadioButton.id("ragroup");
    public RadioButton rbByPlayer = RadioButton.id("raplayer");
    public int colTblGroupList = 10;
    public Table tblGroupList = Table.xpath("//app-follow-bygroup/div[1]/div[1]//table[contains(@class,'table-sm ptable')]", colTblGroupList);
    public int colTblPlayerAgentList = 5;
    public int colUsername = 1;
    public int colLoginID = 2;
    public int colAddAgentPlayerAction = 5;
    String lblPlayerAgentNameByGroupXpath = "(//label[text()='Player/Agent Name'])[%s]";
    public Table tblAddAgentPlayer = Table.xpath("//app-follow-bygroup/div[1]/div[2]//table[contains(@class,'table-sm ptable')]", colTblPlayerAgentList);
    public TextBox txtPlayerAgentNameSearchByGroup = TextBox.xpath("//app-follow-bygroup[1]/div[1]/div[1]//input[1]");
    TextBox txtPlayerAgentNameAddPlayerByGroup = TextBox.xpath("//app-follow-bygroup[1]/div[1]/div[2]//input[1]");
    public Button btnSearchByGroup = Button.xpath("//app-follow-bygroup//button[text()='Search']");
    public Button btnSearchByPlayer = Button.xpath("//app-follow-byplayer//button[text()='Search']");
    Button btnAddPlayerAgent = Button.xpath("//app-follow-bygroup/div[1]/div[2]//table[@class='ptable info']//button");
    public Button btnAddPlayer = Button.xpath("//app-follow-byplayer//button[text()='Add Player']");
    public Button btnAddGroup = Button.xpath("//app-follow-bygroup//button[text()='Add Group']");
    private Label iconLoadSpinner = Label.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    private int colGroupName = 1;
    private int colFollowStatus = 2;
    private int colExchange = 3;
    private int colFancy = 4;
    private int colAdditionalFollowStake = 5;
    private int colAdditionalFollowOdds = 6;
    private int colAccountToBet = 7;
    private int colLastUpdateBy = 8;
    private int colLastUpdateTime = 9;
    private int colAction = 10;
    private int colTblByPlayer = 11;
    private int colLevel = 3;
    public Table tblByPlayer = Table.xpath("//app-follow-byplayer//table[contains(@class,'table-sm ptable')]", colTblByPlayer);
    public Label lblPlayerName = Label.xpath("//label[text()='Player Name']");
    public TextBox txtPlayerName = TextBox.xpath("//input[@type='text']");
    public Label lblErrorContent = Label.xpath("//app-alert//div[@class='modal-body modal-body-fit-with-content']");
    Label lblNoFoundRecordsInGroupList = Label.xpath("//app-follow-bygroup/div[1]/div[1]//table[contains(@class,'table-sm ptable')]//td[text()='No records found.']");
    Label lblNoFoundRecordsInPlayerList = Label.xpath("//app-follow-bygroup/div[1]/div[2]//table[contains(@class,'table-sm ptable')]//td[text()='No records found.']");
    public FollowBets followBets;
    public FollowBetsPage(String types) {
        super(types);
        followBets = ComponentsFactory.followBets(types);
    }

    public GroupDetailsPopup clickAddGroup(String type) {
        switch (type) {
            case "BY PLAYER":
                rbByPlayer.click();
                break;
            default:
                rbByGroup.click();
        }
        btnAddGroup.click();
        return new GroupDetailsPopup();
    }

    public void addGroup(String groupName, String followStatus, String accountToBet, String additionalStake, String additionalOddRange, String product, String stake, boolean isFollowall) {
        rbByGroup.click();
        btnAddGroup.click();
        waitingLoadingSpinner();
        GroupDetailsPopup popup = new GroupDetailsPopup();
        popup.createNewGroup(groupName, followStatus, accountToBet, additionalStake, additionalOddRange, product, stake, isFollowall);
        waitingLoadingSpinner();
    }

    public ConfirmPopup addAgentPlayer(String groupName, String agentOrPlayerAccount) {
        // Select groupName
        selectGroupName(groupName);
        waitingLoadingSpinner();
        //input agent or player account
        txtPlayerAgentNameAddPlayerByGroup.sendKeys(agentOrPlayerAccount);

        //Click add agent player
        btnAddPlayerAgent.click();
        return new ConfirmPopup();
    }

    public void selectGroupName(String groupName) {
        tblGroupList.isTextDisplayed(groupName, 2);
        tblGroupList.getControlBasedValueOfDifferentColumnOnRow(groupName, 1, colGroupName, 1, null, colGroupName, null, false, false).click();
    }

    public boolean verifyByGroupInfo(String groupName, String followStatus, String exchangeFollow, String fancyFollow, String additionalFollowStake, String additionalFollowOdds, String accounToBet, String lastUpdateBy, String lastUpdateDate) {
        List<ArrayList<String>> byGoupData = tblGroupList.getRowsWithoutHeader(false);
        for (int i = 0; i < byGoupData.size(); i++) {
            if (byGoupData.get(i).get(colGroupName - 1).equals(groupName)) {
                Button btnFollowStatus = Button.xpath(tblGroupList.getControlxPathBasedValueOfDifferentColumnOnRow(groupName, 1, colGroupName, 1, null, colFollowStatus, "button[contains(@class,'yesNoBtn')]", false, false));
                if (!btnFollowStatus.isDisplayed()) {
                    System.err.println(String.format("FAILED! Cannot find Follow Status button of according group name %s", groupName));
                    return false;
                }
                if (!btnFollowStatus.getText().equals(followStatus)) {
                    System.err.println(String.format("FAILED! Expected follow status is %s but found %s ", followStatus, btnFollowStatus.getText()));
                    return false;
                }
                if (!byGoupData.get(i).get(colExchange - 1).equals(exchangeFollow)) {
                    System.err.println(String.format("FAILED! Exchange is %s but found %s ", exchangeFollow, byGoupData.get(i).get(colExchange - 1)));
                    return false;
                }
                if (!byGoupData.get(i).get(colFancy - 1).equals(fancyFollow)) {
                    System.err.println(String.format("FAILED! Fancy is %s but found %s ", fancyFollow, byGoupData.get(i).get(colFancy - 1)));
                    return false;
                }
                if (!byGoupData.get(i).get(colAdditionalFollowStake - 1).equals(additionalFollowStake)) {
                    System.err.println(String.format("FAILED! Additional Follow Stake is %s but found %s ", additionalFollowStake, byGoupData.get(i).get(colAdditionalFollowStake - 1)));
                    return false;
                }
                if (!byGoupData.get(i).get(colAdditionalFollowOdds - 1).equals(additionalFollowOdds)) {
                    System.err.println(String.format("FAILED! Additional Follow Odds is %s but found %s ", additionalFollowOdds, byGoupData.get(i).get(colAdditionalFollowOdds - 1)));
                    return false;
                }
                if (!byGoupData.get(i).get(colAccountToBet - 1).contains(accounToBet)) {
                    System.err.println(String.format("FAILED! Account to bet is %s but found %s ", accounToBet, byGoupData.get(i).get(colAccountToBet - 1)));
                    return false;
                }
                if (!byGoupData.get(i).get(colLastUpdateBy - 1).equals(lastUpdateBy)) {
                    System.err.println(String.format("FAILED! Account to bet is %s but found %s ", lastUpdateBy, byGoupData.get(i).get(colLastUpdateBy - 1)));
                    return false;
                }
                if (!lastUpdateDate.isEmpty()) {
                    if (!byGoupData.get(i).get(colLastUpdateTime - 1).equals(lastUpdateDate)) {
                        System.err.println(String.format("FAILED! Account to bet is %s but found %s ", lastUpdateDate, byGoupData.get(i).get(colLastUpdateTime - 1)));
                        return false;
                    }
                }

                return true;
            }
        }
        System.out.println(String.format("The group name %s not exist in the list", groupName));
        return false;
    }

    public Object clickAction(String groupName, String action) {
        Button btn;
        switch (action) {
            case "Edit":
                btn = Button.xpath(tblGroupList.getControlxPathBasedValueOfDifferentColumnOnRow(groupName, 1, colGroupName, 1, null, colAction, "button[1]", false, false));
                if (Objects.isNull(btn)) {
                    System.err.println(String.format("ERROR: Cannot get Edit button at group name %s", groupName));
                    return null;
                }
                btn.click();//app-follow-bygroup/div[1]/div[2]/div/div[2]/button
                return new GroupDetailsPopup();
            default:
                btn = Button.xpath(tblGroupList.getControlxPathBasedValueOfDifferentColumnOnRow(groupName, 1, colGroupName, 1, null, colAction, "button[2]", false, false));
                if (Objects.isNull(btn)) {
                    System.err.println(String.format("ERROR: Cannot get Delete button at group name %s", groupName));
                    return null;
                }
                btn.click();
                return new ConfirmPopup();
        }
    }

    public ConfirmPopup removeAgentPlayerByGroup(String loginID) {
        Button btn = Button.xpath(tblAddAgentPlayer.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, colUsername, 1, null, colAddAgentPlayerAction, "button[1]", false, false));
        btn.click();
        return new ConfirmPopup();
    }


    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(1, 1);
    }

    public boolean isFormSearchByGroupDisplayCorrect() {
        Label lblPlayerAgentName = Label.xpath(String.format(lblPlayerAgentNameByGroupXpath,"1"));
        if (!lblPlayerAgentName.isDisplayed()){
            System.out.println("Player/Agent Name Label display incorrect!");
            return false;
        }
        if (!txtPlayerAgentNameSearchByGroup.isDisplayed()){
            System.out.println("Player/Agent Name Textbox display incorrect!");
            return false;
        }
        if (!btnSearchByGroup.isDisplayed()){
            System.out.println("Search Button display incorrect!");
            return false;
        }
        System.out.println("Player/Agent Name Label display correct!");
        System.out.println("Player/Agent Name Textbox display correct!");
        System.out.println("Search Button display correct!");
        return true;
    }

    public boolean isFormAddPlayerAgentByGroupDisplayCorrect() {
        Label lblPlayerAgentName = Label.xpath(String.format(lblPlayerAgentNameByGroupXpath,"2"));
        if (!lblPlayerAgentName.isDisplayed()){
            System.out.println("Player/Agent Name Label display incorrect!");
            return false;
        }
        if (!txtPlayerAgentNameAddPlayerByGroup.isDisplayed()){
            System.out.println("Player/Agent Name Textbox display incorrect!");
            return false;
        }
        if (!btnAddPlayerAgent.isDisplayed()){
            System.out.println("Search Button display incorrect!");
            return false;
        }
        System.out.println("Player/Agent Name Label display correct!");
        System.out.println("Player/Agent Name Textbox display correct!");
        System.out.println("Add Player Agent Button display correct!");
        return true;
    }

    public boolean isTblGroupListByGroupDisplayCorrect() {
        if (tblGroupList.getHeaderNameOfRows().equals(AGConstant.AgencyManagement.FollowBets.GROUP_LIST_TABLE_HEADER)){
            System.out.println("Group List Header Table display correct");
            return true;
        }
        System.out.println("Group List Header Table display incorrect");
        return false;
    }

    public void searchPlayer(String type,String playerFollowBet) {
        switch (type){
            case "By Group":
                if (!playerFollowBet.isEmpty()){
                    txtPlayerAgentNameSearchByGroup.sendKeys(playerFollowBet);
                }
                btnSearchByGroup.click();
                break;
            default:
                if (!playerFollowBet.isEmpty()){
                    txtPlayerName.sendKeys(playerFollowBet);
                }
                btnSearchByPlayer.click();
        }
    }

    public boolean isPlayerAddedDisplayCorrect(String type, String username) {
        searchPlayer(type, username);
        List<String> lstUsername = tblAddAgentPlayer.getColumn(colUsername,10,false);
        List<String> lstLoginID = tblAddAgentPlayer.getColumn(colLoginID,10,false);
        if (!lblNoFoundRecordsInPlayerList.isDisplayed()){
            for (int j = 0; j < lstUsername.size(); j++){
                if (lstUsername.get(j).equals(username) || lstLoginID.get(j).equals(username)){
                    System.out.println(username+" is added display correct!");
                    return true;
                }
            }
        } else {
            System.out.println("No records found.");
            return true;
        }
        return false;
    }
    public PlayerDetailsPopup clickAddPlayer(){
        btnAddPlayer.click();
        waitingLoadingSpinner();
        return new PlayerDetailsPopup();
    }
}
