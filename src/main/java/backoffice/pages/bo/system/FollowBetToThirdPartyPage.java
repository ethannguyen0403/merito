package backoffice.pages.bo.system;

import backoffice.common.BOConstants;
import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;

import java.util.List;

public class FollowBetToThirdPartyPage extends HomePage {
    public TextBox txtPlayer = TextBox.id("basic-url");
    public Button btnAdd = Button.xpath("//button[contains(@class,'btn-add-player')]");
    public int colUserCode = 1;
    public int colLoginId = 2;
    public int colStatus = 3;
    public int colFollowStatus = 4;
    public int colAction = 11;
    private int totalColumn = 11;
    public Table tblFollowBet = Table.xpath("//table[contains(@class,'table-bordered')]", totalColumn);

    public void addPlayer(String player) {
        txtPlayer.sendKeys(player);
        btnAdd.click();

    }


    public void removePlayer(String userCode) {
        List<String> lstAccount = tblFollowBet.getColumn(colUserCode, 1, false);
        if (lstAccount.get(0).equalsIgnoreCase(BOConstants.NO_RECORD_FOUND)) {
            System.out.println(String.format("The player %s does not exist in the list", userCode));
            return;
        }
        Link link;
        for (int i = 0; i < lstAccount.size(); i++) {
            if (lstAccount.get(i).equalsIgnoreCase(userCode)) {
                link = (Link) tblFollowBet.getControlOfCell(1, colAction, i + 1, "i[contains(@class,'fa-trash-alt')]");
                link.click();
                return;
            }
        }
        System.out.println(String.format("The player %s does not exist in the list", userCode));

    }

    public boolean isPlayerExist(String userCode) {
        String noRecordMessage = tblFollowBet.getColumn(colUserCode, 1, false).get(0);
        if (noRecordMessage.equalsIgnoreCase(BOConstants.NO_RECORD_FOUND)) {
            System.out.println(String.format("The player %s does not exist in the list", userCode));
            return false;
        }
        List<String> lstAccount = tblFollowBet.getColumn(colUserCode, false);
        for (int i = 0; i < lstAccount.size(); i++) {
            if (lstAccount.get(i).equalsIgnoreCase(userCode)) {
                System.out.println(String.format("The player exist in the list", userCode));
                return true;
            }
        }
        System.out.println(String.format("The player %s does not exist in the list", userCode));
        return false;
    }
}
