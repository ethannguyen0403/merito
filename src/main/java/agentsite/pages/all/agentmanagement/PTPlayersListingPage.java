package agentsite.pages.all.agentmanagement;


import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import agentsite.controls.Table;
import agentsite.pages.all.agentmanagement.ptlisting.PTDetailPopup;
import agentsite.pages.all.components.ConfirmPopup;
import agentsite.pages.all.components.LeftMenu;

import java.util.ArrayList;
import java.util.List;

public class PTPlayersListingPage extends LeftMenu {
    public TextBox txtGroupName = TextBox.xpath("//label[text()='Group Name']/following::input[1]");
    TextBox txtPlayerAgentName = TextBox.xpath("//input[@placeholder='Username or Login ID']");
    public Button btnAddGroup = Button.xpath("//button[contains(@class,'addGroup')]");
    Button btnAddPlayerAgent= Button.xpath("//app-pt-player-listing/div[1]/div[2]//button");
    private Label iconLoadSpinner = Label.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    public int tblGroupTotalCol = 5;
    private int colGoupName = 1;
    private int colPT = 2;
    private int colLastUpdateBy = 3;
    private int colLastUpdateTime= 4;
    private int colAction=5;
    public Table tblGroup= Table.xpath("//app-pt-player-listing/div[1]/div[1]//table", tblGroupTotalCol);
    public int tblPTListTotalCol = 5;
    public int colUsername = 1;
    public int colLoginID = 2;
    public int colLevel = 3;
    public int colUpline = 4;
    public int colPTListAction = 5;
    public Table tblPTList = Table.xpath("//app-pt-player-listing/div[1]/div[2]//table", tblPTListTotalCol);

    public void addGroup(String groupName){
        txtGroupName.sendKeys(groupName);
        btnAddGroup.click();
        waitingLoadingSpinner();
    }

    public ConfirmPopup addAgentPlayer(String groupName, String agentOrPlayerAccount)
    {
        // Select groupName
        selectGroupName(groupName);
        waitingLoadingSpinner();
        //input agent or player account
        txtPlayerAgentName.sendKeys(agentOrPlayerAccount);

        //Click add agent player
        btnAddPlayerAgent.click();
        return new ConfirmPopup();
    }

    public void selectGroupName(String groupName)
    {
        tblGroup.getControlBasedValueOfDifferentColumnOnRow(groupName,1,colGoupName,1,null,colGoupName,null,false,false).click();
    }

    public ConfirmPopup deleteGroupName(String groupName)
    {
        tblGroup.getControlBasedValueOfDifferentColumnOnRow(groupName,1,colGoupName,1,null,colAction,"button[1]",false,false).click();
        return new ConfirmPopup();
    }


    public boolean verifyByGroupInfo(String groupName,  String lastUpdateBy, String lastUpdateDate){
        List<ArrayList<String>> ptPlayersData = tblGroup.getRowsWithoutHeader(false);
        for(int i=0; i< ptPlayersData.size();i++){
            if(ptPlayersData.get(i).get(colGoupName-1).equals(groupName))
            {
                if(!ptPlayersData.get(i).get(colLastUpdateBy-1).equals(lastUpdateBy))
                {
                    System.err.println(String.format("FAILED! Account to bet is %s but found %s ",lastUpdateBy,ptPlayersData.get(i).get(colLastUpdateBy-1)));
                    return false;
                }
                if(!lastUpdateDate.isEmpty()){
                    if(!ptPlayersData.get(i).get(colLastUpdateTime-1).equals(lastUpdateDate))
                    {
                        System.err.println(String.format("FAILED! Account to bet is %s but found %s ",lastUpdateDate,ptPlayersData.get(i).get(colLastUpdateTime-1)));
                        return false;
                    }
                }

                return true;
            }
        }
        System.out.println(String.format("The group name %s not exist in the list",groupName));
        return false;
    }

    public PTDetailPopup openPTDetailPopup(String groupName)
    {
        // Click Detail button of the according Group Name
        Button btn = Button.xpath(tblGroup.getControlxPathBasedValueOfDifferentColumnOnRow(groupName,1,colGoupName,1,null,colPT,"button[1]",false,false));
        btn.click();
        return new PTDetailPopup();
    }

    public ConfirmPopup removeAgentPlayer(String groupName, String account)
    {
        selectGroupName(groupName);
        Button btn =Button.xpath(tblPTList.getControlxPathBasedValueOfDifferentColumnOnRow(account,1,colLoginID,1,null,colPTListAction,"button[1]",false,false));
        btn.click();
        return new ConfirmPopup();
    }


    public void waitingLoadingSpinner()
    {
        iconLoadSpinner.waitForControlInvisible(1,1);
    }
}
