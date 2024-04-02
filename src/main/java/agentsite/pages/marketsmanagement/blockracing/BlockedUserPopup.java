package agentsite.pages.marketsmanagement.blockracing;

import agentsite.controls.Table;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.components.ConfirmPopup;
import agentsite.ultils.account.ProfileUtils;
import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static baseTest.BaseCaseTest._brandname;

public class BlockedUserPopup extends ConfirmPopup {

    public Button btnUnBlockNow = Button.xpath("//div[@class='unblock-actions']//button[contains(@class,'pbtn')][1]");
    public Button btnUnBlockNowCompetition = Button.xpath("//button[contains(@class,'pbtn mr-1')]");
    public Button btnUnblockSchedule = Button.xpath("//div[@class='unblock-actions']//button[contains(@class,'pbtn')][2]");
    public TextBox txtMarketName = TextBox.xpath("//input[contains(@class,'input-market-name')]");
    public Button btnClose = Button.xpath("//button[@class='close']");
    public int colUsername = 1;
    public int colLevel = 2;
    public int colBlockedBy = 3;
    public int colBlockedDate = 4;
    public int colSelect = 5;
    private int totalCol = 5;
    public Table tblBlockedUser = Table.xpath("//div[contains(@class,'modal-body')]//table", totalCol);

    public void unblockUser(String loginId) {
        int index = findUserNameRowIndex(loginId);
        Icon icBlock = Icon.xpath(tblBlockedUser.getxPathOfCell(1, colSelect, index, "i"));
        icBlock.click();
    }

    private int findUserNameRowIndex(String username) {
        int i = 1;
        Label lblUsername;
        while (true) {
            lblUsername = Label.xpath(tblBlockedUser.getxPathOfCell(1, colUsername, i, null));
            if (!lblUsername.isDisplayed()) {
                System.out.println("DEBUG! Not found " + username + "in the table");
                return 0;
            }
            if (lblUsername.getText().trim().equals(username)) {
                return i;
            }
            i = i + 1;
        }
    }

    public void verifyBlockedInfoDisplayCorrect(AccountInfo accountBlocked, AccountInfo accountActionBlock, String blockDate) {
        List<ArrayList<String>> lstInfo = tblBlockedUser.getRowsWithoutHeader(1, false);
        Assert.assertTrue(lstInfo.get(0).get(0).contains(accountBlocked.getUserCode()), "FAILED! Username does not display correct");
        Assert.assertTrue(lstInfo.get(0).get(1).contains(ProfileUtils.convertDownlineByBrand(accountBlocked.getLevel(), _brandname)), "FAILED! Level does not display correct");
        Assert.assertTrue(lstInfo.get(0).get(2).contains(accountActionBlock.getUserCode()), "FAILED! Block by does not display correct");
        Assert.assertTrue(lstInfo.get(0).get(3).contains(blockDate), "FAILED! Block date does not display correct");
    }

}
