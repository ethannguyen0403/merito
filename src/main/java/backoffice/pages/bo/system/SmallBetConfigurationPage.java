package backoffice.pages.bo.system;

import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.RadioButton;
import com.paltech.element.common.TextBox;
import net.bytebuddy.asm.Advice;

public class SmallBetConfigurationPage extends HomePage {
    String agentRowByAgentID = "//div[contains(text(),'%s')]";
    String acceptOfPricingByAgentID = "//div[contains(text(),'%s')]//following::input[2]";
    String stakeByAgentID = "//div[contains(text(),'%s')]//following::input[1]";
    String rejectBackIfPotentialWinningByAgentID = "//div[contains(text(),'%s')]//following::input[3]";
    String rejectLayIfPotentialLiabilityByAgentID = "//div[contains(text(),'%s')]//following::input[4]";
    public Button btnYes = Button.xpath("//button[text()='Yes']");
    public Button btnNo = Button.xpath("//button[text()='No']");
    public Label lblUpdateIsSuccessful = Label.xpath("//span[contains(text(),'Update is successful!')]");
    public Label lblConfirmation = Label.xpath("//span[contains(text(),'Confirmation')]");
    public Label lblAreYouSure = Label.xpath("//h6");
    public TextBox txbSearchAgentID = TextBox.xpath("//div[@class='input-group input-group-sm']//input");
    public Button btnAdd = Button.xpath("//button[text()='Add']");
    public Label lblErrorAgentDoesNotExist = Label.xpath("//span[text()='Agent does not exist in the system!']");
    public RadioButton radioButtonStatusAgentOnConfigurationPopup = RadioButton.xpath("//div[@class='modal-body']//button[@role='switch']");
    public TextBox txbStakeOnConfigurationPopup = TextBox.xpath("//div[contains(text(),'Stake')]/parent::div//input");
    public TextBox txbAcceptOnConfigurationPopup = TextBox.xpath("//div[contains(text(),'Accept')]/parent::div//input");
    public TextBox txbRejectBackOnConfigurationPopup = TextBox.xpath("//div[contains(text(),'Back')]/parent::div//input");
    public TextBox txbRejectLayOnConfigurationPopup = TextBox.xpath("//div[contains(text(),'Lay')]/parent::div//input");
    public Button btnSubmitOnConfigurationPopup = Button.xpath("//button[text()='Submit']");
    public Button btnCloseOnConfigurationPopup = Button.xpath("//button[text()='Close']");
    public Label lblAddSucceed = Label.xpath("//span[text()='Add Succeed']");
    String statusByAgentID = "//div[contains(text(),'%s')]/parent::div//button";
    String btnRemoveAgentByAgentID = "//div[contains(text(),'%s')]/parent::div//a[text()='Remove']";
    String agentIDIsAlreadyAdded = "//span[text()='Agent %s is already added!']";
    public Label lblAgent = Label.xpath("//div[text()='Agent:']");
    String tableHeader = "//span[text()='%s']";
    String configurationColums = "//div[contains(text(),'%s')]";
    public Label lblmessageShouldNotBeAdd = Label.xpath("//label[text()='Agent code under Fairenter, Funsport and Laystars should not be added.']");
    public Label lblConfigurationSmallBetOnPopup = Label.cssSelector("h5");

    public void inputTextboxByConfigurationNameAndAgentID(String configurationName, String agentID, String number){
        switch (configurationName){
            case "Stake":
                TextBox txbStake = TextBox.xpath(String.format(stakeByAgentID,agentID));
                txbStake.sendKeys(number);
                break;
            case "Accept % of Pricing":
                TextBox txbAcceptOfPricing =  TextBox.xpath(String.format(acceptOfPricingByAgentID,agentID));
                txbAcceptOfPricing.sendKeys(number);
                break;
            case "Reject Back if Potential Winning":
                TextBox txbRejectBackIfPotentialWinning = TextBox.xpath(String.format(rejectBackIfPotentialWinningByAgentID,agentID));
                txbRejectBackIfPotentialWinning.sendKeys(number);
                break;
            case "Reject Lay if Potential Liability":
                TextBox txbRejectLayIfPotentialLiability = TextBox.xpath(String.format(rejectLayIfPotentialLiabilityByAgentID,agentID));
                txbRejectLayIfPotentialLiability.sendKeys(number);
                break;
            default:
                System.out.println("Input configuration name wrongly");
        }
        txbSearchAgentID.click();
    }

