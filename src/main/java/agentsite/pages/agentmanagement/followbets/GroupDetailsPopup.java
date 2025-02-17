package agentsite.pages.agentmanagement.followbets;

import com.paltech.element.common.*;

public class GroupDetailsPopup {

    public TextBox txtGroupName = TextBox.id("groupName");
    public TextBox txtAdditionalStake = TextBox.xpath("//label[contains(text(),'Additional Stake')]//following::input[1]");
    public TextBox txtAdditionalOddsRange = TextBox.xpath("//label[contains(text(),'Additional Odds Range')]//following::input[1]");
    public TextBox txtStake = TextBox.xpath("//label[text()='Stake %']//following::input[1]");
    public CheckBox cbFollowAll = CheckBox.id("followAllBets");
    public DropDownBox ddbFollowStatus = DropDownBox.xpath("//label[contains(text(),'Follow Status')]//following::select[1]");
    public DropDownBox ddbAccountToBet = DropDownBox.xpath("//label[contains(text(),'Account To Bet')]//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//label[contains(text(),'Product')]//following::select[1]");
    public Button btnSave = Button.xpath("//button[@class='pbtn icon-pointer' and text()='Save'] ");
    public Button btnCancel = Button.xpath("//button[contains(@class,'cancel')]");
    public Label lblGroupDetails = Label.xpath("//div[text()='Group Details']");

    public void createNewGroup(String groupName, String followStatus, String accountToBet, String additionalStake, String additionalOddRange, String product, String stake, boolean isFollowall) {
        txtGroupName.isPresent(3);
        txtGroupName.sendKeys(groupName);
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
