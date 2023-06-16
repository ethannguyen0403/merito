package agentsite.pages.marketsmanagement.blockracing;

import agentsite.controls.Table;
import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class BlockedUserPopup extends ConfirmPopup {

    public Button btnUnBlockNow = Button.xpath("//div[@class='unblock-actions']//button[contains(@class,'pbtn')][1]");
    public Button btnUnBlockNowCompetition = Button.xpath("//button[contains(@class,'pbtn mr-1')]");
    public Button btnUnblockSchedule = Button.xpath("//div[@class='unblock-actions']//button[contains(@class,'pbtn')][2]");
    public TextBox txtMarketName = TextBox.xpath("//input[contains(@class,'input-market-name')]");
    public Button btnClose = Button.xpath("//button[@class='pbtn close-btn']");
    private int totalCol = 5;
    public int colUsername = 1;
    public int colLevel = 2;
    public int colBlockedBy = 3;
    public int colBlockedDate = 4;
    public int colSelect = 6;
    public Table tblBlockedUser = Table.xpath("//div[contains(@class,'modal-body')]//table", totalCol);

    public void unblockUser(String loginId)
    {
        int index = findUserNameRowIndex(loginId);
        Icon icBlock = Icon.xpath(tblBlockedUser.getxPathOfCell(1,colSelect,index,"i"));
        icBlock.click();
    }
    private int findUserNameRowIndex(String username){
        int i =1;
        Label lblUsername;
        while (true){
            lblUsername = Label.xpath(tblBlockedUser.getxPathOfCell(1,colUsername,i,null));
            if(!lblUsername.isDisplayed()){
                System.out.println("DEBUG! Not found "+ username +"in the table");
                return 0;
            }
            if(lblUsername.getText().trim().equals(username)){
                return i;
            }
             i= i+1;
        }
    }


}
