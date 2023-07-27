package backoffice.pages.bo.system;

import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.*;

import java.util.ArrayList;
import java.util.List;

public class SmallBetConfigurationPage extends HomePage {
    String acceptOfPricingByAgentIDXpath = "//div[contains(text(),'%s')]//following::input[2]";
    String stakeByAgentIDXpath = "//div[contains(text(),'%s')]//following::input[1]";
    String rejectBackIfPotentialWinningByAgentIDXpath = "//div[contains(text(),'%s')]//following::input[3]";
    String rejectLayIfPotentialLiabilityByAgentIDXpath = "//div[contains(text(),'%s')]//following::input[4]";
    public Button btnYes = Button.xpath("//button[text()='Yes']");
    public Button btnNo = Button.xpath("//button[text()='No']");
    public Label lblConfirmation = Label.xpath("//span[contains(text(),'Confirmation')]");
    public Label lblAreYouSure = Label.xpath("//h6");
    public TextBox txbSearchAgentID = TextBox.xpath("//div[@class='input-group input-group-sm']//input");
    public Button btnAdd = Button.xpath("//button[text()='Add']");
    public RadioButton radioButtonStatusAgentOnConfigurationPopup = RadioButton.xpath("//div[@class='modal-body']//button[@role='switch']");
    public TextBox txbStakeOnConfigurationPopup = TextBox.xpath("//div[contains(text(),'Stake')]/parent::div//input");
    public TextBox txbAcceptOnConfigurationPopup = TextBox.xpath("//div[contains(text(),'Accept')]/parent::div//input");
    public TextBox txbRejectBackOnConfigurationPopup = TextBox.xpath("//div[contains(text(),'Back')]/parent::div//input");
    public TextBox txbRejectLayOnConfigurationPopup = TextBox.xpath("//div[contains(text(),'Lay')]/parent::div//input");
    public Button btnSubmitOnConfigurationPopup = Button.xpath("//button[text()='Submit']");
    public Button btnCloseOnConfigurationPopup = Button.xpath("//button[text()='Close']");
    String btnRemoveAgentByAgentIDXpath = "//div[contains(text(),'%s')]/parent::div//a[text()='Remove']";
    public Label lblAgent = Label.xpath("//div[text()='Agent:']");
    String configurationColumsXpath = "//div[contains(text(),'%s')]";
    public Label lblmessageShouldNotBeAdd = Label.xpath("//label[text()='Agent code under Fairenter, Funsport and Laystars should not be added.']");
    public Label lblConfigurationSmallBetOnPopup = Label.cssSelector("h5");
    public StaticTable tblReport = StaticTable.xpath("//div[@class='custom-table currency-table']", "div[@class='ps-content']", "div[@class='custom-table-row']",
            "div[@class='custom-table-cell']",100);
    String txbStakeByAgentIDXpath = "//div[text()=' %s ']/following-sibling::div[contains(@class,'custom-table-cell')][2]//input";
    String txbAcceptByAgentIDXpath = "//div[text()=' %s ']/following-sibling::div[contains(@class,'custom-table-cell')][3]//input";
    String txbRejectBackByAgentIDXpath = "//div[text()=' %s ']/following-sibling::div[contains(@class,'custom-table-cell')][4]//input";
    String txbRejectLayByAgentIDXpath = "//div[text()=' %s ']/following-sibling::div[contains(@class,'custom-table-cell')][5]//input";
    int colAgent = 1;

