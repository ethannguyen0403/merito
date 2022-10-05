package agentsite.pages.all.agentmanagement;


import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import agentsite.controls.Table;
import agentsite.pages.all.components.ConfirmPopup;
import agentsite.pages.all.components.LeftMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShadowPlayersListingPage extends LeftMenu {
    public TextBox txtGroupName = TextBox.xpath("//label[text()='Group Name']/following::input[1]");
    public TextBox txtPT = TextBox.xpath("//label[text()='PT%']/following::input[1]");
    public Button btnAddGroup = Button.xpath("//button[contains(@class,'addGroup')]");
    TextBox txtPlayerAgentName = TextBox.xpath("//input[@placeholder='Username or Login ID']");
    Button btnAddPlayerAgent= Button.xpath("//app-shadow-player-listing/div[1]/div[2]//button");
    private Label iconLoadSpinner = Label.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    public int tblByGoupSettingTotalCol = 5;
    private int colGoupName = 1;
    private int colPT = 2;
    private int colLastUpdateBy = 3;
    private int colLastUpdateTime= 4;
    private int colAction=5;
    public Table tblByGroupSetting = Table.xpath("//app-shadow-player-listing/div[1]/div[1]//table",tblByGoupSettingTotalCol);
    public int tblAddAgentPlayerTotalCol = 5;
    public int colUsername = 1;
    public int colLoginID = 2;
    public int colAddAgentPlayerAction = 5;
    public Table tblAddAgentPlayer = Table.xpath("//app-shadow-player-listing/div[1]/div[2]//table", tblAddAgentPlayerTotalCol);

    public void addGroup(String groupName, String PT){
        txtGroupName.sendKeys(groupName);
        txtPT.sendKeys(PT);
        btnAddGroup.click();
        waitingLoadingSpinner();
    }

    public ConfirmPopup addAgentPlayer(String groupName, String agentOrPlayerAccount)
    {
        // Select groupName
        selectGroupName(groupName);

        //input agent or player account
        txtPlayerAgentName.sendKeys(agentOrPlayerAccount);

        //Click add agent player
        btnAddPlayerAgent.click();
        return new ConfirmPopup();
    }

    public void selectGroupName(String groupName)
    {
        tblByGroupSetting.getControlBasedValueOfDifferentColumnOnRow(groupName,1,colGoupName,1,null,colGoupName,null,false,false).click();
    }


    public boolean verifyByGroupInfo(String groupName, String PT, String lastUpdateBy, String lastUpdateDate){
        List<ArrayList<String>> shadowData = tblByGroupSetting.getRowsWithoutHeader(false);
        for(int i=0; i< shadowData.size();i++){
            if(shadowData.get(i).get(colGoupName-1).equals(groupName))
            {
                if(!shadowData.get(i).get(colPT-1).equals(PT))
                {
                    System.err.println(String.format("FAILED! PT expected is %s but found %s ",PT,shadowData.get(i).get(colPT-1)));
                    return false;
                }

                if(!shadowData.get(i).get(colLastUpdateBy-1).equals(lastUpdateBy))
                {
                    System.err.println(String.format("FAILED! Account to bet is %s but found %s ",lastUpdateBy,shadowData.get(i).get(colLastUpdateBy-1)));
                    return false;
                }
                if(!lastUpdateDate.isEmpty()){
                    if(!shadowData.get(i).get(colLastUpdateTime-1).equals(lastUpdateDate))
                    {
                        System.err.println(String.format("FAILED! Account to bet is %s but found %s ",lastUpdateDate,shadowData.get(i).get(colLastUpdateTime-1)));
                        return false;
                    }
                }

                return true;
            }
        }
        System.out.println(String.format("The group name %s not exist in the list",groupName));
        return false;
    }

    public void editGroup(String groupName, String PT)
    {
        clickAction(groupName,"Edit".toUpperCase());
        TextBox txt = TextBox.xpath(tblByGroupSetting.getControlxPathBasedValueOfDifferentColumnOnRow(groupName,1,colGoupName,1,null,colPT,"input",false,false));
        txt.isPresent(2);
        txt.sendKeys(PT);
        clickAction(groupName,"Edit".toUpperCase());

    }
    public Object clickAction(String groupName, String action)
    {
        Button btn;
        switch (action){
            case "EDIT":
                btn = Button.xpath(tblByGroupSetting.getControlxPathBasedValueOfDifferentColumnOnRow(groupName,1,colGoupName,1,null,colAction,"button[1]",false,false));
                if (Objects.isNull(btn)) {
                    System.err.println(String.format("ERROR: Cannot get Edit button at group name %s", groupName));
                    return null;
                }
                btn.click();
                return null;
            default:
                btn = Button.xpath(tblByGroupSetting.getControlxPathBasedValueOfDifferentColumnOnRow(groupName,1,colGoupName,1,null,colAction,"button[2]",false,false));
                if (Objects.isNull(btn)) {
                    System.err.println(String.format("ERROR: Cannot get Delete button at group name %s", groupName));
                    return null;
                }
                btn.click();
                return new ConfirmPopup();
        }
    }

    public ConfirmPopup removeAgentPlayerByGroup(String groupName, String action)
    {
        Button btn =Button.xpath(tblAddAgentPlayer.getControlxPathBasedValueOfDifferentColumnOnRow(groupName,1,colLoginID,1,null,colAddAgentPlayerAction,"button[1]",false,false));
        btn.click();
        return new ConfirmPopup();
    }


    public void waitingLoadingSpinner()
    {
        iconLoadSpinner.waitForControlInvisible(1,1);
    }
}
