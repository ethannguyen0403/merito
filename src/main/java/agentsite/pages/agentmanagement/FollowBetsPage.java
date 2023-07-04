package agentsite.pages.agentmanagement;


import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.followbets.GroupDetailsPopup;
import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.RadioButton;
import com.paltech.element.common.TextBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FollowBetsPage extends HomePage {
    public RadioButton rbByGoup = RadioButton.id("ragroup");
    public RadioButton rbByPlayer = RadioButton.id("raplayer");
    public int tblByGoupSettingTotalCol = 10;
    public Table tblByGroupSetting = Table.xpath("//app-follow-bygroup/div[1]/div[1]//table[contains(@class,'table-sm ptable')]", tblByGoupSettingTotalCol);
    public int tblByPlayerSettingTotalCol = 5;
    public int colUsername = 1;
    public int colLoginID = 2;
    public int colAddAgentPlayerAction = 5;
    public Table tblAddAgentPlayer = Table.xpath("//app-follow-bygroup/div[1]/div[2]//table[contains(@class,'table-sm ptable')]", tblByPlayerSettingTotalCol);
    TextBox txtPlayerAgentNameByGroup = TextBox.xpath("//app-follow-bygroup[1]/div[1]/div[1]//input[1]");
    TextBox txtPlayerAgentNameByPlayer = TextBox.xpath("//app-follow-bygroup[1]/div[1]/div[2]//input[1]");
    Button btnSearchByGroup = Button.xpath("//app-follow-bygroup//button[text()='Search']");
    Button btnAddPlayerAgent = Button.xpath("//app-follow-bygroup/div[1]/div[2]//table[@class='ptable info']//button");
    Button btnAddGroup = Button.xpath("//app-follow-bygroup//button[text()='Add Group']");
    private Label iconLoadSpinner = Label.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    private int colGoupName = 1;
    private int colFollowStatus = 2;
    private int colExchange = 3;
    private int colFancy = 4;
    private int colAdditionalFollowStake = 5;
    private int colAdditionalFollowOdds = 6;
    private int colAccountToBet = 7;
    private int colLastUpdateBy = 8;
    private int colLastUpdateTime = 9;
    private int colAction = 10;

    public FollowBetsPage(String types) {
        super(types);
    }

    public GroupDetailsPopup clickAddGroup(String type) {
        switch (type) {
            case "BY PLAYER":
                rbByPlayer.click();
                break;
            default:
                rbByGoup.click();
        }
        btnAddGroup.click();
        return new GroupDetailsPopup();

    }

    public void addGroup(String groupName, String followStatus, String accountToBet, String additionalStake, String additionalOddRange, String product, String stake, boolean isFollowall) {
        rbByGoup.click();
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
        txtPlayerAgentNameByPlayer.sendKeys(agentOrPlayerAccount);

        //Click add agent player
        btnAddPlayerAgent.click();
        return new ConfirmPopup();
    }

    public void selectGroupName(String groupName) {
        tblByGroupSetting.isTextDisplayed(groupName, 2);
        tblByGroupSetting.getControlBasedValueOfDifferentColumnOnRow(groupName, 1, colGoupName, 1, null, colGoupName, null, false, false).click();
    }

    public boolean verifyByGroupInfo(String groupName, String followStatus, String exchangeFollow, String fancyFollow, String additionalFollowStake, String additionalFollowOdds, String accounToBet, String lastUpdateBy, String lastUpdateDate) {
        List<ArrayList<String>> byGoupData = tblByGroupSetting.getRowsWithoutHeader(false);
        for (int i = 0; i < byGoupData.size(); i++) {
            if (byGoupData.get(i).get(colGoupName - 1).equals(groupName)) {
                Button btnFollowStatus = Button.xpath(tblByGroupSetting.getControlxPathBasedValueOfDifferentColumnOnRow(groupName, 1, colGoupName, 1, null, colFollowStatus, "button[contains(@class,'yesNoBtn')]", false, false));
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
                btn = Button.xpath(tblByGroupSetting.getControlxPathBasedValueOfDifferentColumnOnRow(groupName, 1, colGoupName, 1, null, colAction, "button[1]", false, false));
                if (Objects.isNull(btn)) {
                    System.err.println(String.format("ERROR: Cannot get Edit button at group name %s", groupName));
                    return null;
                }
                btn.click();//app-follow-bygroup/div[1]/div[2]/div/div[2]/button
                return new GroupDetailsPopup();
            default:
                btn = Button.xpath(tblByGroupSetting.getControlxPathBasedValueOfDifferentColumnOnRow(groupName, 1, colGoupName, 1, null, colAction, "button[2]", false, false));
                if (Objects.isNull(btn)) {
                    System.err.println(String.format("ERROR: Cannot get Delete button at group name %s", groupName));
                    return null;
                }
                btn.click();
                return new ConfirmPopup();
        }
    }

    public ConfirmPopup removeAgentPlayerByGroup(String loginID, String action) {
        Button btn = Button.xpath(tblAddAgentPlayer.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, colUsername, 1, null, colAddAgentPlayerAction, "button[1]", false, false));
        btn.click();
        return new ConfirmPopup();
    }


    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(1, 1);
    }
}
