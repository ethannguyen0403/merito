package agentsite.pages.agentmanagement.followbets;

import agentsite.controls.Table;
import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.Button;
import common.AGConstant;

public class OldUIFollowBets extends FollowBets{
    public int colTblPlayerAgentList = 5;
    private int colTblByPlayer = 11;
    public Table tblAddAgentPlayer = Table.xpath("//app-follow-bygroup/div[1]/div[2]//table[contains(@class,'table-sm ptable')]", colTblPlayerAgentList);
    public Table tblByPlayer = Table.xpath("//app-follow-byplayer//table[contains(@class,'table-sm ptable')]", colTblByPlayer);
    private int colLoginId = 2;
    private int colAddAgentPlayerAction = 5;

    @Override
    public boolean isTblPlayerAgentListByGroupDisplayCorrect() {
        if (tblAddAgentPlayer.getHeaderNameOfRows().equals(AGConstant.AgencyManagement.FollowBets.PLAYER_AGENT_LIST_TABLE_HEADER_OLDUI)){
            System.out.println("Player/Agent List Header Table display correct");
            return true;
        }
        System.out.println("Player/Agent List Header Table display incorrect");
        return false;
    }
    @Override
    public boolean isHeaderTableByPlayerDisplayCorrect() {
        if (tblByPlayer.getHeaderNameOfRows().equals(AGConstant.AgencyManagement.FollowBets.PLAYER_TABLE_HEADER_OLDUI)){
            System.out.println("Header Table display correct");
            return true;
        }
        System.out.println("Header Table display incorrect");
        return false;
    }

    @Override
    public ConfirmPopup removeAgentPlayerByGroup(String loginID) {
        Button btn = Button.xpath(tblAddAgentPlayer.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, colLoginId, 1, null, colAddAgentPlayerAction, "button[1]", false, false));
        btn.click();
        return new ConfirmPopup();
    }
}
