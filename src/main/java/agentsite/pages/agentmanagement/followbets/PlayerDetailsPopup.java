package agentsite.pages.agentmanagement.followbets;

import agentsite.controls.Table;
import com.paltech.element.common.*;

public class PlayerDetailsPopup {
    public TextBox txtPlayerName = TextBox.xpath("//app-follow-detail//label[text()='Player Name']//following-sibling::div/input");
    public TextBox txtAdditionalStake = TextBox.xpath("//label[contains(text(),'Additional Stake')]//following::input[1]");
    public TextBox txtAdditionalOddsRange = TextBox.xpath("//label[contains(text(),'Additional Odds Range')]//following::input[1]");
    public TextBox txtStake = TextBox.xpath("//label[text()='Stake %']//following::input[1]");
    public CheckBox cbFollowAll = CheckBox.id("followAllBets");
    public DropDownBox ddbFollowStatus = DropDownBox.xpath("//label[contains(text(),'Follow Status')]//following::select[1]");
    public DropDownBox ddbAccountToBet = DropDownBox.xpath("//label[contains(text(),'Account To Bet')]//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//label[contains(text(),'Product')]//following::select[1]");
    public Button btnSave = Button.xpath("//button[@class='pbtn icon-pointer' and text()='Save']");
    public Button btnClose = Button.xpath("//button[contains(@class,'close')]");
    public Label lblPlayerDetails = Label.xpath("//div[text()='Player Details']");
    private int colFollowDetails = 6;
    public Table tblFollowDetails = Table.xpath("//table[contains(@class,'follow-detail-table')]",colFollowDetails);
    public String txtMarketNameXpath = "//div[text()='%s']//following-sibling::div[1]//input";
    public void createNewPlayer(String groupName, String followStatus, String accountToBet, String additionalStake, String additionalOddRange, String product, String stake, boolean isFollowall) {
        txtPlayerName.isPresent(3);
        txtPlayerName.sendKeys(groupName);
        if (!followStatus.isEmpty()){
            ddbFollowStatus.selectByVisibleText(followStatus);
        }
        ddbAccountToBet.selectByVisibleText(accountToBet);
        txtAdditionalStake.sendKeys(additionalStake);
        txtAdditionalOddsRange.sendKeys(additionalOddRange);
        if (!product.isEmpty()){
            ddbProduct.selectByVisibleText(product);
        }
        txtStake.sendKeys(stake);
        if (isFollowall && !(cbFollowAll.isSelected())){
            cbFollowAll.click();
        }
        btnSave.click();

    }


}