    public void inputTextboxByConfigurationNameAndAgentID(String configurationName, String agentID, String number){
        switch (configurationName){
            case "Stake":
                TextBox txbStake = TextBox.xpath(String.format(stakeByAgentIDXpath,agentID));
                txbStake.sendKeys(number);
                break;
            case "Accept % of Pricing":
                TextBox txbAcceptOfPricing =  TextBox.xpath(String.format(acceptOfPricingByAgentIDXpath,agentID));
                txbAcceptOfPricing.sendKeys(number);
                break;
            case "Reject Back if Potential Winning":
                TextBox txbRejectBackIfPotentialWinning = TextBox.xpath(String.format(rejectBackIfPotentialWinningByAgentIDXpath,agentID));
                txbRejectBackIfPotentialWinning.sendKeys(number);
                break;
            case "Reject Lay if Potential Liability":
                TextBox txbRejectLayIfPotentialLiability = TextBox.xpath(String.format(rejectLayIfPotentialLiabilityByAgentIDXpath,agentID));
                txbRejectLayIfPotentialLiability.sendKeys(number);
                break;
            default:
                System.out.println("Input configuration name wrongly");
        }
        txbSearchAgentID.click();
    }
    public boolean isAgentInfoUpdatedCorrect(String agentID, String statusAgent, String stakeAgent, String acceptPerOfPricing, String rejectBackIfProtentialWin, String rejectLayIfPotentialLiabilty){
        List<ArrayList<String>> lstData = tblReport.getRows(5,false);
        for (int i = 0; i < lstData.size(); i++){
            String agent = lstData.get(i).get(colAgent - 1);
            if (agent.equals(agentID)){
                String status = lstData.get(i).get(1);
                String stake = TextBox.xpath(String.format(txbStakeByAgentIDXpath,agent)).getAttribute("value");
                String acceptPer = TextBox.xpath(String.format(txbAcceptByAgentIDXpath,agent)).getAttribute("value");
                String rejectBack = TextBox.xpath(String.format(txbRejectBackByAgentIDXpath,agent)).getAttribute("value");
                String rejecLay = TextBox.xpath(String.format(txbRejectLayByAgentIDXpath,agent)).getAttribute("value");
                if (!statusAgent.isEmpty() && !status.equals(statusAgent)) {
                    System.out.println("Status of "+ agentID +" is wrong!");
                    return false;
                }
                if (!stakeAgent.isEmpty() && !stake.equals(stakeAgent)){
                    System.out.println("Stake of "+ agentID +" is wrong!");
                    return false;
                }
                if (!acceptPerOfPricing.isEmpty() && !acceptPer.equals(acceptPerOfPricing)){
                    System.out.println("Accept Per Of Pricing of "+ agentID +" is wrong!");
                    return false;
                }
                if (!rejectBackIfProtentialWin.isEmpty() && !rejectBack.equals(rejectBackIfProtentialWin)){
                    System.out.println("Reject Back If Protential Win of "+ agentID +" is wrong!");
                    return false;
                }
                if (!rejectLayIfPotentialLiabilty.isEmpty() && !rejecLay.equals(rejectLayIfPotentialLiabilty)){
                    System.out.println("Reject Lay If Potential Liabilty of "+ agentID +" is wrong!");
                    return false;
                }
                return true;
            }
        }
        System.out.println("Agent is not exist");
        return false;
    }

    public String getValueTextBoxByConfigurationNameAndAgentID(String configurationName, String agentID){
        switch (configurationName){
            case "Stake":
                return TextBox.xpath(String.format(stakeByAgentIDXpath,agentID)).getAttribute("value");
            case "Accept % of Pricing":
                return TextBox.xpath(String.format(acceptOfPricingByAgentIDXpath,agentID)).getAttribute("value");
            case "Reject Back if Potential Winning":
                return TextBox.xpath(String.format(rejectBackIfPotentialWinningByAgentIDXpath,agentID)).getAttribute("value");
            case "Reject Lay if Potential Liability":
                return TextBox.xpath(String.format(rejectLayIfPotentialLiabilityByAgentIDXpath,agentID)).getAttribute("value");
            default:
                System.out.println("Input configuration name wrongly");
        }
        return null;
    }

    public void inputAgentTextBox(String agentID) {
        txbSearchAgentID.sendKeys(agentID);
        btnAdd.click();
    }
    private void clickStatusAgentOnConfigurationPopup(String statusAgent){
        if (statusAgent == "ON" && radioButtonStatusAgentOnConfigurationPopup.getAttribute("aria-checked").contains("false")){
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
        btnSubmitOnConfigurationPopup.click();
    }

    public void clickToRemove(String agentID) {
        Button.xpath(String.format(btnRemoveAgentByAgentIDXpath, agentID)).click();
        btnYes.click();
    }

    public String getTitleOnConfigurationColumByName(String name){
        return Label.xpath(String.format(configurationColumsXpath, name)).getText();
    }
}