    public String getValueTextBoxByConfigurationNameAndAgentID(String configurationName, String agentID){
        switch (configurationName){
            case "Stake":
                return TextBox.xpath(String.format(stakeByAgentID,agentID)).getAttribute("value");
            case "Accept % of Pricing":
                return TextBox.xpath(String.format(acceptOfPricingByAgentID,agentID)).getAttribute("value");
            case "Reject Back if Potential Winning":
                return TextBox.xpath(String.format(rejectBackIfPotentialWinningByAgentID,agentID)).getAttribute("value");
            case "Reject Lay if Potential Liability":
                return TextBox.xpath(String.format(rejectLayIfPotentialLiabilityByAgentID,agentID)).getAttribute("value");
            default:
                System.out.println("Input configuration name wrongly");
        }
        return null;
    }

    public void inputAgentTextBox(String agentID) {
        txbSearchAgentID.sendKeys(agentID);
    }
    private void clickStatusAgentOnConfigurationPopup(String statusAgent){
        if (statusAgent == "true" && radioButtonStatusAgentOnConfigurationPopup.getAttribute("aria-checked").contains("false")){
                radioButtonStatusAgentOnConfigurationPopup.click();
        } else {
            if (radioButtonStatusAgentOnConfigurationPopup.getAttribute("aria-checked").contains("true")){
                radioButtonStatusAgentOnConfigurationPopup.click();
            }
        }
    }
    public void inputConfigurationSmallBetsForNewAgent(String statusAgent, String stakeValue, String acceptOfPricingValue, String rejectBackValue, String rejectLayValue) {
        clickStatusAgentOnConfigurationPopup(statusAgent);
        if (!stakeValue.isEmpty()){
            txbStakeOnConfigurationPopup.sendKeys(stakeValue);
        }
        if (!acceptOfPricingValue.isEmpty()){
            txbAcceptOnConfigurationPopup.sendKeys(acceptOfPricingValue);
        }
        if (!rejectBackValue.isEmpty()){
            txbRejectBackOnConfigurationPopup.sendKeys(rejectBackValue);
        }
        if (!rejectLayValue.isEmpty()){
            txbRejectLayOnConfigurationPopup.sendKeys(rejectLayValue);
        }
    }

    public String getAgentIDAdded(String agentID) {
        return Label.xpath(String.format(agentRowByAgentID, agentID)).getText();
    }

    public String getStatusByAgentID(String agentID){
        return RadioButton.xpath(String.format(statusByAgentID, agentID)).getAttribute("aria-checked");
    }

    public boolean isRemovedAgentByAgentID (String agentID){
        Label removeAgent = Label.xpath(String.format(agentRowByAgentID, agentID));
        return removeAgent.isInvisible(3);
    }

    public void clickToRemove(String agentID) {
        Button.xpath(String.format(btnRemoveAgentByAgentID, agentID)).click();
    }

    public Label isAgentIDIsAlreadyAdded(String agentID) {
        return Label.xpath(String.format(agentIDIsAlreadyAdded, agentID));
    }
    public String getLabelTableHeader(String headerTable){
        return Label.xpath(String.format(tableHeader, headerTable)).getText();
    }
    public String getTitleOnConfigurationColumByName(String name){
        return Label.xpath(String.format(configurationColums, name)).getText();
    }
}
