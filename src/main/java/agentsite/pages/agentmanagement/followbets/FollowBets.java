package agentsite.pages.agentmanagement.followbets;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.RadioButton;
import com.paltech.element.common.TextBox;
import common.AGConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FollowBets extends HomePage {
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
    private int colActionByGroup = 10;
    private int colActionByPlayer = 11;
    private int colTblByPlayer = 11;
    private int colFollowStatusByPlayer = 3;
    private int colExchangeByPlayer = 4;
    private int colFancyByPlayer = 5;
    private int colStakeByPlayer = 6;
    private int colOddsRangeByPlayer = 7;
    private int colAccountToBetByPlayer = 8;
    private int colLastUpdateByByPlayer = 9;
    private int colLastUpdateDateByPlayer = 10;
    public Table tblByPlayer = Table.xpath("//app-follow-byplayer//table[contains(@class,'table-sm ptable')]", colTblByPlayer);
    public Label lblPlayerName = Label.xpath("//label[text()='Player Name']");
    public TextBox txtPlayerName = TextBox.xpath("//input[@type='text']");
    public Label lblErrorContent = Label.xpath("//app-alert//div[@class='modal-body modal-body-fit-with-content']");
    Label lblNoFoundRecordsInPlayerList = Label.xpath("//app-follow-bygroup/div[1]/div[2]//table[contains(@class,'table-sm ptable')]//td[text()='No records found.']");
    public Label lblErrorContentAlert = Label.xpath("//app-alert//div[contains(@class,'modal-body')]");
    public FollowBets(String types) {
        super(types);
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

    public Object clickAction(String type,String groupName, String action) {
        Button btn;
        Table table;
        int colAction;
        switch (type) {
            case "By Group":
                colAction = colActionByGroup;
                table = tblGroupList;
                break;
            default:
                colAction = colActionByPlayer;
                table = tblByPlayer;
        }
        switch (action) {
            case "Edit":
                btn = Button.xpath(table.getControlxPathBasedValueOfDifferentColumnOnRow(groupName, 1, colGroupName, 1, null, colAction, "button[1]", false, false));
                if (Objects.isNull(btn)) {
                    System.err.println(String.format("ERROR: Cannot get Edit button at group name %s", groupName));
                    return null;
                }
                btn.click();
                waitingLoadingSpinner();
                if (type.equals("By Group")){
                    return new GroupDetailsPopup();
                } else {
                    return new PlayerDetailsPopup();
                }
            default:
                btn = Button.xpath(table.getControlxPathBasedValueOfDifferentColumnOnRow(groupName, 1, colGroupName, 1, null, colAction, "button[2]", false, false));
                if (Objects.isNull(btn)) {
                    System.err.println(String.format("ERROR: Cannot get Delete button at group name %s", groupName));
                    return null;
                }
                btn.click();
                return new ConfirmPopup();
        }
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
                if (playerFollowBet != null){
                    txtPlayerAgentNameSearchByGroup.sendKeys(playerFollowBet);
                } else {
                    System.out.println("There is not player!");
                    break;
                }
                btnSearchByGroup.click();
                break;
            default:
                if (playerFollowBet != null){
                    txtPlayerName.sendKeys(playerFollowBet);
                } else {
                    System.out.println("There is not player!");
                    break;
                }
                btnSearchByPlayer.click();
                break;
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

    public ConfirmPopup removePlayerByPlayer(String username) {
        Button btn = Button.xpath(tblByPlayer.getControlxPathBasedValueOfDifferentColumnOnRow(username, 1, colUsername, 1, null, colActionByPlayer, "button[2]", false, false));
        btn.click();
        return new ConfirmPopup();
    }
    public boolean verifyByPlayerInfo(String playerName, String followStatus, String exchangeFollow, String fancyFollow, String additionalFollowStake, String additionalFollowOdds, String accounToBet, String lastUpdateBy, String lastUpdateDate) {
        List<ArrayList<String>> byPlayerData = tblByPlayer.getRowsWithoutHeader(false);
        for (int i = 0; i < byPlayerData.size(); i++) {
            if (byPlayerData.get(i).get(colGroupName - 1).equals(playerName)) {
                Button btnFollowStatus = Button.xpath(tblByPlayer.getControlxPathBasedValueOfDifferentColumnOnRow(playerName, 1, colGroupName, 1, null, colFollowStatusByPlayer, "button[contains(@class,'yesNoBtn')]", false, false));
                if (!btnFollowStatus.isDisplayed()) {
                    System.err.println(String.format("FAILED! Cannot find Follow Status button of according group name %s", playerName));
                    return false;
                }
                if (!btnFollowStatus.getText().equals(followStatus)) {
                    System.err.println(String.format("FAILED! Expected follow status is %s but found %s ", followStatus, btnFollowStatus.getText()));
                    return false;
                }
                if (!byPlayerData.get(i).get(colExchangeByPlayer - 1).equals(exchangeFollow)) {
                    System.err.println(String.format("FAILED! Exchange is %s but found %s ", exchangeFollow, byPlayerData.get(i).get(colExchange - 1)));
                    return false;
                }
                if (!byPlayerData.get(i).get(colFancyByPlayer - 1).equals(fancyFollow)) {
                    System.err.println(String.format("FAILED! Fancy is %s but found %s ", fancyFollow, byPlayerData.get(i).get(colFancy - 1)));
                    return false;
                }
                if (!byPlayerData.get(i).get(colStakeByPlayer - 1).equals(additionalFollowStake)) {
                    System.err.println(String.format("FAILED! Additional Follow Stake is %s but found %s ", additionalFollowStake, byPlayerData.get(i).get(colAdditionalFollowStake - 1)));
                    return false;
                }
                if (!byPlayerData.get(i).get(colOddsRangeByPlayer - 1).equals(additionalFollowOdds)) {
                    System.err.println(String.format("FAILED! Additional Follow Odds is %s but found %s ", additionalFollowOdds, byPlayerData.get(i).get(colAdditionalFollowOdds - 1)));
                    return false;
                }
                if (!byPlayerData.get(i).get(colAccountToBetByPlayer - 1).contains(accounToBet)) {
                    System.err.println(String.format("FAILED! Account to bet is %s but found %s ", accounToBet, byPlayerData.get(i).get(colAccountToBet - 1)));
                    return false;
                }
                if (!byPlayerData.get(i).get(colLastUpdateByByPlayer - 1).equals(lastUpdateBy)) {
                    System.err.println(String.format("FAILED! Account to bet is %s but found %s ", lastUpdateBy, byPlayerData.get(i).get(colLastUpdateBy - 1)));
                    return false;
                }
                if (!lastUpdateDate.isEmpty()) {
                    if (!byPlayerData.get(i).get(colLastUpdateDateByPlayer - 1).equals(lastUpdateDate)) {
                        System.err.println(String.format("FAILED! Account to bet is %s but found %s ", lastUpdateDate, byPlayerData.get(i).get(colLastUpdateTime - 1)));
                        return false;
                    }
                }

                return true;
            }
        }
        System.out.println(String.format("The group name %s not exist in the list", playerName));
        return false;
    }

    public void changeFollowStatus(String type, String groupName, String followStatus) {
        List<ArrayList<String>> byGroupData = new ArrayList<>();
        Table tblData;
        int colFollowStatus;
        switch (type) {
            case "By Group":
                byGroupData = tblGroupList.getRowsWithoutHeader(false);
                tblData = tblGroupList;
                colFollowStatus = this.colFollowStatus;
                break;
            default:
                byGroupData = tblByPlayer.getRowsWithoutHeader(false);
                tblData = tblByPlayer;
                colFollowStatus = colFollowStatusByPlayer;
                break;
        }
        for (int i = 0; i < byGroupData.size(); i++) {
            if (byGroupData.get(i).get(colGroupName - 1).equals(groupName)) {
                Button btnFollowStatus = Button.xpath(tblData.getControlxPathBasedValueOfDifferentColumnOnRow(groupName, 1, colGroupName, 1, null, colFollowStatus, "button[contains(@class,'yesNoBtn')]", false, false));
                if (!btnFollowStatus.getText().equals(followStatus)){
                    btnFollowStatus.click();
                }
            }
        }
    }
    public boolean isTblPlayerAgentListByGroupDisplayCorrect() {return false;}
    public boolean isHeaderTableByPlayerDisplayCorrect() {return false;}
    public ConfirmPopup removeAgentPlayerByGroup(String loginID){return new ConfirmPopup();}
}
