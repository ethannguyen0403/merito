package backoffice.testcases.system;

import backoffice.pages.bo.system.SmallBetConfigurationPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class SmallBetConfigurationTest extends BaseCaseTest {

    /**
     * @title: Validate can add small bets setting for an agent in Small Bet Configuration in Backoffice
     * @pre-condition: 1. Login BO
     *                 2. Active Small Bets Configuration Page
     *                 3. Have an agent that has not added in the list (08D0000)
     * @steps: 1. Input the agent in precondition into the Agent Textbox and click Add button
     *         2. Input valid data and click Submit (Active, Stake, Accept % of Pricing, Reject Back  Potential Winning >=, Reject Lay If Potential Liability <=)
     * @Verify: 1. Verify Agent is added in the list with correct data as added
     */
    @TestRails(id = "16")
    @Test(groups = {"Regression1"})
    public void BO_System_Small_Bets_Configuration_016() {
        log("@title: Validate can add small bets setting for an agent in Small Bet Configuration in Backoffice");
        log("pre-condition 1: Log in BO");
        log("pre-condition 2: Active Small Bets Configuration Page");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("pre-condition 3: Have an agent that has not added in the list");
        String newAgent = "08D0000";
        String statusAgent = "true";
        String stakeValue = "12";
        String acceptOfPricingValue = "8";
        String rejectBackValue = "5";
        String rejectLayValue = "5";
        log("Step 1. Input the agent in precondition into the Agent Textbox and click Add button");
        smallBetConfigurationPage.inputAgentTextBox(newAgent);
        smallBetConfigurationPage.btnAdd.click();
        log("Step 2. Input valid data and click Submit");
        smallBetConfigurationPage.inputConfigurationSmallBetsForNewAgent(statusAgent, stakeValue, acceptOfPricingValue, rejectBackValue, rejectLayValue);
        smallBetConfigurationPage.btnSubmitOnConfigurationPopup.click();
        log("Verify 1: Verify Agent is added in the list with correct data as added");
        Assert.assertEquals(smallBetConfigurationPage.lblAddSucceed.getText(),"Add Succeed");
        Assert.assertEquals(smallBetConfigurationPage.getAgentIDAdded(newAgent), newAgent);
        Assert.assertEquals(smallBetConfigurationPage.getStatusByAgentID(newAgent), statusAgent);
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Stake", newAgent),stakeValue);
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Accept % of Pricing", newAgent),acceptOfPricingValue);
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Reject Back if Potential Winning", newAgent),rejectBackValue);
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Reject Lay if Potential Liability", newAgent),rejectLayValue);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can add small bets setting for an agent in Small Bet Configuration in Backoffice
     * @pre-condition: 1. Login BO
     *                 2. Active Small Bets Configuration Page
     *                 3. Have an agent added small bet setting (08D0000)
     * @steps: 1. Select the agent in the list
     *         2. Click on Remove icon the last column
     *         3. A confirm message display then click Yes button
     * @Verify: 1. Verify a correct message display when click on Remove icon
     *          2. Agent is remove out the list
     */
    @TestRails(id = "17")
    @Test(groups = {"Regression1"})
    public void BO_System_Small_Bets_Configuration_017() {
        log("@title: Validate can add small bets setting for an agent in Small Bet Configuration in Backoffice");
        log("pre-condition 1: Log in BO");
        log("pre-condition 2: Active Small Bets Configuration Page");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("pre-condition 3: Have an agent added small bet setting");
        String newAgent = "08D0000";
        log("Step 1. Select the agent in the list");
        log("Step 2. Click on Remove icon the last column");
        smallBetConfigurationPage.clickToRemove(newAgent);
        log("Verify 1: Verify a correct message display when click on Remove icon");
        Assert.assertEquals(smallBetConfigurationPage.lblConfirmation.getText(), "Confirmation");
        log("Step 3. A confirm message display then click Yes button");
        smallBetConfigurationPage.btnYes.click();
        log("Verify 2: Agent is remove out the list");
        Assert.assertTrue(smallBetConfigurationPage.isRemovedAgentByAgentID(newAgent));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can not add the existed agent in Small Bet Configuration in Backoffice
     * @pre-condition: 1. Login BO
     *                 2. Active Small Bets Configuration Page
     *                 3. Have an agent added small bet setting
     * @steps: 1. Input the agent in precondition into the Agent Textbox and click Add button
     * @Verify: 1. Verify the error message display "Agent [username] is already added!"
     */
    @TestRails(id = "18")
    @Test(groups = {"Regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_System_Small_Bets_Configuration_018(String satSADAgentLoginID) {
        log("@title: Validate can add small bets setting for an agent in Small Bet Configuration in Backoffice");
        log("pre-condition 1: Log in BO");
        log("pre-condition 2: Active Small Bets Configuration Page");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("pre-condition 3: Have an agent added small bet setting");
        log("Step 1. Input the agent in precondition into the Agent Textbox and click Add button");
        smallBetConfigurationPage.inputAgentTextBox(satSADAgentLoginID);
        smallBetConfigurationPage.btnAdd.click();
        log("Verify 1: Verify the error message display Agent "+satSADAgentLoginID+" is already added!");
        Assert.assertEquals(smallBetConfigurationPage.isAgentIDIsAlreadyAdded(satSADAgentLoginID).getText(), "Agent "+satSADAgentLoginID+" is already added!");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can not add the existed agent in Small Bet Configuration in Backoffice
     * @pre-condition: 1. Login BO
     * @steps: 1. Active Small Bets Configuration Page
     * @Verify: 1. Verify UI is correct:
     *              - Agent label + textbox + Add button
     *              - Note message "Agent code under Fairenter, Funsport and Laystars should not be added."
     *              - Table header "Agent,Staus,Stake,Accept % of Pricing, Reject Back if Potential Winning, Reject Lay if Potential Liability, Action"
     *              - Add button is disable if not input agent
     */
    @TestRails(id = "19")
    @Test(groups = {"Regression"})
    public void BO_System_Small_Bets_Configuration_019() {
        log("@title: Validate can add small bets setting for an agent in Small Bet Configuration in Backoffice");
        log("pre-condition 1: Log in BO");
        log("Step 1: Active Small Bets Configuration Page");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Verify 1: Verify UI is correct");
        Assert.assertEquals(smallBetConfigurationPage.lblAgent.getText(), "Agent:");
        Assert.assertTrue(smallBetConfigurationPage.txbSearchAgentID.isDisplayed());
        Assert.assertFalse(smallBetConfigurationPage.btnAdd.isEnabled());
        Assert.assertEquals(smallBetConfigurationPage.getLabelTableHeader("Agent"),"Agent");
        Assert.assertEquals(smallBetConfigurationPage.getLabelTableHeader("Stake"),"Stake");
        Assert.assertEquals(smallBetConfigurationPage.getLabelTableHeader("Accept % of Pricing"),"Accept % of Pricing");
        Assert.assertEquals(smallBetConfigurationPage.getLabelTableHeader("Reject Back if Potential Winning"),"Reject Back if Potential Winning");
        Assert.assertEquals(smallBetConfigurationPage.getLabelTableHeader("Reject Lay if Potential Liability"),"Reject Lay if Potential Liability");
        Assert.assertEquals(smallBetConfigurationPage.getLabelTableHeader("Action"),"Action");
        Assert.assertEquals(smallBetConfigurationPage.lblmessageShouldNotBeAdd.getText(),"Agent code under Fairenter, Funsport and Laystars should not be added.");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can not add the existed agent in Small Bet Configuration in Backoffice
     * @pre-condition: 1. Login BO
     * @steps: 1. Active Small Bets Configuration Page
     *         2. Input any value
     *         3. Click on Add button
     * @Verify: 1. Verify UI is correct:
     *              - Title is "Configuration Small Bets For Agent [Username]"
     *              - Configuration columns has setting: Stake, Accept % of Pricing, Reject Back if Potential Winning, Reject Lay if Potential Liability
     *              - Input columns has: Status toggle button, and textbox according
     *              - Submit and Close button
     */
    @TestRails(id = "20")
    @Test(groups = {"Regression"})
    public void BO_System_Small_Bets_Configuration_020() {
        log("@title: Validate can add small bets setting for an agent in Small Bet Configuration in Backoffice");
        log("pre-condition 1: Log in BO");
        log("Step 1: Active Small Bets Configuration Page");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Step 2: Input any value");
        String newAgent = "08D0000";
        smallBetConfigurationPage.inputAgentTextBox(newAgent);
        log("Step 3: Click on Add button");
        smallBetConfigurationPage.btnAdd.click();
        log("Verify 1: Verify UI is correct");
        Assert.assertEquals(smallBetConfigurationPage.lblConfigurationSmallBetOnPopup.getText(),"Configuration Small Bets For Agent "+newAgent);
        Assert.assertEquals(smallBetConfigurationPage.getTitleOnConfigurationColumByName("Stake"), "Stake <=");
        Assert.assertEquals(smallBetConfigurationPage.getTitleOnConfigurationColumByName("Accept % of Pricing"), "Accept % of Pricing");
        Assert.assertEquals(smallBetConfigurationPage.getTitleOnConfigurationColumByName("Reject Back if Potential Winning"), "Reject Back if Potential Winning >=");
        Assert.assertEquals(smallBetConfigurationPage.getTitleOnConfigurationColumByName("Reject Lay if Potential Liability"), "Reject Lay if Potential Liability <=");
        Assert.assertTrue(smallBetConfigurationPage.radioButtonStatusAgentOnConfigurationPopup.isDisplayed());
        Assert.assertTrue(smallBetConfigurationPage.txbStakeOnConfigurationPopup.isDisplayed());
        Assert.assertTrue(smallBetConfigurationPage.txbAcceptOnConfigurationPopup.isDisplayed());
        Assert.assertTrue(smallBetConfigurationPage.txbRejectBackOnConfigurationPopup.isDisplayed());
        Assert.assertTrue(smallBetConfigurationPage.txbRejectBackOnConfigurationPopup.isDisplayed());
        Assert.assertTrue(smallBetConfigurationPage.btnSubmitOnConfigurationPopup.isEnabled());
        Assert.assertTrue(smallBetConfigurationPage.btnCloseOnConfigurationPopup.isEnabled());
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can update Stake in Small Bet Configuration in Backoffice
     * @pre-condition: 1. There is an agent is add in Small Bet Configuration
     * @steps: 1. Log in BO > Small Bets Configuration
     *         2. Select agent in precondition and update Stake to a new value then press enter
     *         3. Click yes on confirm popup
     * @Verify: 1. Verify messsage Are you sure to update Min Bet of agent [Username] from [oldValue] INR to [newValue] INR? display
     *          2. Verify message "Update is successful!" and value of Stake that is updated after confirm yes
     */

    @TestRails(id = "41")
    @Test(groups = {"Regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_System_Small_Bets_Configuration_041(String satSADAgentLoginID) {
        log("@title: Validate can update Stake in Small Bet Configuration in Backoffice");
        log("Step 1. Log in BO > Small Bets Configuration");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Step 2. Select agent in precondition and update Stake to a new value then press enter");
        String valueOfStakeCurrent = smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Stake", satSADAgentLoginID);
        String newValue = "15";
        smallBetConfigurationPage.inputTextboxByConfigurationNameAndAgentID("Stake", satSADAgentLoginID, newValue);
        log("Verify 1: Verify messsage Are you sure to update Min Bet of agent "+ satSADAgentLoginID +" from "+valueOfStakeCurrent+" INR to "+newValue+" INR? display");
        Assert.assertEquals(smallBetConfigurationPage.lblAreYouSure.getText(), "Are you sure to update Min Bet of agent "+ satSADAgentLoginID +" from "+valueOfStakeCurrent+" INR to "+newValue+" INR?");
        log("Step 3. Click yes on confirm popup");
        smallBetConfigurationPage.btnYes.click();
        log("Verify 2: Verify message \"Update is successful!\" and value of Stake that is updated after confirm yes");
        Assert.assertEquals(smallBetConfigurationPage.lblUpdateIsSuccessful.getText(),"Update is successful!");
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Stake", satSADAgentLoginID), newValue);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can Not update Stake with No confirm in Small Bet Configuration in Backoffice
     * @pre-condition: 1. There is an agent is add in Small Bet Configuration
     * @steps: 1. Log in BO > Small Bets Configuration
     *         2. Select agent in precondition and update Stake to a new value then press enter
     *         3. A confirm message display then click No
     * @Verify: 1. Verify A confirm message display then click No
     *          2. Verify the confirm popup is disappear
     *          3. Verify Accept % of Pricing is kept as current
     */
    @TestRails(id = "42")
    @Test(groups = {"Regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_System_Small_Bets_Configuration_042(String satSADAgentLoginID) {
        log("@title: Validate can Not update Stake with No confirm in Small Bet Configuration in Backoffice");
        log("Step 1. Log in BO > Small Bets Configuration");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Step 2. Select agent in precondition and update Stake to a new value then press enter");
        String valueOfStakeCurrent = smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Stake", satSADAgentLoginID);
        smallBetConfigurationPage.inputTextboxByConfigurationNameAndAgentID("Stake",satSADAgentLoginID, "30");
        log("Verify 1: Verify A confirm message display");
        Assert.assertEquals(smallBetConfigurationPage.lblConfirmation.getText(),"Confirmation");
        log("Step 3. A confirm message display then click No");
        smallBetConfigurationPage.btnNo.click();
        log("Verify 2: Verify the confirm popup is disappear");
        Assert.assertTrue(smallBetConfigurationPage.lblConfirmation.isInvisible(3));
        log("Verify 3: Verify Accept % of Pricing is kept as current");
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Stake", satSADAgentLoginID), valueOfStakeCurrent);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can update Accept % of Pricing in Small Bet Configuration in Backoffice
     * @pre-condition: 1. There is an agent is add in Small Bet Configuration
     * @steps: 1. Log in BO > Small Bets Configuration
     *         2. Select agent in precondition and update Accept % of Pricing to a new value then press enter
     *         3. Click yes on confirm popup
     * @Verify: 1. Verify messsage Are you sure to update Accept % of Pricing of agent [Username] from [oldValue] % to [newValue] % display?
     *          2. Verify message "Update is successful!" and value of Accept % of Pricing that is updated after confirm yes
     */
    @TestRails(id = "43")
    @Test(groups = {"Regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_System_Small_Bets_Configuration_043(String satSADAgentLoginID) {
        log("@title: Validate can update Accept % of Pricing in Small Bet Configuration in Backoffice");
        log("Step 1. Log in BO > Small Bets Configuration");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Step 2. Select agent in precondition and update Accept % of Pricing to a new value then press enter");
        String valueOfAcceptOfPricingCurrent = smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Accept % of Pricing", satSADAgentLoginID);
        String newValue = "10";
        smallBetConfigurationPage.inputTextboxByConfigurationNameAndAgentID("Accept % of Pricing", satSADAgentLoginID, newValue);
        log("Verify 1: Verify messsage Are you sure to update Accept % of Pricing of agent "+ satSADAgentLoginID +" from "+valueOfAcceptOfPricingCurrent+" % to "+newValue+" % display");
        Assert.assertEquals(smallBetConfigurationPage.lblAreYouSure.getText(), "Are you sure to update Accept % of Pricing of agent " + satSADAgentLoginID + " from " + valueOfAcceptOfPricingCurrent + " % to "+newValue+" %?");
        log("Step 3. Click yes on confirm popup");
        smallBetConfigurationPage.btnYes.click();
        log("Verify 2: Verify message \"Update is successful!\" and value of Accept % of Pricing that is updated after confirm yes");
        Assert.assertEquals(smallBetConfigurationPage.lblUpdateIsSuccessful.getText(),"Update is successful!");
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Accept % of Pricing", satSADAgentLoginID), newValue);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can NOT update Accept % of Pricing with No confirm in Small Bet Configuration in Backoffice
     * @pre-condition: 1. There is an agent is add in Small Bet Configuration
     * @steps: 1. Log in BO > Small Bets Configuration
     *         2. Select agent in precondition and update Accept % of Pricing to a new value then press enter
     *         3. A confirm message display then click No
     * @Verify: 1. Verify A confirm message display then click No
     *          2. Verify the confirm popup is disappear
     *          3. Verify Accept % of Pricing is kept as current
     */
    @TestRails(id = "44")
    @Test(groups = {"Regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_System_Small_Bets_Configuration_044(String satSADAgentLoginID) {
        log("@title: Validate can NOT update Accept % of Pricing with No confirm in Small Bet Configuration in Backoffice");
        log("Step 1. Log in BO > Small Bets Configuration");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Step 2. Select agent in precondition and update Accept % of Pricing to a new value then press enter");
        String valueOfAcceptOfPricingCurrent = smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Accept % of Pricing", satSADAgentLoginID);
        smallBetConfigurationPage.inputTextboxByConfigurationNameAndAgentID("Accept % of Pricing", satSADAgentLoginID,"15");
        log("Verify 1: Verify A confirm message display");
        Assert.assertEquals(smallBetConfigurationPage.lblConfirmation.getText(),"Confirmation");
        log("Step 3. A confirm message display then click No");
        smallBetConfigurationPage.btnNo.click();
        log("Verify 2: Verify the confirm popup is disappear");
        Assert.assertTrue(smallBetConfigurationPage.lblConfirmation.isInvisible(3));
        log("Verify 3: Verify Accept % of Pricing is kept as current");
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Accept % of Pricing", satSADAgentLoginID),valueOfAcceptOfPricingCurrent);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can update Reject Back if Potential Winning in Small Bet Configuration in Backoffice
     * @pre-condition: 1. There is an agent is add in Small Bet Configuration
     * @steps: 1. Log in BO > Small Bets Configuration
     *         2. Select agent in precondition and update Reject Back if Potential Winning to a new value then press enter
     *         3. Click yes on confirm popup
     * @Verify: 1. Verify messsage "Are you sure to update Reject Back if Potential Winning of agent [Username] from [oldValue] times to [newValue] times?" displays
     *          2. Verify message "Update is successful!" and value of Reject Back if Potential Winning that is updated after confirm yes
     */
    @TestRails(id = "45")
    @Test(groups = {"Regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_System_Small_Bets_Configuration_045(String satSADAgentLoginID) {
        log("@title: Validate can update Reject Back if Potential Winning in Small Bet Configuration in Backoffice");
        log("Step 1. Log in BO > Small Bets Configuration");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Step 2. Select agent in precondition and update Reject Back if Potential Winning to a new value then press enter");
        String valueOfRejectBackIfPotentialWinningCurrent = smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Reject Back if Potential Winning", satSADAgentLoginID);
        String newValue = "5";
        smallBetConfigurationPage.inputTextboxByConfigurationNameAndAgentID("Reject Back if Potential Winning", satSADAgentLoginID,newValue);
        log("Verify 1: Verify messsage Are you sure to update Reject Back if Potential Winning of agent "+satSADAgentLoginID+" from "+valueOfRejectBackIfPotentialWinningCurrent+" % to "+newValue+" %?");
        Assert.assertEquals(smallBetConfigurationPage.lblAreYouSure.getText(),"Are you sure to update Reject Back if Potential Winning of agent "+satSADAgentLoginID+" from "+valueOfRejectBackIfPotentialWinningCurrent+" times to "+newValue+" times?");
        log("Step 3. Click yes on confirm popup");
        smallBetConfigurationPage.btnYes.click();
        log("Verify 2: Verify message \"Update is successful!\" and value of Reject Back if Potential Winning that is updated after confirm yes");
        Assert.assertEquals(smallBetConfigurationPage.lblUpdateIsSuccessful.getText(),"Update is successful!");
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Reject Back if Potential Winning", satSADAgentLoginID),newValue);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can NOT update Reject Back if Potential Winning No confirm in Small Bet Configuration in Backoffice
     * @pre-condition: 1. There is an agent is add in Small Bet Configuration
     * @steps: 1. Log in BO > Small Bets Configuration
     *         2. Select agent in precondition and update Reject Back if Potential Winning to a new value then press enter
     *         3. A confirm message display then click No
     * @Verify: 1. Verify A confirm message display then click No
     *          2. Verify the confirm popup is disappear
     *          3. Verify Reject Back if Potential Winning is kept as current
     */
    @TestRails(id = "46")
    @Test(groups = {"Regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_System_Small_Bets_Configuration_046(String satSADAgentLoginID) {
        log("@title: Validate can NOT update Reject Back if Potential Winning No confirm in Small Bet Configuration in Backoffice");
        log("Step 1. Log in BO > Small Bets Configuration");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Step 2. Select agent in precondition and update Reject Back if Potential Winning to a new value then press enter");
        String valueOfRejectBackIfPotentialWinningCurrent = smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Reject Back if Potential Winning", satSADAgentLoginID);
        smallBetConfigurationPage.inputTextboxByConfigurationNameAndAgentID("Reject Back if Potential Winning", satSADAgentLoginID,"15");
        log("Verify 1: Verify A confirm message display");
        Assert.assertEquals(smallBetConfigurationPage.lblConfirmation.getText(),"Confirmation");
        log("Step 3. A confirm message display then click No");
        smallBetConfigurationPage.btnNo.click();
        log("Verify 2: Verify the confirm popup is disappear");
        Assert.assertTrue(smallBetConfigurationPage.lblConfirmation.isInvisible(3));
        log("Verify 3: Reject Back if Potential Winning is kept as current");
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Reject Back if Potential Winning", satSADAgentLoginID),valueOfRejectBackIfPotentialWinningCurrent);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can update Reject Lay if Potential Liability in Small Bet Configuration in Backoffice
     * @pre-condition: 1. There is an agent is add in Small Bet Configuration
     * @steps: 1. Log in BO > Small Bets Configuration
     *         2. Select agent in precondition and update Reject Lay if Potential Liability to a new value then press enter
     *         3. Click yes on confirm popup
     * @Verify: 1. Verify messsage Are you sure to update Reject Lay if Potential Liability of agent [Username] from [oldValue] times to [newValue] times?" displays
     *          2. Verify message "Update is successful!" displays and value of Reject Lay if Potential Liability that is updated after confirm yes
     */
    @TestRails(id = "47")
    @Test(groups = {"Regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_System_Small_Bets_Configuration_047(String satSADAgentLoginID) {
        log("@title: Validate can update Reject Lay if Potential Liability in Small Bet Configuration in Backoffice");
        log("Step 1. Log in BO > Small Bets Configuration");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Step 2. Select agent in precondition and update Reject Lay if Potential Winning to a new value then press enter");
        String valueOfRejectLayIfPotentialLiabilityCurrent = smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Reject Lay if Potential Liability", satSADAgentLoginID);
        String newValue = "5";
        smallBetConfigurationPage.inputTextboxByConfigurationNameAndAgentID("Reject Lay if Potential Liability", satSADAgentLoginID,newValue);
        log("Verify 1: Verify messsage Are you sure to update Reject Lay if Potential Liability of agent "+satSADAgentLoginID+" from "+valueOfRejectLayIfPotentialLiabilityCurrent+" times to "+newValue+" times?");
        Assert.assertEquals(smallBetConfigurationPage.lblAreYouSure.getText(),"Are you sure to update Reject Lay if Potential Liability of agent "+satSADAgentLoginID+" from "+valueOfRejectLayIfPotentialLiabilityCurrent+" times to "+newValue+" times?");
        log("Step 3. Click yes on confirm popup");
        smallBetConfigurationPage.btnYes.click();
        log("Verify 2: Verify message \"Update is successful!\" and value of Reject Lay if Potential Liability that is updated after confirm yes");
        Assert.assertEquals(smallBetConfigurationPage.lblUpdateIsSuccessful.getText(),"Update is successful!");
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Reject Lay if Potential Liability", satSADAgentLoginID),newValue);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can NOT update Reject Lay if Potential Liability No confirm in Small Bet Configuration in Backoffice
     * @pre-condition: 1. There is an agent is add in Small Bet Configuration
     * @steps: 1. Log in BO > Small Bets Configuration
     *         2. Select agent in precondition and update Reject Lay if Potential Liability to a new value then press enter
     *         3. A confirm message display then click No
     * @Verify: 1. Verify A confirm message display then click No
     *          2. Verify the confirm popup is disappear
     *          3. Reject Lay if Potential Liability is kept as current
     */
    @TestRails(id = "48")
    @Test(groups = {"Regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_System_Small_Bets_Configuration_048(String satSADAgentLoginID) {
        log("@title: Validate can NOT update Reject Lay if Potential Liability No confirm in Small Bet Configuration in Backoffice");
        log("Step 1. Log in BO > Small Bets Configuration");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Step 2. Select agent in precondition and update Reject Lay if Potential Liability to a new value then press enter");
        String valueOfRejectLayIfPotentialLiabilityCurrent = smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Reject Lay if Potential Liability", satSADAgentLoginID);
        smallBetConfigurationPage.inputTextboxByConfigurationNameAndAgentID("Reject Lay if Potential Liability", satSADAgentLoginID,"15");
        log("Verify 1: A confirm message display");
        Assert.assertEquals(smallBetConfigurationPage.lblConfirmation.getText(),"Confirmation");
        log("Step 3. A confirm message display then click No");
        smallBetConfigurationPage.btnNo.click();
        log("Verify 2: Verify the confirm popup is disappear");
        Assert.assertTrue(smallBetConfigurationPage.lblConfirmation.isInvisible(3));
        log("Verify 3: Reject Lay if Potential Liability is kept as current");
        Assert.assertEquals(smallBetConfigurationPage.getValueTextBoxByConfigurationNameAndAgentID("Reject Lay if Potential Liability", satSADAgentLoginID),valueOfRejectLayIfPotentialLiabilityCurrent);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate message error display when add none exist agent in Small Bet Configuration in Backoffice
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Small Bets Configuration
     *         2. Input a inavlid agent and click Add button
     * @Verify: 1. Verify message "Agent does not exist in the system" display
     */
    @TestRails(id = "49")
    @Test(groups = {"Regression"})
    public void BO_System_Small_Bets_Configuration_049() {
        log("@title: Validate message error display when add none exist agent in Small Bet Configuration in Backoffice");
        log("Step 1. Access Small Bets Configuration");
        SmallBetConfigurationPage smallBetConfigurationPage = backofficeHomePage.navigateSmallBetConfiguration();
        log("Step 2. Input a inavlid agent and click Add button");
        smallBetConfigurationPage.inputAgentTextBox("023R213E");
        smallBetConfigurationPage.btnAdd.click();
        log("Verify 1: Verify message \"Agent does not exist in the system!\" display");
        Assert.assertEquals(smallBetConfigurationPage.lblErrorAgentDoesNotExist.getText(),"Agent does not exist in the system!");
        log("INFO: Executed completely");
    }
}
